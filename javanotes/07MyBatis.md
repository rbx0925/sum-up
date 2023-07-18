# 1 MyBatis入门

框架实际上就是别人写好的jar包，封装了一堆类，对常用方法和已知问题的解决方案进行了实现，是一个半成品软件，我们可以基于它进行二次开发。

## 1.1MyBatis简介

MyBatis 是**支持定制化 SQL**、存储过程以及**高级映射**的优秀的持久层框架

MyBatis 避免了几乎所有的JDBC代码和手动设置参数以及获取结果集

MyBatis可以使用简单的XML或注解用于配置和原始映射，将接口和Java的POJO（Plain Ordinary Java Object，普通的Java对象）映射成数据库中的记录

Mybatis是一个 半自动的ORM（Object  Relation Mapping）框架——对象关系映射

MyBatis

- 对开发人员而言，核心sql还是需要自己优化
- **sql和java编码分开，功能边界清晰，一个专注业务、一个专注数据**

下载地址：https://github.com/mybatis/mybatis-3

中国官网：https://mybatis.org/mybatis-3/zh/index.html

GitHub地址：https://github.com/mybatis/mybatis-3



## 1.2ORM框架

ORM对象关系映射（Object Relational Mapping），主要实现程序对象到关系数据库数据的映射。

ORM框架是连接数据库的桥梁，只要提供了持久化层与数据库表的映射关系，ORM框架在运行时就能参照映射文件的信息，把对象持久化到数据库中。

对象关系映射的解释：

- ORM以最基本的形式建模数据。比如ORM会将MySQL的一张表映射成一个Bean类，表的字段就是这个类的成员变量。
- ORM使所有的MySQL数据表都按照统一的标准精确地映射成java类，使系统在代码层面保持准确统一。
- ORM包含对持久类对象进行CRUD操作的API，例如update()、find()等，可通过函数的链式组合生成最终的SQL语句。
- 使用ORM开发的数据访问结构：数据模型 → ORM框架(mybatis) → 数据库
  - 数据模型：定义数据表的映射模型Bean类，定义Mapper接口和对应的Mapper.xml映射文件，编写CRUD方法。
  - ORM框架：根据Mapper.xml映射文件，ORM生成映射关系，生成insert、update、delete、select语句。
  - 数据库：执行数据库的插入数据、修改数据、删除数据、查询数据操作。

ORM的优缺点：

- 优点：
  - 提高开发效率，降低开发成本
  - 使开发更加对象化
  - 可移植
  - 可以很方便地引入数据缓存之类的附加功能
- 缺点：
  - 自动化进行关系数据库的映射需要消耗系统性能。(其实这里的性能消耗并不高)
  - 在处理多表联查、where条件复杂之类的查询时，ORM的语法会变得复杂。



## 1.3MyBatis使用步骤

使用步骤参考官方文档：https://mybatis.org/mybatis-3/zh/

- 创建一个web模块，打包方式设置为jar

- 导入相关jar包

  - 导入mybatis的jar包

  - 导入mysql驱动的jar包
  - 导入junit依赖的jar包

- 创建数据表的映射模型Bean类

- 创建持久层Mapper接口

- 创建Mybatis的sql映射文件EmployeeMapper.xml

  - 使用该模版创建：https://mybatis.org/mybatis-3/zh/getting-started.html（探究已映射的sql语句）

- 创建Mybatis的全局映射文件mybatis-config.xml

  - 使用该模版创建：https://mybatis.org/mybatis-3/zh/getting-started.html（从 XML 中构建 SqlSessionFactory）

- 编写测试程序



## 1.4MyBatis入门程序★

代码实现：

- 创建一个web模块，打包方式设置为jar

- 导入相关jar包

  ```xml
  <dependencies>
    <!--导入MySQL的驱动包-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.25</version>
    </dependency>
  
    <!--导入MyBatis的jar包-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.6</version>
    </dependency>
  
    <!--junit-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
  ```

- 创建数据表的映射模型Bean类

- 创建持久层Mapper接口

- 创建Mybatis的sql映射文件EmployeeMapper.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <!--该文件是对哪个接口的映射，未配置别名时要写接口全类名-->
  <mapper namespace="com.mybatis.mapper.EmployeeMapper">
      <!--id为方法名，resultType为返回类型，未配置别名时要写bean的全类名-->
      <select id="getAllEmp" resultType="com.mybatis.bean.Employee">
          select * from employees
      </select>
  </mapper>
  ```

- 创建Mybatis的全局映射文件mybatis-config.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "https://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <!--配置根据数据库信息配置数据源属性-->
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/ssm"/>
                  <property name="username" value="root"/>
                  <property name="password" value="12345678"/>
              </dataSource>
          </environment>
      </environments>
      <!--从类的根路径resources下加载接口的映射xml文件-->
      <mappers>
          <mapper resource="EmployeeMapper.xml"/>
      </mappers>
  </configuration>
  ```

- 编写测试程序

  ```java
  public class TestMybatis {
      @Test
      public void test01() throws Exception{
          //从类的根路径resources目录下查找配置文件的路径
          String resource = "mybatis-config.xml";
          //SqlSessionFactoryBuilder.build()需要传入配置文件的输入流对象
          InputStream inputStream = Resources.getResourceAsStream(resource);
          //通过SqlSessionFactoryBuilder对象的build方法获取SqlSessionFactory对象
          SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          //通过SqlSessionFactory对象的openSession方法获取SqlSession
          SqlSession sqlSession = sqlSessionFactory.openSession();
          //通过SqlSession得到EmployeeMapper接口的映射对象
          EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
          //执行已映射的sql语句的方法
          List<Employee> list = mapper.getAllEmp();
          System.out.println(list);
  
      }
  }
  ```



## 1.5 关于加载配置文件路径

Resources.getResourceAsStream为加载类路径下的资源，开发中加载其他配置文件，只要遇到Resource关键词，大部分情况下，这种加载资源的方式就是从类的根路径下(Resources目录下)开始加载。

加载配置文件的两种方式：

- 相对路径： InputStream is = new FileInputStream("d:\\mybatis-config.xml");
  - 缺点：可移植性太差，程序不够健壮。可能会移植到其他的操作系统当中，导致以上路径无效。
- 绝对路径：InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
  - 推荐使用，将程序移植到其他操作系统，也不会发生加载路径错误等问题

  Resources.getResourceAsStream底层源码：

-  InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
  - ClassLoader.getSystemClassLoader() 获取系统的类加载器
  - 系统类加载器有一个方法叫做getResourceAsStream，该方法从类路径当中加载资源
  - 结论：Resources.getResourceAsStream()底层是通过类加载器加载配置文件





# 2 MyBatis全局配置文件

MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。 配置文档的顶层结构如下：

- configuration（配置）
- properties（属性）
- settings（设置）
- typeAliases（类型别名）
- typeHandlers（类型处理器）
- objectFactory（对象工厂）
- plugins（插件）
- environments（环境配置）
  - environment（环境变量）
    - transactionManager（事务管理器）
    - dataSource（数据源）
- databaseIdProvider（数据库厂商标识）
- mappers（映射器）

官方文档配置项说明：https://mybatis.org/mybatis-3/zh/configuration.html

注意：配置mybatis配置文件时，必须按照层次结构顺序编写⚠️



## 2.1属性properties

本质上是java.util.Properties类的实例，是一个Map集合。key和value都是String，引用这些属性使用${属性名}。

```xml
<!--配置属性-->
<properties>
  <property name="jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
  <property name="jdbc.url" value="jdbc:mysql://localhost:3306/powernode"/>
  <property name="jdbc. username" value="root"/>
  <property name="jdbc.password" value="root"/>
</properties>

<!--使用properties中配置的属性-->
<dataSource type="POOLED">
  <property name="driver" value="${jdbc.driver}"/>
  <property name="url" value="${jdbc.url}"/>
  <property name="username" value="${jdbc.username}"/>
  <property name="password" value="${jdbc.password}"/>
</dataSource>
```



## 2.2设置settings★

- 该设置是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。

- 该设置中的配置项很多又都极其重要，这里列举部分关键配置项

  - 驼峰映射：mapUnderscoreToCamelCase⚠️
    - 默认false，未开启

  - 延迟加载：lazyLoadingEnabled

    - 默认false，未开启

  - 按需加载：aggressiveLazyLoading

    - 默认false，未开启（3.4.1 及之前的版本中默认为 true）

  - 生成主键：useGeneratedKeys

    - 默认false，未开启

  - 自动映射：autoMappingBehavior

    - 默认PARTIAL，只会自动映射没有定义嵌套结果映射的字段

  - 缓存机制：localCacheScope

    - 默认SESSION，会缓存一个会话中执行的所有查询

  - mapUnderscoreToCamelCase 是否开启驼峰命名自动映射，默认false未开启，值为true时开启

    

## 2.3类型别名typeAliases★

配置类型别名的两种方式：

- 为一个bean类型设置一个别名
  - alias属性写类名
  - type属性写全限定类名
- 为一个包下所有bean类型设置一个别名
  - 在不使用@Alias注解前提下，默认类名或缩写名为首字母小写的类名
  - @Alias注解可以设置别名，直接在该包的类上添加即可

第一种方式：为一个bean类型设置一个别名

```xml
<typeAliases>
  <typeAlias alias="Author" type="domain.blog.Author"/>
</typeAliases>
```

第二种方式：为一个包下所有bean类型设置一个别名，该包中的类可以使用@Alias注解修改默认别名

```xml
<!--为com.mybatis包下的所有类设置别名-->
<typeAliases>
  <package name="com.mybatis"/>
</typeAliases>
```

注意：mybatis有许多Java 类型内建的类型别名，仅仅列举出部分：

| 别名     | 映射的类型 |
| :------- | :--------- |
| _long    | long       |
| _short   | short      |
| _int     | int        |
| _integer | int        |
| _double  | double     |
| _float   | float      |
| _boolean | boolean    |
| map      | Map        |
| hashmap  | HashMap    |



## 2.4类型处理器typeHandlers

MyBatis 在设置预处理语句（PreparedStatement）中的参数或从结果集中取出一个值时， 都会用类型处理器将获取到的值以合适的方式转换成 Java 类型。下表描述了一些默认的类型处理器。

**提示** 从 3.4.5 开始，MyBatis 默认支持 JSR-310（日期和时间 API） 。

