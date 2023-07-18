# 1 MyBatisPlus概述

## 1.1MyBatisPlus简介

MyBatis-Plus是MyBatis的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

官网：http://mp.baomidou.com/

参考教程：http://mp.baomidou.com/guide/



## 1.2MyBatisPlus特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer2005、SQLServer 等多种数据库
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 XML 热加载**：Mapper 对应的 XML 支持热加载，对于简单的 CRUD 操作，甚至可以无 XML 启动
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **支持关键词自动转义**：支持数据库关键词（order、key......）自动转义，还可自定义关键词
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **内置性能分析插件**：可输出 Sql 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作
- **内置 Sql 注入剥离器**：支持 Sql 注入剥离，有效预防 Sql 注入攻击





# 2 MyBatisPlus使用★★★

## 2.1添加依赖

添加：mybatis-plus-boot-starter、spring-boot-starter、spring-boot-starter-test、MySQL、lombok
在项目中使用Lombok可以减少很多重复代码的书写。比如说getter/setter/toString等方法的编写

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
     </dependency>

    <!--mybatis-plus-->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.3.1</version>
    </dependency>

    <!--mysql-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>

    <!--lombok用来简化实体类-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

**注意：**引入 `MyBatis-Plus` 之后请不要再次引入 `MyBatis` 以及 `MyBatis-Spring`，以避免因版本差异导致的问题⚠️



## 2.2编写配置文件

在 `application.properties` 配置文件中添加 MySQL 数据库的相关配置：

mysql5

```properties
#mysql数据库连接
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/mybatis_plus
spring.datasource.username=root
spring.datasource.password=123456
```

mysql8以上（spring boot 2.1）

**注意：**driver和url的变化

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8
spring.datasource.username=root
spring.datasource.password=123456
```

**注意：**

1、这里的 url 使用了 ?serverTimezone=GMT%2B8 后缀，因为Spring Boot 2.1 集成了 8.0版本的jdbc驱动，这个版本的 jdbc 驱动需要添加这个后缀，否则运行测试用例报告如下错误：

java.sql.SQLException: The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more 

2、这里的 driver-class-name 使用了  com.mysql.cj.jdbc.Driver ，在 jdbc 8 中 建议使用这个驱动，之前的 com.mysql.jdbc.Driver 已经被废弃，否则运行测试用例的时候会有 WARN 信息



## 2.3创建启动类

在 Spring Boot 启动类中添加 `@MapperScan` 注解，扫描 Mapper 文件夹

**注意：**扫描的包名根据实际情况修改

```java
package com.atguigu.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描mapper包下的接口
@MapperScan("com.atguigu.mybatisplus.mapper")
@SpringBootApplication
public class MybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusApplication.class, args);
    }
}
```



## 2.4创建实体类

创建包 entity 编写实体类 `User.java`（此处使用了 [Lombok](https://www.projectlombok.org/) 简化代码）

```java
package com.atguigu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.mybatisplus.pojo.User
 */

@Data
public class User {
    //主键策略注解
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //乐观锁版本注解
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    //自动填充注解（注解值控制自动填充的触发时机）
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
```

Lombok使用参考：https://blog.csdn.net/motui/article/details/79012846



## 2.5创建Mapper接口

创建包 mapper 编写Mapper 接口： `UserMapper.java`

```java
package com.atguigu.mybatisplus.mapper;

import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.mybatisplus.mapper.UserMapper
 */

@Repository
//mybatisplus提供的接口，内置单表操作数据的方法
public interface UserMapper extends BaseMapper<User> {
}
```



## 2.6测试使用

添加测试类，进行功能测试：

```java
package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试Mybatisplus的baseMapper中自动生成的方法
     */
    @Test
    public void testFindAll() {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }

    /**
     * MP添加信息+主键策略
     */
    @Test
    public void testAddUser(){
        User user = new User();
        user.setName("zhang3218");
        user.setAge(33);
        user.setEmail("z3@qq.com");
        userMapper.insert(user);
    }

    /**
     * 修改数据
     */
    @Test
    public void TestUpdateUser(){
        User user = new User();
        user.setId(1607598639448842242L);
        user.setName("zhang3fwkt88");
        userMapper.updateById(user);
    }

    /**
     * 测试 乐观锁插件
     */
    @Test
    public void testOptimisticLocker() {
        //查询
        User user = userMapper.selectById(1L);
        //修改数据
        user.setName("Helen Yao2");
        user.setEmail("helen@qq.com");
        //模拟取出数据后，数据库中version实际数据比取出的值大，即已被其它线程修改并更新了version
        user.setVersion(user.getVersion() - 1);
        //执行更新
        userMapper.updateById(user);
    }

