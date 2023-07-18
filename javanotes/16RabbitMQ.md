# 1 消息队列

## 1.1消息队列概述

消息队列的主要作用是消除高并发访问高峰，加快网站的响应速度。在不使用消息队列的情况下，用户的请求数据直接写入数据库，在高并发的情况下，会对数据库造成巨大的压力，同时也使得系统响应延迟加剧。

RabbitMQ在分布式系统中存储转发消息，可以保证数据不丢失，也能保证高可用性，即集群部署的时候部分机器宕机可以继续运行。



## 1.2消息中间件

MQ全称为Message Queue， 消息队列(MQ)是一种应用程序对应用程序的通信方法。

**消息传递：**

指的是程序之间通过消息发送数据进行通信，而不是通过直接调用彼此来通信，直接调用通常是用于诸如远程过程调用的技术。

**排队：**

指的是应用程序通过队列来通信。

**业务场景说明：**

不同进程（process）之间传递消息时，两个进程之间耦合程度过高，改动一个进程，引发必须修改另一个进程，为了隔离这两个进程，在两进程间抽离出一层（一个模块），所有两进程之间传递的消息，都必须通过消息队列，单独修改某一个进程，不会影响另一个；

不同进程（process）之间传递消息时，为了实现标准化，将消息的格式规范化了，并且，某一个进程接受的消息太多，一下子无法处理完，并且也有先后顺序，必须对收到的消息进行排队，因此诞生了事实上的消息队列；

在项目中，可将一些无需即时返回且耗时的操作提取出来，进行**异步处理**，而这种异步处理的方式大大的节省了服务器的请求响应时间，从而**提高**了**系统**的**吞吐量**。



## 1.3消息队列应用场景

### 1.3.1应用解藕

将消息写入消息队列，需要消息的系统自己从消息队列中订阅，从而系统不需要做任何修改。



### 1.3.2异步处理

场景说明：用户注册后，需要发注册邮件和注册短信，传统的做法有两种

**串行的方式：**

将注册信息写入数据库后，发送注册邮件，再发送注册短信，以上三个任务全部完成后才返回给客户端。 这有一个问题是，邮件，短信并不是必须的，它只是一个通知，而这种做法让客户端等待没有必要等待的东西。

**并行的方式：**

将注册信息写入数据库后，发送邮件的同时，发送短信，以上三个任务完成后，返回给客户端，并行的方式能提高处理的时间。虽然并行已经提高了处理时间，但是，前面说过，邮件和短信对我正常的使用网站没有任何影响，客户端没有必要等着其发送完成才显示注册成功，应该是写入数据库后就返回.

最优解决方案：

**消息队列**⚠️

引入消息队列后，把发送邮件，短信不是必须的业务逻辑异步处理

引入消息队列后，用户的响应时间就等于写入数据库的时间+写入消息队列的时间(可以忽略不计)

效率：引入消息队列后处理后，响应时间是串行的3分之1，是并行的2分之1



### 1.3.3流量削峰

流量削峰一般在秒杀活动中应用广泛

场景：秒杀活动，一般会因为流量过大，导致应用挂掉，为了解决这个问题，一般在应用前端加入消息队列。

解决方案：

使用中间件模式⚠️

消息被MQ保存起来了，然后系统就可以按照自己的消费能力来消费，比如每秒1000个数据，这样慢慢写入数据库，这样就不会卡死数据库了。

中间件模式的的优点：

系统A慢慢的按照数据库能处理的并发量，从消息队列中慢慢拉取消息。在生产中，这个短暂的高峰期积压是允许的。

**流量削峰也叫做削峰填谷**

使用了MQ之后，限制消费消息的速度为1000，但是这样一来，高峰期产生的数据势必会被积压在MQ中，高峰就被“削”掉了。但是因为消息积压，在高峰期过后的一段时间内，消费消息的速度还是会维持在 3消费完积压的消息，这就叫做“填谷”



### 1.3.4QPS和PV

QPS即每秒查询率，是对一个特定的查询服务器在规定时间内所处理流量多少的衡量标准。

或者理解：每秒的响应请求数，也即是最大吞吐能力。

计算关系：

QPS = 并发量 / 平均响应时间

并发量 = QPS * 平均响应时间

原理：每天80%的访问集中在20%的时间里，这20%时间叫做峰值时间。

公式：( 总PV数 * 80% ) / ( 每天秒数 * 20% ) = 峰值时间每秒请求数(QPS) 。

机器：峰值时间每秒QPS / 单台机器的QPS = 需要的机器 。



### 1.3.5PV、UV、PR

**PV：网页访问情况**

PV(page view)，即页面浏览量，或点击量；通常是衡量一个网络新闻频道或网站甚至一条网络新闻的主要指标。

在同一天内，pv只记录第一次进入该页面的独立访问者。同一访问者，多次访问同一页面，不重复计算PV值。

**UV ：网站访问情况**

UV(unique visitor)，指访问某个站点或点击某条新闻的不同IP地址的人数。

在同一天内，uv只记录第一次进入网站的具有独立IP的访问者。同一IP，多次进入网站不重复计算uv值。

**PR：表示网页重要级别**

PR(PageRank)，网页的级别技术，用来标识网页的等级/重要性。级别从1到10级，10级为满分。PR值越高说明该网页越受欢迎。

例如：一个PR值为1的网站表明这个网站不太具有流行度，而PR值为7到10则表明这个网站非常受欢迎（或者说极其重要）。



## 1.4AMQP 和 JMS

### 1.4.1AMQP

AMQP是一种高级消息队列协议（Advanced Message Queuing Protocol），更准确的说是一种binary wire-level protocol（链接协议）。这是其和JMS的本质差别，AMQP不从API层进行限定，而是直接定义网络交换的数据格式。



### 1.4.2JMS

JMS即Java消息服务（JavaMessage Service）应用程序接口，是一个Java平台中关于面向消息中间件（MOM）的API，用于在两个应用程序之间，或分布式系统中发送消息，进行异步通信。



### 1.4.3AMQP 与 JMS 区别

JMS是定义了统一的接口，来对消息操作进行统一；AMQP是通过规定协议来统一数据交互的格式

JMS限定了必须使用Java语言；AMQP只是协议，不规定实现方式，因此是跨语言的。

JMS规定了两种消息模式；而AMQP的消息模式更加丰富



## 1.5消息队列产品

ActiveMQ：基于JMS（官网视频：http://www.atguigu.com/download_detail.shtml?v=201 ）

ZeroMQ：基于C语言开发

Rabbitmq:基于AMQP协议，erlang语言开发，稳定性好（官网视频：http://www.atguigu.com/download_detail.shtml?v=327 ）

RocketMQ：基于JMS，阿里巴巴产品 （官网视频：http://www.atguigu.com/download_detail.shtml?v=332 ）

Kafka：类似MQ的产品；分布式消息系统，高吞吐量（官网视频：http://www.atguigu.com/download_detail.shtml?v=57 ）



## 1.6RabbitMQ

RabbitMQ是由erlang语言开发，基于AMQP（Advanced Message Queue 高级消息队列协议）协议实现的消息队列，它是一种应用程序之间的通信方法，消息队列在分布式系统开发中应用非常广泛。

RabbitMQ官方地址：http://www.rabbitmq.com/ 

RabbitMQ提供了6种模式：简单模式，work模式 ，Publish/Subscribe发布与订阅模式，Routing路由模式，Topics主题模式，RPC远程调用模式（远程调用，不太算MQ；暂不作介绍）；

官网对应模式介绍：https://www.rabbitmq.com/getstarted.html 



### 1.6.1RabbitMQ简介

AMQP，即 Advanced
Message Queuing Protocol（高级消息队列协议），是一个网络协议，是应用层协议的一个开放标准，为面向消息的中间件设计。基于此协议的客户端与消息中间件可传递消息，并不受客户端/中间件不同产品，不同的开发语言等条件的限制。2006年，AMQP 规范发布。类比HTTP。

RabbitMQ 采用 Erlang 语言开发。Erlang 语言由 Ericson 设计，专门为开发高并发和分布式系统的一种语言，在电信领域使用广泛。

RabbitMQ 基础架构如下：⚠️

Producer ⇨ Connection[channel...] ⇨ Broker[`VirtualHost`(`Exchange`[`Queue`...]...)...]  ⇦ Connection[channel...] ⇦ Consumer

生产者  ⇨  连接对象[多条信道]  ⇨  RabbitMQ服务器[ 多台虚拟机( 多台交换机[多个队列] ) ]  ⇦  连接对象[多条信道]  ⇦  消费者



### 1.6.2RabbitMQ 中的相关概念

**Broker**

接收和分发消息的应用，RabbitMQ Server就是 Message Broker

**Virtual host**

出于多租户和安全因素设计的，把 AMQP 的基本组件划分到一个虚拟的分组中，类似于网络中的 namespace 概念。当多个不同的用户使用同一个 RabbitMQ server 提供的服务时，可以划分出多个vhost，每个用户在自己的 vhost 创建 exchange／queue 等

**Connection**

publisher／consumer 和 broker 之间的 TCP 连接

**Channel**

如果每一次访问 RabbitMQ 都建立一个 Connection，在消息量大的时候建立 TCP Connection的开销将是巨大的，效率也较低。Channel 是在 connection 内部建立的逻辑连接，如果应用程序支持多线程，通常每个thread创建单独的 channel 进行通讯，AMQP method 包含了channel id 帮助客户端和message broker 识别 channel，所以 channel 之间是完全隔离的。Channel 作为轻量级的 Connection 极大减少了操作系统建立 TCP connection 的开销

**Exchange**

message 到达 broker 的第一站，根据分发规则，匹配查询表中的 routing key，分发消息到queue 中去。常用的类型有：direct (point-to-point)， topic (publish-subscribe) and fanout (multicast)

**Queue**

存储消息的容器，消息最终被送到这里，等待 consumer 取走

**Binding**

exchange 和 queue 之间的虚拟连接，binding 中可以包含 routing key。Binding 信息被保存到 exchange 中的查询表中，用于 message 的分发依据





# 2 安装及配置RabbitMQ

## 2.1安装RabbitMQ

### 2.1.1下载安装包

1.下载Erlang的rpm包

RabbitMQ是Erlang语言编写，所以Erang环境必须要有，注：Erlang环境一定要与RabbitMQ版本匹配：⚠️

https://www.rabbitmq.com/which-erlang.html 

2.下载socat的rpm包

rabbitmq安装依赖于socat，所以需要下载socat。

socat下载地址：http://repo.iotti.biz/CentOS/7/x86_64/socat-1.7.3.2-5.el7.lux.x86_64.rpm

3.下载RabbitMQ的rpm包

RabbitMQ下载地址：https://www.rabbitmq.com/download.html（根据自身需求及匹配关系，下载对应rpm包）rabbitmq-server-3.8.1-1.el7.noarch.rpm

也可从github等网站下载。https://hub.fastgit.org/rabbitmq/rabbitmq-server/releases/ 



### 2.1.2开始安装

在安装rabbitmq之前需要先安装socat，否则，报错。

可以采用yum安装方式：yum install socat，我们这里采用rpm安装方式

必须按照顺序安装以下rpm软件包

```shell
rpm -ivh erlang-21.3.8.9-1.el7.x86_64.rpm
```

```shell
rpm -ivh socat-1.7.3.2-1.el6.lux.x86_64.rpm
```

```shell
rpm -ivh rabbitmq-server-3.8.1-1.el7.noarch.rpm 
```



### 2.1.3启动RabbitMQ

进入RabbitMQ启动目录

```shell
cd /usr/lib/rabbitmq/bin/ 
```

启用管理插件

```shell
rabbitmq-plugins enable rabbitmq_management
```

启动RabbitMQ

```shell
systemctl start rabbitmq-server.service
```

其他命令

```shell
systemctl status rabbitmq-server.service
systemctl restart rabbitmq-server.service
systemctl stop rabbitmq-server.service 
```

查看进程

```shell
ps -ef | grep rabbitmq
```

端口信息⚠️

```shell
5672：rabbitMq的编程语言客户端连接端口
15672：rabbitMq管理界面端口
25672：rabbitMq集群的端口
```



## 2.2配置RabbitMQ

### 2.2.1配置账号

添加管理员账号密码

