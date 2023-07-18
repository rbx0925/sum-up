# 1 SpringMVC入门

## 1.1SpringMVC简介

Spring MVC属于SpringFrameWork的后续产品，是目前最主流的MVC 框架

Spring MVC 通过一套 MVC 注解进行实现的

天生与Spring框架集成（如IOC容器、AOP等），而且支持REST 风格的 URL 请求。



## 1.2SpringMVC处理请求

- 客户端请求提交到**前端控制器 DispatcherServlet**

- 由DispatcherServlet控制器查询一个或多个**HandlerMapping**，找到处理请求的**Controller**

- DispatcherServlet将请求提交到Controller（也称为Handler）

- Controller调用业务逻辑处理后，返回**ModelAndView**

-  DispatcherServlet查询一个或多个**ViewResoler视图解析器**，找到ModelAndView指定的视图

- 视图负责将结果显示到客户端

  

## 1.3SpringMVC主要组件

- **DispatcherServlet：**前端控制器
- **Controller：**页面控制器/处理器，但控制逻辑转移到前端控制器了，用于对请求进行处理。
- **HandlerMapping ：**请求映射到处理器，找谁来处理，如果映射成功返回一个HandlerExecutionChain对象
  - 包含一个**Handler**处理器(页面控制器)对象
  - 包含多个**HandlerInterceptor**拦截器对象
- **HandlerAdaptor：**处理器适配器
- **ViewResolver :** 视图解析器，找谁来处理返回的页面。把逻辑视图解析为具体的View
- **MultipartResolver：**文件上传解析器
- **HandlerExceptionResolver：**异常处理器



## 1.4SpringMVC使用步骤

官网：https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc

- 创建一个web模块

- 导入SpringMVC的jar包

- 导入服务器渲染工具的jar包
  - Thymeleaf

- 需要servlet-api的jar包**（注意！scope设置为provided！不然部署到服务器上会冲突包错！）**
  - Maven工程没有选择tomcat支持，所以默认是用不了servlet-api这个jar包

- 创建一个首页

- 访问首页

  - 需要让Thymeleaf进行渲染，所以需要将Thymeleaf配置到SpringMVC的框架内
    - 需要设置前缀和后缀

  - 需要创建前端控制器
    - 需要加载springmvc的配置文件

  - 需要创建IndexController
    - 分发的控制器(通过Thymeleaf转发到首页)

      

## 1.5SpringMVC入门程序

- 创建一个web模块，打包方式设置为war

- 导入相关jar包

  - 导入SpringMVC的jar包
  - 导入thymeleaf与spring5的整合jar包
  - 导入servlet-api的jar包**（注意！scope设置为provided！不然部署到服务器上会冲突包错！）**

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
  
      <groupId>com.atguigu</groupId>
      <artifactId>web03</artifactId>
    	<version>1.0</version>
        
      <!--打包方式设置为war⚠️-->
      <packaging>war</packaging>
  
      <properties>
          <maven.compiler.source>17</maven.compiler.source>
          <maven.compiler.target>17</maven.compiler.target>
      </properties>
  
      <dependencies>
          <!--spring-webmvc-->
          <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-webmvc</artifactId>
              <version>5.3.1</version>
          </dependency>
  
          <!-- 导入thymeleaf与spring5的整合包 -->
          <dependency>
              <groupId>org.thymeleaf</groupId>
              <artifactId>thymeleaf-spring5</artifactId>
              <version>3.0.12.RELEASE</version>
          </dependency>
  
          <!--servlet-api 注意这里的scope设置为provided-->
          <dependency>
              <groupId>javax.servlet</groupId>
              <artifactId>javax.servlet-api</artifactId>
              <version>4.0.1</version>
              <scope>provided</scope>
          </dependency>
      </dependencies>
  
  </project>
  ```

- 在项目结构中添加web.xml文件

  - /Users/shuaigouzi/javaProject/javaEEProject/ssm/web03/src/main/webapp/WEB-INF/web.xml
  - /src/main/webapp是添加的路径，用于控制webapp目录的生成位置

- 创建Springmvc的配置文件Springmvc.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
  
      <!--配置注解自动扫描的包-->
      <context:component-scan base-package="com.atguigu.controller "></context:component-scan>
  
      <!--配置视图解析器 (SpringBoot不用配，所以复制即可)-->
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
                          <!--配置前缀-->
                          <property name="prefix" value="/WEB-INF/pages/"></property>
                          <!--配置后缀-->
                          <property name="suffix" value=".html"></property>
                          <!--配置字符集-->
                          <property name="characterEncoding" value="UTF-8"></property>
                      </bean>
                  </property>
              </bean>
          </property>
      </bean>
  </beans>
  ```

- 配置web.xml文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
           version="4.0">
  
      <!--配置前端控制器，所有请求都先到前端控制器-->
      <servlet>
          <servlet-name>dispatcherServlet</servlet-name>
          <!--spring提供的前端控制器，我们需要为它设置访问路径-->
          <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
          <!--初始化参数：加载springmvc的配置文件
              key值是固定的：contextConfigLocation，DispatcherServlet会通过固定的key值去寻找value值去加载配置文件
          -->
          <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>classpath:Springmvc.xml</param-value>
          </init-param>
          <!--自启动-->
          <load-on-startup>1</load-on-startup>
      </servlet>
      <!--设置前端控制器访问路径，表示所有的访问路径都要走前端控制器-->
      <servlet-mapping>
          <servlet-name>dispatcherServlet</servlet-name>
          <url-pattern>/*</url-pattern>
      </servlet-mapping>
  </web-app>
  ```

- 在WEB-INF中创建pages目录及pages/index.html文件，必须与springmvc的配置文件中设置的前缀一致

- 创建com.atguigu.controller.IndexController类，并加上注解@Controller，交给IoC管理

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
  
    	//@RequestMapping注解设置接收请求的路径，收到该路径的请求就执行下方的方法
    	//方法必须无参、方法名随意、返回值为String：逻辑视图
      @RequestMapping("/index")
      public String toIndex(){
          System.out.println("访问到了IndexController的toIndex方法...");
          return "index";
      }
  }
  
  ```

- 创建tomcat服务器，配置war包，访问方法的接收路径测试



# 2 @RequestMapping注解

SpringMVC使用@RequestMapping注解，为控制器指定可以处理哪些 URL 请求。

**DispatcherServlet 截获请求后，就通过控制器上 @RequestMapping 提供的映射信息确定请求所对应的处理方法。**

## 2.1使用注解

### 2.1.1注解的添加位置★

- 只加在方法上
  - 当前模块内所有方法的访问路径都不能一样
- 类上和方法上都加 ★
  - 类上的就相当于是有一个前缀
- 特殊情况
  - 类上和方法上都加，但是方法上没有映射路径
    - 默认就找这个没有映射路径的方法(一类Controller只允许有一个)

```java
@Controller
@RequestMapping("/index")
public class IndexController {

		//访问/index时，默认执行该方法  
    @RequestMapping
    public String one(){
        System.out.println("默认就找这个没有映射路径的方法，一类Controller只允许有一个");
        return "index";
    }

    @RequestMapping("/two")
    public String two(){
        System.out.println("访问的uri为 /index + /two 时找该方法");
        return "index";
    }

    @RequestMapping("/three/test/abc")
    public String three(){
        System.out.println("访问的uri为 /index + /three/test/abc 时找该方法");
        return "index";
    }
}
```



### 2.1.2 注解的常用属性 ★

- value属性
  - 用来设置要映射的请求地址
  - 底层是String数组，可写多个，如果只有一个可省略value
- method属性
  - 要求接收的方法类型：method = RequestMethod.GET
  - 取值：共八种枚举类型，常用的有GET、POST、PUT、DELETE
  - 可同时设置多个，用逗号隔开
- params属性
  - 用来设置请求地址中必须包含的请求参数
  - 值的类型是String类型的数组，可同时设置多个条件
  - 各种表达式说明：
    - param1: 表示请求必须包含名为 param1 的请求参数
    - !param1: 表示请求不能包含名为 param1 的请求参数
    - param1 != value1: 表示请求包含名为 param1 的请求参数，但其值不能为 value1
    - {"param1=value1", "param2"}: 请求必须包含名为 param1 和param2 的两个请求参数，且 param1=value1
- headers属性
  - 用来设置请求地址中必须包含的请求头

```java
   @RequestMapping(
            value = "/hello",
            method = {RequestMethod.GET,RequestMethod.POST},
            params = "username=admin",
            headers = "Referer=http://localhost:8888/springmvc01/index.html")
    public String hello(){
        System.out.println("访问到Controller的hello方法，将跳转到success.html页面...");
        return "success";
    }
```



### 2.1.3ant风格

- Ant 风格资源地址支持 3 种匹配符
  - **?**	 匹配一个字符
  - **\***     匹配任意字符，但必须路径层数相同
  - ******   匹配多层路径

- 演示代码

  ```html
  <a th:href="@{/testAnt/a/ant}">Test Ant</a><br>
  <a th:href="@{/testAnt/ab/ant}">Test Ant</a><br>
  <a th:href="@{/testAnt/a/b/c/ant}">Test Ant</a><br>
  
  ```
  
  ```java
  		//匹配第一个a标签
  		@RequestMapping("/testAnt/?/ant")
  		//匹配第二个a标签
  		@RequestMapping("/testAnt/*/ant")
  		//匹配第三个a标签
      @RequestMapping("/testAnt/**/ant")
      public String testAnt(){
          System.out.println("测试Ant风格的URL");
          return "success";
      }
  ```
  
  

### 2.1.4 带占位符的URL ★

**@PathVariable注解主要用来处理REST风格的URL的中的请求参数。**

- 通过 @PathVariable 可以将**URL中占位符参数**绑定到**控制器处理方法的入参**中，该注解三个参数

  - **value属性：**用来指定要映射的URL中的占位符的名字。

  - **name属性：**与value功能一样，是value的一个别名。

  - **required属性：**设置指定的占位符的名字是否是必须的，默认是true，如果不存在则抛出异常。

    ```java
        //测试路径：http://localhost:8080/web03/index/admin/123456/1
    		@RequestMapping("/index/{username}/{password}/{id}")
        public String one(
                @PathVariable(value = "username") String name,
                @PathVariable(name = "password") String passwd,
                @PathVariable() Integer id,
          			//虽然占位符中没no，不过设置了required=false，不会报错。会拿到null值
                @PathVariable(value = "no", required = fal，e) String no
        ){
            System.out.println(name);
            System.out.println(passwd);
            System.out.println(id);
            System.out.println(no);
            return "index";
        }
    ```

    


## 2.2 REST风格

**REST：即 Representational State Transfer，表现层状态转换，是一种万维网软件架构风格。**

**@PathVariable注解主要用来处理REST风格的URL的中的请求参数。**

### 2.2.1REST风格与传统URL

- 传统风格：增删改查不区分请求方式，使用?传递参数
  
- REST风格：增删改查通过请求方式进行区分，使用/传递参数
  
  | 操作 | REST风格URL及请求方式 | 传统方式URL及请求方式          |
  | ---- | --------------------- | ------------------------------ |
  | 获取 | /book/1  GET请求      | /getBookById?id=1  GET请求     |
  | 添加 | /book   POST请求      | /addBook  POST请求             |
  | 更新 | /book  PUT请求        | /updateBook  POST请求          |
  | 删除 | /book/1  DELETE请求   | /deleteBookById/?id=1  GET请求 |
  
  

### 2.2.2REST风格的四种请求★

- REST风格的GET和POST通过表单的method设置即可

  ```html
  <form action="book" method="get">
      <!--表单省略-->
  </form>
  <form action="book" method="post">
      <!--表单省略-->
  </form>
  ```

- REST风格的PUT和DELET设置需要满足以下要求：

  - 表单的method设置为POST
  - 表单内创建input隐藏域，name属性为_method，value属性为PUT或DELET（不区分大小写）
  - 在web.xml中配置请求方式转化的过滤器

  ```html
  <!--REST风格的put请求-->
  <form action="book" method="post">
      <!--表单省略-->
      <input type="hidden" name="_method" value="put">
      <input type="submit" value="修改数据">
  </form>
  
  <!--REST风格的delete请求-->
  <form action="book/10" method="post">
      <input type="hidden" name="_method" value="delete">
      <input type="submit" value="删除数据">
  </form>
  ```

  ```xml
  <!--配置请求方式转化的过滤器,拦截请求，识别是否携带_method请求参数，如果携带就完成请求方式的转化-->
  <filter>
      <filter-name>hiddenHttpMethodFilter</filter-name>
      <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
  </filter>
  <filter-mapping>
      <filter-name>hiddenHttpMethodFilter</filter-name>
      <url-pattern>/*</url-pattern>
  </filter-mapping>
  ```

  



# 3 处理请求数据

Spring MVC 可以将HTTP请求信息绑定到处理方法的相应入参中，并根据方法的返回值类型做出相应的后续处理。

必要时可以对方法入参标注相应的注解**@PathVariable 、@RequestParam、@RequestHeader、@CookieValue** 

## 3.1@RequestParam注解★

在处理方法入参里使用 @RequestParam注解，可以把URL的请求参数传递给请求方法。

@RequestParam中的属性：

- **value属性：**
- **name属性：**与value功能一样，是value的一个别名。
- **required属性：**用来设置该请求参数是否是必须的，默认是true。
- **defaultValue属性：**用来设置一个默认值，如果没有传入该请求参数将使用此值。
- 请求参数的参数名与形参的变量名一致时，可以省略value属性，甚至可以省略注解**（SpringMVC中唯一一个可以省略注解的）**

```java
  //测试URL：http://localhost:8080/web03/index?username=admin&password=123456&id=1  
	@RequestMapping("/index")
    public String one(
            @RequestParam(value = "username")String name,
      			//名称一致直接省略了注解
            String password,
      			//名称一致直接省略了注解参数
            @RequestParam() Integer id,
      			//未传递key为no的参数，但是设置了required=false，这里no=null
            @RequestParam(value = "no",required = false)String no,
      			//未传递key为def的参数，但是设置了defaultValue="test"，这里def默认值为test
            @RequestParam(value = "def",defaultValue = "test")String def
    ){
        System.out.println(name);
        System.out.println(password);
        System.out.println(id);
        System.out.println(no);
        System.out.println(def);
        return "index";
    }
```



## 3.2@RequestHeader注解

@RequestHeader注解可将请求头中的属性值绑定到处理方法的入参中。

@RequestHeader中的属性：

- **value属性：**用来设置请求头中的属性名。

- **name属性：**与value功能一样，是value的一个别名。

- **required属性：**用来设置该请求头中的属性是否是必须的，默认是true。

- **defaultValue属性：**用来设置一个默认值，如果请求头中没有该属性将使用此值。

- 要获取的请求头参数名与形参的变量名一致时，可以省略value属性

  ```java
      @RequestMapping("/index")
      public String one(
              @RequestHeader("Accept-Encoding") String encoding,
        			//请求头参数名和入参中的变量名一致时可省略value属性
              @RequestHeader() String Host,
        			//请求头中不存在test参数，则使用默认值test info为其赋值
              @RequestHeader(value="test",defaultValue = "test info") String testInfo
      ){
          System.out.println(encoding);
          System.out.println(Host);
          System.out.println(testInfo);
          return "index";
      }
  ```

  

## 3.3@CookieValue注解

通过 @CookieValue 即可将请求头中的Cookie对象的值绑定到处理方法的入参中。

@CookieValue中的属性：

- **value属性：**cookie数据的key值

- **name属性**：与value功能一样，是value的一个别名。

  **required属性**：用来设置请求头中Cookie对象的名字是否是必须的，默认是true。

  **defaultValue属性**：用来设置一个默认值，如果请求头中没有该Cookie对象的名字将使用此值。

- 

- **name属性**：与value功能一样，是value的一个别名。

  **required属性**：用来设置请求头中Cookie对象的名字是否是必须的，默认是true。

  **defaultValue属性**：用来设置一个默认值，如果请求头中没有该Cookie对象的名字将使用此值。

```java
    @RequestMapping("/index")
    public String one(
            @CookieValue() String username,
            @CookieValue(value = "password",required = false) String password
    ){
        System.out.println(username);
        System.out.println(password);
        return "index";
    }
```



## 3.4使用POJO入参★

Spring MVC 会将请求参数的数据，映射到一个java对象内，基于无参构造器和属性的set方法。

会按请求参数名和 POJO 属性名进行自动匹配，自动为该对象填充属性值。而且支持级联属性赋值。

**必须要保证请求参数名与POJO的属性名保持一致。**

```java
    @RequestMapping("/index")
    public String pojoTest(Employee employee){
        System.out.println("employee = " + employee);
        return "index";
    }
```



## 3.5 使用servlet-api作为入参★

不使用@RequestParam注解和入参为POJO同样可以获取请求参数，可以使用原生的Servlet API作为入参进行操作。

SpringMVC处理器方法中接受的原生Servlet API有以下9个：

- **HttpServletRequest（重点）**

- **HttpServletResponse（重点）**

- **HttpSession（重点）**

- java.security.Principal

- Locale

- InputStream

- OutputStream

- Reader

- Writer

  ```java
      @RequestMapping("/index")
      public String apiTest(
              HttpServletRequest request,
              HttpServletResponse response,
              HttpSession session){
          //拿到入参中的ServletAPI对象后，可调用其方法正常使用
          String contextPath = request.getContextPath();
          System.out.println("contextPath = " + contextPath);
        
          System.out.println("session = " + session);
        
          response.setContentType("text/html;charset=utf-8");
          return "index";
      }
  ```

  

## 3.6处理post请求中文乱码问题★

- SpringMVC提供了一个过滤器
  - 配置+字符集的设置

```xml
<!--处理中文乱码的过滤器
    放在配置文件的最上面
-->
<filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <!--需要通过初始化参数设置字符集-->
    <init-param>
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
    </init-param>
    <!--确保utf-8是100%设置成功的-->
    <init-param>
        <param-name>forceRequestEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```





# 4 处理响应数据

**响应数据分为两类：**

- 页面跳转的逻辑视图
- 请求域的共享数据

**响应方式分为两类：**

- 将Controller方法的返回值设置成ModelAndView
- 将Controller方法的返回值设置成String,将Map/Model/ModelMap设置为入参，返回值为逻辑视图

## 4.1ModelAndView

处理器方法的返回值如果为 ModelAndView，则其既包含视图信息，也包含模型数据信息。

**核心属性：**

- private Object view; 视图信息
- private ModelMap model; 模型数据

**添加模型数据的方法：**

- MoelAndView addObject(String attributeName, Object attributeValue)
- ModelAndView addAllObject(Map<String, ?> modelMap)

**获取模型数据的方法：**

- protected Map<String, Object> getModelInternal()
- public ModelMap getModelMap()
- public Map<String, Object> getModel()

**设置视图的方法：**

- void setView(View view) 设置视图对象
- void setViewName(String viewName) 设置视图名字

```java
/**
 * 方式一：将方法的返回值设置为ModelAndView
 */
