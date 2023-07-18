# 1 Web

Web即Web前端开发，是创建Web页面或app等前端界面呈现给用户的过程，通过HTML，CSS及JavaScript以及衍生出来的各种技术、框架、解决方案，来实现互联网产品的用户界面交互。

## 1.1HTML

HTML是Hyper Text Markup Language的缩写。意思是超文本标记语言。它的作用是搭建网页结构，在网页上展示内容

### 1.1.1form表单

在HTML中我们使用form标签来定义一个表单，表单有action和method属性。

- action：请求地址
- method：请求类型
  - POST
  - GET（最多携带4K请求数据，无请求体）

```html
<form action="/bookStory/book/add/" method="post">
    
</form>
```



### 1.1.2input表单项

表单中的每一项，包括: 文本框、密码框、单选框、多选框等等，都称之为表单项，一个表单中可以包含多个表单项

单行输入框

```html
个性签名：<input type="text" name="signal"/><br/>
```

密码输入框

```html
密码：<input type="password" name="secret"/><br/>
```

隐藏域

- 隐藏提交参数

```html
<input type="hidden" name="userId" value="2233"/>
```

单选框

- checked="checked"默认选中

```html
<!--你最喜欢的季节是：-->
<input type="radio" name="season" value="spring" />春天
<input type="radio" name="season" value="summer" checked="checked" />夏天
<input type="radio" name="season" value="autumn" />秋天
<input type="radio" name="season" value="winter" />冬天
```

多选框

- checked="checked"默认选中

```html
<!--你最喜欢的球队是：-->
<input type="checkbox" name="team" value="Brazil"/>巴西
<input type="checkbox" name="team" value="German" checked="checked"/>德国
<input type="checkbox" name="team" value="France"/>法国
<input type="checkbox" name="team" value="China" checked="checked"/>中国
<input type="checkbox" name="team" value="Italian"/>意大利
```

下拉框

- checked="checked"默认选中

```html
<!--你最喜欢的运动是：-->
<select name="interesting">
    <option value="swimming">游泳</option>
    <option value="running">跑步</option>
    <option value="shooting" selected="selected">射击</option>
    <option value="skating">溜冰</option>
</select>
```

按钮

- type="button"普通按钮
- type="reset"重置按钮
- type="submit"提交按钮

```html
<button type="button">普通按钮</button>或<input type="button" value="普通按钮"/>
<button type="reset">重置按钮</button>或<input type="reset" value="重置按钮"/>
<button type="submit">提交按钮</button>或<input type="submit" value="提交按钮"/>
```



### 1.1.3表单传参注意事项

输入框

- 不输入任何内容时，提交name，提交空值的value

单选框：

- 无选项时，不提交name和value

多选框：

- 无选项时，不提交name和value
- 选择多个时：String[] v = request.getParameterValues("team");

下拉框：

- 不选择时，提交name，提交空值的value，主要看自己如何写的空选项option标签

- select标签提供name，被选中的option提供value



## 1.2CSS

css代表层叠样式表，英文全称是Cascading Style Sheets，是一种用来表现HTML或XML等文件样式的计算机语言；css不仅可以静态地修饰网页，还可以配合各种脚本语言动态地对网页各元素进行格式化。

### 1.2.1CSS选择器

基础选择器：

- 标签选择器：标签名 {属性1:属性值1; 属性2:属性值2; };
- 类选择器：.类名 {属性1:属性值1; 属性2:属性值2; };
- id选择器：#id名 {属性1:属性值1; 属性2:属性值2; };
- 通配符选择器： * { 属性1:属性值1; 属性2:属性值2; };

复合选择器：