```shell
rabbitmqctl add_user admin admin
```

分配账号角色

```shell
rabbitmqctl set_user_tags admin administrator
```

设置用户权限 

语法：set_permissions [-p <vhostpath>] <user> <conf> <write> <read> 

```shell
#设置用户admin具有/vhost1这个virtual host中所有资源的配置、写、读权限
rabbitmqctl set_permissions -p "/" admin ".*" ".*" ".*" 
```

修改密码

```shell
rabbitmqctl change_password admin 123456
```

查看用户列表

```shell
rabbitmqctl list_users
```

关闭防火墙

```shell
systemctl stop firewalld.service
```

在web浏览器中输入地址，使用新账号登录

输入默认账号密码：admint 123456

```
http://虚拟机ip:15672/
```

注意：可以在web页面中直接添加用户并分配权限。

1、 超级管理员(administrator)

可登录管理控制台，可查看所有的信息，并且可以对用户，策略(policy)进行操作。

2、 监控者(monitoring)

可登录管理控制台，同时可以查看rabbitmq节点的相关信息(进程数，内存使用情况，磁盘使用情况等)

3、 策略制定者(policymaker)

可登录管理控制台， 同时可以对policy进行管理。但无法查看节点的相关信息。

4、 普通管理者(management)

仅可登录管理控制台，无法看到节点信息，也无法对策略进行管理。

5、 其他

无法登录管理控制台，通常就是普通的生产者和消费者。



### 2.2.2创建Virtual Hosts

虚拟主机：类似于mysql中的database。他们都是以“/”开头

点击Admin导航栏，再点击Virtual Hosts创建新的虚拟主机，名称记得加根号！比如/shopping。

点击虚拟主机，再点击Set Permission，为其用户赋予主机/shopping的所有权限。





# 3 RabbitMQ使用

官网： https://www.rabbitmq.com/ 

需求：使用简单模式完成消息传递

步骤： 

① 创建工程（生成者、消费者）

② 分别添加依赖

③ 编写生产者发送消息

④ 编写消费者接收消息

## 3.1引入依赖

pom.xml

```xml
<dependencies>
  <!--AMQP高级消息队列协议客户端-->
  <dependency>
    <groupId>com.rabbitmq</groupId>
    <artifactId>amqp-client</artifactId>
    <version>5.6.0</version>
  </dependency>
</dependencies>

<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.8.0</version>
      <configuration>
        <source>1.8</source>
        <target>1.8</target>
      </configuration>
    </plugin>
  </plugins>
</build>
```



## 3.2编写生产者

Producer

```java
package com.atguigu.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.rabbitmq.simple.Producer
 */
public class Producer {

    //设置队列的名字
    public static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception {
        //第一步，创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //主机地址
        connectionFactory.setHost("39.106.35.112");
        //连接端口;默认为 5672
        connectionFactory.setPort(5672);
        //虚拟主机名称;默认为 /
        connectionFactory.setVirtualHost("/");
        //连接用户名；默认为guest
        connectionFactory.setUsername("admin");
        //连接密码；默认为guest
        connectionFactory.setPassword("admin");

        //第二步，创建连接
        Connection connection = connectionFactory.newConnection();

        //第三步，创建频道，通过channel连接Broker的虚拟主机
        Channel channel = connection.createChannel();

        //第四步，声明（创建）队列
        /**
         * 参数说明：
         * @param queue      参数1：设置当前队列名称
         * @param durable    参数2：设置当前队列是否持久化,当服务器或MQ重启之后,是否还在
         * @param exclusive  参数3：设置当前队列是否排他，即只能当前连接使用该队列
         * @param autoDelete 参数4：设置当前队列不使用的时候自动删除队列,当没有consumer时,自动删除
         * @param arguments  参数5：队列其它参数
         */
        channel.queueDeclare("simple_queue", true, false, true, null);

        //第五步，发送消息
        //设置发送的消息
        String message = "你好，小兔子";
        /**
         * 参数说明：
         * @param exchange    参数1：指定交换机的名字，简单模式指定为空
         * @param routingKey  参数2：指定路由key，简单模式指定为队列的名字
         * @param props       参数3：指定消息的其他属性
         * @param body        参数4：要发送的消息
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());

        //第六步，关闭资源
        channel.close();
        connection.close();
    }
}
```



## 3.3编写消费者

注意：在消费者端，可以声明队列也可以不声明队列⚠️

-  如果不声明队列，要保证RabbitMQ服务器中该队列必须存在，否则会抛出异常
-  如果声明队列一定要与生产者声明队列时传入的参数值一致，否则也会抛出异常
- 声明队列时，若队列不存在则会创建该队列，若队列存在则不会创建

SimpleConsumer

```java
package com.atguigu.rabbitmq.simple;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.rabbitmq.simple.SimpleConsumer
 */
public class SimpleConsumer {

    //队列名称
    public static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args)  throws IOException, TimeoutException {

        //第一步，创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置主机地址
        connectionFactory.setHost("39.106.35.112");//ip
        //设置端口号
        connectionFactory.setPort(5672);
        //设置虚拟主机
        connectionFactory.setVirtualHost("/");
        //设置用户名
        connectionFactory.setUsername("admin");
        //设置密码
        connectionFactory.setPassword("admin");

        //第二步，创建连接Connection
        Connection connection = connectionFactory.newConnection();

        //第三步，创建通道Channel
        Channel channel = connection.createChannel();
      
        //创建队列这一步骤可省略⚠️⚠️⚠️
      	//如果没有一个名字叫simple_queue的队列，则会创建该队列，如果有则不会创建⚠️
        //channel.queueDeclare("simple_queue",true,false,false,null);

        //第四步，创建Consume对象
        /**
         * 注意：在消费者端，可以声明队列也可以不声明队列⚠️
         * 如果不声明队列消息消息时要保证RabbitMQ服务器中该队列必须存在，否则会抛出异常
         * 如果声明队列一定要与生产者声明队列时传入的参数值一致，否则也会抛出异常
         */
        Consumer consumer = new DefaultConsumer(channel){
            /**
             回调方法,当收到消息后,会自动执行该方法
             @param consumerTag  1.consumerTag：标识
             @param envelope     2.envelope：获取一些信息,交换机,路由key...
             @param properties   3.properties：配置信息
             @param body         4.body：数据
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //打印当前消费者的唯一标识
                System.out.println("consumerTag = " + consumerTag);
                //获取当前是第几条消息
                System.out.println("envelope.getDeliveryTag() = " + envelope.getDeliveryTag());
                //获取交换机的名字
                System.out.println("envelope.getExchange() = " + envelope.getExchange());
                //获取路由key
                System.out.println("envelope.getRoutingKey() = " + envelope.getRoutingKey());
                //获取消息内容
                System.out.println("new String(body) = " + new String(body));
            };
        };

        //第五步，消费消息，消费者类似一个监听程序,主要是用来监听消息
        /**
         参数说明：
         * @param queue：    设置队列的名字
         * @param autoAck：  设置是否自动确认
         * @param callback： 设置Consumer对象
         */
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
```



## 3.4抽取工具类

ConnectionUtil

```java
package com.atguigu.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.rabbitmq.util.ConnectionUtil
 */
public class ConnectionUtil {
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("39.106.35.112");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
  
    //关闭资源
    public static void closeResource(Channel channel , Connection connection) throws Exception {
        if(channel != null){
            channel.close();
        }
        if(connection != null){
            connection.close();
        }
    }
}
```





# 4 AMPQ

消息队列的信息传递遵循AMQP协议，RabbitMQ是基于AMQP协议实现的规范。⚠️

## 4.1相关概念介绍

AMQP 一个提供统一消息服务的应用层标准高级消息队列协议，是应用层协议的一个开放标准，为面向消息的中间件设计。

RabbitMQ是AMQP协议的Erlang的实现。

| 概念           | 说明                                                         |
| -------------- | ------------------------------------------------------------ |
| 连接Connection | 一个网络连接，比如TCP/IP套接字连接。                         |
| 信道Channel    | 多路复用连接中的一条独立的双向数据流通道。为会话提供物理传输介质。 |
| 客户端Client   | AMQP连接或者会话的发起者。AMQP是非对称的，客户端生产和消费消息，服务器存储和路由这些消息。 |
| 服务节点Broker | 消息中间件的服务节点；一般情况下可以将一个RabbitMQ Broker看作一台RabbitMQ 服务器。 |
| 端点           | AMQP对话的任意一方。一个AMQP连接包括两个端点（一个是客户端，一个是服务器）。 |
| 消费者Consumer | 一个从消息队列里请求消息的客户端程序。                       |
| 生产者Producer | 一个向交换机发布消息的客户端应用程序。                       |



## 4.2RabbitMQ运转流程

生产者  ⇨  连接对象[多条信道]  ⇨  RabbitMQ服务器[ 多台虚拟机( 多台交换机[多个队列] ) ]  ⇦  连接对象[多条信道]  ⇦  消费者

### 4.2.1生产者发送消息

1. 生产者创建连接（Connection），开启一个信道（Channel），连接到RabbitMQ Broker；
2. 声明队列并设置属性；如是否排它，是否持久化，是否自动删除；
3. 将路由键（空字符串）与队列绑定起来；
4. 发送消息至RabbitMQ Broker；
5. 关闭信道；
6. 关闭连接；



### 4.2.2消费者接收消息

1. 消费者创建连接（Connection），开启一个信道（Channel），连接到RabbitMQ Broker
2. 向Broker 请求消费相应队列中的消息，设置相应的回调函数；
3. 等待Broker投递响应队列中的消息，消费者接收消息；
4. 确认（ack，自动确认）接收到的消息；
5. RabbitMQ从队列中删除相应已经被确认的消息；
6. 关闭信道；
7. 关闭连接；





# 5 RabbitMQ工作模式

## 5.1工作队列模式

一个生产者对应多个消费者

### 5.1.1模式说明

Work Queues与入门程序的简单模式相比，多了一个或一些消费端，多个消费端共同消费同一个队列中的消息。

应用场景：对于 任务过重或任务较多情况使用工作队列可以提高任务处理的速度。



### 5.1.2一个生产者

WorkProducer

```java
package com.atguigu.rabbitmq.work;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.rabbitmq.work.WorkProducer
 */
public class WorkProducer {

    //队列名称
    static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        //通过信道循环发送多条消息
        for (int i = 1; i <= 10; i++) {
            String body = i+"hello rabbitmq~~~";
            channel.basicPublish("",QUEUE_NAME,null,body.getBytes());
        }
        //关闭连接和信道
        channel.close();
        connection.close();
    }
}
```



### 5.1.3两个消费者

WorkConsumer1

```java
package com.atguigu.rabbitmq.work;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;
import java.io.IOException;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.rabbitmq.work.WorkConsumer1
 */
public class WorkConsumer1 {
    static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //创建消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body：" + new String(body));
            }
        };
        //消费消息
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
```

WorkConsumer2

```java
package com.atguigu.rabbitmq.work;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;
import java.io.IOException;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.rabbitmq.work.WorkConsumer1
 */
public class WorkConsumer2 {
    static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //创建消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body：" + new String(body));
            }
        };
        //消费消息
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
```



### 5.1.4工作队列模式总结

测试结果：

启动两个消费者，然后再启动生产者发送消息；到IDEA的两个消费者对应的控制台查看是否竞争性的接收到消息。

总结：

1. 在一个队列中如果有多个消费者，那么消费者之间对于同一个消息的关系是**竞争**的关系。

2. **Work Queues** 对于任务过重或任务较多情况使用工作队列可以提高任务处理的速度。



## 5.2订阅模式类型

订阅模式流程：

生产者	⇨	交换机	⇨	[队列1、队列2、队列3]	⇨	消费者

订阅模式比工作队列模式多了一个交换机角色：

- 生产者，也就是要发送消息的程序，但是不再发送到队列中，而是发给交换机。
- 消费者，消息的接受者，会一直等待消息到来。
- 消息队列，接收消息、缓存消息。
- 交换机接收生产者发送的消息，并对消息进行处理，比如将消息递交给某个特别队列、递交给所有队列、或者将消息丢弃。至于如何操作取决于Exchange的类型
  - Fanout：广播，将消息交给所有绑定到交换机的队列
  - Direct：定向，把消息交给符合指定routing key 的队列
  - Topic：通配符，把消息交给符合routing pattern（路由模式） 的队列