@RequestMapping("/responseTest")
//返回值设置为ModelAndView
public ModelAndView responseTest(){
    //1. 获取请求参数
    //2. 调用业务层处理业务
    //3. 处理响应(设置逻辑视图、设置请求域共享数据)
    //3.1 实例化一个ModelAndView对象，若使用有参构造可直接传入逻辑视图
    ModelAndView mv=new ModelAndView();
    //3.2 设置逻辑视图
    mv.setViewName("admin");
    //3.3 设置请求域的共享数据,可使用thymeleaf渲染
    mv.addObject("msg","这是请求域的共享数据");
    mv.addObject("name","玛卡巴卡");
    return mv;
}
```



## 4.2Map/Model/ModelMap★

Spring MVC 在内部使用了一个 org.springframework.ui.Model 接口存储模型数据

而将Map和Model和ModelMap作为入参，存储的类型均为BindingAwareModelMap

底层使用的还是**ModelAndView**

- 传进来的对象都是同一个BindingAwareModelMap
  - 三个父级的所有方法，子级都有，所以没有区别
- BindingAwareModelMap的父类ExtendedModelMap继承了ModelMap
- BindingAwareModelMap的父类的父类继承了LinkedHashMap，祖先类有Map
- BindingAwareModelMap的父类ExtendedModelMap实现了接口Model

使用Map作为入参：

- 返回值为逻辑视图
- 向请求域添加数据使用map.put("user","admin")

```java
//使用Map作为入参
@RequestMapping("/testMap")
public String testMap(Map<String, String> map) {
    //将模型数据放到map中，最终会放到request域中
    map.put("user","admin");
    return "success";
}
```

使用Mode作为入参：

- 返回值为逻辑视图
- 向请求域添加数据使用model.addAttribute("name","admin")

```java
//使用Model作为入参
@RequestMapping("/testMap")
public String testMap(Model model) {
    //将模型数据放到Model中，最终会放到request域中
    model.addAttribute("name","admin");
    return "success";
}
```

使用ModelMap作为入参：

- 返回值为逻辑视图
- 向请求域添加数据使用modelMap.addAttribute("name","admin")

```java
//使用ModelMap作为入参
@RequestMapping("/testMap")
public String testMap(ModelMap modelMap) {
    //将模型数据放到modelMap中，最终会放到request域中
    modelMap.addAttribute("name","admin");
    return "success";
}
```



## 4.3处理响应数据底层源码

- SpringMVC的运行流程(相当复杂)

  - 浏览器发出请求到服务器
  - 首先到前端控制器
    - 解析请求的url，调用本对象的getHandler方法，寻找对应的Controller
      - 寻找一个合适的**HandlerMapping**，类型为RequestMappingHandlerMapping
      - 通过HandlerMapping的getHandler方法，寻找对应的Controller和Interceptor
      - 找到对应的Controller和Interceptor后返还给前端控制器
  - 前端控制器再寻找处理器适配器**HandlerAdapter**，用来执行Controller
  - 通过getHandlerAdapter()找到类型为RequestMappingHandlerAdapter的处理器适配器对象ha
  - 通过HandlerAdapter对象ha执行Controller，并且拿到方法返回的ModelAndView对象mv
  - 返回的ModelAndView对象mv再返还给前端控制器
  - 将ModelAndView内的信息：请求域参数、逻辑视图等，传递给视图解析器进行视图渲染
  - 视图解析器渲染完成后再将渲染好的视图给前端控制器
  - 前端控制器最终将渲染好的视图响应给浏览器客户端

- 源码

  - 前端控制器DispatcherServlet的doService方法

    ```java
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//...
    	this.doDispatch(request, response);
    	//...
    }
    ```

  - 前端控制器的doDispatch方法 ★

    ```java
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest processedRequest = request;
        //初始化了一个局部变量：存储Interceptor和Controller
        HandlerExecutionChain mappedHandler = null;
        boolean multipartRequestParsed = false;
        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
    
        try {
            try {
                //初始化了一个ModelAndView,准备接收Controller方法返回的ModelAndView
                //① 直接返回ModelAndView
                //② 返回String,Map/Model/ModelMap作为入参时，最终也变为ModelAndView
                ModelAndView mv = null;
                Object dispatchException = null;
    
                try {
                    processedRequest = this.checkMultipart(request);
                    multipartRequestParsed = processedRequest != request;
                    //调用本对象的getHandler寻找Controller方法和Interceptor拦截器并存储
                    mappedHandler = this.getHandler(processedRequest);
                    if (mappedHandler == null) {
                        this.noHandlerFound(processedRequest, response);
                        return;
                    }
    								//前端控制器寻找一个处理器适配器HandlerAdapter：执行Controller方法的对象
                    HandlerAdapter ha = this.getHandlerAdapter(mappedHandler.getHandler());
                    String method = request.getMethod();
                    boolean isGet = "GET".equals(method);
                    if (isGet || "HEAD".equals(method)) {
                        long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
                        if ((new ServletWebRequest(request, response)).checkNotModified(lastModified) && isGet) {
                            return;
                        }
                    }
    
                    if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                        return;
                    }
    								//通过处理器适配器对象ha，执行Controller方法，并且拿到ModelAndView对象赋给mv
                    mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
                    if (asyncManager.isConcurrentHandlingStarted()) {
                        return;
                    }
    
                    this.applyDefaultViewName(processedRequest, mv);
                    mappedHandler.applyPostHandle(processedRequest, response, mv);
                } catch (Exception var20) {
                    dispatchException = var20;
                } catch (Throwable var21) {
                    dispatchException = new NestedServletException("Handler dispatch failed", var21);
                }
    						//要进行视图的渲染
                //① 根据视图名字得到视图对象
                //② 将数据模型ModelAndView的值添加到请求域内，渲染到视图对象上
                //③ 将渲染后的视图对象，响应给浏览器
                this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
            } catch (Exception var22) {
                this.triggerAfterCompletion(processedRequest, response, mappedHandler, var22);
            } catch (Throwable var23) {
                this.triggerAfterCompletion(processedRequest, response, mappedHandler, new NestedServletException("Handler processing failed", var23));
            }
    
        } finally {
            if (asyncManager.isConcurrentHandlingStarted()) {
                if (mappedHandler != null) {
                    mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
                }
            } else if (multipartRequestParsed) {
                this.cleanupMultipart(processedRequest);
            }
    
        }
    }
    ```

- 前端控制器的getHandler方法

  - 寻找Controller和Interceptor

    ```java
    @Nullable
    protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        if (this.handlerMappings != null) {
            Iterator var2 = this.handlerMappings.iterator();
    
            while(var2.hasNext()) {
              //寻找一个合适的HandlerMapping，他才是寻找Controller和Interceptor的主要负责人
                //mapping才是合适的主要负责人，类型为RequestMappingHandlerMapping
                HandlerMapping mapping = (HandlerMapping)var2.next();
                //调用mapping的getHandler方法，开始寻找Controller和Interceptor
                HandlerExecutionChain handler = mapping.getHandler(request);
                if (handler != null) {
                    //返回的是前端控制器中的局部变量：HandlerExecutionChain mappedHandler
                    return handler;
                }
            }
        }
    
        return null;
    }
    ```

- 前端控制器的getHandlerAdapter方法

  - 寻找处理器适配器**HandlerAdapter**，用来执行Controller

    ```java
    protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
        if (this.handlerAdapters != null) {
            Iterator var2 = this.handlerAdapters.iterator();
    				//寻找一个合适的HandlerAdapter
            while(var2.hasNext()) {
                //adapter是合适的，类型为RequestMappingHandlerAdapter
                HandlerAdapter adapter = (HandlerAdapter)var2.next();
                if (adapter.supports(handler)) {
                  	//将找到的RequestMappingHandlerAdapter返回给ha，然后ha开始执行controller
                    return adapter;
                }
            }
        }
    
        throw new ServletException("No adapter for handler [" + handler + "]: The DispatcherServlet configuration needs to include a HandlerAdapter that supports this handler");
    }
    ```





# 5 视图解析

**视图解析器在springMVC配置文件中进行配置，在springMVC环境搭建阶段，我们已配置了默认的视图解析器Velocity。**

## 5.1视图解析源码分析

- 前端控制器调用完Controller得到ModelAndView对象，即将进行视图的处理

  ```java
  //开始视图渲染
  this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
  ```

- 判断有无异常，判断ModalAndView对象mv，没有问题的话进行视图渲染

  ```java
  private void processDispatchResult(HttpServletRequest request, HttpServletResponse response, @Nullable HandlerExecutionChain mappedHandler, @Nullable ModelAndView mv, @Nullable Exception exception) throws Exception {
      boolean errorView = false;
      if (exception != null) {
         //异常处理
      }
      if (mv != null && !mv.wasCleared()) {
          //要进行视图渲染⚠️
          this.render(mv, request, response);
          //..
      } 
  	//...
      }
  }
  ```

- 获取逻辑视图对象view，开始进行视图渲染

  ```java
  protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
      Locale locale = this.localeResolver != null ? this.localeResolver.resolveLocale(request) : request.getLocale();
      response.setLocale(locale);
      //获取ModelAndView中的逻辑视图
      String viewName = mv.getViewName();
      View view;
      if (viewName != null) {
          //根据逻辑视图得到对应View对象(还未渲染的View对象)
          //View是接口  -->  ThymeleafView
          view = this.resolveViewName(viewName, mv.getModelInternal(), locale, request);
          if (view == null) {
              throw new ServletException("Could not resolve view with name '" + mv.getViewName() + "' in servlet with name '" + this.getServletName() + "'");
          }
      } else {
          view = mv.getView();
          if (view == null) {
              throw new ServletException("ModelAndView [" + mv + "] neither contains a view name nor a View object in servlet with name '" + this.getServletName() + "'");
          }
      }
  
      if (this.logger.isTraceEnabled()) {
          this.logger.trace("Rendering view [" + view + "] ");
      }
  
      try {
          if (mv.getStatus() != null) {
              response.setStatus(mv.getStatus().value());
          }
  			  //通过view对象调用render方法，要进行视图的渲染了
          //视图渲染代码我们是看不到的，但是我们能找到将模型数据放到请求域内
          view.render(mv.getModelInternal(), request, response);
      } catch (Exception var8) {
          if (this.logger.isDebugEnabled()) {
              this.logger.debug("Error rendering view [" + view + "]", var8);
          }
  
          throw var8;
      }
  }
  ```

- WebEngineContext类的内部类RequestAttributesVariablesMap的setVariable方法会将模型数据从map取出放到请求域内

  ```java
          public void setVariable(String name, Object value) {
              this.ensureLevelInitialized(true);
              if (this.level > 0) {
                  int levelIndex = this.searchNameInIndex(name, this.index);
                  if (levelIndex >= 0) {
                      this.newValues[this.index][levelIndex] = value;
                  } else {
                      if (this.names[this.index].length == this.levelSizes[this.index]) {
                          this.names[this.index] = (String[])Arrays.copyOf(this.names[this.index], this.names[this.index].length + 5);
                          this.newValues[this.index] = Arrays.copyOf(this.newValues[this.index], this.newValues[this.index].length + 5);
                          this.oldValues[this.index] = Arrays.copyOf(this.oldValues[this.index], this.oldValues[this.index].length + 5);
                      }
  
                      levelIndex = this.levelSizes[this.index];
                      this.names[this.index][levelIndex] = name;
                      this.oldValues[this.index][levelIndex] = this.request.getAttribute(name);
                      this.newValues[this.index][levelIndex] = value;
                      int var10002 = this.levelSizes[this.index]++;
                  }
              }
  						//向请求域中添加数据
              this.request.setAttribute(name, value);
          }
  ```

- 根据视图名得到视图对象

  ```java
  @Nullable
  protected View resolveViewName(String viewName, @Nullable Map<String, Object> model, Locale locale, HttpServletRequest request) throws Exception {
      if (this.viewResolvers != null) {
          Iterator var5 = this.viewResolvers.iterator();
  				//循环寻找到一个合适的视图解析器ViewResolver  -->  ThymeleafViewResolver(已配置在springMVC.xml中)
          while(var5.hasNext()) {
              //寻找ThymeleafViewResolver
              ViewResolver viewResolver = (ViewResolver)var5.next();
              //根据逻辑视图得到View对象   -->   ThymeleafView（View接口的实现类）
              View view = viewResolver.resolveViewName(viewName, locale);
              if (view != null) {
                  return view;
              }
          }
      }
  
      return null;
  }
  ```



## 5.2 mvc:view-controller标签★

如果发送的请求不想通过controller，只想直接地跳转到目标页面，这时候就可以使用mvc:view-controller标签

配置访问路径之后，会直接交给dispatcherServlet，然后使用ViewResolver进行解析

标签配置方法：

- 在SpringMVC的配置文件中配置mvc:view-controller标签

  - path属性：访问路径
  - view-name属性：逻辑视图(会是红色，但是没有任何影响)

  ```xml
  <!--配置不经过处理器方法直接响应的页面-->
  <mvc:view-controller path="/hello" view-name="index"></mvc:view-controller>
  ```

配置后出现的问题：

- 会造成所有的@Controller注解无法解析，导致404错误。
  - 因为前端控制器dispatcherServlet对象内有两个对象，使用Controller时必须存在
    - RequestMappingHandlerMapping，寻找Controller
    - RequestMappingHandlerAdapter，执行Controller
  - 使用mvc:view-controller标签后，dispatcherServlet对象内就没有这两个对象了

处理方案：

- 添加mvc:annotation-driven标签

- 可以在存在mvc:view-controller的前提下，让RequestMappingHandlerMapping和RequestMappingHandlerAdapter继续可以工作

  ```xml
      <mvc:annotation-driven/>
      <mvc:view-controller path="/hell" view-name="hello"/>
  ```

注意事项：⚠️

- 如果请求存在处理器，则这个标签对应的请求处理将不起作用。因为请求是先去找处理器处理，如果找不到才会去找这个标签配置。
- 即使配置该标签后页面请求的是不通过控制器，SpringMVC里面的拦截器也会捕获到请求。



## 5.3处理静态资源的问题★

**<mvc:default-servlet-handler />标签可以解决静态资源的访问问题**

- 网页文件、样式文件、js文件、图片文件、音频文件...
  - 网页文件建议放在WEB-INF下(让Controller去转发访问)
  - 样式文件、js文件、图片文件、音频文件不能放在WEB-INF下的，需要放在webapp下
    - <http://localhost:8888/springmvc02/static/style.css>请求不到静态资源
- 原因
  - 所有的请求都被前端控制器DispacherServlet拦截，但是没有找到映射该请求的处理方法
    - 默认取找和请求路径对应的Controller，找不到Controller就出现404

解决方案:

- **添加<mvc:default-servlet-handler />标签**

- 该标签依赖mvc:annotation-driven标签

- 添加后，如果是静态资源，前端控制器就会根据路径去寻找相符的静态资源

  ```xml
  <mvc:annotation-driven/>
  <!--处理静态资源的标签也是需要mvc:annotation-driven标签的，原因和添加mvc:view-controller的原因一样-->
  <mvc:default-servlet-handler/>
  ```

添加标签就可以解决的原理：

- 请求路径是<http://localhost:8888/springmvc02/static/style.css>
- 先将请求发送到前端控制器
- 前端控制器就去寻找Controller
  - 能找到，直接访问Controller
  - 找不到,进行下一步

- 判断是否配置了<mvc:default-servlet-handler/>标签
  - 没有配置，直接404
  - 有配置，会去寻找和路径相符的静态资源
    - 找不到，还是404
    - 找的到，成功访问到静态资源

结论：如果Controller的访问路径和静态资源的访问路径相同，则会走Controller



## 5.4普通的转发和重定向

**使用普通转发前提是配置了<mvc:default-servlet-handler />标签⚠️**

- 前端控制器dispatcherServlet处理响应数据，都是通过Thymeleaf渲染去完成的转发

- 普通转发和普通的重定向不走前端控制器dispatcherServlet

  - 不被前端控制器dispatcherServlet处理
  - 和Thymeleaf无关，所以和前缀和后缀也无关

- 普通转发和普通重定向：

  - 返回值还是String，在返回值前添加forward:转发的路径 或  redirect:重定向的路径
  - 和web阶段不同，框架阶段中的/含义都一样，都是上下文路径下，springMVC框架对/进行了处理

  ```java
  		//普通转发和重定向   
      @RequestMapping("/redirectTest")
      public String redirectTest(){
          System.out.println("获取请求参数，调用业务层，设置数据...");
          //使用普通转发到success页面
          return "forward:/WEB-INF/pages/success.html";
          //重定向到test页面
          return "redirect:/static/test.html";
      }
  ```

原理：定义了<mvc:default-servlet-handler />标签，没找到对应的controller就会根据路径查找资源。



## 5.5控制器转发和重定向★

一般情况下，处理器方法返回字符串类型的值会被当成逻辑视图名处理。如果返回的字符串中带 forward: 或 redirect: 前缀时，SpringMVC 会对他们进行特殊处理：将 forward: 和 redirect: 当成指示符，其后的字符串作为 URL 来处理。

**实现Controller之间的相互跳转实现转发和重定向：**⚠️

- redirect:/hello：会完成一个到 /hello 对应的controller 的重定向的操作。

- forward:/hellol：会完成一个到 /hello 对应的controller 的转发操作。

- 处理器方法：

  ```java
  		@RequestMapping("/testRedirect")
      public String testRedirect(){
          System.out.println("测试重定向");
        	//转发到访问路径"/hello"对应的controller
        	return "forward:/hello"; 
        	//重定向到访问路径"/hello"对应的controller
          return "redirect:/hello"; 
      }
  ```




## 5.6多个视图解析器的优先级

如果应用了多个视图解析器策略，那么就必须通过“order”属性来声明优先级，order值越低，则优先级越高

```xml
	<!-- Velocity视图解析器 默认视图 -->
	<bean id="velocityViewResolver"
		class="org.springframework.web.servlet.view.velocity.VelocityViewResolver">
		<property name="contentType" value="text/html;charset=UTF-8" />
		<property name="viewNames" value="*.html" />
		<property name="suffix" value="" />
		<property name="dateToolAttribute" value="date" />
		<property name="numberToolAttribute" value="number" />
		<property name="toolboxConfigLocation" value="/WEB-INF/velocity-toolbox.xml" />
		<property name="requestContextAttribute" value="rc" />
		<property name="order" value="0" />
	</bean>

	<!-- JSP视图解析器 -->
	<bean id="viewResolverJsp"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/page/" />
		<property name="viewClass"
			value="org.springframework.web.servlet.view.JstlView" />
		<property name="viewNames" value="*.jsp" />
		<property name="suffix" value="" />
		<property name="order" value="1" />
	</bean>

	<!-- FreeMarker视图解析器 -->
	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
		<property name="viewClass"
			value="org.springframework.web.servlet.view.freemarker.FreeMarkerView" />
		<property name="contentType" value="text/html; charset=utf-8" />
		<property name="cache" value="false" />
		<property name="viewNames" value="*.ftl" />
		<property name="suffix" value="" />
		<property name="order" value="2" />
	</bean>