- 后代选择器：父级 子级{属性:属性值;属性:属性值;}
- 自元素选择器：.class>h3{color:red;font-size:14px;}
- 交集选择器：p.one {color: #F00;}  
- 并集选择器：.one, p , #test {color: #F00;}  



### 1.2.2CSS样式表

- 行内样式表：<标签名 style="属性1:属性值1; "> 内容 </标签名>
- 内部样式表：< style type="text/CSS"> 选择器{ 属性1: 属性值1; }</ style>
- 外部样式表：< link rel="stylesheet" type="text/css" href="css文件路径">





## 1.3JavaScript

### 1.3.1JavaScript引入方式

两种使用js方式

- 内部脚本方式：< script type="text/javascript">< /script>
- 外部脚本方式：< script src="../js/outer.js">< /script >



### 1.3.2JavaScript的特殊值

三个特殊值：

- undefined的值属于undefined的类型
  - 变量声明了但是没有赋值，就是未定义
- NaN的值属于number的类型
  - 不是一个数字，大概率是因为进行非数值型数据的算数运算导致的
- null的值属于object(引用数据类型)
  - 空值



### 1.3.3Json对象序列化

json对象转为json字符串 ：

```javascript
var s = JSON.stringify(json04);
```

json字符串(满足json格式的字符串对象)转为json对象： ⚠️

```javascript
var jsonObj = JSON.parse(s);
```



### 1.3.4event对象

event 当前事件对象

event.target 目标对象(当前事件作用在哪个目标上：动态的)

```javascript
var target = event.target;

console.log(target)
```



### 1.3.5正则表达式

 正则表达式用来校验字符串是否满足一定的规则的公式

#### 1.3.5.1正则表达式的用途

三大用途：

- 模式验证: 检测某个字符串是否符合规则，例如检测手机号、身份证号等等是否符合规范
- 匹配读取:读取字符串中符合规则的内容
- 匹配替换:替换字符串中符合规则的内容



#### 1.3.5.2正则表达式的语法

对象形式：`var reg = new RegExp("正则表达式")`当正则表达式中有"/"那么就使用这种

```javascript
// 类似创建数组可以new Array()、创建对象可以使用new Object()
var reg = new RegExp("a");
```

直接量形式：`var reg = /正则表达式/`一般使用这种声明方式 

```javascript
// 类似创建数组时可以使用[]、创建对象可以使用{}
var reg = /a/;
```



#### 1.3.5.3正则表达式的组成

正则表达式本身也是一个字符串，它由两种字符组成：

- 普通字符，例如大、小写英文字母；数字等。
- 元字符：被系统赋予特殊含义的字符。例如：^表示以某个字符串开始，$表示以某个字符串结束



#### 1.3.5.4字符集合

| 语法格式    | 示例                                                         | 说明                                               |
| ----------- | ------------------------------------------------------------ | -------------------------------------------------- |
| [字符列表]  | 正则表达式：[abc] 含义：目标字符串包含abc中的任何一个字符 目标字符串：plain 是否匹配：是 原因：plain中的“a”在列表“abc”中 | 目标字符串中任何一个字符出现在字符列表中就算匹配。 |
| [^字符列表] | [^abc] 含义：目标字符串包含abc以外的任何一个字符 目标字符串：plain 是否匹配：是 原因：plain中包含“p”、“l”、“i”、“n” | 匹配字符列表中未包含的任意字符。                   |
| [字符范围]  | 正则表达式：[a-z] 含义：所有小写英文字符组成的字符列表 正则表达式：[A-Z] 含义：所有大写英文字符组成的字符列表 | 匹配指定范围内的任意字符。                         |



#### 1.3.5.5元字符

 在正则表达式中被赋予特殊含义的字符，不能被直接当做普通字符使用。如果要匹配元字符本身，需要对元字符进行转义，转义的方式是在元字符前面加上“\”，例如：\^ 

|      | 说明                                                         |
| ---- | ------------------------------------------------------------ |
| .    | 匹配除换行字符以外的任意字符。                               |
| \w   | 匹配字母或数字或下划线等价于[a-zA-Z0-9_]                     |
| \W   | 匹配任何非单词字符。等价于[^A-Za-z0-9_]                      |
| \s   | 匹配任意的空白符，包括空格、制表符、换页符等等。等价于[\f\n\r\t\v]。 |
| \S   | 匹配任何非空白字符。等价于[^\f\n\r\t\v]。                    |
| \d   | 匹配数字。等价于[0-9]。                                      |
| \D   | 匹配一个非数字字符。等价于[^0-9]                             |
| \b   | 匹配单词的开始或结束                                         |
| ^    | 匹配字符串的开始，但在[]中使用表示取反                       |
| $    | 匹配字符串的结束                                             |



#### 1.3.5.6出现次数

| 代码  | 说明           |
| ----- | -------------- |
| *     | 出现零次或多次 |
| +     | 出现一次或多次 |
| ?     | 出现零次或一次 |
| {n}   | 出现n次        |
| {n,}  | 出现n次或多次  |
| {n,m} | 出现n到m次     |



#### 1.3.5.7正则表达式例子

① 模式验证

**注意**：这里是使用**正则表达式对象**来**调用**方法。

```javascript
// 创建一个最简单的正则表达式对象
var reg = /o/;
// 创建一个字符串对象作为目标字符串
var str = 'Hello World!';
// 调用正则表达式对象的test()方法验证目标字符串是否满足我们指定的这个模式，返回结果true
console.log("/o/.test('Hello World!')="+reg.test(str));
```

② 匹配读取

**注意**：这里是使用**字符串对象**来**调用**方法。

```Javascript
// 在目标字符串中查找匹配的字符，返回匹配结果组成的数组
var resultArr = str.match(reg);
// 数组长度为1
console.log("resultArr.length="+resultArr.length);

// 数组内容是o
console.log("resultArr[0]="+resultArr[0]);
```

③ 替换

**注意**：这里是使用**字符串对象**来**调用**方法。

```Javascript
var newStr = str.replace(reg,'@');
// 只有第一个o被替换了，说明我们这个正则表达式只能匹配第一个满足的字符串
console.log("str.replace(reg)="+newStr);//Hell@ World!

// 原字符串并没有变化，只是返回了一个新字符串
console.log("str="+str);//str=Hello World!
```

④ 全文查找

如果不使用g对正则表达式对象进行修饰，则使用正则表达式进行查找时，仅返回第一个匹配；使用g后，返回所有匹配。

```Javascript
// 目标字符串
var targetStr = 'Hello World!';

// 没有使用全局匹配的正则表达式
var reg = /[A-Z]/;
// 获取全部匹配
var resultArr = targetStr.match(reg);
// 数组长度为1
console.log("resultArr.length="+resultArr.length);

// 遍历数组，发现只能得到'H'
for(var i = 0; i < resultArr.length; i++){
	console.log("resultArr["+i+"]="+resultArr[i]);
}
```

使用g全局匹配，对比上面的

```javascript
// 目标字符串
var targetStr = 'Hello World!';

// 使用了全局匹配的正则表达式
var reg = /[A-Z]/g;
// 获取全部匹配
var resultArr = targetStr.match(reg);
// 数组长度为2
console.log("resultArr.length="+resultArr.length);

// 遍历数组，发现可以获取到“H”和“W”
for(var i = 0; i < resultArr.length; i++){
	console.log("resultArr["+i+"]="+resultArr[i]);
}
```

⑤ 忽略大小写

```javascript
//目标字符串
var targetStr = 'Hello WORLD!';

//没有使用忽略大小写的正则表达式
var reg = /o/g;
//获取全部匹配
var resultArr = targetStr.match(reg);
//数组长度为1
console.log("resultArr.length="+resultArr.length);
//遍历数组，仅得到'o'
for(var i = 0; i < resultArr.length; i++){
	console.log("resultArr["+i+"]="+resultArr[i]);
}
```

对比

```javascript
//目标字符串
var targetStr = 'Hello WORLD!';
//使用了忽略大小写的正则表达式
var reg = /o/gi;
//获取全部匹配
var resultArr = targetStr.match(reg);
//数组长度为2
console.log("resultArr.length="+resultArr.length);
//遍历数组，得到'o'和'O'
for(var i = 0; i < resultArr.length; i++){
	console.log("resultArr["+i+"]="+resultArr[i]);
}
```

⑥ 元字符使用

```javascript
var str01 = 'I love Java';
var str02 = 'Java love me';
// 匹配以Java开头
var reg = /^Java/g;
console.log('reg.test(str01)='+reg.test(str01)); // flase
console.log("<br />");
console.log('reg.test(str02)='+reg.test(str02)); // true
```

```javascript
var str01 = 'I love Java';
var str02 = 'Java love me';
// 匹配以Java结尾
var reg = /Java$/g;
console.log('reg.test(str01)='+reg.test(str01)); // true
console.log("<br />");
console.log('reg.test(str02)='+reg.test(str02)); // flase
```

⑦ 字符集合的使用

```javascript
//n位数字的正则
var targetStr="123456789";
var reg=/^[0-9]{0,}$/;
//或者 ： var reg=/^\d*$/;
var b = reg.test(targetStr);//true
```

```javascript
//数字+字母+下划线，6-16位
var targetStr="HelloWorld";
var reg=/^[a-z0-9A-Z_]{6,16}$/;
var b = reg.test(targetStr);//true
```



#### 1.3.5.8常用正则表达式

| 需求     | 正则表达式                                            |
| -------- | ----------------------------------------------------- |
| 用户名   | /^\[a-zA-Z\_][a-zA-Z_\-0-9]{5,9}$/                    |
| 密码     | /^[a-zA-Z0-9_\-\@\#\&\*]{6,12}$/                      |
| 前后空格 | /^\s+\|\s+$/g                                         |
| 电子邮箱 | /^[a-zA-Z0-9_\.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/ |





## 1.4VUE

### 1.4.1VUE基础语法

插值表达式：

- {{数据模型的key值}}

属性的绑定：

- 单向绑定(v-bind:属性名="key值")  简写  属性名="key值"
- 双向绑定(v-model:属性名="key值")  简写  v-model="key值”  (仅限于value简写，别的也不需要你双向绑定)

条件渲染：

- v-if=“key值"
- v-else=“key值”
- v-show=“key值") 
- 注意：if和else之间不可写别的元素

列表渲染 ：

- < tr v-for="(emp,index) in emps">

  ```html
  <tr v-for="(emp,index) in emps">
      <td>{{index+1}}</td>
      <td>{{emp.id}}</td>
      <td>{{emp.name}}</td>
      <td>{{emp.salary}}</td>
  </tr>
  ```

属性侦听：

- 对message属性进行监控，当firstName或lastName属性的值发生变化时，调用我们准备好的函数。

```javascript
var app = new Vue({
	"el":"#app",
	"data":{
		"firstName":"jim",
		"lastName":"green",
		"fullName":"jim green"
	},
	"watch":{
		"firstName":function(inputValue){
			this.fullName = inputValue + " " + this.lastName;
		},
		"lastName":function(inputValue){
			this.fullName = this.firstName + " " + inputValue;
		}
	}
});
```

事件绑定:

- @事件类型="函数调用"

取消事件默认行为

- @click.prevent="clickAnchor"



### 1.4.2钩子函数

钩子函数又叫生命周期函数，共计十种

- beforeCreate：实例创建前触发
- created：实例创建完成，
- beforeMount：模板渲染前，可以访问数据，模板编译完成，虚拟DOM已经存在
- mounted：模板渲染完成，可以拿到DOM节点和数据
- beforeUpdate：更新前
- updated：更新完成
- activated：激活前
- deactivated：激活后
- beforeDestroy：销毁前
- destroyed：销毁后



## 1.5 HTTP协议

HTTP协议是超文本传输协议，它规定了浏览器和万维网服务器之间通信的规则，如`请求报文`（`客户端往服务器端发送的请求数据`）和`响应报文`（`服务器端往客户端发送数据`）的格式。

### 1.5.1HTTP请求协议

HTTP的请求协议包括：4部分

- 请求行
- 请求头
- 空白行
- 请求体

请求行：（包括三部分）

```http
POST /atguigu/admin.html HTTP/1.1
```

- 第一部分：请求方式（7种）
  - get（常用的）
  - post（常用的）
  - delete
  - put
  - head
  - options
  - trace
- 第二部分：URI
  - 什么是URI？ 统一资源标识符。代表网络中某个资源的名字。但是通过URI是无法定位资源的。
  - 什么是URL？统一资源定位符。代表网络中某个资源，同时，通过URL是可以定位到该资源的。
  - URI和URL什么关系，有什么区别？
    - URL包括URI
    - http://localhost:8080/servlet05/index.html 这是URL。
    - /servlet05/index.html 这是URI。
- 第三部分：HTTP协议版本号

请求头:

```http
Accept  text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,**;q=0.8,application/signed-exchange;v=b3;q=0.9
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6
Cache-Control: max-age=0
Connection: keep-alive
Content-Length: 0
Content-Type: application/x-www-form-urlencoded
Cookie: Idea-5083c5c3=66e5f757-3f90-4449-b1ab-0b5a62d33e8f
Host: localhost:8080
Origin: http://localhost:8080
Referer: http://localhost:8080/atguigu/demo.html
Sec-Fetch-Dest: document
Sec-Fetch-Mode: navigate
Sec-Fetch-Site: same-origin
Sec-Fetch-User: ?1
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.37
sec-ch-ua: "Chromium";v="106", "Microsoft Edge";v="106", "Not;A=Brand";v="99"
sec-ch-ua-mobile: ?0
```

- 请求的主机
- 主机的端口
- 浏览器信息
- 平台信息
- cookie等信息
- ....

空白行:

```http

```

- 空白行是用来区分“请求头”和“请求体”
- 空白行无任何数据

请求体：（浏览器向服务器发送的具体数据。）

```http
username=lisi&userpwd=123
```

- get请求是没有请求体，只有post请求有请求体
- 用户输入的数据
  - post请求将用户输入的数据放在请求体内
  - get请求将用户输入的数据拼接在网址栏（因为没有请求体，所以参数直接放在地址栏中）



### 1.5.2HTTP响应协议

HTTP的响应协议包括：4部分

- 状态行
- 响应头
- 空白行
- 响应体

状态行（包括三部分）

```http
HTTP/1.1 200 ok  
```

- 第一部分：协议版本号（HTTP/1.1）
- 第二部分：状态码（HTTP协议中规定的响应状态号。不同的响应结果对应不同的号码。）
  - 200 表示请求响应成功，正常结束。
  - 404表示访问的资源不存在，通常是因为要么是你路径写错了，要么是路径写对了，但是服务器中对应的资源并没有启动成功。总之404错误是前端错误。
  - 405表示前端发送的请求方式与后端请求的处理方式不一致时发生：
    - 比如：前端是POST请求，后端的处理方式按照get方式进行处理时，发生405
    - 比如：前端是GET请求，后端的处理方式按照post方式进行处理时，发生405
  - 500表示服务器端的程序出现了异常。一般会认为是服务器端的错误导致的。
  - 以4开始的，一般是浏览器端的错误导致的。
  - 以5开始的，一般是服务器端的错误导致的。
- 第三部分：状态的描述信息
  - ok 表示正常成功结束。
  - not found 表示资源找不到。

响应头：

```http
Content-Type: text/html;charset=UTF-8
Content-Length: 160
Date: Mon, 08 Nov 2021 13:19:32 GMT
Keep-Alive: timeout=20
Connection: keep-alive
```

- 响应的内容类型
- 响应的内容长度
- 响应的时间
- ....

空白行：

```http

```

- 用来分隔“响应头”和“响应体”的。
- 空白行无任何数据

响应体：

```http
"<!doctype html><html><head><title>首页</title></head><body><h1>欢迎来到首页</h1></body></html>"
```

- 响应体就是响应的正文，这些内容是一个长的字符串，这个字符串被浏览器渲染，解释并执行，最终展示出效果。

响应状态码；

- 200 成功
- 404 资源找不到(路径错误)
- 500 服务器错误(出现异常)



### 1.5.3GET请求注意事项

可发送的数据：

- get请求只能发送普通的字符串。
- get请求最多发送4K。
- post请求可以发送任何类型的数据，包括普通字符串，流媒体等信息：视频、声音、图片。
- post请求可以发送大数据量，理论上没有长度限制

缓存问题：

- 只要发送get请求，若URL完全相同会先从本地浏览器缓存中找，找不到的时候才会去服务器上获取。
- 缓存问题解决方案：每次请求带个时间戳参数，使用请求的URL不同

安全问题：

- get请求是安全的。因为get请求只是为了从服务器上获取数据。不会对服务器造成威胁。
- post请求是危险的。因为post请求是向服务器提交数据，如果这些数据通过后门的方式进入到服务器当中，服务器是很危险的。另外post是为了提交数据，所以一般情况下拦截请求的时候，大部分会选择拦截（监听）post请求。





# 2 WebService

## 2.1Tomcat

### 2.1.1Tomcat简介

Tomcat是Apache的Jakarta 项目中的一个核心项目，由阿帕奇和SUN公司及其他公司和个人共同研发。支持最新的Servlet 和JSP 规范，是目前比较流行的Web 应用服务器。

常见的JavaWeb服务器：

- **Tomcat（Apache）**：当前应用最广的JavaWeb服务器
- JBoss（Redhat红帽）：支持JavaEE，应用比较广EJB容器 –> SSH轻量级的框架代替
- GlassFish（Orcale）：Oracle开发JavaWeb服务器，应用不是很广
- Resin（Caucho）：支持JavaEE，应用越来越广
- Weblogic（Orcale）：要钱的！支持JavaEE，适合大型项目
- Websphere（IBM）：要钱的！支持JavaEE，适合大型项目

Tomcat目录：

- bin：存放的是二进制可执行文件，可启动和关闭Tomcat；
- conf：核心配置目录，非常重要：
  - **server.xml：配置整个服务器信息。例如修改端口号。默认HTTP请求的端口号是：8080**
- lib：Tomcat的类库，里面是一大堆jar文件。
- logs：存放logs日志文件，记录了Tomcat启动、关闭和异常的信息
- temp：存放Tomcat的临时文件，该目录的内容可以在停止Tomcat后删除！
- **webapps：存放web项目的目录，其中每个文件夹都是一个项目**；如果这个目录下已经存在了目录，那么都是tomcat自带的项目。其中ROOT是一个特殊的项目，在地址栏中访问：http://127.0.0.1:8080，没有给出项目目录时，对应的就是ROOT项目。
- work：编译后的文件，未打包之前的war包。可以把这个目录下的内容删除，再次运行时会生再次生成work目录。
- LICENSE：许可证。
- NOTICE：说明文件。

Tomcat官方网站：<http://tomcat.apache.org/>



### 2.1.2IDEA使用Tomcat

IDEA使用Tomcat的本质：

- idea会为每一个web项目创建一个tomcat的镜像

- tomcat的镜像依赖于本地的tomcat
- tomcat的镜像存储在本机的某个位置上

开发目录结构：

- web_project 模块名
  - resources 存放项目内需要你用到的配置文件
    - 自行创建，并设置为资源根目录：Mark Directory as → Resources Root
  - src java代码
  - web
    - 前端代码(html、js、css、图片、音频...)
    - WEB-INF
      - lib 存放第三方jar包(注意位置和文件名)	
        - 自行创建
      - web.xml web项目的核心配置文件

编译目录结构：(编译其实就是将本地项目web目录下的内容全部拿过来)

- WEB-INF
  - lib
  - web.xml
  - classes
    - java编译后的class文件
    - resources内的配置文件
- 静态资源
  - html/css/js/图片...

注意事项：

- java项目打包后就是jar包，web项目打包后就是war包。idea如果自动添加web组件的话，自动帮助我们创建一个war包 (省事)。如果自己添加的web组件，则idea不会帮助我们创建war包，只能手动创建。
- idea如果自动添加war包的话，自动帮助我们创建一个tomcat的服务器(镜像)。如果是子级添加的web组件，则idea不会帮助我们创建tomcat的镜像，只能手动创建。
- war包的创建：Project Structure → Priject Settings → Artifacts → Add → Web Application:Exploded  → From Models... 

解决Tomcat服务器在DOS命令窗口中的乱码问题（控制台乱码）

- 将tomcat安装目录/conf/logging.properties文件中的内容修改如下：

  ```properties
  java.util.logging.ConsoleHandler.encoding = GBK
  ```




## 2.3 XML

### 2.3.1XML概述

XML是eXtensible Markup Language的缩写，翻译过来就是可扩展标记语言。所以很明显，XML和HTML一样都是标记语言，也就是说它们的基本语法都是标签。

XML是可扩展语言，但他并不是随意编写的，每一个xml都有一个约束文件

- dtd （.dtd）
- Schema （.xsd）

如果没有约束文件，标签确实是随意写的



### 2.3.2XML解析

解析步骤：

- 导入dom4J的jar包

- 创建解析器
- 解析xml获得Document对象
- 获得根节点
- 获得内容

解析模拟文件：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<employees>
    <employee word="java">
        <id>11</id>
        <name>小黑</name>
        <age>18</age>
    </employee>
    <employee word="web">
        <id>12</id>
        <name>小白</name>
        <age>19</age>
    </employee>
</employees>
```

解析代码：

```java
public static void main(String[] args) {
    //1.创建解析器Stream API for XML
    SAXReader reader = new SAXReader();
    //2.解析xml，获得文档对象（⚠️复习相对路径）
    try {
        Document document = reader.read(new FileInputStream("javaWeb/src/employees.xml"));
        //3.获得根节点
        Element root = document.getRootElement();
        //4-1.得到第一个员工
        Element employee = root.element("employee");
        //4-2.得到第一个员工的姓名
        Element employeeName = employee.element("name");
        //4-3.得到第一个员工的姓名的值
        String name = employeeName.getText();
        System.out.println(name);
        //5.获取第一个员工employee的work属性
        Attribute attr = employee.attribute("work");
        String work = attr.getValue();
        System.out.println(work);
        //6.获取第所有员工
        List employees = root.elements("employee");
        for (Element element : employees) {
            String name1 = element.element("name").getText();
            System.out.println(name1);
        }
    } catch (DocumentException e) {
        throw new RuntimeException(e);
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
}
```



## 2.4BeanUtil

功能：将Map集合中的数据，自动装配到javaBean对象内

条件：Map集合的key值必须和javaBean的属性名保持一致

依赖：

- commons-beanutils-1.8.3.jar
- commons-logging-1.1.1.jar
- commons-beanutils-bean-collections-1.8.3.jar

```java
public class UserServlet extends ModelBaseServlet {
		/**
     * 第一步：使用getParameterMap()获取请求参数map
     * 第二步：创建bean对象，使用BeanUtils装配属性值
     * 确保请求参数名和bean类的属性名一致
     * 确保提供了公共的set方法
     * 直接装配，即使参数value是Sting数组类型
     */
    public void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1. 获得请求参数map
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user=new User();
        try {
          	//2.使用BeanUtils装配
            BeanUtils.populate(user,parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```



## 2.5MD5加密

加密方式介绍 ：

- 对称加密：加密和解密使用的相同的密钥，常见的对称加密算法有:DES、3DES
- 非对称加密：加密和解密使用的密钥不同，常见的非对称加密算法有:RSA
  - 加密：使用私钥加密
  - 解密：使用公钥解密
- 消息摘要: 消息摘要算法的主要特征是加密过程不需要密钥，并且经过加密的数据无法被解密，只有相同的原文经过消息摘要算法之后，才能得到相同的密文，所以消息摘要通常用来校验原文的真伪。常用的消息摘要算法有:MD5、SHA、MAC

```java
public class MD5Util {

    /**
     * 针对明文字符串执行MD5加密
     * @param source
     * @return
     */
    public static String encode(String source) {

        // 1.判断明文字符串是否有效
        if (source == null || "".equals(source)) {
            throw new RuntimeException("用于加密的明文不可为空");
        }

        // 2.声明算法名称
        String algorithm = "md5";

        // 3.获取MessageDigest对象
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // 4.获取明文字符串对应的字节数组
        byte[] input = source.getBytes();

        // 5.执行加密
        byte[] output = messageDigest.digest(input);

        // 6.创建BigInteger对象
        int signum = 1;
        BigInteger bigInteger = new BigInteger(signum, output);

        // 7.按照16进制将bigInteger的值转换为字符串
        int radix = 16;
        String encoded = bigInteger.toString(radix).toUpperCase();

        return encoded;
    }
}
```



## 2.6三层结构

三层架构：

- 控制层（Servlet）：控制用户界面层和业务逻辑层进行数据交互
- 业务逻辑层（Service）：针对具体问题的操作，对业务逻辑的处理
- 数据持久层（Dao）：控制事务，直接操作数据库，对数据进行CRUD操作（Data Access Object数据访问对象）

其他层：

- Bean层（bean）：或者叫pojo层、domain层，存放数据库对应的实体类
- 系统服务工具层（utils）：即提供公共的服务性功能，供各个操作层使用
- 用户界面层（web）：即网页，用户能实实在在看得到的，能进行操作的界面



## 2.7JavaWeb项目

### 2.7.1web站点的欢迎页面

可在web.xml文件中使用<welcome-file-list>配置欢迎页面

- 可配置多个欢迎页面，最上放的页面优先级越最高，找不到资源就依次向下找
- 路径不需要以“/”开始，并且路径默认从webapp的根下开始找

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
  	<!--配置欢迎页面-->
    <welcome-file-list>
        <welcome-file>welcome1.html</welcome-file>
        <welcome-file>welcome2.html</welcome-file>
        <welcome-file>welcome3.html</welcome-file>
    </welcome-file-list>

</web-app>
```



### 2.7.2Tomcat默认欢迎页面

Tomcat在tomcat/conf/web.xml文件中进行配置，属于全局配置

Tomcat默认全局欢迎页面：

- index.html 
- index.jsp



### 2.7.3动态欢迎页面

欢迎页可以是静态资源，也可以是Servlet的动态资源

- 静态资源：index.html welcome.html .....
- 动态资源：Servlet类。⚠️配置到欢迎页面时，路径要去掉开始的斜杠



### 2.7.4关于WEB-INF目录

放在WEB-INF目录下的资源是受保护的，无法通过静态资源路径访问

例如：

- 在WEB-INF目录下新建文件：welcome.html
- 打开浏览器访问：http://localhost:8080/tomcat/WEB-INF/welcome.html 出现404错误
- 因为该资源受保护，只可以通过Servlet访问





# 3 Servlet

后端执行哪个java程序取决于前端的请求，一个请求对应一个servlet程序。每一个servlet程序一定实现了Servlet接口。

## 3.1Servlet概述

### 3.1.1Servlet作用

Servlet是浏览器和Web服务器之间的通信规范，BS架构中不可或缺的一环。

BS架构角色：

- 1.浏览器客户端（如谷歌、火狐浏览器）
- 2.WebServer（如tomCat）
- 3.WebApp（如程序员自己开发的书城项目）
- 4.SQL数据库（如MySQL）

Servlet在BS架构中参与`WebServer`与`WebApp`之间的交流规范：

`浏览器`	←HTTP协议→	`Tomcat`	←Servlet规范→	`书城项目`	←JDBC规范→	`Mysql`



### 3.1.2Servlet入门程序

使用步骤：

- 创建一个空Model，并加入Web Application框架
- 在项目下创建目录resources，并设置为资源根目录存放配置文件
- 在WEB-INF下创建目录lib，存放jar包
- 确保项目已经加入servlet依赖的jar包
- 在java目录下编写Servlet接口的实现类，并在web.xml中注册
- 创建war包：Project Structure → Priject Settings → Artifacts → Add → Web Application:Exploded  → From Models... 
- 创建Tomcat，加入war包并启动，通过浏览器访问网址测试

代码实现：

- 创建一个空Model，并加入Web Application框架

- 在项目下创建目录resources，并设置为资源根目录存放配置文件

- 在WEB-INF下创建目录lib，存放jar包

- 确保项目已经加入servlet依赖的jar包

- 在java目录下编写Servlet接口的实现类，并在web.xml中注册

  ```java
  package com.javaweb.servlet;
  
  import javax.servlet.*;
  import java.io.IOException;
  
  /**
   * @Description: TODD
   * @AllClassName: com.javaweb.servlet.HelloServlet
   */
  public class HelloServlet implements Servlet {
      @Override
      public void init(ServletConfig servletConfig) throws ServletException {
  
      }
  
      @Override
      public ServletConfig getServletConfig() {
          return null;
      }
  
      @Override
      public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
          servletResponse.getWriter().write("abcde");
      }
  
      @Override
      public String getServletInfo() {
          return null;
      }
  
      @Override
      public void destroy() {
  
      }
  }
  ```

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
           version="4.0">
      
      <servlet>
          <servlet-name>hello</servlet-name>
          <servlet-class>com.javaweb.servlet.HelloServlet</servlet-class>
      </servlet>
      <servlet-mapping>
          <servlet-name>hello</servlet-name>
          <url-pattern>/hello</url-pattern>
      </servlet-mapping>
  </web-app>
  ```

- 创建war包：Project Structure → Priject Settings → Artifacts → Add → Web Application:Exploded  → From Models... 

- 创建Tomcat，加入war包并启动，通过浏览器访问网址测试



### 3.1.3关于JavaEE和JakartaEE

Oracle公司将JavaEE规范捐献给了Apache组织，Apache发布JavaEE新版本JakartaEE9，并将内部核心的包名都由java替换为了jakarta ，并且在Apache推出的Tomcat10版本上使用了jakartaEE的jar包。

因此，如果你之前的项目还是在使用javax.servlet.Servlet，那么你的项目无法直接部署到Tomcat10+版本上。你只能部署到Tomcat9-版本上。在Tomcat9以及Tomcat9之前的版本中还是能够识别javax.servlet这个包

|                JavaEE                |               JakartaEE                |
| :----------------------------------: | :------------------------------------: |
|     JavaEE目前最高版本是 JavaEE8     |   JavaEE8版本升级之后叫做JakartaEE9    |
| Servlet类名是：javax.servlet.Servlet | Servlet类名是：jakarta.servlet.Servlet |
|      适用于Tomcat9以之前的版本       |          适用于Tomcat10+版本           |



## 3.2Servlet规范

### 3.2.1Servlet生命周期

servlet在Tomcat容器中的生命周期包含四个阶段：创建、初始化、处理请求、销毁，这些生命周期是程序员是不能直接干预的，由Tomcat服务器（WEB Server或WEB容器）全权负责。而WEB容器对servlet的管理基于Map集合。该集合也可称之为Servlet容器

| Key（存储请求的路径） | Value（存储Servlet对象） |
| :-------------------: | :----------------------: |
|       /book/add       |    BookAddServlet对象    |
|      /book/edit       |   BookEditServlet对象    |
|      /book/index      |   BookIndexServlet对象   |

- ① Servlet对象的创建：构造器
  - 默认情况下，**Servlet容器第一次收到HTTP请求时创建对应Servlet对象。**
  - 容器之所以能做到这一点是由于我们在注册Servlet时提供了全类名，容器使用反射技术创建了Servlet的对象。

- ② Servlet对象初始化：init()
  - Servlet容器**创建Servlet对象之后，会调用init(ServletConfig config)**方法。
  - 作用：是在Servlet对象创建后，执行一些初始化操作。例如，读取一些资源文件、配置文件，或建立某种连接（比如：数据库连接）
  - init()方法只在创建对象时执行一次，以后再接到请求时，就不执行了
  - 在javax.servlet.Servlet接口中，public void init(ServletConfig config)方法要求容器将ServletConfig的实例对象传入，这也是我们获取ServletConfig的实例对象的根本方法。

- ③ 处理请求：service()
  - 在javax.servlet.Servlet接口中，定义了**service(ServletRequest req, ServletResponse res)**方法处理HTTP请求。
  - 在每次接到请求后都会执行。
  - 上一节提到的Servlet的作用，主要在此方法中体现。
  - 同时要求容器将ServletRequest对象和ServletResponse对象传入。

- ④ Servlet对象销毁：destroy()
  - 服务器重启、服务器停止执行或Web应用卸载时会销毁Servlet对象，会调用public void destroy()方法。
  - 此方法用于销毁之前执行一些诸如释放缓存、关闭连接、保存内存数据持久化等操作。

关于Servlet中方法的调用情况总结：

- 构造方法只执行一次。
- init方法只执行一次。
- service方法：用户发送一次请求则执行一次，发送N次请求则执行N次。
- destroy方法只执行一次。

服务器启动时创建Servlet对象的方法：

- 在web.xml的servlet标签中添加子标签**load-on-startup**，在该子标签中填写非0整数，越小的整数优先级越高。

  ```xml
  <servlet>
    <servlet-name>aservlet</servlet-name>
    <servlet-class>com.javaweb.servlet.HelloServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>aservlet</servlet-name>
    <url-pattern>/a</url-pattern>
  </servlet-mapping>
  ```



### 3.2.2ServletConfig

ServletConfig是一个接口，也是一个规范，Tomcat服务器实现了ServletConfig接口。

ServletConfig接口封装了Servlet配置信息，每一个Servlet都有一个唯一对应的ServletConfig对象，代表当前Servlet的配置信息。

ServletConfig对象中包装了它对应的Servlet对象，在web.xml文件中< servlet>标签的配置信息

```java
package javax.servlet;

import java.util.Enumeration;

public interface ServletConfig {
  	//获取当前servlet名字
    String getServletName();
		//获取ServletContext对象
    ServletContext getServletContext();
		//通过name获取web.xml文件中的初始化参数
    String getInitParameter(String var1);
		//获取web.xml文件中所有初始化参数的name
    Enumeration<String> getInitParameterNames();
}
```

```java
public class org.apache.catalina.core.StandardWrapperFacade implements ServletConfig {
  ...
}
```

读取ServletConfig初始化参数信息：⚠️

- 第一步：在web.xml文件中为某个添加Servlet标签添加初始化参数信息

  ```xml
  <servlet>
    <servlet-name>configTest</servlet-name>
    <servlet-class>com.bjpowernode.javaweb.servlet.ConfigTestServlet</servlet-class>
    <!--配置Servlet对象的初始化信息-->
    <init-param>
      <param-name>driver</param-name>
      <param-value>com.mysql.cj.jdbc.Driver</param-value>
    </init-param>
    <init-param>
      <param-name>url</param-name>
      <param-value>jdbc:mysql://localhost:3306/bjpowernode</param-value>
    </init-param>
    <init-param>
      <param-name>user</param-name>
      <param-value>root</param-value>
    </init-param>
    <init-param>
      <param-name>password</param-name>
      <param-value></param-value>
    </init-param>
  </servlet>
  ```

- 第二步：通过ServletConfig对象的getInitParameter方法和getInitParameterNames方法进行读取

  ```java
  Enumeration<String> initParameterNames = config.getInitParameterNames();
  //遍历初始化参数集合
  while(initParameterNames.hasMoreElements()){ 
    //取出本次循环的初始化参数name
    String parameterName = initParameterNames.nextElement();
    //根据name取出本次循环的初始化参数的value值
    String parameterVal = config.getInitParameter(parameterName);
    out.println(parameterName + "=" + parameterVal);
  }
  ```

接口 Enumeration< E>：

Enumeration是一个接口，要使用它其实需要的是实现了该类的子类对象。应用场景常见于遍历集合中的元素（如同迭代器Iterator）

实现 Enumeration 接口的对象，可连续调用 nextElement 方法将返回一系列的连续元素。

- hasMoreElements() 这是检测Enumeration 对象是否还有元素，有则返回true,否则faluse

- nextElement() 获取下一个元素



### 3.2.3ServletContext

Web容器在启动时，它会为**每个Web应用程序都创建一个唯一对应的ServletContext上下文对象**，代表当前Web应用。

**对象由Servlet容器在项目启动时创建**，通过ServletConfig对象的getServletContext()方法获取。在项目卸载时销毁。

```java
public interface ServletContext {
  
		//通过名称获取全局初始化参数⚠️
    String getInitParameter(String var1);
  
		//获取所有全局初始化参数的name⚠️
    Enumeration<String> getInitParameterNames();
  
  	//获取根/上下文路径⚠️
    String getContextPath();

  	//根据相对路径获取绝对路径⚠️
    String getRealPath(String var1);
  
  
		//通过ServletContext记录日志1
    void log(String var1);

    //通过ServletContext记录日志2
    void log(Exception var1, String var2);

  	//通过ServletContext记录日志3
    void log(String var1, Throwable var2);

  
		//通过name获取应用域共享参数⚠️
    Object getAttribute(String var1);
  
		//获取所有应用域共享参数的name
    Enumeration<String> getAttributeNames();
  
	  //设置应用域共享参数⚠️
    void setAttribute(String var1, Object var2);
  
		//通过名称删除应用域共享参数
    void removeAttribute(String var1);
  
  	...

}
```

读取ServletContext全局初始化参数信息：⚠️

- 第一步：在web.xml文件中添加全局初始化参数信息

  ```xml
  <!--配置在上下文中的信息不宜过大，影响效率，一般配置些limit分页信息-->
  <context-param>
    <param-name>pageSize</param-name>
    <param-value>10</param-value>
  </context-param>
  <context-param>
    <param-name>startIndex</param-name>
    <param-value>0</param-value>
  </context-param>
  ```

- 第二步：通过ServletContext对象的getInitParameter方法和getInitParameterNames方法进行读取

  ```java
  Enumeration<String> initParameterNames = context.getInitParameterNames();
  //遍历初始化参数集合
  while(initParameterNames.hasMoreElements()){ 
    //取出本次循环的初始化参数name
    String parameterName = initParameterNames.nextElement();
    //根据name取出本次循环的初始化参数的value值
    String parameterVal = context.getInitParameter(parameterName);
    out.println(parameterName + "=" + parameterVal);
  }
  ```

设置应用域共享数据：⚠️

- ServletContext是应用域对象。实际上向应用域当中绑定数据，就相当于把数据放到了缓存（Cache）当中，然后用户访问的时候直接从缓存中取，减少IO的操作，大大提升系统的性能，所以缓存技术是提高系统性能的重要手段。

```java
//通过name获取应用域共享参数
Object getAttribute(String var1);

//获取所有应用域共享参数的name
Enumeration<String> getAttributeNames();

//设置应用域共享参数
void setAttribute(String var1, Object var2);

//通过名称删除应用域共享参数
void removeAttribute(String var1);
```



### 3.2.4GenericServlet

GenericServlet是一个抽象类，继承了Servlet又重写了init方法，将ServletConfig变成成员变量在此方法中为其赋值。

如果想要进行初始化操作，可以重写GenericServlet提供的无参的init()方法，否则将影响ServletConfig的赋值。

将service()保留为抽象方法，继承GenericServlet类后重写service方法即可，该设计模式为模版方法设计模式。

```java
public abstract class GenericServlet implements Servlet, ServletConfig, Serializable {
    private transient ServletConfig config;
  
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        this.init();
    }
  
  	//提供了重载方法供子类重写，初始化使用
    public void init() {
    }

		//完善接口方法返回ServletConfig
    @Override
    public ServletConfig getServletConfig() {
        return sconf;
    }
    ...
}
```

- GenericServlet对Servlet功能进行了封装和完善，重写了init(ServletConfig config)方法，用来获取 ServletConfig对象。此时如果GenericServlet的子类（通常是自定义Servlet）又重写了init(ServletConfig config)方法有可能导致ServletConfig对象获取不到，所以子类不应该重写带参数的这个init()方法。
- 如果想要进行初始化操作，可以重写GenericServlet提供的无参的init()方法，这样就不会影响ServletConfig对象的获取。
- 将service(ServletRequest req,ServletResponse res)保留为抽象方法，让使用者仅关心业务实现即可。



## 3.3HTTPServlet规范

Servlet是面向多协议的规范，而HTTP协议中有HTTP-Servlet专用规范，存在于javax.servlet.http.HttpServlet包。

### 3.3.1HttpServlet

GenericServlet是一个抽象类，继承于Servlet规范中的GenericServlet抽象类，对GenericServlet进行进一步的封装和扩展。

用户访问时，通过service方法调用另一个重载的service方法，参数继承于最初的service方法的参数，并根据HTTP协议进行了拓展。

根据不同HTTP请求类型调用对应的方法进行处理，如doPost()和doGet()。我们继承HttpServlet后重写这两个方法即可。

```java
public abstract class HttpServlet extends GenericServlet {

    //处理get的请求，不重写该方法抛405
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_get_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(405, msg);
        } else {
            resp.sendError(400, msg);
        }

    }

		//处理post的请求，不重写该方法抛405
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String protocol = req.getProtocol();
        String msg = lStrings.getString("http.method_post_not_supported");
        if (protocol.endsWith("1.1")) {
            resp.sendError(405, msg);
        } else {
            resp.sendError(400, msg);
        }

    }


		//重载的service方法，参数继承于最初的service方法的参数，并根据HTTP协议进行了拓展
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        long lastModified;
        if (method.equals("GET")) {
            lastModified = this.getLastModified(req);
            if (lastModified == -1L) {
                this.doGet(req, resp);
            } else {
                long ifModifiedSince = req.getDateHeader("If-Modified-Since");
                if (ifModifiedSince < lastModified) {
                    this.maybeSetLastModified(resp, lastModified);
                    this.doGet(req, resp);
                } else {
                    resp.setStatus(304);
                }
            }
        } else if (method.equals("HEAD")) {
            lastModified = this.getLastModified(req);
            this.maybeSetLastModified(resp, lastModified);
            this.doHead(req, resp);
        } else if (method.equals("POST")) {
            this.doPost(req, resp);
        } else if (method.equals("PUT")) {
            this.doPut(req, resp);
        } else if (method.equals("DELETE")) {
            this.doDelete(req, resp);
        } else if (method.equals("OPTIONS")) {
            this.doOptions(req, resp);
        } else if (method.equals("TRACE")) {
            this.doTrace(req, resp);
        } else {
            String errMsg = lStrings.getString("http.method_not_implemented");
            Object[] errArgs = new Object[]{method};
            errMsg = MessageFormat.format(errMsg, errArgs);
            resp.sendError(501, errMsg);
        }

    }

		
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            HttpServletRequest request = (HttpServletRequest)req;
            HttpServletResponse response = (HttpServletResponse)res;
          	//调用另一个重载的service方法
            this.service(request, response);
        } else {
            throw new ServletException("non-HTTP request or response");
        }
    }
  ......
}

