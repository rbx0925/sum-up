#  1 Spring入门

## 1.1Spring简介

Spring是一个轻量级的控制反转(IoC)和面向切面(AOP)的容器框架。

Spring最初的出现是为了解决EJB臃肿的设计，以及难以测试等问题。

Spring为简化开发而生，让程序员只需关注核心业务的实现，尽可能的不再关注非业务逻辑代码（事务控制，安全日志等）。

**Spring的优良特性：**

- **非侵入式：**

基于Spring开发的应用中的对象可以不依赖于Spring的API。

- **控制反转：**

  IOC(Inversion of Control)，指的是将对象的创建权交给Spring去创建。使用Spring之前，对象的创建都是由我们自己在代码中new创建。而使用Spring之后。对象的创建都是由给了Spring框架。

- **依赖注入：**

  DI(Dependency Injection)，是指依赖的对象不需要手动调用setXXX方法去设置，而是通过配置赋值。

- **面向切面编程：**

  AOP(Aspect Oriented Programming)，在不修改源代码的基础上进行功能扩展。

- **容器：**

  Spring是一个容器，因为它包含并且管理应用对象的生命周期。

  

## 1.2Spring原则和思想

- **依赖倒置原则DIP**

  依赖倒置原则(Dependence Inversion Principle)，简称DIP，主要倡导面向抽象编程，面向接口编程，不要面向具体编程，让**上层**不再依赖**下层**，下面改动了，上面的代码不会受到牵连。这样可以大大降低程序的耦合度，耦合度低了，扩展力就强了，同时代码复用性也会增强。（**软件七大开发原则都是在为解耦合服务**）

- **控制反转IoC思想**

  控制反转（Inversion of Control，缩写为IoC），是面向对象编程中的一种设计思想，可以用来降低代码之间的耦合度，符合依赖倒置原则。

  控制反转的核心是：**将对象的创建权交出去，将对象和对象之间关系的管理权交出去，由第三方容器来负责创建与维护**。

  控制反转常见的实现方式：依赖注入（Dependency Injection，简称DI）

  通常，依赖注入的实现由包括两种方式：

  - **set方法注入**

  - **构造方法注入**

  而Spring框架就是一个实现了IoC思想的框架。

  IoC可以认为是一种**全新的设计模式**，但是理论和时间成熟相对较晚，并没有包含在GoF中。（GoF指的是23种设计模式）

- **OCP开闭原则**

  对扩展开放，对修改关闭，即拓展系统功能时却没有修改代码就符合OCP原则，比如使用xml文件来管理数据库信息。



## 1.3Spring八大模块

- **Spring Core模块**

  这是Spring框架最基础的部分，它提供了依赖注入（DependencyInjection）特征来实现容器对Bean的管理。核心容器的主要组件是 BeanFactory，BeanFactory是工厂模式的一个实现，是任何Spring应用的核心。它使用IoC将应用配置和依赖从实际的应用代码中分离出来。

- **Spring Context模块**

  如果说核心模块中的BeanFactory使Spring成为容器的话，那么上下文模块就是Spring成为框架的原因。

  这个模块扩展了BeanFactory，增加了对国际化（I18N）消息、事件传播、验证的支持。另外提供了许多企业服务，例如电子邮件、JNDI访问、EJB集成、远程以及时序调度（scheduling）服务。也包括了对模版框架例如Velocity和FreeMarker集成的支持

- **Spring AOP模块**

  Spring在它的AOP模块中提供了对面向切面编程的丰富支持，Spring AOP 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。通过使用 Spring AOP，不用依赖组件，就可以将声明性事务管理集成到应用程序中，可以自定义拦截器、切点、日志等操作。

- **Spring DAO模块**

  提供了一个JDBC的抽象层和异常层次结构，消除了烦琐的JDBC编码和数据库厂商特有的错误代码解析，用于简化JDBC。

- **Spring ORM模块**

  Spring提供了ORM模块。Spring并不试图实现它自己的ORM解决方案，而是为几种流行的ORM框架提供了集成方案，包括Hibernate、JDO和iBATIS SQL映射，这些都遵从 Spring 的通用事务和 DAO 异常层次结构。

- **Spring Web MVC模块**

  Spring为构建Web应用提供了一个功能全面的MVC框架。虽然Spring可以很容易地与其它MVC框架集成，例如Struts，但Spring的MVC框架使用IoC对控制逻辑和业务对象提供了完全的分离。

- **Spring WebFlux模块**

  Spring Framework 中包含的原始 Web 框架 Spring Web MVC 是专门为 Servlet API 和 Servlet 容器构建的。反应式堆栈 Web 框架 Spring WebFlux 是在 5.0 版的后期添加的。它是完全非阻塞的，支持反应式流(Reactive Stream)背压，并在Netty，Undertow和Servlet 3.1+容器等服务器上运行。

- **Spring Web模块**

  Web 上下文模块建立在应用程序上下文模块之上，为基于 Web 的应用程序提供了上下文，提供了Spring和其它Web框架的集成，比如Struts、WebWork。还提供了一些面向服务支持，例如：实现文件上传的multipart请求。



## 1.4Spring使用步骤

官网：https://spring.io/

最新正式发布版下载地址：https://repo.spring.io/release/org/springframework/spring/

- 创建spring module
- 导入Spring框架的jar包
- 创建测试类

- 创建spring配置文件
- 编辑spring配置文件，让Spring的容器去管理这个类的对象
  - 创建对象
  - 属性赋值
  - ...
- 测试
  - 创建IOC容器(读取配置文件)
  - 从容器内获取目标对象
  - 使用



## 1.5Spring入门程序★

- 第一步：创建spring module

- 第二步：导入spring框架的jar包

  ```xml
  <!--导入spring-context-->
  <dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context</artifactId>
  <version>5.3.1</version>
  </dependency>
  <!--导入junit4.12-->
  <dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.12</version>
  <scope>test</scope>
  </dependency>
  ```

- 第三步：创建测试类

