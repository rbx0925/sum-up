# 1 Spring Boot入门

## 1.1Spring Boot概述

其最主要作用就是帮助开发人员快速的构建庞大的spring项目，并且尽可能的减少一切xml配置。

主要解决的问题:

1. 让开发人员不再写XML配置文件，不用关注复杂的配置、混乱的依赖管理
2. 让开发人员专心关注业务实现



## 1.2Spring Boot入门程序

### 1.2.1手动配置

除了手动创建springboot，还可以自动创建，在下面讲。⚠️

实现步骤：

- 创建Maven工程
- 添加依赖(springboot父工程依赖 , web启动器依赖)
- 编写启动引导类(springboot项目运行的入口)
- 编写处理器Controller
- 启动项目

#### 1.2.1.1添加依赖

添加父工程坐标、添加JDK版本、添加web启动器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>


    <!--添加父工程坐标-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.6.RELEASE</version>
    </parent>

    <groupId>com.atguigu</groupId>
    <artifactId>springboot_leaf</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--jdk插件已经在父工程中定义好了 , 默认会读取${java.version}变量值-->
    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!--添加从父工程集成的web启动器-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>


</project>
```



#### 1.2.1.2创建启动引导类

- 启动类的类名通常叫xxxApplication
- 传入参数为字节码对象和命令行参数
- 使用@SpringBootApplication注解声明这是一个SpringBoot启动器
- 启动时会自动扫描启动器所在的包以及他的子包，所以创建的Controller必须在他下面⚠️

```java
package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//SpringBoot启动器，声明这是一个SpringBoot应用程序
@SpringBootApplication
public class FirstSpringBootApplication {
    public static void main(String[] args) {
      	//传入当前类的字节码对象和args两个参数
        SpringApplication.run(FirstSpringBootApplication.class,args);
    }
}

```



#### 1.2.1.3创建处理器Controller

- 处理器必须在启动引导类的子包里，不然扫描不到⚠️
- @RestController注解是@RequestBody和@Controller的组合版

```java
package com.atguigu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.HelloController
 */
//该注解是@RequestBody和@Controller的组合版
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello Spring Boot!";
    }
}

```



### 1.2.2自动配置

创建Spring Boot项目：

- 选择创建Spring Initializr模块，可在Service URL中修改中国的地址
- 创建Maven项目
- 选择Java版本和打包类型
- 在Developer Tools中搜索Sping Web并添加，相当于添加了Web启动器依赖⚠️
- 创建项目（自动创建项目，Spring Boot版本只能选择最新的，创建后可再修改，我们使用2.3.6）
- 下载时候不能停，一旦Maven仓库生成啦lasted文件就不会再下载了⚠️
- 注意：刚开始下载时可选择Custom：http://start.aliyun.com/进行下载，可能比https://start.spring.io快

#### 1.2.2.1修改pom.xml

- 修改继承的父工程版本，改为2.3.6（默认3.0.0版本，只支持JDK11及以上）⚠️
- 看JDK版本是否正确，修改JDK版本

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
  
  	<!--修改继承的版本为2.3.6-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.6.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo</name>
    <description>demo</description>
  
  	<!--修改JDK版本为8-->
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>
      	<!--父工程的web启动器-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
				<!--父工程的web测试插件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <!--打包插件，配置后不止打包自己写的控制类，包括依赖也一同打包-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```



#### 1.2.2.2修改application

修改application.properties文件内可配置端口号、上下文路径等

```properties
server.port=8090
server.servlet.context-path:/speingboot
```



### 1.2.3继承版本概述

父工程的父工程（爷爷）中进行了统一的依赖管理，指定了web启动器和其他依赖的版本

而tomcat、json、mvc因为被web启动器依赖了他们，因为依赖的传递性，范围为compile，所以项目也拥有这些依赖



## 1.3配置文件详解

springboot支持二种类型的配置文件

- properties属性配置文件
- yaml配置文件

### 1.3.1文件命名规范★

父工程spring-boot-start-dependncies.pom.xml文件中规范了命名格式，只要符合命名格式将自动添加到IoC容器中

- ?匹配一个字符
- *匹配任意字符
- **匹配多层路径
- 所以文件必须是application开头，目录层级不限⚠️

```xml
<resource>
  <directory>${basedir}/src/main/resources</directory>
  <filtering>true</filtering>
  <includes>
    <include>**/application*.yml</include>
    <include>**/application*.yaml</include>
    <include>**/application*.properties</include>
  </includes>
</resource>
```



### 1.3.2YAML配置文件

YAML配置文件是树形结构的配置文件

语法 :

- 数据结构用树形结构呈现，通过缩进来表示层级

- 连续的项目通过减号 ” - ” 来表示

  ```yaml
  student:
  	hobby:
  		-	eat
  		-	sleep
  ```

- 键值结构里面的key/value对用冒号 ” : ” 来分隔，键值中间有分隔⚠️。

  ```yaml
  username: root
  ```

- YAML配置文件的扩展名是yaml 或 yml

 yml配置文件的特征：

- 树状层级结构展示配置项；
- 配置项之间如果有关系的话需要分行，空两格；⚠️
- 配置项如果有值的话，那么需要在 :之后空一格再写配置项值；⚠️

```yaml
spring:
  jdbc:
    datasource:
      driverClassName: com.mysql.jdbc.Driver
      url: jdbc:mysql:///springboot_01
      username: root
      password: root
```

相较application.properties 配置文件，层级更加鲜明

```properties
spring.jdbc.datasource.driverClassName=com.mysql.jdbc.driver
spring.jdbc.datasource.url=jdbc:mysql:///springboot_01
spring.jdbc.datasource.username=root
spring.jdbc.datasource.password=root
```



### 1.3.3配置文件优先级★

所有的配置文件无论`application.properties`还是`application.yml`都会被读取加载生效，当配置了相同的属性时，默认以properties优先。

若在`application.yml`设置了`spring.profiles.active`，以`spring.profiles.active`设置的文件为准，其他的文件根本不会被读取加载。⚠️



### 1.3.4开发/测试/上线环境切换★

项目开发过程中一般会需要开发环境、测试环境、上线环境，我们可以使用`spring.profiles.active`进行切换：

配置多个YAML配置文件，这些文件名称必须为application-***.yml，并且在application.yml中切换。

比如：
创建application-dev.yml文件如下：

```yaml
spring:
  jdbc:
    datasource:
      driverClassName: com.mysql.jdbc.Driver
      url: jdbc:mysql://location/books
      username: root
      password: root
```

创建application-test.yml文件如下：

```yaml
spring:
  jdbc:
    datasource:
      driverClassName: com.mysql.jdbc.Driver
      url: jdbc:mysql://192.168.3.1/books
      username: root
      password: root
```

创建application-pro.yml文件如下：

```yaml
spring:
  jdbc:
    datasource:
      driverClassName: com.mysql.jdbc.Driver
      url: jdbc:mysql://37.89.30.102/books
      username: root
      password: roottubffrtfafhafadaf6343fgdhafat6e
```

在application.yml 文件中选择开发环境：

```yaml
spring:
  profiles:
    active: dev
```

总结：

1. 如果properties和yml文件都存在，同时又不存在spring.profiles.active设置时，如果有重叠属性，默认以properties优先。⚠️⚠️⚠️
2. 如果设置了spring.profiles.active，并且有重叠属性，以active设置优先。
3. 可以在两种文件中分别增加server.port属性指定不同的端口，启动项目查看控制台端口号进行测试。





# 2 Spring Boot 自动配置★★

## 2.1注入注解

@ConfigurationProperties注解，是SpringBoot提供的重要注解, 他可以将一些配置属性批量注入到bean对象。

@Bean标注在方法上(返回某个实例的方法)，等价于spring的xml配置文件中的<bean>，作用是注册bean对象，方法名是Bean的Id⚠️

@Value注解，每次只可以注入一个简单类型的属性值。

### 2.1.1单个注入注解

使用@Value一个个注入属性值，可从配置文件中直接注入

```java
@Component
public class DataSourceProperties {

    @Value("${spring.jdbc.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.jdbc.datasource.url}")
    private String url;
    @Value("${spring.jdbc.datasource.username}")
    private String username;
    @Value("${spring.jdbc.datasource.password}")
    private String password;

    // 省略getter和setter.....
}
```



### 2.1.2批量注入注解★