注意：Exchange（交换机）只负责转发消息，不具备存储消息的能力，因此如果没有任何队列与Exchange绑定，或者没有符合路由规则的队列，那么消息会丢失！⚠️



## 5.3发布与订阅模式

发布与订阅模式也叫广播模式

### 5.3.1模式说明

1、每个消费者监听自己的队列。
2、生产者将消息发给broker，由交换机将消息转发到绑定此交换机的每个队列，每个绑定交换机的队列都将接收到消息



### 5.3.2一个生产者

FanoutProducer

```java
package com.atguigu.rabbitmq.fanout;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

//发布订阅模式（广播模式）的生产者
public class FanoutProducer {

    //交换机名称
    public static final String EXCHANGE_NAME = "fanout_exchange";
    //队列名称
    public static final String QUEUE_NAME1 = "fanout_queue1";
    public static final String QUEUE_NAME2 = "fanout_queue2";

    public static void main(String[] args) throws Exception {
        //第一步，获取连接
        Connection connection = ConnectionUtil.getConnection();

        //第二步。创建信道
        Channel channel = connection.createChannel();

        //第三步，创建交换机
        /**
         * 参数说明：
         * @param exchange：设置交换机的名字
         * @param type：设置交换机的类型
         * @param durable：设置交换机是否是持久的
         * @param autoDelete：设置交换机是否是自动删除的
         * @param internal：设置该交换机是否是内部使用的
         * @param arguments：交换机的其他参数
         */
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true, true, false, null);
        //第四步，声明队列
        channel.queueDeclare(QUEUE_NAME1, true, false, true, null);
        channel.queueDeclare(QUEUE_NAME2, true, false, true, null);

        //第五步，将队列绑定到交换机
        channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, "");
        channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, "");

        //设置要发送的消息
        String message = "该消息会被发送到每一个队列中";
        //第六步，发送消息
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
      

        //第七步，关闭资源
        ConnectionUtil.closeResource(channel, connection);
    }
}
```



### 5.3.3两个消费者

FanoutConsumer1

```java
package com.atguigu.rabbitmq.fanout;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;
import java.io.IOException;

//简单模式的消费者
public class FanoutConsumer1 {

    public static final String QUEUE_NAME = "fanout_queue1";

    public static void main(String[] args) throws Exception {
        //第一步，获取连接
        Connection connection = ConnectionUtil.getConnection();
        //第二步，创建信道
        Channel channel = connection.createChannel();
        //第三步，创建Consumer对象
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //打印当前消费者的唯一标识
                System.out.println("consumerTag = " + consumerTag);
                //获取当前是第几条消息
                System.out.println("envelope.getDeliveryTag() = " + envelope.getDeliveryTag());
                //获取交换机的名字
                System.out.println("envelope.getExchange() = " + envelope.getExchange());
                //获取路由key
                System.out.println("envelope.getRoutingKey() = " + envelope.getRoutingKey());
                //获取消息内容
                System.out.println("new String(body) = " + new String(body));
            }
        };

        //第四步，消费消息
        /**
         参数说明：
         * @param queue：设置队列的名字
         * @param autoAck：设置是否自动确认
         * @param callback：设置Consumer对象
         */
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
```

FanoutConsumer2

```java
package com.atguigu.rabbitmq.fanout;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;
import java.io.IOException;

//简单模式的消费者
public class FanoutConsumer2 {

    public static final String QUEUE_NAME = "fanout_queue2";

    public static void main(String[] args) throws Exception {
        //第一步，获取连接
        Connection connection = ConnectionUtil.getConnection();
        //第二步，创建信道
        Channel channel = connection.createChannel();
        //第三步，创建Consumer对象
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //打印当前消费者的唯一标识
                System.out.println("consumerTag = " + consumerTag);
                //获取当前是第几条消息
                System.out.println("envelope.getDeliveryTag() = " + envelope.getDeliveryTag());
                //获取交换机的名字
                System.out.println("envelope.getExchange() = " + envelope.getExchange());
                //获取路由key
                System.out.println("envelope.getRoutingKey() = " + envelope.getRoutingKey());
                //获取消息内容
                System.out.println("new String(body) = " + new String(body));
            }
        };

        //第四步，消费消息
        /**
         参数说明：
         * @param queue：设置队列的名字
         * @param autoAck：设置是否自动确认
         * @param callback：设置Consumer对象
         */
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
```



### 5.3.4发布与订阅模式总结

测试结果：

启动所有消费者，然后使用生产者发送消息；在每个消费者对应的控制台可以查看到生产者发送的所有消息；到达**广播**的效果。

在执行完测试代码后，其实到RabbitMQ的管理后台找到Exchanges选项卡，点击 fanout_exchange 的交换机，可以查看到队列的绑定

总结：

交换机需要与队列进行绑定，绑定之后；一个消息可以被多个消费者都收到。⚠️

发布订阅模式与工作队列模式的区别：

1、工作队列模式不用定义交换机，而发布/订阅模式需要定义交换机。

2、发布/订阅模式的生产方是面向交换机发送消息，工作队列模式的生产方是面向队列发送消息(底层使用默认交换机)。

3、发布/订阅模式需要设置队列和交换机的绑定，工作队列模式不需要设置，实际上工作队列模式会将队列绑 定到默认的交换机 。



## 5.4Routing路由模式

路由模式也叫定向模式

### 5.4.1模式说明

路由模式特点：

- 队列与交换机的绑定，不能是任意绑定了，而是要指定一个RoutingKey（路由key）
- 消息的发送方在 向 Exchange发送消息时，也必须指定消息的 RoutingKey。
- Exchange不再把消息交给每一个绑定的队列，而是根据消息的Routing Key进行判断，只有队列的Routingkey与消息的 Routing key完全一致，才会接收到消息

工作流程：

生产者(指定routing key)  ⇨  交换机(根据routing key匹配队列)  ⇨  [队列1、队列2、队列3] (设置routing key的匹配项)  ⇨  消费者



### 5.4.2一个生产者

DirectProducer

```java
package com.atguigu.rabbitmq.direct;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

//路由模式（定向模式）的生产者
public class DirectProducer {
    public static final String EXCHANGE_NAME = "direct_exchange";
    public static final String QUEUE_NAME1 = "direct_queue1";
    public static final String QUEUE_NAME2 = "direct_queue2";

    public static void main(String[] args) throws Exception {
        //1.获取连接
        Connection connection = ConnectionUtil.getConnection();
        //2.创建信道
        Channel channel = connection.createChannel();
        //3.声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true,true,false,null);
        //4.声明队列
        channel.queueDeclare(QUEUE_NAME1,true,false,true,null);
        channel.queueDeclare(QUEUE_NAME2,true,false,true,null);
        //5.将队列绑定到交换机
        channel.queueBind(QUEUE_NAME1,EXCHANGE_NAME,"atguigu");
        channel.queueBind(QUEUE_NAME1,EXCHANGE_NAME,"sgg");
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"bj");
        //设置要发送的消息
//        String message = "该消息会被转发到第二个队列";
        String message = "该消息会被转发到第一个队列";
        //6.发送消息
//        channel.basicPublish(EXCHANGE_NAME,"bj",null,message.getBytes());
        channel.basicPublish(EXCHANGE_NAME,"atguigu",null,message.getBytes());
        //7.关闭资源
        ConnectionUtil.closeResource(channel,connection);
    }
}
```



### 5.4.3两个消费者

DirectConsumer1

```java
package com.atguigu.rabbitmq.direct;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;
import java.io.IOException;

//简单模式的消费者
public class DirectConsumer1 {

    public static final String QUEUE_NAME = "direct_queue1";

    public static void main(String[] args) throws Exception {
        //第一步，获取连接
        Connection connection = ConnectionUtil.getConnection();
        //第二步，创建信道
        Channel channel = connection.createChannel();
        //第三步，创建Consumer对象
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //打印当前消费者的唯一标识
                System.out.println("consumerTag = " + consumerTag);
                //获取当前是第几条消息
                System.out.println("envelope.getDeliveryTag() = " + envelope.getDeliveryTag());
                //获取交换机的名字
                System.out.println("envelope.getExchange() = " + envelope.getExchange());
                //获取路由key
                System.out.println("envelope.getRoutingKey() = " + envelope.getRoutingKey());
                //获取消息内容
                System.out.println("new String(body) = " + new String(body));
            }
        };
      
        //第四步，消费消息
        /**
         参数说明：
         * @param queue：设置队列的名字
         * @param autoAck：设置是否自动确认
         * @param callback：设置Consumer对象
         */
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
```

DirectConsumer2

```java
package com.atguigu.rabbitmq.direct;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;
import java.io.IOException;

//简单模式的消费者
public class DirectConsumer2 {

    public static final String QUEUE_NAME = "direct_queue2";

    public static void main(String[] args) throws Exception {
        //第一步，获取连接
        Connection connection = ConnectionUtil.getConnection();
        //第二步，创建信道
        Channel channel = connection.createChannel();
        //第三步，创建Consumer对象
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //打印当前消费者的唯一标识
                System.out.println("consumerTag = " + consumerTag);
                //获取当前是第几条消息
                System.out.println("envelope.getDeliveryTag() = " + envelope.getDeliveryTag());
                //获取交换机的名字
                System.out.println("envelope.getExchange() = " + envelope.getExchange());
                //获取路由key
                System.out.println("envelope.getRoutingKey() = " + envelope.getRoutingKey());
                //获取消息内容
                System.out.println("new String(body) = " + new String(body));
            }
        };
			  //第四步，消费消息
        /*
         *参数说明：
             * @param queue：设置队列的名字
             * @param autoAck：设置是否自动确认
             * @param callback：设置Consumer对象
         */
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
```



### 5.4.4路由模式总结

测试结果：

启动所有消费者，然后使用生产者发送消息；在消费者对应的控制台可以查看到生产者发送对应routing key对应队列的消息

总结：

Routing模式要求队列在绑定交换机时要指定routing key，消息会转发到符合routing key的队列。



## 5.5Topics通配符模式

### 5.5.1模式说明

Topic类型与Direct相比，都是可以根据RoutingKey把消息路由到不同的队列。只不过Topic类型Exchange可以让队列在绑定Routing key 的时候**使用通配符**

Routingkey 一般都是有一个或多个单词组成，多个单词之间以”.”分割，例如： item.insert

通配符规则：

\#：匹配零个或多个单词

*：匹配不多不少恰好1个单词

举例：

item.#：能够匹配item.insert.abc 或者 item.insert

item.*：只能匹配item.insert



### 5.5.2一个生产者

TopicProducer

```java
package com.atguigu.rabbitmq.topic;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

//通配符模式（主题模式）的生产者
public class TopicProducer {
    //交换机名称
    public static final String EXCHANGE_NAME = "topic_exchange";
    //队列名称
    public static final String QUEUE_NAME1 = "topic_queue1";
    public static final String QUEUE_NAME2 = "topic_queue2";

    public static void main(String[] args) throws Exception {
        //第一步，获取连接
        Connection connection = ConnectionUtil.getConnection();
        //第二步，创建信道
        Channel channel = connection.createChannel();
        //第三步，声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC,true,true,false,null);
        //第四步，声明队列
        channel.queueDeclare(QUEUE_NAME1,true,false,true,null);
        channel.queueDeclare(QUEUE_NAME2,true,false,true,null);
        //第五步，将队列绑定到交换机
        channel.queueBind(QUEUE_NAME1,EXCHANGE_NAME,"*.atguigu.*");
        //路由key以atguigu开头
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"atguigu.#");
        //路由key以www开头
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"www.#");
        //第六步，设置要发送的消息
//        String message = "该消息会被转发到第一个和第二个队列";
//        String message = "该消息会被转发到第一个队列";
        String message = "该消息会被转发到第二个队列";
        //6.发送消息
//        channel.basicPublish(EXCHANGE_NAME,"www.atguigu.com",null,message.getBytes());
//        channel.basicPublish(EXCHANGE_NAME,"bj.atguigu.sz",null,message.getBytes());
        channel.basicPublish(EXCHANGE_NAME,"atguigu.bj.sz.sh.wh.xz.cd.zz",null,message.getBytes());
        //第七步，关闭资源
        ConnectionUtil.closeResource(channel,connection);
    }
}
```



