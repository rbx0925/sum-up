# 1 Maven入门

## 1.1Maven简介

Maven是一款自动化构建工具，专注服务于Java平台的项目构建和依赖管理。在JavaEE开发的历史上构建工具的发展也经历了一系列的演化和变迁：Make→Ant→Maven→Gradle→其他……

构建的概念：

构建并不是创建，构建是一个过程，就是以我们编写的Java代码、框架配置文件、国际化等其他资源文件、JSP页面和图片等静态资源作为“原材料”，去“生产”出一个可以运行的项目的过程。

构建的环节：

1.  清理：删除以前的编译结果，为重新编译做好准备。
2.  编译：将Java源程序编译为字节码文件。
3.  测试：针对项目中的关键点进行测试，确保项目在迭代开发过程中关键点的正确性。
4.  报告：在每一次测试后以标准的格式记录和展示测试结果。
5.  打包：将一个包含诸多文件的工程封装为一个压缩文件用于安装或部署。Java工程对应jar包，Web工程对应war包。
6.  安装：在Maven环境下特指将打包的结果——jar包或war包安装到本地仓库中。
7.  部署：将打包的结果部署到远程仓库或将war包部署到服务器上运行。



## 1.2Maven的功能

- 获取第三方 iar 包
- 添加第三方 iar包
- iar 包之间的依赖关系
- 处理 iar 包之间的冲突
- 将项目拆分成多个工程模块
- 实现项目的分布式部署



## 1.3Maven的配置

### 1.3.1Mac下的环境配置

mac下maven的下载安装：https://www.bilibili.com/video/BV13K4y1X7y9

mac下maven的环境参数配置：

编辑当前用户的环境配置文件：vim ~/.bash_profile

```sh
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home
export JAVA_HOME

M2_HOME=/Users/shuaigouzi/environment/apache-maven-3.8.6
export M2_HOME

PATH=${PATH}:${JAVA_HOME}/bin:${M2_HOME}/bin
export PATH
```



### 1.3.2修改maven编译版本

在Maven的核心配置文件conf/settings.xml文件中添加以下配置

```xml
<profile>
  <id>jdk-1.8</id>
  <activation>
	<activeByDefault>true</activeByDefault>
	<jdk>1.8</jdk>
  </activation>
  <properties>
	  <maven.compiler.source>1.8</maven.compiler.source>
	  <maven.compiler.target>1.8</maven.compiler.target>
	  <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
  </properties>
</profile>
```



### 1.3.3配置本地仓库

Maven默认的本地仓库：~\.m2\repository目录

在Maven的核心配置文件conf/settings.xml文件中添加以下配置

```xml
<localRepository>D:\maven\localRepository</localRepository>
```



### 1.3.4配置阿里云镜像

在Maven的核心配置文件conf/settings.xml文件中配置<mirrors></mirrors>标签

```xml
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>
```



### 1.3.5配置IDEA中的Maven

在IDEA的Build, Execution, Deployment › Build Tools › Maven中可设置Maven配置文件路径

或者对IDEA内置Maven的settings.xml文件进行修改



## 1.4Maven的使用步骤

第一步：创建Maven约定的目录结构，或使用IDEA创建Maven项目

- model/src/main/java
- model/src/main/resource
- model/src/test/java
- model/pom.xml（该文件内的信息是创建Maven自动生成的）

第二步：在pom.xml中添加依赖

- 使用dependencies标签添加依赖
- 添加junit所依赖的jar包的坐标

第三步：

- 在model/src/main/java中编写主程序
- 在model/src/test/java中编写测试程序



## 1.5Maven的入门程序

第一步：创建Maven约定的目录结构，或使用IDEA创建Maven项目

第二步：在pom.xml中添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.maven</groupId>
    <artifactId>maven1</artifactId>
    <version>1.0</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>
  
  	<!--添加junit依赖，相当于为项目导入了junit的jar包-->
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

第三步：

- 在model/src/main/java中编写主程序

  ```java
  public class Hello {
    public String sayHello(String name){
      return "Hello "+name+"!";
    }
  }
  ```