核心在于@ConfigurationProperties和@EnableConfigurationProperties的配合使用。（一个实现批量注入，一个开启批量注入）。⚠️

#### 2.1.2.1使用注解批量注入值★

使用@ConfigurationProperties注解

语法格式：

- prefix="spring.jdbc.datasource"  表示读取属性文件中前缀为spring.jdbc.datasource的值。

使用方法：

- 在类上通过@ConfigurationProperties注解声明该类要读取属性配置
- 可以去掉@Component注解，爆红但不影响运行，Controller中使用对应的注解后，就会识别创建代理对象，红线就会消失
- 属性名称和配置文件中的key必须要保持一致才可以注入成功
- Spring Boot默认读取application.properties属性文件，除非在application.yml中指定了环境文件才会加载其他文件。

```java
package com.atguigu.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//该注解可以不用再加，只是此处和调用他的地方会报红，
//@Component
//通过配置文件中的类为其注入属性值
@ConfigurationProperties(prefix = "spring.jdbc.datasource")
public class DataSourceProperties {

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    
    //省略getter和setter.....
}    
```

然后，在Controller中使用@EnableConfigurationProperties注解，传入该类的字节码对象：

- 首先会根据该类的字节码对象，基于代理模式创建代理对象
- 然后将配置文件里对应的值注入进去

```java
@Controller
//传入字节码对象，基于代理模式创建代理对象
@EnableConfigurationProperties(DataSourceProperties2.class)
public class HelloController {
    
    @Autowired
    private DataSourceProperties2 dataSourceProperties2 ;

    @RequestMapping(path = "/hello")
    @ResponseBody
    public String sayHello(){
        System.out.println(dataSourceProperties2);
        return "hello spring boot";
    }
}
```



#### 2.1.2.2配置文件提示依赖

配置依赖

```xml
<!--批量注入注解依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
  	<!--子项目中将不会包含这个依赖。常用于解决jar包冲突问题，和exclusion排除jar有异曲同工之妙-->
    <optional>true</optional>
</dependency>
```

自动提示

自动提示的内容是小写字母+"-"，比如driverClassName = driver-class-name，批量注入可支持自动驼峰，但是单个注入不支持⚠️

```yaml
spring:
  jdbc:
    datasource:
      driver-class-name: com.mysql.jdbc.Driver
      url: jdbc:mysql://location/books
      username: root
      password: root
```



## 2.2SpringBootApplication注解★★★

@SpringBootApplication注解加在启动类上

### 2.2.1启动类注解源码★★★

启动类之所以要在SpringApplication.run()中传入自己的类型，是为了通过自己的Class对象获取自己都添加了什么注解，而获取到@SpringBootApplication注解后，该注的元注解中又有扫描包注解帮助创建对象、自动配置注解加载自动配置类等。⚠️⚠️⚠️

@SpringBootApplication的元注解是@SpringBootConfiguration，而@SpringBootConfiguration的元注解是@Configuration注解，该注解用来指定一个类为配置类 。则等于@SpringBootConfiguration注解本质上就是一个@Configuration注解

@ComponentScan : 组件扫描注解，默认扫描启动类所在包及子包下的类的注解

@EnableAutoConfiguration : 自动配置注解 , 添加了此注解会自动去读取spring.factories配置文件中的自动配置类，现版本一共127个，但是并不是每个都起作用，因为自动配置类都配置了条件化注解，满足条件才会生效

@SpringBootApplication注解的源码：

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
//声明该类是配置类注解，该注解的元注解是Spring提供的@Configuration注解
@SpringBootConfiguration
//开启自动配置的注解，默认启动时根据条件加载127个配置类，帮助配置环境（仅当前版本）
@EnableAutoConfiguration
//扫描包注解，默认扫描启动类所在包及子包下的类身上的注解
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
    //省略...
}
```



## 2.3条件化配置注解

### 2.3.1条件化配置注解种类

常用的条件化选择注解如下 :

| 注解                            | 作用                             |
| ------------------------------- | -------------------------------- |
| @ConditionalOnBean              | 如果存在某个Bean, 配置类生效     |
| @ConditionalOnMissingBean       | 如果不存在某个Bean, 配置类生效   |
| @ConditionalOnClass             | 如果存在某个类, 配置类生效       |
| @ConditionalOnMissingClass      | 如果不存在某个类, 配置类生效     |
| @ConditionalOnProperty          | 如果存在某个属性配置, 配置类生效 |
| @ConditionalOnWebApplication    | 如果是一个web应用, 配置类生效    |
| @ConditionalOnNotWebApplication | 如果不是一个web应用, 配置类生效  |



## 2.4自动配置注解配置的所配置类

自动配置注解@EnableAutoConfiguration加载的类，根据条件加载前端控制器、Redis等127个配置类⚠️

### 2.4.1自动配置类列表文件

配置类列表文件spring.factories，以键值对的形式存储的所有127个自动配置类信息：

```factroies
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration,\
org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRestClientAutoConfiguration,\
org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.r2dbc.R2dbcDataAutoConfiguration,\
org.springframework.boot.autoconfigure.data.r2dbc.R2dbcRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.r2dbc.R2dbcTransactionManagerAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,\
org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration,\
org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration,\
org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration,\
org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration,\
org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,\
org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration,\
org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration,\
org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration,\
org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration,\
org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration,\
org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration,\
org.springframework.boot.autoconfigure.influx.InfluxDbAutoConfiguration,\
org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration,\
org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration,\
org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration,\
org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration,\
org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration,\
org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration,\
org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration,\
org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration,\
org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration,\
org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration,\
org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration,\
org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration,\
org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,\
org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration,\
org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration,\
org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,\
org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration,\
org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketMessagingAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketRequesterAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketServerAutoConfiguration,\
org.springframework.boot.autoconfigure.rsocket.RSocketStrategiesAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration,\
org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration,\
org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration,\
org.springframework.boot.autoconfigure.security.rsocket.RSocketSecurityAutoConfiguration,\
org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyAutoConfiguration,\
org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration,\
org.springframework.boot.autoconfigure.session.SessionAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.client.reactive.ReactiveOAuth2ClientAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration,\
org.springframework.boot.autoconfigure.security.oauth2.resource.reactive.ReactiveOAuth2ResourceServerAutoConfiguration,\
org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration,\
org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration,\
org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration,\
org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration,\
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration,\
org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration,\
org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration,\
org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration,\
org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration,\
org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration,\
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.reactive.WebSocketReactiveAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration,\
org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration,\
org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration,\
org.springframework.boot.autoconfigure.webservices.client.WebServiceTemplateAutoConfiguration
```



### 2.4.2按条件加载配置类

以一个加载的类举例：org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration

```java
@Configuration(
    proxyBeanMethods = false
)
//当存在RedisOperations类时，该配置起作用
@ConditionalOnClass({RedisOperations.class})
@EnableConfigurationProperties({RedisProperties.class})
@Import({LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class})
public class RedisAutoConfiguration {
    public RedisAutoConfiguration() {
    }

  	//两个注解的作用：当满足条件时，又缺失名字为redisTemplate的RedisTemplate类的对象，Spring会创建对象放到IoC里
    @Bean
    @ConditionalOnMissingBean(
        name = {"redisTemplate"}
    )
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    @ConditionalOnMissingBean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
```



### 2.4.3WebMvcAutoConfiguration

启动类通过注解自动加载的类：org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration

因为我们配置了DispatcherServlet 满足上面定义的条件，所以WebMvcAutoConfiguration会生效，该类会帮我们配置视图解析器和处理适配器。

总结：

1. 该配置起作用后，会创建WebMvcAutoConfiguration自动配置类对象
2. 并通过批量注入功能，为其webMvcProperties属性从配置文件中通过`prefix = "spring.mvc"`批量注入属性值
3. webMvcProperties属性有view属性，view属性是View类的实例，有前缀prefix属性、后缀suffix属性。
4. 可在配置文件中按照`spring.mvc.view.prefix/suffix`为其赋值
5. 然后WebMvcAutoConfiguration即可为`InternalResourceViewResolver视图解析器`的前后缀属性赋值⚠️
6. 赋值视图解析器对象前后缀属性的代码：this.mvcProperties.getView().getPrefix()

```java
//声明该类是配置类
@Configuration(
    proxyBeanMethods = false
)
//如果是一个web应用, 配置类生效
@ConditionalOnWebApplication(
    type = Type.SERVLET
)
//如果有Servler、DispatcherServlet、WebMvcConfigurer这三个类，该配置起作用（Web启动器恰恰涵盖他们的依赖⚠️）
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
//缺少WebMvcConfigurationSupport的对象，该类起作用
@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
@AutoConfigureOrder(-2147483638)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
public class WebMvcAutoConfiguration {
    public static final String DEFAULT_PREFIX = "";
    public static final String DEFAULT_SUFFIX = "";
    private static final String[] SERVLET_LOCATIONS = new String[]{"/"};

