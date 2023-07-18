# 1 Linux入门

## 1.1Linux下载安装

清华镜像：https://mirrors.tuna.tsinghua.edu.cn/

网易镜像：http://mirrors.163.com/centos/



## 1.2Linux目录介绍

- Linux系统/就是根目录 
  - /的下面就是文件夹，不同的文件夹存放不同的内容
- Linux根目录下的文件夹

  - bin    命令文件
  - home  家目录 ★
    - 普通用户的家目录
  - root  root用户的家目录 
  - etc   系统的配置文件目录 ★
  - usr  默认软件安装位置
  - boot 存放的一些系统文件(不要动)
  - media 识别硬件设备
    - 光盘、U盘...
  - mnt 手动挂载 ★
    - 将硬件设备，手动挂载到此目录下
  - opt  安装我们自己的软件目录 ★
    - jdk/tomcat/mysql...
  - var  日志文件，随着时间的流逝，日志文件会逐渐增大，就可以放在这





# 2 Vim编辑器★

Linux编辑文本的两种方式：vi记事本、vim编辑器

文本编辑器共有三种模式：一般模式、编辑模式、命令模式

## 2.1一般模式

使用vim打开文件，默认为一般模式

- 功能：复制、粘贴、删除...

- 无法通过键盘录入内容

| 语法                        | 功能描述                              |
| --------------------------- | ------------------------------------- |
| yy                          | **复制**光标当前一行                  |
| y数字y                      | 复制一段（从第几行到第几行）          |
| p                           | 箭头移动到目的行**粘贴**              |
| u                           | **撤销上一步**                        |
| dd                          | **删除**光标当前行                    |
| d数字d                      | 删除光标（含）后多少行                |
| x                           | 删除一个字母，相当于del，**向后删**   |
| X                           | 删除一个字母，相当于Backspace，向前删 |
| yw                          | 复制一个词                            |
| dw                          | 删除一个词                            |
| ^                           | **移动到行头**                        |
| $                           | **移动到行尾**                        |
| gg或者1+G                   | **移动到页头**                        |
| G                           | **移动到页尾**                        |
| 数字+G（先输入数字，在按G） | **移动到目标行**                      |



## 2.2编辑模式

通过键盘以键盘录入的形式编辑文件内容，需要开启编辑模式

开启方法：输入i, I, o, O, a, A中任意一个字母

关闭方法：按键ESC

| 按键 | 功能                   |
| ---- | ---------------------- |
| i    | **当前光标前**         |
| a    | 当前光标后             |
| o    | **当前光标行的下一行** |
| I    | 光标所在行最前         |
| A    | 光标所在行最后         |
| O    | 当前光标行的上一行     |



## 2.3命令模式

用于保存、退出、显示行号、查找等功能

| 命令                 | 功能                                             |
| -------------------- | ------------------------------------------------ |
| :w                   | **保存**                                         |
| :q                   | **退出**                                         |
| :!                   | **强制执行**                                     |
| : %s/old字符/new字符 | **批量替换**                                     |
| / 要查找的词         | n 查找下一个，N 往上查找                         |
| ? 要查找的词         | n是查找上一个，N是往下查找                       |
| :set nu              | 显示行号                                         |
| :set nonu            | 关闭行号                                         |
| ZZ（shift+zz）       | **没有修改文件直接退出，如果修改了文件保存后退** |
| :nohl                | **去除高亮显示**                                 |





# 3 系统设置

## 3.1网络设置

查看IP地址：
- linux  --->  ifconfig
- windows ---->   ipconfig

IP地址获取方式的区别：
- 服务器系统的ip地址，应该是固定的，自动获取，就有可能存在ip地址的变化
- 服务器的ip地址是要跟域名进行绑定的，所以要求ip地址是固定的

修改IP地址：

- 修改/etc下的配置文件，fcfg-ens33为网卡名

```shell
vim /etc/sysconfig/network-scripts/ifcfg-ens33
```

- 重启网卡驱动

```shell
systemctl restart network
```



## 3.2配置主机名

修改主机名称

```shell
vim /etc/hostname
```

配置主机名和对应关系

```shell
vim /etc/hosts
```

重启服务器，重启后，查看主机名，已经修改成功



## 3.3服务管理★

查看目前所有的服务

```shell
systemctl --type service
```

查看某个服务状态

```shell
systemctl status 服务名
```

关闭某个服务

```shell
systemctl stop 服务名
```

启动某个服务

```shell
systemctl start 服务名
```

重启某个服务

```shell
systemctl restart 服务名
```

查看所有服务的自启设置

```shell
systemctl list-unit-files
```

查看某个服务的自启状态，disabled表示关闭，enabled表示开启