- 在model/src/test/java中编写测试程序

  ```java
  import org.junit.Test;
  
  public class HelloTest {
  
    @Test
    public void testHello(){
      Hello hello = new Hello();
      String maven = hello.sayHello("Maven");
      System.out.println(maven);
    }
  }
  ```





# 2 Maven的核心概念

Maven的核心概念：POM、约定的目录结构、插件和目标、生命周期、仓库、坐标、依赖、继承、聚合



## 2.1POM

Project Object Model：项目对象模型。将Java工程的相关信息封装为对象作为便于操作和管理的模型。Maven工程的核心配置。

可以说学习Maven就是学习pom.xml文件中的配置。



## 2.2约定的目录结构

- 模块名
  - src
    - main 存放主程序代码（将来部署到服务器上的程序）
      - java 存放java代码
      - resources 存放配置文件
    - test 存放测试程序代码
      - java 存放java代码
    - pom.xml maven项目的核心配置文件pom.xml(pom:项目对象模型，jar包的寻找和添加)



## 2.3插件和目标

- Maven的核心程序是没有构建环节的功能的
- 在使用构建的环节时，去本地仓库寻找插件，让插件去进行构建
- 一个插件有可能存在多个构建环节的功能，一个功能就是一个目标



## 2.4生命周期

Maven位置的生命周期就是项目构建的过程

Maven的插件提供了三套独立的生命周期：

- Clean Lifecycle
- Default Lifecycle ★
  - compile
  - test-compile
  - test
  - package
  - install
- Site Lifecycle

执行生命周期中的一个命令，会将他之前的命令一同执行



## 2.5仓库★

本地仓库：

- 开发者电脑上的一个目录

远程仓库：

- 中央仓库（最权威的， 所有的开发人员都共享使用的一个集中的仓库）
- 中央仓库的镜像（就是中央仓库的备份， 在各大洲，重要的城市都是镜像）
- 私服(一个公司，或者一个企业自己搭建的仓库)

仓库中的内容：

- 插件(构建环节需要的插件)
- 第三方jar包
- 我们自己项目安装的jar包

查询步骤：

- 开发人员需要使用mysql驱动--->maven首先查本地仓库--->私服--->镜像--->中央仓库



## 2.6坐标★

坐标：唯一值， 在互联网中唯一标识一个项目的

坐标的命名方式：

- <groupId>公司域名的倒写</groupId>
- <artifactId>自定义项目名称</artifactId>
- <version>自定版本号</version>

坐标的作用：

- maven通过pom.xml文件中配置的依赖坐标从仓库中找到jar包

- 项目使用install安装到本地仓库后，jar包的路径为：**g/a/v/a-v.tar**
- 其他项目将该项目加入到自身依赖后：
  - 若通过IDEA运行则是传统的导包
  - 若通过生命周期命令运行，则去本地仓库根据坐标找jar包



## 2.7依赖★

依赖传递性：

- 在GAV标签下方设置依赖范围  <scope>test</scope>
  - test：只能在测试程序中使用，**不会跟随到服务器上**
  - compile：主程序和测试程序都可以使用，**默认值只有他具有传递性**
  - provided：主程序和测试程序都可以使用，**不会跟随到服务器上**

依赖冲突（具备传递性才会有依赖冲突）

- 第一种情况：（路径长度不同）
  - 直接依赖和间接依赖冲突时，会使用直接依赖
  - 原则：**路径短者优先**（就近原则）
- 第二种情况：（路径长度相同）
  - 两个依赖路径长度相同时，**先配置依赖者优先**
- 完美解决方案：通过依赖排除来解决**（推荐使用）**⚠️

依赖排除：（排除重复的间接依赖，解决依赖冲突问题，虽然他具有传递性，但是我不想要）

```xml
<dependency>
  <groupId>com.atguigu.maven</groupId>
  <artifactId>OurFriends</artifactId>
  <version>1.0-SNAPSHOT</version>
  <!--依赖排除，只写g和v即可，排除所有，无法排除指定版本，可以写多个-->
  <exclusions>
    <exclusion>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```