- 第四步：配置spring核心文件 applicationContext.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  		<!--让spring来管理HelloWord类-->
      <bean id="helloWord" class="com.spring1.pojo.HelloWord">
        	<!--属性注入-->
          <property name="name" value="tom"/>
      </bean>
  </beans>
  ```

- 第五步：测试

  ```java
  public class HelloWordTest {
      @Test
      public void test01(){
          //从类的根路径下加载配置文件，将配置文件内的bean都实例化，放到IoC容器内
          ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
          //从IoC容器内取出Bean对象
          HelloWord hello = ioc.getBean(HelloWord.class);
          hello.sayHello();
      }
  }
  ```

  

## 1.6控制反转IoC和依赖注入DI

spring底层IOC的实现借助于：**XML解析+工厂模式+反射机制**

**spring通过反射获取无参构造方法创建对象，然后存入底层Map集合<beanId, beanObject>里**

### 1.6.1控制反转IoC

- **IoC容器的创建**
  - 在创建Bean之前，首先需要创建IOC容器。Spring提供了IOC容器的两种实现方式：
    - **BeanFactory：**IOC容器的基本实现，是Spring内部的使用接口，是面向Spring本身的，不是提供给开发人员使用的。
    - **ApplicationContext：**BeanFactory的子接口，提供了更多高级特性。面向Spring的使用者，几乎所有场合都使用ApplicationContext而不是底层的BeanFactory。

- **ApplicationContext的主要实现类**

  ClassPathXmlApplicationContext：对应类路径下的XML格式的配置文件

  FileSystemXmlApplicationContext：对应文件系统中的XML格式的配置文件

  ConfigurableApplicationContext：是ApplicationContext的子接口，包含一些扩展方法refresh()和**close()**

  WebApplicationContext：专门为WEB应用而准备的，它允许从相对于WEB根目录的路径中完成初始化工作。

- **getBean获取对象的方式**

1. 根据id去获得，要求xml中不能有重复id的bean，需要强转
2. 根据类型去获得，要求xml中不能有重复类型的bean ⚠️
3. 根据id和类型获得



### 1.6.2依赖注入DI

**依赖注入实现了控制反转的思想，Spring通过依赖注入的方式来完成Bean管理的。**

Bean管理：Bean对象的创建，以及Bean对象中属性的赋值（Bean对象之间关系的维护）。

依赖注入的方式分为两种：**基于set方法和基于有参构造方法**

- **基于set**

  - 在xml文件中，通过property标签，基于set方法为其属性赋值。

    - Property标签的name属性：指定赋值的set方法，格式为：set方法去掉set，然后首字母小写

    - Property标签的ref属性：注入引用数据类型，值为bean的id

    - Property标签的value属性：注入简单数据类型，直接传值即可

    - 简单数据类型：(了解)

      通过org.springframework.beans.BeanUtils.isSimpleValueType方法作为判断简单类型的依据

      - **基本数据类型**
      - **基本数据类型对应的包装类**
      - **String或其他的CharSequence子类**
      - **Number子类**
      - **Date子类**
      - **Enum子类**
      - **URI**
      - **URL**
      - **Temporal子类**
      - **Locale**
      - **Class**
      - **另外还包括以上简单值类型对应的数组类型。**

- **基于constructor：(了解)**

  - 在xml文件中，通过constructor-arg标签，基于**有参构造**方法为其属性赋值。
    - constructor-arg标签的index属性：为第几个参数，从0开始
    - constructor-arg标签的name属性：可指定赋值名称进行赋值
    - constructor-arg标签type属性：可指定类型进行赋值
    - 都省略自动根据type判断注入





# 2 XML的方式管理Bean

## 2.1Bean的创建

让Spring的IOC容器帮我们创建Bean，只需要在Spring的配置文件中通过bean标签配置即可。

### 2.1.1通过标签创建

- bean标签中常用属性说明：
  - **Id属性：**给IOC容器中的bean设置的唯一标识。
  - **class属性：**配置让IOC容器管理的类的全类名，Spring会利用反射技术通过类的无参构造器创建对象。



### 2.1.2FactoryBean

Spring中有两种类型的bean，一种是普通bean，另一种是工厂bean，即FactoryBean。

普通bean设置的类型就是返回的类型；工厂bean设置的类型可以和返回的类型不一样，其返回的类型通过该工厂bean的getObject方法指定。

Spring为Bean提供了多种实例化方式，通常包括4种方式，一种普通bean，三种FactoryBean：

- 第一种：通过构造方法实例化
- 第二种：通过简单工厂模式实例化
- 第三种：通过factory-bean实例化
- 第四种：通过FactoryBean接口实例化

四种方式的实现：

- 第一种：直接通过构造方法实例化

  - 在spring配置文件中配置类的全路径，spring底层基于类的无参数构造方法实例化

  - 先调用无参构造创建，再调用set注入赋值。

    ```xml
    <bean id="userBean" class="com.powernode.spring6.bean.User"/>
    ```

- 第二种：通过简单工厂模式实例化

  1. 先定义一个bean类

     ```java
     public class Vip {
     }
     ```

  2. 再编写工厂类，**简单工厂设计模式中创建bean的方法是静态的**

     ```java
     public class VipFactory {
         public static Vip get(){
             return new Vip();
         }
     }
     ```

  3. 在Spring配置文件中指定创建该Bean的方法（使用factory-method属性指定）

     ```xml
     <!--需要告诉spring框架，调用哪个类的哪个方法获取bean-->
     <bean id="vipBean" class="com.powernode.spring6.bean.VipFactory" factory-method="get"/>
     ```

  4. 编写测试程序

     ```java
     @Test
     public void testSimpleFactory(){
         ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
         Vip vip = applicationContext.getBean("vipBean", Vip.class);
         System.out.println(vip);
     }
     ```

- 第三种：通过factory-bean实例化

  **这种方式本质上是：通过工厂方法模式进行实例化。**

  1. 先定义一个bean类

     ```java
     public class Order {
     }
     ```

  2. 再编写具体工厂类，**创建bean的方法是实例方法**

     ```java
     public class OrderFactory {
         public Order get(){
             return new Order();
         }
     }
     ```

  3. 在Spring配置文件中指定factory-bean以及factory-method

     ```xml
     <!--第一次配置工厂类对象，第二次配置告诉spring通过工厂对象的哪个方法获取bean-->
     <bean id="orderFactory" class="com.powernode.spring6.bean.OrderFactory"/>
     <bean id="orderBean" factory-bean="orderFactory" factory-method="get"/>
     ```

  4. 编写测试程序

     ```java
     @Test
     public void testSelfFactoryBean(){
         ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
         Order orderBean = applicationContext.getBean("orderBean", Order.class);
         System.out.println(orderBean);
     }
     ```

- 第四种：通过FactoryBean接口实例化

  第三种方式中，factory-bean是我们自定义的，factory-method也是我们自己定义的。

  实现FactoryBean接口之后，factory-bean会自动指向实现FactoryBean接口的类，factory-method会自动指向getObject()方法。

  1. 先定义一个Bean

     ```java
     public class Person {
     }
     ```

  2. 编写一个类实现FactoryBean接口，接口返回的bean对象默认单例，isSingleton()返回false则是多例（scope属性）

     ```java
     package com.powernode.spring6.bean;
     import org.springframework.beans.factory.FactoryBean;
     
     
     public class PersonFactoryBean implements FactoryBean<Person> {
     
         @Override
         public Person getObject() throws Exception {
             return new Person();
         }
     
         @Override
         public Class<?> getObjectType() {
             return null;
         }
     
         @Override
         public boolean isSingleton() {
             // true表示单例
             // false表示原型
             return true;
         }
     }
     ```

  3. **通过FactoryBean可对普通Baen进行加工**

  4. 在Spring配置文件中配置FactoryBean

     ```xml
     <bean id="personBean" class="com.powernode.spring6.bean.PersonFactoryBean"/>
     ```

  5.  编写测试程序

     ```java
     @Test
     public void testFactoryBean(){
         ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
         Person personBean = applicationContext.getBean("personBean", Person.class);
         System.out.println(personBean);
     
         Person personBean2 = applicationContext.getBean("personBean", Person.class);
         System.out.println(personBean2);
     }
     ```

     









## 2.2Bean的属性注入

### 2.2.1set注入★★

- 注入外部Bean

  - 在property标签中使用ref属性进行注入。

- 注入内部Bean

  - 在property标签中嵌套bean标签，只有当前bean可以引用，id写不写都一样

  - **注入内部类多写了一个bean标签，一个bean标签对应一个对象，加载文件时会产生新对象⚠️**

    ```xml
        <bean id="empService" class="com.atguigu.service.EmployeeService">
            <property name="employeeDao">
                <bean class="com.atguigu.dao.EmployeeDao">
                    <property name="lastName" value="小明"/>
                    <property name="age" value="20"/>
                </bean>
            </property>
        </bean>
    ```

- 注入简单类型

  - 在property标签中使用value属性进行注入。

  - ```xml
    //注意：value后面的日期字符串格式不能随便写，必须是Date对象toString()方法执行的结果。
    <property name="date" value="Fri Sep 30 15:26:38 CST 2022"/>
    <property name="season" value="WINTER"/>
    <property name="uri" value="/save.do"/>
    //spring6之后，会自动检查url是否有效，如果无效会报错。
    <property name="url" value="http://www.baidu.com"/>
    <property name="localDate" value="EPOCH"/>
    //java.util.Locale 主要在软件的本地化时使用。它本身没有什么功能，更多的是作为一个参数辅助其他方法完成输出的本地化。
    <property name="locale" value="CHINESE"/>
    ```
  
- 级联属性赋值

  - 通过点为其属性的属性赋值
  - **注意：注入的bean修改属性时，修改的是bean本身的属性，一个bean对应一个单实例⚠️**

- 注入数组

  - 当为简单类型直接用array标签内嵌套多个<value></value>标签

  - 当数组内元素为非简单类型时直接用array标签内嵌套多个<ref bean=""/>f标签

    ```xml
        <bean id="person" class="com.powernode.spring6.beans.Person">
            <property name="favariteFoods">
                <array>
                    <value>鸡排</value>
                    <value>汉堡</value>
                    <value>鹅肝</value>
                </array>
            </property>
        </bean>
        <bean id="order" class="com.powernode.spring6.beans.Order">
            <property name="goods">
                <array>
                    <!--这里使用ref标签即可-->
                    <ref bean="goods1"/>
                    <ref bean="goods2"/>
                </array>
            </property>
        </bean>
    ```

- 注入list集合

  - 注入简单类型直接用list标签内嵌套多个<value></value>标签

  - 注入非简单类型时list标签内嵌套多个<ref bean=""/>f标签

  - 注入内部类时可不需要写id

    ```xml
        <property name="lists">
            <list>
                <value>简单类型1</value>
                <value>简单类型2</value>
                <ref bean="userController"></ref>
                <bean class="com.atguigu.controller.UserController"/>
            </list>
        </property>
    ```

- 注入set集合

  - 注入简单类型直接用set标签内嵌套多个<value></value>标签

  - 注入非简单类型时set标签内嵌套多个<ref bean=""/>标签

  - 注入内部类时可不需要写id

  - 无序不可重复，重复时重复的元素会被覆盖

    ```xml
        <bean id="peopleBean" class="com.powernode.spring6.beans.People">
            <property name="phones">
                <set>
                    <!--非简单类型可以使用ref，简单类型使用value-->
                    <value>110</value>
                    <value>110</value>
                    <value>119</value>
                    <ref bean="goods2"/>
                </set>
            </property>
        </bean>
    ```

- 注入map集合

  - 简单类型使用<entry key="" value="">标签

  - 非简单类型使用<entry key-ref="" value-ref="">标签

  - Key不可重复，重复时重复的元素value值会被替换

    ```xml
        <bean id="peopleBean" class="com.powernode.spring6.beans.People">
            <property name="addrs">
                <map>
                    <!--如果key不是简单类型，使用 key-ref 属性-->
                    <!--如果value不是简单类型，使用 value-ref 属性-->
                    <entry key="1" value="北京大兴区"/>
                    <entry key="2" value="上海浦东区"/>
                    <entry key="3" value="深圳宝安区"/>
                </map>
            </property>
        </bean>
    ```

- 注入properties集合

  - 本质上也是map集合，父类是HashTable

  - 使用<props>标签嵌套<prop>标签注入

  - key和value只能是字符串

  - 注入格式为<prop key="key值">value值</prop>

    ```xml
        <bean id="peopleBean" class="com.powernode.spring6.beans.People">
            <property name="properties">
                <props>
                    <prop key="driver">com.mysql.cj.jdbc.Driver</prop>
                    <prop key="url">jdbc:mysql://localhost:3306/spring</prop>
                    <prop key="username">root</prop>
                    <prop key="password">123456</prop>
                </props>
            </property>
        </bean>
    ```

- 注入null和空字符串

  - 注入空字符串使用：<value/> 或者 value=""

  - 注入null使用：<null/> 或者 不为该属性注入值

    ```xml
        <bean id="vipBean" class="com.powernode.spring6.beans.Vip">
            <!--空串的第一种方式-->
            <!--<property name="email" value=""/>-->
            <!--空串的第二种方式-->
            <property name="email">
                <value/>
            </property>
            <!--赋值null的方式-->
            <property name="email">
                <null/>
            </property>
        </bean>
    ```

- 注入特殊符号

  - 第一种：特殊符号使用转义字符代替。

  - 第二种：将含有特殊符号的字符串放到：<![CDATA[]]> 当中。CDATA区中的数据不会被XML文件解析器解析。

  - 注意：使用CDATA时，不能使用value属性，只能使用value标签

    ```xml
        <bean id="mathBean" class="com.powernode.spring6.beans.Math">
            <property name="result">
                <!--只能使用value标签-->
                <value><![CDATA[2 < 3]]></value>
            </property>
        </bean>
    ```

    


### 2.2.2构造注入

**基于constructor有参构造器**

- 在xml文件中，通过constructor-arg标签，基于**有参构造**方法为其属性赋值。

  - constructor-arg标签的index属性：为第几个参数，从0开始

  - constructor-arg标签的name属性：可指定赋值名称进行赋值

  - constructor-arg标签type属性：可指定类型进行赋值

  - 都省略自动根据type判断注入

    ```xml
    <bean id="book3" class="com.atguigu.spring.beans.Book">
        <constructor-arg name="id" value="3"></constructor-arg>
        <constructor-arg name="title" value="红楼梦"></constructor-arg>
        <constructor-arg index="2" value="曹雪芹"></constructor-arg>
        <constructor-arg type="java.lang.Integer" value="100"></constructor-arg>
    </bean>
    ```

    

### 2.2.3命名空间注入★★

- **p命名空间注入：简化set方法注入**（了解）

  - 使用p命名空间的两个前提条件：
    - 第一：在XML头部信息中添加p命名空间的配置信息：xmlns:p="http://www.springframework.org/schema/p"
    - 第二：**p命名空间注入是基于setter方法的，所以需要对应的属性提供setter方法。**

  - 使用方法：

    - 直接写在bean标签的属性内

    - 简单类型：p:首字母小写的set方法名=“属性值"

    - 复杂类型：p:首字母小写的set方法名-ref=“属性值"

      ```xml
      <bean id="empDao" class="com.atguigu.dao.EmployeeDao"></bean>
      <bean id="empService" class="com.atguigu.service.EmployeeService" p:employeeDao-ref="empDao">
      ```

- **c命名空间注入：简化构造方法注入**（了解）

  - 使用c命名空间的两个前提条件：
    - 第一：需要在xml配置文件头部添加信息：xmlns:c="http://www.springframework.org/schema/c"
    - 第二：**需要提供有参构造方法**

  - 使用方法：

    - 直接写在bean标签的属性内

    - 简单类型：c:构造参数属性名=“属性值” 或者 c:_0

    - 复杂类型：c:构造参数属性名-ref=“属性值" 或者c:_0-ref

      ```xml
      <bean id="empDao" class="com.atguigu.dao.EmployeeDao"></bean>
      <bean id="empService" class="com.atguigu.service.EmployeeService" c:employeeDao-ref="empDao" >
      ```

- **util命名空间：★**

  - 作用：将集合bean的配置拿到外面，供其他bean引用。

  - **配置集合类型的bean需要引入util名称空间**，使用前提条件：需要在xml配置文件头部添加信息

    -  xmlns:util="http://www.springframework.org/schema/util"

    - http://www.springframework.org/schema/util 

    - https://www.springframework.org/schema/util/spring-util.xsd

      ```xml
      <beans xmlns="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:p="http://www.springframework.org/schema/p" 
             xmlns:util="http://www.springframework.org/schema/util"
             xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/util https://www.springframework.org/schema/util/spring-util.xsd">
      ```

  - 使用方法：

    - 使用<util:properties id="prop">配置util命名空间

    - 使用 <property name="properties" ref="prop"/>引入util命名空间

    - 注意：**这里是properties集合，我们还可以使用其他集合！！！**

      ```xml
          <util:properties id="prop">
              <prop key="driver">com.mysql.cj.jdbc.Driver</prop>
              <prop key="url">jdbc:mysql://localhost:3306/spring</prop>
              <prop key="username">root</prop>
              <prop key="password">123456</prop>
          </util:properties>
      
          <bean id="dataSource1" class="com.powernode.spring6.beans.MyDataSource1">
              <property name="properties" ref="prop"/>
          </bean>
      
          <bean id="dataSource2" class="com.powernode.spring6.beans.MyDataSource2">
              <property name="properties" ref="prop"/>
          </bean>
      ```



### 2.2.4自动装配

手动装配：以value或ref的方式明确指定属性值都是手动装配。

自动装配：根据bean标签的autowire属性指定的装配规则

**自动装配也是基于set方法的**

- **autowire的属性值为no或default：不自动装配**

- 根据名称自动装配
  - **为bean标签添加属性，autowire="byName"**，表示通过名称进行自动装配。

- 根据类型自动装配
  - **为bean标签添加属性，autowire="byType"**，表示通过类型进行自动装配。



### 2.2.5引入外部属性文件★

将bean的配置信息放到properties文件中，便于修改，常用于**数据库连接信息的配置**

以配置数据库信息为例，操作步骤为：

- 第一步：将druid和mysql驱动的jar包导入
- 第二步：创建存储数据库配置信息的文件db.properties
- 第三步：引入db.properties文件
- 第四步：使用bean管理数据源，并注入文件内的属性
- 第五步：编写测试程序

操作代码：

- 第一步：将druid和mysql驱动的jar包导入

  ```xml
  <!--导入druid的jar包-->
  <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.10</version>
  </dependency>
  <!--导入mysql的jar包-->
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.25</version>
  </dependency>
  ```

- 第二步：创建存储数据库配置信息的文件db.properties

  ```properties
  jdbc.driver=com.mysql.cj.jdbc.Driver
  jdbc.url=jdbc:mysql://localhost:3306/ssm
  jdbc.username=root
  jdbc.password=12345678
  ```

- 第三步：引入db.properties文件

  ```xml
  <!--引入外部属性文件-->
  <context:property-placeholder location="classpath:druid.properties"></context:property-placeholder>
  ```

- 第四步：使用bean管理数据源，并注入文件内的属性

  ```xml
  <!--配置数据源-->
  <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
      <property name="username" value="${jdbc.username}"></property>
      <property name="password" value="${jdbc.password}"></property>
      <property name="url" value="${jdbc.url}"></property>
      <property name="driverClassName" value="${jdbc.driverClassName}"></property>
      <property name="initialSize" value="${jdbc.initialSize}"></property>
      <property name="maxActive" value="${jdbc.maxActive}"></property>
  </bean>
  ```

- 第五步：编写测试程序

  ```java
      @Test
      public void test01() throws SQLException {
          ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
          DruidDataSource dataSource = ioc.getBean(DruidDataSource.class);
          DruidPooledConnection connection = dataSource.getConnection();
          System.out.println("connection = " + connection);
      }
  ```

  

## 2.3Bean的作用域

在Spring中，可以在bean标签的scope属性里设置bean的作用域，以决定这个bean是单实例的还是多实例的。

### 2.3.1作用域属性scope★

- **属性值为singleton：**（默认值）
  - 默认情况下，Spring的IoC容器创建的Bean对象是单例的，每次getBean获取同一个对象
  - 默认情况下，Bean对象的创建是在初始化Spring上下文的时候就完成的。
  - **由此可知：默认情况下，一个bean标签一个对象，在ioc容器创建后，创建bean对象**
- **属性值为prototype：**（原型）
  - Spring的IoC容器创建的Bean对象是多例的，**每次执行getBean()就创建新的Bean对象**
  - 初始化Spring上下文时不会创建对象，getBean时才会创建对象

- **其它scope**

  **scope属性的值不止两个，它一共包括8个选项：**

  - singleton：默认的，单例。

  - prototype：原型。每调用一次getBean()方法则获取一个新的Bean对象。或每次注入的时候都是新对象。

  - request：一个请求对应一个Bean。**仅限于在WEB应用中使用**。

  - session：一个会话对应一个Bean。**仅限于在WEB应用中使用**。

  - global session：**portlet应用中专用的**。如果在Servlet的WEB应用中使用global session的话，和session一个效果。（portlet和servlet都是规范。servlet运行在servlet容器中，例如Tomcat。portlet运行在portlet容器中。）

  - application：一个应用对应一个Bean。**仅限于在WEB应用中使用。**

  - websocket：一个websocket生命周期对应一个Bean。**仅限于在WEB应用中使用。**

  - 自定义scope：很少使用



### 2.3.2Scope的多线程

Scope默认是单例的，即使是多线程获取到的Bean对象都是同一个，跨线程则是不同对象。

如果想一个线程对应一个对象，需要自定义Scope：

- 第一步：自定义Scope。（实现Scope接口）

  - spring内置了线程范围的类：org.springframework.context.support.SimpleThreadScope，可以直接用。

- 第二步：将自定义的Scope注册到Spring容器中。

  ```xml
  <!--这是自定义范围配置器-->
  <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
      <!--scopes属性是个map集合-->
      <property name="scopes">
          <map>
              <!--mythread是作用域的名字-->
              <entry key="myThread">
                  <!--是spring内置的scope接口实现类，也可以自己写-->
                  <bean class="org.springframework.context.support.SimpleThreadScope"/>
              </entry>
          </map>
      </property>
  </bean>
  ```

- 第三步：使用Scope。

  ```xml
  <bean id="sb" class="com.powernode.spring6.beans.SpringBean" scope="myThread" />
  ```

- 第四步：编写测试程序：

  ```java
      @Test
      public void testCustomScope() {
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-scope.xml");
          SpringBean sb1 = applicationContext.getBean("sb", SpringBean.class);
          SpringBean sb2 = applicationContext.getBean("sb", SpringBean.class);
          System.out.println(sb1);
          System.out.println(sb2);
          // 启动线程
          new Thread(new Runnable() {
              @Override
              public void run() {
                  SpringBean a = applicationContext.getBean("sb", SpringBean.class);
                  SpringBean b = applicationContext.getBean("sb", SpringBean.class);
                  System.out.println(a);
                  System.out.println(b);
              }
          }
      }
  ```





### 2.3.3管理自定义对象

自己new的对象仍可以通过Spring管理

```java
// 自己new的对象
User user = new User();
System.out.println(user);

// 创建 默认可列表BeanFactory 对象
DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
// 注册Bean
factory.registerSingleton("userBean", user);
// 从spring容器中获取bean
User userBean = factory.getBean("userBean", User.class);
```



## 2.4Bean的生命周期

Spring IOC容器可以管理bean的生命周期，Spring允许在bean生命周期内特定的时间点执行指定的任务。

### 2.4.1生命周期之五步

Bean生命周期可以粗略的划分为五大步：

- 第一步：实例化Bean
  - 调用无参构造
- 第二步：Bean属性赋值
  - 调用set
- 第三步：初始化Bean（自定义）
  - 调用init，init方法要自己写，方法名随意
- 第四步：使用Bean
- 第五步：销毁Bean（自定义）
  - 调用destory，destory要自己写，方法名随意

自定义的方法需要在配置文件中指定：

- init-method属性指定初始化方法。
- destroy-method属性指定销毁方法。

使用步骤：

- 第一步：定义一个bean

  ```java
  public class User {
      private String name;
  
      public User() {
          System.out.println("1.实例化Bean");
      }
  
      public void setName(String name) {
          this.name = name;
          System.out.println("2.Bean属性赋值");
      }
  
      public void initBean(){
          System.out.println("3.初始化Bean");
      }
  
      public void destroyBean(){
          System.out.println("5.销毁Bean");
      }
  
  }
  
  ```

- 第二步：添加配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--
      init-method属性指定初始化方法。
      destroy-method属性指定销毁方法。
      -->
      <bean id="userBean" class="com.powernode.spring6.bean.User" init-method="initBean" destroy-method="destroyBean">
          <property name="name" value="zhangsan"/>
      </bean>
  
  </beans>
  ```

- 第三步：编写程序测试

  ```java
  public class BeanLifecycleTest {
      @Test
      public void testLifecycle(){
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
          User userBean = applicationContext.getBean("userBean", User.class);
          System.out.println("4.使用Bean");
          // 只有正常关闭spring容器才会执行销毁方法
          ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
          context.close();
      }
  }
  ```

  

### 2.4.2生命周期之七步

可以在五步基础上，初始化前和初始化后添加代码，加入“Bean后处理器”，拓展为七大步

**一定要注意：在spring配置文件中配置的Bean后处理器将作用于当前配置文件中所有的Bean。**

- 第一步：实例化Bean
  - 调用无参构造
- 第二步：Bean属性赋值
  - 调用set
- 第三步：执行“Bean后处理器”的before方法（使用Bean后处理器）
- 第四步：初始化Bean
  - 调用init，init方法要自己写，方法名随意
- 第五步：执行“Bean后处理器”的after方法（使用Bean后处理器）
- 第六步：使用Bean
- 第七步：销毁Bean
  - 调用destory，destory要自己写，方法名随意

Bean后处理器的使用：

- 编写一个类实现BeanPostProcessor接口，并且重写before和after方法
- 在spring.xml文件中配置“Bean后处理器”
- 注意：这个“Bean后处理器”将作用于当前配置文件中所有的bean

使用步骤：

- 第一步：定义一个bean实现BeanPostProcessor类，并且重写before和after方法：

  ```java
  public class LogBeanPostProcessor implements BeanPostProcessor {
      @Override
      public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
          System.out.println("Bean后处理器的before方法执行，即将开始初始化");
          return bean;
      }
  
      @Override
      public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
          System.out.println("Bean后处理器的after方法执行，已完成初始化");
          return bean;
      }
  }
  ```

- 第二步：在配置文件中配置“Bean后处理器”：

  ```xml
  <!--配置Bean后处理器。这个后处理器将作用于当前配置文件中所有的bean。-->
  <bean class="com.powernode.spring6.bean.LogBeanPostProcessor"/>
  ```

  

### 2.4.3生命周期之十步

可以在“Bean后处理器”before方法之前、before方法之后、Bean的使用和销毁之前再添加三步，拓展为十大步

该内容了解即可，实际开发中很难遇到

使用步骤：

- 第一步：在“Bean后处理器”before方法之前，检查Bean是否实现了Aware的相关接口：

  Aware相关的接口包括：BeanNameAware、BeanClassLoaderAware、BeanFactoryAware

  - 当Bean实现了BeanNameAware，Spring会将Bean的名字传递给Bean。

  - 当Bean实现了BeanClassLoaderAware，Spring会将加载该Bean的类加载器传递给Bean。

  - 当Bean实现了BeanFactoryAware，Spring会将Bean工厂对象传递给Bean。

- 第二步：在“Bean后处理器”before方法之后，检查Bean是否实现了InitializingBean的相关接口

- 第三步：在使用Bean之后销毁之前，检查Bean是否实现了DisposableBean的相关接口

添加的这三个步骤的特点：

- 都是在检查当前Bean是否实现了某些特定接口，如果实现了，Spring容器就会调用这个接口中的方法。

通过测试得：

- **InitializingBean的方法早于init-method的执行。**
- **DisposableBean的方法早于destroy-method的执行。**



### 2.4.4根据作用域管理

**Spring 根据Bean的作用域（scope）来选择管理方式：**

- 对于singleton作用域的Bean，Spring 能够精确地知道该Bean何时被创建，何时初始化完成，以及何时被销毁；所以**Spring容器会对singleton的bean进行完整的生命周期管理**
- 而对于 prototype 作用域的 Bean，Spring 只负责创建，当容器创建了 Bean 的实例后，Bean 的实例就交给客户端代码管理，Spring 容器将不再跟踪其生命周期。即**客户端程序拿到bean之后就不再管理该对象的生命周期**



## 2.5Bean的循环依赖

A对象中有B属性。B对象中有A属性。这就是循环依赖。我依赖你，你也依赖我。

### 2.5.1循环依赖问题★

使用singleton下的set注入和构造模拟循环依赖场景：

```xml
      <bean id="husbandBean" class="com.atguigu.bean.Husband" scope="singleton">
        <property name="name" value="丈夫张三"/>
        <property name="wife" ref="wifeBean"/>
      </bean>


      <bean id="wifeBean" class="com.atguigu.bean.Wife" scope="prototype">
        <property name="name" value="妻子小花"/>
        <property name="husband" ref="husbandBean"/>
      </bean>
```

结论：在singleton + set注入的情况下，只要有一个bean的scope为singleton，就不会产生循环依赖，但是使用构造注入会发生异常。

原因：在singleton模式下Spring对Bean的管理主要分为两个阶段：

- 第一个阶段：在Spring容器加载的时候，实例化Bean，只要其中任意一个Bean实例化之后，不等属性赋值，就马上对其进行“曝光”。
- 第二个阶段：Bean“曝光”之后，再进行属性的赋值(调用set方法)

使用prototype下的set注入模拟循环依赖场景：

```xml
      <bean id="husbandBean" class="com.atguigu.bean.Husband" scope="prototype">
        <property name="name" value="丈夫张三"/>
        <property name="wife" ref="wifeBean"/>
      </bean>

      <bean id="wifeBean" class="com.atguigu.bean.Wife" scope="prototype">
        <property name="name" value="妻子小花"/>
        <property name="husband" ref="husbandBean"/>
      </bean>
```

结论：在prototype + set注入的情况下，若bean的scope都为prototype，则产生循环依赖。

原因：注入属性时，每次依赖都new新对象 ，出现了依赖循环。

基于两次测试，分析产生循环依赖的原因：

- 实例化对象的过程和对象属性赋值的过程没有分离开，必须在一起完成导致的。
- 先实例化所有对象，再为其属性赋值则不会产生冲突问题



### 2.5.2解决循环依赖★★

Spring可以解决set + singleton模式下循环依赖的源码分析

Spring底层实现原理：

- 将“实例化Bean”和“给Bean属性赋值”这两个动作分开去完成
- 先把所有的单例Bean实例化出来，放到一个集合当中(我们可以称之为缓存)
- 再再调用set方法给属性赋值。这样就解决了循环依赖的问题。

源码分析：

AbstractAutowireCapableBeanFactory的doCreateBean()方法创建了bean

```java
    protected Object doCreateBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args) throws BeanCreationException {
        BeanWrapper instanceWrapper = null;
        if (mbd.isSingleton()) {
            instanceWrapper = (BeanWrapper)this.factoryBeanInstanceCache.remove(beanName);
        }

        if (instanceWrapper == null) {
            instanceWrapper = this.createBeanInstance(beanName, mbd, args);
        }
				//创建单例的bean实例 
        Object bean = instanceWrapper.getWrappedInstance();
        Class<?> beanType = instanceWrapper.getWrappedClass();
        if (beanType != NullBean.class) {
            mbd.resolvedTargetType = beanType;
        }

        synchronized(mbd.postProcessingLock) {
            if (!mbd.postProcessed) {
                try {
                    this.applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);
                } catch (Throwable var17) {
                    throw new BeanCreationException(mbd.getResourceDescription(), beanName, "Post-processing of merged bean definition failed", var17);
                }

                mbd.postProcessed = true;
            }
        }
				//把单例的Bean实例放到一个集合当中 (官方注释：急切的缓存单例以便能给循环引用)
        boolean earlySingletonExposure = mbd.isSingleton() && this.allowCircularReferences && this.isSingletonCurrentlyInCreation(beanName);
        if (earlySingletonExposure) {
            if (this.logger.isTraceEnabled()) {
                this.logger.trace("Eagerly caching bean '" + beanName + "' to allow for resolving potential circular references");
            }
						//关键步骤：提前曝光单实例的bean！ 标记1  
            this.addSingletonFactory(beanName, () -> {
                return this.getEarlyBeanReference(beanName, mbd, bean);
            });
        }

        Object exposedObject = bean;

        try {
          	//曝光单实例的bean后，再给bean的属性赋值，赋值后的bean为完整的bean单例对象，放到一级缓存中
            this.populateBean(beanName, mbd, instanceWrapper);
            exposedObject = this.initializeBean(beanName, exposedObject, mbd);
        } catch (Throwable var18) {
            if (var18 instanceof BeanCreationException && beanName.equals(((BeanCreationException)var18).getBeanName())) {
                throw (BeanCreationException)var18;
            }

            throw new BeanCreationException(mbd.getResourceDescription(), beanName, "Initialization of bean failed", var18);
        }

        if (earlySingletonExposure) {
            Object earlySingletonReference = this.getSingleton(beanName, false);
            if (earlySingletonReference != null) {
                if (exposedObject == bean) {
                    exposedObject = earlySingletonReference;
                } else if (!this.allowRawInjectionDespiteWrapping && this.hasDependentBean(beanName)) {
                    String[] dependentBeans = this.getDependentBeans(beanName);
                    Set<String> actualDependentBeans = new LinkedHashSet(dependentBeans.length);
                    String[] var12 = dependentBeans;
                    int var13 = dependentBeans.length;

                    for(int var14 = 0; var14 < var13; ++var14) {
                        String dependentBean = var12[var14];
                        if (!this.removeSingletonIfCreatedForTypeCheckOnly(dependentBean)) {
                            actualDependentBeans.add(dependentBean);
                        }
                    }

                    if (!actualDependentBeans.isEmpty()) {
                        throw new BeanCurrentlyInCreationException(beanName, "Bean with name '" + beanName + "' has been injected into other beans [" + StringUtils.collectionToCommaDelimitedString(actualDependentBeans) + "] in its raw version as part of a circular reference, but has eventually been wrapped. This means that said other beans do not use the final version of the bean. This is often the result of over-eager type matching - consider using 'getBeanNamesForType' with the 'allowEagerInit' flag turned off, for example.");
                    }
                }
            }
        }

        try {
            this.registerDisposableBeanIfNecessary(beanName, bean, mbd);
            return exposedObject;
        } catch (BeanDefinitionValidationException var16) {
            throw new BeanCreationException(mbd.getResourceDescription(), beanName, "Invalid destruction signature", var16);
        }
    }
```

标记1：addSingletonFactory方法，进入到DefaultSingletonBeanRegistry类

类中的三个存放缓存的Map集合成员变量：Map集合的key存储的都是bean的name（bean id）

-   一级缓存存储的是：单例Bean对象。完整的单例Bean对象，这个缓存中的Bean对象的属性都已经赋值了。
-   二级缓存存储的是：早期的单例Bean对象。这个缓存中的单例Bean对象的属性没有赋值。
-   三级缓存存储的是：单例工厂对象。这个里面存储了大量的“工厂对象”，每一个单例Bean对象都会对应一个单例工厂对象。这个集合中存储的是，创建该单例对象时对应的那个单例工厂对象。

```java
public class DefaultSingletonBeanRegistry extends SimpleAliasRegistry implements SingletonBeanRegistry {
    private final Map singletonObjects //一级缓存
    private final Map<String, Object> earlySingletonObjects //二级缓存
    private final Map<String, ObjectFactory<?>> singletonFactories //三级缓存


        ...省略部分源码


    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        Assert.notNull(singletonFactory, "Singleton factory must not be null");
        synchronized (this.singletonObjects) {
            if (!this.singletonObjects.containsKey(beanName)) {
                //先把创建bean对象的工厂bean向三级缓存中存储（曝光工厂）
                //曝光工厂是为了通过getSingleton()方法取bean对象
                this.singletonFactories.put(beanName, singletonFactory);
                this.earlySingletonObjects.remove(beanName);
                this.registeredSingletons.add(beanName);
            }
        }
    }

    @Override
    @Nullable
    public Object getSingleton(String beanName) {
        return getSingleton(beanName, true);
    }

    //通过该方法获取单例对象
    @Nullable
    protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        // Quick check for existing instance without full singleton lock
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            singletonObject = this.earlySingletonObjects.get(beanName);
            if (singletonObject == null && allowEarlyReference) {
                synchronized (this.singletonObjects) {
                    // Consistent creation of early reference within full singleton lock	 
                    //先从一级缓存中获取bean对象
                    singletonObject = this.singletonObjects.get(beanName);
                    if (singletonObject == null) {
                        //一级缓存取不到就从二级缓存中获取
                        singletonObject = this.earlySingletonObjects.get(beanName);
                        if (singletonObject == null) {
                            //二级缓存取不到就从三级缓存里取对应的工厂对象
                            ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
                            if (singletonFactory != null) {
                                //通过工厂对象的getObject()拿到单例对象，早期Bean，没有为属性赋值
                                singletonObject = singletonFactory.getObject();
                                //拿到单例对象后放到二级缓存中
                                this.earlySingletonObjects.put(beanName, singletonObject);
                                //清空三级缓存,创建对象后，该对象的工厂类完成任务，执行销毁
                                this.singletonFactories.remove(beanName);
                            }
                        }
                    }
                }
            }
        }
        return singletonObject;
    }
```





# 3 注解的方式管理Bean

## 3.1使用注解标识组件★

负责创建Bean并加入到IoC容器的注解，常见的包括四个：

- @Component：标识一个受Spring IOC容器管理的普通组件
- @Repository：标识一个受Spring IOC容器管理的持久化层组件
- @Service：标识一个受Spring IOC容器管理的业务逻辑层组件
- @Controller：标识一个受Spring IOC容器管理的表述层控制器组件

注解的使用方法：

- 第一步：加入aop的依赖（spring-context的jar包中已包含）

- 第二步：在配置文件中添加context命名空间（Idea可自动引入）

- 第三步：在配置文件中指定扫描的包

  - 被注解标识后还需要通过Spring进行扫描才能够侦测到。

    ```xml
    <context:component-scan base-package="com.powernode.spring6.bean"/>
    ```

- 第四步：在Bean类上使用注解

注解的命名规则：

- 注解value属性赋值即为注解的id名称，可通过value属性值将改注解注入到其他bean的属性上
- 不为注解value属性赋值时，默认名字的规律是：Bean类名首字母小写



## 3.2组件扫描器

### 3.2.1扫描器基本使用★

设置一个扫描器：

1. base-package属性指定一个需要扫描的基类包，Spring容器将会扫描这个基类包及其子包中的所有类。

2. 当需要扫描多个包时可以使用逗号分隔，扫描不到的注解是无效的

3. 如果仅希望扫描特定的类而非基包下的所有类，可使用resource-pattern属性只扫描特定的类。

   ```xml
   <context:component-scan base-package="com.atguigu" resource-pattern="controller/*.class" />
   ```

   

### 3.2.2设置不扫描注解的类★

设置排除通过<context:exclude-filter />标签

- type：可以根据不同的类型排除

  - annotation：根据注解的类型排除
  - assignable：根据父接口或父类排除

- expression ：

  - 注解的全类名
  - 父接口或者父类的全类名

  ```xml
  <!--配置自动扫描的包-->
      <context:component-scan base-package="com.atguigu.spring.annotation">
          <!--设置不扫描那个包下的类-->
  <!--<context:exclude-filter type="annotation" expression="org.springframework.stereotype.Repository"/>-->
          <context:exclude-filter type="assignable" expression="com.atguigu.dao.UserDao"/>
      </context:component-scan>
  ```



### 3.2.3设置扫描注解的类★

设置包含通过<context:include-filter />标签

条件：**必须在<context:component-scan />标签上添加属性 use-default-filters="false"**，禁用默认的过滤器

- type属性：根据不同类型包含

  - annotation：根据注解的类型包含
  - assignable：根据父接口或父类包含

- expression：根据注解的全类名包含

  - 注解的全类名
  - 父接口或者父类的全类名

  ```xml
  <!--配置自动扫描的包-->
      <context:component-scan base-package="com.atguigu.spring.annotation" use-default-filters="false">
          <!--设置只扫描那个包下的类-->
  <!--<context:include-filter type="annotation" expression="org.springframework.stereotype.Repository"/>-->
          <context:include-filter type="assignable" expression="com.atguigu.dao.UserDao"/>
      </context:component-scan>
  ```

  

### 3.2.4选择性实例化Bean

某些业务场景下，只允许com.atguigu包其中所有的Controller参与Bean管理，其他的都不实例化。这时候就需要选择性实例化。

```xml
<context:component-scan base-package="com.powernode.spring6.bean3" use-default-filters="false">
	<context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```



## 3.3使用注解自动装配★

### 3.3.1自动装配的使用：★

@Autowired注解可以用来自动注入**非简单类型**。被翻译为：自动连线的，或者自动装配。

@Qualifier注解与@Autowired注解搭配使用，使@Autowired注解基于名称自动装配【byName】

使用方式：

- 单独使用@Autowired注解，**默认根据类型自动装配**。【默认是byType】
- 使用@Autowired注解的属性都需要被设置。当Spring找不到匹配的bean装配属性时，会抛出异常。
- 如果@Autowired注解required属性设置为false，则即使@Autowired注解对应的bean不存在时，也不会报错。
- @Autowired注解默认根据类型注入。如果存在相同类型的bean时，可配合@Qualifier注解一起使用。

使用位置：

- @Autowired注解可以出现在：属性上、构造方法上、构造方法的参数上、setter方法上。
- 当有参构造方法只有一个时，@Autowired注解可以省略。

当@Autowired注解应在数组和集合属性上时：

- 应用在数组类型的属性上，此时Spring将会把所有匹配的bean进行自动装配。
- 应用在集合属性上，此时Spring读取该集合的类型信息，然后自动装配所有与之兼容的bean。
- 应用在java.util.Map上时，若该Map的键值为String，那么 Spring将自动装配与值类型兼容的bean作为值，并以bean的id值作为键。



### 3.3.2自动装配的过程：★

① 根据属性的类型实现自动装配。

② 如果IOC容器中该属性的类型的bean存在多个，则以属性名作为id从IOC容器中寻找以实现自动装配，找到则装配成功；找不到则抛出异常。

③ 如果IOC容器中该属性的类型的bean存在多个，并且以属性名作为id从IOC容器中寻找也无法实现自动装配，还可以通过@Qualifier注解指定bean的名称实现自动装配。@Qualifiter注解也可以标注在方法的形参前面。





# 4 AOP

## 4.1代理模式

### 4.1.1GoF之代理模式

代理模式的作用是：为其他对象提供一种代理以控制对这个对象的访问。在某些情况下，一个客户不想或者不能直接引用一个对象，此时可以通过一个称之为“代理”的第三者来实现间接引用。

**代理模式中有一个非常重要的特点：对于客户端程序来说，使用代理对象时就像在使用目标对象一样。**

代理模式的作用：

- 在程序中，对象A和对象B无法直接交互时，由代理完成
- 在程序中，功能需要增强时，由代理完成
- 在程序中，目标需要被保护时，由代理完成

代理模式中的角色：

- 代理类（代理主题）
- 目标类（真实主题）
- 代理类和目标类的公共接口（抽象主题）：客户端在使用代理类时就像在使用目标类，不被客户端所察觉，所以代理类和目标类要有共同的行为，也就是实现共同的接口。

代理模式在代码实现上，包括两种形式：

- 静态代理
- 动态代理

静态代理:

- 第一步：创建代理类，继承公共接口，实现公共接口内的所有方法
- 第二步：创建代理类时，传入目标对象，作为代理类的属性。（一定要用接口类型接收，用于调用目标类对象的方法）
- 第三步：在代理类的方法中，调用目标对象方法的同时，还可以添加其他业务操作，增强该方法
- 第四步：通过代理类的对象调用其增强方法

在内存当中动态生成类的技术常见的包括：

- JDK动态代理技术：只能代理接口。
- CGLIB动态代理技术：CGLIB(Code Generation Library)是一个开源项目，**底层是通过继承的方式实现的，底层有一个小而快的字节码处理框架ASM**
- Javassist动态代理技术：Javassist是一个开源的分析、编辑和创建Java字节码的类库，通过使用Javassist对字节码操作为JBoss实现动态"AOP"框架。

静态代理和动态：

- 静态代理：有多少接口就要写多少代理类，代码也没进行复用，容易类爆炸
- 动态代理：在程序运行阶段，在内存中动态生成代理类



### 4.1.2动态代理-JDK

使用步骤：

- 第一步：先创建目标对象

- 第二步：通过目标对象创建代理对象

  **JDK中的java.lang.reflect.Proxy类的newProxyInstance静态方法可返还一个代理对象，该方法有三个参数**

  - 第一个参数：目标对象的类加载器。用来加载内存中代理类的class字节码到JVM，创建代理对象。

  - 第二个参数：目标类和代理类的公共接口们。在内存中生成代理类的时候，要告诉他实现哪些接口。

  - 第三个参数：InvocationHandler接口的匿名内部类或实现类对象。

    ```java
      public static void main(String[] args) {
        //第一步：创建目标对象
        UserService userService = new UserServiceImpl();
        //第二步：创建代理对象
        UserService userPro = (UserService) Proxy.newProxyInstance(
          											//开始传入第一个参数，类加载器
                                userService.getClass().getClassLoader(),
          											//开始传入第二个参数，目标类的接口们
                                userService.getClass().getInterfaces(),
          											//开始传入第三个参数，InvocationHandler接口的匿名内部类对象
                                new InvocationHandler() {
              @Override
              public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                
                //第一个参数：Object proxy。代理对象。
                //第二个参数：Method method。目标方法。
                //第三个参数：Object[] args。目标方法调用时要传的参数。
                System.out.println("事务拓展1...");
                //通过反射调用目标方法：（四要素：何对象？何方法？何参数？何返回值？）
                Object result = method.invoke(userService, args);
                System.out.println("事务拓展2...");
                return result;
              }
            });
        //第三步：调用代理方法
        userPro.addUser(new User());
      }
    ```

    newProxyInstance静态方法执行了两步操作：既在内存中创建了代理类的class文件，又创建了代理对象。

- 第三步：通过代理类的对象调用其增强方法

封装JDK动态代理：

- 封装成一个工具类，直接返回一个代理对象

```java
public class UserProxy {

    public static UserService getProxy(UserService userService) {
        UserService userPro = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //第三个参数内，写拓展的事务
                        System.out.println("事务拓展1...");
                        //通过反射调用目标方法⚠️
                        Object result = method.invoke(userService, args);
                        System.out.println("事务拓展2...");
                        return result;
                    }
                });
        //直接返回一个代理对象
        return userPro;
    }
}
```



### 4.1.3动态代理-CGLIB

概述：

- **代理类继承于被代理类**
- **不需要原始被代理对象，根据被代理类的字节码Class对象动态创建代理对象**
- **执行被代理类的原方法**
- 相比较于JDK代理，少生成了一个字节码对象，节省内存，因为是调用父类的字节码对象创建的代理类对象

使用步骤：

第一步：导入依赖的jar包（spring框架jar包中内置了，不需要导入）

```xml
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>3.3.0</version>
</dependency>
```

第二步：创建一个没有实现接口的目标类

```java
public class UserService {
  public void login(){
    System.out.println("用户正在登录系统....");
  }
}　
```

第三步：使用CGLIB创建代理类对象

```java
public class Client {
    public static void main(String[] args) {
        // 创建字节码增强器
        Enhancer enhancer = new Enhancer();
        //设置目标类是代理类的父类⚠️
        enhancer.setSuperclass(UserService.class);
        // 回调方法的参数 MethodInterceptor接口: 拦截目标类的方法，自己增强后再执行
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                // Object o: 代理类对象 
                // Method method: 被代理类的原始方法 
                // Object[] args: 方法的实际参数 
                // MethodProxy methodProxy: 代理类的方法
                System.out.println("权限校验");
                // 执行目标对象的原方法⚠️
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("日志记录");
                return result;
            }
        });
        // 调用父类的字节码对象创建代理对象⚠️
        UserService userServiceProxy = (UserService) enhancer.create();
				// 通过代理对象调用父类方法
        userServiceProxy.login();

    }
}
```



### 4.1.4两种动态代理区别

- JDK代理使用的是反射机制生成一个实现代理接口的匿名类，在调用具体方法前调用InvokeHandler来处理。
- CGLIB代理使用字节码处理框架ASM，对代理对象类的class文件加载进来，通过修改字节码生成子类。
- JDK创建代理对象效率较高，执行效率较低；
- CGLIB创建代理对象效率较低，执行效率高。
- JDK动态代理机制是委托机制，只能对实现接口的类生成代理，通过反射动态实现接口类；
- CGLIB则使用的继承机制，针对类实现代理，被代理类和代理类是继承关系，所以代理类是可以赋值给被代理类的，因为是继承机制，不能代理final修饰的类。
- JDK代理不需要依赖第三方的库，CGLib 必须依赖于CGLib的类库，spring内置CGLib代理。



## 4.2AOP概述

AOP(Aspect-Oriented Programming，面向切面编程)

AOP关注的是程序中的共性功能，开发时，将共性功能抽取出来制作成独立的功能模块，此时原始功能中将不具有 这些被抽取出的共性功能代码。在被抽取的共性功能的模块运行时候，将共性功能模块也运行，即可完成原始的功能。

- 作用
  - 在不修改已有方法的情况下去增强该方法.
- 好处
  - 减少重复代码
  - 提高开发效率
  - 维护方法便
- 实现方式
  - 动态代理
    - jdk
    - CGLib（spring内置）
  - 设计模式 (了解)
    - 静态代理设计模式
    - 装饰者设计模式
- `AOP` VS `OOP`
  - aop : 面向切面编程
    - 关注共性功能
  - oop : 面向对象编程
    - 关注对象

用一句话总结AOP：**处理交叉业务**，将与核心业务无关的代码独立的抽取出来，形成一个独立的组件，然后以横向交叉的方式应用到业务流程当中的过程被称为AOP。交叉业务：日志模块、事务模块、安全模块等。



## 4.3AOP名词解释★

* ①链接点
  * 目标类中的所有方法
* ②切入点
  * 目标类中具有共性功能的方法（有交叉业务的目标方法）
* ③通知
  * 将共性功能抽取出来独立而成的方法（交叉业务方法）
* ④切面
  * 切入点和通知的位置关系（切入点+通知）
* ⑤目标对象类
  * 也叫被代理类, 包含切入点的类
* ⑥通知类
  * 包含通知方法的类
* ⑦织入
  * 使用动态代理技术将通知动态地放入到切入点执行的过程



## 4.4AOP动态代理环境搭建★

* 开发步骤

  * ①引入依赖
    * spring-aop相关依赖
  * ②制作目标对象类，并交给Spring容器管理
    * 切入点
  * ③制作通知类并交给Spring容器管理
    * 通知类，也叫切面类，通知方法所在的类
  * ④编写spring-core.xml
    * 配置切面(`通知和切入点的关系`)
  * ⑤代码测试

* ①引入依赖

  ```xml
  <dependencies>
    <!--导入spring-context-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.3.1</version>
    </dependency>
    <!--spring core-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>5.3.23</version>
    </dependency>
    <!--aop start-->
    <!--aop jar-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>5.3.23</version>
    </dependency>
    <!--aspects jar-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>5.3.23</version>
    </dependency>
    <!--aop end-->
    <!--导入junit4.12-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
    <!--整合junt-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>5.3.1</version>
    </dependency>
  </dependencies>
  ```

* ②制作目标对象类 (`被代理类`)

  ```java
  @Service
  public class UserServiceImpl implements UserService {
  
      //链接点
      //切入点
      public int add(User user) {
          System.out.println("UserServiceImpl add");
          return 250;
      }
  
      //链接点
      //切入点
      public int delete(Integer id) {
          System.out.println("UserServiceImpl delete");
          return 0;
      }
  
      //链接点
      public int update(User user) {
          System.out.println("UserServiceImpl update");
          return 0;
      }
  
      //链接点
      public User getById(Integer id) {
          System.out.println("UserServiceImpl getById");
          return null;
      }
  }
  
  ```

* ③制作通知类

  ```java
  @Component
  public class FirstAdvice {
      /**
       * 通知
       */
      public void checkPermission() {
          System.out.println("MyAdvice01 checkPermission");
      }
  
  
      /**
       * 通知
       */
      public void printLog(){
          System.out.println("MyAdvice01 printLog");
      }
  
  
  }
  
  ```

* ④编写spring-core.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:context="http://www.springframework.org/schema/context"
         xmlns:aop="http://www.springframework.org/schema/aop"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="
         http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
         http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
  
  
      <!--扫描注解-->
      <context:component-scan base-package="com.spring4"></context:component-scan>
  
      <!--aop配置-->
      <aop:config>
          <!--配置切面 : 通知和切入点的关系-->
          <!--
              ref="firstAdvice" : 通知类,切面类
          -->
          <aop:aspect ref="firstAdvice">
              <!--
                  method="checkPermission" : 通知方法
                  pointcut="" : 切入点
              -->
              <aop:before method="checkPermission"
                          pointcut="execution(public int com.spring4.service.impl.UserServiceImpl.add(com.spring4.pojo.User))"></aop:before>
              <aop:after-returning method="printLog"
                                   pointcut="execution(public int com.spring4.service.impl.UserServiceImpl.add(com.spring4.pojo.User))"></aop:after-returning>
          </aop:aspect>
  
      </aop:config>
  
  </beans>
  ```

* ⑤代码测试

  ```java
  package test;
  
  import com.spring4.pojo.User;
  import com.spring4.service.UserService;
  import org.junit.Test;
  import org.junit.runner.RunWith;
  import org.springframework.beans.factory.annotation.Autowired;;
  import org.springframework.test.context.ContextConfiguration;
  import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
  
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(locations = "classpath:applicationContext.xml")
  public class TestUser {
      @Autowired
      UserService userService;
      @Test
      public void test01(){
          //这里注意，目标类继承了接口，使用JDK动态代理，必须用接口类型才可以接收到。
  //        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
  //        UserService userService = ioc.getBean(UserService.class);
  //        userService.add(new User());
  
          userService.add(new User());
  
      }
  }
  ```



## 4.5切入点表达式★

### 4.5.1切入点表达式语法

* 回顾

  * 全限定类名=全类名=包名.类名

* 语法

  ```xml
  execution(权限修饰符 返回值类型 包名.类名.方法名(形参类型1,形参类型2,...))
  ```

  **访问控制权限修饰符：**

  - 可选项。
  - 没写，就是4个权限都包括。
  - 写public就表示只包括公开的方法。

  **返回值类型：**

  - 必填项。
  - \* 表示返回值类型任意。

  **包名：**

  - 可选项。
  - 两个点..代表当前包以及子包下的所有类。
  - *..代表所有包下的所有类
  - 省略时表示所有的类

  **类名：**

  - 可选项。
  - *代表所有类。
  - *Service代表匹配以Service结尾的类

  **方法名：**

  - 必填项。
  - *表示所有方法。
  - find*表示所有以find开头的方法。

  **形式参数列表：**

  - 必填项
  - () 表示没有参数的方法
  - (..) 参数类型和个数随意的方法
  - (*) 只有一个参数的方法
  - (*, String) 第一个参数类型随意，第二个参数是String的。

  **异常：**

  - 可选项。
  - 省略时表示任意异常类型。

- **总结:**
  * aop往往作用于service层，推荐使用execution(* *..*Service.*(..))



### 4.5.2切入点表达式抽取

表达式抽取 : 抽取表达式进行复用

- 格式一：定义在通知中：该通知内生效

  ```xml
      <aop:aspect ref="adviceTest">
          <aop:before method="commonFunction" pointcut="execution(* *..*Service.*(..))"></aop:before>
      </aop:aspect>
  ```

- 格式二：定义在切面中：该切面内生效

  ```xml
      <aop:aspect ref="adviceTest">
          <aop:pointcut id="p1" expression="execution(* *..*Service.*(..))"/>
          <aop:before method="commonFunction" pointcut-ref="p1"></aop:before>
      </aop:aspect>
  ```

- 格式三：定义在AOP总配置中：在全部范围内共享

  ```xml
      <aop:config>
              <aop:pointcut id="adviceTest" expression="execution(* *..*Service.*(..))"/>
              <aop:aspect ref="proxy">
                <aop:around method="around" pointcut-ref="myProxy"/>
              </aop:aspect>
      </aop:config>
  ```

- 使用注解@Pointcut抽取

  ```java
      //通过@Pointcut注解，抽取切点表达式到pointcut()方法上
      @Pointcut("execution(* com.atguigu.service.OrderService.*(..))")
      public void pointcut() {
      }
      //使用抽取的切点表达式  
      @Before("pointcut()")
      public void beforeAdvice() {
          System.out.println("我是前置通知");
      }
  ```



## 4.6AOP通知★

### 4.6.1通知类别

* 通知类别

  | 通知类别        | 说明                                                     |
  | --------------- | -------------------------------------------------------- |
  | before          | 前置通知 , 在切入点之前执行                              |
  | after-returning | 正常后置通知, 在切入点之后执行, 当切入点没有异常才执行   |
  | after-throwing  | 异常后置通知, 在切入点之后执行, 当切入点有异常才执行     |
  | after           | 最终通知, 在切入点之后执行, 不管切入点是否有异常都会执行 |
  | around          | 环绕通知, 在切入点之前和切入点之后都会执行               |

* 正常执行顺序：环绕通知开始→前置通知→切入点执行→**后置通知**→最终通知→环绕通知结束

* 模拟异常发生：环绕通知开始→前置通知→切入点执行→**异常后置通知**→最终通知→环绕通知结束

* 关于环绕通知要特别注意！⚠️

  * around通知在切入点环绕之前之后都执行
  * around通知**代为执行切入点方法**，执行后需要手动返回结果给切入点
  * around通知需要手动抛出异常给剩下的通知，不然捕获后不抛下面的通知还会执行
  * around通知需要手动执行切入点

* 使用方法：重点看环绕通知

  ```java
  package com.atguigu.advice;
  
  import org.aspectj.lang.ProceedingJoinPoint;
  import org.springframework.stereotype.Component;
  
  /**
   * 10-通知类别
   * 通知类
   */
  @Component
  public class MyAdvice02 {
  
  
      /**
       * 前置通知
       */
      public void before() {
          System.out.println("MyAdvice02 before 前置通知");
      }
  
  
      /**
       * 正常后置通知 : 在切入点之后执行, 当切入点没有异常才执行
       */
      public void afterReturning() {
          System.out.println("MyAdvice02 afterReturning 正常后置通知");
      }
  
  
      /**
       * 异常后置通知 : 在切入点之后执行, 当切入点有异常才执行
       */
      public void afterThrowing() {
          System.out.println("MyAdvice02 afterThrowing 异常后置通知");
  
      }
  
      /**
       * 后置通知 : 在切入点之后执行, 不管切入点是否有异常都执行
       */
      public void after() {
          System.out.println("MyAdvice02 after 后置通知");
      }
  
      /**
       * 环绕通知 : 在切入点之前执行, 在切入点之后执行
       * @param pjp : 切入点对象
       */
      public Object around(ProceedingJoinPoint pjp) {
          try {
              System.out.println("MyAdvice02 around 环绕通知之前");
  
              //执行被代理类的原方法(切入点) , 需要将切入点执行之后的结果返回
              Object result = pjp.proceed();//切入点
  
              System.out.println("MyAdvice02 around 环绕通知之后");
  
              return 500; // 把结果返回给切入点方法
  
          } catch (Throwable throwable) {
              throwable.printStackTrace();
              //切入点有异常,  继续将异常抛出
              throw new RuntimeException(throwable);
          } finally {
              return -500;
  
          }
      }
  
  
  
  }
  ```

  ```xml
  <!--10-通知类别-->
  <aop:config>
    
      <aop:aspect ref="myAdvice02">
          <aop:pointcut id="p1" expression="execution(* *..*Service.*(..))"/>
          <aop:before method="before" pointcut-ref="p1"></aop:before>
          <aop:after-returning method="afterReturning" pointcut-ref="p1"></aop:after-returning>
          <aop:after-throwing method="afterThrowing" pointcut-ref="p1"></aop:after-throwing>
          <aop:after method="after" pointcut-ref="p1"></aop:after>
          <aop:around method="around" pointcut-ref="p1"></aop:around>
      </aop:aspect>
  
  </aop:config>
  ```

  

### 4.6.2通知获取切入点参数

分为两类：

- around通知获取参数，使用ProceedingJoinPoint获取参数
- 非around通知获取参数，使用JoinPoint获取参数	
- **Spring容器调用通知时传入**了以上两个连接点参数
  - 可通过连接点.getSignature()拿到方法的具体信息
  - 可通过连接点.getArgs()拿到参数信息
  - 环绕通知可通过Object result = 连接点.proceed()代为执行切点并拿到切点的返回值

* 代码实现：

  ```java
  /**
   * 01-通知获取切入点输入参数
   */
  @Component
  public class MyAdvice01 {
  
  
      /**
       * 非环绕通知
       *
       * @param jp : 切入点
       */
      public void before(JoinPoint jp) {
          //获取切入点输入参数
          Object[] args = jp.getArgs();
          System.out.println("MyAdvice01 before 输入参数 : " + Arrays.toString(args));
      }
  
  
      /**
       * 环绕通知
       *
       * @param pjp : 切入点
       * @return
       */
      public Object around(ProceedingJoinPoint pjp) {
          //获取切入点输入参数
          Object[] args = pjp.getArgs();
          System.out.println("MyAdvice01 around 输入参数 : " + Arrays.toString(args));
          Object result = null;
          try {
              result = pjp.proceed();
          } catch (Throwable throwable) {
              throwable.printStackTrace();
          }
  
          return result;
      }
  
  
  }
  ```

  ```xml
  <!--aop配置-->
  <aop:config>
      
      <!--aop切面-->
      <aop:aspect ref="myAdvice01">
          <aop:pointcut id="p1" expression="execution(* *..*Service.*(..))"/>
  
          <aop:around method="around" pointcut-ref="p1"></aop:around>
  
          <aop:before method="before" pointcut-ref="p1"></aop:before>
      </aop:aspect>
      
  </aop:config>
  ```



### 4.6.3通知获取切入点返回值

* 概述

  * before : 无法获取
  * **afterReturning : 可以获取**
  * afterThrowing : 无法获取
  * after : 无法获取
  * **around : 可以获取**

* 应用场景

  * 用aop做日志记录时需要获取方法执行的返回值.

* 代码实现

  ```java
  /**
   * 02-通知获取切入点返回值
   */
  @Component
  public class MyAdvice02 {
  
  
      /**
       * 正常后置通知
       *
       * @param result : 切入点的返回值
       */
      public void afterReturning(Object result) {
          System.out.println("MyAdvice02 afterReturning 返回值 : " + result);
      }
  
  
      /**
       * 环绕通知
       * @param pjp : 切入点
       * @return
       */
      public Object around(ProceedingJoinPoint pjp) {
          Object result = null;
          try {
              result = pjp.proceed();
              System.out.println("MyAdvice02 around 返回值 : " + result);
          } catch (Throwable throwable) {
              throwable.printStackTrace();
          }
  
          return result;
      }
  
  
  }
  ```

  ```xml
  <aop:config>
    <!--为通知标签添加returning="result”属性 :当p1切点执行之后，会把执行结果返回给result这个参数。result是切入点的返回值。-->
      <aop:aspect ref="myAdvice02">
          <aop:pointcut id="p1" expression="execution(* *..*Service.*(..))"/>
  
          <aop:after-returning method="afterReturning"
                               pointcut-ref="p1"
                               returning="result"></aop:after-returning>
          <aop:around method="around" pointcut-ref="p1"></aop:around>
      </aop:aspect>
  
  </aop:config>
  ```



### 4.6.4通知获取切入点异常对象

* 概述

  * before : 无法获取
  * afterReturning : 无法获取
  * **afterThrowing : 可以获取**
  * after : 无法获取
  * **around : 可以获取**

* 应用场景

  * 用aop做日志记录时需要获取方法执行的异常信息.

* 代码实现

  ```java
  /**
   * 03-通知获取切入点异常对象
   */
  @Component
  public class MyAdvice03 {
  
  
      /**
       * 异常后置通知
       * @param e : 切入点的异常信息
       */
      public void afterThrowing(Throwable e){
          System.out.println("MyAdvice03 afterThrowing 异常 : " + e);
      }
  
  
      /**
       * 环绕通知
       * @param pjp : 切入点
       */
      public Object around(ProceedingJoinPoint pjp){
          Object result = null;
          try {
              result = pjp.proceed();
          } catch (Throwable e) {
              System.out.println("MyAdvice03 around 异常 : " + e);
              throw new RuntimeException(e);
          }
          return result;
      }
  
  }
  ```

  ```xml
  <!--03-通知获取切入点异常对象-->
  <!--在xml的通知标签中，throwing="e” ：把切入点执行之后的异常对象给e-->
  <aop:config>
      <aop:aspect ref="myAdvice03">
          <aop:pointcut id="p1" expression="execution(* *..*Service.*(..))"/>
  
          <aop:after-throwing method="afterThrowing"
                               pointcut-ref="p1"
                               throwing="e"></aop:after-throwing>
          <aop:around method="around" pointcut-ref="p1"></aop:around>
      </aop:aspect>
  
  </aop:config>
  ```

  

## 4.7AOP注解★

### 4.7.1AOP注解开发

* 开发步骤
  * ①制作目标对象类
  * ②制作通知类
  * ③编写spring-core.xml
    * 开启AOP注解支持 <aop:aspectj-autoproxy />

* ①制作目标对象类

  ```java
  /**
   * 目标类
   */
  @Service
  public class UserServiceImpl implements UserService {
  
      //链接点
      //切入点
      public int add(User user, Integer num, String msg) {
          System.out.println("UserServiceImpl add");
          System.out.println(1 / 0);
          return 250;
      }
  
      //链接点
      //切入点
      public int delete(Integer id) {
          System.out.println("UserServiceImpl delete");
          return 0;
      }
  
      //链接点
      public int update(User user) {
          System.out.println("UserServiceImpl update");
          return 0;
      }
  
      //链接点
      public User getById(Integer id) {
          System.out.println("UserServiceImpl getById");
          return null;
      }
  }
  ```

* ②制作通知类

  ```java
  /**
   * 04-AOP注解开发
   * 通知类
   */
  @Aspect
  @Component
  public class MyAdvice04 {
  
      @Pointcut("execution(* *..*Service.*(..))")
      public void pointCut1(){
  
      }
  
  
      /**
       * 前置通知
       */
      @Before("pointCut1()")
      public void before() {
          System.out.println("MyAdvice04 before");
      }
  
      /**
       * 正常后置通知
       */
  
      @AfterReturning("pointCut1()")
      public void afterReturning() {
          System.out.println("MyAdvice04 afterReturning");
      }
  
  
      /**
       * 异常后置通知
       */
      @AfterThrowing("pointCut1()")
      public void afterThrowing() {
          System.out.println("MyAdvice04 afterThrowing");
      }
  
  
      /**
       * 后置通知
       */
      @After("pointCut1()")
      public void after() {
          System.out.println("MyAdvice04 after");
      }
  
  
      /**
       * 环绕通知
       */
      @Around("pointCut1()")
      public Object around(ProceedingJoinPoint pjp) {
          System.out.println("MyAdvice04 around 之前");
          Object result = null;
          try {
              result = pjp.proceed();
          } catch (Throwable throwable) {
              throwable.printStackTrace();
              throw new RuntimeException(throwable);
          }
          System.out.println("MyAdvice04 around 之后");
          return result;
      }
  
  
  }
  ```

* ③编写spring-core.xml

  ```xml
  <!--开启AOP注解支持-->
  <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
  ```



### 4.7.2注解获取切点参数

* 概述

  * 获取输入参数将通知分为两类 : ①环绕通知 , ②非环绕通知

* 代码实现

  ```java
  @Aspect
  @Component
  public class MyAdvice05 {
  
    
      /**
       * 非环绕通知
       * @param jp : 切入点
       */
      @Before("execution(* *..*Service.*(..))")
      public void before(JoinPoint jp){
          System.out.println("MyAdvice05 before 输入参数 : " + Arrays.toString(jp.getArgs()));
      }
  
  
      /**
       * 环绕通知
       * @param pjp
       * @return
       */
      @Around("execution(* *..*Service.*(..))")
      public Object around(ProceedingJoinPoint pjp) {
          System.out.println("MyAdvice05 around 输入参数 : " + Arrays.toString(pjp.getArgs()));
          Object result = null;
          try {
              result = pjp.proceed();
          } catch (Throwable throwable) {
              throwable.printStackTrace();
              throw new RuntimeException(throwable);
          }
          return result;
      }
  
  }
  ```



### 4.7.3注解获取切点返回值

* 概述

  * `afterReturning` , `around`可以获取切入点返回值
  * 首先在方法上声明一个返回值参数，然后在注解上添加returning属性

* 代码实现

  ```java
  /**
   * 06-AOP注解获取切入点返回值
   */
  
  @Aspect
  @Component
  public class MyAdvice06 {
  
  
      /**
       * 正常后置通知
       * @param result
       */
      @AfterReturning(value = "execution(* *..*Service.*(..))" , returning = "result")
      public void afterReturning(Object result){
          System.out.println("MyAdvice06 afterReturning 返回值 : " + result);
  
      }
  
  
  
      /**
       * 环绕通知
       * @param pjp
       * @return
       */
      @Around("execution(* *..*Service.*(..))")
      public Object around(ProceedingJoinPoint pjp) {
          Object result = null;
          try {
              result = pjp.proceed();
              System.out.println("MyAdvice06 around 返回值 : " + result);
          } catch (Throwable throwable) {
              throwable.printStackTrace();
              throw new RuntimeException(throwable);
          }
          return result;
      }
  
  }
  ```

  

### 4.7.4注解获取切点异常对象

* 概述

  * `afterThrowing`,`around`可以获取切入点异常对象
  * 首先在方法上声明一个返回值参数，然后在注解上添加throwing属性

* 代码实现

  ```java
  /**
   * 07-AOP注解获取切入点异常对象
   */
  @Aspect
  @Component
  public class MyAdvice07 {
  
  
      /**
       * 正常后置通知
       * @param e : 异常对象
       */
      @AfterThrowing(value = "execution(* *..*Service.*(..))" , throwing = "e")
      public void afterThrowing(Throwable e){
          System.out.println("MyAdvice07 afterReturning 异常信息 : " + e);
      }
  
  
  
      /**
       * 环绕通知
       * @param pjp
       * @return
       */
      @Around("execution(* *..*Service.*(..))")
      public Object around(ProceedingJoinPoint pjp) {
          Object result = null;
          try {
              result = pjp.proceed();
          } catch (Throwable throwable) {
              System.out.println("MyAdvice07 around 异常信息 : " + throwable);
              throw new RuntimeException(throwable);
          }
          return result;
      }
  
  }
  ```




### 4.7.5切面的执行顺序

如果多个切面的话，可以使用@Order注解来标识切面类控制顺序。@Order注解的value指定一个整数型的数字，数字越小，优先级越高。

```java
@Compoent
@Aspect
@Order(5)
public class MyAspect {
   ......
}
```

多个通知时的执行顺序：

```
环绕通知开始1
前置通知1
环绕通知开始2
前置通知2
执行纵向业务
后置通知2
最终通知2
环绕通知结束2
后置通知1
最终通知1
环绕通知结束1
```





## 4.8AOP根据类型获取bean★★

**AOP对根据类型获取bean的影响十分重大，分为两个场景，必须掌握的知识点！**⚠️

**只要目标类使用接口，就意味着会使用JDK的动态代理，代理类对象会实现目标类实现的接口，替代目标类对象存储在spring容器中！⚠️**

**只要目标类没有使用接口，就意味着会使用CGLIB代理，代理类对象继承于目标类，替代目标类对象存储在spring容器中！⚠️**

### 4.8.1目标类实现接口场景★

* 场景描述：
  * 声明一个接口，接口有一个实现类
  * 创建一个通知类，对上面接口的实现子类应用通知 
  * ①根据接口类型获取bean 
  * ②根据目标类的类型获取bean
* ①根据接口类型获取bean 
  * 可以获取
* ②根据目标类的类型获取bean
  * 无法获取
* 原因分析
  * UserServiceImpl类(`被代理类`)实现UserService接口, 当运用AOP通知 , 意味着会使用JDK的动态代理, 代理类和目标类实现同一个接口，代理类和接口类之间存在多态性，所以可以通过接口的类型获取代理类。
  * 使用`@Service`注解标记UserServiceImpl类, 实际上是将`代理类对象`放入到Spring容器中




### 4.8.2目标类没有接口场景★

* 场景描述：

  * 只有一个未实现任何接口的目标类
  * 创建一个通知类，对上面的类应用通知 
  * ①根据目标类的类型获取bean
* ①根据目标类的类型获取bean
  * 可以获取
* 原因分析

  * StudentServiceImpl类(`被代理类`)没有实现任何接口,当运用AOP通知 , 意味着会使用CGLIB的动态代理, 代理类继承于目标类，代理类和目标类之间存在多态性，所以可以通过目标类的类型获取代理类。
  * 使用`@Service`注解标记StudentServiceImpl类, 实际上是将`代理类对象`放入到Spring容器中
* 总结
  * 基于接口就是jdk动态代理, 没有接口就是CGLIB动态代理
  * jdk动态代理用接口类型获取代理类，CGLIB动态代理用目标类类型获取代理类



# 5 JdbcTemplate

为了使JDBC更加易于使用，Spring在JDBC API上定义了一个抽象层，以此建立一个JDBC存取框架。

## 5.1JdbcTemplate简介



作为Spring JDBC框架的核心，JDBC模板的设计目的是为不同类型的JDBC操作提供模板方法。通过这种方式，可以在尽可能保留灵活性的情况下，将数据库存取的工作量降到最低。 

概述：

- JdbcTemplate是Spring提供的一个JDBC模板类，是对JDBC的封装，简化JDBC代码。
- 可以将Spring的JdbcTemplate看作是一个小型的轻量级持久化层框架，和我们之前使用过的DBUtils风格非常接近。
- JdbcTemplate实现类**org.springframework.jdbc.core.JdbcTemplate**的数据源属性DataSource datasource，需要我们注入。



## 5.2环境搭建

搭建步骤：

- 第一步：导入依赖的jar包：
  - JdbcTemplate需要的jar包
    - spring-jdbc-5.3.1.jar
    - spring-orm-5.3.1.jar
    - spring-tx-5.3.1.jar
  - 连接数据库的驱动jar包和数据源
    - mysql-connector-java-5.1.37-bin.jar
    - druid-1.1.10.jar
- 第二步：创建数据库和表以及Bean类
- 第三步：将JdbcTemplate类配置到Spring配置文件中，纳入Bean管理
- 第四步：通过外部的db.properties文件，配置数据源，并放到Spring容器中
- 第五步：修改Spring配置文件，把数据源传递给JdbcTemplate
- 第六步：编写测试程序

实际操作：

- 第一步：导入依赖的jar包：

  ```xml
  <!--spring-context-->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.3.1</version>
  </dependency>
  <!--spring-jdbc-->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.3.1</version>
  </dependency>
  <!--spring-orm-->
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-orm</artifactId>
      <version>5.3.1</version>
  </dependency>
  <!--导入druid的jar包-->
  <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.10</version>
  </dependency>
  <!--导入mysql的jar包-->
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.25</version>
  </dependency>
  <!--junit-->
  <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
  </dependency>
  ```

- 第二步：创建数据库和表以及Bean类

- 第三步：将JdbcTemplate类配置到Spring配置文件中，纳入Bean管理

  ```xml
    	<!--直接写框架内的全路径即可-->
      <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate"></bean>
  ```

- 第四步：通过外部的db.properties文件，配置数据源，并放到Spring容器中（配置数据源方法在下一小节）

  ```xml
      <!--加载数据源属性文件-->
      <context:property-placeholder location="classpath:db.properties"/>
    
  		<!--MyDataSource为自定义的数据源，还可以使用德鲁伊数据源等-->
      <bean id="myDataSource" class="com.spring5.jdbc.MyDataSource">
          <property name="driverClassName" value="${jdbc.driver}"/>
          <property name="url" value="${jdbc.url}"/>
          <property name="username" value="${jdbc.username}"/>
          <property name="password" value="${jdbc.password}"/>
      </bean>
  ```

- 第五步：修改Spring配置文件，把数据源传递给JdbcTemplate

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
   
    
      <!--加载数据源属性文件-->
      <context:property-placeholder location="classpath:db.properties"/>
    
  		<!--MyDataSource为自定义的数据源，还可以使用德鲁伊数据源等-->
      <bean id="myDataSource" class="com.spring5.jdbc.MyDataSource">
          <property name="driverClassName" value="${jdbc.driver}"/>
          <property name="url" value="${jdbc.url}"/>
          <property name="username" value="${jdbc.username}"/>
          <property name="password" value="${jdbc.password}"/>
      </bean>
    
    	<!--直接写框架内的全路径即可-->
      <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
          <!--传递数据源-->
          <property name="dataSource" ref="myDataSource"/>
      </bean>
  </beans>
  ```

- 第六步：编写测试程序

  ```java
      @Test
      public void testInsert(){
          // 获取JdbcTemplate对象
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
          JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
          // 执行插入操作
          // 注意：insert delete update的sql语句，都是执行update方法。
          String sql = "insert into t_user(id,real_name,age) values(?,?,?)";
          int count = jdbcTemplate.update(sql, null, "张三", 30);
          System.out.println("插入的记录条数：" + count);
      }
  ```

  

## 5.3配置数据源

### 5.3.1自定义数据源

所有的数据源都要实现javax.sql.DataSource接口，我们可以实现该接口自定义数据源

```java
package com.powernode.spring6.jdbc;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class MyDataSource implements DataSource {
    // 添加4个属性
    private String driver;
    private String url;
    private String username;
    private String password;

    // 提供4个setter方法
    public void setDriver(String driver) {
        this.driver = driver;
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

    // 重点写怎么获取Connection对象就行。其他方法不用管。
    @Override
    public Connection getConnection() throws SQLException {
        try {
          	//注册驱动
            Class.forName(driver);
          	//获取connection连接对象
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

  	..........
  
}

```

然后将自定义的数据源纳入到spring容器内管理，并完成属性注入即可

```xml
    <!--加载数据源属性文件-->
    <context:property-placeholder location="classpath:db.properties"/>   
		<bean id="myDataSource" class="com.spring5.jdbc.MyDataSource">
        <!--注入数据库信息属性-->
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
```



### 5.3.2使用德鲁伊数据源

使用德鲁伊连接池作为数据源，首先要引入依赖

```xml
<dependency>
  <groupId>com.alibaba</groupId>
  <artifactId>druid</artifactId>
  <version>1.1.8</version>
</dependency>
```

将德鲁伊中的数据源配置到spring配置文件中

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--配置德鲁伊数据源-->
    <bean id="druidDataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="druidDataSource"/>
    </bean>
</beans>
```



## 5.4JdbcTemplate的使用

### 5.4.1JdbcTemplate-CUD操作

JdbcTemplate模板的insert、update、delete操作的sql语句，都是执行update方法。

update方法有两个参数：

- 第一个参数：要执行的SQL语句。（SQL语句中可能会有占位符 ? ）

- 第二个参数：可变长参数，参数的个数可以是0个，也可以是多个。一般是SQL语句中有几个问号，则对应几个参数

  ```java
      // 执行插入操作
      // 注意：insert delete update的sql语句，都是执行update方法。
      String sql = "insert into t_user(id,real_name,age) values(?,?,?)";
      int count = jdbcTemplate.update(sql, null, "张三", 30);
  
      // 执行更新操作
      String sql = "update t_user set real_name = ?, age = ? where id = ?";
      int count = jdbcTemplate.update(sql, "张三丰", 55, 1);
  
      // 执行delete
      String sql = "delete from t_user where id = ?";
      int count = jdbcTemplate.update(sql, 1);
  ```



### 5.4.2JdbcTemplate-R操作

JdbcTemplate模板的select操作的sql语句，**查询多个执行query**方法，**查询其他执行queryForObject**方法，**查询结果自动适应下划线和小驼峰格式**

queryForObject方法三个参数：

- 第一个参数：sql语句
- 第二个参数：Bean属性值和数据库记录行的映射对象。在构造方法中指定映射的对象类型。
- 第三个参数：可变长参数，给sql语句的占位符问号传值。

查询单个对象：

```java
    // 执行select查询单个
    String sql = "select * from employees where id=?";
    Employee employee = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class),2);
    System.out.println(employee);
