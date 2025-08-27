# MySQL

## 流式查询**（Streaming Query）**

优点：大数据量时不会有OOM问题。
缺点：占用数据库时间更长，导致网络拥塞的可能性较大。

- ‌**逐行加载**‌：通过 JDBC 的 `ResultSet` 流式处理，每次从数据库取一行数据，避免一次性加载所有数据到内存。
- ‌**内存优化**‌：适用于大数据量（如百万级）导出或处理，防止 OOM（内存溢出）。
- ‌**实现方式**‌：使用 MyBatis 的 `ResultHandler` 接口逐行处理结果。

**‌实现步骤‌**

1. ‌**Mapper 接口定义**‌：

   ```java
   @Mapper
   public interface UserMapper {
       void selectStreaming(@Param(Constants.WRAPPER) QueryWrapper<User> wrapper, ResultHandler<User> handler);
   }
   ```

   

2. ‌**Service 层调用**‌：

   ```java
   @Service
   public class UserService {
       @Autowired
       private UserMapper userMapper;
   
       public void processLargeData() {
           QueryWrapper<User> wrapper = new QueryWrapper<>();
           wrapper.gt("id", 1000);
   
           userMapper.selectStreaming(wrapper, new ResultHandler<User>() {
               @Override
               public void handleResult(ResultContext<? extends User> resultContext) {
                   User user = resultContext.getResultObject();
                   // 逐行处理逻辑（如写入文件、发送消息等）
                   System.out.println(user);
               }
           });
       }
   }
   ```

3. ‌**关键配置（application.yml）**‌：

   ```xml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/test?useCursorFetch=true&defaultFetchSize=1000
   ```

   - `useCursorFetch=true`：启用 MySQL 游标读取（必须设置）。
   - `defaultFetchSize=1000`：每次网络传输的行数（按需调整）。







## 游标查询（Cursor Query）

**结论** : 查询百万数据并导出，游标查询效率 远大于 分页查询，游标查询总耗时5s，分页查询102s，加上实际导出时间，游标查询并导出耗时16s，分页查询导出耗时118s

优点：大数据量时不会有OOM问题，相比流式查询对数据库单次占用时间较短。
缺点：相比流式查询，对服务端资源消耗更大，响应时间更长。

‌**核心原理**‌

- ‌**惰性加载**‌：返回一个 `Cursor` 对象，遍历时才从数据库读取数据，避免内存一次性加载。
- ‌**资源管理**‌：需手动关闭游标，防止数据库连接泄漏。
- ‌**实现方式**‌：MyBatis 提供 `Cursor<T>` 接口，基于数据库游标实现。

‌**实现步骤**‌

1. ‌**Mapper 接口定义**‌：

   ```java
   @Mapper
   public interface UserMapper {
       Cursor<User> selectCursor(@Param(Constants.WRAPPER) QueryWrapper<User> wrapper);
   }
   ```

   

2. ‌**Service 层调用**‌：

   ```java
   @Service
   public class UserService {
       @Autowired
       private UserMapper userMapper;
   
       @Transactional(propagation = Propagation.NOT_SUPPORTED)  // 禁止事务（避免连接提前关闭）
       public void processLargeDataWithCursor() {
           QueryWrapper<User> wrapper = new QueryWrapper<>();
           wrapper.gt("id", 1000);
   
           try (Cursor<User> cursor = userMapper.selectCursor(wrapper)) {
               cursor.forEach(user -> {
                   // 逐行处理逻辑
                   System.out.println(user);
               });
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }
   }
   ```

   

3. ‌**关键配置（同流式查询）**‌：

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/test?useCursorFetch=true&defaultFetchSize=1000
   ```





## 存储过程

​		**存储过程是事先经过编译并存储在数据库中的一段SOL语句的集合，调用存储过程可以简化应用开发人员的很多工作，减少数据在数据库和应用服务器之间的传输，对于提高数据处理的效率是有好处的。存储过程思想上很简单，就是数据库 SOL语言层面的代码封装与重用。**

存储过程思想上很简单，就是[数据库 SQL](https://so.csdn.net/so/search?q=数据库 SQL&spm=1001.2101.3001.7020) 语言层面的代码封装与重用。

csdn存储过程文档↓↓↓↓↓↓

https://ae.aiiz.cn/VaATpf

**存储过程中的关键语法**

```sql
DELIMITER //  

CREATE PROCEDURE CalculateSquare(IN num INT, OUT result INT)  
BEGIN  
    SET result = num * num;  
END //  

DELIMITER ;
```

> - **DELIMITER 用于更改命令结束符，以便在存储过程中使用 BEGIN ... END 语句。通常，我们使用 // 作为新的结束符，并在存储过程定义结束后将其改回 ;。**
> - **CREATE PROCEDURE 用于创建新的存储过程。**
> - **CalculateSquare 是存储过程的名称。**
> - **(IN num INT, OUT result INT) 定义了输入和输出参数。在这个例子中，num 是一个输入参数，result 是一个输出参数。**
> - **BEGIN ... END 之间的部分是存储过程的主体，即要执行的SQL语句。**



**调用存储过程**

要调用上述存储过程并获取结果，你需要使用 `CALL` 语句，并指定一个变量来接收输出参数的值：

```sql
SET @input = 5;  