统一管理jar包版本：

```xml
<!--统一管理当前模块的jar包的版本-->
<properties>
    <spring.version>5.3.1</spring.version>
</properties>

......
  
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>${spring.version}</version>
</dependency>
```



## 2.8继承★

非compile范围的依赖信息是不能在“依赖链”中传递的，但是继承可传递所有范围的依赖信息

继承是单向的关系，子模块只需要明确继承于哪个父模块即可

使用继承的步骤：

- 第一步：创建父工程：
  - 父工程的打包方式为pom，因为父级内部没有代码，只用来管理
  - 父工程只需要保留pom.xml文件即可

```xml
<groupId>com.atguigu.maven</groupId>
<artifactId>Parent</artifactId>
<version>1.0-SNAPSHOT</version>
<!--父工程的打包方式为pom-->
<packaging>pom</packaging>
```

- 第二步：在子工程中引用父工程
  - parent标签里写父工程的GAV坐标
  - relativePath标签：指定从当前pom.xml文件出发寻找父工程的pom.xml文件的相对路径

```xml
<parent>
    <groupId>com.atguigu.maven</groupId>
    <artifactId>Parent</artifactId>
    <version>1.0-SNAPSHOT</version>
</parent>
```

在父工程中管理依赖：

- 将Parent项目中的dependencies标签，用dependencyManagement标签括起来

```xml
<!--依赖管理-->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

- 在子项目中重新指定需要的依赖，不需要写版本号versiom和范围scope

```xml
<!--引用管理的依赖-->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
</dependency>
```

子项目的<groupId>和<version>与父工程一致时，可以不写，因为会继承父工程的。⚠️

```xml
<parent>
    <groupId>com.atguigu.maven</groupId>
    <artifactId>Parent</artifactId>
    <version>1.0-SNAPSHOT</version>
</parent>
<!--子项目只写artifactId，而groupId和version使用父工程的-->
<artifactId>junit</artifactId>
```

注意：不在父工程中管理依赖，直接使用dependencies标签存放依赖，会导致子项目继承全部依赖



## 2.9聚合★

一个大项目包含多个子项目，对父项目进行clear、compile等命令，是对所有子项目进行clear、compile命令。但是如果对一个子项目进行maven操作，不影响其他子项目

不存在继承时父工程的配置：

- 只需要配置聚合代码:

```xml
<!--配置聚合-->
<modules>
    <!--从当前文件触发通过相对路径去寻找其他需要聚合的模块-->
    <module>../OurFriend</module>
    <module>../Hello</module>
    <module>../HelloFriend</module>
</modules>
```

存在继承时父工程的配置：

- 需要配置聚合代码和在有继承关系的子工程内添加relativePath标签:

```xml
<!--配置聚合-->
<modules>
    <!--从当前文件触发通过相对路径去寻找其他需要聚合的模块-->
    <module>../OurFriend</module>
    <module>../Hello</module>
    <module>../HelloFriend</module>
</modules>
```

- 在子工程继承的位置添加relativePath标签:

```xml
<parent>
    <groupId>com.atguigu.maven</groupId>
    <artifactId>Parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!--若聚合的工程有父级，则指定从当前pom.xml文件出发寻找父工程的pom.xml文件的相对路径-->
    <relativePath>../Parent/pom.xml</relativePath>
</parent>
```





# 3 Maven拓展

## 3.1创建Web项目

第一步：创建一个空Model

第二步：打包类型设置为war

```xml
<packaging>war</packaging>
```

第三步：在项目结构中添加web.xml文件

- /Users/shuaigouzi/javaProject/javaEEProject/ssm/web03/src/main/webapp/WEB-INF/web.xml

- **/src/main/webapp**是添加的路径，用于控制webapp目录的生成位置

  

## 3.2Maven搜索依赖站点

- http://mvnrepository.com
- http://search.maven.org