```

查询多个对象：

```java
    // 执行select查询多个
    String sql = "select * from employees";
    // 注意：这里使用的query
    List employees = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    System.out.println(employees);
```

查询某个属性值：

```java
    // 执行select查询某个值
    String sql = "select last_name from employees where id=?";
    // 将该值类的class对象当作第二个参数即可
    String name = jdbcTemplate.queryForObject(sql, String.class,1);
    System.out.println(name);
```



### 5.4.3JdbcTemplate-批处理

**批处理使用batchUpdate方法！**

批量添加：

- **使用batchUpdate方法**

- **通过Object[]去包装一条记录**

- List集合里存放Object数组，数组索引位置必须和sql占位符顺序一致

- sql语句书写的的格式还是针对一条的

  ```java
      @Test
      public void testInsert() {
          // 获取JdbcTemplate对象
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
          JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
  
          // 创建List集合
          List<Object[]> list = new ArrayList<>();
          Object[] objArr1 = {"小a", "xx@email.com", 1, 666.6, 1};
          Object[] objArr2 = {"小b", "xx@email.com", 1, 666.6, 1};
          Object[] objArr3 = {"小c", "xx@email.com", 1, 666.6, 1};
          list.add(objArr1);
          list.add(objArr2);
          list.add(objArr3);
  
          // 执行批量添加
          String sql = "insert into employees values(null,?,?,?,?,?)";
          int[] results = jdbcTemplate.batchUpdate(sql, list);
          System.out.println(Arrays.toString(results));
      }
  ```

批量删除：

- **使用batchUpdate方法**

- **通过Object[]去包装一条记录**

- List集合里存放Object数组，数组索引位置必须和sql占位符顺序一致

- sql语句书写的的格式还是针对一条的

  ```java
      @Test
      public void testDelete() {
          // 获取JdbcTemplate对象
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
          JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
  
          // 创建List集合
          List list = new ArrayList<>();
          Object[] objArr1 = {34};
          Object[] objArr2 = {35};
          Object[] objArr3 = {38};
          list.add(objArr1);
          list.add(objArr2);
          list.add(objArr3);
  
          // 执行批量删除
          String sql = "delete from employees where id=?";
          int[] results = jdbcTemplate.batchUpdate(sql, list);
          System.out.println(Arrays.toString(results));
      }
  ```

批量修改：

- **使用batchUpdate方法**

- **通过Object[]去包装一条记录**

- List集合里存放Object数组，数组索引位置必须和sql占位符顺序一致

- sql语句书写的的格式还是针对一条的

  ```java
      @Test
      public void testUpdate() {
          // 获取JdbcTemplate对象
          ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
          JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
  
          // 创建List集合
          List list = new ArrayList<>();
          Object[] objArr1 = {"aa", 99.9, 1};
          Object[] objArr2 = {"bb", 99.9, 2};
          list.add(objArr1);
          list.add(objArr2);
  
          // 执行批量修改
          String sql = "update employees set last_name=?,salary=? where id=?";
          int[] results = jdbcTemplate.batchUpdate(sql, list);
          System.out.println(Arrays.toString(results));
      }
  ```

  

### 5.4.4使用回调函数

注册回调函数：当execute()执行时，回调函数中的doInPreparedStatement方法也会自动执行

需要在doInPreparedStatement方法内手动给sql语句的占位符赋值及处理结果集和返回最终结果

一般在需要对查询到的结果进行处理时使用

```java
    @Test
    public void testCallBack() {
        // 获取JdbcTemplate对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "select * from employees where id=?";

        Employee employee = jdbcTemplate.execute(sql, new PreparedStatementCallback() {
            @Override
            public Employee doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                //给占位符赋值
                ps.setInt(1, 1);
                //返回结果集
                ResultSet rs = ps.executeQuery();

                //迭代处理结果集
                Employee emp = new Employee();
                if (rs.next()) {
                    emp.setId(rs.getInt("id"));
                    emp.setGender(rs.getInt("gender"));
                    emp.setLastName(rs.getString("last_name"));
                    emp.setEmail(rs.getString("email"));
                    emp.setSalary(rs.getDouble("salary"));
                    emp.setDeptId(rs.getInt("dept_id"));
                }
                return emp;
            }
        });
        System.out.println(employee);
    }