### 5.5.3两个消费者

TopicConsumer1

```java
package com.atguigu.rabbitmq.topic;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;
import java.io.IOException;

//简单模式的消费者
public class TopicConsumer1 {

    public static final String QUEUE_NAME = "topic_queue1";

    public static void main(String[] args) throws Exception {
        //第一步，获取连接
        Connection connection = ConnectionUtil.getConnection();
        //第二步，创建信道
        Channel channel = connection.createChannel();
        //第三步，创建Consumer对象
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //打印当前消费者的唯一标识
                System.out.println("consumerTag = " + consumerTag);
                //获取当前是第几条消息
                System.out.println("envelope.getDeliveryTag() = " + envelope.getDeliveryTag());
                //获取交换机的名字
                System.out.println("envelope.getExchange() = " + envelope.getExchange());
                //获取路由key
                System.out.println("envelope.getRoutingKey() = " + envelope.getRoutingKey());
                //获取消息内容
                System.out.println("new String(body) = " + new String(body));
            }
        };

        //第四步，消费消息
        /**
         参数说明：
         * @param queue：设置队列的名字
         * @param autoAck：设置是否自动确认
         * @param callback：设置Consumer对象
         */
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
```

TopicConsumer2

```java
package com.atguigu.rabbitmq.topic;

import com.atguigu.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;
import java.io.IOException;

//简单模式的消费者
public class TopicConsumer2 {

    public static final String QUEUE_NAME = "topic_queue2";

    public static void main(String[] args) throws Exception {
        //第一步，获取连接
        Connection connection = ConnectionUtil.getConnection();
        //第二步，创建信道
        Channel channel = connection.createChannel();
        //第三步，创建Consumer对象
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //打印当前消费者的唯一标识
                System.out.println("consumerTag = " + consumerTag);
                //获取当前是第几条消息
                System.out.println("envelope.getDeliveryTag() = " + envelope.getDeliveryTag());
                //获取交换机的名字
                System.out.println("envelope.getExchange() = " + envelope.getExchange());
                //获取路由key
                System.out.println("envelope.getRoutingKey() = " + envelope.getRoutingKey());
                //获取消息内容
                System.out.println("new String(body) = " + new String(body));
            }
        };

        //第四步，消费消息
        /**
         参数说明：
         * @param queue：设置队列的名字
         * @param autoAck：设置是否自动确认
         * @param callback：设置Consumer对象
         */
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
```



### 5.5.4通配符模式总结

测试结果：

启动所有消费者，然后使用生产者发送消息；在消费者对应的控制台可以查看到生产者发送对应routing key对应队列的消息；到达**按照需要接收**的效果；并且这些routing key可以使用通配符。

总结：

Topic主题模式可以实现 Publish/Subscribe发布与订阅模式 和 Routing路由模式 的功能；只是Topic在配置routing key 的时候可以使用通配符，显得更加灵活。





# 6 Spring 整合RabbitMQ

## 6.1搭建生产者工程

创建spring_rabbitmq_producer模块作为生产者工程

### 6.1.1添加依赖

pom.xml文件中添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>spring_rabbitmq_producer</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>


    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.1.7.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.amqp</groupId>
            <artifactId>spring-rabbit</artifactId>
            <version>2.1.8.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>5.1.7.RELEASE</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



### 6.1.2编写连接文件

创建rabbitmq.properties文件

```properties
rabbitmq.host=39.106.35.112
rabbitmq.port=5672
rabbitmq.username=admin
rabbitmq.password=admin
rabbitmq.virtual-host=/
```



### 6.1.3编写配置文件

创建rabbitmq-producer.xml文件

- 声明队列时，其name属性是路由key的名称，其id属性用来被交换机绑定，自动删除属性为false等，需要自己重新配置
- 简单模式的队列不需要配置绑定的交换机，使用的是direct类型默认的交换机，交换机的名字是空字符串
- 简单模式的队列name既是队列名称，也是路由key名称
- 声明广播模式的交换机时，queue属性用来指定要绑定的队列的id值
- 声明主题类型的交换机时，pattern属性用来指定带通配符的路由key

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:rabbit="http://www.springframework.org/schema/rabbit"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/rabbit http://www.springframework.org/schema/rabbit/spring-rabbit.xsd">

    <!--引入外部属性文件-->
    <context:property-placeholder location="rabbitmq.properties"/>

    <!--配置ConnectionFactory-->
    <rabbit:connection-factory id="connectionFactory"
                               host="${rabbitmq.host}"
                               port="${rabbitmq.port}"
                               virtual-host="${rabbitmq.virtual-host}"
                               username="${rabbitmq.username}"
                               password="${rabbitmq.password}"/>

    <!--配置RabbitAdmin，用来管理交换机和队列-->
    <rabbit:admin id="rabbitAdmin" connection-factory="connectionFactory"></rabbit:admin>

    <!--配置RabbitTemplate，用于发送消息-->
    <rabbit:template id="rabbitTemplate" connection-factory="connectionFactory"></rabbit:template>


    <!--声明测试简单模式的队列-->
    <!--简单模式的队列不需要配置绑定的交换机，使用的是direct类型默认的交换机，交换机的名字是空字符串""-->
    <!--因为是简单模式，name既是队列名称，也是路由key名称-->
    <rabbit:queue id="simple_queue" name="spring_simple_queue"></rabbit:queue>

    <!--声明测试广播模式的队列-->
    <rabbit:queue id="fanout_queue1" name="spring_fanout_queue1"></rabbit:queue>
    <rabbit:queue id="fanout_queue2" name="spring_fanout_queue2"></rabbit:queue>

    <!--声明测试主题模式的队列-->
    <rabbit:queue id="topic_queue1" name="spring_topic_queue1"></rabbit:queue>
    <rabbit:queue id="topic_queue2" name="spring_topic_queue2"></rabbit:queue>

    <!--声明测试确认模式的队列-->
    <rabbit:queue id="confirm_queue" name="spring_confirm_queue"></rabbit:queue>

    <!--声明测试退回模式的队列-->
    <rabbit:queue id="return_queue" name="spring_return_queue"></rabbit:queue>


    <!--声明广播类型的交换机-->
    <rabbit:fanout-exchange name="spring_fanout_exchange">
        <rabbit:bindings>
            <!--绑定队列，queue：指定要绑定的队列的id值-->
            <rabbit:binding queue="fanout_queue1"></rabbit:binding>
            <rabbit:binding queue="fanout_queue2"></rabbit:binding>
        </rabbit:bindings>
    </rabbit:fanout-exchange>

    <!--声明主题类型的交换机-->
    <rabbit:topic-exchange name="spring_topic_exchange">
        <rabbit:bindings>
            <!--pattern属性：指定带通配符的路由key-->
            <rabbit:binding pattern="animal.dog.#" queue="topic_queue1"></rabbit:binding>
            <rabbit:binding pattern="animal.cat.*" queue="topic_queue2"></rabbit:binding>
        </rabbit:bindings>
    </rabbit:topic-exchange>
</beans>
```



### 6.1.4测试发送消息

创建测试类，分别向简单模式队列、广播模式队列、主题模式队列发送消息

- 简单模式队列，路由key是队列名称，不用写交换机
- 广播模式队列，不用写路由key
- 主题模式队列，交换机、路由key、发送消息三者都要写

```java
package com.atguigu.rabbitmq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:rabbitmq-producer.xml")
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试简单模式
     */
    @Test
    public void testSimple() {
        //向简单模式的队列发送消息，路由key是队列名称
        rabbitTemplate.convertAndSend("spring_simple_queue", "测试Spring整合RabbitMQ的简单模式");
    }

    /**
     * 测试广播模式
     */
    @Test
    public void testFanout() {
        //向广播模式的队列发送消息，要写交换机名称
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "测试Spring整合RabbitMQ的广播模式");
    }

    /**
     * 测试主题模式
     */
    @Test
    public void testTopic() {
        //向第一个队列发送消息
        rabbitTemplate.convertAndSend("spring_topic_exchange", "animal.dog.HaShiQi.DeMu.CaiQuan", "该消息进入第一个队列");
        //向第二个队列发送消息
        //rabbitTemplate.convertAndSend("spring_topic_exchange","animal.cat.OrangeCat","该消息进入第二个队列");
    }
}
```



## 6.2搭建消费者工程

### 6.1.1添加依赖

pom.xml文件中添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>spring_abbitmq_consumer</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.1.7.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.amqp</groupId>
            <artifactId>spring-rabbit</artifactId>
            <version>2.1.8.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>5.1.7.RELEASE</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



### 6.1.2编写连接文件

创建rabbitmq.properties文件

```properties
rabbitmq.host=39.106.35.112
rabbitmq.port=5672
rabbitmq.username=admin
rabbitmq.password=admin
rabbitmq.virtual-host=/
```



### 6.1.3编写配置文件

rabbitmq-consumer.xml

- 配置自动扫描包
- 配置加载连接文件
- 配置连接工厂类对象
- 配置监听器
  - 配置监听的队列时，ref注入监听类，queue-names写监听的队列名称
  - 监听类记得加@Component注解

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:rabbit="http://www.springframework.org/schema/rabbit"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/rabbit
       http://www.springframework.org/schema/rabbit/spring-rabbit.xsd">

    <!--配置自动扫描的包-->
    <context:component-scan base-package="com.atguigu.rabbitmq.listener"></context:component-scan>

    <!--加载配置文件-->
    <context:property-placeholder location="classpath:rabbitmq.properties"/>

    <!-- 配置rabbitmq connectionFactory -->
    <rabbit:connection-factory id="connectionFactory"
                               host="${rabbitmq.host}"
                               port="${rabbitmq.port}"
                               username="${rabbitmq.username}"
                               password="${rabbitmq.password}"
                               virtual-host="${rabbitmq.virtual-host}"/>

    <!--配置监听器容器-->
    <!--auto-declare="true" 当rabbitmq中不存在该队列的时候，自动创建-->
    <rabbit:listener-container connection-factory="connectionFactory" acknowledge="manual" prefetch="1">
        <!--绑定监听器监听的队列-->

        <!--监听普通模式的队列-->
        <rabbit:listener ref="simpleListener" queue-names="spring_simple_queue"></rabbit:listener>

        <!--监听广播模式的队列-->
        <rabbit:listener ref="fanoutListener1" queue-names="spring_fanout_queue1"></rabbit:listener>
        <rabbit:listener ref="fanoutListener2" queue-names="spring_fanout_queue2"></rabbit:listener>

        <!--监听主题模式的队列-->
        <rabbit:listener ref="topicListener1" queue-names="spring_topic_queue1"></rabbit:listener>
        <rabbit:listener ref="topicListener2" queue-names="spring_topic_queue2"></rabbit:listener>

    </rabbit:listener-container>
</beans>
```



### 6.1.4编写监听类

监听类大致一样，此处只写一个作为示范：

- 监听类要使用@Component注解加入IoC容器
- 监听类要继承MessageListener接口
- 监听类通过重写接口onMessage方法获取相关信息

```java
package com.atguigu.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        //获取消费者的唯一标识
        System.out.println("message.getMessageProperties().getConsumerTag() = " + message.getMessageProperties().getConsumerTag());
        //获取当前是第几条消息
        System.out.println("message.getMessageProperties().getDeliveryTag() = " + message.getMessageProperties().getDeliveryTag());
        //获取交换机的名字
        System.out.println("message.getMessageProperties().getReceivedExchange() = " + message.getMessageProperties().getReceivedExchange());
        //获取路由key
        System.out.println("message.getMessageProperties().getReceivedRoutingKey() = " + message.getMessageProperties().getReceivedRoutingKey());
        //打印消息
        System.out.println("new String(message.getBody()) = " + new String(message.getBody()));
    }
}
```



### 6.1.5测试监听消息

创建监听类

- 使用@ContextConfiguration注解加载配置文件
- 使用死循环一直监听队列消费消息

```java
package com.atguigu.rabbitmq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:rabbitmq-consumer.xml")
public class ConsumerTest {

    /**
     * 测试：死循环一直监听队列消费消息
     */
    @Test
    public void test(){
        while (true){
          
        }
    }
}
```





# 7 RabbitMQ高级特性

## 7.1消息的可靠投递

