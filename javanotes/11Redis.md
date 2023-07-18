# 1 NoSQL数据库简介

## 1.1NoSQL数据库概述

NoSQL(NoSQL = Not Only SQL )，意即“不仅仅是SQL”，泛指非关系型的数据库。 

NoSQL 不依赖业务逻辑方式存储，而以简单的key-value模式存储。因此大大的增加了数据库的扩展能力。

特点：

- 不遵循SQL标准。
- 不支持ACID。
- 远超于SQL的性能。

适用场景：

- 对数据高并发的读写
- 海量数据的读写
- 对数据高可扩展性

不适用场景：

- 需要事务支持
- 基于sql的结构化查询存储，处理复杂的关系，需要即席查询。(多条件灵活查询，快速返回查询的精确结果)



## 1.2常见的非关系型数据库

Memcache：

- 很早出现的NoSql数据库
- 数据都在内存中，一般不持久化
- 支持简单的key-value模式，支持类型单一
- 一般是作为缓存数据库辅助持久化的数据库

Redis：

- 几乎覆盖了Memcached的绝大部分功能
- 数据都在内存中，支持持久化，主要用作备份恢复
- 除了支持简单的key-value模式，还支持多种数据结构的存储，比如 list、set、hash、zset等。
- 一般是作为缓存数据库辅助持久化的数据库

MongoDB：

- 高性能、开源、模式自由(schema  free)的文档型数据库
- 数据都在内存中， 如果内存不足，把不常用的数据保存到硬盘
- 虽然是key-value模式，但是对value（尤其是json）提供了丰富的查询功能
- 支持二进制数据及大型对象
- 可以根据数据的特点替代RDBMS ，成为独立的数据库。或者配合RDBMS，存储特定的数据。



# 2 Redis安装

Redis官方网站：http://redis.io

Redis中文官方网站：http://redis.cn

## 2.1Redis安装步骤

安装步骤：

① 准备工作：下载安装最新版的gcc编译器

安装C 语言的编译环境 

```shell
yum -y install gcc
```

测试 gcc版本 

```shell
gcc --version
```

② 下载redis-6.2.1.tar.gz放/opt目录

```shell
scp ./redis-6.2.1.tar.gz root@39.106.35.112:/opt
```

③ 解压：tar -zxvf redis-6.2.1.tar.gz

```shell
tar -zxvf redis-6.2.1.tar.gz
```

④ 进入到redis解压缩后的目录

```shell
cd redis-6.2.1
```

⑤ 执行make编译好

```shell
make
```

⑥ 如果没有准备好C语言编译环境，make 会报错`—Jemalloc/jemalloc.h：没有那个文件`

解决方案：安装gcc后，运行make distclean，然后再进行make

注意：若之前在步骤① 中已安装好gcc，第⑥ 步完全省略⚠️

```shell
make distclean
```

```shell
make
```

⑦ 在redis-6.2.1目录下再次执行make命令编译

```shell
make 
```

⑧ 跳过make test 继续执行: make install

```shell
make install
```



## 2.2常用命令★

查看默认安装目录：/usr/local/bin

常用命令：

- pkill redis：关闭所有Redis服务端
- redis-benchmark：性能测试工具，可以在自己本子运行，看看自己本子性能如何
- redis-check-aof：修复有问题的AOF文件，rdb和aof后面讲
- redis-check-dump：修复有问题的dump.rdb文件
- redis-sentinel：Redis集群使用
- redis-server：Redis服务器启动命令
- redis-cli：客户端，操作入口
- 使用redis-cli进入Redis后，输入ping命令查看Redis是否启动



## 2.3设置后台启动★

设置步骤：

① 备份redis.conf

在/root目录下创建myredis目录

```shell
mkdir myredis
```

拷贝一份redis.conf到myredis目录

```shell
cp  /opt/redis-6.2.1/redis.conf  /root/myredis
```

② 修改配置

注释掉第75行，bind 127.0.0.1，表示只能本机连接

```shell
#bind 127.0.0.1 -::1
```

修改第94行，protected-mode yes改成no，表示无密码方式登录，并允许连接。

```shell
protected-mode no
```

修改第247daemonize no改成yes（L247）让服务在后台启动

```shell
daemonize yes
```

③ Redis启动

```shell
redis-server /root/myredis/redis.conf 
```

④ 用客户端访问

```shell
redis-cli 
```

多个端口可以指定端口登录

```shell
redis-cli -p 6379
```

⑤ Redis关闭

单实例关闭：

```shell
redis-cli shutdown
```

也可以进入终端后关闭当前实例

```shell
redis-cli
```

```shell
shutdown
```

 多实例时关闭指定实例，指定端口关闭

```shell
redis-cli -p 6379 shutdown
```



## 2.4Redis相关知识

默认配置：

- 默认端口：6379
- 默认16个数据库，类似数组下标从0开始，初始默认使用0号库
- 使用命令 select  <dbid>来切换数据库。如: select 8 
- 统一密码管理，所有库同样密码。
- dbsize查看当前数据库的key的数量
- flushdb清空当前库
- flushall通杀全部库

Redis是单线程+多路IO复用技术

多路复用是指使用一个线程来检查多个文件描述符（Socket）的就绪状态，比如调用select和poll函数，传入多个文件描述符，如果有一个文件描述符就绪，则返回，否则阻塞直到超时。得到就绪状态后进行真正的操作可以在同一个线程里执行，也可以启动线程执行（比如使用线程池）





# 3 常用五大数据类型

## 3.1Redis键(key)★

常用命令：

keys *查看当前库所有key

```shell
keys *
```

使用通配符匹配当前库内符合条件的key

```shell
keys hello*
```

判断某个key是否存在，返回1或0

```shell
exists key
```

查看key是什么类型，返回类型

```shell
type key 
```

删除指定的key数据，返回1或0

```shell
del key
```

根据value选择非阻塞删除 ⚠️

- 仅将keys从keyspace元数据中删除，真正的删除会在后续异步操作。

```shell
unlink key
```

为给定的key设置过期时间，单位秒，过期后就获取不到了

```shell
expire key 10
```

查看还有多少秒过期，-1表示永不过期，-2表示已过期

```shell
ttl key
```

select命令切换数据库，范围[0,15]

```shell
select 0
```

查看当前数据库的key的数量

```shell
dbsize
```

清空当前库

```shell
flushdb
```

清空全部库

```shell
flushall
```





## 3.2Redis字符串(String)★

概述：

- String是Redis最基本的类型，可以理解成与Memcached一模一样的类型，一个key对应一个value。
- String类型是二进制安全的。意味着Redis的string可以包含任何数据。比如jpg图片或者序列化的对象。
- String类型是Redis最基本的数据类型，一个Redis中字符串value最多可以是512M

常用命令：

命令：set  <key><value>

作用：设置字符串key和value的映射关系

```shell
set  <key>< value>
```

- 可选参数EX seconds ： 将键的过期时间设置为 seconds 秒

  - k1在10秒后会过期

  ```shell
  set k1 v1 EX 10
  ```

- 可选参数PX milliseconds ： 将键的过期时间设置为 milliseconds 毫秒

  - k1在5000毫秒后会过期

  ```shell
  set k1 v1 PX 5000
  ```

- 可选参数NX ： 只在键不存在时， 才对键进行设置操作，只新增不修改

  - 当k1不存在时，k1才会等于v100，否则直接修改原来的值

  ```shell
  set k1 v100 NX
  ```

- 可选参数XX ： 只在键已经存在时， 才对键进行设置操作，只修改不新增

  - 当k1存在时，直接修改原来的值，若不存在该命令等同于无效

  ```shell
  set k1 v100 XX
  ```

- 返回值OK：SET 命令只在设置操作成功完成时才返回 OK 

- 返回值NULL Bulk Reply：如果命令使用了 NX或者 XX 选项， 但是因为条件没达到而造成设置操作未执行，返回NULL Bulk Reply

命令：get  <key>

作用：通过key查询对应的value

```shell
get	key
```

命令：append  <key><value>

作用：将给定的value追加到原值的末尾，返回value追加后的长度

```shell
append k1 v1
```

命令：strlen  <key>

作用：获得值的长度

```shell
strlen k1
```

命令：setnx  <key><value>（即将废弃）

作用：只有在 key 不存在时，设置 key 的值，只新增不修改，等同于参数NX

```shell
setnx k1 v1
```

命令：incr  <key>

作用：只能对数值型字符串操作，使数值自增1

```shell
incr k1
```

命令：decr  <key>

作用：只能对数值型字符串操作，使数值自减1

```shell
decr k1
```

命令：incrby / decrby  <key><步长>

作用：将 key 中储存的数字值自增自减。自定义步长

```shell
incrby k1 5
```

```shell
decrby k1 3
```

`注意：以上所有的自增自减操作具有原子性，一旦开始操作就会一直运行到结束，中间不会切换到另一个线程。⚠️`

命令：mset  <key1><value1><key2><value2>  ..... 

作用：同时设置一个或多个 key-value

```shell
mset k1 v1 k2 v2 k3 v3
```

命令：mget  <key1><key2><key3> .....

作用：同时获取一个或多个 value  

```shell
mget k1 k2 k3
```

命令：msetnx <key1><value1><key2><value2>  ..... 

作用：同时设置一个或多个 key-value，当且仅当所有给定 key 都不存在，只新增不修改

```shell
msetnx k1 v1 k2 v2
```

`注意：以上批处理操作具有原子性，一个失败则都失败。⚠️`

命令：getrange  <key><起始位置><结束位置>

作用：获得值的范围，类似java中的substring，前包，后包

```shell
getrange k1 0 3
```

命令：setrange  <key><起始位置><value>

作用：设置修改范围，从0开始替字符java，替换数量取决于字符长度

```shell
setrange k1 0 java
```

命令：setex  <key><过期时间><value>

作用：设置键值的同时，设置过期时间，单位秒

```shell
setex k1 10 v1
```

命令：getset <key><value>

作用：以旧换新，设置新值，返回旧值

```shell
getset k1 v100
```



## 3.3Redis列表(List)★

常用命令：

命令：lpush  <key><value1><value2><value3> .... 

作用：从左边插入一个或多个值，倒序插入，返回集合的元素个数

```shell
lpush k1 a b c d e
```

命令：rpush  <key><value1><value2><value3> .... 

作用：从右边插入一个或多个值，正序插入，返回集合的元素个数

```shell
rpush k1 1 2 3 4 5
```

命令：lpop  <key>

作用：从左边(头部)吐出一个值，直到吐光所有值

```shell
lpop k1
```

命令：rpop  <key>

作用：从右边(尾部)吐出一个值，直到吐光所有值

```shell
rpop k1
```

命令：rpoplpush  <key1><key2>

作用：从列表k1右边(尾部)吐出一个值，插到k2列表左边(头部)，返回吐出的值

```shell
rpoplpush k1 k2
```

命令：lrange <key><start><stop>

作用：按照索引下标获得元素(从左到右)，可以写多

```shell
lrange k1 0 10
```

命令：lrange <key><-max><-min>

作用：lrange另一种用法，k1的值最右边为-1，向左依次为-2、-3、-4

```shell
lrange k1 -100 -1
```

命令：lrange <key><0><-1>

作用：lrange获取所有，0代表第一个，-1代表最后一个

```shell
lrange k1 0 -1
```

命令：lindex <key><index>

作用：按照索引下标获得元素(从左到右)

```shell
lindex k1 0
```

命令：llen <key>

作用：获得列表长度 

```shell
llen k1
```

命令：linsert <key>  before <value><newvalue>

作用：在k1列表的某个value的前面插入新的value值，返回插入后的长度

```shell
linsert k1  before a b
```

命令：lrem <key><n><value>

作用：从左边(头部)删除n个value(从左到右)，返回删除这些value后的长度

```shell
lrem k1 2 b
```

命令：lset<key><index><value>

作用：将列表key下标为index的值替换成value