  	//省略...
  
    @Configuration(
        proxyBeanMethods = false
    )
    @Import({EnableWebMvcConfiguration.class})
  	//对WebMvcProperties对象开启批量注入功能，则WebMvcProperties和ResourceProperties加了开启批量注入的注解
    @EnableConfigurationProperties({WebMvcProperties.class, ResourceProperties.class})
    @Order(0)
    public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer {
        private static final Log logger = LogFactory.getLog(WebMvcConfigurer.class);
        private final ResourceProperties resourceProperties;
      	//从该对象的mvcProperties属性的view属性中获取视图前后缀，为视图解析器对象赋值
      	//this.mvcProperties.getView().getPrefix()
        private final WebMvcProperties mvcProperties;
        private final ListableBeanFactory beanFactory;
        private final ObjectProvider<HttpMessageConverters> messageConvertersProvider;
        private final ObjectProvider<DispatcherServletPath> dispatcherServletPath;
        private final ObjectProvider<ServletRegistrationBean<?>> servletRegistrations;
        final ResourceHandlerRegistrationCustomizer resourceHandlerRegistrationCustomizer;

      	//省略...
      
      	//若没有视图解析器类的实例对象，则Spring创建视图解析器对象
        @Bean
        @ConditionalOnMissingBean
        public InternalResourceViewResolver defaultViewResolver() {
            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
          	//设置视图解析器实例的视图前缀属性，从WebMvcProperties对象的实例中获取
            resolver.setPrefix(this.mvcProperties.getView().getPrefix());
          	//设置视图解析器实例的视图后缀属性，从WebMvcProperties对象的实例中获取
            resolver.setSuffix(this.mvcProperties.getView().getSuffix());
            return resolver;
        }
      
      	//省略...
      
      	//处理适配器
        @Bean
        public RequestMappingHandlerAdapter requestMappingHandlerAdapter(@Qualifier("mvcContentNegotiationManager") ContentNegotiationManager contentNegotiationManager, @Qualifier("mvcConversionService") FormattingConversionService conversionService, @Qualifier("mvcValidator") Validator validator) {
            RequestMappingHandlerAdapter adapter = super.requestMappingHandlerAdapter(contentNegotiationManager, conversionService, validator);
            adapter.setIgnoreDefaultModelOnRedirect(this.mvcProperties == null || this.mvcProperties.isIgnoreDefaultModelOnRedirect());
            return adapter;
        }

        protected RequestMappingHandlerAdapter createRequestMappingHandlerAdapter() {
            if (this.mvcRegistrations != null) {
                RequestMappingHandlerAdapter adapter = this.mvcRegistrations.getRequestMappingHandlerAdapter();
                if (adapter != null) {
                    return adapter;
                }
            }

            return super.createRequestMappingHandlerAdapter();
        }
      
    }
  
}
```

向WebMvcProperties对象批量注入的WebMvcProperties对象，根据注解分析配置文件中赋值写法为`spring.mvc.view.前后缀=""`

```java
@ConfigurationProperties(
  	//批量注入的配置文件前缀是pring.mvc，要去配置文件中找spring.mvc对象为WebMvcProperties对象的属性注入值
    prefix = "spring.mvc"
)
public class WebMvcProperties {
    private DefaultMessageCodesResolver.Format messageCodesResolverFormat;
    private Locale locale;
    private LocaleResolver localeResolver;
    private final Format format;
    private boolean dispatchTraceRequest;
    private boolean dispatchOptionsRequest;
    private boolean ignoreDefaultModelOnRedirect;
    private boolean publishRequestHandledEvents;
    private boolean throwExceptionIfNoHandlerFound;
    private boolean logRequestDetails;
    private boolean logResolvedException;
    private String staticPathPattern;
    private final Async async;
    private final Servlet servlet;
  	//该类有视图对象属性view
    private final View view;
    private final Contentnegotiation contentnegotiation;
    private final Pathmatch pathmatch;
}
```

View是内部类，有前缀和后缀属性，在配置文件中为视图解析器前后缀赋值其实就是为该对象的prefix和suffix属性赋值

```java
public static class View {
  	//前缀
    private String prefix;
  	//后缀
    private String suffix;