在使用 RabbitMQ 的时候，作为消息发送方希望杜绝任何消息丢失或者投递失败场景。RabbitMQ 为我们提供了两种方式用来控制消息的投递可靠性模式。confirm 确认模式和return 退回模式。

### 7.1.1两种模式触发时机

消息投递过程：producer—>rabbitmq broker—>exchange—>queue—>consumer

触发时机：

- confirm 确认模式：消息从 producer 到 exchange 则会返回一个 confirmCallback。
- return 退回模式：消息从 exchange–>queue 投递失败则会返回一个 returnCallback。



### 7.1.2添加模式开启设置

生产者的配置文件中添加

- 开启确认模式：publisher-confirms="true"
- 开启退回模式：publisher-returns="true"

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:rabbit="http://www.springframework.org/schema/rabbit"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/rabbit http://www.springframework.org/schema/rabbit/spring-rabbit.xsd">

    <!--引入外部属性文件-->
    <context:property-placeholder location="rabbitmq.properties"/>

    <!--配置ConnectionFactory-->
  	<!--开启可靠投递模式⚠️--> 
    <rabbit:connection-factory id="connectionFactory"
                               host="${rabbitmq.host}"
                               port="${rabbitmq.port}"
                               virtual-host="${rabbitmq.virtual-host}"
                               username="${rabbitmq.username}"
                               password="${rabbitmq.password}"                    
                               publisher-confirms="true"
                               publisher-returns="true"/>

    <!--配置RabbitAdmin，用来管理交换机和队列-->
    <rabbit:admin id="rabbitAdmin" connection-factory="connectionFactory"></rabbit:admin>

    <!--配置RabbitTemplate，用于发送消息-->
    <rabbit:template id="rabbitTemplate" connection-factory="connectionFactory"></rabbit:template>
</beans>
```



### 7.1.3定义队列和交换机

生产者的配置文件中添加

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:rabbit="http://www.springframework.org/schema/rabbit"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/rabbit http://www.springframework.org/schema/rabbit/spring-rabbit.xsd">

    <!--引入外部属性文件-->
    <context:property-placeholder location="rabbitmq.properties"/>

    <!--配置ConnectionFactory-->
    <rabbit:connection-factory id="connectionFactory"
                               host="${rabbitmq.host}"
                               port="${rabbitmq.port}"
                               virtual-host="${rabbitmq.virtual-host}"
                               username="${rabbitmq.username}"
                               password="${rabbitmq.password}"
                               publisher-confirms="true"
                               publisher-returns="true"/>

    <!--配置RabbitAdmin，用来管理交换机和队列-->
    <rabbit:admin id="rabbitAdmin" connection-factory="connectionFactory"></rabbit:admin>

    <!--配置RabbitTemplate，用于发送消息-->
    <rabbit:template id="rabbitTemplate" connection-factory="connectionFactory"></rabbit:template>



    <!--声明测试确认模式的队列⚠️-->
    <rabbit:queue id="confirm_queue" name="spring_confirm_queue"></rabbit:queue>

    <!--声明测试退回模式的队列⚠️-->
    <rabbit:queue id="return_queue" name="spring_return_queue"></rabbit:queue>


    <!--声明一个定向交换机来测试确认模式和退回模式⚠️-->
    <rabbit:direct-exchange name="spring_direct_exchange">
        <rabbit:bindings>
            <!--确认和退回模式的路由key，由key属性定义，而不是name-->
            <rabbit:binding queue="confirm_queue" key="test_confirm"></rabbit:binding>
            <rabbit:binding queue="return_queue" key="test_return"></rabbit:binding>
        </rabbit:bindings>
    </rabbit:direct-exchange>

</beans>
```



### 7.1.4编写测试类

确认模式编写步骤：

  1. 确认模式开启：ConnectionFactory中开启publisher-confirms="true"
  2. 设置ConfirmCallBack
 1. 设置Exchange处理消息的模式
      *      无论消息有没有到交换机,返回给消息发送方ConfirmCallBack⚠️
      *      ConfirmCallBack的参数可判断投递是否成功

退回模式编写步骤：

1. 开启回退模式：ConnectionFactory中设置publisher-returns="true"

2. 设置ReturnCallBack

3. 设置Exchange处理消息的模式：

   - 设置强制退回：rabbitTemplate.setMandatory(true);⚠️

   - 如果消息没有路由到Queue,则丢弃消息（默认）

   - 如果消息没有路由到Queue,返回给消息发送方ReturnCallBack

```java
package com.atguigu.rabbitmq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:rabbitmq-producer.xml")
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试确认模式：消息从Producer到Exchange投递时无论成功失败 都会执行 ConfirmCallBack
     * 确认模式，无论成功还是失败都会触发该回调函数⚠️
     * 步骤：
     * 1. 确认模式开启：ConnectionFactory中开启publisher-confirms="true"
     * 2. 设置ConfirmCallBack
     * 3. 设置Exchange处理消息的模式：
     *      (1). 无论消息有没有到交换机,返回给消息发送方ConfirmCallBack⚠
     *      (2). ConfirmCallBack的参数可判断投递是否成功
     */
    @Test
    public void testConfirm() throws InterruptedException {
        //设置确认模式的回调函数
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                //根据ack判断消息是否投递成功
                if (ack) {
                    System.out.println("消息投递成功！");
                } else {
                    System.out.println("消息投递失败，原因是：" + cause);
                }
            }
        });
        //成功投递
        rabbitTemplate.convertAndSend("spring_direct_exchange", "test_confirm", "该消息可以成功投递");

        //ConfirmCallback是异步的，可能在执行的之后我们实际上已经关闭了rabbitmq资源 ，所以可以让线程等待下时间：
        Thread.sleep(2000);

        //投递失败，设置一个不存在的交换机
        //rabbitTemplate.convertAndSend("spring_direct_exchange1","test_confirm","该消息可以成功投递");
    }

    /**
     * 测试回退模式： 当消息发送给Exchange后,Exchange路由到Queue失败时 才会执行 ReturnCallBack
     * 步骤：
     * 1. 开启回退模式：ConnectionFactory中设置publisher-returns="true"
     * 2. 设置ReturnCallBack
     * 3. 设置Exchange处理消息的模式：
     *      (1).设置强制退回：rabbitTemplate.setMandatory(true);⚠️
     *      (2). 如果消息没有路由到Queue,则丢弃消息（默认）
     *      (3). 如果消息没有路由到Queue,返回给消息发送方ReturnCallBack
     *
     */
    @Test
    public void testReturn() {
        //注意：退回模式需要设置强制退回⚠️
        rabbitTemplate.setMandatory(true);
        //设置退回模式的回调函数，注意：该回调函数只有消息投递失败才会调用
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                //打印失败的状态码
                System.out.println("replyCode = " + replyCode);
                //打印失败的原因
                System.out.println("replyText = " + replyText);
                //打印交换机的名字
                System.out.println("exchange = " + exchange);
                //打印路由key
                System.out.println("routingKey = " + routingKey);
                //获取消息内容
                System.out.println("new String(message.getBody()) = " + new String(message.getBody()));
            }
        });

        //成功投递
        //rabbitTemplate.convertAndSend("spring_direct_exchange","return","退回模式，该消息投递成功");
        //投递失败，设置一个不存在的路由key，因为消息没有路由到队列才会触发ReturnCallback
        rabbitTemplate.convertAndSend("spring_direct_exchange", "err_return", "退回模式，该消息投递失败");
    }
}
```



## 7.2Consumer Ack

ack指Acknowledge，确认。 表示消费端收到消息后的确认方式。

### 7.2.1两种确认方式

有二种确认方式：

自动确认：acknowledge=“none” 默认

手动确认：acknowledge=“manual”



### 7.2.2配置手动确认

在rabbit:listener-container标签中设置acknowledge属性，设置ack方式 none：自动确认，manual：手动确认

设置acknowledge="manual"

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:rabbit="http://www.springframework.org/schema/rabbit"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/rabbit
       http://www.springframework.org/schema/rabbit/spring-rabbit.xsd">

    <!--配置自动扫描的包-->
    <context:component-scan base-package="com.atguigu.rabbitmq.listener"></context:component-scan>

    <!--加载配置文件-->
    <context:property-placeholder location="classpath:rabbitmq.properties"/>

    <!-- 配置rabbitmq connectionFactory -->
    <rabbit:connection-factory id="connectionFactory"
                               host="${rabbitmq.host}"
                               port="${rabbitmq.port}"
                               username="${rabbitmq.username}"
                               password="${rabbitmq.password}"
                               virtual-host="${rabbitmq.virtual-host}"/>

    <!--配置监听器容器-->
    <!--auto-declare="true" 当rabbitmq中不存在该队列的时候，自动创建-->
  	<!--设置手动确认方式acknowledge="manual"⚠️-->
    <rabbit:listener-container connection-factory="connectionFactory" acknowledge="manual">

    </rabbit:listener-container>
</beans>
```



### 7.2.3实现退回模式监听器

编写监听器类并模拟异常：

- 让监听器类实现ChannelAwareMessageListener接口
- 如果消息成功处理，则调用channel的 basicAck()签收（手动确认）
- 如果消息处理失败，则调用channel的basicNack()拒绝签收，broker重新发送给consumer

```java
package com.atguigu.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * Consumer ACK机制：
 *  1. 设置手动签收。acknowledge="manual"
 *  2. 让监听器类实现ChannelAwareMessageListener接口
 *  3. 如果消息成功处理,则调用channel的 basicAck()签收
 *  4. 如果消息处理失败,则调用channel的basicNack()拒绝签收,broker重新发送给consumer
 */
@Component
public class AckListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        // 获取当前是第几条消息，消息传递标记
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {

            // ① 接收消息
            System.out.println("new String(message.getBody()) = " + new String(message.getBody()));

            // ② 处理业务逻辑
            System.out.println("处理业务逻辑");

            //模拟异常
            //int i = 3/0;

            // ③ 手动签收
            /**
             * 手动确认
             * 参数说明：
             * @param deliveryTag：表示当前是第几条消息，消息传递标记
             * @param multiple：设置是否批量确认，如果为true表示可以签收所有的消息
            */
            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            e.printStackTrace();

            // ④ 出现异常，将拒绝签收
            /**
             * 拒绝签收
             * 参数说明：
             * @param deliveryTag：表示当前是第几条消息，消息传递标记
             * @param multiple：设置是否批量确认，如果为true表示可以签收所有的消息
             * @param requeue：设置该消息是否放回原队列，不放入原队列时则满足放入死信队列要求
             */
            channel.basicNack(deliveryTag,true,true);
        }
    }
}
```



### 7.2.4监听退回模式队列

监听确认模式或退回模式的队列，用来测试手动确认：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:rabbit="http://www.springframework.org/schema/rabbit"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/rabbit
       http://www.springframework.org/schema/rabbit/spring-rabbit.xsd">

    <!--配置自动扫描的包-->
    <context:component-scan base-package="com.atguigu.rabbitmq.listener"></context:component-scan>

    <!--加载配置文件-->
    <context:property-placeholder location="classpath:rabbitmq.properties"/>

    <!-- 配置rabbitmq connectionFactory -->
    <rabbit:connection-factory id="connectionFactory"
                               host="${rabbitmq.host}"
                               port="${rabbitmq.port}"
                               username="${rabbitmq.username}"
                               password="${rabbitmq.password}"
                               virtual-host="${rabbitmq.virtual-host}"/>

    <!--配置监听器容器-->
    <!--auto-declare="true" 当rabbitmq中不存在该队列的时候，自动创建-->
    <rabbit:listener-container connection-factory="connectionFactory" acknowledge="manual">
        <!--监听确认模式或退回模式的队列，用来测试手动确认⚠️-->
        <rabbit:listener ref="ackListener" queue-names="spring_confirm_queue"></rabbit:listener>
    </rabbit:listener-container>
</beans>
```



## 7.3消费端限流

### 7.3.1限流概述

若请求激增，每秒5000请求添加到RabbitMQ中，每秒最大处理1000请求，则可使用限流每秒从MQ中拉取1000请求



### 7.3.2设置限流大小

在 <abbit:listener-container >中配置 prefetch 属性设置消费端一次拉取多少条消息