```shell
systemctl is-enabled 服务名
```

关闭自启动

```shell
systemctl disable 服务名
```

开启自启动

```shell
systemctl enable 服务名
```



## 3.4关机重启

将内存中的数据进行落盘操作

```shell
sync
```

关机

```shell
poweroff
```

重启

```shell
reboot
```

立刻重启，等同于reboot

```shell
shutdown -h now 
```

定时关机或者重启并输出提示内容，单位是分钟

```shell
shutdown -h/-r num '提示内容'
```



## 3.5防火墙

关闭防火墙的自动启动

```shell
systemctl disable firewalld.service
```

开启防火墙的自动启动

```shell
enable firewalld
```

查看防火墙状态

```shell
systemctl is-enabled firewalld
```





# 4 常用基本命令

## 4.1基础命令

Linux的基础命令及参数，使用频率较高

### 4.1.1帮助命令

查看命令帮助信息

```shell
man ls
```

查看shell内置命令帮助信息

```shell
help cd
```



### 4.1.2常用快捷键

| 常用快捷键      | 功能                         |
| --------------- | ---------------------------- |
| ctrl + c        | 停止进程                     |
| ctrl+l          | 清屏；彻底清屏是：reset      |
| ctrl + q        | 退出                         |
| **善于用tab键** | 提示(更重要的是可以防止敲错) |
| **上下键**      | 查找执行过的命令             |



### 4.1.3文件分屏查看器

基本语法：more 要查看的文件名称

```shell
more config.conf
```

| 操作           | 功能说明                                 |
| -------------- | ---------------------------------------- |
| 空白键 (space) | 代表向下翻一页；                         |
| Enter          | 代表向下翻『一行』；                     |
| q              | 代表立刻离开 more ，不再显示该文件内容。 |
| Ctrl+F         | 向下滚动一屏                             |
| Ctrl+B         | 返回上一屏                               |
| =              | 输出当前行的行号                         |
| :f             | 输出文件名和当前行的行号                 |



### 4.1.4高级分屏查看器

less指令是more指令的升级版，支持各种显示终端

基本语法：less 要查看的文件

```shell
less smartd.conf
```

| 操作       | 功能说明                                           |
| ---------- | -------------------------------------------------- |
| 空白键     | 向下翻动一页；                                     |
| [pagedown] | 向下翻动一页                                       |
| [pageup]   | 向上翻动一页；                                     |
| /字串      | 向下搜寻『字串』的功能；n：向下查找；N：向上查找； |
| ?字串      | 向上搜寻『字串』的功能；n：向上查找；N：向下查找； |
| q          | 离开 less 这个程序；                               |



### 4.1.5echo命令

echo输出内容到控制台

基本语法：echo [选项] [输出内容]	  -e支持反斜杠控制的转义字符的转换

```shell
echo -e "hello\tworld"
```

| 控制字符 | 作用                |
| -------- | ------------------- |
| \\       | 输出\本身           |
| \n       | 换行符              |
| \t       | 制表符，也就是Tab键 |



### 4.1.6head命令

head用于显示文件的开头部分内容，默认显示前十行

基本语法：head -n 5 文件

```shell
head -n 2 smartd.conf
```

补充：cat命令也可查看，不过默认显示全部内容，加参数-n可显示行号

```shell
cat -n smartd.conf
```

| 选项      | 功能                   |
| --------- | ---------------------- |
| -n <行数> | 指定显示头部内容的行数 |



### 4.1.7tail命令★

tail用于输出文件中尾部的内容，默认输出后10行

基本语法：tail  -n 5 文件 或者 tail  -f  文件

```shell
tail -f houge.txt
```

```shell
tail -n 1 smartd.conf 
```

| 选项     | 功能                                 |
| -------- | ------------------------------------ |
| -n<行数> | 输出文件尾部n行内容                  |
| -f       | 显示文件最新追加的内容，监视文件变化 |



### 4.1.8覆盖>和追加>>

基本语法：

（1）ll >文件		（功能描述：列表的内容写入文件a.txt中（**覆盖写**））

（2）ll >>文件		（功能描述：列表的内容**追加**到文件aa.txt的末尾）

（3）cat 文件1 > 文件2	（功能描述：将文件1的内容覆盖到文件2）

（4）echo “内容” >> 文件

将ls查看信息写入到文件中:

```shell
ls -l>houge.txt
```

将ls查看信息追加到文件中

```shell
ls -l>>houge.txt
```

采用echo将hello单词追加到文件中

```shell
echo hello>>houge.txt
```



### 4.1.9ln软链接

软链接也称为符号链接，类似于windows里的快捷方式，有自己的数据块，主要存放了链接其他文件的路径。

基本语法：

