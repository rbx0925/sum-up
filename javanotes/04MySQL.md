# 1 数据库概述

## 1.1 数据库概述

**数据库（Database，简称DB）**：指长期保存在计算机的存储设备上，按照一定规则组织起来，可以被各种用户或应用共享的数据集合。

> 数据库是什么？
>
> - 存储数据的地方
>
> 为什么要用数据库？
>
> - 因为应用程序产生的数据是在内存中的，如果程序退出或者是断电了，则数据就会消失。使用数据库是为了能够永久保存数据。当然这里指的是非内存数据库。
>
> 用普通文件存储行不行？
>
> - 把数据写入到硬盘上的文件中，当然可以实现持久化的目标，但是不利于后期的检索和管理等。
> - 数据库其实就是磁盘上的存储数据的文件，但不像普通文件存储数据，它是按照一定规则把数据组织起来的特殊文件

**数据库管理系统（Database Management System，DBMS）**：指一种操作和管理数据库的大型软件，用于建立、使用和维护数据库，对数据库进行统一管理和控制，以保证数据库的安全性和完整性。用户通过数据库管理系统访问数据库中的数据。数据库是通过数据库管理系统创建和操作的。

> 我们通常所说的MySQL、Oracle、SqlServer等都是数据库管理系统



**常见数据库管理系统**：

以下是2019年**DB-Engines Ranking** 对各数据库受欢迎程度进行调查后的统计结果：（查看数据库最新排名:https://db-engines.com/en/ranking）

**数据库的分类：**

- **关系型数据库**，采用关系模型来组织数据，简单来说，关系模型指的就是把复杂的数据结构归结为简单的二元关系，即二维表格模型。数据以行和列的形式存储。

  >关系型数据库二维表模型具体体现： 
>
  >- 将数据放到表中，即数据表，表再放到库中。
  >- 一个数据库中可以有多个表，每个表都有一个唯一的名字。
  >- 表具有一些特性，这些特性定义了数据在表中如何存储，类似java中 “类”的设计。
  >- 表的列我们也称为**字段**。每个字段描述了它所含有的数据的意义，**数据表的设计实际上就是对字段的设计**。创建数据表时，为每个字段分配一个数据类型，定义它们的数据长度和字段名。每个字段类似java中的“实例属性”。
  >- 表中的数据是按行存储的，一行即为一条记录。每一行类似于java中的“对象”。

  > MySQL、Oracle、SqlServer等是关系型数据库管理系统。

- **非关系型数据库**，可看成传统关系型数据库的功能阉割版本，比如基于键值对存储数据，通过减少很少用的功能，来提高性能。

  > Redis、Elasticsearch等是非关系型数据库管理系统。

## 1.2  MySQL概述

**MySQL是一种开放源代码的关系型数据库管理系统，开发者为瑞典MySQL AB公司。在2008年1月16号被Sun公司收购。而2009年,SUN又被Oracle收购。**

> MySQL命名的由来：MySQL的开发者Monty Widenius有一个女儿，名叫My Widenius，因此他将自己开发的数据库命名为MySQL。Monty还有一个 儿子，名为Max，因此在2003年，SAP公司与MySQL公司建立合作伙伴关系后，Monty Widenius又将与SAP合作开发的数据库命名为 MaxDB。而现在的MariaDB中的Maria便是Monty Widenius的小孙女的名字。

目前 MySQL被广泛地应用在Internet上的中小型网站中。由于其**体积小、速度快、总体拥有成本低，尤其是开放源码**这一特点，使得很多互联网公司选择了MySQL作为网站数据库（Facebook, Twitter, YouTube，阿里的蚂蚁金服，去哪儿，魅族，百度外卖，腾讯）。

**MySQL的优点有很多，其中主要的优势有如下几点：**

- 可移植性：MySQL数据库几乎支持所有的操作系统，如Linux、Solaris、FreeBSD、Mac和Windows。
- 免费：MySQL的社区版完全免费，一般中小型网站的开发都选择 MySQL 作为网站数据库。
- 开源：2000 年，MySQL公布了自己的源代码，并采用GPL（GNU General Public License）许可协议，正式进入开源的世界。开源意味着可以让更多人审阅和贡献源代码，可以吸纳更多优秀人才的代码成果。
- 关系型数据库：MySQL可以利用标准SQL语法进行查询和操作。
- 速度快、体积小、容易使用：与其他大型数据库的设置和管理相比，其复杂程度较低，易于学习。MySQL的早期版本（主要使用的是MyISAM引擎）在高并发下显得有些力不从心，随着版本的升级优化（主要使用的是InnoDB引擎），在实践中也证明了高压力下的可用性。从2009年开始，阿里的“去IOE”备受关注，淘宝DBA团队再次从Oracle转向MySQL，其他使用MySQL数据库的公司还有Facebook、Twitter、YouTube、百度、腾讯、去哪儿、魅族等等，自此，MySQL在市场上占据了很大的份额。
- 安全性和连接性：十分灵活和安全的权限和密码系统，允许基于主机的验证。连接到服务器时，所有的密码传输均采用加密形式，从而保证了密码安全。由于MySQL是网络化的，因此可以在因特网上的任何地方访问，提高数据共享的效率。
- 丰富的接口：提供了用于C、C++、Java、PHP、Python、Ruby和Eiffel、Perl等语言的API。
- 灵活：MySQL并不完美，但是却足够灵活，能够适应高要求的环境。同时，MySQL既可以嵌入到应用程序中，也可以支持数据仓库、内容索引和部署软件、高可用的冗余系统、在线事务处理系统等各种应用类型。
- MySQL最重要、最与众不同的特性是它的存储引擎架构，这种架构的设计将查询处理（Query Processing）及其他系统任务（Server Task）和数据的存储/提取相分离。这种处理和存储分离的设计可以在使用时根据性能、特性，以及其他需求来选择数据存储的方式。MySQL中同一个数据库，不同的表格可以选择不同的存储引擎。其中使用最多的是InnoDB 和MyISAM，MySQL5.5之后InnoDB是默认的存储引擎。

**针对不同用户，MySQL提供三个不同的版本。**

- （1）MySQL Enterprise Server（企业版）：能够以更高的性价比为企业提供数据仓库应用，该版本需要付费使用，官方提供电话技术支持。

- （2）MySQL Cluster（集群版）：MySQL 集群是 MySQL 适合于分布式计算环境的高可用、高冗余版本。它采用了 NDB Cluster 存储引擎，允许在 1 个集群中运行多个 MySQL 服务器。它不能单独使用，需要在社区版或企业版基础上使用。

- （3）MySQL Community Server（社区版）：在开源GPL许可证之下可以自由的使用。该版本完全免费，但是官方不提供技术支持。本书是基于社区版讲解和演示的。在MySQL 社区版开发过程中，同时存在多个发布系列，每个发布处在不同的成熟阶段。

  > MySQL5.7（RC）是当前稳定的发布系列。RC（Release Candidate候选版本）版只针对严重漏洞修复和安全修复重新发布，没有增加会影响该系列的重要功能。从MySQL 5.0、5.1、5.5、5.6直到5.7都基于5这个大版本，升级的小版本。5.0版本中加入了存储过程、服务器端游标、触发器、视图、分布式事务、查询优化器的显著改进以及其他的一些特性。这也为MySQL 5.0之后的版本迈向高性能数据库的发展奠定了基础。
  >
  > MySQL8.0.26（GA）是最新开发的稳定发布系列。GA（General Availability正式发布的版本）是包含新功能的正式发布版本。这个版本是MySQL数据库又一个开拓时代的开始。





# 2 MySQL的基本使用

## 2.1 MySQL服务的启动

“任务管理器”-->“服务”-->启动和关闭MySQL

命令行

```cmd
net  start  MySQL服务名
net  stop  MySQL服务名
```

## 2.2 客户端连接MySQL

1、命令行客户端：

```cmd
mysql -h 主机IP地址 -P 端口号	-u 用户名	-p回车
Enter Password:密码
```

> 如果访问本机，-h localhost可以省略
>
> 如果端口号没有修改，-P 3306可以省略
>
> 除了-p与密码之间不要空格外，其他的-h,-P,-u与后面的参数值之间可以有空格
>
> 想要连接成功，必须保证服务开启的

2、其他客户端，例如：可视化工具Navicat或SQLyog等

## 2.3 导入和导出数据（了解）

### 2.3.1 单个数据库导出数据

1. **mysqldump 备份**

   ```cmd
   mysqldump -h主机地址 -P端口号 -u用户名 -p密码 --databases  数据库名 > 文件路径/文件名.sql
   ```

   示例：

   ```sql
   C:\Windows\System32>mysqldump -hlocalhost -P3306 -uroot -p123456 --databases  test > d:/test.sql
   #本机3306端口可以省略，简化：
   C:\Windows\System32>mysqldump -uroot -p123456 --databases  test > d:/test.sql
   ```

   > ​    --databases 导出单个数据库，加上此参数后，备份导出的sql文件中，会包含create database语句
>
   > 如果不加此参数，导出的sql文件中没有create database语句，恢复时，需要先创建好库，再导入恢复

2. **使用可视化客户端工具备份**

### 2.3.2 导入数据

1. **在cmd命令行，使用mysql命令恢复数据库**

   示例：

   ```cmd
   C:\Windows\System32>mysql -uroot -p123456 <test.sql
   ```

   > 如果使用mysqldump备份sql格式文件时，没有使用--databases参数，恢复时必须先创建数据，并指定为要恢复的目标库：
   >
   > ```cmd
   > C:\Windows\System32>mysql -uroot -p123456 test <test.sql
   > ```

2. **使用source命令恢复**

   **先登录mysql**，然后再执行如下命令：

   ```mysql
   source  sql脚本路径名.sql
   ```

   示例：

   ```mysql
   mysql>source d:/test.sql;
   ```

3. **使用可视化客户端工具导入备份文件进行恢复**





# 3  SQL语言概述

SQL即Structure Query Language（结构化查询语言），SQL被美国国家标准局（ANSI）确定为关系型数据库语言的美国标准，后来被国际化标准组织（ISO）采纳为关系数据库语言的国际标准。

> 先后有SQL-86 ， SQL-89 ， SQL-92 ， SQL-99 等标准。其中两个重要的标准 SQL92 和 SQL99，它们分别代表了 92 年和 99 年颁布的 SQL 标准，我们今天使用的 SQL 语言依然遵循这些标准。

SQL是专门用来操作/访问数据库的通用语言。不同的数据库生产厂商都支持SQL语句，但都有特有内容。

- 各数据库厂商都支持ISO的SQL标准，**普通话**

- 各数据库厂商在标准的基础上做了自己的扩展，**方言**

SQL 是一种标准化的语言，它允许你在数据库上执行操作，如创建项目，查询内容，更新内容，并删除条目等操作。

> Create, Read, Update, and Delete 通常称为CRUD操作。

## 3.1 MySQL的语法规范和要求

