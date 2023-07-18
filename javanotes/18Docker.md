# 1 Docker简介

## 1.1虚拟化概念

虚拟化，是指通过虚拟化技术将一台计算机虚拟为多台逻辑计算机。在一台计算机上同时运行多个逻辑计算机，每个逻辑计算机可运行不同的操作系统，并且应用程序都可以在相互独立的空间内运行而互不影响，从而显著提高计算机的工作效率。

 虚拟化技术种类很多，例如：软件虚拟化、硬件虚拟化、内存虚拟化、网络虚拟化(vip)、桌面虚拟化、服务虚拟化、虚拟机等等。



## 1.2Docker概述

Docker 是一个开源的应用容器引擎，基于 Go 语言开发。Docker 可以让开发者打包他们的应用以及依赖包到一个轻量级、可移植的容器中，然后发布到任何流行的 Linux 机器上，也可以实现虚拟化。容器是完全使用沙箱机制，相互之间不会有任何接口，更重要的是容器性能开销极低。可以解决环境问题，一次封装，到处运行。

传统虚拟机技术基于安装在主操作系统上的虚拟机管理系统，Docker容器是在操作系统层面上实现虚拟化，直接复用本地主机的操作系统，而传统虚拟机则是在硬件层面实现虚拟化。与传统的虚拟机相比，Docker优势体现为启动速度快、占用体积小。



## 1.3Docker组成部分

Docker是一个客户端-服务器（C/S）架构程序。Docker客户端只需要向Docker服务器或者守护进程发出请求，服务器或者守护进程将完成所有工作并返回结果。Docker提供了一个命令行工具Docker以及一整套RESTful API。你可以在同一台宿主机上运行Docker守护进程和客户端，也可以从本地的Docker客户端连接到运行在另一台宿主机上的远程Docker守护进程。

Docker核心三要素：

**镜像：**类似虚拟机镜像 , 是一个特殊的文件系统

操作系统分为内核和用户空间。对于Linux而言，内核启动后，会挂载root文件系统为其提供用户空间支持。而Docker镜像（Image），就相当于是一个root文件系统。

Docker镜像是一个特殊的文件系统，除了提供容器运行时所需的程序、库、资源、配置等文件外，还包含了一些为运行时准备的一些配置参数（如匿名卷、环境变量、用户等）。 镜像不包含任何动态数据，其内容在构建之后也不会被改变。

**容器：**类似linux系统环境，运行和隔离应用。是镜像运行时的实体

镜像（Image）和容器（Container）的关系，就像是面向对象程序设计中的类和实例一样，镜像是静态的定义，容器是镜像运行时的实体。容器可以被创建、启动、停止、删除、暂停等 。

**仓库：**集中存放镜像文件的地方。

镜像构建完成后，可以很容易的在当前宿主上运行，但是， 如果需要在其它服务器上使用这个镜像，我们就需要一个集中存储、分发镜像的地方，比如后面我们要学的，Docker Registry就是这样的服务。

Docker注册中心：

Docker用Registry来保存用户构建的镜像。Registry分为公共和私有两种。Docker公司运营公共的Registry叫做***\*Docker Hub\****。用户可以在Docker Hub注册账号，分享并保存自己的镜像（说明：在Docker Hub下载镜像巨慢，可以自己构建私有的Registry）。



# 2 Docker安装与启动

Docker官方建议在Ubuntu中安装，因为Docker是基于Ubuntu发布的，不过本文以Centos为例

官网中文安装参考手册：https://docs.docker.com/install/linux/docker-ce/centos/

**注意：**这里建议安装在CentOS7.x以上的版本，在CentOS6.x的版本中，安装前需要安装其他很多的环境，而且Docker很多补丁不支持更新。

## 2.1Centos安装Docker★

（1）yum安装gcc相关，检查gcc和g++是否安装好，如果没有安装好，则需要安装。

```shell
yum -y install gcc
```

```shell
yum -y install gcc-c++
```

（2）安装需要的软件包

```sql
yum install -y yum-utils device-mapper-persistent-data lvm2
```

（3）设置镜像仓库

不要使用官网推荐的源！不要使用官网推荐的源！不要使用官网推荐的源！⚠️

```shell
#不要使用！容易报错，网速慢，超时，安装不上⚠️
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
```

使用这个！使用这个！使用这个！使用阿里源⚠️