```shell
lset k1 0 a
```



## 3.4Redis集合(Set)★

常用命令：

命令：sadd <key><value1><value2> ..... 

作用：将一个或多个 member 元素加入到集合 key 中，已经存在的 member 元素将被忽略，只插入不修改

```shell
sadd k1 v11 v12 v13 v14
```

命令：smembers <key>

作用：取出该集合的所有值

```shell
smembers k1
```

命令：sismember <key><value>

作用：判断集合内是否为含有某值，有返回1，没有返回0

```shell
sismember k1 v11
```

命令：scard<key>

作用：返回该集合的元素个数

```shell
scard k1
```

命令：srem <key><value1><value2> .... 

作用：删除集合中的一个或多个元素

```shell
srem k1 v1 v2
```

命令：spop <key>

作用：随机从该集合中吐出一个值

```shell
spop k1
```

命令：smove <source><destination>value

作用：把集合中一个值从一个集合移动到另一个集合

```shell
smove k1 k2 v1
```

命令：srandmember <key><n>

作用：随机从该集合中取出n个值。不会从集合中删除

```shell
srandmember k1 2
```

命令：sinter <key1><key2>

作用：返回两个集合的交集元素，两个集合中双方都有的

```shell
sinter k1 k2
```

命令：sunion <key1><key2>

作用：返回两个集合的并集元素，两个集合所有的

```shell
sunion k1 k2
```

命令：sdiff <key1><key2>

作用：返回两个集合的差集的元素，集合k1有的，集合k2没有的

```shell
sdiff k1 k2
```



## 3.5Redis哈希(Hash)★

常用命令：

命令：hset <key><field><value>...

作用：给<key>集合中的  <field>键赋值<value>

```shell
hset student name tom age 18
```

命令：hget <key1><field>

作用：从<key1>集合<field>取出 value 

```shell
hget student name
```

命令：hmset <key1><field1><value1><field2><value2>... 

作用：批量设置hash的值

```shell
hmset student name tom name jack
```

命令：hexists<key1><field>

作用：查看哈希表 key 中，给定域 field 是否存在。

```shell
hexists student name
```

命令：hkeys <key>

作用：列出该hash集合的所有field

```shell
hkeys student
```

命令：hvals <key>

作用：列出该hash集合的所有value

```shell
hvals student
```

命令：hincrby <key><field><increment>

作用：为哈希表 key的field的value值加上increment(可为负数)，返回增加后的value，value只能是数值型字符串

```shell
hincrby student age 10
```

命令：hsetnx <key><field><value>

作用：将哈希表 key的field的值设置为 value ，当且仅当域 field 不存在，只新增field，不修改

```shell
hsetnx student name tom
```

命令：del <key>

作用：删除集合

```shell
del student
```



## 3.6有序集合Zset(sorted set)★

Redis有序集合zset与普通集合set非常相似，是一个没有重复元素的字符串集合。

不同之处是有序集合的每个成员都关联了一个评分（score）,这个评分（score）被用来按照从最低分到最高分的方式排序集合中的成员。集合的成员是唯一的，但是评分可以是重复了 。

使用场景：借助zrangebyscore实现文章访问排名等

常用命令：

命令：zadd  <key><score1><value1><score2><value2>…

作用：将一个或多个 member 元素及其 score 值加入到有序集 key 当中

```shell
zadd k1 1 v1 1 v2
```

命令：zrange <key><start><stop>  [WITHSCORES]

作用：根据索引取值，返回有序集 key 中，下标在<start><stop>之间的元素，WITHSCORES可以让分数一起返回

注意：该命令前包后包，[0,-1]表示全部参数，支持[-max,-min]⚠️

```shell
zrange k1 0 100 WITHSCORES
```

命令：zrangebyscore key min max [withscores] [limit offset count]

作用：根据索分数，返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员

```shell
zrangebyscore  k1  1 10 withscores limit 0 2 
```

命令：zrevrangebyscore key max min [withscores] [limit offset count]        

作用：同上，改为从大到小排列。 limit 0 2 分页返回数据，从0开始，返回2个

```shell
zrevrangebyscore  k1  10 1 withscores limit 0 2 
```

命令：zincrby <key><increment><value>    

作用：修改元素分数影响元素的排序，为元素的score加上增量，返回添加增量后的值

```shell
zincrby k1 20 v1
```

命令：zrem  <key><value>

作用：删除该集合下，指定value值的元素 

```shell
zrem k1 v1
```

命令：zcount <key><min><max>

作用：统计该集合内分数多少到多少之间的元素个数

```shell
zcount k1 0 10
```

命令：zrank <key><value>

作用：返回该值在集合中的排名，从0开始

```shell
zrank k1 v1
```





# 4 Redis配置文件

## 4.1Units单位

配置大小单位,开头定义了一些基本的度量单位，只支持bytes，不支持bit，大小写不敏感

```shell
 8 # Note on units: when memory size is needed, it is possible to specify
 9 # it in the usual form of 1k 5GB 4M and so forth:
10 #
11 # 1k => 1000 bytes
12 # 1kb => 1024 bytes
13 # 1m => 1000000 bytes
14 # 1mb => 1024*1024 bytes
15 # 1g => 1000000000 bytes
16 # 1gb => 1024*1024*1024 bytes
17 #
18 # units are case insensitive so 1GB 1Gb 1gB are all the same.
```



## 4.2INCLUDES包含

类似jsp中的include，多实例的情况可以把公用的配置文件提取出来

```shell
35 # include /path/to/local.conf
36 # include /path/to/other.conf
```



## 4.3网络相关配置

### 4.3.1bind★

默认情况bind=127.0.0.1只能接受本机的访问请求

不写的情况下，无限制接受任何ip地址的访问

生产环境肯定要写你应用服务器的地址；服务器是需要远程访问的，所以需要将其注释掉

如果开启了protected-mode，那么在没有设定bind ip且没有设密码的情况下，Redis只允许接受本机的响应

```shell
75 #bind 127.0.0.1 -::1
```

保存配置，停止服务，重启启动查看进程，不再是本机访问了

```shell
ps -ef|grep redis
```

查看进程，redis-server *:6379，\*代表不限制任何ip访问

```shell
[root@centos ~]# ps -ef|grep redis
root      1152     1  0 14:20 ?        00:00:14 redis-server *:6379
root      1264  1132  0 15:37 pts/0    00:00:00 grep --color=auto redis
```



### 4.3.2protected-mode★

将本机访问保护模式设置no

表示允许无密码方式登录、允许非本机的第三方登录、允许连接。

```shell
94 protected-mode no
```



### 4.3.3port

可修改端口号，默认 6379，某些人喜欢修改为9527

```shell
98 port 6379
```



### 4.3.4tcp-backlog

设置tcp的backlog，backlog其实是一个连接队列，高并发队列。backlog队列总和=未完成三次握手队列 + 已经完成三次握手队列。

在高并发环境下你需要一个高backlog值来避免慢客户端连接问题。

默认值为511，值越大客户连接速度越快，根据服务器配置和客户数量的实际情况进行配置。

```shell
107 tcp-backlog 511
```

注意：Linux内核会将这个值减小到/proc/sys/net/core/somaxconn的值（128），所以需要确认增大/proc/sys/net/core/somaxconn和/proc/sys/net/ipv4/tcp_max_syn_backlog（128）两个值来达到想要的效果



### 4.3.5timeout

一个空闲的客户端维持多少秒会关闭，0表示关闭该功能。即永不关闭。

空闲时间：服务器和客户端的交互闲置时间

```shell
119 timeout 0
```



### 4.3.6tcp-keepalive★

对访问客户端的一种心跳检测，每个n秒检测一次。

检测客户端活跃状态，清除不活跃的客户端，检测速度太快也会浪费资源。

单位为秒，如果设置为0，则不会进行Keepalive检测，建议设置成60 

```shell
136 tcp-keepalive 300
```



## 4.4GENERAL通用

### 4.4.1daemonize★

是否为后台进程，设置为yes

守护进程，后台启动

```shell
247 daemonize yes
```



### 4.4.2pidfile

存放pid文件的位置，每个实例会产生一个不同的pid文件

```shell
279 pidfile /var/run/redis_6379.pid
```



### 4.4.3loglevel 

指定日志记录级别，Redis总共支持四个级别：debug、verbose、notice、warning，默认为notice

四个级别根据使用阶段来选择，生产环境选择notice 或者warning

```shell
287 loglevel notice
```



### 4.4.4logfile

日志文件名称

```shell
292 logfile ""
```



### 4.4.5databases★

设定库的数量 默认16，默认数据库为0，可以使用SELECT <dbid>命令在连接上指定数据库id

```shell
317 databases 16
```



## 4.5SECURITY安全

设置密码后，不登录就执行命令，会抛出以下错误：(error) NOAUTH Authentication required⚠️

### 4.5.1永久设置密码★

在myredis/redis.conf配置文件中设置，此处密码为abc123

```shell
879 requirepass abc123
```

再次使用redis客户端时，需要输入密码才可以使用命令

```shell
auth "密码"
```



### 4.5.2设置临时密码

在命令中设置密码，只是临时的。重启redis服务器，密码就还原了。

启动客户端

```shell
redis-cli
```

获取密码

```shell
config get requirepass
```

设置密码

```shell
config set requirepass "密码"
```

使用密码登录

```shell
auth "密码"
```



## 4.6LIMITS限制

### 4.6.1maxclients

设置redis同时可以与多少个客户端进行连接。

默认情况下为10000个客户端。

如果达到了此限制，redis则会拒绝新的连接请求，并且向这些连接请求方发出“max number of clients reached”以作回应。

```shell
929 # Set the max number of connected clients at the same time. By default
930 # this limit is set to 10000 clients, however if the Redis server is not
931 # able to configure the process file limit to allow for the specified limit
932 # the max number of allowed clients is set to the current file limit
933 # minus 32 (as Redis reserves a few file descriptors for internal uses).
934 #
935 # Once the limit is reached Redis will close all the new connections sending
936 # an error 'max number of clients reached'.
937 #
938 # IMPORTANT: When Redis Cluster is used, the max number of connections is also
939 # shared with the cluster bus: every node in the cluster will use two
940 # connections, one incoming and another outgoing. It is important to size the
941 # limit accordingly in case of very large clusters.
942 #	这里设置⚠️
943 # maxclients 10000
```



### 4.6.2maxmemory ★

建议必须设置，否则，将内存占满，造成服务器宕机

设置redis可以使用的内存量。一旦到达内存使用上限，redis将会试图移除内部数据，移除规则可以通过maxmemory-policy来指定。

如果redis无法根据移除规则来移除内存中的数据，或者设置了“不允许移除”，那么redis则会针对那些需要申请内存的指令返回错误信息，比如SET、LPUSH等。

但是对于无内存申请的指令，仍然会正常响应，比如GET等。如果你的redis是主redis（说明你的redis有从redis），那么在设置内存使用上限时，需要在系统中留出一些内存空间给同步队列缓存，只有在你设置的是“不移除”的情况下，才不用考虑这个因素。

```shell
947 # Set a memory usage limit to the specified amount of bytes.
948 # When the memory limit is reached Redis will try to remove keys
949 # according to the eviction policy selected (see maxmemory-policy).
950 #
951 # If Redis can't remove keys according to the policy, or if the policy is
952 # set to 'noeviction', Redis will start to reply with errors to commands
953 # that would use more memory, like SET, LPUSH, and so on, and will continue
954 # to reply to read-only commands like GET.
955 #
956 # This option is usually useful when using Redis as an LRU or LFU cache, or to
957 # set a hard memory limit for an instance (using the 'noeviction' policy).
958 #
959 # WARNING: If you have replicas attached to an instance with maxmemory on,
960 # the size of the output buffers needed to feed the replicas are subtracted
961 # from the used memory count, so that network problems / resyncs will
962 # not trigger a loop where keys are evicted, and in turn the output
963 # buffer of replicas is full with DELs of keys evicted triggering the deletion
964 # of more keys, and so forth until the database is completely emptied.
965 #
966 # In short... if you have replicas attached it is suggested that you set a lower
967 # limit for maxmemory so that there is some free RAM on the system for replica
968 # output buffers (but this is not needed if the policy is 'noeviction').
969 # 这里设置⚠️
970 # maxmemory <bytes>
```



