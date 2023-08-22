# springboot获取 Request 对象的三种方法

常见的获取 Request 对象的方法有以下三种：

1. 通过请求参数中获取 Request 对象；
2. 通过 RequestContextHolder 获取 Request 对象；
3. 通过自动注入获取 Request 对象。



## 1.通过请求参数获取

```java
@RequestMapping("/index")
@ResponseBody
public void index(HttpServletRequest request){
　　// do something
}		
```

该方法实现的原理是 Controller 开始处理请求时，Spring 会将 Request 对象赋值到方法参数中，我们直接设置到参数中即可得到 Request 对象。



## 2.通过 RequestContextHolder 获取

在 Spring Boot 中，RequestContextHolder 是 Spring 框架提供的一个工具类，用于在多线程环境中存储和访问与当前线程相关的请求上下文信息。它主要用于将当前请求的信息存储在线程范围内，以便在不同的组件中共享和访问这些信息，特别是在没有直接传递参数的情况下。 RequestContextHolder 的主要作用有以下几个：

1. **访问请求上下文信息**： 在 Web 应用中，每个请求都会触发一个新的线程来处理。RequestContextHolder 允许你在任何地方获取当前请求的上下文信息，比如 HttpServletRequest 对象、会话信息等。
2. **跨层传递信息**： 在多层架构中，比如控制器、服务层、数据访问层，你可能需要在这些层之间传递一些与请求相关的信息，但不想在每个方法中显式传递。通过 RequestContextHolder，你可以在一处设置请求信息，在其他地方获取并使用。
3. **线程安全的上下文共享**： RequestContextHolder 使用线程局部变量来存储请求上下文信息，确保在多线程环境下每个线程访问的上下文信息都是独立的，避免了线程安全问题。

因此我们可以使用 RequestContextHolde 获取 Request 对象，实现代码如下：

```java
@RequestMapping("/index")
@ResponseBody
public void index(){
	ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
	HttpServletRequest request = servletRequestAttributes.getRequest();
	// do something
}
```



## 3.通过自动注入获取

HttpServletRequest 对象也可以通过自动注入，如属性注入的方式获取，如下代码所示：

```
@Controller
public class HomeController{
    @Autowired
    private HttpServletRequest request; // 自动注入 request 对象
    // do something
}
```