设置限流每次拉取1条消息：prefetch="1"

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:rabbit="http://www.springframework.org/schema/rabbit"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/rabbit
       http://www.springframework.org/schema/rabbit/spring-rabbit.xsd">

    <!--配置自动扫描的包-->
    <context:component-scan base-package="com.atguigu.rabbitmq.listener"></context:component-scan>

    <!--加载配置文件-->
    <context:property-placeholder location="classpath:rabbitmq.properties"/>

    <!-- 配置rabbitmq connectionFactory -->
    <rabbit:connection-factory id="connectionFactory"
                               host="${rabbitmq.host}"
                               port="${rabbitmq.port}"
                               username="${rabbitmq.username}"
                               password="${rabbitmq.password}"
                               virtual-host="${rabbitmq.virtual-host}"/>

    <!--配置监听器容器-->
    <!--auto-declare="true" 当rabbitmq中不存在该队列的时候，自动创建-->
    <!--prefetch="1"设置限流⚠️-->
    <rabbit:listener-container connection-factory="connectionFactory" acknowledge="manual" prefetch="1">
    </rabbit:listener-container>
</beans>
```



### 7.3.3消费端确认限流

消费端必须确认签收才会继续处理其他消息

```java
package com.atguigu.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class AckListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
      	// 获取当前是第几条消息，消息传递标记
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            System.out.println("new String(message.getBody()) = " + new String(message.getBody()));

            // 手动确认签收⚠️
            /**
             * 手动确认
             * 参数说明：
             * @param deliveryTag：表示当前是第几条消息，消息传递标记
             * @param multiple：设置是否批量确认，如果为true表示可以签收所有的消息
            */
            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(deliveryTag,true,true);
        }
    }
}
```



## 7.4 TTL

### 7.4.1概述

TTL 全称 Time To Live（存活时间/过期时间）。

当消息到达存活时间后，还没有被消费，会被自动清除。

RabbitMQ设置过期时间的两种方式：⚠️

- 对整个队列（Queue）设置过期时间。
- 对消息设置过期时间。
- 如果设置了消息的过期时间,也设置了队列的过期时间,它以时间短的为准。⚠️



### 7.4.2控制后台演示消息过期

- 在管理后台界面，增加队列，添加Message TTL参数，设置过期时间
- 在管理后台界面，增加交换机，设置为topic主题模式，交换机详情页面为新增队列绑定该交换机
- 在管理后台界面，交换机详情页面发送消息，查看是否到达时间会过期



### 7.4.3设置TTL队列

定义TTL队列

```xml
<!--定义TTL 队列⚠️-->
<rabbit:queue name="test_queue_ttl" id="test_queue_ttl">
  <!--设置queue的参数-->
  <rabbit:queue-arguments>
    <!--
               设置x-message-ttl队列的过期时间
               默认情况下value-type的类型是String类型,但时间的类型是number类型,所以需要设置成integer类型
             -->
    <entry key="x-message-ttl" value="10000" value-type="java.lang.Integer"></entry>
  </rabbit:queue-arguments>
</rabbit:queue>

<!--设置交换机⚠️-->
<rabbit:topic-exchange name="test_exchange_ttl">
    <!--交换机绑定队列-->
    <rabbit:bindings>
        <rabbit:binding pattern="ttl.#" queue="test_queue_ttl"></rabbit:binding>
    </rabbit:bindings>
</rabbit:topic-exchange>
```



### 7.4.4设置消息过期时间

单独设置消息过期时间的方法

```java
/**
 * TTL：过期时间
 * 1. 队列统一过期
 * 2. 消息单独过期
 * 如果设置了消息的过期时间,也设置了队列的过期时间,它以时间短的为准。
 */
@Test
public void testMessageTtl() {
    // 消息后处理对象,设置一些消息的参数信息
    MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {

        @Override
        public Message postProcessMessage(Message message) throws AmqpException {
            //1.设置message的信息
            // 第二个方法：消息的过期时间 ,5秒之后过期
            message.getMessageProperties().setExpiration("5000");
            //2.返回该消息
            return message;
        }
    };

    //消息单独过期
    rabbitTemplate.convertAndSend("test_exchange_ttl", "ttl.hehe", "message ttl....", messagePostProcessor);
}
```



## 7.5死信队列

死信队列，英文缩写：DLX 。DeadLetter Exchange（死信交换机），当消息成为Dead message后，可以被重新发送到另一个交换机，这个交换机就是DLX。

### 7.5.1概述

死信队列和死信交换机：

Producer	⇨	Exchange	⇨	Queue	⇨	**DLX死信交换机**⚠️	⇨	**Queue死信队列**⚠️	⇨	Consumer

模拟三十分钟超时订单取消机制：⚠️（传递的队列消息为订单号）

订单系统	⇨	正常交换机	⇨	正常队列30分钟LLT	⇨	到达死信息交换机-死信队列-库存恢复 OR 三十分钟内付款走订单系统

**消息成为死信的三种情况：**

1. 原队列存在消息过期设置，消息到达超时时间未被消费
2. 队列消息数量到达限制；比如队列最大只能存储10条消息，而发了11条消息，根据先进先出，最先发的消息会进入死信队列
3. 消费者拒接消费消息，basicNack/basicReject，并且不把消息重新放入原目标队列，requeue=false

**死信的处理方式：**

1. 丢弃，如果不是很重要，可以选择丢弃
2. 记录死信入库，然后做后续的业务分析或处理
3. 通过死信队列，由负责监听死信的应用程序进行处理（最常用，比如实现延时队列）



### 7.5.2声明死信队列

声明死信息队列、设置过期时间、队列长度、正常队列的路由Key必须满足死信队列的匹配

使用queue-arguments绑定 死信交换机

```xml
	<!--
       死信队列：
           1. 声明正常的队列(test_queue_dlx)和交换机(test_exchange_dlx)
           2. 声明死信队列(queue_dlx)和死信交换机(exchange_dlx)
           3. 正常队列绑定死信交换机
               设置两个参数：
                   * x-dead-letter-exchange：死信交换机名称
                   * x-dead-letter-routing-key：发送给死信交换机的routingkey
   -->

    <!--1. 声明正常的队列(test_queue_dlx)和交换机(test_exchange_dlx)-->
    <rabbit:queue name="test_queue_dlx" id="test_queue_dlx">
        <!--3. 正常队列绑定死信交换机-->
        <rabbit:queue-arguments>
            <!--3.1 x-dead-letter-exchange：死信交换机名称-->
            <entry key="x-dead-letter-exchange" value="exchange_dlx"/>
            <!--3.2 x-dead-letter-routing-key：发送给死信交换机的routingkey-->
            <entry key="x-dead-letter-routing-key" value="dlx.hehe"></entry>
            <!--4.1 设置队列的过期时间 ttl-->
            <entry key="x-message-ttl" value="10000" value-type="java.lang.Integer"/>
            <!--4.2 设置队列的长度限制 max-length -->
            <entry key="x-max-length" value="10" value-type="java.lang.Integer"/>
        </rabbit:queue-arguments>
    </rabbit:queue>

    <!--正常交换机-->
    <rabbit:topic-exchange name="test_exchange_dlx">
        <rabbit:bindings>
            <rabbit:binding pattern="test.dlx.#" queue="test_queue_dlx"></rabbit:binding>
        </rabbit:bindings>
    </rabbit:topic-exchange>

    <!--2. 声明死信队列(queue_dlx)和死信交换机(exchange_dlx)-->
    <rabbit:queue name="queue_dlx" id="queue_dlx"></rabbit:queue>
    <rabbit:topic-exchange name="exchange_dlx">
        <rabbit:bindings>
            <rabbit:binding pattern="dlx.#" queue="queue_dlx"></rabbit:binding>
        </rabbit:bindings>
    </rabbit:topic-exchange>
```



### 7.5.3消息过期实现

测试消息过期实现死信队列，通过网页管理平台观察结果

```java
    /**
     * 发送测试死信消息：
     *  1. 过期时间
     *  2. 长度限制
     *  3. 消息拒收
     */
    @Test
    public void testDlx(){
        //1. 测试过期时间，死信消息
        rabbitTemplate.convertAndSend("test_exchange_dlx","test.dlx.haha","我是一条消息,我会死吗？");
    }
```



### 7.5.4消息数量超出实现

测试消息数量超出实现死信队列，通过网页管理平台观察结果

```java
     /**
     * 发送测试死信消息：
     *  1. 过期时间
     *  2. 长度限制
     *  3. 消息拒收
     */
    @Test
    public void testDlx(){
      /*
        1. 测试过期时间,死信消息
        rabbitTemplate.convertAndSend("test_exchange_dlx","test.dlx.haha","我是一条消息,我会死吗？");
			 */
        //2. 测试长度限制后,消息死信
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("test_exchange_dlx","test.dlx.haha","我是一条消息,我会死吗？");
        }
    }
```



### 7.5.5消息拒收实现

测试消消息拒收实现死信队列，通过网页管理平台观察结果

在消费者工程 创建 com.atguigu.listener.DlxListener：

```java
package com.atguigu.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class DlxListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            //1.接收转换消息
            System.out.println(new String(message.getBody()));

            //2. 处理业务逻辑
            System.out.println("处理业务逻辑...");
            int i = 3/0;//出现错误
            //3. 手动签收
            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("出现异常,拒绝接受");
            //4.拒绝签收,不重回队列 requeue=false⚠️
            channel.basicNack(deliveryTag,true,false);
        }
    }
}
```

修改消费者配置文件 spring-rabbitmq-consumer.xml：

```xml
<rabbit:listener-container connection-factory="connectionFactory" acknowledge="manual">
  <!--<rabbit:listener ref="ackListener" queue-names="test_queue_confirm"></rabbit:listener>-->
  <!--<rabbit:listener ref="qosListener" queue-names="test_queue_confirm"></rabbit:listener>-->
  <!--定义监听器,监听正常队列-->
  <rabbit:listener ref="dlxListener" queue-names="test_queue_dlx"></rabbit:listener>
</rabbit:listener-container>
```

运行消费者测试类:

```java
     /**
     * 发送测试死信消息
     *  1. 过期时间
     *  2. 长度限制
     *  3. 消息拒收
     */
    @Test
    public void testDlx(){
      /*
        1. 测试过期时间,死信消息
        rabbitTemplate.convertAndSend("test_exchange_dlx","test.dlx.haha","我是一条消息,我会死吗？");

        2. 测试长度限制后,消息死信
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("test_exchange_dlx","test.dlx.haha","我是一条消息,我会死吗？");
				}
			 */
        //3. 测试消息拒收
        rabbitTemplate.convertAndSend("test_exchange_dlx","test.dlx.haha","我是一条消息,我会死吗？");
    }
```



## 7.6延迟队列

### 7.6.1实现方式

延迟队列存储的对象肯定是对应的延时消息，所谓”延时消息”是指当消息被发送以后，并不想让消费者立即拿到消息，而是等待指定时间后，消费者才拿到这个消息进行消费。

在RabbitMQ中并未提供延迟队列功能，但是可以使用：TTL+死信队列 组合实现延迟队列的效果。⚠️



### 7.6.2生产者

在生产者端实现死信队列

```xml
    <!--1. 声明正常的队列(order_exchange)和交换机(order_queue)-->
    <rabbit:queue name="order_queue" id="order_queue_dlx">
        <!--3. 正常队列绑定死信交换机-->
        <rabbit:queue-arguments>
            <entry key="x-dead-letter-exchange" value="exchange_dlx"/>
            <entry key="x-dead-letter-routing-key" value="dlx.hehe"></entry>
            <entry key="x-message-ttl" value="10000" value-type="java.lang.Integer"/>
        </rabbit:queue-arguments>
    </rabbit:queue>

    <!--正常交换机-->
    <rabbit:topic-exchange name="order_exchange">
        <rabbit:bindings>
            <rabbit:binding pattern="order.#" queue="order_queue"></rabbit:binding>
        </rabbit:bindings>
    </rabbit:topic-exchange>

    <!--2. 声明死信队列(order_queue_dlx)和死信交换机(order_exchange_dlx)-->
    <rabbit:queue name="order_queue_dlx" id="order_queue_dlx"></rabbit:queue>
    <rabbit:topic-exchange name="order_exchange_dlx">
        <rabbit:bindings>_
            <rabbit:binding pattern="dlx.order.#" queue="order_queue_dlx"></rabbit:binding>
        </rabbit:bindings>
    </rabbit:topic-exchange>
