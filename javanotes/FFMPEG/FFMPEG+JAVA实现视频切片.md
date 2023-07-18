# FFMPEG+JAVA实现视频切片

## 1 FFMPEG离线安装

1.安装`yasm`
下载地址：https://www.tortall.net/projects/yasm/releases/yasm-1.3.0.tar.gz

```shell
tar -zxf yasm-1.3.0.tar.gz
cd yasm-1.3.0
./configure
make
make install
```

2.下载`ffmpeg`
下载地址：https://ffmpeg.org/releases/ffmpeg-5.0.tar.gz

```shell
tar -zxf ffmpeg-5.0.tar.gz 
cd ffmpeg-5.0
./configure --enable-shared --prefix=/opt/ffmpeg #prefix后面为需要安装的目录
make #时间较长
make install
```

3.编辑环境变量

```shell
vim /etc/profile

#先添加
export PATH=$PATH:/opt/ffmpeg/bin

#当运行ffmpeg -version说找不到一些文件时可以加入
export LD_LIBRARY_PATH=/opt/ffmpeg/lib

#更新环境变量
source /etc/profile

#查看是否安装成功
ffmpeg -version
```



## 2 获取视频切片

mp4转m3u8命令：

```shell
ffmpeg -i /opt/inputFile/shop.mp4 -c:v copy -c:a copy -f ssegment -segment_format mpegts -segment_list /opt/outFile/shop.m3u8 -segment_time 10 /opt/outFile/shop%05d.ts
```

- /opt/inputFile/shop.mp4输入的mp4文件
- /opt/outFile/shop.m3u8输出的m3u8文件
- -segment_time设置每片的长度，单位为秒
- -segment_list ：段文件的名称，%05d表示5位数字

`生成的效果是：将shop.mp4视频文件每10秒生成一个ts文件，最后生成一个m3u8文件，m3u8文件是ts的索引文件。` 



## 3 NGINX安装

**YUM安装**

注意：Yum安装的Nginx，配置文件不在/user/local/nginx目录中

```shell
yum install nginx -y
```

安装完成后，通过 rpm -ql nginx 命令查看 Nginx 的安装信息：

```shell
rpm -ql nginx
```



**在线安装**

1.安装`pcre`

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

2.安装其他依赖

安装openssl 、zlib 、 gcc 依赖

```shell
yum -y install make zlib zlib-devel gcc-c++ libtool  openssl openssl-devel
```

3.安装nginx

第一步，使用命令解压 nginx安装包

第二步，配置编译安装

```shell
./configure
make
make install
```

第三步，进入目录启动服务

```shell
 cd /usr/local/nginx/sbin
```

```shell
./nginx
```



**离线安装**

从官网下载安装包：http://nginx.org/   nginx-1.12.2.tar.gz

1.安装`gcc++`和`c++`

先找一台版本相同的外网Linux服务器或虚拟机，使用`yum-utils`获取相关依赖包，再拷贝到外网环境

```shell
yum install yum-utils
yumdownloader gcc gcc-c++ --resolve --destdir=/opt/nginx_pack #/opt/nginx_pack是存储包的目录
rpm -Uvh *.rpm --nodeps --force    #自动根据依赖顺序安装
```

2.安装`pcre`

```shell
tar zxvf pcre-8.37.tar.gz #解压缩
./configure   
make
make install
```

3.安装`openssl`

```shell
tar zxvf openssl-xx.tar.gz #解压缩
./config
make
make install
```

4.安装`zlib`

```shell
tar zxvf zlib-xx.tar.gz  #解压缩
./configure   
make
make install
```

5.安装`nginx`

```shell
tar nginx-xx.tar.gz  #解压缩
./configure  
make
make install
```

6.执行命令或进入目录启动

```shell
 cd /usr/local/nginx/sbin
 ./nginx
```



**关闭防火墙**

Nginx默认不能访问的，因为防火墙问题，关闭防火墙-开放访问的端口号，**80**端口

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



**配置文件**

安装目录下的conf/nginx.conf 文件，可修改端口等配置信息，如下是配置切片目录的配置项

配置后m3u8播放路径为：`http:IP:20080/hg/shop.m3u8`

```shell
#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    server {
        listen       20080;
        server_name  localhost;

        location /hg {
            add_header Access-Control-Allow-Origin *;
            add_header Access-Control-Allow-Headers X-Requested-With;
            add_header Access-Control-Allow-Methods GET,POST,PUT,DELETE,OPTIONS;

            types {  
                application/vnd.apple.mpegurl m3u8;  
                video/mp2t ts;  
            }

            alias  /opt/outFile/; #切片存放目录地址
            expires -1;
            add_header Cache-Control no-cache;
        }

        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
}
```



**常用命令**

系统命令

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

应用程序命令