```





# 6 REST_CRUD案例

根据Employees和Department两个实体类，编写REST风格的CRUD操作

## 6.1需求分析

- 获取所有员工操作
- 编写展示所有员工页面
- 添加员工操作
- 编写添加员工页面
- 修改员工操作
- 编写修改员工页面
- 删除员工操作
- 使用REST风格处理请求和响应



## 6.2环境搭建

搭建步骤：

- 第一步：创建web工程，导入依赖的jar包
- 第二步：配置web.wml文件
  - 配置过滤器解决乱码CharacterEncodingFilter
  - 配置前端控制器DispacherServlet
  - 配置过滤器HiddenHttpMethodFilter
- 第三步：创建SpringMVC的配置文件

代码实现：

- 第一步：创建web工程，导入依赖的jar包

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
  
      <groupId>com.atguigu</groupId>
      <artifactId>springMVC6</artifactId>
      <version>1.0</version>
  
      <!--打包方式设置为war⚠️-->
      <packaging>war</packaging>
  
      <properties>
          <maven.compiler.source>17</maven.compiler.source>
          <maven.compiler.target>17</maven.compiler.target>
      </properties>
  
      <dependencies>
          <!--spring-webmvc-->
          <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-webmvc</artifactId>
              <version>5.3.1</version>
          </dependency>
  
          <!-- 导入thymeleaf与spring5的整合包 -->
          <dependency>
              <groupId>org.thymeleaf</groupId>
              <artifactId>thymeleaf-spring5</artifactId>
              <version>3.0.12.RELEASE</version>
          </dependency>
  
          <!--servlet-api 注意这里的scope设置为provided-->
          <dependency>
              <groupId>javax.servlet</groupId>
              <artifactId>javax.servlet-api</artifactId>
              <version>4.0.1</version>
              <scope>provided</scope>
          </dependency>
      </dependencies>
  
  </project>
  ```

