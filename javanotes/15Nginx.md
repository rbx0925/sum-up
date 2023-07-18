#  1 Nginx简介

## 1.1Nginx概述

### 1.1.1Nginx介绍

Nginx 是开源、高性能、高可靠的 Web 和反向代理服务器，而且支持热部署，几乎可以做到 7 * 24 小时不间断运行，即使运行几个月也不需要重新启动，还能在不间断服务的情况下对软件版本进行热更新。性能是 Nginx 最重要的考量，其占用内存少、并发能力强、能支持高达 5w 个并发连接数，最重要的是， Nginx 是免费的并可以商业化，配置使用也比较简单。

Nginx ("engine x") 是一个高性能的HTTP和反向代理服务器,特点是占有内存少，并发能力强。

Nginx可以作为静态页面的web服务器，同时还支持CGI协议的动态语言，比如perl、php等。但是不支持java。Java程序只能通过与tomcat配合完成。



### 1.1.2Nginx 的特点

- 高并发、高性能；
- 模块化架构使得它的扩展性非常好；
- 异步非阻塞的事件驱动模型这点和 Node.js 相似；
- 相对于其它服务器来说它可以连续几个月甚至更长而不需要重启服务器使得它具有高可靠性；
- 热部署、平滑升级；
- 完全开源，生态繁荣。



### 1.1.3Nginx作用

Nginx 的最重要的几个使用场景：

1. 静态资源服务，通过本地文件系统提供服务；
2. 反向代理服务，延伸出包括缓存、负载均衡等；
3. API 服务， OpenResty。

对于前端来说 Node.js 并不陌生， Nginx 和 Node.js 的很多理念类似， HTTP 服务器、事件驱动、异步非阻塞等，且 Nginx 的大部分功能使用 Node.js 也可以实现，但 Nginx 和 Node.js 并不冲突，都有自己擅长的领域。Nginx 擅长于底层服务器端资源的处理（静态资源处理转发、反向代理，负载均衡等）， Node.js 更擅长上层具体业务逻辑的处理，两者可以完美组合。



## 1.2Nginx主要业务

### 1.2.3正向代理

Nginx不仅可以做反向代理，实现负载均衡。还能用作正向代理来进行上网等功能。

**正向代理：**如果把局域网外的Internet想象成一个巨大的资源库，则局域网中的客户端要访问Internet，则需要通过代理服务器来访问，这种代理服务就称为正向代理。



### 1.2.2反向代理

**反向代理：**其实客户端对代理是无感知的，因为客户端不需要任何配置就可以访问，我们只需要将请求发送到反向代理服务器，由反向代理服务器去选择目标服务器获取数据后，在返回给客户端，此时反向代理服务器和目标服务器对外就是一个服务器，暴露的是代理服务器地址，隐藏了真实服务器IP地址。



### 1.2.3负载均衡

增加服务器的数量，然后将请求分发到各个服务器上，将原先请求集中到单个服务器上的情况改为将请求分发到多个服务器上，将负载分发到不同的服务器，这就是负载均衡。



### 1.2.4动静分离

为了加快网站的解析速度，可以把动态页面和静态页面由不同的服务器来解析，加快解析速度。降低原来单个服务器的压力。





# 2 Nginx安装

## 2.1Yum安装

注意：Yum安装的Nginx，配置文件不在/user/local/nginx目录中

```shell
yum install nginx -y
```

安装完成后，通过 rpm -ql nginx 命令查看 Nginx 的安装信息：

```shell
# Nginx配置文件
/etc/nginx/nginx.conf # nginx 主配置文件
/etc/nginx/nginx.conf.default

# 可执行程序文件
/usr/bin/nginx-upgrade
/usr/sbin/nginx

# nginx库文件
/usr/lib/systemd/system/nginx.service # 用于配置系统守护进程
/usr/lib64/nginx/modules # Nginx模块目录

# 帮助文档
/usr/share/doc/nginx-1.16.1
/usr/share/doc/nginx-1.16.1/CHANGES
/usr/share/doc/nginx-1.16.1/README
/usr/share/doc/nginx-1.16.1/README.dynamic
/usr/share/doc/nginx-1.16.1/UPGRADE-NOTES-1.6-to-1.10

# 静态资源目录
/usr/share/nginx/html/404.html
/usr/share/nginx/html/50x.html
/usr/share/nginx/html/index.html

# 存放Nginx日志文件
/var/log/nginx
```

主要关注的文件夹有两个：

1. /etc/nginx/conf.d/ 是子配置项存放处， /etc/nginx/nginx.conf 主配置文件会默认把这个文件夹中所有子配置项都引入；
2. /usr/share/nginx/html/ 静态文件都放在这个文件夹，也可以根据你自己的习惯放在其他地方。



## 2.2在线安装

### 2.2.1安装pcre

第一步，联网下载pcre

```shell
wget http://downloads.sourceforge.net/project/pcre/pcre/8.37/pcre-8.37.tar.gz
```

第二步，解压文件

```shell
tar -zxvf pcre-8.37.tar.gz
```

第三步，在文件内执行 ./configure完成后，回到pcre目录下执行make，最后执行make install

```shell
 ./configure
```

```shell
make install
```

第四步，检查版本

```shell
pcre-config --version  
```



### 2.2.2安装其他依赖

安装openssl 、zlib 、 gcc 依赖

```shell
yum -y install make zlib zlib-devel gcc-c++ libtool  openssl openssl-devel
```





### 2.3.3安装nginx

第一步，使用命令解压 nginx安装包

第二步，配置编译安装

```shell
./configure
```

```shell
make && make install
```

第三步，进入目录启动服务

```shell
 cd /usr/local/nginx/sbin
```

```shell
./nginx
```



### 2.3.4关闭防火墙，访问nginx

在windows系统中访问linux中nginx，默认不能访问的，因为防火墙问题，关闭防火墙-开放访问的端口号，**80**端口

查看开放的端口号

```shell
firewall-cmd --list-all
```

设置开放的服务或端口号

```shell
firewall-cmd --add-service=http --permanent
```

```shell
firewall-cmd --add-port=80/tcp --permanent
```

重启防火墙

```shell
firewall-cmd --reload
```



## 2.3离线安装

需要的安装包：

从官网下载安装包：http://nginx.org/   nginx-1.12.2.tar.gz

需要的其他依赖：

- pcre-8.37.tar.gz 
- openssl-1.0.1t.tar.gz
- zlib-1.2.8.tar.gz

### 2.3.1安装gcc++和c++

首先查看gcc++版本，gcc -v，若未安装则需要先安装gcc++

```shell
执行  cd  /run/media/root/CentOS 7 x86_64/Packages（已经从中拷贝出来了26个安装包，直接使用准备好的文件夹rpmgcc安装即可）
rpm -Uvh *.rpm --nodeps –force
或者通过yum安装
yum -y install gcc
yum -y install gcc-c++
检查安装后版本
gcc -v
g++ -v
```

c++也是如此



### 2.3.2安装Nginx

第一步 安装openssl

1. 解压缩openssl-xx.tar.gz包
2. 进入解压缩目录，执行./config ，检测依赖
3. make && make install

第二步 安装zlib

1. 解压缩zlib-xx.tar.gz包
2. 进入解压缩目录，执行./configure
3. make && make install

第三步 安装nginx

1. 解压缩nginx-xx.tar.gz包 
2. 进入解压缩目录，执行./configure 
3. make && make install

第四步 关闭防火墙





# 3 Nginx常用的命令

## 3.1systemctl 系统命令

```shell
# 开机配置
systemctl enable nginx # 开机自动启动
systemctl disable nginx # 关闭开机自动启动

# 启动Nginx
systemctl start nginx # 启动Nginx成功后，可以直接访问主机IP，此时会展示Nginx默认页面

# 停止Nginx
systemctl stop nginx

# 重启Nginx
systemctl restart nginx

# 重新加载Nginx
systemctl reload nginx

# 查看 Nginx 运行状态
systemctl status nginx

# 查看Nginx进程
ps -ef | grep nginx

# 杀死Nginx进程
kill -9 pid # 根据上面查看到的Nginx进程号，杀死Nginx进程，-9 表示强制结束进程
```



## 3.2Nginx 应用程序命令

```shell

nginx -s reload  # 向主进程发送信号，重新加载配置文件，热重启
nginx -s reopen   # 重启 Nginx
nginx -s stop    # 快速关闭
nginx -s quit    # 等待工作进程处理完成后关闭
nginx -T         # 查看当前 Nginx 最终的配置
nginx -t         # 检查配置是否有问
nginx -c /etc/nginx/nginx.conf  #指定配置文件⚠️
```





# 4 配置文件

nginx.conf 配置文件

## 4.1配置文件层级结构

- main
  - events
  - http
    - server
      - location
      - location
      - location



