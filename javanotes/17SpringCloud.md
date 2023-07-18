# 1 微服务理论

**微服务**简单理解就是将功能模块的多个功能，拆分为多个小功能服务，具体拆分多细的颗粒度，取决于需求项目需要，在服务之间可以通过RPC或其它方式来相互交互。⚠️



**微服务理论论文核心片段：**

In short（简言之）, the microservice architectural style 【架构风格】[1] is an approach to developing a single application as a suite of small services【独立应用变成一套小服务】, each running in its own process and communicating with lightweight(轻量级沟通) mechanisms(每一个都运行在自己的进程内（容器）), often an HTTP resource API(用HTTP，将功能写成能接受请求). These services are built around business capabilities （独立业务能力）and independently deployable by fully automated deployment machinery（应该自动化独立部署）. There is a bare minimum of centralized management of these services（应该有一个能管理这些服务的中心）, which may be written in different programming languages (独立开发语言)and use different data storage technologies（独立的数据存储）

微服务论文：https://www.martinfowler.com/articles/microservices.html 

微服务论文汉译版：http://blog.cuicc.com/blog/2015/07/22/microservices/







# 2 分布式概念

## 2.1什么是分布式★

《分布式系统原理与范型》定义：“分布式系统是若干独立计算机的集合，这些计算机对于用户来说就像单个相关系统”。

分布式系统（distributed system）是建立在网络之上的软件系统。



## 2.2分布式和集群的关系

集群指的是将几台服务器集中在一起，实现同一业务。

**分布式中的每一个节点，都可以做集群。 而集群并不一定就是分布式的。**



## 2.3软件架构演变

ORM单体架构	⇨	MVC垂直架构	⇨	RPC分布式架构	⇨	SOA流动式架构



## 2.4RPC架构

### 2.4.1RPC架构概述★

**RPC**（Remote Procedure Call）是一种进程间通信方式，他是一种技术的思想，而不是规范。RPC是指调用远端机器的函数或方法，RPC可以分为两部分：**用户调用接口** + **具体网络协议**。前者为开发者需要关心的，后者由框架来实现。⚠️



### 2.4.2RPC思想原理

获取：客户端	⇨	客户端代理对象	⇨	客户端Sockets-NewWork	⇨	服务端Sockets	⇨	服务端代理对象	⇨	服务端

返回：服务端	⇨	服务端代理对象	⇨	服务端Sockets-NewWork	⇨	客户端Sockets	⇨	客户端代理对象	⇨	客户端

步骤总结：

1. 客户端调用【Client】
2. 序列化【Client Stub】
3. 发送消息【Client Stub】
4. 反序列化【Server Stub】
5. 调用本地服务【Server Stub】
6. 服务处理【Server Stub】
7. 返回处理结果【Server Stub】
8. 将结果序列化【Server Stub】
9. 返回消息【Client Stub】
10. 反序列化【Client Stub】
11. 返回调用结果【Client】



### 2.4.3服务间交互的方式

1. RPC
   - Netty（Socket）+自定义序列化
2. RestAPI   (严格来说，SpringCloud是使用Rest方式进行服务之间交互的，不属于RPC)
   - HTTP+JSON



## 2.5分布式思想与基本概念

### 2.5.1高并发

（1）**通过设计保证系统可以并行处理很多请求。应对大量流量与请求**

- Tomcat最多支持并发多少用户？

Tomcat 默认配置的最大请求数是 150，也就是说同时支持 150 个并发，当然了，也可以将其改大。

当某个应用拥有 250 个以上并发的时候，应考虑应用服务器的集群。

具体能承载多少并发，需要看硬件的配置，CPU 越多性能越高，分配给 JVM 的内存越多性能也就越高，但也会加重 GC 的负担。

- 操作系统对于进程中的线程数有一定的限制：

Windows 每个进程中的线程数不允许超过 2000

Linux 每个进程中的线程数不允许超过 1000

另外，在 Java 中每开启一个线程需要耗用 1MB 的 JVM 内存空间用于作为线程栈之用。

Tomcat 默认的 HTTP 实现是采用阻塞式的 Socket 通信，每个请求都需要创建一个线程处理。这种模式下的并发量受到线程数的限制，但对于 Tomcat 来说几乎没有 BUG 存在了。

Tomcat 还可以配置 NIO 方式的 Socket 通信，在性能上高于阻塞式的，每个请求也不需要创建一个线程进行处理，并发能力比前者高。但没有阻塞式的成熟。

这个并发能力还与应用的逻辑密切相关，如果逻辑很复杂需要大量的计算，那并发能力势必会下降。如果每个请求都含有很多的数据库操作，那么对于数据库的性能也是非常高的。

对于单台数据库服务器来说，允许客户端的连接数量是有限制的。

并发能力问题涉及整个系统架构和业务逻辑。

系统环境不同，Tomcat版本不同、JDK版本不同、以及修改的设定参数不同。并发量的差异还是满大的。

- maxThreads="1000" 最大并发数 ，默认值为200
- minSpareThreads="100"//初始化时创建的线程数，默认值为10
- acceptCount="700"// 指定当所有可以使用的处理请求的线程数都被使用时，可以放到处理队列中的请求数，超过这个数的请求将不予处理，默认值为100

官方文档：https://tomcat.apache.org/tomcat-8.0-doc/config/http.html

 （2）**高并发衡量指标**

- 响应时间(RT) 

  - 请求做出响应的时间，即一个http请求返回所用的时间

- 吞吐量

  - 系统在单位时间内处理请求的数量

- 每秒查询（请求）数QPS(Query/Request Per Second)、 每秒事务数TPS（Transaction Per Second） 

  - 专业的测试工具：Load Runner

  - Apache ab

  - Apache JMeter

- 并发用户数
  - 承载的正常使用系统功能的用户的数量



### 2.5.2高可用

服务集群部署：

数据库主从+双机热备

- 主-备方式（Active-Standby方式）

主-备方式即指的是一台服务器处于某种业务的激活状态（即Active状态），另一台服务器处于该业务的备用状态（即Standby状态)。

- 双主机方式（Active-Active方式）

双主机方式即指两种不同业务分别在两台服务器上互为主备状态（即Active-Standby和Standby-Active状态）

注意：

当由于网络问题出现两个主机时，为避免出现脑裂现象，通常会通过脚本，当从机认为主机挂掉上位成为主机时，会kill掉主机的进程。



### 2.5.3注册中心

保存某个服务所在地址等信息，方便调用者实时获取其他服务信息

- 服务注册
  - 服务提供者
- 服务发现
  - 服务消费者



### 2.5.4负载均衡

动态将请求派发给比较闲的服务器

**策略：**

- 轮询(Round Robin)
- 加权轮询(Weighted Round Robin)
- 随机Random
- 哈希Hash
- 最小连接数LC
- 最短响应时间LRT



### 2.5.5服务雪崩

服务之间复杂调用，一个服务不可用，导致整个系统受影响不可用

雪崩触发情况：

服务器A正常	⇦	服务器B正常	⇦	服务器C正常	⇦	服务器D异常(不可用，导致整个系统不可用⚠️)



### 2.5.6熔断

某个服务频繁超时，直接将其短路，快速返回mock（模拟/虚拟）值

服务器A正常	⇦	服务器B正常	⇦	服务器C正常	⇦	【熔断器，返回兜底数据User:{...}⚠️】	⇦	服务器D异常⚠️



### 2.5.7限流

限制某个服务每秒的调用本服务的频率

作用：防止爬虫、防止Ddos攻击(洪水攻击)



### 2.5.8API网关

API网关要做很多工作，它作为一个系统的后端总入口，承载着所有服务的组合路由转换等工作，除此之外，我们一般也会把安全，限流，缓存，日志，监控，重试，熔断等放到 API 网关来做



### 2.5.9服务跟踪

追踪服务的调用链，记录整个系统执行请求过程。如：请求响应时间，判断链中的哪些服务属于慢服务（可能存在问题，需要改善）。



### 2.5.10弹性云

Elastic Compute Service（ECS）弹性计算服务

动态扩容，压榨服务器闲时能力

例如：双11,618，高峰时多配置些服务器，平时减少多余的服务器配置（用于其他服务应用），避免资源浪费





# 3 SpringCloud

## 3.1SpringCloud概述

### 3.1.1微服务架构

物联网、网页等客户端	⇨	网关	⇨	断路器仪表盘	⇨	微服务集群	⇨	服务注册	⇨	分布式跟踪	⇨	数据库、队列等

角色解读：

- 物联网：即IoT ，Internet of things 即“万物相连的互联网”，是互联网基础上的延伸和扩展的网络，将各种信息传感设备与互联网结合起来而形成的一个巨大网络，实现在任何时间、任何地点，人、机、物的互联互通。
- 断路器仪表盘：Breaker dashboard 
- 分布式跟踪：Distributed Tracing （分布式处理程序链跟踪用于监视网络等待时间，并可视化通过微服务的请求流）



### 3.1.2微服务架构社区

SpringBoot微服务社区：

- 英文版：https://docs.spring.io/spring-boot/docs/2.3.6.RELEASE/reference/htmlsingle/

SpringCloud微服务社区：

英文版：https://docs.spring.io/spring-cloud/docs/Hoxton.SR9/reference/html/

中文版：https://www.bookstack.cn/read/spring-cloud-docs/docs-index.md



### 3.1.3技术组件

- 服务组册与发现：Eureka
- 服务负载与调用：Netflix Oss Ribbon、Netflix
- 服务熔断升级：Htstrix
- 服务网关：Netflix Zuul
- 服务分布式配置：Spring Cloud Config
- 服务开发：Spring Boot



### 3.1.4版本选择

- cloud
  - Hoxton.SR9
- boot
  - 2.3.6.RELEASE
- cloud Alibaba
  - 2.2.6.RELEASE
-  java
  - JAVA8
- maven
  - 3.5及以上
- mysql
  - 5.7及以上

（1）**SpringBoot：**

SpringBootgit源码地址： 

https://github.com/spring-projects/spring-boot/releases/

SpringBootSpringBoot2.0新特性： 

https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Release-Notes

通过上面官网发现，Boot官方强烈建议升级到**2.X**以上版本。

（2）**SpringCloud：**

SpringCloud采用了英国伦敦地铁站的名称来命名

SpringCloudgit源码地址： https://github.com/spring-projects/spring-cloud/wiki

SpringCloud官网： https://spring.io/projects/spring-cloud



## 3.2工程准备

### 3.2.1Maven版本

Preferences	⇨	Build,Exception,Development	⇨	BuildTools	⇨	Maven



### 3.2.2字符编码

Preferences	⇨	Editer	⇨	FileEncoding



### 3.2.3注解生效激活

Preferences	⇨	Build,Exception,Development	⇨	Compiler	⇨	AnnotationProcessors	⇨	Enable annotation processing



### 3.2.4Java编译版本

ProjectSettings	⇨	Project



### 3.2.5File Type过滤

Preferences	⇨	Editer	⇨	FileTypes	⇨	Ignore files and folfers	⇨	添加 *.idea 和  *.iml (回车确认)



### 3.2.6添加数据库提示

Preferences	⇨	Language&FrameWork	⇨	SQL Dialects	⇨	Global SQL  Dialect (设置全局即可)



### 3.2.7添加小辣椒插件

ProjectSettings	⇨	Plugins	⇨	Lombok

常用注解

```java
//Get方法、Set方法
@Data
//全参构造方法
@AllArgsConstructor
//无参构造方法
@NoArgsConstructor
```



### 3.2.8添加文件模版

Preferences	⇨	Editer	⇨	File And Code Templates	⇨	Ignore files and folfers	⇨	设置模板文件名字和类型以及模板

映射文件模版示例：

Name：mybaties-mapper

Extension：xml

模板例子：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="">


</mapper>
```



## 3.3创建父工程★

创建父工程后，按需执行工程准备操作⚠️

### 3.3.1创建Maven父工程

创建Maven父工程springcloud，并删除src目录，按需准备工程



### 3.3.2父工程依赖

注意，聚合可不用编写，IDEA创建子工程时自动生成

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>springcloud</artifactId>
    <!--打包方式-->
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>cloud_provider_payment8001</module>
    </modules>

    <!-- 统一管理jar包版本 -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
        <mysql.version>8.0.23</mysql.version>
        <druid.version>1.1.16</druid.version>
        <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
    </properties>

    <!-- 子模块继承之后，提供作用：锁定版本+子modlue不用写groupId和version  -->
    <dependencyManagement>
        <dependencies>
            <!--核心依赖spring boot-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.3.6.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--核心依赖spring cloud-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR9</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.2.6.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring.boot.version}</version>
            </dependency>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
            </dependency>
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
                <optional>true</optional>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

父工程创建完成执行mvn:install，去掉test生命周期，查看是否可以正常打包



## 3.4创建微服务的服务提供者★

### 3.4.1创建服务提供者模块

在父工程下创建cloud_provider_payment8001子工程



### 3.4.2添加依赖★

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud_provider_payment8001</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

### 3.4.3编写YML文件

```yaml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://39.106.35.112:3306/cloud2021?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC
    username: root
    password: abc123