```





# 6 Spring事务

**Spring事务管理分为两大类 ，编程式事务和声明式事务，实际应用中以声明式事务为主**

## 6.1Spring事务概述

* 事务ACID
  * 原子性, 一致性, 隔离性, 持久性
* 隔离级别
  * 读未提交(read uncommitted), 读已提交(read committed), 可重复读(repeatable read), 串行化(serializable)
* 存在问题
  * 脏读, 不可重复读, 幻读, 效率低
* 概述
  * Spring事务管理分为两大类 : ①编程式事务 , ②声明式事务
* ①编程式事务 : `原理`
  * 使用java代码进行事务管理
* ②声明式事务
  * xml声明 : 使用xml配置方式进行事务管理
  * 注解声明 : 使用注解配置方式进行事务管理
* 核心API
  * PlatformTransactionManager : 平台事务管理器
  * TransactionDefinition : 事务配置类
  * TransactionStatus : 事务状态类



## 6.2事务核心接口概述★

**PlatformTransactionManager接口：**

* 概述

  * 定义管理事务的常用方法

* 常用方法

  ```java
  //①开启事务
  TransactionStatus getTransaction(@Nullable TransactionDefinition definition) ;
  
  //②提交事务
  void commit(TransactionStatus status);
  
  //③回滚事务
  void rollback(TransactionStatus status) ;
  ```

* 继承结构

  * **DataSourceTransactionManager **: 适用于`JdbcTemplate` 和`mybatis`框架

  * HibernateTransactionManager : 适用于`hibernate`框架

  * JpaTransactionManager : 使用`jpa`框架


**TransactionDefinition接口：**

* 概述

  * 对事务属性(`传播行为`,`隔离级别`,`超时`,`只读性`)进行配置

* 常用方法

  ```java
  //获取传播行为
  default int getPropagationBehavior() {
      return 0;
  }
  
  
  //设置传播行为
  public final void setPropagationBehavior(int propagationBehavior) {
  }
  
  //获取隔离级别
  default int getIsolationLevel() {
      return -1;
  }
  
  //设置隔离级别
  public final void setIsolationLevel(int isolationLevel) {
  
  }
  
  
  //获取超时
  default int getTimeout() {
      return -1;
  }
  
  
  //设置超时
  public final void setTimeout(int timeout) {
  
  }
  
  //获取只读性
  default boolean isReadOnly() {
      return false;
  }
  
  //设置只读性
  public final void setReadOnly(boolean readOnly) {
      this.readOnly = readOnly;
  }
  ```

* 继承结构

  * DefaultTransactionDefinition/DefaultTransactionAttribute


**TransactionStatus接口：**

* 概述
  * 事务状态类 , 对于开发人员来说, 并不会直接使用它 , 而是把`TransactionStatus`对象当作参数传递给`PlatformTransactionManager`对象使用
  * hasSavepoint()判断是否有保存点，业务开启前设置保存点，异常后会滚，类似于游戏存档。
  * flush()更新事务状态
* 继承结构
  * DefaultTransactionStatus



## 6.3事务管理环境搭建★

* 需求

  * 铁甲小宝向玛卡巴卡转账1000元.

* 开发步骤

  * ①定义service层代码
  * ②定义dao层代码
  * ③代码测试

* ①定义service层代码

  ```java
  @Service
  public class UserServiceImpl implements UserService {
  
      @Autowired
      private UserDao userDao;
  
      public void transfer(String outName, String inName, Double money) {
          //铁甲小宝出账1000元
          userDao.outMoney(outName, money);
  
        	//故意模拟的异常
          System.out.println(1 / 0);
  
          //玛卡巴卡入账1000元
          userDao.inMoney(inName, money);
      }
  }
  ```

* ②定义dao层代码

  ```java
  @Repository
  public class UserDaoImpl implements UserDao {
  
      // 将Spring容器中的JdbcTemplate对象注入使用
      @Autowired
      private JdbcTemplate jdbcTemplate;
  
  
      public void outMoney(String outName, Double money) {
          jdbcTemplate.update(
                  "update t_user set money = money - ? where name = ?",
                  money,
                  outName
          );
      }
  
      public void inMoney(String inName, Double money) {
          jdbcTemplate.update(
                  "update t_user set money = money + ? where name = ?",
                  money,
                  inName
          );
      }
  }
  ```

* ③代码测试

  ```java
  @Test
  public void transfer() {
      userService.transfer(
              "铁甲小宝",
              "玛卡巴卡",
              1000.0);
  }
  ```

* 存在问题

  * 当程序出现问题, 转账功能不满足事务的原子性
  * 即出现异常后，铁甲小宝转出了钱，玛卡巴卡没收到
  * **针对以上问题，必须开启事务，借助事务的提交和回滚才能解决**



## 6.4Spring编程式事务

使用`PlatformTransactionManager`、`TransactionDefinition`、`TransactionStatus`这三个接口的具体实现类，来实现spring遍程式事务。

### 6.4.1编程式事务基础版

* 概述

  * 使用`DataSourceTransactionManager ` ,`DefaultTransactionDefinition` , `DefaultTransactionStatus`这三个类进行事务管理

* 代码实现

  ```java
  @Service
  public class UserServiceImpl implements UserService {
  
      @Autowired
      private UserDao userDao;
  		
    	//创建事务管理类对象
      @Autowired
      private PlatformTransactionManager transactionManager;
  
    	//创建事务属性类对象，这里注入的definition1，因为是增删改操作
      @Autowired
      @Qualifier("definition1")
      private TransactionDefinition definition;
  
      public void transfer(String outName, String inName, Double money) {
          TransactionStatus status = null;
  
          try {
              //开启事务，并存储事务状态
              status = transactionManager.getTransaction(definition);
              //铁甲小宝出账1000元
              userDao.outMoney(outName, money);
  						
            	//模拟异常
              System.out.println(1 / 0);
  
              //玛卡巴卡入账1000元
              userDao.inMoney(inName, money);
            
              //没有异常就提交
              transactionManager.commit(status);
  
          } catch (Exception e) {
              e.printStackTrace();
              //有异常就回滚
              transactionManager.rollback(status);
          }
  
  
      }
  }
  ```
  
  DefaultTransactionDefinition类的readOnly属性为false适用于增删改、为true适用于查询，所以根据这两类数据操作，要配置两个事务属性类。
  
  ```xml
  <!--DefaultTransactionDefinition : 适用于增删改操作-->
  <bean id="definition1"  class="org.springframework.transaction.support.DefaultTransactionDefinition">
      <!--传播行为-->
      <property name="propagationBehaviorName" value="PROPAGATION_REQUIRED"></property>
      <!--隔离级别-->
      <property name="isolationLevelName" value="ISOLATION_REPEATABLE_READ"></property>
      <!--超时-->
      <property name="timeout" value="30"></property>
      <!--只读性
          readOnly=true适用于查询 , readOnly=false适用于增删改
      -->
      <property name="readOnly" value="false"></property>
  </bean>
  
  
  <!--DefaultTransactionDefinition : 适用于查询操作-->
  <bean id="definition2" class="org.springframework.transaction.support.DefaultTransactionDefinition">
      <!--传播行为-->
      <property name="propagationBehaviorName" value="PROPAGATION_REQUIRED"></property>
      <!--隔离级别-->
      <property name="isolationLevelName" value="ISOLATION_REPEATABLE_READ"></property>
      <!--超时-->
      <property name="timeout" value="30"></property>
      <!--只读性
          readOnly=true适用于查询 , readOnly=false适用于增删改
      -->
      <property name="readOnly" value="true"></property>
  </bean>
  ```

* 存在问题

  * ①事务管理比较冗余, 可以抽取成工具类;
  * ②`开启事务`, `提交事务`, `回滚事务`属于次要功能, 可以将它们放入到AOP通知.



### 6.4.2编程式事务优化版

- 概述

  * 解决事务管理冗余问题, 将编程式事务抽取成工具类

- 代码实现

  ```java
  /**
   * 事务管理的工具类
   *  开启事务
   *  提交事务
   *  回滚事务
   */
  @Component
  public class MyTransactionManager {
  
      @Autowired
      private PlatformTransactionManager transactionManager;
  
  
      @Autowired
      @Qualifier("definition1")
      private TransactionDefinition definition;
  
  
  
      @Autowired
      private TransactionDefinition definition2;
  
      /**
       * 增删改方法的开启事务
       * @return
       */
      public TransactionStatus dmlStartTransaction() {
          TransactionStatus status = transactionManager.getTransaction(definition);
          return status;
      }
  
  
      /**
       * 查询方法的开启事务
       * @return
       */
      public TransactionStatus dqlStartTransaction() {
          TransactionStatus status = transactionManager.getTransaction(definition2);
          return status;
      }
  
  
      /**
       * 提交事务
       * @param status
       */
      public void commit(TransactionStatus status) {
          transactionManager.commit(status);
      }
  
  
      /**
       * 回滚事务
       * @param status
       */
      public void rollback(TransactionStatus status) {
          transactionManager.rollback(status);
      }
  }
  ```


- 测试封装的编程式事务类

  ```java
  @Service
  public class UserServiceImpl implements UserService {
  
      @Autowired
      private UserDao userDao;
  
      @Autowired
      private MyTransactionManager transactionManager;
  
  
      public void transfer(String outName, String inName, Double money) {
          TransactionStatus status = null;
          try {
              //开启事务,使用dmlStartTransaction()进行增删改
              status = transactionManager.dmlStartTransaction();
              //铁甲小宝出账1000元
              userDao.outMoney(outName, money);
  						
            	//模拟异常
              System.out.println(1 / 0);
  
              //玛卡巴卡入账1000元
              userDao.inMoney(inName, money);
              //没有异常就提交
              transactionManager.commit(status);
  
          } catch (Exception e) {
              e.printStackTrace();
              //有异常就回滚
              transactionManager.rollback(status);
          }
  
  
      }
  
      public User getById(Integer id) {
          TransactionStatus status = null;
          try {
              //开启事务,使用dqlStartTransaction()进行查询
              status = transactionManager.dqlStartTransaction();
              User user = userDao.getById(id);
              //没有异常就提交
              transactionManager.commit(status);
              return user;
          } catch (TransactionException e) {
              e.printStackTrace();
              //有异常就回滚
              transactionManager.rollback(status);
          }
  
          return null;
      }
  
      public int add(User user) {
          return userDao.add(user);
      }
  
      public int delete(Integer id) {
          return userDao.delete(id);
      }
  
      public int update(User user) {
          return userDao.update(user);
      }
  
  
      public List<User> findAll() {
          return userDao.findAll();
      }
  
  }