    @Test
    public void getUserByIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    @Test
    public void getUserByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhang3218");
        map.put("age",33);

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }
}
```

**注意：**

IDEA在 userMapper 处报错，因为找不到注入的对象，因为类是动态创建的，但是程序可以正确的执行。

为了避免报错，可以在 dao 层 的接口上添加 @Repository 注解⚠️



## 2.7配置日志

配置文件添加配置信息

```properties
#mybatis日志
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```



# 3 MyBatisPlus的CRUD接口★

## 3.1insert

### 3.1.1插入操作

```java
public class CRUDTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert(){

        User user = new User();
        user.setName("Helen");
        user.setAge(18);
        user.setEmail("55317332@qq.com");

        int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
    }
}
```

**注意：**数据库插入id值默认为：全局唯一id⚠️



### 3.1.2主键策略

（1）自增

（2）UUID

（3）redis

（4）MP

ID_WORKER：

MyBatis-Plus默认的主键策略是：ID_WORKER  *全局唯一ID*

**参考资料：分布式系统唯一ID生成方案汇总：**https://www.cnblogs.com/haoxinyue/p/5208136.html

MP自增策略：

- 要想主键自增需要配置如下主键策略
  - 需要在创建数据表的时候设置主键自增
  - 实体字段中配置 @TableId(type = IdType.AUTO)⚠️


```java
    //主键策略注解
    @TableId(type = IdType.ASSIGN_ID)
```



## 3.2update

### 3.2.1根据Id更新操作

**注意：**update时生成的sql自动是动态sql：UPDATE user SET age=? WHERE id=? ⚠️

```java
    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(1L);
        user.setAge(28);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }
```



### 3.2.2自动填充

项目中经常会遇到一些数据，每次都使用相同的方式填充，例如记录的创建时间，更新时间等。

我们可以使用MyBatis Plus的自动填充功能，完成这些字段的赋值工作：

（1）数据库表中添加自动填充字段

在User表中添加datetime类型的新的字段 create_time、update_time

（2）实体上添加注解

```java
@Data
public class User {
    ......
        
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //@TableField(fill = FieldFill.UPDATE)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
```

（3）实现元对象处理器接口

**注意：不要忘记添加 @Component 注解** 

```java
package com.atguigu.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.mybatisplus.handler.MyMetaObjectHandler
 */

//实现元对象处理器接口，创建自动填充的控制器，设置自动添加的内容是什么
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建时填充的
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        //乐观锁，默认值为1
        this.setFieldValByName("version", 1, metaObject);
    }

    /**
     * 更新时填充的
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

}
```

（4）测试



### 3.2.3乐观锁

**主要适用场景：**当要更新一条记录的时候，希望这条记录没有被别人更新，也就是说实现线程安全的数据更新

**乐观锁实现方式：**

- 取出记录时，获取当前version
- 更新时，带上这个version
- 执行更新时， set version = newVersion where version = oldVersion
- 如果version不对，就更新失败

（1）数据库中添加version字段

```sql
ALTER TABLE `user` ADD COLUMN `version` INT
```

（2）实体类添加version字段

并添加 @Version 注解

```java
//版本注解
@Version
//版本注解创建自动填充
@TableField(fill = FieldFill.INSERT)
private Integer version;
```

（3）元对象处理器接口添加version的insert默认值

```java
@Override
public void insertFill(MetaObject metaObject) {
    ......
    this.setFieldValByName("version", 1, metaObject);
}
```

**特别说明:**

支持的数据类型只有 int,Integer,long,Long,Date,Timestamp,LocalDateTime整数类型下 `newVersion = oldVersion + 1``newVersion` 会回写到 `entity` 中仅支持 `updateById(id)` 与 `update(entity, wrapper)` 方法在 `update(entity, wrapper)` 方法下, `wrapper` 不能复用!!!

（4）在 MybatisPlusConfig 中注册 Bean

创建配置类

```java
package com.atguigu.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//开启声明式注解
@EnableTransactionManagement
@Configuration
@MapperScan("com.atguigu.mybatisplus.mapper")
public class MybatisPlusConfig {

    /**
     * 添加乐观锁插件对象
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
```

（5）测试乐观锁可以修改成功

测试后分析打印的sql语句，将version的数值进行了加1操作

```java
/**
 * 测试 乐观锁插件
 */