mybatis:
  #映射文件路径
  mapperLocations: classpath:/mapper/*.xml
  #给包起别名
  type-aliases-package: com.atguigu.springcloud.entities
```



### 3.4.4创建启动类

```java
package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
```



### 3.4.5准备实体类

#### 3.4.5.1准备数据表

```sql
CREATE DATABASE  IF NOT EXISTS cloud2021 DEFAULT CHARACTER SET utf8 ;

USE cloud2021 ;

DROP TABLE IF EXISTS payment ;

CREATE TABLE payment (
  id BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  SERIAL VARCHAR (300) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE = INNODB AUTO_INCREMENT = 33 DEFAULT CHARSET = utf8 ;

INSERT INTO payment (id, SERIAL) VALUES(31, '尚硅谷001'),(32, 'atguigu002') ;
```



#### 3.4.5.2准备实体类

```java
package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
```



#### 3.4.5.3封装Json工具类

```java
package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult <T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code,String message){
        this(code,message,null);//如果这行报错，请安装lombok插件
    }
}
```



#### 3.4.5.4准备小辣椒插件

常用注解：

@Data：提供getter/setter

@NoArgsConstructor, 无参构造器 @RequiredArgsConstructor  @AllArgsConstructor  全参数构造器

@EqualsAndHashCode：提供equals和hashCode方法

@Getter/@Setter

@Slf4j 内置log对象，直接调用日志方法输出日志

官方网站：https://www.projectlombok.org/



### 3.4.6准备dao层

#### 3.4.6.1准备Dao层接口★★

@Mapper注解，mybatis提供的，等价：@MapperScan("com.atguigu.springcloud.dao")

```java
package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

//@Component和@Repository注解加不加都可以，主要作用是开发时其他地方使用不报红线
//@Component or @Repository

//为了创建代理对象，可以在当前接口上添加Mapper注解或在启动类上添加扫描包注解⚠️
//mybatis提供的，等价：@MapperScan("com.atguigu.springcloud.dao")⚠️
@Mapper
public interface PaymentDao {
    /**
     * 添加
     */
    int create(Payment payment);

    /**
     * 根据id查询
     */
    Payment getPaymentById(@Param("id") Long id);
}
```



#### 3.4.6.2准备映射文件★

在resources下创建mapper目录，因为YAML配置文件中写的是mapper下⚠️

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.atguigu.springcloud.dao.PaymentDao">

    <!--添加-->
    <insert id="create" useGeneratedKeys="true" keyProperty="id">
        insert into payment(serial)
        values (#{serial});
    </insert>

    <!--根据id查询-->
    <select id="getPaymentById" resultType="payment">
        select * from payment where id=#{id}
    </select>

</mapper>
```



### 3.4.7准备Service层

#### 3.4.7.1准备Service接口

```java
package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springcloud.service.PaymentService
 */
public interface PaymentService {
    /**
     * 写入数据
     */
    int create(Payment payment);

    /**
     * 读取数据
     */
    public Payment getPaymentById(Long id);
}
```



#### 3.4.7.2准备Service实现类★

```java
package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springcloud.service.impl.PaymentServiceImpl
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    //使用@Resource注解代替@Autowired注解，可防止报红
    @Resource
    //@Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById( Long id){
        return paymentDao.getPaymentById(id);
    }
}
```



### 3.4.8准备Controller层

#### 3.4.8.1准备Controller实现类★

@Resource注解是JDK提供的自动注入的注解

```java
package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

//@RequestBody和@RestController注解
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    //接收的Json，要使用@RequestBody注解⚠️
    public CommonResult<Payment> create(@RequestBody Payment payment){
       int result = paymentService.create(payment);
       log.info("*****插入结果："+result);
       if (result>0){
           //说明有数据，能查询成功
           return new CommonResult(200,"插入数据库成功",result);
       }else {
           //查询失败
           return new CommonResult(444,"插入数据库失败",null);
       }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果："+payment);
        if (payment!=null){
            //说明有数据，能查询成功
            return new CommonResult(200,"查询成功",payment);
        }else {
            //查询失败
            return new CommonResult(444,"没有对应记录，查询ID："+id,null);
        }
    }
}
```



### 3.4.9测试

**使用postman测试get和post请求：**

（1）get请求：

请求路径：http://localhost:8001/payment/get/1

（2）post请求

请求路径：http://localhost:8001/payment/create

请求数据：

```java
{
    "serial":"尚硅谷002"
}
```



### 3.4.10总结步骤

1. 建module
2. 改POM
3. 写YML
4. 主启动
5. 业务类



### 3.4.11错误类型总结★★★

invalid bound statement：未找到Mapper映射文件

TooManyResultsException：返回的结果太多，返回类型不满足要求（如查询List用Bean接收）



### 3.4.12配置热部署

热部署的依赖

```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <scope>runtime</scope>
      <optional>true</optional>
    </dependency>
```

Preferences	⇨	Build,Exception,Development	⇨	Compiler	⇨	Build project automatically 和 Display notification on bild completion打勾

Preferences	⇨	Advanced Setting	⇨	Complier	⇨	Allow auto-make to start event if developed application... 打勾

配置后，修改代码后隔几秒自动打包部署，就算修改服务端口好也可以不需要重启服务生效，但是比较耗费电脑内存等资源⚠️



## 3.5创建微服务的服务消费者★  

### 3.5.1创建服务消费者模块

在父工程下创建cloud_consumer_order80子工程



### 3.5.2添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud_consumer_order80</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
      </dependencies>
</project>
```



### 3.5.3编写YML文件

application.yml

```yaml
server:
  port: 80
spring:
  application:
    name: cloud-consumer-order80
```



### 3.5.4创建启动类

com.atguigu.springcloud.OrderMain80

```java
package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
```



### 3.5.5准备实体类

将cloud_provider_payment8001工程下的entities包下的两个实体类复制过来

- CommonResult
- Payment



### 3.5.6RestTemplate

#### 3.5.6.1RestTemplate介绍★★★

RestTemplate提供了多种便捷访问远程Http服务的方法，是一种简单便捷的访问Restful服务模板类，是Spring 提供的用于访问Rest服务的客户端模板工具集

官网地址： https://docs.spring.io/spring-framework/docs/5.2.2.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html

使用RestTemplate访问Restful接口非常的简单粗暴无脑。（url，requestMap，ResponseBean.class）这三个参数分别代表REST请求地址、请求参数、Http响应转换被转换成的对象类型。



#### 3.5.6.2RestTemplate配置类★

配置类的作用就是创建一个RestTemplate对象来调用远程方法⚠️

com.atguigu.springcloud.config.ApplicationContextConfig

```java
package com.atguigu.springcloud.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
```



### 3.5.7准备Controller层

#### 3.5.7.1准备Controller实现类★★★

com.atguigu.springcloud.controller.OrderController

```java
package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    //配置远程调用的微服务的地址
    public static final String PAYMENT_URL = "http://localhost:8001";

    //注入远程调用对象
    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create")
public CommonResult<Payment>   create(@RequestBody Payment payment){
        /**
         * 使用RestTemplate 远程调用支付模块接口
         * 第一个参数：请求地址
         * 第二个参数：响应类型
         */
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);  //写操作
    }

    @GetMapping("/consumer/payment/get/{id}")
public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        /**
         * 使用RestTemplate 远程调用支付模块接口
         * 第一个参数：请求地址
         * 第二个参数：响应类型
         */
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}
```



### 3.5.8测试

1. 先启动cloud-provider-payment8001
2. 再启动cloud-consumer-order80
3. http://localhost/consumer/payment/get/1

注意：不要忘记@RequestBody注解，服务提供者接口方法需要增加@RequestBody注解(踩雷or破雷)；否则，接收不到数据。⚠️



## 3.6工程重构

目的：将服务生产者和服务消费者共有的entities包下的实体类统一管理



### 3.6.1创建实体类API模块

在父工程下创建cloud_api_commons子工程



### 3.6.2添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud_api_commons</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!--工具包，暂时用不着-->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.1.0</version>
        </dependency>
    </dependencies>

</project>
```



### 3.6.3添加实体类

将服务提供者和服务消费者工程下的entities包下实体类先复制过来再删除掉。

- CommonResult
- Payment



### 3.6.4修改提供者和消费者

对项目打包，然后在服务提供者和服务消费者工程中添加依赖

```xml
<!--抽取实体类-->
<dependency>
  <groupId>org.example</groupId>
  <artifactId>cloud_api_commons</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```





# 4 Eureka服务注册与发现

## 4.1Eureka概述

### 4.1.1Eureka介绍

SpringCloud封装了Netflix公司开发的Eureka模块来实现服务治理。后期被阿里Nacos取代。

在传统的RPC远程调用框架中，管理每个服务与服务之间依赖关系比较复杂、所以需要进行服务治理，管理服务与服务之间依赖关联，以实现服务调用，负载均衡、容错等，实现服务发现与注册。



### 4.1.2服务注册概述★

Eureka采用了CS的设计架构，Eureka Server作为服务注册功能的服务器，它是服务注册中心。

而系统中的其他微服务，使用Eureka的客户端连接到Eureka Server并维持**心跳**连接。这样系统的维护人员可以通过Eureka Server来监控系统中各个微服务是否正常运行。周期默认30s，如果多个周期(默认90s)没有收到某个节点的心跳，Eureka Server就会将服务列表中把该结点移出⚠️⚠️⚠️

在服务注册与发现中，有一个注册中心。当服务器启动的时候，会把当前自己服务器的信息，比如：服务通讯地址等以别名方式注册到注册中心上。

另一方（消费者服务），以该别名的方式去注册中心上获取到实际的服务通讯地址，然后，再实现本地RPC远程调用。

**RPC远程调用框架核心设计思想：**在于注册中心，因为使用注册中心管理每个服务与服务之间的一个依赖关系（服务治理概念）。

在任何RPC远程框架中，都会有一个注册中心（存放服务地址相关信息（接口地址））。

连接模式：

提供者服务	⇨	Eureka服务	⇦	消费者服务	⇨	提供者服务



### 4.1.3Eureka客户端和服务端★

（1）**Eureka Server提供服务注册服务**

各个微服务节点通过配置启动后，会在Eureka Server中进行注册，这样Eureka Server中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到。

（2）**Eureka Client通过注册中心进行访问**

是一个Java客户端，用于简化Eureka Server的交互，客户端同时也具备一个内置的、使用轮询（round-robin）负载算法的负载均衡器。在应用启动后，将会在Eureka Server发送心跳（默认周期30秒）。如果Eureka Server在多个心跳周期内没有收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移出（默认90秒）



### 4.2Eureka Server服务端

### 4.2.1创建服务端注册中心★★★

在父工程下创建cloud_eureka_server7001子工程



### 4.2.2添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud_eureka_server7001</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>

        <!--抽取实体类-->
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>cloud_api_commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>

        <!--注册中心客户端注解-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
    </dependencies>

</project>
```



### 4.2.3编写YML文件

```yaml
server:
  port: 7001

eureka:
  instance:
    hostname: localhost

  client:
    #表示不向注册中心注册自己
    register-with-eureka: false
    #表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    fetchRegistry: false
    service-url:
      defaultZone: http://localhost:7001/eureka
```



### 4.2.4创建启动类

注册中心服务端注解：@EnableEurekaServer

com.atguigu.springcloud.EurekaMain7001

```java
package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//注册中心服务端注解
@EnableEurekaServer
@SpringBootApplication
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class,args);
    }
}
```



### 4.2.5注册中心页面

注册中心：http://localhost:7001/



## 4.3Eureka Server客户端

EurekaClient端cloud_provider_payment8001将注册进EurekaServer成为服务提供者provider

### 4.3.1提供者客户端

#### 4.3.1.1加入Eureka依赖

```xml
<!--Eureka注册中心客户端依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```



#### 4.3.1.2添加YML配置

```yaml
eureka:
  client:
    #表示将自己注册到注册中心
    register-with-eureka: true
    #表示去服务中心检索服务
    fetchRegistry: true
    service-url:
      defaultZone: http://localhost:7001/eureka
```



#### 4.3.1.3修改启动类

添加注册中心客户端注解@EnableEurekaClient



### 4.3.2消费者客户端

EurekaClient端cloud_consumer_order80将注册进EurekaServer成为服务消费者consumer

#### 4.3.2.1加入Eureka依赖

```xml
<!--Eureka注册中心客户端依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```



#### 4.3.2.2添加YML配置

```yaml
eureka:
  client:
    #表示将自己注册到注册中心
    register-with-eureka: true
    #表示去服务中心检索服务
    fetchRegistry: true
    service-url:
      defaultZone: http://localhost:7001/eureka