ln -s [原文件或目录] [软链接名]		（功能描述：给原文件创建一个软链接）

经验技巧：

- 删除软链接： rm -rf 软链接名，而不是rm -rf 软链接名/，加/会把它看作目录
- 查询：通过ll就可以查看，列表属性第1位是l，尾部会有位置指向。

案例实操：

（1）创建软连接

```shell
mv houge.txt xiyou/dssz/
```

```shell
ln -s xiyou/dssz/houge.txt ./houzi
```

```shell
ll
```

（2）删除软连接

```shell
rm -rf houzi
```

（3）进入软连接实际物理路径

```shell
ln -s xiyou/dssz/ ./dssz
```

```shell
cd -P dssz/
```





## 4.2时间日期类

### 4.2.1显示系统时间

语法格式：date [OPTION]... [+FORMAT]

基本语法：

（1）date								（功能描述：显示当前时间）

（2）date +%Y							（功能描述：显示当前年份）

（3）date +%m							（功能描述：显示当前月份）

（4）date +%d							（功能描述：显示当前是哪一天）

（5）date "+%Y-%m-%d %H:%M:%S"		（功能描述：显示年月日时分秒）

案例实操：

（1）显示当前时间信息

```shell
date
```

（2）显示当前时间年月日

```shell
date +%Y%m%d
```

（3）显示当前时间年月日时分秒

```shell
date "+%Y-%m-%d %H:%M:%S"
```



### 4.2.2设置系统时间

基本语法：date -s 字符串时间

案例实操：

（1）设置系统当前时间

```shell
date -s "2017-06-19 20:52:18"
```



## 4.3用户管理命令

### 4.3.1添加用户

基本语法：

（1）useradd 用户名			（功能描述：添加新用户）

（2）useradd -g 组名 用户名	（功能描述：添加新用户到某个组）

案例实操：

（1）添加一个用户

```shell
useradd tangseng
```

```shell
ll /home/
```



### 4.3.2设置用户密码★

基本语法：passwd 用户名	（功能描述：设置用户密码）

案例实操:

（1）设置用户的密码

```shell
passwd tangseng
```



### 4.3.3查看所有用户

案例实操:

```shell
cat  /etc/passwd
```





### 4.3.4删除用户

基本语法：

（1）userdel  用户名		（功能描述：删除用户但保存用户主目录）

（2）userdel -r 用户名		（功能描述：用户和用户主目录，都删除）

选项说明：

| 选项 | 功能                                       |
| ---- | ------------------------------------------ |
| -r   | 删除用户的同时，删除与用户相关的所有文件。 |

案例实操：

（1）删除用户但保存用户主目录

```shell
userdel tangseng
```

```shell
ll /home/
```

（2）删除用户和用户主目录，都删除

```shell
useradd zhubajie
```

```shell
ll /home/
```

```shell
userdel -r zhubajie
```

```shell
ll /home/
```



### 4.3.5设置普通用户为root权限

设置步骤：

（1）添加atguigu用户，并对其设置密码。

```shell
useradd atguigu
```

```shell
passwd atguigu
```

（2）修改配置文件

```shell
vim /etc/sudoers
```

修改 /etc/sudoers 文件，找到下面一行(91行)，在root下面添加一行，如下所示：

```shell
## Allow root to run any commands anywhere
root    ALL=(ALL)     ALL
atguigu   ALL=(ALL)     ALL
```

或者配置成采用sudo命令时，不需要输入密码

```shell
## Allow root to run any commands anywhere
root      ALL=(ALL)     ALL
atguigu   ALL=(ALL)     NOPASSWD:ALL
```

修改完毕，现在可以用atguigu帐号登录，然后用命令 sudo ，即可获得root权限进行操作。

（3）案例实操：

用普通用户在/opt目录下创建一个文件夹：

```shell
sudo mkdir module
```

```
chown atguigu:atguigu module/
```



### 4.3.6修改用户组

基本语法：usermod -g 用户组 用户名

选项说明：

| 选项 | 功能                                   |
| ---- | -------------------------------------- |
| -g   | 修改用户的初始登录组，给定的组必须存在 |

案例实操：

（1）将用户加入到用户组

```shell
usermod -g root zhubajie
```





## 4.4用户组管理命令

每个用户都有一个用户组，系统可以对一个用户组中的所有用户进行集中管理。不同Linux 系统对用户组的规定有所不同，

如Linux下的用户属于与它同名的用户组，这个用户组在创建用户时同时创建。

用户组的管理涉及用户组的添加、删除和修改。组的增加、删除和修改实际上就是对/etc/group文件的更新。

### 4.4.1groupadd 新增组

基本语法：groupadd 组名

案例实操：

（1）添加一个xitianqujing组