### 6.4.3编程式事务最终版★

* 概述

  * `开启事务`, `提交事务`, `回滚事务`属于次要功能/共性功能, 可以将它们放入到AOP通知
  * 因为readOnly属性值的原因，增删改和查询要使用不同的环绕通知，所以要写两个通知
  * **注意：一定要将service中的异常抛出，确保能抛到通知中，才可以捕捉到异常进行回滚！**⚠️

* ①service接口及其实现

  ```java
  public interface UserService {
  
      /**
       * 转账
       * @param outName : 出账人
       * @param inName : 入账人
       * @param money : 金额
       */
      void transfer(String outName, String inName, Double money) throws Exception;
  
  
      int add(User user) throws Exception;
  
      int delete(Integer id) throws Exception;
  
  
      int update(User user) throws Exception;
  
  
      User getById(Integer id) throws Exception;
  
      List<User> findAll() throws Exception;
  
  }
  ```

  ```java
  @Service
  public class UserServiceImpl implements UserService {
  
      @Autowired
      private UserDao userDao;
  
  
      public void transfer(String outName, String inName, Double money) throws Exception {
          TransactionStatus status = null;
          //铁甲小宝出账1000元
          userDao.outMoney(outName, money);
  				
        	//模拟异常
          System.out.println(1 / 0);
  
          //玛卡巴卡入账1000元
          userDao.inMoney(inName, money);
  
      }
  
      public User getById(Integer id) throws Exception {
          User user = userDao.getById(id);
          return user;
  
      }
  
      public int add(User user) {
          return userDao.add(user);
      }
  
      public int delete(Integer id) {
          return userDao.delete(id);
      }
  
      public int update(User user) {
          return userDao.update(user);
      }
  
  
      public List<User> findAll() {
          return userDao.findAll();
      }
  
  }
  ```