SET @output = 0;  

CALL CalculateSquare(@input, @output);  

SELECT @output;  -- 输出应该是 25
```



**存储过程的参数** 

 MySQL存储过程的参数用在存储过程的定义，共有三种参数类型,IN,OUT,INOUT,形式如：

```sql
CREATEPROCEDURE 存储过程名([[IN |OUT |INOUT ] 参数名 数据类形...])
```

- IN 输入参数：表示调用者向过程传入值（传入值可以是字面量或变量）
- OUT 输出参数：表示过程向调用者传出值(可以返回多个值)（传出值只能是变量）
- INOUT 输入输出参数：既表示调用者向过程传入值，又表示过程向调用者传出值（值只能是变量）



**springBoot项目中mybatis_plus框架调用mysql存储过程**

 **创建 MySQL 存储过程**‌

假设存储过程 `sp_get_user_info` 接收一个 `IN` 参数（用户 ID），返回一个 `OUT` 参数（用户年龄）和一个结果集（用户信息）：

```sql
DELIMITER $$
CREATE PROCEDURE sp_get_user_info(
    IN userId INT,
    OUT userAge INT
)
BEGIN
    SELECT age INTO userAge FROM user WHERE id = userId;  -- 设置OUT参数
    SELECT * FROM user WHERE id = userId;                -- 返回结果集
END $$
DELIMITER ;
```

**Mapper 接口定义**‌

‌**方式一：注解方式（推荐）**‌

使用 `@Options` 和 `@Select` 注解直接调用存储过程，并通过 `Map` 传递参数‌15：

```java
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Options(statementType = StatementType.CALLABLE)  // 必须声明为CALLABLE‌:ml-citation{ref="7" data="citationList"}
    @Select("{CALL sp_get_user_info(#{userId, mode=IN}, #{age, mode=OUT, jdbcType=INTEGER})}")
    void callUserProcedure(Map<String, Object> params);
}
```

**方式二：XML 配置**

在 XML 文件中定义存储过程调用逻辑‌34：

```
<!-- UserMapper.xml -->
<select id="callUserProcedure" statementType="CALLABLE" resultType="com.example.entity.User">
    {CALL sp_get_user_info(
        #{userId, mode=IN, jdbcType=INTEGER},
        #{age, mode=OUT, jdbcType=INTEGER}
    )}
</select>
```

```java
public interface UserMapper extends BaseMapper<User> {
    List<User> callGetUserById(Long userId);
}
```

**Service 层调用**‌

通过 `Map` 传递参数并获取 `OUT` 值‌35：

```java
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserInfoWithAge(Integer userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        
        // 调用存储过程
        userMapper.callUserProcedure(params);
        
        // 获取OUT参数值
        Integer age = (Integer) params.get("age");
        
        // 获取结果集（假设存储过程返回结果集）
        List<User> users = (List<User>) params.get("resultSet");
        User user = users.get(0);
        user.setAge(age);
        
        return user;
    }
}
```



## 三范式

- 第一范式：数据表中的每一列（每个字段）都不可以再拆分。例如用户表，用户地址还可以拆分成国家、省份、市，这样才是符合第一范式的。
- 第二范式：在第一范式的基础上，非主键列完全依赖于主键，而不能是依赖于主键的一部分。例如订单表里，存储了商品信息（商品价格、商品类型），那就需要把商品 ID 和订单 ID 作为联合主键，才满足第二范式。
- 第三范式：在满足第二范式的基础上，表中的非主键只依赖于主键，而不依赖于其他非主键。例如订单表，就不能存储用户信息（姓名、地址）。

| ‌**范式**‌ | 核心原则     | 解决的问题           | 示例场景                 |
| -------- | ------------ | -------------------- | ------------------------ |
| 1NF      | 原子性       | 消除重复组和嵌套字段 | 拆分多值字段为独立行或列 |
| 2NF      | 完全依赖主键 | 消除部分依赖         | 拆分联合主键中的独立属性 |
| 3NF      | 消除传递依赖 | 避免冗余和间接依赖   | 拆分存在间接关系的字段   |



## sql面试题

https://blog.csdn.net/weixin_45483322/article/details/132390048?ops_request_misc=%257B%2522request%255Fid%2522%253A%252235aefab8576348e3d601dbefa02c6fce%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=35aefab8576348e3d601dbefa02c6fce&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-132390048-null-null.142^v102^pc_search_result_base4&utm_term=mysql%E9%9D%A2%E8%AF%95%E9%A2%98&spm=1018.2226.3001.4187