```

修改生产者，添加测试方法，运行程序创建订单延时队列

```java
    @Test
    public  void testDelay() throws InterruptedException {
        //1.发送订单消息。 将来是在订单系统中,下单成功后,发送消息
        rabbitTemplate.convertAndSend("order_exchange",
                "order.msg","订单信息：id=1,time=2020年10月17日11：41：47");

        //2.打印倒计时10秒
        for (int i = 10; i > 0 ; i--) {
            System.out.println(i+"...");
            Thread.sleep(1000);
        }
    }
```



### 7.6.3消费者

修改消费者项目，添加 com.atguigu.listener.OrderListener

```java
package com.atguigu.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            //1.接收转换消息
            System.out.println(new String(message.getBody()));

            //2. 处理业务逻辑
            System.out.println("处理业务逻辑...");
            System.out.println("根据订单id查询其状态...");
            System.out.println("判断状态是否为支付成功");
            System.out.println("取消订单,回滚库存....");
            //3. 手动签收
            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("出现异常,拒绝接受");
            //4.拒绝签收,不重回队列 requeue=false
            channel.basicNack(deliveryTag,true,false);
        }
    }
}
```

修改消费者配置文件 spring-rabbitmq-consumer.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:rabbit="http://www.springframework.org/schema/rabbit"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/rabbit
       http://www.springframework.org/schema/rabbit/spring-rabbit.xsd">
    <!--加载配置文件-->
    <context:property-placeholder location="classpath:rabbitmq.properties"/>

    <!-- 定义rabbitmq connectionFactory -->
    <rabbit:connection-factory id="connectionFactory" host="${rabbitmq.host}"
                               port="${rabbitmq.port}"
                               username="${rabbitmq.username}"
                               password="${rabbitmq.password}"
                               virtual-host="${rabbitmq.virtual-host}"/>

    <context:component-scan base-package="com.atguigu.listener" />

    <!--定义监听器容器
    acknowledge="manual"：手动签收
    acknowledge="auto" 自动签收
    -->
    <rabbit:listener-container connection-factory="connectionFactory" acknowledge="manual">

        <!--<rabbit:listener ref="ackListener" queue-names="test_queue_confirm"></rabbit:listener>-->
        <!--<rabbit:listener ref="qosListener" queue-names="test_queue_confirm"></rabbit:listener>-->
        <!--定义监听器,监听正常队列-->
        <!--<rabbit:listener ref="dlxListener" queue-names="test_queue_dlx"></rabbit:listener>-->
        <!--延迟队列效果实现：  一定要监听的是 死信队列！！！-->
        <rabbit:listener ref="orderListener" queue-names="order_queue_dlx"></rabbit:listener>
    </rabbit:listener-container>

</beans>
```

运行消费者测试类即可。





# 8 RabbitMQ与SpringBoot整合

## 8.1生产者工程

实现步骤：

1. 创建生产者SpringBoot工程
2. 引入依赖坐标
3. 编写yml配置，基本信息配置
4. 定义交换机，队列以及绑定关系的配置类
5. 注入RabbitTemplate，调用方法，完成消息发送

### 8.1.1添加依赖

pom.xml文件添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>producer_springboot</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--父工程依赖-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.6.RELEASE</version>
    </parent>

    <dependencies>
        <!--rabbitmq依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
        <!--SpringBoot启动依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
    </dependencies>
</project>
```



### 8.1.2创建配置文件

创建application.yml文件

```yaml
spring:
  rabbitmq:
    host: 39.106.35.112
    port: 5672
    username: admin
    password: admin
    virtual-host: /
```



### 8.1.3编写配置类

创建com.atguigu.config.RabbitMQConfig类

```java
package com.atguigu.config;

import org.springframework.amqp.core.*;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "springboot_topic_exchange";
    public static final String QUEUE_NAME = "springboot_topic_queue";

    //第一步，创建交换机
    @Bean
    public Exchange createExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    //第二步，创建队列
    @Bean
    public Queue createQueue(){
        return QueueBuilder.durable(QUEUE_NAME).autoDelete().build();
    }

    //第三步，绑定
    /**
     * @param queue：帮绑定哪个队列
     * @param exchange：绑定哪个交换机
     */
    @Bean
    public Binding bindQueueToExchange(Queue queue , Exchange exchange){
        /**
         * 路由key
         * noargs()：表示不指定参数
         */
        return BindingBuilder.bind(queue).to(exchange).with("springboot.#").noargs();
    }
}
```



### 8.1.4编写测试类

创建com.atguigu.config.ProducerTest类

```java
package com.atguigu;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.ProducerTest
 */
import com.atguigu.config.RabbitMQConfig;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 第一个参数：交换机名字
     * 第二个参数：routingKey
     * 第三个参数：发送的消息
     */
    @Test
    public void testSend(){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"springboot.dog","hello dog");
    }
}
```



### 8.1.5编写启动类

编写com.atguigu.ProducerApplication类

```java
package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.ProducerApplication
 */
@SpringBootApplication
public class ProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class,args);
    }
}
```



## 8.2消费者工程

### 8.2.1添加依赖

pom.xml文件添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>consumer_springboot</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--父工程依赖-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.6.RELEASE</version>
    </parent>

    <dependencies>
        <!--RabbitMQ 启动依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
    </dependencies>

</project>
```



### 8.2.2创建配置文件

创建application.yml文件

```yaml
spring:
  rabbitmq:
    host: 39.106.35.112
    port: 5672
    username: admin
    password: admin
    virtual-host: /
```



### 8.2.3编写队列监听器类

创建com.atguigu.listener.RabbitMQListener类

```java
package com.atguigu.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.listener.RabbimtMQListener
 */
@Component
public class RabbitMQListener {
    //通过@RabbitListener注解指定监听的队列
    @RabbitListener(queues = "springboot_topic_queue")
    public void onMessage(Message message){
        System.out.println("new String(message.getBody()) = " + new String(message.getBody()));
    }
}
```



### 8.2.4创建启动类

创建com.atguigu.ConsumerApplication类

```java
package com.atguigu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @Description: TODD
 * @AllClassName: com.atguigu.ConsumerApplication
 */
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
```





# 9 消息百分百成功投递

谈到消息的可靠性投递，无法避免的，在实际的工作中会经常碰到，比如一些核心业务需要保障消息不丢失，接下来我们看一个可靠性投递的流程图，说明可靠性投递的概念：



## 9.1概述

**步骤流程：**

1数据DB&消息DB	⇨	⚠️Sender【设置status=0】	⇨	2发送消息到Broker	⇨	3Broker内确认签收消息【status=1】	⇨	⚠️ConfirmListener	⇨	4操作消息DB 【操作status=1的消息】	⇨	5定时任务判断投递情况,确保百分百成功投递【通过status来判断未成功投递的消息】	⇨	6重新发送消息到Broker	⇨	7设置投递失败情况次数，到达一定重发次数后不再投递，做其他处理 【status=2，多次重复投递后仍失败则需要人工处理】

注意：成功投递状态status为0，每次投递失败status+1，定时任务判断的也是根据status!=0的消息

**步骤解读：**