```



#### 4.3.2.3修改启动类

添加注册中心客户端注解@EnableEurekaClient



## 4.4测试

1. 先要启动EurekaServer，7001服务
2. 再要启动服务提供者8001服务和服务消费者80服务
3. 可以查看Eureka服务注册中心页面查看http://localhost:7001/
4. 测试查询：http://localhost/consumer/payment/get/31
5. 测试添加：postman测试添加
6. 测试8001服务和80服务效果一样





# 5 Ribbon负载均衡服务调用

## 5.1Ribbon概述

Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具。

简单的说，Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法和服务调用。

Ribbon客户端组件提供一系列完善的配置项，如：连接超时，重试等。

简单的说，就是在配置文件中列出Load Balancer(简称LB)后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮询，随机连接等）去连接这些机器。

官网：https://github.com/Netflix/ribbon

Ribbon目前也进入维护模式，未来终将被Spring Cloud LoadBalancer替代⚠️



## 5.2LB负载均衡★

集中式LB以软件Nginx，LVS，硬件F5等是在服务端的负载均衡，进程内LB如Ribbon和Dubbo是在本地的负载均衡⚠️

**Ribbon=负载均衡+RestTemplate调用**，所以他是基于HTTP协议，而不是RPC⚠️⚠️⚠️

（1）简单的说就是将用户的请求平均分配到多个服务器上，从而达到系统的HA(高可用)。

（2）常见的负载均衡有软件Nginx，LVS，硬件F5等。

（3）Ribbon的本地负载均衡客户端  VS Nginx服务端负载均衡区别：

- Nginx是服务器负载均衡，客户端所有请求都会交给Nginx，然后，由nginx实现转发请求。即负载均衡是由服务器端完成的。

- Ribbon本地负载均衡，在调用微服务接口时候，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从而在本地实现RPC远程服务调用。

（4）集中式LB

即在服务的消费方和提供方之间使用独立的LB设施（可以是硬件，如F5，也可以是软件，如Nginx）,由该设施负责把访问请求通过某种策略转发至服务的提供方；

（5）进程内LB

- 将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。
- Ribbon就属于进程内LB，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址。



## 5.3Ribbon作用

Ribbon在工作时分成两步：

第一步，先选择EurekaServer，它优先选择在同一个区域内负载较少的server。

第二步，再根据用户指定的策略，在从server取到的服务注册列表中选择一个地址。其中Ribbon提供了多种策略。比如：轮询、随机和根据响应时间加权。（默认轮询策略⚠️）

总结：**Ribbon其实就是一个软负载均衡的客户端组件，他可以和其他所需请求的客户端结合使用，和eureka结合只是其中的一个实例**。



## 5.4Ribbon负载均衡演示

在IDEA的Service工具栏再复制两个PaymentMain8001服务，分别为PaymentMain8002、PaymentMain8003

分别修改二者的服务端口，VM Options：-Dserver.port=8002



### 5.4.1修改服务提供者

**注意：该依赖不需要手动引用，Eureka客户端自带Ribbon**⚠️

```xml
<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```

修改服务提供者cloud_provider_payment8001模块的controller，打印当前端口号

```java
@Value("${server.port}")
private String serverPort;
```

```java
@GetMapping(value = "/payment/get/{id}")
public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
  Payment payment = paymentService.getPaymentById(id);
  log.info("*****查询结果："+payment);
  if (payment!=null){
    //说明有数据，能查询成功，打印端口号⚠️
    return new CommonResult(200,"查询成功"+serverPort,payment);
  }else {
    //查询失败
    return new CommonResult(444,"没有对应记录，查询ID："+id,null);
  }
}
```



### 5.4.2修改服务消费者

**注意：该依赖不需要手动引用，Eureka客户端自带Ribbon**⚠️

```xml
<dependency>
<groupId>org.springframework.cloud</groupId>
<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```

修改服务消费者cloud_consumer_order80模块的controller，配置远程调用的微服务的地址为注册中心服务名称

微服务名称不能用下划线⚠️⚠️⚠️

```java
//配置远程调用的微服务的地址
//public static final String PAYMENT_URL = "http://localhost:8001";
//配置远程调用的微服务的地址，要写服务的名字CLOUD_PAYMENT_SERVICE
//名字必须从Eureka注册中心进行http://localhost:7001/查看⚠️
public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
```

修改服务消费者cloud_consumer_order80模块的RestTemplate配置类

```java
//使用Ribbon在客户端实现负载均衡时必须添加该注解
@LoadBalanced
@Bean
public RestTemplate getRestTemplate(){
  return new RestTemplate();
}
```



### 5.4.3测试

访问接口并多次刷新：http://localhost:90/consumer/payment/get/31

主要观察返回数据的端口号，默认是轮询模式，看是否会按顺序切换：

```js
{
  "code": 200,
  "message": "查询成功8003",
  "data": {
  "id": 31,
  "serial": "尚硅谷001"
}
```



## 5.5调用流程

服务消费者Ribbon	⇨	RestTemplate查询可用服务列表	⇨	Eureka Service	⇨	根据负载均衡策略选择提供者	⇨	多个服务提供者实例⚠️	



## 5.6IRule设置访问策略

Ribbon核心组件IRule是访问策略的顶级接口

### 5.6.1访问策略

1. com.netflix.loadbalancer.RoundRobinRule 轮询，默认策略。
2. com.netflix.loadbalancer.RandomRule 随机
3. com.netflix.loadbalancer.RetryRule 先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务 
4. WeightedResponseTimeRule  对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择
5. BestAvailableRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
6. AvailabilityFilteringRule 先过滤掉故障实例，再选择并发较小的实例
7. ZoneAvoidanceRule 默认规则，复合判断server所在区域的性能和server的可用性选择服务器



### 5.6.2自定义策略★

关于使用IRule组件自定义策略，官方文档明确给出警告：

[https://docs.spring.io/spring-cloud-netflix/docs/2.2.6.RELEASE/reference/html/#customizing-the-ribbon-client](#customizing-the-ribbon-client) 

**这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，达不到特殊化订制的目的了。**⚠️⚠️⚠️

定义配置类

com.atguigu.myrule.MyRuleConfig（该包在启动类的上一级，不会被启动类启动时扫描到）⚠️

```java
package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.myrule.MyRuleConfig
 */
@SpringBootConfiguration
public class MyRuleConfig {

    @Bean
    public IRule getRule(){
        return new RandomRule();
    }
}
```

消费者启动类添加注解

```java
//name属性指定调用微服务的名字，从注册中心获取⚠️
//configuration属性指定了包含负载均衡配置类的类型注意
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyRuleConfig.class)
```

添加完成后重启测试即可：http://localhost/consumer/payment/get/31



## 5.7Ribbon负载均衡算法原理★

负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 =实际调用服务器位置下标 ，每次服务重启动后rest接口计数从1开始。

**底层存储服务提供者地址的是一个List数组**⚠️

List <Servicelnstance> instances = discoveryClient.getInstances('CLOUD-PAYMENT-SERVICE*);
如：
List [0] instances = 127.0.0.1:8002
List [1] instances = 127.0.0.1:8001
**8001+ 8002 组合成为集群，它们共计2台机器，集群总数为2，按照轮询算法原理：**
当总请求数为1时：1%2=1对应下标位置为1，则获得服务地址为127.0.0.1:8001
当总清求数位2时：2% 2=0对应下标位置为0，则获得服务地址为127.0.0.1:8002
当总请求数位3时：3%2 =1对应下标位置为1，则获得服务地址为127.0.0.1:8001
当总请求数位4时：4% 2=0对应下标位置为0，则获得服务地址为127.0.0.1:8002

总结：默认的负载均衡算法，通过请求次数，对集群内服务提供者数量取余，实现轮询模式负载均衡。⚠️





# 6 OpenFeign服务接口调用★★★

## 6.1OpenFeign概述

Feign对RestTemplate和Ribbon进行了封装，OpenFeign封装了Feign支持MVC注解⚠️

### 6.1.1OpenFeign介绍

Feign是一个声明式的web服务客户端，让编写web服务客户端变得非常容易，只需创建一个接口并在接口上添加注解即可

**SpringCloud对Feign进行了封装，使其支持了SpringMVC标准注解和HttpMessageConverters。Feign可以与Eureka和Ribbon组合使用以支持负载均衡。**

OpenFeign文档：https://docs.spring.io/spring-cloud-openfeign/docs/2.2.6.RELEASE/reference/html/ 



### 6.1.2OpenFeign作用

Feign使编写Java Http客户端变得更容易。

前面在使用Ribbon+RestTemplate时，利用RestTemplate对Http请求的封装处理，形成了一套模板化的调用方法。

但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务端额调用。所以，Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。

在Feign的实现下，我们只需创建一个接口并使用注解的方式来配置它（以前是DAO接口上面标注Mapper注解，现在是一个微服务接口上面标注一个Feign注解即可），即可完成对服务提供方的接口绑定，简化了使用Spring Cloud Ribbon时，自动封装服务调用客户端的开发量。

- Feign集成了 Ribbon

**利用Ribbon维护了Payment的服务列表信息，并且通过轮询实现了客户端的负载均衡。**而与Ribbon不同的是，通过Feign只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用。

- Feign和OpenFeign两者区别

OpenFeign在Feign的基础上，封装了Feign支持MVC注解，如@RequestMapping等



## 6.2OpenFeign创建消费者★

### 6.2.1创建penFeign消费者模块

在父工程下创建cloud_consumer_feign_order80模块



### 6.2.2添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud_consumer_feign_order80</artifactId>

    <dependencies>
        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <!--抽取实体类-->
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>cloud_api_commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```



### 6.2.3编写YML文件

```yaml
server:
  port: 90
spring:
  application:
    name: cloud-consumer-feign-order80
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:7001/eureka
```



### 6.2.4创建启动类★

com.atguigu.springcloud.OrderFeignMain80

```java
package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//开启Feign客户度端
@EnableFeignClients
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class,args);
    }
}
```



### 6.2.5创建Service接口★★★

com.atguigu.springcloud.service.PaymentFeignService

```java
package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springcloud.service.PaymentFeignService
 */
//通过value属性指定要调用对微服务应用程序的名字，直接在接口里边开始远程调用⚠️
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    //该地址必须与Controller中的保持一致
    @GetMapping(value = "/payment/get/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/create")
    CommonResult create(@RequestBody Payment payment);
}
```



### 6.2.6创建Conteoller实现类★★★

**通过OpenFeign远程调用微服务的接口**

com.atguigu.springcloud.controller.OrderFeignController

```java
package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annrrotation.RestController;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springcloud.controller.OrderFeignController
 */
@RestController
public class OrderFeignController {

    //远程调用微服务的接口
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }
}
```



### 6.2.7测试

1. 先启动Eureka7001
2. 再启动2个微服务8001/8002
3. 启动OpenFeign微服务：cloud-consumer-feign-order80
4. 多次刷新访问：http://localhost/consumer/payment/get/31
5. Feign自带负载均衡配置项，默认也是轮询模式



## 6.3调用流程

服务消费者OpenFeign(包含了Ribbon和RestTemplate)	⇨	Eureka Service(通过网关调用)	⇨	远程调用多个服务提供者实例(会发送当前状态：注册/续订/取消到Eureka Service)⚠️	



## 6.4OpenFeign超时控制★

**使用OpenFeign远程调用时，获取结果默认等待一秒钟，超过后报错。**⚠️⚠️⚠️

### 6.4.1模拟超时场景

服务提供者cloud_provider_payment8001模块编写故意暂停程序

```java
//测试超时
@GetMapping(value = "/payment/feign/timeout")
public String paymentFeignTimeout() {
  //单位秒
  try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) {e.printStackTrace();}
  //打印端口号判断次数
  System.out.println("serverPort = " + serverPort);
  return serverPort;
}
```

服务消费者cloud_consumer_feign_order80模块添加超时方法，PaymentFeignService

```java
//模拟超时
@GetMapping(value = "/payment/feign/timeout")
public String paymentFeignTimeout();
```

服务消费者cloud_consumer_feign_order80模块添加超时方法，OrderFeignController

```java
    //模拟超时
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }
```

测试结果：访问http://localhost/consumer/payment/feign/timeout，OpenFeign默认等待一秒钟，超过后报错

原因：默认Feign客户端只等待一秒钟，但是，服务端处理需要超过1秒钟，导致Feign客户端不想等待了，直接报错。

**为了避免这样的情况，有时候我们需要设置Feign客户端的超时控制，也即Ribbon的超时时间，因为Feign集成了Ribbon进行负载均衡。**⚠️⚠️⚠️



### 6.4.2客户端YML设置超时时间★★★

Feign设置超时时间

在服务消费者cloud_consumer_feign_order80模块的YML设置

使用Feign调用接口分两层，ribbon的调用和hystrix的调用，所以ribbon的超时时间和Hystrix的超时时间的结合就是Feign的超时时间

```yaml
#设置Feign客户端超时时间（openfeign默认支持ribbon）
ribbon:
  #读超时的时间
  ReadTimeout:  3000
  #连接超时的时间
  ConnectTimeout: 3000
  MaxAutoRetries: 1 #同一台实例最大重试次数,不包括首次调用
  MaxAutoRetriesNextServer: 1 #重试负载均衡其他的实例最大重试次数,不包括首次调用
  OkToRetryOnAllOperations: false #非Get请求是否重试，容易重复添加⚠️
  
#hystrix的超时时间
hystrix:
  command:
    default:
      execution:
        timeout:
          enabled: true
        isolation:
          thread:
            timeoutInMilliseconds: 9000
```

一般情况下 都是 ribbon 的超时时间（<）hystrix（熔断器⚠）的超时时间（因为涉及到ribbon的重试机制）⚠️，因为ribbon的重试机制和Feign的重试机制有冲突，所以源码中默认关闭Feign的重试机制，具体看源码。

要开启Feign的重试机制如下：（Feign默认重试五次 源码中有）

```java
@Bean
Retryer feignRetryer() {
        return  new Retryer.Default();
}
```

**根据上面的参数计算重试的次数：(MaxAutoRetries+1)*(MaxAutoRetriesNextServer+1)一共产生4次调用⚠️**

如果在重试期间，时间超过了hystrix的超时时间，便会立即执行熔断，fallback。所以要根据上面配置的参数计算hystrix的超时时间，使得在重试期间不能达到hystrix的超时时间，不然重试机制就会没有意义

**hystrix超时时间的计算： (1 + MaxAutoRetries + MaxAutoRetriesNextServer) * ReadTimeout 即按照以上的配置 hystrix的超时时间应该配置为 （1+1+1）*3=9秒**

当ribbon超时后且hystrix没有超时，便会采取重试机制。当OkToRetryOnAllOperations设置为false时，只会对get请求进行重试。如果设置为true，便会对所有的请求进行重试，如果是put或post等写操作，如果服务器接口没做幂等性，会产生不好的结果，所以OkToRetryOnAllOperations慎用。

如果不配置ribbon的重试次数，默认会重试一次⚠️

注意：

- 默认情况下,GET方式请求无论是连接异常还是读取异常,都会进行重试
- 非GET方式请求,只有连接异常时,才会进行重试



## 6.5OpenFeign日志打印功能

**Feign提供了日志打印功能，我们可以通过配置来调整日志的级别，从而对接口的调用情况进行监控和输出。**

### 6.5.1日志级别

NONE：默认的，不显示任何日志

BASIC：仅记录请求方法、RUL、响应状态码及执行时间

HEADERS：除了BASIC中定义的信息之外，还有请求和响应的头信息

