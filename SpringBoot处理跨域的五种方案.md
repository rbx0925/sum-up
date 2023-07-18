# SpringBoot处理跨域的五种方案

## 1、[跨域](https://so.csdn.net/so/search?q=跨域&spm=1001.2101.3001.7020)问题

​		出于浏览器的**同源策略**限制。同源策略是一种约定，它是浏览器最核心也最基本的安全功能，如果缺少了同源策略，则浏览器的正常功能可能都会受到影响。可以说Web是构建在同源策略基础之上的，浏览器只是针对同源策略的一种实现。

​		同源策略会阻止一个域的javascript脚本和另外一个域的内容进行交互。**所谓同源（即指在同一个域）就是两个页面具有相同的协议，主机和端口号。**



## 2、跨域形式

当一个请求url的协议、域名、端口三者之间任意一个与当前页面url不同即为跨域。

| 当前页面              | 被请求页面                       | 是否跨域 | 原因                       |
| --------------------- | -------------------------------- | -------- | -------------------------- |
| https://www.baidu.com | https://www.baidu.com/index.html | 否       | 满足同协议、同域名、同端口 |
| http://www.test.com   | https://www.test.com             | 是       | 不同的协议                 |
| https://www.baicu.com | https://www.baicom001.com        | 是       | 不同的域名                 |
| https://www.baidu.com | https://www.baidu.com:9999       | 是       | 不同的端口                 |



## 3、非同源限制

1. 无法读取非同源网页的 Cookie
2. LocalStorage 和 IndexedDB 无法接触非同源网页的 DOM
3. 无法向非同源地址发送AJAX 请求



## 4、Spring Boot五种跨域方式

### 一、自定义CorsFilter对象

```java
@Configuration
public class CrossConfig {

    @Bean
    public CorsFilter getCrossConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*"); // 放行哪些头信息
        corsConfiguration.setAllowCredentials(true); // 是否允许发送Cookie
        corsConfiguration.addAllowedMethod("*"); // 允许哪些请求方式进行跨域请求
        corsConfiguration.addAllowedOrigin("*"); //允许哪些地址可以请求本域内的信息
        corsConfiguration.addExposedHeader("*"); // 暴露哪些响应头信息
        // 完成请求的映射
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        //返回一个自定义的CorsFilter对象
        return new CorsFilter(source);
    }
}
```



### 二、实现[WebMvcConfigurer](https://so.csdn.net/so/search?q=WebMvcConfigurer&spm=1001.2101.3001.7020)

WebMvcConfigurer是一个接口，定义了很多管理Web应用的方法，使用WebMvcConfigurer来完成跨域也是推荐做法。

```java
@Configuration
public class CrossConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns("*").allowedMethods("GET", "POST", "PUT")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true)
                .maxAge(3600);
    }
	//需要添加的方法可自行添加。
}
```

还需要在主启动类上加上@EnableWebMvc注解，表示开启WebMvcConfigurer的支持。

```java
@SpringBootApplication
@EnableWebMvc
public class TaobaoApplication {

	 public static void main(String[] args) {
        SpringApplication.run(TaobaoApplication.class, args);
    }

}
```



### 三、使用注解达到局部跨域

在Controller层中为类标注上@CrossOrigin(origins=“允许访问本程序的地址”）注解以达到局部的跨域

```java
@RestController
@CrossOrigin(origins="*") //允许所有请求都能访问本程序的此地址
public class UserController{
	
	@Autowired
	private UserService userService;	

	@RequestMapping(value="/userList" method=RequestMethod.GET)
	public List<User> getUserList(){
		List<User> userList = userService.getUserList();
		return userList;
	}
}
```



### 四、设置响应头达到局部跨域

为响应头添加**Access-Control-Allow-Origin**字段达到局部跨域

```java
@RestController
public class UserController{
	
	@Autowired
	private UserService userService;	

	@RequestMapping(value="/userList" method=RequestMethod.GET)
	public List<User> getUserList(HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin","*"); // 允许所有请求进行访问
		List<User> userList = userService.getUserList();
		return userList;
	}
}
```



### 五、使用过滤器进行跨域

**可以使用JavaWeb所学的Filter接口达到全局跨域的效果，在SSM项目中也是一种推荐的做法。**

```java
@Component
public class MyCorsFilter implements Filter {

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
    chain.doFilter(req, res);
  }
  
}
```

在Web.xml配置文件中进行注册

```xml
<filter>
	<filter-name>CorsFilter</filter-name>
	<filter-class>com.mesnac.aop.MyCorsFilter</filter-class>
</filter>
<filter-mapping>
	<filter-name>CorsFilter</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
```