- 第二步：配置web.wml文件

  - 配置过滤器解决乱码CharacterEncodingFilter
  - 配置前端控制器DispacherServlet
  - 配置过滤器HiddenHttpMethodFilter

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
           version="4.0">
      <!--配置CharacterEncodingFilter过滤器，解决POST请求中文乱码问题
         注意：该过滤器一定要配置到最上面
     -->
      <filter>
          <filter-name>encodingFilter</filter-name>
          <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
          <!--设置属性encoding的值-->
          <init-param>
              <param-name>encoding</param-name>
              <param-value>utf-8</param-value>
          </init-param>
          <!--设置属性forceRequestEncoding的值-->
          <init-param>
              <param-name>forceRequestEncoding</param-name>
              <param-value>true</param-value>
          </init-param>
      </filter>
      <filter-mapping>
          <filter-name>encodingFilter</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>
      <!--配置前端控制器-->
      <servlet>
          <servlet-name>dispatcherServlet</servlet-name>
          <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
          <!-- 配置DispatcherServlet的初始化參數：设置SpringMVC文件的路径和文件名称 -->
          <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>classpath:Springmvc.xml</param-value>
          </init-param>
          <load-on-startup>1</load-on-startup>
      </servlet>
      <servlet-mapping>
          <servlet-name>dispatcherServlet</servlet-name>
          <url-pattern>/</url-pattern>
      </servlet-mapping>
  
      <!--配置HiddenHttpMethodFilte过滤器，目的是为了将POST请求转换为PUT或DELETE请求-->
      <filter>
          <filter-name>hiddenHttpMethodFilter</filter-name>
          <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
      </filter>
      <filter-mapping>
          <filter-name>hiddenHttpMethodFilter</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>
  </web-app>
  
  
  ```

- 第三步：创建SpringMVC的配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xmlns:mvc="http://www.springframework.org/schema/mvc"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
  
      <!--配置自动扫描的包-->
      <context:component-scan base-package="com.atguigu"></context:component-scan>
  
      <!--配置视图解析器-->
      <bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver" id="viewResolver">
          <!--配置字符集-->
          <property name="characterEncoding" value="UTF-8"></property>
          <!--配置模板引擎-->
          <property name="templateEngine">
              <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                  <!--配置模板解析器-->
                  <property name="templateResolver">
                      <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                          <!--配置前缀-->
                          <property name="prefix" value="/WEB-INF/pages/"></property>
                          <!--配置后缀-->
                          <property name="suffix" value=".html"></property>
                          <!--配置字符集-->
                          <property name="characterEncoding" value="UTF-8"></property>
                      </bean>
                  </property>
              </bean>
          </property>
      </bean>
      <!--去首页-->
      <mvc:view-controller path="/" view-name="index"></mvc:view-controller>
  
  
      <mvc:annotation-driven></mvc:annotation-driven>
  </beans>
  ```



## 6.3功能实现

实现步骤：

- 第一步：创建MVC架构的各种类和接口
- 第二步：使用注解将实现类放到spring容器中并完成注入
- 第三步：CRUD功能实现及控制模块编写
- 第四步：优化CRUD，合并添加和修改操作

代码实现：

- 第一步：创建MVC架构的各种类和接口

- 第二步：使用注解将实现类放到spring容器中并完成注入

- 第三步：CRUD功能实现及控制模块编写

  ```java
  package com.atguigu.controller;
  
  import com.atguigu.bean.Employee;
  import com.atguigu.service.impl.DepartmentServiceImpl;
  import com.atguigu.service.impl.EmployeeServiceImpl;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestMethod;
  
  import java.util.Map;
  
  /**
   * @Description: TODD
   * @AllClassName: com.atguigu.controller.EmployeeController
   */
  @Controller
  @RequestMapping("/employee")
  public class EmployeeController {
  
      @Autowired
      EmployeeServiceImpl employeeService;
      @Autowired
      DepartmentServiceImpl departmentService;
  
      //不设置value属性，当直接访问/employee时到展示所有员工信息的页面
      @RequestMapping
      public String toShowPage(Map map) {
          map.put("employees", employeeService.showAllEmp());
          return "employee/show";
      }
  
      //跳转到创建页面-合并后被淘汰！
      @RequestMapping(
              value = "/toCreatePage",
              method = RequestMethod.GET
      )
      public String toCreatePage(Map map) {
          map.put("departments", departmentService.showAllDept());
          return "employee/create";
      }
  
      //跳转到修改页面-合并后被淘汰！
      @RequestMapping(
              value = "/toEditPage/{id}",
              method = RequestMethod.GET
      )
      public String toEditPage(
              @PathVariable Integer id,
              Map map
      ) {
          map.put("employee", employeeService.showEmp(id));
          map.put("departments", departmentService.showAllDept());
          return "employee/edit";
      }
  
      //创建对象-合并后被淘汰！
      @RequestMapping(
              value = "/create",
              method = RequestMethod.PUT
      )
      public String create(Employee employee) {
          employeeService.createEmp(employee);
          return "redirect:/employee";
      }
  
      //创建或修改对象-合并后同时用来执行创建和修改操作！
      @RequestMapping(
              value = "/edit",
              method = RequestMethod.POST
      )
      public String edit(Employee employee) {
          employeeService.editEmp(employee);
          return "redirect:/employee";
      }
  
      //通过id删除对象
      @RequestMapping(
              value = "/delete/{id}",
              method = RequestMethod.DELETE
      )
      public String delete(@PathVariable Integer id) {
          employeeService.deleteEmp(id);
          return "redirect:/employee";
      }
  
  }
  
  ```

- 第四步：优化CRUD，合并添加和修改操作

  ```java
      //跳转到创建或修改页面-合并
      @RequestMapping(
              value = {"/toEditPage/{id}", "/toCreatePage/{id}"},
              method = RequestMethod.GET
      )
      public String toCreateOrEditPage(
              @PathVariable Integer id,
              Map map
      ) {
          Employee employee;
  
          //当id为0时去新增页面，否则去修改页面
          if (id == 0) {
              employee = new Employee(null, null, null, null, null);
          } else {
              employee = employeeService.showEmp(id);
          }
  
          //向请求域添加信息
          map.put("employee", employee);
          map.put("departments", departmentService.showAllDept());
  
          return "employee/editOrCreate";
      }
  
      //创建或修改对象-优化后合并到这一个方法
      @RequestMapping(
              value = "/edit",
              method = RequestMethod.POST
      )
      public String edit(Employee employee) {
          employeeService.editEmp(employee);
          return "redirect:/employee";
      }
  ```
  
  



# 7 处理JSON

## 7.1HttpMessageConverter

**注意：SpringMVC的配置文件中一定要配置mvc:annotation-driven标签。**⚠️

HttpMessageConverter<T> 是 Spring3.0 新添加的一个接口，负责将请求信息转换为一个对象（类型为 T），将对象（类型为 T）输出为响应信息。

使用 HttpMessageConverte ，Spring 提供了两种途径：

- 使用 @RequestBody / @ResponseBody 对处理方法进行标注。
- 使用 HttpEntity<T> / ResponseEntity<T> 作为处理方法的入参或返回值。

### 7.1.1@RequestBody注解★

**对方法进行标注，负责将请求信息转换为一个对象**

概述：

- 可以将请求体转换为一个String对象

- Ajax通过异步请求发送json对象到Controller，@RequestBody注解可以将json信息自动装配到Bean对象的属性上

- 注意：get请求是没有请求体，只有post请求有请求体，对get请求使用会异常

使用方法：

```java
@RequestMapping("/index")
//必须有请求体，处理get请求状态码400
public String toInwwwbbx(@RequestBody String bodyMsg){
		//输出结果为name=tom&age=18
    System.out.println(bodyMsg);
    return "success";
}
//后期使用异步请求时，自动装配对象
@RequestMapping("/index")
public String toInwwwbbx(@RequestBody Employee employee){
    return "success";
}
```

**注意：SpringMVC的配置文件中一定要配置mvc:annotation-driven标签。**⚠️



### 7.1.2HttpEntity

**作为处理方法的入参或返回值，负责将请求信息转换为一个对象**

概述：

- 将请求的信息(请求头和请求体)变为HttpEntity对象，泛型就是对象的存储类型

- get请求和post请求都可以使用，get请求没没有请求体获取的值为null

- getHeaders()获取请求头

- getBody()获取请求体

使用方法：

```java
@RequestMapping("/index")
public String toInwwwbbx(HttpEntity<String> httpEntity){
		//泛型内规定了接收的类型
    String headers = httpEntity.getHeaders();
    String body = httpEntity.getBody();
    return "success";
}
```



### 7.1.3@ResponseBody注解★

**对方法进行标注，负责将对象（类型为 T）输出为响应信息**

概述：

 *  位置：方法上、类上（相当于对类中所有的方法都加了这个注解）
 *  功能：将当前Controller方法的返回值作为响应体数据，输出给请求的客户端

 * Controller方法的返回值默认是逻辑视图，如果是Ajax的异步请求，需要给出响应数据
 * 通过输出流写回数据，相当于response.getWriter().write("xxx")

使用方法：

```java
    @RequestMapping("/b")
    @ResponseBody
    public String responseBean(){
        return "i am response data";
    }
```

**注意：SpringMVC的配置文件中一定要配置mvc:annotation-driven标签。**⚠️



### 7.1.4ResponseEntity

**使用ResponseEntity<T>完成文件下载功能**

- 用法：设置为controller方法的返回值。
- 位置：方法返回值位置

使用方法：

- 将需要下载的文件数据存储到byte数组内：

  - 拿到下载文件的绝对路径

  - 创建输入流，管理下载文件

  - 创建byte数组，将数据读取到byte数组内