FULL：除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据



### 6.5.2日志配置类

com.atguigu.springcloud.config.FeignConfig

```java
package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
 
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
```



### 6.5.3YML文件配置日志

```yaml
 #开启OpenFeign日志打印功能
logging:
  level:
    com.atguigu.springcloud.service.PaymentFeignService: debug
```





# 7 Hystrix断路器

## 7.1Hystrix概述

复杂分布式体系结构中的应用程序有数十个依赖关系，每一个依赖关系在某些时候将不可避免的失败。

### 7.1.1分布式的服务雪崩

**服务雪崩：**

多个微服务之间调用的时候，假如微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务，这就是所谓的"扇出"。

如果扇出的链路上某个微服务的调用响应的时间过长或者不可用，对微服A的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的"雪崩效应"。

对于高流量的应用来说，单一的后端依赖可能会导致所有的服务器上的所有资源都在几秒钟内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障。这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。

所以，通常当你发现一个模块下的某个实例失败后，这时候这个模块依然还会接收流量，然后这个有问题的模块还调用了其他的模块，这样就会发生级联故障，或者叫雪崩。



### 7.1.2服务雪崩解决方案

**Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等。**

Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。

“断路器”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似熔断保险丝），向调用方返回一个符合预期的、可处理的备选响应（Fallback），而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

**Hystrix作用：**

- 服务降级
- 服务熔断
- 接近实时的监控



## 7.2Hystrix重要概念★

### 7.2.1服务降级Fallback★

服务器忙，请稍候再试，不让客户端等待并立刻返回一个友好提示（只是返回个兜底的数据⚠️）

**哪些情况会触发降级:**

- 程序运行异常
- 超时自动降级
- 服务熔断触发服务降级
- 线程池/信号量打满也会导致服务降级
- 人工降级（如双十一业务繁忙，客户的服务等第二天再处理）



### 7.2.2服务熔断Breaker★

类比保险丝达到最大服务访问后，直接拒绝访问，拉闸限电，然后调用服务降级的方法并返回友好提示

过程：

服务的降级->进而熔断->恢复调用链路



### 7.2.3服务限流Flowlimit★

秒杀高并发等操作，严禁一窝蜂的过来拥挤，大家排队，一秒钟N个，有序进行



## 7.3搭建hystrix服务提供者

### 7.3.1创建模块

创建子工程cloud_provider_hystrix_payment8001



### 7.3.2添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud_provider_hystrix_payment8001</artifactId>


    <dependencies>
        <!--新增hystrix-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```



### 7.3.3创建YML文件

```yaml
server:
  port: 8001

spring:
  application:
    name: cloud-hystrix-payment-service

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:7001/eureka/
```



### 7.3.4创建启动类

com.atguigu.springcloud.PaymentHystrixMain8001

```java
package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class,args);
    }
}
```



### 7.3.5创建Service层

Service接口：com.atguigu.springcloud.service.PaymentService

```java
package com.atguigu.springcloud.service;

public interface PaymentService {
    String paymentInfo_OK(Integer id);
    String payment_Timeout(Integer id);
}
```

Service实现类：com.atguigu.springcloud.service.impl.

```java
package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    //成功
    public String paymentInfo_OK(Integer id){
        //随便return的数据，用于测试
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id：  "+id+"\t"+"哈哈哈"  ;
    }

    //失败，超时
    public String payment_Timeout(Integer id){
        int timeNumber = 3;
        //延时操作
        try { TimeUnit.SECONDS.sleep(timeNumber); }catch (Exception e) {e.printStackTrace();}
        //随便return的数据，用于测试
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+id+"\t"+"呜呜呜"+" 耗时(秒)"+timeNumber;
    }
}
```



### 7.3.6创建Controller层

com.atguigu.springcloud.controller.PaymentController

```java
package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("*******result:"+result);
        return result;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentService.payment_Timeout(id);
        log.info("*******result:"+result);
        return result;
    }
}
```



### 7.3.7正常测试

- 启动eureka7001
- 启动cloud-provider-hystrix-payment8001
- 访问
- 访问： http://localhost:8001/payment/hystrix/ok/31
- 每次调用耗费3秒钟： http://localhost:8001/payment/hystrix/timeout/31
- 上述module均OK
- 以上述为根基平台，从正确->错误->降级熔断->恢复



### 7.3.8Jmeter压测测试

**下载地址：**

WIN下载地址：https://archive.apache.org/dist/jmeter/binaries/

MAC下载地址：https://dlcdn.apache.org//jmeter/binaries/apache-jmeter-5.5.tgz

**使用方法：**

1. 先添加并设置线程组参数
   - 设置线程数800、2000等
   - 设置Ramp-Up时间(秒)
   - 设置循环次数100
2. 再添加并设置HTTP请求取样器的参数
   - 设置协议http
   - 设置服务器名称或IP127.0.0.1
   - 设置端口号8001
   - 设置HTTP请求GET
   - 设置路径http://localhost:8001/payment/hystrix/timeout/31
3. 点击上方的测试按钮

**压测的过程中再来访问一下微服务：**

http://localhost:8001/payment/hystrix/ok/31

http://localhost:8001/payment/hystrix/timeout/31

**演示结果：**

两个都在自己转圈圈

为什么会被卡死

tomcat的默认的工作线程数被打满了，没有多余的线程来分解压力和处理。

**Jmeter压测结论：**

上面还是服务提供者8001自己测试，假如此时外部的消费者80也来访问，那消费者只能干等，最终导致消费端80不满意，服务端8001直接被拖死



## 7.4搭建hystrix服务消费者

### 7.4.1创建模块

创建子模块cloud_consumer_feign_hystrix_order80



### 7.4.2添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud_consumer_feign-hystrix_order80</artifactId>

    <dependencies>
        <!--新增hystrix-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--抽取实体类-->
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>cloud_api_commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```



### 7.4.3创建YML文件

```yaml
server:
  port: 90

spring:
  application:
    name: cloud-consumer-hystrix-order

eureka:
  client:
  	#表示向注册中心注册自己
    register-with-eureka: true    
    #表示需要去注册中心检索服务
    fetch-registry: true   
    service-url:
      defaultZone: http://localhost:7001/eureka/
```



### 7.4.4创建启动类

com.atguigu.springcloud.OrderHystrixMain80

```java
package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OrderHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class,args);
    }
}
```



### 7.4.5创建Service层

com.atguigu.springcloud.service.PaymentHystrixService

```java
package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//注册中心中8001服务提供者的名字
@FeignClient("CLOUD-HYSTRIX-PAYMENT-SERVICE")
public interface PaymentHystrixService {
    //与8001服务提供者一致
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    //与8001服务提供者一致
    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_Timeout(@PathVariable("id") Integer id);
}
```



### 7.4.6创建Controller层

com.atguigu.springcloud.controller.OrderHystrixController

```java
package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("*******result:"+result);
        return result;
    }
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        String result = paymentHystrixService. paymentInfo_Timeout(id);
        log.info("*******result:"+result);
        return result;
    }
}
```



### 7.4.7Jmeter压测测试★

正常测试：http://localhost/consumer/payment/hystrix/ok/32

正常测试无问题后，启动Jmeter压测，默认超时时间为1秒，压测下很容易出现超时。

**高并发测试：**

- 2W个线程压8001
- 消费端80微服务再去访问正常的OK微服务8001地址
- http://localhost/consumer/payment/hystrix/timeout/32

测试结果：超时错误。

原因：8001同一层次的其他接口服务被困死，因为tomcat线程里面的工作线程已经被挤占完毕，导致超时



## 7.5服务降级

核心降级注解@HystrixCommand，一般会配置一个全局降级方法进行使用。不过局部降级方法优先级高于全局降级方法⚠️⚠️⚠️

### 7.5.1服务提供者配置降级★★

修改cloud_provider_hystrix_payment8001模块

**降级核心注解：** @HystrixCommand

设置降级条件和兜底方法：com.atguigu.springcloud.service.impl.PaymentServiceImpl

```java
    //失败
    /**
     * 超时降级演示
     * 注解@HystrixCommand：⚠️
     * fallbackMethod：设置降级方法
     * commandProperties：设置降级的其他参数
     */
    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler",commandProperties = {
            /**
             * 注解@HystrixProperty：
             * value：配置多少毫秒内不会降级，正常处理业务
             */
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String payment_Timeout(Integer id){
        //设置数学异常，进行模拟异常测试，不会等待，立刻执行兜底方法⚠️
        //int a = 2/0;

        int timeNumber = 3;
        //延时操作
        try { TimeUnit.SECONDS.sleep(timeNumber); }catch (Exception e) {e.printStackTrace();}
        //随便return的数据，用于测试
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+id+"\t"+"呜呜呜"+" 耗时(秒)"+timeNumber;
    }

    /**
     * 降级兜底方法，上面方法出问题,我来处理，返回一个出错信息，降级方法与业务方法的参数类型、个数、返回值要保持一致⚠️
     * 触发条件：当违反配置的降级规则（比如注解设置了5秒却睡了6秒）和出现异常时都会触发降级
     */
    public String payment_TimeoutHandler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+" payment_TimeoutHandler,系统繁忙,请稍后再试\t o(╥﹏╥)o ";
    }
```

启动类开启降级注解

```java
//开启服务降级
@EnableCircuitBreaker
```



### 7.5.2服务消费者配置降级★★

修改cloud_consumer_hystrix_order80模式，降级更多在服务消费者。⚠️

设置降级条件和兜底方法：com.atguigu.springcloud.controller.OrderHystrixController

```java
    //超时降级演示⚠️
    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler",commandProperties = {
            //超过1.5秒就降级自己
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        //设置数学异常，进行模拟异常测试，不会等待，立刻执行兜底方法⚠️
        //int a = 2/0;
        String result = paymentHystrixService. paymentInfo_Timeout(id);
        log.info("*******result:"+result);
        return result;
    }

    //兜底方法，上面方法出问题,我来处理，返回一个出错信息⚠️
    public String payment_TimeoutHandler(Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒后再试。或自己运行出  错，请检查自己。";
    }
```

启动类开启降级注解，注意和服务提供者的启动类设置的不同⚠️

```java
//开启服务降级
@EnableHystrix
```

配置文件中开启降级

```yaml
feign:
	hystrix:
    #开启消费者端降级
		enabled: true
```



### 7.5.3测试降级

访问：http://localhost:8001/payment/hystrix/timeout/31

测试服务提供者违反配置的降级规则、出现异常两种情况的降级触发

访问：http://localhost:90/consumer/payment/hystrix/timeout/31

测试服务消费者违反配置的降级规则、出现异常两种情况的降级触发（实际上访问的URL响应超过3秒，分别是超时异常和算数异常）



### 7.5.4全局兜底方法★

每个方法配置一个服务降级方法，技术上可以，但实际上会代码膨胀，除了个别重要的核心业务配置专属兜底方法，其它普通的可以通过@DefaultProperties(defaultFallback = "")统一跳转到统一处理结果页面

通用的和独享的各自分开，避免了代码膨胀，合理减少了代码量

Controller类上加注解，指定全局兜底方法

```java
//全局的兜底方法
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
```

Controller类中定义兜底方法

```java
    //下面是全局fallback方法⚠️
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试,(┬＿┬)";
    }
```

使用全局兜底方法的类添加注解即可

```java
    //配置全局兜底方法，不过还是专属兜底方法优先级更高⚠️
		@HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
      //设置数学异常，进行模拟异常测试，不会等待，立刻执行兜底方法⚠️
      //int a = 2/0;
      String result = paymentHystrixService. paymentInfo_Timeout(id);
      log.info("*******result:"+result);
      return result;
    }
```



### 7.5.5抽取兜底类★★★

**在服务消费端，需要为Feign客户端定义的接口添加一个服务降级处理的实现类即可实现解耦，降级的最终解决方案⚠️**

可以应对的异常：

- 运行
- 超时
- 宕机

抽取兜底方法：

（1）创建PaymentFallbackService类实现PaymentFeignClientService接口⚠️

com.atguigu.springcloud.fallback.PaymentFallbackService

```java
package com.atguigu.springcloud.fallback;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK , (┬＿┬)";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut , (┬＿┬)";
    }
}
```

（2）YML开启容错（之前已开启）

```yaml
feign:
  hystrix:
    #如果处理自身的容错就开启。开启方式与生产端不一样。
    enabled: true
```

（3）修改Service接口的@FeignClient注解，指定降级服务类

```java
ackage com.atguigu.springcloud.service;

import com.atguigu.springcloud.fallback.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//value属性指定调用的微服务名⚠️
//fallback属性指定包含当前接口方法的降级类对象⚠️
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVICE",fallback = PaymentFallbackService.class)
//@FeignClient("CLOUD-HYSTRIX-PAYMENT-SERVICE")
public interface PaymentHystrixService {
    //与8001服务提供者一致
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    //与8001服务提供者一致
    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_Timeout(@PathVariable("id") Integer id);
}
```

（4）去除Controller类上的服务降级设置，现在已经不需要了

```java
package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Slf4j

public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("*******result:"+result);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        //设置数学异常，进行模拟异常测试，不会等待，立刻执行兜底方法⚠️
        //int a = 2/0;
        String result = paymentHystrixService. paymentInfo_Timeout(id);
        log.info("*******result:"+result);
        return result;
    }

}
```

（5）测试

1. 单个eureka先启动7001
2. PaymentHystrixMain8001启动
3. 正常访问测试：http://localhost/consumer/payment/hystrix/ok/31
4. 故意关闭微服务8001
5. 客户端自己调用提升
6. 此时服务端provider已经down了，但是我们做了服务降级处理，让客户端在服务端不可用时也会获得提示信息而不会挂起耗死服务器

**实现原理：**⚠️⚠️⚠️