* ②事务通知类

  ```java
  /**
   * 01-Spring编程式事务最终版
   * 事务通知类
   */
  @Component
  public class TxAdvice {
  
  
      @Autowired
      private MyTransactionManager transactionManager;
  
  
      /**
       * 增删改的环绕通知 (readOnly=false)
       * @param pjp
       * @return
       */
      public Object dmlAround(ProceedingJoinPoint pjp) {
          Object result = null;
          TransactionStatus status = null;
          try {
  
              //开启事务
              status = transactionManager.dmlStartTransaction();
  						
            	//执行切点
              result = pjp.proceed();
  
              //没异常, 提交事务
              transactionManager.commit(status);
  
          } catch (Throwable throwable) {
              throwable.printStackTrace();
              //有异常 , 回滚事务
              transactionManager.rollback(status);
          }
          return result;
  
      }
  
  
      /**
       * 查询的环绕通知 (readOnly=true)
       * @param pjp
       * @return
       */
      public Object dqlAround(ProceedingJoinPoint pjp) {
          Object result = null;
          TransactionStatus status = null;
          try {
  
              //开启事务
              status = transactionManager.dqlStartTransaction();
  						
              //执行切点
              result = pjp.proceed();
  
              //没异常, 提交事务
              transactionManager.commit(status);
  
          } catch (Throwable throwable) {
              throwable.printStackTrace();
              //有异常 , 回滚事务
              transactionManager.rollback(status);
          }
          return result;
  
      }
  
  }
  ```