```

HttpServlet源码分析:⚠️

- 通过HttpServlet创建Servlet时，需要先创建一个普通类，暂且称为FinalServlet，再继承抽象类HttpServlet。

- 用户第一次请求时，会先创建FinalServlet类的对象，Tomcat会执行FinalServlet的无参数构造方法创建。

- 创建对象之后，继承自GenericServlet的有参init方法会执行，方法内会为ServletConfig赋值并调用无参init()方法。

- Tomcat接下来会调用FinalServlet对象的service方法处理请求，继承自父类HttpServlet的`service(ServletRequest req, ServletResponse res)`方法会执行。该service方法内会调用另一个重载的`service(HttpServletRequest req, HttpServletResponse resp)`方法，该方法会根据HttpServletRequest获取请求的类型，根据类型判断后执行对应的方法。如doGet、doPost、doPut。

- 然后我们要重写doGet、doPos方法，因为不重写会根据请求类型调用原来的，返回405错误。（如前端人员发的请求类型错误，则有可能会收到405错误）

- 没必要为了去掉405而直接重写doGet和dopost，他有存在的价值。比如前端人员验证登录用成了get。

  

### 3.3.2HttpServletRequest

该接口是ServletRequest接口的子接口，封装了HTTP请求的相关信息。

览器请求服务器时会封装请求报文交给服务器，服务器会解析请求报文，将结果封装到request对象中。

由Servlet容器创建其实现类对象并传入service(HttpServletRequest req, HttpServletResponse res)方法中。

```java
public interface HttpServletRequest extends ServletRequest {
    String BASIC_AUTH = "BASIC";
    String FORM_AUTH = "FORM";
    String CLIENT_CERT_AUTH = "CLIENT_CERT";
    String DIGEST_AUTH = "DIGEST";

