# 1 搭建环境

## 1.1创建项目结构

- 创建父工程shf_parent，类型为project，删除src目录。
- 创建工具类子模块common_util，类型为model，创建时选择父项目为shf_parent。
- 创建实体类子模块model，类型为Maven的model，创建时选择父项目为shf_parent。
- 创建项目子模块web_admin，类型为Maven的model，创建时选择父项目为shf_parent。



## 1.2配置依赖关系

### 1.2.1父工程shf_parent

pom.xml文件内容：

- 打包方式设置为pom（idea自动完成）
- 聚合三个子模块（idea自动完成）
- 添加依赖
- 设置Maven编译版本

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu</groupId>
    <artifactId>shf_parent</artifactId>
    <!--打包方式设置为父工程，idea自动设置-->
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>

    <!--idea自动生成的聚合-->
    <modules>
        <module>common_util</module>
        <module>model</module>
        <module>web_admin</module>
    </modules>

    <!--jar包的版本管理-->
    <properties>
        <java.version>1.8</java.version>
        <spring.version>5.2.20.RELEASE</spring.version>
        <thymeleaf.version>3.0.11.RELEASE</thymeleaf.version>
        <pagehelper.version>4.1.6</pagehelper.version>
        <servlet-api.version>4.0.1</servlet-api.version>
        <fastjson.version>1.2.70</fastjson.version>
        <mybatis.version>3.5.6</mybatis.version>
        <mybatis.spring.version>2.0.6</mybatis.spring.version>
        <mysql.version>8.0.25</mysql.version>
        <druid.version>1.1.12</druid.version>
        <commons-fileupload.version>1.3.1</commons-fileupload.version>
        <slf4j-version>1.7.30</slf4j-version>
        <logback-version>1.2.3</logback-version>
    </properties>

    <!--依赖管理，子类可按需引用继承-->
    <dependencyManagement>
        <dependencies>
            <!-- SpringMVC相关 -->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <!--spring封装的jdbc数据库访问-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-tx</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <!--Spring提供的对AspectJ框架的整合-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-aspects</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <!--用于spring测试(没啥用，就是为了单元测试)-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-test</artifactId>
                <version>${spring.version}</version>
            </dependency>

            <!--用于springMVC模板-->
            <dependency>
                <groupId>org.thymeleaf</groupId>
                <artifactId>thymeleaf-spring5</artifactId>
                <version>${thymeleaf.version}</version>
            </dependency>

            <!--mybatis的分页插件-->
            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper</artifactId>
                <version>${pagehelper.version}</version>
            </dependency>
            <!-- Mybatis -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>${mybatis.version}</version>
            </dependency>
            <!-- Mybatis与Spring整合所需要的jar包 -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis-spring</artifactId>
                <version>${mybatis.spring.version}</version>
            </dependency>
            <!-- MySql -->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <!-- 连接池 -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <!-- 文件上传组件 -->
            <dependency>
                <groupId>commons-fileupload</groupId>
                <artifactId>commons-fileupload</artifactId>
                <version>${commons-fileupload.version}</version>
            </dependency>

            <!-- fastjson json转换的-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>${fastjson.version}</version>
            </dependency>

            <!-- 日志 -->
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-api</artifactId>
                <version>${slf4j-version}</version>
            </dependency>
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-classic</artifactId>
                <version>${logback-version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <!--子类强制继承的依赖-->
    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>${servlet-api.version}</version>
            <!--Servlet-API  不会部署到服务器上-->
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- 控制Maven的编译版本：java编译插件，设置编译版本1.8-->
            <!-- 其实之前在maven的配置文件中设置过，不加也行⚠️ -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.2</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



### 1.2.2子模块common_util

pom.xml文件内容：

- 继承父工程（idea自动完成）
- 选择性继承父工程内管理的依赖
- 添加依赖
- 设置Maven编译版本

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>shf_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <!--version、groupId与父模块相同，可省略只写artifactId-->
    <artifactId>common_util</artifactId>

    <!--将父工程内管理的jar包都继承下来，可以不用加version-->
    <dependencies>
        <!-- SpringMVC相关 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <!--spring封装的jdbc数据库访问-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
        </dependency>
        <!--Spring提供的对AspectJ框架的整合-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
        </dependency>
        <!--用于spring测试-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
        </dependency>

        <!--用于springMVC模板-->
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf-spring5</artifactId>
        </dependency>

        <!--mybatis的分页插件-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
        </dependency>
        <!-- Mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
        </dependency>
        <!-- Mybatis与Spring整合所需要的jar包 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
        </dependency>
        <!-- MySql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!-- 连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>
        <!-- 文件上传组件 -->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
        </dependency>

        <!-- fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
        </dependency>

        <!-- 日志 -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
        </dependency>
    </dependencies>
</project>
```



### 1.2.3子模块model

pom.xml文件内容：

- 继承父工程（idea自动完成）
- model是实体类子模块，只提供bean类，几乎不需要引入父工程管理的依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>shf_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <!--version、groupId与父模块相同，可省略只写artifactId-->
    <artifactId>model</artifactId>

</project>
```



### 1.2.4子模块web_admin

pom.xml文件内容：

- 继承父工程（idea自动完成）
- 选择性继承父工程内管理的依赖
- 添加兄弟模块common_util和model为依赖，根据依赖传递使用他们compile的依赖
- 设置设置服务器jetty的Maven的插件形式

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>shf_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <!--version、groupId与父模块相同，可省略只写artifactId-->
    <artifactId>web_admin</artifactId>

    <!--设置打包方式为war包-->
    <packaging>war</packaging>

    <!--直接依赖兄弟模块common_util和model，根据依赖传递的特性可使用他们compile的jar包-->
    <dependencies>
        <!--使用兄弟模块common_util的依赖-->
        <dependency>
            <groupId>com.atguigu</groupId>
            <artifactId>common_util</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!--使用兄弟模块model的依赖-->
        <dependency>
            <groupId>com.atguigu</groupId>
            <artifactId>model</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <!--设置服务器jetty的参数，以Maven的插件形式存在-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.15.v20190215</version>
                <configuration>
                    <!-- 如果检测到项目有更改则自动热部署，每隔n秒扫描一次。默认为0，即不扫描-->
                    <scanIntervalSeconds>10</scanIntervalSeconds>
                    <webAppConfig>
                        <!--指定web项目的根路径，默认为/    设置上下文路径-->
                        <contextPath>/</contextPath>
                    </webAppConfig>
                    <httpConnector>
                        <!--设置端口号，默认 8080-->
                        <port>8000</port>
                    </httpConnector>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



## 1.3配置SSM环境

### 1.3.1common_util

该模块负责依赖与常用工具类管理

- 依赖管理的实现：
  - 直接继承父工程的依赖，再成为其他模块的依赖
- 工具类管理的实现：
  - 在main/java下创建不同的包，对异步请求结果处理类、枚举处理类、类型转换类、MD5加密类等工具类进行管理

模块结构：

- common_util
  - src/main/java/com.atguigu
    - result (存放工具类)
    - util (存放工具类，QiniuUtil.java文件先不加入)



### 1.3.2model

该模块主要存放数据表对应的pojo类：

- 在main/java下创建每张表对应的pojo类

模块结构：

- model
  - src/main/java/com.atguigu
    - entity (存放实体类)
    - vo (存放实体类)



### 1.3.3web_admin

该模块是ssm模块，实现MVC功能，请创建Web模块、日志模块、mvc目录结构、ssm目录结构

#### 1.3.3.1添加web模块

- 将项目的打包方式设置为war包
- 在模块结构中添加web.xml，模块名后添加路径src/main/webapp/



#### 1.3.3.2添加日志模块

在resources目录添加logback.xml文件，该文件名固定

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="false">

    <!--定义日志文件的存储地址 logs为当前项目的logs目录 还可以设置为../logs -->
    <property name="LOG_HOME" value="logs" />

    <!--控制台日志， 控制台输出 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度,%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- 日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```



#### 1.3.3.3创建mvc目录结构

mvc结构：

- web_admin/src/main

  - java/com.atguigu

    - controller (控制层)
      - IndexController(首页访问控制类)
      - RoleController(用户角色管理控制类)
    - dao (持久层)
      - RoleDao (映射接口)
    - service (业务层)
      - RoleService (业务接口)
      - impl
        - RoleServiceImpl (业务实现类)

  - resources (资源目录)

    - mapper

      - RoleDao.xml (接口映射文件)

      

#### 1.3.3.4创建ssm分层

ssm分层：

- web_admin/src/main
  - resources (资源目录)
    - mapper
      - RoleDao.xml (接口映射文件)
    - spring
      - spring-dao.xml (数据源+mybatis)⚠️
      - spring-mvc.xml (视图解析器+fastjson处理器+注解扫描器+mvc常规配置)⚠️
      - spring-service.xml (注解扫描器+事务管理器)⚠️
    - mybatis-config.xml (mybatis主配置文件：配置别名+驼峰映射+缓存+分页插件+数据厂商标识等)⚠️
    - db.properties (数据源信息)
    - logback.xml (日志文件)



#### 1.3.3.5创建web资源

web资源：

- web-admin/src/main
  - webapp (web目录)
    - WEB-INF
      - pages (必须和视图解析器前缀一致)
        - frame
          - index.html
        - role
          - index.html
        - web.xml (字符编码过滤器+DispatcherServlet+监听服务启动加载resources/spring下的三个spring容器)⚠️



#### 1.3.3.6properties.xml 

数据源配置文件

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/db_hose
jdbc.username=root
jdbc.password=12345678
```



#### 1.3.3.7spring-dao.xml

持久层dao配置文件：数据源+mybatis

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring.xsd">

    <!--引入db.properties-->
    <context:property-placeholder location="classpath:db.properties"/>
    <!--创建数据源：数据库连接池-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <!--进行Spring和MyBatis的整合-->
    <bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!--需要数据源-->
        <property name="dataSource" ref="dataSource"/>
        <!--需要加载mybatis的核心配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <!--需要加载mybatis的所有映射文件-->
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>

    <!--扫描所有的dao接口-->
    <mybatis-spring:scan base-package="com.atguigu.dao"/>

</beans>
```



#### 1.3.3.8spring-mvc.xml

控制层mvc配置文件：视图解析器+fastjson处理器+注解扫描器+mvc常规配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--controller包的注解扫描-->
    <context:component-scan base-package="com.atguigu.controller"/>

    <!--配置视图解析器 ：Thymeleaf  SpringBoot之后是不需要自己配置-->
    <bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver" id="viewResolver">
        <!--配置字符集属性-->
        <property name="characterEncoding" value="UTF-8"></property>
        <!--配置模板引擎属性-->
        <property name="templateEngine">
            <!--配置内部bean-->
            <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                <!--配置模块的解析器属性-->
                <property name="templateResolver">
                    <!--配置内部bean-->
                    <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                        <!--配置前缀  ★-->
                        <property name="prefix" value="/WEB-INF/pages/"></property>
                        <!--配置后缀  ★-->
                        <property name="suffix" value=".html"></property>
                        <!--配置字符集-->
                        <property name="characterEncoding" value="UTF-8"></property>
                    </bean>
                </property>
            </bean>
        </property>
    </bean>

    <!--mvc的驱动设置，fastjson转换器的添加-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <!-- 配置Fastjson支持 -->
            <bean class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                        <value>application/json</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!--静态资源访问-->
    <mvc:default-servlet-handler/>

    <!--配置首页访问-->
    <mvc:view-controller path="index.html" view-name="frame/index"/>

</beans>
```



#### 1.3.3.9spring-service.xml

业务层service配置文件：注解扫描器+事务管理器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--设置一个注解的扫描包-->
    <context:component-scan base-package="com.atguigu.service"/>

    <!--事务-->
    <!--配置事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--需要数据源-->
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--开启事务的注解支持-->
    <tx:annotation-driven transaction-manager="transactionManager"/>

</beans>
```



#### 1.3.3.10mybatis-config.xml

mybatis主配置文件：配置别名+驼峰映射+缓存+分页插件+数据厂商标识等

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <!--自动驼峰-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
        <!--延迟加载-->
        <!--缓存-->
        <!--自动映射：默认是开的，一般不关-->
    </settings>
    <!--起别名-->
    <typeAliases>
        <package name="com.atguigu"/>
    </typeAliases>
    <!--分页插件：用到的时候在配置-->

    <!--数据库厂商的标识：看需求-->

    <!--其他的都交给Spring了-->
</configuration>
```



#### 1.3.3.11web.xml 

监听服务启动初始化spring容器，字符编码过滤器+前端控制器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!-- 解决post乱码 添加字符编码过滤器 -->
    <filter>
        <filter-name>encode</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceRequestEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encode</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!-- 配置SpringMVC框架前端控制器 -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>

    <!-- 加载spring容器 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <!-- 使用通配符，一次性加载spring目录下的三个spring容器 -->
        <param-value>classpath:spring/spring-*.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
  
</web-app>
```



## 1.4测试SSM环境

### 1.4.1完善dao层

dao/RoleDao接口

```java
package com.atguigu.dao;

import com.atguigu.entity.Role;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface RoleDao {
    List<Role> findAll();
}
```

resoueces/mapper/RoleDao.xml映射文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成RoleMapper接口的全类名-->
<mapper namespace="com.atguigu.dao.RoleDao">

    <!-- 用于select查询公用抽取的列 -->
    <sql id="columns">
        select id,role_name,role_code,description,create_time,update_time,is_deleted from acl_role
    </sql>

    <!--查询所有-->
    <select id="findAll" resultType="role">
        <include refid="columns"></include>
        where is_deleted = 0
    </select>
</mapper>
```



### 1.4.2完善service层

RoleService接口

```java
package com.atguigu.service;

import com.atguigu.entity.Role;
import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService {
    List<Role> findAll();
}
```

RoleServiceImpl类

```java
package com.atguigu.service.impl;

import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
```



### 1.4.3完善controller层

IndexController首页访问控制类

```java
package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.IndexController
 */
@Controller
public class IndexController {

    private final static String PAGE_INDEX = "frame/index";

    @RequestMapping("/")
    public String index() {
        return PAGE_INDEX;
    }

}
```

RoleController用户角色管理控制类

```java
package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final static String PAGE_INDEX = "role/index";

    @Autowired
    private RoleService roleService;

    @RequestMapping
    public String findAll(Map map){
        //1. 调用业务层处理业务
        List<Role> list = roleService.findAll();
        //2. 将数据共享到请求域
        map.put("list",list);
        //3. 设置逻辑视图
        return PAGE_INDEX;
    }

}
```



### 1.4.4完善web

frame/index.html页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <!--base标签我们一直在用，咱们项目阶段先不用base标签-->
    <!--    <base th:href="@{/}">-->
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>首页</h1>
<a th:href="@{/role}">查询所有的角色</a>
</body>
</html>
```

role/index.html页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>role的所有数据</h1>
<table border="1" width="800px">
    <tr>
        <th>序号</th>
        <th>名称</th>
        <th>编码</th>
        <th>描述</th>
        <th>创建时间</th>
    </tr>
    <tr th:each="role:${list}">
        <td th:text="${role.id}"></td>
        <td th:text="${role.roleName}"></td>
        <td th:text="${role.roleCode}"></td>
        <td th:text="${role.description}"></td>
        <td th:text="${#dates.format(role.createTime,'yyyy-MM-dd HH:mm:ss')}"></td>
    </tr>
</table>
</body>
</html>
```



### 1.4.5开启jetty服务测试

项目打包：

Maven → shf-parent (root)  → Lifecycle → install

项目运行：

Maven → web_admin → Plugins → jetty → jetty:run

访问：http://localhost:8000/（8000是web_admin.pom文件中设置的jetty服务的端口）



# 2 用户角色管理

## 2.1集成后台前端框架

后台前端框架模板：Hplus

下载地址：https://gitee.com/hplus_admin/hplus

### 2.1.1添加框架静态资源

下载框架，在web_admin模块webapp下创建static目录存放从Hplus模版中拷贝的静态资源：

webapp/static目录下的资源（必须叫static，开发的规范）：

- js目录
- css目录
- img目录
- fonts目录



### 2.1.2完善框架主页面

修改pages/frame/index.html页面，修改内容和路径

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="renderer" content="webkit" />

    <title>用户管理</title>

    <meta name="keywords" content="用户管理后台" />
    <meta name="description" content="用户管理后台" />

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}" />
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet" />
    <link th:href="@{/static/css/font-awesome.min.css?v=4.4.0}" rel="stylesheet" />
    <link th:href="@{/static/css/animate.css}" rel="stylesheet" />
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet" />
    <link th:href="@{/static/css/jquery.contextMenu.min.css}" rel="stylesheet"/>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden;">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i></div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span><img alt="image" class="img-circle" th:src="@{/static/img/profile_small.jpg}" /></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
									<span class="clear">
										<span class="block m-t-xs"><strong class="font-bold">Beaut-zihan</strong></span>
										<span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
									</span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="J_menuItem" href="form_avatar.html">修改头像</a></li>
                            <li><a class="J_menuItem" href="profile.html">个人资料</a></li>
                            <li><a class="J_menuItem" href="contacts.html">联系我们</a></li>
                            <li><a class="J_menuItem" href="mailbox.html">信箱</a></li>
                            <li class="divider"></li>
                            <li><a href="login.html">安全退出</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">H+</div>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-home"></i>
                        <span class="nav-label">权限管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" th:href="@{/role}" href="index_v1.html" data-index="0">用户管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" th:href="@{/role}">角色管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" th:href="@{/role}">菜单管理</a>
                        </li>
                    </ul>
                </li>


            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0;">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary" href="#"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search" />
                        </div>
                    </form>
                </div>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i></button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i></button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown" data-toggle="dropdown">页签操作<span class="caret"></span></button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="tabCloseCurrent"><a>关闭当前</a></li>
                    <li class="J_tabCloseOther"><a>关闭其他</a></li>
                    <li class="J_tabCloseAll"><a>全部关闭</a></li>
                </ul>
            </div>
            <a href="#" class="roll-nav roll-right tabReload"><i class="fa fa-refresh"></i> 刷新</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <!--在当前网页内恰套其他html页面，src就是路径-->
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" th:src="@{/main}"  frameborder="0" data-id="index_v1.html" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">&copy; 2014-2015 <a href="http://www.zi-han.net/" target="_blank">zihan's blog</a></div>
        </div>
    </div>

</div>


<!-- 全局js -->
<script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
<script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>
<script th:src="@{/static/js/plugins/metisMenu/jquery.metisMenu.js}"></script>
<script th:src="@{/static/js/plugins/slimscroll/jquery.slimscroll.min.js}"></script>
<script th:src="@{/static/js/plugins/contextMenu/jquery.contextMenu.min.js}"></script>
<script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>

<!-- 自定义js -->
<script th:src="@{/static/js/hplus.js?v=4.1.0}"></script>
<script type="text/javascript" th:src="@{/static/js/contabs.js}"></script>
</body>
</html>
```



### 2.1.3添加欢迎登录页面

添加pages/frame/main.html页面

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="renderer" content="webkit" />
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
</head>
<body style="position: relative;">
<div style="text-align:center;margin-top: 100px;font-size: 20px;">
    <strong>欢迎登录尚好房平台管理系统</strong>
</div>
</body>
</html>
```



### 2.1.4添加欢迎页面控制器

方式一：修改IndexController（推荐）

```java
package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.IndexController
 */
@Controller
public class IndexController {

    private final static String PAGE_INDEX = "frame/index";
    private final static String PAGE_MAIN = "frame/main";

    @RequestMapping("/")
    public String index() {
        return PAGE_INDEX;
    }

    @RequestMapping("/main")
    public String main() {
        return PAGE_MAIN;
    }

}

```

方式二：在spring-mvc.xml中添加

```xml
<!--配置iframe欢迎页面访问-->
<mvc:view-controller path="main" view-name="frame/main"/>
```



## 2.2角色管理

### 2.2.1弹出层封装

在webapp/static/js目录下新建文件myLayer.js，对弹出层进行二开发，封装五种样式的弹出层：

```js
var opt = {
    alert : function(msg){
        layer.alert(msg);
    },
    //加载中
    load : function () {
        layer.load(1, {
            shade: [0.5,'#fff'] //0.1透明度的白色背景
        });
    },
    //确认框(确认和取消按钮，一般用于删除等危险操作)
    confirm : function(url, msg) {
        var msg = msg ? msg : "确定该操作吗？";
        layer.confirm(msg,function(index){
            opt.load();
            window.location = url;
        });
    },
    //提示框(有正确或错误的图标)
    dialog : function(message, messageType) {
        if(message != '' && message != null) {
            if(messageType == '1') {
                layer.msg(message, {icon: 1});
            } else {
                layer.alert(message, {icon: 2});
            }
        }
    },
    //打开一个窗口(弹出层，url就是弹出层内显示的frame窗体的路径)
    openWin : function(url,title, width,height) {
        var title = title ? title : false;
        layer.open({
            type: 2,
            title: title,
            zIndex:10000,
            anim: -1,
            maxmin: true,
            aini:2,
            shadeClose: false, //点击遮罩关闭层
            area: [width+"px", height+"px"],
            content: url
        });
    },
    //关闭窗口
    closeWin : function(refresh,call) {
        var index = parent.layer.getFrameIndex(window.name);
        if(refresh) {
            parent.location.reload();
        }
        if(call) {
            parent.init();
        }
        parent.layer.close(index); //执行关闭
    }
}
```



### 2.2.2添加角色CRUD页面

角色CRUD的web页面，都在`webapp/WEB-INF/pages/role`目录下

#### 2.2.2.1角色展示页面

角色展示页面：`pages/role/index.html`文件

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>
    <script th:src="@{/static/js/plugins/jeditable/jquery.jeditable.js}"></script>
    <!-- Data Tables -->
    <script th:src="@{/static/js/plugins/dataTables/jquery.dataTables.js}"></script>
    <script th:src="@{/static/js/plugins/dataTables/dataTables.bootstrap.js}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <!--弹出层按钮-->
                    <button type="button" class="btn btn-sm btn-primary create">新增</button>
                    <table class="table table-striped table-bordered table-hover dataTables-example">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>角色名称</th>
                            <th>角色编码</th>
                            <th>描述</th>
                            <th>创建时间</th>
                            <th>操作 </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="gradeX" th:each="item,it : ${list}">
                            <td class="text-center" th:text="${it.count}">11</td>
                            <td th:text="${item.roleName}">22</td>
                            <td th:text="${item.roleCode}">33</td>
                            <td th:text="${item.description}">33</td>
                            <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}" >33</td>
                            <td class="text-center">
                                <a class="edit" th:attr="data-id=${item.id}">修改</a>
                                <a class="delete" th:attr="data-id=${item.id}">删除</a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!--在使用thymeleaf时，前端页面如要在javascript中获取后端传入的数据，需要使用<script th:inline="javascript">-->
<script th:inline="javascript">
<script th:inline="javascript">
    //弹出层事件
    $(function () {
        //新增
        $(".create").on("click", function () {
            opt.openWin("/role/create", "新增", 580, 430);
        });
        //修改
        $(".edit").on("click", function () {
            var id = $(this).attr("data-id");
            opt.openWin('/role/edit/' + id, '修改', 580, 430);
        });
        //删除
        $(".delete").on("click", function () {
            var id = $(this).attr("data-id");
            opt.confirm('/role/delete/' + id);
        });
    });
</script>
</script>
</body>
</html>
```



#### 2.2.2.2角色新增页面

角色新增页面：`pages/role/create.html`文件

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>新增</title>

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/role/save}" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色名称：</label>
                    <div class="col-sm-10">
                        <input type="text" name="roleName" id="roleName" value="" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色编码：</label>
                    <div class="col-sm-10">
                        <input type="text" name="roleCode" id="roleCode" value="" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">描述：</label>
                    <div class="col-sm-10">
                        <textarea name="description" id="description" class="form-control" style="width:100%;height: 50px;" ></textarea>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" value="取消" onclick="opt.closeWin()">取消</button></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



#### 2.2.2.3角色修改页面

角色修改页面：`pages/role/edit.html`文件

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>修改</title>

  <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
  <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
  <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

  <!-- Data Tables -->
  <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

  <link th:href="@{/static/css/animate.css}" rel="stylesheet">
  <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

  <!-- 全局js -->
  <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
  <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>

  <!-- 弹出层js -->
  <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
  <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
  <div class="ibox float-e-margins">
    <div class="ibox-content" style="width: 98%;">
      <form id="ec" th:action="@{/role/update}" method="post" class="form-horizontal" >
        <input type="hidden" name="id" th:value="${role.id}">
        <div class="form-group">
          <label class="col-sm-2 control-label">角色：</label>

          <div class="col-sm-10">
            <input type="text" name="roleName" id="roleName" th:value="${role.roleName}" class="form-control"/>
          </div>
        </div>
        <div class="hr-line-dashed"></div>
        <div class="form-group">
          <label class="col-sm-2 control-label">角色编码：</label>
          <div class="col-sm-10">
            <input type="text" name="roleCode" id="roleCode" th:value="${role.roleCode}" class="form-control"/>
          </div>
        </div>
        <div class="hr-line-dashed"></div>
        <div class="form-group">
          <label class="col-sm-2 control-label">描述：</label>
          <div class="col-sm-10">
            <textarea name="description" id="description" class="form-control" style="width:100%;height: 50px;" th:text="${role.description}" ></textarea>
          </div>
        </div>
        <div class="hr-line-dashed"></div>
        <div class="form-group posf">
          <div class="col-sm-4 col-sm-offset-2 text-right">
            <button class="btn btn-primary" type="submit">确定</button>
            <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button></div>
        </div>
      </form>
    </div>
  </div>
</div>
</div>
</body>
</html>
```



#### 2.2.2.4创建修改成功页面

在WEB-INF/pages/common目录下创建success.html文件

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>成功提示页</title>

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content">
            <div class="form-group">
                <div class="col-sm-10">操作成功</div>
            </div>
            <div class="hr-line-dashed"></div>
            <div class="form-group posf">
                <div class="col-sm-4 col-sm-offset-2">
                    <!--传递true，让父级刷新-->
                    <button class="btn btn-primary" type="button" onclick="opt.closeWin(true);">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
```





### 2.2.3角色CRUD持久层

dao层接口文件：`dao/RoleDao`

```java
package com.atguigu.dao;

import com.atguigu.entity.Role;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface RoleDao {

    /**
     * @Description: 查询所有
     */
    List<Role> findAll();

    /**
     * @Description: 插入一条数据
     */
    Integer insert(Role role);

    /**
     * @Description: 通过id获取
     */
    Role getById(Long id);

    /**
     * @Description: 修改数据
     */
    Integer update(Role role);

    /**
     * @Description: 删除数据
     */
    void delete(Long id);

}
```

dao层映射文件：`resources/mapper/RoleDao.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.RoleDao">

    <!-- 用于select查询公用抽取的列 -->
    <sql id="columns">
        select id,role_name,role_code,description,create_time,update_time,is_deleted from acl_role
    </sql>

    <!--查询所有-->
    <select id="findAll" resultType="role">
        <include refid="columns"></include>
        where is_deleted = 0
    </select>

    <!--查询单个-->
    <select id="getById" resultType="role">
        <include refid="columns"/>
        where
        id = #{id}
    </select>

    <!--新增-->
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        insert into acl_role (
        id ,
        role_name ,
        role_code ,
        description
        ) values (
        #{id} ,
        #{roleName} ,
        #{roleCode} ,
        #{description}
        )
    </insert>

    <!--修改方式一：可赋值为null
    <update id="update">
        update acl_role set
        role_name=#{roleName},role_code=#{roleCode},description=#{description}
        where id=#{id}
    </update>
    -->

    <!--修改方式二：使用set标签，赋为null或空串时不修改原来的数据-->
    <update id="update">
        update acl_role
        <set>
            <if test="roleName!=null and roleName!=''">
                role_name=#{roleName},
            </if>
            <if test="roleCode!=null and roleCode!=''">
                role_code=#{roleCode},
            </if>
            <if test="description!=null and description!=''">
                description=#{description},
            </if>
        </set>
        where id=#{id}
    </update>


    <!--逻辑删除-->
    <update id="delete">
        update acl_role set
        is_deleted = 1
        where
        id = #{id}
    </update>

</mapper>
```



### 2.2.4角色CRUD业务层

service层接口文件：`service/RoleService`

```java
package com.atguigu.service;

import com.atguigu.entity.Role;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService {

    /**
     * @Description: 角色列表
     */
    List<Role> findAll();

    /**
     * @Description: 角色新增
     */
    Integer insert(Role role);

    /**
     * @Description: 修改回显
     */
    Role getById(Long id);

    /**
     * @Description: 修改操作
     */
    Integer update(Role role);

    /**
     * @Description: 删除操作
     */
    void delete(Long id);
}
```

service层接口实现类文件：`service/impl/RoleServiceImpl`

```java
package com.atguigu.service.impl;

import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * @Description: 展示所有角色
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * @Description: 新增角色
     */
    @Override
    public Integer insert(Role role) {
        return roleDao.insert(role);
    }

    /**
     * @Description: 修改角色回显数据
     */
    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    /**
     * @Description: 角色更新操作
     */
    @Override
    public Integer update(Role role) {
        return roleDao.update(role);
    }

    /**
     * @Description: 角色删除操作
     */
    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }
}
```



### 2.2.5角色CRUD控制层

controller层类文件：`controller/RoleController`

```java
package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/role";

    @Autowired
    private RoleService roleService;

    /**
     * @Description: 处理/role请求，查询所有角色
     */
    @RequestMapping
    public String findAll(Map map) {
        List<Role> list = roleService.findAll();
        //向请求域添加搜索结果数据
        map.put("list", list);
        //搜索内容的回显
        map.put("filters", filters);
        return PAGE_INDEX;
    }

    /**
     * @Description: 处理/create请求，跳转到添加角色页面
     */
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * @Description: 处理/save请求，执行添加角色操作
     */
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/edit/id请求，跳转到修改角色页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Role role = roleService.getById(id);
        map.put("role",role);
        return PAGE_EDIT;
    }

    /**
     * @Description: 处理/update请求，执行角色修改操作
     */
    @RequestMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/delete/id请求，执行角色删除操作
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

}
```





### 2.2.6角色模糊查询实现

第一步：修改`pages/role/index.html`文件，添加查询的表单和查询条件输入框及查询按钮。

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>
    <script th:src="@{/static/js/plugins/jeditable/jquery.jeditable.js}"></script>
    <!-- Data Tables -->
    <script th:src="@{/static/js/plugins/dataTables/jquery.dataTables.js}"></script>
    <script th:src="@{/static/js/plugins/dataTables/dataTables.bootstrap.js}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<!--查询角色的表单-->
<form id="ec" th:action="@{/role}" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">

                        <!--高级搜索内容开始-->
                        <table class="table form-table margin-bottom10">
                            <tr>
                                <td>
                                    <!--搜索内容回显-->
                                    <input type="text" name="roleName"
                                           th:value="${#maps.containsKey(filters, 'roleName')} ? ${filters.roleName} : ''"
                                           placeholder="角色名称" class="input-sm form-control"/>
                                </td>
                            </tr>
                        </table>
                        <div>
                            <!--点击搜索按钮提交当前表单-->
                            <button type="button" class="btn btn-sm btn-primary" onclick="document.forms.ec.submit();">
                                搜索
                            </button>
                            <button type="button" class="btn btn-sm btn-primary create">新增</button>
                            <button type="button" id="loading-example-btn"
                                    onclick="javascript:window.location.reload();" class="btn btn-white btn-sm">刷新
                            </button>
                        </div>
                        <!--高级搜索内容结束-->

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>角色名称</th>
                                <th>角色编码</th>
                                <th>描述</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="gradeX" th:each="item,it : ${list}">
                                <td class="text-center" th:text="${it.count}">11</td>
                                <td th:text="${item.roleName}">22</td>
                                <td th:text="${item.roleCode}">33</td>
                                <td th:text="${item.description}">33</td>
                                <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}">33</td>
                                <td class="text-center">
                                    <a class="edit" th:attr="data-id=${item.id}">修改</a>
                                    <a class="delete" th:attr="data-id=${item.id}">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!--在使用thymeleaf时，前端页面如要在javascript中获取后端传入的数据，需要使用<script th:inline="javascript">-->
<script th:inline="javascript">
<script th:inline="javascript">
    //弹出层事件
    $(function () {
        //新增
        $(".create").on("click", function () {
            opt.openWin("/role/create", "新增", 580, 430);
        });
        //修改
        $(".edit").on("click", function () {
            var id = $(this).attr("data-id");
            opt.openWin('/role/edit/' + id, '修改', 580, 430);
        });
        //删除
        $(".delete").on("click", function () {
            var id = $(this).attr("data-id");
            opt.confirm('/role/delete/' + id);
        });
    });
</script>
</script>
</body>
</html>
```

第二步：修改持久层文件，findRole方法替换掉findAll方法

dao层接口文件

```java
    /**
     * @Description: 查询所有
     */
//    List<Role> findAll();

    /**
     * @Description: index页搜索条件，可替代查询所有
     */
    List<Role> findRole(Map<String,Object> filters);
```

dao层映射文件

```xml
<!--查询所有-->
<!--    <select id="findAll" resultType="role">-->
<!--        <include refid="columns"></include>-->
<!--        from acl_role-->
<!--        where is_deleted = 0-->
<!--    </select>-->

<!--按条件模糊搜索-->
<select id="findRole" resultType="role">
  <include refid="columns"></include>
  <where>
    <!--判断有无搜索条件-->
    <if test="roleName != null and roleName != ''">
      role_name like concat('%',#{roleName},'%')
      <!--精准搜索：and role_name = #{roleName}-->
    </if>
    and is_deleted = 0
  </where>
</select>
```

第三步：修改业务层，findRole方法替换掉findAll方法

service层接口

```java
    /**
     * @Description: 角色列表
     */
//		List<Role> findAll();

    /**
     * @Description: index页搜索条件，可替代查询所有
     */
    List<Role> findRole(Map<String,Object> filters);
```

service层接口实现类

```java
    /**
     * @Description: 展示所有角色
     */
//    @Override
//    public List<Role> findAll() {
//        return roleDao.findAll();
//    }

    /**
     * @Description: 条件搜索，可代替查询所有角色
     */
    @Override
    public List<Role> findRole(Map<String, Object> filters) {
        return  roleDao.findRole(filters);
    }
```

第四步：修改控制层

controller层：

```java
	  /**
     * @Description: 处理/role请求，查询所有角色
     */
//    @RequestMapping
//    public String findAll(Map map) {
//        List<Role> list = roleService.findAll();
//        map.put("list", list);
//        return PAGE_INDEX;
//    }

    /**
     * @Description: 处理/role请求，根据条件搜索，代替findAll
     */
    @RequestMapping
    public String findRole(
            Map map,
            HttpServletRequest request
    ) {
        Map<String, Object> filters =  getFilters(request);
        List<Role> list = roleService.findRole(filters);
        map.put("list", list);
        return PAGE_INDEX;
    }

		/**
     * @Description: 将请求中的搜索条件分装成map
     */
    private Map<String, Object> getFilters(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> filters = new TreeMap();
        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    filters.put(paramName, values);
                } else {
                    //若只有一个value，只向Map集合中放value本身
                    filters.put(paramName, values[0]);
                }
            }
        }
        return filters;
    }
```



## 2.3分页处理实现

### 2.3.1分页之dao层

首先在mybatis-config.xml添加分页插件PageHelper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <plugins>
        <!-- com.github.pagehelper 为 PageHelper 类所在包名 -->
        <plugin interceptor="com.github.pagehelper.PageHelper">
            <!-- 设置数据库类型 Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL 六种数据库-->
            <property name="dialect" value="mysql"/>
        </plugin>
    </plugins>
</configuration>
```



#### 2.3.1.1dao层接口文件

```java
package com.atguigu.dao;

import com.atguigu.entity.Role;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface RoleDao {

    /**
     * @Description: 搜索结果分页显示
     */
    List<Role> findPage(Map<String, Object> filters);

    /**
     * @Description: 插入一条数据
     */
    Integer insert(Role role);

    /**
     * @Description: 通过id获取
     */
    Role getById(Long id);

    /**
     * @Description: 修改数据
     */
    Integer update(Role role);

    /**
     * @Description: 删除数据
     */
    void delete(Long id);

}
```



#### 2.3.1.2dao层映射文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.RoleDao">

    <!-- 用于select查询公用抽取的列 -->
    <sql id="columns">
        select id,role_name,role_code,description,create_time,update_time,is_deleted from acl_role
    </sql>

    <!--搜索结果分页显示-->
    <select id="findPage" resultType="role">
        <include refid="columns"></include>
        <where>
            <!--判断有无搜索条件-->
            <if test="roleName != null and roleName != ''">
                role_name like concat('%',#{roleName},'%')
                <!--精准搜索：and role_name = #{roleName}-->
            </if>
            and is_deleted = 0
            order by id desc
        </where>
    </select>

    <!--查询单个-->
    <select id="getById" resultType="role">
        <include refid="columns"/>
        where
        id = #{id}
    </select>

    <!--新增-->
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        insert into acl_role (
        id ,
        role_name ,
        role_code ,
        description
        ) values (
        #{id} ,
        #{roleName} ,
        #{roleCode} ,
        #{description}
        )
    </insert>

    <!--修改方式一：可赋值为null
    <update id="update">
        update acl_role set
        role_name=#{roleName},role_code=#{roleCode},description=#{description}
        where id=#{id}
    </update>
    -->

    <!--修改方式二：使用set标签，赋为null或空串时不修改原来的数据-->
    <update id="update">
        update acl_role
        <set>
            <if test="roleName!=null and roleName!=''">
                role_name=#{roleName},
            </if>
            <if test="roleCode!=null and roleCode!=''">
                role_code=#{roleCode},
            </if>
            <if test="description!=null and description!=''">
                description=#{description},
            </if>
        </set>
        where id=#{id}
    </update>


    <!--删除-->
    <update id="delete">
        update acl_role set
        is_deleted = 1
        where
        id = #{id}
    </update>

</mapper>
```



### 2.3.2分页之service层

#### 2.3.2.1service层接口

```java
package com.atguigu.service;

import com.atguigu.entity.Role;
import com.github.pagehelper.PageInfo;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService {

    /**
     * @Description: 搜索结果分页显示
     */
    PageInfo<Role> findPage(Map<String, Object> filters);


    /**
     * @Description: 角色新增
     */
    Integer insert(Role role);

    /**
     * @Description: 修改回显
     */
    Role getById(Long id);

    /**
     * @Description: 修改操作
     */
    Integer update(Role role);

    /**
     * @Description: 删除操作
     */
    void delete(Long id);
}
```



#### 2.3.2.2service层接口实现类

```java
package com.atguigu.service.impl;

import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * @Description: 搜索结果分页显示
     */
    @Override
    public PageInfo<Role> findPage(Map<String, Object> filters) {
        //当前页数，common-util引入工具类：CastUtil
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 3);

        //分页插件
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleDao.findPage(filters);
        //构造方法中传入list集合和每页显示条数
        //通过pageInfo.getList()可获取list集合的数据，将pageInfo对象放到请求域就可以共享到前端
        PageInfo pageInfo = new PageInfo<Role>(list, 3);
        return pageInfo;
    }

    /**
     * @Description: 新增角色
     */
    @Override
    public Integer insert(Role role) {
        return roleDao.insert(role);
    }

    /**
     * @Description: 修改角色回显数据
     */
    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    /**
     * @Description: 角色更新操作
     */
    @Override
    public Integer update(Role role) {
        return roleDao.update(role);
    }

    /**
     * @Description: 角色删除操作
     */
    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }
}
```



### 2.3.3分页之controller层

```java
package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/role";

    @Autowired
    private RoleService roleService;

    /**
     * @Description: 处理/role请求，搜索处理、分页处理
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String,Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Role> page = roleService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        return PAGE_INDEX;
    }

    /**
     * @Description: 处理/create请求，跳转到添加角色页面
     */
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * @Description: 处理/save请求，执行添加角色操作
     */
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/edit/id请求，跳转到修改角色页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Role role = roleService.getById(id);
        map.put("role",role);
        return PAGE_EDIT;
    }

    /**
     * @Description: 处理/update请求，执行角色修改操作
     */
    @RequestMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/delete/id请求，执行角色删除操作
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

    /**
     * 封装页面提交的分页参数及搜索条件
     */
    private Map<String, Object> getFilters(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> filters = new TreeMap();
        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    filters.put(paramName, values);
                } else {
                    //若只有一个value，只向Map集合中放value本身
                    filters.put(paramName, values[0]);
                }
            }
        }
      
      	//设置默认页数和分页显示数据的数量
        if(!filters.containsKey("pageNum")) {
            filters.put("pageNum", 1);
        }
        if(!filters.containsKey("pageSize")) {
            filters.put("pageSize", 3);
        }

        return filters;
    }


}
```



### 2.3.4分页之前端HTML页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>
    <script th:src="@{/static/js/plugins/jeditable/jquery.jeditable.js}"></script>
    <!-- Data Tables -->
    <script th:src="@{/static/js/plugins/dataTables/jquery.dataTables.js}"></script>
    <script th:src="@{/static/js/plugins/dataTables/dataTables.bootstrap.js}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</head>
<body class="gray-bg">
<!--查询角色的表单-->
<form id="ec" th:action="@{/role}" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">

                        <!--搜索内容开始-->
                        <table class="table form-table margin-bottom10">
                            <tr>
                                <td>
                                    <!--搜索内容回显-->
                                    <input type="text" name="roleName"
                                           th:value="${#maps.containsKey(filters, 'roleName')} ? ${filters.roleName} : ''"
                                           placeholder="角色名称" class="input-sm form-control"/>
                                </td>
                            </tr>
                        </table>
                        <div>
                            <!--点击搜索按钮提交表单，并为表单项的页码参数pageNum赋值1-->
                            <button type="button" class="btn btn-sm btn-primary"
                                    onclick="javascript:document.forms.ec.pageNum.value=1;document.forms.ec.submit();">
                                搜索
                            </button>
                            <button type="button" class="btn btn-sm btn-primary create">新增</button>
                            <button type="button" id="loading-example-btn"
                                    onclick="javascript:window.location.reload();" class="btn btn-white btn-sm">刷新
                            </button>
                        </div>
                        <!--搜索内容结束-->

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>角色名称</th>
                                <th>角色编码</th>
                                <th>描述</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="gradeX" th:each="item,it : ${page.list}">
                                <td class="text-center" th:text="${it.count}">11</td>
                                <td th:text="${item.roleName}">22</td>
                                <td th:text="${item.roleCode}">33</td>
                                <td th:text="${item.description}">33</td>
                                <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}">33</td>
                                <td class="text-center">
                                    <a class="edit" th:attr="data-id=${item.id}">修改</a>
                                    <a class="delete" th:attr="data-id=${item.id}">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <!--分页插件页码开始-->
                        <!--将当前页码添加到表单项中，随着点击页码按钮而作为请求参数提交-->
                        <input type="hidden" name="pageNum" id="pageNum" th:value="${page.pageNum}"/>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="dataTables_info" id="DataTables_Table_0_info" role="alert"
                                     aria-live="polite" aria-relevant="all">显示
                                    <span th:text="${page.startRow}"></span> 到 <span th:text="${page.endRow}"></span> 项，
                                    共 <span th:text="${page.total}"></span> 项
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
                                    <ul class="pagination">

                                        <!--页码开始-->
                                        <!--首页，两种样式，根据是否为首页进行切换⚠️-->
                                        <li th:if="${page.isFirstPage}" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页是首页，则无法点击首页跳转-->
                                            <a href="javascript:;">首页</a>
                                        </li>
                                        <li th:if="${!page.isFirstPage}" class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页不是首页，修改当前页pageNum的value值为1，点击即可提交表单跳转到首页-->
                                            <a th:href="'javascript:document.forms.ec.pageNum.value=1;document.forms.ec.submit();'">首页</a>
                                        </li>

                                        <!--上一页，两种样式，根据是否为首页进行切换⚠️-->
                                        <li th:if="${page.isFirstPage}" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页是首页，则无法点击上一页跳转-->
                                            <a href="javascript:;">上一页</a>
                                        </li>
                                        <li th:if="${!page.isFirstPage}" class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页不是首页，修改当前页pageNum的value值为上一页prePage属性的值，点击即可提交表单跳转到首页-->
                                            <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.prePage}+';document.forms.ec.submit();'">上一页</a>
                                        </li>

                                        <!--页码，循环所有导航页号navigatepageNums，并判断是否为当前页，设置active样式-->
                                        <li th:class="${i==page.pageNum?'paginate_button active':'paginate_button'}" aria-controls="DataTables_Table_0" tabindex="0" th:each="i:${page.navigatepageNums}">
                                            <!--为每个导航页码都赋值上跳转到自己页面的超链接-->
                                            <a th:href="'javascript:document.forms.ec.pageNum.value='+${i}+';document.forms.ec.submit();'" th:text="${i}"></a>
                                        </li>

                                        <!--下一页，两种样式，根据是否为尾页进行切换⚠️-->
                                        <li th:if="${page.isLastPage}" class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页是尾页，则无法点击下一页跳转-->
                                            <a href="javascript:;">下一页</a>
                                        </li>
                                        <li th:if="${!page.isLastPage}" class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页不是尾页，修改当前页pageNum的value值为下一页nextPage属性的值，点击即可提交表单跳转到首页-->
                                            <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.nextPage}+';document.forms.ec.submit();'">下一页</a>
                                        </li>

                                        <!--尾页，两种样式，根据是否为尾页进行切换⚠️-->
                                        <li th:if="${page.isLastPage}" class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页是尾页，则无法点击尾页跳转-->
                                            <a href="javascript:;">尾页</a>
                                        </li>
                                        <li th:if="${!page.isLastPage}" class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0">
                                            <!--若当前页不是尾页，修改当前页pageNum的value值为总页数pages属性的值，点击即可提交表单跳转到尾页-->
                                            <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.pages}+';document.forms.ec.submit();'">尾页</a>
                                        </li>
                                        <!--页码结束-->

                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!--分页插件页码结束-->

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!--在使用thymeleaf时，前端页面如要在javascript中获取后端传入的数据，需要使用<script th:inline="javascript">-->
<script th:inline="javascript">
    //弹出层事件
    $(function () {
        //新增
        $(".create").on("click", function () {
            opt.openWin("/role/create", "新增", 580, 430);
        });
        //修改
        $(".edit").on("click", function () {
            var id = $(this).attr("data-id");
            opt.openWin('/role/edit/' + id, '修改', 580, 430);
        });
        //删除
        $(".delete").on("click", function () {
            var id = $(this).attr("data-id");
            opt.confirm('/role/delete/' + id);
        });
    });

</script>
</body>
</html>
```





## 2.4管理代码封装

### 2.4.1封装dao层

在common_util模块下进行封装

#### 2.4.1.1封装BaseDao

```java
package com.atguigu.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.BaseDao
 */
public interface BaseDao<T> {

    /**
     * 获取index页面的列表实体数据，以及查询结果的分页展示
     */
    List<T> findPage(Map<String,Object> filters);


    /**
     * 插入一个实体
     */
    void insert(T t);


    /**
     * 根据ID删除实体，参数ID可以是字符串型也可以是整型
     */
    void delete(Serializable id);


    /**
     * 更新一个实体
     */
    void update(T t);


    /**
     * 根据ID获取实体，参数ID可以是字符串型也可以是整型
     */
    T getById(Serializable id);

}
```



#### 2.4.1.2改造RoleDao

```java
package com.atguigu.dao;

import com.atguigu.entity.Role;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface RoleDao extends BaseDao<Role> {

}
```



### 2.4.2封装service层

在common_util模块下进行封装

#### 2.4.2.1封装BaseService

```java
package com.atguigu.service;

import com.github.pagehelper.PageInfo;
import java.io.Serializable;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.BaseService
 */
public interface BaseService<T> {

    /**
     * 获取实体数据，包装到PageInfo分页对象中返回
     */
    PageInfo<T> findPage(Map<String,Object> filters);


    /**
     * 插入一个实体
     */
    void insert(T t);


    /**
     * 根据ID删除实体，参数ID可以是字符串型也可以是整型
     */
    void delete(Serializable id);


    /**
     * 更新一个实体
     */
    void update(T t);


    /**
     * 根据ID获取实体，参数ID可以是字符串型也可以是整型
     */
    T getById(Serializable id);

}
```



#### 2.4.2.2改造RoleService

```java
package com.atguigu.service;

import com.atguigu.entity.Role;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService extends BaseService<Role> {

}
```



#### 2.4.2.3封装BaseServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.service.BaseService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TODD
 * @AllClassName: com.atguigu.service.impl.BaseServiceImpl
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {


    //模版方法设计模式，通过该抽象方法获取实际的dao层对象
    //service层实现类重写该方法返回dao层对象，就可以用该方法返回值调用其他方法⚠️
    public abstract BaseDao<T> getEntityDao();

    /**
     * 搜索结果分页显示
     */
    @Override
    public PageInfo<T> findPage(Map<String, Object> filters) {
        //当前页数，common-util引入工具类：CastUtil
        //先获取请求参数处理方法getFilters中设置的初始化值，未设置时使用默认值⚠️
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 3);

        //分页插件，必须设置在查询之前⚠️
        PageHelper.startPage(pageNum, pageSize);
        //使用方法返回的dao层对象调用
        List<T> list = getEntityDao().findPage(filters);
        //构造方法中传入list集合和每页显示条数
        //通过pageInfo.getList()可获取list集合的数据，将pageInfo对象放到请求域就可以共享到前端
        return new PageInfo<T>(list, 3);
    }


    /**
     * 新增一个实体
     */
    @Override
    public void insert(T t) {
        getEntityDao().insert(t);
    }


    /**
     * 根据ID获取实体，参数ID可以是字符串型也可以是整型
     */
    @Override
    public T getById(Serializable id) {
        return getEntityDao().getById(id);
    }


    /**
     * 更新一个实体
     */
    @Override
    public void update(T t) {
        getEntityDao().update(t);
    }


    /**
     * 根据ID删除实体，参数ID可以是字符串型也可以是整型
     */
    @Override
    public void delete(Serializable id) {
        getEntityDao().delete(id);
    }

}
```



#### 2.4.2.4改造RoleServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public BaseDao<Role> getEntityDao() {
        return roleDao;
    }

}
```



### 2.4.3封装Controller层

在common_util模块下进行封装

#### 2.4.3.1封装BaseController

```java
package com.atguigu.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.BaseController
 */
public class BaseController {

    /**
     * 封装页面提交的分页参数及搜索条件
     * @param request
     * @return
     */
    protected Map<String, Object> getFilters(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> filters = new TreeMap();
        while(paramNames != null && paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] values = request.getParameterValues(paramName);
            if (values != null && values.length != 0) {
                if (values.length > 1) {
                    filters.put(paramName, values);
                } else {
                    //若只有一个value，只向Map集合中放value本身
                    filters.put(paramName, values[0]);
                }
            }
        }
        //判断请求参数内没有pageNum和pageSize的话，初始值设置为1和3
        if(!filters.containsKey("pageNum")) {
            filters.put("pageNum", 1);
        }
        if(!filters.containsKey("pageSize")) {
            filters.put("pageSize", 3);
        }

        return filters;
    }
}
```



#### 2.4.3.2改造RoleController

```java
package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/role";

    @Autowired
    private RoleService roleService;

    /**
     * @Description: 处理/role请求，搜索处理、分页处理
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String,Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Role> page = roleService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        return PAGE_INDEX;
    }

    /**
     * @Description: 处理/create请求，跳转到添加角色页面
     */
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * @Description: 处理/save请求，执行添加角色操作
     */
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/edit/id请求，跳转到修改角色页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Role role = roleService.getById(id);
        map.put("role",role);
        return PAGE_EDIT;
    }

    /**
     * @Description: 处理/update请求，执行角色修改操作
     */
    @RequestMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    /**
     * @Description: 处理/delete/id请求，执行角色删除操作
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

}
```



### 2.4.4封装Thymeleaf模板

在wb_admin模块下进行封装

#### 2.4.4.1封装头部资源引用

在common下新建：head.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

<div th:fragment="head">
    <title>权限管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge,chrome=1"/>
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="shortcut icon" th:href="@{favicon.ico}"/>
    <link rel="shortcut icon" th:href="@{/static/favicon.ico}">
    <link th:href="@{/static/css/bootstrap.min.css?v=3.3.7}" rel="stylesheet">
    <link th:href="@{/static/css/font-awesome.css?v=4.4.0}" rel="stylesheet">

    <!-- Data Tables -->
    <link th:href="@{/static/css/plugins/dataTables/dataTables.bootstrap.css}" rel="stylesheet">

    <link th:href="@{/static/css/animate.css}" rel="stylesheet">
    <link th:href="@{/static/css/style.css?v=4.1.0}" rel="stylesheet">

    <!-- 全局js -->
    <script th:src="@{/static/js/jquery.min.js?v=2.1.4}"></script>
    <script th:src="@{/static/js/bootstrap.min.js?v=3.3.7}"></script>
    <script th:src="@{/static/js/plugins/jeditable/jquery.jeditable.js}"></script>
    <!-- Data Tables -->
    <script th:src="@{/static/js/plugins/dataTables/jquery.dataTables.js}"></script>
    <script th:src="@{/static/js/plugins/dataTables/dataTables.bootstrap.js}"></script>

    <!-- 弹出层js -->
    <script th:src="@{/static/js/plugins/layer/layer.min.js}"></script>
    <script th:src="@{/static/js/myLayer.js}"></script>
</div>
</body>
</html>
```



#### 2.4.4.2引用头部封装模版

增改查页面head部分全部替换：

```html
<head th:include="common/head :: head"></head>
```



#### 2.4.4.3封装分页插件样式

在common下新建：pagination.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Title</title>
</head>
<body>

<div class="row" th:fragment="pagination">
    <!--将每页的数量作为请求参数提交，可修改每页最多显示的条数-->
    <input type="hidden" name="pageSize" id="pageSize" th:value="${page.pageSize}"/>
    <!--将当前页码作为请求参数提交，切换不同页码的页面-->
    <input type="hidden" name="pageNum" id="pageNum" th:value="${page.pageNum}"/>
    <div class="col-sm-6">
        <div class="dataTables_info" id="DataTables_Table_0_info" role="alert"
             aria-live="polite" aria-relevant="all">显示
            <span th:text="${page.startRow}"></span> 到 <span th:text="${page.endRow}"></span> 项，
            共 <span th:text="${page.total}"></span> 项
        </div>
    </div>
    <div class="col-sm-6">
        <div class="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
            <ul class="pagination">

                <!--页码开始-->
                <!--首页，两种样式，根据是否为首页进行切换⚠️-->
                <li th:if="${page.isFirstPage}" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页是首页，则无法点击首页跳转-->
                    <a href="javascript:;">首页</a>
                </li>
                <li th:if="${!page.isFirstPage}" class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页不是首页，修改当前页pageNum的value值为1，点击即可提交表单跳转到首页-->
                    <a th:href="'javascript:document.forms.ec.pageNum.value=1;document.forms.ec.submit();'">首页</a>
                </li>

                <!--上一页，两种样式，根据是否为首页进行切换⚠️-->
                <li th:if="${page.isFirstPage}" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页是首页，则无法点击上一页跳转-->
                    <a href="javascript:;">上一页</a>
                </li>
                <li th:if="${!page.isFirstPage}" class="paginate_button previous" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页不是首页，修改当前页pageNum的value值为上一页prePage属性的值，点击即可提交表单跳转到首页-->
                    <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.prePage}+';document.forms.ec.submit();'">上一页</a>
                </li>

                <!--页码，循环所有导航页号navigatepageNums，并判断是否为当前页，设置active样式-->
                <li th:class="${i==page.pageNum?'paginate_button active':'paginate_button'}" aria-controls="DataTables_Table_0" tabindex="0" th:each="i:${page.navigatepageNums}">
                    <!--为每个导航页码都赋值上跳转到自己页面的超链接-->
                    <a th:href="'javascript:document.forms.ec.pageNum.value='+${i}+';document.forms.ec.submit();'" th:text="${i}"></a>
                </li>

                <!--下一页，两种样式，根据是否为尾页进行切换⚠️-->
                <li th:if="${page.isLastPage}" class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页是尾页，则无法点击下一页跳转-->
                    <a href="javascript:;">下一页</a>
                </li>
                <li th:if="${!page.isLastPage}" class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页不是尾页，修改当前页pageNum的value值为下一页nextPage属性的值，点击即可提交表单跳转到首页-->
                    <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.nextPage}+';document.forms.ec.submit();'">下一页</a>
                </li>

                <!--尾页，两种样式，根据是否为尾页进行切换⚠️-->
                <li th:if="${page.isLastPage}" class="paginate_button next disabled" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页是尾页，则无法点击尾页跳转-->
                    <a href="javascript:;">尾页</a>
                </li>
                <li th:if="${!page.isLastPage}" class="paginate_button next" aria-controls="DataTables_Table_0" tabindex="0">
                    <!--若当前页不是尾页，修改当前页pageNum的value值为总页数pages属性的值，点击即可提交表单跳转到尾页-->
                    <a th:href="'javascript:document.forms.ec.pageNum.value='+${page.pages}+';document.forms.ec.submit();'">尾页</a>
                </li>
                <!--页码结束-->

            </ul>
        </div>
    </div>
</div>
</body>
</html>
```



#### 2.4.4.4引用分页封装模版

```html
<div class="row" th:include="common/pagination :: pagination"></div>
```



#### 2.4.4.5完整封装页面展示

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<body class="gray-bg">
<!--查询角色的表单-->
<form id="ec" th:action="@{/role}" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">

                        <!--搜索内容开始-->
                        <table class="table form-table margin-bottom10">
                            <tr>
                                <td>
                                    <!--搜索内容回显-->
                                    <input type="text" name="roleName"
                                           th:value="${#maps.containsKey(filters, 'roleName')} ? ${filters.roleName} : ''"
                                           placeholder="角色名称" class="input-sm form-control"/>
                                </td>
                            </tr>
                        </table>
                        <div>
                            <!--点击搜索按钮提交表单，并为表单项的页码参数pageNum赋值1-->
                            <button type="button" class="btn btn-sm btn-primary"
                                    onclick="javascript:document.forms.ec.pageNum.value=1;document.forms.ec.submit();">
                                搜索
                            </button>
                            <button type="button" class="btn btn-sm btn-primary create">新增</button>
                            <button type="button" id="loading-example-btn"
                                    onclick="javascript:window.location.reload();" class="btn btn-white btn-sm">刷新
                            </button>
                        </div>
                        <!--搜索内容结束-->

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>角色名称</th>
                                <th>角色编码</th>
                                <th>描述</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="gradeX" th:each="item,it : ${page.list}">
                                <td class="text-center" th:text="${it.count}">11</td>
                                <td th:text="${item.roleName}">22</td>
                                <td th:text="${item.roleCode}">33</td>
                                <td th:text="${item.description}">33</td>
                                <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}">33</td>
                                <td class="text-center">
                                    <a class="edit" th:attr="data-id=${item.id}">修改</a>
                                    <a class="delete" th:attr="data-id=${item.id}">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <!--分页插件页码开始-->
                        <div class="row" th:include="common/pagination :: pagination"></div>
                        <!--分页插件页码结束-->

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!--在使用thymeleaf时，前端页面如要在javascript中获取后端传入的数据，需要使用<script th:inline="javascript">-->
<script th:inline="javascript">
    //弹出层事件
    $(function () {
        //新增
        $(".create").on("click", function () {
            opt.openWin("/role/create", "新增", 580, 430);
        });
        //修改
        $(".edit").on("click", function () {
            var id = $(this).attr("data-id");
            opt.openWin('/role/edit/' + id, '修改', 580, 430);
        });
        //删除
        $(".delete").on("click", function () {
            var id = $(this).attr("data-id");
            opt.confirm('/role/delete/' + id);
        });
    });

</script>
</body>
</html>
```



## 2.5前端数据校验

前端数据校验使用jQuery Validate 插件

jQuery Validate 插件捆绑了一套有用的验证方法，包括 URL 和电子邮件验证，同时提供了一个用来编写用户自定义方法的 API。

参考文档：https://www.runoob.com/jquery/jquery-plugin-validate.html

### 2.5.1默认校验规则

| 序号 | 规则               | 描述                                                         |
| :--- | :----------------- | :----------------------------------------------------------- |
| 1    | required:true      | 必须输入的字段。                                             |
| 2    | remote:"check.php" | 使用 ajax 方法调用 check.php 验证输入值。                    |
| 3    | email:true         | 必须输入正确格式的电子邮件。                                 |
| 4    | url:true           | 必须输入正确格式的网址。                                     |
| 5    | date:true          | 必须输入正确格式的日期。日期校验 ie6 出错，慎用。            |
| 6    | dateISO:true       | 必须输入正确格式的日期（ISO），例如：2009-06-23，1998/01/22。只验证格式，不验证有效性。 |
| 7    | number:true        | 必须输入合法的数字（负数，小数）。                           |
| 8    | digits:true        | 必须输入整数。                                               |
| 9    | creditcard:        | 必须输入合法的信用卡号。                                     |
| 10   | equalTo:"#field"   | 输入值必须和 #field 相同。                                   |
| 11   | accept:            | 输入拥有合法后缀名的字符串（上传文件的后缀）。               |
| 12   | maxlength:5        | 输入长度最多是 5 的字符串（汉字算一个字符）。                |
| 13   | minlength:10       | 输入长度最小是 10 的字符串（汉字算一个字符）。               |
| 14   | rangelength:[5,10] | 输入长度必须介于 5 和 10 之间的字符串（汉字算一个字符）。    |
| 15   | range:[5,10]       | 输入值必须介于 5 和 10 之间。                                |
| 16   | max:5              | 输入值不能大于 5。                                           |
| 17   | min:10             | 输入值不能小于 10。                                          |



### 2.5.2使用插件添加校验

#### 2.5.2.1导入插件js资源

head.html文件引入js库

```html
<!--jQuery Validate 插件-->
<script th:src="@{/static/js/plugins/validate/jquery.validate.min.js}" type="text/javascript" ></script>
<script th:src="@{/static/js/plugins/validate/messages_zh.min.js}" type="text/javascript" ></script>
```



#### 2.5.2.2新增操作添加校验

向create.html添加校验代码：

```html
<script type="text/javascript">
    $(function(){//window.onload
        //为id值为ec的表单添加校验
        $('#ec').validate({
            //规则：roleName和description不能为空
            rules:{
                roleName:"required",
                description:"required"
            },
            //错误的提示信息：如果为空则提示信息
            messages:{
                roleName:"角色必须输入",
                description:"描述必须输入"
            },
            //拦截表单的提交，如果有不符合规则的内容，则不会提交，如果提交的话，还会将确定按钮设置为禁用
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
```

create.html完整的校验代码：

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head::head"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/role/save}" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色名称：</label>
                    <div class="col-sm-10">
                        <input type="text" name="roleName" id="roleName" value="" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色编码：</label>
                    <div class="col-sm-10">
                        <input type="text" name="roleCode" id="roleCode" value="" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">描述：</label>
                    <div class="col-sm-10">
                        <textarea name="description" id="description" class="form-control" style="width:100%;height: 50px;" ></textarea>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" value="取消" onclick="opt.closeWin()">取消</button></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function(){//window.onload
        //为id值为ec的表单添加校验
        $('#ec').validate({
            //规则：roleName和description不能为空
            rules:{
                roleName:"required",
                description:"required"
            },
            //错误的提示信息：如果为空则提示信息
            messages:{
                roleName:"角色必须输入",
                description:"描述必须输入"
            },
            //拦截表单的提交，如果有不符合规则的内容，则不会提交，如果提交的话，还会将确定按钮设置为禁用
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
</html>
```



#### 2.5.2.3修改操作添加校验

向edit.html添加校验代码：

```html
<script type="text/javascript">
    $(function(){//window.onload
        //为id值为ec的表单添加校验
        $('#ec').validate({
            //规则：roleName和description不能为空
            rules:{
                roleName:"required",
                description:"required"
            },
            //错误的提示信息：如果为空则提示信息
            messages:{
                roleName:"角色必须输入",
                description:"描述必须输入"
            },
            //拦截表单的提交，如果有不符合规则的内容，则不会提交，如果提交的话，还会将确定按钮设置为禁用
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
```

edit.html完整的校验代码：

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head::head"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/role/update}" method="post" class="form-horizontal" >
                <!--将roleId作为隐藏域-->
                <input type="hidden" name="id" th:value="${role.id}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色：</label>

                    <div class="col-sm-10">
                        <input type="text" name="roleName" id="roleName" th:value="${role.roleName}" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">角色编码：</label>
                    <div class="col-sm-10">
                        <input type="text" name="roleCode" id="roleCode" th:value="${role.roleCode}" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">描述：</label>
                    <div class="col-sm-10">
                        <textarea name="description" id="description" class="form-control" style="width:100%;height: 50px;" th:text="${role.description}" ></textarea>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button></div>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
</body>
<script type="text/javascript">
    $(function(){//window.onload
        //为id值为ec的表单添加校验
        $('#ec').validate({
            //规则：roleName和description不能为空
            rules:{
                roleName:"required",
                description:"required"
            },
            //错误的提示信息：如果为空则提示信息
            messages:{
                roleName:"角色必须输入",
                description:"描述必须输入"
            },
            //拦截表单的提交，如果有不符合规则的内容，则不会提交，如果提交的话，还会将确定按钮设置为禁用
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
</html>
```



## 2.6用户管理

使用封装代码，实现用户管理的增删改查

### 2.6.1用户管理dao层

#### 2.6.1.1dao层接口文件

AdminDao

```java
package com.atguigu.dao;

import com.atguigu.entity.Admin;



/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RoleDao
 */
public interface AdminDao extends BaseDao<Admin> {
}
```



#### 2.6.1.2dao层映射文件

AdminDao.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.AdminDao">

    <!-- 用于select查询公用抽取的列 -->
    <sql id="columns">
        select id,username,password,name,phone,head_url,description,create_time,update_time,is_deleted from acl_admin
    </sql>

    <!--搜索结果分页显示-->
    <select id="findPage" resultType="admin">
        <include refid="columns"></include>
        <where>
            <if test="username != null and username != ''">
                and username like CONCAT('%',#{username},'%')
            </if>
            <if test="name != null and name != ''">
                and name like CONCAT('%',#{name},'%')
            </if>
            <if test="phone != null and phone != ''">
                and phone like CONCAT('%',#{phone},'%')
            </if>
            <if test="createTimeBegin != null and createTimeBegin != ''">
                and create_time >= #{createTimeBegin}
            </if>
            <if test="createTimeEnd != null and createTimeEnd != ''">
                and create_time &lt;= #{createTimeEnd}
            </if>
            and is_deleted = 0
            order by id desc
        </where>
    </select>

    <!--查询单个-->
    <select id="getById" resultType="admin">
        <include refid="columns"/>
        where
        id = #{id}
    </select>

    <!--新增-->
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        insert into acl_admin (
        id ,
        username ,
        password ,
        name ,
        phone ,
        head_url ,
        description
        ) values (
        #{id} ,
        #{username} ,
        #{password} ,
        #{name} ,
        #{phone} ,
        #{headUrl} ,
        #{description}
        )
    </insert>

    <!--修改方式一：可赋值为null
    <update id="update">
        update acl_admin set
        name=#{name},phone=#{phone},head_url=#{headUrl}
        where id=#{id}
    </update>
    -->

    <!--修改方式二：使用set标签，赋为null或空串时不修改原来的数据-->
    <update id="update">
        update acl_admin
        <set>
            <if test="name != null and name != ''">
                name = #{name},
            </if>
            <if test="phone != null and phone != ''">
                phone = #{phone},
            </if>
            <if test="headUrl != null and headUrl != ''">
                head_url = #{headUrl},
            </if>
        </set>
        where id=#{id}
    </update>


    <!--删除-->
    <update id="delete">
        update acl_admin set
        is_deleted = 1
        where
        id = #{id}
    </update>

</mapper>
```



### 2.6.2用户管理service层

#### 2.6.2.1service层接口

AdminService

```java
package com.atguigu.service;

import com.atguigu.entity.Admin;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface AdminService extends BaseService<Admin> {
}
```



#### 2.6.2.2service层接口实现类

AdminServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.AdminDao;
import com.atguigu.dao.BaseDao;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public BaseDao<Admin> getEntityDao() {
        return adminDao;
    }

}
```



### 2.6.3用户管理controller层

AdminController

```java
package com.atguigu.controller;

import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * TODD
 * @AllClassName: com.atguigu.controller.AdminController
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private final static String PAGE_INDEX = "admin/index";
    private final static String PAGE_CREATE = "admin/create";
    private final static String PAGE_EDIT = "admin/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/admin";

    @Autowired
    private AdminService adminService;

    /**
     *  处理/请求，跳转到index页，搜索处理、分页处理
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String,Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Admin> page = adminService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        return PAGE_INDEX;
    }

    /**
     * 处理/create请求，跳转到添加资源页面
     */
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * 处理/save请求，执行添加资源操作
     */
    @RequestMapping("/save")
    public String save(Admin admin) {
        adminService.insert(admin);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/edit/id请求，跳转到修改资源页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Admin admin = adminService.getById(id);
        map.put("admin",admin);
        return PAGE_EDIT;
    }

    /**
     * 处理/update请求，执行资源修改操作
     */
    @RequestMapping(value="/update")
    public String update(Admin admin) {
        adminService.update(admin);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/delete/id请求，执行资源删除操作
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

}
```



### 2.6.4用户管理前端html页面

在frame/index左侧导航栏页面修改内容

```html
<li>
    <a class="J_menuItem" th:href="@{/admin}" data-index="0">用户管理</a>
</li>
```

在/pages/admin下创建CURD页面

#### 2.6.4.1查询index页面

index.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head th:include="common/head :: head"></head>

<body class="gray-bg">
<form id="ec" th:action="@{/admin}" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <table class="table form-table margin-bottom10">
                            <tr>
                                <td>
                                    <input type="text" name="username" th:value="${#maps.containsKey(filters, 'username')} ? ${filters.username} : ''" placeholder="用户名" class="input-sm form-control"/>
                                </td>
                                <td>
                                    <input type="text" name="name" th:value="${#maps.containsKey(filters, 'name')} ? ${filters.name} : ''" placeholder="用户姓名" class="input-sm form-control"/>
                                </td>
                                <td>
                                    <input type="text" name="phone" th:value="${#maps.containsKey(filters, 'phone')} ? ${filters.phone} : ''" placeholder="手机号码" class="input-sm form-control"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="datetime-local" name="createTimeBegin" th:value="${#maps.containsKey(filters, 'createTimeBegin')} ? ${filters.createTimeBegin} : ''" placeholder="开始日期：YYYY-MM-DD" class="input-sm form-control"/>
                                </td>
                                <td>
                                    <input type="datetime-local" name="createTimeEnd" th:value="${#maps.containsKey(filters, 'createTimeEnd')} ? ${filters.createTimeEnd} : ''" placeholder="截止日期：YYYY-MM-DD" class="input-sm form-control"/>
                                </td>
                                <td>
                                </td>
                            </tr>
                        </table>
                        <div>
                            <button type="button" class="btn btn-sm btn-primary" onclick="javascript:document.forms.ec.pageNum.value=1;document.forms.ec.submit();">搜索</button>
                            <button type="button" class="btn btn-sm btn-primary create"> 新增</button>
                            <button type="button" id="loading-example-btn" onclick="javascript:window.location.reload();" class="btn btn-white btn-sm">刷新</button>
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>头像</th>
                                <th>用户姓名</th>
                                <th>用户账号</th>
                                <th>手机号</th>
                                <th>创建时间</th>
                                <th>操作 </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="gradeX" th:each="item,it: ${page.list}">
                                <td class="text-center" th:text="${it.count}">11</td>
                                <td>
                                    <img th:src="${item.headUrl}" style="width: 60px; height: 60px;">
                                </td>
                                <td th:text="${item.name}">22</td>
                                <td th:text="${item.username}">33</td>
                                <td th:text="${item.phone}">22</td>
                                <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}" >33</td>
                                <td class="text-center">
                                    <a class="edit" th:attr="data-id=${item.id}">修改</a>
                                    <a class="delete" th:attr="data-id=${item.id}">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div class="row" th:include="common/pagination :: pagination"></div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<script th:inline="javascript">
    $(function(){
        $(".create").on("click",function () {
            opt.openWin('/admin/create','新增',630,430)
        });
        $(".edit").on("click",function () {
            var id = $(this).attr("data-id");
            opt.openWin('/admin/edit/' + id,'修改',580,430);
        });
        $(".delete").on("click",function(){
            var id = $(this).attr("data-id");
            opt.confirm('/admin/delete/'+id);
        });
    });
</script>
</body>
</html>
```



#### 2.6.4.2新增create页面

create.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<script type="text/javascript">
    $(function(){
        $('#ec').validate({
            rules:{
                name:"required",
                username:{
                    rangelength:[3,15]
                },
                phone:{
                    required:true,
                    rangelength:[11,11]
                },
                password:{
                    required:true,
                    rangelength:[6,15]
                },
                confirmPassword:{
                    equalTo:"#password"
                }
            },
            messages:{
                name:"真实姓名必须输入",
                username:{
                    rangelength:"用户账号3到15位"
                },
                phone:{
                    required: "手机号码必须输入",
                    rangelength:"手机号码格式不正确"
                },
                password:{
                    required: "密码必须输入",
                    rangelength:"密码6到15位"
                },
                confirmPassword:{
                    equalTo:"密码与确认密码不一致"
                }
            },
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/admin/save}" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">真实姓名：</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" id="name" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">用户账号：</label>
                    <div class="col-sm-10">
                        <input type="text" name="username" id="username" class="form-control"/>
                        <label for="username" class="error" id="usernameLabel"></label>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机号码：</label>
                    <div class="col-sm-10">
                        <input type="text" name="phone" id="phone" maxlength="11" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">密码：</label>
                    <div class="col-sm-10">
                        <input type="password" name="password" id="password" maxlength="15" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">确认密码：</label>
                    <div class="col-sm-10">
                        <input type="password" name="confirmPassword" id="confirmPassword" maxlength="15" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



#### 2.6.4.3修改edit页面

edit.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<script type="text/javascript">
    $(function(){
        $('#ec').validate({
            rules:{
                name:"required",
                phone:{
                    required:true,
                    rangelength:[11,11]
                }
            },
            messages:{
                name:"真实姓名必须输入",
                phone:{
                    required: "手机号码必须输入",
                    rangelength:"手机号码格式不正确"
                }
            },
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/admin/update}" method="post" class="form-horizontal">
                <input type="hidden" name="id" th:value="${admin.id}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">真实姓名：</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" id="name" th:value="${admin.name}" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机号码：</label>
                    <div class="col-sm-10">
                        <input type="text" name="phone" id="phone" th:value="${admin.phone }" maxlength="11" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```





# 3 Apache Dubbo★

## 3.1分布式RPC框架

### 3.1.1架构的演变

单体架构、垂直架构、SOA架构到微服务架构

单体架构

- 架构简单，前期开发成本低、开发周期短
- 适合小型项目（OA、CRM、ERP 企业内部应用）

垂直架构：

- 按照业务进行切割，形成小的单体项目
- 垂直MVC项目主要有表现层，业务逻辑层，数据访问层组成的MVC架构
- 整个项目打包放在一个tomcat里面。适合于 访问量小，用户数不多的业务

SOA架构：

- SOA 全称为 Service-Oriented Architecture，即面向服务的架构
- 据需求通过网络对松散耦合的粗粒度应用组件(服务)进行分布式部署、组合和使用
- 一个服务通常以独立的形式存在于操作系统进程中

微服务架构：

- SOA 上做的升级，微服务架构强调的一个重点是“业务需要彻底的组件化和服务化”
- 原有的单个业务系统会拆分为多个可以独立开发、设计、运行的小应用。这些小应用之间通过服务完成交互和集成



### 3.1.2Apache Dubbo概述

Apache Dubbo是一款高性能的Java RPC框架。其前身是阿里巴巴公司开源的一个高性能、轻量级的开源Java RPC框架，可以和Spring框架无缝集成。Dubbo提供了三大核心能力:面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。

RPC全称为remote procedure call，即远程过程调用。

Dubbo官网地址：http://dubbo.apache.org 



### 3.1.3Zookeeper

Dubbo没有自己实现注册中心，所以要使用Zookeeper作为注册中心，即要使用Dubbo必须先启动Zookeeper

Zookeeper作为注册中心是以树形结构构建服务的，结构如下：

- Dubbo
  - Service
    - 服务提供者
      - 提供服务的IP和端口
    - 服务消费者
      - 消费服务的IP

这种服务结构，一旦服务提供者发生变化，注册中心可以及时联系服务消费者修改

Zookeeper还支持集群，防止单点服务器异常

Zookeeper底层调用原理是代理模式，基于类型获取⚠️



### 3.1.4配置Zookeeper★

1. 将`conf`目录中的`zoo_sample.cfg`文件复制一份到`conf`目录并命名为`zoo.cfg`
2. 修改`zoo.cfg`文件: 大概在第12行，设置`dataDir=../data`
3. zookeeper从3.5开始默认占用8080端口号，因为Tomcat默认也占用8080端口号，我们可以在zoo.cfg配置文件中修改该端口号

```shell
admin.serverPort=8888
```

启动Zookeeper，首先进入bin目录：

-  Windows启动：双击zkServer.cmd
- Linux/Mac启动：./zkServer.sh start

连接Zookeeper，首先进入bin目录：

-  Windows连接：双击zkCli.cmd
- Linux/Mac连接：./zkCli.sh



### 3.1.5调用关系

提供服务：服务提供者→注册中心⇆服务消费者→计数器←服务提供者

消费服务：服务消费者→服务提供者（基于代理模式，根据类型调用）

提供服务是异步，消费服务是同步，双向操作仅有`注册中心⇆服务消费者`

调用关系说明:

1. 服务容器负责启动，加载，运行服务提供者。

2. 服务提供者在启动时，向注册中心注册自己提供的服务。

3. 服务消费者在启动时，向注册中心订阅自己所需的服务。

4. 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。

5. 服务消费者，从提供者地址列表中，基于负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。

6. 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。



## 3.2Dubbo入门程序★

首先基于Maven创建项目结构：

- dubbo_parent（父工程，管理依赖）
  - dubbo_interface（接口工程）
  - dubbo_provider（服务提供者）
  - dubbo_consumer（服务消费者）

### 3.2.1父工程dubbo_parent

删除src目录，只保留pom.xml文件，打包方式设置为pom，导入以下依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu</groupId>
    <artifactId>dubbo_parent</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>dubbo_provider</module>
        <module>dubbo_consumer</module>
        <module>dubbo_interface</module>
    </modules>


    <properties>
        <dubbo.version>3.0.8</dubbo.version>
        <spring.version>5.2.20.RELEASE</spring.version>
    </properties>
    <dependencyManagement>
        <dependencies>
            <!--Spring相关jar包-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <!--Dubbo相关jar包-->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo</artifactId>
                <version>${dubbo.version}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-dependencies-zookeeper</artifactId>
                <version>${dubbo.version}</version>
                <type>pom</type>
            </dependency>
        </dependencies>
    </dependencyManagement>


</project>
```



### 3.2.2创建接口工程

子工程`dubbo_interface`是接口工程，为服务提供者和服务消费者提供统一接口。

#### 3.2.2.1创建服务接口

创建接口文件

```java
package com.atguigu.service;

public interface HelloService {
    String sayHello(String name);
}
```



#### 3.2.2.2提供接口

第一步：必须将`dubbo-interface`执行install的操作，将当前模块安装到本地仓库中，pom.xml内可查看坐标

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>dubbo_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>dubbo_interface</artifactId>

</project>
```

第二步：服务提供者`dubbo_provider`和服务消费者`dubbo_consumer`的pom.xml文件中必须使用坐标添加该依赖

```xml
<!--继承接口依赖dubbo_interface-->
<dependency>
  <artifactId>dubbo_interface</artifactId>
  <groupId>com.atguigu</groupId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```



### 3.2.3创建服务提供者

子工程`dubbo_provider`是服务提供者工程，向服务注册中心zookeeper注册服务。

#### 3.2.3.1引入父工程依赖

修改pom.xml文件，打包方式为war，导入父工程的依赖，导入接口工程的依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>dubbo_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>war</packaging>

    <artifactId>dubbo_provider</artifactId>

    <dependencies>
        <!--Spring相关jar包-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <!--Dubbo相关jar包-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-dependencies-zookeeper</artifactId>
            <type>pom</type>
        </dependency>
        <!--继承接口依赖dubbo_interface-->
        <dependency>
            <artifactId>dubbo_interface</artifactId>
            <groupId>com.atguigu</groupId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.15.v20190215</version>
                <configuration>
                    <!-- 如果检测到项目有更改则自动热部署，每隔n秒扫描一次。默认为0，即不扫描-->
                    <scanIntervalSeconds>10</scanIntervalSeconds>
                    <webAppConfig>
                        <!--指定web项目的根路径，默认为/ -->
                        <contextPath>/</contextPath>
                    </webAppConfig>
                    <httpConnector>
                        <!--端口号，默认 8080-->
                        <port>8081</port>
                    </httpConnector>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



#### 3.2.3.2创建服务实现类

service层实现类：

@DubboService注解：将当前类的服务注册到注册中心⚠️

```java
package com.atguigu.service.impl;

import com.atguigu.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.HelloServiceImpl
 * @DubboService 将当前类的服务注册到注册中心
 */
@DubboService
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello:"+name;
    }
}
```



#### 3.2.3.3配置注册服务信息

创建spring-service.xml，配置向服务注册中心zookeeper注册服务的信息

`dubbo:annotation`包扫描器比`context:component-scan`包扫描器更加强大，Dubbo升级了包扫描器，同时扫描spring和springMVC也不冲突

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd">
  
    <!-- 为当前服务提供者起个名字 name值随便定义，但不能和消费者冲突 -->
    <dubbo:application name="dubbo_provider"/>
  
    <!-- 连接服务注册中心zookeeper ip为zookeeper所在服务器的ip地址-->
    <dubbo:registry address="zookeeper://localhost:2181"/>
  
    <!-- 设置服务暴漏在哪个端口下，name指定协议，端口默认是20880 -->
    <dubbo:protocol name="dubbo" port="20881"></dubbo:protocol>
  
    <!-- 扫描指定包，加入@DubboService注解的类会被发布为服务，也可以使用配置文件  -->
    <dubbo:annotation package="com.atguigu.service"/>

</beans>
```



#### 3.2.3.4配置web.xml

使用监听器，监听服务启动，加载spring-service.xml文件，向服务注册中心zookeeper注册服务

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-service.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
</web-app>
```



#### 3.2.3.5启动服务提供者

Maven → web_admin → Plugins → jetty → jetty:run

访问：http://localhost:8081/（8081是pom文件中设置的jetty服务的端口）



### 3.2.4创建服务消费者

子工程`dubbo_consumer`是服务消费者工程，在服务注册中心zookeeper使用服务。

#### 3.2.4.1引入父工程依赖

修改pom.xml文件，打包方式为war，导入父工程的依赖，导入接口工程的依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>dubbo_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>dubbo_consumer</artifactId>

    <packaging>war</packaging>
    <dependencies>
        <!--Spring相关jar包-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <!--Dubbo相关jar包-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-dependencies-zookeeper</artifactId>
            <type>pom</type>
        </dependency>
        <!--继承接口依赖dubbo_interface-->
        <dependency>
            <artifactId>dubbo_interface</artifactId>
            <groupId>com.atguigu</groupId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.15.v20190215</version>
                <configuration>
                    <!-- 如果检测到项目有更改则自动热部署，每隔n秒扫描一次。默认为0，即不扫描-->
                    <scanIntervalSeconds>10</scanIntervalSeconds>
                    <webAppConfig>
                        <!--指定web项目的根路径，默认为/ -->
                        <contextPath>/</contextPath>
                    </webAppConfig>
                    <httpConnector>
                        <!--端口号，默认 8080-->
                        <port>8082</port>
                    </httpConnector>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



#### 3.2.4.2创建Controller

不需要创建service层，直接向服务注册中心zookeeper获取服务，调用服务提供者的service

@DubboReference注解：从注册中心拿到服务层对象⚠️

```java
package com.atguigu.controller;

import com.atguigu.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.HelloController
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    /***
     * 不能使用@Autowired从IoC容器内找内容相同的完成自动装配
     * 应该去注册中心拿到服务层对象，使用@DubboReference注解
     */
    @DubboReference
    private HelloService helloService;

    @RequestMapping
    @ResponseBody
    public String hello(String name){
        String atguigu = helloService.sayHello(name);
        return atguigu;
    }
}
package com.atguigu.controller;

import com.atguigu.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.HelloController
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    /***
     * 不能使用@Autowired从IoC容器内找内容相同的完成自动装配
     * 应该去注册中心拿到服务
     */
    @DubboReference
    private HelloService helloService;

    @RequestMapping
    @ResponseBody
    public String hello(String name){
        String atguigu = helloService.sayHello(name);
        return atguigu;
    }
}

```



#### 3.2.4.3配置获取服务信息

创建spring-service.xml，配置向服务注册中心zookeeper获取服务的信息

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://code.alibabatech.com/schema/dubbo
         http://code.alibabatech.com/schema/dubbo/dubbo.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
  
    <!--设置注解包的扫描-->
    <context:component-scan base-package="com.atguigu.controller"/>
  
    <!-- 为当前消费者起个名字，注意：消费者和提供者应用名不要一样 -->
    <dubbo:application name="dubbo_consumer" />
  
    <!-- 连接服务注册中心zookeeper ip为zookeeper所在服务器的ip地址-->
    <dubbo:registry address="zookeeper://localhost:2181"/>
  
    <!-- 运行dubbo不检查提供者是否提前开启，可以不需要考虑启动顺序，可配置timepout属性，长时间没提供者才会报错-->
    <dubbo:consumer check="false"></dubbo:consumer>
</beans>
```



#### 3.2.4.4配置web.xml

使用监听器，监听服务启动，加载spring-service.xml文件，向服务注册中心zookeeper获取服务

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 指定加载的配置文件 ，通过参数contextConfigLocation加载 -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```



#### 3.2.4.5启动服务消费者

Maven → web_admin → Plugins → jetty → jetty:run

访问：http://localhost:8082/（8082是pom文件中设置的jetty服务的端口）



## 3.3Dubbo管理控制台

我们在开发时，需要知道Zookeeper注册中心都注册了哪些服务，有哪些消费者来消费这些服务。我们可以通过部署一个管理中心来实现。其实管理中心就是一个web应用，使用Tomcat或其他服务器部署即可。

### 3.3.1获取平台项目

Dubbo管理控制台是Apache在GitHub上开源的项目，任何人都可以访问获取

GitHub网址：https://github.com/apache/dubbo-admin

我们可从GitHub克隆项目使用，也可以使用别人打包好的war包，但是使用war包和打包的JDK版本不同时，部署后可能会响应404！⚠️



### 3.3.2部署管理平台

以Tomcat部署war包为例：

- 将war包文件复制到Tomcat的webapps目录下
- Windows：在Tomcat的bin目录双击startup.bat文件启动Tomcat，war包会自动解压
- Linux+Mac：命令行cd到在Tomcat的bin目录下，`startup.sh`开启服务，`./shutdown.sh`关闭服务
- 访问http://localhost:8080/dubbo-admin-2.6.0/ ，输入用户名(root)和密码(root)，切换简体中文，路径要根据解压目录名更换
- 启动服务提供者工程和服务消费者工程，可以在查看到对应的信息

项目内的dubbo.properties文件是配置信息文件，修改之后需要重启Tomcat

```properties
#注意dubbo.registry.address对应的值需要对应当前使用的Zookeeper的ip地址和端口号
dubbo.registry.address=zookeeper://zk所在机器ip:zk端口
dubbo.admin.root.password=root //管理员访问密码
dubbo.admin.guest.password=guest //游客访问密码
```



## 3.4Dubbo相关配置说明

### 3.4.1包扫描

`dubbo:annotation`包扫描器比`context:component-scan`包扫描器更加强大

服务提供者需要配置，表示包扫描，作用是扫描指定包(包括子包)下的类

Dubbo升级了包扫描器，同时扫描spring和springMVC也不冲突

```xml
<dubbo:protocol name="dubbo" port="20880"/>
```



### 3.4.2基于XML配置

如果不使用包扫描，也可以通过XML配置的方式来发布和消费服务

#### 3.4.2.1配置提供者

首先，必须把服务提供者service层实现类中的`@DubboService`注解去掉，

```java
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "111Hello:"+name;
    }
}
```

然后，修改xml文件，注释掉Dubbo包扫描器，将该实现类通过xml配置到IoC中，再从IoC取出注册到注册中心

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd">

    <dubbo:application name="dubbo_provider"/>

    <dubbo:registry address="zookeeper://localhost:2181"/>

    <dubbo:protocol name="dubbo" port="20881"></dubbo:protocol>

    <!--<dubbo:annotation package="com.atguigu.service"/>-->

    <!--不使用dubbo:annotation包扫描器，直接配置-->
    <!--将服务HelloServiceImpl添加到IoC容器-->
    <bean id="helloService" class="com.atguigu.service.impl.HelloServiceImpl" />
		<!--将IoC容器中的HelloServiceImpl对象，注册到注册中心-->
    <dubbo:service interface="com.atguigu.service.HelloService" ref="helloService" />

</beans>
```



#### 3.4.2.2配置消费者

首先，修改controller，不再使用`@DubboReference`注解从注册中心获取服务，而是使用`@Autowired`注解从IoC容器自动注入

```java
@Controller
@RequestMapping("/hello")
public class HelloController {

		//@DubboReference
    @Autowired
    private HelloService helloService;

    @RequestMapping
    @ResponseBody
    public String hello(String name){
        String atguigu = helloService.sayHello(name);
        return atguigu;
    }
}
```

然后，修改xml配置，将注册中心的服务对象，注入到自己的IoC容器中

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://code.alibabatech.com/schema/dubbo
         http://code.alibabatech.com/schema/dubbo/dubbo.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <dubbo:application name="dubbo_consumer"/>

    <dubbo:registry address="zookeeper://localhost:2181"/>

    <dubbo:consumer check="false"></dubbo:consumer>

    <context:component-scan base-package="com.atguigu.controller"/>
    
    <!-- 将注册中心的服务对象，注入到自己的IoC容器 -->
    <dubbo:reference interface="com.atguigu.service.HelloService" id="helloService"/>

</beans>
```



### 3.4.3RPC协议

生产者和消费者一方配置即可。一般在服务提供者一方配置，可以指定使用的协议名称和端口号。

注意:：不同的服务提供者，协议使用的端口号不能相同⚠️

其中Dubbo支持的协议有：dubbo、rmi、hessian、http、webservice、rest、redis等：

推荐使用的是dubbo协议：

- 适合于小数据量大并发的服务调用，
- 以及服务消费者机器数远大于服务提供者机器数的情况。
- 不适合传送大数据量的服务，比如传文件，传视频等，除非请求量很低。

配置方式：

在xml文件中配置，可配置但协议或多协议，若是多协议可以在@DubboService注解中使用protocol属性选择协议

配置多协议：

```xml
<!-- 多协议配置 -->
<dubbo:protocol name="dubbo" port="20880" />
<dubbo:protocol name="rmi" port="20890" />
```

选择协议：

```java
@DubboService(protocol = "dubbo")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello:"+name;
    }
}
```



### 3.4.4启动时检查

标签：`<dubbo:consumer check="false"/>` 配置在 `服务消费者` 一方，如果不配置默认check值为true。

推荐设置为false，启动服务消费者时，不检查服务提供者是否提前开启，可以不需要考虑启动顺序

可以配置timepout属性，服务消费者启动一段时间后，还没提供者才会报错

```xml
<!--启动时候不检查 设置连接超时时间-->
<dubbo:consumer check="false" timeout="600000"></dubbo:consumer>
```



### 3.4.5地址缓存

地址缓存问题，围绕 `注册中心zookeeper` 服务是否关闭展开。

服务消费者和服务生产者启动时，会将服务提供方地址缓存到本地，以后再调用则不会访问注册中心，会访问缓存中的地址。

即第一次启动时 `注册中心zookeeper` 必须在线，，消费者将地址缓存到本地中后，`注册中心zookeeper` 宕机了也不影响。



## 3.5Dubbo负载均衡

当存在多个提供者时才需要考虑负载均衡

### 3.5.1负载均衡概述

负载均衡（Load Balance）：其实就是将请求分摊到多个操作单元上进行执行，从而共同完成工作任务。在集群负载均衡时，Dubbo 提供了多种均衡策略（包括随机、轮询、最少活跃调用数、一致性Hash），缺省为random随机调用。

1. 随机算法 RandomLoadBalance（默认）
2. 轮询算法 RoundRobinLoadBalance（不保证绝对轮询）
3. 最小活跃数算法 LeastActiveLoadBalance
4. 一致性hash算法 ConsistentHashLoadBalance

配置负载均衡策略，既可以在服务提供者一方配置，也可以在服务消费者一方配置，两者选其一即可: 

① 在服务消费者一方配置

```java
@RestController
public class HelloController {

    //在服务消费者一方配置负载均衡策略
    @DubboReference(loadbalance = "roundrobin")
    private HelloService helloService;
```

② 在服务提供者一方配置

```java
@DubboService(loadbalance = "roundrobin")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello:"+name;
    }
}
```

#### 

### 3.5.2负载均衡实践

准备环境：关闭`jetty`的热部署自动刷新，拷贝多个服务提供者`jetty`，依次修改以下配置后启动，模拟集群

① `pom.xml`文件中`jetty`插件的端口设号置为不重复的端口

② `spring-service.xml`中`dubbo`的端口号设置为不重复的端口

③ 为了演示方便，将`HelloServiceImpl`的`sayHello`方法的返回值改成`return "hello:端口号" + name`

④ 依次启动多个服务提供者`jetty`，然后启动一个服务消费者`jetty`进入页面，刷新页面测试。





# 4 服务拆分

## 4.1业务介绍

### 4.1.1项目模块划分

根据前面的介绍，目前我们的系统规划了3个dubbo服务提供者模块：权限服务、房源服务与会员服务，及2个服务消费者模块：尚好房管理平台（web-admin）与网站前端（web-front）

Dubbo服务提供者模块：

- 权限管理服务（ACL：Access Control List访问控制列表）
- 房源管理服务（HRS：Housing Resources）
- 会员管理服务（USER）

Dubbo服务消费者模块：

- 尚好房管理平台（web-admin）
- 网站前端（web-front）



### 4.1.2服务调用关系

管理平台（web-admin） ⇆ Nginx ⇆ 	网页前端（web-front）

管理平台（web-admin） ⇆ Dubbo服务(xx管理服务) ⇆ 	网页前端（web-front）



### 4.1.3项目说明

#### 4.1.3.1拆分步骤

当前项目为单体的SSM项目，目前开发了权限管理的用户管理与角色管理，接下来要开发房源管理的数据字典、小区管理与房源管理，权限管理与房源管理属于两不同的dubbo服务，当前我们就来把单体架构拆分为dubbo通信的分布式架构，

1. 父工程`shf_parent`模块管理dubbo相关的依赖
2. 子模块`common_util`引入父模块的依赖
3. 创建新模块`service-api`，该模块负责`服务层的API接口管理`和 `所有Dubbo服务提供者模块 ` 的依赖管理
4. 提取子模块`web_admin`的service层API接口，交给`service-api`模块管理
5. 创建新模块`service`作为service模块的父工程，删除src目录，引入依赖，负责所有子模块的聚合，打包方式设置为pom
6. 创建`service`的子模块`service-acl`，作为权限管理服务模块，该模块要进行服务提供者相关配置
7. 拆分子模块`web_admin`的dao层和service层实现类，resources目录中dao层service层的相关资源，交给`service-acl`模块管理
8. 创建新模块`web`作为`所有Dubbo服务消费者模块`的父工程，删除src目录，负责所有子模块的聚合，打包方式设置为pom
9. 子模块`web_admin` 设置`web`为父工程，并将`shf_parent`父工程中`web_admin` 相关聚合信息删除，配置服务消费者相关设置
10. 启动`service-acl`与`web-admin`模块进行测试



#### 4.1.3.2最终项目结构

拆分前的项目结构：

- shf_parent
  - common_util
  - model
  - web_admin

拆分后的项目结构:

- shf_parent
  - common_util
  - model
  - service_api（服务接口）
  - service
    - service_acl（权限管理服务）
  - web
    - web_admin（平台页面 / 服务消费者）



## 4.2服务拆分

### 4.2.1shf_parent

在shf-parent模块pom.xml新增dubbo依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu</groupId>
    <artifactId>shf_parent</artifactId>
    <!--打包方式设置为父工程，idea自动设置-->
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>

    <!--idea自动生成的聚合-->
    <modules>
        <module>common_util</module>
        <module>model</module>
        <module>service_api</module>
        <module>service</module>
        <module>web</module>
    </modules>

    <!--jar包的版本管理-->
    <properties>
        <java.version>1.8</java.version>
        <spring.version>5.2.20.RELEASE</spring.version>
        <thymeleaf.version>3.0.11.RELEASE</thymeleaf.version>
        <pagehelper.version>4.1.6</pagehelper.version>
        <servlet-api.version>4.0.1</servlet-api.version>
        <fastjson.version>1.2.70</fastjson.version>
        <mybatis.version>3.5.6</mybatis.version>
        <mybatis.spring.version>2.0.6</mybatis.spring.version>
        <mysql.version>8.0.25</mysql.version>
        <druid.version>1.1.12</druid.version>
        <commons-fileupload.version>1.3.1</commons-fileupload.version>
        <slf4j-version>1.7.30</slf4j-version>
        <logback-version>1.2.3</logback-version>
        <dubbo.version>3.0.8</dubbo.version>
    </properties>

    <!--依赖管理，子类可按需引用继承-->
    <dependencyManagement>
        <dependencies>
            <!-- SpringMVC相关 -->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <!--spring封装的jdbc数据库访问-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-tx</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <!--Spring提供的对AspectJ框架的整合-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-aspects</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <!--用于spring测试(没啥用，就是为了单元测试)-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-test</artifactId>
                <version>${spring.version}</version>
            </dependency>

            <!--用于springMVC模板-->
            <dependency>
                <groupId>org.thymeleaf</groupId>
                <artifactId>thymeleaf-spring5</artifactId>
                <version>${thymeleaf.version}</version>
            </dependency>

            <!--mybatis的分页插件-->
            <dependency>
                <groupId>com.github.pagehelper</groupId>
                <artifactId>pagehelper</artifactId>
                <version>${pagehelper.version}</version>
            </dependency>
            <!-- Mybatis -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>${mybatis.version}</version>
            </dependency>
            <!-- Mybatis与Spring整合所需要的jar包 -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis-spring</artifactId>
                <version>${mybatis.spring.version}</version>
            </dependency>
            <!-- MySql -->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <!-- 连接池 -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <!-- 文件上传组件 -->
            <dependency>
                <groupId>commons-fileupload</groupId>
                <artifactId>commons-fileupload</artifactId>
                <version>${commons-fileupload.version}</version>
            </dependency>

            <!-- fastjson json转换的-->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>${fastjson.version}</version>
            </dependency>

            <!-- 日志 -->
            <dependency>
                <groupId>org.slf4j</groupId>
                <artifactId>slf4j-api</artifactId>
                <version>${slf4j-version}</version>
            </dependency>
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-classic</artifactId>
                <version>${logback-version}</version>
            </dependency>
          
            <!--Dubbo相关jar包-->
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo</artifactId>
                <version>${dubbo.version}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.dubbo</groupId>
                <artifactId>dubbo-dependencies-zookeeper</artifactId>
                <version>${dubbo.version}</version>
                <type>pom</type>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <!--子类强制继承的依赖-->
    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>${servlet-api.version}</version>
            <!--Servlet-API  不会部署到服务器上-->
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!-- 控制Maven的编译版本：java编译插件，设置编译版本1.8-->
            <!-- 其实之前在maven的配置文件中设置过，不加也行⚠️ -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.2</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



### 4.2.2common_util

在common-util模块pom.xml引入依赖，该模块仍负责管理整个项目的依赖和提供工具类

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>shf_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <!--version、groupId与父模块相同，可省略只写artifactId-->
    <artifactId>common_util</artifactId>

    <!--将父工程内管理的jar包都继承下来，可以不用加version-->
    <dependencies>
        <!-- SpringMVC相关 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <!--spring封装的jdbc数据库访问-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
        </dependency>
        <!--Spring提供的对AspectJ框架的整合-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
        </dependency>
        <!--用于spring测试-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
        </dependency>

        <!--用于springMVC模板-->
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf-spring5</artifactId>
        </dependency>

        <!--mybatis的分页插件-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
        </dependency>
        <!-- Mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
        </dependency>
        <!-- Mybatis与Spring整合所需要的jar包 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
        </dependency>
        <!-- MySql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!-- 连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>
        <!-- 文件上传组件 -->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
        </dependency>

        <!-- fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
        </dependency>

        <!-- 日志 -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
        </dependency>

        <!--Dubbo相关jar包-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-dependencies-zookeeper</artifactId>
          	<!--默认type值为jar，设置为pom后无法添加到当前模块的类路径⚠️-->
            <type>pom</type>
        </dependency>
    </dependencies>
</project>
```



### 4.2.3service_api

创建service_api模块，引入依赖，剪切web_admin模块的service接口，该模块仍负责管理service项目依赖和service层API接口

#### 4.2.3.1pom.xml

pom.xml引入common_util和model依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>shf_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>service_api</artifactId>
    <packaging>jar</packaging>

    <!--直接依赖兄弟模块common_util和model，根据依赖传递的特性可使用他们compile的jar包-->
    <dependencies>
        <!--使用兄弟模块common_util的依赖-->
        <dependency>
            <groupId>com.atguigu</groupId>
            <artifactId>common_util</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!--使用兄弟模块model的依赖-->
        <dependency>
            <groupId>com.atguigu</groupId>
            <artifactId>model</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

</project>
```



#### 4.2.3.2serviceAPI

从web_admin模块中剪切的service层接口，com.atguigu.service目录下的接口文件

AdminService接口：

```java
package com.atguigu.service;

import com.atguigu.entity.Admin;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface AdminService extends BaseService<Admin> {

}
```

RoleService接口：

```java
package com.atguigu.service;

import com.atguigu.entity.Role;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RoleService
 */
public interface RoleService extends BaseService<Role> {

}
```



### 4.2.4service

创建service模块，删除src目录，引入依赖，负责所有子模块的聚合，打包方式设置为pom，作为所有service项目的父工程

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>shf_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>service</artifactId>
    <packaging>pom</packaging>
    <modules>
        <module>service_acl</module>
    </modules>

    <dependencies>
        <dependency>
            <groupId>com.atguigu</groupId>
            <artifactId>service_api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
</project>
```



### 4.2.5service_acl

创建service_acl模块，继承service父工程，剪切web_admin的dao层和service层实现类及resources目录中的相关资源

#### 4.2.5.1pom.xml

修改pom.xml文件，加入相关依赖，打包方式设置为war包

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>service</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>service_acl</artifactId>
    <packaging>war</packaging>

    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.15.v20190215</version>
                <configuration>
                    <!-- 如果检测到项目有更改则自动热部署，每隔n秒扫描一次。默认为0，即不扫描-->
                    <scanIntervalSeconds>10</scanIntervalSeconds>
                    <webAppConfig>
                        <!--指定web项目的根路径，默认为/ -->
                        <contextPath>/</contextPath>
                    </webAppConfig>
                    <httpConnector>
                        <!--端口号，默认 8080-->
                        <port>7001</port>
                    </httpConnector>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



#### 4.2.5.2添加Web结构

打包方式设置为war包后，在模块结构上按指定路径添加web.xml文件



#### 4.2.5.3dao层

从web_admin模块中剪切的dao层文件，com.atguigu.dao目录下的文件，不做任何修改



#### 4.2.5.4service层

从web_admin模块中剪切的service层实现类，com.atguigu.service.impl目录下的接口实现类

修改AdminControllerImpl、RoleControlleImplr，并添加Dubbo服务的相关注解@DubboService⚠️

AdminServiceImpl文件：

```java
package com.atguigu.service.impl;

import com.atguigu.dao.AdminDao;
import com.atguigu.dao.BaseDao;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@DubboService
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public BaseDao<Admin> getEntityDao() {
        return adminDao;
    }

}
```

RoleServiceImpl：

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.RoleDao;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RoleServiceImpl
 */
@DubboService
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public BaseDao<Role> getEntityDao() {
        return roleDao;
    }

}
```



#### 4.2.5.5Resources目录结构

从web_admin复制的resources中和dao层service层相关的目录结构：

- mapper
  - AdminDao.xml（未改动）
  - RoleDao.xml（未改动）
- spring
  - spring-registry.xml（未改动）
  - spring-dao.xml（有改动）⚠️
  - spring-service.xml（未改动）
- db.properties（未改动）
- logback.xml（未改动）
- mybatis-config.xml（未改动）



#### 4.2.5.6spring-registry.xml

配置服务提供者的相关信息：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 定义服务提供者名称名称 -->
    <dubbo:application name="service_acl"/>

    <!--指定暴露服务的端口，如果不指定默认为20880 -->
    <dubbo:protocol name="dubbo" port="20881"/>

    <!--指定服务注册中心地址-->
    <dubbo:registry address="zookeeper://localhost:2181"/>

    <!--批量扫描，发布服务-->
    <dubbo:annotation package="com.atguigu"/>
</beans>
```



#### 4.2.5.7web.xml

加载spring容器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!-- 加载spring容器 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <!-- 使用通配符，一次性加载spring目录下的所有spring容器 -->
        <param-value>classpath:spring/spring-*.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
</web-app>
```



### 4.2.6web

创建web模块，删除src目录，引入依赖，负责所有子模块的聚合，打包方式设置为pom，作为所有前端项目的父工程

将web_admin模块剪切到该模块目录下，并配置为该模块的子模块，同时去掉父工程shf_parent中的聚合信息

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>shf_parent</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>web</artifactId>
    <packaging>pom</packaging>

    <modules>
        <module>web_admin</module>
    </modules>

    <dependencies>
        <dependency>
            <groupId>com.atguigu</groupId>
            <artifactId>service_api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

</project>
```



### 4.2.7web_admin

将web_admin目录移动到web目录内

删除shf-parent模块pom.xml文件的<module>web-admin</module>标签，改模块的聚合已移动到web模块

#### 4.2.7.1pom.xml

修改了父模块为web，删除了依赖（父模块已引入依赖）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>web</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <!--version、groupId与父模块相同，可省略只写artifactId-->
    <artifactId>web_admin</artifactId>

    <!--设置打包方式为war包-->
    <packaging>war</packaging>


    <!--设置服务器jetty的参数，以Maven的插件形式存在-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.15.v20190215</version>
                <configuration>
                    <!-- 如果检测到项目有更改则自动热部署，每隔n秒扫描一次。默认为0，即不扫描-->
                    <scanIntervalSeconds>10</scanIntervalSeconds>
                    <webAppConfig>
                        <!--指定web项目的根路径，默认为/    设置上下文路径-->
                        <contextPath>/</contextPath>
                    </webAppConfig>
                    <httpConnector>
                        <!--设置端口号，默认 8080-->
                        <port>8000</port>
                    </httpConnector>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



#### 4.2.7.2改造controller层

修改AdminController、RoleController中的service层对象注入标签，再使用service时需要向注册中心获取

RoleController

```java
package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/role";

    @DubboReference
    private RoleService roleService;

    /**
     * 处理/请求，跳转到index页，搜索处理、分页处理
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String,Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Role> page = roleService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        return PAGE_INDEX;
    }

    /**
     * 处理/create请求，跳转到添加资源页面
     */
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * 处理/save请求，执行添加资源操作
     */
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/edit/id请求，跳转到修改资源页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Role role = roleService.getById(id);
        map.put("role",role);
        return PAGE_EDIT;
    }

    /**
     * 处理/update请求，执行资源修改操作
     */
    @RequestMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/delete/id请求，执行资源删除操作
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

}
```

AdminController

```java
package com.atguigu.controller;


import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * TODD
 * @AllClassName: com.atguigu.controller.AdminController
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private final static String PAGE_INDEX = "admin/index";
    private final static String PAGE_CREATE = "admin/create";
    private final static String PAGE_EDIT = "admin/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/admin";

    @DubboReference
    private AdminService adminService;

    /**
     *  处理/请求，跳转到index页，搜索处理、分页处理
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String,Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Admin> page = adminService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        return PAGE_INDEX;
    }

    /**
     * 处理/create请求，跳转到添加资源页面
     */
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * 处理/save请求，执行添加资源操作
     */
    @RequestMapping("/save")
    public String save(Admin admin) {
        adminService.insert(admin);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/edit/id请求，跳转到修改资源页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Admin admin = adminService.getById(id);
        map.put("admin",admin);
        return PAGE_EDIT;
    }

    /**
     * 处理/update请求，执行资源修改操作
     */
    @RequestMapping(value="/update")
    public String update(Admin admin) {
        adminService.update(admin);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/delete/id请求，执行资源删除操作
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

}
```



#### 4.2.7.3删除资源

删除service与dao层包下的代码，删除mapper文件夹，删除mybatis-config.xml文件

resources目录结构：

- spring

  - spring-registry.xml（新增）⚠️
  - spring-mvc.xml（修改）⚠️

- logback.xml（未修改）

  

#### 4.2.7.4spring-registry.xml

配置服务消费者的相关信息，注意不要设置扫描包，在spring-mvc.xml文件内统一设置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--这里不需要设置扫描包，springMvc中已经设置了，不然会冲突⚠️-->
    <!--<context:component-scan base-package="com.atguigu.controller"/>-->

    <!--配置dubbo应用程序名称-->
    <dubbo:application name="web_admin"></dubbo:application>

    <!--注册配置中心-->
    <dubbo:registry address="zookeeper://localhost:2181"></dubbo:registry>

    <!--启动时候不检查 设置连接超时时间-->
    <dubbo:consumer check="false" timeout="600000"></dubbo:consumer>
</beans>
```



#### 4.2.7.5spring-mvc.xml

使用<import resource="spring-registry.xml"/>导入服务消费者配置文件，一块注册到IoC容器中。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--将spring-registry.xml引入到spring-mvc中，相当于把内容复制到该文件内-->
    <!--其实可以将spring-registry.xml内容都写在该文件内，不过不方便维护-->
    <import resource="spring-registry.xml"/>

    <!--controller包的注解扫描-->
    <context:component-scan base-package="com.atguigu.controller"/>

    <!--配置视图解析器 ：Thymeleaf  SpringBoot之后是不需要自己配置-->
    <bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver" id="viewResolver">
        <!--配置字符集属性-->
        <property name="characterEncoding" value="UTF-8"></property>
        <!--配置模板引擎属性-->
        <property name="templateEngine">
            <!--配置内部bean-->
            <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                <!--配置模块的解析器属性-->
                <property name="templateResolver">
                    <!--配置内部bean-->
                    <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                        <!--配置前缀  ★-->
                        <property name="prefix" value="/WEB-INF/pages/"></property>
                        <!--配置后缀  ★-->
                        <property name="suffix" value=".html"></property>
                        <!--配置字符集-->
                        <property name="characterEncoding" value="UTF-8"></property>
                    </bean>
                </property>
            </bean>
        </property>
    </bean>

    <!--mvc的驱动设置，fastjson转换器的添加-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <!-- 配置Fastjson支持 -->
            <bean class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                        <value>application/json</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!--静态资源访问-->
    <mvc:default-servlet-handler/>

    <!--配置首页访问-->
    <mvc:view-controller path="index.html" view-name="frame/index"/>
    <mvc:view-controller path="main" view-name="frame/main"/>

</beans>
```



### 4.2.8Web.xml

web.xml中取消加载spring目录下的xml文件，否则会加载重复

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!-- 解决post乱码 添加字符编码过滤器 -->
    <filter>
        <filter-name>encode</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceRequestEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encode</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!-- 配置SpringMVC框架前端控制器 -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
    
</web-app>
```



### 4.2.9测试

启动`service-acl`与`web-admin`模块进行测试

服务提供者：service-acl

服务消费者：web-admin





# 5 二手房管理

## 5.1数据字典

### 5.1.1搭建service-house模块

在service创建service-house模块，以service为父工程，搭建方式与service-acl一致，内容相同，但是需要修改pom.xml文件中服务器的端口号防止冲突，再修改spring-register.xml文件中服务提供者的名称和端口。最后不要忘记配置web.xml⚠️

#### 5.1.1.1pom.xml

打包方式设置为war包后，在模块结构上按指定路径添加web.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>service</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>service_house</artifactId>
    <packaging>war</packaging>

    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.15.v20190215</version>
                <configuration>
                    <!-- 如果检测到项目有更改则自动热部署，每隔n秒扫描一次。默认为0，即不扫描-->
                    <scanIntervalSeconds>10</scanIntervalSeconds>
                    <webAppConfig>
                        <!--指定web项目的根路径，默认为/ -->
                        <contextPath>/</contextPath>
                    </webAppConfig>
                    <httpConnector>
                        <!--端口号，默认 8080-->
                        <port>7002</port>
                    </httpConnector>
                </configuration>
            </plugin>
        </plugins>
    </build>


</project>
```



#### 5.1.1.2spring-register.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 定义服务提供者名称名称 -->
    <dubbo:application name="service_hrs"/>

    <!--指定暴露服务的端口，如果不指定默认为20880 -->
    <dubbo:protocol name="dubbo" port="20882"/>

    <!--指定服务注册中心地址-->
    <dubbo:registry address="zookeeper://localhost:2181"/>

    <!--批量扫描，发布服务-->
    <dubbo:annotation package="com.atguigu"/>
</beans>
```



### 5.1.2准备web资源

前端页面是基于zTree组件做的树形图，每次展开一个节点，即发起一次查询该节点的子节点异步请求，以此来展示字典信息。

#### 5.1.2.1引入zTree组件

将资源文件中的zTree_v3文件夹复制到web_admin模块static/js/plugins目录下，该插件依赖Jquery，但head文件已引用

文档地址:http://www.treejs.cn/v3/demo.php#_108

该插件支持的渲染数据格式为：

- [{ id:1, isParent:true, name:"文件名称"},{ id:2, isParent:true, name:"文件名称"}.......]
  - id：为点击该节点发起查询其子节点的参数，即parent_id
  - isParent：判断是否为父节点，若是true展示图标为文件夹，若false则图标为文件
  - name：页面中渲染展示的节点名称

​		

#### 5.1.2.2创建index页面

在web_admin模块新增页面：pages/dict/index.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>

<link rel="stylesheet" th:href="@{/static/js/plugins/zTree_v3/zTreeStyle.css}" type="text/css">
<script type="text/javascript" th:src="@{/static/js/plugins/zTree_v3/jquery.ztree.core.js}"></script>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">

            <div class="zTreeDemoBackground left">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
    </div>
</div>
<script th:inline="javascript">
    $(function(){
        // 文档地址:http://www.treejs.cn/v3/demo.php#_108
        var setting = {
            async: {
                enable: true,
                url:"/dict/findZnodes",
                type:"get",
                autoParam:["id"],
                dataFilter: filter
            }
        };

        function filter(treeId, parentNode, childNodes) {
            childNodes = childNodes.data
            if (!childNodes) return null;
            for (var i=0, l=childNodes.length; i<l; i++) {
                childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            }
            return childNodes;
        }

        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting);
        });
    });
</script>
</body>
</html>
```



#### 5.1.2.3添加导航

在frame/index文件中添加导航

```html
<li>
   <a href="#">
      <i class="fa fa-home"></i>
      <span class="nav-label">二手房管理</span>
      <span class="fa arrow"></span>
   </a>
   <ul class="nav nav-second-level">
      <li>
         <a class="J_menuItem" th:href="@{/dict}" data-index="0">数据字典</a>
      </li>
   </ul>
</li>
```



### 5.1.3dubbo服务端接口

在service_api模块新增serviceAPI：DictService

```java
package com.atguigu.service;

import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.DictService
 */
public interface DictService {


    /**
     * 通过id获取所有子节点
     */
    List<Map<String,Object>> getNodesByParentId(Long id);

}
```



### 5.1.4dubbo服务提供者

#### 5.1.4.1dao层

DictDao

```java
package com.atguigu.dao;

import com.atguigu.entity.Dict;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.DictDao
 */
public interface DictDao {
    /**
     * 通过parent_id获取所有子节点
     */
    List findListByParentId(Long id);

    /**
     * 通过parent_id判断是否为父节点
     */
    Integer isParentNode(Long id);
}
```

DictMapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.DictDao">

    <!-- 用于select查询公用抽取的列 -->
    <sql id="columns">
        select id,parent_id,name,dict_code,create_time,update_time,is_deleted from  hse_dict
    </sql>

    <!--通过parent_id获取所有子节点-->
    <select id="findListByParentId" resultType="dict">
        <include refid="columns" />
        where parent_id = #{parentId}
    </select>

</mapper>
```



#### 5.1.4.3service层

DictServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.DictDao;
import com.atguigu.entity.Dict;
import com.atguigu.service.DictService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.DictServiceImpl
 */

@DubboService
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    /**
     * 通过id获取所有子节点
     */
    @Override
    public List<Map<String, Object>> getNodesByParentId(Long id) {

        //获取所有子节点
        List<Dict> list = dictDao.findListByParentId(id);

        //创建处理后的list集合
        List<Map<String, Object>> result = new ArrayList<>();

        //遍历子节点，转换为合适的样式[{ id:'',name:'',isParent:true}...]
        for (Dict dict : list) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",dict.getId());
            map.put("name",dict.getName());

            //调用dao层查询本次循环的节点是否为父节点
            map.put("isParent",dictDao.isParentNode(dict.getId()) > 0 ? true : false);

            //存储每次转换的结果
            result.add(map);
        }

        return result;
    }

}
```



### 5.1.5dubbo服务消费者

在web_admin模块新增Controller：DictController

```java
package com.atguigu.controller;

import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.DictController
 */
@Controller
@RequestMapping("/dict")
public class DictController {
    private final static String PAGE_INDEX = "dict/index";

    @DubboReference
    private DictService dictService;

    /**
     * 访问/dict路径时返回dict/index页面
     */
    @RequestMapping
    public String index() {
        return PAGE_INDEX;
    }

    /**
     * 通过异步获取子节点渲染到页面上
     * 返回的结果是封装后的异步请求统一返回值模版Result
     * 第一次进入页面时，id值为空，通过defaultValue给参数默认值，这一步很关键！⚠️
     */
    @RequestMapping("/findZnodes")
    @ResponseBody
    public Result findByParentId(@RequestParam(value = "id", defaultValue = "0") Long id) {
        List<Map<String, Object>> result = dictService.getNodesByParentId(id);
        return Result.ok(result);
    }
}
```



## 5.2小区管理

### 5.2.1准备web资源

#### 5.2.1.1创建index页面

在web_admin模块创建页面：pages/community/index.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">

<head th:include="common/head :: head"></head>

<body class="gray-bg">
<form id="ec" th:action="@{/community}" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <table class="table form-table margin-bottom10">
                            <tr>
                                <td>
                                    <input type="text" name="name" th:value="${#maps.containsKey(filters, 'name')} ? ${filters.name} : ''" placeholder="小区名称" class="input-sm form-control"/>
                                </td>
                                <td>
                                    <select name="areaId" id="areaId" class="form-control">
                                        <option value="">-请选择区域-</option>
                                        <option th:each="item,it : ${areaList}" th:text="${item.name}" th:value="${item.id}" th:selected="${#maps.containsKey(filters, 'areaId')} ? ${item.id } eq  ${filters.areaId } : false">-选择区域-</option>
                                    </select>
                                </td>
                                <td>
                                    <select name="plateId" id="plateId" class="form-control">
                                        <option value="">-请选择-</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                        <div>
                            <button type="submit" class="btn btn-sm btn-primary"> 搜索</button>
                            <button type="button" class="btn btn-sm btn-primary create" sec:authorize="hasAuthority('community.create')"> 新增</button>
                            <button type="button" id="loading-example-btn" onclick="javascript:window.location.reload();" class="btn btn-white btn-sm">刷新</button>
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>小区名称</th>
                                <th>区域</th>
                                <th>板块</th>
                                <th>详细地址</th>
                                <th>建筑年代</th>
                                <th>创建时间</th>
                                <th>操作 </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="gradeX" th:each="item,it: ${page.list}">
                                <td class="text-center" th:text="${it.count}">11</td>
                                <td th:text="${item.name}">22</td>
                                <td th:text="${item.areaName}">33</td>
                                <td th:text="${item.plateName}">22</td>
                                <td th:text="${item.address}">22</td>
                                <td th:text="${item.buildYears}">22</td>
                                <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}" >33</td>
                                <td class="text-center">
                                    <a class="edit" th:attr="data-id=${item.id}" sec:authorize="hasAuthority('community.edit')">修改</a>
                                    <a class="delete" th:attr="data-id=${item.id}" sec:authorize="hasAuthority('community.delete')">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div class="row" th:include="common/pagination :: pagination"></div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<script th:inline="javascript">
    $(function(){
        $(".create").on("click",function () {
            opt.openWin('/community/create','新增',630,430)
        });
        $(".edit").on("click",function () {
            var id = $(this).attr("data-id");
            opt.openWin('/community/edit/' + id,'修改',580,430);
        });
        $(".delete").on("click",function(){
            var id = $(this).attr("data-id");
            opt.confirm('/community/delete/'+id);
        });

        $("#areaId").bind("change",function() {
            var areaId = $("#areaId").val();
            if(areaId == '') return
            $("#plateId").empty();
            $.get("/dict/findListByParentId/" + areaId,function(response) {
                $("<option value=''>-请选择板块-</option>").appendTo("#plateId");
                var res = JSON.parse(response)
                $.each(res.data, function(i,item) {
                    var plateId = [[${#maps.containsKey(filters, 'plateId')} ? ${filters.plateId} : '']];
                    if(item.id == plateId) {
                        $("<option></option>").val(item.id).text(item.name).attr('selected', 'true').appendTo("#plateId");
                    } else {
                        $("<option></option>").val(item.id).text(item.name).appendTo("#plateId");
                    }
                });
            });
        });
        // 触发 select 元素的 change 事件：
        $("#areaId").trigger("change");
    });
</script>
</body>
</html>
```



#### 5.2.1.2创建create页面

在web_admin模块创建页面：pages/community/create.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<script type="text/javascript">
    $(function(){
        $("#areaId").bind("change",function() {
            var areaId = $("#areaId").val();
            $("#plateId").empty();
            $.get("/dict/findListByParentId/" + areaId,function(response) {
                var res = JSON.parse(response)
                $.each(res.data, function(i,item) {
                    $("<option></option>").val(item.id).text(item.name).appendTo("#plateId");
                });
            });
        });
    });
</script>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/community/save}" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">小区名称：</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" id="name" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">描述：</label>
                    <div class="col-sm-10">
                        <input type="text" name="description" id="description" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">区域：</label>
                    <div class="col-sm-10">
                        <select name="areaId" id="areaId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${areaList}" th:text="${item.name}" th:value="${item.id}">-选择区域-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">板块：</label>
                    <div class="col-sm-10">
                        <select name="plateId" id="plateId" class="form-control">
                            <option value="">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">详细地址：</label>
                    <div class="col-sm-10">
                        <input type="text" name="address" id="address" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">建筑年代：</label>
                    <div class="col-sm-10">
                        <input type="text" name="buildYears" id="buildYears" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">物业价格(元/平)：</label>
                    <div class="col-sm-10">
                        <input type="text" name="propertyPrice" id="propertyPrice" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">物业公司：</label>
                    <div class="col-sm-10">
                        <input type="text" name="propertyCompany" id="propertyCompany" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">开发商：</label>
                    <div class="col-sm-10">
                        <input type="text" name="developer" id="developer" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">楼栋数：</label>
                    <div class="col-sm-10">
                        <input type="text" name="buildNum" id="buildNum" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">房屋数：</label>
                    <div class="col-sm-10">
                        <input type="text" name="houseNum" id="houseNum" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">均价(万元/平)：</label>
                    <div class="col-sm-10">
                        <input type="text" name="averagePrice" id="averagePrice" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



#### 5.2.1.3创建edit页面

在web_admin模块创建页面：pages/community/edit.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<script type="text/javascript">
    $(function(){
        $("#areaId").bind("change",function() {
            var areaId = $("#areaId").val();
            if(areaId == '') return
            $("#plateId").empty();
            $.get("/dict/findListByParentId/" + areaId,function(response) {
                var res = JSON.parse(response)
                $.each(res.data, function(i,item) {
                    var plateId = [[${community.plateId}]];
                    if(item.id == plateId) {
                        $("<option></option>").val(item.id).text(item.name).attr('selected', 'true').appendTo("#plateId");
                    } else {
                        $("<option></option>").val(item.id).text(item.name).appendTo("#plateId");
                    }
                });
            });
        });
        // 触发 select 元素的 change 事件：
        $("#areaId").trigger("change");
    });
</script>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/community/update}" method="post" class="form-horizontal">
                <input type="hidden" name="id" th:value="${community.id}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">小区名称：</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" id="name" th:value="${community.name}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">描述：</label>
                    <div class="col-sm-10">
                        <input type="text" name="description" id="description" th:value="${community.description}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">区域：</label>
                    <div class="col-sm-10">
                        <select name="areaId" id="areaId" class="form-control">
                            <option value="">-选择区域-</option>
                            <option th:each="item,it : ${areaList}" th:text="${item.name}" th:value="${item.id}" th:selected="${item.id} eq ${community.areaId}">-选择区域-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">板块：</label>
                    <div class="col-sm-10">
                        <select name="plateId" id="plateId" class="form-control">
                            <option value="">-选择板块-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">详细地址：</label>
                    <div class="col-sm-10">
                        <input type="text" name="address" id="address" th:value="${community.address}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">建筑年代：</label>
                    <div class="col-sm-10">
                        <input type="text" name="buildYears" id="buildYears" th:value="${community.buildYears}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">物业价格(元/平)：</label>
                    <div class="col-sm-10">
                        <input type="text" name="propertyPrice" id="propertyPrice" th:value="${community.propertyPrice}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">物业公司：</label>
                    <div class="col-sm-10">
                        <input type="text" name="propertyCompany" id="propertyCompany" th:value="${community.propertyCompany}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">开发商：</label>
                    <div class="col-sm-10">
                        <input type="text" name="developer" id="developer" th:value="${community.developer}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">楼栋数：</label>
                    <div class="col-sm-10">
                        <input type="text" name="buildNum" id="buildNum" th:value="${community.buildNum}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">房屋数：</label>
                    <div class="col-sm-10">
                        <input type="text" name="houseNum" id="houseNum" th:value="${community.houseNum}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">均价(万元/平)：</label>
                    <div class="col-sm-10">
                        <input type="text" name="averagePrice" id="averagePrice" th:value="${community.averagePrice}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



#### 5.2.1.4添加导航

在frame/index文件中添加导航

```html
<li>
   <a class="J_menuItem" th:href="@{/community}" data-index="0">小区管理</a>
</li>
```



### 5.2.2dubobo服务端接口

DictService

```java
/**
 * 为Community下拉框服务：根据编码获取子节点数据列表
 */
List<Dict> findListByDictCode(String dictCode);


/**
 * 为Community下拉框服务：根据上级id获取子节点数据列表
 */
List<Dict> findListByParentId(Long parentId);
```

在service_api模块新增serviceAPI：

```java
package com.atguigu.service;

import com.atguigu.entity.Community;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.ComunityService
 */
public interface CommunityService extends BaseService<Community> {
}
```



### 5.2.3dubobo服务提供者

#### 5.2.3.1dao层

DictDao新增内容

```java
    /**
     * 根据编码获取实例，为下拉列表赋值
     */
    Dict getByDictCode(String dictCode);

    /**
     * 通过id获取name，通过id获取区域和板块的名字
     */
    String getNameById(Long id);
```

DictMapper新增内容

```xml
    <!--判断该节点是否是父节点-->
    <select id="isParentNode" resultType="integer">
        select count(*) from hse_dict
        where parent_id = #{parentId}
    </select>

    <!--根据编码获取实例-->
    <select id="getByDictCode" resultType="Dict">
        <include refid="columns" />
        where
        dict_code = #{dictCode}
    </select>

    <!--通过id获取name-->
    <select id="getNameById" resultType="string">
        select name from  hse_dict
        where
        id = #{id}
    </select>
```

 CommunityDao

```java
package com.atguigu.dao;

import com.atguigu.entity.Community;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.CommunityDao
 */
public interface CommunityDao extends BaseDao<Community>{
}
```

CommunityMapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.CommunityDao">

    <!-- 用于select查询公用抽取的列 -->
    <sql id="columns">
        select
        id,name,description,province_id,city_id,area_id,plate_id,address,longitude,latitude,build_years,property_price,property_company,developer,build_num,house_num,average_price,create_time,update_time,is_deleted
        from hse_community
    </sql>

    <!--首次进入index页面及搜索结果的展示数据-->
    <select id="findPage" resultType="Community">
        <include refid="columns"/>

        <where>
            <if test="name != null and name != ''">
                and name like CONCAT('%',#{name},'%')
            </if>
            <if test="areaId != null and areaId != ''">
                and area_id = #{areaId}
            </if>
            <if test="plateId != null and plateId != ''">
                and plate_id = #{plateId}
            </if>
            and is_deleted = 0
        </where>
        order by id desc
    </select>

    <!--新增一个实体-->
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        insert into hse_community (
        id ,
        name ,
        description ,
        province_id ,
        city_id ,
        area_id ,
        plate_id ,
        address ,
        longitude ,
        latitude ,
        build_years ,
        property_price ,
        property_company ,
        developer ,
        build_num ,
        house_num ,
        average_price
        ) values (
        #{id} ,
        #{name} ,
        #{description} ,
        #{provinceId} ,
        #{cityId} ,
        #{areaId} ,
        #{plateId} ,
        #{address} ,
        #{longitude} ,
        #{latitude} ,
        #{buildYears} ,
        #{propertyPrice} ,
        #{propertyCompany} ,
        #{developer} ,
        #{buildNum} ,
        #{houseNum} ,
        #{averagePrice}
        )
    </insert>

    <!--删除实体，实际上是修改is_deleted值-->
    <update id="delete">
        update hse_community set
        update_time = now(),
        is_deleted = 1
        where
        id = #{id}
    </update>

    <!--根据id查询实体信息，修改回显-->
    <select id="getById" resultType="Community">
        <include refid="columns"/>
        where
        id = #{id}
    </select>

    <!--修改实体-->
    <update id="update">
        update hse_community
        <set>
            <if test="name!=null and name!=''">
                name=#{name},
            </if>
            <if test="description!=null and description!=''">
                description=#{description},
            </if>
            <if test="areaId!=null and areaId!=''">
                area_Id=#{areaId},
            </if>
            <if test="plateId!=null and plateId!=''">
                plate_Id=#{plateId},
            </if>
            <if test="address!=null and address!=''">
                address=#{address},
            </if>
            <if test="buildYears!=null and buildYears!=''">
                build_Years=#{buildYears},
            </if>
            <if test="propertyPrice!=null and propertyPrice!=''">
                property_Price=#{propertyPrice},
            </if>
            <if test="propertyCompany!=null and propertyCompany!=''">
                property_Company=#{propertyCompany},
            </if>
            <if test="developer!=null and developer!=''">
                developer=#{developer},
            </if>
            <if test="buildNum!=null and buildNum!=''">
                build_Num=#{buildNum},
            </if>
            <if test="houseNum!=null and houseNum!=''">
                house_Num=#{houseNum},
            </if>
            <if test="averagePrice!=null and averagePrice!=''">
                average_Price=#{averagePrice},
            </if>
        </set>
        where id=#{id}
    </update>

</mapper>
```



#### 5.2.3.2service层

DictServiceImpl新增内容

```java
    /**
     * 为下拉框服务：根据编码间接获取子节点列表
     */
    @Override
    public List<Dict> findListByDictCode(String dictCode) {
        //先根据编码获取对应的实例
        Dict dict = dictDao.getByDictCode(dictCode);
        if(null == dict) return null;
        //如果实例不为空，就获取当前实例的所有子节点
        return this.findListByParentId(dict.getId());
    }

    /**
     * 为下拉框服务：根据上级id获取子节点列表
     */
    @Override
    public List<Dict> findListByParentId(Long parentId) {
        return dictDao.findListByParentId(parentId);
    }
```

CommunityServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.CommunityDao;
import com.atguigu.dao.DictDao;
import com.atguigu.entity.Community;
import com.atguigu.service.CommunityService;
import com.atguigu.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.CommunityServiceImpl
 */
@DubboService
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {

    @Autowired
    private CommunityDao communityDao;

    @Autowired
    private DictDao dictDao;

    @Override
    public BaseDao<Community> getEntityDao() {
        return communityDao;
    }

    /**
     * 重写封装后的PageInfo方法，因为需要为区域和板块查询赋值
     * 虽然我们不知道区域和板块的名字，但是有他们的id，可以根据id去字典里查⚠️
     * 循环的每一个对象，都将查到的结果name，设置到自己areaName和plateName属性上
     * 使用两表联查也可以，但是复杂度相对较高
     */
    @Override
    public PageInfo<Community> findPage(Map<String, Object> filters) {
        //当前页数
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        //每页显示的记录条数
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 3);
        PageHelper.startPage(pageNum, pageSize);

        List<Community> list = communityDao.findPage(filters);
        for(Community community : list) {
            //获取区域和板块的名字
            String areaName = dictDao.getNameById(community.getAreaId());
            String plateName = dictDao.getNameById(community.getPlateId());
            //设置区域和板块的名字
            community.setAreaName(areaName);
            community.setPlateName(plateName);
        }
        return new PageInfo<Community>(list, 3);
    }
}
```



### 5.2.4dubobo服务消费者

DictController新增内容

```java
    /**
     * 为下拉列表服务，根据编码间接获取子节点列表，第一次选择
     */
    @RequestMapping(value = "findListByDictCode/{dictCode}")
    @ResponseBody
    public Result<List<Dict>> findListByDictCode(@PathVariable String dictCode) {
        List<Dict> list = dictService.findListByDictCode(dictCode);
        return Result.ok(list);
    }

    /**
     * 为下拉列表服务，根据上级id获取子节点列表，第二次选择
     */
    @RequestMapping(value = "findListByParentId/{parentId}")
    @ResponseBody
    public Result<List<Dict>> findListByParentId(@PathVariable Long parentId) {
        List<Dict> list = dictService.findListByParentId(parentId);
        return Result.ok(list);
    }
```

CommunityController

```java
package com.atguigu.controller;

import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.CommunityController
 */
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    private final static String LIST_ACTION = "redirect:/community";
    private final static String PAGE_INDEX = "community/index";
    private final static String PAGE_SHOW = "community/show";
    private final static String PAGE_CREATE = "community/create";
    private final static String PAGE_EDIT = "community/edit";
    private final static String PAGE_SUCCESS = "common/success";

    @DubboReference
    private CommunityService communityService;

    @DubboReference
    private DictService dictService;


    /**
     * 处理/community请求，进入index页面
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String, Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Community> page = communityService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        //将第一个选择区域的下拉框的值添加到请求域中⚠️
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("areaList", areaList);

        return PAGE_INDEX;
    }


    /**
     * 处理/community/create请求，进入新增页面
     */
    @RequestMapping("/create")
    public String create(Map map) {
        //将第一个选择区域的下拉框的值添加到请求域中⚠️
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("areaList", areaList);

        return PAGE_CREATE;
    }


    /**
     * 处理/community/save请求，保存新增
     */
    @RequestMapping("/save")
    public String save(Community community) {
        //使用bean作为入参，根据bean插入数据
        communityService.insert(community);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/community/edit/id请求，进入修改页面
     */
    @RequestMapping("/edit/{id}")
    public String edit(Map map, @PathVariable Long id) {
        //根据修改的id获取信息回显
        Community community = communityService.getById(id);
        map.put("community", community);

        //将第一个选择区域的下拉框的值添加到请求域中⚠️
        List<Dict> areaList = dictService.findListByDictCode("beijing");
        map.put("areaList", areaList);
        return PAGE_EDIT;
    }


    /**
     * 处理/community/update请求，保存修改
     */
    @RequestMapping(value = "/update")
    public String update(Community community) {
        //使用bean作为入参，根据bean修改数据
        communityService.update(community);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/community/delete/id请求，删除实例
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        communityService.delete(id);
        return LIST_ACTION;
    }

}5
```



## 5.3房源管理

### 5.3.1准备web资源

#### 5.3.1.1创建index页面

在web_admin模块创建页面：pages/house/index.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">

<head th:include="common/head :: head"></head>

<body class="gray-bg">
<form id="ec" th:action="@{/house}" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <table class="table form-table margin-bottom10">
                            <tr>
                                <td>
                                    <input type="text" name="name" th:value="${#maps.containsKey(filters, 'name')} ? ${filters.name} : ''" placeholder="房源名称" class="input-sm form-control"/>
                                </td>
                                <td>
                                    <select name="communityId" id="communityId" class="form-control">
                                        <option value="">-请选择小区-</option>
                                        <option th:each="item,it : ${communityList}" th:text="${item.name}" th:value="${item.id}" th:selected="${#maps.containsKey(filters, 'communityId')} ? ${item.id} eq ${filters.communityId} : false">-选择小区-</option>
                                    </select>
                                </td>
                                <td>
                                    <select name="houseTypeId" id="houseTypeId" class="form-control">
                                        <option value="">-请选择户型-</option>
                                        <option th:each="item,it : ${houseTypeList}" th:text="${item.name}" th:value="${item.id}" th:selected="${#maps.containsKey(filters, 'houseTypeId')} ? ${item.id} eq ${filters.houseTypeId} : false">-请选择-</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <select name="floorId" id="floorId" class="form-control">
                                        <option value="">-请选择楼层-</option>
                                        <option th:each="item,it : ${floorList}" th:text="${item.name}" th:value="${item.id}" th:selected="${#maps.containsKey(filters, 'floorId')} ? ${item.id} eq ${filters.floorId} : false">-请选择-</option>
                                    </select>
                                </td>
                                <td>
                                    <select name="buildStructureId" id="buildStructureId" class="form-control">
                                        <option value="">-请选择建筑结构-</option>
                                        <option th:each="item,it : ${buildStructureList}" th:text="${item.name}" th:value="${item.id}" th:selected="${#maps.containsKey(filters, 'buildStructureId')} ? ${item.id} eq ${filters.buildStructureId} : false">-请选择-</option>
                                    </select>
                                </td>
                                <td>
                                    <select name="directionId" id="directionId" class="form-control">
                                        <option value="">-请选择朝向-</option>
                                        <option th:each="item,it : ${directionList}" th:text="${item.name}" th:value="${item.id}" th:selected="${#maps.containsKey(filters, 'directionId')} ? ${item.id} eq ${filters.directionId} : false">-请选择-</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <select name="decorationId" id="decorationId" class="form-control">
                                        <option value="">-请选择装修情况-</option>
                                        <option th:each="item,it : ${decorationList}" th:text="${item.name}" th:value="${item.id}" th:selected="${#maps.containsKey(filters, 'decorationId')} ? ${item.id} eq ${filters.decorationId} : false">-请选择-</option>
                                    </select>
                                </td>
                                <td>
                                    <select name="houseUseId" id="houseUseId" class="form-control">
                                        <option value="">-请选择房屋用途-</option>
                                        <option th:each="item,it : ${houseUseList}" th:text="${item.name}" th:value="${item.id}" th:selected="${#maps.containsKey(filters, 'houseUseId')} ? ${item.id} eq ${filters.houseUseId} : false">-请选择-</option>
                                    </select>
                                </td>
                                <td>

                                </td>
                            </tr>
                        </table>
                        <div>
                            <button type="submit" class="btn btn-sm btn-primary"> 搜索</button>
                            <button type="button" class="btn btn-sm btn-primary create"> 新增</button>
                            <button type="button" id="loading-example-btn" onclick="javascript:window.location.reload();" class="btn btn-white btn-sm">刷新</button>
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>房源名称</th>
                                <th>总价：万元</th>
                                <th>单价：元/平米</th>
                                <th>建筑面积</th>
                                <th>套内面积</th>
                                <th>挂牌日期</th>
                                <th>上次交易日期</th>
                                <th>状态</th>
                                <th width="160">操作 </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="gradeX" th:each="item,it: ${page.list}">
                                <td class="text-center" th:text="${it.count}">11</td>
                                <td th:text="${item.name}">22</td>
                                <td th:text="${item.totalPrice}">22</td>
                                <td th:text="${item.unitPrice}">22</td>
                                <td th:text="${item.buildArea}">22</td>
                                <td th:text="${item.insideArea}">22</td>
                                <td th:text="${item.listingDateString}">22</td>
                                <td th:text="${item.lastTradeDateString}">22</td>
                                <td th:text="${item.status == 1 ? '已发布' : '未发布'}"></td>
                                <td class="text-center">
                                    <a class="edit" th:attr="data-id=${item.id}">修改</a>
                                    <a class="delete" th:attr="data-id=${item.id}">删除</a>
                                    <a class="publish" th:if="${item.status} eq '0'" th:attr="data-id=${item.id},data-status=1">发布</a>
                                    <a class="publish" th:if="${item.status} eq '1'" th:attr="data-id=${item.id},data-status=0">取消发布</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div class="row" th:include="common/pagination :: pagination"></div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<script th:inline="javascript">
    $(function(){
        $(".create").on("click",function () {
            opt.openWin('/house/create','新增',630,430)
        });
        $(".edit").on("click",function () {
            var id = $(this).attr("data-id");
            opt.openWin('/house/edit/' + id,'修改',630,430);
        });
        $(".delete").on("click",function(){
            var id = $(this).attr("data-id");
            opt.confirm('/house/delete/'+id);
        });
        $(".publish").on("click",function () {
            var id = $(this).attr("data-id");
            var status = $(this).attr("data-status");
            opt.confirm("/house/publish/" + id + "/" + status, "确定该操作吗？");
        });
    });
</script>
</body>
</html>
```



#### 5.3.1.2创建create页面

在web_admin模块创建页面：pages/house/create.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/house/save}" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">小区：</label>
                    <div class="col-sm-10">
                        <select name="communityId" id="communityId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${communityList}" th:text="${item.name}" th:value="${item.id}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">房源名称：</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" id="name" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">描述：</label>
                    <div class="col-sm-10">
                        <input type="text" name="description" id="description" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">总价：万元：</label>
                    <div class="col-sm-10">
                        <input type="text" name="totalPrice" id="totalPrice" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">单位价格：</label>
                    <div class="col-sm-10">
                        <input type="text" name="unitPrice" id="unitPrice" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">建筑面积：</label>
                    <div class="col-sm-10">
                        <input type="text" name="buildArea" id="buildArea" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">套内面积：</label>
                    <div class="col-sm-10">
                        <input type="text" name="insideArea" id="insideArea" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">户型：</label>
                    <div class="col-sm-10">
                        <select name="houseTypeId" id="houseTypeId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${houseTypeList}" th:text="${item.name}" th:value="${item.id}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">楼层：</label>
                    <div class="col-sm-10">
                        <select name="floorId" id="floorId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${floorList}" th:text="${item.name}" th:value="${item.id}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">建筑结构：</label>
                    <div class="col-sm-10">
                        <select name="buildStructureId" id="buildStructureId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${buildStructureList}" th:text="${item.name}" th:value="${item.id}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">朝向：</label>
                    <div class="col-sm-10">
                        <select name="directionId" id="directionId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${directionList}" th:text="${item.name}" th:value="${item.id}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">装修情况：</label>
                    <div class="col-sm-10">
                        <select name="decorationId" id="decorationId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${decorationList}" th:text="${item.name}" th:value="${item.id}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">房屋用途：</label>
                    <div class="col-sm-10">
                        <select name="houseUseId" id="houseUseId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${houseUseList}" th:text="${item.name}" th:value="${item.id}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">电梯比例：</label>
                    <div class="col-sm-10">
                        <input type="text" name="elevatorRatio" id="elevatorRatio" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">挂牌日期：</label>
                    <div class="col-sm-10">
                        <input name="listingDateString" type="date" class="form-control layer-date" placeholder="YYYY-MM-DD"/>
                        <label class="laydate-icon"></label>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">上次交易日期：</label>
                    <div class="col-sm-10">
                        <input name="lastTradeDateString" type="date" class="form-control layer-date" placeholder="YYYY-MM-DD"/>
                        <label class="laydate-icon"></label>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



#### 5.3.1.3创建edit页面

在web_admin模块创建页面：pages/house/edit.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/house/update}" method="post" class="form-horizontal">
                <input type="hidden" name="id" th:value="${house.id}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">小区：</label>
                    <div class="col-sm-10">
                        <select name="communityId" id="communityId" class="form-control">
                            <option value="">-选择小区-</option>
                            <option th:each="item,it : ${communityList}" th:text="${item.name}" th:value="${item.id}" th:selected="${item.id} eq ${house.communityId}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">房源名称：</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" id="name" th:value="${house.name}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">描述：</label>
                    <div class="col-sm-10">
                        <input type="text" name="description" id="description" th:value="${house.description}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">总价：万元：</label>
                    <div class="col-sm-10">
                        <input type="text" name="totalPrice" id="totalPrice" th:value="${house.totalPrice}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">单位价格：</label>
                    <div class="col-sm-10">
                        <input type="text" name="unitPrice" id="unitPrice" th:value="${house.unitPrice}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">建筑面积：</label>
                    <div class="col-sm-10">
                        <input type="text" name="buildArea" id="buildArea" th:value="${house.buildArea}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">套内面积：</label>
                    <div class="col-sm-10">
                        <input type="text" name="insideArea" id="insideArea" th:value="${house.insideArea}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">户型：</label>
                    <div class="col-sm-10">
                        <select name="houseTypeId" id="houseTypeId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${houseTypeList}" th:text="${item.name}" th:value="${item.id}" th:selected="${item.id} eq ${house.houseTypeId}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">楼层：</label>
                    <div class="col-sm-10">
                        <select name="floorId" id="floorId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${floorList}" th:text="${item.name}" th:value="${item.id}" th:selected="${item.id} eq ${house.floorId}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">建筑结构：</label>
                    <div class="col-sm-10">
                        <select name="buildStructureId" id="buildStructureId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${buildStructureList}" th:text="${item.name}" th:value="${item.id}" th:selected="${item.id} eq ${house.buildStructureId}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">朝向：</label>
                    <div class="col-sm-10">
                        <select name="directionId" id="directionId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${directionList}" th:text="${item.name}" th:value="${item.id}" th:selected="${item.id} eq ${house.directionId}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">装修情况：</label>
                    <div class="col-sm-10">
                        <select name="decorationId" id="decorationId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${decorationList}" th:text="${item.name}" th:value="${item.id}" th:selected="${item.id} eq ${house.decorationId}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">房屋用途：</label>
                    <div class="col-sm-10">
                        <select name="houseUseId" id="houseUseId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${houseUseList}" th:text="${item.name}" th:value="${item.id}" th:selected="${item.id} eq ${house.houseUseId}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">电梯比例：</label>
                    <div class="col-sm-10">
                        <input type="text" name="elevatorRatio" id="elevatorRatio" th:value="${house.elevatorRatio}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">挂牌日期：</label>
                    <div class="col-sm-10">
                        <input name="listingDateString" type="date" th:value="${house.listingDateString}" class="form-control layer-date" placeholder="YYYY-MM-DD"/>
                        <label class="laydate-icon"></label>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">上次交易日期：</label>
                    <div class="col-sm-10">
                        <input name="lastTradeDateString" type="date" th:value="${house.lastTradeDateString}" class="form-control layer-date" placeholder="YYYY-MM-DD" />
                        <label class="laydate-icon"></label>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



#### 5.3.1.4添加导航

在frame/index文件中添加导航

```html
<li>
   <a class="J_menuItem" th:href="@{/house}"data-index="0">房源管理</a>
</li>
```



### 5.3.2dubobo服务端接口

CommunityService新增内容

```java
/**
 * 获取全部小区列表，House下拉选择框要使用
 */
List<Community> findAll();
```

HouseService

```java
package com.atguigu.service;

import com.atguigu.entity.House;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.HouseService
 */
public interface HouseService extends BaseService<House>{

    /**
     * 更新发布状态
     */
    void publish(Long id, Integer status);

}

```



### 5.3.3dubobo服务提供者

#### 5.3.3.1dao层

CommunityDao新增内容

```java
/**
 * 获取全部小区列表，House下拉选择框要使用
 */
List<Community> findAll();
```

CommunityMapper新增内容

```xml
<!--查询所有小区，House下拉选择框要使用⚠️-->
<select id="findAll" resultType="Community">
    <include refid="columns"></include>
    where
    is_deleted = 0
    order by id desc
</select>
```

HouseDao

```java
package com.atguigu.dao;

import com.atguigu.entity.House;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.HouseDao
 */
public interface HouseDao extends BaseDao<House>{

    /**
     * 更新发布状态
     */
    void publish(@Param("id") Long id,@Param("status") Integer status);

}
```

HouseMapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.HouseDao">

    <!-- 用于select查询公用抽取的列 -->
    <sql id="columns">
        select
        id,community_id,name,description,total_price,unit_price,build_area,inside_area,house_type_id,floor_id,build_structure_id,direction_id,decoration_id,house_use_id,elevator_ratio,listing_date,last_trade_date,status,create_time,update_time,is_deleted
        from hse_house
    </sql>

    <!--index页及查询结果数据-->
    <select id="findPage" resultType="House">
        <include refid="columns"/>
        <where>
            <if test="communityId != null and communityId != ''">
                and community_id = #{communityId}
            </if>
            <if test="name != null and name != ''">
                and name like CONCAT('%',#{name},'%')
            </if>
            <if test="houseTypeId != null and houseTypeId != ''">
                and house_type_id = #{houseTypeId}
            </if>
            <if test="floorId != null and floorId != ''">
                and floor_id = #{floorId}
            </if>
            <if test="buildStructureId != null and buildStructureId != ''">
                and build_structure_id = #{buildStructureId}
            </if>
            <if test="directionId != null and directionId != ''">
                and direction_id = #{directionId}
            </if>
            <if test="decorationId != null and decorationId != ''">
                and decoration_id = #{decorationId}
            </if>
            <if test="houseUseId != null and houseUseId != ''">
                and house_use_id = #{houseUseId}
            </if>
            and is_deleted = 0
        </where>
        order by id desc
    </select>

    <!--新增实例-->
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        insert into hse_house (
        community_id ,
        name ,
        description ,
        total_price ,
        unit_price ,
        build_area ,
        inside_area ,
        house_type_id ,
        floor_id ,
        build_structure_id ,
        direction_id ,
        decoration_id ,
        house_use_id ,
        elevator_ratio ,
        listing_date ,
        last_trade_date
        ) values (
        #{communityId} ,
        #{name} ,
        #{description} ,
        #{totalPrice} ,
        #{unitPrice} ,
        #{buildArea} ,
        #{insideArea} ,
        #{houseTypeId} ,
        #{floorId} ,
        #{buildStructureId} ,
        #{directionId} ,
        #{decorationId} ,
        #{houseUseId} ,
        #{elevatorRatio} ,
        #{listingDate} ,
        #{lastTradeDate}
        )
    </insert>

    <!--删除实例-->
    <update id="delete">
        update hse_house set
        is_deleted = 1
        where
        id = #{id}
    </update>

    <!--根据id获取实例，修改回显-->
    <select id="getById" resultType="House">
        <include refid="columns" />
        where
        id = #{id}
    </select>

    <!--修改实例-->
    <update id="update">
        update hse_house
        <set>
            <if test="communityId!=null and communityId!=''">
                community_Id=#{communityId},
            </if>
            <if test="name!=null and name!=''">
                name=#{name},
            </if>
            <if test="description!=null and description!=''">
                description=#{description},
            </if>
            <if test="totalPrice!=null and totalPrice!=''">
                total_Price=#{totalPrice},
            </if>
            <if test="unitPrice!=null and unitPrice!=''">
                unit_Price=#{unitPrice},
            </if>
            <if test="buildArea!=null and buildArea!=''">
                build_Area=#{buildArea},
            </if>
            <if test="insideArea!=null and insideArea!=''">
                inside_Area=#{insideArea},
            </if>
            <if test="houseTypeId!=null and houseTypeId!=''">
                house_Type_Id=#{houseTypeId},
            </if>
            <if test="floorId!=null and floorId!=''">
                floor_Id=#{floorId},
            </if>
            <if test="buildStructureId!=null and buildStructureId!=''">
                build_Structure_Id=#{buildStructureId},
            </if>
            <if test="directionId!=null and directionId!=''">
                direction_Id=#{directionId},
            </if>
            <if test="decorationId!=null and decorationId!=''">
                decoration_Id=#{decorationId},
            </if>
            <if test="houseUseId!=null and houseUseId!=''">
                house_Use_Id=#{houseUseId},
            </if>
            <if test="elevatorRatio!=null and elevatorRatio!=''">
                elevator_Ratio=#{elevatorRatio},
            </if>
            <if test="listingDateString!=null and listingDateString!=''">
                listing_Date=#{listingDateString},
            </if>
            <if test="lastTradeDateString!=null and lastTradeDateString!=''">
                last_Trade_Date=#{lastTradeDateString},
            </if>
        </set>
        where id=#{id}
    </update>

    <!--更新发布状态-->
    <update id="publish">
        update hse_house set status=#{status} where id=#{id}
    </update>

</mapper>
```



#### 5.3.3.2service层

CommunityServiceImpl新增内容

```java
/**
 * 获取全部小区列表，House下拉选择框要使用
 */
@Override
public List<Community> findAll() {
    return communityDao.findAll();
}
```

HouseServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.HouseDao;
import com.atguigu.entity.House;
import com.atguigu.service.HouseService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.HouseServiceImpl
 */
@DubboService
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {

    @Autowired
    private HouseDao houseDao;

    @Override
    public BaseDao<House> getEntityDao() {
        return houseDao;
    }
    

    /**
     * 更新发布状态
     */
    @Override
    public void publish(Long id, Integer status) {
        houseDao.publish(id,status);
    }
}
```



### 5.3.4dubobo服务消费者

HouseController

```java
package com.atguigu.controller;

import com.atguigu.entity.*;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.atguigu.service.HouseService;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.HouseController
 */
@Controller
@RequestMapping(value = "/house")
public class HouseController extends BaseController {


    @DubboReference
    private HouseService houseService;

    @DubboReference
    private DictService dictService;

    @DubboReference
    private CommunityService communityService;

    private final static String LIST_ACTION = "redirect:/house";
    private final static String PAGE_INDEX = "house/index";
    private final static String PAGE_SHOW = "house/show";
    private final static String PAGE_CREATE = "house/create";
    private final static String PAGE_EDIT = "house/edit";
    private final static String PAGE_SUCCESS = "common/success";


    /**
     * 处理/house请求路径，跳转到index页面，展示搜索结果
     */
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        Map<String, Object> filters = getFilters(request);
        PageInfo<House> page = houseService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索数据回显
        map.put("filters",filters);

        //为下拉框准备数据
        getSource(map);

        return PAGE_INDEX;
    }


    /**
     * 处理/create请求路径，进入新增页面
     */
    @RequestMapping("/create")
    public String create(Map map){
        //为下拉框准备数据
        getSource(map);
        return PAGE_CREATE;
    }


    /**
     * 处理/save请求路径，保存新增
     */
    @RequestMapping("/save")
    public String save(House house) {
        houseService.insert(house);

        return PAGE_SUCCESS;
    }


    /**
     * 处理/edit/id请求路径，到编辑修改页面
     */
    @RequestMapping("/edit/{houseId}")
    public String edit(@PathVariable Long houseId,Map map){
        //为下拉框准备数据
        getSource(map);
        House house = houseService.getById(houseId);
        map.put("house",house);
        return PAGE_EDIT;
    }



    /**
     * 处理/delete请求路径，删除数据
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        houseService.delete(id);
        return LIST_ACTION;
    }


    /**
     * 保处理/update请求路径
     */
    @RequestMapping("/update")
    public String update(House house){
        houseService.update(house);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/publish/id/status请求路径，发布房源
     */
    @RequestMapping("/publish/{id}/{status}")
    public String publish(@PathVariable Long id,@PathVariable Integer status) {
        houseService.publish(id, status);
        return LIST_ACTION;
    }


    /**
     * 封装所有的下拉选择框，多个页面都要使用
     */
    public void getSource(Map map){
        //需要所有的小区
        List<Community> communityList = communityService.findAll();
        //所有的户型
        List<Dict> houseTypeList = dictService.findListByDictCode("houseType");
        //所有的装修情况
        List<Dict> decorationList = dictService.findListByDictCode("decoration");
        //所有的楼层
        List<Dict> floorList = dictService.findListByDictCode("floor");
        //所有的朝向
        List<Dict> directionList = dictService.findListByDictCode("direction");
        //所有的建筑结构
        List<Dict> buildStructureList = dictService.findListByDictCode("buildStructure");
        //所有的房屋用途
        List<Dict> houseUseList = dictService.findListByDictCode("houseUse");

        map.put("communityList",communityList);
        map.put("houseTypeList",houseTypeList);
        map.put("decorationList",decorationList);
        map.put("floorList",floorList);
        map.put("directionList",directionList);
        map.put("buildStructureList",buildStructureList);
        map.put("houseUseList",houseUseList);
    }


    /**
     * 页面详情
     */
    @RequestMapping("/show/{id}")
    public String show(Map map,@PathVariable Long id) {
        //房源详细信息
        //ServiceImpl实现类中重写getById，有些属性只有id不满足要求，需要从字典中获取name⚠️
        House house = houseService.getById(id);
        map.put("house",house);

        //房源小区信息
        Community community = communityService.getById(house.getCommunityId());
        map.put("community",community);

        return PAGE_SHOW;
    }
}
```



## 5.4房源详情之介绍和图片

展示房源详细信息、房源小区信息、房源图片、房源房产图片

### 5.4.1准备web资源

#### 5.4.1.1创建show页面

house/show.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head th:include="common/head :: head"></head>
<body class="gray-bg">
<div class="row">
  <div class="col-sm-12">
    <div class="wrapper wrapper-content animated fadeInUp">
      <div class="ibox">
        <div class="ibox-content">
          <div class="row">
            <div class="col-sm-12">
              <div class="m-b-md">
                <a th:href="@{/house/show/{id}(id=${house.id})}" class="btn btn-white btn-xs pull-right">刷新</a>
                <a href="/house" class="btn btn-white btn-xs pull-right">返回</a>
                <h2 th:text="${house.name}">金色城市</h2>
              </div>
              <dl class="dl-horizontal">
                <dt>状态：</dt>
                <dd><span class="label label-primary" th:text="${house.status == 1 ? '已发布' : '未发布'}">进行中</span>
                </dd>
              </dl>
            </div>
          </div>

          <div class="row">
            <div class="col-sm-5">
              <dl class="dl-horizontal">
                <dt>总价：</dt><dd th:text="${house.totalPrice} + '万元'"></dd>
                <dt>单位价格：</dt><dd th:text="${house.unitPrice} + '元/平米'"></dd>
                <dt>建筑面积：</dt><dd th:text="${house.buildArea} + '平米'"></dd>
                <dt>套内面积：</dt><dd th:text="${house.insideArea} + '平米'"></dd>
                <dt>房屋户型：</dt><dd th:text="${house.houseTypeName}"></dd>
                <dt>所在楼层：</dt><dd th:text="${house.floorName}"></dd>
                <dt>建筑结构：</dt><dd th:text="${house.buildStructureName}"></dd>
                <dt>房屋朝向：</dt><dd th:text="${house.directionName}"></dd>
                <dt>装修情况：</dt><dd th:text="${house.decorationName}"></dd>
                <dt>房屋用途：</dt><dd th:text="${house.houseUseName}"></dd>
                <dt>梯户比例：</dt><dd th:text="${house.elevatorRatio}"></dd>
                <dt>挂牌时间：</dt><dd th:text="${house.listingDateString}"></dd>
                <dt>上次交易：</dt><dd th:text="${house.lastTradeDateString}"></dd>
              </dl>
            </div>
            <div class="col-sm-7" id="cluster_info">
              <dl class="dl-horizontal">
                <dt>小区名称：</dt><dd th:text="${community.name}"></dd>
                <dt>小区均价：</dt><dd th:text="${community.averagePrice}+'元/平米'">已上传房本照片</dd>
                <dt>区域：</dt><dd th:text="${community.areaName}">商品房</dd>
                <dt>板块：</dt><dd th:text="${community.plateName}"></dd>
                <dt>详细地址：</dt><dd th:text="${community.address}"></dd>
                <dt>建筑年代：</dt><dd th:text="${community.buildYears}">满五年</dd>
                <dt>物业价格：</dt><dd th:text="${community.propertyPrice}+'元/平米'">共有</dd>
                <dt>物业公司：</dt><dd th:text="${community.propertyCompany}">有抵押 19万元 中国银行四川分行 业主自还</dd>
                <dt>开发商：</dt><dd th:text="${community.developer}">已上传房本照片</dd>
                <dt>楼栋数：</dt><dd th:text="${community.buildNum}">已上传房本照片</dd>
                <dt>房屋数：</dt><dd th:text="${community.houseNum}">已上传房本照片</dd>
              </dl>
            </div>
          </div>

          <div class="row">
            <div class="col-sm-12">
              <div class="ibox float-e-margins">
                <div class="ibox-title">
                  <h3>房源图片信息</h3>
                  <a class="btn btn-xs btn-primary" id="upload1">上传房源图片</a>
                </div>
                <div class="ibox-content">
                  <a th:each="item,it : ${houseImage1List}" class="fancybox" >
                    <img alt="image" th:src="${item.imageUrl}"/>
                    <a th:attr="data-id=${item.id}" class="deleteImages">删除</a>
                  </a>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-12">
              <div class="ibox float-e-margins">
                <div class="ibox-title">
                  <h3>房产图片信息</h3>
                  <a class="btn btn-xs btn-primary" id="upload2">上传房产图片</a>
                </div>
                <div class="ibox-content">
                  <a th:each="item,it : ${houseImage2List}" class="fancybox" >
                    <img alt="image" th:src="${item.imageUrl}"/>
                    <a th:attr="data-id=${item.id}" class="deleteImages">删除</a>
                  </a>
                </div>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="panel blank-panel">
              <div class="pull-left" style="margin-top: 10px;">
                <a class="btn btn-xs btn-white"><h3>经纪人信息</h3></a>
                <a class="btn btn-xs btn-primary createBroker" sec:authorize="hasAuthority('house.editBroker')">添加</a>
              </div>
              <table class="table table-striped table-bordered table-hover dataTables-example">
                <thead>
                <tr>
                  <th>序号</th>
                  <th>经纪人头像</th>
                  <th>经纪人姓名</th>
                  <th>创建时间</th>
                  <th>操作 </th>
                </tr>
                </thead>
                <tbody>
                <tr class="gradeX" th:each="item,it : ${houseBrokerList}">
                  <td class="text-center" th:text="${it.count}">11</td>
                  <td>
                    <img th:src="${item.brokerHeadUrl}" style="width: 60px; height: 60px;">
                  </td>
                  <td th:text="${item.brokerName}">33</td>
                  <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}" >33</td>
                  <td class="text-center">
                    <a class="deleteBroker" th:attr="data-id=${item.id}">删除</a>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>

          <div class="row">
            <div class="panel blank-panel">
              <div class="pull-left" style="margin-top: 10px;">
                <a class="btn btn-xs btn-white"><h3>房东信息</h3></a>
                <a class="btn btn-xs btn-primary createUser" sec:authorize="hasAuthority('house.editUser')">添加</a>
              </div>
              <table class="table table-striped table-bordered table-hover dataTables-example">
                <thead>
                <tr>
                  <th>序号</th>
                  <th>姓名</th>
                  <th>手机号</th>
                  <th>性别</th>
                  <th>身份证号码</th>
                  <th>创建时间</th>
                  <th>操作 </th>
                </tr>
                </thead>
                <tbody>
                <tr class="gradeX" th:each="item,it : ${houseUserList}">
                  <td class="text-center" th:text="${it.count}">11</td>
                  <td th:text="${item.name}">33</td>
                  <td th:text="${item.phone}">33</td>
                  <td th:text="${item.sex == 1 ? '男' : '女'}">33</td>
                  <td th:text="${item.idNo}">33</td>
                  <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}" >33</td>
                  <td class="text-center">
                    <a class="editUser" th:attr="data-id=${item.id}">修改</a>
                    <a class="deleteUser" th:attr="data-id=${item.id}">删除</a>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
```



#### 5.4.1.2修改index页面

house/index.html中添加跳转按钮

```html
<a class="detail" th:attr="data-id=${item.id}" >详情</a>
```

house/index.html中添加js跳转事件

```html
<script>
        $(".detail").on("click",function () {
            var id = $(this).attr("data-id");
            window.location = "/house/show/" + id;
        });
</script>
```



### 5.4.2dubobo服务端接口

HouseImageService

```java
package com.atguigu.service;

import com.atguigu.entity.HouseImage;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.HouseImageService
 */
public interface HouseImageService extends BaseService<HouseImage>{

    /**
     * 通过house_id+type获取房产房源图片List
     */
    List<HouseImage> findImageByHouseIdAndType(Long houseId,Integer type);

}
```



### 5.4.3dubobo服务端提供者

房源对象虽然有全部的数据，但是获取的都是id，需要通过id拿到对应的name属性进行渲染，所以不得不修改getByid方法，返回name属性有值的对象

房源图片表是hse_house_image，字段type=0时为房源图片，字段type=1时为房产图片

#### 5.4.3.1dao层

HouseImageDao

```java
package com.atguigu.dao;

import com.atguigu.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.HouseImageDao
 */
public interface HouseImageDao extends BaseDao<HouseImage>{

    /**
     * 通过house_id+type获取房产房源图片List
     */
    List<HouseImage> findImageByHouseIdAndType(@Param("houseId") Long houseId,@Param("type") Integer type);

}
```

HouseImageMapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.HouseImageDao">

    <!--通过house_id+type获取房产房源图片List-->
    <select id="findImageByHouseIdAndType" resultType="houseImage">
        select * from hse_house_image where house_id=#{houseId} and type=#{type} and is_deleted=0
    </select>

</mapper>
```



#### 5.4.3.2service层

CommunityServiceImpl 添加内容

```java
/**
 * 重写getById，通过字典和id，为区域和版快信息赋值
 */
@Override
public Community getById(Serializable id) {
    Community community = communityDao.getById(id);

    //通过字典为区域赋值
    community.setPlateName(dictDao.getNameById(community.getPlateId()));
    //通过字典为区域赋值
    community.setAreaName(dictDao.getNameById(community.getAreaId()));

    return community;
}
```

HouseServiceImpl添加内容

```java
@Autowired
private DictDao dictDao;

/**
 * 重写getById，通过字典和id，为其房源详细信息赋值
 */
@Override
public House getById(Serializable id) {
    House house = houseDao.getById(id);
    
    //需要通过数据字典中数据的id值获取对应的name值
    //为户型name赋值
    house.setHouseTypeName(dictDao.getNameById(house.getHouseTypeId()));

    //为楼层name赋值
    house.setFloorName(dictDao.getNameById(house.getFloorId()));

    //为建筑结构name赋值
    house.setBuildStructureName(dictDao.getNameById(house.getBuildStructureId()));

    //为朝向name赋值
    house.setDirectionName(dictDao.getNameById(house.getDirectionId()));

    //为装修情况name赋值
    house.setDecorationName(dictDao.getNameById(house.getDecorationId()));

    //房屋用途name赋值
    house.setHouseUseName(dictDao.getNameById(house.getHouseUseId()));
    return house;
}
```

HouseImageServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.HouseImageDao;
import com.atguigu.entity.HouseImage;
import com.atguigu.service.HouseImageService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.HouseImageServiceImpl
 */
@DubboService
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage> implements HouseImageService {

    @Autowired
    private HouseImageDao houseImageDao;

    @Override
    public BaseDao<HouseImage> getEntityDao() {
        return houseImageDao;
    }

    /**
     * 通过house_id+type获取房产房源图片List
     */
    @Override
    public List<HouseImage> findImageByHouseIdAndType(Long houseId, Integer type) {
        return houseImageDao.findImageByHouseIdAndType(houseId,type);
    }

}
```



### 5.4.4dubobo服务端消费者

HouseController添加内容

```java
    @DubboReference
    private HouseImageService houseImageService;

    /**
     * 页面详情
     */
    @RequestMapping("/show/{id}")
    public String show(Map map,@PathVariable Long id) {
            //详情数据1：房源详细信息
            //ServiceImpl实现类中重写getById，有些属性只有id不满足要求，需要从字典中获取name⚠️
            House house = houseService.getById(id);
            map.put("house",house);

            //详情数据2：房源小区信息
            //ServiceImpl实现类中重写getById，有些属性只有id不满足要求，需要从字典中获取name⚠️
            Community community = communityService.getById(house.getCommunityId());
            map.put("community",community);

            //详情数据3：房源的房源图片，表：hse_house_image
            //房源和房产图片都在hse_house_image一张表上，通过type区分，1房源2房产
            //房源图片通过house_id+type1查询
            List<HouseImage> houseImage1List = houseImageService.findImageByHouseIdAndType(id,1);
            map.put("houseImage1List",houseImage1List);


            //详情数据4：房源的房产图片，表：hse_house_image
            //房产图片通过house_id+type2查询
            List<HouseImage> houseImage2List = houseImageService.findImageByHouseIdAndType(id,2);
            map.put("houseImage2List",houseImage2List);

        return PAGE_SHOW;
    }
```



## 5.5房源详情之经纪人

房源经纪人就是用户（acl_admin），一个房源（hse_house）可以有多个经纪人，一个经纪人可以有多个房源，所以需要中间表（hse_house_broker）

### 5.5.1dubobo服务端接口

HouseBrokerService

```java
package com.atguigu.service;

import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.HouseBrokerService
 */
public interface HouseBrokerService extends BaseService<HouseBroker>{

    /**
     * 两表联查，根据房源id返回查询的房源的经纪人信息和房源信息
     */
    List<HouseBroker> findBrokerByHouseId(Long houseId);

    /**
     * 查询不是该房源经纪人的其他所有经纪人，为房源添加新的经纪人
     */
    List<Admin> findHouseOtherBroker(Long houseId);
}
```



### 5.5.2dubobo服务端提供者

#### 5.5.2.1dao层

HouseBrokerDao

```java
package com.atguigu.dao;

import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.HouseBrokerDao
 */
public interface HouseBrokerDao extends BaseDao<HouseBroker> {

    /**
     * 两表联查，根据房源id返回查询的房源的经纪人信息和房源信息
     */
    List<HouseBroker> findBrokerByHouseId(Long houseId);


    /**
     * 查询不是该房源经纪人的其他所有经纪人，为房源添加新的经纪人
     */
    List<Admin> findHouseOtherBroker(List<Long> ids);

}
```

HouseBrokerMapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成RoleDao的全类名-->
<mapper namespace="com.atguigu.dao.HouseBrokerDao">

    <!--两表联查，根据房源id查询该房源的经纪人信息，全部房源信息+用户信息的头像url和姓名-->
    <select id="findBrokerByHouseId" resultType="houseBroker">
        SELECT hhb.*,aa.name brokerName,aa.head_url brokerHeadUrl
        FROM hse_house_broker hhb
        JOIN acl_admin aa
        ON hhb.broker_id=aa.id
        WHERE hhb.house_id=#{houseId}
        AND hhb.is_deleted=0
        AND aa.is_deleted=0
    </select>

    <!--查询不是该房源经纪人的其他所有经纪人，为房源添加新的经纪人-->
    <!--注意：ids这个集合有可能是没有长度的，因为这个房源最开始一个经纪人都没有⚠️-->
    <select id="findHouseOtherBroker" resultType="admin">
        SELECT * FROM acl_admin
        <where>
            <if test="list.size>0">
                id not in
                <!--循环查询参数，去掉所有已有的经纪人-->
                <foreach collection="list" open="(" close=")" separator="," item="id">
                    #{id}
                </foreach>
            </if>
            and is_deleted=0
        </where>
    </select>

    <!--新增房源的经纪人-->
    <insert id="insert">
        insert into hse_house_broker(house_id,broker_id)
        values(#{houseId},#{brokerId})
    </insert>

    <!--删除房源的经纪人-->
    <update id="delete">
        update hse_house_broker set is_deleted=1 where id=#{id}
    </update>
</mapper>
```



#### 5.5.2.2service层

HouseBrokerServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.HouseBrokerDao;
import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;
import com.atguigu.service.HouseBrokerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.HouseBrokerServiceImpl
 */
@DubboService
public class HouseBrokerServiceImpl extends BaseServiceImpl<HouseBroker> implements HouseBrokerService {

    @Autowired
    private HouseBrokerDao houseBrokerDao;

    @Override
    public BaseDao<HouseBroker> getEntityDao() {
        return houseBrokerDao;
    }


    /**
     * 两表联查，根据房源id返回查询的房源的经纪人信息和房源信息
     */
    @Override
    public List<HouseBroker> findBrokerByHouseId(Long houseId) {
        return houseBrokerDao.findBrokerByHouseId(houseId);
    }


    /**
     * 查询房源经纪人以外的其他所有经纪人，为房源添加新的经纪人
     */
    @Override
    public List<Admin> findHouseOtherBroker(Long houseId) {

        //第一步：查询到当前房源的所有经纪人信息
        List<HouseBroker> brokerList = houseBrokerDao.findBrokerByHouseId(houseId);

        //通过循环取出经纪人的id，放到ids集合中
        List<Long> ids=new ArrayList<>();
        for (HouseBroker houseBroker : brokerList) {
            ids.add(houseBroker.getBrokerId());
        }

        //第二步：查询admin表格，将上面的经纪人排除掉(暂时写在houseBrokerDao)
        //对admin表格的查询，按理说应该调用AdminDao,但是AdminDao在acl内，无法调用，到微服务架构解决
        //注意：service和dao会分开，就可以调用，SOA架构，粒度比较大，容易出现重复问题⚠️
        return houseBrokerDao.findHouseOtherBroker(ids);
    }
}
```



### 5.5.3dubobo服务端消费者

HouseControllert添加内容

```java
    @DubboReference
    private HouseBrokerService houseBrokerService;

    /**
     * 页面详情
     */
    @RequestMapping("/show/{id}")
    public String show(Map map,@PathVariable Long id) {
        //详情数据1：房源详细信息
        //ServiceImpl实现类中重写getById，有些属性只有id不满足要求，需要从字典中获取name⚠️
        House house = houseService.getById(id);
        map.put("house",house);

        //详情数据2：房源小区信息
        //ServiceImpl实现类中重写getById，有些属性只有id不满足要求，需要从字典中获取name⚠️
        Community community = communityService.getById(house.getCommunityId());
        map.put("community",community);

        //详情数据3：房源的房源图片，表：hse_house_image
        //房源和房产图片都在hse_house_image一张表上，通过type区分，1房源2房产
        //房源图片通过house_id+type1查询
        List<HouseImage> houseImage1List = houseImageService.findImageByHouseIdAndType(id,1);
        map.put("houseImage1List",houseImage1List);


        //详情数据4：房源的房产图片，表：hse_house_image
        //房产图片通过house_id+type2查询
        List<HouseImage> houseImage2List = houseImageService.findImageByHouseIdAndType(id,2);
        map.put("houseImage2List",houseImage2List);

        //详情数据5：房源的经纪人信息，表：hse_house_broker是中间表，存的房源和用户多对多关系
        List<HouseBroker> houseBrokerList = houseBrokerService.findBrokerByHouseId(id);
        map.put("houseBrokerList",houseBrokerList);

        return PAGE_SHOW;
    }
```

HouseBrokerController

```java
package com.atguigu.controller;

import com.atguigu.entity.Admin;
import com.atguigu.entity.HouseBroker;
import com.atguigu.service.HouseBrokerService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.HouseBrokerController
 */
@Controller
@RequestMapping("/houseBroker")
public class HouseBrokerController {

    @DubboReference
    private HouseBrokerService houseBrokerService;


    private final static String LIST_ACTION = "redirect:/house/show/";
    private final static String PAGE_CREATE = "houseBroker/create";
    private final static String PAGE_SUCCESS = "common/success";


    /**
     * 处理/create/id请求，进入新增页面，并传入当前房源的id
     */
    @RequestMapping("/create/{houseId}")
    public String create(Map map, @PathVariable Long houseId) {
        //需要将除当前房源经纪人以外的其他经纪人添加到下拉列表
        List<Admin> adminList = houseBrokerService.findHouseOtherBroker(houseId);
        map.put("adminList",adminList);
        //传入当前房源id
        map.put("houseId",houseId);
        return PAGE_CREATE;
    }


    /**
     * 处理/save，保存新增
     */
    @RequestMapping("/save")
    public String save(HouseBroker houseBroker){
        houseBrokerService.insert(houseBroker);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/delete/id请求，删除当前房源的经纪人
     * 删除之后，应该重定向到当前房源的show页面
     */
    @RequestMapping("/delete/{houseId}/{houseBrokerId}")
    public String delete(@PathVariable Long houseId,@PathVariable Long houseBrokerId){
        //根据houseBrokerId
        houseBrokerService.delete(houseBrokerId);
        //删除完后重定向到房源的详情页面
        return LIST_ACTION + houseId;
    }


}
```



### 5.5.4准备web资源

#### 5.5.4.1创建create页面

houseBroker/create.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/houseBroker/save}" method="post" class="form-horizontal">
                <input type="hidden" name="houseId" th:value="${houseId}"/>
                <div class="form-group">
                    <label class="col-sm-2 control-label">经纪人：</label>
                    <div class="col-sm-10">
                        <select name="brokerId" id="brokerId" class="form-control">
                            <option value="">-请选择-</option>
                            <option th:each="item,it : ${adminList}" th:text="${item.name}" th:value="${item.id}">-请选择-</option>
                        </select>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



#### 5.5.4.2添加新建js

在房源详情页house/show.html中添加

```html
<script type="text/javascript">
    $(function(){
        $(".createBroker").on("click",function () {
            opt.openWin('/houseBroker/create/[[${house.id}]]','新增经纪人',630,300)
        });
        $(".deleteBroker").on("click",function(){
            var id = $(this).attr("data-id");
            opt.confirm('/houseBroker/delete/[[${house.id}]]/'+id);
        });
    });
</script>
```



## 5.6房源详情之房东

一个房源可以有多位房东联系人，一个房东也可以有多个房源，虽然是多对多，但因为不需要房东的数据具有唯一性，所以可以不使用中间表

### 5.6.1dubobo服务端接口

HouseUserService

```java
package com.atguigu.service;

import com.atguigu.entity.HouseUser;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.HouseUserService
 */
public interface HouseUserService extends BaseService<HouseUser> {

    /**
     * 通过房源id查找房东信息列表
     */
    List<HouseUser> findUserByHouseId(Long houseId);

}
```



### 5.6.2dubobo服务端提供者

#### 5.6.2.1dao层

HouseUserDao

```java
package com.atguigu.dao;

import com.atguigu.entity.HouseUser;
import com.atguigu.service.BaseService;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.HouseUserDao
 */
public interface HouseUserDao extends BaseDao<HouseUser> {

    /**
     * 通过房源id查找房东信息列表
     */
    List<HouseUser> findUserByHouseId(Long houseId);

}
```

HouseUserMapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.HouseUserDao">

    <!-- 通过房源id查找房东信息列表 -->
    <select id="findUserByHouseId" resultType="houseUser">
        select * from hse_house_user where house_id=#{houseId} and is_deleted=0
    </select>

    <!--插入实例-->
    <insert id="insert">
        INSERT INTO hse_house_user(house_id,NAME,phone,sex,id_no)
        VALUES(#{houseId},#{name},#{phone},#{sex},#{idNo})
    </insert>

    <!--根据id获取实例，更新页面的数据回显-->
    <select id="getById" resultType="houseUser">
        select * from hse_house_user where id=#{id} and is_deleted=0
    </select>

    <!--更新实例-->
    <update id="update">
        update hse_house_user
        <set>
            <if test="name!=null and name!=''">
                name=#{name},
            </if>
            <if test="phone!=null and phone!=''">
                phone=#{phone},
            </if>
            <if test="sex!=null and sex!=''">
                sex=#{sex},
            </if>
            <if test="idNo!=null and idNo!=''">
                id_No=#{idNo},
            </if>
        </set>
        where id=#{id}
    </update>

    <!--删除实例，实际上还是修改is_deleted值-->
    <update id="delete">
        update hse_house_user set is_deleted=1 where id=#{id}
    </update>

</mapper>
```



#### 5.6.2.2service层

HouseUserServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.HouseUserDao;
import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.HouseUserServiceImpl
 */
@DubboService
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {

    @Autowired
    private HouseUserDao houseUserDao;

    @Override
    public BaseDao<HouseUser> getEntityDao() {
        return houseUserDao;
    }

    /**
     * 通过房源id查找房东信息列表
     */
    @Override
    public List<HouseUser> findUserByHouseId(Long houseId) {
        return houseUserDao.findUserByHouseId(houseId);
    }
}
```



### 5.6.3dubobo服务端消费者

HouseControllert添加内容

```java
    @DubboReference
    private HouseUserService houseUserService;


    /**
     * 页面详情
     */
    @RequestMapping("/show/{id}")
    public String show(Map map,@PathVariable Long id) {
        //详情数据1：房源详细信息
        //ServiceImpl实现类中重写getById，有些属性只有id不满足要求，需要从字典中获取name⚠️
        House house = houseService.getById(id);
        map.put("house",house);

        //详情数据2：房源小区信息
        //ServiceImpl实现类中重写getById，有些属性只有id不满足要求，需要从字典中获取name⚠️
        Community community = communityService.getById(house.getCommunityId());
        map.put("community",community);

        //详情数据3：房源的房源图片，表：hse_house_image
        //房源和房产图片都在hse_house_image一张表上，通过type区分，1房源2房产
        //房源图片通过house_id+type1查询
        List<HouseImage> houseImage1List = houseImageService.findImageByHouseIdAndType(id,1);
        map.put("houseImage1List",houseImage1List);


        //详情数据4：房源的房产图片，表：hse_house_image
        //房产图片通过house_id+type2查询
        List<HouseImage> houseImage2List = houseImageService.findImageByHouseIdAndType(id,2);
        map.put("houseImage2List",houseImage2List);

        //详情数据5：房源的经纪人信息，表：hse_house_broker是中间表，存的房源和用户多对多关系
        List<HouseBroker> houseBrokerList = houseBrokerService.findBrokerByHouseId(id);
        map.put("houseBrokerList",houseBrokerList);

        //详情数据6：房源的房东信息，表：hse_house_user
        //一个房源可能会有多个房东，一对多的关系
        List<HouseUser> houseUserList = houseUserService.findUserByHouseId(id);
        map.put("houseUserList",houseUserList);

        return PAGE_SHOW;
    }
```

HouseUserController

```java
package com.atguigu.controller;

import com.atguigu.entity.HouseUser;
import com.atguigu.service.HouseUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.HouseUserController
 */
@Controller
@RequestMapping("/houseUser")
public class HouseUserController {

    @DubboReference
    private HouseUserService houseUserService;

    private final static String LIST_ACTION = "redirect:/house/show/";

    private final static String PAGE_CREATE = "houseUser/create";
    private final static String PAGE_EDIT = "houseUser/edit";
    private final static String PAGE_SUCCESS = "common/success";




    /**
     * 处理/create/id请求，进入新增页面，并传入当前房源的id
     */
    @RequestMapping("/create/{houseId}")
    public String create(Map map, @PathVariable Long houseId) {
        map.put("houseId",houseId);
        return PAGE_CREATE;
    }


    /**
     * 处理/save，保存新增
     */
    @RequestMapping("/save")
    public String save(HouseUser houseUser) {
        houseUserService.insert(houseUser);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/edit/id请求，进入编辑页面
     */
    @RequestMapping("/edit/{houseUserId}")
    public String edit(@PathVariable Long houseUserId,Map map){
        //查询到当前的
        HouseUser houseUser = houseUserService.getById(houseUserId);
        map.put("houseUser",houseUser);
        return PAGE_EDIT;
    }


    /**
     * 处理/update请求，更新实例
     */
    @RequestMapping("/update")
    public String update(HouseUser houseUser){
        houseUserService.update(houseUser);
        return PAGE_SUCCESS;
    }


    /**
     * 处理/delete/id请求，删除实例，重定向到当前房源的详情页面
     */
    @RequestMapping("/delete/{houseId}/{houseUserId}")
    public String method(@PathVariable Long houseId,@PathVariable Long houseUserId){
        houseUserService.delete(houseUserId);
        return LIST_ACTION + houseId;
    }
}
```



### 5.6.4准备web资源

#### 5.6.4.1创建create页面

houseUser/create.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/houseUser/save}" method="post" class="form-horizontal">
                <input type="hidden" name="houseId" th:value="${houseId}"/>
                <div class="form-group">
                    <label class="col-sm-2 control-label">房东姓名：</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" id="name" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机：</label>
                    <div class="col-sm-10">
                        <input type="text" name="phone" id="phone" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-10">
                        <div class="radio">
                            <label><input type="radio" checked="checked" value="1" name="sex">男</label>
                        </div>
                        <div class="radio">
                            <label> <input type="radio" value="2" name="sex">女</label>
                        </div>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">身份证号：</label>
                    <div class="col-sm-10">
                        <input type="text" name="idNo" id="idNo" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



#### 5.6.4.2创建edit页面

houseUser/edit.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/houseUser/update}" method="post" class="form-horizontal">
                <input type="hidden" name="id" th:value="${houseUser.id}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">房东姓名：</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" id="name" th:value="${houseUser.name}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机：</label>
                    <div class="col-sm-10">
                        <input type="text" name="phone" id="phone" th:value="${houseUser.phone}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-10">
                        <div class="col-sm-10">
                            <div class="radio">
                                <label><input type="radio" th:checked="${houseUser.sex} eq 1" value="1" name="sex">男</label>
                            </div>
                            <div class="radio">
                                <label> <input type="radio" th:checked="${houseUser.sex} eq 2" value="2" name="sex">女</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">身份证号：</label>
                    <div class="col-sm-10">
                        <input type="text" name="idNo" id="idNo" th:value="${houseUser.idNo}" class="form-control" />
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



#### 5.6.4.3添加新建修改js

在房源详情页house/show.html中添加

```html
<script>
    $(".createUser").on("click",function () {
        opt.openWin('/houseUser/create/[[${house.id}]]','新增房东',630,430)
    });
    $(".editUser").on("click",function () {
        var id = $(this).attr("data-id");
        opt.openWin('/houseUser/edit/' + id,'修改房东',630,430);
    });
    $(".deleteUser").on("click",function(){
        var id = $(this).attr("data-id");
        opt.confirm('/houseUser/delete/[[${house.id}]]/'+id);
    });
</script>
```





# 6 图片上传

## 6.1图片存储方案

在实际开发中，我们会有很多处理不同功能的服务器。例如：

- 应用服务器：负责部署我们的应用

- 数据库服务器：运行我们的数据库

- 文件服务器：负责存储用户上传文件的服务器

常见的图片存储方案：

- 方案一：使用nginx搭建图片服务器

- 方案二：使用开源的分布式文件存储系统，例如Fastdfs、HDFS等

- 方案三：使用云存储，例如阿里云、七牛云等



## 6.2七牛云村存储

七牛云存储SDK文档：https://developer.qiniu.com/kodo/1239/java

### 6.2.1添加依赖

shf-parent添加依赖管理

```xml
<!--放到properties标签中的jar包版本管理-->
<qiniuyun.version>7.7.0</qiniuyun.version>

<!--七牛云服务平台，第三方服务（图片上传）-->
<dependency>
  <groupId>com.qiniu</groupId>
  <artifactId>qiniu-java-sdk</artifactId>
  <version>${qiniuyun.version}</version>
</dependency>
```

common-util引入依赖

```xml
<!--七牛云服务平台，第三方服务（图片上传）-->
<dependency>
    <groupId>com.qiniu</groupId>
    <artifactId>qiniu-java-sdk</artifactId>
</dependency>
```



### 6.2.2文件上传

根据七牛云存储SDK文档找到对应方法

```java
package atguigu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

/**
 * @Author chenxin
 * @date 2022/11/30
 * @Version 1.0
 * 测试成功，测试代码是可以删除，我是教学，代码我就不删，我把@Test注解删除
 *  在安装的时候，会自动执行单元测试
 */

public class QiNiuTest {
    /**
     * 测试文件上传
     *  现在只是测试，咱们写尚好房，肯定要部署服务器
     */


    public void testUplod(){
        //构造一个带指定 Region 对象的配置类   区域的选择
        Configuration cfg = new Configuration(Region.region1());
        // 指定分片上传版本
        //cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        //java代码，首先需要找到你的七牛云账号：每个账号最少会有一对秘钥(账号的唯一标识)
        String accessKey = "FSfYKp-U1f9PWgUtZHPISP8yMCv4wmk6H1aBusF5";
        String secretKey = "dzAsEo6oF0k0Hubvh3H5bCYnYki8hi9fPODWaSln";
        //还需要指定账号下的空间名称(一个账号有可能有多个空间，你到底上传到哪个空间)
        String bucket = "studyshf";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        //指定要上传图片的位置
        String localFilePath = "/Users/shuaigouzi/images/小狐狸和小王子看日落.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);//图片到七牛云上的名字(当前图片的唯一标识，删除也是根据这个名字删)
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }

    }

}
```



### 6.2.3文件删除

根据七牛云存储SDK文档找到对应方法

```java
package atguigu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;


public class QiNiuTest {

    public void testDelete(){
        //构造一个带指定 Region 对象的配置类   区域也要选择华南
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释

        //首先找到删除的账号   一对秘钥去锁定
        String accessKey = "FSfYKp-U1f9PWgUtZHPISP8yMCv4wmk6H1aBusF5";
        String secretKey = "dzAsEo6oF0k0Hubvh3H5bCYnYki8hi9fPODWaSln";
        //还需要找到空间的名字
        String bucket = "studyshf";
        //图片的名字
        String key = "FmGgq1zqJEOCvqiXdp7GKrdCPKnc";

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
```



### 6.2.4封装工具类

为了方便操作七牛云存储服务，我们可以将官方提供的案例简单改造成一个工具类，在我们的项目中直接使用此工具类来操作就可以

封装工具类时，必须配置准确云存储信息，特别是SKD信息、存储库名称和片区机房参数⚠️

将该工具类放到`common-util`模块的`com.atguigu.util`包中，通过依赖传递其他模块都可使用⚠️

```java
package com.atguigu.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * 七牛云工具类
 */
public class QiniuUtil {

    public  static String accessKey = "FSfYKp-U1f9PWgUtZHPISP8yMCv4wmk6H1aBusF5";
    public  static String secretKey = "dzAsEo6oF0k0Hubvh3H5bCYnYki8hi9fPODWaSln";
    public  static String bucket = "studyshf";

    public static void upload2Qiniu(String filePath,String fileName){
        //构造一个带指定Region对象的配置类，要根据机房配置不同的参数，查看文档⚠️
        Configuration cfg = new Configuration(Region.huabei());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(filePath, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    //上传文件
    public static void upload2Qiniu(byte[] bytes, String fileName){
        //构造一个带指定Region对象的配置类，要根据机房配置不同的参数，查看文档⚠️
        Configuration cfg = new Configuration(Region.huabei());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    //删除文件
    public static void deleteFileFromQiniu(String fileName){
        //构造一个带指定Region对象的配置类，要根据机房配置不同的参数，查看文档⚠️
        Configuration cfg = new Configuration(Region.huabei());
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
```





## 6.3房源图片上传

### 6.3.1添加触发上传事件

house/show.html页面添加js响应事件

```javascript
$("#upload1").on("click",function(){
    opt.openWin('/houseImage/uploadShow/[[${house.id}]]/1','上传房源图片',580,430);
});
$("#upload2").on("click",function(){
    opt.openWin('/houseImage/uploadShow/[[${house.id}]]/2','上传房产图片',580,430);
});
$(".deleteImages").on("click",function(){
    var id = $(this).attr("data-id");
    opt.confirm('/houseImage/delete/[[${house.id}]]/'+id);
});
```

### 

### 6.3.2配置上传解析器

在服务消费者端`web_admin`的spring-mvc.xml中配置上传解析器

```xml
    <!--配置上传解析器-->
    <!--"maxUploadSize":表示文件大小，图片的大小-->
    <!--"maxInMemorySize" ：图片加载到内存当中的大小 长 * 宽 * 像素字节数(argb8888,rgb565,argb4444)-->
    <!--"defaultEncoding":UTF-8-->
    <bean id="multipartResolver"
          class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!-- 设定文件上传的最大值为100MB，100*1024*1024 -->
        <property name="maxUploadSize" value="104857600" />
        <!-- 设定文件上传时写入内存的最大值，如果小于这个参数不会生成临时文件，默认为10240 -->
        <property name="maxInMemorySize" value="4096" />
        <!-- 设定默认编码 -->
        <property name="defaultEncoding" value="UTF-8"/>
    </bean>
```



### 6.3.3添加服务器端代码

对web服务器端而言，获取的是图片URL，传递的却是图片字节数据

#### 6.3.3.1dao层

HouseImageMapper添加内容

```xml
<!--使用七牛云上传房源房产图-->
<insert id="insert">
    insert into hse_house_image(house_id,image_name,image_url,type)
    values(#{houseId},#{imageName},#{imageUrl},#{type})
</insert>

<!--删除房源房产图-->
<update id="delete">
    update hse_house_image set is_deleted=1 where id=#{id}
</update>

<!--获取传房源房产图-->
<select id="getById" resultType="houseImage">
    select * from hse_house_image where id=#{id} and is_deleted=0
</select>
```



#### 6.3.3.2controller层

HouseImageController

```java
package com.atguigu.controller;

import com.atguigu.entity.HouseImage;
import com.atguigu.result.Result;
import com.atguigu.service.HouseImageService;
import com.atguigu.util.QiniuUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/houseImage")
public class HouseImageController {

    @DubboReference
    private HouseImageService houseImageService;

    private final static String LIST_ACTION = "redirect:/house/show/";
    private final static String PAGE_UPLOED_SHOW = "house/upload";

    /**
     * 处理/uploadShow/houseId/type请求，跳转到上传页面
     * 通过参数决定传递哪个房源的房源图或者房产图
     */
    @RequestMapping("/uploadShow/{houseId}/{type}")
    public String uploadShow(@PathVariable Long houseId, @PathVariable Integer type, Map map) {
        //参数决定传递的房源和传递的图片类型，放到请求域，发异步请求需要
        map.put("houseId", houseId);
        map.put("type", type);
        return PAGE_UPLOED_SHOW;
    }


    /**
     * 处理/upload/houseId/type请求，图片上传操作
     * 通过参数决定传递哪个房源的房源图或房产图
     */
    @RequestMapping("/upload/{houseId}/{type}")
    @ResponseBody
    public Result upload(
            @PathVariable Long houseId,
            @PathVariable Integer type,
            //必须叫file，插件里写死了，可能有多个，所以用数组
            @RequestParam("file") MultipartFile[] files) throws IOException {
        //第一步：先将图片循环上传到七牛云服务器
        for (MultipartFile file : files) {

            //循环的file就是需要上传的文件，使用UUID随机为其生成个名字
            String fileName = UUID.randomUUID().toString();

            //将file转换为byte数组，使用七牛云传递byte[]的方式上传图片
            QiniuUtil.upload2Qiniu(file.getBytes(), fileName);

            //第二步：需要在数据库内添加记录，设置了house_id，图片名字，图片路径，type
            HouseImage houseImage = new HouseImage();
            houseImage.setHouseId(houseId);
            houseImage.setImageName(fileName);

            //使用七牛云空间域名+图片名字拼凑图片的完整URL
            houseImage.setImageUrl("http://rm5n3wdxr.hb-bkt.clouddn.com/" + fileName);
            houseImage.setType(type);

            houseImageService.insert(houseImage);
        }
        return Result.ok();
    }


    /**
     * 处理/delete/houseId/houseImageId请求，删除图片
     * 1.从七牛云删除可以做或不做
     * 2.从数据库将图片信息删除(逻辑删除,并非真正删除)
     * 3.删除完成后回到详情页面
     */
    @RequestMapping("/delete/{houseId}/{houseImageId}")
    public String delete(@PathVariable Long houseId, @PathVariable Long houseImageId) {
        //1. 从七牛云将图片删除，这一步可以不做，实现删除记录之类功能
        HouseImage houseImage = houseImageService.getById(houseImageId);
        QiniuUtil.deleteFileFromQiniu(houseImage.getImageName());
        //2. 从数据库将图片信息删除(逻辑删除)
        houseImageService.delete(houseImageId);
        //3. 删除完成重定向到详情页面
        return LIST_ACTION + houseId;
    }
}
```



### 6.3.4创建上传页面

创建页面：house/upload.html

页面模版存放位置：hplus-master/form_webuploader.html

说明：

- BASE_URL为WebUploader组件flash的地址，通过flash实现的异步上传

- BASE_UPLOAD为我们的上传地址，即上面定义的controller上传方法

- webuploader-demo.js为批量上传的实现代码，替换webuploader-demo.js文件85行上传地址为：server: BASE_UPLOAD⚠️
- 在代码正确的前提下，若多次传图失败，尝试为web服务器换个端口再启动。配置server: BASE_UPLOAD时可多设置些传输图片的大小⚠️

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<link rel="stylesheet" type="text/css" th:href="@{/static/css/plugins/webuploader/webuploader.css}">
<link rel="stylesheet" type="text/css" th:href="@{/static/css/demo/webuploader-demo.css}">
<script th:src="@{/static/js/plugins/webuploader/webuploader.min.js}"></script>
<!--修改这个js文件内的内容：85行-->
<script th:src="@{/static/js/demo/webuploader-demo.js}"></script>
<script type="text/javascript">
    // 添加全局站点信息 swf文件路径，加载上传成功的进度条等样式
    var BASE_URL = '/static/js/plugins/webuploader';
    //自定义文件接收服务端：这里是定义传到服务器上的Controller路径
    //传递图片步骤：第一步：先传到web服务器，第二步：然后由java代码再传到云服务器
    //webuploader-demo.js为批量上传的实现代码，我们直接使用，替换webuploader-demo.js文件第85行
    var BASE_UPLOAD = '/houseImage/upload/[[${houseId}]]/[[${type}]]';
</script>
<body class="gray-bg">
<div class="row">
    <div class="col-sm-9">
        <div class="wrapper wrapper-content animated fadeInUp">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="row">
                        <div class="ibox-content">
                            <div class="page-container" id="uploadShow">
                                <p>请选择需要上传的图片</p>
                                <div id="uploader" class="wu-example">
                                    <div class="queueList">
                                        <div id="dndArea" class="placeholder">
                                            <div id="filePicker"></div>
                                            <p>支持批量上传</p>
                                        </div>
                                    </div>
                                    <div class="statusBar" style="display:none;">
                                        <div class="progress">
                                            <span class="text">0%</span>
                                            <span class="percentage"></span>
                                        </div>
                                        <div class="info"></div>
                                        <div class="btns">
                                            <div id="filePicker2"></div>
                                            <!--异步上传-->
                                            <div class="uploadBtn">开始上传</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-4 col-sm-offset-2">
                            <button class="btn btn-primary" type="button" onclick="opt.closeWin(true);">确定</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
```



## 6.4用户头像上传

### 6.4.1添加JS触发事件

admin/index.html中添加上传头像按钮

```html
<a class="upload" th:attr="data-id=${item.id}">上传头像</a>
```


admin/index.html中添加点击上传头像的js触发事件

```javascript
$(".upload").on("click",function(){
    var id = $(this).attr("data-id");
    opt.openWin('/admin/uploadShow/'+id,'上传头像',580,300);
});
```



### 6.4.2创建上传页面

admin/upload.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/admin/upload}" method="post" enctype="multipart/form-data" class="form-horizontal">
                <input type="hidden" name="adminId" th:value="${adminId}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">上传头像：</label>
                    <div class="col-sm-10">
                        <input type="file" name="file" id="file" class="form-control" readonly/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



### 6.4.3添加服务器端代码

AdminController添加内容

```java
		private final static String PAGE_UPLOED_SHOW = "admin/upload";

		/**
     * 处理/uploadShow/id请求，跳转到头像上传页面
     */
    @RequestMapping("/uploadShow/{adminId}")
    public String uploadShow(@PathVariable Long adminId,Map map){
        map.put("adminId",adminId);
        return PAGE_UPLOED_SHOW;
    }

    /**
     * 处理/upload请求，上传图片url到数据库，上传图片到七牛云
     */
    @RequestMapping("/upload")
    public String upload(Long adminId, @RequestParam("file") MultipartFile file) throws IOException {
        //1. 将图片上传到七牛云
        String fileName= UUID.randomUUID().toString();
        QiniuUtil.upload2Qiniu(file.getBytes(),fileName);
        //2. 对当前用户做修改操作，将head_url进行修改
        Admin admin=new Admin();
        admin.setId(adminId);
        admin.setHeadUrl("http://rm5dtfln6.hn-bkt.clouddn.com/"+fileName);
        adminService.update(admin);
        return PAGE_SUCCESS;
    }
```



# 7 前端房源展示

和后台管理系统使用Thymeleaf视图解析器渲染不同，前端页面的渲染方式使用Vue+Axios。

## 7.1搭建前端模块web_front

以web模块为父工程，在web模块下创建web_front模块，搭建过程与web_admin一致

### 7.1.1模块架构

模块的目录及文件架构如下

- web_front
  - pom.xml（打包方式设置为war，修改jetty端口）
  - src/main/
    - java/com.atguigu.controller
    - resources
      - spring
        - spring-mvc.xml（去掉图片上传解析器、去掉视图解析器Thymeleaf）⚠️
        - spring-registry.xml（修改消费者名字）⚠️
      - logback.xml
    - webapp
      - WEB-INF
        - web.xml



### 7.1.2pom.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>web</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <!--设置打包方式为war包-->
    <artifactId>web_front</artifactId>

    <!--设置打包方式为war包-->
    <packaging>war</packaging>


    <!--设置服务器jetty的参数，以Maven的插件形式存在-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.15.v20190215</version>
                <configuration>
                    <!-- 如果检测到项目有更改则自动热部署，每隔n秒扫描一次。默认为0，即不扫描-->
                    <scanIntervalSeconds>10</scanIntervalSeconds>
                    <webAppConfig>
                        <!--指定web项目的根路径，默认为/    设置上下文路径-->
                        <contextPath>/</contextPath>
                    </webAppConfig>
                    <httpConnector>
                        <!--设置端口号，默认 8080-->
                        <port>8002</port>
                    </httpConnector>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



### 7.1.3spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--将spring-registry.xml引入到spring-mvc中，相当于把内容复制到该文件内-->
    <!--其实可以将spring-registry.xml内容都写在该文件内，不过不方便维护-->
    <import resource="spring-registry.xml"/>

    <!--controller包的注解扫描-->
    <context:component-scan base-package="com.atguigu.controller"/>

    <!-- 开启mvc注解，fastjson转换器的添加-->
    <mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <!-- 配置Fastjson支持 -->
            <bean class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                        <value>application/json</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

    <!--静态资源访问-->
    <mvc:default-servlet-handler/>

</beans>
```



### 7.1.4spring-registry.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!--这里不需要设置扫描包，springMvc中已经设置了，不然会冲突⚠️-->
    <!--<context:component-scan base-package="com.atguigu.controller"/>-->

    <!--配置dubbo应用程序名称-->
    <dubbo:application name="web_front"></dubbo:application>

    <!--注册配置中心-->
    <dubbo:registry address="zookeeper://localhost:2181"></dubbo:registry>

    <!--启动时候不检查 设置连接超时时间-->
    <dubbo:consumer check="false" timeout="600000"></dubbo:consumer>
</beans>
```



### 7.1.5logback.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="false">

    <!--定义日志文件的存储地址 logs为当前项目的logs目录 还可以设置为../logs -->
    <property name="LOG_HOME" value="logs" />

    <!--控制台日志， 控制台输出 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度,%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- 日志输出级别 -->
    <root level="DEBUG">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```



### 7.1.6web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!-- 解决post乱码 添加字符编码过滤器 -->
    <filter>
        <filter-name>encode</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceRequestEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encode</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!-- 配置SpringMVC框架前端控制器 -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>

</web-app>
```



## 7.2首页多条件排序查询

### 7.2.1引入静态资源

路径：项目模板/前端模板

复制static文件夹与index.html到web-front模块webapp目录下（不能放在WEB-INF目录里，小心复制错了文件夹⚠️）



### 7.2.2引入VUE组件

路径：vue js组件

复制vue.js与axios.js到webapp/static/js下

待会在index页面中引入

```html
<script src="./static/js/vue.js"></script>
<script src="./static/js/axios.js"></script>
```



### 7.2.3创建index页面

直接在webapp目录下创建index.html（不能放在WEB-INF目录里，小心别创建错了位置⚠️）

```html
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="Author" contect="http://www.webqin.net">
    <title>尚好房</title>
    <link rel="shortcut icon" href="./static/images/favicon.ico"/>
    <link type="text/css" href="./static/css/css.css" rel="stylesheet"/>
    <script type="text/javascript" src="./static/js/jquery.js"></script>
    <script type="text/javascript" src="./static/js/js.js"></script>
    <script type="text/javascript" src="./static/js/vue.js"></script>
    <script type="text/javascript" src="./static/js/axios.js"></script>
    <script type="text/javascript">
        $(function () {
            //导航定位
            $(".nav li:eq(1)").addClass("navCur");
        })
    </script>
</head>

<body>
<div id="list">
    <div class="header">
        <div class="width1190">
            <div class="fl">您好，欢迎来到尚好房！</div>
            <div class="fr">
                <a href="login.html">登录</a> |
                <a href="register.html">注册</a> |
                <a href="javascript:;">加入收藏</a> |
                <a href="javascript:;">设为首页</a>
            </div>
            <div class="clears"></div>
        </div><!--width1190/-->
    </div>
    <div class="list-nav">
        <div class="width1190">
            <div class="list"><h3>房源分类</h3></div><!--list/-->
            <ul class="nav">
                <li><a href="index.html">首页</a></li>
                <li><a href="about.html">关于我们</a></li>
                <li><a href="contact.html">联系我们</a></li>
                <div class="clears"></div>
            </ul><!--nav/-->
            <div class="clears"></div>
        </div><!--width1190/-->
    </div><!--list-nav/-->
    <div class="banner" style="background:url(./static/images/ban.jpg) center center no-repeat;"></div>

    <div class="content">
        <div class="width1190">
            <form action="#" method="get" class="pro-search">
                <table>
                    <tr>
                        <th>房源区域：</th>
                        <td>
                            <div style="line-height: 30px;">
                                <a href="javascript:;" @click="searchArea('')"
                                   :class="houseQueryVo.areaId=='' ? 'pro-cur' : ''">不限</a>
                                <a href="javascript:;" @click="searchArea(item.id)"
                                   :class="item.id==houseQueryVo.areaId ? 'pro-cur' : ''" v-for="item in areaList"
                                   :key="item.id">{{ item.name }}</a>
                            </div>
                            <!--新增区域-->
                            <div style="font-size: 12px;border-top:#ccc 1px dotted;">
                                <a href="javascript:;" @click="searchPlate(item.id)"
                                   :class="item.id==houseQueryVo.plateId ? 'pro-cur' : ''" v-for="item in plateList"
                                   :key="item.id">{{ item.name }}</a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>户型：</th>
                        <td>
                            <a href="javascript:;" @click="searchHouseType('')"
                               :class="houseQueryVo.houseTypeId=='' ? 'pro-cur' : ''">不限</a>
                            <a href="javascript:;" @click="searchHouseType(item.id)"
                               :class="item.id==houseQueryVo.houseTypeId ? 'pro-cur' : ''" v-for="item in houseTypeList"
                               :key="item.id">{{ item.name }}</a>
                        </td>
                    </tr>
                    <tr>
                        <th>楼层：</th>
                        <td>
                            <a href="javascript:;" @click="searchFloor('')"
                               :class="houseQueryVo.floorId=='' ? 'pro-cur' : ''">不限</a>
                            <a href="javascript:;" @click="searchFloor(item.id)"
                               :class="item.id==houseQueryVo.floorId ? 'pro-cur' : ''" v-for="item in floorList"
                               :key="item.id">{{ item.name }}</a>
                        </td>
                    </tr>
                    <tr>
                        <th>建筑结构：</th>
                        <td>
                            <a href="javascript:;" @click="searchBuildStructure('')"
                               :class="houseQueryVo.buildStructureId=='' ? 'pro-cur' : ''">不限</a>
                            <a href="javascript:;" @click="searchBuildStructure(item.id)"
                               :class="item.id==houseQueryVo.buildStructureId ? 'pro-cur' : ''"
                               v-for="item in buildStructureList" :key="item.id">{{ item.name }}</a>
                        </td>
                    </tr>
                    <tr>
                        <th>朝向：</th>
                        <td>
                            <a href="javascript:;" @click="searchDirection('')"
                               :class="houseQueryVo.directionId=='' ? 'pro-cur' : ''">不限</a>
                            <a href="javascript:;" @click="searchDirection(item.id)"
                               :class="item.id==houseQueryVo.directionId ? 'pro-cur' : ''" v-for="item in directionList"
                               :key="item.id">{{ item.name }}</a>
                        </td>
                    </tr>
                    <tr>
                        <th>装修情况：</th>
                        <td>
                            <a href="javascript:;" @click="searchDecoration('')"
                               :class="houseQueryVo.decorationId=='' ? 'pro-cur' : ''">不限</a>
                            <a href="javascript:;" @click="searchDecoration(item.id)"
                               :class="item.id==houseQueryVo.decorationId ? 'pro-cur' : ''"
                               v-for="item in decorationList" :key="item.id">{{ item.name }}</a>
                        </td>
                    </tr>
                    <tr>
                        <th>房屋用途：</th>
                        <td>
                            <a href="javascript:;" @click="searchHouseUse('')"
                               :class="houseQueryVo.houseUseId=='' ? 'pro-cur' : ''">不限</a>
                            <a href="javascript:;" @click="searchHouseUse(item.id)"
                               :class="item.id==houseQueryVo.houseUseId ? 'pro-cur' : ''" v-for="item in houseUseList"
                               :key="item.id">{{ item.name }}</a>
                        </td>
                    </tr>
                </table>
                <div class="paixu">
                    <strong>排序：</strong>
                    <a href="javascript:;" @click="sortDefault()"
                       :class="houseQueryVo.defaultSort=='1' ? 'pai-cur' : ''">默认</a>
                    <a href="javascript:;" @click="sortPrice()" :class="houseQueryVo.priceSort=='1' ? 'pai-cur' : ''">价格
                        &or;</a>
                    <a href="javascript:;" @click="sortTime()" :class="houseQueryVo.timeSort=='1' ? 'pai-cur' : ''">最新
                        &or;</a>
                </div>
            </form><!--pro-search/-->
        </div><!--width1190/-->
        <div class="width1190">
            <div class="pro-left">
                <dl v-for="item in page.list" :key="item.id">
                    <dt><a :href="'info.html?id='+item.id"><img :src="item.defaultImageUrl" width="286"
                                                                height="188"/></a></dt>
                    <dd>
                        <h3><a :href="'info.html?id='+item.id">{{ item.name }}</a></h3>
                        <div class="pro-wei">
                            <img src="/static/images/weizhi.png" width="12" height="16"/> <strong class="red">{{
                            item.communityName }}</strong>
                        </div>
                        <div class="pro-fang">{{ item.buildArea }}平 {{ item.houseTypeName}} {{ item.floorName}} {{
                            item.directionName}}
                        </div>
                        <div class="pra-fa"> 发布时间：{{ item.createTimeString }}</div>
                    </dd>
                    <div class="price">¥ <strong>{{ item.totalPrice }}</strong><span class="font12">万元</span></div>
                    <div class="clears"></div>
                </dl>

            </div><!--pro-left/-->

            <div class="clears"></div>
            <ul class="pages">
                <li>
                    <a href="javascript:;" @click="fetchData(page.prePage)" v-if="page.hasPreviousPage">上一页</a>
                </li>
                <li v-for="item in page.navigatepageNums" :class="item==page.pageNum ? 'page_active' : ''">
                    <a href="javascript:;" @click="fetchData(item)">{{ item }}</a>
                </li>
                <li>
                    <a href="javascript:;" @click="fetchData(page.nextPage)" v-if="page.hasNextPage">下一页</a>
                </li>
            </ul>
        </div><!--width1190/-->
    </div><!--content/-->

    <div class="footer">
        <div class="width1190">
            <div class="fl"><a href="index.html"><strong>尚好房</strong></a><a href="about.html">关于我们</a><a
                    href="contact.html">联系我们</a><a href="follow.html">个人中心</a></div>
            <div class="fr">
                <dl>
                    <dt><img src="./static/images/erweima.png" width="76" height="76"/></dt>
                    <dd>微信扫一扫<br/>房价点评，精彩发布</dd>
                </dl>
                <dl>
                    <dt><img src="./static/images/erweima.png" width="76" height="76"/></dt>
                    <dd>微信扫一扫<br/>房价点评，精彩发布</dd>
                </dl>
                <div class="clears"></div>
            </div>
            <div class="clears"></div>
        </div><!--width1190/-->
    </div><!--footer/-->
    <div class="copy">Copyright@ 2020 尚好房 版权所有 沪ICP备1234567号-0&nbsp;&nbsp;&nbsp;&nbsp;技术支持：XXX</div>
    <div class="bg100"></div>
</div>
<script>
    new Vue({
        el: '#list',
        data: {
            areaList: [],
            plateList: [],
            houseTypeList: [],
            floorList: [],
            buildStructureList: [],
            directionList: [],
            decorationList: [],
            houseUseList: [],
            //接口当前页的数据，存储分页信息
            page: {
                list: [],
                pageNum: 1,
                pageSize: 2, //方便测试分页
                pages: 1,
                //导航页码
                navigatepageNums: [1, 2, 3, 4],
                //上一页
                prePage: 0,
                //下一页
                nextPage: 0,
                //是否为首页
                hasPreviousPage: false,
                //是否为尾页
                hasNextPage: false
            },
            //存储查询条件的Json对象，存储用户点击的搜索项⚠️
            //该Json对象会传到后端dao层，dao层判断是否为空串进行拼接查询条件
            houseQueryVo: {
                areaId: '',
                plateId: '',
                houseTypeId: '',
                floorId: '',
                buildStructureId: '',
                directionId: '',
                decorationId: '',
                houseUseId: '',

                defaultSort: 1,
                priceSort: null,
                timeSort: null,

            },
        },
        created() {
            this.fetchDictData()
            //默认刚进入页码显示全部房源，发起一次异步请求
            this.fetchData(1)
        },
        methods: {
            fetchDictData() {
                //axios在then的内部不能使用Vue的实例化的this, 因为在内部 this 没有被绑定
                var that = this
                axios.get('/dict/findListByDictCode/beijing').then(function (response) {
                    that.areaList = response.data.data
                });
                axios.get('/dict/findListByDictCode/houseType').then(function (response) {
                    that.houseTypeList = response.data.data
                });
                axios.get('/dict/findListByDictCode/floor').then(function (response) {
                    that.floorList = response.data.data
                });
                axios.get('/dict/findListByDictCode/buildStructure').then(function (response) {
                    that.buildStructureList = response.data.data
                });
                axios.get('/dict/findListByDictCode/direction').then(function (response) {
                    that.directionList = response.data.data
                });
                axios.get('/dict/findListByDictCode/decoration').then(function (response) {
                    that.decorationList = response.data.data
                });
                axios.get('/dict/findListByDictCode/houseUse').then(function (response) {
                    that.houseUseList = response.data.data
                });
            },

            //房源区域的二级搜索
            searchArea(id) {
                this.houseQueryVo.areaId = id
                this.houseQueryVo.plateId = ''
                this.fetchData(1)

                //如果点击的是不限，则进入到if内，不再发请求，直接return
                if (id == '') {
                    this.plateList = []
                    return
                }
                var that = this
                axios.get('/dict/findListByParentId/' + id).then(function (response) {
                    that.plateList = response.data.data
                });
            },

            //存储查询条件值，用户点击的搜索项触发的方法
            searchPlate(id) {
                this.houseQueryVo.plateId = id
                this.fetchData(1)
            },
            searchHouseType(id) {
                this.houseQueryVo.houseTypeId = id
                this.fetchData(1)
            },
            searchFloor(id) {
                this.houseQueryVo.floorId = id
                this.fetchData(1)
            },
            searchBuildStructure(id) {
                this.houseQueryVo.buildStructureId = id
                this.fetchData(1)
            },
            searchDirection(id) {
                this.houseQueryVo.directionId = id
                this.fetchData(1)
            },
            searchDecoration(id) {
                this.houseQueryVo.decorationId = id
                this.fetchData(1)
            },
            searchHouseUse(id) {
                this.houseQueryVo.houseUseId = id
                this.fetchData(1)
            },

            //用户选择的排序规则：默认/单价/时间
            sortDefault() {
                this.houseQueryVo.defaultSort = 1
                this.houseQueryVo.priceSort = null
                this.houseQueryVo.timeSort = null
                this.fetchData(1)
            },
            sortPrice() {
                this.houseQueryVo.defaultSort = null
                this.houseQueryVo.priceSort = 1
                this.houseQueryVo.timeSort = null
                this.fetchData(1)
            },
            sortTime() {
                this.houseQueryVo.defaultSort = null
                this.houseQueryVo.priceSort = null
                this.houseQueryVo.timeSort = 1
                this.fetchData(1)
            },

            //无论点击哪个搜索项，都会调用该方法向后端发起异步查询请求⚠️
            //houseQueryVo对象是搜索条件对象以及分页信息
            fetchData(pageNum = 1) {
                this.page.pageNum = pageNum
                if (pageNum < 1) pageNum = 1
                //axios在then的内部不能使用Vue的实例化的this, 因为在内部 this 没有被绑定
                var that = this
                axios.post('/house/list/' + pageNum + '/' + this.page.pageSize, this.houseQueryVo).then(function (response) {
                    //后端返回的pageInfo对象，包含着全部的渲染列表信息及分页信息，赋值给Vue的page对象，通过page渲染数据⚠️
                    that.page = response.data.data
                });
            },
        }
    })
</script>
</body>
</html>
```



### 7.2.4后端地区查询

通过查询字典中的code，展示房源地区信息，并点击房源地区通过id查询子地区，实现二次查询

#### 7.2.4.1后端controller层

DictController

```java
package com.atguigu.controller;

import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.DictController
 */
@Controller
@RequestMapping("/dict")
@ResponseBody
public class DictController {
    private final static String PAGE_INDEX = "dict/index";

    @DubboReference
    private DictService dictService;


    /**
     * 请求路径：/dict/findListByDictCode/code，作用：根据code返回字典内相关信息
     */
    @RequestMapping("/findListByDictCode/{code}")
    public Result findListByDictCode(@PathVariable String code){
        List<Dict> dictList = dictService.findListByDictCode(code);
        return Result.ok(dictList);
    }


    /**
     * 请求路径：findListByParentId/parentId，作用：根据id二次查找下级地区板块
     */
    @RequestMapping("/findListByParentId/{parentId}")
    public Result findListByParentId(@PathVariable Long parentId){
        List<Dict> dictList = dictService.findListByParentId(parentId);
        return Result.ok(dictList);
    }

}
```



### 7.2.5后端多条件关联查询

借助VO对象封装查询参数和传递查询结果。

#### 7.2.5.1后端serviceAPI

HouseService新增内容

```Java
/**
 * 查询前台首页展示信息，HouseVo中包含了字典、小区、房源的信息，返回HouseVo对象即可
 */
PageInfo<HouseVo> findHouseByHouseQueryVo(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo);
```



#### 7.2.5.2后端dao层

HouseDao新增内容

```java
/**
 * 查询前台首页展示信息，HouseVo中包含了字典、小区、房源的信息，返回HouseVo对象即可
 */
List<HouseVo> findHouseByQueryVo(HouseQueryVo houseQueryVo);
```

HouseMapper新增内容

```xml
<!--需要两表联查(hse_house/hse_community),hse_dict表中的名字，到时候根据id去查询-->
<select id="findHouseByQueryVo" resultType="houseVo">
    SELECT hc.`name` communityName,hh.* FROM hse_house hh LEFT JOIN hse_community hc
    ON hh.`community_id`=hc.`id`
    <where>
        <if test="areaId!=null and areaId!=''">
            and hc.area_Id=#{areaId}
        </if>
        <if test="plateId!=null and plateId!=''">
            and hc.plate_Id=#{plateId}
        </if>
        <if test="houseTypeId!=null and houseTypeId!=''">
            and house_Type_Id=#{houseTypeId}
        </if>
        <if test="floorId!=null and floorId!=''">
            and hh.floor_Id=#{floorId}
        </if>
        <if test="buildStructureId!=null and buildStructureId!=''">
            and  hh.build_Structure_Id=#{buildStructureId}
        </if>
        <if test="directionId!=null and directionId!=''">
            and  hh.direction_Id=#{directionId}
        </if>
        <if test="decorationId!=null and decorationId!=''">
            and hh.decoration_Id=#{decorationId}
        </if>
        <if test="houseUseId!=null and houseUseId!=''">
            and hh.house_Use_Id=#{houseUseId}
        </if>
        <!--必须是已发布的、小区和房源也未删除的才能被前台看到-->
        and hh.status=1
        and hh.is_deleted=0
        and hc.is_deleted=0
    </where>
    <!--查询中的三个排序信息-->
    <if test="defaultSort==1">
        order by hh.id desc
    </if>
    <if test="priceSort==1">
        order by hh.total_price desc
    </if>
    <if test="timeSort==1">
        order by hh.create_time desc
    </if>
</select>
```



#### 7.2.5.3后端service层

HouseServiceImpl新增内容

```java
/**
 * 查询前台首页展示信息，HouseVo中包含了字典、小区、房源的信息，返回HouseVo对象即可
 * 关于房源中个别属性只有id，可再去字典中根据id查到name再赋值即可
 */
@Override
public PageInfo<HouseVo> findHouseByHouseQueryVo(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo) {
    PageHelper.startPage(pageNum,pageSize);
    List<HouseVo> houseVoList = houseDao.findHouseByQueryVo(houseQueryVo);
    for (HouseVo houseVo : houseVoList) {
        //需要将数据字典中三个id值换成三个name值: 需要调用DictDao
        houseVo.setHouseTypeName(dictDao.getNameById(houseVo.getHouseTypeId()));
        houseVo.setDirectionName(dictDao.getNameById(houseVo.getDirectionId()));
        houseVo.setFloorName(dictDao.getNameById(houseVo.getFloorId()));
    }
    return new PageInfo<>(houseVoList,3);
}
```



#### 7.2.5.4后端controller层

HouseController

```java
package com.atguigu.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import com.atguigu.result.Result;
import com.atguigu.service.HouseService;
import com.atguigu.vo.HouseQueryVo;
import com.atguigu.vo.HouseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/house")
public class HouseController {

    @DubboReference
    private HouseService houseService;

    /**
     * 房源列表
     */
    @PostMapping(value = "/list/{pageNum}/{pageSize}")
    public Result findListPage(@RequestBody HouseQueryVo houseQueryVo,
                               @PathVariable Integer pageNum,
                               @PathVariable Integer pageSize) {
        //调用业务层处理业务，这里的page对象会响应到前台，直接赋给前台的page对象
        //前端根据page中的数据进行列表渲染⚠️
        PageInfo<HouseVo> page = houseService.findHouseByHouseQueryVo(pageNum, pageSize, houseQueryVo);
        return Result.ok(page);
    }

}
```



## 7.3房源详情

### 7.3.1准备前端页面

在webapp下创建info.html文件

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="Author" contect="http://www.webqin.net">
    <title>尚好房</title>
    <link rel="shortcut icon" href="./static/images/favicon.ico"/>
    <link type="text/css" href="./static/css/css.css" rel="stylesheet"/>
    <link rel="stylesheet" href="./static/css/swiper-bundle.min.css">
    <script type="text/javascript" src="./static/js/jquery.js"></script>
    <script type="text/javascript" src="./static/js/js.js"></script>
    <script src="./static/js/vue.js"></script>
    <script src="./static/js/axios.js"></script>
    <!--<script src="./static/js/request.js"></script>-->
    <script src="./static/js/util.js"></script>

    <script src="./static/js/swiper-bundle.min.js"></script>
    <style>
        .swiper{width:100%;height:100%}.swiper{width:100%;height:300px;margin-left:auto;margin-right:auto}.swiper-slide{background-size:cover;background-position:center}.mySwiper2{height:80%;width:100%}.mySwiper{height:20%;box-sizing:border-box;padding:10px 0}.mySwiper .swiper-slide{width:25%;height:100%;opacity:.4}.mySwiper .swiper-slide-thumb-active{opacity:1}.swiper-slide img{display:block;width:100%;height:100%;object-fit:cover}
    </style>
    <script type="text/javascript">
        $(function () {
            //导航定位
            $(".nav li:eq(0)").addClass("navCur");
        })
    </script>
</head>

<body>
<div id="item">
    <div class="header">
        <div class="width1190">
            <div class="fl">您好，欢迎来到尚好房！</div>
            <div class="fr">
                <a href="login.html">登录</a> |
                <a href="register.html">注册</a> |
                <a href="javascript:;">加入收藏</a> |
                <a href="javascript:;">设为首页</a>
            </div>
            <div class="clears"></div>
        </div><!--width1190/-->
    </div>
    <div class="list-nav">
        <div class="width1190">
            <div class="list"><h3>房源分类</h3></div><!--list/-->
            <ul class="nav">
                <li><a href="index.html">首页</a></li>
                <li><a href="about.html">关于我们</a></li>
                <li><a href="contact.html">联系我们</a></li>
                <div class="clears"></div>
            </ul><!--nav/-->
            <div class="clears"></div>
        </div><!--width1190/-->
    </div><!--list-nav/-->
    <div class="banner" style="background:url(/static/images/ban.jpg) center center no-repeat;"></div>

    <div class="content">
        <div class="width1190" style="width:1000px;">
            <div class="proImg fl">
                <!--<img src="/static/images/fangt1.jpg"/>-->

                <div style="--swiper-navigation-color: #F2F2F2; --swiper-pagination-color: #F2F2F2" class="swiper mySwiper2">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide" v-for="item in houseImage1List" :key="item.id" >
                            <img :src="item.imageUrl" />
                        </div>
                    </div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
                <div thumbsSlider="" class="swiper mySwiper">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide" v-for="item in houseImage1List" :key="item.id">
                            <img :src="item.imageUrl"/>
                        </div>
                    </div>
                </div>
            </div>
            <!--proImg/-->
            <div class="proText fr">
                <h3 class="proTitle">
                    {{ house.name }}
                    <span v-if="isFollow" style="margin-left: 50px; font-size: 14px;"><a href="###">已关注</a></span>
                    <span v-else style="margin-left: 50px; font-size: 14px;"><a href="###" @click="follow()">关注</a></span>
                </h3>
                <div class="proText1">
                    <div class="proText1-detail-pri">
                        <strong>{{ house.houseTypeName }}</strong>
                        <em>{{ community.buildYears }}/{{ house.floorName }}</em>
                    </div>
                    <div class="proText1-detail-pri">
                        <strong>{{ house.directionName }}</strong>
                        <em>{{ house.decorationName }}/{{ house.buildStructureName }}</em>
                    </div>
                    <div class="proText1-detail-pri">
                        <strong>{{ house.totalPrice }}万元</strong>
                        <em>{{ house.unitPrice }}元/平/{{ house.buildArea }}平方米</em>
                    </div>
                    <ul class="proText1-detail-oth clears">
                        <li>
                            <span>小区名称：</span><a href="#">{{ community.name }}</a>
                        </li>
                        <li>
                            <span>所在区域：</span><a href="#">{{ community.areaName }}</a><a href="#">{{ community.plateName }}</a>
                        </li>
                        <li>
                            <span>房屋编号：</span>{{ house.id }}
                        </li>
                    </ul>
                    <div class="jingji">
                        <div class="jingji-pho">
                            <a href="#">
                                <img :src="houseBroker.brokerHeadUrl" alt="">
                            </a>
                        </div>
                        <div class="jingji-deta">
                            <a href="javascript:;" class="projrgwc">{{ houseBroker.brokerName }}</a>
                            <span>本小区好评经纪人</span>
                        </div>
                        <a href="javascript:;" class="jingji-tel">4008758119 <span>转</span>35790</a>
                    </div>
                </div>
            </div><!--proText/-->
            <div class="clears"></div>
        </div>
    </div><!--width1190/-->
    <div class="proBox" style="width:1000px;margin:10px auto;">
        <div class="proEq">
            <!--选项卡菜单-->
            <ul class="fl">
                <li class="proEqCur">房源信息</li>
                <li>房源特色</li>
                <li>户型分间</li>
                <li>经纪人反馈</li>
            </ul>
            <div class="clears"></div>
        </div><!--proEq/-->
        <!--每一个选项卡-->
        <div class="proList">
            <dl class="proList-con clearf">
                <dt>基本属性</dt>
                <dl>
                    <dd><span>房屋户型</span>{{ house.houseTypeName }}</dd>
                    <dd><span>所在楼层</span>{{ house.floorName }}</dd>
                    <dd><span>建筑面积</span>{{ house.buildArea }}平方米</dd>
                    <dd><span>建筑结构</span>{{ house.buildStructureName }}</dd>
                    <dd><span>套内面积</span>{{ house.insideArea }}平方米</dd>
                    <dd><span>房屋朝向</span>{{ house.directionName }}</dd>
                    <dd><span>装修情况</span>{{ house.decorationName }}</dd>
                    <dd><span>梯户比例</span>{{ house.elevatorRatio }}</dd>
                </dl>
            </dl>
            <dl class="proList-con clearf">
                <dt>交易性质</dt>
                <dl>
                    <dd><span>挂牌时间</span>{{ house.listingDateString }}</dd>
                    <dd><span>交易权属</span>商品房</dd>
                    <dd><span>上次交易</span>{{ house.lastTradeDateString }}</dd>
                    <dd><span>房屋用途</span>{{ house.houseUseName }}</dd>
                    <dd><span>房屋年限</span>满五年</dd>
                    <dd><span>产权所属</span>共有</dd>
                    <dd><span>抵押信息</span>有抵押 19万元 中国银行四川分行 业主自还</dd>
                    <dd><span>房本备件</span>已上传房本照片</dd>
                </dl>
            </dl>
            <div class="proList-con-war">
                特别提示：本房源所示信息仅供参考，购房时以改房屋档案登记信息、产权证信息以及所签订合同条款约定为准；本房源公示信息不作为合同条款，不具有合同约束力。
            </div>
            <img :src="item.imageUrl" v-for="item in houseImage1List" :key="item.id" style="width: 430px;height: 290px;"/>
        </div><!--proList/-->
        <!--每一个选项卡-->
        <div class="proList">
            <dl class="proList-con clearf">
                <dt>房源特色</dt>
                <dd>
                    <a href="#" class="proList-con-icon">满五年</a>
                    <a href="#" class="proList-con-icon">随时看房</a>
                    <a href="#" class="proList-con-icon">VR看房</a>
                </dd>
            </dl>
            <dl class="proList-con clearf">
                <dt>小区介绍</dt>
                <dd>
                    中国央企电建开发的，实力雄厚，品质保证。小区保安24小时巡逻，大门和楼栋均设有门禁，居住安全有保障。小区实行人车分流，配套健身设施齐全，老人和孩子可以安心享受居住环境。小区物业为开发商自己物业人员
                </dd>
            </dl>
            <dl class="proList-con clearf">
                <dt>核心卖点</dt>
                <dd>
                    本房满五年，卧室带有阳台，对小区中庭，采光好户型方正
                </dd>
            </dl>
            <dl class="proList-con clearf">
                <dt>周边配套</dt>
                <dd>
                    小区门口有多家商场，特色小吃众多，满足您绝大多数需求。1公里左右的师大现代广场休闲娱乐设施众多，充分满足您的娱乐选择。200米外即是金茶路菜市，居家买菜方便快捷。小区对门即是市政公园，在晚饭之余可以和家人朋友一期散步休憩，享受休闲。

                </dd>
            </dl>
            <dl class="proList-con clearf">
                <dt>交通出行</dt>
                <dd>
                    距离大面铺地铁站3.5公里（来源于百度地图）。川师成龙校区西门公交车站距离小区306米（来源于百度地图），有856路、898路。龙安村招呼站距离小区200米（来源于百度地图），有332路，313路。交通线路多，直达地铁站口，出行便捷
                </dd>
            </dl>
            <div class="proList-con-war">
                注：1.房源介绍中的周边配套、在建设施、规划设施、地铁信息、绿化率、得房率、容积率等信息为通过物业介绍、房产证、实勘、政府官网等渠道获取，因时间、政策会发生变化，与实际情况可能略有偏差，房源介绍仅供参考。
                2.房源介绍中与距离相关的数据均来源于百度地图。 3.土地使用起止年限详见业主土地证明材料或查询相关政府部门的登记文件。
            </div>
        </div><!--proList/-->
        <!--每一个选项卡-->
        <div class="proList">
            <div class="proList-fm">
                <img src="/static/images/standard_f1ba9c2f-a917-421d-ad0f-2a6048a0d0d7.jfif" alt="">
            </div>
            <div class="proList-fd">
                <table>
                    <tr>
                        <td>房间名</td>
                        <td>平方</td>
                        <td>朝向</td>
                        <td>窗户</td>
                    </tr>
                    <tr>
                        <td>客厅</td>
                        <td>29.76平方米</td>
                        <td>无</td>
                        <td>未知窗户类型</td>
                    </tr>
                    <tr>
                        <td>卧室A</td>
                        <td>10平方米</td>
                        <td>无</td>
                        <td>未知窗户类型</td>
                    </tr>
                    <tr>
                        <td>卧室B</td>
                        <td>13.06平方米</td>
                        <td>北</td>
                        <td>普通窗</td>
                    </tr>
                    <tr>
                        <td>卧室C</td>
                        <td>7.72平方米</td>
                        <td>西</td>
                        <td>落地窗</td>
                    </tr>
                    <tr>
                        <td>厨房</td>
                        <td>5.45平方米</td>
                        <td>北</td>
                        <td>普通窗</td>
                    </tr>
                    <tr>
                        <td>卫生间</td>
                        <td>4.38平方米</td>
                        <td>南</td>
                        <td>普通窗</td>
                    </tr>
                    <tr>
                        <td>阳台A</td>
                        <td>2.57平方米</td>
                        <td>北</td>
                        <td>普通窗</td>
                    </tr>
                    <tr>
                        <td>阳台B</td>
                        <td>4.81平方米</td>
                        <td>北 东</td>
                        <td>普通窗</td>
                    </tr>
                </table>
            </div>
            <div class="clears"></div>
        </div><!--proList/-->
        <!--每一个选项卡-->
        <div class="proList">
            <dl class="proList-jingjiL clearf">
                <dt>
                    <img src="/static/images/d61bd0db-9b94-4199-85e1-8360606f9c99.jpg.480x640.jpg.55x55.jpg" alt="">
                </dt>
                <dd>
                    <div>
                        <a href="#">王琢</a>
                        <span>4008897069转34851</span>
                    </div>
                    <p>
                        房屋所在楼盘电建地产云立方，我带看过此房，了解房屋相关信息。房屋三梯八户，，产权面积88平米，装修三房，卧室有阳台周边配套齐全，生活、出行便利。更多详情，欢迎来电咨询。竭诚为您服务，只为您找到满意的家！
                    </p>
                    <div>
                        2022/01/13 带客户看过此房，共带看本房3次
                    </div>
                </dd>
            </dl>
            <dl class="proList-jingjiL clearf">
                <dt>
                    <img src="/static/images/adb503d4-3b05-4574-a61a-e5efbd39ec47.png.480x640.jpg.55x55.jpg" alt="">
                </dt>
                <dd>
                    <div>
                        <a href="#">文辉</a>
                        <span>4008896851转37783</span>
                    </div>
                    <p>
                        云立方套三单卫，低楼层，简单装修，对小区中庭，客厅带飘窗，主卧室带阳台，户型方正，有钥匙，可以实地看房。
                    </p>
                    <div>
                        2022/01/01 带客户看过此房，共带看本房1次
                    </div>
                </dd>
            </dl>
            <dl class="proList-jingjiL clearf">
                <dt>
                    <img src="/static/images/832c9fdc-e770-416d-8ae4-cc17e294049e.jpg.480x640.jpg.55x55.jpg" alt="">
                </dt>
                <dd>
                    <div>
                        <a href="#">常新文</a>
                        <span>4008897038转86910</span>
                    </div>
                    <p>
                        本房满五年，卧室带有阳台，对小区中庭，采光好户型方正
                    </p>
                    <div>
                        2021/12/26 带客户看过此房，共带看本房1次
                    </div>
                </dd>
            </dl>
        </div><!--proList/-->
    </div><!--proBox/-->

    <div class="footer">
        <div class="width1190">
            <div class="fl"><a href="index.html"><strong>尚好房</strong></a><a href="about.html">关于我们</a><a
                    href="contact.html">联系我们</a><a href="follow.html">个人中心</a></div>
            <div class="fr">
                <dl>
                    <dt><img src="/static/images/erweima.png" width="76" height="76"/></dt>
                    <dd>微信扫一扫<br/>房价点评，精彩发布</dd>
                </dl>
                <dl>
                    <dt><img src="/static/images/erweima.png" width="76" height="76"/></dt>
                    <dd>微信扫一扫<br/>房价点评，精彩发布</dd>
                </dl>
                <div class="clears"></div>
            </div>
            <div class="clears"></div>
        </div><!--width1190/-->
    </div><!--footer/-->
    <div class="copy">Copyright@ 2020 尚好房 版权所有 沪ICP备1234567号-0&nbsp;&nbsp;&nbsp;&nbsp;技术支持：XXX</div>
    <div class="bg100"></div>
</div>
<script>
    new Vue({
        el: '#item',

        data: {
            id: null,
            house: {},
            community: {},
            houseBroker: {},
            houseImage1List: [],
            isFollow: false,

            isLoad: false
        },

        created() {
            this.init()
        },

        mounted () {
            const timer = setInterval(() => {
                // 图片加载成功，再去初始化轮播图
                if(this.isLoad) {
                this.runSwiper()
                clearInterval(timer);
            }
        }, 500);
        },

        methods: {
            runSwiper() {
                var swiper = new Swiper(".mySwiper", {
                    spaceBetween: 10,
                    slidesPerView: 4,
                    freeMode: true,
                    watchSlidesProgress: true
                })
                new Swiper(".mySwiper2", {
                    spaceBetween: 10,
                    navigation: {
                        nextEl: ".swiper-button-next",
                        prevEl: ".swiper-button-prev"
                    },
                    thumbs: {
                        swiper: swiper
                    }
                })
            },

            init() {
                //获取到当前房源的id值
                this.id = util.getQueryVariable("id")
                this.fetchData()
            },

            fetchData() {
                var that = this
                //发送异步请求到后台，需要服务器查询出，当前房源数据，小区数据，经纪人数据，房源图片，是否关注(写死未关注)
                axios.get('/house/info/'+this.id).then(function (response) {
                    that.house = response.data.data.house
                    that.community = response.data.data.community
                    that.houseBroker = response.data.data.houseBrokerList.length > 0 ? response.data.data.houseBrokerList[0] : '',
                    that.houseImage1List = response.data.data.houseImage1List
                    that.isFollow = response.data.data.isFollow
                    that.isLoad = true
                });
            }
        }
    })
</script>
</body>
</html>
```



### 7.3.2准备后端接口

HouseController添加内容

```java
    @DubboReference
    private CommunityService communityService;

    @DubboReference
    private HouseBrokerService houseBrokerService;

    @DubboReference
    private HouseImageService houseImageService;


    /**
     * 房源详情页面的详细信息
     */
    @RequestMapping("/info/{houseId}")
    public Result info(@PathVariable Long houseId){
        //当前房源的信息(内部关于数据字典的信息已完成转化)
        House house = houseService.getById(houseId);
        //当前房源小区的信息(内部关于数据字典的信息已完成转化)
        Community community = communityService.getById(house.getCommunityId());

        List<HouseBroker> houseBrokerList = houseBrokerService.findBrokerByHouseId(houseId);

        List<HouseImage> houseImage1List = houseImageService.findImageByHouseIdAndType(houseId, 1);

        //是否关注了该房源，目前先写死，等登陆讲完后，再去判断
        Boolean isFollow=false;

        Map<String,Object> map=new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseImage1List",houseImage1List);
        map.put("isFollow",isFollow);

        return Result.ok(map);
    }
```





# 8 前端登录、注册与关注房源

## 8.1注册

### 8.1.1搭建service_user模块

以service模块为父工程，在service模块下创建service_user模块，搭建过程与service_user一致

#### 8.1.1.1模块架构

模块的目录及文件架构如下

- web_front
  - pom.xml（打包方式设置为war，修改jetty端口）⚠️
  - src/main/
    - java
      - com.atguigu.dao
      - com.atguigu.service.impl
    - resources
      - mapper
      - spring
        - spring-mvc.xml
        - spring-registry.xml（修改提供者名字和端口）⚠️
        - spring-service.xml
      - logback.xml
      - db.properties
      - mybaties-config.xml
    - webapp
      - WEB-INF
        - web.xml



#### 8.1.1.2pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>service</artifactId>
        <groupId>com.atguigu</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>service_user</artifactId>
    <packaging>war</packaging>

    <build>
        <plugins>
            <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.15.v20190215</version>
                <configuration>
                    <!-- 如果检测到项目有更改则自动热部署，每隔n秒扫描一次。默认为0，即不扫描-->
                    <scanIntervalSeconds>10</scanIntervalSeconds>
                    <webAppConfig>
                        <!--指定web项目的根路径，默认为/ -->
                        <contextPath>/</contextPath>
                    </webAppConfig>
                    <httpConnector>
                        <!--端口号，默认 8080-->
                        <port>7003</port>
                    </httpConnector>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



#### 8.1.1.3spring-registry.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 定义服务提供者名称名称 -->
    <dubbo:application name="service_user"/>

    <!--指定暴露服务的端口，如果不指定默认为20880 -->
    <dubbo:protocol name="dubbo" port="20883"/>

    <!--指定服务注册中心地址-->
    <dubbo:registry address="zookeeper://localhost:2181"/>

    <!--批量扫描，发布服务-->
    <dubbo:annotation package="com.atguigu"/>
</beans>
```



### 8.1.2短信验证

#### 8.1.2.1创建注册页面

在webapp下创建register.html文件

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="Author" contect="http://www.webqin.net">
    <title>尚好房</title>
    <link rel="shortcut icon" href="/static//static/images/favicon.ico"/>
    <link type="text/css" href="/static/css/css.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/js.js"></script>
    <script src="/static/js/vue.js"></script>
    <script src="/static/js/axios.js"></script>
    <script type="text/javascript">
        $(function () {
            //导航定位
            $(".nav li:eq(6)").addClass("navCur");
        })
    </script>
</head>

<body>
<div id="register">
    <div class="header">
        <div class="width1190">
            <div class="fl">您好，欢迎来到尚好房！</div>
            <div class="fr">
                <a href="login.html">登录</a> |
                <a href="register.html">注册</a> |
                <a href="javascript:;">加入收藏</a> |
                <a href="javascript:;">设为首页</a>
            </div>
            <div class="clears"></div>
        </div><!--width1190/-->
    </div>
    <div class="list-nav">
        <div class="width1190">
            <div class="list"><h3>房源分类</h3></div><!--list/-->
            <ul class="nav">
                <li><a href="index.html">首页</a></li>
                <li><a href="about.html">关于我们</a></li>
                <li><a href="contact.html">联系我们</a></li>
                <div class="clears"></div>
            </ul><!--nav/-->
            <div class="clears"></div>
        </div><!--width1190/-->
    </div><!--list-nav/-->
    <div class="banner" style="background:url(/static/images/ban.jpg) center center no-repeat;"></div>

    <div class="content">
        <div class="width1190">
            <div class="reg-logo">
                <form id="signupForm" method="post" action="" class="zcform">
                    <p class="clearfix">
                        <label class="one" for="agent">手机号码：</label>
                        <input id="agent" v-model="registerVo.phone" type="text" class="required" style="width: 200px;" maxlength="11" placeholder="请输入您的手机号码"/>
                        <input type="button" :value="codeTest" @click="getCodeFun()" style="padding: 5px 5px;width: 100px;height: 48px;"/>
                    </p>
                    <p class="clearfix">
                        <span style="color: red;margin-left: 90px;">{{valid.phone}}</span>
                    </p>
                    <p class="clearfix">
                        <label class="one" for="agent">验证码：</label>
                        <input id="agent" v-model="registerVo.code" type="text" class="required" maxlength="4" value placeholder="请输入手机验证码"/>
                    </p>
                    <p class="clearfix">
                        <span style="color: red;margin-left: 90px;">{{valid.code}}</span>
                    </p>
                    <p class="clearfix">
                        <label class="one" for="password">登录密码：</label>
                        <input id="password" v-model="registerVo.password" type="password" maxlength="9"
                               class="{required:true,rangelength:[8,20],}" value placeholder="请输入密码"/>
                    </p>
                    <p class="clearfix">
                        <span style="color: red;margin-left: 90px;">{{valid.password}}</span>
                    </p>
                    <p class="clearfix">
                        <label class="one" for="confirm_password">确认密码：</label>
                        <input id="confirm_password" v-model="registerVo.confirmPassword"  type="password" maxlength="9"
                               class="{required:true,equalTo:'#password'}" value placeholder="请再次输入密码"/>
                    </p>
                    <p class="clearfix">
                        <span style="color: red;margin-left: 90px;">{{valid.confirmPassword}}</span>
                    </p>
                    <p class="clearfix">
                        <label class="one" for="agent">昵称：</label>
                        <input id="agent" v-model="registerVo.nickName" type="text" maxlength="10" class="required" value placeholder="请输入您的昵称"/>
                    </p>
                    <p class="clearfix">
                        <span style="color: red;margin-left: 90px;">{{valid.nickName}}</span>
                    </p>
                    <!--<p class="clearfix agreement">
                        <input type="checkbox" />
                        <b class="left">已阅读并同意<a href="#">《用户协议》</a></b>
                    </p>-->
                    <p class="clearfix"><input class="submit" type="button" @click="submitRegister" value="立即注册"/></p>
                </form>
                <div class="reg-logo-right">
                    <h3>如果您已有账号，请</h3>
                    <a href="login.html" class="logo-a">立即登录</a>
                </div><!--reg-logo-right/-->
                <div class="clears"></div>
            </div><!--reg-logo/-->
        </div><!--width1190/-->
    </div><!--content/-->

    <div class="footer">
        <div class="width1190">
            <div class="fl"><a href="index.html"><strong>尚好房</strong></a><a href="about.html">关于我们</a><a
                    href="contact.html">联系我们</a><a href="follow.html">个人中心</a></div>
            <div class="fr">
                <dl>
                    <dt><img src="/static/images/erweima.png" width="76" height="76"/></dt>
                    <dd>微信扫一扫<br/>房价点评，精彩发布</dd>
                </dl>
                <dl>
                    <dt><img src="/static/images/erweima.png" width="76" height="76"/></dt>
                    <dd>微信扫一扫<br/>房价点评，精彩发布</dd>
                </dl>
                <div class="clears"></div>
            </div>
            <div class="clears"></div>
        </div><!--width1190/-->
    </div><!--footer/-->
    <div class="copy">Copyright@ 2020 尚好房 版权所有 沪ICP备1234567号-0&nbsp;&nbsp;&nbsp;&nbsp;技术支持：XXX</div>
    <div class="bg100"></div>
</div>
<script>
    var app =new Vue({
        el: '#register',

        data: {
            //包含着注册信息的对象，发起异步注册请求时主要传递该对象
            registerVo: {
                phone: '',
                password: '',
                confirmPassword: '',
                nickName: '',
                code: ''
            },

            valid: {
                phone: '',
                password: '',
                confirmPassword: '',
                nickName: '',
                code: ''
            },

            sending: true,     //是否发送验证码
            second: 60,        //倒计时间
            codeTest: '获取验证码'
        },

        mounted () {
        },

        methods: {
            submitRegister() {
                var isValid = true
                if(!(/^1[3456789]\d{9}$/.test(this.registerVo.phone))) {
                    this.valid.phone = '手机号码不正确'
                    isValid = false
                }
                if(this.registerVo.code == '') {
                    this.valid.code = '验证码必须输入';
                    isValid = false
                }
                if(this.registerVo.password == '') {
                    this.valid.password = '密码必须输入';
                    isValid = false
                }
                if(this.registerVo.password != this.registerVo.confirmPassword) {
                    this.valid.confirmPassword = '密码不一致';
                    isValid = false
                }
                if(this.registerVo.nickName == '') {
                    this.valid.nickName = '昵称必须输入';
                    isValid = false
                }
                if(!isValid) {
                    return
                }
                var that = this
                axios.post('/userInfo/register', this.registerVo).then(function (response) {
                    if(response.data.code == 200){
                        window.location.href = 'login.html'
                    }else{
                        alert(response.data.message);
                    }
                });

            },

            getCodeFun() {
                if (!this.sending)
                    return;

                if (!(/^1[3456789]\d{9}$/.test(this.registerVo.phone))) {
                    alert('手机号码不正确');
                    return;
                }

                var that = this
                axios.get('/userInfo/sendCode/'+this.registerVo.phone).then(function (response) {
                    that.sending = false;
                    //按照常理这一步没有，是用户查看短信，手动将验证码输入到文本框
                    that.registerVo.code = response.data.data
                    that.timeDown();
                });
            },

            timeDown() {
                let result = setInterval(() => {
                --this.second;
                this.codeTest = this.second
                if (this.second < 1) {
                    clearInterval(result);
                    this.sending = true;
                    this.second = 60;
                    this.codeTest = "获取验证码"
                }
            }, 1000);
            }
        }
    })
</script>
</body>
</html>
```



#### 8.1.2.2准备后端接口

UserInfoController

```java
package com.atguigu.controller;

import com.atguigu.result.Result;
import com.atguigu.service.UserInfoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.UserInfoController
 */
@Controller
@RequestMapping("/userInfo")
@ResponseBody
public class UserInfoController {

    /**
     * 处理/sendCode/phone路径，用户输入手机号后获取验证码的请求
     * 此处模拟8888验证码返回给用户， 正常情况下应该以短信的形式，并且将code放在会话域
     */
    @RequestMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone, HttpSession session){
        //真实环境就是一个4位或者6位的随机数
        //如果是真实环境，需要将code发送到用户的手机上，并且将code放在会话域(后续验证验证码是否正确)
        String code="8888";
        //现在是模拟，将code响应给前台，还是将验证码放在会话域
        session.setAttribute("code",code);
        return Result.ok(code);
    }

}
```



### 8.1.3注册用户操作

#### 8.1.3.1后端serviceAPI

UserInfoService

```java
package com.atguigu.service;

import com.atguigu.entity.UserInfo;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.UserInfoService
 */
public interface UserInfoService extends BaseService<UserInfo> {

    /**
     * 通过手机号码获取用户的详细信息，注册操作中判断手机号是否重复
     */
    UserInfo findUserInfoByPhone(String phone);
}
```



#### 8.1.3.2后端dao层

UserInfoDao

```java
package com.atguigu.dao;

import com.atguigu.entity.UserInfo;


public interface UserInfoDao extends BaseDao<UserInfo>{

    /**
     * 通过手机号码获取用户的详细信息，注册操作中判断手机号是否重复
     */
    UserInfo findUserInfoByPhone(String phone);

}
```

UserInfoMapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.UserInfoDao">

    <!--通过手机号码获取用户的详细信息，注册操作中判断手机号是否重复-->
    <select id="findUserInfoByPhone" resultType="userInfo">
        select * from user_info where phone=#{phone} and is_deleted=0
    </select>

    <!--新增一个实例-->
    <insert id="insert">
        insert into user_info(phone,nick_name,status,password)
        values(#{phone},#{nickName},#{status},#{password})
    </insert>

</mapper>
```



#### 8.1.3.3后端service层

UserInfoServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserInfoDao;
import com.atguigu.entity.UserInfo;
import com.atguigu.service.UserInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


@DubboService
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public BaseDao<UserInfo> getEntityDao() {
        return userInfoDao;
    }

    /**
     * 通过手机号码获取用户的详细信息，注册操作中判断手机号是否重复
     */
    @Override
    public UserInfo findUserInfoByPhone(String phone) {
        return userInfoDao.findUserInfoByPhone(phone);
    }

}
```



#### 8.1.3.4后端controller层

UserInfoController添加内容

```java
package com.atguigu.controller;

import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserInfoService;
import com.atguigu.util.MD5;
import com.atguigu.vo.RegisterVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.UserInfoController
 */
@Controller
@RequestMapping("/userInfo")
@ResponseBody
public class UserInfoController {

    @DubboReference
    private UserInfoService userInfoService;

    /**
     * 处理/sendCode/phone路径，用户输入手机号后获取验证码的请求
     * 此处模拟8888验证码返回给用户， 正常情况下应该以短信的形式，并且将code放在会话域
     */
    @RequestMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone, HttpSession session){
        //真实环境就是一个4位或者6位的随机数
        //如果是真实环境，需要将code发送到用户的手机上，并且将code放在会话域(后续验证验证码是否正确)
        String code="8888";
        //现在是模拟，将code响应给前台，还是将验证码放在会话域
        session.setAttribute("code",code);
        return Result.ok(code);
    }


    /**
     * 处理/register路径，用户注册操作
     * registerVo请求参数包裹着注册所需的全部信息
     */
    @RequestMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo, HttpSession session){
        //1. 获取到注册的数据(code/手机号/密码/昵称)
        String code = registerVo.getCode();
        String phone = registerVo.getPhone();
        String password = registerVo.getPassword();
        String nickName = registerVo.getNickName();
        //2. 校验参数是否为空
        if(StringUtils.isEmpty(code)||StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)||StringUtils.isEmpty(nickName)){
            //若有空参数，直接给前台一个参数错误203响应
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }
        //3. 校验验证码是否正确
        Object trueCode = session.getAttribute("code");
        if(!trueCode.equals(code)){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }
        //4. 校验手机号是否重复(根据phone，去数据库做二次查询)
        UserInfo userInfo = userInfoService.findUserInfoByPhone(phone);
        if(userInfo!=null){
            //若不为空，则查询到了实例对象，说明手机号已被使用
            return Result.build(null,ResultCodeEnum.PHONE_REGISTER_ERROR);
        }

        //5. 将数据保存到数据库即可
        UserInfo info=new UserInfo();
        info.setPhone(phone);
        info.setNickName(nickName);
        //使用MD5对密码进行加密
        info.setPassword(MD5.encrypt(password));
        //status不设置也行，因为默认值为1。1表示该用户锁定，0表示该用户正常
        info.setStatus(1);

        //新增操作
        userInfoService.insert(info);

        return Result.ok();
    }

}
```

·

#### 8.1.3.5创建登录页面

在webapp下创建login.html文件，先不添加任何内容，作为注册成功后的跳转页面

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
</head>
<body>
<h1>登录页面</h1>
</body>
</html>
```



## 8.2登录

### 8.2.1准备前端资源

#### 8.2.1.1修改login页面

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="Author" contect="http://www.webqin.net">
    <title>尚好房</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico"/>
    <link type="text/css" href="/static/css/css.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/js.js"></script>
    <script src="/static/js/vue.js"></script>
    <script src="/static/js/axios.js"></script>
    <script src="/static/js/util.js"></script>
    <script type="text/javascript">
        $(function () {
            //导航定位
            $(".nav li:eq(6)").addClass("navCur");
        })
    </script>
</head>

<body>
<div id="login">
    <div class="header">
        <div class="width1190">
            <div class="fl">您好，欢迎来到尚好房！</div>
            <!--判断用户未登录显示的样式-->
            <div class="fr" v-if="userInfo.nickName==''">
                <a href="login.html">登录</a> |
                <a href="register.html">注册</a> |
                <a href="javascript:;">加入收藏</a> |
                <a href="javascript:;">设为首页</a>
            </div>
            <!--判断用户登录后显示的样式-->
            <div class="fr" v-else>
                <a href="javascript:;">欢迎 {{ userInfo.nickName }}</a> |
                <a href="javascript:;" @click="logout">退出</a> |
                <a href="follow.html">我的关注</a> |
                <a href="javascript:;">加入收藏</a> |
                <a href="javascript:;">设为首页</a>
            </div>
            <div class="clears"></div>
        </div><!--width1190/-->
    </div>
    <div class="list-nav">
        <div class="width1190">
            <div class="list"><h3>房源分类</h3></div><!--list/-->
            <ul class="nav">
                <li><a href="index.html">首页</a></li>
                <li><a href="about.html">关于我们</a></li>
                <li><a href="contact.html">联系我们</a></li>
                <div class="clears"></div>
            </ul><!--nav/-->
            <div class="clears"></div>
        </div><!--width1190/-->
    </div><!--list-nav/-->
    <div class="banner" style="background:url(/static/images/ban.jpg) center center no-repeat;"></div>

    <div class="content">
        <div class="width1190">
            <div class="reg-logo">
                <form id="signupForm" method="post" action="follow.html" class="zcform">
                    <p class="clearfix">
                        <label class="one" for="agent">用户名：</label>
                        <input id="agent" v-model="loginVo.phone" type="text" class="required" value placeholder="请输入您的用户名"/>
                    </p>
                    <p class="clearfix">
                        <span style="color: red;margin-left: 90px;">{{valid.phone}}</span>
                    </p>
                    <p class="clearfix">
                        <label class="one" for="password">登录密码：</label>
                        <input id="password" v-model="loginVo.password" type="password"
                               class="{required:true,rangelength:[8,20],}" value placeholder="请输入密码"/>
                    </p>
                    <p class="clearfix">
                        <span style="color: red;margin-left: 90px;">{{valid.password}}</span>
                    </p>
                    <!--<p class="clearfix agreement">
                        <input type="checkbox" />
                        <b class="left">已阅读并同意<a href="#">《用户协议》</a></b>
                    </p>-->
                    <p class="clearfix"><input class="submit" type="button" @click="submitLogin()" value="立即登录"/></p>
                </form>
                <div class="reg-logo-right">
                    <h3>如果您没有账号，请</h3>
                    <a href="register.html" class="logo-a">立即注册</a>
                </div><!--reg-logo-right/-->
                <div class="clears"></div>
            </div><!--reg-logo/-->
        </div><!--width1190/-->
    </div><!--content/-->

    <div class="footer">
        <div class="width1190">
            <div class="fl"><a href="index.html"><strong>尚好房</strong></a><a href="about.html">关于我们</a><a
                    href="contact.html">联系我们</a><a href="follow.html">个人中心</a></div>
            <div class="fr">
                <dl>
                    <dt><img src="/static/images/erweima.png" width="76" height="76"/></dt>
                    <dd>微信扫一扫<br/>房价点评，精彩发布</dd>
                </dl>
                <dl>
                    <dt><img src="/static/images/erweima.png" width="76" height="76"/></dt>
                    <dd>微信扫一扫<br/>房价点评，精彩发布</dd>
                </dl>
                <div class="clears"></div>
            </div>
            <div class="clears"></div>
        </div><!--width1190/-->
    </div><!--footer/-->
    <div class="copy">Copyright@ 2020 尚好房 版权所有 沪ICP备1234567号-0&nbsp;&nbsp;&nbsp;&nbsp;技术支持：XXX</div>
    <div class="bg100"></div>
</div>
<script>
    var app =new Vue({
        el: '#login',

        data: {
            //存储登录请求中的用户信息
            loginVo: {
                phone: '',
                password: ''
            },

            //存储登录成功后的用户信息
            valid: {
                phone: '',
                password: ''
            },

            //存储从localStorage获取的登录的用户信息
            userInfo:{
                nickName:''
            },
        },

        created(){
            this.login()
        },
        methods: {
            login(){
                var userInfoString = window.localStorage.getItem("userInfo")
                if(userInfoString != null && userInfoString != '') {
                    this.userInfo = JSON.parse(userInfoString)
                }
            },
            logout() {
                axios.get('/userInfo/logout').then(function (response) {
                    window.localStorage.setItem("userInfo", '')
                    window.location.href = 'index.html'
                });
            },
            submitLogin() {
                var isValid = true
                if(this.loginVo.phone == '') {
                    this.valid.password = '用户名必须输入';
                    isValid = false
                }
                if(this.loginVo.password == '') {
                    this.valid.password = '密码必须输入';
                    isValid = false
                }
                if(!isValid) {
                    return
                }
                var that = this
                //异步登录请求，若登录成功将返回的信息保存在本地localStorage中
                axios.post('/userInfo/login', this.loginVo).then(function (response) {
                    //判断响应信息的状态码是否为200
                    if(response.data.code == 200){
                        //将请求返回的登录信息保存到的浏览器本地，以键值对的形式存储
                        window.localStorage.setItem("userInfo", JSON.stringify(response.data.data))

                        //获取登录前的页面面，即从哪个页面点的登录
                        var originUrl = util.getOriginUrl()
                        if(originUrl != '') {
                            //若获取到登录前的页面，就跳转到该页面
                            window.location.href = originUrl
                        } else {
                            //若没有获取到，就去到首页
                            window.location.href = "index.html"
                        }
                    }else{
                        //如果状态码不等于200，就弹出错误提示
                        alert(response.data.message);
                    }
                });
            }
        }
    })
</script>
</body>
</html>
```



#### 8.2.1.2修改index页面

根据判断是否登录修改顶部样式

```html
<div class="width1190">
    <div class="fl">您好，欢迎来到尚好房！</div>
    <!--判断用户未登录显示的样式-->
    <div class="fr" v-if="userInfo.nickName==''">
        <a href="login.html">登录</a> |
        <a href="register.html">注册</a> |
        <a href="javascript:;">加入收藏</a> |
        <a href="javascript:;">设为首页</a>
    </div>
    <!--判断用户登录后显示的样式-->
    <div class="fr" v-else>
        <a href="javascript:;">欢迎 {{ userInfo.nickName }}</a> |
        <a href="javascript:;" @click="logout">退出</a> |
        <a href="follow.html">我的关注</a> |
        <a href="javascript:;">加入收藏</a> |
        <a href="javascript:;">设为首页</a>
    </div>
    <div class="clears"></div>
</div><!--width1190/-->
```

添加Vue对象中的data数据、created钩子函数和methods方法

```javascript
new Vue({
  el: '#list',
  data: {
    areaList: [],
    plateList: [],
    houseTypeList: [],
    floorList: [],
    buildStructureList: [],
    directionList: [],
    decorationList: [],
    houseUseList: [],

    //接口当前页的数据，存储分页信息
    page: {
      list: [],
      pageNum: 1,
      pageSize: 2, //方便测试分页
      pages: 1,
      //导航页码
      navigatepageNums: [1, 2, 3, 4],
      //上一页
      prePage: 0,
      //下一页
      nextPage: 0,
      //是否为首页
      hasPreviousPage: false,
      //是否为尾页
      hasNextPage: false
    },

    //存储查询条件的Json对象，存储用户点击的搜索项⚠️
    //该Json对象会传到后端dao层，dao层判断是否为空串进行拼接查询条件
    houseQueryVo: {
      areaId: '',
      plateId: '',
      houseTypeId: '',
      floorId: '',
      buildStructureId: '',
      directionId: '',
      decorationId: '',
      houseUseId: '',
      //排序规则相关属性
      defaultSort: 1,
      priceSort: null,
      timeSort: null,
    },

    //存储从localStorage获取的登录的用户信息
    userInfo:{
      nickName:''
    },
  },
  created() {
    //异步获取页面所以搜索项数据
    this.fetchDictData()
    //默认刚进入页码显示全部房源，发起一次异步请求
    this.fetchData(1)
    //默认进入页面检验是否已登录
    this.login();
  },
  methods: {
    //检验是否登录，去.localStorage获取登录信息，若有登录信息就赋给data中的userInfo对象
    login(){
      var userInfoString = window.localStorage.getItem("userInfo")
      if(userInfoString != null && userInfoString != '') {
        this.userInfo = JSON.parse(userInfoString)
      }
    },
    //登出当前用户，从localStorage移除登录信息，并跳转到首页
    logout() {
      axios.get('/userInfo/logout').then(function (response) {
        //移除掉本地的userInfo数据
        window.localStorage.setItem("userInfo", '')
        //并且回到首页
        window.location.href = 'index.html'
      });
    },
    fetchDictData() {
      //axios在then的内部不能使用Vue的实例化的this, 因为在内部 this 没有被绑定
      var that = this
      axios.get('/dict/findListByDictCode/beijing').then(function (response) {
        that.areaList = response.data.data
      });
      axios.get('/dict/findListByDictCode/houseType').then(function (response) {
        that.houseTypeList = response.data.data
      });
      axios.get('/dict/findListByDictCode/floor').then(function (response) {
        that.floorList = response.data.data
      });
      axios.get('/dict/findListByDictCode/buildStructure').then(function (response) {
        that.buildStructureList = response.data.data
      });
      axios.get('/dict/findListByDictCode/direction').then(function (response) {
        that.directionList = response.data.data
      });
      axios.get('/dict/findListByDictCode/decoration').then(function (response) {
        that.decorationList = response.data.data
      });
      axios.get('/dict/findListByDictCode/houseUse').then(function (response) {
        that.houseUseList = response.data.data
      });
    },

    //房源区域的二级搜索
    searchArea(id) {
      this.houseQueryVo.areaId = id
      this.houseQueryVo.plateId = ''
      this.fetchData(1)

      //如果点击的是不限，则进入到if内，不再发请求，直接return
      if (id == '') {
        this.plateList = []
        return
      }
      var that = this
      axios.get('/dict/findListByParentId/' + id).then(function (response) {
        that.plateList = response.data.data
      });
    },

    //存储查询条件值，用户点击的搜索项触发的方法
    searchPlate(id) {
      this.houseQueryVo.plateId = id
      this.fetchData(1)
    },
    searchHouseType(id) {
      this.houseQueryVo.houseTypeId = id
      this.fetchData(1)
    },
    searchFloor(id) {
      this.houseQueryVo.floorId = id
      this.fetchData(1)
    },
    searchBuildStructure(id) {
      this.houseQueryVo.buildStructureId = id
      this.fetchData(1)
    },
    searchDirection(id) {
      this.houseQueryVo.directionId = id
      this.fetchData(1)
    },
    searchDecoration(id) {
      this.houseQueryVo.decorationId = id
      this.fetchData(1)
    },
    searchHouseUse(id) {
      this.houseQueryVo.houseUseId = id
      this.fetchData(1)
    },

    //用户选择的排序规则：默认/单价/时间
    sortDefault() {
      this.houseQueryVo.defaultSort = 1
      this.houseQueryVo.priceSort = null
      this.houseQueryVo.timeSort = null
      this.fetchData(1)
    },
    sortPrice() {
      this.houseQueryVo.defaultSort = null
      this.houseQueryVo.priceSort = 1
      this.houseQueryVo.timeSort = null
      this.fetchData(1)
    },
    sortTime() {
      this.houseQueryVo.defaultSort = null
      this.houseQueryVo.priceSort = null
      this.houseQueryVo.timeSort = 1
      this.fetchData(1)
    },

    //无论点击哪个搜索项，都会调用该方法向后端发起异步查询请求⚠️
    //houseQueryVo对象是搜索条件对象以及分页信息
    fetchData(pageNum = 1) {
      this.page.pageNum = pageNum
      if (pageNum < 1) pageNum = 1
      //axios在then的内部不能使用Vue的实例化的this, 因为在内部 this 没有被绑定
      var that = this
      axios.post('/house/list/' + pageNum + '/' + this.page.pageSize, this.houseQueryVo).then(function (response) {
        //后端返回的pageInfo对象，包含着全部的渲染列表信息及分页信息，赋值给Vue的page对象，通过page渲染数据⚠️
        that.page = response.data.data
      });
    },
  }
})
```



#### 8.2.1.3修改register页面

根据判断是否登录修改顶部样式

```html
<div class="width1190">
    <div class="fl">您好，欢迎来到尚好房！</div>
    <!--判断用户未登录显示的样式-->
    <div class="fr" v-if="userInfo.nickName==''">
        <a href="login.html">登录</a> |
        <a href="register.html">注册</a> |
        <a href="javascript:;">加入收藏</a> |
        <a href="javascript:;">设为首页</a>
    </div>
    <!--判断用户登录后显示的样式-->
    <div class="fr" v-else>
        <a href="javascript:;">欢迎 {{ userInfo.nickName }}</a> |
        <a href="javascript:;" @click="logout">退出</a> |
        <a href="follow.html">我的关注</a> |
        <a href="javascript:;">加入收藏</a> |
        <a href="javascript:;">设为首页</a>
    </div>
    <div class="clears"></div>
</div><!--width1190/-->
```

添加Vue对象中的data数据、created钩子函数和methods方法

```javascript
var app =new Vue({
  el: '#register',

  data: {
    //包含着注册信息的对象，发起异步注册请求时主要传递该对象
    registerVo: {
      phone: '',
      password: '',
      confirmPassword: '',
      nickName: '',
      code: ''
    },

    valid: {
      phone: '',
      password: '',
      confirmPassword: '',
      nickName: '',
      code: ''
    },

    sending: true,     //是否发送验证码
    second: 60,        //倒计时间
    codeTest: '获取验证码',

    //存储从localStorage获取的登录的用户信息
    userInfo: {
      nickName: ''
    },
  },

  created() {
    //默认进入页面检验是否已登录
    this.login();
  },

  methods: {
    //检验是否登录，去.localStorage获取登录信息，若有登录信息就赋给data中的userInfo对象
    login() {
      var userInfoString = window.localStorage.getItem("userInfo")
      if (userInfoString != null && userInfoString != '') {
        this.userInfo = JSON.parse(userInfoString)
      }
    },
    //登出当前用户，从localStorage移除登录信息，并跳转到首页
    logout() {
      axios.get('/userInfo/logout').then(function (response) {
        //移除掉本地的userInfo数据
        window.localStorage.setItem("userInfo", '')
        //并且回到首页
        window.location.href = 'index.html'
      });
    },
    submitRegister() {
      var isValid = true
      if(!(/^1[3456789]\d{9}$/.test(this.registerVo.phone))) {
        this.valid.phone = '手机号码不正确'
        isValid = false
      }
      if(this.registerVo.code == '') {
        this.valid.code = '验证码必须输入';
        isValid = false
      }
      if(this.registerVo.password == '') {
        this.valid.password = '密码必须输入';
        isValid = false
      }
      if(this.registerVo.password != this.registerVo.confirmPassword) {
        this.valid.confirmPassword = '密码不一致';
        isValid = false
      }
      if(this.registerVo.nickName == '') {
        this.valid.nickName = '昵称必须输入';
        isValid = false
      }
      if(!isValid) {
        return
      }
      var that = this
      axios.post('/userInfo/register', this.registerVo).then(function (response) {
        if(response.data.code == 200){
          window.location.href = 'login.html'
        }else{
          alert(response.data.message);
        }
      });

    },

    getCodeFun() {
      if (!this.sending)
        return;

      if (!(/^1[3456789]\d{9}$/.test(this.registerVo.phone))) {
        alert('手机号码不正确');
        return;
      }

      var that = this
      axios.get('/userInfo/sendCode/'+this.registerVo.phone).then(function (response) {
        that.sending = false;

        //为验证码赋值，实际开发环境中是用户查看短信，手动输入验证码⚠️
        that.registerVo.code = response.data.data
        that.timeDown();
      });
    },

    timeDown() {
      let result = setInterval(() => {
        --this.second;
        this.codeTest = this.second
        if (this.second < 1) {
          clearInterval(result);
          this.sending = true;
          this.second = 60;
          this.codeTest = "获取验证码"
        }
      }, 1000);
    }
  }
})
```



#### 8.2.1.2修改info页面

根据判断是否登录修改顶部样式

```html
<div class="width1190">
    <div class="fl">您好，欢迎来到尚好房！</div>
    <!--判断用户未登录显示的样式-->
    <div class="fr" v-if="userInfo.nickName==''">
        <a href="login.html">登录</a> |
        <a href="register.html">注册</a> |
        <a href="javascript:;">加入收藏</a> |
        <a href="javascript:;">设为首页</a>
    </div>
    <!--判断用户登录后显示的样式-->
    <div class="fr" v-else>
        <a href="javascript:;">欢迎 {{ userInfo.nickName }}</a> |
        <a href="javascript:;" @click="logout">退出</a> |
        <a href="follow.html">我的关注</a> |
        <a href="javascript:;">加入收藏</a> |
        <a href="javascript:;">设为首页</a>
    </div>
    <div class="clears"></div>
</div><!--width1190/-->
```

添加Vue对象中的data数据、created钩子函数和methods方法

```javascript
new Vue({
  el: '#item',
  data: {
    id: null,
    house: {},
    community: {},
    houseBroker: {},
    houseImage1List: [],
    //是否已关注该房源
    isFollow: false,

    //轮播图的图片是否加载完成
    isLoad: false,

    //存储从localStorage获取的登录的用户信息
    userInfo: {
      nickName: ''
    },
  },
  created() {
    //发起异步请求，获取当前房源的详细信息并渲染
    this.init()
    //默认进入页面检验是否已登录
    this.login();
  },
  mounted() {
    const timer = setInterval(() => {
      // 图片加载成功，再去初始化轮播图
      if (this.isLoad) {
        this.runSwiper()
        clearInterval(timer);
      }
    }, 500);
  },
  methods: {
    //检验是否登录，去.localStorage获取登录信息，若有登录信息就赋给data中的userInfo对象
    login() {
      var userInfoString = window.localStorage.getItem("userInfo")
      if (userInfoString != null && userInfoString != '') {
        this.userInfo = JSON.parse(userInfoString)
      }
    },
    //登出当前用户，从localStorage移除登录信息，并跳转到首页
    logout() {
      axios.get('/userInfo/logout').then(function (response) {
        //移除掉本地的userInfo数据
        window.localStorage.setItem("userInfo", '')
        //并且回到首页
        window.location.href = 'index.html'
      });
    },
    runSwiper() {
      var swiper = new Swiper(".mySwiper", {
        spaceBetween: 10,
        slidesPerView: 4,
        freeMode: true,
        watchSlidesProgress: true
      })
      new Swiper(".mySwiper2", {
        spaceBetween: 10,
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev"
        },
        thumbs: {
          swiper: swiper
        }
      })
    },
    init() {
      //获取到当前房源的id值
      this.id = util.getQueryVariable("id")
      this.fetchData()
    },
    //查询当前房源详细信息
    fetchData() {
      var that = this
      //发送异步请求到后台，需要服务器查询出，当前房源数据，小区数据，经纪人数据，房源图片，是否关注(写死未关注)
      axios.get('/house/info/' + this.id).then(function (response) {
        that.house = response.data.data.house
        that.community = response.data.data.community
        that.houseBroker = response.data.data.houseBrokerList.length > 0 ? response.data.data.houseBrokerList[0] : '',
          that.houseImage1List = response.data.data.houseImage1List
        that.isFollow = response.data.data.isFollow
        that.isLoad = true
      });
    }
  }
})
```



### 8.2.2准备后端接口

UserInfoController添加内容

```java
/**
 * 处理/login路径，用户登录操作
 * 先校验，通过校验后再将信息放到会话域一份，并返回给前端一份
 */
@RequestMapping("/login")
public Result login(@RequestBody LoginVo loginVo, HttpSession session){
    //1. 获取前端请求参数，手机号和密码
    String phone = loginVo.getPhone();
    String password = loginVo.getPassword();//password是明文
    //2. 前端请求参数的非空校验
    if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)){
        return Result.build(null,ResultCodeEnum.PARAM_ERROR);
    }
    //3. 验证用户名是否正确(根据phone去查询UserInfo对象)
    UserInfo userInfo = userInfoService.findUserInfoByPhone(phone);
    if(userInfo==null){
        return Result.build(null,ResultCodeEnum.ACCOUNT_ERROR);
    }
    //4. 验证登录密码是否正确，注册时使用MD5加密，相同的明文多次加密的结果是一样的
    //还有其他加密方式，这里必须根据注册时的加密方式来编写对应的校验方式
    if(!userInfo.getPassword().equals(MD5.encrypt(password))){
        //返回密码不正确的相关验证码和信息
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }
    //5. 验证用户是否被锁定(status是否是1)
    if(userInfo.getStatus()==0){
        //返回用户锁定的相关验证码和信息
        return Result.build(null,ResultCodeEnum.ACCOUNT_LOCK_ERROR);
    }
    //6. 将当前登录人信息保存在会话域
    session.setAttribute("userInfo",userInfo);

    //7. 将用户信息(手机号、昵称)响应给前端
    Map<String,Object> map=new HashMap<>();
    map.put("phone",phone);
    map.put("nickName",userInfo.getNickName());

    return Result.ok(map);//这里返回200的响应
}


/**
 * 处理/logout路径，用户登出请求
 */
@RequestMapping("/logout")
public Result logout(HttpSession session){
    //从会话域中删除用户信息
    session.removeAttribute("userInfo");
    return Result.ok();
}
```



## 8.3关注房源

### 8.3.1实现关注房源功能

#### 8.3.1.1修改info页面

添加Vue对象的methods属性中的方法

```javascript
//关注当前房源
follow() {
  var that = this
  //之所以多加一级/auth，是为了添加拦截器做准备
  axios.get('/userFollow/auth/follow/'+this.id).then(function (response) {
    //如果没登录，拦截器会返回208状态，跳转登录页面
    if (response.data.code == 208) {
      window.location.href = 'login.html?originUrl='+window.location.href
    } else {
      that.isFollow = true
    }
  });
}
```

#### 8.3.1.2后端ServiceAPI

UserFollowService

```java
package com.atguigu.service;

import com.atguigu.entity.UserFollow;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.UserFollowService
 */
public interface UserFollowService extends BaseService<UserFollow> {

    /**
     * 实现用户关注房源
     */
    void follow(Long userId,Long houseId);

    /**
     * 获取用户是否关注该房源的信息
     */
    UserFollow findFollowByUserAndHouse(Long userId, Long houseId);
}
```



#### 8.3.1.3后端dao层

UserFollowDao

```java
package com.atguigu.dao;

import com.atguigu.entity.UserFollow;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.UserFollowDao
 */
public interface UserFollowDao extends BaseDao<UserFollow> {

    /**
     * 获取用户是否关注该房源的信息
     */
    UserFollow findFollowByUserAndHouse(@Param("userId") Long userId, @Param("houseId") Long houseId);
}
```

UserFollowMapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.UserFollowDao">

    <!--向中间表插入信息，实现关注功能-->
    <insert id="insert">
        insert into user_follow(user_id,house_id)
        values(#{userId},#{houseId})
    </insert>

    <!--查询当前用户是否关注该房源信息-->
    <select id="findFollowByUserAndHouse" resultType="userFollow">
        select * from user_follow where user_id=#{userId} and house_id=#{houseId} and is_deleted=0
    </select>

</mapper>
```



#### 8.3.1.4后端service层

UserFollowServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserFollowDao;
import com.atguigu.entity.UserFollow;
import com.atguigu.service.UserFollowService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


@DubboService
public class UserFollowServiceImpl extends BaseServiceImpl<UserFollow> implements UserFollowService {

    @Autowired
    private UserFollowDao userFollowDao;

    
    @Override
    public BaseDao<UserFollow> getEntityDao() {
        return userFollowDao;
    }
    

    /**
     * 实现用户关注该房源
     */
    @Override
    public void follow(Long userId, Long houseId) {
        UserFollow userFollow=new UserFollow();
        userFollow.setHouseId(houseId);
        userFollow.setUserId(userId);
        userFollowDao.insert(userFollow);
    }

    /**
     * 查找当前用户是否关注了该房源的信息
     */
    @Override
    public UserFollow findFollowByUserAndHouse(Long userId, Long houseId) {
        return userFollowDao.findFollowByUserAndHouse(userId,houseId);
    }

}
```



#### 8.3.1.5后端controller层

HouseController修改内容

```java
@DubboReference
private UserFollowService userFollowService;

/**
 * 房源详情页面的详细信息
 */
@RequestMapping("/info/{houseId}")
public Result info(@PathVariable Long houseId, HttpSession session){
    //当前房源的信息(内部关于数据字典的信息已完成转化)
    House house = houseService.getById(houseId);
    //当前房源小区的信息(内部关于数据字典的信息已完成转化)
    Community community = communityService.getById(house.getCommunityId());

    List<HouseBroker> houseBrokerList = houseBrokerService.findBrokerByHouseId(houseId);

    List<HouseImage> houseImage1List = houseImageService.findImageByHouseIdAndType(houseId, 1);

    //从会话域中取出当前登录的用户信息
    UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
    //是否关注了该房源，默认赋值为未关注，即不登录用户显示未关注
    Boolean isFollow=false;
    //若会话域中有登录的用户信息，就发起二次查询，获取真实的关注信息
    if(userInfo!=null){
        UserFollow userFollow = userFollowService.findFollowByUserAndHouse(userInfo.getId(), houseId);
        if(userFollow!=null){
            //若关注信息的中间表对象不为空，则说明该用户关注了该房源
            isFollow=true;
        }
    }

    Map<String,Object> map=new HashMap<>();
    map.put("house",house);
    map.put("community",community);
    map.put("houseBrokerList",houseBrokerList);
    map.put("houseImage1List",houseImage1List);
    map.put("isFollow",isFollow);

    return Result.ok(map);
}
```

UserFollowController

```java
package com.atguigu.controller;

import com.atguigu.entity.UserInfo;
import com.atguigu.result.Result;
import com.atguigu.service.UserFollowService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.UserFollowController
 */
@Controller
@RequestMapping("/userFollow")
@ResponseBody
public class UserFollowController {

    @DubboReference
    private UserFollowService userFollowService;

    /**
     * 处理/auth/follow/id路径，用户关注当前房源的请求
     */
    @RequestMapping("/auth/follow/{houseId}")
    public Result follow(@PathVariable Long houseId, HttpSession session){
        //目前先保持手动登录，确保请求域中有用户信息，一会再去实现拦截器
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        userFollowService.follow(userInfo.getId(),houseId);
        return Result.ok();
    }
}
```



## 8.4拦截器

### 8.4.1添加拦截器

创建com.atguigu.interceptor包，然后创建拦截器的实现类LoginInterceptor，核心在于WebUtil工具类的使用

```java
package com.atguigu.interceptor;

import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.util.WebUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.interceptor.LoginInterceptor
 */
public class LoginInterceptor implements HandlerInterceptor {
  
  	/**
     * 重写拦截器preHandle方法，拦截对应的URL后通过判断会话域中有无登录的用户信息决定是否放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证是否处于登录状态
        HttpSession session = request.getSession();
        //从请求域中获取当前登录用户
        Object userInfo = session.getAttribute("userInfo");
        //如果没获取到，说明为空，需要跳转到登录页面
        if(userInfo==null){
            //这里处理到是异步请求不是Servlet，不可以用重定向或转发直接跳转⚠️
            //解决方案：借助工具类WebUtil返回Json对象，并响应给前端208用户未登录状态码
            //注意：还需要在spring-mvc.xml还需要配置拦截器的拦截路径
            WebUtil.writeJSON(response, Result.build(null, ResultCodeEnum.LOGIN_AUTH));
            return false;
        }
        return true;
    }
}
```



### 8.4.2注册拦截器

在spring-mvc.xml中注册拦截器

```xml
<!--配置拦截器的拦截路径，只要URI包含auth就拦截-->
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/**/auth/**"/>
        <bean class="com.atguigu.interceptor.LoginInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```



## 8.5管理已关注房源

### 8.5.1准备前端资源

#### 8.5.1.1创建我的关注页面

在webapp目录下创建follow.html

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="Author" contect="http://www.webqin.net">
    <title>尚好房</title>
    <link rel="shortcut icon" href="/static/images/favicon.ico"/>
    <link type="text/css" href="/static/css/css.css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/js/js.js"></script>
    <script src="/static/js/vue.js"></script>
    <script src="/static/js/axios.js"></script>
    <script type="text/javascript">
        $(function () {
            //导航定位
            $(".nav li:eq(6)").addClass("navCur");
        })
    </script>
</head>

<body>
<div id="follow">
    <div class="header">
        <div class="width1190">
            <div class="fl">您好，欢迎来到尚好房！</div>
            <!--判断用户未登录显示的样式-->
            <div class="fr" v-if="userInfo.nickName==''">
                <a href="login.html">登录</a> |
                <a href="register.html">注册</a> |
                <a href="javascript:;">加入收藏</a> |
                <a href="javascript:;">设为首页</a>
            </div>
            <!--判断用户登录后显示的样式-->
            <div class="fr" v-else>
                <a href="javascript:;">欢迎 {{ userInfo.nickName }}</a> |
                <a href="javascript:;" @click="logout">退出</a> |
                <a href="follow.html">我的关注</a> |
                <a href="javascript:;">加入收藏</a> |
                <a href="javascript:;">设为首页</a>
            </div>
            <div class="clears"></div>
        </div><!--width1190/-->
    </div>
    <div class="list-nav">
        <div class="width1190">
            <div class="list">
                <h3>房源分类</h3>
            </div><!--list/-->
            <ul class="nav">
                <li><a href="index.html">首页</a></li>
                <li><a href="about.html">关于我们</a></li>
                <li><a href="contact.html">联系我们</a></li>
                <div class="clears"></div>
            </ul><!--nav/-->
            <div class="clears"></div>
        </div><!--width1190/-->
    </div><!--list-nav/-->
    <div class="banner" style="background:url(/static/images/ban.jpg) center center no-repeat;"></div>

    <div class="content">
        <div class="width1190">
            <div class="vip-left">
                <div class="vipNav">
                    <h3 class="vipTitle">会员中心</h3>
                    <dl>
                        <dt class="vipIcon3">账户设置</dt>
                        <dd>
                            <a href="javascript:;">我的资料</a>
                            <a href="javascript:;">账户密码设置</a>
                        </dd>
                        <dt class="vipIcon1">我的尚好房</dt>
                        <dd>
                            <a href="follow.html" class="vipNavCur">关注房源</a>
                            <a href="javascript:;">申请社区自由经纪人</a>
                            <a href="javascript:;">社区自由经纪人</a>
                        </dd>
                    </dl>
                </div><!--vipNav/-->
            </div><!--vip-left/-->
            <div class="vip-right">
                <h3 class="vipright-title">关注房源</h3>
                <ul class="guanzhueq">
                    <li class="guanzhueqcur"><a href="javascript:;">二手房</a></li>
                    <div class="clearfix"></div>
                </ul><!--guanzhueq/-->
                <div class="guanzhulist">
                    <dl v-for="item in page.list" :key="item.id">
                        <dt><a :href="'info.html?id='+item.houseId"><img :src="item.defaultImageUrl" width="190"
                                                                         height="128"/></a></dt>
                        <dd>
                            <h3><a :href="'info.html?id='+item.houseId">{{ item.name }}</a></h3>
                            <div class="guantext">{{ item.buildArea }}平 {{ item.houseTypeName}} {{ item.floorName}} {{
                                item.directionName}}
                            </div>
                            <div class="guantext2">关注时间：{{ item.createTimeString}}
                                <a href="javascript:;" class="qxgz" @click="cancelFollow(item.id)">取消关注</a></div>
                        </dd>
                        <div class="price">¥ <strong>{{ item.totalPrice }}</strong><span class="font12">万元</span></div>
                        <div class="clearfix"></div>
                    </dl>
                </div><!--guanzhulist/-->
                <div class="clears"></div>
                <ul class="pages">
                    <li>
                        <a href="javascript:;" @click="fetchData(page.prePage)" v-if="page.hasPreviousPage">上一页</a>
                    </li>
                    <li v-for="item in page.navigatepageNums" :class="item==page.pageNum ? 'page_active' : ''">
                        <a href="javascript:;" @click="fetchData(item)">{{ item }}</a>
                    </li>
                    <li>
                        <a href="javascript:;" @click="fetchData(page.nextPage)" v-if="page.hasNextPage">下一页</a>
                    </li>
                </ul>
            </div><!--vip-right/-->
            <div class="clearfix"></div>
        </div><!--width1190/-->
    </div><!--content/-->

    <div class="footer">
        <div class="width1190">
            <div class="fl"><a href="index.html"><strong>尚好房</strong></a><a href="about.html">关于我们</a><a
                    href="contact.html">联系我们</a><a href="follow.html">个人中心</a></div>
            <div class="fr">
                <dl>
                    <dt><img src="/static/images/erweima.png" width="76" height="76"/></dt>
                    <dd>微信扫一扫<br/>房价点评，精彩发布</dd>
                </dl>
                <dl>
                    <dt><img src="/static/images/erweima.png" width="76" height="76"/></dt>
                    <dd>微信扫一扫<br/>房价点评，精彩发布</dd>
                </dl>
                <div class="clears"></div>
            </div>
            <div class="clears"></div>
        </div><!--width1190/-->
    </div><!--footer/-->
    <div class="copy">Copyright@ 2020 尚好房 版权所有 沪ICP备1234567号-0&nbsp;&nbsp;&nbsp;&nbsp;技术支持：XXX</div>
    <div class="bg100"></div>
</div>
<script src="/static/js/api/userFollow.js"></script>
<script src="/static/js/api/user.js"></script>
<script>
    var app = new Vue({
        el: '#follow',

        data: {
            //接口返回的分页数据，在此声明
            page: {
                list: [],
                pageNum: 1,
                pageSize: 2,
                pages: 1,
                navigatepageNums: [1, 2, 3, 4],
                prePage: 0,
                nextPage: 0,
                hasPreviousPage: false,
                hasNextPage: false
            },

            userInfo: {
                nickName: ''
            }
        },

        mounted() {
            //首次进入页面渲染房源信息
            this.fetchData(1)
            //获取登录信息
            this.login()
        },

        methods: {
            //获取登录信息
            login() {
                let userInfoString = window.localStorage.getItem("userInfo")
                if (userInfoString != null && userInfoString != '') {
                    this.userInfo = JSON.parse(userInfoString)
                }
            },
            //登出方法
            logout() {
                axios.get('/userInfo/logout').then(function (response) {
                    window.localStorage.setItem("userInfo", '')
                    window.location.href = 'index.html'
                });
            },
            //查询当前用户关注的所有房源信息
            fetchData(pageNum = 1) {
                if (pageNum < 1) pageNum = 1
                if (pageNum >= this.pages) pageNum = this.pages

                var that = this
                axios.get('/userFollow/auth/list/' + pageNum + '/' + this.page.pageSize).then(function (response) {
                    //如果没登录，拦截器会返回208状态，跳转登录页面
                    if (response.data.code == 208) {
                        window.location.href = 'login.html?originUrl=' + window.location.href
                    } else {
                        that.page = response.data.data
                    }
                });
            },
            //取消关注的方法
            cancelFollow(id) {
                var that = this
                axios.get('/userFollow/auth/cancelFollow/' + id).then(response => {
                    //如果没登录，拦截器会返回208状态，跳转到登录页面
                    if (response.data.code == 208) {
                        window.location.href = 'login.html?originUrl=' + window.location.href
                    } else {
                        //如果登录就重新获取一次当前用户关注的房源信息
                        that.fetchData(1)
                    }
                })
            }
        }
    })
</script>
</body>
</html>
```



### 8.5.2解决跨服务调用

因为粒度较大，导致无法在service_user中调用service_house的dao层

#### 8.5.2.1解决方案

1. 在dao层中重写DictDao（代码重复，不推荐）
2. 直接调用ServiceAPI，即消费DictService服务，使用其方法，使当前服务即是提供者又是消费者⚠️（推荐）



#### 8.5.2.2ServiceAPI

service_house模块的DictService添加内容

```java
/**
 * 为service_user服务：根据id获取其在字典中对应的name
 */
String getNameById(Long id);
```



#### 8.5.2.3service层

service_house模块的DictServiceImpl添加内容

```java
/**
 * 为service_user提供服务：根据id获取其在字典中对应的name
 */
@Override
public String getNameById(Long id){
    return dictDao.getNameById(id);
}
```



### 8.5.3准备后端资源

#### 8.5.3.1ServiceAPI

UserFollowService添加内容

```java
/**
 * 查询当前登录用户的所有关注信息，UserFollowVo中包含了所有信息，最终以分页对象的形式返回
 */
PageInfo<UserFollowVo> findUserFollow(Integer pageNum, Integer pageSize, Long userId);
```



#### 8.5.3.2dao层

UserFollowDao添加内容

```java
/**
 * 三表联查，查询当前登录用户的所有关注信息，UserFollowVo中包含了所有信息，返回该对象的List集合即可
 */
List<UserFollowVo> findUserFollow(Long userId);
```

UserFollowMapper添加内容

```xml
<!--查询当前登录用户的所有关注信息，UserFollowVo中包含了所有信息-->
<select id="findUserFollow" resultType="userFollowVo">
    SELECT uf.id,uf.house_id,uf.create_time,
    hc.name communityName,
    hh.name,hh.build_area,hh.total_price,hh.default_image_url,hh.house_type_id,hh.floor_id,hh.direction_id
    FROM user_follow uf LEFT JOIN hse_house hh
    ON uf.house_id = hh.id LEFT JOIN hse_community hc
    ON hh.community_id=hc.id
    WHERE user_id=#{userId}
    AND uf.is_deleted=0
    AND hh.is_deleted=0
    AND hc.is_deleted=0
</select>

<!--删除当前用户关注的房源信息-->
<update id="delete">
    update user_follow set is_deleted=1 where id=#{id}
</update>
```



#### 8.5.3.3service层

UserFollowServiceImpl添加内容

```java
    //不在同一个服务内，因此无法使用DictDao，只能从注册中心获取DictService对象来使用其方法⚠️
    @DubboReference
    private DictService dictService;

    /**
     * 查询当前登录用户的所有关注信息，UserFollowVo中包含了所有信息，最终以PageInfo对象的Json形式返回给前端
     */
    @Override
    public PageInfo<UserFollowVo> findUserFollow(Integer pageNum, Integer pageSize, Long userId) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserFollowVo> userFollowVoList = userFollowDao.findUserFollow(userId);
        for (UserFollowVo userFollowVo : userFollowVoList) {
            //因为粒度还是比较大，所以无法使用service_house内的DictDao的内容
            //解决方案：⚠️
            //1. 在dao层中重写DictDao（代码重复，不推荐）
            //2. 直接调用ServiceAPI，即消费DictService服务，使用其方法，使当前服务即是提供者又是消费者⚠️
            //service_user不仅仅是提供者，还是一个消费者(允许的)
            userFollowVo.setHouseTypeName(dictService.getNameById(userFollowVo.getHouseTypeId()));
            userFollowVo.setFloorName(dictService.getNameById(userFollowVo.getFloorId()));
            userFollowVo.setDirectionName(dictService.getNameById(userFollowVo.getDirectionId()));
        }
        return new PageInfo<>(userFollowVoList,3);
    }
```



#### 8.5.3.4controller层

UserFollowController添加内容

```java
/**
 * 处理/auth/list/pageNum/pageSize路径，获取用户所有关注的房源
 * 先从会话域中获取当前用户的登录信息，再以分页对象的形式返还给前端
 */
@RequestMapping("/auth/list/{pageNum}/{pageSize}")
public Result list(@PathVariable Integer pageNum,@PathVariable Integer pageSize,HttpSession session){
    //查询当前登录人关注的房源信息(带分页)
    UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
    PageInfo<UserFollowVo> userFollowVoList = userFollowService.findUserFollow(pageNum, pageSize, userInfo.getId());
    return Result.ok(userFollowVoList);
}

/**
 * 处理/auth/cancelFollow/id路径，取消关注房源
 */
@RequestMapping("/auth/cancelFollow/{userFollowId}")
public Result cancelFollow(@PathVariable Long userFollowId){
    userFollowService.delete(userFollowId);
    return Result.ok();
}
```



## 8.6使用阿里云短信服务

### 8.6.1导入相关依赖

父工程shf_parent

```xml
<aliyun.version>2.0.22</aliyun.version>
<jedis.version>2.9.0</jedis.version>
```

```xml
<!--阿里云短信服务-->
<dependency>
    <groupId>com.aliyun</groupId>
    <artifactId>alibabacloud-dysmsapi20170525</artifactId>
    <version>${aliyun.version}</version>
</dependency>

<!--Redis-->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>${jedis.version}</version>
</dependency>
```

子模块common_util

```xml
<!--阿里云短信-->
<dependency>
    <groupId>com.aliyun</groupId>
    <artifactId>alibabacloud-dysmsapi20170525</artifactId>
</dependency>

<!--Redis-->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>
```



### 8.6.2封装阿里云工具类

SendSms

```java
package com.atguigu.util;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponseBody;
import darabonba.core.client.ClientOverrideConfiguration;
import darabonba.core.exception.TeaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.util.AliSmsUtil
 */
public class SendSms {

    private static String accessKeyId = "LTAI5t9v9gFPqHrTtTbwGQux";
    private static String accessKeySecret = "XLkWChxUC6xk2rtKG0ADV22LkUsot3";


    public static Boolean sendSms(String phone, String code) {

        // 配置凭据身份验证信息
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder().accessKeyId(accessKeyId).accessKeySecret(accessKeySecret).build());

        // 配置阿里云客户端
        AsyncClient client = AsyncClient.builder()
                .region("cn-beijing").credentialsProvider(provider)
                .overrideConfiguration(ClientOverrideConfiguration.create().setEndpointOverride("dysmsapi.aliyuncs.com"))
                .build();

        // API参数：签名名称、模板Code、手机号、验证码
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("李德水个人网站")
                .templateCode("SMS_262450421")
                .phoneNumbers(phone)
                .templateParam("{\"code\":\"" + code + "\"}")
                .build();

        // 异步获取API请求的返回值
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);

        // 处理发送结果
        try {
            SendSmsResponseBody body = response.get().getBody();
            Logger logger = LoggerFactory.getLogger(SendSms.class);
            if (body.getCode().equals("OK")){
                logger.info("用户验证码发送成功");
                return true;
            }else {
                logger.error(body.getMessage());
            }
        } catch (TeaException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭客户端
            client.close();
        }
        return false;
    }
}

```



### 8.6.3封装Redis工具类

JedisPoolUtil

```java
package com.atguigu.util;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.util.JedisPoolUtil
 */


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil() {
    }

    public static JedisPool getJedisPoolInstance() {
        if (null == jedisPool) {
            synchronized (JedisPoolUtil.class) {
                if (null == jedisPool) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    //最大连接数, 默认8个
                    poolConfig.setMaxTotal(200);
                    //最大空闲连接数,默认8个
                    poolConfig.setMaxIdle(32);
                    //获取连接时的最大等待毫秒数,如果超时就抛异常
                    poolConfig.setMaxWaitMillis(100 * 1000);
                    //连接超时时是否阻塞，false时报异常,ture阻塞直到超时, 默认true
                    poolConfig.setBlockWhenExhausted(true);

                    //测试ping pong
                    poolConfig.setTestOnBorrow(true);

                    //传入Redis数据库信息，包括ip、端口、超时时间、密码
                    jedisPool = new JedisPool(poolConfig, "39.106.35.112", 6379, 60000, "abc123");
                }
            }
        }
        return jedisPool;
    }

    public static void release(JedisPool jedisPool, Jedis jedis) {
        if (null != jedis) {
            jedisPool.returnResource(jedis);
        }
    }

}
```

RedisUtil

```java
package com.atguigu.util;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.util.RedisTemplate
 */
@EnableScheduling
@EnableAsync
public class RedisUtil {

    //查找验证码
    public static Map<String, Object> findCode(String phone) {
        Jedis jedis = null;
        try {
            //从连接池获取Jedis对象
            JedisPool pool = JedisPoolUtil.getJedisPoolInstance();
            jedis = pool.getResource();

            //选择数据库
            jedis.select(0);

            //对redis进行操作
            //获取value的值
            String code = jedis.get(phone);
            //获取v的过期时间
            Long timeout = jedis.ttl(phone);

            //返回指定格式
            HashMap<String, Object> result = new HashMap<>();
            result.put("code",code);
            result.put("timeout",timeout);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            jedis.close();
        }
        return null;
    }

    //插入验证码
    public static Boolean addCode(String phone, String code, Integer timeout) {
        Jedis jedis = null;
        try {
            //从连接池获取Jedis对象
            JedisPool pool = JedisPoolUtil.getJedisPoolInstance();
            jedis = pool.getResource();

            //选择数据库
            jedis.select(0);

            //通过代码对redis进行操作
            jedis.setex(phone, timeout, code);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            jedis.close();
        }
        return false;
    }

}
```



### 8.6.4修改后端资源

UserInfoController修改内容

```java
    /**
     * 处理/sendSms/phone路径，调用阿里云短信服务器发送短信
     */
    @RequestMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone, HttpSession session) {
        //先判断redis内有验证码，有的话就不发送了
        Map redisInfo = RedisUtil.findCode(phone);
        Long time = (Long) redisInfo.get("timeout");
        if (time != -2) {
            return Result.ok(time);
        } else {
            //生成验证码并放到redis中
            Integer hashCode = UUID.randomUUID().toString().hashCode();
            // String.hashCode()可能会是负数，如果为负数需要转换为正数
            hashCode = hashCode < 0 ? -hashCode : hashCode;
            //截取六位数验证码
            String code = hashCode.toString().substring(0, 6);
            //设置超时时间，单位(秒)
            Integer timeout = 60;
            //向用户发送验证码
            Boolean sendOk = SendSms.sendSms(phone, code);
            if (sendOk) {
                //发送成功，在redis中存储验证码
                RedisUtil.addCode(phone, code, timeout);
            } else {
                System.out.println("发送失败");
            }
        }
        return Result.ok(false);
    }


    /**
     * 处理/register路径，用户注册操作
     * registerVo请求参数包裹着注册所需的全部信息
     */
    @RequestMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo, HttpSession session) {
        //1. 获取到注册的数据(code/手机号/密码/昵称)
        String code = registerVo.getCode();
        String phone = registerVo.getPhone();
        String password = registerVo.getPassword();
        String nickName = registerVo.getNickName();
        //2. 校验参数是否为空
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || StringUtils.isEmpty(nickName)) {
            //若有空参数，直接给前台一个参数错误203响应
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }
        //3. 校验验证码是否正确
        //先根据手机号获取验证码，然后再判断
        Map redisInfo = RedisUtil.findCode(phone);
        String trueCode = (String) redisInfo.get("code");
        if (!code.equals(trueCode)) {
            return Result.build(null, ResultCodeEnum.CODE_ERROR);
        }
        //4. 校验手机号是否重复(根据phone，去数据库做二次查询)
        UserInfo userInfo = userInfoService.findUserInfoByPhone(phone);
        if (userInfo != null) {
            //若不为空，则查询到了实例对象，说明手机号已被使用
            return Result.build(null, ResultCodeEnum.PHONE_REGISTER_ERROR);
        }
        //5. 将数据保存到数据库即可
        UserInfo info = new UserInfo();
        info.setPhone(phone);
        info.setNickName(nickName);
        //使用MD5对密码进行加密
        info.setPassword(MD5.encrypt(password));
        //status不设置也行，因为默认值为1。1表示该用户锁定，0表示该用户正常
        info.setStatus(1);

        //新增操作
        userInfoService.insert(info);

        return Result.ok();
    }
```



### 8.6.5修改前端资源

注册页面regist.html的内容，修改验证码长度为6位数！修改密码和确认密码长度为16位数，修改发送手机验证码的异步函数！⚠️

```html
<p class="clearfix">
    <label class="one" for="agent">验证码：</label>
    <input id="agent" v-model="registerVo.code" type="text" class="required" maxlength="6" value placeholder="请输入手机验证码"/>
</p>
<p class="clearfix">
    <span style="color: red;margin-left: 90px;">{{valid.code}}</span>
</p>
<p class="clearfix">
    <label class="one" for="password">登录密码：</label>
    <input id="password" v-model="registerVo.password" type="password" maxlength="16"
           class="{required:true,rangelength:[8,20],}" value placeholder="请输入密码"/>
</p>
<p class="clearfix">
    <span style="color: red;margin-left: 90px;">{{valid.password}}</span>
</p>
<p class="clearfix">
    <label class="one" for="confirm_password">确认密码：</label>
    <input id="confirm_password" v-model="registerVo.confirmPassword"  type="password" maxlength="16"
           class="{required:true,equalTo:'#password'}" value placeholder="请再次输入密码"/>
</p>
```

```java
var that = this
axios.get('/userInfo/sendCode/'+this.registerVo.phone).then(function (response) {
    if(response.data.data){
        that.second = response.data.data;
    }
    that.sending = false;
    that.timeDown();
});
```





# 9 权限管理

## 9.1给用户分配角色

操作的服务提供者模块是service_acl，操作的服务消费者模块是web_admin

### 9.1.1准备web资源

#### 9.1.1.1修改index

修改admin/index，添加分配角色的按钮和js触发事件

```html
 <a class="assign" th:attr="data-id=${item.id}">分配角色</a>
```

```javascript
$(".assign").on("click",function () {
  var id = $(this).attr("data-id");
  opt.openWin('/admin/assignShow/'+id,'分配角色',550,450)
});
```



#### 9.1.1.2创建分配角色页面

创建admin/assignShow.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>

<style type="text/css">
    select option{
        width:260px;
        height:25px;
        line-height:25px;
        padding: 5px 5px;
    }
</style>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <form id="ec" th:action="@{/admin/assignRole}" method="post" class="form-horizontal">
            <!--将用户id放在隐藏域内-->
            <input type="hidden" name="adminId" th:value="${adminId}">
            <!--计划存储多个角色id，用户选择的多个角色id-->
            <input type="hidden" name="roleIds" id="roleIds" value="">
            <div style="text-align: center;padding-left: 20px;">
                <div id="s1" style="float: left;">
                    <div style="font-weight:900;">未选择</div>
                    <select id="select1" multiple="multiple" style="width: 220px;height: 280px;overflow-y:auto;" ondblclick="funRight()">
                        <option th:each="item: ${noAssignRoleList}" th:value="${item.id}" th:text="${item.roleName}">11</option>
                    </select>
                </div>
                <div style="float: left;padding-top:120px;">
                    <br />
                    <button type="button" id="right"> &gt;&gt; </button><br /><br />

                    <button type="button" id="left">  &lt;&lt; </button>

                </div>
                <div id="s2" style="float: left;">
                    <div style="font-weight:900;">已选择</div>
                    <select id="select2" multiple="multiple" style="width: 220px;height: 280px;overflow-y:auto;" ondblclick="funLeft()">
                        <option th:each="item: ${assignRoleList}" th:value="${item.id}" th:text="${item.roleName}">11</option>
                    </select>
                </div>

                <div class="form-group" style="clear: left;padding-top: 20px;">
                    <button type="button" class="btn btn-sm btn-primary " onclick="add()" style="margin-left: 10px;"> 保存</button>
                    <button type="button" class="btn btn-sm btn-primary " onclick="cancel()" style="margin-left: 10px;"> 重置</button>
                    <button class="btn btn-sm btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button>
                </div>
                <br/>
            </div>
        </form>
    </div>
</div>
<script th:inline="javascript">
    $(function(){
        $("#right").on("click",function() {
            $("#select1 option").each(function(index, item){
                if(item.selected == true){
                    document.getElementById("select2").appendChild(item);
                }
            });
        });

        $("#left").on("click",function() {
            $("#select2 option").each(function(index, item){
                if(item.selected == true){
                    document.getElementById("select1").appendChild(item);
                }
            });
        });
    });

    function funRight() {
        $("#right").trigger("click");
    }

    function funLeft() {
        $("#left").trigger("click");
    }

    function add() {
        var roleIds = "";//  1,8,10,
        $("#select2 option").each(function(index, item){
            roleIds += $(item).val() + ",";
        });
        //将拼接好的字符串设置给#roleIds的value属性上
        $("#roleIds").val(roleIds);
        //提交一个叫ec的表单
        document.forms.ec.submit();
    }

    function cancel() {
        window.location.reload();
    }
</script>
</body>
</html>
```



### 9.1.2准备后端数据

#### 9.1.2.1ServiceAPI

RoleService添加内容

```java
/**
 * 查询该用户已拥有和未拥有的角色列表
 * 使用集合作为返回值，可以一次性返回已拥有和未拥有两个不同的角色列表
 */
Map<String, List<Role>> findRoleByAdminId(Long adminId);
```

AdminRoleService

```java
package com.atguigu.service;

import com.atguigu.entity.AdminRole;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.AdminRoleService
 */
public interface AdminRoleService extends BaseService<AdminRole> {

    /**
     * 为当前用户添加多个角色
     */
    void insertAdminRole(Long adminId,Long[] roleIds);
}
```



#### 9.1.2.2dao层

RoleDao添加内容

```java
/**
 * 获取所有角色信息
 */
List<Role> findAll();
```

RoleMapper添加内容

```xml
<!--获取所有角色-->
<select id="findAll" resultType="role">
    <include refid="columns"></include>
    where is_deleted=0
</select>
```

AdminRoleDao

```java
package com.atguigu.dao;

import com.atguigu.entity.AdminRole;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.AdminRoleDao
 */
public interface AdminRoleDao extends BaseDao<AdminRole> {
    
    /**
     * 根据用户id获取所有角色id值集合
     */
    List<Long> findRoleIdsByAdminId(Long adminId);

    /**
     * 删除当前用户的所有角色
     */
    void deleteByAdminId(Long adminId);
}
```

AdminRoleMapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.AdminRoleDao">

   <!--根据用户id获取所有角色id值集合-->
    <select id="findRoleIdsByAdminId" resultType="long">
        select role_id from acl_admin_role where admin_id=#{adminId} and is_deleted=0
    </select>

    <!--删除用户的所有角色-->
    <update id="deleteByAdminId" >
        update acl_admin_role set is_deleted=1 where admin_id=#{adminId}
    </update>

    <!--为用户新增一个角色-->
    <insert id="insert">
        insert into acl_admin_role(role_id,admin_id)
        values(#{roleId},#{adminId})
    </insert>
</mapper>
```



#### 9.1.2.3service层

RoleServiceImpl添加内容

```java
@Autowired
private AdminRoleDao adminRoleDao;

/**
* 查询该用户已拥有和未拥有的角色列表
* 使用集合作为返回值，可以一次性返回已拥有和未拥有两个不同的角色列表
*/
@Override
public Map<String, List<Role>> findRoleByAdminId(Long adminId) {
    //1. 查询出所有的角色信息
    List<Role> roleList = roleDao.findAll();
    //2. 查询出当前用户拥有的角色id
    List<Long> roleIds = adminRoleDao.findRoleIdsByAdminId(adminId);
    //3. 循环所有的角色信息
    List<Role> noAssignRoleList=new ArrayList<>();
    List<Role> assignRoleList=new ArrayList<>();
    for (Role role : roleList) {
        //判断role的id是都在roleIds内存在
        if(roleIds.contains(role.getId())){
            //说明role是当前用户已拥有的角色
            assignRoleList.add(role);
        }else{
            //说明role是当前用户未拥有的角色
            noAssignRoleList.add(role);
        }
    }
    Map<String,List<Role>> map=new HashMap<>();
    //存储未拥有的角色列表
    map.put("noAssignRoleList",noAssignRoleList);
    //存储已拥有的角色列表
    map.put("assignRoleList",assignRoleList);
    return map;
}
```

AdminRoleServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.AdminRoleDao;
import com.atguigu.dao.BaseDao;
import com.atguigu.entity.AdminRole;
import com.atguigu.service.AdminRoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.AdminRoleServiceImpl
 */
@DubboService
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRole> implements AdminRoleService {

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Override
    public BaseDao<AdminRole> getEntityDao() {
        return adminRoleDao;
    }

    /**
     * 为当前用户添加角色
     * 添加步骤：先全部删除之前设置的角色，再循环新增现在的角色
     */
    @Override
    @Transactional
    public void insertAdminRole(Long adminId, Long[] roleIds) {
        //1. 先将adminId的所有角色删除
        adminRoleDao.deleteByAdminId(adminId);
        //2. 在循环添加新的角色信息
        for (Long roleId : roleIds) {
            if (roleId == null)
                continue;
            //创建实例对象并赋值，为新增作准备
            AdminRole adminRole = new AdminRole();
            adminRole.setRoleId(roleId);
            adminRole.setAdminId(adminId);
            adminRoleDao.insert(adminRole);
        }
    }
}
```



#### 9.1.2.4controller层

AdminController添加内容

```java
@DubboReference
private RoleService roleService;

/**
 * 处理/assignShow/id路径，跳转到添加角色页面
 */
@RequestMapping("/assignShow/{adminId}")
public String assignShow(@PathVariable Long adminId,Map map){
    map.put("adminId",adminId);
    //需要从数据库查询得到两个List集合
    //1. 当前用户未拥有的角色信息
    //2. 当前用户已拥有的角色信息
    Map<String, List<Role>> map1 = roleService.findRoleByAdminId(adminId);
    //map1中的两个对数据，需要放在请求域(将map1中的数据添加到map内)
    map.putAll(map1);
    return "admin/assignShow";
}

@DubboReference
private AdminRoleService adminRoleService;
/**
 * 为当前用户添加多个角色
 */
@RequestMapping("/assignRole")
//MVC的强大之处，使用数组接收字符串"1,2,3,"，可直接将请求参数转化为数组
public String assignRole(Long adminId,Long[] roleIds){
    System.out.println(adminId);
    System.out.println(Arrays.toString(roleIds));
    adminRoleService.insertAdminRole(adminId,roleIds);
    return PAGE_SUCCESS;
}
```



## 9.2给角色分配权限

### 9.2.1准备web资源

role/index添加内容

跳转到分配页面的按钮

```html
<a class="assign" th:attr="data-id=${item.id}">分配权限</a>
```

js触发事件

```javascript
$(".assign").on("click",function () {
    var id = $(this).attr("data-id");
    opt.openWin("/role/assignShow/"+id,'修改',580,430);
});
```

创建assignShow.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>

<link rel="stylesheet" th:href="@{/static/js/plugins/zTree_v3/zTreeStyle.css}" type="text/css">
<script type="text/javascript" th:src="@{/static/js/plugins/zTree_v3/jquery.ztree.core.js}"></script>
<script type="text/javascript" th:src="@{/static/js/plugins/zTree_v3/jquery.ztree.excheck.js}"></script>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/role/assignPermission}" method="post" class="form-horizontal">
                <input type="hidden" name="roleId" th:value="${roleId}">
                <input type="hidden" name="permissionIds" id="permissionIds" value="">
                <div class="zTreeDemoBackground left">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="button" id="button">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button></div>
                </div>
            </form>
        </div>
    </div>
</div>
<script th:inline="javascript">
    $(function(){
        // 文档地址:http://www.treejs.cn/v3/demo.php#_201
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        //var zNodes = JSON.parse([[${zNodes}]]);
        var zNodes = [[${zNodes}]];
        // var zNodes =[
        //     { id:1, pId:0, name:"随意勾选 1", open:true},
        //     { id:11, pId:1, name:"随意勾选 1-1", open:true},
        //     { id:111, pId:11, name:"随意勾选 1-1-1"},
        //     { id:112, pId:11, name:"随意勾选 1-1-2"},
        //     { id:12, pId:1, name:"随意勾选 1-2", open:true},
        //     { id:121, pId:12, name:"随意勾选 1-2-1"},
        //     { id:122, pId:12, name:"随意勾选 1-2-2"},
        //     { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
        //     { id:21, pId:2, name:"随意勾选 2-1"},
        //     { id:22, pId:2, name:"随意勾选 2-2", open:true},
        //     { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
        //     { id:222, pId:22, name:"随意勾选 2-2-2"},
        //     { id:23, pId:2, name:"随意勾选 2-3"}
        // ];

        var zTree =$.fn.zTree.init($("#treeDemo"), setting, zNodes);
        zTree.expandAll(true);
        $("#button").on("click",function () {
            var checkedNodes = zTree.getCheckedNodes();
            console.log(checkedNodes)
            var permissionIdList = [];
            for(var i=0; i<checkedNodes.length; i++) {
                permissionIdList.push(checkedNodes[i].id)
            }
            $("#permissionIds").val(permissionIdList.join(","));
            document.forms.ec.submit();
        });
    });
</script>
</body>
</html>
```



### 9.2.2准备后端数据

#### 9.2.2.1ServiceAPI

PermissionService

```java
package com.atguigu.service;

import com.atguigu.entity.Permission;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.PermissionService
 */
public interface PermissionService extends BaseService<Permission> {

    /**
     * 返回渲染菜单指定格式的数据
     */
    List<Map<String,Object>> findZNodes(Long roleId);
}
```

RolePermissionService

```java
package com.atguigu.service;

import com.atguigu.entity.RolePermission;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.RolePermissionService
 */
public interface RolePermissionService extends BaseService<RolePermission> {

    /**
     * 更新角色权限
     */
    void insertRolePermission(Long roleId,Long[] permissionIds);

}
```



#### 9.2.2.2dao层

PermissionDao

```java
package com.atguigu.dao;

import com.atguigu.entity.Permission;
import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.PermissionDao
 */
public interface PermissionDao extends BaseDao<Permission> {
    /**
     * 查询出所有权限菜单，加工后用插件渲染
     */
    List<Permission> findAll();
}
```

RolePermissionDao

```java
package com.atguigu.dao;

import com.atguigu.entity.RolePermission;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.dao.RolePermissionDao
 */
public interface RolePermissionDao extends BaseDao<RolePermission> {
    /**
     * 根据角色Id查询到所有权限Id，为了将其设置为选中状态
     */
    List<Long> findPermissionIdByRoleId(Long roleId);

    /**
     * 根据角色id删除所有的权限Id
     */
    void deleteByRoleId(Long roleId);


}
```

PermissionMapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.PermissionDao">

   <!--查询出所有权限菜单，加工后用插件渲染-->
    <select id="findAll" resultType="permission">
        select * from acl_permission where is_deleted=0
    </select>
</mapper>
```

RolePermissionMapper

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--名称空间设置成dao层接口的全类名-->
<mapper namespace="com.atguigu.dao.RolePermissionDao">

   <!--查询当前角色已有的权限菜单，为了设置选中状态-->
    <select id="findPermissionIdByRoleId" resultType="long">
        select permission_id from acl_role_permission where role_id=#{roleId} and is_deleted=0
    </select>

    <!--删除当前角色对应的全部权限菜单-->
    <update id="deleteByRoleId" >
        update acl_role_permission set is_deleted=1 where role_id=#{roleId}
    </update>

    <!--新增角色对应的权限菜单，和删除配合使用完成权限菜单的修改功能-->
    <insert id="insert">
        insert into acl_role_permission(role_id,permission_id)
        values(#{roleId},#{permissionId})
    </insert>
</mapper>
```



#### 9.2.2.3service层

PermissionServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.PermissionDao;
import com.atguigu.dao.RolePermissionDao;
import com.atguigu.entity.Permission;
import com.atguigu.service.PermissionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.PermissionServiceImpl
 */
@DubboService
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public BaseDao<Permission> getEntityDao() {
        return permissionDao;
    }


    /**
     * 先查询出所有的权限菜单，经过处理变为满足插件渲染的格式数据，再返回给前端
     */
    @Override
    public List<Map<String, Object>> findZNodes(Long roleId) {
        //先查出所有的权限菜单
        List<Permission> list = permissionDao.findAll();

        //再查出当前角色已拥有的权限菜单，为了将其设置为选中状态
        List<Long> permissionIdList = rolePermissionDao.findPermissionIdByRoleId(roleId);

        //将权限菜单list加工为插件可以渲染的格式
        List<Map<String,Object>> zNodes=new ArrayList<>();
        for (Permission permission : list) {
            Map<String,Object> map=new HashMap<>();
            //{ id:1, pId:0, name:"", checked:true}
            map.put("id",permission.getId());
            map.put("pId",permission.getParentId());
            map.put("name",permission.getName());
            //是否选中，当前角色已拥有的权限菜单，将其设置为选中状态
            if(permissionIdList.contains(permission.getId())){
                map.put("checked",true);
            }
            //每一个map都放到list集合里
            zNodes.add(map);
        }
        return zNodes;
    }


}
```

RolePermissionServiceImpl

```java
package com.atguigu.service.impl;

import com.atguigu.dao.BaseDao;
import com.atguigu.dao.RolePermissionDao;
import com.atguigu.entity.RolePermission;
import com.atguigu.service.RolePermissionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.impl.RolePermissionServiceImpl
 */
@DubboService
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public BaseDao<RolePermission> getEntityDao() {
        return rolePermissionDao;
    }

    /**
     * 更新角色权限菜单，先删除当前所有的权限菜单，再新增本次设置的
     */
    @Override
    @Transactional
    public void insertRolePermission(Long roleId, Long[] permissionIds) {
        //1. 根据roleId将目前的所有关系删除
        rolePermissionDao.deleteByRoleId(roleId);
        //2. 再重新循环新增权限
        for (Long permissionId : permissionIds) {
            RolePermission rolePermission=new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionDao.insert(rolePermission);
        }
    }
}
```



#### 9.2.2.4contreller层

RoleController添加内容

```java
    @DubboReference
    private PermissionService permissionService;

    @DubboReference
    private RolePermissionService rolePermissionService;

    /**
     * 处理/assignShow/roleId路径，获取角色权限列表
     */
    @RequestMapping("/assignShow/{roleId}")
    public String assignShow(@PathVariable Long roleId,Map map){
        //传入当前角色Id
        map.put("roleId",roleId);
        //获取指定格式的菜单渲染数据，并放到请求域
        //[{ id:2, pId:0, name:"用户管理", checked:true},{},{}...]
        List<Map<String, Object>> zNodes = permissionService.findZNodes(roleId);
        map.put("zNodes",zNodes);
        return "role/assignShow";
    }

    /**
     * 处理/assignPermission路径的请求，更新角色权限
     */
    @RequestMapping("/assignPermission")
    public String assignPermission(Long roleId,Long[] permissionIds){
        rolePermissionService.insertRolePermission(roleId,permissionIds);
        return "common/success";
    }
```



## 9.3左侧动态菜单

### 9.3.1准备web资源

修改frame/index.html，替换当前用户头像及名称，替换左侧菜单

```html
<nav class="navbar-default navbar-static-side" role="navigation">
   <div class="nav-close"><i class="fa fa-times-circle"></i></div>
   <div class="sidebar-collapse">
      <ul class="nav" id="side-menu">
         <li class="nav-header">
            <div class="dropdown profile-element">
               <span><img alt="image" class="img-circle" th:src="${admin.headUrl}" style="width: 50px;height: 50px;"/></span>
               <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                  <span class="clear">
                     <span class="block m-t-xs"><strong class="font-bold" th:text="${admin.name}">Beaut-zihan</strong></span>
                     <span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
                  </span>
               </a>
               <ul class="dropdown-menu animated fadeInRight m-t-xs">
                  <li><a class="J_menuItem" href="javascript:">修改头像</a></li>
                  <li><a class="J_menuItem" href="javascript:">个人资料</a></li>
                  <li><a class="J_menuItem" href="javascript:">联系我们</a></li>
                  <li><a class="J_menuItem" href="javascript:">信箱</a></li>
                  <li class="divider"></li>
                  <li><a href="/logout">安全退出</a></li>
               </ul>
            </div>
            <div class="logo-element">H+</div>
         </li>
        <!--循环菜单开始-->
        <li th:each="one: ${permissionList}">
          <a href="#">
            <i class="fa fa-home"></i>
            <span class="nav-label" th:text="${one.name}">系统管理</span>
            <span class="fa arrow"></span>
          </a>
          <ul class="nav nav-second-level collapse">
            <li th:each="two,it: ${one.children}"><a class="J_menuItem" th:href="${two.url}" th:data-index="${it.count}" th:text="${two.name}">用户管理</a></li>
          </ul>
        </li>
        <!--循环菜单结束-->
      </ul>
   </div>
</nav>
```





### 9.3.2准备后端数据

首先将mvc配置文件中配置的首页访问路径删除

其次是再配置PermissionHelper工具类到model模块下的util包下，再配置其他层⚠️

#### 9.3.2.1PermissionHelper

在model模块中添加工具类

```java
package com.atguigu.util;

import com.atguigu.entity.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 根据权限数据构建菜单数据
 * </p>
 *
 */
public class PermissionHelper {

    /**
     * 使用递归方法建菜单
     * @param treeNodes
     * @return
     */
    public static List<Permission> bulid(List<Permission> treeNodes) {
        //创建存储处理结果的集合
        List<Permission> trees = new ArrayList<>();
        //循环每一个权限菜单节点
        for (Permission treeNode : treeNodes) {
            //找出所有parent_id为0的节点
            if (treeNode.getParentId().longValue() == 0) {
                //parent_id为0设置为一级节点
                treeNode.setLevel(1);
                //传入本次循环的一级节点，和所有节点列表，为其子节点属性赋值
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static Permission findChildren(Permission treeNode, List<Permission> treeNodes) {
        //初始化当前节点的子节点集合
        treeNode.setChildren(new ArrayList<Permission>());

        //循环每一个权限菜单节点
        for (Permission it : treeNodes) {
            //查找当前节点的直接子节点
            if(treeNode.getId().longValue() == it.getParentId().longValue()) {
                //设置直接子节点的等级为父节点等级+1
                int level = treeNode.getLevel() + 1;
                it.setLevel(level);
                //确保子节点已经初始化，其实之前已经初始化过了，这里加上双保险
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                //将当前节点的直接子节点存入到自己的Children属性(list集合)中
                //直接递归，查找子节点的直接子节点，直到第N辈的子节点无子节点可循环时跳出该递归⚠️
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }
}
```



#### 9.3.2.2ServiceAPI

PermissionService添加内容

```java
    /**
     * 获取当前用户的菜单权限，获取后循环渲染
     */
    List<Permission> findPermissionByAdminId(Long adminId);
```



#### 9.3.2.3dao层

PermissionDao添加内容

```java
    /**
     * 获取当前用户的权限菜单，然后循环渲染
     */
    List<Permission> findPermissionByAdminId(Long adminId);
```

PermissionMapper添加内容

```xml
    <!--三表关联查询，过去当前用户的权限菜单-->
    <select id="findPermissionByAdminId" resultType="permission">
        SELECT DISTINCT ap.* FROM acl_admin_role aar LEFT JOIN acl_role_permission arp
        ON aar.`role_id`=arp.`role_id` LEFT JOIN acl_permission ap
        ON arp.`permission_id`=ap.`id`
        WHERE aar.`admin_id`=#{adminId}
        AND ap.`type`=1
        AND aar.`is_deleted`=0
        AND arp.`is_deleted`=0
        AND ap.`is_deleted`=0
    </select>
```



#### 9.3.2.4service层

PermissionServiceImpl添加内容，

```java
    /**
     * 先拿到当前用户的所有权限菜单信息，再通过工具类PermissionHelper处理菜单的级别关系
     * PermissionHelper类底层是通过递归来处理分级关系的，因为是服务器渲染的，处理后才可以循环渲染
     */
    @Override
    public List<Permission> findPermissionByAdminId(Long adminId) {
        List<Permission> permissionList = permissionDao.findPermissionByAdminId(adminId);
        //permissionList所有的菜单信息，需要借助PermissionHelper处理其分级关系
        List<Permission> permissionList1 = PermissionHelper.bulid(permissionList);
        return permissionList1;
    }
```



#### 9.3.2.5controller层

IndexController修改内容

```java
    @DubboReference
    private AdminService adminService;

    @DubboReference
    private PermissionService permissionService;
  
    /**
     * 首页默认访问路径
     */
    @RequestMapping("/")
    public String index(Map map) {
        //先写死，因为还没做后台的登录功能，先模拟登录用户的id
        Long adminId = 3L;
        Admin admin = adminService.getById(adminId);
        List<Permission> permissionList = permissionService.findPermissionByAdminId(adminId);
        map.put("admin", admin);
        map.put("permissionList",permissionList);
        return PAGE_INDEX;
    }

    /**
     * 首页iframe窗体中内置的欢迎页面
     */
    @RequestMapping("/main")
    public String main() {
        return PAGE_MAIN;
    }
```



## 9.4菜单管理

### 9.4.1准备web资源

#### 9.4.1.1index页面

permission/index.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head th:include="common/head :: head"></head>

<body class="gray-bg">
<form id="ec" action="#" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <div>
                            <button type="button" class="btn btn-sm btn-primary create"
                                    th:attr="data-id=0,data-type=1,data-name='一级菜单'">新增一级菜单
                            </button>
                            <button type="button" id="loading-example-btn"
                                    onclick="javascript:window.location.reload();" class="btn btn-white btn-sm">刷新
                            </button>
                        </div>


                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>权限名称</th>
                                <th>菜单url</th>
                                <th>权限标识</th>
                                <th>类型</th>
                                <th>排序</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <!--定义变量记录循环次数-->
                            <tbody th:with="flag=0">

                            <!--定义迭代渲染的模板，以及模板形参-->
                            <th:block th:fragment="row(list)">

                                <!--循环数据-->
                                <div th:each="one: ${list}" th:with="flag = ${flag} + 1">
                                    <tr class="gradeX">
                                        <td><span th:style="${'padding-left:' + 20 * (flag - 1) + 'px'}"
                                                  th:text="${one.name}">22</span></td>
                                        <td th:text="${one.url}">33</td>
                                        <td th:text="${one.code}">22</td>
                                        <td>
                                            <strong><span th:if="${one.type } eq 1"
                                                          style="color: blue">菜单</span></strong>
                                            <strong><span th:if="${one.type } eq 2">按钮</span></strong>
                                        </td>
                                        <td th:text="${one.sort}">22</td>
                                        <td th:text="${#dates.format(one.createTime,'yyyy-MM-dd HH:mm:ss')}">33</td>
                                        <td class="text-center">
                                            <!--如果是一级菜单可以执行的操作-->
                                            <a th:if="${flag == 1}" class="create"
                                               th:attr="data-id=${one.id},data-type=1,data-name=${one.name}">
                                                新增二级菜单
                                            </a>
                                            <!--如果是二级菜单可以执行的操作-->
                                            <a th:if="${flag == 2}" class="create"
                                               th:attr="data-id=${one.id},data-type=2,data-name=${one.name}">
                                                新增功能按钮
                                            </a>
                                            <!--都可以执行的操作-->
                                            <a class="edit" th:attr="data-id=${one.id}">修改</a>
                                            <a class="delete" th:attr="data-id=${one.id}">删除</a>
                                        </td>
                                    </tr>

                                    <!--判断循环的数据是否有子节点-->
                                    <div th:if="${#lists.size(one.children) > 0}">
                                        <!--有子节点传入继续循环-->
                                        <div th:include="this::row(${one.children})"/>
                                    </div>
                                </div>

                            </th:block>

                            <!--使用this调用本页面的模板，传入实参，开启迭代-->
                            <div th:include="this::row(${list})"/>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<script th:inline="javascript">
    $(function () {
        //新增一级、二级、按钮都是触发这个单击事件
        $(".create").on("click", function () {
            //新增内容父级id
            var parentId = $(this).attr("data-id");
            //一级二级type是1表示菜单，三级type是2表示按钮
            var type = $(this).attr("data-type");
            //新增内容父级名称
            var parentName = $(this).attr("data-name");
            opt.openWin('/permission/create?parentId=' + parentId + '&type=' + type + '&parentName=' + parentName, '新增', 630, 430)
        });
        $(".edit").on("click", function () {
            var id = $(this).attr("data-id");
            opt.openWin('/permission/edit/' + id, '修改', 580, 430);
        });
        $(".delete").on("click", function () {
            var id = $(this).attr("data-id");
            opt.confirm('/permission/delete/' + id);
        });
    });
</script>
</body>
</html>
```



#### 9.4.1.2create页面

permission/create.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<script type="text/javascript">
    $(function(){
        $('#ec').validate({
            rules:{
                name:"required"
            },
            messages:{
                name:"权限名称必须输入"
            },
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/permission/save}" method="post" class="form-horizontal">
                <input type="hidden" name="parentId" th:value="${permission.parentId}"/>
                <input type="hidden" name="type" th:value="${permission.type}"/>
                <div class="form-group">
                    <label class="col-sm-2 control-label">上级权限：</label>
                    <div class="col-sm-10">
                        <input type="text" name="parentName" id="parentName" th:value="${permission.parentName}" disabled="disabled" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">权限名称：</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" id="name" value="" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group" th:if="${permission.type == 1}">
                    <label class="col-sm-2 control-label">菜单url：</label>
                    <div class="col-sm-10">
                        <input type="text" name="url" id="url" class="form-control" style="width:100%;height: 50px;" ></input>
                    </div>
                </div>
                <div class="hr-line-dashed" th:if="${permission.type == 1}"></div>

                <div class="form-group" th:if="${permission.type == 2}">
                    <label class="col-sm-2 control-label">权限值：</label>
                    <div class="col-sm-10">
                        <input type="text" name="code" id="code" class="form-control" style="width:100%;height: 50px;" ></input>
                    </div>
                </div>
                <div class="hr-line-dashed" th:if="${permission.type == 2}"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">排序：</label>
                    <div class="col-sm-10">
                        <input type="text" name="sort" id="sort" value="1" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



#### 9.4.1.3edit页面

permission/edit.html

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:include="common/head :: head"></head>
<script type="text/javascript">
    $(function(){
        $('#ec').validate({
            rules:{
                name:"required"
            },
            messages:{
                name:"权限名称必须输入"
            },
            submitHandler: function(form) {
                $(form).find(":submit").attr("disabled", true).text("正在提交...");
                form.submit();
            }
        });
    });
</script>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-content" style="width: 98%;">
            <form id="ec" th:action="@{/permission/update}" method="post" class="form-horizontal">
                <input type="hidden" name="id" th:value="${permission.id}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">权限名称：</label>
                    <div class="col-sm-10">
                        <input type="text" name="name" id="name" th:value="${permission.name}" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group" th:if="${permission.type == 1}">
                    <label class="col-sm-2 control-label">菜单url：</label>
                    <div class="col-sm-10">
                        <input type="text" name="url" id="url" th:value="${permission.url}" class="form-control" style="width:100%;height: 50px;" ></input>
                    </div>
                </div>
                <div class="hr-line-dashed" th:if="${permission.type == 1}"></div>
                <div class="form-group" th:if="${permission.type == 2}">
                    <label class="col-sm-2 control-label">权限值：</label>
                    <div class="col-sm-10">
                        <input type="text" name="code" id="code" th:value="${permission.code}" class="form-control" style="width:100%;height: 50px;" ></input>
                    </div>
                </div>
                <div class="hr-line-dashed" th:if="${permission.type == 2}"></div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">排序：</label>
                    <div class="col-sm-10">
                        <input type="text" name="sort" id="sort" th:value="${permission.sort}" class="form-control"/>
                    </div>
                </div>
                <div class="hr-line-dashed"></div>
                <div class="form-group posf">
                    <div class="col-sm-4 col-sm-offset-2 text-right">
                        <button class="btn btn-primary" type="submit">确定</button>
                        <button class="btn btn-white" type="button" onclick="javascript:opt.closeWin();" value="取消">取消</button></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
```



### 9.4.2准备后端资源

#### 9.4.2.1ServiceAPI

PermissionService添加内容

```java
    /**
     * 获取菜单的全部数据
     */
    List<Permission> findAll();
```



#### 9.4.2.2dao层

PermissionDao添加内容

```java
    /**
     * 获取自己的子节点列表
     */
    List<Permission> findPermissionByParentId(Serializable parentId);
```

PermissionMapper添加内容

```xml
    <!--插入实例-->
    <insert id="insert">
        insert into acl_permission(parent_Id,type,name,url,code,sort)
        values(#{parentId},#{type},#{name},#{url},#{code},#{sort})
    </insert>

    <!--根据id获取实例，用作修改时数据回显-->
    <select id="getById" resultType="permission">
        select * from acl_permission where id=#{id} and is_deleted=0
    </select>

    <!--修改实例-->
    <update id="update">
        update acl_permission
        <set>
            <if test="name!=null and name!=''">
                name=#{name},
            </if>
            <if test="url!=null and url!=''">
                url=#{url},
            </if>
            <if test="code!=null and code!=''">
                code=#{code},
            </if>
            <if test="sort!=null and sort!=''">
                sort=#{sort},
            </if>
        </set>
        where id=#{id}
    </update>

    <!--通过id获取子节点，迭代删除时使用-->
    <select id="findPermissionByParentId" resultType="permission">
        select * from acl_permission where parent_id=#{parentId} and is_deleted=0
    </select>

    <!--逻辑删除实例-->
    <update id="delete">
        update acl_permission set is_deleted=1 where id=#{id}
    </update>
```



#### 9.4.2.3service层

PermissionServiceImpl添加内容

```java
    /**
     * 获取所有的权限菜单节点，并通过PermissionHelper类递归来处理分级关系
     */
    @Override
    public List<Permission> findAll() {
        List<Permission> list = permissionDao.findAll();
        return PermissionHelper.bulid(list);
    }

    /**
     * 重写基类中的删除方法，递归删除节点和其所有子节点
     */
    @Override
    public void delete(Serializable id) {
        //获取自己的子节点，递归删除
        List<Permission> permissionList = permissionDao.findPermissionByParentId(id);
        //判断有无子节点，若有子节点则进行递归删除
        if(permissionList!=null && permissionList.size()!=0){
            //迭代递归删除子节点
            for (Permission permission : permissionList) {
                delete(permission.getId());
            }
        }
        //删除子节点后，再删除自身节点
        permissionDao.delete(id);
    }
```



#### 9.4.2.4controller层

PermissionController

```java
package com.atguigu.controller;

import com.atguigu.entity.Permission;
import com.atguigu.service.PermissionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.PermissionController
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {


    @DubboReference
    private PermissionService permissionService;

    private final static String LIST_ACTION = "redirect:/permission";
    private final static String PAGE_INDEX = "permission/index";
    private final static String PAGE_CREATE = "permission/create";
    private final static String PAGE_EDIT = "permission/edit";
    private final static String PAGE_SUCCESS = "common/success";


    /**
     * 获取菜单
     */
    @RequestMapping
    public String index(Map map){
        List<Permission> list = permissionService.findAll();
        map.put("list",list);
        return PAGE_INDEX;
    }

    /**
     * 处理/create请求，跳转到新增页面
     */
    @RequestMapping("/create")
    public String create(Permission permission,Map map){
        map.put("permission",permission);
        return PAGE_CREATE;
    }

    /**
     * 处理/save请求，保存新增
     */
    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.insert(permission);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/edit/id操作，跳转到编辑页面
     */
    @RequestMapping("/edit/{permissionId}")
    public String edit(@PathVariable Long permissionId, Map map){
        Permission permission = permissionService.getById(permissionId);
        map.put("permission",permission);
        return PAGE_EDIT;
    }

    /**
     * 处理/update请求，保存更新
     */
    @RequestMapping("/update")
    public String update(Permission permission){
        permissionService.update(permission);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/delete/id请求，删除操作
     */
    @RequestMapping("/delete/{permissionId}")
    public String delete(@PathVariable Long permissionId){
        permissionService.delete(permissionId);
        return LIST_ACTION;
    }
}
```





# 10 Spring Security

## 10.1Spring Security简介

前面我们已经完成了尚好房权限管理的部分相关功能，给用户分配角色，给角色分配权限，及左侧动态菜单，做好权限管理的数据准备，接下来我们要使用这些数据进行权限的相关控制。

- 认证：系统提供的用于识别用户身份的功能，通常提供用户名和密码进行登录其实就是在进行认证，认证的目的是让系统知道你是谁。

- 授权：用户认证成功后，需要为用户授权，其实就是指定当前用户可以操作哪些功能。


本章节就是要对后台系统进行权限控制，其本质就是对用户进行认证和授权。

Spring Security是 Spring提供的安全认证服务的框架。 使用Spring Security可以帮助我们来简化认证和授权的过程。

官网：https://spring.io/projects/spring-security/  

中文官网：https://www.w3cschool.cn/springsecurity/ 



## 10.2Spring Security集成与入门

### 10.2.1引入依赖

shf_parent父工程的pom.xml中添加版本管理

```xml
<spring.security.version>5.4.10</spring.security.version>
```

```xml
<!-- spring security安全框架 -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>${spring.security.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    <version>${spring.security.version}</version>
</dependency>
```

web_admin模块中引入依赖

```xml
<!-- spring security安全框架 -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
</dependency>
```



### 10.2.2配置SpringSecurityFilter

web_admin模块的web.xml文件中配置代理过滤器

```xml
<!-- SpringSecurity Filter -->
<!-- DelegatingFilterProxy用于整合第三方框架（代理过滤器，非真正的过滤器，真正的过滤器需要在spring的配置文件） -->
<filter>
  <filter-name>springSecurityFilterChain</filter-name>
  <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
  <filter-name>springSecurityFilterChain</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```



### 10.2.3配置Spring Security

配置Spring Security有两种方式：

​	1、xml文件配置

​	2、java类配置

两种方式配置效果一致，当前我们使用java类配置，更加简洁，现在先创建java类WebSecurityConfig

注意：该配置类也需要被扫描⚠️

```java
package com.atguigu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//标记该类是配置类
@Configuration
//@EnableWebSecurity是开启SpringSecurity的默认行为
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

}
```



### 10.2.4修改扫描器

扫描WebSecurityConfig类

```xml
<!--包的注解扫描-->
<context:component-scan base-package="com.atguigu"/>
```



### 10.2.5添加配置

内存分配用户名密码（包括静态资源在内的所有访问资源受限⚠️）、设置加密方式（必须设置）、设置允许iframe嵌套显示

```java
package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//标记该类是配置类
@Configuration
//@EnableWebSecurity是开启SpringSecurity的默认行为
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 内存中分配登录的用户名密码
     * Security已经开始工作，未认证的话，去到登录页面(springSecurity默认提供的)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //对于密码来说，必须要求加密(指定加密方式)
        //不设置加密方式的报错：springSecurity There is no PasswordEncoder mapped for the id "null"
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("123456"))
                .roles("");
    }


    /**
     * 设置加密方式，必须指定加密方式，上下加密方式要一致⚠️
     * Bean注解是将这个方法的返回值对象装配到IoC容器，为了注册的时候可以从IoC容器内获取到这个对象进行加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 设置允许iframe嵌套显示，默认Spring Security不允许iframe嵌套显示
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //必须调用父类的方法，否则就不需要认证即可访问
        super.configure(http);
        //允许iframe嵌套显示（iframe中还有iframe）
        //http.headers().frameOptions().disable();
        //允许iframe显示
        http.headers().frameOptions().sameOrigin();
    }

}
```



## 10.3Spring Security集成进阶

### 10.3.1登录基础设置

自己配置SpringSecurity，配置登录、退出和跨域等配置

```java
package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//标记该类是配置类
@Configuration
//@EnableWebSecurity是开启SpringSecurity的默认行为
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置加密方式，必须指定加密方式，上下加密方式要一致
     * Bean注解是将这个方法的返回值对象装配到IoC容器，为了注册的时候可以从IoC容器内获取到这个对象进行加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 设置允许iframe嵌套显示，默认Spring Security不允许iframe嵌套显示
     * 可自己配置SpringSecurity，配置登出、登入等设置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //不再需要父级的方法，自己配置SpringSecurity
        //super.configure(http);

        //设置允许iframe进行嵌套
        http.headers().frameOptions().disable();

        //and()方法返回的是http对象本身⚠️
        //设置不需要认证就可以访问的路径(所有的静态资源、/login)
        http.authorizeRequests()
                // 允许匿名访问的路径
                .antMatchers("/static/**","/login").permitAll()
                //其他的请求都需要认证
                .anyRequest().authenticated();

        //设置登录
        http.formLogin()
                //设置自己的登录页面，不再使用SpringSecurity默认提供的
                //未登录时，访问哪个路径都跳转到这里
                .loginPage("/login")
                //登录认证成功后默认转跳的路径
                .defaultSuccessUrl("/");

        //设置退出
        http.logout()
                //退出登陆的路径，指定spring security拦截的注销url
                //退出功能是security提供的
                .logoutUrl("/logout")
                //用户退出后要被重定向的url
                .logoutSuccessUrl("/login");

        //跨域设置
        http.csrf()
                //需要将跨域请求关闭，不然请求不到/login无法退出
                .disable();
    }

}


```



### 10.3.2定义登录页面

创建frame/login.html文件，说明如下：

1. Spring Security登录的默认用户名密码字段为：username、password，默认提交请求地址：/login，这样我们都不要去更改，如果更改的话需在上面的配置方法中更改。

2. 用户名密码错误url会错误标识：error，可用作提示信息

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head th:include="common/head :: head"></head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">房</h1>

        </div>
        <h3>欢迎使用 尚好房平台管理系统</h3>

        <form class="m-t" role="form" th:action="@{/login}" method="post">
            <label style="color:red;" th:if="${param.error}" th:text="用户名或密码错误"></label>
            <div class="form-group">
                <input type="text" name="username" value="" class="form-control" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input type="password" name="password" value="" class="form-control" placeholder="密码" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


            <p class="text-muted text-center"> <a href="javascript:"><small>忘记密码了？</small></a> | <a href="javascript:">注册一个新账号</a>
            </p>

        </form>
    </div>
</div>

</body>

</html>
```

IndexController中添加SpringSecurity中设置的 `/login` 登录路径

```java
    private final static String PAGE_LOGIN = "frame/login";

		/**
     * 控制SpringSecurity中设置的登录请求/login所去的页面
     */
    @RequestMapping("/login")
    public String login(){
        return PAGE_LOGIN;
    }
```



### 10.3.3获取用户信息

#### 10.3.3.1ServiceAPI

AdminService添加内容

```java
    /**
     * 后台登录验证，根据用户名获取用户信息
     */
    Admin findAdminByUsername(String username);
```



#### 10.3.3.2dao层

AdminDao添加内容

```java
    /**
     * 后台登录验证，根据用户名获取用户信息
     */
    Admin findAdminByUsername(String username);
```

AdminMapper添加内容

```xml
<!--后台登录验证，根据用户名获取用户信息-->
<select id="findAdminByUsername" resultType="Admin">
    select * from acl_admin where username=#{username} and is_deleted=0
</select>
```



#### 10.3.3.3service层

AdminServiceImpl添加内容

```java
    /**
     * 后台登录验证，根据用户名获取用户信息
     */
    @Override
    public Admin findAdminByUsername(String username) {
        return adminDao.findAdminByUsername(username);
    }
```



### 10.3.4UserDetailsService接口

Spring Security支持通过实现UserDetailsService接口的方式来提供用户认证授权信息

#### 10.3.4.1创建接口实现类

在web_admin模块中创建com.atguigu.service.UserDetailsServiceImpl类，实现UserDetailsService接口

```java
package com.atguigu.service;

import com.atguigu.entity.Admin;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.UserDetailsServiceImpl
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @DubboReference
    private AdminService adminService;

    /**
     * SpringSecurity在验证用户名和密码的时，默认调用UserDetailsService的loadUserByUsername方法
     * 我们选择重写，调用的就是重写之后的
     * username就是在登录页面输入的用户名
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("用户输入的用户名：" + username);
        //1. 通过用户名去数据查询Admin对象回来
        Admin admin = adminService.findAdminByUsername(username);
        //2. 如果对象有值，说明用户名是对的，然后在让SpringSecurity去完成密码的校验(有加密)
        if (admin == null) {
            //如果没有查到对应的实例，说明用户名不存在
            throw new UsernameNotFoundException("用户名不存在！");
        }
        //若用户存在，则开始校验密码。admin.getPassword()就是从数据库查询回来的密码--> 必须是加密的密码
      	//无论用户实例是什么对象，都会根据对象的参数返回SpringSecurity提供的User对象，即当前登录对象的加工后的认证对象
        return new User(username, admin.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));
    }
}
```



#### 10.3.4.1改造添加用户加密

前面添加用户是我们没有对密码进行加密处理，现在改造。删除未加密的数据记录，重新创建用户信息

修改AdminController类save方法

```java
    //security提供的加密对象，在WebSecurityConfig中放入到IoC容器里了，所以可以自动装配
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 处理/save请求，执行添加资源操作
     */
    @RequestMapping("/save")
    public String save(Admin admin) {
        //使用security提供的加密对象对密码进行加密
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminService.insert(admin);
        return PAGE_SUCCESS;
    }
```



### 10.3.5获取当前登录用户

#### 10.3.5.1获取用户信息

注意：User为：org.springframework.security.core.userdetails.User，叫User的类比较多，导包千万别导错了⚠️

```java
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
User user = (User)authentication.getPrincipal();
```

返回的信息是当前登录对象的加工后的认证对象，格式如下

```java
{
    "accountNonExpired": true,
    "accountNonLocked": true,
    "authorities": [],
    "credentialsNonExpired": true,
    "enabled": true,
    "username": "admin"
}
```



#### 10.3.5.2封装获取信息方法

我们可以定义一个controller方法，获取当前用户信息，便于获取。

```java
/**
 * 获取当前登录信息
 * @return
 */
@RequestMapping("/getInfo")
@ResponseBody
public Object getInfo() {
  	//可以返回当前登录对象的加工后的认证对象，也可以返回登录对象或封装了信息的Map集合，看需求
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getPrincipal();
}
```



### 10.3.6左侧菜单动态获取权限

之前获取左侧菜单我们是写死了的，目前可以动态获取当前用户了

IndexController修改内容

```java
/**
 * 后台首页
 */
@RequestMapping("/")
public String index(Map map) {
    //获取SpringSecurity提供的User对象，即当前登录对象的加工后的认证对象
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = (User)authentication.getPrincipal();

    //通过认证对象的用户名获取现在登录的Admin对象
    Admin admin = adminService.findAdminByUsername(user.getUsername());
    map.put("admin", admin);

    //根据当前登录对象Id寻找对应的权限菜单，放到请求域根据登录用户的权限进行左侧菜单动态渲染
    List<Permission> permissionList = permissionService.findPermissionByAdminId(admin.getId());
    map.put("permissionList",permissionList);

    return PAGE_INDEX;
}
```



## 10.4用户授权

### 10.4.1获取设置用户权限

#### 10.4.1.1ServiceAPI

PermissionService增加内容

```java
/**
 * 根据当前登录用户的id获取全部按钮的权限code
 */
List<String> findPermissionCodeByAdminId(Long adminId);
```



#### 10.4.1.2dao层

PermissionDao增加内容

```java
/**
 * 根据当前登录用户的id获取全部的按钮权限code
 */
List<String> findPermissionCodeByAdminId(Long adminId);

/**
 * 直接获取全部的权限code，为超级管理员提供全部按钮权限时使用
 */
List<String> findAllCode();
```

PermissionMapper增加内容

```xml
<!--根据当前登录用户的id获取全部按钮的权限code-->
<select id="findPermissionCodeByAdminId" resultType="string">
    SELECT DISTINCT ap.code FROM acl_admin_role aar LEFT JOIN acl_role_permission arp
    ON aar.`role_id`=arp.`role_id` LEFT JOIN acl_permission ap
    ON arp.`permission_id`=ap.`id`
    WHERE aar.`admin_id`=#{adminId}
    AND ap.`type`=2
    AND aar.`is_deleted`=0
    AND arp.`is_deleted`=0
    AND ap.`is_deleted`=0
</select>

<!--直接获取全部的权限code，为超级管理员提供全部权限按钮时使用。但是要注意只获取按钮的-->
<select id="findAllCode" resultType="string">
    select code from acl_permission where is_deleted=0 and type=2
</select>
```



#### 10.4.1.3service层

PermissionServiceImpl增加内容

```java
/**
 * 根据当前登录用户的id获取全部的权限code
 * 对超级管理员进行判断，如果是超级管理员则拥有全部权限
 */
@Override
public List<String> findPermissionCodeByAdminId(Long adminId) {
    List<String> codes=null;
    //如果当前登录用户的id为1，则是超级管理员，拥有全部权限
    if(adminId==1){
        codes=permissionDao.findAllCode();
    }else{
        codes=permissionDao.findPermissionCodeByAdminId(adminId);
    }
    return codes;
}
```



#### 10.4.1.4设置用户权限

UserDetailsServiceImpl修改内容

```java
package com.atguigu.service;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.entity.Admin;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.service.UserDetailsServiceImpl
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @DubboReference
    private AdminService adminService;

    @DubboReference
    private PermissionService permissionService;

    /**
     * SpringSecurity在验证用户名和密码的时，默认调用UserDetailsService的loadUserByUsername方法
     * 我们选择重写，调用的就是重写之后的
     * username就是在登录页面输入的用户名
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("用户输入的用户名：" + username);
        //1. 通过用户名去数据查询Admin对象回来
        Admin admin = adminService.findAdminByUsername(username);

        //2. 如果对象有值，说明用户名是对的，然后在让SpringSecurity去完成密码的校验(有加密)
        if (admin == null) {
            //如果没有查到对应的实例，说明用户名不存在
            throw new UsernameNotFoundException("用户名不存在！");
        }

        //开始添加当前登录成功用户的所有权限code
        List<String> codeList = permissionService.findPermissionCodeByAdminId(admin.getId());

        //将拿到的权限code转换为支持的权限参数类型
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(String code : codeList) {
            if(StringUtils.isEmpty(code)) continue;
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(code);
            authorities.add(authority);
        }


        //若用户存在，则开始校验密码。admin.getPassword()就是从数据库查询回来的密码--> 必须是加密的密码
        //无论用户实例是什么对象，都会根据对象的参数返回SpringSecurity提供的User对象，即当前登录对象
        return new User(username, admin.getPassword(),
                //传入当前成功登录用户的权限列表
                authorities);
    }
}
```



### 10.4.2controller权限控制

#### 10.4.2.1添加开启权限注解

WebSecurityConfig类添加开启标签

```java
//开启权限按钮验证功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
```



#### 10.4.2.2controller设置权限

以角色管理增删改查等为例，RoleController功能权限数据我们已经配置到数据库表的code字段里

使用`@PreAuthorize("hasAuthority('code')")`添加权限

```java
package com.atguigu.controller;

import com.atguigu.entity.Role;
import com.atguigu.service.PermissionService;
import com.atguigu.service.RolePermissionService;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * TODD
 * @AllClassName: com.atguigu.controller.RoleController
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final static String PAGE_INDEX = "role/index";
    private final static String PAGE_CREATE = "role/create";
    private final static String PAGE_EDIT = "role/edit";
    private final static String PAGE_SUCCESS = "common/success";
    private final static String LIST_ACTION = "redirect:/role";


    @DubboReference
    private RoleService roleService;

    @DubboReference
    private PermissionService permissionService;

    @DubboReference
    private RolePermissionService rolePermissionService;


    /**
     * 处理/请求，跳转到index页，搜索处理、分页处理
     */
    @PreAuthorize("hasAuthority('role.show')")
    @RequestMapping
    public String index(Map map, HttpServletRequest request) {
        //处理请求参数
        Map<String,Object> filters = getFilters(request);
        //传递参数到service层，拿到查询结果并构建分页对象
        PageInfo<Role> page = roleService.findPage(filters);

        //将PageInfo分页对象放到请求域，里面有分页信息和搜索结果
        map.put("page", page);
        //搜索内容的回显
        map.put("filters", filters);

        return PAGE_INDEX;
    }

    /**
     * 处理/create请求，跳转到添加资源页面
     */
    @PreAuthorize("hasAuthority('role.create')")
    @RequestMapping("/create")
    public String create() {
        return PAGE_CREATE;
    }

    /**
     * 处理/save请求，执行添加资源操作
     */
    @PreAuthorize("hasAuthority('role.create')")
    @RequestMapping("/save")
    public String save(Role role) {
        roleService.insert(role);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/edit/id请求，跳转到修改资源页面
     */
    @PreAuthorize("hasAuthority('role.edit')")
    @RequestMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Map map
    ) {
        Role role = roleService.getById(id);
        map.put("role",role);
        return PAGE_EDIT;
    }

    /**
     * 处理/update请求，执行资源修改操作
     */
    @PreAuthorize("hasAuthority('role.edit')")
    @RequestMapping(value="/update")
    public String update(Role role) {
        roleService.update(role);
        return PAGE_SUCCESS;
    }

    /**
     * 处理/delete/id请求，执行资源删除操作
     */
    @PreAuthorize("hasAuthority('role.delete')")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        //不是在iframe窗体内执行操作，直接重定向即可
        return LIST_ACTION;
    }

    /**
     * 处理/assignShow/roleId路径，获取角色权限列表
     */
    @PreAuthorize("hasAuthority('role.assgin')")
    @RequestMapping("/assignShow/{roleId}")
    public String assignShow(@PathVariable Long roleId,Map map){
        //传入当前角色Id
        map.put("roleId",roleId);
        //获取指定格式的菜单渲染数据，并放到请求域
        //[{ id:2, pId:0, name:"用户管理", checked:true},{},{}...]
        List<Map<String, Object>> zNodes = permissionService.findZNodes(roleId);
        map.put("zNodes",zNodes);
        return "role/assignShow";
    }

    /**
     * 处理/assignPermission路径的请求，更新角色权限
     */
    @PreAuthorize("hasAuthority('role.assgin')")
    @RequestMapping("/assignPermission")
    public String assignPermission(Long roleId,Long[] permissionIds){
        rolePermissionService.insertRolePermission(roleId,permissionIds);
        return "common/success";
    }

}
```



#### 10.4.2.3友好提示页面

IndexController添加内容

```java
    private final static String PAGE_AUTH = "frame/auth";

    /**
     * 处理/auth路径，定义没有权限的提示页面
     */
    @RequestMapping("/auth")
    public String auth() {
        return PAGE_AUTH;
    }
```

frame添加页面auth.html

```html
<!DOCTYPE html>
<html>
<head>
</head>
<body style="position: relative;">
<div style="text-align:center;margin-top: 100px;font-size: 20px;">
    <strong>没有权限</strong>
</div>
</body>
</html>
```

实现创建CustomAccessDeineHandler类AccessDeniedHandler接口

```java
package com.atguigu.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.config.CustomAccessDeineHandler
 */
public class CustomAccessDeineHandler implements AccessDeniedHandler {
    /**
     * 权限验证不通过的处理器，未通过授权统一跳转到/auth路径
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.sendRedirect("/auth");
    }
}
```

WebSecurityConfig中配置异常类：AccessDeniedHandler接口实现类

```java
@Override
protected void configure(HttpSecurity http) throws Exception {

    //不再需要父级的方法，自己配置SpringSecurity
    //super.configure(http);

    //设置允许iframe进行嵌套
    http.headers().frameOptions().disable();

    //and()方法返回的是http对象本身⚠️
    //设置不需要认证就可以访问的路径(所有的静态资源、/login)
    http.authorizeRequests()
            // 允许匿名访问的路径
            .antMatchers("/static/**","/login").permitAll()
            //其他的请求都需要认证
            .anyRequest().authenticated();

    //设置登录
    http.formLogin()
            //设置自己的登录页面，不再使用SpringSecurity默认提供的
            //未登录时，访问哪个路径都跳转到这里
            .loginPage("/login")
            //登录认证成功后默认转跳的路径
            .defaultSuccessUrl("/");

    //设置退出
    http.logout()
            //退出登陆的路径，指定spring security拦截的注销url
            //退出功能是security提供的
            .logoutUrl("/logout")
            //用户退出后要被重定向的url
            .logoutSuccessUrl("/login");

    //跨域设置
    http.csrf()
            //需要将跨域请求关闭，不然请求不到/login无法退出
            .disable();

    //添加自定义无权限处理器
    http.exceptionHandling().
            //我们自己定义的实现了AccessDeniedHandler接口的处理器类
            accessDeniedHandler(new CustomAccessDeineHandler());
}
```



### 10.4.3页面功能按钮权限控制

页面上控制显示，但还能通过路径访问，可配合controller权限控制使用

#### 10.4.3.1引入依赖

shf_parent添加依赖管理

```xml
<thymeleaf-springsecurity5.version>3.0.4.RELEASE</thymeleaf-springsecurity5.version>
```

```xml
<!--用于spring security5标签，thymeleaf和security整合的jar包-->
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
    <version>${thymeleaf-springsecurity5.version}</version>
</dependency>
```

web_admin引入依赖

```xml
<!--用于spring security5标签，thymeleaf和security整合的jar包-->
<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
</dependency>
```



#### 10.4.3.2视图模板引擎配置

修改spring-mvc.xml，在模板引擎配置spring security 标签支持：在templateResolver模块下添加内容

```xml
    <!--配置视图解析器 ：Thymeleaf  SpringBoot之后是不需要自己配置-->
    <bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver" id="viewResolver">
        <!--配置字符集属性-->
        <property name="characterEncoding" value="UTF-8"></property>
        <!--配置模板引擎属性-->
        <property name="templateEngine">
            <!--配置内部bean-->
            <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                <!--配置模块的解析器属性-->
                <property name="templateResolver">
                    <!--配置内部bean-->
                    <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                        <!--配置前缀  ★-->
                        <property name="prefix" value="/WEB-INF/pages/"></property>
                        <!--配置后缀  ★-->
                        <property name="suffix" value=".html"></property>
                        <!--配置字符集-->
                        <property name="characterEncoding" value="UTF-8"></property>
                    </bean>
                </property>

                <!--在templateResolver模块下，开始添加权限认证标签支持⚠️⚠️⚠️-->
                <!-- 添加spring security 标签支持：sec -->
                <property name="additionalDialects">
                    <set>
                        <bean class="org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect" />
                    </set>
                </property>
               <!-- 添加完毕 -->
              
            </bean>
        </property>
    </bean>
```



#### 10.4.3.3页面按钮控制

以角色管理role/indexx.html为例：

使用标签：sec:authorize="hasAuthority('')"与controller层一一对应

1、在html文件里面申明使用spring-security标签

```html
<html xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
```

2、按钮上使用标签

```html
<button type="button" class="btn btn-sm btn-primary create" sec:authorize="hasAuthority('role.create')">新增</button>
```

```html
<a class="edit" th:attr="data-id=${item.id}" sec:authorize="hasAuthority('role.edit')">修改</a>
<a class="delete" th:attr="data-id=${item.id}" sec:authorize="hasAuthority('role.delete')">删除</a>
<a class="assgin" th:attr="data-id=${item.id}" sec:authorize="hasAuthority('role.assgin')">分配权限</a>
```

3、完整代码

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/extras/spring-security">
<head th:include="common/head :: head"></head>
<body class="gray-bg">
<!--查询角色的表单-->
<form id="ec" th:action="@{/role}" method="post">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">

                        <!--搜索内容开始-->
                        <table class="table form-table margin-bottom10">
                            <tr>
                                <td>
                                    <!--搜索内容回显-->
                                    <input type="text" name="roleName"
                                           th:value="${#maps.containsKey(filters, 'roleName')} ? ${filters.roleName} : ''"
                                           placeholder="角色名称" class="input-sm form-control"/>
                                </td>
                            </tr>
                        </table>
                        <div>
                            <!--点击搜索按钮提交表单，并为表单项的页码参数pageNum赋值1-->
                            <button type="button" class="btn btn-sm btn-primary"
                                    onclick="javascript:document.forms.ec.pageNum.value=1;document.forms.ec.submit();">
                                搜索
                            </button>
                            <button type="button" class="btn btn-sm btn-primary create" sec:authorize="hasAuthority('role.create')">新增</button>
                            <button type="button" id="loading-example-btn"
                                    onclick="javascript:window.location.reload();" class="btn btn-white btn-sm">刷新
                            </button>
                        </div>
                        <!--搜索内容结束-->

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>角色名称</th>
                                <th>角色编码</th>
                                <th>描述</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="gradeX" th:each="item,it : ${page.list}">
                                <td class="text-center" th:text="${it.count}">11</td>
                                <td th:text="${item.roleName}">22</td>
                                <td th:text="${item.roleCode}">33</td>
                                <td th:text="${item.description}">33</td>
                                <td th:text="${#dates.format(item.createTime,'yyyy-MM-dd HH:mm:ss')}">33</td>
                                <td class="text-center">
                                    <a class="edit" th:attr="data-id=${item.id}" sec:authorize="hasAuthority('role.edit')">修改</a>
                                    <a class="delete" th:attr="data-id=${item.id}" sec:authorize="hasAuthority('role.delete')">删除</a>
                                    <a class="assgin" th:attr="data-id=${item.id}" sec:authorize="hasAuthority('role.assgin')">分配权限</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <!--分页插件页码开始-->
                        <div class="row" th:include="common/pagination :: pagination"></div>
                        <!--分页插件页码结束-->

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!--在使用thymeleaf时，前端页面如要在javascript中获取后端传入的数据，需要使用<script th:inline="javascript">-->
<script th:inline="javascript">
    //弹出层事件
    $(function () {
        //新增
        $(".create").on("click", function () {
            opt.openWin("/role/create", "新增", 580, 430);
        });
        //修改
        $(".edit").on("click", function () {
            var id = $(this).attr("data-id");
            opt.openWin('/role/edit/' + id, '修改', 580, 430);
        });
        //删除
        $(".delete").on("click", function () {
            var id = $(this).attr("data-id");
            opt.confirm('/role/delete/' + id);
        });
        //分配权限
        $(".assign").on("click",function () {
            var id = $(this).attr("data-id");
            opt.openWin("/role/assignShow/"+id,'修改',580,430);
        });
    });

</script>
</body>
</html>
```





# 11 Session共享

## 11.1业务介绍

当前我们的服务都是单独部署，web-admin与web-front都使用了session保存当前用户信息，不存在session问题，在生成环境我们的服务都会集群部署多份，这时就会出现session问题，所以必须实现session共享。

### 11.1.1Session共享方案

session复制：

- 应用服务器开启web容器的session复制功能，在集群中的几台服务器之间同步session对象，浪费内存。

session绑定：

- 利用hash算法，比如nginx的ip_hash,使得同一个Ip的请求分发到同一台服务器上。宕机就完了。

利用cookie记录session：

- session记录在客户端，每次请求服务器的时候，将session放在请求中发送给服务器，不安全。

session服务器（推荐使用，本次主要讲解的内容）：

- 利用独立部署的session服务器（集群）统一管理session，服务器每次读写session时，都访问session服务器。



## 11.2共享原理

### 11.2.1Session共享原理

Session 共享实现的原理是将原来内存中的 Session 放在多个服务器（如Tomcat）都能访问到的位置，如数据库，Redis 中等，从而实现多实例 Session 共享。

实现共享后，只要浏览器的 Cookie 中的 Session ID 没有改变，多个实例中的任意一个被销毁不会影响用户访问。



### 11.2.2Spring Session共享原理

当请求进来的时候，SessionRepositoryFilter 会先拦截到请求，将 request 和 response 对象转换成 SessionRepositoryRequestWrapper 和 SessionRepositoryResponseWrapper 。

后续当第一次调用 request 的getSession方法时，会调用到 SessionRepositoryRequestWrapper 的`getSession`方法。这个方法是被重写过的，逻辑是先从 request 的属性中查找，如果找不到；再查找一个key值是"SESSION"的 Cookie，通过这个 Cookie 拿到 SessionId 去 Redis 中查找，如果查不到，就直接创建一个RedisSession 对象，同步到 Redis 中。

总结：拦截请求，将之前在服务器内存中进行 Session 创建销毁的动作，改成在 Redis 中创建。



## 11.3Spring Session集成

查找步骤：浏览器会发送JSission和Sission到服务器，如果Jsission没找到，就会使用Sission去Redis中去查找！可以按F12查看浏览器请求数据来确认。⚠️

### 11.3.1引入依赖

shf_parent添加版本管理

```xml
<redis-session.version>1.3.5.RELEASE</redis-session.version>
```

```xml
<!--spring-session 同步到redis-->
<dependency>
    <groupId>org.springframework.session</groupId>
    <artifactId>spring-session-data-redis</artifactId>
    <version>${redis-session.version}</version>
</dependency>
```

web_front引入依赖

```xml
    <dependencies>
        <!--spring-session 同步到redis-->
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
            <!--排除了两个jar包，我们用的是5版本的，而这两个spring相关jar包是4.2的，不兼容-->
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-aop</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-context</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
```



### 11.3.2添加配置

spring/spring-redis.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                         http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--Jedis连接池的相关配置-->
    <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
        <!--最大连接数, 默认8个-->
        <property name="maxTotal" value="100"></property>
        <!--最大空闲连接数, 默认8个-->
        <property name="maxIdle" value="50"></property>
        <!--允许借调 在获取连接的时候检查有效性, 默认false-->
        <property name="testOnBorrow" value="true"/>
        <!--允许归还 在return给pool时，是否提前进行validate操作-->
        <property name="testOnReturn" value="true"/>
    </bean>
    <!--配置JedisConnectionFactory-->
    <bean id="connectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
        <property name="hostName" value="39.106.35.112"/>
        <property name="password" value="abc123"/>
        <property name="port" value="6379"/>
        <property name="database" value="0"/>
        <property name="poolConfig" ref="jedisPoolConfig"/>
    </bean>
    <!-- 配置session共享 -->
    <bean id="redisHttpSessionConfiguration"
          class="org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration">
        <property name="maxInactiveIntervalInSeconds" value="600" />
    </bean>
</beans>
```

spring-mvc.xml引入配置

```xml
<!--加载Redis配置文件-->
<import resource="spring-redis.xml"></import>
```



### 11.3.3添加session共享过滤器

web.xml中添加过滤器

该过滤器必须是第一个过滤器，所有的请求经过该过滤器后执行后续操作

```xml
<!-- spring session共享filter -->
<!-- 该过滤器必须是第一个过滤器，所有的请求经过该过滤器后执行后续操作 -->
<!-- 该过滤器不会影响处理POST中文乱码-->
<filter>
  <filter-name>springSessionRepositoryFilter</filter-name>
  <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
  <filter-name>springSessionRepositoryFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```

完整web.xml示例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!-- spring session共享filter -->
    <!-- 该过滤器必须是第一个过滤器，所有的请求经过该过滤器后执行后续操作 -->
    <!-- 该过滤器不会影响处理POST中文乱码-->
    <filter>
        <filter-name>springSessionRepositoryFilter</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>springSessionRepositoryFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!-- 解决post乱码 添加字符编码过滤器 -->
    <filter>
        <filter-name>encode</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceRequestEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encode</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!-- 配置SpringMVC框架前端控制器 -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>

</web-app>
```





# 12 阶段性总结

## 12.1web阶段

- HTML+CSS

  - 重点：能看懂、微调(Vue渲染+Thymeleaf渲染)

- JS  ★

  - 已经有java基础，变量、数组、对象、流程控制结构

- Vue ★

  - 经常出错，错误很哪找(去浏览器的控制台)
  - 经常会出现浏览器缓存

- Servlet

  - 请求处理 ★
  - 响应处理 ★
  - 三个作用域对象 ★

- Thymeleaf ★

  - 服务器渲染的工具，替代之前的jsp

- Cookie和Session ★

- AJAX ★★★

  - JS
  - Vue ☆
  - jQuery

- Filter和Listener

  - 会配置过滤器就可以

  

## 12.2SSM框架阶段

### 12.2.1MyBatis

- MyBatis的框架搭建
  - 核心配置文件
    - 所有的配置都可以被Spring所取代，暂时还保留了一些
  - 映射文件 ★★★
- 动态sql ★
  - where/if/set/sql
- 缓存机制
- 分页插件 ★



### 12.2.2Spring

- IOC ★★★

  - 将MyBatis的dao层对象都放在ioc容器内了
  - 但凡遇到一些对象，先把他放在ioc容器内，哪里用，哪里就进行注入(自动装配)
  - bean标签  ★
  - set注入  ★
  - 四个创建对象的注解 ★
  - 自动装配 ★

- AOP ★★★

  - 面向切面编程
    - 将非核心业务提取出来，随时添加，随时拿走！不需要修改核心业务代码
  - 在声明式事务使用

  

### 12.2.3SpringMVC

- SpringMVC的HelloWorld
  - 前端控制器 ★
  - Controller ★

- 设置请求路径(访问路径、虚拟路径) ★
- 处理请求数据 ★
- 处理响应数据 ★
- SpringMVC的运行流程（1）
- 处理JSON ★
  - 主要针对的是异步请求

- 文件的上传下载
- 拦截器 ★
- 异常处理
- SpringMVC的运行流程（2）



## 12.3项目阶段

练习SSM的使用

- 请求可能出现问题
- 业务层业务处理可能出现问题
- 持久层操作数据库可能出现问题