```shell
groupadd xitianqujing
```



### 4.4.2groupdel 删除组

基本语法：groupdel 组名

案例实操：

（1）删除xitianqujing组

```shell
groupdel xitianqujing
```



### 4.4.3groupmod 修改组

基本语法：groupmod -n 新组名 老组名

选项说明：

| 选项       | 功能描述           |
| ---------- | ------------------ |
| -n<新组名> | 指定工作组的新组名 |



### 4.4.4查看组

案例实操：

```shell
cat  /etc/group
```





## 4.5文件权限类

### 4.5.1文件权限修改★

文件的权限划分：r=4 w=2 x=1  

如果没有权限，就会出现减号[ - ]而已。从左至右用0-9这些数字来表示:

（1）0首位表示类型

- 在Linux中第一个字符代表这个文件是目录、文件或链接文件等等
  - \- 代表文件
  -  d 代表目录
  -  l 链接文档(link file)；

（2）第1-3位确定属主（该文件的所有者）拥有该文件的权限。---User

（3）第4-6位确定属组（所有者的同组用户）拥有该文件的权限，---Group

（4）第7-9位确定其他用户拥有该文件的权限 ---Other

案例实操:

（1）修改文件使其所属主用户具有执行权限

```shell
cp xiyou/dssz/houge.txt ./
```

```shell
chmod u+x houge.txt
```

（2）修改文件使其所属组用户具有执行权限

```shell
chmod g+x houge.txt
```

（3）修改文件所属主用户执行权限,并使其他用户具有执行权限

```shell
chmod u-x,o+x houge.txt
```

（4）采用数字的方式，设置文件所有者、所属组、其他用户都具有可读可写可执行权限。

```shell
chmod 777 houge.txt
```

（5）修改整个文件夹里面的所有文件的所有者、所属组、其他用户都具有可读可写可执行权限。

```shell
chmod -R 777 xiyou/
```



### 4.5.2chown 改变所有者

基本语法：

chown [选项] [最终用户] [文件或目录]		（功能描述：改变文件或者目录的所有者）

选项说明：

| 选项 | 功能     |
| ---- | -------- |
| -R   | 递归操作 |

案例实操:

（1）修改文件所有者

```shell
chown atguigu houge.txt 
```

（2）递归改变文件所有者和所有组

```shell
chown -R atguigu:atguigu xiyou/
```



### 4.5.3chgrp 改变所属组

基本语法：

chgrp [最终用户组] [文件或目录]	（功能描述：改变文件或者目录的所属组）

案例实操：

（1）修改文件的所属组

```shell
chgrp root houge.txt
```





## 4.6搜索查找类

### 4.6.1find查找文件或者目录★

find指令将从指定目录向下递归地遍历其各个子目录，将满足条件的文件显示在终端。

基本语法：find [搜索范围] [选项]

选项说明：

| 选项            | 功能                             |
| --------------- | -------------------------------- |
| -name<查询方式> | 按照指定的文件名查找模式查找文件 |
| -user<用户名>   | 查找属于指定用户名所有文件       |
| -size<文件大小> | 按照指定的文件大小查找文件。     |

案例实操：

（1）按文件名：根据名称查找/目录下的filename.txt文件。

```shell
find xiyou/ -name “*.txt”
```

（2）按拥有者：查找/opt目录下，用户名称为-user的文件

```shell
find opt/ -user atguigu
```

（3）按文件大小：在/home目录下查找大于200M的文件（+n 大于  -n小于  n等于）

```shell
find /home -size +200M
```



### 4.6.2grep 过滤查找及管道符

管道符，“|”，表示将前一个命令的处理结果输出传递给后面的命令处理

基本语法：grep 选项 查找内容 源文件

选项说明：

| 选项 | 功能               |
| ---- | ------------------ |
| -n   | 显示匹配行及行号。 |

案例实操：

（1）查找某文件在第几行

```shell
ls | grep -n test
```



### 4.6.3which查找命令

查找命令在那个目录下

基本语法：which 命令

案例实操：

```shell
which ll
```



## 4.7压缩和解压类★

### 4.7.1gzip/gunzip 压缩

基本语法：

（1）gzip 文件		（功能描述：压缩文件，只能将文件压缩为*.gz文件）

（2）gunzip 文件.gz	（功能描述：解压缩文件命令）

经验技巧：

（1）**只能压缩文件**不能压缩目录

（2）**不保留原来的文件**

案例实操：

（1）gzip压缩

```shell
gzip houge.txt
```

（2）gunzip解压缩文件

```shell
gunzip houge.txt.gz 
```



### 4.7.2zip/unzip 压缩

基本语法：