    String getAuthType();

    Cookie[] getCookies();

    long getDateHeader(String var1);

    String getHeader(String var1);

    Enumeration<String> getHeaders(String var1);

    Enumeration<String> getHeaderNames();

    int getIntHeader(String var1);

    String getMethod();

    String getPathInfo();

    String getPathTranslated();

    String getContextPath();

    String getQueryString();

    String getRemoteUser();

    boolean isUserInRole(String var1);

    Principal getUserPrincipal();

    String getRequestedSessionId();

    String getRequestURI();

    StringBuffer getRequestURL();

    String getServletPath();

    HttpSession getSession(boolean var1);

    HttpSession getSession();

    String changeSessionId();

    boolean isRequestedSessionIdValid();

    boolean isRequestedSessionIdFromCookie();

    boolean isRequestedSessionIdFromURL();

    /** @deprecated */
    boolean isRequestedSessionIdFromUrl();

    boolean authenticate(HttpServletResponse var1) throws IOException, ServletException;

    void login(String var1, String var2) throws ServletException;

    void logout() throws ServletException;

    Collection<Part> getParts() throws IOException, ServletException;

    Part getPart(String var1) throws IOException, ServletException;

    <T extends HttpUpgradeHandler> T upgrade(Class<T> var1) throws IOException, ServletException;
}

```

#### 3.3.2.1获取请求参数

在请求参数中通过name获取value

- 输入框：可通过name获取单个value，未输入时只传递name，获取的value为空字符
- 单选框：可通过name获取单个value，未选择时不传递name，获取的value为null
- 多选框：可通过name获取多个value，未选择时不传递name，获取的value为nul
- 下拉框：可通过name获取单个value，未选择时看自己如何写的空选项option标签
- 总结：不存在的name获取的是null，未赋值的name获取的是空串，多选框获取到的value是数组

获取一个值：

```java
//输入框、单选框、下拉框
String userId = request.getParameter("userId");
```

获取多个值：

```java
//多选框
String[] soccerTeams = request.getParameterValues("soccerTeam");
for(int i = 0; i < soccerTeams.length; i++){
	System.out.println("team "+i+"="+soccerTeams[i]);
}
```



#### 3.3.2.2获取url地址参数

```java
System.out.println("上下文路径："+ request.getContextPath());
System.out.println("端口号："+request.getServerPort());
System.out.println("主机名："+request.getServerName());
System.out.println("协议："+request.getScheme());
```



#### 3.3.2.3获取请求头信息

```java
String header = request.getHeader("User-Agent");
System.out.println("user-agent:"+header);
String referer = request.getHeader("Referer");
System.out.println("上个页面的地址："+referer);//登录失败，返回登录页面让用户继续登录
```



#### 3.3.2.4请求的转发

- 将请求转发给另外一个URL，然后响应给浏览器，服务器内部完成的操作
- 发送了一次请求，只可以转发到Web服务器内的资源或Servlet上
- 转发有恶意提交安全隐患，转发涉及到表单页时，每次刷新页面都会自动提交创建空对象⚠️

```java
//获取请求转发对象
RequestDispatcher dispatcher = request.getRequestDispatcher("success.html");
//发起转发
dispatcher.forward(request, response);
```



#### 3.3.2.5向请求域中保存数据

```java
//将数据保存到request对象的属性域中
request.setAttribute("attrName", "attrValueInRequest");
//两个Servlet要想共享request对象中的数据，必须是转发的关系
request.getRequestDispatcher("/ReceiveServlet").forward(request, response);
```

```java
//从request属性域中获取数据
Object attribute = request.getAttribute("attrName");
System.out.println("attrValue="+attribute);
```



#### 3.3.2.6解决请求乱码问题

对于POST请求，请求的参数在请求体中，设置request字符编码即可

注意：

- Tomcat9前及之前，如果前端请求体提交的是中文，后端获取之后出现乱码
- Tomcat10之后，request请求体当中的字符集默认就是UTF-8，不会出现乱码问题。

```java
request.setCharacterEncoding("UTF-8");
```

对于get请求，请求的参数在请求行中，需修改tomcat/conf/server.xml配置文件的Connector标签

注意：

- Tomcat7及以下版本才需要处理，Tomcat8及以上默认`URIEncoding="UTF-8"`

```xml
<Connector URIEncoding="UTF-8"/>
```



### 3.3.4HttpServletResponse

该接口是ServletResponse接口的子接口，封装了服务器针对于HTTP响应的相关信息，暂时只有服务器的配置信息。

由Servlet容器创建其实现类对象，并传入service(HttpServletRequest req, HttpServletResponse res)方法中。

```java
public interface HttpServletResponse extends ServletResponse {

    void addCookie(Cookie var1);

    boolean containsHeader(String var1);

    String encodeURL(String var1);

    String encodeRedirectURL(String var1);

    /** @deprecated */
    String encodeUrl(String var1);

    /** @deprecated */
    String encodeRedirectUrl(String var1);

    void sendError(int var1, String var2) throws IOException;

    void sendError(int var1) throws IOException;

    void sendRedirect(String var1) throws IOException;