## 4.2main段参数

从配置文件开始到 events 块之间的内容，主要会设置一些影响nginx 服务器整体运行的配置指令，主要包括配置运行 Nginx 服务器的用户（组）、允许生成的 worker process 数，进程 PID 存放路径、日志存放路径和类型以及配置文件的引入等。

worker_processes_number：指定 Nginx 启动的 worker 子进程数量，推荐与当前CPU一致。⚠️

```shell
worker_processes 4; # 指定具体子进程数量
worker_processes auto; # 与当前cpu物理核心数一致
```

user：指定运行 Nginx 的 woker 子进程的属主和属组，其中组可以不指定。⚠️

```shell
user USERNAME [GROUP]
user nginx lion; # 用户是nginx;组是lion
```

pid：指定运行 Nginx master 主进程的 pid 文件存放路径。

```shell
pid /opt/nginx/logs/nginx.pid # master主进程的的pid存放在nginx.pid的文件
```

worker_rlimit_nofile_number：指定 worker 子进程可以打开的最大文件句柄数。

```shell
worker_rlimit_nofile 20480; # 可以理解成每个worker子进程的最大连接数量。
```

worker_rlimit_core：指定 worker 子进程异常终止后的 core 文件，用于记录分析问题。

```shell
worker_rlimit_core 50M; # 存放大小限制
working_directory /opt/nginx/tmp; # 存放目录
```

worker_cpu_affinity：将每个 worker 子进程与我们的 cpu 物理核心绑定。

将每个 worker 子进程与特定 CPU 物理核心绑定，优势在于，避免同一个 worker 子进程在不同的 CPU 核心上切换，缓存失效，降低性能。但其并不能真正的避免进程切换。

```shell
worker_cpu_affinity：cpu1
```

worker_priority：指定 worker 子进程的 nice 值，以调整运行 Nginx 的优先级，通常设定为负值，以优先调用 Nginx 。

```shell
worker_priority -10; # 120-10=110，110就是最终的优先级
```

Linux 默认进程的优先级值是120，值越小越优先；nice 定范围为 -20 到 +19 。（应用的默认优先级值是120加上 nice 值等于它最终的值，这个值越小，优先级越高。）

worker_shutdown_timeout：指定 worker 子进程优雅退出时的超时时间。

```shell
worker_shutdown_timeout 5s;
```

timer_resolution：worker 子进程内部使用的计时器精度，调整时间间隔越大，系统调用越少，有利于性能提升；反之，系统调用越多，性能下降。

```shell
timer_resolution 100ms;
```

在 Linux 系统中，用户需要获取计时器时需要向操作系统内核发送请求，有请求就必然会有开销，因此这个间隔越大开销就越小。

daemon：指定 Nginx 的运行方式，前台还是后台，前台用于调试，后台用于生产。

```shell
daemon off; # 默认是on，后台运行模式
```



## 4.3events段参数

events 块涉及的指令主要影响 Nginx 服务器与用户的网络连接，常用的设置包括是否开启对多 work process 下的网络连接进行序列化，是否允许同时接收多个网络连接，选取哪种事件驱动模型来处理连接请求，每个 work process 可以同时支持的最大连接数等。

worker_connections：worker 子进程能够处理的最大并发连接数。⚠️

```shell
worker_connections 1024 # 每个子进程的最大连接数为1024
```

use：Nginx 使用何种事件驱动模型。⚠️

```shell
use method; # 不推荐配置它，让nginx自己选择
#method 可选值为：select、poll、kqueue、epoll、/dev/poll、eventport
```

accept_mutex：是否打开负载均衡互斥锁。

```shell
accept_mutex on # 默认是off关闭的，这里推荐打开
```



## 4.4http段参数

　http全局块配置的指令包括文件引入、MIME-TYPE 定义、日志自定义、连接超时时间、单链接请求数上限等。



### 4.4.1server段参数

这块和虚拟主机有密切关系，虚拟主机从用户角度看，和一台独立的硬件主机是完全一样的，该技术的产生是为了节省互联网服务器硬件成本。⚠️

- 每个 http 块可以包括多个 server 块，而每个 server 块就相当于一个虚拟主机。
- 而每个 server 块也分为全局 server 块，以及可以同时包含多个 location 块。

指定虚拟主机域名。

```shell
# 格式：
server_name name1 name2 name3

# 示例：
server_name www.nginx.com;
```

域名匹配的四种写法：

- 精确匹配：server_name www.nginx.com ;
- 左侧通配：server_name *.nginx.com ;
- 右侧统配：server_name www.nginx.* ;
- 正则匹配：server_name ~^www\.nginx\.*$ ;

匹配优先级：**精确匹配** > **左侧通配符匹配** > **右侧通配符匹配** > **正则表达式匹配**。

server_name 配置实例：

```shell
vim /etc/nginx/nginx.conf
```

```sh

# 这里只列举了http端中的sever端配置

# 左匹配
server {
  listen  80;
  server_name  *.nginx-test.com;
  root  /usr/share/nginx/html/nginx-test/left-match/;
  location / {
    index index.html;
  }
}

# 正则匹配
server {
  listen  80;
  server_name  ~^.*\.nginx-test\..*$;
  root  /usr/share/nginx/html/nginx-test/reg-match/;
  location / {
    index index.html;
  }
}

# 右匹配
server {
  listen  80;
  server_name  www.nginx-test.*;
  root  /usr/share/nginx/html/nginx-test/right-match/;
  location / {
    index index.html;
  }
}

# 完全匹配
server {
  listen  80;
  server_name  www.nginx-test.com;
  root  /usr/share/nginx/html/nginx-test/all-match/;
  location / {
    index index.html;
  }
}
```

访问分析：

- 当访问 www.nginx-test.com 时，都可以被匹配上，因此选择优先级最高的“完全匹配”；
- 当访问 mail.nginx-test.com 时，会进行“左匹配”；
- 当访问 www.nginx-test.org 时，会进行“右匹配”；
- 当访问 doc.nginx-test.com 时，会进行“左匹配”；
- 当访问 www.nginx-test.cn 时，会进行“右匹配”；
- 当访问 fe.nginx-test.club 时，会进行“正则匹配”。



#### 4.4.1.1location段参数

root：指定静态资源目录位置，它可以写在 http 、 server 、 location 等配置中。⚠️

[注意] root 会将定义路径与 URI 叠加， alias 则只取定义路径。

```shell
root path

例如：
location /image {
  root /opt/nginx/static;
}

当用户访问 www.test.com/image/1.png 时，实际在服务器找的路径是 /opt/nginx/static/image/1.png
```

alias：它也是指定静态资源目录位置，它只能写在 location 中。⚠️

[注意] 使用 alias 末尾一定要添加 / ，并且它只能位于 location 中。

```shell
location /image {
  alias /opt/nginx/static/image/;
}

当用户访问 www.test.com/image/1.png 时，实际在服务器找的路径是 /opt/nginx/static/image/1.png
```

location：配置路径。⚠️

匹配规则：

- **=** 精确匹配；
- **~** 正则匹配，区分大小写；
- **~\*** 正则匹配，不区分大小写；
- **^~** 匹配到即停止搜索；

```shell
location [ = | ~ | ~* | ^~ ] uri {
  ...
}
```

匹配优先级：= 、 ^~ 、~ 、~* 、不带任何字符。

实例：

```shell
server {
  listen  80;
  server_name  www.nginx-test.com;

  # 只有当访问 www.nginx-test.com/match_all/ 时才会匹配到/usr/share/nginx/html/match_all/index.html
  location = /match_all/ {
      root  /usr/share/nginx/html
      index index.html
  }

  # 当访问 www.nginx-test.com/1.jpg 等路径时会去 /usr/share/nginx/images/1.jpg 找对应的资源
  location ~ \.(jpeg|jpg|png|svg)$ {
    root /usr/share/nginx/images;
  }

  # 当访问 www.nginx-test.com/bbs/ 时会匹配上 /usr/share/nginx/html/bbs/index.html
  location ^~ /bbs/ {
    root /usr/share/nginx/html;
    index index.html index.htm;
  }
}
```

location 中的反斜线

- 不带 / 当访问 www.nginx-test.com/test 时， Nginx 先找是否有 test 目录，如果有则找 test 目录下的 index.html ；如果没有 test 目录， nginx 则会找是否有 test 文件；
- 带 / 当访问 www.nginx-test.com/test 时， Nginx 先找是否有 test 目录，如果有则找 test 目录下的 index.html ，如果没有它也不会去找是否存在 test 文件。

```shell
location /test {
  ...
}

location /test/ {
  ...
}
```

return

停止处理请求，直接返回响应码或重定向到其他 URL ；执行 return 指令后， location 中后续指令将不会被执行。