**配置服务降级类，会创建两个代理对象**，当服务提供端运行异常、超时、宕机时，原来的服务降级处理，再调用方法处理请求时，调用的是客户端服务降级类代理对象的方法⚠️



## 7.6服务熔断

### 7.6.1服务熔断概述

**断路器：（类似于家里保险丝）**

- **熔断打开**
  - 请求不再进行调用当前服务，内部设置时钟一般为MTTR(平均故障处理时间)，当打开时长达到所设时钟则进入熔断状态

- **熔断关闭**
  - 熔断关闭不会对服务进行熔断

- **熔断半开**
  - 部分请求根据规则调用当前服务，如果请求成功且符合规则则认为当前服务恢复正常，**关闭熔断**

**熔断机制概述**

熔断机制是应对雪崩效应的一种微服务链路保护机制。当扇出链路的某个微服务出错不可用或者响应时间太长时，会进行服务的降级，进而熔断该节点微服务的调用，快速返回错误的响应信息。

当检测到该节点微服务调用响应正常后，恢复调用链路。

在SpringCloud框架里，熔断机制通过Hystrix实现。**Hystrix会监控微服务间调用的状态，当失败的调用到一定阈值，缺省是`10秒内20次调用并有50%的失败`情况，就会启动熔断机制。**熔断机制的注解是@HystrixCommand



### 7.6.2配置熔断器★★★

涉及到断路器的三个重要参数：**快照时间窗、请求总数阈值、错误百分比阈值。**

配置属性参考：https://github.com/Netflix/Hystrix/wiki/Configuration 

1、快照时间窗：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒。

2、请求总数阈值：在快照时间窗内，必须满足请求总数阈值才有资格熔断。默认20，意味着在10秒内，如果该hystrix命令的调用次数不足20次，即使所有的请求都超时或其他原因失败，断路器都不会打开。

3、错误百分比阈值：当请求总数在快照时间窗内超过了阈值，**比如在快照时间窗内发生了30次调用，如果在这30次调用，有15次发生了超时异常，也就是超过50%的错误百分比，在默认设定50%阈值情况下，这时候就会将断路器打开，只要访问就走兜底方法。过了熔断时间后会尝试发送一次请求，如果正常就关闭熔断器，如果不正常就继续打开熔断器。**⚠️

**开始配置：修改cloud_provider_hystrix_payment8001模块**

添加工具类依赖，请求成功后随机返回一段字符串

```xml
<!--工具包，测试熔断-->
<dependency>
  <groupId>cn.hutool</groupId>
  <artifactId>hutool-all</artifactId>
  <version>5.1.0</version>
</dependency>
```

Service接口添加服务熔断方法：com.atguigu.springcloud.service.PaymentService

```java
String paymentCircuitBreaker(Integer id);
```

Service实现类实现服务熔断方法，并编写降级兜底方法：com.atguigu.springcloud.service.impl.PaymentServiceImpl

```java
    //服务熔断
    //10秒内10个请求，错误率大于等于60%就会触发熔断器，然后5秒后会放一个请求尝试恢复
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            //当在配置时间窗口内达到此数量，打开断路，默认20个
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            //断路多久以后开始尝试是否恢复，默认5s，如果恢复，就关了断路器
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            //出错百分比阈值，当达到此阈值后，开始短路。默认50%
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        //hutool.cn工具包
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }

    //降级兜底方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }
```

Controller实现类调用Service熔断方法：com.atguigu.springcloud.controller.PaymentController

```java
//===服务熔断
@GetMapping("/payment/circuit/{id}")
public String paymentCircuitBreaker(@PathVariable("id") Integer id){
    String result = paymentService.paymentCircuitBreaker(id);
    log.info("*******result:"+result);
    return result;
}
```

**测试：**

自测cloud-provider-hystrix-payment8001

正确： http://localhost:8001/payment/circuit/31

错误： http://localhost:8001/payment/circuit/-31

一次正确一次错误trytry

重点测试

多次错误（狂点），然后慢慢正确，发现刚开始不满足条件，就算是正确的访问地址也不能进行访问，需要慢慢的恢复链路



### 7.6.3熔断器开关条件

**断路器开启或者关闭的条件：**

1. 当满足一定阀值的时候（默认10秒内超过20个请求次数）
2. 当失败率达到一定的时候（默认10秒内超过50%请求失败）
3. 到达以上阀值，断路器将会开启
4. 当开启的时候，所有请求都不会进行转发，直接走兜底
5. 一段时间之后（默认是5秒），这个时候断路器是半开状态，会让其中一个请求进行转发。如果成功，断路器会关闭，若失败，继续开启。

**熔断器开启后：**

1：再有请求调用的时候，将不会调用主逻辑，而是直接调用降级fallbak。通过断路器，实现了自动地发现错误并将降级逻辑切换为主逻辑，减少响应延迟的效果。

2：原来的主逻辑要如何恢复呢？

对于这一个问题，hystrix也为我们实现了自动恢复功能。

当断路器打开，对主逻辑进行熔断之后，hystrix会启动一个休眠时间窗，在这个时间窗内，降级逻辑是临时的成为主逻辑，当休眠时间窗到期，断路器将进入半开状态，释放一次请求到原来的主逻辑上，如果此次请求正常返回，那么断路器将继续闭合，主逻辑恢复，如果这次请求依然有问题，断路器继续进入打开状态，休眠时间窗重新计时。



## 7.7Hystrix仪表盘

### 7.7.1Hystrix仪表盘概述

除了隔离依赖服务的调用以外，Hystrix还提供了准实时的调用监控(Hystrix Dashboard)，Hystrix会持续地记录所有通过Hystrix发起的请求的执行信息，并以统计报表和图形的形式展示给用户，包括每秒执行多少请求多少成功，多少失败等。

Netflix通过hystrix-metrics-event-stram项目实现了对以上指示的监控。Spring Cloud也提供了Hystrix Dashboard的整合，对监控内容转化成可视化界面。



### 7.7.2创建模块

创建模块cloud_consumer_hystrix_dashboard9001



### 7.7.3添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud_consumer_hystrix_dashboard9001</artifactId>

    <dependencies>
        <!--新增hystrix dashboard-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```



### 7.7.4创建YML文件

```yaml
server:
  port: 9001
hystrix:
  dashboard:
    #监控所有微服务
    proxy-stream-allow-lis
```



### 7.7.5创建启动类

关键注解：@EnableHystrixDashboard

com.atguigu.springcloud.HystrixDashboardMain9001

```java
package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class,args);
    }
}
```



### 7.7.6被监控者依赖

所有被监控者，都需要监控依赖配置：

cloud-provider-hystrix-payment8001模块添加依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```



### 7.7.8被监控者监控断路器

监控所有被监控者的断路器，都需要在被监控者的启动类中添加方法

注意：新版本Hystrix需要在主启动类MainAppHystrix8001中指定监控路径，写配置类也行

修改cloud-provider-hystrix-payment8001的启动类添加方法：

```java
/**
 *此配置是为了服务监控而配置，与服务容错本身无关，springcloud升级后的坑
 *ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，
 *只要在自己的项目里配置上下面的servlet就可以了
 */
@Bean
public ServletRegistrationBean getServlet() {
HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
registrationBean.setLoadOnStartup(1);
registrationBean.addUrlMappings("/hystrix.stream");
registrationBean.setName("HystrixMetricsStreamServlet");
return registrationBean;
}
```



### 7.7.9测试监控断路器

启动cloud-consumer-hystrix-dashboard9001该微服务后续将监控微服务8001

访问监控页面：http://localhost:9001/hystrix

输入URL：http://localhost:8001/hystrix.stream，点击按钮进行监控

测试地址

http://localhost:8001/payment/circuit/31

http://localhost:8001/payment/circuit/-31

先访问正确地址，再访问错误地址，再正确地址，会发现图示断路器都是慢慢放开的





# 8 Gateway新一代网关

Cloud全家桶中有个很重要的组件就是网关，在1.x版本中都是采用的Zuul网关https://github.com/Netflix/zuul/wiki，但在2.x版本中，zuul的升级一直跳票，SpringCloud最后自己研发了一个网关代替Zull，那就是SpringCloud Geteway； 

## 8.1Gateway概述

Gateway旨在提供一种简单而有效的方式来对API进行路由，以及提供一些强大的过滤器功能，例如：熔断、限流、重试等

SpringCloud Gateway的目标提供统一的路由方式且基于Filter链的方式提供了网关基本的功能，例如：安全、监控/指标、和限流。

Spring Cloud Gateway 使用的Webflux中的reactor-netty响应式编程组件，底层使用了Netty通讯框架。

Geteway官网：https://docs.spring.io/spring-cloud-gateway/docs/2.2.6.RELEASE/reference/html/ 

网关的作用：

- 反向代理
- 鉴权
- 流量控制
- 熔断
- 日志监控

网关位置：

外部请求	⇨	负载均衡	⇨	网关	⇨	微服务



## 8.2三大核心概念★

Web请求，通过一些匹配条件，定位到真正的服务节点。并在这个转发过程的前后，进行一些精细化控制。

Predicate就是我们的匹配条件： 而Filter，就是可以理解为一个无所不能的拦截器。有了这两个元素，再加上目标uri,就可以实现一个具体的路由了。

NettyServer	⇨	SpringCloudGeteWayBootApplication [多个Route(多个Predicate、多个Filter)]	⇨	NettyClient

### 8.2.1Route路由

路由是构建网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由



### 8.2.2Predicate断言

开发人员可以匹配HTTP请求中的所有内容（例如请求头或请求参数），如果请求与断言相匹配则进行路由



### 8.2.3Filter过滤

指的是Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前或者之后对请求进行修改。



## 8.3Gateway工作流程

### 8.3.1官网总结

**核心逻辑：路由转发+执行过滤器链**

Gateway Client	⇨	Gateway Handler Mapping	⇨	Gateway Web Handler	⇨	Filter	⇨	ProxyFilter	⇨	ProxiedService

**过程概述：**

客户端向Spring Cloud Gateway发出请求。然后在Gateway Handler Mapping中找到与请求匹配的路由，将其发送到Gateway Web Handler.

Handler再通过指定的过滤器链来将请求发送给我们实际的服务执行业务逻辑，然后返回。

过滤器会在发送代理请求之前（"pre"）或之后("post")执行业务逻辑。

Filter在**"pre"类型**的过滤器可以做参数校验、权限校验、流量监控、日志输出、协议转换等，在**"post"类型**的过滤器中可以做响应内容、响应头的修改，日志的输出，流量控制等有着非常重要的作用。



## 8.4配置入门网关模块

### 8.4.1创建项目

创建子工程cloud_gateway_gateway9527



### 8.4.2添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud_gateway_gateway9527</artifactId>

    <dependencies>
        <!--新增gateway，不需要引入web和actuator模块-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
        <!--抽取实体类-->
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>cloud_api_commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```



### 8.4.3创建YML文件★

```yaml
server:
  port: 9527
  
spring:
  application:
    name: cloud-gateway

eureka:
  instance:
    hostname: cloud-gateway-service
  client:
      register-with-eureka: true
      fetch-registry: true
      service-url:
          defaultZone: http://localhost:7001/eureka
```



### 8.4.4创建启动类★

com.atguigu.springcloud.GateWayMain9527

```java
package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GateWayMain9527 {
    public static void main(String[] args) {
            SpringApplication.run( GateWayMain9527.class,args);
        }
}
```



### 8.4.5YML新增网关配置★★★

我们目前不想暴露8001端口，希望在8001外面套一层9527，于是配置路由映射⚠️

**网关9527通过路由可以反向代理8001**

```yaml
server:
  port: 9527
  