    void setDateHeader(String var1, long var2);

    void addDateHeader(String var1, long var2);

    void setHeader(String var1, String var2);

    void addHeader(String var1, String var2);

    void setIntHeader(String var1, int var2);

    void addIntHeader(String var1, int var2);

    void setStatus(int var1);

    /** @deprecated */
    void setStatus(int var1, String var2);

    int getStatus();

    String getHeader(String var1);

    Collection<String> getHeaders(String var1);

    Collection<String> getHeaderNames();
}
```

#### 3.3.4.1PrintWriter

PrintWriter对象的功能为向浏览器输出数据

- 写出的数据可以是页面、页面片段、字符串等

- 当写出的数据包含中文时，浏览器接收到的响应数据就可能有乱码。

```java
//通过PrintWriter对象向浏览器端发送响应信息
PrintWriter writer = res.getWriter();
writer.write("Servlet response");
writer.close();
```



#### 3.3.4.2解决响应乱码问题

可以在响应头中设置编码信息，或直接设置返回内容类型

注意：

- Tomcat9前及之前，如果后端响应体提交的是中文，服务器端获取之后出现乱码
- Tomcat10之后，response响应体当中的字符集默认就是UTF-8，不会出现乱码问题。
- 设置好以后，会在浏览器的响应报文中看到设置的响应头中的信息。

第一种方式：在响应头中设置编码信息

```java
response.setHeader("Content-Type", "text/html;charset=UTF-8");
```

第二种方式：设置返回内容类型

```java
response.setContentType("text/html;charset=utf-8");
```



#### 3.3.4.3响应的重定向

- 实现请求重定向，将重定向的url响应给浏览器，浏览器根据url再发一次请求
- 发了两次请求，第二次请求是浏览器完成的操作，可请求服务器外的数据

```java
response.sendRedirect(“success.html”);
```



## 3.4Servlet的技术体系

### 3.4.1Servlet继承体系

HttpServlet是HTTP协议的专用类，在http包下，比GenericServlet更加适合HTTP协议下的开发。

HttpServlet是HTTP协议的一部分，而非servlet规范的一部分，在javax.servlet.http.HttpServlet包下

- servlet规范中相关的类和接口：
  - jakarta.servlet.Servlet  核心接口（接口）
  - jakarta.servlet.ServletConfig  Servlet配置信息接口（接口）
  - jakarta.servlet.ServletContext  Servlet上下文接口（接口）
  - jakarta.servlet.ServletRequest  Servlet请求接口（接口）
  - jakarta.servlet.ServletResponse  Servlet响应接口（接口）
  - jakarta.servlet.ServletException  Servlet异常（异常类）
  - jakarta.servlet.GenericServlet  标准通用的Servlet类（抽象类）
- http包下的类和接口（jakarta.servlet.http.*）
  - jakarta.servlet.http.HttpServlet （HTTP协议专用的Servlet类，抽象类）
  - jakarta.servlet.http.HttpServletRequest （HTTP协议专用的请求对象）
  - jakarta.servlet.http.HttpServletResponse （HTTP协议专用的响应对象）
- HttpServletRequest对象中封装了哪些信息：
  - HttpServletRequest，简称request对象。
  - HttpServletRequest中封装了请求协议的全部内容。
  - Tomcat服务器（WEB服务器）将“请求协议”中的数据全部解析出来，然后将这些数据全部封装到request对象当中了。
  - 也就是说，我们只要面向HttpServletRequest，就可以获取请求协议中的数据。
- HttpServletResponse对象是专门用来响应HTTP协议到浏览器的。



### 3.4.2Servlet实现体系

- 实现Servlet接口
  - 实现所有抽象方法

- 继承GenericServlet抽象类
  - 实现service方法

- 继承HttpServlet抽象类
  - 重写doGet和doPost方法

- 通过idea向导创建Servlet
  - 默认使用注解和继承HttpServlet抽象类



## 3.5Web路径设置

四种路径使用情况：

- web.xml中设置的Servlet访问路径
- 网页中的路径（form表单的action、css文件、js文件、图片音频文件等）
- 转发路径
- 重定向路径

### 3.5.1Servlet访问路径

web.xml中`/`由Tomcat服务器解析，代表上下文路径下

web.xml中设置Servlet访问路径：

- 必须`/`开头，表示上下文路径下
  - /login：代表 /app/login（/app表示上下文路径）
  - /user/login：代表 /app/user/login（/app表示上下文路径）



### 3.5.2网页中的路径

页面中的`/`是浏览器解析的，表示当前端口下

路径类型：

- 相对路径：不加`/`或者加`./`
  - 此处的相对路径，相对的是当前`URL`，和当前html所在的文件位置无关⚠️
  - 不推荐使用，修改上下文路径层级后都需要更改
- 绝对路径：加`/`或者使用`base标签`
  - 直接使用`/`是从当前端口下查找文件，因为是浏览器解析的
  - 使用`base标签`时，为所有不加`/`的路径添加前缀
  - 推荐使用，修改上下文路径层级后都需要更改

- base标签：
  - 表示所有不加`/`的URL都添加了`/app`前缀
  - 必须设置在所有URL之前，在他之后的URL才会生效，一般设置在head标签里

```html
<head>
		<base href="/app">
    <meta charset="UTF-8">
    <title>index</title>
</head>
```



### 3.5.3转发路径

转发中的`/`由Tomcat服务器解析，代表上下文路径下

路径类型：

- 相对路径

  - 会把转发前所在的`Servlet`的`URL`作为相对的路径，而并非文件路径⚠️
  - 不推荐使用，转发使用相对路径时会有问题，层级发生变化需要修改

- 绝对路径

  - 直接使用`/`代表从上下文路径下查找，因为是服务器解析的

    ```java
    request.getRequestDispatcher("/books").forward(request,response);
    ```

  

### 3.5.4重定向路径

重定向中的`/`是浏览器解析的，表示当前端口下

- 相对路径

  - 会把重定向前所在的`Servlet`的`URL`作为相对的路径，而并非文件路径⚠️

  - 不推荐使用，重定向时使用相对路径时会有问题，层级发生变化需要修改

- 绝对路径

  - 直接使用`/`是从当前端口下查找文件，因为是浏览器解析的

  - 推荐使用`request.getContextPath()`获取上下文路径后再拼接转发的目标路径

    ```java
    response.sendRedirect(request.getContextPath() + "/books");
    ```

- 服务器外部资源：

  - 重定向的本质是服务器发起二次请求，所以重定向的`URL`可以是服务器外部的资源

    ```java
    response.sendRedirect("http://www.baidu.com");
    ```



## 3.6转发和重定向的选择

只要请求域request内绑定了数据就用转发，其他任何时候都用重定向！！！

重定向可以用来记录当前用户访问了哪些网址⚠️

|                         | 转发                             | 重定向                                              |
| ----------------------- | -------------------------------- | --------------------------------------------------- |
| 浏览器感知              | 在服务器内部完成，浏览器感知不到 | 服务器以302状态码通知浏览器访问新地址，浏览器有感知 |
| 浏览器地址栏            | 不改变                           | 改变                                                |
| 整个过程发送请求次数    | 一次                             | 两次                                                |
| 能否共享request对象数据 | 能                               | 否                                                  |
| WEB-INF下的资源 ⚠️       | 能访问  (因为是服务器内操作)     | 不能访问 （WEB-INF内资源受保护）                    |
| 目标资源 ⚠️              | 必须是当前web的资源(内部找)      | 不局限于当前web应用 (第二次请求客户端发)            |

注意：

1.转发是一次请求，无论转发了多少个servlet，都通过传递request参数，使他们都在同一个请求域里。

2.重定向使用的对象不是request了，是response，别写错了。

3.重定向的路径需要从项目名开始，或者说需要添加项目名。因为response是将路径响应给浏览器，浏览器发请求需要加项目名，浏览器自发的再发起一次全新的请求。

4.转发是小猫咪来控制的，tomcat内部完成的，所以必须是web内部的资源，但会存在刷新问题，比如插入数据后返回成功页面，再次刷新又会插入数据。



## 3.7Servlet注解的使用

Servlet3.0版本之后，推出了各种Servlet基于注解式开发。不需要配置web.xml文件，直接在java类上使用注解进行标注。

@WebServlet注解的源码：

```java
//元注解，表示可以出现在类上
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebServlet {
  //用来指定Servlet的名字。等同于<servlet-name>
  String name() default "";

  //当注解的属性名是value的时候，使用注解的时候，value属性名是可以省略的
  String[] value() default {};

  //用来指定Servlet的映射路径。可以指定多个字符串
  String[] urlPatterns() default {};

  //用来指定在服务器启动阶段是否加载该Servlet。等同于<load-on-startup>
  int loadOnStartup() default -1;

  //初始化参数，里面放注解@WebServlet
  WebInitParam[] initParams() default {}; 

  boolean asyncSupported() default false;

  String smallIcon() default "";

  String largeIcon() default "";

  String description() default "";

  String displayName() default "";
}
```

@WebServlet注解的使用：

```java
@WebServlet(
  name = "hello",
  urlPatterns = {"/hello1", "/hello2", "/hello3"},
  loadOnStartup = 1,
  initParams = {
    @WebInitParam(name = "username", value = "root"), 
    @WebInitParam(name = "password", value = "123")
  })
public class HelloServlet extends HttpServlet {
}
```

value属性的多种用法：（value可不用写属性名，其功能和urlPatterns相同）

```java
//@WebServlet(value = {"/welcome1", "/welcome2"})
// 如果注解的属性名是value的话，属性名也是可以省略的。
//@WebServlet(value = "/welcome1")
//@WebServlet({"/wel", "/abc", "/def"})
@WebServlet("/wel")
public class WelcomeServlet extends HttpServlet {
}
```

注解对象的使用格式：

- @注解名称(属性名=属性值, 属性名=属性值, 属性名=属性值....)
- 注意：不是必须将所有属性都写上，只需要提供需要的。（需要什么用什么。）
- 注意：属性是一个数组，如果数组中只有一个元素，使用该注解的时候，属性值的大括号可以省略。
- 注意：属性名是value时，可直接省略属性名。
- 注解的name不写时默认值为类名。



## 3.8Servlet的优化

所有Servlet请求的处理方式都是固定的，统一由Web服务器封装请求数据、封装响应数据、响应给浏览器等。只有service方法内的操作不同，所以可以通过模版方法模式进行优化，共用一个Servlet，service为模板方法。

### 3.8.1模版方法设计模式

模板方法设计模式概述：

- 在模板类的模板方法当中定义核心算法骨架，具体的实现步骤可以延迟到子类当中完成。
- 模板类通常是一个抽象类，模板类当中的模板方法定义核心算法，这个方法通常是final的（但也可以不是final的）
- 模板类当中的抽象方法就是不确定实现的方法，这个不确定怎么实现的事儿交给子类去做。

模版方法设计模式属于GoF设计模式中的一种

GoF设计模式：

- 通常我们所说的23种设计模式。（Gang of Four：4人组提出的设计模式）
- 单例模式
- 工厂模式
- 代理模式
- 门面模式
- 责任链设计模式
- 观察者模式
- **模板方法设计模式**

- ....

JavaEE设计模式：

- DAO
- DTO
- VO
- PO
- pojo
- ....



### 3.8.2静态分发路径优化

创建模版类，拦截多个请求，获取请求的Servlet路径，通过判断执行对应的方法。

```java
// 模板类，拦截多个请求
@WebServlet({"/employee/list", "/employee/save", "/employee/edit", "/employee/detail", "/employee/delete", "/employee/modify"})
// 也可以使用模糊匹配进行拦截
// 若使用模糊匹配，request.getServletPath()获取不到精确路径
// @WebServlet("/employee/*")
public class employeeServlet extends HttpServlet {