### 4.6.3maxmemory-policy

volatile-lru：使用LRU算法移除key，只对设置了过期时间的键；（最近最少使用）

allkeys-lru：在所有集合key中，使用LRU算法移除key

volatile-random：在过期集合中移除随机的key，只对设置了过期时间的键

allkeys-random：在所有集合key中，移除随机的key

volatile-ttl：移除那些TTL值最小的key，即那些最近要过期的key

noeviction：不进行移除。针对写操作，只是返回错误信息

```shell
972 # MAXMEMORY POLICY: how Redis will select what to remove when maxmemory
973 # is reached. You can select one from the following behaviors:
974 #
975 # volatile-lru -> Evict using approximated LRU, only keys with an expire set.
976 # allkeys-lru -> Evict any key using approximated LRU.
977 # volatile-lfu -> Evict using approximated LFU, only keys with an expire set.
978 # allkeys-lfu -> Evict any key using approximated LFU.
979 # volatile-random -> Remove a random key having an expire set.
980 # allkeys-random -> Remove a random key, any key.
981 # volatile-ttl -> Remove the key with the nearest expire time (minor TTL)
982 # noeviction -> Don't evict anything, just return an error on write operations.
983 #
984 # LRU means Least Recently Used
985 # LFU means Least Frequently Used
986 #
987 # Both LRU, LFU and volatile-ttl are implemented using approximated
988 # randomized algorithms.
989 #
990 # Note: with any of the above policies, when there are no suitable keys for
991 # eviction, Redis will return an error on write operations that require
992 # more memory. These are usually commands that create new keys, add data or
993 # modify existing keys. A few examples are: SET, INCR, HSET, LPUSH, SUNIONSTORE,
994 # SORT (due to the STORE argument), and EXEC (if the transaction includes any
995 # command that requires memory).
996 #
997 # The default is:
998 # 这里设置⚠️
999 # maxmemory-policy noeviction
```



### 4.6.4maxmemory-samples

设置样本数量，LRU算法和最小TTL算法都并非是精确的算法，而是估算值，所以你可以设置样本的大小，redis默认会检查这么多个key并选择其中LRU的那个。

一般设置3到7的数字，数值越小样本越不准确，但性能消耗越小。

```shell
1001 # LRU, LFU and minimal TTL algorithms are not precise algorithms but approximated
1002 # algorithms (in order to save memory), so you can tune it for speed or
1003 # accuracy. By default Redis will check five keys and pick the one that was
1004 # used least recently, you can change the sample size using the following
1005 # configuration directive.
1006 #
1007 # The default of 5 produces good enough results. 10 Approximates very closely
1008 # true LRU but costs more CPU. 3 is faster but not very accurate.
1009 # 这里设置⚠️
1010 # maxmemory-samples 5
```





# 5 Redis的发布和订阅

## 5.1什么是发布和订阅

Redis 发布订阅 (pub/sub) 是一种消息通信模式：发送者 (pub) 发送消息，订阅者 (sub) 接收消息。

Redis 客户端可以订阅任意数量的频道。

业务逻辑：

- 多个客户端可以订阅同一个频道
- 当有一个客户端向该频道发送消息时，订阅该频道的所有客户端都可以收到该消息



## 5.2发布订阅命令行实现

（1）打开一个客户端订阅channel1

```shell 
SUBSCRIBE redisChannel
```

（2）打开另一个客户端，给channel1发布消息，发布的消息不可有空格

```shell 
publish redisChannel hello
```

```shell
publish redisChannel IAmDog
```

（3）打开第一个客户端可以看到发送的消息

```shell 
127.0.0.1:6379> SUBSCRIBE redisChannel
Reading messages... (press Ctrl-C to quit)
1) "subscribe"
2) "redisChannel"
3) (integer) 1
1) "message"
2) "redisChannel"
3) "hello"	⚠️发送的消息1
1) "message"
2) "redisChannel"
3) "IAmDog"	⚠️发送的消息2
```

注：发布的消息没有持久化，客户端订阅后，无法收到订阅前发布的消息



# 6 Redis_Jedis

## 6.1修改Redis配置★

修改配置文件，设置Redis密码，密码直接配置无需加引号

```shell
requirepass 密码
```

修改配置文件，注释掉Redis登录连接的IP限制

```shell
#bind 127.0.0.1 -::1
```

修改配置文件，关闭Redis本机保护

```shell
protected-mode no
```

重启Redis，查看是修改成功

```shell
redis-server /root/myredis/redis.conf 
```

```shell
ps -ef|grep redis
```

修改成功后的Redis信息

```shell
[root@centos ~]# ps -ef|grep redis
root      1152     1  0 14:20 ?        00:00:14 redis-server *:6379
root      1264  1132  0 15:37 pts/0    00:00:00 grep --color=auto redis
```

关闭防火墙，若防火墙自启动，重启后还会打开

```shell
systemctl stop firewalld
```

关闭防火墙的开机自启动，非必需操作

```shell
systemctl disable firewalld.service
```

查看防火墙状态

```shell
systemctl status firewalld
```

防火墙关闭的状态如下

```shell
● firewalld.service - firewalld - dynamic firewall daemon
   Loaded: loaded (/usr/lib/systemd/system/firewalld.service; disabled; vendor preset: enabled)
   Active: inactive (dead)
     Docs: man:firewalld(1)
```

注意：修改该配置后，可使用第三方软件连接Redis⚠️



## 6.2使用Redis_Jedis★

使用Redis_Jedis步骤：

（1）创建Maven空Model，引入入Jedis所需要的jar包

```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.9.0</version>
</dependency>
```

（2）创建测试类com.redis.jedis.JedisTest

- 先通过Redis信息创建jedis对象：Jedis jedis = new Jedis("39.106.35.112", 6379);
- 再密码认证
- 再选择数据库(默认0)
- 再对redis操作
- 再关闭数据库

```java
package com.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class JedisTest {
    public static void main(String[] args) {
        //根据redis信息创建Jedis对象
        Jedis jedis = new Jedis("39.106.35.112", 6379);

        //密码认证
        jedis.auth("abc123");

        //选择数据库
        jedis.select(7);

        //对redis进行操作
        String ping = jedis.ping();
        System.out.println(ping);

        //通过代码对redis进行操作
        jedis.hset("cat","name", "tom");
        String name = jedis.hget("cat", "name");
        System.out.println(name);

        //关闭连接
        jedis.close();

    }
}
```



## 6.3Jedis-API

### 6.3.1key

```java
jedis.set("k1", "v1");
jedis.set("k2", "v2");
jedis.set("k3", "v3");
Set<String> keys = jedis.keys("*");
System.out.println(keys.size());
for (String key : keys) {
System.out.println(key);
}
System.out.println(jedis.exists("k1"));
System.out.println(jedis.ttl("k1"));                
System.out.println(jedis.get("k1"));
```



### 6.3.2String

```java
jedis.mset("str1","v1","str2","v2","str3","v3");
System.out.println(jedis.mget("str1","str2","str3"));
```



### 6.3.3List

```java
List<String> list = jedis.lrange("mylist",0,-1);
for (String element : list) {
System.out.println(element);
}
```



### 6.3.4Set

```java
jedis.sadd("orders", "order01");
jedis.sadd("orders", "order02");
jedis.sadd("orders", "order03");
jedis.sadd("orders", "order04");
Set<String> smembers = jedis.smembers("orders");
for (String order : smembers) {
System.out.println(order);
}
jedis.srem("orders", "order02");
```



### 6.3.5Hash

```java
jedis.hset("hash1","userName","lisi");
System.out.println(jedis.hget("hash1","userName"));
Map<String,String> map = new HashMap<String,String>();
map.put("telphone","13810169999");
map.put("address","atguigu");
map.put("email","abc@163.com");
jedis.hmset("hash2",map);
List<String> result = jedis.hmget("hash2", "telphone","email");
for (String element : result) {
System.out.println(element);
}
```



### 6.3.6Zset

```java
jedis.zadd("zset01", 100d, "z3");
jedis.zadd("zset01", 90d, "l4");
jedis.zadd("zset01", 80d, "w5");
jedis.zadd("zset01", 70d, "z6");
 
Set<String> zrange = jedis.zrange("zset01", 0, -1);
for (String e : zrange) {
System.out.println(e);
}
```





# 7 Redis事务和锁机制

## 7.1Redis的事务定义

Redis事务是一个单独的隔离操作：事务中的所有命令都会序列化、按顺序地执行。事务在执行的过程中，不会被其他客户端发送来的命令请求所打断。

Redis事务的主要作用就是串联多个命令防止别的命令插队，和MySql中的事务完全是两个概念。



## 7.2Multi、Exec、Discard★

从输入Multi命令开始，输入的命令都会依次进入命令队列中，但不会执行，直到输入Exec后，Redis会将之前的命令队列中的命令依次执行。组队的过程中可以通过discard来放弃组队。  

命令解读：

- Multi：从输入Multi命令开始，输入的命令都会依次进入命令队列中，但不会执行，
- Exec：直到输入Exec后，Redis会将之前的命令队列中的命令依次执行。
- Discard：组队的过程中可以通过discard来放弃组队。  

代码操作：

- 开启事务队列，最后取消

  ```shell
  127.0.0.1:6379> MULTI
  OK
  127.0.0.1:6379(TX)> set k1 v1
  QUEUED
  127.0.0.1:6379(TX)> set k2 v2
  QUEUED
  127.0.0.1:6379(TX)> DISCARD
  OK
  127.0.0.1:6379> keys *
  (empty array)
  127.0.0.1:6379> 
  ```

- 开启事务队列，最后提交

  - 输入EXEC提交后，会看到之前每条命令执行是否成功

  ```shell
  127.0.0.1:6379> MULTI
  OK
  127.0.0.1:6379(TX)> set k1 v1
  QUEUED
  127.0.0.1:6379(TX)> set k2 v2
  QUEUED
  127.0.0.1:6379(TX)> EXEC
  1) OK
  2) OK
  127.0.0.1:6379> keys *
  1) "k1"
  2) "k2"
  127.0.0.1:6379> 
  ```

  - 若运行期间，队列中的命名出错，则出错的本条命令不执行，但不会影响其他命令

  ```shell
  127.0.0.1:6379> MULTI
  OK
  127.0.0.1:6379(TX)> INCR k1
  QUEUED
  127.0.0.1:6379(TX)> set k3 v3
  QUEUED
  127.0.0.1:6379(TX)> EXEC
  1) (error) ERR value is not an integer or out of range
  2) OK
  127.0.0.1:6379> 
  ```

  

## 7.3事务失败的两种情况★

Redis事务不存在回滚机制。

事务失败的两种情况：

- 添加指令到队列时的语法错误，整个队列都被取消，执行命令exec命令报错，无法提交任何数据

  ```shell
  127.0.0.1:6379> MULTI
  OK
  127.0.0.1:6379(TX)> set k1 v1
  QUEUED
  127.0.0.1:6379(TX)> set k2
  (error) ERR wrong number of arguments for 'set' command
  127.0.0.1:6379(TX)> set k3 v3
  QUEUED
  127.0.0.1:6379(TX)> EXEC
  (error) EXECABORT Transaction discarded because of previous errors.
  127.0.0.1:6379> keys *
  (empty array)
  127.0.0.1:6379> 
  ```