    public View() {
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
```



## 2.5自动配置原理★★★

springboot会把META-INF/spring.factories文件中的类都注入到spring容器中吗？⚠️⚠️⚠️

不一定。 `META-INF/spring.factories` 文件中列出的类是Spring Boot自动配置的入口点，这些类将在Spring Boot应用程序启动时自动加载。这些类必须实现 `org.springframework.boot.autoconfigure.EnableAutoConfiguration` 接口，以便Spring Boot自动配置机制可以将它们自动加载到应用程序上下文中。

但是，仅仅实现 `EnableAutoConfiguration` 接口并不意味着这些类都会被注入到Spring容器中。它们只是在应用程序启动时被自动加载，以便Spring Boot自动配置机制可以在需要时调用它们。实际上，只有在特定的条件下，这些自动配置类才会被注入到Spring容器中。

因此，虽然 `META-INF/spring.factories` 中的类都是Spring Boot自动配置的入口点，但它们不一定都会被注入到Spring容器中。

### 2.5.1加载spring.factories

当启动SpringBoot时，会创建SpringApplication，构造函数内执行如下方法

```java
this.setInitializers(this.getSpringFactoriesInstances(ApplicationContextInitializer.class));
```

继续执行，会发现将实例化工厂类对象

```java
		private <T> Collection<T> getSpringFactoriesInstances(Class<T> type) {
        return this.getSpringFactoriesInstances(type, new Class[0]);
    }
		
    private <T> Collection<T> getSpringFactoriesInstances(Class<T> type, Class<?>[] parameterTypes, Object... args) {
        ClassLoader classLoader = this.getClassLoader();
      	//该Set集合存储实例化工厂类的名称
        //通过SpringFactoriesLoader加载器获取实例化工厂类的名称
        Set<String> names = new LinkedHashSet(SpringFactoriesLoader.loadFactoryNames(type, classLoader));
      	//通过createSpringFactoriesInstances将工厂类进行实例化
        List<T> instances = this.createSpringFactoriesInstances(type, parameterTypes, classLoader, args, names);
        AnnotationAwareOrderComparator.sort(instances);
        return instances;
    }
```

发现此处会利用类加载器加载一个文件： META-INF/spring.factories，ClassLoader默认是从classpath下读取文件，因此，SpringBoot会在初始化的时候，加载所有classpath:META-INF/spring.factories文件，包括jar包当中的。而在Spring的一个依赖包：spring-boot-autoconfigure中，就有这样的文件：`spring-boot-autoconfigure-2.3.6.RELEASE.jar!/META-INF/spring.factories`。



**springBoot自动配置原理**
自动配置就是把第三方组件中的Bean,装载到IOC容器里面,不需要开发人员再去写Bean相关的配置,注解@EnableAutoConfiguration,@Configuration,@ConditionalOnClass就是自动配置的核心,@springBootApplication修饰的类也会被@Configuration间接修饰,即"源配置类",Spring框架会对"源配置类"所在的包进行组件扫描(Compoent Scan),SpringBoot框架最终会导入AutoConfigurationImportSelector来实现自动配置,
首先会通过SpringFactories机制加载配置文件,通过ClassLoader去获取classpath中配置文件META/spring.factories,找出所有符合条件的自动配置类,根据注解@Conditional过滤掉不必要的自动配置类⚠️⚠️⚠️



我们引入的任何第三方启动器，只要其他框架实现自动配置，也都会有类似文件。⚠️

```java
public static List<String> loadFactoryNames(Class<?> factoryType, @Nullable ClassLoader classLoader) {
    String factoryTypeName = factoryType.getName();
    return (List)loadSpringFactories(classLoader).getOrDefault(factoryTypeName, Collections.emptyList());
}

private static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader) {
    MultiValueMap<String, String> result = (MultiValueMap)cache.get(classLoader);
    if (result != null) {
        return result;
    } else {
        try {
          	//加载类路径下的META-INF/spring.factories文件⚠️⚠️⚠️
            Enumeration<URL> urls = classLoader != null ? classLoader.getResources("META-INF/spring.factories") : ClassLoader.getSystemResources("META-INF/spring.factories");
            MultiValueMap<String, String> result = new LinkedMultiValueMap();

            while(urls.hasMoreElements()) {
                URL url = (URL)urls.nextElement();
                UrlResource resource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                Iterator var6 = properties.entrySet().iterator();

                while(var6.hasNext()) {
                    Map.Entry<?, ?> entry = (Map.Entry)var6.next();
                    String factoryTypeName = ((String)entry.getKey()).trim();
                    String[] var9 = StringUtils.commaDelimitedListToStringArray((String)entry.getValue());
                    int var10 = var9.length;

                    for(int var11 = 0; var11 < var10; ++var11) {
                        String factoryImplementationName = var9[var11];
                        result.add(factoryTypeName, factoryImplementationName.trim());
                    }
                }
            }

            cache.put(classLoader, result);
            return result;
        } catch (IOException var13) {
            throw new IllegalArgumentException("Unable to load factories from location [META-INF/spring.factories]", var13);
        }
    }
}
```



### 2.5.2读取自动配置类

自动配置加载文件spring.factories中以`key(注解):value(127个配置类)`的形式存储所有自动配置类，所以当前jar包可以找到这些配置类并按条件加载。关于详细内容章节 `2.2.3自动配置加载文件` 和章节 `2.2.4WebMvcAutoConfiguration` 已经做了详细介绍。



### 2.5.3自动配置类的默认属性

自动配置类的默认属性配置，是通过`批量注入注解`+`YAML配置文件` /` Properties配置文件`来实现的，不配置时没有默认值则为null。

自动配置类的批量注入注解中明确规定了配置文件中的前缀，通过该前缀即可在配置文件中设置默认属性值，启动SpringBoot时，通过自动批量注入注解，注入默认值⚠️⚠️⚠️



## 2.6自定义启动器★

### 2.6.1创建启动器项目

创建项目spring-boot-jdbc-starter，以配置自动配置数据源类为例

#### 2.6.1.1添加启动期依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu</groupId>
    <artifactId>spring-boot-jdbc-starter</artifactId>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.6.RELEASE</version>
    </parent>



    <dependencies>
        <!--引入spring‐boot‐starter；所有starter的基本配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!--德鲁伊连接池，自动配置德鲁伊连接池所需依赖-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.12</version>
        </dependency>

        <!--C3P0是一个开源的JDBC连接池，自动配置C3P0连接池所需依赖-->
        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.2</version>
        </dependency>

        <!--编写配置文件有提示的依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>


</project>
```



#### 2.6.1.2创建属性配置类

该类的作用是通过批量注入读取配置文件内的属性，间接为数据源赋值，起到桥梁作用

```java
package com.atguigu.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 将配置文件中的值注入到该对象中，再将该对象的属性取出来赋值给数据源对象，起到中间桥梁的作用
 * @AllClassName: com.atguigu.springboot.properties.DataSourceProperties
 */
@Component
@ConfigurationProperties(prefix = "spring.jdbc.datasource")
public class DataSourceProperties {

    private String driverClassName;
    private String url;
    private String username;
    private String password;


    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "HelloController{" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
```



#### 2.6.1.3创建自动配置类

让Spring管理数据源类，先创建，再通过属性配置类去读配置文件，为数据源类的属性注入值

```java
package com.atguigu.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.atguigu.springboot.properties.DataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @Description: 创建配置类，返回数据源
 * @AllClassName: com.atguigu.springboot.config.DataSourceAutoConfig
 */
//声明当前类是一个配置类
@SpringBootConfiguration
//批量注入
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceAutoConfig {


    @Autowired
    DataSourceProperties dataSourceProperties;

    //当该类被自动配置后，创建javax.sql.DataSource接口对象放入Spring容器
    //并通过DataSourceProperties类获取在配置文件中的配置，为其属性批量注入值
    @Bean
    public DataSource createDataSource(){

        //创建数据连接池
        DruidDataSource dataSource = new DruidDataSource();

        //设置数据库相关信息
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());

        //返回德鲁伊连接池
        return dataSource;
    }

}
```



#### 2.6.1.4编写自动配置文件

创建resources/META-INF/spring.factories，让SPringBoot启动期加载该文件，来完成自动配置

```factories
# Auto Configure
# Set ConfigClass AllName Return Spring Bean
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.atguigu.springboot.config.DataSourceAutoConfig
```



#### 2.6.1.5打包启动器

在项目的Maven中install打包，便于其他项目引入该启动器的坐标直接使用。



### 2.6.2使用自定义的启动器

使用自定义启动器时需要先添加依赖

#### 2.6.2.1添加自定义启动器坐标

```xml
<!--导入自定义的启动器的依赖-->
<dependency>
    <groupId>com.atguigu</groupId>
    <artifactId>spring-boot-jdbc-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```



#### 2.6.2.2配置文件添加配置信息

```yaml
spring:
  jdbc:
    datasource:
      driverClassName: com.mysql.jdbc.Driver
      url: jdbc:mysql://location/books
      username: root
      password: root
```



#### 2.6.2.3注入自定义自动配置类

```java
package com.atguigu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.HelloController
 */
@RestController
public class HelloController {

  	//注入
    @Autowired
    private DataSource dataSource;

    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("dataSource.getClass().getName() = " + dataSource.getClass().getName());
        return "Hello Spring Boot!";
    }


}
```



### 2.6.3总结

自定义的启动器中，在自动配置文件内设置了自动配置注解会加载的自动配置类DataSourceAutoConfig，然后自动配置类又通过属性配置类的批量注入。只要项目添加了自定义启动器，当SpringBoot启动时，就会从yaml配置文件中获取相关属性信息间接赋值给配置类的数据源对象，然后自动配置类将接口类型的数据源对象直接添加到spring容器中，则可以根据接口类型直接通过Spring自动注入使用。



## 2.7配置多种数据源★

同时配置德鲁伊和C3P0的数据源为例。

### 2.7.1修改启动器自动配置类

使用@ConditionalOnProperty注解，通过配置文件中type属性值的判断生效的数据源

```java
package com.atguigu.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.atguigu.springboot.properties.DataSourceProperties;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @Description: 创建配置类，返回数据源
 * @AllClassName: com.atguigu.springboot.config.DataSourceAutoConfig
 */
//声明当前类是一个配置类
@SpringBootConfiguration
//批量注入
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceAutoConfig {


    @Autowired
    DataSourceProperties dataSourceProperties;

    /**
     * 返回德鲁伊连接池
     * @return javax.sql.DataSource
     */
    @Bean
    //必须要有type属性，type的属性并且为druid，才会生效
    @ConditionalOnProperty(value = "spring.jdbc.datasource.type",havingValue = "druid")
    public DataSource createDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }

    /**
     * 返回C3P0连接池
     * @return javax.sql.DataSource
     */
    @Bean
    //必须要有type属性，type的属性并且为c3p0，才会生效
    @ConditionalOnProperty(value = "spring.jdbc.datasource.type",havingValue = "c3p0")
    public DataSource createC3P0DataSource() throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(dataSourceProperties.getDriverClassName());
        dataSource.setJdbcUrl(dataSourceProperties.getUrl());
        dataSource.setUser(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        return dataSource;
    }
}
```



### 2.7.2修改配置文件

使用该自定义启动器的模块，要在配置文件中指定数据源

```yaml
spring:
  jdbc:
    datasource:
      driverClassName: com.mysql.jdbc.Driver
      url: jdbc:mysql://location/books
      username: root
      password: root
      type: c3p0
```



### 2.7.3使用数据源

使用数据源时，可直接使用或对其类型进行判断后强转为具体的数据源类型，当然也可写一个工厂类进行返回这两个数据源类型

```java
package com.atguigu.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.controller.HelloController
 */
//该注解是@RequestBody和@Controller的组合版
@RestController
public class HelloController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/hello")
    public String sayHello() {
        //判断数据源类型是不是德鲁伊数据源
        if (dataSource instanceof DruidDataSource) {
            DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        }else {
            //若是C3P0数据源，则进行强转为C3P0数据源
            ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource) dataSource;
        }
        System.out.println("dataSource.getClass().getName() = " + dataSource.getClass().getName());
        return "Hello Spring Boot!";
    }
}
```





# 3 Spring Boot常用启动器

## 3.1静态资源目录和拦截器配置

### 3.1.1静态资源目录★

在WEB开发中我们经常需要引入一些静态资源 , 例如 : HTML , CSS , JS , 图片等 , 如果是普通的项目静态资源可以放在项目的webapp目录下，现在使用Spring Boot做开发 , 项目中没有webapp目录 , 我们的项目是一个jar工程，那么就没有webapp，所以在springboot中有一个叫做ResourceProperties的类，里面就定义了静态资源的默认查找路径：

ResourceProperties：

```java
public class ResourceProperties {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = new String[]{
      "classpath:/META-INF/resources/", 
      "classpath:/resources/", 
      "classpath:/static/", 
      "classpath:/public/"
    };
}
```

由类的代码可知，默认的静态资源路径为：

```properties
classpath:/META-INF/resources/
classpath:/resources/
classpath:/static/（推荐使用⚠️）
classpath:/public
```

只要静态资源放在这些目录中任何一个，SpringMVC都会帮我们处理。 我们习惯会把静态资源放在classpath:/static/ 目录下。

如果想要修改默认的静态资源路径, 配置如下 : (⚠️：自定义静态资源目录后，ResourceProperties类中四个路径将不再生效⚠️)

新建 application.yml

```yaml
spring:
  resources:
    static-locations: classpath:/webapp/
```



### 3.1.2自定义拦截器★★

回顾一下SpringMVC中配置拦截器的步骤 :

1. 编写一个拦截器(实现HandlerInterceptor接口)
2. 在XML文件中注册拦截器(mvc:interceptors)

而SpringBoot没有XML配置文件，需要借助配置类完成注册

1. 编写一个拦截器类，实现HandlerInterceptor接口⚠️
2. 通过WebMvcConfigurer配置类注册拦截器⚠️

编写拦截器步骤：

创建拦截器类，实现HandlerInterceptor接口

```java
package com.atguigu.interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 处理请求之前调用
     * 作用：开启事务，验证权限
     * 返回true才会放行处理请求，执行对应的Controller方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor拦截器的preHandle方法执行....");
        return true;
    }

    /**
     * 处理请求之后，渲染视图之前调用
     * 作用：查看和修改模型数据和视图
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor拦截器的postHandle方法执行....");
    }

    /**
     * 渲染视图之后调用
     * 作用：可以做一些清理的工作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor拦截器的afterCompletion方法执行....");
    }
}
```

注册拦截器步骤：

创建配置类，实现WebMvcConfigurer接口的addInterceptors方法，使用该方法完成注册及设置拦截路径。

```java
package com.atguigu.config;

import com.atguigu.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 注册拦截器需要使用配置类，继承WebMvcConfigurer接口
 * @AllClassName: com.atguigu.config.MyConfig
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {

    //注入拦截器对象
    @Autowired
    private MyInterceptor myInterceptor ;

    /**
     * 注册拦截器，并设置拦截路径和放行路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册我们编写的拦截器
        registry.addInterceptor(myInterceptor)
                //设置拦截的路径
                .addPathPatterns("/**")
                //设置不拦截的路径
                .excludePathPatterns("/*.html");

    }
}
```



# 4 SpringBoot整合MVC★★★

## 4.1环境搭建

### 4.1.1数据库准备

注意：IDEA自带的数据库连接MySql的驱动如果是8.0.22及之前的，连接需要指定时区⚠️

```yaml
jdbc:mysql://localhost:3306??serverTimezone=Asia/Shanghai
```



### 4.1.2创建项目

创建普通的Maven项目 springboot_case



### 4.1.3导入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu</groupId>
    <artifactId>springboot_case</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--指定父工程版本-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.6.RELEASE</version>
    </parent>