    // 模板方法
    // 重写service方法（并没有重写doGet或者doPost）

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取servlet path，判断执行哪个方法，类似于HttpServlet类设计思路
        String servletPath = request.getServletPath();
        if ("/employee/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/employee/save".equals(servletPath)) {
            doSave(request, response);
        } else if ("/employee/edit".equals(servletPath)) {
            doEdit(request, response);
        } else if ("/employee/detail".equals(servletPath)) {
            doDetail(request, response);
        } else if ("/employee/delete".equals(servletPath)) {
            doDel(request, response);
        } else if ("/employee/modify".equals(servletPath)) {
            doModify(request, response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("处理/list路径的请求");
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("处理/save路径的请求");
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("处理/edit路径的请求");
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("处理/detail路径的请求");
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("处理/delete路径的请求");
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("处理/modify路径的请求");
    }


}
```



### 3.8.3动态分发路径优化

优化思路与前者相同，需要调用的所有方法都集中在同一个Servlet中，判断调用方法的机制不同。该优化方式的调用方法机制是获取请求参数中对应方法的名称，通过方法名称和方法的形参即可使用反射动态调用该方法。

```java
package com.atguigu.bookstore.servlet.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.bookstore.servlet.base.BaseServlet
 */
public class ModelBaseServlet extends ViewBaseServlet {
    /**
     * 重写了Service就可以不重写doGet、doPost，直接在service中进行了处理
     * 继承BaseServlet的子类servlet会自动调用service方法
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //统一处理请求乱码问题(POST)，继承的子类将无需处理
        request.setCharacterEncoding("utf-8");
        //统一处理响应乱码问题，继承的子类将无需处理
        response.setContentType("text/html;charset=utf-8");

        //获取请求参数method传来的方法名字，需要知道调用哪个方法
        String methodName = request.getParameter("method");

        //存储被调用方法的对象
        Method method = null;

        try {

            /**
             * this.getClass()获取当前servlet类的class类对象，
             * 进行了泛型约束Class<? extends BaseServlet>， 获取的是BaseServlet或者其子类的类对象
             * 通过反射获取获取方法对象method
             */
            Class<? extends ModelBaseServlet> aClass = this.getClass();
            method = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            /*
             * this：当前类，使用service方法的类，这里是继承BaseServlet的子类
             * method.invoke在this这个类中调用method方法
             */
            method.setAccessible(true);
            method.invoke(this, request, response);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            //若使用拦截器或过滤器，异常捕获后必须抛出去
            throw new RuntimeException();
        }

    }
}
```





# 4 Thymeleaf

前端MVC设计理念：将封装数据的Model模型、显示用户界面的View视图、协调调度的Controller控制器分开，使前后端对接更方便。

## 4.1Thymeleaf概述

### 4.1.1Thymeleaf简介

Thymeleaf是一款用于渲染XML/XHTML/HTML5内容的模板引擎。类似JSP，Velocity，FreeMaker等， 它也可以轻易的与Spring MVC等Web框架进行集成作为Web应用的模板引擎。它的主要作用是在静态页面上渲染显示动态数据。

3.1.2Thymeleaf优势

- SpringBoot官方推荐使用的视图模板技术，和SpringBoot完美整合。
- 不经过服务器运算仍然可以直接查看原始值，对前端工程师更友好。



### 4.1.2逻辑视图

使用Thymeleaf必须明确逻辑视图、物理视图、视图前缀、视图后缀的概念

创建文件 `/pages/user/index.html`

- 物理视图：`/pages/user/index.html`
- 逻辑视图：`index`
- 视图前缀：`/pages/user/`
- 视图后缀：`.html`

总结：物理视图 = 视图前缀 + 逻辑视图 + 视图后缀



## 4.2Thymeleaf使用

### 4.2.1配置Thymeleaf环境

#### 4.2.1.1导入相关jar包

- attoparser-2.0.5.RELEASE.jar
- javassist-3.20.0-GA.jar
- log4j-1.2.15.jar
- ognl-3.1.26.jar
- slf4j-api-1.7.25.jar
- slf4j-log4j12-1.7.25.jar
- thymeleaf-3.0.12.RELEASE.jar
- unbescape-1.1.6.RELEASE.jar



#### 4.2.1.2配置全局上下文参数

配置视图前缀和视图后缀的全局初始化参数信息

- 将前缀设置到`/WEB-INF`目录下，只能通过servlet访问，更安全
- 重定向访问的话，必须重定向Servlet才能访问`/WEB-INF`目录下的页面

```xml
<!-- 在上下文参数中配置视图前缀和视图后缀 -->
<context-param>
    <param-name>view-prefix</param-name>
    <param-value>/WEB-INF/view/</param-value>
</context-param>
<context-param>
    <param-name>view-suffix</param-name>
    <param-value>.html</param-value>
</context-param>
```



#### 4.2.1.3创建Servlet基类

该类继承HttpServlet类，其他类继承该类，使用Thymeleaf时，不用创建并配置Thymeleaf解析器对象，同时又能处理前端请求。

- 该类的子类重写doGet或doPost方法即可，现阶段只存在两种请求类型
- 重写doGet或doPost方法中的一个方法，然后调用另一个， 因为两种请求处理方式一致

```java
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewBaseServlet extends HttpServlet {

    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {

        // 1.获取ServletContext对象
        ServletContext servletContext = this.getServletContext();

        // 2.创建Thymeleaf解析器对象
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);

        // 3.给解析器对象设置参数
        // ①HTML是默认模式，明确设置是为了代码更容易理解
        templateResolver.setTemplateMode(TemplateMode.HTML);

        // ②设置前缀
        String viewPrefix = servletContext.getInitParameter("view-prefix");

        templateResolver.setPrefix(viewPrefix);

        // ③设置后缀
        String viewSuffix = servletContext.getInitParameter("view-suffix");

        templateResolver.setSuffix(viewSuffix);

        // ④设置缓存过期时间（毫秒）
        templateResolver.setCacheTTLMs(60000L);

        // ⑤设置是否缓存
        templateResolver.setCacheable(true);

        // ⑥设置服务器端编码方式
        templateResolver.setCharacterEncoding("utf-8");

        // 4.创建模板引擎对象
        templateEngine = new TemplateEngine();

        // 5.给模板引擎对象设置模板解析器
        templateEngine.setTemplateResolver(templateResolver);

    }

    protected void processTemplate(String templateName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1.设置响应体内容类型和字符集
        resp.setContentType("text/html;charset=UTF-8");

        // 2.创建WebContext对象
        WebContext webContext = new WebContext(req, resp, getServletContext());

        // 3.处理模板数据
        templateEngine.process(templateName, webContext, resp.getWriter());
    }
}
```



#### 4.2.1.4继承ViewBaseServlet

创建ViewBaseServlet的子类，重写doGet或doPost，使用processTemplate方法返回逻辑视图和参数给视图解析器渲染

- processTemplate方法本质上是使用了转发！⚠️

```java
public class TestThymeleafServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("username","奥巴马");
        //请求转发跳转到/WEB-INF/view/target.html
        processTemplate("target",request,response);
    }
}
```



#### 4.2.1.5编写Thymeleaf页面

必须牢记`th命名空间`引入Thymeleaf的方法：<html lang="en" xmlns:th="http://www.thymeleaf.org">⚠️

```html
<!DOCTYPE html>
<!--引入Thymeleaf命名空间-->
<html lang="en" xmlns:th="http://www.thymeleaf.org">
    <head>
      	<base th:href="@{/}" href="/bookstore/"/>
        <meta charset="UTF-8">
        <title>目标页面</title>
    </head>
    <body>
        <h1 th:text="${username}">Thymeleaf渲染后该值会被替换</h1>
    </body>
</html>
```



## 4.3Thymeleaf语法

### 4.3.1th命名空间

使用th命名空间引入thymeleaf语法

```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```



### 4.3.2基础表达式

#### 4.3.2.1文本值表达式

使用`th:text="文本内容"`修改标签文本值

- 不经过服务器解析，直接用浏览器打开HTML文件，看到的是标签体原始值
- 经过服务器解析，Thymeleaf引擎根据th:text属性指定的标签体新值去替换签体原始值

```html
<p th:text="标签体新值">标签体原始值</p>
```



#### 4.3.2.2属性值表达式

使用`th:属性名="新属性值"`修改标签文本值

```html
<input type="text" name="username" th:value="文本框新值" value="文本框旧值" />
```



#### 4.3.2.3解析上下文路径

语法：@{/}

应用场景一：base标签的href属性

```html
<base th:href="@{/}">
```

应用场景二：使用在请求路径上

```html
<a href="getUser?name=tom&age=18">普通传递请求参数</a>
<!--Thymeleaf可使用@{}进行传参-->
<a th:href="@{getUser(name='tom',age=18)}">Thymeleaf传递请求参数</a>
```



### 4.3.3域对象

请求域

- ${请求域共享数据的key值

会话域

- ${session.会话域中共享数据的key值}

应用域

- ${application.应用域共享数据的key值}



### 4.3.4请求参数

- ${param.请求参数的key值}
  - 如果key值对应多个value值，返回一个集合，可以通过索引去获取集合内的数据
  - 如果key值对应多个value值，返回一个数组时，可以通过下标取值



### 4.3.5内置对象

所谓内置对象其实就是在Thymeleaf的表达式中可以直接使用的对象

内置对象有很多，重点记忆如下5个关键常用的：⚠️

- #dates  对日期的一些操作(格式化) 
- #strings 对字符串的一些操作 
- #lists  对list集合的操作 
- #sets  对set集合的操作 
- #maps  对map集合的操作

```html
<h3>表达式的基本内置对象</h3>
<p th:text="${#request.getContextPath()}">调用#request对象的getContextPath()方法</p>
<p th:text="${#request.getAttribute('helloRequestAttr')}">调用#request对象的getAttribute()方法，读取属性域</p>
<p th:value="${#maps.containsKey(userMaps, 'name')} ? ${userMaps.name} : 无名称属性"></p>
```



### 4.3.6OGNL语法

对象导航图语言（Object Graph Navigation Language），简称OGNL。其作用是对数据进行访问，它拥有类型转换、访问对象方法、操作集合对象等功能。

访问对象属性：基于对象的公共get方法

- 对象.属性名

访问List集合或数组：

- 集合或数组[下标]

访问Map集合：

- Map集合.key
- Map集合['key']



### 4.3.7分支与迭代

#### 4.3.7.1Thymeleaf分支语法

标签`th:if`、`th:unless`会根据条件决定是否显示。

- 标签th:if（值为true时显示）
- 标签th:unless（值为false时显示）
- 可使用not取反，`if标签 + not关键词` == `unless标签`

Servlet层传入请求域参数：

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 1.创建ArrayList对象并填充
    List<Employee> employeeList = new ArrayList<>();

    employeeList.add(new Employee(1, "tom", 500.00));
    employeeList.add(new Employee(2, "jerry", 600.00));
    employeeList.add(new Employee(3, "harry", 700.00));

    // 2.将集合数据存入请求域
    request.setAttribute("employeeList", employeeList);

    // 3.调用父类方法渲染视图
    super.processTemplate("list", request, response);
}
```

Thymeleaf处理参数获得布尔值控制是否显示：

```html
<tr th:if="${#lists.isEmpty(employeeList)}">
  <td colspan="3">抱歉！没有查询到你搜索的数据！</td>
</tr>
<tr th:if="${not #lists.isEmpty(employeeList)}">
  <td colspan="3">有数据！</td>
</tr>
<tr th:unless="${#lists.isEmpty(employeeList)}">
  <td colspan="3">有数据！</td>
</tr>
```

标签`th:switch`也具有分支功能

```html
<h3>测试switch</h3>
<div th:switch="${user.memberLevel}">
    <p th:case="level-1">银牌会员</p>
    <p th:case="level-2">金牌会员</p>
    <p th:case="level-3">白金会员</p>
    <p th:case="level-4">钻石会员</p>
</div>
```



#### 4.3.7.2Thymeleaf迭代语法

标签`th:each`迭代，格式为：<tr th:each="集合的每一项,状态 : ${集合}">

status表示遍历的状态，它包含如下属性:

- index 遍历出来的每一个元素的下标
- count 遍历出来的每一个元素的计数

```html
<!--遍历显示请求域中的teacherList-->
<table border="1" cellspacing="0" width="500">
    <tr>
        <th>编号</th>
        <th>姓名</th>
    </tr>
  	<!--集合为空，不进行遍历-->
    <tbody th:if="${#lists.isEmpty(teacherList)}">
        <tr>
            <td colspan="2">教师数据为空</td>
        </tr>
    </tbody>

    <!--集合不为空，遍历展示数据-->
    <tbody th:unless="${#lists.isEmpty(teacherList)}">
        <!--遍历展示数据-->
        <tr th:each="teacher,status : ${teacherList}">
            <td th:text="${status.count}">这里显示编号</td>
            <td th:text="${teacher.teacherName}">这里显示老师的名字</td>
        </tr>
    </tbody>
</table>
```



### 4.3.8 Thymeleaf模板

设置 Thymeleaf模板：

- th:fragment（设置模板标识）

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

<div th:fragment="abc">
  <h1>我是Thymeleaf模板的内容，可通过fragment的属性值对我进行引用</h1>
</div>
</body>
</html>
```

引入 Thymeleaf模板：

- th:include（常用）
- th:insert
- th:replace

```html
<!--在原有div下，直接把内容塞进去，不包含自己的标签-->
<div th:include="base :: abc" id="aHeader01"></div>
<!--在原有div下，包含自己的标签，直接塞进去-->
<div th:insert="base :: abc" id="aHeader02"></div>
<!--直接替换原有div-->
<div th:replace="base :: abc" id="aHeader03"></div>
```



### 4.3.9VUE嵌套Thymeleaf

Thymeleaf先于Vue渲染，所以Thymeleaf渲染的内容会被Vue覆盖掉，解决这一问题必须使用双中括号语法

语法：`"Vue对象属性值": "[[${Thymeleaf共享域中的值}]]"`

```html
<script>
    new Vue({
        "el": "#app",
        "data": {
            "username": "[[${param.username}]]",
            "password": "[[${param.password}]]",
            "errorMsg": "[[${ errMsg == null ? '请输入用户名或密码' :errMsg}]]"
        }
    })
</script>
```



### 4.3.10内部js嵌套Thymeleaf

在使用thymeleaf时，前端页面如要在javascript中获取后端传入的数据，需要使用<script th:inline="javascript">

- 修改script标签：`<script th:inline="javascript">`

```html
<script th:inline="javascript">
    <!--弹出层事件-->
    $(function(){
        <!--点击按钮发起异步请求，获取创建页面放到当前页面弹出层的iframe窗体里-->
        $(".create").on("click",function () {
            opt.openWin("/role/create","新增",580,430);
        });
    });