（1）zip  [选项] XXX.zip  将要压缩的内容 		（功能描述：压缩文件和目录的命令）

（2）unzip [选项] XXX.zip						（功能描述：解压缩文件）

选项说明：

| zip选项 | 功能     |
| ------- | -------- |
| -r      | 压缩目录 |

| unzip选项 | 功能                     |
| --------- | ------------------------ |
| -d<目录>  | 指定解压后文件的存放目录 |

经验技巧：

（1）zip 压缩命令在window/linux都通用，**可以压缩目录且保留源文件**。

案例实操：

（1）压缩 1.txt 和2.txt，压缩后的名称为mypackage.zip 

```shell
zip mypackage.zip  1.txt 2.txt 
```

（2）解压 mypackage.zip

```shell
unzip  mypackage.zip
```

（3）解压mypackage.zip到指定目录-d

```shell
unzip  mypackage.zip -d /opt
```



### 4.7.3tar/tar.gz 打包★

基本语法：

tar  [选项]  XXX.tar.gz  将要打包进去的内容		（功能描述：打包目录，压缩后的文件格式.tar.gz）

选项说明：

| 选项   | 功能                 |
| ------ | -------------------- |
| -z     | 打包同时压缩         |
| **-c** | **产生.tar打包文件** |
| -v     | 显示详细信息         |
| -f     | 指定压缩后的文件名   |
| **-x** | **解包.tar文件**     |

案例实操：

（1）压缩多个文件

```shell
tar -zcvf houma.tar.gz houge.txt bailongma.txt 
```

（2）压缩目录

```shell
tar -zcvf xiyou.tar.gz xiyou/
```

（3）解压到当前目录

```shell
tar -zxvf houma.tar.gz
```

（4）解压到指定目录

```shell
tar -zxvf xiyou.tar.gz -C /opt
```





## 4.8磁盘分区类

### 4.8.1fidsk查看分区

基本语法：

（1）fdisk -l		（功能描述：查看磁盘分区详情）

选项说明：

| 选项 | 功能                   |
| ---- | ---------------------- |
| -l   | 显示所有硬盘的分区列表 |

经验技巧：

（1）该命令必须在root用户下才能使用

功能说明：

- Device：分区序列
- Boot：引导
- Start：从X磁柱开始
- End：到Y磁柱结束
- Blocks：容量
- Id：分区类型ID
- System：分区类型



### 4.8.2 mount挂载★

基本语法：

（1）mount [-t vfstype] [-o options] device dir	（功能描述：挂载设备）

（2）umount 设备文件名或挂载点			（功能描述：卸载设备）

参数说明：