spring:
  application:
    name: cloud-gateway
  #网关配置
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true  #开启从注册中心动态创建路由的功能，利用微服务名进行路由
      routes:
        #路由1
        - id: payment_routh #路由的ID，没有固定规则但要求唯一，建议配合服务名
          uri: http://localhost:8001   #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**   #断言,路径相匹配的进行路由

        #路由2
        - id: payment_routh2
          uri: http://localhost:8001
          predicates:
            - Path=/payment/lb/**   #断言,路径相匹配的进行路由,**代表多层路径

eureka:
  instance:
    hostname: cloud-gateway-service
  client:
    service-url:
      register-with-eureka: true
      fetch-registry: true
      defaultZone: http://localhost:7001/eureka
```



### 8.4.6测试反向代理

启动7001：cloud-eureka-server7001

启动8001：cloud-provider-payment8001（旧的）

启动9527网关：cloud-gateway-gateway9527

最终实现路由映射：

添加网关前： http://localhost:8001/payment/get/31

添加网关后： http://localhost:9527/payment/get/31



## 8.5动态路由

通过微服务名实现动态路由

默认情况下Gateway会根据注册中心的服务列表，以注册中心上微服务名为路径创建动态路由进行转发，从而实现动态路由的功能

### 8.5.1动态路由和负载均衡★★★

修改YML，从注册中心创建动态路由，利用微服务名进行路由

- **需要注意的是uri的协议为lb，表示启用Gateway的负载均衡功能。**⚠️
- lb://serviceName是spring cloud gateway在微服务中自动为我们创建的**负载均衡uri。**

```yaml
server:
  port: 9527

spring:
  application:
    name: cloud-gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true  #开启从注册中心动态创建路由的功能，利用微服务名进行路由
      routes:
        #路由1
        - id: payment_routh #路由的ID，没有固定规则但要求唯一，建议配合服务名
          #uri: http://localhost:8001   #匹配后提供服务的路由地址
          uri: lb://cloud-payment-service
          predicates:
            - Path=/payment/get/**   #断言,路径相匹配的进行路由

        #路由2
        - id: payment_routh2
          #uri: http://localhost:8001
          uri: lb://cloud-payment-service
          predicates:
            - Path=/payment/lb/**   #断言,路径相匹配的进行路由,**代表多层路径

eureka:
  instance:
    hostname: cloud-gateway-service
  client:
    service-url:
      register-with-eureka: true
      fetch-registry: true
      defaultZone: http://localhost:7001/eureka
```



### 8.5.2测试

启动一个eureka7001+两个服务提供者8001/8002+9627

访问：http://localhost:9527/payment/lb

刷新页面会发现8001/8002两个端口切换，因为实现了动态路由和负载均衡。⚠️



## 8.6Predicate的使用★

### 8.6.1断言工厂

Spring Cloud Gateway将路由匹配作为Spring WebFlux HandlerMapper基础框架的一部分。

Spring Cloud Gateway包括许多内置的Route Predicate工厂。所有这些Predicate都与HTTP请求的不同属性匹配。多个Route Predicate工厂可以进行组合

Spring Cloud Gateway创建Route对象时，使用RoutePredicateFactory创建Predicate对象，Predicate对象可以赋值给 Route。Spring Cloud Gateway包含许多内置的Route Predicate Factories。



### 8.6.2常用的Route Predicate★★

说白了，Predicate就是为了实现一组匹配规则，让请求过来找到对应的Route进行处理

- After Route Predicate：在什么之后
- Before Route Predicate：在什么之前
- Between Route Predicate：在什么中间
- Cookie Route Predicate：必须携带指定Cookie
- Header Route Predicate：请求头
- Host Route Predicate：请求地址
- Method Route Predicate：请求的方法类型
- Path Route Predicate：请求路径
- Query Route Predicate：查询参数

例子：

```yaml
spring:
  application:
    name: cloud-gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true  #开启从注册中心动态创建路由的功能，利用微服务名进行路由
      routes:
        - id: payment_routh #路由的ID，没有固定规则但要求唯一，建议配合服务名
          #uri: http://localhost:8001   #匹配后提供服务的路由地址
          uri: lb://cloud-payment-service
          predicates:
            - Path=/payment/get/**   #断言,路径相匹配的进行路由

        - id: payment_routh2
          #uri: http://localhost:8001   #匹配后提供服务的路由地址
          uri: lb://cloud-payment-service
          #断言设置⚠️
          predicates:
            - Path=/payment/lb/**   #断言,路径相匹配的进行路由
            - After=2020-03-08T10:59:34.102+08:00[Asia/Shanghai]
            - Cookie=username,zhangshuai #并且Cookie是username=zhangshuai才能访问
            - Header=X-Request-Id, \d+ #请求头中要有X-Request-Id属性并且值为整数的正则表达式
            - Host=**.atguigu.com
            - Method=GET
            - Query=username, \d+ #要有参数名称并且是正整数才能路由
```



## 8.7Filter的使用★

### 8.7.1Filter概述

路由过滤器可用于修改进入的HTTP请求和返回的HTTP响应，路由过滤器只能指定路由进行使用。

SpringCloud Gateway内置了多种路由过滤器，他们都由GatewayFilter的工厂类来产生。



### 8.7.2GatewayFilter 

GatewayFilter共计31种之多，并且没有满足要求的还可以通过配置类进行配置，分别可以在过滤前后执行相关代码。

配置方法：

```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: add_request_header_route
        uri: https://example.org
        filters:
        #过滤器工厂会在匹配的请求头上添加一对请求头，名称为X-Request-red, 值为blue
        - AddRequestHeader=X-Request-red, blue
```



### 8.7.3自定义过滤器★★★

**使用过滤器有两种方式，直接在配置文件中配置，或者实现GlobalFilter接口。**⚠️

（1）自定义全局GlobalFilter的核心接口

- GlobalFilter 
- Ordered

（2）功能

- 全局日志记录
- 统一网关鉴权

（3）实例代码

com.atguigu.springcloud.filter.MyLogGateWayFilter，**这么配置后不需要在YML中配了**。

```java
package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.Date;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter,Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*********come in MyLogGateWayFilter: "+new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("username");
        if(StringUtils.isEmpty(uname)){
            log.info("*****用户名为Null 非法用户,(┬＿┬)");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            //拦截
            return exchange.getResponse().setComplete();
        }
        //放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
```

测试：

不添加username参数响应406错误：http://localhost:9527/payment/lb/31

添加username参数后正常访问：http://localhost:9527/payment/lb/31?username=admin





# 9 分布式链路请求跟踪

## 9.1请求跟踪概述

在微服务框架中，一个由客户端发起的请求在后端系统中会经过多个不同的服务节点调用来协同产生最后的请求结果，每一个前端请求都会形成一个复杂的分布式服务调用链路，链路中的任何一环出现高延时或错误都会引起整个请求最后的失败。

而Spring Cloud Sleuth提供了一套完整的服务跟踪的解决方案，在分布式系统中提供追踪解决方案并且兼容支持了zipkin(负责展现) ，可以追踪到请求信息查找错误并可视化展示。

Spring Cloud Sleuth网站：https://docs.spring.io/spring-cloud-sleuth/docs/2.2.6.RELEASE/reference/html/

zipkin网站：https://zipkin.io/ 



## 9.2Zipkin的使用

Spring Cloud Sleuth和OpenZipkin（也称为Zipkin）集成。Zipkin是一个分布式跟踪平台，可用于跟踪跨多个服务调用的事务。Zipkin允许开发人员以图形方式查看事务占用的时间量，并分解在调用中涉及的每个微服务所用的时间。在微服务架构中，Zipkin是识别性能问题的宝贵工具。

**建立Spring Cloud Sleuth和Zipkin涉及4项操作：**

1. 将Spring Cloud Sleuth和Zipkin JAR文件添加到捕获跟踪数据的服务中；
2. 在每个服务中配置Spring属性以指向收集跟踪数据的Zipkin服务器；
3. 安装和配置Zipkin服务器以收集数据；
4. 定义每个客户端所使用的采样策略，便于向Zipkin发送跟踪信息。



### 9.2.1zipkin安装

可以在命令行直接拉取他的jar包，再使用java运行

```shell
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar
```

也可以下载后在java运行，下载地址：https://github.com/openzipkin/zipkin 

可视化网页：http://localhost:9411/zipkin/ 



### 9.2.2zipkin术语

完整的调用链路

表示一请求链路，一条链路通过Trace Id唯一标识，Span标识发起的请求信息，各span通过parent id 关联起来。

一条链路通过Trace Id唯一标识，Span标识发起的请求信息，各 span通过parent id 关联起来。

[Trace_id=x, Span_id=1, Parent_id=null] ⇨ [Trace_id=x, Span_id=2, Parent_id=1] ⇨ [Trace_id=x, Span_id=3, Parent_id=2]...

名词解释

- Trace:类似于树结构的Span集合，表示一条调用链路，存在唯一标识
- span:表示调用链路来源，通俗的理解span就是一次请求信息



## 9.3搭建链路监控步骤★

### 9.3.1服务提供者

修改cloud_provider_payment8001

pom.xml添加依赖

```xml
<!--包含了sleuth+zipkin-->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

YML配置文件

```yaml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
    
  #新增内容开始⚠️  
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    sampler:
      #采样率值介于0~1之间，1表示全部采样
      probability: 1
  #新增内容结束  
  
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/cloud2021?useUnicode
    username: root
    password: root
    
```

业务类PaymentController添加内容

```java
//测试zipkin
@GetMapping("/payment/zipkin")
public String paymentZipkin(){
        return "hi ,i'am paymentzipkin server，welcome to atguigu，O(∩_∩)O哈哈~";
}
```



### 9.3.2服务消费者

修改cloud_consumer_order80

pom.xml添加依赖

```xml
 <!--包含了sleuth+zipkin-->
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

YML配置文件

```yaml
server:
  port: 80

spring:
    application:
        name: cloud-order-service
    
  #新增内容开始⚠️  
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    sampler:
      #采样率值介于0~1之间，1表示全部采样
      probability: 1
  #新增内容结束  
  
eureka:
  client:
    #表示是否将自己注册进EurekaServer默认为true。
    register-with-eureka: false
    #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    fetchRegistry: true
```

业务类OrderController添加内容

```java
 //==> zipkin+sleuth
@GetMapping("/consumer/payment/zipkin")
public String paymentZipkin(){
        String result = restTemplate.getForObject("http://CLOUD-PAYMENT-SERVICE"+"/payment/zipkin/", String.class);
        return result;
}
```



### 9.3.3测试Zipkin

1. 依次启动eureka7001/8001/80
2. 多测试几下：http://localhost:90/consumer/payment/zipkin
3. 打开浏览器访问： http://localhost:9411
4. 点击Root列表中的【SHOW】按钮，查看依赖关系。





# 10 SpringCloud Alibaba简介

## 10.1概述

Spring Cloud Netflix项目进入维护模式，意味着SpringCloud团队将不会再向模块添加新功能。

所以2018.10.31，Spring Cloud Alibaba正式入驻了Spring Cloud官网孵化器，并在Maven中央库发布了第一个版本。



## 10.2作用

**服务限流降级**：默认支持 WebServlet、WebFlux, OpenFeign、RestTemplate、Spring Cloud Gateway, Zuul, Dubbo 和 RocketMQ 限流降级功能的接入，可以在运行时通过控制台实时修改限流降级规则，还支持查看限流降级 Metrics 监控。

**服务注册与发现**：适配 Spring Cloud 服务注册与发现标准，默认集成了 Ribbon 的支持。

**分布式配置管理**：支持分布式系统中的外部化配置，配置更改时自动刷新。

**消息驱动能力**：基于 Spring Cloud Stream 为微服务应用构建消息驱动能力。

**分布式事务**：使用 @GlobalTransactional 注解， 高效并且对业务零侵入地解决分布式事务问题。。

**阿里云对象存储**：阿里云提供的海量、安全、低成本、高可靠的云存储服务。支持在任何应用、任何时间、任何地点存储和访问任意类型的数据。

**分布式任务调度**：提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。同时提供分布式的任务执行模型，如网格任务。网格任务支持海量子任务均匀分配到所有 Worker（schedulerx-client）上执行。

**阿里云短信服务**：覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。

GitHub官网：https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md



## 10.3解决方案

一整套解决方案，简单理解就是替换Netflix那一套

Sentinel：把流量作为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

Nacos：一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

RocketMQ：一款开源的分布式消息系统，基于高可用分布式集群技术，提供低延时的、高可靠的消息发布与订阅服务。

Dubbo：Apache Dubbo™ 是一款高性能 Java RPC 框架。

Seata：阿里巴巴开源产品，一个易于使用的高性能微服务分布式事务解决方案。

Alibaba Cloud ACM：一款在分布式架构环境中对应用配置进行集中管理和推送的应用配置中心产品。

Alibaba Cloud OSS: 阿里云对象存储服务（Object Storage Service，简称 OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。您可以在任何应用、任何时间、任何地点存储和访问任意类型的数据。

Alibaba Cloud SchedulerX: 阿里中间件团队开发的一款分布式任务调度产品，提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。

Alibaba Cloud SMS: 覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。 

官网：[https://spring.io/projects/spring-cloud-alibaba#overview](#overview) 



## 10.4核心依赖★

父工程中添加的SpringCloud Alibaba依赖

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>2.2.6.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```





# 11 Nacos服务注册和配置中心

Nacos(Dynamic Naming and Configuration Service)，即命名配置服务

## 11.1Nacos简介

一个更易于构建云原生应用的动态服务发现，配置管理和服务管理中心

Nacos就是注册中心+配置中心的组合

等价于：Nacos = Eureka+Config+Bus

- **替代Eureka做服务注册中心**
- **替代Config做服务配置中心**

下载地址：https://github.com/alibaba/Nacos

官网文档：https://nacos.io/zh-cn/index.html



## 11.2CAP原则★

nacos与其他注册中心比较：

| 服务注册与发现框架 | CAP模型 | 控制台管理 | 社区活跃度        |
| ------------------ | ------- | ---------- | ----------------- |
| Eureka             | AP      | 支持       | 低（2.x版本闭源） |
| Zookeeper          | CP      | 不支持     | 中                |
| Consul             | CP      | 支持       | 高                |
| Nacos              | AP      | 支持       | 高                |

据说nacos在阿里巴巴内部有超过10万的实例运行，已经过了类似双十一等各种大型流量的考验

CAP原则又称CAP定理，指的是在一个分布式系统中，一致性（Consistency）、可用性（Availability）、分区容错性（Partition tolerance）。CAP 原则指的是，这三个要素最多只能同时实现两点，不可能三者兼顾。⚠️

- 一致性C：这里指强一致性必须时时刻刻同步，并非最终一致性，**多台服务器时刻同步信息保持一致，若不一致就停止对外服务（比如银行）**
- 可用性A：牺牲了强一致性，但是仍然有最终一致性。**网络异常时即使不一致也对外提供服务**，等网络恢复后数据会最终一致
- 分区容错性P：**若A、B、C是集群，C连不上了之后，就产生了AB和C两个数据不一致的分区**，我们无法保证网络实时在线，因此这是无法避免的必选项。这时只能从A和C中再选一个。



## 11.3安装并运行Nacos★

（1）从官网下载安装包（使用Nacos需要配置$JAVA_home环境变量）

```
https://github.com/alibaba/nacos/releases/tag/1.4.2 
```

（2）将安装包传递到服务器上后解压

```shell
tar -zxvf nacos-server-1.4.2.tar.gz
```

（3）进入bin目录运行。注意：默认：**MODE="cluster"集群方式启动，如果单机启动需要设置-m standalone参数**，否则，启动失败。

```shell
sh startup.sh -m standalone
```

（4）启动成功后访问页面，默认账号密码都是nacos

```shell
http://39.106.35.112:8848/nacos
```



## 11.4搭建Nacos的服务

### 11.4.1搭建Nacos服务提供者★★★

（1）创建模块cloudalibaba_provider_payment9001

（2）添加依赖

继承父工厂的关键依赖：

```xml
<!--spring cloud alibaba 2.2.6.RELEASE-->
<dependency>
  <groupId>com.alibaba.cloud</groupId>
  <artifactId>spring-cloud-alibaba-dependencies</artifactId>
  <version>2.2.6.RELEASE</version>
  <type>pom</type>
  <scope>import</scope>
</dependency>
```

自己的依赖：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba_provider_payment9001</artifactId>

    <dependencies>
        <!--alibaba nacos依赖⚠️-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

（3）创建YML文件

```yaml
server:
  port: 9001

spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        #配置Nacos地址
        server-addr: 39.106.35.112:8848
#配置健康监控
management:
  endpoints:
    web:
      exposure:
        #默认只公开了/health和/info端点，要想暴露所有端点只需设置成星号
        include: '*'
```

（4）创建主启动类

com.atguigu.springcloud.alibaba.PaymentMain9001

```java
package com.atguigu.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//通用注解，使用Zookeeper、Eureka或Nacos所有注册中心如zk等都可使用⚠️
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9001.class,args);
    }
}
```

（5）创建Contriller类

com.atguigu.springcloud.alibaba.controller.PaymentController

```java
package com.atguigu.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController{
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id) {
        //随便打印自己服务端口号，测试数据
        return "nacos registry, serverPort: "+ serverPort+"\t id"+id;
    }
}
```

（6）创建

创建第二个，并修改服务端口-Dserver.port=9002。

（7）测试

测试消费者工程的服务是否正常：

- http://localhost:9001/payment/nacos/1
- http://localhost:9002/payment/nacos/1

访问nacos控制台：http://39.106.35.112:8848/nacos/#/serviceManagement



### 11.4.2搭建Nacos服务消费者★★★

（1）创建模块cloudalibaba_consumer_nacos_order83

（2）添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba_consumer_nacos_order83</artifactId>

    <dependencies>
        <!--SpringCloud ailibaba nacos 依赖⚠️-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>cloud_api_commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

（3）创建YML文件

```yaml
server:
  port: 83

spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: 39.106.35.112:8848
#消费者将要去访问的微服务名称(注册成功进nacos的微服务提供者的服务名，注意：nacos-payment-provider含有IP和端口)
#key可随便定义，使用@Value注入属性时书写一致即可
service-url:
  nacos-user-service: http://nacos-payment-provider
```

（4）创建负载均衡配置类

com.atguigu.springcloud.alibaba.config.ApplicationContextConfig

```java
package com.atguigu.springcloud.alibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig{
    //负载均衡
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
```

（5）创建Contriller类

com.atguigu.springcloud.alibaba.controller.OrderNacosController

```java
package com.atguigu.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderNacosController{
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id){
        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id,String.class);
    }

}
```

（6）测试

测试页面：http://localhost:83/consumer/payment/nacos/1

83访问9001/9002，轮询负载OK

在nacos控制台尝试修改权重和开关服务再次进行测试。

**Nacos支持AP和CP模式的切换：**（默认是AP）⚠️⚠️⚠️

curl -X PUT '$NACOS_SERVER:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP' 



## 11.5Nacos作为服务配置中心

### 11.5.1创建项目

创建cloudalibaba_config_nacos_client3377模块



### 11.5.2添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba_config_nacos_client3377</artifactId>

    <dependencies>
        <!--nacos-config配置中心⚠️-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
        <!--nacos-discovery注册中心⚠️-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--web + actuator-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--一般基础配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```



### 11.5.3创建YML文件★★★

创建bootstrap.yml

Nacos同springcloud-config一样，在项目初始化时，要保证先从配置中心进行配置拉取，拉取配置之后，才能保证项目的正常启动

springboot中配置文件的加载是存在优先级顺序的，bootstrap优先级高于application⚠️

```yaml
server:
  port: 3377

spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: 39.106.35.112:8848 #服务注册中心地址
      config:
        server-addr: 39.106.35.112:8848 #配置中心地址
        file-extension: yaml #指定yaml格式的配置（yml和yaml都可以）

#${spring.application.name}-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}
#nacos-config-client-dev.yaml  (一定要与file-extension值保持一致)
```

创建application.yml

```yaml
spring:
  profiles:
    active: dev #表示开发环境，现在不是配置到application.yml，而是配置到云端
```



### 11.5.4创建启动类

com.atguigu.springcloud.alibaba.NacosConfigClientMain3377

```java
package com.atguigu.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigClientMain3377{
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientMain3377.class, args);
    }
}
```



### 11.5.5创建业务类★

com.atguigu.springcloud.alibaba.controller.ConfigClientController

```java
package com.atguigu.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//通过SpringCould原生注解@RefreshScope实现配置自动更新
//因为配置配到了云端，修改后需要实时更新⚠️⚠️⚠️
@RefreshScope
public class ConfigClientController{
    //对应nacos配置:nacos-config-client-dev.yaml
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
```



### 11.5.6在Nacos中新建配置★★

新建配置的页面：http://39.106.35.112:8848/nacos/#/newconfig

Nacos中的dataid的组成格式与SpringBoot配置文件中的匹配规则在官网中有详细说明

官方文档： https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html

说明：之所以需要配置 `spring.application.name` ，是因为它是构成 Nacos 配置管理 `dataId`字段的一部分。

在 Nacos Spring Cloud 中，`dataId` 的完整格式如下：

```plain
${prefix}-${spring.profiles.active}.${file-extension}
```

- `prefix` 默认为 `spring.application.name` 的值，也可以通过配置项 `spring.cloud.nacos.config.prefix`来配置。
- `spring.profiles.active` 即为当前环境对应的 profile，即具体使用哪个环境，比如本次使用的环境为dev。 **注意：当 `spring.profiles.active` 为空时，对应的连接符 `-` 也将不存在，dataId 的拼接格式变成 `${prefix}.${file-extension}`**，即当是单环境，并非多环境的情况。⚠️
- `file-exetension` 为配置内容的数据格式，可以通过配置项 `spring.cloud.nacos.config.file-extension` 来配置。目前只支持 `properties` 和 `yaml` 类型。

最后公式：${spring.application.name}-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}

**本工程最终配置**：nacos-config-client-dev.yaml

GROUP使用默认的即可 

新建YML文件，类型选YMAL⚠️

编写配置内容，并提交。（必须根据@Value("${config.info}")注入注解写配置）

```yaml
config:
    info: "测试Nacos作为配置中心，Hello"
```



### 11.5.7测试动态刷新

编写配置内容，并提交。

启动前需要在nacos客户端-配置管理-配置管理栏目下有没有对应的yaml配置文件

运行cloud-config-nacos-client3377的主启动类

调用接口查看配置信息： http://localhost:3377/config/info

然后修改配置内容，再次调用接口，查看是否自带动态刷新。



## 11.6分类配置★

### 11.6.1DataID方案

可在nacos客户端-配置管理-配置管理中为每个环境创建配置，按环境名称编辑Data ID即可

比如除了dev环境外，新增pro和test环境：

- nacos-config-client-pro.yaml
- nacos-config-client-test.yaml



### 11.6.2Group方案

通过Group实现环境区分，创建配置时指定组名，然后在配置文件中指定使用改组的文件，不指定时还是使用默认组的。

```yaml
spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: 39.106.35.112:8848 #服务注册中心地址
      config:
        server-addr: 39.106.35.112:8848 #配置中心地址
        file-extension: yaml #指定yaml格式的配置（yml和yaml都可以）
        #指定组⚠️
        group: TEST_GROUP
```



### 11.6.3NameSpace方案

新建一个命名空间，会自动生成命名空间ID

这时候再创建配置时需要点击切换到该命名空间，然后新建配置（可指定组）

配置文件中可指定XX名称空间IDXX组名称的环境，例：

```yaml
spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: 39.106.35.112:8848 #服务注册中心地址
      config:
        server-addr: 39.106.35.112:8848 #配置中心地址
        file-extension: yaml #指定yaml格式的配置（yml和yaml都可以）
        group: TEST_GROUP
        #指定名称空间⚠️
        namespace: 09dqa-jhy34-9ugw1-3309h-daf31
```

按照域名配置填写

```yaml
config: 
  info: 9f62d48c-ef2e-4d83-a9fb-c9db5833f93b DEFAULT_GROUP nacos-config-client-dev.yaml
```





# 12 Nacos集群和持久化配置

## 12.1Nacos部署模式

Nacos三种部署模式：

- 单机模式：用于测试和单机使用
- 集群模式：用于生产环境，确保高可用
- 多集群模式：多用于数据中心场景



## 12.2Nacos持久化配置★

### 12.2.1持久化配置原因

默认Nacos使用嵌入式数据库实现数据的存储。所以，如果启动多个默认配置下的Nacos节点，数据存储是存在一致性问题的。

为了解决这个问题，Nacos采用了集中式存储的方式来支持集群化部署，目前只支持MySQL 的存储。



### 12.2.2切换数据库

Nacos自带的derby数据库无法集群部署，必须切换为mysql数据库。

创建数据库：nacos_config

在数据库内执行Nacos官方SQL文件：/nacos/conf/nacos-mysql.sql

修改conf/application.properties中的数据库设置：(大概33行-41行)

```sh
spring.datasource.platform=mysql

db.num=1

db.url.0=jdbc:mysql://39.106.35.112:3306/nacos_config? characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user=root
db.password=abc123
```



## 12.3Nacos集群部署★

### 12.3.1配置三个节点

复制Nacos文件为三份，分别命名为nacos48、nacos49、nacos50

三个节点的端口设置，分别为：8848,8849,8850（21行）

三个节点/conf下配置cluster.conf（注意：复制份cluster.conf.example，去掉.example后缀！⚠️）

为防止cluster.conf自动添加默认IP配置，可在各自配置文件中设置服务主机IP：nacos.inetutils.ip-address=39.106.35.112⚠️

```sh
#2022-12-21T10:35:27.932
39.106.35.112:8848
39.106.35.112:8849
39.106.35.112:8850
```

修改三个节点的startup.sh文件内设置的启动内存大小，云服务器内存不够用

```shell
 93     JAVA_OPT="${JAVA_OPT} -server -Xms128m -Xmx128m -Xmn128m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"
 94     JAVA_OPT="${JAVA_OPT} -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${BASE_DIR}/logs/java_heapdump.hprof"
 95     JAVA_OPT="${JAVA_OPT} -XX:-UseLargePages"
```

分别启动三个节点，直接./startup.sh即可，因为需要使用集群启动。查看进程

```shell
[root@centos nacos]# ps -ef | grep nacos 
root      1685     1  7 10:09 pts/0    00:00:28 /opt/jdk1.8.0_152/bin/java -server -Xms256m -Xmx256m -Xmn128m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/nacos/nacos8848/logs/java_heapdump.hprof -XX:-UseLargePages -Dnacos.member.list= -Djava.ext.dirs=/opt/jdk1.8.0_152/jre/lib/ext:/opt/jdk1.8.0_152/lib/ext -Xloggc:/opt/nacos/nacos8848/logs/nacos_gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -Dloader.path=/opt/nacos/nacos8848/plugins/health,/opt/nacos/nacos8848/plugins/cmdb -Dnacos.home=/opt/nacos/nacos8848 -jar /opt/nacos/nacos8848/target/nacos-server.jar --spring.config.additional-location=file:/opt/nacos/nacos8848/conf/ --logging.config=/opt/nacos/nacos8848/conf/nacos-logback.xml --server.max-http-header-size=524288 nacos.nacos
root      2135     1 78 10:15 pts/0    00:00:08 /opt/jdk1.8.0_152/bin/java -server -Xms256m -Xmx256m -Xmn128m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/nacos/nacos8849/logs/java_heapdump.hprof -XX:-UseLargePages -Dnacos.member.list= -Djava.ext.dirs=/opt/jdk1.8.0_152/jre/lib/ext:/opt/jdk1.8.0_152/lib/ext -Xloggc:/opt/nacos/nacos8849/logs/nacos_gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -Dloader.path=/opt/nacos/nacos8849/plugins/health,/opt/nacos/nacos8849/plugins/cmdb -Dnacos.home=/opt/nacos/nacos8849 -jar /opt/nacos/nacos8849/target/nacos-server.jar --spring.config.additional-location=file:/opt/nacos/nacos8849/conf/ --logging.config=/opt/nacos/nacos8849/conf/nacos-logback.xml --server.max-http-header-size=524288 nacos.nacos
root      2188     1 42 10:15 pts/0    00:00:01 /opt/jdk1.8.0_152/bin/java -server -Xms256m -Xmx256m -Xmn128m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/nacos/nacos8850/logs/java_heapdump.hprof -XX:-UseLargePages -Dnacos.member.list= -Djava.ext.dirs=/opt/jdk1.8.0_152/jre/lib/ext:/opt/jdk1.8.0_152/lib/ext -Xloggc:/opt/nacos/nacos8850/logs/nacos_gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -Dloader.path=/opt/nacos/nacos8850/plugins/health,/opt/nacos/nacos8850/plugins/cmdb -Dnacos.home=/opt/nacos/nacos8850 -jar /opt/nacos/nacos8850/target/nacos-server.jar --spring.config.additional-location=file:/opt/nacos/nacos8850/conf/ --logging.config=/opt/nacos/nacos8850/conf/nacos-logback.xml --server.max-http-header-size=524288 nacos.nacos
root      2202  1423  0 10:15 pts/0    00:00:00 grep --color=auto nacos
```

登录任意节点查看集群：http://39.106.35.112:8848/nacos/#/clusterManagement



### 12.3.2Nginx代理集群

修改nginx的配置文件：vim /etc/nginx/nginx.conf 或 vim /usr/local/nginx/conf/nginx.conf

```sh
    # 配置上游服务器    
    upstream testProxy {
      server 39.106.35.112:8848;
      server 39.106.35.112:8849;
      server 39.106.35.112:8850;
    }


    server {
        # 访问39.106.35.112:80路径，就会去访问代理服务器
        listen       80;
        server_name  39.106.35.112;

        # 反向代理
        location / {
          # 代理转发          
          proxy_pass http://testProxy/;
        }
    }
```

重启nginx

```shell
nginx -s reload
```

测试是否代理成功：http://39.106.35.112/nacos/#/clusterManagements

测试结果：三个节点共享创建的所有配置等数据。

修改cloudalibaba_config_nacos_client3377模块的服务注册中心地址为Nginx，实现负载均衡

```yaml
server:
  port: 3377

spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        #server-addr: 39.106.35.112:8848 #服务注册中心地址
        server-addr: 39.106.35.112:80 #Nginx反向代理并负载均衡⚠️
      config:
        #server-addr: 39.106.35.112:8848 #配置中心地址
        server-addr: 39.106.35.112:80 #Nginx反向代理并负载均衡⚠️
        file-extension: yaml #指定yaml格式的配置（yml和yaml都可以）
```

测试：管理页面中修改配置等YAML文件内容，会发现三个节点内都发生了变化，再次进行测试。（此时一台节点离线不影响，AP模式）





# 13 Sentinel实现熔断与限流

## 13.1概述

Sentinel 控制台是流量控制、熔断降级规则统一配置和管理的入口，它为用户提供了机器自发现、簇点链路自发现、监控、规则配置等功能，与Hystrix相同。

```
客户端接入 -> 机器自发现 -> 查看簇点链路 -> 配置流控规则 -> 查看流控效果
```

Sentinel的作用：

- 服务雪崩
- 服务降级
- 服务熔断
- 服务限流  

Sentinel介绍：[https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D](https://github.com/alibaba/Sentinel/wiki/介绍)



## 13.2安装Sentinel控制台★★

下载地址：   https://github.com/alibaba/Sentinel/releases

下载到本地sentinel-dashboard-1.8.2.jar

运行前提：

- java8环境
- 8080端口不被占用

启动sentinel管理服务端

```shell
java -jar sentinel-dashboard-1.8.2.jar 
```

访问sentinel管理界面

登录网址：http://localhost:8080

**登录账号密码均为sentinel**⚠️



## 13.3初始化演示工程

前提是必须保障Nacos8848和Sentinel8080启动成功

### 13.3.1创建模块

创建模块cloudalibaba_sentinel_service8401



### 13.3.2添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloudalibaba_sentinel_service8401</artifactId>

    <dependencies>
        <!--抽取实体类-->
        <dependency>
            <groupId>org.example</groupId>
            <artifactId>cloud_api_commons</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency>
            <!--规则持久化时才需要导入-->
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>4.6.3</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```



### 13.3.3创建YML文件★

创建application.yml

```yaml
  cloud:
    nacos:
      discovery:
        server-addr: 39.106.35.112:8848
    sentinel:
      transport:
        dashboard: localhost:8080
        port: 8719  #默认8719，应用与Sentinel控制台交互的端口，应用本地会起一个该端口占用HttpServer

management:
  endpoints:
    web:
      exposure:
        # '*'可以用来表示所有端点都使用该属性
        include: '*'
```



### 13.3.4创建启动类★

com.atguigu.springcloud.alibaba.MainApp8401

```java
package com.atguigu.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MainApp8401{
    public static void main(String[] args) {
        SpringApplication.run(MainApp8401.class, args);
    }
}
```



### 13.3.5创建业务类

com.atguigu.springcloud.alibaba.controller.FlowLimitController

```java
package com.atguigu.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController{
    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }
}
```



### 13.3.6查看sentienl控制台

启动8401微服务，首次访问Sentienl控制台发现无信息

说明Sentinel采用的懒加载，必须执行一次访问

http://localhost:8401/testA



## 13.4流控规则

在簇点链路页面中可添加流控规则

### 13.4.1流控规则参数

**高级选项：**

- 流控模式：默认直接
- 流控效果：快速失败

**阈值类型：**

- 测试QPS：表示1秒钟内查询N次就是OK,若超过次数N，就直接-快速失败，报默认错误
- 测试并发线程：表示大并发线程数是N次内就是OK,若请求次数过快导致产生了超过N的线程，就直接-快速失败，报默认错误（线程处理很快，很难触发，可以让当前线程睡眠进行模拟测试）



### 13.4.2流控关联资源

资源名为/testA。当关联资源/testB时，多次请求/testB触发了限流规则，就会限流/testA的REST访问地址，限制的是资源名



### 13.4.3流控效果

**直接失败：**

抛出异常：Blocked by Sentinel (flow limiting)

**预热Warm Up：**

公式：阈值除以coldFactor（默认值为3），经过预热时长后才会达到阈值（即先以30%访问量冷启动，过了预热时间后再慢慢增加）

**排队等待：**

匀速排队，让请求以均匀的速度通过，阈值类型必须设置成QPS，否则无效。

设置含义：设置资源名每秒N次请求，每秒超过第N次的请求就排队等待执行，等待的超时时间为xxx毫秒。

官方文档：https://github.com/alibaba/Sentinel/wiki/%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6



## 13.5熔断规则

### 13.5.1熔断规则参数

慢调用比例：设置最大响应时间，超过该时间未响应则视为慢调用，到达一定比例就会熔断一部分时间。

异常比例：设置异常比例小数

异常数：设置异常数量

官方文档：[https://github.com/alibaba/Sentinel/wiki/%E7%86%94%E6%96%AD%E9%99%8D%E7%BA%A7](https://github.com/alibaba/Sentinel/wiki/熔断降级)



## 13.6热点规则

### 13.6.1热点参数限流规则

参数索引填写第几个参数，从0开始。（如：限制索引为0的第一个参数，每秒访问量是1）

参数例外项内可写参数类型，参数具体值和次数。（如：限制索引为0的参数，类型String，value值为phone时每秒限制访问10次）⚠️

官方文档：https://github.com/alibaba/Sentinel/wiki/%E7%83%AD%E7%82%B9%E5%8F%82%E6%95%B0%E9%99%90%E6%B5%81



### 13.6.2热点规则测试

自定义兜底方法

@SentinelResource，相当于@HystrixCommand

```java
    @GetMapping("/testHotKey")
    /**
     * 关于@SentinelResource注解
     * value属性：指定流量限制的资源名
     * blockHandler属性：指定违反规则时调用的兜底方法名
     * blockHandlerClass属性：指定包含违法规则时调用的降级方法的类的类型
     * fallback属性：指定出异常时调用的兜底方法名
     * fallbackClass属性：指定出异常时调用的兜底方法的类的类型
     * 要注意降级方法的返回值和参数要一致⚠️
     */
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey", fallback = "deal_testHotKey_exception")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
//        int age = 10/0;
        return "------testHotKey";
    }


    //兜底方法
    public String deal_testHotKey (String p1, String p2, BlockException exception){
        return "------deal_testHotKey,o(╥﹏╥)o";
    }