Step 1： 首先把消息信息(业务数据）存储到数据库中，紧接着，我们再把这个消息记录也存储到一张消息记录表里（或者另外一个同源数据库的消息记录表）

Step 2：发送消息到MQ Broker节点（采用confirm方式发送，会有异步的返回结果）

Step 3、4：生产者端接受MQ Broker节点返回的Confirm确认消息结果，然后进行更新消息记录表里的消息状态。比如默认Status = 0 当收到消息确认成功后，更新为1即可！

Step 5：但是在消息确认这个过程中可能由于网络闪断、MQ Broker端异常等原因导致 回送消息失败或者异常。这个时候就需要发送方（生产者）对消息进行可靠性投递了，保障消息不丢失，100%的投递成功！（有一种极限情况是闪断，Broker返回的成功确认消息，但是生产端由于网络闪断没收到，这个时候重新投递可能会造成消息重复，需要消费端去做幂等处理）所以我们需要有一个定时任务，（比如每5分钟拉取一下处于中间状态的消息，当然这个消息可以设置一个超时时间，比如超过1分钟 Status = 0 ，也就说明了1分钟这个时间窗口内，我们的消息没有被确认，那么会被定时任务拉取出来）

Step 6：接下来我们把中间状态的消息进行重新投递 retry send，继续发送消息到MQ ，当然也可能有多种原因导致发送失败

Step 7：我们可以采用设置最大努力尝试次数，比如投递了3次，还是失败，那么我们可以将最终状态设置为Status = 2 ，最后 交由人工解决处理此类问题（或者把消息转储到失败表中）。



## 9.2数据库文件

```sql
-- ----------------------------
-- Table structure for broker_message_log
-- ----------------------------
DROP TABLE IF EXISTS `broker_message_log`;
CREATE TABLE `broker_message_log` (
  `message_id` varchar(255) NOT NULL COMMENT '消息唯一ID',
  `message` varchar(4000) NOT NULL COMMENT '消息内容',
  `try_count` int(4) DEFAULT '0' COMMENT '重试次数',
  `status` varchar(10) DEFAULT '' COMMENT '消息投递状态 0投递中,1投递成功,2投递失败',
  `next_retry` timestamp NOT NULL DEFAULT '0000-00-00 00：00：00' ON UPDATE CURRENT_TIMESTAMP COMMENT '下一次重试时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00：00：00' ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00：00：00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `message_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2018091102 DEFAULT CHARSET=utf8;
```





# 10 消息幂等性保障

## 10.1概述

幂等性指一次和多次请求某一个资源,对于资源本身应该具有同样的结果。也就是说,其任意多次执行对资源本身所产生的影响均与一次执行的影响相同。(消费多条相同的消息,得到与消费该消息一次相同的结果)

注意：解决的是重复消费问题。向MQ发送消息后，因网络原因导致又重复发送了一次消息。⚠️



## 10.2乐观锁机制

**消息幂等性保障 乐观锁机制**

生产者发送消息：

```sql
id=1,money=500,version=1
```

消费者接收消息：

```sql
id=1,money=500,version=1
id=1,money=500,version=1
```

消费者需要保证幂等性：第一次执行SQL语句：

```sql
第一次执行：version=1
update account set money = money - 500 , version = version + 1
where id = 1 and version = 1
```

消费者需要保证幂等性：第二次执行SQL语句：

```sql
第二次执行：version=2
update account set money = money - 500 , version = version + 1
where id = 1 and version = 1
```





# 11RabbitMQ集群搭建

摘要：实际生产应用中都会采用消息队列的集群方案，如果选择RabbitMQ那么有必要了解下它的集群方案原理

## 11.1集群方案的原理

RabbitMQ这款消息队列中间件产品本身是基于Erlang编写，Erlang语言天生具备分布式特性（通过同步Erlang集群各节点的magic cookie来实现）。因此，RabbitMQ天然支持Clustering。这使得RabbitMQ本身不需要像ActiveMQ、Kafka那样通过ZooKeeper分别来实现HA方案和保存集群的元数据。集群是保证可靠性的一种方式，同时可以通过水平扩展以达到增加消息吞吐量能力的目的。



## 11.2单机多实例部署

由于某些因素的限制，有时候你不得不在一台机器上去搭建一个rabbitmq集群，这个有点类似zookeeper的单机版。真实生成环境还是要配成多机集群的。有关怎么配置多机集群的可以参考其他的资料，这里主要论述如何在单机中配置多个rabbitmq实例。

主要参考官方文档：https://www.rabbitmq.com/clustering.html 

首先确保RabbitMQ运行没有问题：

```sql
systemctl start rabbitmq-server.service
```

```sql
systemctl status rabbitmq-server.service
```

停止rabbitmq服务：

```sql
systemctl stop rabbitmq-server.service
```

启动三个节点做集群演示：

由于web管理插件端口占用,所以还要指定其web插件占用的端口号。

```sql
RABBITMQ_NODE_PORT=5672 RABBITMQ_NODENAME=rabbit1 RABBITMQ_SERVER_START_ARGS="-rabbitmq_management listener [{port,15672}]"  rabbitmq-server -detached
```

```sql
RABBITMQ_NODE_PORT=5673 RABBITMQ_NODENAME=rabbit2 RABBITMQ_SERVER_START_ARGS="-rabbitmq_management listener [{port,15673}]"  rabbitmq-server -detached
```

```sql
RABBITMQ_NODE_PORT=5674 RABBITMQ_NODENAME=rabbit3 RABBITMQ_SERVER_START_ARGS="-rabbitmq_management listener [{port,15674}]"  rabbitmq-server -detached
```

启动三个节点后，分别访问三个节点，后台管理页面，看看是否OK

停止服务命令（不要执行这三个命令，否则还得执行上面三个命令）⚠️：

```sql
#不要执行
rabbitmqctl -n rabbit1 stop
```

```sql
#不要执行
rabbitmqctl -n rabbit2 stop
```

```sql
#不要执行
rabbitmqctl -n rabbit3 stop
```

rabbit1操作作为主节点：

```sql
[root@centos ~]# rabbitmqctl -n rabbit1 stop_app 
Stopping rabbit application on node rabbit1@centos ...
[root@centos ~]# rabbitmqctl -n rabbit1 reset
Resetting node rabbit1@centos ...
[root@centos ~]# rabbitmqctl -n rabbit1 start_app
Starting node rabbit1@centos ...
 completed with 3 plugins.
```

rabbit2操作为从节点：

```sql
[root@centos ~]# rabbitmqctl -n rabbit2 stop_app
Stopping rabbit application on node rabbit2@centos ...
[root@centos ~]# rabbitmqctl -n rabbit2 reset
Resetting node rabbit2@centos ...
[root@centos ~]# rabbitmqctl -n rabbit2 join_cluster rabbit1
Clustering node rabbit2@centos with rabbit1
[root@centos ~]# rabbitmqctl -n rabbit2 start_app
Starting node rabbit2@centos ...
 completed with 3 plugins.
[root@centos ~]# 
```

rabbit3操作为从节点：

```sql
[root@centos ~]# rabbitmqctl -n rabbit3 stop_app
Stopping rabbit application on node rabbit3@centos ...
[root@centos ~]# rabbitmqctl -n rabbit3 reset
Resetting node rabbit3@centos ...
[root@centos ~]# rabbitmqctl -n rabbit3 join_cluster rabbit1
Clustering node rabbit3@centos with rabbit1
[root@centos ~]# rabbitmqctl -n rabbit3 start_app
Starting node rabbit3@centos ...
 completed with 3 plugins.
[root@centos ~]# 
```

查看集群状态：

```shell
[root@centos ~]# rabbitmqctl -n rabbit1 cluster_status
Cluster status of node rabbit1@centos ...
Basics

Cluster name: rabbit1@centos

Disk Nodes

rabbit1@centos
rabbit2@centos
rabbit3@centos

Running Nodes

rabbit1@centos
rabbit2@centos
rabbit3@centos

Versions

rabbit1@centos: RabbitMQ 3.8.1 on Erlang 21.3.8.9
rabbit2@centos: RabbitMQ 3.8.1 on Erlang 21.3.8.9
rabbit3@centos: RabbitMQ 3.8.1 on Erlang 21.3.8.9

Alarms

(none)

Network Partitions

(none)

Listeners

Node: rabbit1@centos, interface: [::], port: 25672, protocol: clustering, purpose: inter-node and CLI tool communication
Node: rabbit1@centos, interface: [::], port: 5672, protocol: amqp, purpose: AMQP 0-9-1 and AMQP 1.0
Node: rabbit1@centos, interface: [::], port: 15672, protocol: http, purpose: HTTP API
Node: rabbit2@centos, interface: [::], port: 25673, protocol: clustering, purpose: inter-node and CLI tool communication
Node: rabbit2@centos, interface: [::], port: 5673, protocol: amqp, purpose: AMQP 0-9-1 and AMQP 1.0
Node: rabbit2@centos, interface: [::], port: 15673, protocol: http, purpose: HTTP API
Node: rabbit3@centos, interface: [::], port: 25674, protocol: clustering, purpose: inter-node and CLI tool communication
Node: rabbit3@centos, interface: [::], port: 5674, protocol: amqp, purpose: AMQP 0-9-1 and AMQP 1.0
Node: rabbit3@centos, interface: [::], port: 15674, protocol: http, purpose: HTTP API

Feature flags

Flag: drop_unroutable_metric, state: enabled
Flag: empty_basic_get_metric, state: enabled
Flag: implicit_default_bindings, state: enabled
Flag: quorum_queue, state: enabled
Flag: virtual_host_metadata, state: enabled
[root@centos ~]# 
```

创建用户：

```shell
rabbitmqctl -n rabbit1 add_user admin admin
```

```shell
rabbitmqctl -n rabbit1 set_user_tags admin administrator
```

```shell
rabbitmqctl -n rabbit1 change_password admin 123456
```

使用浏览器登录web管理页面，Admin页面点击用户，Set Permission设置权限⚠️



## 11.3集群管理命令

将节点加入指定集群中。在这个命令执行前需要停止RabbitMQ应用并重置节点。

```shell
rabbitmqctl join_cluster {cluster_node} [–ram]
```

显示集群的状态。

```shell
rabbitmqctl cluster_status
```

修改集群节点的类型。在这个命令执行前需要停止RabbitMQ应用。

```shell
rabbitmqctl change_cluster_node_type {disc|ram}
```

将节点从集群中删除，允许离线执行。

```shell
rabbitmqctl forget_cluster_node [–offline]
```

在集群中的节点应用启动前咨询clusternode节点的最新信息，并更新相应的集群信息。这个和join_cluster不同，它不加入集群。考虑这样一种情况，节点A和节点B都在集群中，当节点A离线了，节点C又和节点B组成了一个集群，然后节点B又离开了集群，当A醒来的时候，它会尝试联系节点B，但是这样会失败，因为节点B已经不在集群中了。rabbitmqctl update_cluster_nodes -n A C可以解决这种场景，让A直接去连C。

```shell
rabbitmqctl update_cluster_nodes {clusternode}
```

取消队列queue同步镜像的操作。

```shell
rabbitmqctl cancel_sync_queue [-p vhost] {queue}
```

设置集群名称。集群名称在客户端连接时会通报给客户端。Federation和Shovel插件也会有用到集群名称的地方。集群名称默认是集群中第一个节点的名称，通过这个命令可以重新设置。

```shell
rabbitmqctl set_cluster_name {name}
```



## 11.4RabbitMQ镜像集群配置

完成RabbitMQ默认集群模式，但并不保证队列的高可用性(队列消息无法集群内同步)，尽管交换机、绑定这些可以复制到集群里的任何一个节点，但是队列内容不会复制。

虽然该模式解决一项目组节点压力，但队列节点宕机直接导致该队列无法应用，只能等待重启，所以要想在队列节点宕机或故障也能正常应用，就要复制队列内容到集群里的每个节点，必须要创建镜像队列。

镜像队列是基于普通的集群模式的，然后再添加一些策略，所以你还是得先配置普通集群，然后才能设置镜像队列，我们就以上面的集群接着做。设置的镜像队列可以通过开启的网页的管理端Admin->Policies，也可以通过命令。

**在web管理页面添加策略：**

Admin页面，点击Polices，点击Add / update a policy添加

- Name:策略名称
- Pattern：匹配的规则，如果是匹配所有的队列，是^
- Definition:使用ha-mode模式中的all，也就是同步所有匹配的队列。问号链接帮助文档。

|    表单name | 表单内容                               |
| ----------: | -------------------------------------- |
|       Name: | my_ha（随便定义）                      |
|    Pattern: | ^                                      |
|   Apply to: | Exchanges and queues  exchanges Queues |
|   Priority: |                                        |
| Definition: | ha-mode=all                            |

配置完成后，可创建队列查看其是否有+2的标志。



## 11.5负载均衡-HAProxy

HAProxy提供高可用性、负载均衡以及基于TCP和HTTP应用的代理，支持虚拟主机，它是免费、快速并且可靠的一种解决方案,包括Twitter，Reddit，StackOverflow，GitHub在内的多家知名互联网公司在使用。HAProxy实现了一种事件驱动、单一进程模型，此模型支持非常大的并发连接数。

官方网站：https://www.haproxy.org/ 

### 11.5.1安装HAProxy

下载依赖包

```shell
yum install gcc vim wget
```

上传haproxy源码包; -C解压到指定的目录

```shell
tar -zxvf haproxy-2.3.14.tar.gz -C /usr/local
```

进入目录、进行编译、安装

```shell
cd /usr/local/haproxy-2.3.14
```

make 表示编译；TARGET=linux31 表示CentOS7系统；PREFIX=/usr/local/haproxy指定安装路径

TARGET=linux310，内核版本，使用uname -r查看内核，如：3.10.0-514.el7，此时该参数就为linux310

```shell
make TARGET=linux310 PREFIX=/usr/local/haproxy
```

```shell
make install PREFIX=/usr/local/haproxy
```

```shell
mkdir /etc/haproxy
```

添加用户组：-r 创建一个系统组；-g 组ID

```shell
groupadd -r -g 149 haproxy
```

添加用户：-g 新账户组的名称；-r 创建一个系统用户；-s 新用户的登录shell; -u 新账户的用户ID

```shell
useradd -g haproxy -r -s /sbin/nologin -u 149 haproxy
```

创建haproxy配置文件

```shell
vim /etc/haproxy/haproxy.cfg
```



### 11.5.2配置HAProxy

配置文件路径：/etc/haproxy/haproxy.cfg

```sh
#全局配置
global
    #日志输出配置，所有日志都记录在本机，通过local0输出
    log 127.0.0.1 local0 info
    #最大连接数
    maxconn 5120
    #改变当前的工作目录
    chroot /usr/local/haproxy
    #以指定的UID运行haproxy进程
    uid 99
    #以指定的GID运行haproxy进程
    gid 99
    #以守护进程方式运行haproxy
    daemon
    quiet
    nbproc 20
    #当前进程PID文件
    pidfile /var/run/haproxy.pid
#默认配置
defaults
    #应用全局的日志配置
    log global
    #默认的模式mode{tcp|http|health}
    mode tcp
    #日志类别
    option tcplog
    #不记录检查检查日志信息
    option dontlognull
    #3次失败则认为服务不可用
    retries 3
    option redispatch
    #每个进程可用的最大连接数
    maxconn 2000   
#绑定配置
listen rabbitmq_cluster
    bind *:5677
    #配置TCP模式
    mode tcp
    #balance url_param userid
    #balance url_param session_id check_post 64
    #balance hdr(User-Agent)
    #balance hdr(host)
    #balance hdr(Host) use_domain_only
    #balance rdp-cookie
    #balance leastconn
    #balance source //ip
    #简单的轮询，默认三台机器轮询
    balance roundrobin
    #server rabbit1 定义服务内部标识，
    #127.0.0.1:5672 服务连接IP和端口，
    #check inter 5000 定义每隔多少毫秒检查服务是否可用，
    #rise 2 服务故障后需要多少次检查才能被再次确认可用，
    #fall 2 经历多次失败的检查检查后，haproxy才会停止使用此服务
    #weight 1 定义服务权重，要换成自己的IP
    server rabbit1 192.168.6.100:5672 check inter 5000 rise 2 fall 2 weight 1
    server rabbit2 192.168.6.100:5673 check inter 5000 rise 2 fall 2 weight 1
    server rabbit3 192.168.6.100:5674 check inter 5000 rise 2 fall 2 weight 1
#haproxy监控页面地址
listen stats
    bind 192.168.6.100:8100
    mode http
    option httplog
    stats enable
    stats uri /rabbitmq-stats
    stats refresh 5s
```

启动HAproxy负载

```shell
/usr/local/haproxy/sbin/haproxy -f /etc/haproxy/haproxy.cfg
```

查看haproxy进程状态，根据kill掉进程结束服务

```shell
ps -ef | grep haproxy
```

访问如下地址对mq节点进行监控

springboot yml文件中访问mq集群地址：

```yaml
spring: 
  rabbitmq: 
    host: 192.168.6.100
    port: 5677
    username: admin
    password: 123456
virtual-host: /
#addresses: 192.168.6.100:5672,192.168.6.100:5673,192.168.6.100:5674
```

最后，使用浏览器访问IP:8100//rabbitmq-stats查看web端负载均衡页面。