* ③第一种配置方式：xml配置

  ```xml
  <!--xml配置-->
  <aop:config>
  
      <aop:aspect ref="txAdvice">
          <aop:pointcut id="p1" expression="execution(* *..*Service.transfer(..))"/>
          <aop:around method="dmlAround" pointcut-ref="p1"></aop:around>
      </aop:aspect>
  
      <aop:aspect ref="txAdvice">
          <aop:pointcut id="p2" expression="execution(* *..*Service.getById(..))"/>
          <aop:around method="dqlAround" pointcut-ref="p2"></aop:around>
      </aop:aspect>
  
      <aop:aspect ref="txAdvice">
          <aop:pointcut id="p3" expression="execution(* *..*Service.add(..))"/>
          <aop:around method="dmlAround" pointcut-ref="p3"></aop:around>
      </aop:aspect>
  </aop:config>
  ```

* ③第二种配置方式：注解配置

  ```xml
  <!--开启AOP注解支持-->
  <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
  ```

  ```java
  @Aspect
  @Component
  public class TxAdvice {
  
  
      @Autowired
      private MyTransactionManager transactionManager;
      
  
      /**
       * 增删改的环绕通知 (readOnly=false)
       * @param pjp
       * @return
       */
      @Around(value = "execution(* *..*Service.transfer(..))")
      public Object transferAround(ProceedingJoinPoint pjp) {
          Object result = null;
          TransactionStatus status = null;
          try {
  
              //开启事务
              status = transactionManager.dmlStartTransaction();
  
              result = pjp.proceed();
  
              //没异常, 提交事务
              transactionManager.commit(status);
  
          } catch (Throwable throwable) {
              throwable.printStackTrace();
              //有异常 , 回滚事务
              transactionManager.rollback(status);
          }
          return result;
  
      }
  
      /**
       * 查询的环绕通知 (readOnly=true)
       * @param pjp
       * @return
       */
      @Around("execution(* *..*Service.getById(..))")
      public Object dqlAround(ProceedingJoinPoint pjp) {
          Object result = null;
          TransactionStatus status = null;
          try {
  
              //开启事务
              status = transactionManager.dqlStartTransaction();
  
              result = pjp.proceed();
  
              //没异常, 提交事务
              transactionManager.commit(status);
  
          } catch (Throwable throwable) {
              throwable.printStackTrace();
              //有异常 , 回滚事务
              transactionManager.rollback(status);
          }
          return result;
  
      }
  
  }
  ```

* 存在问题

  * 使用编程式事务非常麻烦. 但是属于Spring事务管理的原理



## 6.5Spring声明式事务

**在`Spring编程式事务最终版`中定义的`TxAdvice`事务通知类, Spring框架已经定义好了, 开发人员只需要配置使用即可**

* 声明式事务两种使用方式：
  * xml声明
  * 注解声明

### 6.5.1xml声明式事务的使用★

* 概述

  * 使用`xml配置`的方式来使用Spring框架提供的事务通知类
  * **注意：开启tx命名空间时，一定要注意导入的类，默认导入的类很容易导错**

* 代码实现

  * 配置事务通知类的标签：<tx:advice id="xxx"> (要引入tx命名空间)
    * 配置事务通知类的方法<tx:attributes ><tx:attributes />
    * 对每个方法置事务属性：<tx:method name="transfer" read-only="false"/>

  * 配置事务通知类作用到切入点上的标签：<aop:advisor advice-ref="xxx" pointcut-ref="xxx"></aop:advisor >

* **配置完成后，事务通知类中的方法可以作用到切面中的所有方法，当使用同名方法时，就会使用对应方法的事务属性**

  ```xml
  <!--配置Spring自带事务通知类-->
  <tx:advice id="txAdvice">
      <tx:attributes>
          <!--transfer方法的事务属性-->
          <tx:method name="transfer" read-only="false"/>
          <tx:method name="add" read-only="false"/>
      </tx:attributes>
  </tx:advice>
  
  <aop:config>
  
      <aop:pointcut id="p1" expression="execution(* *..*Service.*(..))"/>
    	<!--将txAdvice事务通知类作用到切入点上-->
      <aop:advisor advice-ref="txAdvice" pointcut-ref="p1"></aop:advisor>
  
  </aop:config>
  ```



### 6.5.2xml声明式事务的属性★

* 配置Spring自带事务通知类

  * 可使用通配符适配更多同类型方法

* 配置方法的事务属性：

  * name：方法名称
  * propagation：传播行为
  * isolation：隔离级别
  * timeout：超时时间
  * read-only：只查询

* 方法少时直接写方法名称，方法多时使用通配符：

  ```xml
  <!--配置Spring自带事务通知类-->
  <tx:advice id="txAdvice">
      <tx:attributes>
          <!--transfer方法的事务属性-->
          <tx:method name="transfer" propagation="REQUIRED" isolation="REPEATABLE_READ" timeout="30" read-only="false"/>
  
          <tx:method name="add*" propagation="REQUIRED" isolation="REPEATABLE_READ" timeout="30" read-only="false"/>
          <tx:method name="delete*" propagation="REQUIRED" isolation="REPEATABLE_READ" timeout="30" read-only="false"/>
          <tx:method name="update*" propagation="REQUIRED" isolation="REPEATABLE_READ" timeout="30" read-only="false"/>
  
          <tx:method name="get*" propagation="REQUIRED" isolation="REPEATABLE_READ" timeout="30" read-only="true"/>
          <tx:method name="find*" propagation="REQUIRED" isolation="REPEATABLE_READ" timeout="30" read-only="true"/>
      </tx:attributes>
  </tx:advice>
  
  
  <aop:config>
  
      <aop:pointcut id="p1" expression="execution(* *..*Service.*(..))"/>
      <aop:advisor advice-ref="txAdvice" pointcut-ref="p1"></aop:advisor>
  
  </aop:config>
  ```





## 6.6注解声明式事务★

* 开发步骤

  * ①开启注解式事务
    * 指定事务管理器
  * ②修改service层代码
    * 使用`@Transactional`注解

* ①开启注解式事务

  ```xml
  <!--开启注解声明式事务-->
  <tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>
  ```

* ②修改service层代码

  * 添加**@Transactional**注解，并配置传播行为、隔离级别、只查询、超时等属性信息。
  * 类上添加了注解，方法上又添加了注解时，**方法上的注解会覆盖掉类上的注解。**
  * 企业开发中, 业务类中查询操作多, 增删改操作少，所以类上的readOnly一般设置为true。

  ```java
  @Transactional(
          propagation = Propagation.REQUIRED,
          isolation = Isolation.REPEATABLE_READ,
          readOnly = true,
          timeout = 30
  )
  @Service
  public class UserServiceImpl implements UserService {
  
      @Autowired
      private UserDao userDao;
  
  
      @Transactional(readOnly = false)
      public void transfer(String outName, String inName, Double money) throws Exception {
          TransactionStatus status = null;
          //李亚南出账1000元
          userDao.outMoney(outName, money);
  
          System.out.println(1 / 0);
  
          //李涛入账1000元
          userDao.inMoney(inName, money);
  
      }
  
      @Transactional(readOnly = false)
      public int add(User user) {
          return userDao.add(user);
      }
  
  
      @Transactional(readOnly = false)
      public int delete(Integer id) {
          return userDao.delete(id);
      }
  
  
      @Transactional(readOnly = false)
      public int update(User user) {
          return userDao.update(user);
      }
  
      public User getById(Integer id) throws Exception {
          User user = userDao.getById(id);
          return user;
  
      }
  
  
      public List<User> findAll() {
          return userDao.findAll();
      }
  
  }
  
  ```



## 6.7事务的属性★

### 6.7.1事务的readOnly属性

- 概述

  * 对一个查询操作来说，如果我们把它设置成只读，就能够明确告诉数据库，这个操作不涉及写操作。 这样数据库就能够针对查询操作来进行优化。

- 注意事项

  * 查询操作, readOnly=true或readOnly=false , 都不会报错, 建议设置为readOnly=true

  * 增删改操作, 只能将readOnly=false, 否则报错

* 代码实现

  ```java
  @Transactional(readOnly = false)
  public User getById(Integer id) throws Exception {
      User user = userDao.getById(id);
      return user;
  }
  
  @Transactional(readOnly = true)
  public void transfer(String outName, String inName, Double money) throws Exception {
      TransactionStatus status = null;
      //铁甲小宝出账1000元
      userDao.outMoney(outName, money);
  
      //玛卡巴卡入账1000元
      userDao.inMoney(inName, money);
  
  }
  ```



### 6.7.2事务的timeout属性

* 概述

  * 当一个程序长时间占用数据库链接, 该程序的操作应该被结束并回滚 , 将占用的链接给其他正常的程序使用

* 注意事项

  * 如果`超时代码`在`操作数据库`之后, 是不会触发timeout属性 , 也就不会回滚事务
  * 因为操作数据库之后，数据库早已连接上并操作完毕了，根本不存在超时响应

* 代码实现

  ```java
  @Transactional(timeout = 5)//如果程序超过5秒没有响应就结束事务并回滚
  public int add(User user) {
      
      //睡眠6秒
      try {
          Thread.sleep(6000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      
      //操作数据库
      int add = userDao.add(user);
      
      return add;
  }
  ```



### 6.7.3事务的回滚异常★

* 概述

  * **默认情况，遇到运行时异常可以回滚，遇到编译期异常不能回滚**
  * rollbackFor : 设置需要回滚的异常，属性值为异常类的字节码对象
  * noRollbackFor : 设置不需要回滚的异常，属性值为异常类的字节码对象

* 总结

  * 一般情况下, 都是设置`rollbackFor = Exception.class`

* 代码实现

  ```java
  //如果程序超过5秒没有响应就结束事务并回滚
  @Transactional(rollbackFor = Exception.class)
  public int add(User user) throws FileNotFoundException {//相当于会话
      int add = 0;
  
      //产生编译期异常, 不会回滚事务
      new FileInputStream("girl1.jpg");//FileNotFoundException
  
      //操作数据库
      add = userDao.add(user);
      //产生运行时异常 , 会回滚事务
      System.out.println(1 / 0);
  
      return add;
  }
  
  
  @Transactional
  public int add2(User user) {//相当于会话
      int add = 0;
      try {
          add = 0;
  
          //产生编译期异常, 不会回滚事务
          new FileInputStream("girl1.jpg");//FileNotFoundException
  
          //操作数据库
          add = userDao.add(user);
          //产生运行时异常 , 会回滚事务
          System.out.println(1 / 0);
      } catch (Exception e) {
          e.printStackTrace();
          throw new RuntimeException(e);
      }
  
      return add;
  }
  ```