```shell
return code [text];
return code URL;
return URL;

例如：
location / {
  return 404; # 直接返回状态码
}

location / {
  return 404 "pages not found"; # 返回状态码 + 一段文本
}

location / {
  return 302 /bbs ; # 返回状态码 + 重定向地址
}

location / {
  return https://www.baidu.com ; # 返回重定向地址
}
```

rewrite

根据指定正则表达式匹配规则，重写 URL 。

```shell
语法：rewrite 正则表达式 要替换的内容 [flag];

上下文：server、location、if

示例：rewirte /images/(.*\.jpg)$ /pic/$1; # $1是前面括号(.*\.jpg)的反向引用
```

flag 可选值的含义：

- last 重写后的 URL 发起新请求，再次进入 server 段，重试 location 的中的匹配；
- break 直接使用重写后的 URL ，不再匹配其它 location 中语句；
- redirect 返回 302 临时重定向；
- permanent 返回 301 永久重定向。

```shell
server{
  listen 80;
  server_name fe.lion.club; # 要在本地hosts文件进行配置
  root html;
  location /search {
    rewrite ^/(.*) https://www.baidu.com redirect;
  }

  location /images {
    rewrite /images/(.*) /pics/$1;
  }

  location /pics {
    rewrite /pics/(.*) /photos/$1;
  }

  location /photos {

  }
}
```

按照这个配置我们来分析：

- 当访问 fe.lion.club/search 时，会自动帮我们重定向到 https://www.baidu.com；
- 当访问 fe.lion.club/images/1.jpg 时，第一步重写 URL 为 fe.lion.club/pics/1.jpg ，找到 pics 的 location ，继续重写 URL 为 fe.lion.club/photos/1.jpg ，找到 /photos 的 location 后，去 html/photos 目录下寻找 1.jpg 静态资源。

 if 指令

```shell

语法：if (condition) {...}

上下文：server、location

示例：
if($http_user_agent ~ Chrome){
  rewrite /(.*)/browser/$1 break;
}
```

condition 判断条件

- **$variable** 仅为变量时，值为空或以0开头字符串都会被当做 false 处理；
- **=** 或 **!=** 相等或不等；
- **~** 正则匹配；
- **! ~** 非正则匹配；
- **~\*** 正则匹配，不区分大小写；
- **-f** 或 **! -f** 检测文件存在或不存在；
- **-d** 或 **! -d** 检测目录存在或不存在；
- **-e** 或 **! -e** 检测文件、目录、符号链接等存在或不存在；
- **-x** 或 **! -x** 检测文件可以执行或不可执行；

实例：

当访问 localhost:8080/images/ 时，会进入 if 判断里面执行 rewrite 命令。

```shell
$variable 仅为变量时，值为空或以0开头字符串都会被当做 false 处理；
= 或 != 相等或不等；
~ 正则匹配；
! ~ 非正则匹配；
~* 正则匹配，不区分大小写；
-f 或 ! -f 检测文件存在或不存在；
-d 或 ! -d 检测目录存在或不存在；
-e 或 ! -e 检测文件、目录、符号链接等存在或不存在；
-x 或 ! -x 检测文件可以执行或不可执行；
```

autoindex

用户请求以 / 结尾时，列出目录结构，可以用于快速搭建静态资源下载网站。

autoindex.conf 配置信息：

当访问 fe.lion.com/download/ 时，会把服务器 /opt/source/download/ 路径下的文件展示出来

```shell

server {
  listen 80;
  server_name fe.lion-test.club;

  location /download/ {
    root /opt/source;

    autoindex on; # 打开 autoindex，，可选参数有 on | off
    autoindex_exact_size on; # 修改为off，以KB、MB、GB显示文件大小，默认为on，以bytes显示出⽂件的确切⼤⼩
    autoindex_format html; # 以html的方式进行格式化，可选参数有 html | json | xml
    autoindex_localtime off; # 显示的⽂件时间为⽂件的服务器时间。默认为off，显示的⽂件时间为GMT时间
  }
}
```



## 4.5变量

Nginx 提供给使用者的变量非常多，但是终究是一个完整的请求过程所产生数据， Nginx 将这些数据以变量的形式提供给使用者。

下面列举些项目中常用的变量：