```

该兜底方法对算数异常无法降级处理

默认

- @SentinelResource(value = "testHotKey")
- 异常打到了前台用户界面，不友好

自定义：

- @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")  //value值与资源名一致即可
- 方法testHostKey里面第一个参数只要QPS超过每秒1次，马上降级处理



## 13.7系统规则

系统保护规则是从应用级别的入口流量进行控制，从单台机器的 load、CPU 使用率、平均 RT、入口 QPS 和并发线程数等几个维度监控应用指标，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性。

系统保护规则是应用整体维度的，而不是资源维度的，并且**仅对入口流量生效**。入口流量指的是进入应用的流量（`EntryType.IN`），比如 Web 服务或 Dubbo 服务端接收的请求，都属于入口流量。

系统规则支持以下的模式：

- **Load 自适应**（仅对 Linux/Unix-like 机器生效）：系统的 load1 作为启发指标，进行自适应系统保护。当系统 load1 超过设定的启发值，且系统当前的并发线程数超过估算的系统容量时才会触发系统保护（BBR 阶段）。系统容量由系统的 `maxQps * minRt` 估算得出。设定参考值一般是 `CPU cores * 2.5`。
- **CPU usage**（1.5.0+ 版本）：当系统 CPU 使用率超过阈值即触发系统保护（取值范围 0.0-1.0），比较灵敏。
- **平均 RT**：当单台机器上所有入口流量的平均 RT 达到阈值即触发系统保护，单位是毫秒。
- **并发线程数**：当单台机器上所有入口流量的并发线程数达到阈值即触发系统保护。
- **入口 QPS**：当单台机器上所有入口流量的 QPS 达到阈值即触发系统保护。

配置系统规则后，所有的资源都会被限制。（仅能测出入口QPS）



## 13.8@SentinelResource

@SentinelResource方法用来自定义服务降级方法

主启动添加@EnableDiscoveryClient注解，即可使用

### 13.8.1注解的使用

 * value属性：指定流量限制的资源名（配置流控等限制时，使用value指定的名字配置，不要使用/开头的⚠️）
 * blockHandler属性：指定违反规则时调用的兜底方法名
 * blockHandlerClass属性：指定包含违法规则时调用的降级方法的类的类型
 * fallback属性：指定出异常时调用的兜底方法名
 * fallbackClass属性：指定出异常时调用的兜底方法的类的类型

```java
package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.alibaba.handler.CustomerBlockHandler;
import com.atguigu.springcloud.alibaba.handler.CustomerFallBack;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController{
    @GetMapping("/testA")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }


    /**
     * 关于@SentinelResource注解
     * value属性：指定流量限制的资源名
     * blockHandler属性：指定违反规则时调用的兜底方法名
     * blockHandlerClass属性：指定包含违法规则时调用的降级方法的类的类型
     * fallback属性：指定出异常时调用的兜底方法名
     * fallbackClass属性：指定出异常时调用的兜底方法的类的类型
     * 要注意降级方法的返回值和形参列表要一致⚠️
     */
    @GetMapping("/testHotKey")
    @SentinelResource(
            value = "testHotKey",
            blockHandler = "handleException2",
            blockHandlerClass = CustomerBlockHandler.class,
            fallback = "fallBack1",
            fallbackClass = CustomerFallBack.class
    )
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        //模拟异常⚠️
        //int age = 10/0;
        return "------testHotKey";
    }
  
      //出现异常调用的兜底方法