### 6.7.4事务的隔离级别★

* 隔离级别

  * 读未提交 : read uncommitted
    * 脏读,  不可重复读, 幻读
  * 读已提交 : read committed
    * 不可重复读, 幻读
  * 可重复读 : repeatable read
    * 幻读(合理)
  * 序列化 : serializable
    * 效率低

* 问题解释

  * 脏读 : 一个事务A可以读取到事务B中未提交的数据
  * 不可重复读 : 一个事务A读取一条数据, 前后结果不一致.
  * 幻读 : 在事务A中添加记录1(id=250)但是没有提交, 在事务B中查询记录1(id=250)显示没有, 在事务B添加记录1(id=250)显示有.
  * 效率低 : 事务之间是排队执行

* 注意事项

  * mysql数据库默认的隔离级别是repeatable read(`可重复读`) 
  * 建议大家将Spring程序的隔离级别设置为repeatable read(`可重复读`)  , 也是默认值
  * Isolation是个枚举类，Isolation属性的值为该类的枚举

* 代码实现

  ```JAVA
  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public int add(User user) throws FileNotFoundException {//相当于会话
      //操作数据库
      int add = userDao.add(user);
      return add;
  }
  
  
  @Transactional(isolation = Isolation.REPEATABLE_READ)
  public List<User> findAll() {
      List<User> userList = userDao.findAll();
      System.out.println("userList = " + userList);
      return userList;
  }
  ```



### 6.7.5事务传播行为★

* 概述

  * 事务传播行为 : 管理员方法中的事务和协调员方法中的事务的关系.

* 传播行为

  | 传播属性 | 事务管理员 | 事务协调员 |
  | :--: | :--: | :--: |
  | REQUIRED | 开启T1 | 加入T1 |
  | - | 无 | 新建T2 |
  | REQUIRES_NEW |开启T1|新建T2|
  | - | 无 | 新建T2 |
  | SUPPORTS | 开启T1 | 加入T1 |
  | - | 无 | 无 |
  | NOT_SUPPORTED | 开启T1 | 无 |
  | - | 无 | 无 |
  | MANDATORY | 开启T1 | 加入T1 |
  | - | 无 | ERROR |
  | NEVER | 开启T1 | ERROR |
  | - | 无 | 无 |
  | NESTED         |设置savePoint|事务回滚到此处|
  | - | 设置savePoint | 事务回滚到此处 |

* 传播行为总结：


    * REQUIRED : 协调员必须有事务.


    * REQUIRES_NEW : 协调员必须有新的事务


    * SUPPORTS : 协调员和管理员保持一致


    * NOT_SUPPORTED : 协调员不支持事务


    * MANDATORY : 强制管理员有事务, 如果没有协调员就报错


    * NEVER : 强制管理员没有事务, 如果有协调员就报错


* 应用场景

  * 不管程序执行正常与否都需要做日志记录

* ①事务管理员方法

  ```java
  /**
   * 事务管理员
   */
  @Transactional
  public void transfer(String outName, String inName, Double money) throws Exception {
      //添加日志
      logService.add("UserServiceImpl类执行了transfer方法");
  
      //铁甲小宝出账1000元
      userDao.outMoney(outName, money);
  
      //System.out.println(1 / 0);
  
      //玛卡巴卡入账1000元
      userDao.inMoney(inName, money);
  
  }
  ```

* ②事务协调员方法

  ```java
  /**
  * 事务协调员 : 日志记录
  */
  @Transactional(propagation = Propagation.NEVER)
  public void add(String content) throws Exception {
      logDao.add(content);
      System.out.println(1 / 0);
  }
  
  ```

* 注意事项

  * 事务的传播行为是在`事务协调员`设置

  



# 7.Spring+MyBatis整合

## 7.1整合步骤

- 导入依赖jar包
  - Spring ioc的、Spring aop的、mybatis的、Spring和MyBatis整合的、数据库相关的、测试的
- 创建基本架构
  - 创建业务层和控制层
  - 创建持久层和mapper映射文件
  - 创建数据库配置文件
- 配置Mybatis和Spring的配置文件
  - 会替换mybatis核心配置文件内的很多内容
  - mapper等对象交给spring的IoC容器进行管理
- 测试

## 7.2具体实现★

- 导入相关的jar包

  - spring的jar包

  - mybaties的jar包

  - mybaties整合spring的jar包

  - mysql驱动的jar包

  - 德鲁伊数据源的jar包

  - Spring整合JUnit4的jar包

  - mybatis的jar包

  - junit单元测试的jar包

    ```xml
    <dependencies>
    
      <!--Spring的jar包-->
      <!-- spring-context -->
      <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.3.1</version>
      </dependency>
      <!-- spring-orm -->
      <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-orm</artifactId>
      <version>5.3.1</version>
      </dependency>
      
      <!--mybatis整合spring的jar包-->
      <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>2.0.6</version>
      </dependency>
     
      <!--导入MySQL的驱动包-->
      <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.25</version>
      </dependency>
    
    	<!--德鲁伊数据库连接池-->
      <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.10</version>
      </dependency>
      
      <!--Spring整合JUnit4-->
      <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>5.3.1</version>
      </dependency>
      <!--MyBatis所需jar包-->
      <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.7</version>
      </dependency>
    
      <!--单元测试-->
      <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
      </dependency>
    </dependencies>
    ```

- 准备数据库和，创建数据表对应的Bean类

- 创建MVC架构，mapper、service、controller

- 创建数据库配置文件db.properties

  ```properties
  jdbc.driver=com.mysql.cj.jdbc.Driver
  jdbc.url=jdbc:mysql://localhost:3306/ssm
  jdbc.username=root
  jdbc.password=12345678
  ```

- 创建Mybatis核心文件mybatis-config.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
  
      <!--加载外部的配置文件-->
      <!--    <properties resource="db.properties"/>-->
  
      <!--开启自动驼峰-->
      <settings>
          <setting name="mapUnderscoreToCamelCase" value="true"/>
      </settings>
  
      <!--为所有的javaBean起别名-->
      <typeAliases>
          <package name="com.atguigu.bean"/>
      </typeAliases>
  
      <!--插件-->
  
      <!--配置数据库环境 ⚠️数据库交给spring来管理-->
      <!--<environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="${driver}"/>
                  <property name="url" value="${url}"/>
                  <property name="username" value="${username}"/>
                  <property name="password" value="${password}"/>
              </dataSource>
          </environment>
      </environments>-->
  
      <!--数据库厂商标识-->
  
      <!--加载核心配置文件-->
      <!--<mappers ⚠️让Spring的IoC管理所有的mapper接口>
          <mapper resource="mapper/EmployeeMapper.xml"/>
      </mappers>-->
  </configuration>
  ```

- 创建Mybatis中mapper的映射文件EmployeeMapper.xml，存放于mapper目录下

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <!--这里的命名空间需要指定该文件实现的哪个接口-->
  <mapper namespace="com.atguigu.mapper.EmployeeMapper">
    	<!--核心配置文件中起过别名了，直接使用employee即可-->
      <select id="getEmp" resultType="employee">
          select * from  employees
      </select>
  </mapper>
  ```

- 创建Spring配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
         xmlns:tx="http://www.springframework.org/schema/tx"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
  
      <!--设置注解扫描包-->
      <context:component-scan base-package="com.atguigu"/>
      
      <!--加载外部属性文件 ⚠️接替mybatis工作-->
      <context:property-placeholder location="classpath:db.properties"/>
  
      <!--创建德鲁伊数据源：数据库连接池-->
      <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
          <property name="driverClassName" value="${jdbc.driver}"/>
          <property name="url" value="${jdbc.url}"/>
          <property name="username" value="${jdbc.username}"/>
          <property name="password" value="${jdbc.password}"/>
      </bean>
  
      <!--让Spring去扫描所有的接口和映射文件，将所有Mapper对象都创建好，存入到ioc容器内 ⚠️-->
      <!--SqlSessionFactoryBean是整合jar包提供，帮助我们完成上述功能-->
      <bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
          <!--需要数据源：因为必须知道操作的数据库-->
          <property name="dataSource" ref="dataSource"/>
          <!--加载mybatis的核心配置文件-->
          <property name="configLocation" value="classpath:mybatis-config.xml"/>
          <!--加载mybatis的所有映射文件-->
          <property name="mapperLocations" value="classpath:mapper/*.xml"/>
      </bean>
  
      <!--单独需要扫描一下所有的mapper接口-->
      <mybatis-spring:scan base-package="com.atguigu.mapper"/>
  
      <!--配置事务管理器-->
      <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
          <!--需要数据源-->
          <property name="dataSource" ref="dataSource"/>
      </bean>
  
      <!--开启事务的注解支持-->
      <tx:annotation-driven transaction-manager="transactionManager"/>
  
  </beans>
  ```

- 使用注解@Controller和@Service将MVC中的类添加到IoC容器中管理

- 使用注解@Autowired完成依赖注入

- 使用注解@Transactional可以对service层中的方法进行事务管理

- 测试

  ```java
  package test;
  
  import com.atguigu.controller.EmployeeController;
  import org.junit.Test;
  import org.junit.runner.RunWith;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.test.context.ContextConfiguration;
  import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
  
  /**
   * @Description: TODD
   * @AllClassName: test.FirstTest
   */
  @ContextConfiguration(locations = "classpath:applicationContext.xml")
  @RunWith(SpringJUnit4ClassRunner.class)
  public class FirstTest {
  
      @Autowired
      private EmployeeController employeeController;
  		
      @Test
      public void test01(){
        	//find是接口内定义的方法名称
          employeeController.find();
      }
  }
  
  ```






# 8 Spring拓展

## 8.1整合juint4

第一步：导入整合juint4依赖的jar包

```xml
<!--Spring和junit整合的jar包-->
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-test</artifactId>
  <version>5.3.1</version>
</dependency>
```

第二步：在单元测试类上添加注解

```java
    //加载配置文件或者配置类，适用于非完全注解开发，locations 加载配置文件
    @ContextConfiguration(locations = "classpath:beans02.xml")
    //加载配置文件或者配置类，适用于完全注解开发，classes 加载配置类
    @ContextConfiguration(classes = SpringConfiguration.class)
    //使用Spring和junit4的运行器运行
    @RunWith(SpringJUnit4ClassRunner.class)
    //设置完注解会自动帮我创建ioc容器
    public class SpringTest02 {

      @Autowired
      private UserController userController;

      @Test
      public void test01(){
        // 测试内容
        System.out.println("bean = " + userController);
      }

    }
```



## 8.2整合juint5★

注意使用时引入的类：import org.junit.jupiter.api.Test

第一步：导入整合juint5的jar包

```xml
<!--spring对junit的支持相关依赖和整合juint4的一致-->
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-test</artifactId>
  <version>5.3.1</version>
</dependency>

<!--junit5依赖-->
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.9.0</version>
  <scope>test</scope>
</dependency>
```

第二步：在单元测试类上添加注解

```java
//一定要注意导入的Test不是juint4的Test类了⚠️
import org.junit.jupiter.api.Test;

//使用Spring和junit5的运行器运行
@ExtendWith(SpringExtension.class)
//加载配置文件，适用于非完全注解开发，locations 加载配置文件
@ContextConfiguration("classpath:spring.xml")
public class SpringJUnit5Test {

    @Autowired
    private User user;

    @Test
    public void testUser(){
        System.out.println(user.getName());
    }
}
```



## 8.3整合日志框架★

* 概述

  * Spring5.0官方建议整合log4j2

* 不使用System.out.println()输出日志的原因

  * ①底层使用锁机制,  效率非常低;
  * ②没有日志开关,无法做到在测试阶段打开日志, 在部署阶段关闭日志
  * ③需要在项目部署阶段记录日志, 因为部署阶段没有控制台用不了`sout` , 可以使用框架将日志内容记录到`文件`或`数据库`

* 日志框架

  * jul  : java自带的, 没人用
  * log4j : 目前非常流行的日志框架 , 被apache收购
  * log4j2 : apache重构, 新推出来的日志框架
  * logback : 和`log4j`同一个作者，重新研发的新框架

* 日志门面/接口

  * jcl : `Jakarta Commons Logging` , 算法很复杂, 出现问题难以定位 , 很少有人使用.
  * slf4j : `simple logging facade for java` , 也叫简单日志门面 

* 日志级别

  * off -> error -> warn -> info -> debug -> all
    * off : 关闭所有日志
    * error : 打印错误日志信息
    * warn : 打印警告日志信息
    * info : 打印一般的程序运行信息
    * debug : 打印程序的调试信息
    * all : 打印所有日志
  * 比如 : 日志级别=info , 打印 error -> warn -> info 这些级别的日志

* 使用方案

  * log4j2 + slf4j

* 开发步骤

  * ①引入日志的依赖
    * slf4j , log4j2
  * ②定义log4j2.xml
  * ③定义单元测试类

* ①引入日志的依赖

  ```xml
  <!--日志的依赖-->
  <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.25</version>
  </dependency>
  
  <!--log4j2的依赖-->
  <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-slf4j-impl</artifactId>
      <version>2.19.0</version>
  </dependency>
  ```

* ②定义log4j2.xml (文件名固定为：log4j2.xml，文件必须放到类根路径下)

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!--日志级别以及优先级排序: OFF  > ERROR > WARN > INFO > DEBUG  > ALL -->
  <configuration status="INFO">
  
      <!--先定义所有的appender-->
      <appenders>
          <!--输出日志信息到控制台-->
          <console name="Console" target="SYSTEM_OUT">
              <!--控制日志输出的格式-->
              <PatternLayout pattern="%-5p %d{yyyy-MM-dd HH:mm:ss,SSS} %m  (%F:%L) \n"/>
          </console>
  
      </appenders>
      <!--然后定义logger，只有定义了logger并引入的appender，appender才会生效-->
      <loggers>
  
          <!--root：用于指定项目的根日志，如果没有单独指定Logger，则会使用root作为默认的日志输出-->
          <!--全局日志级别-->
          <root level="debug">
              <appender-ref ref="Console"/>
          </root>
  
  
          <!--局部日志级别-->
          <!--
          <logger level="info" name="com.atguigu.test.MyTest">
          </logger>
          -->
      </loggers>
  
  
  </configuration>
  ```

* ③定义单元测试类

  ```java
  public class LoggerTest {
    	//日志使用出错时可仔细检查导入的类是否正确 ⚠️
      import org.slf4j.Logger;
      import org.slf4j.LoggerFactory;
  
      //必须传入当前测试类的运行时对象
      Logger logger = LoggerFactory.getLogger(Student.class);
  
      @Test
      public void test1(){
          logger.error("error信息");
          logger.warn("warn信息");
          logger.info("info信息");
          logger.debug("debug信息");
      }
  }
  ```



## 8.4@Nullable注解

概述：

* 在Spring5.0中大量使用了 @Nullable 注解 , 该注解可以用在属性, 方法, 形参上面. 可以明确地告诉开 发人员使用了 @Nullable 注解的位置传递null也不会造成空指针异常

使用位置：

* ①用在属性上面 : 属性值可以为空
* ②用在方法上面 : 方法返回值可以为空
* ③用在形参前面 : 参数值可以为空



## 8.5lombok插件和maven插件

使用方式：

使用快捷键command + , 打开配置菜单，点击Plugins搜索插件名称

- ①maven插件
  - 实现Maven依赖的搜索，可复制完整的gav标签
  - 可修改默认的快捷键
- ②lombok插件
  - 2.1, 安装lombok插件
  - 2.2, 设置idea支持注解编程



## 8.6Spring6版本的环境搭建

安装依赖的jar包

- 在spring6版本正式发布之前，需要使用仓库地址进行引入

- 引入spring context依赖

  ```xml
  <!--Spring6的正式版发布之前，这个仓库地址是需要的-->
  <repositories>
    <repository>
      <id>repository.spring.milestone</id>
      <name>Spring Milestone Repository</name>
      <url>https://repo.spring.io/milestone</url>
    </repository>
  </repositories>
  
  <dependencies>
    <!--spring context依赖：使用的是6.0.0-M2里程碑版-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>6.0.0-M2</version>
    </dependency>
  </dependencies>
  ```

  