类型处理器的处理步骤：

- 当使用select查询到结果获得结果集，从结果集中取出一个值时，都会用类型处理器将获取的值转为java类型

  - 比如取到的是integer需要转换为int，取到的是char需要转换为String等

- 自定义类型转换器:

  - 我们可以重写类型处理器或创建自己的类型处理器，来处理不支持或非标准的类型
  - 步骤
    1. 实现org.apache.ibatis.type.TypeHandler接口或者继承org.apache.ibatis.type.BaseTypeHandler
    2. 指定其映射某个JDBC类型（可选操作）
    3. 在mybatis全局配置文件中注册

- 处理枚举类型：

  - 若想映射枚举类型 `Enum`，则需要从 `EnumTypeHandler` 或者 `EnumOrdinalTypeHandler` 中选择一个来使用。

    ```xml
    <!-- mybatis-config.xml -->
    <typeHandlers>
      <typeHandler handler="org.apache.ibatis.type.EnumOrdinalTypeHandler" javaType="java.math.RoundingMode"/>
    </typeHandlers>
    ```

- 根据情况将枚举映射成字符串和整型

  - 自动映射器（auto-mapper）会自动地选用 `EnumOrdinalTypeHandler` 来处理枚举类型， 所以如果我们想用普通的 `EnumTypeHandler`，就必须要显式地为那些 SQL 语句设置要使用的类型处理器。

  - 通过自定义映射完成枚举映射类型的切换

    ```xml
    <!--设置自定义映射-->
    <resultMap type="org.apache.ibatis.submitted.rounding.User" id="usermap">
      <id column="id" property="id"/>
      <result column="name" property="name"/>
      <result column="funkyNumber" property="funkyNumber"/>
      <result column="roundingMode" property="roundingMode"/>
    </resultMap>
    
    <!--使用自定义映射，查询到的枚举为整型-->
    <select id="getUser" resultMap="usermap">
      select * from users
    </select>
    ```

    

## 2.5对象工厂objectFactory

MyBatis 创建结果对象的新实例时，都会使用一个对象工厂ObjectFactory实例来完成实例化工作。 默认的对象工厂需要做的仅仅是实例化目标类，要么通过默认无参构造方法，要么通过存在的参数映射来调用带有参数的构造方法。

我们可以创建自己的对象工厂来完成实例化操作，实现ObjectFactory接口即可。

```java
//继承DefaultObjectFactory类，该类实现了ObjectFactory接口。
public class ExampleObjectFactory extends DefaultObjectFactory {
  @Override
  public <T> T create(Class<T> type) {
    return super.create(type);
  }

  @Override
  public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
    return super.create(type, constructorArgTypes, constructorArgs);
  }

  @Override
  public void setProperties(Properties properties) {
    super.setProperties(properties);
  }

  @Override
  public <T> boolean isCollection(Class<T> type) {
    return Collection.class.isAssignableFrom(type);
  }}
```

```xml
<!-- mybatis-config.xml -->
<objectFactory type="org.mybatis.example.ExampleObjectFactory">
  <property name="someProperty" value="100"/>
</objectFactory>
```

ObjectFactory 接口只有三个方法，它包含两个创建实例用的方法，一个是处理默认无参构造方法的，另外一个是处理带参数的构造方法的。 另外，setProperties 方法可以被用来配置 ObjectFactory，在初始化你的 ObjectFactory 实例后， objectFactory 元素体中定义的属性会被传递给 setProperties 方法。



## 2.6插件plugins

MyBatis 允许你在映射语句执行过程中的某一点进行拦截调用。

默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：

- Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
- ParameterHandler (getParameterObject, setParameters)
- ResultSetHandler (handleResultSets, handleOutputParameters)
- StatementHandler (prepare, parameterize, batch, update, query)



## 2.7环境配置environments

### 2.7.1环境变量

- 在mybatis-config.xml文件中，可在environments标签中配置多个环境：

  - environments标签的default属性：用来配置默认环境名称
  - 每个环境对应一组environment标签，通过id属性来区分各环境

  ```xml
  <environments default="development">
    <!--配置第一个数据库-->
    <environment id="development1">
     ......
    </environment>
    <!--配置第二个数据库-->
    <environment id="development2">
     ......
    </environment>
    <!--配置第三个数据库-->
    <environment id="development3">
     ......
    </environment>
  </environments>
  ```

- 获取SqlSessionFactory对象时，可传递一个环境名称参数，来指点使用哪个数据库环境。不传为默认环境。

  ```java
  SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"development2");
  ```

  

### 2.7.2事务管理器

在 MyBatis 中有两种类型的事务管理器（也就是 type="[JDBC|MANAGED]"）

- JDBC：使用 JDBC 的提交和回滚功能，它依赖从数据源获得的连接来管理事务作用域。

  ```xml
  <transactionManager type="JDBC">
    <property name="skipSetAutoCommitOnClose" value="true"/>
  </transactionManager>
  ```

- MANAGED ：默认会关闭连接。容器不想被关闭需要将 closeConnection 属性设置为 false 来阻止默认的关闭行为。

  ```xml
  <transactionManager type="MANAGED">
    <property name="closeConnection" value="false"/>
  </transactionManager>
  ```

如果使用 Spring + MyBatis，则没有必要配置事务管理器，因为 Spring 模块会使用自带的管理器来覆盖前面的配置



### 2.7.3数据源

- dataSource被称为数据源，其type属性用来指定数据连接池。

  - 数据源概念：

    - 但凡是给程序提供Connection对象的，都叫做数据源。
    - 数据源实际上是一套规范。JDK中有这套规范：javax.sql.DataSource。
    - 我们自己也可以编写自己的数据源，只要实现javax.sql.DataSource即可。
    - 数据库连接池是提供Connection连接对象的，所以数据库连接池就是一个数据源

  - type的值：type="[UNPOOLED|POOLED|JNDI]”，必须为三者之一，每个配置参数也都不同

    - UNPOOLED：不使用数据库连接池技术。每一次请求过来之后，都是创建新的Connection对象。
    - POOLED：使用mybatis自己实现的数据库连接池。
    - JNDI：集成其它第三方的数据库连接池。
      - JNDI集成其它第三方的数据库连接池。 
      - JNDI是一套规范，大部分的web容器都实现了JNDI规范：例如：Tomcat。
      - JNDI是：java命名目录接口。Tomcat服务器实现了这个规范。

  - 使用mybatis自己实现的数据库连接池，并配置参数：

    ```xml
    <dataSource type="POOLED">
      ......
      <!--正常使用连接池的话，池中有很多参数是需要设置的-->
      <!--设置好参数，可以让连接池发挥的更好。事半功倍的效果-->
    
      
      <!--连接池当中最多的正在使用的连接对象的数量上限。最多有多少个连接可以活动。默认值10-->
      <property name="poolMaximumActiveConnections" value="10"/>
      
      <!--每隔2秒打印日志，并且尝试获取连接对象-->
      <property name="poolTimeToWait" value="2000"/>
      
      <!--强行让某个连接空闲，超时时间的设置，超出该时间就宣布过期-->
      <property name="poolMaximumCheckoutTime" value="10000"/>
    
      <!--最多的空闲数量-->
      <property name="poolMaximumIdleConnections" value="5"/>
    </dataSource>
    ```

    

## 2.8数据厂商标识

MyBatis 可以根据不同的数据库厂商执行不同的语句，这种多厂商的支持是基于映射语句中的 `databaseId` 属性。

```xml
<databaseIdProvider type="DB_VENDOR">
  <property name="MySQL" value="mysql"/>
  <property name="SQL Server" value="sqlserver"/>
  <property name="DB2" value="db2"/>
  <property name="Oracle" value="oracle" />
</databaseIdProvider>
```

在映射文件的标签内使用databaseId指定该方法所适配的sql语句

```xml
    <insert id="add" databaseId="mysql">
        insert into employees values(null,#{lastName}...,#{deptId})
    </insert>
```



## 2.9映射器mappers★

映射器mappers配置会告诉 MyBatis 去哪里找映射文件，找到SQL 映射文件后，才能拿到定义 SQL 映射语句操作数据表。

映射文件的加载包含绝对路径和相对路径的四种方式：

- 从resources目录下引用一个Mapper.xml映射文件

  ```xml
  <!-- 使用相对于类路径的资源引用 -->
  <mappers>
    <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
    <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
    <mapper resource="org/mybatis/builder/PostMapper.xml"/>
  </mappers>
  ```

- 使用绝对路径引用一个Mapper.xml映射文件

  ```xml
  <!-- 使用完全限定资源定位符（URL） -->
  <mappers>
    <mapper url="file:///var/mappers/AuthorMapper.xml"/>
    <mapper url="file:///var/mappers/BlogMapper.xml"/>
    <mapper url="file:///var/mappers/PostMapper.xml"/>
  </mappers>
  ```

- 使用mapper接口的全限定类名，要求映射接口对应的Mapper.xml映射文件，目录结构和文件名必须与接口一致⚠️

  ```xml
  <!-- 使用映射器接口实现类的完全限定类名 -->
  <mappers>
    <mapper class="com.mybatis.mapper.EmployeeMapper"/>
    <mapper class="com.mybatis.mapper.UserMapper"/>
  </mappers>
  ```

- 将包内的mapper接口全部注册为映射器，要求映射接口对应的Mapper.xml映射文件，目录结构和文件名必须与接口一致⚠️

  ```xml
  <!-- 将包内的映射器接口全部注册为映射器 -->
  <mappers>
    <package name="com.mybatis.mapper"/>
  </mappers>
  ```





# 3 映射文件配置

MyBatis 的真正强大在于它的语句映射，与相同功能的 JDBC 代码进行对比，你会立即发现省掉了将近 95% 的代码。MyBatis 致力于减少使用成本，让用户能更专注于 SQL 代码。



## 3.1映射文件常见标签

**以下关于mapper根标签：**

- mapper标签的namespace属性用来绑定持久层文件，告诉MyBatis该映射文件指向那个Mapper接口

- mapper标签的namespace属性是用来指定命名空间的，防止sql语句标签的id重复

- 本质上，映射文件中的sql语句标签的完整写法：namespace.sql语句的id