```shell
nginx -s reload  # 向主进程发送信号，重新加载配置文件，热重启
nginx -s reopen   # 重启 Nginx
nginx -s stop    # 快速关闭
nginx -s quit    # 等待工作进程处理完成后关闭
nginx -T         # 查看当前 Nginx 最终的配置
nginx -t         # 检查配置是否有问
nginx -c /etc/nginx/nginx.conf  #指定配置文件⚠️
```



## 4 浏览器播放m3u8

浏览器无法直接播放，必须使用插件，如videoJS⚠️

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>视频播放</title>
    <link href="./videojs/video-js.css" rel="stylesheet">
</head>
<body>
<video id=example-video width=800 height=600 class="video-js vjs-default-skin vjs-big-play-centered" controls poster="http://39.105.221.89:20080/hg/shop.jpg">
    <source
            src="http://39.105.221.89:20080/hg/shop.m3u8"
            type="application/x-mpegURL">
</video>
<input type="button" onClick="switchvideo()" value="switch"/>

<script src="./videojs/video.js"></script>
<script src="./videojs/videojs-contrib-hls.js"></script>
<script>
    var player = videojs('example-video');
    player.play();

    function switchvideo(){
        player.src({
            src: 'http://39.105.221.89:20080/hg/shop.m3u8',
            type: 'application/x-mpegURL',
            withCredentials: true
        });
        player.play();
    }
</script>

</body>
</html>
```



## 5 JAVA生成切片

在实际开发中，可以通过`java`调用`cmd`命令，使用ffmpeg进行切片操作，主要代码如下：

**ProcessUtil.java**

```java
public class ProcessUtil {

    public static String execute(List<String> command) {
        StringBuffer inputStringBuffer = new StringBuffer();
        StringBuffer errorStringBuffer = new StringBuffer();
        try {
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();
            System.out.println("============inputStream============");
            // 处理InputStream
            Thread t1 = new Thread(() -> {
                InputStream input = null;
                InputStreamReader reader = null;
                BufferedReader buffer = null;

                try {
                    input = process.getInputStream();
                    reader = new InputStreamReader(input);
                    buffer = new BufferedReader(reader);
                    String inputLine = "";
                    while ((inputLine = buffer.readLine()) != null) {
                        System.out.println(inputLine);
                        inputStringBuffer.append(inputLine);
                    }
                    //退出循环后表示结束流
                    System.out.println("===》》退出循环后表示结束流");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (buffer != null) {
                            buffer.close();
                        }
                        if (reader != null) {
                            reader.close();
                        }
                        if (input != null) {
                            input.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.setName("deviceName");
            t1.start();

            System.out.println("============errorStream============");
            // 处理ErrorStream
            new Thread(() -> {
                InputStream input = null;
                InputStreamReader reader = null;
                BufferedReader buffer = null;
                try {
                    input = process.getErrorStream();
                    reader = new InputStreamReader(input);
                    buffer = new BufferedReader(reader);
                    String errorLine = "";
                    while ((errorLine = buffer.readLine()) != null) {
                        System.out.println(errorLine);
                        errorStringBuffer.append(errorLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (buffer != null) {
                            buffer.close();
                        }
                        if (reader != null) {
                            reader.close();
                        }
                        if (input != null) {
                            input.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            // 这里进程阻塞，将等待外部转换成功后，才往下执行
            //process.waitFor();
            t1.wait();

            /**
             * 只会存在一个输入流返回
             */
            if (inputStringBuffer != null) {
                return inputStringBuffer.toString();
            }
            if (errorStringBuffer != null) {
                return errorStringBuffer.toString();
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
```

**Main.java**

```java
public class Main {
    public static void main(String[] args) {
        String input = "D:/m3u8/demo.mp4";
        String output = "D:/m3u8/hls/test.m3u8";
        List<String> command = new ArrayList<>();
        command.add("ffmpeg");
        command.add("-i");
        command.add(input);
        command.add("-c:v");
        command.add("copy");
        command.add("-c:a");
        command.add("copy");
        command.add("-f");
        command.add("ssegment");
        command.add("-segment_format");
        command.add("mpegts");
        command.add("-segment_list");
        command.add(output);
        command.add("-segment_time");
        command.add("10");
        command.add("D:/m3u8/hls/test%05d.ts");
        String result = ProcessUtil.execute(command);
    }
}
```

执行完java程序以后，如果在`D:/m3u8/hls`生成了ts和m3u8文件，则表示成功.

**TS切片好处**

比如：我们上传一个10MB的视频进行播放，如果不进行切片话，前端页面在播放时需要先下载完整的视频，这样会导致视频加载时间过长。如果使用ts切片后使用m3u8进行播放，在前端播放时页面会自动间隔一定时间下载ts文件进行播放。