@Test
public void testOptimisticLocker() {
    //查询
    User user = userMapper.selectById(1L);
    //修改数据
    user.setName("Helen Yao");
    user.setEmail("helen@qq.com");
    //执行更新
    userMapper.updateById(user);
}
```

（6）测试乐观锁修改失败

```java
/**
 * 测试乐观锁插件 失败
 */
@Test
public void testOptimisticLockerFail() {
    //查询
    User user = userMapper.selectById(1L);
    //修改数据
    user.setName("Helen Yao1");
    user.setEmail("helen@qq.com1");
    //模拟取出数据后，数据库中version实际数据比取出的值大，即已被其它线程修改并更新了version
    user.setVersion(user.getVersion() - 1);
    //执行更新
    userMapper.updateById(user);
}
```



## 3.3select

### 3.3.1根据id查询记录

```java
@Test
public void testSelectById(){
    User user = userMapper.selectById(1L);
    System.out.println(user);
}
```



### 3.3.2通过多个id批量查询

完成了动态sql的foreach的功能，传入数组

```java
@Test
public void testSelectBatchIds(){
    List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
    users.forEach(System.out::println);
}
```



### 3.3.3简单的条件查询

通过map封装查询条件

```java
@Test
public void testSelectByMap(){
    HashMap<String, Object> map = new HashMap<>();
    map.put("name", "Helen");
    map.put("age", 18);
    List<User> users = userMapper.selectByMap(map);
    users.forEach(System.out::println);
}
```

**注意：**map中的key对应的是数据库中的列名。例如数据库user_id，实体类是userId，这时map的key需要填写user_id



### 3.3.4分页

MyBatis Plus自带分页插件，只要简单的配置即可实现分页功能

（1）创建配置类

此时可以删除主类中的 *@MapperScan* 扫描注解，在乐观锁下面添加

```java
/**
 * 分页插件
 */
@Bean
public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
}
```

（2）测试selectPage分页

**测试：**最终通过page对象获取相关数据

```java
@Test
public void testSelectPage() {
    
    Page<User> page = new Page<>(1,5);
    
    //方式一，直接拿Page对象接收查询分页结果
    Page<User> page1 = userMapper.selectPage(page, null)
    
    //方式二，默认查询的分页结果存储到了方法的第一个参数中
    userMapper.selectPage(page, null);
    //循环打印查询到的数据信息
    page.getRecords().forEach(System.out::println);
    System.out.println(page.getCurrent());
    System.out.println(page.getPages());
    System.out.println(page.getSize());
    System.out.println(page.getTotal());
    System.out.println(page.hasNext());
    System.out.println(page.hasPrevious());
}
```

控制台sql语句打印：SELECT id,name,age,email,create_time,update_time FROM user LIMIT 0,5 

（3）测试selectMapsPage分页：结果集是Map

```java
@Test
public void testSelectMapsPage() {
   //Page不需要泛型
   Page<Map<String, Object>> page = new Page<>(1, 5);
   Page<Map<String, Object>> pageParam = userMapper.selectMapsPage(page, null);
   List<Map<String, Object>> records = pageParam.getRecords();
   records.forEach(System.out::println);
   System.out.println(pageParam.getCurrent());
   System.out.println(pageParam.getPages());
   System.out.println(pageParam.getSize());
   System.out.println(pageParam.getTotal());
   System.out.println(pageParam.hasNext());
   System.out.println(pageParam.hasPrevious());
}
```



## 3.4delete

### 3.4.1根据id删除记录

```java
@Test
public void testDeleteById(){
    int result = userMapper.deleteById(8L);
    System.out.println(result);
}
```

### 3.4.2批量删除 

```java
    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(8, 9, 10));
        System.out.println(result);
    }