    <dependencies>
        <!--单元测试启动器-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>

        <!--通用mapper启动器依赖，TK Mapper⚠️⚠️⚠️ -->
        <!--注意这里使用的不是MyBatis，是TK Mapper -->
        <!--使用TK Mapper后查询全部及查询一个等简单的查询不再需要写接口 -->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>2.1.5</version>
        </dependency>

        <!--JDBC启动器依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>

        <!--mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.23</version>
        </dependency>

        <!--druid启动器依赖-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>

        <!--web启动器依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--spring boot actuator监控管理依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <!--编码工具包-->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>

        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <!-- 配置使用redis启动器 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <!--spring boot maven插件 , 可以将项目运行依赖的jar包打到我们的项目中-->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```



### 4.1.4创建启动类★★

创建启动类时，导包一定不要导错，导入TK开头的！⚠️

要使用@MapperScan注解扫描所有的映射接口，包名一定要写正确⚠️

```java
package com.atguigu.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springboot.SpringbootCaseApplication
 */
@SpringBootApplication
//指定要扫描Mapper接口的包名，创建代理对象，该注解一定是加强版的TKMapper注解⚠️
@MapperScan(basePackages = "com.atguigu.springboot.mapper")
//开启事务支持
@EnableTransactionManagement
public class SpringbootCaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootCaseApplication.class, args);
    }
}
```



## 4.2数据访问层

### 4.2.1 编写配置文件

创建application.yml文件，配置启动服务的端口、Mysql信息、Redis信息、MyBatis的别名信息等

```yaml
server:
  port: 10001

spring:
  #配置连接数据库的相关信息
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    #MySql驱动低于8.0.23需要指定时区：?serverTimezone=Asia/Shanghai
    url: jdbc:mysql://39.106.35.112:3306/springboot
    username: root
    password: abc123
    type: com.alibaba.druid.pool.DruidDataSource
  #配置Redis
  redis:
    host: 39.106.35.112
    port: 6379
    password: abc123

mybatis:
  #指定给哪个包下的类起别名
  type-aliases-package: com.atguigu.springboot.pojo
  #配置该属性是为了打印SQL语句
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```



### 4.2.2编写实体类User★★

@Entity注解可标识实体类

@Table(name = "tb_user")可指定当前实体类对应的表的名称，名称若一样就不需要指定

@Id标识当前字段对应数据表的主键，删除或修改根据该属性来判定

@Column(name = "name")，若列名和属性名一致可省略该注解，支持自动驼峰和下划线匹配

实现序列化接口Serializable

```java
package com.atguigu.springboot.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springboot.pojo.User
 */
//标识当前是一个实体类⚠️
@Entity
//指定当前实体类对应的表的名称，名称若一样就不需要指定⚠️
@Table(name = "tb_user")
public class User implements Serializable {
    //标识当前字段对应数据表的主键，删除或修改根据该属性来判定⚠️
    @Id
    private Integer id;
    //@Column(name = "name")，若列名和属性名一致可省略该注解，支持自动驼峰和下划线匹配⚠️
    private String name;
    private String gender;
    private Integer age;
    private String address;
    private String qq;
    private String email;
    private String username;
    private String phone;

    public User() {
    }