</script>
```





# 5 Session会话域

Session是HTTP规范的一部分，HttpSession类的实现。

## 5.1Session概述

核心作用是存放登录成功的用户信息⚠️

### 5.1.1Session简介

因为HTTP协议是无状态协议，无法得知用户的会话状态，所以使用Session保存浏览器客户端和服务器间的会话状态。

Session是服务端的会话技术，数据存储在服务端，依赖于Cookie实现。

首次请求，若登录成功，执行`request.getSession()`创建Session，将用户信息放到Session中保存，。

第二次请求，



### 5.1.2Session实现原理

Session保存在客户端，客户端的Session列表是一个Map，map的key是sessionId，map的value是session对象。

|    key     |    value     |
| :--------: | :----------: |
| SessionId1 | Session对象1 |
| SessionId2 | Session对象2 |

首次请求：

- 若执行`request.getSession()`创建Session，会同时创建SessionId
- 创建后将Session和SessionId存储到Session列表`Map<SessionId,Session>`中
- 最后将SessionId响应给浏览器客户端。

第二次请求：

- 浏览器自动将内存中的SessionId发送给服务器
- 服务器自动根据SessionId从Session列表中查找对应的Session对象



## 5.2Session使用

### 5.2.1Session测试程序

```java
// 1.调用request对象的方法尝试获取HttpSession对象
HttpSession session = request.getSession();

// 2.调用HttpSession对象的isNew()方法
boolean wetherNew = session.isNew();

// 3.打印HttpSession对象是否为新对象
System.out.println("wetherNew = " + wetherNew+"HttpSession对象是新的":"HttpSession对象是旧的"));

// 4.调用HttpSession对象的getId()方法
String id = session.getId();

// 5.打印JSESSIONID的值
System.out.println("JSESSIONID = " + id);
```



### 5.2.2Session常用方法

```java
//获取session，若没有就新建
HttpSession session = request.getSession();
//获取session，有boolen参数，设置为false时，若没有就不新建
HttpSession session = request.getSession(false);

//设置超时销毁时间，以下为5小时，5小时内客户端都没发请求就销毁
session.setMaxInactiveInterval(60*60*5);

//会话域设置参数的方法
setAttribute(String key,Object value)
getAttribute(String key)
removeAttribute(String key)
```

web.xml中设置所有session超时的默认时间：

```xml
<!--设置session30分钟如果没人访问就超时销毁，默认半小时-->
<session-config>
	<session-timeout>30</session-timeout>
</session-config>
```



### 5.2.3Session销毁情况

Session对象存储在服务端。当用户很多又一味创建不释放，那么服务器端的内存迟早要被耗尽。

- 销毁方式一：手动销毁。session.invalidate();
- 销毁方式二：超时销毁。达到最大空闲时间session回自动失效，默认为30分钟。
- 销毁方式三：关闭浏览器，会导致关闭浏览器cookie消失，sessionId消失则会话等同于结束。



### 5.2.4getSession方法原理

执行方法后会去请求中寻找JSESSIONID的Cookie

- 找不到（首次请求）
  - 服务器会为其创建session对象并设置JSESSIONID的Cookie
- 找的到
  - 根据JSESSIONID的Value值寻找Session对象
    - 找不到（比如其对应的sission对象被手动或超时销毁了）
      - 服务器会为其创建session对象并设置JSESSIONID的Cookie
    - 找的到
      - 返回该对象



### 5.2.5浏览器禁用Cookie

服务器正常发送cookie给浏览器，但是浏览器设置了禁用cookie，则SessionId无法保存在客户端。

请求中无cookie时，无法将SessionId给客户端，则每一次请求客户端都会创建session对象，浪费资源。

解决方案：cookie禁用时，强行实现session机制：

- 需要使用URL重写机制：
- [http://localhost:8080/servlet/**session;jsessionid=19D1C99560DCBF84839FA43D58F56E16**](http://localhost:8080/servlet12/test/session;jsessionid=19D1C99560DCBF84839FA43D58F56E16)
- URL重写机制会提高开发者的成本。开发人员在编写任何请求路径的时候，后面都要添加一个sessionid，给开发带来了很大的难度，很大的成本。所以大部分的网站都是这样设计的：若禁用cookie，禁止用户使用。



### 5.2.6实现Session保存会话

实现方式：

- 收到访问请求，查看Session会话域中是否存在用户信息，不存在即未登录，强制跳转到登录页面
- 登录成功后将用户信息保存到Session会话域中，再访问其他页面时就不再需要登录。



## 5.3Cookie概述

### 5.3.1Cookie简介

Cookie是客户端的会话技术，它是服务器存放在浏览器的一小份数据。存储Cookie后，浏览器每次访问该服务器时，都会将Cookie携带到服务器上去。



### 5.3.2Cookie的作用

Cookie常用来记住用户名和保存电影的播放进度，其本质作用为： 

1. 在浏览器中存放数据
2. 将浏览器中存放的数据携带到服务器



### 5.3.3Cookie常用方法

在java的servlet.http中，对cookie提供了一个Cookie类来专门表示cookie数据。jakarta.servlet.http.Cookie

```java
//1. 创建一个Cookie对象
Cookie cookie01=new Cookie("username","admin6688");
Cookie cookie02=new Cookie("password","root8866");

//1.2 在添加到响应之前设置持久化时间
cookie01.setMaxAge(600);//单位是s

//1.3 在添加到响应之前设置cookie的携带路径
cookie01.setPath(request.getContextPath()+"/aaa");

//2. 将cookie对象添加到response内
response.addCookie(cookie01);
response.addCookie(cookie02);
```

获取cookie时一定要先判断！！！若浏览器没有返回cookie，得到的是null而不是空数组，小心空指针！⚠️

```java
// 1.通过request对象获取。（返回值是一个数组，因为浏览器可能会提交多个cookie给服务器。）
// 2.注意细节：遍历时一定要先判断！！！。
Cookie[] cookies = request.getCookies();

// 如果不是null，表示一定有cookie
if(cookies !=null){
  // 遍历数组
  for (Cookie cookie : cookies) {
    // 获取cookie的name和value
    String name = cookie.getName();
    String value = cookie.getValue();
    System.out.println(name + "=" + value);
  }
}
```



### 5.3.4Cookie保存位置

- 保存在浏览器客户端上 。
- 默认保存在运行内存中。（浏览器只要关闭cookie就消失了。）
- 也可以保存在硬盘文件中。（永久保存。）



### 5.3.5Cookie机制

- cookie在服务端创建，放到response对象里发送到浏览器。
- cookie数据一旦发给浏览器，则浏览器每次请求都会自动携带该path下所有的cookie数据给服务器。
- cookie机制和session机制不属于java中的机制，它们都是HTTP协议的一部分。



### 5.3.6Cookie的有效时间

- 没有设置有效时间，则默认为瞬时cookie，保存在浏览器的运行内存中，浏览器关闭则cookie消失。
- 持久化的cookie，需要设置一个时间，时间到了cookie数据就没有了（存到磁盘里）
  - cookie.setMaxAge(60 * 60); 设置cookie在一小时之后失效。
- 设置cookie的有效时间 > 0，这个cookie一定会存储到硬盘文件当中。
- 设置cookie的有效时间 = 0，cookie被删除，同名cookie被删除。
- 设置cookie的有效时间 < 0，cookie被删除，瞬时cookie，和默认一样。



### 5.3.7Cookie关联的路径

- 默认的有效path是它所在的servlet设置的uri和其子路径。
- 通过方法设置有效path，此路径及其子路径都被覆盖。cookie.setPath(“/user”);
- 设置上下文路径后，此cookie覆盖所有路径。cookie.setPath(request.getContextPath());



## 5.4Kaptcha验证码

kaptcha是谷歌开源的图片验证码信息，借助Session实现验证码功能。

步骤：用户请求验证码URL时，会随机生成一组验证码，将验证码方法哦请求域中，并根据验证码生成验证码图片响应给前端，前端输入验证码后，后端拿到再从会话域Session中取出来进行判断，看是否正确，默认的key是KAPTCHA_SESSION_KEY。

### 5.4.1后端配置Kaptcha

导入jar包kaptcha-2.3.2.jar

在web.xml中配置Kaptcha的Servlet及参数

```xml
<servlet>
    <servlet-name>KaptchaServlet</servlet-name>
    <servlet-class>com.google.code.kaptcha.servlet.KaptchaServlet</servlet-class>

    <!-- 通过配置初始化参数影响KaptchaServlet的工作方式 -->
    <!-- 可以使用的配置项参考com.google.code.kaptcha.util.Config类 -->
    <!-- 配置kaptcha.border的值为no取消图片边框 -->
    <init-param>
        <param-name>kaptcha.border</param-name>
        <param-value>no</param-value>
    </init-param>
  
</servlet>
<servlet-mapping>
    <servlet-name>KaptchaServlet</servlet-name>
    <url-pattern>/kaptcha</url-pattern>
</servlet-mapping>
```

参数详解

```xml
kaptcha.border
验证码图片的边框，可以设置yes或者no
默认值 yes

kaptcha.border.color
边框的颜色reg值。合法值 rgb，black，blue，white
默认值 black

kaptcha.border.thickness
边框的宽度
默认 1

kaptcha.image.width
图片的宽度
默认200

kaptcha.image.height
图片的高度
默认50

kaptcha.producer.impl
生成图片使用的类
默认 com.google.code.kaptcha.impl.DefaultKaptcha


kaptcha.textproducer.impl
生成图片中文字的使用的类
默认com.google.code.kaptcha.text.impl.DefaultTextCreator

	
kaptcha.textproducer.char.string
验证码中使用的字符
默认 abcde2345678gfymnpwx

kaptcha.textproducer.char.length
验证码中字符的数量
默认 5

kaptcha.textproducer.font.names
验证码的字体
默认 Arial, Courier

kaptcha.textproducer.font.size
字体的大小
默认 40

kaptcha.textproducer.font.color
字体颜色 rgb值，颜色单词
默认 black

kaptcha.textproducer.char.space
两个字符之间的间距
默认 2

kaptcha.noise.impl
干扰线生成类
默认 com.google.code.kaptcha.impl.DefaultNoise

kaptcha.noise.color
干扰线颜色
默认 black

kaptcha.obscurificator.impl
The obscurificator implementation.
默认 com.google.code.kaptcha.impl.WaterRipple

kaptcha.background.impl
背景颜色设置类
默认 com.google.code.kaptcha.impl.DefaultBackground

kaptcha.background.clear.from
渐变颜色 左到右
默认 light grey

kaptcha.background.clear.to
渐变颜色 右到左
默认 white

kaptcha.word.impl
词语渲染器
默认 com.google.code.kaptcha.text.impl.DefaultWordRenderer

kaptcha.session.key
在session中存储属性的名字
默认 KAPTCHA_SESSION_KEY
```



### 5.4.2前端配置Kaptcha

验证码的使用：

```html
<!--图片的src写验证码的servlet路径-->
<img src="kaptcha" alt="" />
```

验证码的刷新：

```js
//切换验证码，其实就是重新设置img标签的src，使用时间戳来更新
changeCode(){
  this.checkCodePath = "kaptcha?time=" + new Date()
}
```



### 5.4.3后端验证码校验

获取验证码并进行判断步骤（要注意使用Object进行接收）

```java
//获取用户输入的验证码
String code = request.getParameter("code");

//注意接收类型
Object kaptcha_session_key = request.getSession().getAttribute("KAPTCHA_SESSION_KEY");

//直接用equals进行判断
if(code.equals(kaptcha_session_key)){
  //这里写若验证码正确执行的操作，或者验证码不正确这里直接return
}
```





# 6 异步请求

真正的前后端分离是前端项目和后端项目分服务器部署，数据全部通过Ajax方式以JSON格式来传递。

## 6.1异步请求

异步请求，在JS单线程的基础上新创建一个线程用来发起请求和等待响应结果，不会影响原来的线程

### 6.1.1Ajax异步请求

Ajax请求的参数中还有回调函数then和异常捕捉函数catch

```js
//使用axios发送异步请求
axios({
  "method":"post",
  "url":"demo01",
  "params":{
    "userName":"tom",
    "userPwd":"123456"
  }
}).then(response => {
  //then里面是处理请求成功的响应数据
  //response就是服务器端的响应数据,是json类型的
  //response里面的data就是响应体的数据
  this.message = response.data
}).catch(error => {
  //error是请求失败的错误描述
  console.log(error)
})
```



## 6.2后端响应Json

若对前端的异步请求作出响应，后端响应的响应体中只可以放String类型，若要想响应Json数据必须将Json转换为字符串来实现

### 6.2.1Gson的使用

我们可以借助Gson工具来实现将Json转换为Json类型的字符串

首先，先导入jar包gson-2.2.4.jar

然后，通过Gson将对象转换为String，返还给前端

```java
//转换为Json类型的字符串
Gson gson = new Gson();
String json = gson.toJson(object)
//响应给前端的异步请求
response.getWriter().write(json);
```





# 7 Filter和Listener

## 7.1Filter概述

Filter：一个实现了特殊接口(Filter)的Java类， 实现对请求资源(jsp,servlet,html,)的过滤的功能.  过滤器是一个运行在服务器的程序, 优先于请求资源(Servlet或者jsp,html)之前执行. 过滤器是javaweb技术中**最为实用**的技术之一

### 7.1.1Filter作用

- Filter可以在Servlet这个目标程序执行前后添加过滤规则。
- 一般情况下，都是在过滤器当中编写公共代码。

应用场景：登录权限检查、解决网站乱码、过滤敏感字符等



### 7.1.2Filter入门程序

第一步：编写一个Java类**实现Filter接口**：jarkata.servlet.Filter。并且实现这个接口当中所有的方法。

- 无参构造方法：Filter对象默认情况下，在服务器启动的时候会新建对象。（单实例）
- init方法：在Filter对象第一次被创建之后调用，并且只调用一次。
- doFilter方法：只要用户发送一次请求，则执行一次。在这个方法中编写过滤规则。
- destroy方法：在Filter对象被释放/销毁之前调用，并且只调用一次。
- 和Servlet对象生命周期一致。

```java
package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 编写过滤器的步骤:
 * 1. 写一个类实现Filter接口，并且重写方法
 * 2. 在web.xml中配置该过滤器的拦截路径
 */