```

### 3.4.3简单的条件查询删除

```java
@Test
public void testDeleteByMap() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("name", "Helen");
    map.put("age", 18);
    int result = userMapper.deleteByMap(map);
    System.out.println(result);
}
```



## 3.5逻辑删除

- 物理删除：真实删除，将对应数据从数据库中删除，之后查询不到此条被删除数据
- 逻辑删除：假删除，将对应数据中代表是否被删除字段状态修改为“被删除状态”，之后在数据库中仍旧能看到此条数据记录

（1）数据库中添加 deleted字段

```java
ALTER TABLE `user` ADD COLUMN `deleted` boolean
```

（2）实体类添加deleted字段

并加上 @TableLogic 注解 和 @TableField(fill = FieldFill.INSERT) 注解

```java
@TableLogic
@TableField(fill = FieldFill.INSERT)
private Integer deleted;
```

（3）元对象处理器接口添加deleted的insert默认值

```java
@Override
public void insertFill(MetaObject metaObject) {
    ......
    this.setFieldValByName("deleted", 0, metaObject);
}
```

（4）application.properties 加入配置

此为默认值，如果你的默认值和mp默认的一样,该配置可无⚠️

```
mybatis-plus.global-config.db-config.logic-delete-value=1
mybatis-plus.global-config.db-config.logic-not-delete-value=0
```

（5）测试逻辑删除

- 测试后发现，数据并没有被删除，deleted字段的值由0变成了1
- 测试后分析打印的sql语句，是一条update
- **注意：**被删除数据的deleted 字段的值必须是 0，才能被选取出来执行逻辑删除的操作

```java
/**
 * 测试 逻辑删除
 */
@Test
public void testLogicDelete() {
    int result = userMapper.deleteById(1L);
    System.out.println(result);
}
```

（7）测试逻辑删除后的查询

MyBatis Plus中查询操作也会自动添加逻辑删除字段的判断

```java
/**
 * 测试 逻辑删除后的查询：
 * 不包括被逻辑删除的记录
 */