    public User(Integer id, String name, String gender, Integer age, String address, String qq, String email, String username, String phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.qq = qq;
        this.email = email;
        this.username = username;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
```



### 4.2.3编写映射接口★★

继承Mapper<T>接口

继承增强版的TKMapper，基础查询不需要再自己写了，都已经使用动态SQL实现了，继承后直接使用即可

```java
package com.atguigu.springboot.mapper;

import com.atguigu.springboot.pojo.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Description: 继承增强版的TKMapper，基础查询不需要再自己写了⚠️⚠️⚠️
 * @AllClassName: com.atguigu.springboot.mapper.UserMapper
 */
public interface UserMapper extends Mapper<User> {
}
```



### 4.2.4编写测试代码

导入的@Test注解是Juint5的注解的包是import org.junit.jupiter.api.Test，千万别导错了⚠️

@SpringBootTest：指定这是一个Spring Boot的测试类, 运行时会自动加载Spring Boot运行环境

@RunWith(SpringRunner.class)：指定Junit核心运行类，使用Juint5时可省略

```java
package com.atguigu.springboot.test;

import com.atguigu.springboot.mapper.UserMapper;
import com.atguigu.springboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springboot.test.UserMapperTest
 */

//当导入的junit的jar包是4的时候需要该注解，指定Junit核心运行类
//@RunWith(SpringRunner.class)
//指定这是一个Spring Boot的测试类, 运行时会自动加载Spring Boot运行环境
@SpringBootTest
public class UserMapperTest {


    //会有红色虚线，因为没检测到该对象，不过运行时会创建代理对象
    //可以在Mapper上加@repository注解取消报红，但是没必要
    @Autowired
    private UserMapper userMapper;


    /**
     * 创建查询所有测试类，导入的@Test注解是Juint5的注解，千万别导错了
     * 注意导入的@Test注解包名为：import org.junit.jupiter.api.Test;⚠️️
     */
    @Test
    public void testSelectAll(){
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    /**
     * 测试添加
     */
    @Test
    public void testInsert(){
        User user = new User(null,"小黑","男",18,"宏福苑","512354665","5123@emal.com","xiaobai","13062065627");
        //插入数据
        userMapper.insert(user);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate(){
        //根据主键id获取一个user
        User user = userMapper.selectByPrimaryKey(11);
        //修改属性
        user.setName("小白");
        //提交修改
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete(){
        //删除并接收影响的行数，也可以不接收，这里是物理删除
        int line = userMapper.deleteByPrimaryKey(11);
        System.out.println("line = " + line);
    }

}
```



## 4.3业务层

### 4.3.1编写接口

```java
package com.atguigu.springboot.service;

import com.atguigu.springboot.pojo.User;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springboot.service.UserService
 */
public interface UserService {
    /**
     * 查询所有实例信息
     */
    List<User> findAll();

    /**
     * 根据id获取实例
     */
    User getUserById(Integer id);

    /**
     * 添加实例
     */
    void addUser(User user);

    /**
     * 更新实例
     */
    void updateUser(User user);

    /**
     * 删除实例
     */
    void deleteUser(Integer id);
}
```



### 4.3.2编写实现类

```java
package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.mapper.UserMapper;
import com.atguigu.springboot.pojo.User;
import com.atguigu.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springboot.service.impl.UserServiceImpl
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
```



## 4.4表现层

### 4.4.1新建工具类

用来包装异步请求的响应结果为统一样式

```java
package com.atguigu.springboot.utils;

import java.io.Serializable;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springboot.utils.Result
 */
public class Result implements Serializable {
    private boolean status ; //响应状态  true  false
    private String msg ;  // 响应信息
    private Object data ;  //处理成功的响应数据

    public static Result ok(Object data){
        Result result = new Result();
        result.setStatus(true);
        result.setMsg("ok");
        result.setData(data);
        return  result ;
    }

    public static Result error(String msg){
        Result result = new Result();
        result.setStatus(false);
        result.setMsg("error");
        result.setMsg(msg);
        return  result ;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
```



### 4.4.2编写表现层代码

处理异步请求，向前端响应Json数据

pojo入参和@RequestBody的区别：pojo必须一个个的传参数，@RequestBody支持传递一个Json再自动解析成bean对象⚠️

```java
package com.atguigu.springboot.controller;

import com.atguigu.springboot.pojo.User;
import com.atguigu.springboot.service.UserService;
import com.atguigu.springboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springboot.controller.UserController
 */
//处理异步请求，所以需要该注解，处理和返回的都是Json
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public Result findAll(){
        //调用UserService查询所有的用户
        return Result.ok(userService.findAll());
    }


    @PostMapping("/save")
    //使用pojo入参和@RequestBody的区别：
    //pojo必须一个个的传参数，@RequestBody支持传递一个Json再自动解析成bean对象
    public Result save(@RequestBody  User user){
        userService.addUser(user);
        return Result.ok("create success");
    }
}
```



### 4.4.3使用postman测试

选择测试接口，请求类型为POST、URL为http://localhost:10001/user/save、Body选择发送raw-json，测试新增。（以新增为例）

```java
{
    "name": "小明",
    "gender": "男",
    "age": 18,
    "address": "桃花岛",
    "qq": "212223390222",
    "email": "huangrong222@qq.com",
    "username": "小明",
    "phone": "15600003333"
}
```



## 4.5页面展示

### 4.5.1添加静态资源

在resources目录下创建static目录 , 将提供的页面复制进来 , 修改即可 :

- 页面异步请求的端口和服务器端口一致
- 页面异步请求访问的路径和对应的表现层控制方法路径要致
- 页面异步请求参数名称和和对应的表现层控制方法参数一致



## 4.6缓存优化★★★

### 4.6.1优化策略

对于不经常变化的数据，首次从数据库查询之后放到Redis中进行存储，再继续查询时则去Redis数据库直接获取，从缓存中读取，减少IO操作，提高效率。



### 4.6.2添加依赖

项目创建时就已添加，不需要再添加

```xml
<!--springboot整合redis启动器-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```



### 4.6.3编写配置文件

application.yml文件中Redis数据库信息已配置完毕，不需要再配置

```yaml
spring:
  redis: # 配置redis
    host: 39.106.35.112
    port: 6379
    password: abc123
```



### 4.6.4修改业务层实现类代码★★★

修改UserServiceImpl获取全部用户数据的方法

```java
//加满足条件则自动配置类，Spring自动生成的对象
@Autowired
private RedisTemplate redisTemplate;

@Override
public List<User> findAll() {
    //从缓存中查询数据  规定存储用户信息使用string类型进行存储, 存储的key就是userList
    List<User> userList = (List<User>) redisTemplate.boundValueOps("userList").get();
    //如果缓存中没有数据, 查询数据库 , 将查询到的数据放入缓存
    if (userList==null){
        //如果是第一次访问就查询数据库
        userList = userMapper.selectAll();
        //将第一次查询到的用户放到Redis中
        redisTemplate.boundValueOps("userList").set(userList);
        System.out.println("查询的是数据库");
    }else {
        System.out.println("查询的是缓存");
    }
    return userMapper.selectAll();
}
```



# 5 SpringBoot其他组件

## 5.1SpringBoot Actuator

**Spring Boot Actuator**是SpringBoot自带的一个健康 监控组件 , 可以帮助我们监控和管理Spring Boot应用，比如健康检查、审计、统计和HTTP追踪等。

### 5.1.1引入依赖

引入SpringBoot Actuator起步依赖

```xml
<!--引入SpringBoot Actuator起步依赖- ->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```



### 5.1.2配置参数

application.yml文件配置参数

```yaml
management:
  endpoints:
    web:
      exposure:
        # 对外暴露的访问入口, 默认是/health和/info这两个情况，*是暴漏所有情况
        include: '*'
        # 如果不设置，则默认是/actuator路径
      base-path: /monitor
  endpoint:
    health:
      # 显示所有健康状态
      show-details: ALWAYS
  server:
    port: 9999
```

项目启动之后就可以通过发送http请求获取系统健康数据了 , 例如 : http://localhost:9999/monitor/health , 返回数据如下 :

```js
{
    "status": "UP",
    "details": {
        "db": {
            "status": "UP",
            "details": {
                "database": "MySQL",
                "hello": 1
            }
        },
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 355816562688,
                "free": 129251151872,
                "threshold": 10485760
            }
        },
        "redis": {
            "status": "UP",
            "details": {
                "version": "2.8.9"
            }
        }
    }
}
```



### 5.1.3常用访问路径

我们可以发送以下请求路径， 获取系统状态信息

| HTTP 方法 | 路径            | 描述                                                         |
| --------- | --------------- | ------------------------------------------------------------ |
| GET       | /autoconfig     | 提供了一份自动配置报告，记录哪些自动配置条件通过了，哪些没通过 |
| GET       | /configprops    | 描述配置属性(包含默认值)如何注入Bean                         |
| GET       | /beans          | 描述应用程序上下文里全部的Bean，以及它们的关系               |
| GET       | /dump           | 获取线程活动的快照                                           |
| GET       | /env            | 获取全部环境属性                                             |
| GET       | /env/{name}     | 根据名称获取特定的环境属性值                                 |
| GET       | /health         | 报告应用程序的健康指标，这些值由HealthIndicator的实现类提供  |
| GET       | /info           | 获取应用程序的定制信息，这些信息由info打头的属性提供         |
| GET       | /mappings       | 描述全部的URI路径，以及它们和控制器(包含Actuator端点)的映射关系 |
| GET       | /metrics        | 报告各种应用程序度量信息，比如内存用量和HTTP请求计数         |
| GET       | /metrics/{name} | 报告指定名称的应用程序度量值                                 |
| POST      | /shutdown       | 关闭应用程序，要求endpoints.shutdown.enabled设置为true       |
| GET       | /trace          | 提供基本的HTTP请求跟踪信息(时间戳、HTTP头等)                 |



## 5.2SpringBoot Admin

相当于SpringBoot Actuator的可视化升级版⚠️

### 5.2.1创建服务端项目

创建项目springboot_admin_server



### 5.2.2服务端添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>springboot_admin_server</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--选择父工程的版本-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.6.RELEASE</version>
    </parent>

    <dependencies>
        <!--启动器依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--SpringBoot Admin服务端依赖-->
        <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-server</artifactId>
            <version>2.2.0</version>
        </dependency>
    </dependencies>
</project>
```



### 5.2.3服务端配置文件

创建application.yml文件

```yaml
spring:
  #为当前应用起名
  application:
    name: admin-server
server:
  #访问管理服务端的端口号
  port: 8769
```



### 5.2.4服务端编写启动类

```java
package com.atguigu.springboot.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springboot.admin.SpringbootAdminServiceApplication
 */
@SpringBootApplication
//开启管理服务
@EnableAdminServer
public class SpringbootAdminServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootAdminServiceApplication.class, args);
    }
}
```



### 5.2.5启动服务端

启动服务端，访问http://localhost:8769



### 5.2.6客户端添加依赖

```xml
<!--SpringBoot Admin客户端依赖-->
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.2.0</version>
</dependency>
```



### 5.2.7客户端添加配置

application.yml文件添加内容

```yaml
spring:
  #为当前应用起名
  application:
    name: admin-client
  boot:
    admin:
      client:
        # 指定注册地址 , Spring Boot Admin Server地址
        url: http://localhost:8769   
```



### 5.2.8启动客户端

启动客户端，再次访问服务端，即可查看到客户端详细信息http://localhost:8769





# 6 Spring Boot项目打包部署★

## 6.1项目打包

Spring Boot配置插件后，打包后会有两个jar包，一个不包含依赖的普通包，一个含有依赖的jar包

### 6.1.1添加打包插件★★★

```xml
<build>
    <plugins>
        <!-- 打jar包时如果不配置该插件，打出来的jar包没有清单文件 -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```



### 6.1.2执行打包操作

1. 运行maven的打包命令 : package

2. 打包之前我们需要跳过测试 , 如果不跳过测试那么我们编写的测试类都会被maven自动执行, 可能会出现错误,导致打包不成功

3. 执行之后可以在控制台看到打包的日志信息, 其中有生成的包的位置

4. 插件打完包之后，将原来的普通包改名为xxx.jar.original , 新打的拥有全部依赖的包为xxx.jar

5. 简单总结一下 :

   - .jar.original 是普通jar包，不包含依赖

   - .jar 是可执行jar包，包含了pom中的所有依赖，可以直接用java -jar 命令执行

   - 如果是部署，就用.jar , 如果是给别的项目用，就要给.jar.original这个包



## 6.2项目运行

### 6.2.1运行jar包★★★

打开命令行运行打出来的包；使用命令：java –jar 包全名

```shell
java -jar springboot_02-1.0-SNAPSHOT.jar
```





# 7 RedisTemplate常用方法总结★★★

## 7.1String类型

判断是否有key所对应的值，有则返回true，没有则返回false

```java
redisTemplate.hasKey(key)
```

有则取出key值所对应的值

```java
redisTemplate.opsForValue().get(key)
```

删除单个key值

```java
redisTemplate.delete(key)
```

批量删除key

```java
redisTemplate.delete(keys) //其中keys:Collection<K> keys
```

将当前传入的key值序列化为byte[]类型

```java
redisTemplate.dump(key)
```

设置过期时间

```java
public Boolean expire(String key, long timeout, TimeUnit unit) {
    return redisTemplate.expire(key, timeout, unit);
}
public Boolean expireAt(String key, Date date) {
   return redisTemplate.expireAt(key, date);
}
```

查找匹配的key值，返回一个Set集合类型

```java
public Set<String> getPatternKey(String pattern) {
    return redisTemplate.keys(pattern);
}
```

修改redis中key的名称

```java
 public void renameKey(String oldKey, String newKey) {
    redisTemplate.rename(oldKey, newKey);
}
```

返回传入key所存储的值的类型

```java
public DataType getKeyType(String key) {
    return redisTemplate.type(key);
}
```

如果旧值存在时，将旧值改为新值

```java
public Boolean renameOldKeyIfAbsent(String oldKey, String newKey) {
    return redisTemplate.renameIfAbsent(oldKey, newKey);
}
```

从redis中随机取出一个key

```java
redisTemplate.randomKey()
```

返回当前key所对应的剩余过期时间

```java
 public Long getExpire(String key) {
    return redisTemplate.getExpire(key);
}
```

返回剩余过期时间并且指定时间单位

```java
public Long getExpire(String key, TimeUnit unit) {
    return redisTemplate.getExpire(key, unit);
}
```

将key持久化保存

```java
public Boolean persistKey(String key) {
    return redisTemplate.persist(key);
}
```

将当前数据库的key移动到指定redis中数据库当中

```java
public Boolean moveToDbIndex(String key, int dbIndex) {
    return redisTemplate.move(key, dbIndex);
}
```

设置当前的key以及value值

```java
redisTemplate.opsForValue().set(key, value)
```

设置当前的key以及value值并且设置过期时间

```java
redisTemplate.opsForValue().set(key, value, timeout, unit)
```

返回key中字符串的子字符

```java
public String getCharacterRange(String key, long start, long end) {
    return redisTemplate.opsForValue().get(key, start, end);
}
```

将旧的key设置为value，并且返回旧的key

```java
public String setKeyAsValue(String key, String value) {
    return redisTemplate.opsForValue().getAndSet(key, value);
}
```

批量获取值

```java
 public List<String> multiGet(Collection<String> keys) {
    return redisTemplate.opsForValue().multiGet(keys);
 }
```

在原有的值基础上新增字符串到末尾

```java
redisTemplate.opsForValue().append(key, value)
```

以增量的方式将double值存储在变量中

```java
 public Double incrByDouble(String key, double increment) {
    return redisTemplate.opsForValue().increment(key, increment);
 }
```

通过increment(K key, long delta)方法以增量方式存储[long值](https://www.zhihu.com/search?q=long值&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"article"%2C"sourceId"%3A"383876242"})（正值则自增，负值则自减）

```java
public Long incrBy(String key, long increment) {
    return redisTemplate.opsForValue().increment(key, increment);
}
```

如果对应的map集合名称不存在，则添加否则不做修改

```java
Map valueMap = new HashMap();  
valueMap.put("valueMap","map");  
valueMap.put("valueMap2","map2");  
valueMap.put("valueMap3","map3");  
redisTemplate.opsForValue().multiSetIfAbsent(valueMap); 
```

设置map集合到redis

```java
Map valueMap = new HashMap();  
valueMap.put("valueMap","map");  
valueMap.put("valueMap2","map2");  
valueMap.put("valueMap3","map3");  
redisTemplate.opsForValue().multiSet(valueMap);  

```

获取字符串的长度

```java
redisTemplate.opsForValue().size(key)
```

用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始

```java
redisTemplate.opsForValue().set(key, value, offset)
```

重新设置key对应的值，如果存在返回false，否则返回true

```java
redisTemplate.opsForValue().setIfAbsent(key, value)
```

将值 value 关联到 key,并将 key 的过期时间设为 timeout

```java
redisTemplate.opsForValue().set(key, value, timeout, unit)
```

将二进制第offset位值变为value

```java
redisTemplate.opsForValue().setBit(key, offset, value)
```

对key所储存的字符串值，获取指定偏移量上的位(bit)

```java
redisTemplate.opsForValue().getBit(key, offset)
```



## 7.2Hash类型

Redis hash 是一个string类型的field和value的映射表，hash特别适合用于存储对象。 Redis 中每个 hash 可以存储 2^32 -  键值对（40多亿）。

获取变量中的指定map键是否有值,如果存在该map键则获取值，没有则返回null。

```java
redisTemplate.opsForHash().get(key, field)
```

获取变量中的键值对

```java
public Map<Object, Object> hGetAll(String key) {
    return redisTemplate.opsForHash().entries(key);
}
```

新增hashMap值

```java
redisTemplate.opsForHash().put(key, hashKey, value)
```

以map集合的形式添加键值对

```java
public void hPutAll(String key, Map<String, String> maps) {
    redisTemplate.opsForHash().putAll(key, maps);
}
```

仅当hashKey不存在时才设置

```java
public Boolean hashPutIfAbsent(String key, String hashKey, String value) {
    return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
}
```

删除一个或者多个[hash表](https://www.zhihu.com/search?q=hash表&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"article"%2C"sourceId"%3A"383876242"})字段

```java
public Long hashDelete(String key, Object... fields) {
    return redisTemplate.opsForHash().delete(key, fields);
}
```

查看hash表中指定字段是否存在

```java
public boolean hashExists(String key, String field) {
    return redisTemplate.opsForHash().hasKey(key, field);
}
```

给哈希表key中的指定字段的整数值加上增量increment

```java
public Long hashIncrBy(String key, Object field, long increment) {
    return redisTemplate.opsForHash().increment(key, field, increment);
}
public Double hIncrByDouble(String key, Object field, double delta) {
    return redisTemplate.opsForHash().increment(key, field, delta);
}
```

获取所有hash表中字段

```java
redisTemplate.opsForHash().keys(key)
```

获取hash表中字段的数量

```java
redisTemplate.opsForHash().size(key)
```

获取hash表中存在的所有的值

```java
public List<Object> hValues(String key) {
    return redisTemplate.opsForHash().values(key);
}
```

匹配获取键值对，ScanOptions.NONE为获取全部键对

```java
public Cursor<Entry<Object, Object>> hashScan(String key, ScanOptions options) {
    return redisTemplate.opsForHash().scan(key, options);
}
```



## 7.3List类型

通过索引获取列表中的元素

```java
redisTemplate.opsForList().index(key, index)
```

获取列表指定范围内的元素(start开始位置, 0是开始位置，end 结束位置, -返回所有)

```java
redisTemplate.opsForList().range(key, start, end)
```

存储在list的头部，即添加一个就把它放在最前面的索引处

```java
redisTemplate.opsForList().leftPush(key, value)
```

把多个值存入List中(value可以是多个值，也可以是一个Collection value)

```java
redisTemplate.opsForList().leftPushAll(key, value)
```

List存在的时候再加入

```java
redisTemplate.opsForList().leftPushIfPresent(key, value)
```

如果pivot处值存在则在pivot前面添加

```java
redisTemplate.opsForList().leftPush(key, pivot, value)
```

按照先进先出的顺序来添加(value可以是多个值，或者是Collection var2)

```java
redisTemplate.opsForList().rightPush(key, value)
redisTemplate.opsForList().rightPushAll(key, value)
```

在pivot元素的右边添加值

```java
redisTemplate.opsForList().rightPush(key, pivot, value)
```

设置指定索引处元素的值

```java
redisTemplate.opsForList().set(key, index, value)
```

移除并获取列表中第一个元素(如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止)

```java
redisTemplate.opsForList().leftPop(key)

redisTemplate.opsForList().leftPop(key, timeout, unit)
```

移除并获取列表最后一个元素

```java
redisTemplate.opsForList().rightPop(key)
redisTemplate.opsForList().rightPop(key, timeout, unit)
```

从一个队列的右边弹出一个元素并将这个元素放入另一个指定队列的最左边

```java
redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey)
redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit)
```

删除集合中值等于value的元素(index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素; index<0, 从尾部开始删除第一个值等于value的元素)

```java
redisTemplate.opsForList().remove(key, index, value)
```

将List列表进行剪裁

```java
redisTemplate.opsForList().trim(key, start, end)
```

获取当前key的List列表长度

```java
redisTemplate.opsForList().size(key)
```



## 7.4Set类型

添加元素

```java
redisTemplate.opsForSet().add(key, values)
```

移除元素(单个值、多个值)

```java
redisTemplate.opsForSet().remove(key, values)
```

删除并且返回一个随机的元素

```java
redisTemplate.opsForSet().pop(key)
```

获取集合的大小

```java
redisTemplate.opsForSet().size(key)
```

判断集合是否包含value

```java
redisTemplate.opsForSet().isMember(key, value)
```

获取两个集合的交集(key对应的无序集合与otherKey对应的无序集合求交集)

```java
redisTemplate.opsForSet().intersect(key, otherKey)
```

获取多个集合的交集(Collection var2)

```java
redisTemplate.opsForSet().intersect(key, otherKeys)
```

key集合与otherKey集合的交集存储到destKey集合中(其中otherKey可以为单个值或者集合)

```java
redisTemplate.opsForSet().intersectAndStore(key, otherKey, destKey)
```

key集合与多个集合的交集存储到destKey无序集合中

```java
redisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey)
```

获取两个或者多个集合的并集(otherKeys可以为单个值或者是集合)

```java
redisTemplate.opsForSet().union(key, otherKeys)
```

key集合与otherKey集合的并集存储到destKey中(otherKeys可以为单个值或者是集合)

```java
redisTemplate.opsForSet().unionAndStore(key, otherKey, destKey)
```

获取两个或者多个集合的差集(otherKeys可以为单个值或者是集合)

```java
redisTemplate.opsForSet().difference(key, otherKeys)
```

差集存储到destKey中(otherKeys可以为单个值或者集合)

```java
redisTemplate.opsForSet().differenceAndStore(key, otherKey, destKey)
```

随机获取集合中的一个元素

```java
redisTemplate.opsForSet().randomMember(key)
```

获取集合中的所有元素

```java
redisTemplate.opsForSet().members(key)
```

随机获取集合中count个元素

```java
redisTemplate.opsForSet().randomMembers(key, count)
```

获取多个key无序集合中的元素（去重），count表示个数

```java
redisTemplate.opsForSet().distinctRandomMembers(key, count)
```

遍历set，类似于Interator(ScanOptions.NONE为显示所有的)

```java
redisTemplate.opsForSet().scan(key, options)
```



## 7.5zSet类型

ZSetOperations提供了一系列方法对有序集合进行操作 添加元素(有序集合是按照元素的score值由小到大进行排列)

```java
redisTemplate.opsForZSet().add(key, value, score)
```

删除对应的value,value可以为多个值

```java
redisTemplate.opsForZSet().remove(key, values)
```

增加元素的score值，并返回增加后的值

```java
redisTemplate.opsForZSet().incrementScore(key, value, delta)
```

返回元素在集合的排名,有序集合是按照元素的score值由小到大排列

```java
redisTemplate.opsForZSet().rank(key, value)
```

返回元素在集合的排名,按元素的score值由大到小排列

```java
redisTemplate.opsForZSet().reverseRank(key, value)
```

获取集合中给定区间的元素(start 开始位置，end 结束位置, -查询所有)

```java
redisTemplate.opsForZSet().reverseRangeWithScores(key, start,end)
```

按照Score值[查询](https://www.zhihu.com/search?q=查询&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra={"sourceType"%3A"article"%2C"sourceId"%3A"383876242"})集合中的元素，结果从小到大排序

```java
redisTemplate.opsForZSet().reverseRangeByScore(key, min, max)
redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max)
```

> 返回值为:`Set<ZSetOperations.TypedTuple<V>>`

从高到低的排序集中获取分数在最小和最大值之间的元素

```java
redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, start, end)
```

根据score值获取集合元素数量

```java
redisTemplate.opsForZSet().count(key, min, max)
```

获取集合的大小

```java
redisTemplate.opsForZSet().size(key)
redisTemplate.opsForZSet().zCard(key)
```

获取集合中key、value元素对应的score值

```java
redisTemplate.opsForZSet().score(key, value)
```

移除指定索引位置处的成员

```java
redisTemplate.opsForZSet().removeRange(key, start, end)
```

移除指定score范围的集合成员

```java
redisTemplate.opsForZSet().removeRangeByScore(key, min, max)
```

获取key和otherKey的并集并存储在destKey中（其中otherKeys可以为单个字符串或者字符串集合）

```java
redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey)
```

获取key和otherKey的交集并存储在destKey中（其中otherKeys可以为单个字符串或者字符串集合）

```java
redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey)
```

遍历集合，和iterator一模一样

```java
Cursor<TypedTuple<Object>> scan = opsForZSet.scan("test3", ScanOptions.NONE);
while (scan.hasNext()){
    ZSetOperations.TypedTuple<Object> item = scan.next();
    System.out.println(item.getValue() + ":" + item.getScore());
}
```