1. MySQL的SQL语法的大小写问题：

   - MySQL的关键字和函数名等不区分大小写（建议使用大写）；

     > 下面两句一样效果：

     ```mysql
     show databases;
     SHOW DATABASES;
     ```

   - 标识符(库名、表名)是否区分大小写与操作系统有关（windows、Mac不区分大小，linux区分大小，建议全部使用小写）[参考](https://dev.mysql.com/doc/refman/8.0/en/identifier-case-sensitivity.html)

     > MySQL库和表是以文件形式存储的，库名存储为目录，表存储为文件。所以库名和表名是否区分大小写与操作系统的文件系统是否区分大小有关。
     >
     > 即使不区分大小，在同一个SQL语句中，相同的库名或表名不应该使用不同的大小写表示，比如以下错误示例：
     >
     > ```mysql
     > mysql> SELECT * FROM my_table WHERE MY_TABLE.col=1;
     > ```
     >
     > 列、索引、存储子程序和触发器名在任何平台上对大小写不敏感，列的别名也不敏感。
     >
     > 默认情况，**表别名**在Unix中对大小写敏感，但在Windows或Mac OS X中对大小写不敏感。下面的查询在Unix中不会工作，因为它同时引用了别名a和A：
     >
     > ```mysql
     > mysql> SELECT col_name FROM tbl_name AS a
     >     -> WHERE a.col_name = 1 OR A.col_name = 2;
     > ```

   - 数据值是否区分大小写，和字符集与校对规则有关。

     > \_ci（大小写不敏感），\_cs（大小写敏感），\_bin（二元，即比较是基于字符编码的值而与language无关，区分大小写）

2. 标识符命名规范：

   - 尽量使用26个英文字母大小写，数字0-9，下划线，不要使用其他符号
   - 建议不要使用MySQL的关键字等来作为表名、字段名等，如果不小心使用，请在SQL语句中使用`（飘号）引起来
   - 数据库和表名、字段名等对象名中间不要包含空格
   - 同一个MySQL数据库管理系统中，数据库不能同名，同一个库中，表不能重名，同一个表中，字段不能重名

   > 建议：数据库名、表名、表别名、字段名、字段别名等都小写，多个单词组成使用下划线分割。

3. 关于标点符号：  
   - SQL 可以写在一行或者分多行写，每条完整SQL语句以`;`结尾
   
   - 小括号，引号等符号必须成对结束的，列如：`()` 、`''`、`""`。
   
   - 所有标点符号必须英文状态下半角输入方式输入。

   - 表示字符串和日期类型的数值时使用单引号`''`，字符串也可以使用双引号`""`，但是建议使用单引号。
   
   - 列的别名建议使用双引号`""`，给表名取别名不要使用双引号。
   
     > 取别名时as可以省略，如果列的别名没有包含空格，可以省略双引号，如果有空格双引号不能省略。
   
4. 关于注释
   - 单行注释：`#`单行注释内容（MySQL特有的方式）
   - 单行注释：`--` 单行注释内容 ，**需注意**`--`与注释内容之间必须有空格（SQL标准中，`--`后没有空格，这样某些语句中会导致问题）
   - 多行注释：`/* `多行注释内容` */`

```mysql
#以下两句是一样的，不区分大小写
show databases;
SHOW DATABASES;

#创建表格
#create table student info(...); #表名错误，因为表名有空格
create table student_info(...); 

#其中name使用``飘号，因为name和系统关键字或系统函数名等预定义标识符重名了。
CREATE TABLE t_stu(
    id INT,
    `name` VARCHAR(20)
);

select id as "编号", `name` as "姓名" from t_stu; #起别名时，as都可以省略
select id as 编号, `name` as 姓名 from t_stu; #如果字段别名中没有空格，那么可以省略""
select id as 编 号, `name` as 姓 名 from t_stu; #错误，如果字段别名中有空格，那么不能省略""
```

## 3.2 MySQL支持的数据类型

MySQL支持所有标准的SQL数据类型，常见类型主要有数值类型、日期时间类型和字符串类型。

### 3.2.1 数值类型

数值类型主要用来存储数字，不同的数值类型提供不同的取值范围，可以存储的值范围越大，所需要的存储空间也越大。MySQL支持所有标准SQL中的数值类型，其中包括严格数据类型（INTEGER、SMALLINT、DECIMAL、NUMERIC）和近似数值类型（FLOAT、REAL、DOUBLE PRECISION）。MySQL还扩展了TINYINT、MEDIUMINT和BIGINT等3种不同长度的整数类型，并增加了BIT类型，用来存储位数据。

> 对于MySQL中的数值类型，还要做如下说明：
>
> - 关键字INT是INTEGER的同义词。
> - 关键字DEC和FIXED是DECIMAL的同义词。
> - NUMERIC和DECIMAL类型被视为相同的数据类型。
>
> - DOUBLE视为DOUBLE PRECISION的同义词，并在REAL_AS_FLOAT SQL模式未启用的情况下，将REAL也视为DOUBLE PRECISION的同义词。

| **数据类型**                   | **字节数** | 无符号数的取值范围 UNSIGNED                          | **有符号数的取值范围 **SIGNED                      | 备注                                         |
| ------------------------------ | ---------- | ---------------------------------------------------- | -------------------------------------------------- | -------------------------------------------- |
| **整数型**                     |            |                                                      |                                                    |                                              |
| TINYINT                        | 1          | 0~255                                                | -128~127                                           |                                              |
| SMALLINT                       | 2          | 0~65535                                              | -32768~32767                                       |                                              |
| MEDIUMINT                      | 3          | 0~16777215                                           | -8388608~8388607                                   |                                              |
| **INT** / **INTEGER**          | 4          | 0~4294967295                                         | -2147483648~2147483647                             |                                              |
| BIGINT                         | 8          | 0~18446744073709551615                               | -9223372036854775808~  9223372036854775807         |                                              |
| **浮点型**                     |            |                                                      |                                                    | **存储数据的近似值，不够精确**               |
| FLOAT                          | 4          | 0和1.175494351E-38~  3.402823466E+38                 | -3.402823644E+38~  -1.175494351E-38                | 单精度浮点型                                 |
| **DOUBLE **                    | 8          | 0和2.2250738585072014E-308~  1.7976931348623157E+308 | -1.7976931348623157E+308~  2.2250738585072014E-308 | 双精度浮点型                                 |
| **定点型**                     |            |                                                      |                                                    | **存储精确的数字数据值，底层使用字符存储。** |
| **DECIMAL[(M,D)]/ DEC[(M,D)]** | M+2        | 0和2.2250738585072014E-308~  1.7976931348623157E+308 | -1.7976931348623157E+308~  2.2250738585072014E-308 | DEC(M)等效DEC(M,0)，M默认值为10              |

> 整数类型使用(M)指定数值显示宽度，需要配合UNSIGNED（无符号类型、非负）、ZEROFILL属性指定数值宽度，表示实际数值不足M位时，在左边使用0填充。比如：`INT(4) UNSINGED ZEROFILL`，存储数值`12`时，查询数据结果显示：`0012`。从 MySQL 8.0.17 开始，不推荐整数类型使用(M)和ZEROFILL属性。
>
> 浮点型与定点型也支持UNSIGNED属性，从 MySQL 8.0.17 开始，对于 FLOAT、DOUBLE 和 DECIMAL类型的列，不推荐使用 UNSIGNED 属性。
>
> 浮点类型和定点类型都可以使用(M,D)指定数值宽度，**比如 `DECIMAL(5,2)`，SQL标准要求能够存储任何5位数并且包含2位小数的值，因此此例可以存储的值范围从 -999.99 到 999.99**。从 MySQL 8.0.17 开始，不推荐使用浮点类型的非标准 FLOAT(M,D) 和 DOUBLE(M,D) 语法，但DECIMAL(M,D)语法是推荐使用。
>
> 选择：
> 结合需求尽量选择合适存储范围的类型；
> 当对数值要求高精度时使用DECIMAL(M,D)类型，如果准确度不是太重要或如果速度为最高优先级，DOUBLE类型即足够了。
>
> [参考文档](https://dev.mysql.com/doc/refman/8.0/en/numeric-type-syntax.html)



### 3.2.2 日期和时间类型

| **数据类型** | **字节数** | **取值范围**                              | **日期格式**         | **零值**              |
| ------------ | ---------- | ----------------------------------------- | -------------------- | --------------------- |
| YEAR         | 1          | 1901~2155                                 | YYYY                 | 0000                  |
| DATE         | 4          | 1000-01-01~9999-12-3                      | YYYY-MM-DD           | '0000-00-00'          |
| TIME         | 3          | -838:59:59~838:59:59                      | HH:MM:SS             | '00:00:00'            |
| DATETIME     | 8          | 1000-01-01 00:00:00~  9999-12-31 23:59:59 | YYYY-MM-DD  HH:MM:SS | '0000-00-00 00:00:00' |
| TIMESTAMP    | 4          | 1970-01-01 00:00:01~  2038-01-19 03:14:07 | YYYY-MM-DD  HH:MM:SS | '0000-00-00 00:00:00' |

> 日期时间类型的值可以使用字符串和数字表示。比如：'2000-10-10 12:12:12' 和 20001010121212 都可以表示DATETIME或TIMESTAMP类型的值
>
> 每个日期时间类型都有一个有效数值范围和“零”值，当您指定 MySQL 无法表示的无效值时，可以使用该“零”值（结合SQL模式使用）。
>
> TIME：也可以表示时间间隔，所以有效范围远大于24
>
> TIMESTAMP：会随时区变化，MySQL8之前默认情况下`DEFALUT`默认值不能为NULL，如果存储入NULL值自动转换为当前系统日期时间。MySQL8之后默认需要手动设置`DEFALUT`值为`CURRENT_TIMESTAMP` 来表示默认值为当前日期时间，并支持自动更新（修改其他字段数据后）。
>
> DATETIME：不会随时区变化，5.65版本开始支持默认值`CURRENT_TIMESTAMP`，支持自动更新数据值。表示的日期时间范围更大，**推荐使用**。
>
> [参考文档](https://dev.mysql.com/doc/refman/8.0/en/date-and-time-types.html)



### 3.2.3 字符串类型

MySQL的字符串类型有CHAR、VARCHAR、BINARY、VARBINARY、BLOB、TEXT、ENUM、SET等。MySQL的字符串类型可以用来存储文本字符串数据，还可以存储二进制字符串。

| **数据类型**   | **字符（字节）数**                | **类型描述**                 | 备注                                                         |
| -------------- | --------------------------------- | ---------------------------- | ------------------------------------------------------------ |
| **CHAR[(M)]**  | 0~255                             | 定长字符串                   | 固定长度字符串类型；char(10)    'aaa       '  占10位<br />未指定字符长度，则默认长度为1字符 |
| **VARCHAR(M)** | 0~65535（最大字符数与字符集有关） | 可变长字符串                 | 可变长度字符串类型； varchar(10)  'aaa'  占3位<br />必须指定长度 |
| TINYTEXT       | 0~255                             | 短文本字符串                 |                                                              |
| **TEXT**       | 0~65535                           | 长文本数据                   | 字符串类型，只能存储纯文本，不能有默认值                     |
| MEDIUMTEXT     | 0~16777215                        | 中等长度文本数据             |                                                              |
| LONGTEXT       | 0~4294967295                      | 极大长度文本数据             |                                                              |
| **二进制类型** |                                   |                              | **二进制字符串类型，以字节定义长度**，没有字符集             |
| BINARY[(M)]    | 0~255                             | 定长二进制                   | 类似CHAR，存储的是二进制字符，指定字节长度，默认长度为1字节  |
| VARBINARY(M)   | 0~65535                           | 变长二进制                   | 类似VARCHAR，M指定的是字节数，不考虑编码问题。               |
| TINYBLOB       | 0~255                             | 不超过 255 个字符的二进制    |                                                              |
| **BLOB**       | 0~65535                           | 二进制形式的长文本数据       | 字节类型，可以存储图片，不能有默认值                         |
| MEDIUMBLOB     | 0~16777215                        | 二进制形式的中等长度文本数据 |                                                              |
| LOGNGBLOB      | 0~4294967295                      | 二进制形式的极大文本数据     |                                                              |

> CHAR(M)：定长字符串。M表示最大字符数，不指定M值时，默认为1位。存储数据不足指定位数时，右侧使用空格补足，检索时自动删除尾部所有空格字符。
>
> VARCHAR(M):可变长字符串。必须指定M值即最大字符数，最多可以存储的最大字符数与最大行大小和字符编码有关，比如UTF-8编码，理论最多存储21844个字符。存储和检索时会保留尾部空格。
>
> `BINARY`和`VARBINARY`类似于`CHAR`和`VARCHAR`类型，但是以字节定义长度。
>
> 在大多数方面，`BLOB` 列可以视为`VARBINARY`列。同样，您可以将一 `TEXT`列视为一 `VARCHAR`列。 `BLOB`和`TEXT`不同于 `VARBINARY`和 `VARCHAR`在以下方面：
>
> - 对于`BLOB`和 `TEXT`列的索引，您必须指定索引前缀长度。对于`CHAR`和`VARCHAR`，前缀长度是可选的。
> - `BLOB`和`TEXT`列不能有`DEFAULT`值。

### 3.2.4 其他类型

- **ENUM枚举**:选择固定的几个常量值的一个。比如 gender  enum('m','f')  值是m或f

- **SET集合类型**：选择固定的几个常量值的任意几个组合。比如 id set('1','2','3')      值可以是123的任意组合

- **JSON类型**

  > 5.7.8开始支持JSON数据类型

## 3.3 SQL分类

- **DDL（Data Definition Language）**：数据定义语言，用来定义数据库对象，即库、表、列等。

  > 主要包括**CREATE**、 **ALTER**、**DROP**等语句

- **DML（Data Manipulation Language）**：数据操作语言，用来定义数据库记录（数据）。

  > 主要包括**INSERT**、 **UPDATE**、 **DELETE**、**SELECT**等语句

  - **DQL（Data Query Language）**：数据查询语言，用来查询记录（数据）。

    > 因为查询语句使用的非常的频繁，所以很多人把查询语句单拎出来一类，主要包括**SELECT**语句

- **DCL（Data Control Language）：**数据控制语言，用来定义访问权限和事务管理等。

  > 主要包括**GRANT**、**COMMIT**、**ROLLBACK**等语句





# 4 SQL语句

## 4.5  DDL:操作数据库

​	使用的关键字：**CREATE**、**ALTER**、**DROP**

### 4.5.1 创建数据库

CREATE DATABASE [IF NOT EXISTS] 数据库名；语句用于创建新的数据库：

```sql
SQL> CREATE DATABASE mydb1;
SQL> CREATE DATABASE mydb2 character SET GBK;
SQL> CREATE DATABASE mydb3 character SET GBK COLLATE gbk_chinese_ci;
```

### 4.5.2 查看数据

查看当前数据库服务器中的所有数据库

```sql
SQL> SHOW DATABASES;
```

查看前面创建的mydb2数据库的定义信息

```sql
SQL> Show CREATE DATABASE mydb2;
```

### 4.5.3 修改数据库

查看服务器中的数据库，并把mydb2的字符集修改为utf8;

```sql
SQL> ALTER DATABASE mydb2 character SET utf8;
```

### 4.5.4 删除数据库

DROP DATABASE [IF EXISTS] 数据库名；

```sql
SQL> DROP DATABASE mydb3;
```

### 4.5.5 其他语句

使用（切换）数据库

```sql
SQL> USE mydb2;
```

查看当前使用的数据库（非DDL语句）

```sql
SQL> Select database();
```



## 4.6  DDL:操作数据表

### 4.6.1 创建数据表：

语法：

  	CREATE TABLE [IF NOT EXISTS] [数据库名.]表名(
  	    字段名1 数据类型 [属性 ]  [ 索引] [注释],
  	
  	    字段名2 数据类型 [属性 ]  [ 索引] [注释]，
  	
  	    ……
  	
  	    字段名n 数据类型  [属性 ]  [ 索引] [注释]
  	);



> ```mysql
> CREATE TABLE Employees
> (
>      id INT,
>      age INT,
>      first VARCHAR(10),
>      last VARCHAR(10)		
> );
> ```

### 4.6.2 查看数据表：

创建完成数据表后，可以通过SHOW CREATE TABLE查看数据表

> ```sql
> SHOW CREATE TABLE table_name;
> ```

​	如果只想查看表中列的相关信息使用DESC 表名：

> ```sql
> DESC table_name;
> ```

​	查看数据库中所有表：

> ```mysql
> SHOW TABLES;
> ```

### 4.6.3 修改数据表：

> - 修改表名
>
>   ALTER TABLE 原表名 RENAME [TO] 新表名;
>
>   ```sql
>   ALTER TABLE stu RENAME student;
>   或者
>   RENAME TABLE stu TO student;#方言
>   ```
>
> - 修改字段名（列）
>
>   ALTER TABLE 表名 CHANGE 原字段名 新字段名 新数据类型;
>
>   ```sql
>   ALTER TABLE student CHANGE stu_age stu_sex VARCHAR(10);
>   ```
>
> - 修改字段数据类型
>
>   ALTER TABLE 表名 MODIFY 字段名 数据类型;
>
>   ```sql
>   ALTER TABLE student MODIFY stu_sex CHAR;
>   ```
>
> - 添加字段
>
>   ALTER TABLE 表名 ADD 新字段名 数据类型 [FIRST|AFTER 字段名2];
>
>   ```sql
>   ALTER TABLE student ADD stu_hobby VARCHAR(50);
>   ```
>
> - 删除字段
>
>   ALTER TABLE 表名 DROP 字段名;
>
>   ```sql
>   ALTER TABLE student DROP stu_hobby;
>   ```
>
> - 修改字段排列位置
>
>   ALTER TABLE 表名 MODIFY 字段名1 数据类型 [FIRST|AFTER 字段名2];
>
>   ```sql
>   ALTER TABLE student MODIFY stu_name VARCHAR(50) FIRST;
>   ALTER TABLE student MODIFY stu_name VARCHAR(50) AFTER stu_sex;
>   ```

### 4.6.4 删除数据表：

DROP TABLE语句用于删除现有表。

> ```sql
> DROP TABLE table_name;
> ```



## 4.7 DML操作表数据

DML是对表中的数据进行增、删、改的操作。不要与DDL混淆了。（重要）

主要包括：INSERT 、UPDATE、 DELETE

### 4.7.1插入数据★

> - 为所有列插入数据
>
>   语法：
>
>   ```
>   INSERT INTO 【库名.】表名(字段名1,字段名2,……) VALUES(值1,值2,……);
>   ```
>
>   ```sql
>   -- 创建表 --
>   CREATE TABLE emp(
>      id INT,
>      name VARCHAR(10),
>      gender VARCHAR(10),
>      birthday DATE,
>      salary DECIMAL(10,2),
>      entry_date DATE,
>      resume_text VARCHAR(200)
>    );
>   ```
>
>   ```sql
>   -- 插入全部数据 --
>   INSERT INTO emp(	-- VALUES中的值一定要和INSERT语句中的列名顺序对应--
>     id,
>     name,
>     gender,
>     birthday,
>     salary,
>     entry_date,
>     resume_text
>   ) VALUES(
>     1,
>     'lilei',
>     'male',
>     '1992-05-10',		-- 插入的日期和字符一样，都使用引号括起来--
>     8000,
>     '2013-06-10',
>     'none'	
>   );
>   
>   或者
>   INSERT INTO emp
>   VALUES(				-- VALUES中值的顺序必须与数据表中字段的顺序对应--
>     3,
>     'king',
>     'female',
>     '1993-06-15',
>     9000,
>     '2014-07-10',
>     null			-- 如果插入空值，请使用null--
>   );
>   ```
>
> - 为指定列插入数据
>
>   ```sql
>   INSERT INTO emp(
>     id,
>     name,
>     gender,
>     birthday
>   ) VALUES(
>     5,
>     'mary',
>     'female',
>     '1995-07-10'
>   );
>   ```
>
> - 批量插入数据
>
>   语法：
>
>   ```
>   INSERT INTO 表名[(字段名1,字段名2,……)] 
>                               
>   VALUES(值1,值2,……),
>                               
>        (值1，值2,……),
>                               
>         ……
>                               
>        (值1，值2,……);
>   ```
>   
>   ```sql
>    CREATE TABLE teacher(
>      id INT,
>      name VARCHAR(50),
>      age INT
>    );
>                               
>    INSERT INTO teacher 
>    VALUES	(1,'AA',20),
>    		(2,'BB',21);
>   或者
>    INSERT INTO teacher(id,name) 
>    VALUES	(3,'CC'),
>   		(4,'DD');
>                               
>   ```
>
> 

### 4.7.2 更新数据★

> 语法：
>
> ```
> UPDATE 表名
> 
>    SET 字段名1=值1 [,字段名2=值2,……]
> 
>    [WHERE条件表达式];
> ```
>
> ```sql
> 将所有员工薪水修改为10000元。
> UPDATE emp SET salary=10000 
> 
> 将姓名为‘zs’的员工薪水修改为8000元。
> UPDATE emp SET salary=8000 WHERE name='zhangsan';
> 
> 将姓名为‘lisi’的员工薪水修改为9000元,gender改为‘female’。
> UPDATE emp SET salary=9000,gender='female' WHERE name='lisi';
> 
> 将所有女性员工的薪水在原有基础上增加1000元。
> UPDATE emp SET salary=salary+1000 WHERE gender='female'; 
> 
> ```
>
> 

### 4.7.3 删除数据★

> - 使用删除数据
>
>   语法：DELETE FROM 表名 [WHERE 条件表达式];
>
>   ```sql
>   删除表中名称为‘zs’的记录。
>   DELETE FROM emp WHERE name='zs';
>   
>   删除表中所有记录。
>   DELETE FROM emp;
>   ```
>
> - 使用TRUNCATE删除数据 （DDL）
>
>   语法：TRUNCATE [TABLE] 表名;
>
>   ```sql
>   删除emp表中所有数据：
>   TRUNCATE TABLE emp;
>   ```
>
>   
>
> - DELETE和TRUNCATE区别：
>
>   ```
>   DELETE语句是DML语句，TRUNCATE语句通常被认为是DDL语句。
>                                                                                                     
>   DELETE 删除表中的数据，表结构还在；DELETE语句后面可以跟WHERE子句，指定条件从而实现删除部分数据;删除后的数据可以找回。删除数据后主键id不能再使用。
>                                                                                                     
>   TRUNCATE 语句只能用于删除表中所有的数据；实际是把表直接DROP掉，然后再创建一个同样的新表；主键id会重新计算再次使用；删除的数据不能找回。无法进行事务回滚。执行速度比DELETE快。
>                                                                                                     
>   ```
>
>   

## 4.8 DQL数据查询★★★

因为查询语句使用的非常的频繁，所以很多人把查询语句单拎出来一类（重要）

查询关键字：**SELECT** 

SELECT语句是用于查看计算结果或者查看从数据表中筛选出的数据的。

SELECT语句语法格式：

```mysql
SELECT 
	selection_list /*要查询的列名称*/
	
【 FROM table_list /*要查询的表名称*/

WHERE condition /*行条件*/

GROUP BY grouping_columns /*对结果分组*/

HAVING condition /*分组后的行条件*/

ORDER BY sorting_columns /*排序*/

LIMIT offset_start, row_count /*结果限定*/ 】
```

SELECT语句用于计算或使用函数的基本语法（了解）：

```mysql
SELECT 常量;
SELECT 表达式;
SELECT 函数;
```

示例：

```mysql
SELECT 1; 
SELECT 9 + 2;
SELECT 9 / 2; -- 结果为4.5
SELECT 9 DIV 2;-- 结果为4 ，只保留整数
SELECT 9 % 2; -- 同 SELECT 9 MOD 2;
SELECT NOW(); 
```

> 算术运算符：加`+`，减`-`，乘`*`，除`/` 、`DIV`，模`%`、 `MOD`

**如果要从数据表中筛选数据，需要加FROM子句。FROM指定数据来源。字段列表筛选列。**

> 本课件主要案例需要预先导入数据：[练习脚标.sql](练习脚本.sql)

### 4.8.1  简单查询★

#### **查询所有字段**

```sql
查询stu表中的所有数据：
SELECT * FROM stu;
```

#### **查询指定字段**

```sql
查询stu表中所有的sid和sname：
SELECT sid, sname FROM stu;
```

#### **去除重复记录**

​	去除重复记录（两行或两行以上记录中列的数据都相同），例如emp表中sal字段就存在相同的记录。当只查询emp表的sal字段时，那么会出现重复记录，那么想去除重复记录，需要使用DISTINCT：

语法格式：SELECT DISTINCT 字段名 FROM 表名;

```sql
select distinct did from t_employee;-- 查询员工的部门编号
```

#### **使用别名**

在当前select语句中给某个字段或表达式计算结果，或表等取个临时名称，便于当前select语句的编写和理解。这个临时名称称为别名。

```mysql
select 字段名1 as "别名1", 字段名2 as "别名2" from 表名称 as 别名;
```

- 当列的别名中包含有空格等特殊符号时，必须加双引号。
- **表的别名<font color='red'>不能</font>加双引号**，表的别名中间不能包含空格等特殊符号。
- as大小写都可以，as也完全可以省略。

```sql
SELECT eid AS "编号",ename AS "姓名",salary AS "薪 资" FROM t_employee AS emp;
```

​	给列起别名时，是可以省略AS关键字的：

```sql
SELECT eid "编号",ename "姓名",salary "薪 资" FROM t_employee AS emp;
```

```mysql
SELECT ename,salary,salary+1000 new_sal FROM t_employee;
```

```mysql
#查询员工的姓名、薪资、奖金比例、实发工资
#实发工资 = 薪资 + 薪资 * 奖金比例
#NULL在mysql中比较和计算都有特殊性，所有的计算遇到的null都是null。
SELECT ename AS 姓名,
salary AS 薪资,
commission_pct AS 奖金比例,
salary + salary * IFNULL(commission_pct,0) AS 实发工资
FROM t_employee;
```

### 4.8.2 条件查询与运算符★

条件查询就是在查询时给出WHERE子句，在WHERE子句中可以使用如下运算符及关键字：

| **运算符**、关键字     | **含义**                                           |
| ---------------------- | -------------------------------------------------- |
| =                      | 等于,不能用于判断null                              |
| <=>                    | 安全等于，可以用于null判断                         |
| !=                     | 不等于                                             |
| <>                     | 不等于   有个别数据库不支持“!=”，所以建议使用“<>”  |
| <                      | 小于                                               |
| <=                     | 小于等于                                           |
| >                      | 大于                                               |
| >=                     | 大于等于                                           |
| AND 或 &&              | 与，同时满足多个条件                               |
| OR 或 \|\|             | 或，满足多个条件之一即可                           |
| NOT 或 !               | 非，满足此相反条件                                 |
| IN(set) 或 NOT IN(set) | 判断某个字段是否在指定集合中                       |
| IS NULL 或 IS NOT NULL | 关键字判断是否为空值                               |
| [NOT] LIKE             | 模糊查询，配合“%”和“_”使用，只针对字符串或日期类型 |
| BETWEEN x AND y        | 表示区间范围                                       |

> - 比较运算产生的结果为1(TRUE)、0 (FALSE)或  NULL。这些运算可用于数字和字符串。根据需要，字符串可自动转换为数字，而数字也可自动转换为字符串。
>
>   列如：SELECT 1<'2x';结果为1。SELECT 3<'2x';结果为0
>
> - 在SQL中，所有逻辑 操作符的求值所得结果均为 TRUE、FALSE或 NULL  (UNKNOWN)。在 MySQL中，它们体现为 1 (TRUE)、 0  (FALSE)和 NULL
>
>   - 逻辑AND。当所有操作数均为非零值、并且不为NULL时，计算所得结果为 1  ，当一个或多个操作数为0 时，所得结果为 0 ，其余情况返回值为 NULL  。
>
>   - 逻辑 OR。当两个操作数均为非  NULL值时，如有任意一个操作数为非零值，则结果为1，否则结果为0。当有一个操作数为NULL时，如另一个操作数为非零值，则结果为1，否则结果为  NULL 。假如两个操作数均为 NULL，则所得结果为 NULL。

(1)	查询薪资高于9000的女性员工信息

```sql
SELECT * FROM t_employee WHERE salary>9000 AND gender='女';
```

(2)	查询编号是1或者姓名是'贾宝玉'的员工信息

```sql
SELECT * FROM t_employee WHERE eid=1 OR ename='贾宝玉';
```

(3)	查询编号为1,3,5的员工记录

```sql
SELECT * FROM t_employee WHERE eid=1 OR eid=3 OR eid=5;
SELECT * FROM t_employee WHERE eid IN(1,3,5);
```

(4)	查询编号不是1,3,5的员工记录

```sql
SELECT * FROM t_employee WHERE eid NOT IN(1,3,5);
SELECT * FROM t_employee WHERE NOT eid IN(1,3,5);
```

(5)　查询薪资在9000到10000之间的员工记录

```sql
SELECT * FROM t_employee WHERE salary >= 9000 AND salary <=10000;
SELECT * FROM t_employee WHERE salary BETWEEN 9000 AND 10000;
```

(6)　查询薪资不在9000到15000之间的员工记录

```mysql
SELECT * FROM t_employee WHERE salary NOT BETWEEN 9000 AND 15000;
SELECT * FROM t_employee WHERE NOT salary  BETWEEN 9000 AND 15000;
```

(7)	查询佣金比例为null的员工记录

```sql
SELECT * FROM t_employee WHERE commission_pct = NULL;#失败
SELECT * FROM t_employee WHERE commission_pct <=> NULL;#安全等于，可用于判断null值
SELECT * FROM t_employee WHERE commission_pct IS NULL;
```

(8)	查询佣金比例不为null的员工记录

```sql
SELECT * FROM t_employee WHERE commission_pct IS NOT NULL;
SELECT * FROM t_employee WHERE NOT commission_pct IS NULL;
```



### 4.8.3 模糊查询★

当想查询姓名中包含a字母的学生时就需要使用模糊查询了。模糊查询需要使用关键字LIKE。

通配符: 

​	_ 表示一个字符

​	% 表示任意0~n个字符

(1)	查询姓名由4个字构成的员工记录

```sql
SELECT * FROM t_employee WHERE ename LIKE '____';
-- 其中 “_”匹配任意一个字符，4个“_”表示4个任意字符
```

 (2)	查询名字以’李'开头的员工记录

```sql
SELECT * FROM t_employee WHERE ename LIKE '李%';
```

(3)	查询姓名第三个字是'格'的员工记录

```sql
SELECT * FROM t_employee WHERE ename LIKE '__格%';
```

(4)	#查询名字中包含'冰'字的员工记录

```sql
SELECT * FROM t_employee WHERE ename LIKE '%冰%';
```

### 4.8.4  排序★

语法格式：

```
SELECT 字段名1,字段名2,……

FROM 表名

ORDER BY  字段名1 [ASC|DESC], 字段名2 [ASC|DESC]……
```

(1)　查询所有员工记录，结果按薪资升序排序

```sql
SELECT * FROM t_employee
ORDER BY salary ASC;
#或者
SELECT *
FROM stu
ORDER BY sage;	-- 默认ASC升序排列--
```

(2)　查询所有员工记录，按编号降序排序

```sql
SELECT *
FROM t_employee
ORDER BY   eid DESC;
```

(3)　查询员工的薪资，按照薪资从低到高，薪资相同按照员工编号从高到低

```sql

SELECT *
FROM t_employee
ORDER BY salary ASC , eid DESC;
```

### 4.8.5  聚合函数  ★

聚合函数是用来做纵向运算的函数：

| 函数    | 含义                                                         |
| ------- | ------------------------------------------------------------ |
| COUNT() | 统计指定列不为NULL的记录行数                                 |
| MAX()   | 计算指定列的最大值，如果指定列是字符串类型，那么使用字符串排序运算 |
| MIN()   | 计算指定列的最小值，如果指定列是字符串类型，那么使用字符串排序运算 |
| SUM()   | 计算指定列的数值和，如果指定列类型不是数值类型，那么计算结果为0 |
| AVG()   | 计算指定列的平均值，如果指定列类型不是数值类型，那么计算结果为0 |

(1)　**COUNT**

当需要纵向统计时可以使用COUNT()。

语法格式：SELECT COUNT(*|1|列名) FROM 表名;

查询员工表中记录总数：

```sql
SELECT COUNT(*) AS total FROM t_employee;
```

查询员工表中有佣金的人数：

```sql
SELECT COUNT(commission_pct) total FROM t_employee;
```

注意：因为count()函数中给出的是commission_pct列，那么只统计commission_pct列非NULL的行数。

查询员工表中月薪大于15000的人数：

```sql
SELECT COUNT(*) FROM t_employee WHERE salary > 15000;
```

统计实发月工资大于15000元的人数：

```sql
SELECT COUNT(*) FROM t_employee WHERE salary*(IFNULL(commission_pct,0)+1) > 15000;
```

(2)　**SUM和AVG**

当需要纵向求和时使用sum()函数。

查询所有雇员月薪和：

```sql
SELECT SUM(salary) FROM t_employee;-- 不包含佣金
```

查询所有雇员月薪和，以及所有雇员佣金和：

```sql
SELECT SUM(salary), SUM(salary*IFNULL(commission_pct,0)) FROM t_employee;
```

查询所有雇员月薪+佣金和：

```sql
SELECT SUM(salary+salary*IFNULL(commission_pct,0)) FROM t_employee;
```

统计所有员工平均工资：

```sql
SELECT AVG(salary) FROM t_employee;
```

(3)　**MAX和MIN**

查询最高工资和最低工资：

```sql
SELECT MAX(salary), MIN(salary) FROM t_employee;
```

找出年龄最小、最大的员工的出生日期

```mysql
SELECT MAX(birthday),MIN(birthday) FROM t_employee;
```



### 4.8.6 分组查询★

当需要分组查询时需要使用GROUP BY子句，例如查询每个部门的工资和，这说明要按照部门来分组。

注：凡和聚合函数同时出现的列名，则一定要写在group by 之后

1. **分组查询**

查询每个部门的部门编号和每个部门的工资和：

```sql
SELECT did,SUM(salary) "sum"
FROM t_employee
GROUP BY did;
```

查询每个部门的部门编号以及每个部门的人数，并按人数倒序排序：

```sql
SELECT did,COUNT(*) "cnt"
FROM t_employee
WHERE salary>10000
GROUP BY did ORDER BY cnt DESC;
```

查询每个部门的部门编号以及每个部门工资大于10000的人数：

```sql
SELECT did,COUNT(*)
FROM t_employee
WHERE salary>10000
GROUP BY did;
-- ----------------------
SELECT did,COUNT(*) "count"
FROM t_employee
WHERE salary>10000
GROUP BY did WITH ROLLUP -- 合计，WITH ROLLUP，加在group by后面，用于合计分组统计结果
```

2. **HAVING子句**

查询工资总和大于40000的部门编号以及工资和：

```sql
SELECT did, SUM(salary) 
FROM t_employee
GROUP BY did
HAVING SUM(salary) > 40000;
#或者
SELECT did, SUM(salary) "total"
FROM t_employee
GROUP BY did
HAVING total > 40000;-- 使用别名
```

查询部门女员工薪资总和大于20000的部门编号及部门女员工工资总和

```mysql
SELECT did, SUM(salary) "total"
FROM t_employee
WHERE gender='女'
GROUP BY did 
HAVING total > 20000 ;
```

注：HAVING与WHERE的区别:

> 1. *HAVING是在分组后对数据进行过滤，WHERE是在分组前对数据进行过滤；*        
>2. *HAVING后面可以使用分组函数(统计函数)， WHERE后面不可以使用分组函数。*
> 3. *WHERE是对分组前记录的条件，如果某行记录没有满足WHERE子句的条件，那么这行记录不会参加分组；而HAVING是对分组后数据的约束。*



### 4.8.7  LIMIT★

LIMIT用来限定查询结果的起始行，以及总行数。

语法格式：SELECT 字段名1,字段名2,……FROM 表名 LIMIT [m,]n;

如上语法格式中，LIMIT后可以跟两个参数，第一个参数m是可选的，代表起始索引，若不指定则使用默认值0，代表第一条记录，第二个参数n是必选的，代表从第m+1条记录开始，取n条记录，

1.　查询5行记录，起始行从0开始

```sql
SELECT * FROM t_employee LIMIT 0, 5;
或者
SELECT * FROM t_employee LIMIT 5;
```

*注意：起始行从0开始，即第一行开始！*

2.　从第三条记录开始，查询10行记录

```sql
SELECT* FROM t_employee LIMIT 3, 10;
```

3.　分页查询

如果每一页记录为10条，希望查看第3页记录应该怎么查呢？

第一页记录起始行为0，一共查询10行；

第二页记录起始行为10，一共查询10行；

第三页记录起始行为20，一共查询10行；

如果每一页记录为n条，希望查看第x页记录应该怎么查呢？

​	LIMIT (x-1)*n, n



## 4.9查询语句语法顺序★★★

查询语句书写顺序：SELECT - FROM - WHERE - GROUP BY- HAVING - ORDER BY - LIMIT

查询语句执行顺序：FROM - WHERE -GROUP BY - HAVING - SELECT - ORDER BY - LIMIT  





# 5 约束

## 5.1 约束的概念理解

1. **约束（CONSTRAINTS）是强制加在表上的一些规定**。**约束的作用是为了保证数据的完整性**，数据完整性（Data Integrity）是指数据的精确性（Accuracy）和可靠性（Reliability）。也就是说约束是为了防止数据库中存在不符合语义规定的数据和防止因错误信息的输入输出造成无效操作或错误信息而提出的。

   数据的完整性要从以下四个方面考虑：

   * 实体完整性（Entity Integrity）：例如，同一个表中，不能存在两条完全相同无法区分的记录
   * 域完整性（Domain Integrity）：例如：年龄范围0-120，性别范围“男/女”
   * 引用完整性（Referential Integrity）：例如：员工所在部门，在部门表中要能找到这个部门
   * 用户自定义完整性（User-defined Integrity）：例如：用户名唯一、密码不能为空等，本部门经理的工资不得高于本部门职工的平均工资的5倍。

2. **根据约束的作用，约束可分为：**

   - PRIMARY KEY 主键(非空且唯一)约束

   - UNIQUE 唯一键约束，规定某个字段在整个表中是唯一的

   - FOREIGN KEY 外键约束
   - NOT NULL 非空约束，规定某个字段不能为空

   - CHECK 检查约束

   - DEFAULT 默认值约束

     AUTO_INCREMENT自增是键约束字段的一个额外的属性。

3. 根据约束的影响范围可分为：

   - **表级约束**

     所有键约束和检查约束是表级约束，即不仅要看约束字段当前单元格的数据，还要看其他单元格的数据。

   - **列级约束**

     非空约束和默认值约束都是列级约束，即约束字段只看当前单元格的数据即可，和其他单元格无关。

   所有的表级约束都可以在`information_schema.table_constraints`表中查看。

   ```mysql
   SELECT * FROM information_schema.table_constraints WHERE table_name = '表名称';
   ```

4. **约束与索引（INDEX）**

   在MySQL中键约束会自动创建索引，提高查询效率。索引的详细讲解在高级部分。

   MySQL高级会给大家讲解索引、存储引擎等，因为高级阶段要给大家分析SQL性能。而基础阶段先不强调效率，只要能查出来就行。

   约束是一个逻辑概念，它不会单独占用物理空间，

   索引是一个物理概念，它是会占用物理空间。

   例如：字典

   字典里面有要求，不能有重复的字（字一样，读音也一样），这是约束。

   字典里面有“目录”，它可以快速的查找某个字，目录需要占用单独的页。

## 5.2 非空约束

**作用：非空约束用于保证数据表中某个字段的值不为NULL**

**关键字：NOT NULL**

**特点：非空约束只能出现在表对象的列上限定当前列值非空**

> 默认所有的类型的值都可以是NULL，包括INT、FLOAT等数据类型
>
> 空字符串''不等于NULL，0也不等于NULL

**添加与删除非空约束示例：**

```sql
CREATE TABLE student(
  Id int,
  Name varchar(50) NOT NULL,	-- 创建表示添加非空约束--
  tel char(11)
);
INSERT INTO student values(1,’tom’,null);

-- 为已存在表的列添加非空约束(了解)--
ALTER TABLE student MODIFY tel char(11) NOT NULL;

-- 删除非空约束（了解）--
ALTER TABLE student MODIFY tel char(11);
```

## 5.3 唯一键约束     

**作用：用来限制某个字段/某列的值不能重复。**

**关键字：UNIQUE** 

**特点：数据唯一，可以为NULL，每个表可以有多个唯一键约束。**

> 可以多个列组合的值唯一。
>
> 在创建唯一约束的时候，如果不给唯一约束命名，就默认和列名相同。
>
> MySQL会给唯一约束的列上默认创建一个唯一索引。
>
> 删除唯一约束只能通过删除唯一索引的方式删除。

**添加与删除唯一约束示例：**

创建表时添加唯一约束

```sql
#方式一：
CREATE TABLE student(
  Id int PRIMARY KEY,
  Name varchar(50) UNIQUE,
  tel char(11) UNIQUE
);
```

```mysql
#方式二：方便创建联合唯一键
CREATE TABLE student(
  Id int PRIMARY KEY,
  Name varchar(50),
  tel char(11),
  UNIQUE KEY(Name),
  UNIQUE KEY(tel)
);
```

创建表后添加唯一约束（了解）

```sql
CREATE TABLE student(
  id int,
  name varchar(50)
);
-- 为已存在表添加唯一约束--
ALTER TABLE student ADD UNIQUE (name);
```

删除唯一键约束（索引）（了解）

```mysql
ALTER TABLE student DROP INDEX name;
```

查看表的索引（了解）

```mysql
show index from 表名称;
```

## 5.4 主键约束

**作用：用于唯一地标识表中的某一条记录。**

**关键字：PRIMARY KEY**

**特点：数据唯一，且不能为NULL（相当于唯一约束与非空约束的组合），每个表中最多有一个主键。**

> 当创建主键约束时，系统默认会在所在的列或列组合上建立对应的主键索引（能够根据主键查询
> 的，就根据主键查询，效率更高）。如果删除主键约束了，主键约束对应的索引就自动删除了。
>
> 需要注意的一点是，不要修改主键字段的值。因为主键是数据记录的唯一标识，如果修改了主键的
> 值，就有可能会破坏数据的完整性。

为表添加主键约束操作示例：

方式一：创建表时添加主键约束

```sql
CREATE TABLE student(
  id int PRIMARY KEY,
  name varchar(50)
);
```

方式二：此种方式优势在于，可以创建联合主键

```sql
CREATE TABLE student(
  id int,
  name varchar(50),
  PRIMARY KEY(id)
);
```

创建联合主键

```sql
CREATE TABLE student(
  classid int,
  stuid int,
  name varchar(50),
  PRIMARY KEY(classid，stuid)
);
```

创建表后添加主键约束（了解）

```sql
CREATE TABLE student(
  id int,
  name varchar(50)
);
-- 为已存在的表添加主键约束--
ALTER TABLE student ADD PRIMARY KEY (id);
```

删除主键约束（了解）：

```mysql
alter table student drop primary key;
```

## 5.5 自动增长列

自增是键约束字段的一个额外的属性。

**作用：给主键添加自动增长的数值，默认从初始值1开始自增**。当需要产生唯一标识符或顺序值时，可设置自增长

**关键字：AUTO_INCREMENT** 

> SQL Server数据库 (identity) ，Oracle数据库(sequence)

特点与要求：

> 一个表最多只能有一个自增长列
>
> 自增长列约束的列必须是键列（主键列，唯一键列等），通常是主键
>
> 自增约束的列的数据类型必须是整数类型
>
> 如果自增列指定了 0 值和 null值，会在当前最大值的基础上自增；如果自增列手动指定了其它具体值，直接赋值为指定的具体值。
>

**添加与删除自增示例：**

添加自增约束

```sql
CREATE TABLE student(
  id int PRIMARY KEY AUTO_INCREMENT,
  name varchar(50)
);

INSERT INTO student(name) values(‘tom’);
```

```sql
CREATE TABLE student(
  id int PRIMARY KEY,
  name varchar(50)
);
-- 为已存在表的列添加自增约束（了解）--
ALTER TABLE student MODIFY id INT  AUTO_INCREMENT;

```

删除自增列（了解）

```mysql
alter table student modify id INT;
```

## 5.6 默认值约束

**作用：给某个字段/某列指定默认值**

**关键字：DEFAULT**

**特点：一旦设置默认值，在插入数据时，如果此字段没有显式赋值，则赋值为默认值。常与非空约束一起使用**

**操作示例：**

```sql
CREATE TABLE student(
  Id int pirmary key,
  Name varchar(50) NOT NULL,
  gender char NOT NULL DEFAULT '男' -- 创建表时，为gender字段添加默认值 --
);
insert into student values(1,'lily','女');

insert into student values(2,'jerry',DEFAULT);
-- 修改gender默认值为‘女’（了解）
ALTER TABLE student MODIFY gender char DEFAULT '女';
-- 删除gender字段默认值约束，如果有非空约束，也一并删除（了解）
alter table student modify gender char; 
```

## 5.7 CHECK约束

**作用：检查某个字段的数据是否符合指定的要求。一般指的是值的范围**

**关键字：CHECK**

> 说明：从 MySQL 8.0.16 开始支持CHECK约束，CREATE TABLE 允许所有存储引擎的表和列 CHECK 约束的核心功能。

**操作示例：**

```mysql
CREATE TABLE t1(
  CHECK (c1 <> c2), -- 表约束：支持引用其他列名
  c1 INT CHECK (c1 > 10), -- 列约束：只能引用当前正在定义的列名
  c2 INT CONSTRAINT c2_positive CHECK (c2 > 0),-- 列约束
  c3 INT CHECK (c3 < 100),-- 列约束
  CONSTRAINT c1_nonzero CHECK (c1 <> 0),-- 表约束
  CHECK (c1 > c3)-- 表约束
);
-- ----------------------
CREATE TABLE student(
	id int PRIMARY key auto_increment, -- 自增列不能使用check约束
	name varchar(10),
	age int check(age>=18 and age<100),
    gender char check(gender in('男','女'),
    sex char enum('m','f'),
    work_city set('bj','sh','sz','gz')                  
);
insert into student value(1,'tom',19,'男','m','bj');
insert into student value(2,'rose',19,'女','f','bj,sz');
```

建表后添加check约束（了解）

```mysql
#如何在建表后添加检查约束，使用add check
alter table 表名称 add check(条件);
```

删除check约束（了解）

```mysql
alter table 表名称 drop check 检查约束名;
```



## 5.8  外键约束

**作用：限定某个表的某个字段的引用完整性。**

> 引用完整性是对实体之间关系的描述，即外键与引用键之间的引用规则，外键约束的作用就是保证这种关系准确可靠。

**关键字：FOREIGN KEY**

**相关概念：**

- **主表（父表）**：被引用的表，被参考的表

- **从表（子表）**：引用别人的表，参考别人的表

- **外键：**从表中的某个字段，引用自主表中的某个字段或多个字段

- **引用键：**主表中被引用的字段

  > 例如：员工表的员工所在部门这个字段did的值要参考部门表的字段id。
  >
  > 部门表是主表，员工表是从表。
  >
  > 员工表的did字段为外键，对应的引用键为的部门表的id字段

**特点：**

- 从表的外键列，必须引用/参考主表的主键或唯一约束的列，通常是主键。
  为什么？因为被依赖/被参考的值必须是唯一的
- 外键和引用键中的对应列名字可以不相同，但是数据类型必须一样，逻辑意义一致。
- 当主表的记录被从表参照时，主表的记录将不允许删除，如果要删除数据，需要先删除从表中依赖
  该记录的数据，然后才可以删除主表的数据
- 在创建外键约束时，如果不给外键约束命名，默认名不是列名，而是自动产生一个外键名（例如
  student_ibfk_1;），也可以指定外键约束名。
- 创建(CREATE)表时就指定外键约束的话，先创建主表，再创建从表
- 删表时，先删从表（或先删除外键约束），再删除主表
- 在“从表”中指定外键约束，并且一个表可以建立多个外键约束

**操作示例：**

第一种添加外键约束的方式：创建从表时添加

```sql
-- 主表--
CREATE TABLE student(
  sid int primary key,
  name varchar(10) not null,
  sex char(10) default '男'
);
```

```sql
-- 从表--
create table score(
  id int,
  score int,
  sid int ,	 -- 外键，列的数据类型一定要与主键的类型一致
  CONSTRAINT fk_stu_score foreign key(sid) references student(sid)
);
```

第二种添加外键方式：创建从表时未添加外键约束，通过修改从表添加外键约束。

```sql
ALTER table score ADD CONSTRAINT fk_stu_score FOREIGN KEY(sid) REFERENCES student(sid);
```

删除外键约束

```sql
ALTER TABLE score DROP FOREIGN KEY fk_stu_score;
```

> 问题：建和不建外键约束有什么区别？
>
> 答：建外键约束，你的操作（创建表、删除表、添加、修改、删除）会受到限制，从语法层面受到限制。例如：在员工表中不可能添加一个员工信息，它的部门的值在部门表中找不到。
>
> 不建外键约束，你的操作（创建表、删除表、添加、修改、删除）不受限制，要保证数据的引用完整性，只能依靠程序员的自觉，或者是在Java程序中进行限定。例如：在员工表中，可以添加一个员工的信息，它的部门指定为一个完全不存在的部门。
>
> 外键约束会影响性能，效率，所以在并发大的互联网项目中很多人不愿意加外键约束。
>
> 在 MySQL 里，外键约束是有成本的，需要消耗系统资源。对于大并发的 SQL 操作，有可能会不适合。比如大型网站的中央数据库，可能会因为外键约束的系统开销而变得非常慢。所以， MySQL 允许你不使用系统自带的外键约束，在应用层面完成检查数据一致性的逻辑。也就是说，即使你不用外键约束，也要想办法通过应用层面的附加逻辑，来实现外键约束的功能，确保数据的一致性。



> 外键约束可以设置约束等级：
> （1）No action方式：如果子表中有匹配的记录,则不允许对父表对应候选键进行update/delete操作  
> （2）Restrict方式：同no action, 都是立即检查外键约束
> （3）Set null方式：在父表上update/delete记录时，将子表上匹配记录的列设为null，但是要注意子表的外键列不能为not null  
> （4）Cascade方式：在父表上update/delete记录时，同步update/delete掉子表的匹配记录 
> （5）Set default方式（在可视化工具SQLyog中可能显示空白）：父表有变更时,子表将外键列设置成一个默认的值，但Innodb不能识别
>
> on update No action | Restrict | Set null | Cascade 
> on delete No action | Restrict | Set null | Cascade 
> 如果没有写on update 或on delete字句，表示选择了No action | Restrict。





# 6 多表关联查询★★★

多个表之间是有关系的，那么关系靠谁来维护?

外键约束或程序员通过代码逻辑自行维护

## 6.1  多表的关系

1.  **一对多（多对一）**

   在一对多关系中，主键数据表中只能含有一个记录，而在其关系表中这条记录可以与一个或者多个记录相关，也可以没有记录与之相关，这种关系与现实生活中父子的关系很像，每个孩子都有一个父亲，但一个父亲可能有多个孩子，也可能没有孩子，多对一是从不同角度来看问题。

   如：客户和订单，分类和商品，部门和员工.

   一对多建表原则：在多的一方创建一个字段，字段作为外键指向一的一方的主键.

2.  **多对多关系**

   在多对多关系中，两个数据表里的每条记录都可以和另一个数据表里任意数量的记录相关，这种关系与现实生活中学生与选修课的关系很像，一个学生可以有多门选修课，一门选修课也可以有多个学生。

   多对多关系建表原则：需要创建第三张表,中间表中至少两个字段，这两个字段分别作为外键指向各自一方的主键.

3. **一对一关系**

   在一对一关系中，关系表的每一边都只能存在一条记录，每个数据表中的关键字在对应的关系表中只能存在一条记录或者没有对应的记录，这种关系与现实生活中配偶的关系很像，如果已经结婚，那么只有一个配偶，如果没有结婚，那么没有配偶。

   在实际的开发中应用不多.因为一对一可以创建成一张表.

   两种建表原则：

   唯一外键对应：假设一对一是一个一对多的关系，在多的一方创建一个外键指向一的一方的主键，将外键设置为unique.

   主键对应：让一对一的双方的主键进行建立关系.

##  6.2  JOIN关联查询

关联查询：两个或更多个表一起联合查询。

前提条件：这些一起查询的表之间是有关系的（一对一、一对多），它们之间一定是有关联字段，这个关联字段可能建立了外键，也可能没有建立外键，总之这个字段至少使得表与表之间建立了一种逻辑关系。。

比如：员工表和部门表，这两个表依靠“部门编号”进行关联。

### 6.2.1 内连接★

连接查询就是求出多个表的乘积，例如t1连接t2，那么查询出的结果就是t1*t2。

![032](imgs/032.jpg)

连接查询会产生笛卡尔积，假设集合A={a,b}，集合B={0,1,2}，则两个集合的笛卡尔积为{(a,0),(a,1),(a,2),(b,0),(b,1),(b,2)}。可以扩展到多个集合的情况。

那么多表查询产生这样的结果并不是我们想要的，那么怎么去除重复的，不想要的记录呢，当然是通过条件过滤。通常要查询的多个表之间都存在关联关系，那么就通过关联关系去除笛卡尔积。

执行如下SQL语句

```sql
select * from t_employee,t_department;
```

**使用主外键关系字段做为条件来去除无用信息**

```sql
SELECT * FROM t_employee emp,t_department emp WHERE emp.did=dept.did;
```

上面查询结果会把两张表的所有列都查询出来，也许你不需要那么多列，这时就可以指定要查询的列了。

```sql
SELECT emp.ename,emp.sal,emp.comm,dept.dname 
FROM t_employee emp,t_department dept
WHERE emp.did=dept.did;
```

**上面的连接语句就是内连接，使用简单**

SQL内连接语法格式：

```
SELECT 查询字段 
FROM 表1 
[INNER]JOIN 表2 
ON 表1.关系字段 = 表2.关系字段 
[WHERE 查询条件];
-- 或者 -----------------
SELECT 查询字段 
FROM 表1, 表2 
WHERE 表1.关系字段 = 表2.关系字段 
```

```sql
SELECT * 
FROM t_employee emp
INNER JOIN t_department dept
ON emp.did=dept.did;
```

**内连接的特点：查询结果必须满足查询条件，结果相当于求两个表的交集**

### **6.2.2 外连接★**

包括左外连接和右外连接，外连接的特点：查询出的结果存在不满足查询条件的可能，结果除包函满足条件的数据外，还包含左表所有不满足条件的数据

#### A、左外连接

语法：	

```
SELECT 查询字段 
FROM 表1 
LEFT [OUTER] JOIN 表2 
ON 表1.关系字段=表2.关系字段 
[WHERE 查询条件];
```

```sql
SELECT * 
FROM t_employee emp
LEFT JOIN t_department dept
ON emp.did=dept.did;
```

左连接是先查询出左表（即以左表为主），然后查询右表，右表中满足条件的显示出来，不满足条件的显示NULL。

#### B、右外连接


右连接就是先把右表中所有记录都查询出来，然后左表满足条件的显示，不满足显示NULL。

```sql
SELECT * 
FROM t_employee emp
RIGHT JOIN t_department dept
ON emp.did=dept.did;
```

**连接查询心得**：

​	连接不限于两张表，连接查询也可以是三张、四张，甚至N张表的连接查询。通常连接查询不可能需要整个笛卡尔积，而只是需要其中一部分，那么这时就需要使用条件来去除不需要的记录。这个条件大多数情况下都是使用主外键关系去除。

两张表的连接查询一定有一个主外键关系，三张表的连接查询就一定有两个主外键关系，所以在大家不是很熟悉连接查询时，首先要学会去除无用笛卡尔积，那么就是用主外键关系作为条件来处理。如果两张表的查询，那么至少有一个主外键条件，三张表连接至少有两个主外键条件。

### 6.2.3**自然连接**★

​	前面学习的表连接查询，需要指定表与表之间的连接字段，SQL标准中还有一种自然连接，不需要指定连接字段，表与表之间列名和数据类型相同的字段，会被自动匹配，自然连接默认按内连接的方式进行查询，自然连接的语法格式如下所示。

SELECT 查询字段 FROM 表1 [别名] NATURAL [ LEFT | RIGHT ] JOIN 表2 [别名];

如上语法格式中，通过NATURAL关键字使两张表进行自然连接，可以理解为自动查找关联字段（要求关联字段同名同类型）。

```sql
SELECT * FROM t_employee NATURAL JOIN t_department; 
SELECT * FROM t_employee NATURAL LEFT JOIN t_department; 
SELECT * FROM t_employee NATURAL RIGHT JOIN t_department; 
```

### 6.2.4**自连接**★

​	自连接，它连接的两张表是同一张表，通过起别名进行区分。

语法格式：

```
SELECT 查询字段 
FROM 表1 [别名1]
[INNER]JOIN 表2 [别名2]
ON 关联字段关系
[where 条件]
```

```mysql
#查询每一个员工自己的编号、名字、薪资和他的领导的编号、姓名、薪资。
SELECT emp.eid,emp.ename,emp.salary,  mgr.eid,mgr.ename,mgr.salary
FROM t_employee AS emp INNER JOIN t_employee AS mgr
ON emp.mid = mgr.eid; 
```

### 6.2.5 联合查询字段列表问题★

```mysql
#查询字段的问题
#查询每一个员工及其所在部门的信息
#要求：显示员工的编号，姓名，部门编号，部门名称
SELECT eid,ename,did,dname
FROM t_employee INNER JOIN t_department
ON t_employee.did = t_department.did;
/*
错误代码： 1052
Column 'did' in field list is ambiguous（模糊不清的；引起歧义的）
*/

SELECT eid,ename,t_employee.did,dname
FROM t_employee INNER JOIN t_department
ON t_employee.did = t_department.did;


#查询每一个员工及其所在部门的信息
#要求，显示员工的编号，姓名，部门表的所有字段
SELECT eid,ename,t_department.*
FROM t_employee INNER JOIN t_department
ON t_employee.did = t_department.did;
```

## 6.3 UNION合并结果集

作用：**UNION**用于把来自许多SELECT语句的结果组合到一个结果集合中。

UNION查询有两种方式：

- UNION：会去除重复记录，例如：

  ```mysql
  SELECT 字段1,字段2,... FROM 表1
  UNION 
  SELECT 字段1,字段2,... FROM 表2;
  ```

- UNION ALL：不会去除重复记录，例如：

  ```mysql
  SELECT 字段1,字段2,... FROM 表1
  UNION ALL 
  SELECT 字段1,字段2,... FROM 表2;
  ```

注意：列于每个SELECT语句的对应位置的被选择的列应具有相同的类型。（例如，被第一个语句选择的第一列应和被其它语句选择的第一列具有相同的类型。）在第一个SELECT语句中被使用的列名称也被用于结果的列名称。

```mysql
SELECT *
FROM t_employee LEFT JOIN t_department
ON t_employee.did = t_department.did

UNION 

SELECT *
FROM t_employee RIGHT JOIN t_department
ON t_employee.did = t_department.did;
```





# 7 函数（拓展）

比较浪费效率，一般不用函数

函数：代表一个独立的可复用的功能。

和Java中的方法类似又有所不同，不同点在于：MySQL中的函数必须有返回值，参数可以有可以没有。

MySQL中函数分类：

- 系统预定义函数：MySQL数据库管理软件给我提供好的函数，直接用就可以，任何数据库都可以用公共的函数。

  - 分组函数：或者又称为聚合函数，多行函数，表示会对表中的多行记录一起做一个“运算”，得到一个结果。
    - 求平均值的avg，求最大值的max，求最小值的min，求总和sum，求个数的count等

  - 单行函数：表示会对表中的每一行记录分别计算，有n行得到还是n行结果
    - 数学函数、字符串函数、日期时间函数、条件判断函数、窗口函数等

- 用户自定义函数：由开发人员自己定义的，通过CREATE FUNCTION语句定义，是属于某个数据库的对象。




## 7.1 分组函数

**分组函数**-**聚合函数**-**分组函数**

分组函数作用于一组数据，并对一组数据返回一个值。

* **分组函数类型**

  * **AVG()** 
  * **SUM()**
  * **MAX()** 
  * **MIN()** 
  * **COUNT() **

  ```mysql
  #演示分组函数，聚合函数，多行函数
  #统计t_employee表的员工的数量
  SELECT COUNT(*) FROM t_employee;
  SELECT COUNT(1) FROM t_employee;
  SELECT COUNT(eid) FROM t_employee;
  SELECT COUNT(commission_pct) FROM t_employee;
  
  /*
  count(*)或count(常量值)：都是统计实际的行数。
  count(字段/表达式)：只统计“字段/表达式”部分非NULL值的行数。
  */
  
  #找出t_employee表中最高的薪资值
  SELECT MAX(salary) FROM t_employee;
  
  #找出t_employee表中最低的薪资值
  SELECT MIN(salary) FROM t_employee;
  
  #统计t_employee表中平均薪资值
  SELECT AVG(salary) FROM t_employee;
  
  #统计所有人的薪资总和，财务想看一下，一个月要准备多少钱发工资
  SELECT SUM(salary) FROM t_employee; #没有考虑奖金
  SELECT SUM(salary+salary*IFNULL(commission_pct,0)) FROM t_employee; 
  
  #找出年龄最小、最大的员工的出生日期
  SELECT MAX(birthday),MIN(birthday) FROM t_employee;
  
  #查询最新入职的员工的入职日期
  SELECT MAX(hiredate) FROM t_employee;
  ```



## 7.2单行函数

**只对一行进行变换，每行返回一个结果**

可以嵌套

参数可以是一字段或一个表达式或一个值

### 7.2.1 数值函数/数学函数

| 函数          | 用法                                 |
| :------------ | ------------------------------------ |
| ABS(x)        | 返回x的绝对值                        |
| CEIL(x)       | 返回大于x的最小整数值                |
| FLOOR(x)      | 返回小于x的最大整数值                |
| MOD(x,y)      | 返回x/y的模                          |
| RAND()        | 返回0~1的随机值                      |
| ROUND(x,y)    | 返回参数x的四舍五入的有y位的小数的值 |
| TRUNCATE(x,y) | 返回数字x截断为y位小数的结果         |
| SQRT(x)       | 返回x的平方根                        |
| POW(x,y)      | 返回x的y次方                         |

```mysql
SELECT ROUND(45.926, 2)  -- 45.93
SELECT TRUNCATE(45.926)  -- 45
SELECT MOD(1600, 300)	 -- 100
```

```mysql
#在“t_employee”表中查询员工无故旷工一天扣多少钱，
#分别用CEIL、FLOOR、ROUND、TRUNCATE函数。
#假设本月工作日总天数是22天，
#旷工一天扣的钱=salary/22。
SELECT ename,salary/22,CEIL(salary/22),
FLOOR(salary/22),ROUND(salary/22,2),
TRUNCATE(salary/22,2) FROM t_employee; 

#查询公司平均薪资，并对平均薪资分别
#使用CEIL、FLOOR、ROUND、TRUNCATE函数
SELECT AVG(salary),CEIL(AVG(salary)),
FLOOR(AVG(salary)),ROUND(AVG(salary)),
TRUNCATE(AVG(salary),2) FROM t_employee;
```

### 7.2.2 字符串函数

| 函数                            | 用法                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| CONCAT(S1,S2,......,Sn)         | 连接S1,S2,......,Sn为一个字符串                              |
| CONCAT_WS(s, S1,S2,......,Sn)   | 同CONCAT(s1,s2,...)函数，但是每个字符串之间要加上s           |
| CHAR_LENGTH(s)                  | 返回字符串s的字符数                                          |
| LENGTH(s)                       | 返回字符串s的字节数，和字符集有关                            |
| INSERT(str, index , len, instr) | 将字符串str从第index位置开始，len个字符长的子串替换为字符串instr |
| UPPER(s) 或 UCASE(s)            | 将字符串s的所有字母转成大写字母                              |
| LOWER(s)  或LCASE(s)            | 将字符串s的所有字母转成小写字母                              |
| LEFT(s,n)                       | 返回字符串s最左边的n个字符                                   |
| RIGHT(s,n)                      | 返回字符串s最右边的n个字符                                   |
| LPAD(str, len, pad)             | 用字符串pad对str最左边进行填充，直到str的长度为len个字符     |
| RPAD(str ,len, pad)             | 用字符串pad对str最右边进行填充，直到str的长度为len个字符     |
| LTRIM(s)                        | 去掉字符串s左侧的空格                                        |
| RTRIM(s)                        | 去掉字符串s右侧的空格                                        |
| TRIM(s)                         | 去掉字符串s开始与结尾的空格                                  |
| TRIM(【BOTH 】s1 FROM s)        | 去掉字符串s开始与结尾的s1                                    |
| TRIM(【LEADING】s1 FROM s)      | 去掉字符串s开始处的s1                                        |
| TRIM(【TRAILING】s1 FROM s)     | 去掉字符串s结尾处的s1                                        |
| REPEAT(str, n)                  | 返回str重复n次的结果                                         |
| REPLACE（str, a, b）            | 用字符串b替换字符串str中所有出现的字符串a                    |
| STRCMP(s1,s2)                   | 比较字符串s1,s2                                              |
| SUBSTRING(s,index,len)          | 返回从字符串s的index位置其len个字符                          |

```mysql
#字符串函数
#mysql中不支持 + 拼接字符串，需要调用函数来拼接
#（1）在“t_employee”表中查询员工姓名ename和电话tel，
#并使用CONCAT函数，CONCAT_WS函数。
SELECT CONCAT(ename,tel),CONCAT_WS('-',ename,tel) FROM t_employee;


#（2）在“t_employee”表中查询员工姓名和薪资，并把姓名处理成“张xx”的样式。
#LEFT（s，n）函数表示取字符串s最左边的n个字符，
#而RPAD（s，len，p）函数表示在字符串s的右边填充p使得字符串长度达到len。
SELECT  RPAD(LEFT(ename,1),3,'x'),salary
FROM t_employee;

#（3）在“t_employee”表中查询薪资高于10000的男员工姓名、
#姓名包含的字符数和占用的字节数。
SELECT ename,CHAR_LENGTH(ename) AS 占用字符数,LENGTH(ename) AS 占用字节数量
FROM t_employee
WHERE salary>10000 AND gender ='男';


#（4）在“t_employee”表中查询薪资高于10000的男员工姓名和邮箱email，
#并把邮箱名“@”字符之前的字符串截取出来。
SELECT ename,email,
SUBSTRING(email,1, POSITION('@' IN email)-1)
FROM t_employee
WHERE salary > 10000 AND gender ='男';
#mysql中 SUBSTRING截取字符串位置，下标从1开始，不是和Java一样从0开始。
#mysql中 position等指定字符串中某个字符，子串的位置也不是从0开始，都是从1开始。

SELECT TRIM('    hello   world   '); #默认是去掉前后空白符
SELECT CONCAT('[',TRIM('    hello   world   '),']'); #默认是去掉前后空白符
SELECT TRIM(BOTH '&' FROM '&&&&hello   world&&&&'); #去掉前后的&符号
SELECT TRIM(LEADING '&' FROM '&&&&hello   world&&&&'); #去掉开头的&符号
SELECT TRIM(TRAILING '&' FROM '&&&&hello   world&&&&'); #去掉结尾的&符号
```



### 7.2.3 日期函数

| 函数                                                         | 功能描述                                            |
| ------------------------------------------------------------ | --------------------------------------------------- |
| CURDATE()或CURRENT_DATE()                                    | 返回当前系统日期                                    |
| CURTIME()或CURRENT_TIME()                                    | 返回当前系统时间                                    |
| NOW()/SYSDATE()/CURRENT_TIMESTAMP()/  LOCALTIME()/LOCALTIMESTAMP() | 返回当前系统日期时间                                |
| UTC_DATE()/UTC_TIME()                                        | 返回当前UTC日期值/时间值                            |
| UNIX_TIMESTAMP(date)                                         | 返回一个UNIX时间戳                                  |
| YEAR(date)/MONTH(date)/DAY(date)/  HOUR(time)/MINUTE(time)/SECOND(time) | 返回具体的时间值                                    |
| EXTRACT(type FROM date)                                      | 从日期中提取一部分值                                |
| DAYOFMONTH(date)/DAYOFYEAR(date)                             | 返回一月/年中第几天                                 |
| WEEK(date)/WEEKOFYEAR(date)                                  | 返回一年中的第几周                                  |
| DAYOFWEEK()                                                  | 返回周几，注意，周日是1，周一是2，…周六是7          |
| WEEKDAY(date)                                                | 返回周几，注意，周一是0，周二是1，…周日是6          |
| DAYNAME(date)                                                | 返回星期，MONDAY,TUESDAY,…SUNDAY                    |
| MONTHNAME(date)                                              | 返回月份，January,…                                 |
| DATEDIFF(date1,date2)/TIMEDIFF(time1,time2)                  | 返回date1-date2的日期间隔/返回time1-time2的时间间隔 |
| DATE_ADD(date,INTERVAL expr type)或ADDDATE/DATE_SUB/SUBDATE  | 返回与给定日期相差INTERVAL时间段的日期              |
| ADDTIME(time,expr)/SUBTIME(time,expr)                        | 返回给定时间加上/减去expr的时间值                   |
| DATE_FORMAT(datetime,fmt)/  TIME_FORMAT(time,fmt)            | 按照字符串fmt格式化日期datetime值/时间time值        |
| STR_TO_DATE(str,fmt)                                         | 按照字符串fmt对str进行解析，解析为一个日期          |
| GET_FORMAT(val_type,format_type)                             | 返回日期时间字符串的显示格式                        |

函数中日期时间类型说明

| 参数类型 | 描述 | 参数类型      | 描述     |
| -------- | ---- | ------------- | -------- |
| YEAR     | 年   | YEAR_MONTH    | 年月     |
| MONTH    | 月   | DAY_HOUR      | 日时     |
| DAY      | 日   | DAY_MINUTE    | 日时分   |
| HOUR     | 时   | DAY_SECOND    | 日时分秒 |
| MINUTE   | 分   | HOUR_MINUTE   | 时分     |
| SECOND   | 秒   | HOUR_SECOND   | 时分秒   |
| WEEK     | 星期 | MINUTE_SECOND | 分秒     |
| QUARTER  | 一刻 |               |          |

函数中format参数说明

| 格式符 | 说明                                                      | 格式符 | 说明                                                    |
| ------ | --------------------------------------------------------- | ------ | ------------------------------------------------------- |
| %Y     | 4位数字表示年份                                           | %y     | 两位数字表示年份                                        |
| %M     | 月名表示月份（January,…）                                 | %m     | 两位数字表示月份（01,02,03，…）                         |
| %b     | 缩写的月名（Jan.,Feb.,…）                                 | %c     | 数字表示月份（1,2,3…）                                  |
| %D     | 英文后缀表示月中的天数（1st,2nd,3rd,…）                   | %d     | 两位数字表示表示月中的天数（01,02,…）                   |
| %e     | 数字形式表示月中的天数（1,2,3,…）                         | %p     | AM或PM                                                  |
| %H     | 两位数字表示小数，24小时制（01,02,03,…）                  | %h和%I | 两位数字表示小时，12小时制（01,02,03,…）                |
| %k     | 数字形式的小时，24小时制（1,2,3,…）                       | %l     | 数字表示小时，12小时制（1,2,3,…）                       |
| %i     | 两位数字表示分钟（00,01,02,…）                            | %S和%s | 两位数字表示秒（00,01,02,…）                            |
| %T     | 时间，24小时制（hh:mm:ss）                                | %r     | 时间，12小时制（hh:mm:ss）后加AM或PM                    |
| %W     | 一周中的星期名称（Sunday,…）                              | %a     | 一周中的星期缩写（Sun.,Mon.,Tues.,…）                   |
| %w     | 以数字表示周中的天数（0=Sunday,1=Monday,…）               | %j     | 以3位数字表示年中的天数（001,002,…）                    |
| %U     | 以数字表示的的第几周（1,2,3,…）  其中Sunday为周中的第一天 | %u     | 以数字表示年中的年份（1,2,3,…）  其中Monday为周中第一天 |
| %V     | 一年中第几周（01~53），周日为每周的第一天，和%X同时使用   | %X     | 4位数形式表示该周的年份，周日为每周第一天，和%V同时使用 |
| %v     | 一年中第几周（01~53），周一为每周的第一天，和%x同时使用   | %x     | 4位数形式表示该周的年份，周一为每周第一天，和%v同时使用 |
| %%     | 表示%                                                     |        |                                                         |

GET_FORMAT函数中val_type 和format_type参数说明

| 值类型   | 格式化类型 | 显示格式字符串    |
| -------- | ---------- | ----------------- |
| DATE     | EUR        | %d.%m.%Y          |
| DATE     | INTERVAL   | %Y%m%d            |
| DATE     | ISO        | %Y-%m-%d          |
| DATE     | JIS        | %Y-%m-%d          |
| DATE     | USA        | %m.%d.%Y          |
| TIME     | EUR        | %H.%i.%s          |
| TIME     | INTERVAL   | %H%i%s            |
| TIME     | ISO        | %H:%i:%s          |
| TIME     | JIS        | %H:%i:%s          |
| TIME     | USA        | %h:%i:%s %p       |
| DATETIME | EUR        | %Y-%m-%d %H.%i.%s |
| DATETIME | INTERVAL   | %Y%m%d %H%i%s     |
| DATETIME | ISO        | %Y-%m-%d %H:%i:%s |
| DATETIME | JIS        | %Y-%m-%d %H:%i:%s |
| DATETIME | USA        | %Y-%m-%d %H.%i.%s |

```mysql
#日期时间函数
/*
获取系统日期时间值
获取某个日期或时间中的具体的年、月等值
获取星期、月份值，可以是当天的星期、当月的月份
获取一年中的第几个星期，一年的第几天
计算两个日期时间的间隔
获取一个日期或时间间隔一定时间后的另个日期或时间
和字符串之间的转换
*/
#（1）获取系统日期。CURDATE（）和CURRENT_DATE（）函数都可以获取当前系统日期。将日期值“+0”会怎么样？
SELECT CURDATE(),CURRENT_DATE();

#（2）获取系统时间。CURTIME（）和CURRENT_TIME（）函数都可以获取当前系统时间。将时间值“+0”会怎么样？
SELECT CURTIME(),CURRENT_TIME();

#（3）获取系统日期时间值。CURRENT_TIMESTAMP（）、LOCALTIME（）、SYSDATE（）和NOW（）
SELECT CURRENT_TIMESTAMP(),LOCALTIME(),SYSDATE(),NOW();

#（4）获取当前UTC（世界标准时间）日期或时间值。
#本地时间是根据地球上不同时区所处的位置调整 UTC 得来的，
#例如，北京时间比UTC时间晚8个小时。
#UTC_DATE(),CURDATE(),UTC_TIME(), CURTIME()
SELECT UTC_DATE(),CURDATE(),UTC_TIME(), CURTIME();


#（5）获取UNIX时间戳。
SELECT UNIX_TIMESTAMP(),UNIX_TIMESTAMP('2022-1-1');

#（6）获取具体的时间值，比如年、月、日、时、分、秒。
#分别是YEAR（date）、MONTH（date）、DAY（date）、HOUR（time）、MINUTE（time）、SECOND（time）。
SELECT YEAR(CURDATE()),MONTH(CURDATE()),DAY(CURDATE());
SELECT HOUR(CURTIME()),MINUTE(CURTIME()),SECOND(CURTIME());


#（7）获取日期时间的指定值。EXTRACT（type FROM date/time）函数
SELECT EXTRACT(YEAR_MONTH FROM CURDATE());

#（8）获取两个日期或时间之间的间隔。
#DATEDIFF（date1，date2）函数表示返回两个日期之间间隔的天数。
#TIMEDIFF（time1，time2）函数表示返回两个时间之间间隔的时分秒。

#查询今天距离员工入职的日期间隔天数
SELECT ename,DATEDIFF(CURDATE(),hiredate) FROM t_employee;

#查询现在距离中午放学还有多少时间
SELECT TIMEDIFF(CURTIME(),'12:0:0');

#（9）在“t_employee”表中查询本月生日的员工姓名、生日。
SELECT ename,birthday
FROM t_employee
WHERE MONTH(CURDATE()) = MONTH(birthday);


#(10)#查询入职时间超过5年的
SELECT ename,hiredate,DATEDIFF(CURDATE(),hiredate) 
FROM t_employee
WHERE DATEDIFF(CURDATE(),hiredate)  > 365*5;
```

### 7.1.4 流程函数

| 函数                                                         | 功能                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| IF(value,t,f)                                                | 如果value是真，返回t,否则返回f                               |
| IFNULL(value1,value2)                                        | 如果value1不为空，返回value1,否则返回value2                  |
| CASE WHEN 条件1 THEN result1 WHEN 条件2 THEN result2 .... [ELSE resultn] END | 依次判断条件，哪个条件满足了，就返回对应的result,所有条件都不满足就返回ELSE的result。如果没有单独的ELSE子句，当所有WHEN后面的条件都不满足时则返回NULL值结果。**相当于Java的if...else if...else...** |
| CASE  expr WHEN 常量值1 THEN 值1 WHEN 常量值1 THEN 值1 .... [ELSE 值n] END | 判断表达式expr与哪个常量值匹配，找到匹配的就返回对应值，都不匹配就返回ELSE的值。如果没有单独的ELSE子句，当所有WHEN后面的常量值都不匹配时则返回NULL值结果。**相当于Java的switch...case...** |

```mysql
#条件判断函数
/*
这个函数不是筛选记录的函数，
而是根据条件不同显示不同的结果的函数。
*/
#如果薪资大于20000，显示高薪，否则显示正常
SELECT ename,salary,IF(salary>20000,'高薪','正常')
FROM t_employee;

#计算实发工资
#实发工资 = 薪资 + 薪资 * 奖金比例
SELECT ename,salary,commission_pct,
salary + salary * commission_pct
FROM t_employee;
#如果commission_pct是，计算完结果是NULL

SELECT ename,salary,commission_pct,
salary + salary * IFNULL(commission_pct,0) AS 实发工资
FROM t_employee;


SELECT ename,salary,commission_pct,
ROUND(salary + salary * IFNULL(commission_pct,0),2) AS 实发工资
FROM t_employee;

#查询员工编号，姓名，薪资，等级，等级根据薪资判断，
#如果薪资大于20000，显示“羡慕级别”，
#如果薪资15000-20000，显示“努力级别”，
#如果薪资10000-15000，显示“平均级别”
#如果薪资10000以下，显示“保底级别”
/*mysql中没有if...elseif函数，有case 函数。
等价于if...elseif 
*/
SELECT eid,ename,salary,
CASE WHEN salary>20000 THEN '羡慕级别'
     WHEN salary>15000 THEN '努力级别'
     WHEN salary>10000 THEN '平均级别'
     ELSE '保底级别'
END AS "等级"
FROM t_employee;  

#在“t_employee”表中查询入职7年以上的
#员工姓名、工作地点、轮岗的工作地点数量情况。
/*
计算工作地点的数量，转换为求 work_place中逗号的数量+1。
 work_place中逗号的数量 = work_place的总字符数 -  work_place去掉,的字符数
 work_place去掉, ，使用replace函数
*/
SELECT work_place, 
CHAR_LENGTH(work_place)-CHAR_LENGTH(REPLACE(work_place,',',''))
FROM t_employee;
 
 #类似于Java中switch...case
SELECT ename,work_place,
CASE (CHAR_LENGTH(work_place)-CHAR_LENGTH(REPLACE(work_place,',',''))+1)
WHEN 1 THEN '只在一个地方工作'
WHEN 2 THEN '在两个地方来回奔波'
WHEN 3 THEN '在三个地方流动'
ELSE '频繁出差'
END AS "工作地点数量情况"
FROM t_employee
WHERE DATEDIFF(CURDATE(),hiredate)  > 365*7;
```

### 7.2.5 其他函数

| 函数          | 用法                                                |
| ------------- | --------------------------------------------------- |
| database()    | 返回当前数据库名                                    |
| version()     | 返回当前数据库版本                                  |
| user()        | 返回当前登录用户名                                  |
| password(str) | 返回字符串str的加密版本，41位长的字符串。mysql8弃用 |
| md5(str)      | 返回字符串str的md5值，也是一种加密方式              |

### 7.2.6 窗口函数

窗口函数也叫OLAP函数（Online Anallytical Processing，联机分析处理），可以对数据进行实时分析处理。窗口函数是每条记录都会分析，有几条记录执行完还是几条，因此也属于单行函数。MySQL8版本开始支持窗口函数。

| 函数分类 | 函数                 | 功能描述                                                     |
| -------- | -------------------- | ------------------------------------------------------------ |
| 序号函数 | ROW_NUMBER()         | 顺序排序，每行按照不同的分组逐行编号，例如：1,2,3,4          |
|          | RANK()               | 并列排序，每行按照不同的分组进行编号，同一个分组中排序字段值出现重复值时，并列排序并跳过重复序号，例如：1,1,3 |
|          | DENSE_RANK()         | 并列排序，每行按照不同的分组进行编号，同一个分组中排序字段值出现重复值时，并列排序不跳过重复序号，例如：1,1,2 |
| 分布函数 | PERCENT_RANK()       | 排名百分比，每行按照公式（rank-1）/ （rows-1）进行计算。其中，rank为RANK()函数产生的序号，rows为当前窗口的记录总行数 |
|          | CUME_DIST()          | 累积分布值，表示每行按照当前分组内小于等于当前rank值的行数 / 分组内总行数 |
| 前后函数 | LAG（expr，n）       | 返回位于当前行的前n行的expr值                                |
|          | LEAD（expr，n）      | 返回位于当前行的后n行的expr值                                |
| 首尾函数 | FIRST_VALUE（expr）  | 返回当前分组第一行的expr值                                   |
|          | LAST_VALUE（expr）   | 返回当前分组每一个rank最后一行的expr值                       |
| 其他函数 | NTH_VALUE（expr，n） | 返回当前分组第n行的expr值                                    |
|          | NTILE（n）           | 用于将分区中的有序数据分为n个等级，记录等级数                |

窗口函数的语法格式如下

```mysql
函数名([参数列表]) OVER ()
函数名([参数列表]) OVER (子句)
```

over关键字用来指定窗口函数的窗口范围。如果OVER后面是空（），则表示SELECT语句筛选的所有行是一个窗口。OVER后面的（）中支持以下4种语法来设置窗口范围。

- WINDOW：给窗口指定一个别名；
- PARTITION BY子句：一个窗口范围还可以分为多个区域。按照哪些字段进行分区/分组，窗口函数在不同的分组上分别处理分析；
- ORDER BY子句：按照哪些字段进行排序，窗口函数将按照排序后结果进行分析处理；
- FRAME子句：FRAME是当前分区的一个子集，FRAME子句用来定义子集的规则。

```mysql
#（1）在“t_employee”表中查询薪资在[8000,10000]之间的员工姓名和薪资并给每一行记录编序号
SELECT ROW_NUMBER() OVER () AS "row_num",ename,salary
FROM t_employee WHERE salary BETWEEN 8000 AND 10000;

#（2）计算每一个部门的平均薪资与全公司的平均薪资的差值。
SELECT  did,AVG(salary) OVER() AS avg_all,
AVG(salary) OVER(PARTITION BY did) AS avg_did,
ROUND(AVG(salary) OVER()-AVG(salary) OVER(PARTITION BY did),2) AS deviation
FROM  t_employee;


#（3）在“t_employee”表中查询女员工姓名，部门编号，薪资，查询结果按照部门编号分组后在按薪资升序排列，并分别使用ROW_NUMBER（）、RANK（）、DENSE_RANK（）三个序号函数给每一行记录编序号。
SELECT ename,did,salary,gender,
ROW_NUMBER() OVER (PARTITION BY did ORDER BY salary) AS "row_num",
RANK() OVER (PARTITION BY did ORDER BY salary) AS "rank_num" ,
DENSE_RANK() OVER (PARTITION BY did ORDER BY salary) AS "ds_rank_num" 
FROM t_employee WHERE gender='女';

#或

SELECT ename,did,salary,
ROW_NUMBER() OVER w AS "row_num",
RANK() OVER w AS "rank_num" ,
DENSE_RANK() OVER w AS "ds_rank_num" 
FROM t_employee WHERE gender='女'
WINDOW w AS (PARTITION BY did ORDER BY salary);


#（4）在“t_employee”表中查询每个部门最低3个薪资值的女员工姓名，部门编号，薪资值。
SELECT ROW_NUMBER() OVER () AS "rn",temp.*
FROM(SELECT ename,did,salary,
ROW_NUMBER() OVER w AS "row_num",
RANK() OVER w AS "rank_num" ,
DENSE_RANK() OVER w AS "ds_rank_num" 
FROM t_employee WHERE gender='女'
WINDOW w AS (PARTITION BY did ORDER BY salary))temp 
WHERE temp.rank_num<=3;

#或
SELECT ROW_NUMBER() OVER () AS "rn",temp.*
FROM(SELECT ename,did,salary,
ROW_NUMBER() OVER w AS "row_num",
RANK() OVER w AS "rank_num" ,
DENSE_RANK() OVER w AS "ds_rank_num" 
FROM t_employee WHERE gender='女'
WINDOW w AS (PARTITION BY did ORDER BY salary))temp 
WHERE temp.ds_rank_num<=3;


#（5）在“t_employee”表中查询每个部门薪资排名前3的员工姓名，部门编号，薪资值。
SELECT temp.*
FROM(SELECT ename,did,salary,
DENSE_RANK() OVER w AS "ds_rank_num" 
FROM t_employee
WINDOW w AS (PARTITION BY did ORDER BY salary DESC))temp 
WHERE temp.ds_rank_num<=3;

#（6）在“t_employee”表中查询全公司薪资排名前3的员工姓名，部门编号，薪资值。
SELECT temp.*
FROM(SELECT ename,did,salary,
DENSE_RANK() OVER w AS "ds_rank_num" 
FROM t_employee
WINDOW w AS (ORDER BY salary DESC))temp 
WHERE temp.ds_rank_num<=3;
```





# 8 子查询（拓展）

比较浪费效率，一般不这么用

子查询是另一个语句中的一个SELECT语句。

SELECT查询语句可以嵌套在另一个SELECT中，UPDATE，DELETE，INSERT，CREATE语句等。

以下是一个子查询的例子：

```mysql
SELECT * FROM t1 WHERE column1 = (SELECT column1 FROM t2);
```

在本例中，`SELECT * FROM t1...`是外部查询（或外部语句）,`（SELECT  column1 FROM  t2）`是子查询。我们可以说子查询嵌套在外部查询中。实际上，子查询也可以嵌套在其它子查询中，嵌套程度可以很深。子查询必须要位于小括号中。

子查询的结果形式：

一个子查询会返回一个标量（单一值）、一个行、一个列或一个表（一行或多行及一列或多列）。这些子查询被称为标量、列、行和表子查询。

## 8.1 SELECT的SELECT中嵌套子查询★

```mysql
/*
子查询：嵌套在另一个SQL语句中的查询。
SELECT语句可以嵌套在另一个SELECT中，UPDATE，DELETE，INSERT，CREATE语句等。

(1)SELECT的SELECT中嵌套子查询
*/

#（1）在“t_employee”表中查询每个人薪资和公司平均薪资的差值，并显示员工薪资和公司平均薪资相差5000元以上的记录。
SELECT ename AS "姓名",
	salary AS "薪资",
 ROUND((SELECT AVG(salary) FROM t_employee),2) AS "全公司平均薪资",
 ROUND(salary-(SELECT AVG(salary) FROM t_employee),2) AS "差值"
FROM t_employee
WHERE ABS(ROUND(salary-(SELECT AVG(salary) FROM t_employee),2))>5000;

#（2）在“t_employee”表中查询每个部门平均薪资和公司平均薪资的差值。
SELECT did,AVG(salary),
AVG(salary)-(SELECT AVG(salary) FROM t_employee)
FROM t_employee
GROUP BY did;
```



## 8.2 SELECT的WHERE或HAVING中嵌套子查询★

当子查询结果作为外层另一个SQL的过滤条件，通常把子查询嵌入到WHERE或HAVING中。根据子查询结果的情况，分为如下三种情况。

- 当子查询的结果是单列单个值，那么可以直接使用比较运算符，如“<”、“<=”、“>”、“>=”、“=”、“!=”等与子查询结果进行比较。
- 当子查询的结果是单列多个值，那么可以使用比较运算符IN或NOT IN进行比较。
- 当子查询的结果是单列多个值，还可以使用比较运算符, 如“<”、“<=”、“>”、“>=”、“=”、“!=”等搭配ANY、SOME、ALL等关键字与查询结果进行比较。

```mysql
/*
子查询嵌套在where后面。
在where或having后面的子查询结果是：
（1）单个值，那么可以用=，>,<,>=,<=,!=这样的运算符和子查询的结果做比较
（2）多个值，那么需要用in,not in, >all,>any....形式做比较
 如“<”、“<=”、“>”、“>=”、“=”、“!=”等搭配ANY、SOME、ALL等关键字与查询结果进行比较

*/
#（1）在“t_employee”表中查询薪资最高的员工姓名（ename）和薪资（salary）。
SELECT ename,salary
FROM t_employee
WHERE salary = (SELECT MAX(salary) FROM t_employee);

#（2）在“t_employee”表中查询比全公司平均薪资高的男员工姓名和薪资。
SELECT ename,salary
FROM t_employee
WHERE salary > (SELECT AVG(salary) FROM t_employee) AND gender = '男';

#（3）在“t_employee”表中查询和“白露”，“谢吉娜”同一部门的员工姓名和电话。
SELECT ename,tel,did
FROM t_employee
WHERE did IN(SELECT did FROM t_employee WHERE ename='白露' || ename='谢吉娜');

SELECT ename,tel,did
FROM t_employee
WHERE did =ANY(SELECT did FROM t_employee WHERE ename='白露' || ename='谢吉娜');


#（4）在“t_employee”表中查询薪资比“白露”，“李诗雨”，“黄冰茹”三个人的薪资都要高的员工姓名和薪资。
SELECT ename,salary
FROM t_employee
WHERE salary >ALL(SELECT salary FROM t_employee WHERE ename IN('白露','李诗雨','黄冰茹'));


#（5）查询“t_employee”和“t_department”表，按部门统计平均工资，
#显示部门平均工资比全公司的总平均工资高的部门编号、部门名称、部门平均薪资，并按照部门平均薪资升序排列。
SELECT t_department.did,dname,AVG(salary)
FROM t_employee 
RIGHT JOIN t_department
ON t_employee.did = t_department.did
GROUP BY t_department.did
HAVING AVG(salary) >(SELECT AVG(salary) FROM t_employee)
ORDER BY AVG(salary);

```

## 8.3 SELECT中的EXISTS型子查询

EXISTS型子查询也是存在外层SELECT的WHERE子句中，不过它和上面的WHERE型子查询的工作模式不相同，所以这里单独讨论它。

如果EXISTS关键字后面的参数是一个任意的子查询，系统将对子查询进行运算以判断它是否返回行，如果至少返回一行，那么EXISTS的结果为true，此时外层查询语句将进行查询；如果子查询没有返回任何行，那么EXISTS的结果为false，此时外层查询语句不进行查询。EXISTS和NOT EXISTS的结果只取决于是否返回行，而不取决于这些行的内容，所以这个子查询输入列表通常是无关紧要的。

如果EXISTS关键字后面的参数是一个关联子查询，即子查询的WHERE条件中包含与外层查询表的关联条件，那么此时将对外层查询表做循环，即在筛选外层查询表的每一条记录时，都看这条记录是否满足子查询的条件，如果满足就再用外层查询的其他WHERE条件对该记录进行筛选，否则就丢弃这行记录。

```mysql
#exist型子查询
/*
（1）exists()中的子查询和外面的查询没有联合的情况下，
如果exists()中的子查询没有返回任何行，那么外面的子查询就不查了。
（2）exists()中的子查询与外面的查询有联合工作的情况下，
循环进行把外面查询表的每一行记录的值，代入()中子查询，如果可以查到结果，
就留下外面查询的这条记录，否则就舍去。
*/

#（1）查询“t_employee”表中是否存在部门编号为NULL的员工，
#如果存在，查询“t_department”表的部门编号、部门名称。
SELECT * FROM t_department 
WHERE EXISTS(SELECT * FROM t_employee  WHERE did IS NULL);

#（2）查询“t_department”表是否存在与“t_employee”表相同部门编号的记录，如果存在，查询这些部门的编号和名称。
SELECT * FROM t_department
WHERE EXISTS(SELECT * FROM t_employee WHERE t_employee.did = t_department.did);

#查询结果等价于下面的sql
SELECT DISTINCT t_department.*
FROM t_department INNER JOIN t_employee
ON t_department.did = t_employee.did;
```

## 8.4 SELECT的FROM中嵌套子查询

当子查询结果是多列的结果时，通常将子查询放到FROM后面，然后采用给子查询结果取别名的方式，把子查询结果当成一张“动态生成的临时表”使用。

```mysql
#子查询嵌套在from后面
/*
当一个查询要基于另一个查询结果来筛选的时候，
另一个查询还是多行多列的结果，那么就可以把这个查询结果当成一张临时表，
放在from后面进行再次筛选。

*/

#（1）在“t_employee”表中，查询每个部门的平均薪资，
#然后与“t_department”表联合查询
#所有部门的部门编号、部门名称、部门平均薪资。

SELECT did,AVG(salary) FROM t_employee GROUP BY did;

+------+-------------+
| did  | AVG(salary) |
+------+-------------+
|    1 |  11479.3125 |
|    2 |       13978 |
|    3 |    37858.25 |
|    4 |       12332 |
|    5 |       11725 |
+------+-------------+
5 ROWS IN SET (0.00 sec)

#用上面的查询结果，当成一张临时表，与t_department部门表做联合查询
#要给这样的子查询取别名的方式来当临时表用，不取别名是不可以的。
#而且此时的别名不能加""
#字段的别名可以加""，表的别名不能加""

SELECT t_department.did ,dname,AVG(salary)
FROM t_department LEFT JOIN (SELECT did,AVG(salary) FROM t_employee GROUP BY did) temp
ON t_department.did = temp.did;
#错误，from后面的t_department和temp表都没有salary字段，
#SELECT t_department.did ,dname,AVG(salary)出现AVG(salary)是错误的

SELECT t_department.did ,dname,pingjun
FROM t_department LEFT JOIN (SELECT did,AVG(salary) AS pingjun FROM t_employee GROUP BY did) temp
ON t_department.did = temp.did;


#（2）在“t_employee”表中查询每个部门中薪资排名前2的员工姓名、部门编号和薪资。
SELECT * FROM (
SELECT ename,did,salary,
DENSE_RANK() over (PARTITION BY did ORDER BY salary DESC) AS paiming
FROM t_employee) temp
WHERE temp.paiming <=2;
```

## 8.4 UPDATE中嵌套子查询

```mysql
#子查询也可以嵌套在update语句中
#（1）修改“t_employee”表中部门编号（did）和
#“测试部”部门编号（did）相同的员工薪资为原来薪资的1.5倍。
UPDATE t_employee
SET salary = salary * 1.5
WHERE did = (SELECT did FROM t_department WHERE dname = '测试部');

#（2）修改“t_employee”表中did为NULL的员工信息，
#将他们的did值修改为“测试部”的部门编号。
#子查询select did from t_department where dname = '测试部'
#这种子查询必须是单个值，否则无法赋值

UPDATE t_employee 
SET did = (SELECT did FROM t_department WHERE dname = '测试部')
WHERE did IS NULL;

#（3）修改“t_employee”表中“李冰冰”的薪资值等于“孙红梅”的薪资值。
#这里使用子查询先在“t_employee”表中查询出“孙红梅”的薪资。
#select salary from t_employee where ename = '孙红梅';

UPDATE t_employee
SET salary = (SELECT salary FROM t_employee WHERE ename = '孙红梅')
WHERE ename = '李冰冰';
#You can't specify target table 't_employee' for update in FROM clause'

UPDATE t_employee
SET salary = (SELECT salary FROM(SELECT salary FROM t_employee WHERE ename = '孙红梅')temp)
WHERE ename = '李冰冰';
#当update的表和子查询的表是同一个表时，需要将子查询的结果用临时表的方式表示
#即再套一层子查询，使得update和最外层的子查询不是同一张表

#（4）修改“t_employee”表“李冰冰”的薪资与她所在部门的平均薪资一样。
#子查询，查询李冰冰的部门编号 
#select did from t_employee where ename = '李冰冰';

#子查询第二层，查询李冰冰所在部门的平均薪资
#select avg(salary) from t_employee where did = (select did from t_employee where ename = '李冰冰');

#子查询第三层，把第二层的子查询结果当成临时表再查一下结果
#目的使得和外层的update不是同一张表
SELECT pingjun FROM (SELECT AVG(salary) pingjun FROM t_employee WHERE did = (SELECT did FROM t_employee WHERE ename = '李冰冰') temp)

#update更新
UPDATE t_employee
SET salary = 
(SELECT pingjun FROM 
	(SELECT AVG(salary) pingjun FROM t_employee WHERE did = 
		(SELECT did FROM t_employee WHERE ename = '李冰冰') ) temp)
WHERE ename = '李冰冰';
```

## 8.5 DELETE中嵌套子查询

```mysql
#delete语句中也可以嵌套子查询
#（1）从“t_employee”表中删除“测试部”的员工记录。
DELETE FROM t_employee 
WHERE did = (SELECT did FROM t_department WHERE dname = '测试部');


#（2）从“t_employee”表中删除和“李冰冰”同一个部门的员工记录。
#子查询 “李冰冰”的部门编号
#select did from t_employee where ename = '李冰冰';

DELETE FROM t_employee WHERE did = (SELECT did FROM t_employee WHERE ename = '李冰冰');
#You can't specify target table 't_employee' for update in FROM clause'
#删除和子查询是同一张表

DELETE FROM t_employee WHERE did = (SELECT did FROM (SELECT did FROM t_employee WHERE ename = '李冰冰')temp);
```

## 8.6 使用子查询复制表结构和数据

```mysql
#演示通过子查询复制表，
#（1）复制表结构
#（2）复制一条或多条记录
#（3）同时复制表结构和记录
#仅仅是复制表结构，可以用create语句
CREATE TABLE department LIKE t_department;

#使用INSERT语句+子查询，复制数据，此时INSERT不用写values
INSERT INTO department (SELECT * FROM t_department WHERE did<=3);

#同时复制表结构+数据
CREATE TABLE d_department AS (SELECT * FROM t_department);
#如果select后面是部分字段，复制的新表就只有这一部分字段
```





# 7  事务

## 7.1 事务概述

### 7.1.1 事务概念

​	**数据库事务(Database Transaction) ，是指作为单个逻辑工作单元执行的一系列操作，要么完全地执行，要么完全地不执行。** 事务处理可以确保除非事务性单元内的所有操作都成功完成，否则不会永久更新面向数据的资源。通过将一组相关操作组合为一个要么全部成功要么全部失败的单元，可以简化错误恢复并使应用程序更加可靠。一个逻辑工作单元要成为事务，必须满足所谓的ACID（原子性、一致性、隔离性和持久性）属性。事务是数据库运行中的逻辑工作单位，由DBMS中的事务管理子系统负责事务的处理。

### 7.1.2 事务特性★★★

- **原子性Atomicity**：指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。 
- **一致性Consistency**：事务必须使数据库从一个一致性状态变换到另外一个一致性状态。例如：转账前和转账后的总金额不变。
- **隔离性Isolation**：事务的隔离性是多个用户并发访问数据库时，数据库为每一个用户开启的事务执行时，不能被其他事务的执行所干扰，多个并发执行的事务之间要相互隔离。（通过事务的隔离级别保证隔离性）
- **持久性Durability**：指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来即使数据库发生故障也不应该对其有任何影响。

ACID四个特性名称必须背下来⚠️

```mysql
/*
原子性：
例如：
张三给李四转账500
张三转账之前余额是1000
李四转账之前余额是1000
成功：张三账号变为500，李四变为1500，
失败：张三账号是1000，李四还是1000.


#一致性
例如：
张三给李四转账500
张三转账之前余额是1000
李四转账之前余额是1000

要么他俩的余额不变， 还是1000，总和就是2000
要么他俩的余额有变化，张三500，李四1500，总和仍然是2000
错误：
张三500，李四1000，总和是1500，结果不对
张三1000，李四1500，总和是2500，结果不对

#隔离性
例如：张三要给李四转500，
      王五要给李四转500，
      张三转账是否成功，和王五是否转账成功无关。
      
      
#持久性：
例如：张三要给李四转500，一旦成功提交，就转账成功，撤不回来了。
      */
```



## 7.2 MySQL中事务操作

MySQL两种引擎：InnoDB是支持事务的，MyISAM是不支持事务的（存取效率高）。

MySQL默认是自动提交事务，即每条SQL(DML)语句自动单独处在一个事务中，SQL执行前开启，执行完自动提交。

**开启事务：**

```mysql
start transaction; -- 开启一个事务
或
begin;
```

**提交事务：**

```mysql
commit;-- 事务提交后数据才会持久存储，并结束当前事务
```

**回滚事务：**

```mysql
rollback;-- 撤销开启之前事务后的所有DML语句数据操作，并结束当前事务
```

**关闭/开启自动提交事务：**接下来所有语句都必须手动提交，否则就不会永久生效

```mysql
set autocommit=false; -- 关闭自动提交事务，需要手动开启和提交事务才能对数据库中数据产生持久影响
set autocommit=true;-- 重新开启自动提交事务。 
```

注意： DDL语句不支持事务，事务只对DML语句有效，对于DDL语句无效。

```mysql
#说明：DDL不支持事务
#DDL：create,drop,alter等创建库、创建表、删除库、删除表、修改库、修改表结构等这些语句不支持事务。
#换句话只对insert,update,delete语句支持事务。
TRUNCATE 表名称; 清空整个表的数据，不支持事务。 把表drop掉，新建一张表。

START TRANSACTION;
TRUNCATE t_employee;
ROLLBACK; #回滚  无效
```

## 7.3 事务的隔离级别

### 7.3.1 事务并发问题★★★

以上介绍完事务的四大特性(简称ACID)，现在重点来说明下事务的隔离性，当多个线程都开启事务操作数据库中的数据时，数据库系统要能进行隔离操作，以保证各个线程获取数据的准确性，在介绍数据库提供的各种隔离级别之前，我们先看看如果不考虑事务的隔离性，会发生的几种问题：

- **脏读：**指一个事务读取了另一个事务未提交的数据。

  当一个事务正在多次修改某个数据，而在这个事务中这多次的修改都还未提交，这时一个并发的事务来访问该数据，就会造成两个事务得到的数据不一致。例如：用户A向用户B转账100元，对应SQL命令如下

  ```mysql
      update account set money=money+100 where name=’B’;  (此时A通知B)
      update account set money=money - 100 where name=’A’;
  ```

  当只执行第一条SQL时，A通知B查看账户，B发现确实钱已到账（此时即发生了脏读），而之后无论第二条SQL是否执行，只要该事务不提交，则所有操作都将回滚，那么当B以后再次查看账户时就会发现钱其实并没有转。

- **不可重复读：**在一个事务内读取表中的某一行数据，多次读取结果不同。一个事务读取到了另一个事务修改并提交后的数据。（update时发生）

  例如事务T1在读取某一数据，而事务T2立马修改了这个数据并且提交事务给数据库，事务T1再次读取该数据就得到了不同的结果，发送了不可重复读。

  不可重复读和脏读的区别是，脏读是某一事务读取了另一个事务未提交的脏数据，而不可重复读则是读取了前一事务提交的数据。

  在某些情况下，不可重复读并不是问题，比如我们多次查询某个数据当然以最后查询得到的结果为主。但在另一些情况下就有可能发生问题，例如对于同一个数据A和B依次查询就可能不同，A和B就可能打起来了……

- **虚读（幻读）：**是指在一个事务内读取到了别的事务插入的数据，导致前后读取不一致。 （insert时发生） 

  幻读是事务非独立执行时发生的一种现象。例如事务T1对一个表中所有的行的某个数据项做了从“1”修改为“2”的操作，这时事务T2又对这个表中插入了一行数据项，而这个数据项的数值还是为“1”并且提交给数据库。而操作事务T1的用户如果再查看刚刚修改的数据，会发现还有一行没有修改，其实这行是从事务T2中添加的，就好像产生幻觉一样，这就是发生了幻读。

  幻读和不可重复读都是读取了另一条已经提交的事务（这点就脏读不同），所不同的是不可重复读查询的都是同一个数据项，而幻读针对的是一批数据整体（比如数据的个数）。

### 7.3.2 事务隔离级别★★★

**数据库事务的隔离性**：数据库系统必须具有隔离并发运行各个事务的能力, 使它们不会相互影响, 避免各种并发问题。**一个事务与其他事务隔离的程度称为隔离级别。**数据库规定了多种事务隔离级别, 不同隔离级别对应不同的干扰程度, 隔离级别越高, 数据一致性就越好, 但并发性越弱，也就是说级别越高，数据越安全，性能越低。

MySQL数据库通过设置以下事务的隔离级别以避免以上问题发生：

① **Serializable (串行化)：**可避免脏读、不可重复读、幻读的发生。

② **Repeatable read (可重复读)：**可避免脏读、不可重复读的发生。（MySQL默认）

③ **Read committed (读已提交)：**可避免脏读的发生。（Oracle默认）

④ **Read uncommitted (读未提交)：**最低级别，任何情况都无法保证。

在MySQL数据库中，支持上面四种隔离级别，默认的为Repeatable read (可重复读)；而在Oracle数据库中，只支持Serializable (串行化)级别和Read committed (读已提交)这两种级别，其中默认的为Read committed级别。 

> Oracle 支持的 2 种事务隔离级别：**READ-COMMITED**, SERIALIZABLE。 Oracle 默认的事务隔离级别为: READ COMMITED 。

### 7.3.3 查看与设置事务隔离级别★

```mysql
#查看当前使用的隔离级别
SELECT @@transaction_isolation;
#设置隔离级别
SET transaction_isolation='repeatable-read';-- 当前Session有效
```

- 在MySQL数据库中查看当前事务的隔离级别：

  ```mysql
  SELECT @@transaction_isolation;#查看当前使用的隔离级别
  SELECT @@GLOBAL.transaction_isolation;# 查看全局的隔离级别
  SELECT @@SESSION.transaction_isolation;# 查看当前会话的隔离级别
  
  #mysql5.7.20之前方式，mysql8版本后已废除：
  SELECT @@TX_ISOLATION; 
  select @@session.tx_isolation;
  select @@global.tx_isolation;
  ```

  

- 在MySQL数据库中设置事务的隔离级别：

  方式一：

  | 语法                                                  | 作用域                                                       |
  | ----------------------------------------------------- | ------------------------------------------------------------ |
  | set global transaction isolation level 隔离级别名称;  | 全局有效                                                     |
  | set session transaction isolation level 隔离级别名称; | 当前会话有效，即当前数据库连接有效                           |
  | set transaction isolation level 隔离级别名称;         | 只对下一个单个事务有效。 <br />无法通过select语句查看设置的事务隔离级别 |

  ```mysql
  #示例：
  set session transaction isolation level Read committed; -- 对当前会话（连接）有效的事务隔离级别
  ```

  方式二：直接设置系统变量

  | 语法                                       | 作用域                                        |
  | ------------------------------------------ | --------------------------------------------- |
  | SET GLOBAL transaction_isolation = value   | Global                                        |
  | SET @@GLOBAL.transaction_isolation= value  | Global                                        |
  | SET SESSION transaction_isolation= value   | Session                                       |
  | SET @@SESSION.transaction_isolation= value | Session                                       |
  | SET transaction_isolation= value           | Session                                       |
  | SET @@transaction_isolation= value         | Next transaction only，无法通过select语句查看 |

  ```mysql
  #示例：
  SET transaction_isolation='repeatable-read'; -- 当前会话有效，注意：使用的是字符串，并且单词中间有横线-
  #5.7.20版本之前方式：
  SET tx_isolation='repeatable-read';
  ```

  **注意：设置隔离级别必须在开启事务之前。**

  > *"transaction_isolation was added in MySQL 5.7.20 as an alias for tx_isolation, which is now deprecated and is removed in MySQL 8.0. Applications should be adjusted to use transaction_isolation in preference to tx_isolation."*
  >
  > [设置事务隔离级别](https://dev.mysql.com/doc/refman/5.7/en/set-transaction.html)

# 8  用户管理

root是超级管理员用户，权限最大，很容易引发由于误操作所导致的数据不安全问题，怎么办？

如何验证登录用户的合法身份以及针对不同的登录用户进行合理的权限分配，即**用户管理**。

数据库管理员DBA一般使用root账户登录，拥有全部数据库的权限。

一般用户应该使用普通用户权限，应该单独指定数据库对象权限，单独指定操作权限等。

## 8.1 身份认证

**主机ip+用户名+密码**作为用户身份验证

主机IP地址可以是一个明确的IP（例如：192.168.1.25），可以是某个IP段（例如：192.168.1.%)，可以是任意IP地址（%)。

主机+用户名示例：

- root@localhost：只允许在本机使用root用户登录
- root@%：运行在任意机器上使用root用户登录

- root@192.168.11.56：只运行在192.168.11.56主机上使用root用户登录

- tom@%：运行在任意机器上使用tom用户登录


> 建立用户时，用于身份验证的IP地址，是连接你的服务的客户端的IP地址
>
> 例如：root@192.168.11.56，表示客户端在192.168.11.56的机器上可以访问你的mysql服务
>
> 而这个客户端，要连接你的服务时，-h后面的主机的IP地址是，服务器所在的机器的IP地址。
>
> 例如：mysql服务在192.168.11.11机器上，   客户端在192.168.11.56上，那么客户端可以用root@192.168.11.56或root@%，连接时填写mysql -h192.168.11.11 -u root -p密码

## 8.2 权限管理

### 1、用户依次有以下权限级别

1. 全局权限
2. 数据库权限
3. 表权限
4. 字段权限
4. 存储过程或函数子程序的权限


对用户的操作进行逐级权限验证，如果上一级有这个权限，下一级就不用验证了。

全局 > 数据库 > 表 > 字段

### 2、查看账户权限

```
show grants for '用户名'@'主机IP地址';
```

### 3、新建用户和删除用户

![1561009788685](imgs/1561009788685.png)

![1561009769163](imgs/1561009769163.png)

1. 创建用户的语法格式：

   CREATE USER 'username'@'hostname' [IDENTIFIED BY 'password'];

   ```mysql
   #示例：
   CREATE USER 'tom'@'localhost' IDENTIFIED BY '123456';
   ```

   > 如果主机为：（1）192.168.29.53，表示仅限于在该IP登录；（2）%，就表示可以从任意IP登录
   
2. 查看创建的用户信息

   ```mysql
   USE mysql;
   SELECT * FROM user;
   ```

   ![image-20201222233719613](imgs\image-20201222233719613.png)

3. 删除用户

   ```mysql
   drop user '用户名'@'主机IP地址';
   ```

   

### 4、授予和收回权限

![1561009821823](imgs/1561009821823.png)

1. 授权语法格式

   GRANT 权限列表 ON dbname.tablename TO 'username'@'hostname' [IDENTIFIED BY 'password'];

   对应语句：

   ```mysql
   GRANT 权限列表 ON *.* TO '用户名'@'主机IP地址';  #全局
   GRANT 权限列表 ON 数据库名.* TO '用户名'@'主机IP地址'; #某个库
   GRANT 权限列表 ON 数据库名.表格 TO '用户名'@'主机IP地址'; #某个库的某个表
   
   ```

   例如：

   ```mysql
   GRANT SELECT ON *.* TO 'tom'@'192.168.29.53';
   GRANT SELECT ON `test`.* TO 'tom'@'192.168.29.53';
   GRANT UPDATE ON `test`.`t_department` TO 'tom'@'192.168.29.53';
   GRANT SELECT (tid), INSERT (tid), UPDATE (tid), REFERENCES (tid) ON `1101db`.`course` TO 'tom'@'192.168.29.30';
   ```
   
2. 收回权限：

   ```mysql
   revoke 权限列表 ON *.* FROM '用户名'@'主机IP地址';
   revoke 权限列表 ON 数据库名.* FROM '用户名'@'主机IP地址';
   revoke 权限列表 ON 数据库名.表格 FROM '用户名'@'主机IP地址';
   revoke 权限列表 ON 表名.* FROM '用户名'@'主机IP地址';
   ```

### 5、修改用户密码

格式：（需要先登录MySQL，并且必须是有授权权限的用户登录）

```mysql
ALTER USER '用户名'@'主机' IDENTIFIED BY '新密码';
```

示例：

```mysql
ALTER USER 'root'@'localhost' IDENTIFIED BY '123456';
```





# 9 MySQL8的部分新特性

## 1、系统表全部为InnoDB表

从 MySQL 8.0 开始，mysql 系统表和数据字典表使用 InnoDB 存储引擎，存储在 MySQL 数据目录下的 mysql.ibd 表空间文件中。在 MySQL 5.7 之前，这些系统表使用 MyISAM 存储引擎，存储在 mysql 数据库文件目录下各自的表空间文件中。关于数据库存储引擎的详细内容，在MySQL高级课程讲解。

在MySQL5.7版本中查看系统表类型，结果如下：

```mysql
mysql> #MySQL5.7
mysql> #查看系统表类型
mysql> SELECT DISTINCT(ENGINE) FROM information_schema.tables;
+----------------------+
| ENGINE             		|
+----------------------+
| MEMORY             		|
| InnoDB             		|
| MyISAM             		|
| CSV                		|
| PERFORMANCE_SCHEMA 	|
| NULL               		|
+----------------------+
6 rows in set (0.04 sec)
```

在MySQL8.0版本中查看系统表类型，结果如下：

```mysql
mysql> #MySQL8.0
mysql> #查看系统表类型
mysql> SELECT DISTINCT(ENGINE) FROM information_schema.tables;
+--------------------+
| ENGINE                |
+--------------------+
| InnoDB                |
| NULL                  |
| PERFORMANCE_SCHEMA |
| CSV                   |
+--------------------+
4 rows in set (0.00 sec)
```

系统表全部换成事务型的InnoDB表，默认的MySQL实例将不包含任何MyISAM表，除非手动创建MyISAM表。

## 2、默认字符集改为utf8mb4

在8.0版本之前，MySQL默认的字符集为Latin1，而8.0版本默认字符集为utf8mb4。

Latin1是ISO-8859-1的别名，有些环境下写作Latin-1。ISO-8859-1编码是单字节编码，不支持中文等多字节字符，但向下兼容ASCII。

MySQL中utf8字符集，它是utf8mb3的别称，使用三个字节编码表示一个字符。自MySQL4.1版本被引入，能够支持绝大多数语言的字符，但依然有些字符不能正确编码，如emoji表情字符等，为此MySQL5.5引入了utf8mb4字符集。在MySQL5.7对utf8mb4进行了大幅优化，并丰富了校验字符集。mb4就是“most byte 4”的意思，专门用来兼容四字节的Unicode，utf8mb4编码是utf8编码的超集，兼容utf8，并且能存储4字节的表情字符。如果原来某些库和表的字符集是utf8，可以直接修改为utf8mb4，不需要做其他转换。但是从uft8mb4转回utf8就会有问题。

使用SHOW语句查看MySQL5.7版本数据库的默认编码。

```mysql
mysql> #查看MySQL5.7数据库的默认编码
mysql> SHOW VARIABLES LIKE 'character_set_database';
+------------------------+--------+
| Variable_name            | Value  |
+------------------------+--------+
| character_set_database | latin1 |
+------------------------+--------+
1 row in set, 1 warning (0.00 sec)
```

使用SHOW语句查看MySQL8.0版本数据库的默认编码。

```mysql
mysql> #查看MySQL8.0数据库的默认编码
mysql> SHOW VARIABLES LIKE 'character_set_database';
+------------------------+---------+
| Variable_name            | Value   |
+------------------------+---------+
| character_set_database | utf8mb4 |
+------------------------+---------+
1 row in set, 1 warning (0.00 sec)
```

字符集校对规则是在字符集内用于字符比较和排序的一套规则，比如有的规则区分大小写，有的则无视。校对规则特征：

- 两个不同的字符集不能有相同的校对规则。
- 每个字符集有一个默认校对规则。
- 校对规则存在命名约定，以其相关的字符集名开始，中间包括一个语言名，并且以_ci、_cs或_bin结尾。其中_ci表示大小写不敏感、_cs表示大小写敏感、bin表示直接比较字符的二进制编码，即区分大小写。

使用SHOW语句查看utf8mb4字符集的部分校对规则如下：

```mysql
mysql> SHOW COLLATION LIKE 'utf8mb4_0900%';
+-----------+---------+-----+---------+----------+---------+--------------+
| Collation  | Charset | Id  | Default | Compiled | Sortlen | Pad_attribute|
+-------------------+---------+-----+---------+----------+-------+--------+
|utf8mb4_0900_ai_ci	| utf8mb4 | 255 | Yes    | Yes     |   0 | NO PAD        	|
|utf8mb4_0900_as_ci	| utf8mb4 | 305 |         | Yes     |   0 | NO PAD        	|
|utf8mb4_0900_as_cs	| utf8mb4 | 278 |         | Yes     |   0 | NO PAD        	|
|utf8mb4_0900_bin	| utf8mb4 | 309 |         | Yes     |   1 | NO PAD        	|
+-------------------+---------+-----+---------+----------+---------+------+
4 rows in set (0.00 sec)
```

## 3、支持检查约束（见上面检查约束）

## 4、支持窗口函数（见上面窗口函数）

## 5、用户管理

在MySQL 8.x中，默认的身份认证插件是“caching_sha2_password”，替代了之前的“mysql_native_password”。可以通过系统变量default_authentication_plugin和mysql数据库中的user表来看到这个变化。

在MySQL8之前默认的身份插件是“mysql_native_password”，即MySQL用户的密码使用PASSWORD函数进行加密。在MySQL 8.x中，默认的身份认证插件是“caching_sha2_password”，替代了之前的“mysql_native_password”，PASSWORD函数被弃用了。

在MySQL版本5.6.6版本起，在mysql.user表中添加了“password_expired”字段，它允许设置密码是否失效。如果“password_lifetime”字段值不为NULL，那么从MySQL服务启动时间开始，经过“password_lifetime”字段值的时间间隔之后，密码就过期了，即“password_expired”字段就为“Y”。任何密码超期的账号想要连接服务器端进行数据库操作都必须更改密码。MySQL8.0版本允许数据库管理员手动设置账户密码过期时间。

从MySQL 8.x版本开始允许限制重复使用以前的密码。

在MySQL8之前，如果要给多个用户授予相同的角色，需要为每个用户单独授权。在MySQL8之后，可以为多个用户赋予统一的角色，然后给角色授权即可，角色可以看成是一些权限的集合，这样就无须为每个用户单独授权。如果角色的权限修改，将会使得该角色下的所有用户的权限都跟着修改，这就非常方便。

mysql的密码字段有变化：

- mysql5.7之前mysql系统库的user表，密码字段名是password
- mysql5.7版本mysql系统库的user表，密码字段名是authentication_string

- mysql8.0版本mysql系统库的user表，密码字段名是authentication_string，另外用户管理还有角色概念，mysql系统库中有default_roles表。

## 6、其他新特性（略）

通用表达式、计算列、DDL操作支持原子性、数据字典合并等等。

通用表达式简称为CTE（Common Table Expressions）。CTE是命名的临时结果集，作用范围是当前语句。CTE可以理解为一个可以复用的子查询，但是和子查询又有区别，一个CTE可以引用其他CTE，CTE还可以是自引用(递归CTE)，也可以在同一查询中多次引用，但子查询不可以。

```mysql
WITH [RECURSIVE]
 cte_name [(col_name [, col_name] ...)] AS (subquery)
 [, cte_name [(col_name [, col_name] ...)] AS (subquery)] ...
```

通用表达式以“WITH”开头，如果“WITH”后面加“RECURSIVE”就表示接下来在通用表达式中需要递归引用自己，否则就不递归引用。每一个通用表达式都需要有一个名字，它相当于是子查询结果集的名字。

```mysql
#（1）在“t_employee”表中查询每个人薪资和公司平均薪资的的差值。
WITH temp AS (SELECT ROUND(AVG(salary),2) AS pingjun FROM t_employee)
SELECT ename AS "员工姓名",
    salary AS "薪资",
    pingjun "公司平均薪资",
    ROUND(salary - pingjun,2) "差值"
FROM t_employee,temp
HAVING ABS(差值)>5000;

#（2）查询薪资低于9000的员工编号，员工姓名，员工薪资，领导编号，领导姓名，领导薪资
WITH 
emp AS (SELECT eid,ename,salary,`mid` FROM t_employee WHERE salary <9000),
mgr(meid,mename,msalary) AS (SELECT eid,ename,salary FROM t_employee)
  
SELECT eid AS "员工薪资",
	ename AS "员工姓名",
	salary AS "员工薪资",
	meid AS "领导编号",
	mename AS "领导姓名",
	msalary AS "领导薪资"
FROM emp INNER JOIN mgr ON emp.mid = mgr.meid;

#（3）查询eid为21的员工，和他所有领导，直到最高领导。
CREATE TABLE emp AS (SELECT eid,ename,salary,tel,`mid` FROM t_employee WHERE salary < 10000);
UPDATE emp SET MID=19 WHERE eid=21; 
UPDATE emp SET MID=17 WHERE eid=19; 
UPDATE emp SET MID=16 WHERE eid=17; 
UPDATE emp SET MID=15 WHERE eid=16;
UPDATE emp SET MID=4 WHERE eid=15; 
UPDATE emp SET MID=NULL WHERE eid=4;
SELECT * FROM emp;


WITH RECURSIVE cte
AS ( 
	SELECT eid,ename,`mid`
 	FROM emp
 	WHERE eid = 21

UNION ALL

 	SELECT emp.eid,emp.ename,emp.mid
 	FROM emp INNER JOIN cte
 	ON emp.eid = cte.mid
 	WHERE emp.eid IS NOT NULL
)
SELECT * FROM cte;
```