- 执行队列中的指令时产生错误，只有报错的指令不被执行，不影响其他指令

  ```shell
  127.0.0.1:6379> MULTI
  OK
  127.0.0.1:6379(TX)> INCR k1
  QUEUED
  127.0.0.1:6379(TX)> set k3 v3
  QUEUED
  127.0.0.1:6379(TX)> EXEC
  1) (error) ERR value is not an integer or out of range
  2) OK
  127.0.0.1:6379> 
  ```

事务的错误处理：

- 组队中某个命令出现了报告错误，执行时整个的所有队列都会被取消。
- 如果执行阶段某个命令报出了错误，则只有报错的命令不会被执行，而其他的命令都会执行，不会回滚。



## 7.4悲观锁机制

Redis没有悲观锁机制，悲观锁机制常用语关系型数据库，判断是否可以操作数据的时候会先锁起来。

悲观锁(Pessimistic Lock), 顾名思义，就是很悲观，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会block直到它拿到锁。传统的关系型数据库里边就用到了很多这种锁机制，比如行锁，表锁等，读锁，写锁等，都是在做操作之前先上锁。



## 7.5乐观锁机制★

Redis使用乐观锁机制，判断是否可以操作数据时不为数据库上锁，但通过核查更新数据产生的版本号(check-and-set机制)实现事务。

乐观锁(Optimistic Lock), 顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号等机制。乐观锁适用于多读的应用类型，这样可以提高吞吐量。Redis就是利用这种check-and-set机制实现事务的。



## 7.6WATCH上锁★

在执行multi之前，先执行watch key1 [key2],可以监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。

注意：为key添加锁后，一旦执行队列时，key的值被修改过，则无法成功执行队列内的命令，并返回nil⚠️

模拟上锁：

- 客户端1：首先设置余额balance为1000元，然后对余额上锁，减掉二百元后成功提交

  ```shell
  127.0.0.1:6379> set balance 1000
  OK
  127.0.0.1:6379> WATCH balance
  OK
  127.0.0.1:6379> MULTI
  OK
  127.0.0.1:6379(TX)> decrby balance 200
  QUEUED
  127.0.0.1:6379(TX)> exec
  1) (integer) 800
  ```

- 客户端1：然后再次对余额上锁，对于剩下的800元余额，再次取出200元

  ```shell
  127.0.0.1:6379> WATCH balance
  OK
  127.0.0.1:6379> MULTI
  OK
  127.0.0.1:6379(TX)> decrby balance 200
  QUEUED
  ```

- 客户端2：在该队列还未执行exec命令之前，客户端2同样操作了余额数据，取出500元

  ```shell
  127.0.0.1:6379> get balance
  "800"
  127.0.0.1:6379> decrby balance 500
  (integer) 300
  127.0.0.1:6379> 
  ```

- 客户端1：客户端2操作后，开始提交客户端1的队列，却发现因为客户端修改了余额数据，导致提交失败，返回nil

  ```shell
  127.0.0.1:6379(TX)> exec
  (nil)
  127.0.0.1:6379> 
  ```

总结：上锁后若对上锁的key进行修改则导致命令队列exec时无法成功执行并返回nil值。但查询上锁的key值不会产生影响。



## 7.7unwatch取消锁

取消 WATCH 命令对所有 key 的监视。

如果在执行 WATCH 命令之后，EXEC 命令或DISCARD 命令先被执行了的话，那么就不需要再执行UNWATCH 了。

注意：队列的执行或者放弃，都会取消对当前key的监视，不再需要执行UNWATCH 。⚠️



## 7.8Redis事务三特性★

- 单独的隔离操作 
  - 事务中的所有命令都会序列化、按顺序地执行。事务在执行的过程中，不会被其他客户端发送来的命令请求所打断。 
- 没有隔离级别的概念 
  - 队列中的命令没有提交之前都不会实际被执行，因为事务提交前任何指令都不会被实际执行
- 不保证原子性 
  - 事务中如果有一条命令执行失败，其后的命令仍然会被执行，没有回滚  





# 8 Redis事务秒杀案例

## 8.1创建模拟事务环境★

在Redis数据库中，创建模拟数据表：

- 秒杀商品的库存表：sk(seckill):产品id:产品名称
- 秒杀成功者清单表：sk(seckill):产品id:用户id

Redis数据库内的秒杀商品的库存表结构：

|       key        |      String      |
| :--------------: | :--------------: |
| sk:prod-id:phone | 秒杀产品剩余个数 |

Redis数据库内的秒杀成功者清单表结构：

|       key       |      List      |
| :-------------: | :------------: |
| sk:prod-id:user | 秒杀成功用户id |

添加库存数据：

```shell
empty array)
127.0.0.1:6379> set sk:9527:phone 100 
OK
127.0.0.1:6379> 
```

秒杀成功数据由Java应用程序创建，可使用命令查看秒杀人员：

```shell
lrange sk:9527:user 0 -1
```

搭建模拟秒杀页面：

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <base th:href="@{/}">
    <meta charset="UTF-8">
    <title>秒杀页面</title>
</head>
<body>
<h1>秒杀菠萝手机!</h1>
<h3>美国有苹果，中国有菠萝！</h3>
<a href="seckill?productId=9527">点击秒杀</a>
</body>
</html>
```

编写分配处理秒杀请求任务的Controller:

```java
package com.redis.controller;

import com.redis.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