```sql
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

（4）更新yum软件包索引

```shell
yum makecache fast
```

（5）安装DOCKER CE（社区版）（DOCKER EE企业版收费）

```shell
yum -y install docker-ce
```



## 2.2启动Docker★

启动docker：

手动启动

```shell
systemctl start docker 
```

自动启动

```shell
systemctl enable docker  
```

检查版本

```shell
docker version
```

下载并运行HelloWorld镜像

```shell
docker run hello-world
```



## 2.3配置镜像加速★

CentOS7版本:

（1）创建目录

```shell
mkdir -p /etc/docker
```

（2）编辑文件（文件内配置一个源即可⚠️）

```shell
vim  /etc/docker/daemon.json
```

（3）配置源

文件内配置网易源

```sh
{
"registry-mirrors": ["http://hub-mirror.c.163.com"]
}
```

文件内配置阿里源（推荐）

```sh
{
"registry-mirrors": ["https://8y2y8njn.mirror.aliyuncs.com"]
}
```

文件内配置ustc源

是老牌的linux镜像服务提供者了，还在遥远的ubuntu 5.04版本的时候就在用。ustc的docker镜像加速器速度很快。

ustc docker mirror的优势之一就是不需要注册，是真正的公共服务。

https://lug.ustc.edu.cn/wiki/mirrors/help/docker

```sh
{
"registry-mirrors": ["https://docker.mirrors.ustc.edu.cn"]
}
```

（4）重写加载Docker

```shell
systemctl daemon-reload
```

（5）重启Docker

```shell
systemctl restart docker
```



## 2.4卸载Docker

（1）停止Docker

```shell
systemctl stop docker
```

（2）删除客户端

```shell
yum -y remove docker-ce
```

（3）删除配置文件

```shell
rm -rf /var/lib/docker
```

（4）官方文档内的一步卸载：https://docs.docker.com/install/linux/docker-ce/centos/#uninstall-old-versions

```shell
yum remove docker \
docker-client \
docker-client-latest \
docker-common \
docker-latest \
docker-latest-logrotate \
docker-logrotate \
docker-engine
```





# 3 Docker命令

## 3.1Docker启动与停止★

| 功能                 | 命令                     |
| -------------------- | ------------------------ |
| 启动docker：         | systemctl start docker   |
| 停止docker：         | systemctl stop docker    |
| 重启docker：         | systemctl restart docker |
| 查看docker状态：     | systemctl status docker  |
| 开机启动：           | systemctl enable docker  |
| 查看docker概要信息： | docker info              |
| 查看docker帮助文档： | docker --help            |



## 3.2Docker操作指令★

| 指令    | 介绍                                                         |
| ------- | ------------------------------------------------------------ |
| cp      | 本地文件系统(OS操作系统\|宿主机)和容器之间进行文件或者文件夹拷贝 |
| exec    | 登录一个容器，使用命令行操作正在运行的容器。                 |
| images  | 镜像的集合查询。                                             |
| ps      | 容器列表                                                     |
| pull    | 下载镜像                                                     |
| restart | 重启一个或多个容器                                           |
| rm      | 删除一个或多个容器                                           |
| rmi     | 删除一个或多个镜像                                           |
| run     | 创建一个容器，并运行起来                                     |
| save    | 导出镜像到一个文件(tar)中                                    |
| search  | 搜索镜像（从Docker Hub）                                     |
| start   | 启动一个或多个已经停止的容器                                 |
| stop    | 停止一个或多个正在运行的容器                                 |



## 3.3镜像相关命令★

查看所有镜像：docker images

搜索镜像：docker search 镜像名称

拉取镜像：docker pull 镜像名称

删除单个镜像(-f 强制删除)：docker rmi  -f 镜像ID

删除多个镜像：docker rmi -f  镜像名1:TAG   镜像名2:TAG

删除所有镜像：docker rmi -f $(docker images -qa)

官方镜像站：https://hub.docker.com/search?image_filter=official&type=image



## 3.4容器相关命令★

**容器**也是docker中的核心概念，镜像是创建容器的软件 , 容器是由镜像运行产生的运行实例。镜像和容器的关系，就如同Java语言中类和对象的关系。

### 3.4.1查看容器

| 查看正在运行的容器：     | docker ps                  |
| ------------------------ | -------------------------- |
| 查看所有容器：           | docker ps –a               |
| 查看最后一次运行的容器： | docker ps –l               |
| 查看停止的容器：         | docker ps -f status=exited |



### 3.4.2创建与启动容器

创建容器命令：docker run

```shell
-i：表示运行容器
-t：表示容器启动后会进入其命令行。加入这两个参数后，容器创建就能登录进去。即分配一个伪终端。
--name :为创建的容器命名。
-v：表示目录映射关系（前者是宿主机目录，后者是映射到宿主机上的目录），可以使用多个－v做多个目录或文件映射。注意：最好做目录映射，在宿主机上做修改，然后共享到容器上。
-d：在run后面加上-d参数,则会创建一个守护式容器在后台运行（这样创建容器后不会自动登录容器，如果只加-i -t两个参数，创建后就会自动进去容器）。
-p：表示端口映射，前者是宿主机端口，后者是容器内的映射端口。可以使用多个-p做多个端口映射
```



### 3.4.3交互式创建容器

以交互式方式创建并启动容器，启动完成后，直接进入当前容器。使用exit命令退出容器。需要注意的是以此种方式启动容器，如果退出容器，则容器会进入停止状态。可以理解成交互式容器 是前台容器。

```shell
docker run -it --name=容器名称 镜像名称:标签 /bin/bash
```



### 3.4.4创建后台容器

创建命令

```shell
docker run -id --name=mycentos2 centos:7
```

查看 docker 容器已经运行



### 3.4.5守护式创建容器

创建一个守护式容器；如果对于一个需要长期运行的容器来说，我们可以创建一个守护式容器。

命令如下（容器名称不能重复）：守护容器可以理解成在后台运行的容器

```shell
docker run -id --name=容器名称 镜像名称:标签
```

进入守护式容器方式：

```
docker exec -it 容器名称 (或者容器ID)  /bin/bash
```

退出容器输入exit

```shell
exit
```



### 3.4.6停止与启动容器

先查看容器

```shell
docker ps -a
```

停止容器，再查看是否已经停止

```shell
docker stop 容器名称（或者容器ID）
```

启动容器，再查看是否已经启动

```shell
docker start 容器名称（或者容器ID）
```



### 3.4.7文件拷贝

将文件拷贝到容器内

```shell
docker cp 需要拷贝的文件或目录 容器名称:容器目录
```

将文件从容器内拷贝出

```shell
docker cp 容器名称:容器目录 需要拷贝的文件或目录
```



### 3.4.8目录挂载

我们可以在创建容器的时候，将宿主机的目录与容器内的目录进行映射，这样我们就可以通过修改宿主机某个目录的文件从而去影响容器。

创建容器 添加-v参数 后边为 宿主机目录:容器目录，例如：

```shell
docker run -di --name=容器的名字 -v /usr/local/myhtml:/usr/local/myhtml centos:7
```

注意：

如果你共享的是多级的目录，可能会出现权限不足的提示。这是因为CentOS7中的安全模块selinux把权限禁掉了

我们需要添加参数 --privileged=true 来解决挂载的目录没有权限的问题

```shell
docker run -id --privileged=true --name=mycentos5 -v /usr/local/myhtml:/usr/local/myhtml centos:7
```



### 3.4.9查看容器信息

我们可以通过以下命令查看容器运行的各种数据

```shell
docker inspect 容器名称（或容器ID） 
```

通过inspect 可以查看的信息太多，如果想单纯有针对性的查看某个信息，也可以直接执行下面的命令直接输出IP地址

```shell
docker inspect --format='{{.NetworkSettings.IPAddress}}' 容器名称（或容器ID）
```



### 3.4.10删除容器

删除指定的容器

```shell
docker rm 容器名称（容器ID）
```

删除容器的时候，如果容器在运行，会报错，必须先停止容器

```shell
# 查看正在运行的容器
docker ps
# 删除正在运行的容器
docker rm mycentos11
# 停止容器
docker stop mycentos11
```

也可以使用-f参数进行强制删除：

```shell
docker rm -f 容器名称或id
```





# 4 应用部署

## 4.1MySql部署

（1）拉取mysql镜像

```shell
docker pull centos/mysql-57-centos7
```

（2）创建容器

-p 代表端口映射，格式为 宿主机映射端口:容器运行端口

-e 代表添加环境变量 MYSQL_ROOT_PASSWORD 是root用户远程登陆密码

创建守护式容器 ，并且通过 docker ps 查看是否映射成功，

```shell
# 创建mysql5.7容器
# docker run -di --name=容器名字 -p 宿主机端口:容器端口 -e MYSQL_ROOT_PASSWORD=mysql密码 容器名称
docker run -di --name=mysql5.7 -p 33306:3306 -e MYSQL_ROOT_PASSWORD=123456 centos/mysql-57-centos7
```

（3）远程登录mysql

连接宿主机的IP ,指定端口为33306，使用Navicat连接。



## 4.2Tomcat部署

（1）拉取镜像

```shell
docker pull tomcat:7-jre7
```

（2）创建容器

创建容器 -p表示地址映射 -v 表示目录挂载

```shell
# 创建tomcat容器;并挂载了webapps目录
docker run -di --name=mytomcat -p 9000:8080 -v /usr/local/webapps:/usr/local/tomcat/webapps tomcat:7-jre7
```

（3）部署资源

将资料中的mavenweb目录上传到docker容器挂载目录里面，请求地址：http://39.106.35.112:9000/mavenweb/



## 4.3Nginx部署

（1）拉取镜像

```shell
docker pull nginx
```

（2）创建Nginx容器

```shell
docker run -di --name=mynginx -p 8001:80 nginx
```

（3）访问测试

请求地址：http://39.106.35.112:8001/mavenweb/ 



## 4.4 Redis部署

（1）拉取镜像

```shell
docker pull redis
```

（2）创建容器

```shell
docker run -di --name=myredis -p 16379:6379 redis
```

（3）通过客户端工具连接测试。或者通过java代码用Jedis客户端进行测试。





# 5 迁移与备份

## 5.1容器保存为镜像

我们可以通过以下命令将容器保存为镜像

```shell
# 保存nginx容器为镜像
docker commit 容器名称  镜像名称
例如：docker commit mynginx mynginx_i
```



## 5.2镜像备份

我们可以通过以下命令将镜像保存为tar 文件

```shell
# 命令形式：docker save –o tar文件名 镜像名
# 保存镜像为文件 -o：表示output 输出的意思
docker  save -o mynginx.tar mynginx_i
```



## 5.3 镜像恢复与迁移

首先我们先删除掉mynginx_img镜像 然后执行此命令进行恢复

```shell
# 命令形式：docker load -i tar文件名
docker load -i mynginx.tar
```





# 6 Dockerfile

## 6.1Dockerfile概述

Dockerfile其实就是一个文本文件，由一系列命令和参数构成，Docker可以读取Dockerfile文件并根据Dockerfile文件的描述来构建镜像。

1、对于开发人员：可以为开发团队提供一个完全一致的开发环境；

2、对于测试人员：可以直接拿开发时所构建的镜像或者通过Dockerfile文件构建一个新的镜像开始工作了；

3、对于运维人员：在部署时，可以实现应用的无缝移植。



## 6.2常用命令

| 命令                               | 作用                                                         |
| ---------------------------------- | ------------------------------------------------------------ |
| FROM image_name:tag                | 定义了使用哪个基础镜像启动构建流程                           |
| MAINTAINER user_name               | 声明镜像的创建者                                             |
| ENV key value                      | 设置环境变量 (可以写多条)                                    |
| RUN command                        | 是Dockerfile的核心部分(可以写多条)                           |
| ADD source_dir/file dest_dir/file  | 将宿主机的文件复制到容器内，如果是一个压缩文件，将会在复制后自动解压 |
| COPY source_dir/file dest_dir/file | 和ADD相似，但是如果有压缩文件并不能解压                      |
| WORKDIR path_dir                   | 设置工作目录                                                 |



## 6.3使用脚本创建镜像

步骤：

（1）编写Dockerfile

```shell
#依赖镜像名称和ID
FROM centos:7
#指定镜像创建者信息
MAINTAINER atguigu
#切换工作目录
WORKDIR /usr
RUN yum install -y glibc.i686
RUN mkdir /usr/local/java
#ADD 是相对路径jar,把java添加到容器中
ADD jdk-8u144-linux-i586.tar.gz /usr/local/java/
#配置java环境变量
ENV JAVA_HOME /usr/local/java/jdk1.8.0_144
ENV JRE_HOME $JAVA_HOME/jre
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH
ENV PATH $JAVA_HOME/bin:$PATH
```

（2）执行命令构建镜像，注意后边的空格和点，不要省略

```shell
docker build -t='jdk1.8' .
```





# 7 Docker私有仓库

## 7.1 私有仓库搭建与配置

（1）拉取私有仓库镜像

```shell
docker pull registry
```

（2）启动私有仓库容器

```shell
docker run -di --name=registry -p 5000:5000 registry
```

（3）打开浏览器 输入地址 http://192.168.6.100:5000/v2/_catalog 看到{"repositories":[]} 表示私有仓库搭建成功并且内容为空

```json
{
  "repositories":[]
} 
```

（4）修改daemon.json，添加以下内容，保存退出。目的是让容器信任下面的地址

```shell
"insecure-registries":["192.168.6.100:5000"]
```

（5）重启docker 服务

```shell
systemctl restart docker
```



## 7.2 镜像上传至私有仓库

（1）标记此镜像为私有仓库的镜像

```shell
# 标记镜像为私有仓库的镜像  
# docker tag jdk1.8 宿主机IP:5000/jdk1.8
docker tag jdk1.8 192.168.6.100:5000/jdk1.8
```

（2）再次启动私服容器

```shell
# 再次启动私有仓库容器  
docker start registry
```

（3）上传标记的镜像

```shell
# 上传标记的镜像  
# docker push 宿主机IP:5000/jdk1.8
docker push 192.168.6.100:5000/jdk1.8
```

重新刷新，发现jdk 1.8 已经上传到私服



## 7.3 从私有仓库拉取镜像

```shell
# 执行拉取镜像命令并查看
docker pull 192.168.6.100:5000/jdk1.8
docker images
```