public class EncodingFilter implements Filter {
    @Override
    public void destroy() {
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        
    }

}
```

第二步：在过滤器当中编写chain.doFilter(request, response); 代码。

- 执行下一个过滤器，如果下面没有过滤器了，执行最终的Servlet
- **这行代码之前是请求过滤，这行代码之后是响应过滤。⚠️**
- 路径/xx.do 对应一个Filter，也对应一个Servlet。Filter的优先级高先执行。

```java
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //解决请求参数的乱码
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("UTF-8");

        //每次有请求被当前filter接收到的时候，就会执行doFilter进行过滤处理
        System.out.println("EncodingFilter接收到了一个请求...");

        //这句代码表示放行
        chain.doFilter(req, resp);
      
        //放行后执行的代码
        System.out.println("EncodingFilter放行了了一个请求...");
    }
```

第三步：在web.xml文件中对Filter**配置过滤路径**，或者使用注解：@WebFilter({"*.do"})。

```xml
<filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>com.atguigu.filter.EncodingFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>encodingFilter</filter-name>
    <!--url-pattern表示指定拦截哪些资源-->
    <url-pattern>/demo01</url-pattern>
</filter-mapping>
```



### 7.1.3Filter过滤器链

一个请求可能被多个过滤器所过滤，只有当所有过滤器都放行，请求才能到达目标资源，如果有某一个过滤器没有放行，那么请求则无法到达后续过滤器以及目标资源，多个过滤器组成的链路就是过滤器链。

执行顺序：

- 若使用xml配置，则filter-mapping配置的顺序靠前的先执行

- 若使用@WebFilter的时候，执行顺序是比较Filter的类名。

  - 例一：FilterA和FilterB，则先执行FilterA。

  - 例二：Filter1和Filter2，则先执行Filter1。

注意：该设计模式为责任链设计模式⚠️



### 7.1.4Filter过滤路径

关于Filter的配置路径：

- /hello、/dept/save 这些配置方式都是精确匹配。
- /* 极端情况，匹配所有路径。
- *.abc 后缀匹配。不要以 / 开始！！！
- /dept/* 前缀匹配。



### 7.1.5Filter选择性拦截

把项目中需要保护的功能保护起来，没有登录不允许访问购物车订单

```java
@WebFilter(filterName = "LoginFilter",urlPatterns = "/order")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1. 检查是否处于登录状态(就是看session域内有没有user对象)
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;

        Object user = request.getSession().getAttribute("user");
        if(user==null){
            //未登录
            response.sendRedirect(request.getContextPath()+"/user?flag=toLoginPage");
        }else{
            chain.doFilter(req, resp);
        }
    }
    public void init(FilterConfig config) throws ServletException {}
    public void destroy() {}
}
```



### 7.1.6责任链设计模式

Filter过滤器的设计模式为责任链设计模式。

责任链设计模式最大的核心思想：

- 在程序运行阶段，动态的组合程序的调用顺序。

责任链设计模式能解决的缺点：

-  对于在编译阶段已经完全确定了调用关系的程序，无法再改变调用关系，除非修改源代码重新编译。这种做法显然违背了OCP原则。为了实现不修改源代码的前提下，改动调用顺序，所以使用责任链设计模式。

过滤器最大的优点：

- 在程序编译阶段不会确定调用顺序。因为Filter的调用顺序是配置到web.xml文件中的。
- 只要修改web.xml配置文件中filter-mapping的顺序就可以调整Filter的执行顺序。
- 显然Filter的执行顺序是在程序运行阶段动态组合的。那么这种设计模式被称为责任链设计模式。



### 7.1.7Filter、Finally和重定向

在过滤器中的finally中进行重定向时，若重定向的uri与filter过滤的uri一致时，很容易出现重定向死循环

在过滤器中的finally中进行重定向时，若重定向的uri与filter过滤的uri一致时，不会发生重定向循环操作

关键在于try里是否进行了响应：

第一次请求 中间出现运行时异常 servlet方法中的转发没有执行，就直接给到dofilter去了，所以try里没有把响应结束，finally里的重定向就可以执行。

第二次请求，因为中间没有运行时异常，servlet中的方法执行了转发，到了dofilter，try里执行了响应，所以finally里重定向失效了。



## 7.2Listener概述

Listener：Servlet规范中定义的一种特殊类，它用于监听Web应用程序中的ServletContext，HttpSession 和HttpServletRequest等域对象的创建与销毁事件，以及监听这些域对象中的属性发生修改的事件。

### 7.2.1Listener作用

监听器和过滤器都是Servlet规范中的一员，在Servlet中，所有的监听器接口都是以“Listener”结尾。

作用：

- 监听器实际上是Servlet规范留给我们javaweb程序员的特殊时机。
- 特殊的时刻如果想执行这段代码，你需要想到使用对应的监听器。

应用场景：统计在线人数、监听服务器启动加载文件或开启某些业务等



### 7.2.2Listener入门程序

以ServletContextListener监听器为例：

第一步：编写一个类实现ServletContextListener接口。并且重写里面的默认方法。

```java
package com.atguigu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 1. 写一个类实现对应的：Listener的接口(我们这里使用的是ServletContextListener),并且实现它里面的方法
 *    1.1 contextInitialized()这个方法在ServletContext对象被创建出来的时候执行，也就是说在服务器启动的时候执行
 *    1.2 contextDestroyed()这个方法会在ServletContext对象被销毁的时候执行，也就是说在服务器关闭的时候执行
 *
 * 2. 在web.xml中注册(配置)监听器
 */
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
```

第二步：在web.xml文件中对ServletContextListener进行配置或使用@WebListener注解。不做此操作tomcat将无法创建listener对象。

```xml
<listener>
    <listener-class>com.atguigu.listener.ContextLoaderListener</listener-class>
</listener>
```

第三步：编写监听ServletContext对象创建和销毁时，所执行的语句。

```java
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("在服务器启动的时候，模拟创建SpringMVC的核心容器...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("在服务器启动的时候，模拟销毁SpringMVC的核心容器...");
    }
```



### 7.2.3Listener的类型

jakarta.servlet包下：

- ServletContextListener
- ServletContextAttributeListener
- ServletRequestListener
- ServletRequestAttributeListener

jakarta.servlet.http包下：

- HttpSessionListener
- HttpSessionAttributeListener⚠️
  - 该监听器需要使用@WebListener注解进行标注。
  - 该监听器**监听的是session域中数据的变化**。只要数据变化，则执行相应的方法。主要监测点在session域对象上。
- HttpSessionBindingListener⚠️
  - 该监听器不需要使用@WebListener进行标注。
  - **假设User类实现了该监听器，那么****User对象在被放入session的时候触发bind事件，User对象从session中删除的时候，触发unbind事件**。
  - 假设Customer类没有实现该监听器，那么Customer对象放入session或者从session删除的时候，不会触发bind和unbind事件。
- HttpSessionIdListener
  - session的id发生改变的时候，监听器中的唯一一个方法就会被调用。
- HttpSessionActivationListener
  - 监听session对象的钝化和活化的。
  - 钝化：session对象从内存存储到硬盘文件。
  - 活化：从硬盘文件把session恢复到内存。



### 7.2.4Listener统计在线人数

业务场景1：

- 统计网站实时的用户的在线数量

解决方案1:

- 我们可以通过服务器端有没有分配session对象，因为一个session代表了一个用户。有一个session就代表有一个用户。如果采用这种逻辑去实现的话，session有多少个，在线用户就有多少个。这种方式的话：HttpSessionListener够用了。session对象只要新建，则count++，然后将count存储到ServletContext域当中，在页面展示在线人数即可。

业务场景2:

- 业务发生改变了，只统计登录的用户的在线数量

解决方案2:

- session.setAttribute("user", userObj);
- 用户登录的标志是session中曾经存储过User类型的对象。那么这个时候可以让User类型的对象实现HttpSessionBindingListener监听器，只要User类型对象存储到session域中，则count++，然后将count++存储到ServletContext对象中。页面展示在线人数即可。

总结：

- 实现oa项目中当前登录在线的人数。
- 登录标志：
  - session.setAttribute("user", userObj); 用户登录后user存在会话域里，表示有新用户登录。
- 退出标志：
  - session.removeAttribute("user"); User类型的对象从session域中移除了。
  - 或者有可能是session销毁了。（session超时）

代码示例：

```java
public class User implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // 用户登录了
        // User类型的对象向session中存放了。
        // 获取ServletContext对象
        ServletContext application = event.getSession().getServletContext();
        // 获取在线人数。
        Object onlinecount = application.getAttribute("onlinecount");
        if (onlinecount == null) {
            application.setAttribute("onlinecount", 1);
        } else {
            int count = (Integer) onlinecount;
            count++;
            application.setAttribute("onlinecount", count);
        }

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // 用户退出了
        // User类型的对象从session域中删除了。
        ServletContext application = event.getSession().getServletContext();
        Integer onlinecount = (Integer) application.getAttribute("onlinecount");
        onlinecount--;
        application.setAttribute("onlinecount", onlinecount);
    }

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```





# 8 ThreadLocal

一个完整的业务流程，包含开启事务、成功提交、失败回滚。一般放在service里执行。

## 8.1JDBC事务控制

开启事务的前提是必须保证使用同一个connection链接对象

### 8.1.1事务相关操作

开启事务，即关闭事务的自动提交

```java
connection.setAutoCommit(false);
```

提交事务

```java
connection.commit();
```

回滚事务

```java
connection.rollBack();
```



### 8.1.2事务整体代码块

```java
try{
    
    // 关闭事务的自动提交
    connection.setAutoCommit(false);
    
    // 事务中包含的所有数据库操作
    
    // 提交事务
    connection.commit();
}catch(Excetion e){
    
    // 回滚事务
    connection.rollBack();
    
} finally {
    connection.setAutoCommit(true);
    //回收到连接池
    connection.close();
}
```



## 8.2ThreadLocal

### 8.2.1ThreadLocal实现原理

源码是线程和的connection的Map集合

|   Key    |   Value    |
| :------: | :--------: |
| 线程对象 | connection |



### 8.2.2修改连接池

```java
package com.atguigu.bookstore.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 这个工具类中会提供仨方法:
 * 1. 获取连接池对象
 * 2. 从连接池中获取连接
 * 3. 将链接归还到连接池
 */
public class JDBCUtil {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    static {
        try {
            //1. 使用类加载器读取配置文件，转成字节输入流
            InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            //2. 使用Properties对象加载字节输入流
            Properties properties = new Properties();
            properties.load(is);
            //3. 使用DruidDataSourceFactory创建连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接池对象
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {
        try {
            Connection conn = threadLocal.get();
            if (conn == null) {
                //说明此时ThreadLocal中没有连接
                //从连接池中获取一个连接
                conn = dataSource.getConnection();
                //将连接存储到ThreadLocal中
                threadLocal.set(conn);
            }
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 释放连接
     */
    public static void releaseConnection(){
        try {
            //这里是获取要被关闭的连接
            Connection conn = JDBCUtil.getConnection();
            //1. 先将conn的AutoCommit设置为true
            conn.setAutoCommit(true);
            //2. 将conn从ThreadLocal中移除掉
          	//关闭后要记得从map中移除，因为tomcat有线程池，不删除就会拿到重复的。
            threadLocal.remove();
            //3. 将conn归还到连接池
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
```



### 8.2.3添加拦截器

 保证所有数据库操作使用同一个连接，在通过拦截器对涉及事务的URL进行拦截开启事务

编写TransactionFilter来统一处理事务：

```java
package com.atguigu.bookstore.filter;

import com.atguigu.bookstore.utils.JDBCUtil;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class TransactionFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Connection conn = null;
        try {
            //开启事务
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            chain.doFilter(req, resp);
            //没有出现异常，则提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常,回滚事务
            try {
                conn.rollback();
                //
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
```

配置拦截器：

```xml
<filter>
    <filter-name>TransactionFilter</filter-name>
    <filter-class>com.atguigu.filter.TransactionFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>TransactionFilter</filter-name>
   	<!--哪些请求要使用TransactionFilter做事务控制，这里就配置哪些请求的地址-->
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

注意：涉及到业务的类要去除释放数据库连接的操作，转移到拦截器中，并且捕获的异常要再次抛出来