/**
 * @Description: TODD
 * @AllClassName: com.redis.controller.Seckill
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    SeckillService seckillService;

    @RequestMapping()
    public String seckill(
            @RequestParam String productId
    ){
        //模拟用户id
        Integer userId = new Random().nextInt(5000);
        Boolean flag = seckillService.seckill(Integer.valueOf(productId),userId);
        if (flag){
            System.out.println("抢购成功");
        }else {
            System.out.println("抢购失败");
        }

        return "index";
    }
}
```

编写真正处理秒杀请求的Service：处理秒杀请求方法共计四种，下方进行详细说明。

准备好秒杀应用程序后，使用ab命令进行模拟秒杀高并发环境：

使用ab命令模拟高并发：⚠️

创建postfile文件，内容为模拟请求中，请求体参数内容：productId=9527&

格式：ab	-n	总请求次数	-c	高并发次	-k	长链接	-p	文件路径	-T	form表单的enctry属性	访问的url

```shell
ab -n 2000 -c 200 -k -p /Users/shuaigouzi/javaProject/javaEEProject/ssm/redis/src/main/resources/postfile -T application/x-www-form-urlencoded http://localhost:8080/redis/seckill
```



## 8.2基础版秒杀

基础版秒杀使用Redis事务队列来完成，遇到高并发会有超卖现象发生

```java
@Service
public class SeckillService {

    public Boolean seckill(Integer productId, Integer userId) {
        Jedis jedis = null;

        try {
            //根据redis信息创建Jedis对象
            jedis = new Jedis("39.106.35.112", 6379);

            //密码认证
            jedis.auth("abc123");

            //选择数据库
            jedis.select(0);

            //对redis进行操作
            String ping = jedis.ping();
            System.out.println(ping);

            //通过代码对redis进行操作
            //准备产品和用户的key值
            String proKey = "sk:"+productId+":phone";
            String userKey = "sk:"+productId+":user";
            //获取到产品的库存
            String num = jedis.get(proKey);
            //判断库存是否充足
            if (Integer.parseInt(num)<=0){
                System.out.println("抢光了");
                return false;
            }
            //库存充足，库存减一
            jedis.decr(proKey);
            //将当前秒杀成功的用户添加到redis的list内
            jedis.rpush(userKey,userId.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            jedis.close();
        }

        return false;
    }
}
```



## 8.3加锁版秒杀★

加锁版秒杀，是在Redis事务队列的基础上，对key值进行监听，来防止超卖现象发生。但是会存在秒杀过后仍有库存现象。

```java
@Service
public class SeckillService {

    public Boolean seckill(Integer productId, Integer userId) {
        Jedis jedis = null;

        try {
            //根据redis信息创建Jedis对象
            jedis = new Jedis("39.106.35.112", 6379);

            //密码认证
            jedis.auth("abc123");

            //选择数据库
            jedis.select(0);

            //对redis进行操作
            String ping = jedis.ping();
            System.out.println(ping);

            //通过代码对redis进行操作
            //准备产品和用户的key值
            String proKey = "sk:"+productId+":phone";
            String userKey = "sk:"+productId+":user";

            //监控proKey，为其上锁
            jedis.watch(proKey);

            //获取到产品的库存
            String num = jedis.get(proKey);
            //判断库存是否充足
            if (Integer.parseInt(num)<=0){
                System.out.println("抢光了");
                return false;
            }

            //开启队列，将操作放到队列里，返回事务对象
            //接下来加入队列内的方法不再通过jedis调，通过multi去调
            //开启队队列必须在获取库存下方，队列里不应该包含不加到队列中的命令
            Transaction multi = jedis.multi();

            //库存充足，库存减一
            multi.decr(proKey);

            //将当前秒杀成功的用户添加到redis的list内
            multi.rpush(userKey,userId.toString());

            //执行队列，可以解决超卖的问题
            multi.exec();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            jedis.close();
        }

        return false;


    }

}
```



## 8.4封装JedisPoolUtil

本质上和上一种加锁版秒杀相同，秒杀过后仍有库存现象，不过封装了Redis连接池。

JedisPoolUtil（封装连接池文件）

```java
package com.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static volatile JedisPool jedisPool = null;

	private JedisPoolUtil() {
	}

	public static JedisPool getJedisPoolInstance() {
		if (null == jedisPool) {
			synchronized (JedisPoolUtil.class) {
				if (null == jedisPool) {
					JedisPoolConfig poolConfig = new JedisPoolConfig();
					//最大连接数, 默认8个
					poolConfig.setMaxTotal(200);
					//最大空闲连接数,默认8个
					poolConfig.setMaxIdle(32);
					//获取连接时的最大等待毫秒数,如果超时就抛异常
					poolConfig.setMaxWaitMillis(100*1000);
					//连接超时时是否阻塞，false时报异常,ture阻塞直到超时, 默认true
					poolConfig.setBlockWhenExhausted(true);

					//测试ping pong
					poolConfig.setTestOnBorrow(true);

					//传入Redis数据库信息，包括ip、端口、超时时间、密码
					jedisPool = new JedisPool(poolConfig, "39.106.35.112", 6379, 60000, "abc123");
				}
			}
		}
		return jedisPool;
	}

	public static void release(JedisPool jedisPool, Jedis jedis) {
		if (null != jedis) {
			jedisPool.returnResource(jedis);
		}
	}

}
```

秒杀的Service文件：

```java
@Service
public class SeckillService {

    public Boolean seckill(Integer productId, Integer userId) {
        Jedis jedis = null;

        try {
            //从连接池获取Jedis对象
            JedisPool pool = JedisPoolUtil.getJedisPoolInstance();
            jedis = pool.getResource();



            //选择数据库
            jedis.select(0);

            //对redis进行操作
            String ping = jedis.ping();
            System.out.println(ping);

            //通过代码对redis进行操作
            //准备产品和用户的key值
            String proKey = "sk:"+productId+":phone";
            String userKey = "sk:"+productId+":user";

            //监控proKey，为其上锁
            jedis.watch(proKey);

            //获取到产品的库存
            String num = jedis.get(proKey);
            //判断库存是否充足
            if (Integer.parseInt(num)<=0){
                System.out.println("抢光了");
                return false;
            }

            //开启队列，将操作放到队列里，返回事务对象
            //接下来加入队列内的方法不再通过jedis调，通过multi去调
            //开启队队列必须在获取库存下方，队列里不应该包含不加到队列中的命令
            Transaction multi = jedis.multi();

            //库存充足，库存减一
            multi.decr(proKey);

            //将当前秒杀成功的用户添加到redis的list内
            multi.rpush(userKey,userId.toString());

            //执行队列，可以解决超卖的问题
            multi.exec();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            jedis.close();
        }
        return false;
    }
}
```



## 8.5使用LUA脚本秒杀★

使用lua脚本解决乐观锁有库存剩余的问题，最完美的秒杀解决方案！

Lua 是一个小巧的[脚本语言](http://baike.baidu.com/item/脚本语言)，Lua脚本可以很容易的被C/C++ 代码调用，也可以反过来调用C/C++的函数，Lua并没有提供强大的库，一个完整的Lua解释器不过200k，所以Lua不适合作为开发独立应用程序的语言，而是作为嵌入式脚本语言。

很多应用程序、游戏使用LUA作为自己的嵌入式脚本语言，以此来实现可配置性、可扩展性。

这其中包括魔兽争霸地图、魔兽世界、博德之门、愤怒的小鸟等众多游戏插件或外挂。

LUA脚本：

```shell
#用户的参数
local userid=KEYS[1]; 
#产品的参数
local prodid=KEYS[2];
#产品的key值
local qtkey="sk:"..prodid..":phone";
#用户的key值
local usersKey="sk:"..prodid.":usr'; 
#判断用户是否秒杀过，不能重复秒杀抢购
local userExists=redis.call("sismember",usersKey,userid);
if tonumber(userExists)==1 then 
  return 2;
end 
#不重复就拿库存
local num= redis.call("get" ,qtkey);
#库存判断
if tonumber(num)<=0 then 
  return 0; 
else 
#库存减一，秒杀用户添加
  redis.call("decr",qtkey);
  redis.call("sadd",usersKey,userid);
end
return 1;
```

项目中使用LUA脚本完成秒杀，该类使用的Redis连接池，需要先实现连接池：

```java
public class SecKill_redisByScript {

	static String secKillScript ="local userid=KEYS[1];\r\n" + 
			"local prodid=KEYS[2];\r\n" + 
			"local qtkey='sk:'..prodid..\":phone\";\r\n" +
			"local usersKey='sk:'..prodid..\":usr\";\r\n" + 
			"local userExists=redis.call(\"sismember\",usersKey,userid);\r\n" + 
			"if tonumber(userExists)==1 then \r\n" + 
			"   return 2;\r\n" + 
			"end\r\n" + 
			"local num= redis.call(\"get\" ,qtkey);\r\n" + 
			"if tonumber(num)<=0 then \r\n" + 
			"   return 0;\r\n" + 
			"else \r\n" + 
			"   redis.call(\"decr\",qtkey);\r\n" + 
			"   redis.call(\"sadd\",usersKey,userid);\r\n" + 
			"end\r\n" + 
			"return 1" ;
			 

	public static boolean doSecKill(String uid,String prodid) throws IOException {

		JedisPool jedispool =  JedisPoolUtil.getJedisPoolInstance();
		Jedis jedis=jedispool.getResource();

		String sha1=  jedis.scriptLoad(secKillScript);
		Object result= jedis.evalsha(sha1, 2, uid,prodid);

		  String reString=String.valueOf(result);
		if ("0".equals( reString )  ) {
			System.err.println("已抢空！！");
		}else if("1".equals( reString )  )  {
			System.out.println("抢购成功！！！！");
		}else if("2".equals( reString )  )  {
			System.err.println("该用户已抢过！！");
		}else{
			System.err.println("抢购异常！！");
		}
		jedis.close();
		return true;
	}
}
```

修改controller，直接调用LUA脚本

```java
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    SeckillService seckillService;

    @RequestMapping()
    public String seckill(
            @RequestParam String productId
    ) {
        //模拟用户id
        String userId = new Random().nextInt(5000) + "";
        Boolean flag = true;
        try {
            //使用lua脚本解决乐观锁有库存剩余的问题
            flag = SecKill_redisByScript.doSecKill(userId, productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag) {
            System.out.println("抢购成功");
        } else {
            System.out.println("抢购失败");
        }

        return "index";
    }

}
```





# 9 Redis持久化

Redis 持久化在指定的时间间隔内将内存中的数据集快照写入磁盘， 也就是行话讲的Snapshot快照，它恢复时是将快照文件直接读到内存里

Redis提供了2个不同形式的持久化方式。

- RDB（Redis DataBase）
- AOF（Append Of File）

官网介绍：http://www.redis.io 

## 9.1Redis持久化之RDB

持久化RDB就是将内存中的Redis数据写入到文件内，以此完成数据备份，但数据的完整性不高。

### 9.1.1RDB持久化流程

备份的流程：

Redis会单独创建（fork）一个子进程来进行持久化，会先将数据写入到 一个临时文件中，待持久化过程都结束了，再用这个临时文件替换上次持久化好的文件。 整个过程中，主进程是不进行任何IO操作的，这就确保了极高的性能 如果需要进行大规模数据的恢复，且对于数据恢复的完整性不是非常敏感，那RDB方式要比AOF方式更加的高效。RDB的缺点是最后一次持久化后的数据可能丢失。

Fork的作用：

Fork的作用是复制一个与当前进程一样的进程。新进程的所有数据（变量、环境变量、程序计数器等） 数值都和原进程一致，但是是一个全新的进程，并作为原进程的子进程

在Linux程序中，fork()会产生一个和父进程完全相同的子进程，但子进程在此后多会exec系统调用，出于效率考虑，Linux中引入了“写时复制技术”

写时复制技术：

在配置文件中设置了指定时间内，修改足够的key数目的备份条件，同时达成时才满足快照触发条件。



### 9.1.2配置文件相关设置

#### 9.1.2.1dbfilename

在redis.conf中配置文件名称，默认为dump.rdb，默认大小92byte

```shell
421 dbfilename dump.rdb
```



#### 9.1.2.2dir

redis.conf文件的保存路径，也可以修改。默认为Redis启动时命令行所在的目录下

```shell
444 dir ./:/:：:
```



#### 9.1.2.3save

配置文件中默认的快照配置，满足触发要求后就进行一次备份，将内存中Redis的数据持久化到redis.conf文件

RDB是整个内存的压缩过的Snapshot，RDB的数据结构，可以配置复合的快照触发条件

配置命令若为save 300 100，则表示：5分钟内至少100个key发生变化，才满足快照触发条件

禁用：不设置save指令，或者给save传入空字符串

```shell
 352 # Save the DB to disk.
 353 #
 354 # save <seconds> <changes>
 355 #
 356 # Redis will save the DB if both the given number of seconds and the given
 357 # number of write operations against the DB occurred.
 358 #
 359 # Snapshotting can be completely disabled with a single empty string argument
 360 # as in following example:
 361 # 禁用设置⚠️
 362 # save ""
 363 #
 364 # Unless specified otherwise, by default Redis will save the DB:
 365 #   * After 3600 seconds (an hour) if at least 1 key changed
 366 #   * After 300 seconds (5 minutes) if at least 100 keys changed
 367 #   * After 60 seconds if at least 10000 keys changed
 368 #
 369 # You can set these explicitly by uncommenting the three following lines.
 370 # 开始设置⚠️
 371 # save 3600 1
 372 # save 300 100
 373 # save 60 10000
```



#### 9.1.2.4Redis写操作开关

当磁盘满了，Redis无法写入磁盘的话，直接关掉Redis的写操作。推荐yes，设置为no会导致服务器异常

没多余的资源进行持久化就关闭持久化，一定不要忘记，Redis持久化是为了备份，Redis主要目的是为了缓存。

```shell
388 stop-writes-on-bgsave-error yes
```



#### 9.1.2.5rdbcompression

对于存储到磁盘中的快照，可以设置是否进行压缩存储。如果是的话，redis会采用LZF算法进行压缩。

如果你不想消耗CPU来进行压缩的话，可以设置为关闭此功能no，不过推荐yes。

```shell
394 rdbcompression yes
```



#### 9.1.2.6rdbchecksum

在存储快照后，还可以让redis使用CRC64算法来进行数据校验，但是这样做会增加大约10%的性能消耗，如果希望获取到最大的性能提升，可以关闭此功能no，不过推荐yes。

```shell
403 rdbchecksum yes
```



### 9.1.3RDB快照版本管理

存储快照版本

- 先通过config get dir  查询dump.rdb文件的目录 
- 将*.rdb的文件拷贝到备份的文件夹

RDB的恢复：

- 关闭Redis
- 先把备份的快照文件拷贝到工作目录下进行替换原来的rdb文件
- 启动Redis, 备份数据会直接加载



### 9.1.4触发RDB快照的条件★

- save配置项：配置文件中通过设置的触发快照条件
- shutdown：关闭客户端时进行快照操作
- bgsave：Redis会在后台异步进行快照操作，同时还可以响应客户端请求。
- save：该命令只管保存，其它不管，全部阻塞。不建议使用！



### 9.1.5持久化RDB的优劣势

优势：

- 适合大规模的数据恢复
- 对数据完整性和一致性要求不高更适合使用
- 节省磁盘空间
- 恢复速度快

劣势：

- Fork的时候，内存中的数据被克隆了一份，大致2倍的膨胀性需要考虑。
- 虽然Redis在fork时使用了写时拷贝技术,但是如果数据庞大时还是比较消耗性能。
- 在备份周期在一定间隔时间做一次备份，所以如果Redis意外down掉的话，就会丢失最后一次快照后的所有修改。



## 9.2Redis持久化之AOF

以日志的形式来记录每个写操作，将Redis执行过的所有写命令记录下来，不记录读的， 只许追加文件但不可以改写文件，redis启动之初会读取该文件内的命令从前到后执行一次重新构建数据，以此完成数据的恢复工作。

AOF和RDB同时开启，AOF优先级更高，系统默认取AOF的数据！⚠️



### 9.2.1AOF持久化流程

（1）客户端的请求写命令会被append追加到AOF缓冲区内；

（2）AOF缓冲区根据AOF持久化策略[always,everysec,no]将操作sync同步到磁盘的AOF文件中；

（3）AOF文件大小超过重写策略或手动重写时，会对AOF文件rewrite重写，压缩AOF文件容量；

（4）Redis服务重启时，会重新load加载AOF文件中的写操作达到数据恢复的目的；



### 9.2.2配置文件相关设置

#### 9.2.2.1appendonly

开启APOF，默认关闭

```shell
1230 appendonly yes
```



#### 9.2.2.2appendfilename

修改AOF快照文件名称，默认为 appendonly.aof

```shell
1234 appendfilename "appendonly.aof"
```



#### 9.2.2.3appendfsync

AOF同步频率设置：

- appendfsync always
  - 始终同步，每次Redis的写入都会立刻记入日志；性能较差但数据完整性比较好

```shell
1259 # appendfsync always
```

- appendfsync everysec
  - 每秒同步，每秒记入日志一次，如果宕机，本秒的数据可能丢失，默认值⚠️

```shell
1260 appendfsync everysec
```

- appendfsync no
  - redis不主动进行同步，把同步时机交给操作系统

```shell
1261 # appendfsync no
```



#### 9.2.2.4重写机制开关

AOF采用文件追加方式，但是文件越追加越大，就提供了重写机制进行压缩

压缩分为自动和手动，自动重写可以在配置文件中打开，默认不开启

```shell
1282 no-appendfsync-on-rewrite no
```



#### 9.2.2.5重写占比基准值

设置重写的基准值，文件达到100%时开始重写（100%表示文件是原来重写后文件的2倍时触发）

默认值为100%

```shell
1301 auto-aof-rewrite-percentage 100
```



#### 9.2.2.6重写大小基准值

设置重写的大小基准值，最小文件64MB。达到这个值开始重写

默认值为64MB

```shell
1302 auto-aof-rewrite-min-size 64mb
```



### 9.2.3AOF启动/修复/恢复★

AOF默认不开启，可以在redis.conf中配置文件名称，默认为 appendonly.aof，AOF文件的保存路径，同RDB的路径一致。

启动AOF：

- 开启AOF，需要在配置文件中设置：

```shell
1230 appendonly yes
```

- 可以修改AOF快照文件名称，默认为 appendonly.aof

```shell
1234 appendfilename "appendonly.aof"
```

正常恢复：

- 将有数据的aof文件复制一份保存到对应目录(查看目录：config get dir)
- 恢复：重启redis然后重新加载

异常恢复：

- 如遇到AOF文件损坏，启动时是按照AOF启动，则`redis-cli`无法启动客户端，`ps -ef|grep redis`查不到进程
- 同时开启了AOF和RDB时，AOF优先，appendonly.aof文件损坏直接无法启动Redis，这时也并不会尝试RDB启动
- 通过/usr/local/bin/redis-check-aof --fix appendonly.aof进行恢复

```shell
/usr/local/bin/redis-check-aof --fix appendonly.aof
```

- 恢复：重启redis，然后重新加载



### 9.2.4Rewrite重写压缩

AOF采用文件追加方式，文件会越来越大为避免出现此种情况，新增了重写机制, 当AOF文件的大小超过所设定的阈值时，Redis就会启动AOF文件的内容压缩， 只保留可以恢复数据的最小指令集.可以使用命令bgrewriteaof

重写方式分为两种：

- 自动重写：在配置文件中开启
- 手动重写：执行命令bgrewriteaof

开启自动重写：

- 在配置文件中修改no-appendfsync-on-rewrite为yes

```shell
1282 no-appendfsync-on-rewrite yes
```

重写规则：

- Redis会记录上次重写时的AOF大小，默认配置是当AOF文件大小是上次rewrite后大小的一倍且文件大于64M时触发
- 重写虽然可以节约大量磁盘空间，减少恢复时间。但是每次重写还是有一定的负担的，因此设定重写条件。 
- auto-aof-rewrite-percentage：设置重写的基准值，文件达到100%时开始重写（100%表示aof文件大小是原来的2倍时触发）
- auto-aof-rewrite-min-size：设置重写的基准值，最小文件64MB。达到这个值以上开始重写。
- 例如：文件达到70MB开始重写，降到50MB，下次什么时候开始重写？100MB
- 系统载入时或者上次重写完毕时，Redis会记录此时AOF大小，设为base_size,
- 如果Redis的AOF当前大小是原来大小的两倍 (默认)，且当前大小>=64mb(默认)的情况下，Redis会对AOF进行重写。 

重写流程：

（1）bgrewriteaof触发重写，判断是否当前有bgsave或bgrewriteaof在运行，如果有，则等待该命令结束后再继续执行。

（2）主进程fork出子进程执行重写操作，保证主进程不会阻塞。

（3）子进程遍历redis内存中数据到临时文件，客户端的写请求同时写入aof_buf缓冲区和aof_rewrite_buf重写缓冲区保证原AOF文件完整以及新AOF文件生成期间的新的数据修改动作不会丢失。

（4）1).子进程写完新的AOF文件后，向主进程发信号，父进程更新统计信息。2).主进程把aof_rewrite_buf中的数据写入到新的AOF文件。

（5）使用新的AOF文件覆盖旧的AOF文件，完成AOF重写。



### 9.2.5持久化AOF的优劣势

优势：

- 备份机制更稳健，丢失数据概率更低。
- 可读的日志文本，通过操作AOF稳健，可以处理误操作。

劣势：

- 比起RDB占用更多的磁盘空间。
- 恢复备份速度要慢。
- 每次读写都同步的话，有一定的性能压力。
- 存在个别Bug，造成恢复不能。



## 9.3RDB和AOF的总结★

二者的选择：

- 官方推荐两个都启用。
- 如果对数据不敏感，可以选单独用RDB。
- 不建议单独用 AOF，因为可能会出现Bug。
- 如果只是做纯内存缓存，可以都不用

官方建议：

- RDB持久化方式能够在指定的时间间隔能对你的数据进行快照存储

- AOF持久化方式记录每次对服务器写的操作,当服务器重启的时候会重新执行这些命令来恢复原始的数据,AOF命令以redis协议追加保存每次写的操作到文件末尾. 

- Redis还能对AOF文件进行后台重写,使得AOF文件的体积不至于过大

- 只做缓存：如果你只希望你的数据在服务器运行的时候存在,你也可以不使用任何持久化方式.

- 同时开启两种持久化方式

- 在这种情况下,当redis重启的时候会优先载入AOF文件来恢复原始的数据, 因为在通常情况下AOF文件保存的数据集要比RDB文件保存的数据集要完整.

- RDB的数据不实时，同时使用两者时服务器重启也只会找AOF文件。那要不要只使用AOF呢？ 

- 建议不要，因为RDB更适合用于备份数据库(AOF在不断变化不好备份)， 快速重启，而且不会有AOF可能潜在的bug，留着作为一个万一的手段。

- 性能建议

  ```tex
  因为RDB文件只用作后备用途，建议只在Slave上持久化RDB文件，而且只要15分钟备份一次就够了，只保留save 900 1这条规则。
  
  如果使用AOF，好处是在最恶劣情况下也只会丢失不超过两秒数据，启动脚本较简单只load自己的AOF文件就可以了。
  
  代价,一是带来了持续的IO，二是AOF rewrite的最后将rewrite过程中产生的新数据写到新文件造成的阻塞几乎是不可避免的。
  
  只要硬盘许可，应该尽量减少AOF rewrite的频率，AOF重写的基础大小默认值64M太小了，可以设到5G以上。
  
  默认超过原大小100%大小时重写可以改到适当的数值。
  ```

  



# 10 主从复制

主机数据更新后根据配置和策略， 自动同步到备机的master/slaver机制，Master以写为主，Slave以读为主

这里使用一台机器搭建伪主从复制，但实际开发中每一个主和从都在不同的linux服务器上。

配置主从复制的目的：⚠️

- 读写分离，性能扩展
- 容灾快速恢复

## 10.1搭建伪环境

前提：已存在文件`/root/myredis/redis.conf`，登录限制、本机保护都已经设置完毕，使用默认端口6379，最好关闭AOF持久化

### 10.1.1搭建步骤

- 创建文件`redis6379.conf`、`redis6380.conf`和`redis6381.conf`
- 在文件中引入已配置的文件`/root/myredis/redis.conf`，再修改pidfile、port、dbfilename三个属性
  - 使用include引入配置过的配置文件，写绝对路径
  - 修改自己的专属pid文件
  - 修改自己的专属端口
  - 修改自己的dump.rdb持久化文件
  - 设置主机密码masterauth
- 根据配置文件启动三个Redis服务端
- 查看是否成功启动
- 分别连接三个Redis服务器
- 查看三个服务器，打印主从复制的相关信息

### 10.1.2实现代码

- 创建文件`redis6379.conf`、`redis6380.conf`和`redis6381.conf`

- 在文件中引入已配置的文件`/root/myredis/redis.conf`，再修改pidfile、port、dbfilename三个属性

  - 使用include引入配置过的配置文件，写绝对路径
  - 修改自己的专属pid文件
  - 修改自己的专属端口
  - 修改自己的dump.rdb持久化文件
  - 设置主机密码masterauth

  redis6379.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6379.pid
  port 6379
  dbfilename dump6379.rdb
  masterauth abc123
  ```

  redis6380.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6380.pid
  port 6380
  dbfilename dump6380.rdb
  masterauth abc123
  ```

  redis6381.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6381.pid
  port 6381
  dbfilename dump6381.rdb
  masterauth abc123
  ```

- 根据配置文件启动三个Redis服务端

  ```shell
  redis-server /root/myredis/redis6379.conf
  ```

  ```shell
  redis-server /root/myredis/redis6380.conf
  ```

  ```shell
  redis-server /root/myredis/redis6381.conf

- 查看是否成功启动

  ```shell
  ps -ef|grep redis
  ```

  启动成功的进程信息

  ```shell
  [root@centos ~]# ps -ef|grep redi
  root      1256     1  0 21:23 ?        00:00:00 redis-server *:6379
  root      1243     1  0 21:23 ?        00:00:00 redis-server *:6380
  root      1250     1  0 21:23 ?        00:00:00 redis-server *:6381
  root      1262  1138  0 21:23 pts/0    00:00:00 grep --color=auto redis
  ```

- 分别连接三个Redis服务器

  - -p：数据库的端口
  - -a：数据库的密码

  ```shell
  redis-cli -p 6379 -a abc123
  ```

  ```shell
  redis-cli -p 6380 -a abc123 
  ```

  ```shell
  redis-cli -p 6381 -a abc123

- 查看三个服务器，打印主从复制的相关信息

  ```shell
  info replication
  ```



## 10.2配置主从库

配置主从库只需要在从库中设置自己的主库即可，主库设置密码，从库配置文件一定要设置masterauth属性！⚠️

语法格式：

（1）slaveof  <ip><port>	成为某个实例的从服务器

实例操作：

（1）Redis服务器6380和6381分别指定6379为主库

```shell
slaveof 127.0.0.1 6379
```

（2）打印三个库的主从相关信息

```shell
info replication
```

（3）尝试主库添加数据，从库获取数据等操作



## 10.3主从库间的关系

（1）主库写数据，从库读数据，在从库上写数据报错

（2）从库设置主库后，一旦成功，从库所有持久化数据被主库覆盖

（3）主库shutdown或挂掉，再次重启后一切正常，从库会等待主库上线

（4）从库shutdown或挂掉，再次重启后需要重新设置主库

可以将`slaveof 127.0.0.1 6379`命令添加到从库的配置文件中，永久生效

```shell
include /root/myredis/redis.conf
pidfile /var/run/redis_6380.pid
port 6380
dbfilename dump6380.rdb
masterauth abc123
replica-priority 10
slaveof 127.0.0.1 6379
```



## 10.4三种常用主从模式★

### 10.4.1一主二从

一个主库，n个从库，所有的从库直接连接主库，与上面配置的主从库情景相同。

特点：

（1）主库写数据，从库读数据，在从库上写数据报错

（2）从库设置主库后，一旦成功，从库所有持久化数据被主库覆盖

（3）主库shutdown或挂掉，再次重启后一切正常，从库会等待主库上线

（4）从库shutdown或挂掉，再次重启后需要重新设置主库





### 10.4.2薪火相传

上一个Slave可以是下一个slave的Master，Slave同样可以接收其他 slaves的连接和同步请求，那么该slave作为了链条中下一个的master, 可以有效减轻master的写压力,去中心化降低风险。

薪火相传类似于多叉树结构，唯一的主库就是根节点，根节点只用对自己的直接子节点负责，主库只用对自己的直接从库负责，减缓主库的压力和风险。

情景模拟：

- 配置6380的主库为6379，配置后6379为主库，6380为从库

  ```shell
  slaveof 127.0.0.1 6379
  ```

- 配置6381的主库为6380，配置后6380为从库，但他同时是6381的主库

  ```shell
  slaveof 127.0.0.1 6380

特点：

- 主库无法跨级操作`从库的从库`
- 若从库shutdown或挂掉，需要重新设置自己的主库，而`从库的从库`则会一直等待从库上线。
- 若`从库的从库`shutdown或挂掉，需要重新设置从库为自己的主库。
- 主库设置数据后传给从库，从库再传给`从库的从库`，从而确保所有从库都拿到了主库的数据，又减少了主库压力⚠️
- 两个从库仍无法写数据



### 10.4.3反客为主

当一个master宕机后，后面的slave可以立刻升为master，其后面的slave不用做任何修改。

用 slaveof  no one  将从机变为主机。

情景模拟：

- 在一主二从模式下，从库若想脱离主库，输入`slaveof  no one`命令即可

  ```shell
  slaveof  no one
  ```

- 该命令仅仅可让当前从库脱离主库，并不能影响其他从库
- 当前从库脱离组织后，自己成为主库，可执行写操作，任意set



## 10.5复制原理

1. Slave启动成功连接到master后会发送一个sync命令
2. Master接到命令启动后台的存盘进程，同时收集所有接收到的用于修改数据集命令， 在后台进程执行完毕之后，master将传送整个数据文件到slave,以完成一次完全同步
3. 全量复制：而slave服务在接收到数据库文件数据后，将其存盘并加载到内存中。
4. 增量复制：Master继续将新的所有收集到的修改命令依次传给slave,完成同步
5. 但是只要是重新连接master,一次完全同步（全量复制)将被自动执行



## 10.6哨兵模式

反客为主的自动版，能够后台监控主机是否故障，如果故障了根据投票数自动将从库转换为主库

创建一个或多个哨兵，监控主库和从库的状态，若主库宕机了，哨兵会从剩下的从库中选择出一个新的主库，其他的从库也会将这个新的主库作为自己的主库，原来的主库上线后也会作为新主库的从库。

使用哨兵模式建议主从库密码一致！哨兵模式无法单独为从库设置密码⚠️

### 10.6.1搭建哨兵模式★

首先要先搭建一主二从模式，并在配置文件中修改哨兵模式专属配置：

- 创建文件`redis6379.conf`、`redis6380.conf`和`redis6381.conf`

- 在文件中引入已配置的文件`/root/myredis/redis.conf`，再修改pidfile、port、dbfilename三个属性

  - 使用include引入配置过的配置文件，写绝对路径
  - 修改自己的专属pid文件
  - 修改自己的专属端口
  - 修改自己的dump.rdb持久化文件
  - 设置主机密码masterauth
  - 设置从机的优先级，值越小，优先级越高，用于选举主机时使用。默认100，replica-priority

  redis6379.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6379.pid
  port 6379
  dbfilename dump6379.rdb
  masterauth abc123
  replica-priority 1
  ```

  redis6380.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6380.pid
  port 6380
  dbfilename dump6380.rdb
  masterauth abc123
  replica-priority 10
  ```

  redis6381.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6381.pid
  port 6381
  dbfilename dump6381.rdb
  masterauth abc123
  replica-priority 50
  ```

- 根据配置文件启动三个Redis服务端

  ```shell
  redis-server /root/myredis/redis6379.conf
  ```

  ```shell
  redis-server /root/myredis/redis6380.conf
  ```

  ```shell
  redis-server /root/myredis/redis6381.conf
  ```

- 查看是否成功启动

  ```shell
  ps -ef|grep redis
  ```

  启动成功的进程信息

  ```shell
  [root@centos ~]# ps -ef|grep redi
  root      1256     1  0 21:23 ?        00:00:00 redis-server *:6379
  root      1243     1  0 21:23 ?        00:00:00 redis-server *:6380
  root      1250     1  0 21:23 ?        00:00:00 redis-server *:6381
  root      1262  1138  0 21:23 pts/0    00:00:00 grep --color=auto redis
  ```

- 分别连接三个Redis服务器

  - -p：数据库的端口
  - -a：数据库的密码

  ```shell
  redis-cli -p 6379 -a abc123
  ```

  ```shell
  redis-cli -p 6380 -a abc123 
  ```

  ```shell
  redis-cli -p 6381 -a abc123
  ```

- 查看三个服务器，打印主从复制的相关信息

  ```shell
  info replication
  ```

- 将三台主机配置为一主二从模式，Redis服务器6380和6381分别指定6379为主库

  ```shell
  slaveof 127.0.0.1 6379
  ```

开始配置哨兵：

- 创建`/root/myredis/sentinel.conf`哨兵配置文件，配置哨兵内容
  - mymaster为监控对象起的服务器名称， 1 为至少有多少个哨兵同意该监控的服务器宕机
  - sentinel auth-pass <master-name> <password>
    设置连接master和slave时的密码，要求master和slave的密码必须相同，若数据库有密码必须设置！⚠️

```shell
sentinel monitor mymaster 127.0.0.1 6379 1
sentinel auth-pass mymaster abc123
```

- 启动哨兵，哨兵开启时会自动检测主库从库，并打印相关信息log

```shell
/usr/local/bin/redis-sentinel  /root/myredis/sentinel.conf 
```

```shell
1706:X 24 Nov 2022 00:27:51.487 # WARNING: The TCP backlog setting of 511 cannot be enforced because /proc/sys/net/core/somaxconn is set to the lower value of 128.
1706:X 24 Nov 2022 00:27:51.489 # Sentinel ID is 56657ef57fb48a0a05ba3d207a738f840cc74731
1706:X 24 Nov 2022 00:27:51.489 # +monitor master mymaster 127.0.0.1 6379 quorum 1
1706:X 24 Nov 2022 00:27:51.490 * +slave slave 127.0.0.1:6380 127.0.0.1 6380 @ mymaster 127.0.0.1 6379
1706:X 24 Nov 2022 00:27:51.492 * +slave slave 127.0.0.1:6381 127.0.0.1 6381 @ mymaster 127.0.0.1 6379
```

- 测试哨兵，shutdown主库，等待十几秒，哨兵会选择出新的主库，并打印详细信息log

```shell
1706:X 24 Nov 2022 00:30:54.003 #	前面很多log，这里省略
1706:X 24 Nov 2022 00:30:54.003 # +failover-end master mymaster 127.0.0.1 6379
1706:X 24 Nov 2022 00:30:54.003 # +switch-master mymaster 127.0.0.1 6379 127.0.0.1 6380
1706:X 24 Nov 2022 00:30:54.003 * +slave slave 127.0.0.1:6381 127.0.0.1 6381 @ mymaster 127.0.0.1 6380
1706:X 24 Nov 2022 00:30:54.003 * +slave slave 127.0.0.1:6379 127.0.0.1 6379 @ mymaster 127.0.0.1 6380
1706:X 24 Nov 2022 00:31:24.040 # +sdown slave 127.0.0.1:6379 127.0.0.1 6379 @ mymaster 127.0.0.1 6380
```

- 选择出新的主库后可以在新主库的客户端使用命令info replication查看主从信息，并测试set数据其他从库能否获取

  - 哨兵会向主库和从库的配置文件中写入数据，告诉谁是新的主库，从库的主库是谁等信息

  ```shell
  info replication
  ```



### 10.6.2哨兵配置文件

- 配置文件：

  ```shell
  sentinel monitor mymaster 127.0.0.1 6379 1
  sentinel auth-pass mymaster abc123
  ```

- sentinel auth-pass <master-name> <password>

  - mymaster为监控对象起的服务器名称， 1 为至少有多少个哨兵同意该监控的服务器宕机。
  - 关于哨兵的配置：
    - 我们可以配置多个哨兵，哨兵会一直和主机通信，但一般配置为奇数个，方便投票选新主库
    - 配置多个哨兵投票的目的：因为若哨兵自己宕机了会认为主库宕机了，配置一个严谨度不高
    - 所有哨兵中，当半数以上哨兵都投票认为主库宕机了，这时才会更换新主库

- sentinel auth-pass mymaster abc123

  - 设置连接master和slave时的密码，注意的是sentinel不能分别为master和slave设置不同的密码，因此master和slave的密码
    应应该设置相同⚠️

    

### 10.6.3哨兵选择主库判定

- 先选择优先级最小的、再选择偏移量最大的（即获得主库数据最多最全的）、再选择runid最小的（随机生成四十位的runid）

- 在配置文件中设置优先级，数字越小优先级越高，默认值100

  ```shell
  replica-priority: 20
  ```

- 输入`info replication`命令可查看当前从库的优先级

  ```shell
  127.0.0.1:6381> info replication
  # Replication
  role:slave
  master_host:127.0.0.1
  master_port:6380
  master_link_status:up
  master_last_io_seconds_ago:1
  master_sync_in_progress:0
  slave_repl_offset:55137
  # 哨兵优先级，数字越小优先级越高⚠️
  slave_priority:20
  slave_read_only:1
  connected_slaves:0
  master_failover_state:no-failover
  master_replid:3c402438ceef78d829c2a828eb0040f34b62c8fc
  master_replid2:71da29d6ba9f66aa10c2bea35518dd5a59dd2711
  master_repl_offset:55137
  second_repl_offset:15273
  repl_backlog_active:1
  repl_backlog_size:1048576
  repl_backlog_first_byte_offs
  ```





# 11 Redis集群

## 11.1Redis集群概述

分析哨兵模式的遗留问题：

- 当主机宕机时，哨兵会为其分配新的主机，导致ip发生了变化，可java应用程序中配置的还是已经宕机的主机IP地址、端口等信息。浏览器客户端发送请求时，会经过应用程序，发到已宕机的Redis主机上。

遗留问题的解决方案-代理主机：

- 应用程序向代理主机发起请求，再由代理主机去访问主机上的主库，当主机宕机时，哨兵会根据优先级选择一个从机作为新的主机，然后再告知代理主机新主机的IP、端口等信息，让代理主机去访问新主机。

关于以上问题，redis3.0中提供了解决方案。就是无中心化集群配置。



## 11.2Redis集群作用

Redis 集群实现了对Redis的水平扩容（水平扩容是增加多台服务器，竖直扩容是为当前服务器更换更新更强劲的设备），即启动N个redis节点，将整个数据库分布存储在这N个节点中，每个节点存储总数据的1/N。

Redis 集群通过分区（partition）来提供一定程度的可用性（availability）： 即使集群中有一部分节点失效或者无法进行通讯， 集群也可以继续处理命令请求。



## 11.3搭建Redis集群★

前提：已存在配置文件`/root/myredis/redis.conf`，登录限制、本机保护、后台启动、密码(abc123)都已经设置完毕

### 11.3.1搭建集群的伪环境★

目标：Redis要求至少有三个主节点（主机），每个主节点有一个备份节点（从机），则至少六台服务器。

- 创建节点配置文件

  - 模拟Redis服务器第一台：redis6379.conf
  - 模拟Redis服务器第二台：redis6380.conf
  - 模拟Redis服务器第三台：redis6381.conf
  - 模拟Redis服务器第四台：redis6389.conf
  - 模拟Redis服务器第五台：redis6390.conf
  - 模拟Redis服务器第六台：redis6391.conf

- 在文件中引入已配置的文件`/root/myredis/redis.conf`，再修改相关属性

  - 使用include引入配置过的配置文件，写绝对路径

  - 修改自己的专属pid文件

  - 修改自己的专属端口

  - 修改自己的dump.rdb持久化文件

  - 设置作为主机的密码masterauth
  - 设置作为从机的密码requirepass
  - 打开集群模式开关cluster-enabled
  - 设置节点的配置文件名cluster-config-file⚠️
    - 节点的配置文件在启动集群后自动创建
    - 存储集群的节点信息，主从的对应关系
    - 集群关闭后再重启会根据此文件恢复各节点的关系
  - 设置节点失联最大时间，超过该时间，集群就会进行主从切换cluster-node-timeout

  第一台服务器配置文件redis6379.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6379.pid
  port 6379
  dbfilename dump6379.rdb
  masterauth abc123
  requirepass abc123
  cluster-enabled yes
  cluster-config-file nodes-6379.conf
  cluster-node-timeout 15000
  ```

  第二台服务器配置文件redis6380.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6380.pid
  port 6380
  dbfilename dump6380.rdb
  masterauth abc123
  requirepass abc123
  cluster-enabled yes
  cluster-config-file nodes-6380.conf
  cluster-node-timeout 15000
  ```

  第三台服务器配置文件redis6381.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6381.pid
  port 6381
  dbfilename dump6381.rdb
  masterauth abc123
  requirepass abc123
  cluster-enabled yes
  cluster-config-file nodes-6381.conf
  cluster-node-timeout 15000
  ```

  第四台服务器配置文件redis6389.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6389.pid
  port 6389
  dbfilename dump6389.rdb
  masterauth abc123
  requirepass abc123
  cluster-enabled yes
  cluster-config-file nodes-6389.conf
  cluster-node-timeout 15000
  ```

  第五台服务器配置文件redis6390.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6390.pid
  port 6390
  dbfilename dump6390.rdb
  masterauth abc123
  requirepass abc123
  cluster-enabled yes
  cluster-config-file nodes-6390.conf
  cluster-node-timeout 15000
  ```

  第六台服务器配置文件redis6391.conf:

  ```shell
  include /root/myredis/redis.conf
  pidfile /var/run/redis_6391.pid
  port 6391
  dbfilename dump6391.rdb
  masterauth abc123
  requirepass abc123
  cluster-enabled yes
  cluster-config-file nodes-6391.conf
  cluster-node-timeout 15000
  ```



### 11.3.2多个节点合成集群★

- 先关闭其他测试时开启的Redis服务，防止端口冲突

  ```shell
  pkill redis
  ```

- 启动六个Redis服务

  ```shell
  redis-server /root/myredis/redis6379.conf
  ```

  ```shell
  redis-server /root/myredis/redis6380.conf
  ```

  ```shell
  redis-server /root/myredis/redis6381.conf
  ```

  ```shell
  redis-server /root/myredis/redis6389.conf
  ```

  ```shell
  redis-server /root/myredis/redis6390.conf
  ```

  ```shell
  redis-server /root/myredis/redis6391.conf
  ```

- 查看六个节点的启动情况

  ```shell
  ps -ef|grep redis
  ```

  启动成功的进程信息：

  ```shell
  [root@ubuntu ~]# ps -ef|grep redis
  root      2425     1  0 10:16 ?        00:00:00 redis-server *:6379 [cluster]
  root      2431     1  0 10:16 ?        00:00:00 redis-server *:6380 [cluster]
  root      2437     1  0 10:16 ?        00:00:00 redis-server *:6381 [cluster]
  root      2443     1  0 10:16 ?        00:00:00 redis-server *:6391 [cluster]
  root      2449     1  0 10:16 ?        00:00:00 redis-server *:6390 [cluster]
  root      2455     1  0 10:16 ?        00:00:00 redis-server *:6389 [cluster]
  ```

- 将六个节点合成一个集群

  - 这里的redis-cli命令的软链接与平时执行的redis-cli非同一个，是`/opt/redis-6.2.1/src`目录下的软链接
  - 合成前必须确保所有Redis节点已启动
  - 必须使用真实的IP地址，不可用`127.0.0.1`，这里使用的云服务器真实IP地址`39.106.35.112`加Redis服务的端口号
  - 参数`--cluster-replicas 1`配置集群的方式，表示一台主机对应一台从机为一组，模拟了六台，则该集群有三组
  - 参数-a是添加各Redis的密码，若Redis设置了密码必须添加该参数
  - 启动该命令后会生成每个节点对应的节点配置文件

  ```shell
  cd  /opt/redis-6.2.1/src
  ```

  ```shell
  redis-cli --cluster create --cluster-replicas 1 -a abc123 39.106.35.112:6379 39.106.35.112:6380 39.106.35.112:6381 39.106.35.112:6389 39.106.35.112:6390 39.106.35.112:6391
  ```

- 输入yes同意启动节点后，会打印主从机的相关日志信息

  - replicates属性是主机40位的runid，可根据此属性判断主机和从机的对应关系

  ```shell
  >>> Trying to optimize slaves allocation for anti-affinity
  [WARNING] Some slaves are in the same host as their master
  M: 7b9a682163f4953a25e4a06df1047ff4e4bde292 39.106.35.112:6379
     slots:[0-5460] (5461 slots) master
  M: fa91cf27afbfbacdb83a7e5b150f10efb6c64821 39.106.35.112:6380
     slots:[5461-10922] (5462 slots) master
  M: f230ef0c6591d20ed2f8ca46aca332234a437b2f 39.106.35.112:6381
     slots:[10923-16383] (5461 slots) master
  S: 70260880eeb99f8b4ace0f0d0886ca7e9a30b30b 39.106.35.112:6389
     replicates 7b9a682163f4953a25e4a06df1047ff4e4bde292
  S: 590a53e6a9faed136c48f6bb9bfe26380e9c974d 39.106.35.112:6390
     replicates fa91cf27afbfbacdb83a7e5b150f10efb6c64821
  S: 80829af47b7e72871bf66b31bd83fff9df9ea678 39.106.35.112:6391
     replicates f230ef0c6591d20ed2f8ca46aca332234a437b2f
  ```

- 集群搭建完成后，也会打印各节点的相关日志信息⚠️

  - slots:[0-5460] (5461 slots) master：该日志可查看插槽范围，主机敏感
  - 1 additional replica(s)：该日志表示主机拥有一个从机，主机敏感
  - replicates fa91...cf4821：该属性可判断从机和主机的对应关系，从机敏感
  - [OK] All 16384 slots covered：表示该集群共16384个插槽

  ```shell
  >>> Performing Cluster Check (using node 39.106.35.112:6379)
  M: 7b9a682163f4953a25e4a06df1047ff4e4bde292 39.106.35.112:6379
     slots:[0-5460] (5461 slots) master
     1 additional replica(s)
  M: fa91cf27afbfbacdb83a7e5b150f10efb6c64821 39.106.35.112:6380
     slots:[5461-10922] (5462 slots) master
     1 additional replica(s)
  S: 70260880eeb99f8b4ace0f0d0886ca7e9a30b30b 39.106.35.112:6389
     slots: (0 slots) slave
     replicates 7b9a682163f4953a25e4a06df1047ff4e4bde292
  S: 80829af47b7e72871bf66b31bd83fff9df9ea678 39.106.35.112:6391
     slots: (0 slots) slave
     replicates f230ef0c6591d20ed2f8ca46aca332234a437b2f
  M: f230ef0c6591d20ed2f8ca46aca332234a437b2f 39.106.35.112:6381
     slots:[10923-16383] (5461 slots) master
     1 additional replica(s)
  S: 590a53e6a9faed136c48f6bb9bfe26380e9c974d 39.106.35.112:6390
     slots: (0 slots) slave
     replicates fa91cf27afbfbacdb83a7e5b150f10efb6c64821
  [OK] All nodes agree about slots configuration.
  >>> Check for open slots...
  >>> Check slots coverage
  [OK] All 16384 slots covered.
  ```

- 连接集群客户端

  ```shell
  redis-cli -p 6379 -a abc123 -c
  ```

- 查看各节点信息

  ```shell
  cluster nodes
  ```



### 11.3.3redis cluster

一个集群至少要有三个主节点。

选项` --cluster-replicas 1 `表示我们希望为集群中的每个主节点创建一个从节点。

分配原则尽量保证每个主数据库运行在不同的IP地址，每个从库和主库不在一个IP地址上。



### 11.3.4cluster nodes

集群搭建完成后，可使用`cluster nodes`命令查看集群中各节点的信息

```shell
cluster nodes
```



### 11.3.5slots

一个 Redis 集群包含 16384 个插槽（hash slot）， 数据库中的每个键都属于这 16384 个插槽的其中一个

集群构建完成时最后一条日志信息：

```shell
[OK] All 16384 slots covered.
```

集群使用公式 CRC16(key) % 16384 来计算键 key 属于哪个槽， 其中 CRC16(key) 语句用于计算键 key 的 CRC16 校验和 。

集群中的每个节点负责处理一部分插槽。 举个例子， 如果一个集群有主节点ABC，辅节点DEF， 其中：

主节点 A 负责处理 0 号至 5460 号插槽。

主节点 B 负责处理 5461 号至 10922 号插槽。

主节点 C 负责处理 10923 号至 16383 号插槽。



## 11.4Redis集群录入值★

### 11.4.1使用-c参数★

- 不使用-c参数连接集群客户端（不可以不使用-c参数连接客户端）
  - 在redis-cli每次录入时，redis都会计算出该key应该送往的插槽
  - 如果不是该客户端对应服务器的插槽，redis会报错，并告知应前往的redis实例地址和端口

```shell
[root@ubuntu src]#  redis-cli -p 6379 -a abc123
Warning: Using a password with '-a' or '-u' option on the command line interface may not be safe.
127.0.0.1:6379> set k1 v1
(error) MOVED 12706 39.106.35.112:6381
127.0.0.1:6379> set k2 v2
OK
127.0.0.1:6379> 
```

- 使用-c参数连接集群客户端注意
  -  使用-c参数`redis-cli -p 6379 -a abc123 -c`登入后，再录入、查询键值对可以自动重定向。
  - 注意：不在一个slot下的键值，是不能使用mget，mset等多键操作⚠️

```shell
[root@ubuntu src]#  redis-cli -p 6379 -a abc123 -c
Warning: Using a password with '-a' or '-u' option on the command line interface may not be safe.
127.0.0.1:6379> set k1 v1
-> Redirected to slot [12706] located at 39.106.35.112:6381
OK
127.0.0.1:6379> 
```



### 11.4.2使用组录入★

虽然不在一个slot下的键值，不能使用mget，mset等多键操作，但可以通过{}来定义组的概念，从而使key中{}内相同内容的键值对放到一个slot中去。

- 在6379的集群客户端下，正常添加k5，会添加到6381内

  - located at 39.106.35.112:6381

  ```shell
  127.0.0.1:6379> set k5 v5
  -> Redirected to slot [12582] located at 39.106.35.112:6381
  OK
  ```

- 在6379的集群客户端下，使用组添加k5，会添加到6379内

  ```shell
  39.106.35.112:6381> set k5{dog} v5
  -> Redirected to slot [254] located at 39.106.35.112:6379
  OK
  ```

- 将k5放到组名后面也可正常添加

  ```shell
  39.106.35.112:6381> set {dog}k5 v5
  -> Redirected to slot [254] located at 39.106.35.112:6379
  OK
  ```

- 使用mset添加多组

  ```shell
  39.106.35.112:6381> mset k1{dog} v1 k2{dog} v2  k5{dog} v5
  -> Redirected to slot [254] located at 39.106.35.112:6379
  OK
  ```



## 11.5故障恢复

- 主节点下线，从节点会自动升为主节点。
  - 注意：根据cluster-node-timeout设置的主从切换时间判断⚠️
- 主节点恢复后，主节点回来变成从节点。
- 当存在所有某一段插槽的主节点和从节点都宕掉的情况时：
  - cluster-require-full-coverage 为yes 
    - 整个集群都挂掉，该集群无法使用
  - cluster-require-full-coverage 为no
    - 这一段插槽数据全都不能使用，也无法存储，但其他段的插槽仍正常使用
- 配置文件中cluster-require-full-coverage属性的设置
  - 默认值为yes

```shell
1454 # cluster-require-full-coverage yes
```



## 11.6集群的Jedis开发

即使连接的不是主机，集群会自动切换主机存储。主机写，从机读。

无中心化主从集群。无论从哪台主机写的数据，其他主机上都能读到数据。

```java
public class JedisClusterTest {
  public static void main(String[] args) { 
     Set<HostAndPort>set =new HashSet<HostAndPort>();
     set.add(new HostAndPort("192.168.31.211",6379));
     JedisCluster jedisCluster=new JedisCluster(set);
     jedisCluster.set("k1", "v1");
     System.out.println(jedisCluster.get("k1"));
  }
}
```



## 11.7Redis的优劣势

优势：

- 实现扩容
- 分摊压力
- 无中心配置相对简单

劣势：

- 多键操作是不被支持的 
- 多键的Redis事务是不被支持的。lua脚本不被支持
- 由于集群方案出现较晚，很多公司已经采用了其他的集群方案，而代理或者客户端分片的方案想要迁移至redis cluster，需要整体迁移而不是逐步过渡，复杂度较大。











使用ab命令模拟高并发：

创建文件 vim postfile 内容：productId=9527&

格式：ab	-n	总请求次数	-c	高并发次	-k	长链接	-p	文件路径	-T	form表单的enctry属性	访问的url



高并发发送请求，共计2000次，一次发200个，发十次。

二次优化后，乐观锁的问题，是事务本身的问题，会导致秒杀后还有剩余。

三次优化，使用连接池，本质上和第二次优化的版本一样。