- 创建ResponseEntity对象，作为返回数据返回
  - ResponseEntity三个构造参数分别为文件的byte数组、响应头、状态码HttpStatus.OK
  - 创建响应头对象MultiValueMap<String,String> headers=new HttpHeaders();
  - 告诉浏览器响应的是个文件headers.add("Content-Disposition","attachment;filename="文件名称");
- 测试文件存放位置：webapp/upload/面试.mp4
- 使用方法：

```java
/**
 *  文件下载
 *   ResponseEntity<T>完成文件下载功能
 *   位置：方法返回值位置
 */
@RequestMapping("/download")
public ResponseEntity<byte[]> download(HttpServletRequest request){
    //1. 将需要下载的文件数据存储到byte数组内
    //1.1 拿到下载文件的绝对路径
    ServletContext servletContext = request.getServletContext();
    String realPath = servletContext.getRealPath("/upload/面试.mp4");
    System.out.println("下载文件的绝对路径："+realPath);
    //1.2 创建输入流，执行待下载文件
    byte[] bytes=null;
    try {
        InputStream input=new FileInputStream(realPath);
        //1.3 创建byte数组，将数据读取到byte数组内
        bytes=new byte[input.available()];//将文件的大小作为byte数组的长度
        input.read(bytes);
    } catch (Exception e) {
        e.printStackTrace();
    }
    //2. 创建ResponseEntity对象，作为返回数据返回
    //需要将上面的byte数组，传进去
    //2.1 不设置响应头时，将数据响应给浏览器，浏览器会直接将二进制乱码显示在页面上，而不是下载文件
    //通过设置响应头的方式，告诉浏览器，我这是一个文件，不要打开，要下载
    MultiValueMap<String,String> headers=new HttpHeaders();
    //此处可以修改下载的文件名
    String filename="mushi面试.mp4";
    try {
        //如果文件名存在中文，特殊处理一下
        filename=URLEncoder.encode(filename,"utf-8");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    headers.add("Content-Disposition","attachment;filename="+filename);
    ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(bytes,headers, HttpStatus.OK);
    return responseEntity;
}
```



## 7.2返回JSON格式★

向客户端响应json字符串的方式：

 *      ① 自己手动编写json字符串
 *      ② 手动将java对象转为json字符串，将json字符串return
 *      **③ 直接return java对象，让SpringMVC框架将java对象转为json字符串，响应给客户端  (这里进行说明)★**

步骤：

* 第一步：导入jackson的jar包(天然和SpringMVC是整合关系)。也可以使用Jackson，Fastjson，Gson等JSON转换组件

  ```xml
  <!-- jackson-databind -->
  <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.12.3</version>
  </dependency>
  ```

  使用jackson以外的其他JSON转换组件，需要在配置文件中说明，比如使用fastJson组件：

  ```xml
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
  ```

* 第二步：必须保证已配置<mvc:annotation-driven />标签才能装配Jackson的转换器

  ```xml
  <mvc:annotation-driven/>
  ```

* 第三步：在Controller方法上添加@ResponseBody注解，可以对**Bean对象、集合、数组等对象使用**。

  ```java
  		@RequestMapping("/b")
      @ResponseBody
     	//添加@ResponseBody，修改返回值，返回对象
      public Dog responseBean(){
          Dog dog = new Dog("大黄",3);
          System.out.println(dog);
          return dog;
      }
  ```




## 7.3mvc:annotation-driven★

需要配置mvc:annotation-driven标签的情况：

- 配置不经过处理器方法直接响应的页面即配置了mvc:view-controller标签
- 配置了处理静态资源即配置了mvc:default-servlet-handler标签

配置mvc:annotation-driven标签前后说明：

- 没有配置mvc:view-controller标签时：
  - DispatcherServlet中装配的handlerAdapters有以下4个：
    - HttpRequestHandlerAdapter
    - SimpleControllerHandlerAdapte
    - RequestMappingHandlerAdapter
    - HandlerFunctionAdapter
- 配置了mvc:view-controller标签，没有配置mvc:annotation-driven标签时：
  - DispatcherServlet中装配的handlerAdapters少了了2个：
    - HttpRequestHandlerAdapter
    - SimpleControllerHandlerAdapte
    - **RequestMappingHandlerAdapter（消失：导致所有的请求找到不映射）**
    - **HandlerFunctionAdapter（消失）**
- 配置了mvc:view-controller标签，也配置mvc:annotation-driven标签（添加转换器）时：
  - DispatcherServlet中装配的handlerAdapters又变成了3个：
    - HttpRequestHandlerAdapter
    - SimpleControllerHandlerAdapte
    - **RequestMappingHandlerAdapter（再次添加了映射转换器）**





# 8 文件上传

## 8.1MultipartResolver

**MultipartResolver是文件上传的核心接口**

文件上传核心接口和实现类：

- Spring MVC 为文件上传提供了直接的支持，通过MultipartResolver 接口实现。
- Spring MVC 上下文中默认没有装配 MultipartResovler，需要在上下文中配置 MultipartResolver。
- Jakarta Commons FileUpload 技术实现了一个 MultipartResolver接口， 实现类是：CommonsMultipartResolver  



## 8.2文件上传步骤★

场景：客户端将文件传输到服务器端

上传步骤：

- 创建POST表单
- 导入依赖的jar包
- 配置文件上传解析器
- 编写Controller方法
- 使用@RequestParam注解接收上传文件的数据

代码实现：

- 准备工作

  - 创建一个表单(带文件表单)

    - 必须为post请求
    - 给form表单添加属性 enctype="multipart/form-data"

    ```html
    <form action="upload" method="post" enctype="multipart/form-data">
        <!--普通的表单-->
        username:<input type="text" name="username" value="admin"/><br/>
        password:<input type="password" name="password" value="123456"><br/>
        <!--单个文件表单-->
        file:<input type="file" name="file"/><br/>
        <input type="submit">
    </form>
    ```

  - 导入文件上传依赖的jar包

    ```xml
    <!-- commons-fileupload -->
    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.4</version>
    </dependency>
    ```

  - 配置文件上传解析器

    - 在Springmvc.xml中配置一个bean
      - 设置字符集
      - 设置单个上传文件大小，单位byte
      - 设置总上传文件大小，单位byte
    - id的值必须是：multipartResolver
    - bean的全类名时：org.springframework.web.multipart.commons.CommonsMultipartResolver

    ```xml
    <!--配置文件上传的解析器：id的值必须是：multipartResolver-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!--设置字符集-->
        <property name="defaultEncoding" value="utf-8"/>
        <!--设置单个上传文件的大小，102400为100kb-->
        <property name="maxUploadSizePerFile" value="102400"/>
        <!--设置总上传文件的大小，因为一个表单有多个文件-->
        <property name="maxUploadSize" value="1024000"/>
    </bean>
    ```

- 编写Controller方法，进行上传功能实现

  - 普通表单的获取使用@RequestParam注解，请求参数名与入参变量名一致时注解可省略
  - 文件表单获取单个文件时@RequestParam("file") MultipartFile file
    - 获取表单中名为file的文件，把该文件对象赋给file
  - 文件表单获取多个文件时@RequestParam("file") MultipartFile[] files
    - 上传文件的input的name属性相同时，会放到一个数组内
  - 根据路径创建文件后，通过file.transferTo(file1)将要上传的文件写入到file中
  - file创建时传入的路径位置就是文件上传到服务器上的路径位置

  ```java
  @RequestMapping("/upload")
  public String upload(
          String username,
          String password,
          @RequestParam("file") MultipartFile[] files,
          @RequestParam("file666") MultipartFile file666,
          HttpServletRequest request){
      //普通表单的获取
      System.out.println("username = " + username);
      System.out.println("password = " + password);
      //文件表单的获取
      ServletContext servletContext = request.getServletContext();
      String realPath = servletContext.getRealPath("/upload");
    	//上传多个文件时，循环去做⚠️
      for (MultipartFile file : files) {
          //文件的名字获取
          System.out.println("file.getName() = " + file.getName());
          System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
  
          //文件要保存到服务器上
          //获取到上传的位置
          try {
            	//指定位置
              File file1=new File(realPath+"/"+file.getOriginalFilename());
              //写入的文件代码
              file.transferTo(file1);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  
      return "success";
  }
  ```

  



# 9 拦截器

Spring MVC也可以使用拦截器对请求进行拦截处理，用户可以自定义拦截器来实现特定的功能，自定义的拦截器可以实现HandlerInterceptor接口，也可以继承HandlerInterceptorAdapter 适配器类。

## 9.1拦截器运行原理

### 9.1.1拦截器核心接口★

**HandlerInterceptor接口方法说明：**

- **preHandle()：**业务处理器处理请求之前被调用。
  - 可以在此方法中做一些权限的校验。
  - 如果该拦截器对请求进行拦截处理后还要调用其他的拦截器，或者是业务处理器去进行处理，则返回true。
  - 如果程序员决定不需要再调用其他的组件去处理请求，则返回false。
- **postHandle()：**业务处理器处理请求之后，渲染视图之前调用。
  - 在此方法中可以对ModelAndView中的模型和视图进行处理。
- **afterCompletion()：**这个方法在 DispatcherServlet 完全处理完请求后被调用。
  - 可以在该方法中进行一些资源清理的操作。



### 9.2拦截器使用方法★

- 创建一个类实现HandlerInterceptor接口

  - 重写接口的三个方法
  - **preHandle()方法的布尔返回值会决定是否放行，重点设置！**

- 在SpringMVC配置文件中配置拦截器**（两种方式）**

  - 通过bean直接配置的拦截器，会拦截所有请求

  - 通过preHandle()方法的返回值为true进行放行

  - 配置拦截器方式一

    ```xml
    <!--配置拦截器方式1-->
    <mvc:interceptors>
      <!--声明定义的拦截器,通过这种方式定义的拦截器会拦截所有请求-->
      <bean id="firstInterceptor" class="com.atguigu.controller.InterceptorTest"></bean>
    </mvc:interceptors>
    ```

  - 配置拦截器方式二

    ```xml
    <mvc:interceptors>
      <mvc:interceptor>
        <!--设置拦截路径-->
        <mvc:mapping path="/emp/**"/>
        <!--声明定义的拦截器-->
        <bean id="firstInterceptor" class="com.atguigu.controller.InterceptorTest"></bean>
      </mvc:interceptor>
    </mvc:interceptors>
    ```

- 设置拦截路径

  - 使用<mac:mapping path="拦截路径"/>标签

  - 使用通配符 *， 同时拦多个路径

    - 通配符只可以拦截相同的级别。比如：emp/* 可以拦截 emp/abc 无法拦截 emp/all/show

  - 使用通配符 **， 同时拦多个级别的多个路径

    

### 9.3拦截器源码分析

拦截器源码：

在DispatcherServlet类中，第531行，执行所有拦截器的preHandle

```java
if (!mappedHandler.applyPreHandle(processedRequest, response)) {
    return;
}
```

在DispatcherServlet类中，第535行，执行controller方法

```java
mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
```

在DispatcherServlet类中，第541行，执行拦截器的postHandle方法

```java
mappedHandler.applyPostHandle(processedRequest, response, mv);
```

在DispatcherServlet类中，第601行，执行拦截器的afterCompletion方法

```java
mappedHandler.triggerAfterCompletion(request, response, (Exception)null);
```

在HandlerExecutionChain类中，第72行，执行applyPreHandle方法中遍历所有的拦截器执行拦截器的preHandle方法

```java
boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    for(int i = 0; i < this.interceptorList.size(); this.interceptorIndex = i++) {
        HandlerInterceptor interceptor = (HandlerInterceptor)this.interceptorList.get(i);
      	//这里获取会执行每一个拦截器的preHandle方法，若返回的false，取反后走if代码块，然后返回false结束方法
      	//默认拦截器返回的值为true，取反后为false，一定不走if代码块
        if (!interceptor.preHandle(request, response, this.handler)) {
          	//return方法之前，要执行所有放行拦截器的after⚠️
            this.triggerAfterCompletion(request, response, (Exception)null);
            return false;
        }
    }

    return true;
}
```

在HandlerExecutionChain类中，第84行，applyPostHandle方法中反向遍历所有的拦截器执行拦截器的postHandle方法

```java
void applyPostHandle(HttpServletRequest request, HttpServletResponse response, @Nullable ModelAndView mv) throws Exception {
		//倒序循环，先执行自己的postHandle方法，最后执行默认自带的⚠️
    for(int i = this.interceptorList.size() - 1; i >= 0; --i) {
        HandlerInterceptor interceptor = (HandlerInterceptor)this.interceptorList.get(i);
        interceptor.postHandle(request, response, this.handler, mv);
    }

}
```

在HandlerExecutionChain类中，第92行的triggerAfterCompletion方法中反向遍历所有的拦截器执行拦截器的afterCompletion方法

```java
void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, @Nullable Exception ex) {
  //倒序循环，先执行自己的afterCompletion方法，最后执行默认自带的⚠️
  for(int i = this.interceptorIndex; i >= 0; --i) {
        HandlerInterceptor interceptor = (HandlerInterceptor)this.interceptorList.get(i);

        try {
            interceptor.afterCompletion(request, response, this.handler, ex);
        } catch (Throwable var7) {
            logger.error("HandlerInterceptor.afterCompletion threw exception", var7);
        }
    }

}
```



## 9.4配置单个拦截器★

配置步骤：

- 第一步：创建一个类实现HandlerInterceptor接口，并重写三个方法
- 第二步：在SpringMVC配置文件中配置拦截器

实现代码：

- 第一步：创建一个类实现HandlerInterceptor接口，并重写三个方法

  ```java
  public class InterceptorTest implements HandlerInterceptor {
      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
          System.out.println("拦截器执行了preHandle～");
          return true;
      }
  
      @Override
      public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
          System.out.println("拦截器执行了PostHandle～");
      }
  
      @Override
      public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
          System.out.println("拦截器执行了afterCompletion～");
      }
  }
  
  ```

- 第二步：在SpringMVC配置文件中配置拦截器

  ```xml
  <mvc:interceptors>
    <mvc:interceptor>
      <!--设置拦截路径-->
      <mvc:mapping path="/emp/**"/>
      <!--声明定义的拦截器-->
      <bean id="firstInterceptor" class="com.atguigu.controller.InterceptorTest"></bean>
    </mvc:interceptor>
  </mvc:interceptors>
  ```



## 9.5配置多个拦截器★

**多个拦截器的执行顺序由SpringMVC配置文件中配置的顺序决定，和JavaWeb的过滤器相同，为责任链设计模式**⚠️

配置步骤：

- 第一步：创建多个类实现HandlerInterceptor接口，并重写三个方法

- 第二步：在SpringMVC配置文件中配置所有的拦截器

  ```xml
      <mvc:interceptors>
          <!--配置第一个拦截器-->
          <mvc:interceptor>
              <mvc:mapping path="/emp/**"/>
              <bean id="firstInterceptor" class="com.atguigu.controller.InterceptorTest"></bean>
          </mvc:interceptor>
          <!--配置第二个拦截器-->
          <mvc:interceptor>
              <mvc:mapping path="/emp/**"/>
              <bean id="firstInterceptor2" class="com.atguigu.controller.InterceptorTest2"></bean>
          </mvc:interceptor>
          <!--配置第三个拦截器-->
          <mvc:interceptor>
              <mvc:mapping path="/emp/**"/>
              <bean id="firstInterceptor3" class="com.atguigu.controller.InterceptorTest3"></bean>
          </mvc:interceptor>
      </mvc:interceptors>
  ```

  

## 9.6多个拦截器执行顺序★

### 9.6.1多个拦截器都放行

- preHandle是正序的
  - 由配置文件的上下位置绝对
- 剩下两个是逆序的

**多个拦截器都放行时，控制台打印信息：**

```java
//执行preHandle方法时，是正序执行
拦截器1执行了preHandle方法
拦截器2执行了preHandle方法
拦截器3执行了preHandle方法
//执行PostHandle方法时，是倒序执行⚠️ 执行完所有preHandle方法才开始执行
拦截器3执行了PostHandle方法
拦截器2执行了PostHandle方法
拦截器1执行了PostHandle方法
//执行afterCompletion方法时，是倒序执行⚠️ 执行完所有PostHandle方法才开始执行
拦截器3执行了afterCompletion方法
拦截器2执行了afterCompletion方法
拦截器1执行了afterCompletion方法
```



### 9.6.2多个拦截器有不放行

- 两个拦截器

  - 第一个不放行，如何执行
    - 执行第一个拦截器的preHandle，其他都不执行

  - 第一个放行，第二个不放行，如何执行
    - 执行第一个拦截器的preHandle，执行第二个拦截器的preHandle，执行第一个拦截器的afterCompletion
    - 只要放行的拦截器，必定要执行afterCompletion方法
- 总结：
  - 若有n个拦截器，则从1到n逐个执行，遇到不放行的才停止
  - 一直执行到不放行拦截器的preHandle方法
  - 然后开始倒序执行放行拦截器的afterCompletion方法
  - 最后底层源码接连return false，终止doDispatch方法

**一个拦截器不放行时，控制台打印信息：**

```java
//执行preHandle方法，正常可以执行
拦截器1执行了preHandle方法
//postHandle方法未执行⚠️
//afterCompletion方法未执行⚠️
```

**多个拦截器第三个不放行时，控制台打印信息：**

```java
//执行preHandle方法，三个都可以执行
拦截器1执行了preHandle方法
拦截器2执行了preHandle方法
拦截器3执行了preHandle方法
//所有的postHandle方法都未执行⚠️
//执行afterCompletion方法时，第三个拦截器未执行，其他的仍倒叙执行
拦截器2执行了afterCompletion方法
拦截器1执行了afterCompletion方法
```





# 10 异常处理

## 10.1异常处理简介

**SpringMVC通过 HandlerExceptionResolver接口处理程序的异常，包括 Handler 映射、数据绑定以及目标方法执行时发生的异常。**

**SpringMVC提供的 HandlerExceptionResolver 是接口的实现类**

DispatcherServlet默认装配的异常处理器有：

- ExceptionHandlerExceptionResolver
- ResponseStatusExceptionResolver
- DefaultHandlerExceptionResolver

DispatcherServlet默认没有装配装的异常处理器有：

- SimpleMappingExceptionResolver



## 10.2源码分析

DefaultHandlerExceptionResolver类第46行

```java
//出现异常后，也是返回一个ModelAndView类的对象mv，只不过这个ModelAndView是异常的mv
//返回的这个mv可正常解析，正常渲染，就是发生异常后响应给客户端浏览器的样子
protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex){
  ...
}
```

SimpleMappingExceptionResolver类第76行

```java
//根据是否出现异常，返回不同的ModelAndView
protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
    String viewName = this.determineViewName(ex, request);
    if (viewName != null) {
        Integer statusCode = this.determineStatusCode(request, viewName);
        if (statusCode != null) {
            this.applyStatusCodeIfPossible(request, response, statusCode);
        }

        return this.getModelAndView(viewName, ex, request);
    } else {
        return null;
    }
}
```

关于controller中出现异常的情况分析：

```java
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpServletRequest processedRequest = request;
    HandlerExecutionChain mappedHandler = null;
    boolean multipartRequestParsed = false;
    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

    try {
        try {
            ModelAndView mv = null;
            Exception dispatchException = null;

            try {
                processedRequest = this.checkMultipart(request);
                multipartRequestParsed = processedRequest != request;
                mappedHandler = this.getHandler(processedRequest);
                if (mappedHandler == null) {
                    this.noHandlerFound(processedRequest, response);
                    return;
                }

                HandlerAdapter ha = this.getHandlerAdapter(mappedHandler.getHandler());
                String method = request.getMethod();
                boolean isGet = "GET".equals(method);
                if (isGet || "HEAD".equals(method)) {
                    long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
                    if ((new ServletWebRequest(request, response)).checkNotModified(lastModified) && isGet) {
                        return;
                    }
                }

                if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                    return;
                }
								//这里开始执行controller方法，若方法发生异常则拿不到ModelAndView对象mv，被下面的catch捕捉
                mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
                if (asyncManager.isConcurrentHandlingStarted()) {
                    return;
                }
							
                this.applyDefaultViewName(processedRequest, mv);
              	//发生异常后拦截器的PostHandle方法也没执行，直接跳到下面的catch中
                mappedHandler.applyPostHandle(processedRequest, response, mv);
            } catch (Exception var20) {
              	//进catch执行异常处理
                dispatchException = var20;
            } catch (Throwable var21) {
                dispatchException = new NestedServletException("Handler dispatch failed", var21);
            }
						//跳出catch后开始执行视图解析，可这时mv对象并没拿到，为null值
          	//在processDispatchResult方法中会根据有无异常来将不同的ModelAndView对象赋给mv
          	//返回异常的ModelAndView对象时，会将自己配置的逻辑视图放到view中，异常信息放到model中
          	//因为配置了SimpleMappingExceptionResolver异常处理器，就会根据配置参数参与处理异常
            this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
        } catch (Exception var22) {
            this.triggerAfterCompletion(processedRequest, response, mappedHandler, var22);
        } catch (Throwable var23) {
            this.triggerAfterCompletion(processedRequest, response, mappedHandler, new NestedServletException("Handler processing failed", var23));
        }

    } finally {
        if (asyncManager.isConcurrentHandlingStarted()) {
            if (mappedHandler != null) {
                mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
            }
        } else if (multipartRequestParsed) {
            this.cleanupMultipart(processedRequest);
        }

    }
}
```



## 10.3自定义异常响应页面★

默认是通过HandlerExceptionResolver (DefaultHandlerExceptionResolver)去完成异常的视图渲染

想自定义异常处理页面，必须使用SimpleMappingExceptionResolver替代掉他来完成异常的视图渲染

SimpleMappingExceptionResolve可对所有异常进行统一处理，它将异常类名映射为逻辑视图名，发生异常时响应渲染的逻辑视图。

**使用SimpleMappingExceptionResolver的步骤：**

- 第一步：在SpringMVC的配置文件中配置异常处理器SimpleMappingExceptionResolver
  - 为exceptionMappings注入属性值，即设置异常类型和逻辑视图的映射关系
  - 异常类型捕捉顺序和配置顺序无关，原则是先找最匹配的
- 第二步：创建发生异常时响应的视图页面
  - 发生异常时会将异常信息添加到请求域中，使用${exception}获取即可

代码实现：

- 第一步：在SpringMVC的配置文件中配置异常处理器SimpleMappingExceptionResolver

  - 为exceptionMappings注入属性值，即设置异常类型和逻辑视图的映射关系
  - 异常类型捕捉顺序和配置顺序无关，原则是先找最匹配的

  ```xml
  <bean id="exceptionResolver" class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
    <!--配置异常和页面的对应关系：什么异常去什么页面-->  
    <property name="exceptionMappings">
      		<!--properties集合的属性注入-->  
          <props>
              <!--key为异常类型的全类名，value为逻辑视图-->
              <prop key="java.lang.ArithmeticException">error01</prop>
              <prop key="java.lang.NullPointException">error02</prop>
              <prop key="java.lang.Exception">error03</prop>
          </props>
      </property>
      <!--设置向request域中存放异常信息的key，默认是exception-->
    	<!--修改exceptionAttribute的属性值即可，不过没必要，也没意义>
      <property name="exceptionAttribute" value="exception"></property>
  </bean>
  ```

- 第二步：创建发生异常时响应的视图页面

  - 发生异常时会将异常信息添加到请求域中，使用${exception}获取即可

  ```html
  <!DOCTYPE html>
  <html lang="en" xmlns:th="http://www.thymeleaf.org">
  <head>
      <base th:href="@{/}">
      <meta charset="UTF-8">
      <title>Title</title>
  </head>
  <body>
  <h1>异常信息是：</h1>
  <h1 th:text="${exception}"></h1>
  </body>
  </html>
  ```

  



# 11 SpringMVC的运行流程

**客户端发送请求到服务器，先到是前端控制器DispatcherServlet。**

## 11.1运行流程描述★

前端控制器执行doDispatch方法进行处理请求：

- 前端控制器会去解析url，从判断是否存在对应的映射(Controller方法)
  - 如果有，会进行下一步
  - 如果没有
    - 判断是否配置了静态资源的访问(是否配置了mvc:default-servlet-handler)
    - 如果配置了，去寻找静态资源
      - 找得到，直接响应静态资源的内容
      -  找不到，还是404
    - 如果没有配置，直接就是404

- 接上一步，开始寻找HandlerMapping(接口) → RequestMppingHandlerMapping(实现类)
  - 获取到当前映射路径对应的拦截器(Interceptor)和处理器(Controller)-底层有map存储拦截器和Controller与url的映射关系

- 在去获取HandlerAdapter(接口) → RequestMppingHandlerAdapter(实现类)得到ha，下面用来执行Controller
- 开始执行拦截器的preHandle(正序)
  - 拦截器都放行，会进行下一步
  - 拦截器有不放行的，执行完放行拦截器的afterCompletion(逆序)，**doDispatch方法结束**

- 接上一步，开始通过HandlerAdapter执行Controller，拿到对象mv(ModelAndView)
  - 执行Controller时是否有异常产生：
    - 没有异常，会进行下一步
    - 有异常产生，执行Controller得到的mv是一个null值
      - **不会执行拦截器的postHandle(逆序的）**
      - 会进行视图渲染，渲染之前，会进行异常的ModelAndView的生成
        - 异常解析器会在这时运行，解析异常获取到逻辑视图和异常数据的key值
      - 正常进行视图的渲染即可

- 接上一步，Controller会返回一个正常的ModelAndView对象
- 执行拦截器的postHandle(逆序的)
- 进行视图的渲染
  - 寻找到合适的视图解析器ViewResolver → ThymeleafViewResolver
  - 根据逻辑视图，解析出视图对象View → ThymeleafView
  - 进行View对象的渲染
- 执行拦截器的afterCompletion(逆序)
- 将View视图交给浏览器进行展示即可(我们就在浏览器看到了网页内容)

## 11.2运行流程源码

前端控制器DispatcherServlet类的doDispatch方法：

```java
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpServletRequest processedRequest = request;
        //一个执行链，不仅有要执行的分控制器方法，还有相应的多个拦截器
        HandlerExecutionChain mappedHandler = null;
        boolean multipartRequestParsed = false;
        WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

        try {
            try {
              	//渲染视图用到的对象：域数据+逻辑视图
                ModelAndView mv = null;
                Exception dispatchException = null;

                try {
                    processedRequest = this.checkMultipart(request);
                    multipartRequestParsed = processedRequest != request;
                  	//查找当前请求的url对应的Interceptor和Controller，循环+判断 标记1
                    mappedHandler = this.getHandler(processedRequest);
                    if (mappedHandler == null) {
                        this.noHandlerFound(processedRequest, response);
                        return;
                    }
										//变量ha是RequestMppingHandlerAdapter类的实例，用来执行controller，循环+判断 标记2
                    HandlerAdapter ha = this.getHandlerAdapter(mappedHandler.getHandler());
                    String method = request.getMethod();
                    boolean isGet = "GET".equals(method);
                    if (isGet || "HEAD".equals(method)) {
                        long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
                        if ((new ServletWebRequest(request, response)).checkNotModified(lastModified) && isGet) {
                            return;
                        }
                    }
										//执行拦截器的preHandle 标记3
                    if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                        return;
                    }
										//执行Controller，拿到mv对象，mv是ModelAndView类的实例，Controller执行异常就走catch 标记4
                    mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
                    if (asyncManager.isConcurrentHandlingStarted()) {
                        return;
                    }

                    this.applyDefaultViewName(processedRequest, mv);
                  	//执行拦截器的postHandle(逆序的)，但前提是执行controller，不然直接跳到catch 标记5
                    mappedHandler.applyPostHandle(processedRequest, response, mv);
                } catch (Exception var20) {
                  	//捕捉执行controller发生的异常，并给成员变量dispatchException赋值异常对象
                    dispatchException = var20;
                } catch (Throwable var21) {
                    dispatchException = new NestedServletException("Handler dispatch failed", var21);
                }
								//拿到mv对象后，开始准备视图解析，方法内部执行render方法，执行拦截器afterCompletion(倒序) 标记6
                //若有异常，会执行异常处理器，拿到异常的ModelAndView，然后寻找合适的视图解析器进行渲染
                this.processDispatchResult(processedRequest, response, mappedHandler, mv, (Exception)dispatchException);
            } catch (Exception var22) {
              	//若视图解析器执行发生了异常，仍执行拦截器afterCompletion(倒序) 
                this.triggerAfterCompletion(processedRequest, response, mappedHandler, var22);
            } catch (Throwable var23) {
                this.triggerAfterCompletion(processedRequest, response, mappedHandler, new NestedServletException("Handler processing failed", var23));
            }

        } finally {
            if (asyncManager.isConcurrentHandlingStarted()) {
                if (mappedHandler != null) {
                    mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
                }
            } else if (multipartRequestParsed) {
                this.cleanupMultipart(processedRequest);
            }

        }
    }

```

标记信息解读：

**标记1：**getHandler方法，循环所有拦截器并进行判断，寻找当前请求url对应的拦截器

```Java
@Nullable
protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
    if (this.handlerMappings != null) {
        Iterator var2 = this.handlerMappings.iterator();

        while(var2.hasNext()) {
            HandlerMapping mapping = (HandlerMapping)var2.next();
            HandlerExecutionChain handler = mapping.getHandler(request);
            if (handler != null) {
                return handler;
            }
        }
    }

    return null;
}
```

**标记2：**getHandlerAdapter方法，循环所有处理器适配器并进行判断，寻找可以执行当前controller的处理器适配器

```Java
protected HandlerAdapter getHandlerAdapter(Object handler) throws ServletException {
    if (this.handlerAdapters != null) {
        Iterator var2 = this.handlerAdapters.iterator();

        while(var2.hasNext()) {
            HandlerAdapter adapter = (HandlerAdapter)var2.next();
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
    }

    throw new ServletException("No adapter for handler [" + handler + "]: The DispatcherServlet configuration needs to include a HandlerAdapter that supports this handler");
}
```

**标记3：**applyPreHandle方法，正序循环执行所有适配拦截器的preHandle()，并通过返回值判断是否放行。要是有不放行的，就执行之前所有放行拦截器的AfterCompletion方法，并且rutern false，终止整个doDispatch方法，不在执行controller方法不会渲染视图。

```Java
boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
    for(int i = 0; i < this.interceptorList.size(); this.interceptorIndex = i++) {
        HandlerInterceptor interceptor = (HandlerInterceptor)this.interceptorList.get(i);
        if (!interceptor.preHandle(request, response, this.handler)) {
            this.triggerAfterCompletion(request, response, (Exception)null);
            return false;
        }
    }

    return true;
}
```

**标记4：**handle方法，通过HandlerAdapter执行Controller，返回ModelAndView对象，渲染视图时使用。

```Java
ModelAndView handle(HttpServletRequest var1, HttpServletResponse var2, Object var3) throws Exception;
```

**标记5：**applyPostHandle方法，倒序循环执行所有适配拦截器的PostHandle()，前提是执行Controller时没有异常。

```Java
void applyPostHandle(HttpServletRequest request, HttpServletResponse response, @Nullable ModelAndView mv) throws Exception {
    for(int i = this.interceptorList.size() - 1; i >= 0; --i) {
        HandlerInterceptor interceptor = (HandlerInterceptor)this.interceptorList.get(i);
        interceptor.postHandle(request, response, this.handler, mv);
    }

}
```

**标记6：**processDispatchResult方法，用于当前请求的响应视图的解析。

将当前请求对应的拦截器、ModelAndView、异常信息对象等作为参数传递到方法中。

方法的位置在catch下面，因此无论执行Controller时没有异常都会执行。

如果实参异常信息不为null，异常处理器会执行，用异常对应的逻辑视图和异常信息生成一个异常的ModelAndView

方法内部然后开始调用了render() → resolveViewName() 最终拿到一个被视图解析器渲染过后的View对象

方法最后倒叙执行所有对应拦截器的AfterCompletion放个

```Java
private void processDispatchResult(HttpServletRequest request, HttpServletResponse response, @Nullable HandlerExecutionChain mappedHandler, @Nullable ModelAndView mv, @Nullable Exception exception) throws Exception {
    boolean errorView = false;
    if (exception != null) {
        if (exception instanceof ModelAndViewDefiningException) {
            this.logger.debug("ModelAndViewDefiningException encountered", exception);
            mv = ((ModelAndViewDefiningException)exception).getModelAndView();
        } else {
            Object handler = mappedHandler != null ? mappedHandler.getHandler() : null;
            mv = this.processHandlerException(request, response, handler, exception);
            errorView = mv != null;
        }
    }

    if (mv != null && !mv.wasCleared()) {
        this.render(mv, request, response);
        if (errorView) {
            WebUtils.clearErrorRequestAttributes(request);
        }
    } else if (this.logger.isTraceEnabled()) {
        this.logger.trace("No view rendering, null ModelAndView returned.");
    }

    if (!WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
        if (mappedHandler != null) {
            mappedHandler.triggerAfterCompletion(request, response, (Exception)null);
        }

    }
}
```

**标记6中的render方法：**方法内调用resolveViewName方法，该方法会返回一个被视图解析器解析渲染后的View对象

```Java
protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
    Locale locale = this.localeResolver != null ? this.localeResolver.resolveLocale(request) : request.getLocale();
    response.setLocale(locale);
    String viewName = mv.getViewName();
    View view;
    if (viewName != null) {
        view = this.resolveViewName(viewName, mv.getModelInternal(), locale, request);
        if (view == null) {
            throw new ServletException("Could not resolve view with name '" + mv.getViewName() + "' in servlet with name '" + this.getServletName() + "'");
        }
    } else {
        view = mv.getView();
        if (view == null) {
            throw new ServletException("ModelAndView [" + mv + "] neither contains a view name nor a View object in servlet with name '" + this.getServletName() + "'");
        }
    }

    if (this.logger.isTraceEnabled()) {
        this.logger.trace("Rendering view [" + view + "] ");
    }

    try {
        if (mv.getStatus() != null) {
            response.setStatus(mv.getStatus().value());
        }

        view.render(mv.getModelInternal(), request, response);
    } catch (Exception var8) {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Error rendering view [" + view + "]", var8);
        }

        throw var8;
    }
}
```

**标记6中的render方法中调用的resolveViewName方法：**循环查找合适的视图解析器，我们这里只配置了ThymeleafViewResolver，然后找到合适的视图解析器，会根据名字返回一个视图对象View，这里是ThymeleafView，然后返回View

```Java
protected View resolveViewName(String viewName, @Nullable Map<String, Object> model, Locale locale, HttpServletRequest request) throws Exception {
    if (this.viewResolvers != null) {
        Iterator var5 = this.viewResolvers.iterator();

        while(var5.hasNext()) {
            ViewResolver viewResolver = (ViewResolver)var5.next();
            View view = viewResolver.resolveViewName(viewName, locale);
            if (view != null) {
                return view;
            }
        }
    }

    return null;
}
```





# 12 SSM整合★

## 12.1整合步骤

- 第一步：创建Web Model，打包类型设置为war，导入依赖的jar包，不再需要单元测试相关依赖
  - spring的jar包
  - mybaties的jar包
  - mybaties整合spring的jar包
  - spring-webmvc的jar包
  - thymeleaf与spring5的jar包
  - servlet-api的jar包 (注意scope要设置为provided)
  - mysql驱动的jar包
  - 德鲁伊数据源的jar包
  - Spring整合JUnit4的jar包
  - mybatis的jar包
- 第二步：在项目结构中添加web.xml文件
  - /Users/shuaigouzi/javaProject/javaEEProject/ssm/web03/src/main/webapp/WEB-INF/web.xml
  - **/src/main/webapp**是添加的路径，用于控制webapp目录的生成位置

- 第三步：创建MVC基本架构

  - 创建bean类
  - 创建持久层和mapper的接口
  - 创建业务层srevice的类和接口
  - 创建控制层controller的类
  - 创建web文件，在WEB-INF目录中创建pages/index.html作为首页，目录名必须和配置的视图解析器前缀一致
  - 创建web测试文件，在WEB-INF目录中创建pages/employee.html作为测试页面，在controller中配置路径及数据

- 第四步：创建并配置相关文件：

  - 创建并配置数据库配置文件db.properties
  - 创建并配置持久层接口的映射文件Mapper.xml，存放于resources/mapper目录下

- 第五步：创建并配置核心文件：

  - 创建并配置Mybatis配置文件mybatis-config.xml
    - 开启驼峰、设置别名、配置插件、配置数据库厂商标识即可，其他配置项交给spring管理
  - 创建并配置spring配置文件spring-config.xml
    - 设置注解扫描器，排除@Controller注解，防止与springMVC配置文件冲突
    - 加载db.properties文件、创建数据源的bean
    - 创建sqlSessionFactoryBean的bean并注入属性、使用mybatis-spring扫描器扫描所有的mapper接口 (整合mybatis)
    - 配置事务管理器、开启事务注解支持 (事务)
  - 创建并配置springMVC配置文件spring-mvc-config.xml
    - 设置注解扫描器，只扫描@Controller注解，防止与spring配置文件冲突
    - 配置视图解析器(字符集、模版引擎、逻辑视图的前缀后缀等相关属性)
    - 配置三个mvc标签：mvc:view-controller(设置首页)、mvc:annotation-driven、mvc:default-servlet-handler
  - 配置web.xml文件
    - 配置字符过滤器(必须最前面)、配置前端控制器、配置HiddenHttpMethodFilte-REST风格的过滤器
    - 整合后，需要让tomCat加载spring配置文件，而不是在单元测试中加载
      - 添加全局初始化参数，key为contextConfigLocation，value为spring配置文件的路径
    - 整合后，需要创建一个监听器，监听到服务器启动就开始加载spring文件，创建ioc容器进行对象管理

- 第六步：创建tomCat，将war包装配到tomCat中进行测试。

  

## 12.2具体实现★

- 第一步：创建Web Model，打包类型设置为war，导入依赖的jar包，不再需要单元测试相关依赖

  - spring的jar包

  - mybaties的jar包

  - mybaties整合spring的jar包

  - spring-webmvc的jar包

  - thymeleaf与spring5的jar包

  - servlet-api的jar包 (注意scope要设置为provided)

  - mysql驱动的jar包

  - 德鲁伊数据源的jar包

  - Spring整合JUnit4的jar包

  - mybatis的jar包

    设置打包方式为war包：

    ```xml
    <packaging>war</packaging>
    ```

    引入依赖：

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
      
      <!--spring-webmvc-->
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.1</version>
      </dependency>
    
      <!-- 导入thymeleaf与spring5的整合包 -->
      <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf-spring5</artifactId>
        <version>3.0.12.RELEASE</version>
      </dependency>
    
      <!--servlet-api注意这里的scope设置为provided-->
      <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
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
      
    </dependencies>
    ```

- 第二步：在项目结构中添加web.xml文件

  - /Users/shuaigouzi/javaProject/javaEEProject/ssm/web03/src/main/webapp/WEB-INF/web.xml
  - **/src/main/webapp**是添加的路径，用于控制webapp目录的生成位置

- 第三步：创建MVC基本架构

  - 创建bean类
  - 创建持久层和mapper的接口
  - 创建业务层srevice的类和接口
  - 创建控制层controller的类
  - 创建web文件，在WEB-INF目录中创建pages/index.html作为首页，目录名必须和配置的视图解析器前缀一致
  - 创建web测试文件，在WEB-INF目录中创建pages/employee.html作为测试页面，在controller中配置路径及数据

- 第四步：创建并配置相关文件：

  - 创建并配置数据库配置文件db.properties

    ```properties
    jdbc.driver=com.mysql.cj.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/ssm
    jdbc.username=root
    jdbc.password=12345678
    ```

  - 创建并配置持久层接口的映射文件EmployeeMapper.xml，存放于resources/mapper目录下

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

- 第五步：创建并配置核心文件：

  - 创建并配置Mybatis配置文件mybatis-config.xml

    - 开启驼峰、设置别名、配置插件、配置数据库厂商标识即可，其他配置项交给spring管理

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

  - 创建并配置spring配置文件spring-config.xml

    - 设置注解扫描器，排除@Controller注解，防止与springMVC配置文件冲突
    - 加载db.properties文件、创建数据源的bean
    - 创建sqlSessionFactoryBean的bean并注入属性、使用mybatis-spring扫描器扫描所有的mapper接口 (整合mybatis)
    - 配置事务管理器、开启事务注解支持 (事务)

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xmlns:mybatis-spring="http://mybatis.org/schema/mybatis-spring"
           xmlns:tx="http://www.springframework.org/schema/tx"
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://mybatis.org/schema/mybatis-spring http://mybatis.org/schema/mybatis-spring.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
    
        <!--设置注解扫描包-->
        <context:component-scan base-package="com.atguigu">
            <!--排除@Controller注解，防止与springMVC冲突-->
            <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        </context:component-scan>
    
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

  - 创建并配置springMVC配置文件spring-mvc-config.xml

    - 设置注解扫描器，只扫描@Controller注解，防止与spring配置文件冲突
    - 配置视图解析器(字符集、模版引擎、逻辑视图的前缀后缀等相关属性)
    - 配置三个mvc标签：mvc:view-controller(设置首页)、mvc:annotation-driven、mvc:default-servlet-handler

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xmlns:mvc="http://www.springframework.org/schema/mvc"
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
    
        <!--配置自动扫描的包-->
        <context:component-scan base-package="com.atguigu">
            <!--只扫描@Controller注解，防止与spring冲突-->
            <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        </context:component-scan>
    
        <!--配置视图解析器-->
        <bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver" id="viewResolver">
            <!--配置字符集-->
            <property name="characterEncoding" value="UTF-8"></property>
            <!--配置模板引擎-->
            <property name="templateEngine">
                <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
                    <!--配置模板解析器-->
                    <property name="templateResolver">
                        <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                            <!--配置前缀-->
                            <property name="prefix" value="/WEB-INF/pages/"></property>
                            <!--配置后缀-->
                            <property name="suffix" value=".html"></property>
                            <!--配置字符集-->
                            <property name="characterEncoding" value="UTF-8"></property>
                        </bean>
                    </property>
                </bean>
            </property>
        </bean>
        <!--去首页-->
        <mvc:view-controller path="/" view-name="index"></mvc:view-controller>
    
        <!--防止controller失效，必须配置-->
        <mvc:annotation-driven/>
    
        <!--加载网页的静态资源，必须配置-->
        <mvc:default-servlet-handler/>
    </beans>
    ```

  - 配置web.xml文件

    - 配置字符过滤器(必须最前面)、配置前端控制器、配置HiddenHttpMethodFilte-REST风格的过滤器
    - 整合后，需要让tomCat加载spring配置文件，这里将spring配置文件的路径配置为全局参数
      - 添加全局初始化参数，key为contextConfigLocation，value为spring配置文件的路径
    - 整合后，需要创建一个监听器，监听到服务器启动就开始读取全局初始化参数，拿到spring配置文件的路径，创建ioc容器进行对象管理

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
             version="4.0">
        <!--配置CharacterEncodingFilter过滤器，解决POST请求中文乱码问题
           注意：该过滤器一定要配置到最上面
       -->
        <filter>
            <filter-name>encodingFilter</filter-name>
            <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
            <!--设置属性encoding的值-->
            <init-param>
                <param-name>encoding</param-name>
                <param-value>utf-8</param-value>
            </init-param>
            <!--设置属性forceRequestEncoding的值-->
            <init-param>
                <param-name>forceRequestEncoding</param-name>
                <param-value>true</param-value>
            </init-param>
        </filter>
        <filter-mapping>
            <filter-name>encodingFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>
        <!--配置前端控制器-->
        <servlet>
            <servlet-name>dispatcherServlet</servlet-name>
            <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
            <!-- 配置DispatcherServlet的初始化參數：设置SpringMVC文件的路径和文件名称 -->
            <init-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>classpath:spring-mvc-config.xml</param-value>
            </init-param>
            <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
            <servlet-name>dispatcherServlet</servlet-name>
            <url-pattern>/</url-pattern>
        </servlet-mapping>
    
        <!--配置HiddenHttpMethodFilte过滤器，目的是为了将POST请求转换为PUT或DELETE请求-->
        <filter>
            <filter-name>hiddenHttpMethodFilter</filter-name>
            <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
        </filter>
        <filter-mapping>
            <filter-name>hiddenHttpMethodFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>
    
        <!--整合后，设置一个全局参数：将spring配置文件的路径配置为全局参数-->
        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-config.xml</param-value>
        </context-param>
    
        <!--整合后，创建一个监听器：监听到服务器启动开始读取全局初始化参数，拿到spring配置文件的路径，创建ioc容器进行对象管理-->
        <listener>
            <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
        </listener>
    
    </web-app>
    ```

- 第六步：创建tomCat，将war包装配到tomCat中进行测试。



## 12.3整合问题

**主要描述Spring与SpringMVC的整合问题**

Spring和SpringMVC扫描器冲突问题：

- 在Spring配置文件中，设置注解扫描器时排除@Controller注解
- 在SpringMVC配置文件中，设置注解扫描器时只包含@Controller注解
- 则Controller对象全部由SpringMVC的IoC容器进行管理



整合后Spring配置文件的加载模式：

- 整合后不在单元测试模块中使用，所以不再需要junit依赖
- 整合后Spring配置文件的加载及IoC容器的初始化由tomCat完成



TomCat加载Spring配置文件的配置：

- 设置一个全局参数：将spring配置文件的路径配置为全局参数
- 创建一个监听器：监听到服务器启动开始读取全局初始化参数，拿到spring配置文件的路径，创建ioc容器进行对象管理



Spring的IoC容器与SpringMVC的IoC容器的关系：

- SpringMVC 的 IoC 容器中的 bean 可以引用 Spring IoC 容器中的 bean (子引用父)
- Spring 的 IoC 容器中的 bean 不能引用 SpringMVC IoC 容器中的 bean (父不能引用子)
- SpringMVC的IOC容器为Spring的IOC容器的子容器



## 12.4ContextLoaderListener★

public class ContextLoaderListener extends ContextLoader implements **ServletContextListener**，该类实现了**ServletContextListener**监听器接口

**ContextLoaderListener的加载顺序：**

- 该类是一个ServletContextListener监听器，在项目启动的时候加载
- Servlet、Filter、Listener的加载顺序：Listener、Filter、Servlet，所以该类加载早于DispatcherServlet

**由ContextLoaderListener加载创建的IoC容器的关系：**

使用ContextLoaderListener加载非SpringMVC配置文件创建的IoC容器是父容器，DispatcherServlet加载SpringMVC的的配置文件创建的IoC容器是子容器。子容器优先使用自己的Bean，如果没有，可以使用父容器的Bean。

**使用ContextLoaderListener的注意事项：**

同时使用了ContextLoaderListener和DispatcherServlet，如果<context:component-scan >的路径设置不合理，就会重复的创建Bean，甚至导致无法应用业务层事务的问题。

正确的加载方式1:

```xml
<!--DispatcherServlet加载的配置文件：springmvc配置文件-->
<context:component-scan base-package="com.atguigu.controller"></context:component-scan>

<!--ContextLoaderListener加载的配置文件：spring配置文件-->
<context:component-scan base-package="com.atguigu.service,com.atguigu.dao"></context:component-scan>
```

正确的加载方式2:

```xml
<!--DispatcherServlet加载的配置文件：springmvc配置文件-->
<context:component-scan base-package="com.atguigu">
  <!--只扫描@Controller注解，防止与spring冲突-->
  <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>

<!--ContextLoaderListener加载的配置文件：spring配置文件-->
<context:component-scan base-package="com.atguigu">
  <!--排除@Controller注解，防止与springMVC冲突-->
  <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```

关键内容：⚠️

首先需要知道的是，ContextLoaderListener属于Spring[框架](https://so.csdn.net/so/search?q=框架&spm=1001.2101.3001.7020)，其实现了ContextLoaderListener接口，本质是一个listener，当服务启动，Context初始化和销毁时被调用；而DispatcherServlet，其继承了HttpServlet，本质是一个Servlet，Servlet初始化以及相关url访问时会被调用。

ContextLoaderListener会读取web.[xml](https://so.csdn.net/so/search?q=xml&spm=1001.2101.3001.7020)中定义的context-param标签，例如：

```xml
<!-- Context ConfigLocation -->
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath*:/spring-context*.xml</param-value>
</context-param>
```

当然，你可以不定义，ContextLoaderListener会有一个默认的地址去寻找文件。ContextLoaderListener其主要功能是初始化Ioc容器，这样，我们在项目中管理bean容器就可以通过ApplicationContext实例化，而不必去new；还有，当你用到了Shiro 、Dubbo等其他需要Sping配置的框架，其需要在ContextLoaderListener中加载，因此需要定义在该文件下。**当然也可以定义在后面提到的sping-mvc.xml文件中，但考虑到listener和servlet加载顺序，更推荐定义在这里**。（包括像一些定时调度任务等也可以定义在这里）

DispatcherServlet中常常见到的配置是：

```xml
<!-- MVC Servlet -->
<servlet>
    <servlet-name>springServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath*:/spring-mvc*.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>springServlet</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

同样定义了contextConfigLocation参数，其中的内容大致为各个标签扫描的路径、视图文件解析等，总的来说是项目启动后，有人点进你的网站中进行具体操作时执行的一些步骤。

还有一点，这两者也牵扯到了Spring的父子容器的概念，其访问规则为：

```
子容器可以访问父容器的对象，而父容器不可以访问子容器的对象。
子容器优先使用自己的Bean，如果没有，可以使用父容器的Bean。
使用ContextLoaderListener加载非SpringMVC配置文件创建的IoC容器是父容器，DispatcherServlet加载SpringMVC的的配置文件创建的IoC容器是子容器。
```

针对扫描的包的话，ContextLoaderListener扫描的包包括Dao、Service（aop增强），而DispatcherServlet其扫描的包主要为Controller。根据访问规则，我们就可以在Controller中实例化Service和Dao。不仅如此，也可以联想到Spring的前端表现框架不仅有[SpringMVC](https://so.csdn.net/so/search?q=SpringMVC&spm=1001.2101.3001.7020)，也可以是Struts2或其他框架，这样的父子容器设计，也可以让Spring有非常好的可扩展性。