@Test
public void testLogicDeleteSelect() {
    User user = new User();
    List<User> users = userMapper.selectList(null);
    users.forEach(System.out::println);
}
```

测试后分析打印的sql语句，包含 WHERE deleted=0 

SELECT id,name,age,email,create_time,update_time,deleted FROM user WHERE deleted=0





# 4 MyBatisPlus条件构造器

## 4.1wapper介绍

- Wrapper ： 条件构造抽象类，最顶端父类
  - AbstractWrapper ： 用于查询条件封装，生成 sql 的 where 条件
    - QueryWrapper ： Entity 对象封装操作类，不是用lambda语法
    - UpdateWrapper ： Update 条件封装，用于Entity对象更新操作
  - AbstractLambdaWrapper ： Lambda 语法使用 Wrapper统一处理解析 lambda 获取 column。
    - LambdaQueryWrapper ：看名称也能明白就是用于Lambda语法使用的查询Wrapper
    - LambdaUpdateWrapper ： Lambda 更新封装Wrapper

如果想进行复杂条件查询，那么需要使用条件构造器 Wapper，涉及到如下方法

**1、delete**

**2、selectOne**

**3、selectCount**

**4、selectList**

**5、selectMaps**

**6、selectObjs**

**7、update**



## 4.2AbstractWrapper

**注意：**以下条件构造器的方法入参中的 `column `均表示数据库字段⚠️

重点记忆带使用⚠️标记的

### 1、ge、gt、le、lt、isNull、isNotNull⚠️

 1、*GE*：大于等于号（>=）；2、*GT*是大于号(>)；3、*LE*是小于等于号(<=)；4、*L·<)。

```java
@Test
public void testSelect() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.ge("age", 28);
    List<User> users = userMapper.selectList(queryWrapper);
    System.out.println(users);
}
```

### 2、eq、ne⚠️

严格相等或严格不等

**注意：**seletOne返回的是一条实体记录，当出现多条时会报错

```java
@Test
public void testSelectOne() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("name", "Tom");
    User user = userMapper.selectOne(queryWrapper);
    System.out.println(user);
}
```

SELECT id,name,age,email,create_time,update_time,deleted,version FROM user WHERE deleted=0 AND name = ? 

### 3、between⚠️、notBetween

范围查询，包含大小边界

```java
@Test
public void testSelectCount() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.between("age", 20, 30);
    Integer count = userMapper.selectCount(queryWrapper);
    System.out.println(count);
}
```

SELECT COUNT(1) FROM user WHERE deleted=0 AND age BETWEEN ? AND ? 

### 4、allEq

多条件全部相等 

```java
@Test
public void testSelectList() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    Map<String, Object> map = new HashMap<>();
    map.put("id", 2);
    map.put("name", "Jack");
    map.put("age", 20);
    queryWrapper.allEq(map);
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
}
```

SELECT id,name,age,email,create_time,update_time,deleted,version 

FROM user WHERE deleted=0 AND name = ? AND id = ? AND age = ? 

### 5、like⚠️、notLike、likeLeft、likeRight

selectMaps返回Map集合列表 

```java
@Test
public void testSelectMaps() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper
        .notLike("name", "e")
        .likeRight("email", "t");
    List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);//返回值是Map列表
    maps.forEach(System.out::println);
}
```

SELECT id,name,age,email,create_time,update_time,deleted,version 

FROM user WHERE deleted=0 AND name NOT LIKE ? AND email LIKE ? 

### 6、in、notIn、inSql、notinSql、exists、notExists

in、notIn：

```java
notIn("age",{1,2,3})--->age not in (1,2,3)notIn("age", 1, 2, 3)--->age not in (1,2,3)
```

inSql、notinSql：可以实现子查询

- 例: `inSql("age", "1,2,3,4,5,6")`--->`age in (1,2,3,4,5,6)`
- 例: `inSql("id", "select id from table where id < 3")`--->`id in (select id from table where id < 3)`

```java
@Test
public void testSelectObjs() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    //queryWrapper.in("id", 1, 2, 3);
    queryWrapper.inSql("id", "select id from user where id < 3");
    List<Object> objects = userMapper.selectObjs(queryWrapper);//返回值是Object列表
    objects.forEach(System.out::println);
}
```

SELECT id,name,age,email,create_time,update_time,deleted,version 

FROM user WHERE deleted=0 AND id IN (select id from user where id < 3) 

### 7、or、and

**注意：**这里使用的是 UpdateWrapper 

不调用`or`则默认为使用 `and `连

```java
@Test
public void testUpdate1() {
    //修改值
    User user = new User();
    user.setAge(99);
    user.setName("Andy");
    //修改条件
    UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
    userUpdateWrapper
        .like("name", "h")
        .or()
        .between("age", 20, 30);
    int result = userMapper.update(user, userUpdateWrapper);
    System.out.println(result);
}
```

UPDATE user SET name=?, age=?, update_time=? WHERE deleted=0 AND name LIKE ? OR age BETWEEN ? AND ?

### 8、嵌套or、嵌套and

这里使用了lambda表达式，or中的表达式最后翻译成sql时会被加上圆括号 

```java
@Test
public void testUpdate2() {
    //修改值
    User user = new User();
    user.setAge(99);
    user.setName("Andy");
    //修改条件
    UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
    userUpdateWrapper
        .like("name", "h")
        .or(i -> i.eq("name", "李白").ne("age", 20));
    int result = userMapper.update(user, userUpdateWrapper);
    System.out.println(result);
}
```

UPDATE user SET name=?, age=?, update_time=? 

WHERE deleted=0 AND name LIKE ? 

OR ( name = ? AND age <> ? ) 

### 9、orderBy、orderByDesc⚠️、orderByAsc

```java
@Test
public void testSelectListOrderBy() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.orderByDesc("id");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
}
```

SELECT id,name,age,email,create_time,update_time,deleted,version 

FROM user WHERE deleted=0 ORDER BY id DESC 

### 10、last

直接拼接到 sql 的最后

**注意：**只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用

```java
@Test
public void testSelectListLast() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.last("limit 1");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
}
```

SELECT id,name,age,email,create_time,update_time,deleted,version 

FROM user WHERE deleted=0 limit 1 

### **11、**指定要查询的列

```java
@Test
public void testSelectListColumn() {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.select("id", "name", "age");
    List<User> users = userMapper.selectList(queryWrapper);
    users.forEach(System.out::println);
}
```

SELECT id,name,age FROM user WHERE deleted=0 

### 12、set、setSql

最终的sql会合并 user.setAge()，以及 userUpdateWrapper.set()  和 setSql() 中 的字段

```java
@Test
public void testUpdateSet() {
    //修改值
    User user = new User();
    user.setAge(99);
    //修改条件
    UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
    userUpdateWrapper
        .like("name", "h")
        .set("name", "老李头")//除了可以查询还可以使用set设置修改的字段
        .setSql(" email = '123@qq.com'");//可以有子查询
    int result = userMapper.update(user, userUpdateWrapper);
}
```

UPDATE user SET age=?, update_time=?, name=?, email = '123@qq.com' WHERE deleted=0 AND name LIKE ? 

### 13、select⚠️

只返回指定字段，其余为null

```java
@Test
public void getUserByInfo(){
    QueryWrapper<User> wrapper = new QueryWrapper<>();
    wrapper.orderByDesc("age");
    
    wrapper.select("name","age");
    List<User> users = userMapper.selectList(wrapper);
    users.forEach(System.out::println);
}
```

 SELECT name age from user order_by age Desc







- 

- 