- 底层：namespace + id` 是作为 `Map<String, MappedStatement>` 的 key 使用的。如果没有 `"namespace"`，就剩下 id ，那么 id 重复会导致数据互相覆盖。如果有了 `"namespace"`，自然 id 就可以重复，`"namespace"`不同，`namespace + id`自然也就不同。

  ```xml
  <mapper namespace="com.mybatis.mapper.EmployeeMapper">
      <!--该select标签的完成id为：com.mybatis.mapper.EmployeeMapper.getAllEmp-->
      <select id="getAllEmp" resultType="com.mybatis.bean.Employee">
          select * from employees
      </select>
  </mapper>
  ```

- 结论：不同的 XML Mapper 文件，如果配置了 `namespace` ，那么 id 可以重复；如果未配置 `namespace` ，那么 id 不能重复。⚠️

**以下列举 Mybatis 中常见的标签：**

- `<select/>`- 映射查询语句。
- `<insert/>`- 映射插入语句。
- `<update/>`- 映射更新语句。
- `<delete/>`- 映射删除语句。
- `<cache/>`- 对给定命名空间的缓存配置。
- `<cache-ref />` - 对其他命名空间缓存配置的引用。。
- `<resultMap />` - 自定义映射，是最复杂也是最强大的元素，用来描述如何从数据库结果集中来加载对象。
- `<sql />`可被其他语句引用的可重用语句块。
- `<include />` 标签，引用 `<sql />` 标签的语句。
- `<selectKey />` 标签，不支持自增的主键生成策略标签。

**以下关于动态 SQL 相关的标签：**

- `<if />`

- `<choose />`、`<when />`、`<otherwise />`

- `<trim />`、`<where />`、`<set />`

- `<foreach />`：集合遍历

  

## 3.2select属性★

查询语句是 MyBatis 中最常用的元素之一，MyBatis在查询和结果映射做了相当多的改进。

-   id：引用该sql的方法名
-   parameterType：参数类型，使用别名或者全限定类名，可省略
-   resultType：期望从这条语句中返回结果的类全限定名或别名。 注意，如果返回的是集合，那应该设置为集合包含的类型
-   resultMap：使用自己定义的结果映射， resultType 和 resultMap 之间只能同时使用一个
-   databaseId：如果配置了数据库厂商标识，MyBatis 会根据数据库标识执行对应的databaseId语句
-   flushCache：将其设置为 true 后，只要语句被调用，都会导致本地缓存和二级缓存被清空，默认值：false。
-   useCache：将其设置为 true 后，将会导致本条语句的结果被二级缓存缓存起来，默认值：对 select 元素为 true。
-   timeout：等待数据库返回请求结果的超时秒数，超时就会抛出异常，默认值：数据库驱动中获得
-   fetchSize：给驱动的建议值，尝试让驱动程序每次批量返回的结果行数等于这个设置值。 默认值为未设置
-   resultSetType：仅适用于多结果集的情况。赋予每个结果集一个名称，多个名称之间以逗号分隔。



## 3.3insert,update,delete属性★

-   id：引用该sql的方法名
-   parameterType：参数类型，使用别名或者全限定类名，可省略
-   flushCache：将其设置为 true 后，只要语句被调用，都会导致本地缓存和二级缓存被清空，默认值：true。
-   timeout：等待数据库返回请求结果的超时秒数，超时就会抛出异常，默认值：数据库驱动中获得
-   useGeneratedKeys：仅适用于 insert 和 update，使用getGeneratedKeys方法来取出由数据库内部生成的主键
-   keyProperty：仅适用于 insert 和 update，指定存放主键的属性
-   keyColumn：仅适用于 insert 和 update，设置生成键值在表中的列名，在某些数据库（像 PostgreSQL）中，当主键列不是表中的第一列的时候，是必须设置的。如果生成列不止一个，可以用逗号分隔多个属性名称
-   databaseId：如果配置了数据库厂商标识，MyBatis 会根据数据库标识执行对应的databaseId语句



## 3.4获取主键生成方式★

支持主键自增，例如MySQL数据库

不支持主键自增，例如Oracle数据库

获取支持主键自增的主键值：

设置 useGeneratedKeys=”true”，然后再把 keyProperty 设置到目标属性上。

```xml
<insert id="addEmployee" useGeneratedKeys="true" keyProperty="id">
    insert into employees(last_name,email,salary,dept_id)
    values(#{lastName},#{email},#{salary},#{deptId})
</insert>
```

获取不支持主键自增的主键值：

方式1：使用 selectKey 子标签：selectKey  标签将会首先运行，id  会被设置，然后插入语句会被调用。

```xml
<!--方式1-->
<insert id="addEmployee" databaseId="oracle">
    <selectKey order="BEFORE" keyProperty="id" resultType="integer">
        select employee_seq.nextval from dual
    </selectKey>
    insert into oracle_employees(id,last_name,email,salary,dept_id)
    values(#{id},#{lastName},#{email},#{salary},#{deptId})
</insert>
```

方式2：使用 selectKey 子标签：selectKey  标签将会最后运行，先调用插入语句，再设置id。

```xml
<!--方式2-->
<insert id="addEmployee" databaseId="oracle">
    <selectKey order="AFTER" keyProperty="id" resultType="integer">
        select employee_seq.currval from dual
    </selectKey>
    insert into oracle_employees(id,last_name,email,salary,dept_id)
    values(employee_seq.nextval,#{lastName},#{email},#{salary},#{deptId})
</insert>
```



## 3.5使用注解

mybatis中也提供了注解式开发⽅式，采⽤注解可以减少映射⽂件的配置，sql语句是写在java程序中，增加维护成本。

官方说明：使⽤注解来映射简单语句会使代码显得更加简洁，但对于稍微复杂⼀点的语句，最好⽤ XML 来映射语句。

原则：简单sql可以注解。复杂sql使⽤xml。

@Insert注解使用方法：

```java
//接口内使用@Insert注解    
@Insert("INSERT INTO employees VALUES(null,#{lastName},#{email},#{gender},#{salary},#{deptId})")
void insertEmp(Employee emp);
```

```java
//测试注解方法
Employee employee = new Employee(6,null,"小陆",null,88888.0,null);
mapper.insertEmp(employee);
sqlSession.commit();
sqlSession.close();
```

@Delete注解使用方法：

```java
//接口内使用@Delete注解
@Delete("DELETE FROM employees WHERE id=#{id}")
void delete(Integer id);
```

```java
//测试注解方法
mapper.delete(42);
sqlSession.commit();
sqlSession.close();
```

@Update注解使用方法：

```java
//接口内使用@Update注解
@Update("UPDATE employees SET last_name=#{lastName},email=#{email},gender=#{gender},salary=#{salary},dept_id=#{deptId} WHERE id=#{id}")
void update(Employee emp);
```

```java
//测试注解方法
Employee employee = new Employee(44,null,"小七",null,88888.0,null);
mapper.update(employee);
sqlSession.commit();
sqlSession.close();
```

@Select注解使用方法：

```java
//接口内使用@Select注解
//核心配置文件setting中设置的自动匹配驼峰仍然可以使用！
@Select("SELECT * FROM employees WHERE id=#{id}")
Employee selectEmp(Integer id);
```

```java
//测试注解方法
Employee emp = mapper.selectEmp(1);
System.out.println(emp);
```

@Result注解使用方法：

```java
//@Result注解用来自定义映射，常和select注解配合使用
@Select("SELECT * FROM employees WHERE id=#{id}")
@Results({
  @Result(property = "id", column = "id"),
  @Result(property = "lastName", column = "last_name"),
  @Result(property = "email", column = "email"),
  @Result(property = "gender", column = "gender"),
  @Result(property = "salary", column = "salary"),
  @Result(property = "deptId", column = "dept_id"),
})
Employee selectEmp(Integer id);
```





# 4 参数传递

Mapper映射文件内的sql语句，通过参数传递，来获取调用CRUD方法时传递的实参。

## 4.1参数的获取方式

- #{key值}  (推荐使用)

  - 采用占位符的方式，没有SQL注入的问题
  - 等同于JDBC的 ? 占位符

- ${key值}

  - 采用sql语句拼接的方式，有SQL注入的问题

  - 只是简单的字符串替换，替换String类型的参数时需要加单引号

    

## 4.2参数处理

参数位置支持的属性：（占位符#{}的属性）

- javaType：参数的类型
- jdbcType：如果用的orcle数据库，假如占位符传值为Null的会报错，设置该属性可避免报错 ⚠️
- mode：调用存储过程，mode 属性为 IN，OUT 或 INOUT 参数，OUT 或 INOUT参数会改变对象属性的真实值
- numericScale：保留小数点几位
- resultMap：指定映射结果集
- typeHandler：设置类型处理器
- jdbcTypeName：指定数据类型名称，比如结构体，jdbcTypeName=MY_TYPE
- expression：设置表达式

参数使用格式：#{获取参数,参数处理内容}

应用场景：当使用oracle数据库时，若传递的值为null会不识别报错，可使用jdbc解决⚠️

注意事项：

- #{name,jdbcType=NULL}
- NULL一定要大写

```xml
<insert>
  insert into employees values(#{id,jdbcType=NULL},#{lastName}) 
</insert>
```

使用setting中的配置也可以解决：

```xml
<settings>
	<setting name="jdbcTypeForNull" value="NULL"/>
</settings>
```



## 4.3参数的传递★

### 4.3.1单个普通类型参数

可以接受基本类型，包装类型，字符串类型等。这种情况MyBatis可直接使用这个参数，不需要经过任何处理。

Mapper接口：

```java
public interface EmployeeMapper {
  	//传递一个参数
    Employee findById(Integer id);
}
```

Mapper映射文件：


```xml
<select id="findById" resultType="com.mybatis.bean.Employee">
	<!--#{}内可随便写-->
  select * from employees where id = #{id}
</select>
```

junit单元测试：


```java
Employee employee = mapper.findById(1);
System.out.println(employee);
```



### 4.3.2多个参数

任意多个参数，都会被MyBatis重新包装成一个Map传入。Map的key是param1，param2，或者arg0，arg1…，值就是参数的值。

Mapper接口：

```java
public interface EmployeeMapper {
		//传递多个参数
    List<Employee> findByDeptIdAndGender(Integer deptId,Integer gender);
}
```

Mapper映射文件：


```xml
<!--底层把参数都放到了map数组⚠️-->
<select id="findByDeptIdAndGender" resultType="employee">
  <!--方式1，使用param，注意：从param1开始-->
  select * from employees where dept_id = #{param1} and gender=#{param2}
  <!--方式2：使用arg，注意：从arg0开始-->
  select * from employees where dept_id = #{arg0} and gender=#{arg1}
</select>
```

junit单元测试：


```java
List<Employee> list = mapper.findByDeptIdAndGender(1,1);
System.out.println(list);
```



### 4.3.3@Param

为参数使用@Param起一个名字，MyBatis就会将这些参数封装进map中，key就是我们自己指定的名字。

Mapper接口：

```java
public interface EmployeeMapper {
		//使用@Param参数名称注解
    List<Employee> findByDeptIdAndGender(@Param("dept") Integer deptId, @Param("gender") Integer gender);
}
```

Mapper映射文件：


```xml
<select id="findByDeptIdAndGender" resultType="employee">
  <!--使用注解内的value值-->
  select * from employees where dept_id = #{dept} and gender=#{gender}
</select>
```

junit单元测试：


```java
List<Employee> list = mapper.findByDeptIdAndGender(1,1);
System.out.println(list);
```



### 4.3.4Bean对象参数

当这些参数属于我们业务javaBean时，我们直接传递javaBean。

Mapper接口：

```java
public interface EmployeeMapper {
		//传递一个javaBean对象
    void insertEmp(Employee employee);
}
```

Mapper映射文件：


```xml
<insert id="insertEmp" >
  <!--直接写对象的属性值-->
  insert into employees value(null,#{lastName},#{email},#{gender},#{salary},#{deptId})
</insert>
```

junit单元测试：


```java
Employee employee = new Employee(null,"小明",1,"xx@xx.com",9.9,1);
mapper.insertEmp(employee);
//CUD操作需要提交
sqlSession.commit();
```



### 4.3.5Map参数

我们也可以封装多个参数为map，直接传递。

Mapper接口：

```java
public interface EmployeeMapper {
		//传递Map集合
    void insertEmp(Map map);
}
```

Mapper映射文件：


```xml
<insert id="insertEmp" >
  <!--直接写map的key值-->
  insert into employees value(null,#{lastName},#{email},#{gender},#{salary},#{deptId})
</insert>
```

junit单元测试：


```java
//创建map集合并赋值
HashMap<String, Object> map = new HashMap<>();
map.put("lastName","小明");
map.put("gender",1);
map.put("email","xxx@xx.com");
map.put("salary",8.8);
map.put("deptId",1);
//传入map集合
mapper.insertEmp(map);
//提交
sqlSession.commit();
```



### 4.3.6Collection和Array

会被MyBatis封装成一个map传入，Collection对应的key是collection，Array对应的key是array，如果是List集合，key还可以是list。

调用属性的方式：#{key[下标]}

Mapper接口：

```java
public interface EmployeeMapper {
		//传递Collection集合
    void insertEmp(Collection collection);
}
```

Mapper映射文件：


```xml
<insert id="insertEmp">
  <!--底层被转换成了map数组，取值格式为#{key[下标]}-->
  insert into employees 
  value(null,#{collection[0]},#{collection[1]},#{collection[2]},#{collection[3]},#{collection[4]})
</insert>
```

junit单元测试：


```java
//创建Collection集合并赋值
Collection collection = new ArrayList<>();
collection.add("小明");
collection.add("xxx@xx.com");
collection.add(1);
collection.add(6.6);
collection.add(1);
//传入Collection集合
mapper.insertEmp(collection);
//提交
sqlSession.commit();
```



## 4.4参数传递源码分析

开始分析：传递的参数，都到了invoke方法的Object[] args数组里。

- Object proxy：代理对象
- Method method：目标方法
- Object[] args：目标方法的参数数组

```java
public Object invoke(Object proxy, Method method, Object[] args)
```

经过方法的连续调用，跳转到execute方法通过switch判断语句类型，再根据方法返回值的数量走对应的方法。再通过方法对其参数数组进行转换，跳转到getNamedParams方法。

```java
//此时的参数还是原来的参数数组 
public Object getNamedParams(Object[] args) {
  //这里的names是个map。key是intger，从0开始，value是string，存的注解的value
  final int paramCount = names.size();
  if (args == null || paramCount == 0) {
    return null;
    //这一步判断有没有注解，没有注解走下面的内容，有的话走else
  } else if (!hasParamAnnotation && paramCount == 1) {
    Object value = args[names.firstKey()];
    return wrapToMapIfCollection(value, useActualParamName ? names.get(0) : null);
  } else {
    //有注解，走这里，底层新建一个param集合
    final Map<String, Object> param = new ParamMap<>();
    int i = 0;
    //遍历names集合（注解集合）
    for (Map.Entry<Integer, String> entry : names.entrySet()) {
      //将names集合的value(注解名称)放到param的key里
      //将args数组的每一项，从0开始，放到param的value里
      param.put(entry.getValue(), args[entry.getKey()]);
      // add generic param names (param1, param2, ...)
      //常量GENERIC_NAME_PREFIX为param，从1开始
      final String genericParamName = GENERIC_NAME_PREFIX + (i + 1);
      // ensure not to overwrite parameter named with @Param
      //名字不包含param1走这儿，以param1为key再put一次，如果包含则不再put
      if (!names.containsValue(genericParamName)) {
        param.put(genericParamName, args[entry.getKey()]);
      }
      i++;
    }
    //循环完成，返回param集合（若无param重复的值，则数组数量为参数的两倍）
    return param;
  }
}
```





# 5 查询

## 5.1查询的几种情况

查询单行数据返回单个对象

```java
Employee getEmployeeById(Integer id);
```

查询多行数据返回对象的集合

```java
Employee getEmployeeById(Integer id);
```

查询单行数据返回Map集合 

- 数据库中的字段名为key
- 数据库中的字段值为value

```java
Map<String,Object> getEmployeeByIdReturnMap(Integer id );
```

查询多行数据返回Map集合

- 使用注解制定key值

```java
@MapKey("id") //指定使用数据库中的那个字段的值作为map的key
Map<Integer,Employee> getEmployeesReturnMap();
```



## 5.2resultType自动映射

自动映射的作用：

- 在数据库列名和Java类属性名相同的情况，MyBatis会自动将数据库的值自动匹配到 Java 类的属性（忽略大小写）当中。

  - 使用方式：resultType=“Bean的全限定类名/别名”

- MyBatis也可开启对Java的驼峰命名与数据库的字段进行匹配，例如Bean:lastName==数据库：last_name。

  - 驼峰命名开启方式：

    ```xml
    <settings>
      <setting name="mapUnderscoreToCamelCase" value="true" />
    </settings>
    ```

自动映射分为三种等级：

- NONE - 禁用自动映射。仅对手动映射的属性进行映射。
- PARTIAL （默认）- 对除在内部定义了嵌套结果映射（也就是连接的属性）以外的属性进行映射。
- FULL - 自动映射所有属性。



## 5.3resultMap自定义映射★

数据库中字段的名字不满足自动映射也不满足驼峰时，可以起别名和自定义映射（手动映射 ）

定义步骤：

- 新建一个resultMap标签设置映射关系，该标签有两个属性，

  - Id属性：自定义名字
  - type属性：设置映射类型（**最终返回的类型**）

- resultMap标签下还有子标签id和result，用来映射bean类的属性和sql的查询字段

  - 两个子标签的property属性为JavaBean的属性 

  - 两个子标签的column属性为查询结果的列名 
  - 两个子标签的javaType属性为映射的java全限定类名（了解）
  - 两个子标签的jdbcType属性为JDBC 类型（了解）
  - 两个子标签的typeHandler属性可以覆盖默认的类型处理器（了解）

- 官方推荐子标签id用来设置主键id的映射，可以提高效率

```xml
<!--id为引用该自定义映射的标识，type为最后返回的类型-->
<resultMap id="empMap" type="employee">
  <!--id和result功能一样，用来映射bean类的属性和sql的查询字段-->
  <!--property是属性，column是字段-->
  <id property="id" column="id"></id>
  <!--当满足驼峰匹配和自动映射时也推荐写全映射属性，不然多表查询有问题⚠️-->
  <result property="name" column="last_name"></result>
</resultMap>
```



## 5.4级联查询

级联查询一对一：

- 第一步：编写employee自定义映射，为级联属性赋值。

```xml
<resultMap id="resultMap1" type="employee">
    <id property="id" column="id"></id>
    <result property="name" column="last_name"></result>
    <result property="email" column="email"></result>
    <result property="gender" column="gender"></result>
    <result property="salary" column="salary"></result>
    <!--级联属性赋值后，自动为其属性进行查询-->
    <result property="dept.id" column="dept_id"></result>
    <result property="dept.name" column="name"></result>
</resultMap>
```

- 第二步：编写select标签，引用自定义映射，使用JOIN关联两表进行查询。

```xml
<select id="getById" resultMap="resultMap1">
    SELECT employees.*,departments.name name
    FROM employees JOIN departments
    ON employees.dept_id = departments.id
    WHERE employees.id = #{id}
</select>
```



## 5.5关联查询

### 5.5.1关联查询一对一

- 第一步，先写employee自定义映射，添加association标签给对象成员变量的属性赋值：
  - property：为employee中的哪个属性做映射
  - javaType：指定映射的属性类型（写全类名）

```xml
<resultMap id="resultMap2" type="employee">
    <id property="id" column="id"></id>
    <result property="name" column="last_name"></result>
    <result property="email" column="email"></result>
    <result property="gender" column="gender"></result>
    <result property="salary" column="salary"></result>
  	<!--property：为employee中的属性做映射，javaType：指定映射的属性类型-->
    <association property="dept" javaType="department">
        <result property="id" column="dept_id"></result>
        <result property="name" column="name"></result>
    </association>
</resultMap>
```

- 第二步，写select标签，引用自定义映射，使用JOIN关联两表进行查询。

```xml
<select id="getById" resultMap="resultMap2">
    SELECT employees.*,departments.name name
    FROM employees JOIN departments
    ON employees.dept_id = departments.id
    WHERE employees.id = #{id}
</select>
```



### 5.5.2关联查询一对多

- 第一步，先写自定义映射，拿多个值时不能用association标签给集合赋值了，要用**collection标签**：
  - property：集合的属性名
  - ofType：集合的泛型，即集合存储的什么类型的对象

```xml
<resultMap id="resultMap" type="department">
  <id property="id" column="dept_id"></id>
  <result property="name" column="name"></result>
  <!--property：集合的属性名，ofType：集合的泛型，即集合存储的什么类型的对象-->
  <collection property="emps" ofType="employee">
    <id property="id" column="id"></id>
    <result property="name" column="last_name"></result>
    <result property="email" column="email"></result>
    <result property="gender" column="gender"></result>
    <result property="salary" column="salary"></result>
  </collection>
</resultMap>
```



## 5.6分步查询

### 5.6.1分步查询一对一★

分步查询一对一的步骤：（查询两次，要创建各自的接口和映射文件 ）

1. 先通过员工的id查询员工信息
2. 再通过查询出来的员工信息中的外键(部门id)查询对应的部门信息
3. 将第二次查询到的部门信息通过association标签设置到员工中

好处：

1. 可复用
2. 支持懒加载

实际操作：

- 第一步，在Employee和Deparment的tMapper接口中定义对应的抽象方法
- 第二步，创建他们的映射xml文件，通过select标签实现接口中的方法
- 第三步，根据方法写自定义映射，通过association标签分步查询，发起第二次部门查询，给部门属性赋值
  - property属性：指定Employee中部门的属性名**（把值放到哪个属性里），根据第二步查询结果给其赋值**
  - select属性：通过接口的**全类名.方法名**去锁定一个方法
  - column属性：设置将第一条sql语句的查询结果的哪一列值，作为**第二条sql语句的查询语句的参数**

第一次查询，EmployeeMapper.xml文件：

```xml
<resultMap id="resultMap" type="employee">
  <id property="id" column="id"></id>
  <result property="name" column="last_name"></result>
  <result property="email" column="email"></result>
  <result property="gender" column="gender"></result>
  <result property="salary" column="salary"></result>
  <!--二次查询，设置属性名、返回类型、查询方法、查询参数-->
  <association
               property="dept"
               javaType="department"
               select="atguigu.mapper.DepartmentMapper.getDeptById"
               column="dept_id"
               ></association>
</resultMap>
<!--第一步查询的sql-->
<select id="getEmpById" resultMap="resultMap">
  SELECT * FROM employees WHERE id = #{id}
</select>
```

第二次查询，DepartmentMapper.xml文件：

```xml
<!--第二步查询的sql-->
<select id="getDeptById" resultType="department">
    SELECT * FROM departments WHERE id = #{id}
</select>
```



### 5.6.2分步查询一对多★

分步查询一对多的步骤：（查询两次，要创建各自的接口和映射文件 ）

1. 先通过name查询到部门的信息
2. 再通过查询出来的部门信息中的外键(部门id)查询对应的员工信息
3. 将第二次查询到的多个员工信息使用collection标签设置到部门中

具体实现：

- 第一步，先修改自定义映射，把二次查询的方法和查询的参数都设置上去

```xml
<resultMap id="resultMap" type="department">
    <id property="id" column="dept_id"></id>
    <result property="name" column="name"></result>
    <!--将第一次查到的id传过去用于第二次查询-->
    <collection
            property="emps"
            ofType="employee"
            column="id"
            select="atguigu.mapper.EmployeeMapper.getEmpByDept">
        <id property="id" column="id"></id>
        <result property="name" column="last_name"></result>
        <result property="email" column="email"></result>
        <result property="gender" column="gender"></result>
        <result property="salary" column="salary"></result>
    </collection>
</resultMap>
```



## 5.7分步查询多列值的传递★

分布查询，第二步查询，传递单个参数时，直接使用column=“首次查询结果的字段名”即可。但是多个参数时需用其他格式

- 传递值格式：column=“ {key1 = value1, key2 = value2…} “
  - value为第一次查询结果的字段名
  - Key为自定义的名称
- 取值格式：
  - #{key}

多列值传递步骤：

第一步，根据格式传递多个值。**注意：传递的值只能是第一次查询到的字段值**

```xml
<resultMap id="resultMap" type="department">
    <id property="id" column="dept_id"></id>
    <result property="name" column="name"></result>
  	<!--分步查询，第二步查询时传递多列值-->
    <collection
            property="emps"
            ofType="employee"
            column="{deptId=id,name=name}"
            select="atguigu.mapper.EmployeeMapper.getEmpByDeptByGender">
        <id property="id" column="id"></id>
        <result property="name" column="last_name"></result>
        <result property="email" column="email"></result>
        <result property="gender" column="gender"></result>
        <result property="salary" column="salary"></result>
    </collection>
</resultMap>
```

第二步，修改二次查询的select标签，取出这些值进行查询

```xml
<select id="getEmpByDeptByGender" resultMap="resultMap">
    SELECT * FROM employees WHERE dept_id = #{deptId} AND last_name = #{name}
</select>
```



## 5.8延迟加载★

延迟加载也叫懒加载，在分步查询的基础上，可以使用延迟加载来提升查询的效率，只查第一步，按需查询第二步。

延迟加载的概述：

- 默认关闭，可以自setting中开启（全局设置）
- 第一次查询100%会查，第二次使用时才会查
- 延时加载包括按需加载的功能，二者都是全局性的设置。

| **设置名**            | **描述**                                                     |
| :-------------------- | :----------------------------------------------------------- |
| lazyLoadingEnabled    | 延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。 特定关联关系中可通过设置 fetchType 属性来覆盖该项的开关状态。 默认false关闭 |
| aggressiveLazyLoading | 开启时，任一方法的调用都会加载该对象的所有延迟加载属性。 否则，每个延迟加载属性会按需加载（参考 lazyLoadTriggerMethods)。 默认false关闭 |

可对当前分步查询使用懒加载，添加到标签的属性上（局部设置）⚠️

- fetchType=“lazy” 使用懒加载

- fetchType=“eager” 不使用懒加载（默认值）
- 分布查询上直接设置的懒加载优先级高于全局设置

使用方法：

在分步查询的基础上，可以使用延迟加载来提升查询的效率，只需要在全局的settings中进行如下的配置

```xml
<settings>
    <!-- 开启延迟加载 -->
    <setting name="lazyLoadingEnabled" value="true"/>
    <!-- 设置加载的数据是按需加载-->
    <setting name="aggressiveLazyLoading" value="false"/>
</settings>
```

在association标签中也可以设置延迟加载，将覆盖全局配置

```xml
<resultMap id="myEmp3" type="com.atguigu.mybatis.entities.Employee">
    <id property="id" column="id"></id>
    <result property="lastName" column="last_name"></result>
    <result property="email" column="email"></result>
    <result property="salary" column="salary"></result>
    <!--通过association标签分步查询给部门属性赋值
        property属性：指定Employee中部门的属性名
        select属性：指定调用那个接口的那个方法查询部门信息
        column属性：指定将那个字段的值传入到select中调用的方法中
        fetchType属性：是否使用延迟加载
            lazy： 使用延迟加载
            eager：关闭延迟加载
    -->
    <association property="dept" select="com.atguigu.mybatis.dao.DepartmentMapper.getDepartmentById"
     column="dept_id" fetchType="lazy"></association>
</resultMap>
```





# 6 动态SQL★

## 6.1if where

if用于完成简单的判断，if也可以代替whrer的功能，不过where 后要加上1=1，防止无判断条件报错。

where用于解决SQL语句中where关键字以及条件前面的and或者or的问题 

If标签的说明：

- If的test属性如果是true，则if标签中的sql语句就会拼接。反之则不拼接
- test属性中可以使用的是：
  - 当使用了@Param注解，使用注解名即可
  - 当未使用@Param注解，使用param1、param2等
  - 当使用了bean对象，test中直接使用属性名即可
  - 当使用了map集合作为参数，test中直接使用key
- 在mybatis的动态sql中，不能用&&，除非用html转义字符，不过更推荐用and⚠️
  - 比如& amp; & amp;  是&&
  - 比如&quat;&quat;是双引号

where标签的说明：

- 自动去除某些条件前面多余的and或or

- 所有条件都为空时，where标签保证不会生成where子句

注意：where不能去掉后面的and和or！！！

```xml
    <select id="getEmp" resultType="employee">
        SELECT * FROM employees
        <where>
            <!--第一个就直接拼接AND也没事，where标签会去掉-->
            <if test="param1 != null">
                AND dept_id=#{param1}
            </if>
            <if test="param2 != null">
                AND gender=#{param2}
            </if>
        </where>
    </select>
```



## 6.2trim

Trim功能包括且大于where

trim 可以在条件判断完的SQL语句前后添加或者去掉指定的字符

- prefix: 添加前缀
- prefixOverrides: 去掉前缀
- suffix: 添加后缀
- suffixOverrides: 去掉后缀

使用方式:

- 属性值中的“或”写成"｜"
- prefix=“WHERE”。是在trim标签的所有内容前面添加where，所有内容的if都为false时则不添加
- suffixOverrides="and|or”。把trim标签中每一个内容的后缀and或者or去掉

```xml
    <trim prefix="WHERE" suffixOverrides="and|or">
        <if test="param1 != null">
            dept_id=#{param1} AND
        </if>
        <if test="param2 != null">
            gender=#{param2}
        </if>
    </trim>
```



## 6.3set

set 主要是用于解决修改操作中SQL语句中可能多出逗号的问题

- set的功能也可以被trim标签所替代
- 主要使用在update语句当中，用来生成set关键字，同时去掉最后多余的“,”
- 我们只更新提交的不为空的字段。如果提交的字段是空或者""，这个字段将不更新
- 比如可动态生成语句：UPDATE employees SET last_name="小伍",salary=66666 WHERE id=6
- 使用方式
  - 注意：是去除后面多余的”,"
  - 必须要保证有一个条件是成立的，不然报错！！！⚠️

```xml
    <update id="updateEmp">
        UPDATE employees
        <set>
            //如果为null或者空串则不修改⚠️
            <if test="lastName != null and lastName != ''">last_name=#{lastName},</if>
            <if test="email != null">email=#{email},</if>
            <if test="gender != null">gender=#{gender},</if>
            <if test="salary != null">salary=#{salary},</if>
            <if test="deptId != null">dept_id=#{deptId},</if>
        </set>
        WHERE id=#{id}
    </update>
```

```java
//测试该标签：值为null的都未进行修改
Employee employee = new Employee(6, null, "小陆", null, 88888.0, null);
mapper.updateEmp(employee);
sqlSession.commit();
```



## 6.4choose(when,otherwise)

等同于switch(case、default)只要有一个分支执行了，其他分支都不被执行，但一定有一个分支被执行   

- 使用方式：
  - 根据部门、性别、薪资其中之一进行查找
  - 查找的优先级从上到下，等同于switch，没满足的条件走otherwise

```xml
<select id="getEmpByGenderByDeptBySally" resultType="employee">
    SELECT * FROM employees
    <where>
        <choose>
            <when test="param1!=null">dept_id=#{param1}</when>
            <when test="param2!=null">gender=#{param2}</when>
            <otherwise>
                salary=#{param3}
            </otherwise>
        </choose>
    </where>
</select>
```



## 6.5foreach

循环数组或集合，动态生成sql，常用于批量删除和批量插入

动态生成语句例子：

- 批量删除-IN：DELETE FROM employees WHERE id IN(1,2,3)
- 批量删除-OR：DELETE FROM employees WHERE id=25 OR id=26 OR id=27
- 批量插入：INSERT INTO employees VALUES(null,"小黑",null,0,666,2),(...),(...)

使用方法：

- collection: 要迭代的数组或集合
  - **注意：底层会转换为map集合，必须使用注解、arg0或者array、collection等类型名称才能取到值！**
- item: 代表数组或集合中的元素
  - Item为bean对象时，取属性值格式为：对象.属性名

- open: 开始字符，拼接的sql语句最前面
- close:结束字符，拼接的sql语句最后面
- separator: 元素与元素之间的分隔符
- index:
  - 迭代的是List集合: index表示的当前元素的下标
  - 迭代的Map集合: index表示的当前元素的key

批量删除-IN：

```xml
<delete id="deleteByIds">
  DELETE FROM employees WHERE id IN
  <foreach collection="array" item="id" separator="," open="(" close=")">
    #{id}
  </foreach>
</delete>
```

```java
//测试批量删除-IN 
Integer[] ids = {1, 2, 3};
mapper.deleteByIds(ids);
sqlSession.commit();
```

批量删除-OR：

```xml
<delete id="deleteByIdsOr">
  DELETE FROM employees WHERE
  <foreach collection="array" item="id" separator="OR">
    id=#{id}
  </foreach>
</delete>
```

```java
//测试批量删除-OR
Integer[] ids = {31, 32, 33};
mapper.deleteByIdsOr(ids);
sqlSession.commit();
```

批量插入：

```xml
<insert id="insertMany">
  INSERT INTO employees VALUES
  <foreach collection="collection" item="emp" separator=",">
    (null,
    #{emp.lastName},
    #{emp.email},
    #{emp.gender},
    #{emp.salary},
    #{emp.deptId})
  </foreach>
</insert>
```

```java
//测试批量插入  
Employee employee1 = new Employee(null, null, "小七", null, 77777.0, null);
Employee employee2 = new Employee(null, null, "小八", null, 88888.0, null);
Employee employee3 = new Employee(null, null, "小九", null, 99999.0, null);
List list = new ArrayList();
list.add(employee1);
list.add(employee2);
list.add(employee3);
mapper.insertMany(list);
sqlSession.commit();
```



## 6.6sql include

sql 标签是用于抽取可重用的sql片段，将相同的，使用频繁的SQL片段抽取出来，单独定义，方便多次引用

使用方法：

- sql标签用来生命sql片段
  - id属性为引用该片段的标识
- include标签用来将声明的sql片段包含到某个sql语句当中
  - refid属性用来引入sql片段

作用：提高复用性，易维护。

使用步骤：

先用sql标签声明一个sql片段：

```xml
<sql id="empColumn">
    id,
    last_name lastName,
    email,
    gender,
    salary,
    dept_id deptId
</sql>
```

再使用include标签引入声明的sql片段

```xml
<select id="getEmp" resultType="employee">
    SELECT
    <include refid="empColumn"></include>
    FROM employees
</select>
```





# 7 MyBatis缓存机制

## 7.1缓存机制概述

执行DQL（select语句）的时候将查询结果放到缓存当中（内存当中），如果下一次**还是执行完全相同的DQL语句**，直接从缓存中拿数据，不再查数据库了，即不执行IO从硬盘上找数据了，极大提高了查询效率。

**注意：再说一次，缓存只针对select语句⚠️**

**缓存的本质：是把查询到的结果，以对象的形式传入到了虚拟机运行内存的堆中，根据查询情况去决定是不是直接取出该对象还是再查一次！**

常见缓存机制：

- 字符串常量池
- 整型常量池
- 线程池
- 数据库连接池
- mybatis缓存池

mybatis缓存等级：

- 一级缓存：将查询到的数据存储到SqlSession中（针对当前会话）
- 二级缓存：将查询到的数据存储到SqlSessionFactory中（针对整个数据库）
- 或者集成其他第三方缓存：比如EhCache、Memcache等



## 7.2一级缓存★

一级缓存(local cache), 本地缓存

概述：

- 一级缓存(local cache), 即本地缓存, 作用域默认为sqlSession。当  Session flush 或 close 后, 该 Session 中的所有 Cache 将被清空。
- 本地缓存不能被关闭, 但可以调用 clearCache() 来清空本地缓存, 或者改变缓存的作用域。
- 在mybatis3.1之后, 可以配置本地缓存的作用域. 在 mybatis的全局配置文件中配置。

Setting设置：

- 设置名：localCacheScope
- 默认值：SESSION
- 描述：MyBatis 利用本地缓存机制（Local Cache）防止循环引用和加速重复的嵌套查询。 默认值为 SESSION，会缓存一个会话中执行的所有查询。 若设置值为 STATEMENT，本地缓存将仅用于执行语句，对相同 SqlSession 的不同查询将不会进行缓存。

一级缓存底层原理：

- 同一次会话期间只要查询过的数据都会保存在当前SqlSession的一个Map中
  - key: hashCode+查询的SqlId+编写的sql查询语句+参数
  - value：查询结果的对象



## 7.3一级缓存失效情况★

- 不同的SqlSession对应不同的一级缓存
- 同一个SqlSession但是查询条件不同
- 同一个SqlSession两次查询期间执行了任何一次增删改操作
- 同一个SqlSession两次查询期间手动清空了缓存
- 同一个SqlSession两次查询期间提交了事务



## 7.4二级缓存★

二级缓存(second level cache)，全局作用域缓存

使用的条件：

- 在核心文件中配置 <setting name="cacheEnabled" value="true"/>，不过默认值为true，无需设置。
- 在需要使用二级缓存的SqlMapper.xml映射文件中添加配置：<cache/>
- 使用二级缓存的bean对象是可序列化的，即必须实现java.io.Serializable接口
- Sqlsession对象关闭或提交之后，一级缓存中的数据才会被写入到二级缓存当中。此时二级缓存才可用。
- 满足以上要求时，再使用同一个SqlSessionFactory对象，执行DQL并且两次DQL之间无增删改操作，就会使用缓存

使用的步骤：

- 条件1：默认开启，无需设置

- 条件2：在对应的Mapper.xml中添加顶级标签<cache/>

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
  <mapper namespace="com.atguigu.mapper.EmployeeMapper">
  
      <cache/>
    
      ......
  
  <mapper/>
  ```

- 条件3：操作对象要实现java.io.Serializable接口

  ```java
  public class Employee implements Serializable {
  }
  ```

- 条件4：首次执行查询方法后，必须把Sqlsession对象关闭或提交，才会将一级缓存写到二级缓存中

  ```java
  SqlSessionFactoryBuilder builder= new SqlSessionFactoryBuilder();
  SqlSessionFactory factory = builder.build(Resources.getResourceAsStream("mybatis-config.xml"));
  //根据同一个SqlSessionFactory对象获取两个不同的连接对象
  SqlSession sqlSession1 = factory.openSession();
  SqlSession sqlSession2 = factory.openSession();
  //根据不同连接对象获取mapper对象
  EmployeeMapper mapper1 = sqlSession1.getMapper(EmployeeMapper.class);
  EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
  System.out.println(mapper1.getEmp(2, 2));
  //执行完第一个select语句之后，已经存到sqlSession1的一级缓存之中了
  //现在执行commit()或close()方法⚠️
  //将sqlSession1的一级缓存中存到二级缓存SqlSessionFactory中
  //查询了两次，最终却只执行了一次查询操作
  sqlSession1.commit();
  System.out.println(mapper2.getEmp(2, 2));
  ```

二级缓存失效条件：

- 两次DQL之间做了增删改操作，一级二级缓存都会失效

二级缓存相关属性设置：

- 设置位置：在<cache/>标签内设置
- eviction=“FIFO”：缓存回收策略：
  - LRU – 最近最少使用的：移除最长时间不被使用的缓存对象（还有种类似的淘汰算法LFU）
  - FIFO – 先进先出：按对象进入缓存的顺序来移除它们，先进入二级缓存的对象先淘汰。
  - SOFT – 软引用：移除基于垃圾回收器状态和软引用规则的对象。
  - WEAK – 弱引用：更积极地移除基于垃圾收集器状态和弱引用规则的对象。
- flushInterval：刷新间隔，单位毫秒，**不设置则不刷新缓存，一直向二级缓存中缓存数据⚠️**
- size：设置二级缓存可存储的对象数量，正整数
- readOnly：只读，true/false
  - 若为true，第一次查到的缓存对象与第二次从换存中拿到的对象是同一个，效率高线程不安全
  - 若为false，会通过序列化拷贝一个新的对象返回给你，默认为false，线程安全，但效率低



## 7.5缓存的相关属性设置

1. 全局setting的cacheEnabled：

   配置二级缓存的开关，一级缓存一直是打开的。

2. **select标签的useCache属性：**

   这个属性是用来配置这个select是否使用二级缓存，默认为true。

   一级缓存一直是使用的。

3. 所有的增删改查标签中的flushCache属性：

   **增删改默认flushCache=true。正因为这样，所以增删改的sql执行以后，会同时清空一级和二级缓存。**

   **查询默认 flushCache=false。但是可以修改为true，即两次查询之间就不存在缓存，没意义了，不推荐**

4. sqlSession.clearCache()：**只是用来清除一级缓存**。



## 7.6MyBatis集成EhCache

集成EhCache是为了**代替mybatis⾃带的⼆级缓存**。⼀级缓存是⽆法替代的。

mybatis对外提供了接⼝，也可以集成第三⽅的缓存组件。⽐如EhCache、Memcache等。都可以。

EhCache是Java写的。Memcache是C语⾔写的。所以mybatis集成EhCache较为常⻅，按照以下步骤操作，就可以完成集成：

第⼀步：在pom.xml中引⼊mybatis整合ehcache的依赖。

```xml
<!--mybatis集成ehcache的组件-->
<dependency>
 <groupId>org.mybatis.caches</groupId>
 <artifactId>mybatis-ehcache</artifactId>
 <version>1.2.2</version>
</dependency>
```

第⼆步：在类的根路径下新建echcache.xml⽂件，并提供以下配置信息。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
         updateCheck="false">
    <!--磁盘存储:将缓存中暂时不使⽤的对象,转移到硬盘,类似于Windows系统的虚拟内存-->
    <diskStore path="e:/ehcache"/>

    <!--
    defaultCache：默认的管理策略
    eternal：设定缓存的elements是否永远不过期。如果为true，则缓存的数据始终有效，如果为false那么还要根据timeToIdleSeconds，timeToLiveSeconds判断
    maxElementsInMemory：在内存中缓存的element的最⼤数⽬
    overflowToDisk：如果内存中数据超过内存限制，是否要缓存到磁盘上
    diskPersistent：是否在磁盘上持久化。指重启jvm后，数据是否有效。默认为false
    timeToIdleSeconds：对象空闲时间(单位：秒)，指对象在多⻓时间没有被访问就会失效。只对eternal为false的有效。默认值0，表示⼀直可以访问
    timeToLiveSeconds：对象存活时间(单位：秒)，指对象从创建到失效所需要的时间。只对eternal为false的有效。默认值0，表示⼀直可以访问
    memoryStoreEvictionPolicy：缓存的3 种清空策略
    FIFO：first in first out (先进先出)
    LFU：Less Frequently Used (最少使⽤).意思是⼀直以来最少被使⽤的。缓存的元素有⼀个hit 属性，hit 值最⼩的将会被清出缓存
    LRU：Least Recently Used(最近最少使⽤). (ehcache 默认值).缓存的元素有⼀个时间戳，当缓存容量满了，⽽⼜需要腾出地⽅来缓存新的元  素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存
   -->
    <defaultCache 
            eternal="false" 
            maxElementsInMemory="1000" 
            overflowToDisk="false" 
            diskPersistent="false"
            timeToIdleSeconds="0" 
            timeToLiveSeconds="600" 
            memoryStoreEvictionPolicy="LRU"
    />
</ehcache>
```

第三步：修改SqlMapper.xml⽂件中的缓存标签，添加type属性。即不再用mybatis自己的缓存组件了

```xml
<!--使用第三方二级缓存-->
<cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
```

第四步：编写测试程序使⽤

```java
@Test
public void testSelectById2() throws Exception {
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().b
    uild(Resources.getResourceAsStream("mybatis-config.xml"));

    SqlSession sqlSession1 = sqlSessionFactory.openSession();
    CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
    Car car1 = mapper1.selectById(83L);
    System.out.println(car1);

    sqlSession1.close();

    SqlSession sqlSession2 = sqlSessionFactory.openSession();
    CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);
    Car car2 = mapper2.selectById(83L);
    System.out.println(car2);
}
```





# 8 MyBatis逆向工程

## 8.1逆向工程简介

MyBatis Generator: 简称MBG，是一个专门为MyBatis框架使用者定制的代码生成器，可以快速的根据表生成对应的映射文件，接口，以及bean类。原理是根据数据库表格逆向生成。

支持基本的增删改查，以及QBC风格的条件查询。但是表连接、存储过程等这些复杂sql的定义需要我们手工编写

官方文档：http://www.mybatis.org/generator/ 

官方工程：https://github.com/mybatis/generator/releases



## 8.2逆向工程基于插件

第⼀步：基础环境准备：新建一个模块，打包方式设置为jar

第⼆步：在pom中添加逆向⼯程插件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.mybatis.study</groupId>
    <artifactId>study</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <!--定制构建过程-->
    <build>
        <!--可配置多个插件-->
        <plugins>
            <!--其中的⼀个插件：mybatis逆向⼯程插件-->
            <plugin>
                <!--插件的GAV坐标-->
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.4.1</version>
                <!--允许覆盖-->
                <configuration>
                    <overwrite>true</overwrite>
                </configuration>
                <!--插件的依赖-->
                <dependencies>
                    <!--mysql驱动依赖-->
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>8.0.30</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>

</project>
```

第三步：配置generatorConfig.xml：该⽂件名必须叫做：generatorConfig.xml，必须放在类的根路径Resources下

注意：如果使用的是MySQL8，在jdbcConnection标签中还需要添加以下标签<property name="nullCatalogMeansCurrent" value="true" />主要处理识别其他库同名表名的问题

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!--
targetRuntime有两个值：
MyBatis3Simple：⽣成的是基础版，只有基本的增删改查。
MyBatis3：⽣成的是增强版，除了基本的增删改查之外还有复杂的增删改查。
-->
    <context id="DB2Tables" targetRuntime="MyBatis3Simple">
        <!--防⽌⽣成重复代码-->
        <plugin type="org.mybatis.generator.plugins.UnmergeableXmlMappersPlugin"/>

        <commentGenerator>
            <!--是否去掉⽣成⽇期-->
            <property name="suppressDate" value="true"/>
            <!--是否去除注释-->
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>
        <!--连接数据库信息-->
        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/ssm"
                        userId="root"
                        password="12345678">
        </jdbcConnection>
        <!-- ⽣成pojo包名和位置 -->
        <javaModelGenerator targetPackage="com.powernode.mybatis.pojo" targetProject="src/main/java">
            <!--是否开启⼦包-->
            <property name="enableSubPackages" value="true"/>
            <!--是否去除字段名的前后空⽩-->
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!-- ⽣成SQL映射⽂件的包名和位置 -->
        <sqlMapGenerator targetPackage="com.powernode.mybatis.mapper" targetProject="src/main/resources">
            <!--是否开启⼦包-->
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>
        <!-- ⽣成Mapper接⼝的包名和位置 -->
        <javaClientGenerator
                type="xmlMapper"
                targetPackage="com.powernode.mybatis.mapper"
                targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>
        <!-- 表名和对应的实体类名-->
        <table tableName="employees" domainObjectName="Employee"/>
        <table tableName="departments" domainObjectName="Department"/>
    </context>
</generatorConfiguration>
```

第四步：运⾏插件（在Maven的生命周期插件位置：Plugins mybatis-generator mybatis-generator:mybatis-generate）

第五步：编写核心配置文件添加依赖

```xml
<dependencies>
    <!-- mybatis-generator-core -->
    <dependency>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-core</artifactId>
        <version>1.3.6</version>
    </dependency>

    <!--导入MySQL的驱动包-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.25</version>
    </dependency>

    <!--导入MyBatis的jar包-->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.6</version>
    </dependency>

    <!--junit-->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

第六步：编写测试程序

```java
public class MainTest {
    @Test
    public void testMGB() throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder
                ().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        mapper.deleteByPrimaryKey(4);
        sqlSession.commit();
    }
}
```

第七步：创建Example查询条件（QBC风格）

```java
// 查⼀个
Car car = mapper.selectByPrimaryKey(89L);
System.out.println(car);
// 查所有
List<Car> cars = mapper.selectByExample(null);
cars.forEach(c -> System.out.println(c));
// 多条件查询
// QBC ⻛格：Query By Criteria ⼀种查询⽅式，⽐较⾯向对象，看不到sql语句。
CarExample carExample = new CarExample();
carExample.createCriteria()
        .andBrandEqualTo("丰⽥霸道")
        .andGuidePriceGreaterThan(new BigDecimal(60.0));
carExample.or().andProduceTimeBetween("2000-10-11", "2022-10-11");
mapper.selectByExample(carExample);
sqlSession.commit();
```



## 8.3逆向工程基于命令

第⼀步：基础环境准备：新建一个模块，打包方式设置为jar

第⼆步：在pom中添加逆向⼯程的jar包和其他依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>mybatis05</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- mybatis-generator-core -->
        <dependency>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-core</artifactId>
            <version>1.3.6</version>
        </dependency>

        <!--导入MySQL的驱动包-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.25</version>
        </dependency>

        <!--导入MyBatis的jar包-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.6</version>
        </dependency>

        <!--junit-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/log4j/log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>

    </dependencies>
</project>
```

第三步：在Resources下编写MBG的配置文件mbg.xml（重要几处配置）,参考官方文档

注意：如果使用的是MySQL8，在jdbcConnection标签中还需要添加以下标签<property name="nullCatalogMeansCurrent" value="true" />主要处理识别其他库同名表名的问题

```xml
<!DOCTYPE generatorConfiguration PUBLIC
        "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!--
id属性：设置一个唯一标识
targetRuntime属性值说明：
MyBatis3Simple：基本的增删改查
MyBatis3：带条件查询的增删改查
-->
    <context id="simple" targetRuntime="MyBatis3Simple">
        <!--设置连接数据库的相关信息-->
        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/ssm"
                        userId="root"
                        password="12345678">
            <!--如果是mysql8需要加上这一行-->
            <property name="nullCatalogMeansCurrent" value="true"/>
        </jdbcConnection>

        <!--设置JavaBean的生成策略-->
        <javaModelGenerator targetPackage="com.atguigu.mybatis.mbg.beans" targetProject="src/main/java"/>

        <!--设置SQL映射文件的生成策略-->
        <sqlMapGenerator targetPackage="com.atguigu.mybatis.mbg.mapper" targetProject="src/main/resources"/>

        <!--设置Mapper接口的生成策略-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.atguigu.mybatis.mbg.mapper"
                             targetProject="src/main/java"/>

        <!--逆向分析的表-->
        <table tableName="employees" domainObjectName="Employee"/>
        <table tableName="departments" domainObjectName="Department"/>
    </context>
</generatorConfiguration>
```

第四步：参考官方文档创建代码生成器运行代码并运行

```java
@Test
public void testMGB() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
    List<String> warnings = new ArrayList<String>();
    boolean overwrite = true;
    //使用JUNIT时，路径的含义是从当前模块下查找
    File configFile = new File("src/main/resources/mbg.xml");
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(configFile);
    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
}
```

第五步：添加核心配置文件并配置文件信息

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <!--自动匹配驼峰-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>

    <typeAliases>
        <package name="com.atguigu.mybatis.mbg.beans"/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/ssm"/>
                <property name="username" value="root"/>
                <property name="password" value="12345678"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <package name="com.atguigu.mybatis.mbg.mapper"/>
    </mappers>
</configuration>
```

第六步：编写测试程序

```java
@Test
public void testSelectByPrimaryKey() throws IOException {
    //设置MyBatis全局配置文件的路径
    String resource = "mybatis-config.xml";
    //读取类路径下的配置文件得到输入流
    InputStream inputStream = Resources.getResourceAsStream(resource);
    //创建SqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    //获取SqlSession对象，相当于Connection对象
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
        //获取Mapper代理对象
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectByPrimaryKey(1);
        System.out.println(employee);
    } finally {
        //关闭sqlSession
        sqlSession.close();
    }
}
```





# 9 MyBatis拓展

## 9.1PageHelper分页插件★

PageHelper插件基于SQL的limit分⻚实现

- mysql的limit后⾯两个数字：
  - 第⼀个数字：startIndex（起始下标。下标从0开始。）
  - 第⼆个数字：pageSize（每⻚显示的记录条数）
  - 若只写一个n，则默认从下标0开始显示n条数据
- 假设已知⻚码pageNum，还有每⻚显示的记录条数pageSize，第⼀个数字可以动态的获取吗？
  - startIndex = (pageNum - 1) * pageSize
- 分析：
  - 通过limit获取数据并不难
  - 通过limit获取是否有下一页等相关数据比较难，还是推荐使用插件

pagehelp插件的使用步骤：

第⼀步：引⼊依赖

```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.3.1</version>
</dependency>
```

第⼆步：在mybatis-config.xml⽂件中配置插件：（typeAliases标签下⾯进⾏配置）

```xml
<plugins>
 	<plugin interceptor="com.github.pagehelper.PageInterceptor"></plugin>
</plugins>
```

第三步：编写Java代码

```java
//查询前，开启分页功能。参数为页码和每页显示个数，可返回Page对象
//Page<Object> page = PageHelper.startPage(1, 2);
PageHelper.startPage(1, 2);

// 执⾏查询语句，不再获取全部，会根据页码和每页显示个数获取
List<Employee> empList = mapper.getEmp();

//打印当前页的每条对象信息
for (Employee employee : empList) {
  System.out.println(employee);
}
//System.out.println("当前页是："+page.getPageNum());
//System.out.println("每页显示的条数是："+page.getPageSize());
//System.out.println("总页数是："+page.getPages());
//System.out.println("总记录数是："+page.getTotal());

// 查询后，获取分⻚信息PageInfo对象，设置导航卡片数量
PageInfo<Employee> pageInfo = new PageInfo<>(empList, 5);
System.out.println(pageInfo.getList());
sqlSession.close();
```

获取PageInfo属性的方法

```java
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;

    //由于startRow和endRow不常用，这里说个具体的用法
    //可以在页面中"显示startRow到endRow 共size条数据"

    //当前页面第一个元素在数据库中的行号⚠️
    private int startRow;
    //当前页面最后一个元素在数据库中的行号⚠️
    private int endRow;
    //总记录数⚠️
    private long total;

    //总页数
    private int pages;
    //结果集
    private List list;

    //前一页
    private int prePage;
    //下一页
    private int nextPage;
    //是否为第一页
    private boolean isFirstPage;
    //是否为最后一页
    private boolean isLastPage;
    //是否有前一页
    private boolean hasPreviousPage;
    //是否有下一页
    private boolean hasNextPage;
    //导航页码数
    private int navigatePages;
    //所有导航页号
    private int[] navigatepageNums;
    //导航条上的第一页
    private int navigateFirstPage;
    //导航条上的最后一页
    private int navigateLastPage;

    public PageInfo() {
        this.isFirstPage = false;
        this.isLastPage = false;
        this.hasPreviousPage = false;
        this.hasNextPage = false;
    }
```

项目实例：

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <div th:fragment="pagination">
        <!--隐藏域，name是pageNum-->
        <input type="hidden" name="pageNum">
        <div class="col-sm-6">
            <div class="dataTables_info" id="DataTables_Table_0_info" role="alert" aria-live="polite" aria-relevant="all">显示
                <span th:text="${page.startRow}"></span> 到 <span th:text="${page.endRow}"></span> 项，
                共 <span th:text="${page.total}"></span> 项
            </div>
        </div>
        <div class="col-sm-6">
            <div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
                <ul class="pagination">
                    <!--首页-->
                    <li th:if="${page.isFirstPage}" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0"
                        id="DataTables_Table_0_previous">
                        <a href="javascript:;">首页</a>
                    </li>
                    <li th:if="${!page.isFirstPage}" class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0"
                        id="DataTables_Table_0_previous">
                        <a th:href="'javascript:document.forms.ec.pageNum.value=1;document.forms.ec.submit();'">首页</a>
                    </li>
                    <!--上一页-->
                    <li th:if="${page.isFirstPage}" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0"
                        id="DataTables_Table_0_previous">
                        <a href="javascript:;">上一页</a>
                    </li>
                    <li th:if="${!page.isFirstPage}" class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0"
                        id="DataTables_Table_0_previous">
                        <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.prePage}+';document.forms.ec.submit();'">上一页</a>
                    </li>
                    <!--页码-->
                    <li th:class="${i==page.pageNum?'paginate_button active':'paginate_button'}" aria-controls="DataTables_Table_0" tabindex="0" th:each="i:${page.navigatepageNums}">
                        <a th:href="'javascript:document.forms.ec.pageNum.value='+${i}+';document.forms.ec.submit();'" th:text="${i}"></a>
                    </li>
                    <!--下一页-->
                    <li th:if="${page.isLastPage}" class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0"
                        id="DataTables_Table_0_next">
                        <a href="javascript:;">下一页</a>
                    </li>
                    <li th:if="${!page.isLastPage}" class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0"
                        id="DataTables_Table_0_next">
                        <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.nextPage}+';document.forms.ec.submit();'">下一页</a>
                    </li>
                    <!--尾页-->
                    <li th:if="${page.isLastPage}" class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0"
                        id="DataTables_Table_0_next">
                        <a href="javascript:;">尾页</a>
                    </li>
                    <li th:if="${!page.isLastPage}" class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0"
                        id="DataTables_Table_0_next">
                        <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.pages}+';document.forms.ec.submit();'">尾页</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
```





## 9.2junit断言机制

junit使用包含三个步骤：

- 设置实际值
- 设置期望值
- 添加断言进行测试

```java
@Test
public void test01() {
    MathService mathService = new MathService();
    // 实际值
    int actual = mathService.sub(10, 5);
    // 期望值
    int expected = 5;
    // 添加断言机制
    Assert.assertEquals(expected, actual);
}
```



## 9.3封装SqlSessionUtil

```java
public class SqlSessionUtil {

    // 工具类的构造方法一般都是私有化的。
    // 工具类中所有的方法都是静态的，直接采用类名即可调用。不需要new对象。
    // 为了防止new对象，构造方法私有化。
    private SqlSessionUtil() {
    }

    private static SqlSessionFactory sqlSessionFactory;

    // 类加载时，解析mybatis-config.xml文件。创建SqlSessionFactory对象。
    // 一个SqlSessionFactory对应一个environment
    // 一个environment通常是一个数据库。
    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取会话对象。
     *
     * @return 会话对象
     */
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

}
```



## 9.4日志组件

主要说明如何集成logback框架和log4j框架

### 9.4.1日志组件概述

日志组件的设置：

- 在**mybatis-config.xml**文件中，可以通过settings进行配置
- 配置的书写顺序必须严格按照xml的dtd约束。
- settings是核心配置标签，影响mybatis的行为
- 注意：这里若不通过logImpl指定的话，则默认会自动查找！

```xml
 <settings >
   <setting name = "logImpl" value = "STDOUT_LOGGING" / >
 </settings >
```

mybatis常见的集成的日志组件：

- SLF4J（沙拉风）：**沙拉风是一个日志标准**，其中有一个框架叫做logback，它实现了沙拉风规范。
- LOG4J
- LOG4J2
- STDOUT_LOGGING（标准日志）：mybatis已经实现了这种标准日志



### 9.4.2集成logback日志框架

logback日志框架实现了slf4j标准。(沙拉风：日志门面。日志标准。)

第一步：引入logback的依赖。

```xml
<!--logback-->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.11</version>
</dependency>
```

第二步：引入logback所必须的xml配置文件。

- 这个配置文件的名字必须叫做：logback.xml或者logback-test.xml，不能是其它的名字。

- 这个配置文件必须放到类的根路径下。不能是其他位置。

- 主要配置日志输出相关的级别以及日志具体的格式。

```xml
<configuration debug="false">
    <!--控制台输出-->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>
    <!--mybatis log configure-->
    <logger name="com.apache.ibatis" level="TRACE"/>
    <logger name="java.sql.Connection" level="DEBUG"/>
    <logger name="java.sql.Statement" level="DEBUG"/>
    <logger name="java.sql.PreparedStatement" level="DEBUG"/>
    <!--日志输出级别,logback日志级别包括五个：TRACE < DEBUG < INFO < WARN < ERROR-->
    <root level="DEBUG">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="FILE"/>
    </root>
</configuration>
```



### 9.4.3集成log4j日志框架

第一步：引入log4j的依赖

```xml
<!--log4j-->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

第二步：创建log4j 的配置文件log4j.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
 
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
 
 <appender name="STDOUT" class="org.apache.log4j.ConsoleAppender">
   <param name="Encoding" value="UTF-8" />
   <layout class="org.apache.log4j.PatternLayout">
    <param name="ConversionPattern" value="%-5p %d{MM-dd HH:mm:ss,SSS} %m  (%F:%L) \n" />
   </layout>
 </appender>
 <logger name="java.sql">
   <level value="debug" />
 </logger>
 <logger name="org.apache.ibatis">
   <level value="info" />
 </logger>
 <root>
   <level value="debug" />
   <appender-ref ref="STDOUT" />
 </root>
</log4j:configuration>
```