| 变量名称                    | 描述                                                         |
| --------------------------- | ------------------------------------------------------------ |
| $arg_name                   | 请求行中的参数name                                           |
| $args                       | 请求行中的参数                                               |
| $binary_remote_addr         | 二进制形式的客户端地址                                       |
| $body_bytes_sent            | 发送到客户端的字节数，不包含响应标头                         |
| $bytes_sent                 | 发送到客户端的总字节数                                       |
| $connection                 | 连接序列号                                                   |
| $connection_requests        | 当前通过连接发出的请求数量                                   |
| $connection_time            | 连接时间（以秒为单位，毫秒级分辨率 ）（1.19.10）             |
| $content_length             | "内容长度"请求标头字段                                       |
| $content_type               | "内容类型"请求标头字段                                       |
| $cookie_name                | cookie名称                                                   |
| $document_root              | 当前请求的root或alias指令的值                                |
| $document_uri               | 同$uri                                                       |
| $host                       | 按以下优先顺序排列：请求行中的主机名，或"Host"请求标头字段中的主机名，或与请求匹配的服务器名称 |
| $hostname                   | 主机名                                                       |
| $http_name                  | 任意请求标头字段;变量名称的最后一部分是转换为小写的字段名称，短划线由下划线替换 |
| $https                      | ""（如果连接在 SSL 模式下运行），否则为空字符串on            |
| $is_args                    | ""（如果请求行有参数），否则为空字符串?                      |
| $limit_rate                 | 设置此变量可启用响应速率限制;请参见[limit_rate](https://links.jianshu.com/go?to=http%3A%2F%2Fnginx.org%2Fen%2Fdocs%2Fhttp%2Fngx_http_core_module.html%23limit_rate) |
| $msec                       | 日志写入时间，单位为秒，精度是毫秒                           |
| $nginx_version              | nginx版本                                                    |
| $pid                        | 进程ID                                                       |
| $pipe                       | 如果请求是通过http流水线发送，则其值为"p"，否则为“."         |
| $proxy_protocol_addr        | 代理服务器的客户端地址，如果是直接访问，该值为空字符串       |
| $proxy_protocol_port        | 代理服务器的客户端端口                                       |
| $proxy_protocol_server_addr | 代理协议标头中的服务器地址                                   |
| $proxy_protocol_server_port | 代理协议标头中的服务器端口                                   |
| $query_string               | 同$args                                                      |
| $realpath_root              | 当前请求的文档根目录或别名的真实路径，会将所有符号连接转换为真实路径 |
| $remote_addr                | 客户端地址                                                   |
| $remote_port                | 客户端端口                                                   |
| $remote_user                | 基本身份验证随附的用户名                                     |
| $request                    | 完整的原始请求行                                             |
| $request_body               | 客户端的请求body体                                           |
| $request_body_file          | 将客户端请求body体保存在临时文件中。文件处理结束后，此文件需删除。 |
| $request_completion         | 如果请求成功，值为"OK"，如果请求未完成或者请求不是一个范围请求的最后一部分，则为空 |
| $request_filename           | 当前连接请求的文件路径，由root或alias指令与URI请求生成       |
| $request_id                 | 从 16 个十六进制随机字节生成的唯一请求标识符                 |
| $request_length             | 请求长度（包括请求行、标头和请求正文）                       |
| $request_method             | HTTP请求方法，通常为"GET"或"POST"                            |
| $request_time               | 处理客户端请求使用的时间,单位为秒，精度毫秒； 从读入客户端的第一个字节开始，直到把最后一个字符发送给客户端后进行日志写入为止。 |
| $request_uri                | 客户端请求参数的原始URI，它无法修改，请查看$uri更改或重写URI，不包含主机名， |
| $scheme                     | 请求使用的Web协议，"http" 或 "https"                         |
| $sent_http_name             | 任意响应标头字段;变量名称的最后一部分是转换为小写的字段名称，短划线由下划线替换 |
| $sent_trailer_name          | 在响应末尾发送的任意字段 （1.13.2）;变量名称的最后一部分是转换为小写的字段名称，短划线由下划线替换 |
| $server_addr                | 接受请求的服务器的地址                                       |
| $server_name                | 接受请求的服务器的名称                                       |
| $server_port                | 接受请求的服务器的端口                                       |
| $server_protocol            | 服务器的HTTP版本，通常为 "HTTP/1.0" 或 "HTTP/1.1"            |
| $status                     | 响应码                                                       |
| $time_iso8601               | ISO 8601 标准格式的本地时间 ,eg: "2017-05-24T18:31:27+08:00" |
| $time_local                 | 通用日志格式中的本地时间 .eg: "24/May/2017:18:31:27 +0800"   |
| $uri                        | 请求中的当前 URI                                             |
| $http_host                  | 请求地址，即浏览器中你输入的地址（IP或域名）                 |
| $http_referer               | url跳转来源,用来记录从那个页面链接访问过来的                 |
| $http_user_agent            | 用户终端浏览器等信息                                         |
| $http_x_forwarded_for       | 代理服务器的地址                                             |
| $upstream_addr              | 请求的上游服务器                                             |
| $upstream_connect_time      | 上游服务握手时间                                             |
| $upstream_header_time       | 接收上游header所花费的时间                                   |
| $upstream_response_time     | 接收上游body体所花费的时间                                   |
| $upstream_response_length   | 上游响应长度                                                 |
| $upstream_cache_status      | 缓存命中/未命中(如果适用)                                    |

实例演示 var.conf ：

```sh
server{
  listen 8081;
  server_name var.lion-test.club;
  root /usr/share/nginx/html;
  location / {
    return 200 "
remote_addr: $remote_addr
remote_port: $remote_port
server_addr: $server_addr
server_port: $server_port
server_protocol: $server_protocol
binary_remote_addr: $binary_remote_addr
connection: $connection
uri: $uri
request_uri: $request_uri
scheme: $scheme
request_method: $request_method
request_length: $request_length
args: $args
arg_pid: $arg_pid
is_args: $is_args
query_string: $query_string
host: $host
http_user_agent: $http_user_agent
http_referer: $http_referer
http_via: $http_via
request_time: $request_time
https: $https
request_filename: $request_filename
document_root: $document_root
";
  }
}
```

当我们访问 http://var.lion-test.club:8081/test?pid=121414&cid=sadasd 时，由于 Nginx 中写了 return 方法，因此 chrome 浏览器会默认为我们下载一个文件，下面展示的就是下载的文件内容：

```sh
remote_addr: 27.16.220.84
remote_port: 56838
server_addr: 172.17.0.2
server_port: 8081
server_protocol: HTTP/1.1
binary_remote_addr: 茉
connection: 126
uri: /test/
request_uri: /test/?pid=121414&cid=sadasd
scheme: http
request_method: GET
request_length: 518
args: pid=121414&cid=sadasd
arg_pid: 121414
is_args: ?
query_string: pid=121414&cid=sadasd
host: var.lion-test.club
http_user_agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36
http_referer: 
http_via: 
request_time: 0.000
https: 
request_filename: /usr/share/nginx/html/test/
document_root: /usr/share/nginx/html
```

Nginx 的配置还有非常多，以上只是罗列了一些常用的配置，在实际项目中还是要学会查阅文档。





# 5 Nginx 应用核心概念

代理是在服务器和客户端之间假设的一层服务器，代理将接收客户端的请求并将它转发给服务器，然后将服务端的响应转发给客户端。

不管是正向代理还是反向代理，实现的都是上面的功能。

## 5.1 正向代理

正向代理，意思是一个位于客户端和原始服务器（origin server）之间的服务器，为了从原始服务器取得内容，客户端向代理发送一个请求并指定目标（原始服务器），然后代理向原始服务器转交请求并将获得的内容返回给客户端。

正向代理是为我们服务的，即为客户端服务的，客户端可以根据正向代理访问到它本身无法访问到的服务器资源。

正向代理对我们是透明的，对服务端是非透明的，即服务端并不知道自己收到的是来自代理的访问还是来自真实客户端的访问。

 

## 5.2 反向代理

反向代理（Reverse Proxy）方式是指以代理服务器来接受 Internet 上的连接请求，然后将请求转发给内部网络上的服务器，并将从服务器上得到的结果返回给 Internet 上请求连接的客户端，此时代理服务器对外就表现为一个反向代理服务器。

反向代理是为服务端服务的，反向代理可以帮助服务器接收来自客户端的请求，帮助服务器做请求转发，负载均衡等。

反向代理对服务端是透明的，对我们是非透明的，即我们并不知道自己访问的是代理服务器，而服务器知道反向代理在为他服务。

反向代理的优势：

- 隐藏真实服务器；
- 负载均衡便于横向扩充后端动态服务；
- 动静分离，提升系统健壮性。

那么“动静分离”是什么？负载均衡又是什么？



## 5.3 动静分离

动静分离是指在 Web 服务器架构中，将静态页面与动态页面或者静态内容接口和动态内容接口分开不同系统访问的架构设计方法，进而提示整个服务的访问性和可维护性。

一般来说，都需要将动态资源和静态资源分开，由于 Nginx 的高并发和静态资源缓存等特性，经常将静态资源部署在 Nginx 上。如果请求的是静态资源，直接到静态资源目录获取资源，如果是动态资源的请求，则利用反向代理的原理，把请求转发给对应后台应用去处理，从而实现动静分离。

使用前后端分离后，可以很大程度提升静态资源的访问速度，即使动态服务不可用，静态资源的访问也不会受到影响。



## 5.4 负载均衡

一般情况下，客户端发送多个请求到服务器，服务器处理请求，其中一部分可能要操作一些资源比如数据库、静态资源等，服务器处理完毕后，再将结果返回给客户端。

这种模式对于早期的系统来说，功能要求不复杂，且并发请求相对较少的情况下还能胜任，成本也低。随着信息数量不断增长，访问量和数据量飞速增长，以及系统业务复杂度持续增加，这种做法已无法满足要求，并发量特别大时，服务器容易崩。

很明显这是由于服务器性能的瓶颈造成的问题，除了堆机器之外，最重要的做法就是负载均衡。

请求爆发式增长的情况下，单个机器性能再强劲也无法满足要求了，这个时候集群的概念产生了，单个服务器解决不了的问题，可以使用多个服务器，然后将请求分发到各个服务器上，将负载分发到不同的服务器，这就是负载均衡，核心是「分摊压力」。Nginx 实现负载均衡，一般来说指的是将请求转发给服务器集群。

举个具体的例子，晚高峰乘坐地铁的时候，入站口经常会有地铁工作人员大喇叭“请走 B 口， B 口人少车空....”，这个工作人员的作用就是负载均衡。

Nginx 实现负载均衡的策略：

- **轮询策略**：默认情况下采用的策略，将所有客户端请求轮询分配给服务端。这种策略是可以正常工作的，但是如果其中某一台服务器压力太大，出现延迟，会影响所有分配在这台服务器下的用户；
- **最小连接数策略**：将请求优先分配给压力较小的服务器，它可以平衡每个队列的长度，并避免向压力大的服务器添加更多的请求；
- **最快响应时间策略**：优先分配给响应时间最短的服务器；
- **客户端 IP 绑定策略**：来自同一个 IP 的请求永远只分配一台服务器，有效解决了动态网页存在的 session 共享问题。





# 6  Nginx 实战配置

在配置反向代理和负载均衡等等功能之前，有两个核心模块是我们必须要掌握的，这两个模块应该说是 Nginx 应用配置中的核心，它们分别是：upstream 、proxy_pass 。

## 6.1 upstream

用于定义上游服务器（指的就是后台提供的应用服务器）的相关信息。

[用户] <=> [Nginx] <=> [upstream模块:上游服务器1、上游服务器2......] <=> [数据库]

### 6.1.1语法格式

语法：

```sh
语法：upstream name {
  ...
}

上下文：http

示例：
upstream back_end_server{
  server 192.168.100.33:8081
}
```



### 6.1.2常用指令★★★

在 upstream 内可使用的指令：

- server 定义上游服务器地址；
- zone 定义共享内存，用于跨 worker 子进程；
- keepalive 对上游服务启用长连接；
- keepalive_requests 一个长连接最多请求 HTTP 的个数；
- keepalive_timeout 空闲情形下，一个长连接的超时时长；
- hash 哈希负载均衡算法；
- ip_hash 依据 IP 进行哈希计算的负载均衡算法；
- least_conn 最少连接数负载均衡算法；
- least_time 最短响应时间负载均衡算法；
- random 随机负载均衡算法。



### 6.1.3server指令★

server定义上游服务器地址

```sh
语法：server address [parameters]
上下文：upstream
```

parameters 可选值：

- weight=number 权重值，默认为1；
- max_conns=number 上游服务器的最大并发连接数；
- fail_timeout=time 服务器不可用的判定时间；
- max_fails=numer 服务器不可用的检查次数；
- backup 备份服务器，仅当其他服务器都不可用时才会启用；
- down 标记服务器长期不可用，离线维护。



### 6.1.4keepalive指令

限制每个 worker 子进程与上游服务器空闲长连接的最大数量。

```sh
keepalive connections;

上下文：upstream

示例：keepalive 16;
```



### 6.1.5keepalive_requests指令

单个长连接可以处理的最多 HTTP 请求个数。

```sh
语法：keepalive_requests number;

默认值：keepalive_requests 100;

上下文：upstream
```



### 6.1.6keepalive_timeout指令

空闲长连接的最长保持时间。

```sh
语法：keepalive_timeout time;

默认值：keepalive_timeout 60s;

上下文：upstream
```



### 6.1.7ip_hash★

每个请求按访问ip的hash结果分配，这样每个访客固定访问一个后端服务器，可以解决session的问题。 

```sh
upstream server_pool{   
    ip_hash; 
    server 192.168.5.21:80;    
    server 192.168.5.22:80;    
}
```



### 6.1.8fair★

借助第三方插件

按后端服务器的响应时间来分配请求，响应时间短的优先分配。

```shell
upstream server_pool{   
    server 192.168.5.21:80;    
    server 192.168.5.22:80;    
    fair;    
}
```



## 6.2 proxy_pass

用于配置代理服务器。

### 6.2.1语法格式

```
语法：proxy_pass URL;

上下文：location、if、limit_except

示例：
proxy_pass http://127.0.0.1:8081
proxy_pass http://127.0.0.1:8081/proxy
```



### 6.2.2URL 参数原则

#### 6.2.2.1参数原则

1. URL 必须以 http 或 https 开头；
2. URL 中可以携带变量；
3. URL 中是否带 URI ，会直接影响发往上游请求的 URL。

接下来让我们来看看两种常见的 URL 用法：

1. proxy_pass http://192.168.100.33:8081
2. proxy_pass http://192.168.100.33:8081/

这两种用法的区别就是带 / 和不带 / ，在配置代理时它们的区别可大了：

- 不带 / 意味着 Nginx 不会修改用户 URL ，而是直接透传给上游的应用服务器；
- 带 / 意味着 Nginx 会修改用户 URL ，修改方法是将 location 后的 URL 从用户 URL 中删除。



#### 6.2.2.2不带 / 的用法

```
location /bbs/{
  proxy_pass http://127.0.0.1:8080;
}
```

分析：

1. 用户请求 URL ：/bbs/abc/test.html
2. 请求到达 Nginx 的 URL ：/bbs/abc/test.html
3. 请求到达上游应用服务器的 URL ：/bbs/abc/test.html



#### 6.2.2.3带 / 的用法

```
location /bbs/{
  proxy_pass http://127.0.0.1:8080/;
}
```

分析：

1. 用户请求 URL ：/bbs/abc/test.html
2. 请求到达 Nginx 的 URL ：/bbs/abc/test.html
3. 请求到达上游应用服务器的 URL ：/abc/test.html

并没有拼接上 /bbs ，这点和 root 与 alias 之间的区别是保持一致的。



# 7 配置反向代理

## 7.1搭建模拟环境

（1）准备两台tomcat服务器，一台8801，一台8802，需要修改关闭、启动和整合(新版本默认注释则不需要改)的端口号

（2）在两台tomcat里面webapps目录中，创建名称是dog文件夹，在dog文件夹中创建页面index.html(让index.html内容不一样，查看效果)，用于测试



## 7.2配置反向代理

配置http下的server段，同时配置了三个反向代理。⚠️

proxy_pass指定代理的URL，末尾加分号，某些情况下URL需要以/结束

并且上游服务器代理的是地址，不可以加路径，如/dog

此处配置刻意模拟了两张情况：

1. 代理上游服务器
2. 代理普通服务器

vim /etc/nginx/nginx.conf

```sh
    # 配置上游服务器    
    upstream testProxy {
      server 39.106.35.112:8801 weight=2 max_conns=1000 fail_timeout=10s max_fails=3;
      keepalive 32;
      keepalive_requests 80;
      keepalive_timeout 20s;
    }


    server {
        #访问39.106.35.112:80路径+location的设置，就会去访问代理服务器
        listen       80;
        server_name  39.106.35.112;
        #配置代理1      
        location /proxy1 {
          proxy_pass http://testProxy/dog;
        }
        # 配置代理2
        location /proxy2 {
          proxy_pass http://39.106.35.112:8802/dog;
        }
    }
```



## 7.3测试代理服务器

配置完成后重启 Nginx 服务器 nginx -s reload 。

测试代理：

- 测试代理1：http://39.106.35.112/proxy1/
- 测试代理2：http://39.106.35.112/proxy2/





# 8 负载均衡

## 8.1配置负载均衡

配置上游服务器位置：http里面，server外面，配置负载均衡主要是要使用 upstream 指令⚠️

这里配置的http://testProxy/，最后的/很关键，正因为加了根号，才可以正常访问/dog时响应其目录下的index.html⚠️⚠️⚠️

并且上游服务器代理的是地址，不可以加路径，如/dog

```sh
    # 配置上游服务器    
    upstream testProxy {
      server 39.106.35.112:8801;
      server 39.106.35.112:8802;
    }

    server {
        #访问39.106.35.112:80路径，就会去访问代理服务器
        listen       80;
        server_name  39.106.35.112;
        #代理上游服务器列表，实现负载均衡，默认轮询策略 
        location /proxy {
          proxy_pass http://testProxy/;
        }
    }
```



## 8.2测试负载均衡

配置完成后：

1. nginx -t 检测配置是否正确；
2. nginx -s reload 重启 Nginx 服务器；
3. 执行 ss -nlt 命令查看端口是否被占用，从而判断 Nginx 服务是否正确启动。

访问并多次刷新测试：http://39.106.35.112/proxy/dog/



## 8.3分配服务器策略

### 8.3.1轮询

负载均衡默认的分配策略⚠️

每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器down掉，能自动剔除。



### 8.3.2权重

weight代表权重，默认为1，权重越高被分配的客户端越多

指定轮询几率，weight和访问比率成正比，用于后端服务器性能不均的情况。 例如：

```sh
    # 配置上游服务器    
    upstream testProxy {
      server 39.106.35.112:8801 weight=1;
      server 39.106.35.112:8802 weight=2;
    }
```



### 8.3.3哈希

通过制定关键字作为 hash key ，基于 hash 算法映射到特定的上游服务器中。关键字可以包含有变量、字符串。例如：

```sh
    # 配置上游服务器    
    upstream testProxy {
      # hash $request_uri 表示使用 request_uri 变量作为 hash 的 key 值
      # 只要访问的 URI 保持不变，就会一直分发给同一台服务器。⚠️
      hash $request_uri;
      server 39.106.35.112:8801 weight=1;
      server 39.106.35.112:8802 weight=2;
    }
```



### 8.3.4IP哈希

每个请求按访问ip的hash结果分配，这样每个访客固定访问一个后端服务器，可以解决session的问题。 例如：

```sh
    # 配置上游服务器    
    upstream testProxy {
      ip_hash;
      server 39.106.35.112:8801 weight=1;
      server 39.106.35.112:8802 weight=2;
    }
```



### 8.3.5最少连接数算法

各个 worker 子进程通过读取共享内存的数据，来获取后端服务器的信息。来挑选一台当前已建立连接数最少的服务器进行分配请求。 例如：

```sh
    # 配置上游服务器    
    upstream testProxy {
    	# zone可以设置共享内存空间的名字和大小
      zone test 10M;
  		least_conn;
      server 39.106.35.112:8801 weight=1;
      server 39.106.35.112:8802 weight=2;
    }
```



### 8.3.6Fair

需要借助第三方插件，按后端服务器的响应时间来分配请求，响应时间短的优先分配。 例如：

```sh
    # 配置上游服务器    
    upstream testProxy {
      server 39.106.35.112:8801 weight=1;
      server 39.106.35.112:8802 weight=2;
      fair;
    }
```





# 9 配置动静分离

## 9.1动静分离策略

Nginx 动静分离简单来说就是把动态跟静态请求分开，不能理解成只是单纯的把动态页面和静态页面物理分离。严格意义上说应该是动态请求跟静态请求分开，可以理解成使用Nginx 处理静态页面，Tomcat处理动态页面。动静分离从目前实现角度来讲大致分为两种：

一种是纯粹把静态文件独立成单独的域名，放在独立的服务器上，也是目前主流推崇的方案；

另外一种方法就是动态跟静态文件混合在一起发布，通过 nginx 来分开。

通过 location 指定不同的后缀名实现不同的请求转发。

通过 expires 参数设置，可以使浏览器缓存过期时间，减少与服务器之前的请求和流量。

具体 Expires 定义：是给一个资源设定一个过期时间，也就是说无需去服务端验证，直接通过浏览器自身确认是否过期即可，所以不会产生额外的流量。

此种方法非常适合不经常变动的资源。（如果经常更新的文件，不建议使用 Expires 来缓存），我这里设置 3d，表示在这 3 天之内访问这个 URL，发送一个请求，比对服务器该文件最后更新时间没有变化，则不会从服务器抓取，返回状态码 304，如果有修改，则直接从服务器重新下载，返回状态码 200。



## 9.2配置静态资源

首先在Linux服务器/root/data/images目录下放些测试文件

在负载均衡和反向代理的基础上配置静态资源的访问

```sh
    # 配置上游服务器    
    upstream testProxy {
      server 39.106.35.112:8801;
      server 39.106.35.112:8802;
    }


    server {
        #访问39.106.35.112:80路径，就会去访问代理服务器
        listen       80;
        server_name  39.106.35.112;
        #代理上游服务器列表，实现负载均衡，默认轮询策略 
        location /proxy {
          proxy_pass http://testProxy/;
        }
        # 静态资源，表示访问39.106.35.112:80/images会去/root/data/images目录查找静态资源⚠️
        location /images {
          root /root/data/;
          #直接访问会以列表的形式展开
          autoindex on;
        }
    }
```

配置后访问http://39.106.35.112/images进行测试，会发现403 Forbidden，没有权限访问root中配置的目录

查看worker process的启动用户发现是nginx，需要换成root即可有权限访问/root下的内容

```shell
[root@centos ~]# ps -ef|grep nginx
nginx    11187 26573  0 18:10 ?        00:00:00 nginx: worker process
root     11605 23860  0 18:14 pts/0    00:00:00 grep --color=auto nginx
root     26573     1  0 15:54 ?        00:00:00 nginx: master process /usr/sbin/nginx
```

修改启动用户为root，修改配置文件顶部的user nginx，改为user root：

```sh
user root;
```



## 9.3测试静态资源

浏览器访问静态资源：http://39.106.35.112/images/

浏览器访问动态资源：http://39.106.35.112/proxydog/（会发现动态资源仍不受影响）

注意：因为设置了autoindex on，所以会自动以索引的形式展开





# 10 配置缓存

缓存可以非常有效的提升性能，因此不论是客户端（浏览器），还是代理服务器（ Nginx ），乃至上游服务器都多少会涉及到缓存。可见缓存在每个环节都是非常重要的。下面让我们来学习 Nginx 中如何设置缓存及策略。

## 10.1配置缓存

配置缓存后，我们可以在 /etc/nginx/cache_temp 路径下找到相应的缓存文件⚠️

```sh
    # 配置缓存目录等⚠️
    proxy_cache_path /etc/nginx/cache_temp levels=2:2 keys_zone=cache_zone:30m max_size=2g inactive=60m use_temp_path=off;


    # 配置上游服务器    
    upstream testProxy {
      server 39.106.35.112:8801;
      server 39.106.35.112:8802;
    }


    server {
        # 访问39.106.35.112:80路径，就会去访问代理服务器
        listen       80;
        server_name  39.106.35.112;
        # 缓存的详细设置⚠️
        location /proxy {
          # 设置缓存内存，上面配置中已经定义好的
          proxy_cache cache_zone;
          # 缓存状态为200的请求，缓存时长为5分钟
          proxy_cache_valid 200 5m;
          # 缓存文件的key为请求的URI
          proxy_cache_key $request_uri;
          # 把缓存状态设置为头部信息，响应给客户端
          add_header Nginx-Cache-Status $upstream_cache_status;
          # 代理转发          
          proxy_pass http://testProxy/;
        }
        # 静态资源
        location /images {
          root /root/data;
          #直接访问会以列表的形式展开
          autoindex on;
        }
    }
```



## 10.2配置缓存内容

对于一些实时性要求非常高的页面或数据来说，就不应该去设置缓存，下面来看看如何配置不缓存的内容。

```sh
    # 配置缓存目录等
    proxy_cache_path /etc/nginx/cache_temp levels=2:2 keys_zone=cache_zone:30m max_size=2g inactive=60m use_temp_path=off;


    # 配置上游服务器    
    upstream testProxy {
      server 39.106.35.112:8801;
      server 39.106.35.112:8802;
    }


    server {
        # 访问39.106.35.112:80路径，就会去访问代理服务器
        listen       80;
        server_name  39.106.35.112;
        # URI 中后缀为 .txt 或 .text 的设置变量值为 "no cache"⚠️
        if ($request_uri ~ \.(txt|text)$) {
          set $cache_name "no cache"
        }
        # 缓存的详细设置
        location /proxy {
          # 判断该变量是否有值，如果有值则不进行缓存，如果没有值则进行缓存⚠️
          proxy_no_cache $cache_name; 
          # 设置缓存内存，上面配置中已经定义好的
          proxy_cache cache_zone;
          # 缓存状态为200的请求，缓存时长为5分钟
          proxy_cache_valid 200 5m;
          # 缓存文件的key为请求的URI
          proxy_cache_key $request_uri;
          # 把缓存状态设置为头部信息，响应给客户端
          add_header Nginx-Cache-Status $upstream_cache_status;
          # 代理转发          
          proxy_pass http://testProxy/;
        }
        # 静态资源
        location /images {
          root /root/data;
          #直接访问会以列表的形式展开
          autoindex on;
        }
    }
```



## 10.3缓存配置参数

**proxy_cache**

存储一些之前被访问过、而且可能将要被再次访问的资源，使用户可以直接从代理服务器获得，从而减少上游服务器的压力，加快整个访问速度。

```sh
语法：proxy_cache zone | off ; # zone 是共享内存的名称

默认值：proxy_cache off;

上下文：http、server、location
```

**proxy_cache_path**

设置缓存文件的存放路径。

参数含义：

- **path** 缓存文件的存放路径；
- **level path** 的目录层级；
- **keys_zone** 设置共享内存；
- **inactive** 在指定时间内没有被访问，缓存会被清理，默认10分钟。

```sh
语法：proxy_cache_path path [level=levels] ...可选参数省略，下面会详细列举

默认值：proxy_cache_path off

上下文：http
```

**proxy_cache_key**

设置缓存文件的 key 。

```sh
语法：proxy_cache_key

默认值：proxy_cache_key $scheme$proxy_host$request_uri;

上下文：http、server、location
```

**proxy_cache_valid**

配置什么状态码可以被缓存，以及缓存时长。

```sh
语法：proxy_cache_valid [code...] time;

上下文：http、server、location

配置示例：proxy_cache_valid 200 304 2m;; # 说明对于状态为200和304的缓存文件的缓存时间是2分钟
```

**proxy_no_cache**

定义相应保存到缓存的条件，如果字符串参数的至少一个值不为空且不等于“ 0”，则将不保存该响应到缓存。

```sh
语法：proxy_no_cache string;

上下文：http、server、location

示例：proxy_no_cache $http_pragma    $http_authorization;
```

**proxy_cache_bypass**

定义条件，在该条件下将不会从缓存中获取响应。

```sh
语法：proxy_cache_bypass string;

上下文：http、server、location

示例：proxy_cache_bypass $http_pragma    $http_authorization;
```

**upstream_cache_status 变量**

它存储了缓存是否命中的信息，会设置在响应头信息中，在调试中非常有用。

```sh
MISS: 未命中缓存
HIT：命中缓存
EXPIRED: 缓存过期
STALE: 命中了陈旧缓存
REVALIDDATED: Nginx验证陈旧缓存依然有效
UPDATING: 内容陈旧，但正在更新
BYPASS: X响应从原始服务器获取
```





# 11 配置HTTPS

## 11.1HTTPS工作流程

在学习如何配置 HTTPS 之前，我们先来简单回顾下 HTTPS 的工作流程是怎么样的？它是如何进行加密保证安全的？

1. 客户端（浏览器）访问 https://www.baidu.com 百度网站；
2. 百度服务器返回 HTTPS 使用的 CA 证书；
3. 浏览器验证 CA 证书是否为合法证书；
4. 验证通过，证书合法，生成一串随机数并使用公钥（证书中提供的）进行加密；
5. 发送公钥加密后的随机数给百度服务器；
6. 百度服务器拿到密文，通过私钥进行解密，获取到随机数（公钥加密，私钥解密，反之也可以）；
7. 百度服务器把要发送给浏览器的内容，使用随机数进行加密后传输给浏览器；
8. 此时浏览器可以使用随机数进行解密，获取到服务器的真实传输内容；

这就是 HTTPS 的基本运作原理，使用对称加密和非对称机密配合使用，保证传输内容的安全性。



## 11.2配置证书

下载证书的压缩文件，里面有个 Nginx 文件夹，把 xxx.crt 和 xxx.key 文件拷贝到服务器目录，再进行如下配置：

```sh
    server {
      # SSL 访问端口号为 443
      listen 443 ssl http2 default_server;
      # 填写绑定证书的域名(我这里是随便写的)
      server_name lion.club;
      # 证书地址
      ssl_certificate /etc/nginx/https/lion.club_bundle.crt;
      # 私钥地址
      ssl_certificate_key /etc/nginx/https/lion.club.key;      
      ssl_session_timeout 10m;
      # 支持ssl协议版本，默认为后三个，主流版本是[TLSv1.2]
      ssl_protocols TLSv1 TLSv1.1 TLSv1.2; 

      location / {
        root         /usr/share/nginx/html;
        index        index.html index.htm;
      }
    }
```

如此配置后就能正常访问 HTTPS 版的网站了。





# 12 配置跨域 CORS

## 12.1跨域的定义

**跨域的定义**：

同源策略限制了从同一个源加载的文档或脚本如何与来自另一个源的资源进行交互。这是一个用于隔离潜在恶意文件的重要安全机制。通常不允许不同源间的读操作。 

**同源的定义**：

如果两个页面的协议，端口（如果有指定）和域名都相同，则两个页面具有相同的源。

下面给出了与 URL http://store.company.com/dir/page.html 的源进行对比的示例：

```http
http://store.company.com/dir2/other.html 同源
https://store.company.com/secure.html 不同源，协议不同
http://store.company.com:81/dir/etc.html 不同源，端口不同
http://news.company.com/dir/other.html 不同源，主机不同
```

**不同源会有如下限制**：

- Web 数据层面，同源策略限制了不同源的站点读取当前站点的 Cookie 、 IndexDB 、 LocalStorage 等数据；
- DOM 层面，同源策略限制了来自不同源的 JavaScript 脚本对当前 DOM 对象读和写的操作；
- 网络层面，同源策略限制了通过 XMLHttpRequest 等方式将站点的数据发送给不同源的站点。



## 12.2 Nginx 解决跨域

**Nginx 解决跨域的原理**:

例如：

- 前端 server 的域名为：fe.server.com
- 后端服务的域名为：dev.server.com

现在我在 fe.server.com 对 dev.server.com 发起请求一定会出现跨域。

现在我们只需要启动一个 Nginx 服务器，将 server_name 设置为 fe.server.com 然后设置相应的 location 以拦截前端需要跨域的请求，最后将请求代理回 dev.server.com 。如下面的配置：

```sh
    server {
      listen        80;
      #设置同源域名⚠️
      server_name  fe.server.com;
      location / {
        #再使用代理转发⚠️
        proxy_pass dev.server.com;
      }
    }
```

这样可以完美绕过浏览器的同源策略：fe.server.com 访问 Nginx 的 fe.server.com 属于同源访问，而 Nginx 对服务端转发的请求不会触发浏览器的同源策略。





# 13 配置开启 GZIP压缩

## 13.1 GZIP压缩概述

**GZIP压缩介绍：**

GZIP 是规定的三种标准 HTTP 压缩格式之一。目前绝大多数的网站都在使用 GZIP 传输 HTML 、CSS 、 JavaScript 等资源文件。

对于文本文件， GZiP 的效果非常明显，开启后传输所需流量大约会降至 1/4~1/3 。

并不是每个浏览器都支持 gzip 的，如何知道客户端是否支持 gzip 呢，请求头中的 Accept-Encoding 来标识对压缩的支持。

**配置要求：**

启用 gzip 同时需要客户端和服务端的支持，如果客户端支持 gzip 的解析，那么只要服务端能够返回 gzip 的文件就可以启用 gzip 了,我们可以通过 Nginx 的配置来让服务端支持 gzip 。下面的 respone 中 content-encoding:gzip ，指服务端开启了 gzip 的压缩方式。



## 13.2配置GZIP压缩

在 /etc/nginx/conf.d/ 文件夹中新建配置文件 gzip.conf ：

```sh

# 默认off，是否开启gzip
gzip on; 
# 要采用 gzip 压缩的 MIME 文件类型，其中 text/html 被系统强制启用；
gzip_types text/plain text/css application/json application/x-javascript text/xml application/xml application/xml+rss text/javascript;

# ---- 以上两个参数开启就可以支持Gzip压缩了 ---- #

# 默认 off，该模块启用后，Nginx 首先检查是否存在请求静态文件的 gz 结尾的文件，如果有则直接返回该 .gz 文件内容；
gzip_static on;

# 默认 off，nginx做为反向代理时启用，用于设置启用或禁用从代理服务器上收到相应内容 gzip 压缩；
gzip_proxied any;

# 用于在响应消息头中添加 Vary：Accept-Encoding，使代理服务器根据请求头中的 Accept-Encoding 识别是否启用 gzip 压缩；
gzip_vary on;

# gzip 压缩比，压缩级别是 1-9，1 压缩级别最低，9 最高，级别越高压缩率越大，压缩时间越长，建议 4-6；
gzip_comp_level 6;

# 获取多少内存用于缓存压缩结果，16 8k 表示以 8k*16 为单位获得；
gzip_buffers 16 8k;

# 允许压缩的页面最小字节数，页面字节数从header头中的 Content-Length 中进行获取。默认值是 0，不管页面多大都压缩。建议设置成大于 1k 的字节数，小于 1k 可能会越压越大；
# gzip_min_length 1k;

# 默认 1.1，启用 gzip 所需的 HTTP 最低版本；
gzip_http_version 1.1;
```

其实也可以通过前端构建工具例如 webpack 、rollup 等在打生产包时就做好 Gzip 压缩，然后放到 Nginx 服务器中，这样可以减少服务器的开销，加快访问速度。





# 14 Nginx原理与配置文件

## 14.1Nginx进程结构

当客户端发起请求后：Master进程监控到请求	⇨	唤醒一个或多个Worker进行管理	⇨	静态代理或动态代理

- Master用来管理子进程的，其本身并不真正处理用户请求。

- - 某个子进程 down 掉的话，它会向 Master 进程发送一条消息，表明自己不可用了，此时 Master 进程会去新起一个子进程;
  - 某个配置文件被修改了 Master 进程会去通知 work 进程获取新的配置信息，这也就是我们所说的热部署。

- 子进程间是通过共享内存的方式进行通信的。

**master-workers机制总结：**

当有请求到来时，master会唤醒worker进程进行抢夺，可以根据情况配置策略，当请求过少时只唤醒一部分worker⚠️

```sh
    # event段设置：
    # 当一个worker抢占到一个链接时，是否尽可能的让其获得更多的连接,默认是off，并发量大时可缓解客户端等待时间。
    multi_accept on;
    # 开启nginx的抢占锁机制，即master指派worker抢占锁，默认是on开启。
    accept_mutex  off; 
```



## 14.2配置文件重载原理

**reload 重载配置文件的流程：**

1. 向 master 进程发送 HUP 信号（ reload 命令）；
2. master 进程检查配置语法是否正确；
3. master 进程打开监听端口；
4. master 进程使用新的配置文件启动新的 worker 子进程；
5. master 进程向老的 worker 子进程发送 QUIT 信号；
6. 老的 worker 进程关闭监听句柄，处理完当前连接后关闭进程；
7. 整个过程 Nginx 始终处于平稳运行中，实现了平滑升级，用户无感知。



## 14.3worker进程

**worker进程概述：**

首先，对于每个worker进程来说，独立的进程，不需要加锁，所以省掉了锁带来的开销，同时在编程以及问题查找时，也会方便很多。

其次，采用独立的进程，可以让互相之间不会影响，一个进程退出后，其它进程还在工作，服务不会中断，master进程则很快启动新的worker进程。

当然，worker进程的异常退出，肯定是程序有bug了，异常退出，会导致当前worker上的所有请求失败，不过不会影响到所有请求，所以降低了风险。

**worker进程数量设置：**

Nginx 同redis类似都采用了io多路复用机制，每个worker都是一个独立的进程，但每个进程里只有一个主线程，通过异步非阻塞的方式来处理请求， 即使是成千上万个请求也不在话下。每个worker的线程可以把一个cpu的性能发挥到极致。

所以worker数和服务器的cpu数相等是最为适宜的⚠️。设少了会浪费cpu，设多了会造成cpu频繁切换上下文带来的损耗。

```sh
#设置worker数量
worker_processes 4
#work绑定cpu(4 work绑定4cpu)。
worker_cpu_affinity 0001 0010 0100 1000

#work绑定cpu (4 work绑定8cpu中的4个) 。
worker_cpu_affinity 00000001 00000010 00000100 00001000  00010000  00100000 01000000  10000000
```



## 14.4连接数

worker_connections 1024

这个值是表示每个worker进程所能建立连接的最大值。

一个nginx能建立的最大连接数是：

worker_connections * worker_processes

对于HTTP请求本地资源来说，能够支持的**最大并发数量**是：

worker_connections * worker_processes

如果是支持http1.1的浏览器每次访问要占两个连接，分别是一个请求连接和一个响应连接。

所以普通的静态访问最大并发数是： ⚠️

worker_connections * worker_processes /2

而如果是HTTP作为反向代理来说，因为还需要访问Tomcat增加两次连接，需要四个连接，最大并发数量应该是：

worker_connections * worker_processes/4

因为作为反向代理服务器，每个并发会建立与客户端的连接和与后端服务的连接，会占用两个连接。



## 14.5配置文件详解

/etc/nginx/nginx.conf

```sh
#安全问题，建议用nobody,不要用root.
#user  nobody;
 
#worker数和服务器的cpu数相等是最为适宜
worker_processes  2;
 
#work绑定cpu(4 work绑定4cpu)
worker_cpu_affinity 0001 0010 0100 1000
 
#work绑定cpu (4 work绑定8cpu中的4个) 。
worker_cpu_affinity 0000001 00000010 00000100 00001000  
 
#error_log path(存放路径) level(日志等级) path表示日志路径，level表示日志等级，
#具体如下：[ debug | info | notice | warn | error | crit ]
#从左至右，日志详细程度逐级递减，即debug最详细，crit最少，默认为crit。 
 
#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;
#pid        logs/nginx.pid;
 
events {
    #这个值是表示每个worker进程所能建立连接的最大值，所以，一个nginx能建立的最大连接数，应该是worker_connections * worker_processes。
    #当然，这里说的是最大连接数，对于HTTP请求本地资源来说，能够支持的最大并发数量是worker_connections * worker_processes，
    #如果是支持http1.1的浏览器每次访问要占两个连接，
    #所以普通的静态访问最大并发数是： worker_connections * worker_processes /2，
    #而如果是HTTP作为反向代理来说，最大并发数量应该是worker_connections * worker_processes/4。
    #因为作为反向代理服务器，每个并发会建立与客户端的连接和与后端服务的连接，会占用两个连接。
 
    worker_connections  1024;  
 
    #这个值是表示nginx要支持哪种多路io复用。
    #一般的Linux选择epoll, 如果是(*BSD)系列的Linux使用kquene。
    #windows版本的nginx不支持多路IO复用，这个值不用配。
    use epoll;
 
    # 当一个worker抢占到一个链接时，是否尽可能的让其获得更多的连接,默认是off 。
    multi_accept on; //并发量大时缓解客户端等待时间。
    # 默认是on ,开启nginx的抢占锁机制。
    accept_mutex  off; //master指派worker抢占锁
}
http {
    #当web服务器收到静态的资源文件请求时，依据请求文件的后缀名在服务器的MIME配置文件中找到对应的MIME Type，再根据MIME Type设置HTTP Response的Content-Type，然后浏览器根据Content-Type的值处理文件。
 
    include       mime.types;  #/usr/local/nginx/conf/mime.types
 
    #如果 不能从mime.types找到映射的话，用以下作为默认值-二进制
    default_type  application/octet-stream;
 
     #日志位置
     access_log  logs/host.access.log  main;
 
     #一条典型的accesslog：
     #101.226.166.254 - - [21/Oct/2013:20:34:28 +0800] "GET /movie_cat.php?year=2013 HTTP/1.1" 200 5209 "http://www.baidu.com" "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; MDDR; .NET4.0C; .NET4.0E; .NET CLR 1.1.4322; Tablet PC 2.0); 360Spider"
 
     #1）101.226.166.254:(用户IP)
     #2）[21/Oct/2013:20:34:28 +0800]：(访问时间) 
     #3）GET：http请求方式，有GET和POST两种
     #4）/movie_cat.php?year=2013：当前访问的网页是动态网页，movie_cat.php即请求的后台接口，year=2013为具体接口的参数
     #5）200：服务状态，200表示正常，常见的还有，301永久重定向、4XX表示请求出错、5XX服务器内部错误
     #6）5209：传送字节数为5209，单位为byte
     #7）"http://www.baidu.com"：refer:即当前页面的上一个网页
     #8）"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; #.NET CLR 3.0.30729; Media Center PC 6.0; MDDR; .NET4.0C; .NET4.0E; .NET CLR 1.1.4322; Tablet PC 2.0); 360Spider"： agent字段：通常用来记录操作系统、浏览器版本、浏览器内核等信息
 
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                       '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';
 
    #开启从磁盘直接到网络的文件传输，适用于有大文件上传下载的情况，提高IO效率。
    sendfile        on; //大文件传递优化，提高效率
   
    #一个请求完成之后还要保持连接多久, 默认为0，表示完成请求后直接关闭连接。
    #keepalive_timeout  0;
    keepalive_timeout  65; 
 
    #开启或者关闭gzip模块
    #gzip  on ; //文件压缩，再传输，提高效率
 
    #设置允许压缩的页面最小字节数，页面字节数从header头中的Content-Length中进行获取。
    #gzip_min_lenth 1k;//超过该大小开始压缩，否则不用压缩
 
    # gzip压缩比，1 压缩比最小处理速度最快，9 压缩比最大但处理最慢（传输快但比较消耗cpu）
    #gzip_comp_level 4;
 
    #匹配MIME类型进行压缩，（无论是否指定）"text/html"类型总是会被压缩的。
    #gzip_types types text/plain text/css application/json  application/x-javascript text/xml   
 
    #动静分离
    #服务器端静态资源缓存，最大缓存到内存中的文件，不活跃期限
    open_file_cache max=655350 inactive=20s;   
   
    #活跃期限内最少使用的次数，否则视为不活跃。
    open_file_cache_min_uses 2;
 
    #验证缓存是否活跃的时间间隔 
    open_file_cache_valid 30s;
    
upstream  myserver{
    # 1、轮询（默认）
    # 每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器down掉，能自动剔除。
    # 2、指定权重
    # 指定轮询几率，weight和访问比率成正比，用于后端服务器性能不均的情况。
    #3、IP绑定 ip_hash
    # 每个请求按访问ip的hash结果分配，这样每个访客固定访问一个后端服务器，可以解决session的问题。
    #4、备机方式 backup
    # 正常情况不访问设定为backup的备机，只有当所有非备机全都宕机的情况下，服务才会进备机。当非备机启动后，自动切换到非备机
# ip_hash;
server 192.168.161.132:8080 weight=1;
server 192.168.161.132:8081 weight=1 backup;
    #5、fair（第三方）公平，需要安装插件才能用
    #按后端服务器的响应时间来分配请求，响应时间短的优先分配。   
    #6、url_hash（第三方）
    #按访问url的hash结果来分配请求，使每个url定向到同一个后端服务器，后端服务器为缓存时比较有效。
 
      # ip_hash;
             server 192.168.161.132:8080 weight=1;
             server 192.168.161.132:8081 weight=1;
             ip_hash;
      
      #fair
 
      #hash $request_uri
      #hash_method crc32
      
}
 
    server {
        #监听端口号
        listen       80;
 
        #服务名
        server_name  192.168.161.130;
 
        #字符集
        #charset utf-8;
 
#location [=|~|~*|^~] /uri/ { … }   
# = 精确匹配
# ~ 正则匹配，区分大小写
# ~* 正则匹配，不区分大小写
# ^~  关闭正则匹配
 
#匹配原则：
 
# 1、所有匹配分两个阶段，第一个叫普通匹配，第二个叫正则匹配。
# 2、普通匹配，首先通过“=”来匹配完全精确的location
        #   2.1、 如果没有精确匹配到， 那么按照最大前缀匹配的原则，来匹配location
        #   2.2、 如果匹配到的location有^~,则以此location为匹配最终结果，如果没有那么会把匹配的结果暂存，继续进行正则匹配。
        # 3、正则匹配，依次从上到下匹配前缀是~或~*的location, 一旦匹配成功一次，则立刻以此location为准，不再向下继续进行正则匹配。
        # 4、如果正则匹配都不成功，则继续使用之前暂存的普通匹配成功的location.
          #不是以波浪线开头的都是普通匹配。
        location / {   # 匹配任何查询，因为所有请求都以 / 开头。但是正则表达式规则和长的块规则将被优先和查询匹配。
   
    #定义服务器的默认网站根目录位置
            root   html;//相对路径，省略了./         /user/local/nginx/html  路径
            
    #默认访问首页索引文件的名称
    index  index.html index.htm;
 
    #反向代理路径
            proxy_pass http://myserver;
 
    #反向代理的超时时间
            proxy_connect_timeout 10;
 
            proxy_redirect default;
         }
          #普通匹配
location  /images/ {    
    root images ;
 }
           # 反正则匹配
location ^~ /images/jpg/ {  # 匹配任何以 /images/jpg/ 开头的任何查询并且停止搜索。任何正则表达式将不会被测试。 
    root images/jpg/ ;
}
#正则匹配
location ~*.(gif|jpg|jpeg)$ {       
      #所有静态文件直接读取硬盘
              root pic ;
      
      #expires定义用户浏览器缓存的时间为3天，如果静态页面不常更新，可以设置更长，这样可以节省带宽和缓解服务器的压力
              expires 3d; #缓存3天
         }
 
        #error_page  404              /404.html;
 
        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        } 
    }
}
```