| 参数       | 功能                                                         |
| ---------- | ------------------------------------------------------------ |
| -t vfstype | 指定文件系统的类型，通常不必指定。mount 会自动选择正确的类型。常用类型有：光盘或光盘镜像：iso9660DOS fat16文件系统：msdos[Windows](http://blog.csdn.net/hancunai0017/article/details/6995284) 9x fat32文件系统：vfatWindows NT ntfs文件系统：ntfsMount Windows文件[网络](http://blog.csdn.net/hancunai0017/article/details/6995284)共享：smbfs[UNIX](http://blog.csdn.net/hancunai0017/article/details/6995284)(LINUX) 文件网络共享：nfs |
| -o options | 主要用来描述设备或档案的挂接方式。常用的参数有：loop：用来把一个文件当成硬盘分区挂接上系统ro：采用只读方式挂接设备rw：采用读写方式挂接设备　  iocharset：指定访问文件系统所用字符集 |
| device     | 要挂接(mount)的设备                                          |
| dir        | 设备在系统上的挂接点(mount point)                            |

案例实操：

（1）挂载光盘镜像文件，设备/dev/cdrom挂载到 挂载点 /mnt/cdrom中

```shell
mount -t iso9660 /dev/cdrom /mnt/cdrom/	
```

（2）卸载光盘镜像文件

```shell
umount /mnt/cdrom
```

设置开机自动挂载：编辑/etc/fstab文件，在最后一行添加如下内容

```shell
vim /etc/fstab
```

```shell
/dev/cdrom		/mnt/cdrom/			iso9660			defaults		0	0	0
```





## 4.9进程线程类

进程是正在执行的一个程序或命令，每一个进程都是一个运行的实体，都有自己的地址空间，并占用一定的系统资源。

### 4.9.1**ps** 查看系统进程★

基本语法：

（1）ps -aux | grep xxx		（功能描述：查看系统中所有进程）

（2）ps -ef | grep xxx		（功能描述：可以查看子父进程之间的关系）

选项说明：

| 选项 | 功能                   |
| ---- | ---------------------- |
| -a   | 选择所有进程           |
| -u   | 显示所有用户的所有进程 |
| -x   | 显示没有终端的进程     |

功能说明：

- ps -aux显示信息说明：
  - USER：该进程是由哪个用户产生的
  - PID：进程的ID号
  - %CPU：该进程占用CPU资源的百分比，占用越高，进程越耗费资源；
  - %MEM：该进程占用物理内存的百分比，占用越高，进程越耗费资源；
  - VSZ：该进程占用虚拟内存的大小，单位KB；
  - RSS：该进程占用实际物理内存的大小，单位KB；
  - TTY：该进程是在哪个终端中运行的。其中tty1-tty7代表本地控制台终端，tty1-tty6是本地的字符界面终端，tty7是图形终端。pts/0-255代表虚拟终端。
  - STAT：进程状态。常见的状态有：R：运行、S：睡眠、T：停止状态、s：包含子进程、+：位于后台
  - START：该进程的启动时间
  - TIME：该进程占用CPU的运算时间，注意不是系统时间
  - COMMAND：产生此进程的命令名

- ps -ef显示信息说明
  - UID：用户ID 
  - PID：进程ID 
  - PPID：父进程ID 
  - C：CPU用于计算执行优先级的因子。数值越大，表明进程是CPU密集型运算，执行优先级会降低；数值越小，表明进程是I/O密集型运算，执行优先级会提高 
  - STIME：进程启动的时间 
  - TTY：完整的终端名称 
  - TIME：CPU时间 
  - CMD：启动进程所用的命令和参数

经验技巧：

（1）如果想查看进程的CPU占用率和内存占用率，可以使用aux;

（2）如果想查看进程的父进程ID可以使用ef;

案例实操：

（1）查看cpu：

```shell
ps aux
```

（2）查看进程：

```shell
ps -ef
```



### 4.9.2kill 终止进程★

基本语法

（1）kill -9 进程号		（功能描述：通过进程号杀死进程）

（2）killall 进程名称			（功能描述：通过进程名称杀死进程，也支持通配符，这在系统因负载过大而变得很慢时很有用）	

案例实操：

（1）杀死浏览器进程

```shell
kill -9 5102
```

（2）通过进程名称杀死进程

```shell
killall firefox
```





# 5 定时任务

## 5.1设置定时任务★

基本语法：crontab [选项]

选项说明：

| 选项 | 功能                          |
| ---- | ----------------------------- |
| -e   | 编辑crontab定时任务           |
| -l   | 查询crontab任务               |
| -r   | 删除当前用户所有的crontab任务 |

参数说明：

（1）crontab配置参数

| 项目      | 含义                 | 范围                    |
| --------- | -------------------- | ----------------------- |
| 第一个“*” | 一小时当中的第几分钟 | 0-59                    |
| 第二个“*” | 一天当中的第几小时   | 0-23                    |
| 第三个“*” | 一个月当中的第几天   | 1-31                    |
| 第四个“*” | 一年当中的第几月     | 1-12                    |
| 第五个“*” | 一周当中的星期几     | 0-7（0和7都代表星期日） |

（2）特殊符号

| 特殊符号 | 含义                                                         |
| -------- | ------------------------------------------------------------ |
| *        | 代表任何时间。比如第一个“*”就代表一小时中每分钟都执行一次的意思。 |
| ，       | 代表不连续的时间。比如“0 8,12,16 * * * 命令”，就代表在每天的8点0分，12点0分，16点0分都执行一次命令 |
| -        | 代表连续的时间范围。比如“0 5  *  *  1-6命令”，代表在周一到周六的凌晨5点0分执行命令 |
| */n      | 代表每隔多久执行一次。比如“*/10  *  *  *  *  命令”，代表每隔10分钟就执行一遍命令 |

（3）特定时间执行命令

| 时间              | 含义                                                         |
| ----------------- | ------------------------------------------------------------ |
| 45 22 * * * 命令  | 在22点45分执行命令                                           |
| 0 17 * * 1 命令   | 每周1 的17点0分执行命令                                      |
| 0 5 1,15 * * 命令 | 每月1号和15号的凌晨5点0分执行命令                            |
| 40 4 * * 1-5 命令 | 每周一到周五的凌晨4点40分执行命令                            |
| */10 4 * * * 命令 | 每天的凌晨4点，每隔10分钟执行一次命令                        |
| 0 0 1,15 * 1 命令 | 每月1号和15号，每周1的0点0分都会执行命令。注意：星期几和几号最好不要同时出现，因为他们定义的都是天。非常容易让管理员混乱。 |

案例实操：

（1）每隔1分钟，向/root/bailongma.txt文件中添加一个11的数字

```shell
crontab -e 
```

```shell
*/1 * * * * /bin/echo ”11” >> /root/bailongma.txt
```



 

# 6 软件包管理

## 6.1RPM

RPM（RedHat Package Manager），RedHat软件包管理工具

### 6.1.1 RPM查询命令

基本语法：

rpm -qa				（功能描述：查询所安装的所有rpm软件包）

经验技巧：

（1）由于软件包比较多，一般都会采取过滤。**rpm -qa | grep rpm**软件包

案例实操:

（1）查询firefox软件安装情况

```shell
rpm -qa |grep firefox 
```



### 6.1.2RPM卸载命令

基本语法：

（1）rpm -e RPM软件包  

（2） **rpm -e --nodeps 软件包** 

选项说明：

| 选项     | 功能                                                         |
| -------- | ------------------------------------------------------------ |
| -e       | 卸载软件包                                                   |
| --nodeps | 卸载软件时，不检查依赖。这样的话，那些使用该软件包的软件在此之后可能就不能正常工作了。 |

案例实操：

（1）卸载firefox软件

```shell
rpm -e firefox
```



### 6.1.3RPM安装命令★

基本语法：**rpm -ivh RPM包全名**

选项说明：

| 选项     | 功能                     |
| -------- | ------------------------ |
| -i       | -i=install，安装         |
| -v       | -v=verbose，显示详细信息 |
| -h       | -h=hash，进度条          |
| --nodeps | --nodeps，不检测依赖进度 |

案例实操

（1）安装firefox软件

```shell
rpm -ivh firefox-45.0.1-1.el6.centos.x86_64.rpm 
```



## 6.2YUM

### 6.2.1YUM概述

YUM（全称为 Yellow dog Updater, Modified）是一个在Fedora和RedHat以及CentOS中的Shell前端软件包管理器。基于RPM包管理，能够从指定的服务器自动下载RPM包并且安装，可以自动处理依赖性关系，并且一次安装所有依赖的软件包，无须繁琐地一次次下载、安装。



## 6.2.1YUM常用命令★

基本语法：yum [选项] [参数]

选项说明：

| 选项 | 功能                  |
| ---- | --------------------- |
| -y   | 对所有提问都回答“yes” |

参数说明：

| 参数         | 功能                          |
| ------------ | ----------------------------- |
| install      | 安装rpm软件包                 |
| update       | 更新rpm软件包                 |
| check-update | 检查是否有可用的更新rpm软件包 |
| remove       | 删除指定的rpm软件包           |
| list         | 显示软件包信息                |
| clean        | 清理yum过期的缓存             |
| deplist      | 显示yum软件包的所有依赖关系   |

案例实操实操：

（1）采用yum方式安装firefox

```shell
yum -y install firefox.x86_64
```





# 7 软件安装

## 7.1安装JDK★

安装步骤：先卸妆、再安装、再配环境变量

卸妆：

（1）使用下面的命令查询系统中自带的jdk

```shell
rpm -qa | grep jdk
```

（2）使用下面的命令卸载自带的jdk，全都卸载掉

```shell
rpm -e –nodeps jdk的rpm软件包的名字
```

安装：

（3）将jdk的tar.gz的压缩包上传到/opt目录下

（4）将jdk压缩包解压缩

```shell
tar -zxvf jdk-8u152-linux-x64.tar.gz
```

配置环境变量：

（5）在/etc/profile.d目录下创建my_env.sh文件

```shell
vim /etc/profile.d/my_env.sh
```

（6）在my_env.sh文件中配置JAVA_HOME环境变量

```sh
#配置JAVA_HOME
JAVA_HOME=/opt/jdk1.8.0_152
#将JAVA_HOME/bin追加到PATH内
PATH=$PATH:$JAVA_HOME/bin
#将环境变量提升为全局
export PATH JAVA_HOME
```

（7）执行source /etc/profile.d/my_env.sh使环境变量立即生效，如果不生效就重启XShell

```shell
source /etc/profile.d/my_env.sh
```



## 7.2安装Tomcat★

安装步骤：

（1）将tomcat的压缩包上传到/opt目录下

（2）解压缩tomcat的压缩包

```shell
tar -zxvf apache-tomcat-7.0.70.tar.gz
```

（3）进入tomcat的bin目录执行./startup.sh启动tomcat服务器

（4）也可以配置tomcat的环境变量，这样就可以在任意目录下执行startup.sh启动tomcat了

```sh
#配置JAVA_HOME
JAVA_HOME=/opt/jdk1.8.0_152
#将JAVA_HOME/bin追加到PATH内
PATH=$PATH:$JAVA_HOME/bin
#配置TOMCAT_HOME
TOMCAT_HOME=/opt/apache-tomcat-8.5.82
#将TOMCAT_HOME/bin追加到PATH内
PATH=$PATH:$TOMCAT_HOME/bin
#将环境变量提升为全局
export PATH JAVA_HOME TOMCAT_HOME
```

（5）启动/关闭tomcat

```shell
startup.sh 
```

```shell
shutdown.sh 
```



## 7.3安装MySql★

安装步骤：

（1）卸载自带的Mysql-libs（如果之前安装过mysql，要全都卸载掉）

```shell
rpm -qa | grep mariadb
```

```shell
rpm -e --nodeps mariadb-libs
```

（2）在/opt目录下创建MySQL目录

（3）将mysql的压缩包上传到opt目录下,并解压

```shell
tar -zxvf mysql8.tar.gz
```

（4）按照标号顺序依次安装rpm软件包，其余rpm包不需要安装

```shell
rpm -ivh mysql-community-common-8.0.30-1.el7.x86_64.rpm
```

```shell
rpm -ivh mysql-community-client-plugins-8.0.30-1.el7.x86_64.rpm
```

```shell
rpm -ivh mysql-community-libs-8.0.30-1.el7.x86_64.rpm
```

```shell
rpm -ivh mysql-community-client-8.0.30-1.el7.x86_64.rpm
```

```shell
rpm -ivh mysql-community-icu-data-files-8.0.30-1.el7.x86_64.rpm
```

```shell
rpm -ivh mysql-community-server-8.0.30-1.el7.x86_64.rpm
```

注意：若是云服务器，最后一步安装可能会报错

```shell
[root@centos mysql8]# rpm -ivh mysql-community-server-8.0.30-1.el7.x86_64.rpm
warning: mysql-community-server-8.0.30-1.el7.x86_64.rpm: Header V4 RSA/SHA256 Signature, key ID 3a79bd29: NOKEY
error: Failed dependencies:
        libaio.so.1()(64bit) is needed by mysql-community-server-8.0.30-1.el7.x86_64
        libaio.so.1(LIBAIO_0.1)(64bit) is needed by mysql-community-server-8.0.30-1.el7.x86_64
        libaio.so.1(LIBAIO_0.4)(64bit) is needed by mysql-community-server-8.0.30-1.el7.x86_64
```

原因及解决方案：安装阿里云服务器时提示错误，而使用yum安装时，却发现默认安装的是32位的 libaio

```shell
解决方案：
最后到Centos官网下载安装： http://mirror.centos.org/centos/7/os/x86_64/Packages/

wget http://mirror.centos.org/centos/7/os/x86_64/Packages/libaio-0.3.109-13.el7.x86_64.rpm
wget http://mirror.centos.org/centos/7/os/x86_64/Packages/libaio-devel-0.3.109-13.el7.x86_64.rpm 

安装第一个包
rpm -ivh libaio-0.3.109-13.el7.x86_64.rpm

安装第二个包
rpm -ivh libaio-devel-0.3.109-13.el7.x86_64.rpm

再次安装Mysql，安装成功
rpm -ivh mysql-community-server-8.0.30-1.el7.x86_64.rpm
```

（5）初始化MySQL

```shell
mysqld --initialize --console
```

（6）修改mysql的权限，/var/lib/mysql/是默认安装目录，将该目录下所属用户和所属组都改成mysql⚠️

```shell
chown -R mysql:mysql /var/lib/mysql/
```

（7）查看MySQL的临时密码

```shell
cat /var/log/mysqld.log | grep localhost
```

（8）启动MySQL服务

启动服务

```shell
systemctl start mysqld
```

查看是否启动成功

```shell
systemctl status mysqld
```

（9）使用临时密码连接MySQL之后修改密码

连接数据库方式1：需要回车输入密码（首次设置密码使用这种）

```shell
mysql -uroot -p
```

连接数据库方式2：-p后紧跟密码

```shell
mysql -uroot -p密码
```

使用SQL语句修改root用户密码

```sql
alter user 'root'@'localhost' identified by 'root用户的密码';
```

（10）修改root用户的登录IP限制，%表示允许任何IP登录

先到mysql数据库

```sql
use mysql;
```

再允许任何IP都可得访问该库，不过该操作十分危险，一般只允许自己常用的运维主机IP登录，小心SQL勒索病毒⚠️

```sql
update user set host ='%' where user='root';
```

（11）输入exit退出，在命令行中重启MySql服务

```shell
systemctl restart mysqld
```

（12）针对旧版本连接工具，不支持mysql8加密方法，导致无法远程登录。

更改mysql5的加密方式，修改root用户的登录IP限制

```sql
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root';
```

然后刷新下权限

```sql
FLUSH PRIVILEGES;
```

最后重启下mysql

```shell
systemctl restart mysqld
```