//    public String deal_testHotKey_exception (String p1, String p2, Throwable exception){
//        return "方法执行出现了异常";
//    }


}
```



### 13.8.2违反规则兜底全局类

要注意降级方法的返回值和形参列表要一致，方法必须是public static的⚠️

```java
package com.atguigu.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {

    //要注意降级方法的返回值和形参列表要一致
    //方法必须是public static的
    
    public static String handleException(String p1, String p2,BlockException exception){
        return "包含降级方法的类中的第一个方法";
    }

    public static String handleException2(String p1, String p2,BlockException exception){
        return "包含降级方法的类中的第二个方法";
}
}

```



### 13.8.3发生异常兜底全局类

必须加static修饰，和原方法型参列表一致⚠️

```java
package com.atguigu.springcloud.alibaba.handler;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springcloud.alibaba.handler.CustomerFallBack
 */
public class CustomerFallBack {
    //必须加static修饰，和原方法型参列表一致⚠️

    public static String fallBack1(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        return "fall back 1";
    }

    public static String fallBack2(@RequestParam(value = "p1",required = false) String p1,
                            @RequestParam(value = "p2",required = false) String p2) {
        return "fall back 2";
    }
}

```



## 13.9规则持久化★

### 13.9.1规则持久化的必要性

一旦我们重启应用，Sentinel规则将消失，生产环境需要将配置规则进行持久化。 

将限流配置规则持久化进Nacos保存，只要刷新8401某个rest地址，sentinel控制台的流控规则就能看到，只要Nacos里面的配置不删除，针对8401上Sentinel上的流控规则持续有效



### 13.9.2添加持久化依赖

创建项目时已经添加了

```xml
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
```



### 13.9.3YML添加持久化配置

```yaml
server:
  port: 8401

spring:
  application:
    name: cloudalibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        server-addr: 39.106.35.112:8848
    sentinel:
      transport:
        dashboard: localhost:8080
        port: 8719  #默认8719，应用与Sentinel控制台交互的端口，应用本地会起一个该端口占用HttpServer
      #配置Sentinel规则持久化
      datasource:
        ds1:
          nacos:
            server-addr: 39.106.35.112:8848
            dataId: cloudalibaba-sentinel-service
            groupId: DEFAULT_GROUP
            data-type: json
            rule-type: flow

management:
  endpoints:
    web:
      exposure:
        include: '*'
```



### 13.9.4添加Nacos业务规则配置

新建Nacos配置

Data ID：cloudalibaba-sentinel-service

Grop：默认

描述：无

配置格式：Json

配置内容：

```json
[
    {
         "resource": "/testA",
         "limitApp": "default",
         "grade": 1,
         "count": 1,
         "strategy": 0,
         "controlBehavior": 0,
         "clusterMode": false 
    }
]
```

内容解读：

- resource: 资源名，即限流规则的作用对象
- limitApp: 流控针对的调用来源，若为default则不区分调用来源
- grade: 限流阈值类型（QPS或并发线程数）；0代表根据并发数量来限流，1代表根据QPS来进行限流控制
- count: 限流阈值
- strategy: 调用关系限流策略：直接、链路、关联
- controlBehavior: 流量控制效果（直接拒绝、Warm Up、匀速排队）
- clusterMode: 是否为集群模式

配置后修改限流规则，也可直接操作Nacos的配置中的YMAL文件。
