# 1 elasticsearch 入门

## 1.1 简介

### 1.1.1概述

Elasticsearch (简称ES)是一个分布式、RESTful 风格的全文搜索引擎

官网: https://www.elastic.co/



### 1.1.2倒排索引

**倒排索引步骤:**

- 数据根据词条进行分词,同时记录文档索引位置
- 将词条相同的数据化进行合并
- 对词条进行排序

**搜索过程:**

先将搜索词语进行分词，分词后在倒排索引列表查询文档位置(docId)。根据docId查询文档数据。



## 1.1.3elasticsearch对比solr

当单纯对已有数据进行搜索时，Solr更快。48mm/19mm

当实时建立索引时，Solr会产生io阻塞，查询性能较差，Elasticsearch更具有明显的优势。48mm/220mm

**ElasticSearch vs Solr 总结**

- es基本是开箱即用，非常简单。Solr安装略微复杂。
- Solr 利用 Zookeeper 进行分布式管理，而 Elasticsearch 自身带有分布式协调管理功能。
- Solr 支持更多格式的数据，比如JSON、XML、CSV，而 Elasticsearch 仅支持json文件格式。
- Solr 是传统搜索应用的有力解决方案，但 Elasticsearch 更适用于新兴的实时搜索应用。⚠️

现在很多互联网应用都是要求实时搜索的，所以我们选择了elasticsearch。



## 1.2elasticsearch 安装

### 1.2.1 下载软件

下载网站：https://www.elastic.co/cn/downloads/past-releases#elasticsearch

MAC的7.8.0：https://www.elastic.co/cn/downloads/past-releases/elasticsearch-7-8-0

**选择7.8版本即可，其它elastic stack也可以在这里下载。**



### 1.2.2 windows环境安装

解压elasticsearch-7.8.0-windows-x86_64.zip，目录结构:

| 目录    | 说明           |
| ------- | -------------- |
| bin     | 可执行脚本目录 |
| config  | 配置目录       |
| jdk     | 内置jdk目录    |
| lib     | 类库           |
| logs    | 日志目录       |
| modules | 模块目录       |
| plugins | 插件目录       |

解压完成后进入bin目录，双击运行elasticsearch.bat

Mac启动命令： 双击运行bin/elasticsearch 

测试访问： http://localhost:9200/

```json
{
  "name": "shuaigouzideMacBook-Pro.local",
  "cluster_name": "elasticsearch",
  "cluster_uuid": "6FpeemHZT7GpCNONIQKW_w",
  "version": {
    "number": "7.8.0",
    "build_flavor": "default",
    "build_type": "tar",
    "build_hash": "757314695644ea9a1dc2fecd26d1a43856725e65",
    "build_date": "2020-06-14T19:35:50.234439Z",
    "build_snapshot": false,
    "lucene_version": "8.5.1",
    "minimum_wire_compatibility_version": "6.8.0",
    "minimum_index_compatibility_version": "6.0.0-beta1"
  },
  "tagline": "You Know, for Search"
}
```





### 1.2.3 linux环境安装

将下载好的elasticsearch-7.8.0-linux-x86_64.tar.gz上传至服务器并解压。先将jdk环境搭建好，jdk环境配置略。

将解压后的目录改个名，默认解压到了/opt目录下

```shell
mv	elasticsearch-7.8.0/ es-7.8.0
```

linux中elasticsearch不能直接通过root用户启动，所以要先创建一个普通用户。⚠️

```shell
#创建用户
useradd es;
#设置密码
passwd es;
```

**给普通用户授权**：文件的用户和组都改成es

```shell
chown -R es:es es-7.8.0/
```

**给用户设置sudo权限**:

vim /etc/sudoers，大概第91行

```shell
#在root    ALL=(ALL)       ALL下面新增
es      ALL=(ALL)       ALL
```

普通用户在启动elasticsearch时会出现文件大小受限制的错误。

解决办法:

**普通用户打开文件最大数限制修改**

```shell
#编辑limits.conf文件
vi /etc/security/limits.conf

#在大概61行文件最下方，添加以下内容
* soft nofile 65536 
* hard nofile 131072 
* soft nproc 2048 
* hard nproc 4096
```

**普通用户启动线程数限制**

```shell
# Centos6改这个⚠️
vi /etc/security/limits.d/90‐nproc.conf
# Centos7改这个⚠️
vi /etc/security/limits.d/20‐nproc.conf

#添加以下内容
* soft nproc 4096
```

**普通用户增大虚拟内存**

```shell
vi /etc/sysctl.conf
#添加以下内容:
vm.max_map_count=262144

#保存后执行sysctl -p让配置生效
sysctl -p
```

全部步骤完成后需要重新打开终端，重新登入。

切换普通用户(es)登入⚠️，进入到${解压目录}/config目录下，修改elasticsearch.yml配置文件:

```yaml
#节点名称，随便定义，推荐别乱改，大概23行
node.name: node-1
#运行访问的网络，0.0.0.0表示任意ip都匹配，这样可以远程访问，大概55行
network.host: 0.0.0.0
#集群初始master选举主节点，即使单台也会选一个主节点，必须和节点名称一致，大概72行
cluster.initial_master_nodes: ["node-1"]
```

启动elasticsearch

```shell
#进入到bin目录(注意，不能使用root账号启动)，-d代表后台启动
./elasticsearch -d
```

浏览器测试访问： http://localhost:9200/



### 1.2.4 docker安装

```shell
docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -d --name elasticsearch docker.elastic.co/elasticsearch/elasticsearch:7.8.0
```



### 1.2.5 kibana安装

elasticsearch服务是一个restful风格的http服务。我们可以采用postman作为客户端来进行操作，elastic stack官方也给我们提供了kibana来进行客户端操作，这个相比postman要友好一点，因为里面有些自动补全的代码提示。

下载地址: https://www.elastic.co/cn/downloads/past-releases/kibana-7-8-0

上传tar并解压文件:

进入到config目录，修改kibana.yml文件：

```yaml
#超时时间默认30秒，如果你启动的慢一些可以多设置点时间，非必须修改⚠️，74行
elasticsearch.requestTimeouts: 30000
#服务端口
server.port: 5601
#运行访问的IP设置，0.0.0.0可以远程访问
server.host: "0.0.0.0"
#设置语言
i18n.locale: "zh-CN"
```

进入bin目录，后台启动kibana:

```shell
nohup ./kibana &
```

开始访问：http://localhost:5601/app/kibana#/home

在控制台模块中可以查询数据



### 1.2.6 ik分词器安装

下载地址: https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.8.0/elasticsearch-analysis-ik-7.8.0.zip

解压时创建文件，配置时先关es

进入到**${es安装目录}/plugins**目录，**新建ik目录**⚠️

```shell
#在ik目录下解压elasticsearch-analysis-ik-7.8.0.zip文件
unzip elasticsearch-analysis-ik-7.8.0.zip
#删除zip文件

```

重启es

测试分词器:

```json
POST _analyze
{
  "analyzer": "ik_smart",
  "text": "我是中国人"
}
```

注意：存储和查询必须使用相同的分词器才可以查询到⚠️



## 1.3elasticsearch核心概念

### 1.3.1es对照数据库

| 关系型数据库       | ElasticSearch    |
| ------------------ | ---------------- |
| Database（数据库） | Index（索引库）  |
| Table（表）        | Type（类型）     |
| Row（行）          | Document（文档） |
| Column（列）       | Field（字段）    |



### 1.3.2索引(Index)

相当于MySql的数据库

一个索引就是一个拥有几分相似特征的文档的集合。⚠️比如说，你可以有一个客户数据的索引，另一个产品目录的索引，还有一个订单数据的索引。一个索引由一个名字来标识（必须全部是小写字母），并且当我们要对这个索引中的文档进行索引、搜索、更新和删除的时候，都要使用到这个名字⚠️。在一个集群中，可以定义任意多的索引。

能搜索的数据必须索引，这样的好处是可以提高查询速度，比如：新华字典前面的目录就是索引的意思，目录可以提高查询速度。

**Elasticsearch索引的精髓：一切设计都是为了提高搜索的性能。**



### 1.3.3 类型(Type)

相当于MySql的表

在一个索引中， 一个类型是你的索引的一个逻辑上的分类/分区，其语义完全由你来定。通常，会为具有一组共同字段的文档定义一个类型。不同的版本，类型发生了不同的变化

| 版本 | Type                                            |
| ---- | ----------------------------------------------- |
| 5.x  | 支持多种type                                    |
| 6.x  | 只能有一种type                                  |
| 7.x  | 默认不再支持自定义索引类型（默认类型为：_doc）⚠️ |



### 1.3.4 文档(Document)

相当于MySql的行

文档以Json格式存储⚠️，如：{id:xx, name:xxx}

一个文档是一个可被索引的基础信息单元，也就是一条数据

比如：你可以拥有某一个客户的文档，某一个产品的一个文档，当然，也可以拥有某个订单的一个文档。文档以JSON（Javascript Object Notation）格式来表示，而JSON是一个到处存在的互联网数据交互格式。

在一个index/type里面，你可以存储任意多的文档。



### 1.3.5 字段(Field)

相当于MySql的列

Json中的属性⚠️ ，如：id:xx

相当于是数据表的字段，对文档数据根据不同属性进行的分类标识。



### 1.3.6 映射(Mapping)

相当于MySql中的关系

mapping是处理数据的方式和规则方面做一些限制，如：某个字段的数据类型、默认值、分析器、是否被索引等等。这些都是映射里面可以设置的，其它就是处理ES里面数据的一些使用规则设置也叫做映射，按着最优规则处理数据对性能提高很大，因此才需要建立映射，并且需要思考如何建立映射才能对性能更好。





# 2 elasticsearch基本操作

参考文档: https://www.elastic.co/guide/en/elasticsearch/reference/7.8/index.html

## 2.1 分词器

官方提供的分词器有这么几种: Standard、Letter、Lowercase、Whitespace、UAX URL Email、Classic、Thai等，中文分词器可以使用第三方的比如IK分词器。前面我们已经安装过了。

IK分词器核心配置:

- main.dic：单词词典
- stopword.dic: 停用词，这里只记录了英文的一部分单词，比如: ﻿a、an、and、are、as、at、be、but、by等。

IK分词器:

```json
POST _analyze
{
  "analyzer": "ik_smart",
  "text": "我是中国人"
}

结果:
{
  "tokens" : [
    {
      "token" : "我",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "是",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    },
    {
      "token" : "中国人",
      "start_offset" : 2,
      "end_offset" : 5,
      "type" : "CN_WORD",
      "position" : 2
    }
  ]
}

POST _analyze
{
  "analyzer": "ik_max_word",
  "text": "我是中国人"
}
结果:
{
  "tokens" : [
    {
      "token" : "我",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "是",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    },
    {
      "token" : "中国人",
      "start_offset" : 2,
      "end_offset" : 5,
      "type" : "CN_WORD",
      "position" : 2
    },
    {
      "token" : "中国",
      "start_offset" : 2,
      "end_offset" : 4,
      "type" : "CN_WORD",
      "position" : 3
    },
    {
      "token" : "国人",
      "start_offset" : 3,
      "end_offset" : 5,
      "type" : "CN_WORD",
      "position" : 4
    }
  ]
}

```

Standard分词器:

```json
POST _analyze
{
  "analyzer": "standard",
  "text": "我是中国人"
}

结果:
{
  "tokens" : [
    {
      "token" : "我",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "<IDEOGRAPHIC>",
      "position" : 0
    },
    {
      "token" : "是",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "<IDEOGRAPHIC>",
      "position" : 1
    },
    {
      "token" : "中",
      "start_offset" : 2,
      "end_offset" : 3,
      "type" : "<IDEOGRAPHIC>",
      "position" : 2
    },
    {
      "token" : "国",
      "start_offset" : 3,
      "end_offset" : 4,
      "type" : "<IDEOGRAPHIC>",
      "position" : 3
    },
    {
      "token" : "人",
      "start_offset" : 4,
      "end_offset" : 5,
      "type" : "<IDEOGRAPHIC>",
      "position" : 4
    }
  ]
}

```



## 2.2 索引操作

官网文档： https://www.elastic.co/guide/en/elasticsearch/reference/7.17/indices-create-index.html

### 2.2.1 创建索引

语法: PUT /{索引名称}⚠️

```json
PUT /bookstore

结果:
{
  "acknowledged" : true,
  "shards_acknowledged" : true,
  "index" : "bookstore"
}
```



### 2.2.2 查看所有索引

语法: GET /_cat/indices?v

```json
GET /_cat/indices?v

结果:
含义：
健康	打开状态	索引名字	唯一标识	主分片的数量	副本数量	文档总数	主分片大小	副本大小			
health status index                          uuid                   pri rep docs.count docs.deleted store.size pri.store.size
green  open   .kibana-event-log-7.8.0-000001 kDxcMuKvRcilufkqDyL0gg   1   0          2            0     10.4kb         10.4kb
yellow open   bookstore                      oxjc8jxhThKP8Zq_nN8wpg   1   1          0            0       208b           208b
green  open   .apm-custom-link               PVQlgu4nT4iisHrHcOloXA   1   0          0            0       208b           208b
green  open   .kibana_task_manager_1         C8mler6jSs-l46gmfFc-LA   1   0          5            1     30.4kb         30.4kb
green  open   .apm-agent-configuration       C6yyW27NQd-Tn7vR54EGpw   1   0          0            0       208b           208b
green  open   .kibana_1                      Gl1i5ZA9Qk6gMHhLEFzEew   1   0         64            2    134.5kb        134.5kb
```



### 2.2.3 查看单个索引

语法: GET /{索引名称}

```json
GET /bookstore
结果:
{
  "bookstore" : {
    "aliases" : { },
    "mappings" : { },
    "settings" : {
      "index" : {
        "creation_date" : "1671940186838",
        "number_of_shards" : "1",
        "number_of_replicas" : "1",
        "uuid" : "oxjc8jxhThKP8Zq_nN8wpg",
        "version" : {
          "created" : "7080099"
        },
        "provided_name" : "bookstore"
      }
    }
  }
}
```



### 2.2.4 删除索引

语法: DELETE /{索引名称}

```json
DELETE /bookstore
结果:
{
  "acknowledged" : true
}
```



## 2.3 文档操作

### 2.3.1 创建文档

语法1:

POST /{索引名称}/{类型}

{

jsonbody

}

注意：该语法自动生成ID并返回，再查询都通过该ID来查询！⚠️

```shell
POST /bookstore/_doc
{
  "name": "三体",
  "price": 59
}

返回结果:
{
  "_index" : "bookstore",
  "_type" : "_doc",
  "_id" : "VhSJR4UByguxwagZl21F",⚠️
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 3,
  "_primary_term" : 1
}
```

语法2:

PUT /{索引名称}/{类型}/{id}

{

jsonbody

}

注意：使用PUT请求添加文档必须指定唯一标识ID，并且第一次执行是添加，第二次是执行是修改⚠️

```shell
PUT /bookstore/_doc/1
{
  "name": "小王子",
  "price": 98
}

返回结果:
{
  "_index" : "bookstore",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}
```



### 2.3.2 查看文档

语法:GET /{索引名称}/{类型}/{id}

```shell
查询三体
GET /bookstore/_doc/VhSJR4UByguxwagZl21F

查询小王子
GET /bookstore/_doc/1

结果1:
{
  "_index" : "bookstore",
  "_type" : "_doc",
  "_id" : "VhSJR4UByguxwagZl21F",
  "_version" : 1,
  "_seq_no" : 3,
  "_primary_term" : 1,
  "found" : true,
  "_source" : {
    "name" : "三体",
    "price" : 59
  }
}

结果2:
{
  "_index" : "bookstore",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 3,
  "_seq_no" : 2,
  "_primary_term" : 1,
  "found" : true,
  "_source" : {
    "name" : "小王子",
    "price" : 123
  }
}
```



### 2.3.3 修改文档

**注意：修改文档类似于重新创建，缺失的字段会自动丢弃。**⚠️

语法: 

PUT /{索引名称}/{类型}/{id}

{

jsonbody

}

```shell
PUT /bookstore/_doc/1
{
  "name": "小王子",
  "price": 66
}

结果：
{
  "_index" : "bookstore",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 2,
  "result" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 1,
  "_primary_term" : 1
}
```



### 2.3.4 修改局部属性

语法: 

POST /{索引名称}/_update/{docId}
{
  "doc": {
    "属性": "值"
  }
}

**注意：局部更新只能使用post请求。**⚠️

```shell
POST /bookstore/_update/1
{
  "doc": {
    "price": 123
  }
}

结果：
{
  "_index" : "bookstore",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 3,
  "result" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 2,
  "_primary_term" : 1
}
```



### 2.3.5 删除文档

语法: DELETE /{索引名称}/{类型}/{id}

```shell
DELETE /bookstore/_doc/1

结果:
{
  "_index" : "my_index",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 12,
  "result" : "deleted",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 11,
  "_primary_term" : 1
}
```



### 2.3.6 批量创建和删除

添加或删除的数据不能换行⚠️

语法：

```shell
PUT _bulk
{"actionName":{"_index":"indexName", "_type":"typeName"}} 
#添加才需要写属性⚠️
{"field1":"value1", "field2":"value2"} 

#actionName可以有CREATE、DELETE等。
```

批量创建

```shell
PUT _bulk
{"create":{"_index":"bookstore","_id":"1001"}}
{"id":1, "name":"童年", "price":30}
{"create":{"_index":"bookstore","_id":"1002"}}
{"id":2, "name":"大鱼", "price":26.9}

结果:
{
  "took" : 223,
  "errors" : false,
  "items" : [
    {
      "create" : {
        "_index" : "bookstore",
        "_type" : "_doc",
        "_id" : "1001",
        "_version" : 1,
        "result" : "created",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 4,
        "_primary_term" : 1,
        "status" : 201
      }
    },
    {
      "create" : {
        "_index" : "bookstore",
        "_type" : "_doc",
        "_id" : "1002",
        "_version" : 1,
        "result" : "created",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 5,
        "_primary_term" : 1,
        "status" : 201
      }
    }
  ]
}
```

批量删除

```shell
POST _bulk
{"delete":{"_index":"bookstore","_id":"1001"}}
{"delete":{"_index":"bookstore","_id":"1002"}}

结果:
{
  "took" : 58,
  "errors" : false,
  "items" : [
    {
      "delete" : {
        "_index" : "bookstore",
        "_type" : "_doc",
        "_id" : "1001",
        "_version" : 2,
        "result" : "deleted",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 6,
        "_primary_term" : 1,
        "status" : 200
      }
    },
    {
      "delete" : {
        "_index" : "bookstore",
        "_type" : "_doc",
        "_id" : "1002",
        "_version" : 2,
        "result" : "deleted",
        "_shards" : {
          "total" : 2,
          "successful" : 1,
          "failed" : 0
        },
        "_seq_no" : 7,
        "_primary_term" : 1,
        "status" : 200
      }
    }
  ]
}
```



## 2.4 映射mapping

有了索引库，等于有了数据库中的database。

接下来就需要建索引库(index)中的映射了，类似于数据库(database)中的表结构(table)。创建数据库表需要设置字段名称，类型，长度，约束等；索引库也一样，需要知道这个类型下有哪些字段，每个字段有哪些约束信息，这就叫做映射(mapping)。

### 2.4.1 查看映射

语法: GET /{索引名称}/_mapping

```json
GET /bookstore/_mapping

结果:
{
  "bookstore" : {
    "mappings" : {
      "properties" : {
        "id" : {
          "type" : "long"
        },
        "name" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "price" : {
          "type" : "long"
        }
      }
    }
  }
}
```



### 2.4.2 动态映射

在向索引库插入文档时自动生成的映射属于映射属于动态映射。⚠️

在关系数据库中，需要事先创建数据库，然后在该数据库下创建数据表，并创建 表字段、类型、长度、主键等，最后才能基于表插入数据。而Elasticsearch中不需要定义Mapping映射（即关系型数据库的表、字段等），在文档写入 Elasticsearch时，会根据文档字段自动识别类型，这种机制称之为<font color='red'>动态映射</font>。

映射规则对应:

| 数据        | 对应的类型 |
| ----------- | ---------- |
| null        | 字段不添加 |
| true\|flase | boolean    |
| 字符串      | text       |
| 数值        | long       |
| 小数        | float      |
| 日期        | date       |



### 2.4.3 静态映射

在创建索引库时手动配置的映射为静态映射。

静态映射是在Elasticsearch中也可以事先定义好映射，包含文档的各字段类型、分词器等，这种方式称之为<font color="red">静态映射</font>。

```shell
#删除原创建的索引
DELETE /my_index

#创建索引，并同时指定映射关系和分词器等。
PUT /my_index
{
	#指定映射
  "mappings": {
  	#都有哪些类型
    "properties": {
      "title": {
        "type": "text",
        "index": true,
        "store": true,
        #存储和查询必须同一个分词器
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_max_word"
      },
      "category": {
        #不分词字符串
        "type": "keyword",
        "index": true,
        "store": true
      },
      "images": {
        #不分词字符串
        "type": "keyword",
        "index": true,
        "store": true
      },
      "price": {
        "type": "integer",
        "index": true,
        "store": true
      }
    }
  }
}

结果:
{
  "acknowledged" : true,
  "shards_acknowledged" : true,
  "index" : "my_index"
}
```

**type分类如下:**

- 字符串：string，string类型包含 text 和 keyword。 

- text：该类型被用来索引长文本，在创建索引前会将这些文本进行分词⚠️，转化为词的组合，建立索引；允许es来检索这些词，text类型不能用来排序和聚合⚠️。 比如我是中国人，分为我、是、中国人

- keyword：该类型不能分词，可以被用来检索过滤、排序和聚合⚠️，keyword类型不可用text进行分词模糊检索。 比如商品总类：日用品不能分词⚠️

- 数值型：long、integer、short、byte、double、float 

- 日期型：date 

- 布尔型：boolean 





# 3 DSL高级查询

## 3.1 DSL概述

DSL（Domain Specific Language）翻译为领域专用语言，是Elasticsearch提供了基于JSON的DSL来定义查询。

**DSL概览**

elasticsearch dsl ⇨数据查询【无条件 + 带条件【单条件【模糊匹配、精确匹配】+ 多条件【bool[ must、should、filter、must_not ]】】】

采用批量操作准备数据:

```shell
POST _bulk
{"create":{"_index":"my_index","_id":1}}
{"id":1,"title":"华为笔记本电脑","category":"华为","images":"http://www.gulixueyuan.com/xm.jpg","price":5388}
{"create":{"_index":"my_index","_id":2}}
{"id":2,"title":"华为手机","category":"华为","images":"http://www.gulixueyuan.com/xm.jpg","price":5500}
{"create":{"_index":"my_index","_id":3}}
{"id":3,"title":"VIVO手机","category":"vivo","images":"http://www.gulixueyuan.com/xm.jpg","price":3600}
```



## 3.2 DSL查询

### 3.2.1 查询所有文档

match_all:

```shell
POST /my_index/_search
{
  "query": {
    "match_all": {}
  }
}

结果:
{
  "took" : 0,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "id" : 1,
          "title" : "华为笔记本电脑",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5388
        }
      },
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "2",
        "_score" : 1.0,
        "_source" : {
          "id" : 2,
          "title" : "华为手机",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5500
        }
      },
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "3",
        "_score" : 1.0,
        "_source" : {
          "id" : 3,
          "title" : "VIVO手机",
          "category" : "vivo",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 3600
        }
      }
    ]
  }
}
```



### 3.2.2 匹配查询(match)

match: 哪些文档包含它的分词，它就把哪些文档返回 ⚠️

```shell
POST /my_index/_search
{
  "query": {
    "match": {
      "title": "华为智能手机"
    }
  }
}

结果:
{
  "took" : 3,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 2,
      "relation" : "eq"
    },
    "max_score" : 0.5619608,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "2",
        "_score" : 0.5619608,
        "_source" : {
          "id" : 2,
          "title" : "华为手机",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5500
        }
      },
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 0.35411233,
        "_source" : {
          "id" : 1,
          "title" : "华为笔记本电脑",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5388
        }
      }
    ]
  }
}

```



### 3.2.3补充条件删除

注意：哪些文档包含它的分词，它就把哪些文档直接删除 ⚠️

```json
POST /my_index/_delete_by_query
{
  "query": {
    "match": {
      "title": "vivo"
    }
  }
}
结果:
{
  "took" : 51,
  "timed_out" : false,
  "total" : 1,
  "deleted" : 1,
  "batches" : 1,
  "version_conflicts" : 0,
  "noops" : 0,
  "retries" : {
    "bulk" : 0,
    "search" : 0
  },
  "throttled_millis" : 0,
  "requests_per_second" : -1.0,
  "throttled_until_millis" : 0,
  "failures" : [ ]
}
```



### 3.2.4 多字段匹配

哪些文档包含它的分词，它就把哪些文档返回 ⚠️

```shell
POST /my_index/_search
{
  "query": {
    "multi_match": {
      "query": "华为智能手机",
      "fields": ["title","category"]
    }
  }
}

结果:
{
  "took" : 3,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 2,
      "relation" : "eq"
    },
    "max_score" : 0.5619608,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "2",
        "_score" : 0.5619608,
        "_source" : {
          "id" : 2,
          "title" : "华为手机",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5500
        }
      },
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 0.35411233,
        "_source" : {
          "id" : 1,
          "title" : "华为笔记本电脑",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5388
        }
      }
    ]
  }
}

```



### 3.2.5 前缀匹配

以分完词后的前缀进行精确匹配

```shell
POST /my_index/_search
{
  "query": {
   "prefix": {
     "title": {
       "value": "vivo智能"
     }
   }
  }
}
结果:
{
  "took" : 0,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 0,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  }
}
```



### 3.2.6 关键字精确查询

**term:关键字不会进行分词，直接对创建时的分词进行精确查询。**

```shell
POST /my_index/_search
{
  "query": {
   "term": {
     "title": {
       "value": "华为手机"
     }
   }
  }
}

结果:
{
  "took" : 0,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 0,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  }
}
```



### 3.2.7 多关键字精确查询

对创建时的分词进行精确查询

```shell
POST /my_index/_search
{
  "query": {
   "terms": {
     "title": [
       "华为手机",
       "华为"
     ]
   }
  }
}

结果:
{
  "took" : 0,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 2,
      "relation" : "eq"
    },
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "id" : 1,
          "title" : "华为笔记本电脑",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5388
        }
      },
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "2",
        "_score" : 1.0,
        "_source" : {
          "id" : 2,
          "title" : "华为手机",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5500
        }
      }
    ]
  }
}
```



### 3.2.8 范围查询

范围查询使用range⚠️

- gte: 大于等于
- lte: 小于等于
- gt: 大于
- lt: 小于

```shell
POST /my_index/_search
{
  "query": {
    "range": {
      "price": {
        "gte": 3000,
        "lte": 5000
      }
    }
  }
}
结果:
{
  "took" : 0,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 1,
      "relation" : "eq"
    },
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "3",
        "_score" : 1.0,
        "_source" : {
          "title" : "VIVO手机",
          "category" : "vivo"
        }
      }
    ]
  }
}

```



### 3.2.9 指定返回字段

**query同级增加_source进行过滤。若只想看名称和类型两个字段的数据，在source中只定义这两个字段即可。**

```shell
POST /my_index/_search
{
  "query": {
   "terms": {
     "title": [
       "华为手机",
       "华为"
     ]
   }
  },
  "_source": ["title","category"]
}
```



### 3.2.10 组合查询

bool 各条件之间有and,or或not的关系

- must: 各个条件都必须满足，所有条件是and的关系
- should: 各个条件有一个满足即可，即各条件是or的关系
- must_not: 不满足所有条件，即各条件是not的关系
- filter: 与must效果等同，但是它不计算得分，效率更高点。

must 

```json
POST /my_index/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "title": "华为"
          }
        },
        {
          "range": {
            "price": {
              "gte": 3000,
              "lte": 5000
            }
          }
        }
      ]
    }
  }
}
结果:
{
  "took" : 0,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 0,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  }
}
```

should

```shell
POST /my_index/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "match": {
            "title": "华为"
          }
        },
        {
          "range": {
            "price": {
              "gte": 3000,
              "lte": 5000
            }
          }
        }
      ]
    }
  }
}

结果:
{
  "took" : 0,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "3",
        "_score" : 1.0,
        "_source" : {
          "id" : 3,
          "title" : "VIVO手机",
          "category" : "vivo",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 3600
        }
      },
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "2",
        "_score" : 0.5619608,
        "_source" : {
          "id" : 2,
          "title" : "华为手机",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5500
        }
      },
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 0.35411233,
        "_source" : {
          "id" : 1,
          "title" : "华为笔记本电脑",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5388
        }
      }
    ]
  }
}

```

如果should和must同时存在，他们之间是and关系：

```shell
POST /my_index/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "match": {
            "title": "华为"
          }
        },
        {
          "range": {
            "price": {
              "gte": 3000,
              "lte": 5000
            }
          }
        }
      ],
      "must": [
        {
          "match": {
            "title": "华为"
          }
        },
        {
          "range": {
            "price": {
              "gte": 3000,
              "lte": 5000
            }
          }
        }
      ]
    }
  }
}

结果:
{
  "took" : 1,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 0,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  }
}

```

must_not

```shell
POST /my_index/_search
{
  "query": {
    "bool": {
      "must_not": [
        {
          "match": {
            "title": "华为"
          }
        },
        {
          "range": {
            "price": {
              "gte": 3000,
              "lte": 5000
            }
          }
        }
      ]
    }
  }
}
结果:
{
  "took" : 0,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 0,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  }
}
```

filter

**_score的分值为0**

```shell
POST /my_index/_search
{
  "query": {
    "bool": {
      "filter": [
        {
          "match": {
            "title": "华为"
          }
        }
      ]
    }
  }
}

结果:
{
  "took" : 1,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 2,
      "relation" : "eq"
    },
    "max_score" : 0.0,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 0.0,
        "_source" : {
          "id" : 1,
          "title" : "华为笔记本电脑",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5388
        }
      },
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "2",
        "_score" : 0.0,
        "_source" : {
          "id" : 2,
          "title" : "华为手机",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5500
        }
      }
    ]
  }
}
```



### 3.2.11 聚合查询

聚合允许使用者对es文档进行统计分析，类似与关系型数据库中的group by，当然还有很多其他的聚合，例如取最大值、平均值等等。

max最大值

```shell
POST /my_index/_search
{
  "query": {
    "match_all": {}
  },
  #不显示查询出来的数据，只显示最大价格⚠️
  "size": 0, 
  "aggs": {
  #名字随便写，并非必须叫max_price⚠️
    "max_price": {
      "max": {
        "field": "price"
      }
    }
  }
}

结果:
{
  "took" : 1,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "max_price" : {
      "value" : 5500.0
    }
  }
}

```

min最小值

```shell
POST /my_index/_search
{
  "query": {
    "match_all": {}
  },
  "size": 0, 
  "aggs": {
    "min_price": {
      "min": {
        "field": "price"
      }
    }
  }
}

结果:
{
  "took" : 12,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "min_price" : {
      "value" : 3600.0
    }
  }
}
```

avg平均值

```shell
POST /my_index/_search
{
  "query": {
    "match_all": {}
  },
  "size": 0, 
  "aggs": {
    "avg_price": {
      "avg": {
        "field": "price"
      }
    }
  }
}
结果:
{
  "took" : 12,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "avg_price" : {
      "value" : 4829.333333333333
    }
  }
}
```

sum求和

```json
POST /my_index/_search
{
  "query": {
    "match_all": {}
  },
  "size": 0, 
  "aggs": {
    "sum_price": {
      "sum": {
        "field": "price"
      }
    }
  }
}
结果:
{
  "took" : 3,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "sum_price" : {
      "value" : 14488.0
    }
  }
}
```

stats信息汇总，包含总数、最大值、最小值、平均值、总和

```json
POST /my_index/_search
{
  "query": {
    "match_all": {}
  },
  "size": 0, 
  "aggs": {
    "stats_price": {
      "stats": {
        "field": "price"
      }
    }
  }
}
结果:
{
  "took" : 20,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "stats_price" : {
      "count" : 3,
      "min" : 3600.0,
      "max" : 5500.0,
      "avg" : 4829.333333333333,
      "sum" : 14488.0
    }
  }
}
```

terms 桶聚和相当于sql中的group by语句，分组查询

```json
POST /my_index/_search
{
  "query": {
    "match_all": {}
  },
  "size": 0, 
  "aggs": {
    "groupby_category": {
      "terms": {
        "field": "category",
        #结果显示几行数据⚠️
        "size": 10
      }
    }
  }
}
结果:
{
  "took" : 16,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "groupby_category" : {
      "doc_count_error_upper_bound" : 0,
      "sum_other_doc_count" : 0,
      "buckets" : [
        {
          "key" : "华为",
          "doc_count" : 2
        },
        {
          "key" : "vivo",
          "doc_count" : 1
        }
      ]
    }
  }
}
```

还可以对桶继续下钻：

```json
POST /my_index/_search
{
  "query": {
    "match_all": {}
  },
  "size": 0, 
  "aggs": {
    "groupby_category": {
      "terms": {
        "field": "category",
        "size": 10
      },
      "aggs": {
        "avg_price": {
          "avg": {
            "field": "price"
          }
        }
      }
    }
  }
}
结果:
{
  "took" : 2,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "groupby_category" : {
      "doc_count_error_upper_bound" : 0,
      "sum_other_doc_count" : 0,
      "buckets" : [
        {
          "key" : "华为",
          "doc_count" : 2,
          "avg_price" : {
            "value" : 5444.0
          }
        },
        {
          "key" : "vivo",
          "doc_count" : 1,
          "avg_price" : {
            "value" : 3600.0
          }
        }
      ]
    }
  }
}
```





## 3.3 进阶查询

### 3.3.1 排序

```shell
POST /my_index/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "title": "华为"
          }
        }
      ]
    }
  },
  #先按价格升序，再按_score分值降序
  "sort": [
    {
      "price": {
        "order": "asc"
      }
    },
    {
      "_score": {
        "order": "desc"
      }
    }
  ]
}
结果:
{
  "took" : 0,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 2,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 0.35411233,
        "_source" : {
          "id" : 1,
          "title" : "华为笔记本电脑",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5388
        },
        "sort" : [
          5388,
          0.35411233
        ]
      },
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "2",
        "_score" : 0.5619608,
        "_source" : {
          "id" : 2,
          "title" : "华为手机",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5500
        },
        "sort" : [
          5500,
          0.5619608
        ]
      }
    ]
  }
}

```



### 3.3.2 分页查询

from、size分页

分页的两个关键属性:from、size。

- from: 当前页的起始索引，默认从0开始。 from = (pageNum - 1) * size。如每页显示5条，第6页为=(6-1)*5=25条，则显示25到5 ⚠️
- size: 每页显示多少条

```json
POST /my_index/_search
{
  "query": {
    "match_all": {}
  },
  #从第几个开始，默认从0开始查询出第一个
  "from": 0,
  #每页显示几条
  "size": 2
}
结果:
{
  "took" : 3,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "id" : 1,
          "title" : "华为笔记本电脑",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5388
        }
      },
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "2",
        "_score" : 1.0,
        "_source" : {
          "id" : 2,
          "title" : "华为手机",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5500
        }
      }
    ]
  }
}
```

scroll分页

第一次使用分页查询:

```json
POST /my_index/_search?scroll=1m
{
  "query": {
    "match_all": {}
  },
  "size": 1
}
结果:
{
  "_scroll_id" : "FGluY2x1ZGVfY29udGV4dF91dWlkDXF1ZXJ5QW5kRmV0Y2gBFGRKV2JWWHdCeUZ2WWVjeDY1V3NlAAAAAAAAGskWTERWbzhrWFZTdFd3WnVoOV9EaGV0dw==",
  "took" : 0,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 1.0,
        "_source" : {
          "id" : 1,
          "title" : "华为笔记本电脑",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5388
        }
      }
    ]
  }
}
```

接着就滚动查询，用第一次返回的_scroll_id接着查：

```json
GET /_search/scroll?scroll=1m
{
  "scroll_id":"FGluY2x1ZGVfY29udGV4dF91dWlkDXF1ZXJ5QW5kRmV0Y2gBFHNKV2VWWHdCeUZ2WWVjeDZYbXNGAAAAAAAAGwUWTERWbzhrWFZTdFd3WnVoOV9EaGV0dw=="
}
结果:
{
  "_scroll_id" : "FGluY2x1ZGVfY29udGV4dF91dWlkDXF1ZXJ5QW5kRmV0Y2gBFHNKV2VWWHdCeUZ2WWVjeDZYbXNGAAAAAAAAGwUWTERWbzhrWFZTdFd3WnVoOV9EaGV0dw==",
  "took" : 4,
  "timed_out" : false,
  "terminated_early" : true,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 3,
      "relation" : "eq"
    },
    "max_score" : 1.0,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "2",
        "_score" : 1.0,
        "_source" : {
          "id" : 2,
          "title" : "华为手机",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5500
        }
      }
    ]
  }
}
```



### 3.3.3 高亮查询

在进行关键字搜索时，搜索出的内容中的关键字会显示不同的颜色，称之为高亮。

查询的结果关键字自带红色加粗⚠️

```json
POST /my_index/_search
{
  "query": {
    "match": {
      "title": "华为"
    }
  },
  "highlight": {
    "pre_tags": "<b style='color:red'>",
    "post_tags": "</b>",
    "fields": {
      "title": {}
    }
  }
}
结果:
{
  "took" : 80,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 2,
      "relation" : "eq"
    },
    "max_score" : 0.8025915,
    "hits" : [
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "2",
        "_score" : 0.8025915,
        "_source" : {
          "id" : 2,
          "title" : "华为手机",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5500
        },
        "highlight" : {
          "title" : [
            "<b style='color:red'>华为</b>手机"
          ]
        }
      },
      {
        "_index" : "my_index",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 0.49191093,
        "_source" : {
          "id" : 1,
          "title" : "华为笔记本电脑",
          "category" : "华为",
          "images" : "http://www.gulixueyuan.com/xm.jpg",
          "price" : 5388
        },
        "highlight" : {
          "title" : [
            "<b style='color:red'>华为</b>笔记本电脑"
          ]
        }
      }
    ]
  }
}

```



### 3.3.4 近似查询

返回包含与搜索字词相似的字词的文档。**编辑距离**是将一个术语转换为另一个术语所需的一个字符更改的次数。这些更改可以包括：

- 更改字符（box → fox）
- 删除字符（black → lack）
- 插入字符（sic → sick）
- 转置两个相邻字符（act → cat）

主要是怕输入字符时不小心输入错误，近似查询可以帮助纠错，比如搜索手机输入成了手鸡⚠️

为了找到相似的术语，**fuzzy**查询会在指定的编辑距离内创建一组搜索词的所有可能的变体或扩展。然后查询返回每个扩展的完全匹配。通过fuzziness修改编辑距离。一般使用默认值AUTO，根据术语的长度生成编辑距离。默认0.5，改成1纠错能力更强，但是消耗性能也更大。

创建一个索引

```json
PUT /test
```

创建一个文档

```json
PUT /test/_doc/1
{
  "title":"hello world"
}
```

使用近似查询

```json
#fuzzy查询，应该输入world，不小心输入成word，不过ES对其自动纠错
GET /test/_search
{
  "query": {
    "fuzzy": {
      "title": {
        "value": "word"
      }
    }
  }
}
#查询不到结果可使用fuzziness提升匹配距离，但更加浪费效率⚠️

结果:
{
  "took" : 633,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 1,
      "relation" : "eq"
    },
    "max_score" : 0.21576157,
    "hits" : [
      {
        "_index" : "test",
        "_type" : "_doc",
        "_id" : "1",
        "_score" : 0.21576157,
        "_source" : {
          "title" : "hello world"
        }
      }
    ]
  }
}
```





# 4 Java api 操作 es

## 4.1API基本使用

官方参考:https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.x/java-rest-high-getting-started.html

7.8版本:https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.8/java-rest-high-getting-started-maven.html⚠️

### 4.1.1引入Maven依赖

相关依赖

```xml
<dependencies>
  <dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>7.8.0</version>
  </dependency>
  <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
  </dependency>
  <dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>2.0.16</version>
  </dependency>
</dependencies>
```

创建高级客户端对象

```java
RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
```



### 4.1.2 索引操作

```java
	//创建索引
    @Test
    public void createIndex(){
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX);
        try {
            createIndexRequest.mapping("{\n" +
                    "    \"properties\": {\n" +
                    "      \"name\": {\n" +
                    "        \"type\": \"keyword\",\n" +
                    "        \"index\": true,\n" +
                    "        \"store\": true\n" +
                    "      },\n" +
                    "      \"age\": {\n" +
                    "        \"type\": \"integer\",\n" +
                    "        \"index\": true,\n" +
                    "        \"store\": true\n" +
                    "      },\n" +
                    "      \"remark\": {\n" +
                    "        \"type\": \"text\",\n" +
                    "        \"index\": true,\n" +
                    "        \"store\": true,\n" +
                    "        \"analyzer\": \"ik_max_word\",\n" +
                    "        \"search_analyzer\": \"ik_max_word\"\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }", XContentType.JSON);
            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            System.out.println(createIndexResponse.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查看索引
    @Test
    public void getIndex(){
        GetIndexRequest request = new GetIndexRequest(INDEX);
        try {
            GetIndexResponse getIndexResponse = client.indices().get(request, RequestOptions.DEFAULT);
            System.out.println(getIndexResponse.getMappings());
            System.out.println(getIndexResponse.getSettings());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除索引
    @Test
    public void deleteIndex(){
        DeleteIndexRequest request = new DeleteIndexRequest(INDEX);
        try {
            AcknowledgedResponse acknowledgedResponse = client.indices().delete(request, RequestOptions.DEFAULT);
            System.out.println(acknowledgedResponse.isAcknowledged());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
```



### 4.1.3 文档操作

```java
	//创建文档
    @Test
    public void createDocument(){
        IndexRequest request = new IndexRequest(INDEX);
        request.id("1");
        Student student = new Student();
        student.setAge(18);
        student.setName("robin");
        student.setRemark("good man");
        request.source(JSONObject.toJSONString(student), XContentType.JSON);
        try {
            IndexResponse index = client.index(request, RequestOptions.DEFAULT);
            System.out.println(index.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //修改文档
    @Test
    public void updateDocuemnt(){
        UpdateRequest request = new UpdateRequest(INDEX,"1");
        try {
            Student student = new Student();
            student.setRemark("very good man");
            request.doc(JSONObject.toJSONString(student), XContentType.JSON);
            UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
            System.out.println(response.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据ID查询
    @Test
    public void getDocument(){
        GetRequest request = new GetRequest(INDEX,"1");
        try {
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            System.out.println(response.getSourceAsString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //批量操作
    @Test
    public void bulkDocument(){
        BulkRequest request = new BulkRequest();
        Student student = new Student();
        for(int i=0;i<10;i++){
            student.setAge(18 + i);
            student.setName("robin" + i);
            student.setRemark("good man " + i);
            request.add(new IndexRequest(INDEX).id(String.valueOf(10 + i)).source(JSONObject.toJSONString(student), XContentType.JSON));
        }
        try {
            BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
            for(BulkItemResponse itemResponse : response.getItems()){
                System.out.println(itemResponse.isFailed());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除文档
    @Test
    public void deleteDocument(){
        DeleteRequest request = new DeleteRequest(INDEX,"11");
        try {
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            System.out.println(response.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```



### 4.1.4 DSL查询

```java
	private static final String MY_INDEX = "my_index";

    /**
     * dsl查询文档:
     * {
     *   "query": {
     *     "match": {
     *       "title": "华为智能手机"
     *     }
     *   }
     * }
     * */
    @Test
    public void search(){
        SearchRequest request = new SearchRequest(MY_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("title","华为智能手机"));
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            for(SearchHit hit : response.getHits().getHits()){
                System.out.println(hit.getSourceAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 高亮查询
     * */
    @Test
    public void highlightSearch(){
        SearchRequest request = new SearchRequest(MY_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("title","华为智能手机"));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.preTags("<b style='color:red'>");
        highlightBuilder.postTags("</b>");
        builder.highlighter(highlightBuilder);
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            for(SearchHit hit : response.getHits().getHits()){
                System.out.println(hit.getSourceAsMap().get("title") + ":" +hit.getHighlightFields().get("title").fragments()[0].string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 聚合查询
     * */
    @Test
    public void aggsSearch(){
        SearchRequest request = new SearchRequest(MY_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        AggregationBuilder aggregationBuilder = AggregationBuilders
                .terms("groupby_category").field("category");
        aggregationBuilder.subAggregation(AggregationBuilders.avg("avg_price").field("price"));
        builder.aggregation(aggregationBuilder);
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            Terms terms = aggregations.get("groupby_category");
            terms.getBuckets().forEach(bucket -> {
                Avg avg = bucket.getAggregations().get("avg_price");
                System.out.println(bucket.getKeyAsString() + ":" + bucket.getDocCount() + "," + avg.getValue());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```



## 4.2 完整代码

```java
package com.atguigu.es.test;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.es.pojo.Student;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;


import java.io.IOException;
import java.util.Map;


/**
 * @Description: TODD
 * @AllClassName: com.atguigu.es.test.JavaApiTest
 */
public class JavaApiTest {

    //创建高级客户端对象
    RestHighLevelClient client = new RestHighLevelClient(RestClient
            .builder(new HttpHost("127.0.0.1", 9200, "http")));


    /**
     * 测试创建索引库
     */
    @Test
    public void createIndex() throws IOException {
        //创建CreateIndexRequset对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("students");

        //设置静态映射
        createIndexRequest.mapping("{\n" +
                "    \"properties\": {\n" +
                "      \"name\": {\n" +
                "        \"type\": \"keyword\",\n" +
                "        \"index\": true,\n" +
                "        \"store\": true\n" +
                "      },\n" +
                "      \"age\": {\n" +
                "        \"type\": \"integer\",\n" +
                "        \"index\": true,\n" +
                "        \"store\": true\n" +
                "      },\n" +
                "      \"remark\": {\n" +
                "        \"type\": \"text\",\n" +
                "        \"index\": true,\n" +
                "        \"store\": true,\n" +
                "        \"analyzer\": \"ik_max_word\",\n" +
                "        \"search_analyzer\": \"ik_max_word\"\n" +
                "      }\n" +
                "    }\n" +
                "  }", XContentType.JSON);

        //通过高级客户端调用create方法创建索引库
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        //获取ack
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("acknowledged= " + acknowledged);
    }


    /**
     * 查询索引库
     */
    @Test
    public void testGetIndex() throws IOException {
        //创建GetIndexRequest对象
        GetIndexRequest getIndexRequest = new GetIndexRequest("students");
        //调用get方法
        GetIndexResponse getIndexResponse = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        //获取映射信息
        Map<String, MappingMetadata> mappings = getIndexResponse.getMappings();
        System.out.println("mappings = " + mappings);
        //获取设置信息
        Map<String, Settings> settings = getIndexResponse.getSettings();
        System.out.println("settings = " + settings);
    }

    /**
     * 删除索引库
     */
    @Test
    public void testDelete() throws IOException {
        //创建DeleteIndexRequest对象
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("bookstore");
        //调用delete方法
        AcknowledgedResponse acknowledgedResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        //获取ack
        boolean acknowledged = acknowledgedResponse.isAcknowledged();
        System.out.println("acknowledged = " + acknowledged);
    }

    /**
     * 创建文档
     */
    @Test
    public void testIndexDocument() throws IOException {
        //创建IndexRequest对象
        IndexRequest indexRequest = new IndexRequest("students");
        //设置文档的唯一标识id
        indexRequest.id("1001");
        //创建Student对象
        Student student = new Student("张三", 23, "隔壁老王的朋友");
        //使用阿里巴巴的fastjson将Student对象转换为JSON字符串
        String stuJson = JSONObject.toJSONString(student);
        //设置文档的类型
        indexRequest.source(stuJson,XContentType.JSON);
        //调用index方法
        IndexResponse index = client.index(indexRequest, RequestOptions.DEFAULT);
        //获取结果
        System.out.println("index.getResult() = " + index.getResult());
    }

    //修改文档
    @Test
    public void updateDocuemnt(){
        UpdateRequest request = new UpdateRequest("students","1001");
        try {
            Student student = new Student();
            student.setName("法外狂徒张三");
            //转换成JSON
            request.doc(JSONObject.toJSONString(student), XContentType.JSON);
            //调用update方法
            UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
            //获取结果
            System.out.println(response.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //根据ID查询
    @Test
    public void getDocument(){
        GetRequest request = new GetRequest("students","1001");
        try {
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            System.out.println(response.getSourceAsString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //批量操作
    @Test
    public void bulkDocument(){
        BulkRequest request = new BulkRequest();
        Student student = new Student();
        //循环添加新文档
        for(int i=0;i<10;i++){
            student.setAge(18 + i);
            student.setName("robin" + i);
            student.setRemark("good man " + i);
            //添加新文档
            request.add(new IndexRequest("students").id(String.valueOf(10 + i)).source(JSONObject.toJSONString(student), XContentType.JSON));
        }
        try {
            //发送添加请求（同步请求）
            BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
            for(BulkItemResponse itemResponse : bulkResponse.getItems()){
                //是否创建失败，若打印false则表示创建成功
                System.out.println(itemResponse.isFailed());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除文档
    @Test
    public void deleteDocument(){
        DeleteRequest request = new DeleteRequest("students", "1001");
        try {
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            System.out.println(response.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    //索引库名称
    private static final String MY_INDEX = "my_index";

    /**
     * dsl查询文档:
     * {
     *   "query": {
     *     "match": {
     *       "title": "华为智能手机"
     *     }
     *   }
     * }
     * */
    @Test
    public void search(){
        SearchRequest request = new SearchRequest(MY_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //设置查询条件
        builder.query(QueryBuilders.matchQuery("title","华为智能手机"));
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            //循环打印查询结果
            for(SearchHit hit : response.getHits().getHits()){
                System.out.println(hit.getSourceAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 高亮查询
     * */
    @Test
    public void highlightSearch(){
        SearchRequest request = new SearchRequest(MY_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("title","华为智能手机"));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        //设置高亮查询的css样式
        highlightBuilder.preTags("<b style='color:red'>");
        highlightBuilder.postTags("</b>");
        builder.highlighter(highlightBuilder);
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            for(SearchHit hit : response.getHits().getHits()){
                System.out.println(hit.getSourceAsMap().get("title") + ":" +hit.getHighlightFields().get("title").fragments()[0].string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 聚合查询
     * */
    @Test
    public void aggsSearch(){
        SearchRequest request = new SearchRequest(MY_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        //设置查询条件和返回字段
        //使用聚合函数terms桶聚（分组查询，根据category分组）
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("groupby_category").field("category");
        //使用聚合函数，查询分组后的结果平均价格
        aggregationBuilder.subAggregation(AggregationBuilders.avg("avg_price").field("price"));
        builder.aggregation(aggregationBuilder);
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            Aggregations aggregations = response.getAggregations();
            Terms terms = aggregations.get("groupby_category");
            terms.getBuckets().forEach(bucket -> {
                Avg avg = bucket.getAggregations().get("avg_price");
                System.out.println(bucket.getKeyAsString() + ":" + bucket.getDocCount() + "," + avg.getValue());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
```





# 5 spring data elasticsearch

## 5.1 spring data框架

### 5.1.1 spring data概述★

Spring Data是一个用于简化数据库、非关系型数据库、索引库访问，并支持云服务的开源框架。其主要目标是使得对数据的访问变得方便快捷，并支持map-reduce框架和云计算数据服务。 Spring Data可以极大的简化JPA（Elasticsearch…）的写法，可以在几乎不用写实现的情况下，实现对数据的访问和操作。除了CRUD外，还包括如分页、排序等一些常用的功能。

Spring Data的官网：https://spring.io/projects/spring-data

常用模块:

- Spring Data JDBC
- Spring Data JPA
- Spring Data MongoDB
- Spring Data Redis
- spring data elasticsearch

**我们这里主要关注spring data elasticsearch**



### 5.1.2 spring data elasticsearch

Spring Data Elasticsearch 基于 spring data API 简化 Elasticsearch操作，将原始操作Elasticsearch的客户端API 进行封装 。Spring Data为Elasticsearch项目提供集成搜索引擎。Spring Data Elasticsearch POJO的关键功能区域为中心的模型与Elastichsearch交互文档和轻松地编写一个存储索引库数据访问层。

**选择版本时要考虑版本兼容性，springboot2.3.x版本可以兼容elasticsearch7.x版本。**



## 5.2 框架集成★★★

#### 5.2.1 依赖

pom.xml

```xml
<parent>
    <artifactId>spring-boot-starter-parent</artifactId>
    <groupId>org.springframework.boot</groupId>
    <version>2.3.6.RELEASE</version>
</parent>
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
    </dependency>
</dependencies>
```



#### 5.2.2 yml配置

application.yml

```yaml
spring:
  elasticsearch:
    rest:
      uris: http://localhost:9200
```



#### 5.2.3 document映射

```java
package com.atguigu.springdata.es.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

//无参构造
@NoArgsConstructor
//全参构造
@AllArgsConstructor
//公共的get和set方法
@Data
//索引名
@Document(indexName = "goods")
public class Goods implements Serializable {
		//不分词字符串
    @Field(type = FieldType.Keyword)
    private String id;
  	//分词字符串
    @Field(type = FieldType.Text)
    private String goodsName;
    @Field(type = FieldType.Integer)
    private Integer store;
    @Field(type = FieldType.Double)
    private double price;

}
```



#### 5.2.4 dao数据访问

```java
package com.atguigu.springdata.es.dao;

import com.atguigu.springdata.es.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

//泛型是pojo类型和pojo的主键类型⚠️
public interface GoodsDao extends ElasticsearchRepository<Goods,String> {
    //自定义一个方法根据商品名称查询
    //格式必须固定，find + 索引库名 + 驼峰属性名  ⚠️
    Goods findGoogsByGoodsName(String goodsName);
  
    //根据格式再自定义一个根据ID查询
    Goods findGoodsById(Long id);
}
```



#### 1.2.5 springboot启动类

```java
package com.atguigu.springdata.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataEsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDataEsApplication.class,args);
    }
}
```



#### 1.2.6 接口测试

```java
package com.atguigu.springdata.es.test;

import com.atguigu.springdata.es.dao.GoodsDao;
import com.atguigu.springdata.es.pojo.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GoodsDaoTest {

    @Autowired
    private GoodsDao goodsDao;

    /*
        测试添加文档
    */
    @Test
    public void testCreateDocument(){
        //创建Goods对象
        Goods goods = new Goods("1001", "Iphone 14 ProMax", 100, 9788.00);
        //调用GoodsDao中继承的save方法添加文档
        goodsDao.save(goods);
    }

    /*
        测试查询文档
    */
    @Test
    public void testGetDocument(){
      	//查询方法有很多，格式为findxxxx，或者根据格式自定义查询方法⚠️
        Goods goods = goodsDao.findById("1001").get();
        System.out.println("goods = " + goods);
    }

    /*
        测试自定义的方法1
    */
    @Test
    public void testGetGoodsByGoodsName(){
        Goods googsByGoodsName = goodsDao.findGoogsByGoodsName("Iphone 14 ProMax");
        System.out.println("googsByGoodsName = " + googsByGoodsName);
    }

    /*
        测试自定义的方法2
    */
    @Test
    public void testGetGoodsById(){
        Goods googsById = goodsDao.findGoodsById(1001l);
        System.out.println("googsByGoodsName = " + googsById);
    }
}
```



## 5.3CAP定理

### 5.3.1CAP定理的概述

- **CAP 定理**，又被叫作**布鲁尔定理**。对于设计分布式系统(不仅仅是分布式事务)的架构师来说，CAP 就是你的入门理论。

- 分布式系统（distributed system）正变得越来越重要，大型网站几乎都是分布式的。

- 分布式系统的最大难点，就是各个节点的状态如何同步。CAP 定理是这方面的基本定理，也是理解分布式系统的起点。

**C (一致性)**：指数据在多个副本之间能够保持一致的特性（严格的一致性）在分布式系统中的所有数据备份，在同一时刻是否同样的值。（所有节点在同一时间具有相同的数据）

一致性（Consistency）是指多副本（Replications）问题中的数据一致性。可以分为强一致性、与弱一致性。

① 强一致性

**简言之，在任意时刻，所有节点中的数据是一样的。**

例如，对于关系型数据库，要求更新过的数据能被后续的访问都能看到，这是强一致性。

② 弱一致性

数据更新后，如果能容忍后续的访问只能访问到部分或者全部访问不到，则是弱一致性。

**最终一致性就属于弱一致性。**

**A (可用性)**：指系统提供的服务必须一直处于可用的状态，每次只要收到用户的请求，服务器就必须给出回应。在合理的时间内返回合理的响应（不是错误和超时的响应）

**只有非故障节点才能满足业务正常；只有在合理的时间内，用户才能接受；只有返回合理的响应，用户才能接受。**

**P (网络分区容错性)**：网络节点之间无法通信的情况下，节点被隔离，产生了网络分区， 整个系统仍然是可以工作的 . 大多数分布式系统都分布在多个子网络。每个子网络就叫做一个区（partition）。分区容错的意思是，区间通信可能失败。比如，一台服务器放在中国，另一台服务器放在美国，这就是两个区，它们之间可能无法通信。

- 什么是分区？

**在分布式系统中，不同的节点分布在不同的子网络中，由于一些特殊的原因，这些子节点之间出现了网络不通的状态，但他们的内部子网络是正常的。从而导致了整个系统的环境被切分成了若干个孤立的区域。这就是分区。**

- CAP原则的精髓就是要么AP，要么CP，要么AC，但是不存在CAP。
- 其实AC虽然可以实现，但也不符合使用逻辑，因为必须要求服务都在同一台服务器上⚠️
- 银行等单位必须保障一致性，所以一般用CP，其他系统为了确保系统使用的容错性一般用AP 



### 5.3.2CAP定理的证明

现在我们就来证明一下，为什么不能同时满足三个特性？

假设有两台服务器，一台放着应用A和数据库V，一台放着应用B和数据库V，他们之间的网络可以互通，也就相当于分布式系统的两个部分。

在满足一致性的时候，两台服务器 N1和N2，一开始两台服务器的数据是一样的，DB0=DB0。在满足可用性的时候，用户不管是请求N1或者N2，都会得到立即响应。在满足分区容错性的情况下，N1和N2有任何一方宕机，或者网络不通的时候，都不会影响N1和N2彼此之间的正常运作。

当用户通过N1中的A应用请求数据更新到服务器DB0后，这时N1中的服务器DB0变为DB1，通过分布式系统的数据同步更新操作，N2服务器中的数据库V0也更新为了DB1，这时，用户通过B向数据库发起请求得到的数据就是即时更新后的数据DB1。

上面是正常运作的情况，但分布式系统中，最大的问题就是网络传输问题，现在假设一种极端情况，N1和N2之间的网络断开了，但我们仍要支持这种网络异常，也就是满足分区容错性，那么这样能不能同时满足一致性和可用性呢？

假设N1和N2之间通信的时候网络突然出现故障，有用户向N1发送数据更新请求，那N1中的数据DB0将被更新为DB1，由于网络是断开的，N2中的数据库仍旧是DB0；

如果这个时候，有用户向N2发送数据读取请求，由于数据还没有进行同步，应用程序没办法立即给用户返回最新的数据DB1，怎么办呢？有二种选择，第一，牺牲数据一致性，响应旧的数据DB0给用户；第二，牺牲可用性，阻塞等待，直到网络连接恢复，数据更新操作完成之后，再给用户响应最新的数据DB1。

上面的过程比较简单，但也说明了要满足分区容错性的分布式系统，只能在一致性和可用性两者中，选择其中一个。也就是说分布式系统不可能同时满足三个特性。这就需要我们在搭建系统时进行取舍了，那么，怎么取舍才是更好的策略呢?



### 5.3.3取舍策略

现如今，对于多数大型互联网应用的场景，主机众多、部署分散，而且现在的集群规模越来越大，节点只会越来越多，所以节点故障、网络故障是常态，因此分区容错性也就成为了一个分布式系统必然要面对的问题。那么就只能在C和A之间进行取舍。

**原因是**

因为，在分布式系统中，网络无法 100% 可靠，分区其实是一个必然现象，随着网络节点出现问题，产生了分区, 这时候其他节点和出错节点的数据必然会不一致，这时候就要面临选择，

是选择停掉所有的服务，等网络节点修复后恢复数据，以此来保证一致性（PC）,

还是选择继续提供服务，放弃强一致性的要求，以此来保证整体的可用性（PA）。

所以，最多满足两个条件：

| 组合 | 分析结果                                                     |
| ---- | ------------------------------------------------------------ |
| CA   | 满足原子和可用，放弃分区容错。说白了，就是一个整体的应用。   |
| CP   | 满足原子和分区容错，也就是说，要放弃可用。当系统被分区，为了保证原子性，必须放弃可用性，让服务停用。 |
| AP   | 满足可用性和分区容错，当出现分区，同时为了保证可用性，必须让节点继续对外服务，这样必然导致失去原子性。 |

在分布式系统设计中AP的应用较多，即保证分区容忍性和可用性，牺牲数据的强一致性（写操作后立刻读取到最新数据），保证数据最终一致性。比如：订单退款，今日退款成功，明日账户到账，只要在预定的用户可以接受的时间内退款事务走完即可。

顺便一提，CAP 理论中是忽略网络延迟，也就是当事务提交时，从节点 A 复制到节点 B 没有延迟，但是在现实中这个是明显不可能的，所以总会有一定的时间是不一致。

但是，有个特殊情况需要注意：但对于传统的项目就可能有所不同，拿银行的转账系统来说，涉及到金钱的对于数据一致性不能做出一丝的让步，C必须保证，出现网络故障的话，宁可停止服务，可以在A和P之间做取舍。

**总而言之，没有最好的策略，好的系统应该是根据业务场景来进行架构设计的，只有适合的才是最好的。**





# 6 elasticsearch集群

单台Elasticsearch服务器提供服务，往往都有最大的负载能力，超过这个阈值，服务器性能就会大大降低甚至不可用，所以生产环境中，一般都是运行在指定服务器集群中。

除了负载能力，单点服务器也存在其他问题：

- 单台机器存储容量有限
- 单服务器容易出现单点故障，无法实现高可用
- 单服务的并发处理能力有限

配置服务器集群时，集群中节点数量没有限制，大于等于2个节点就可以看做是集群了。一般出于高性能及高可用方面来考虑集群中节点数量都是3个以上。

### 6.1 集群安装★

#### 6.1.1 环境搭建

一般集群建议3台机器以上，这里我们就使用1台机器来安装集群环境。一般生产环境都是使用linux服务器，所以我们这里就是linux环境下安装es集群。

| 节点名称 | 节点IP        | http服务端口 | transport节点通讯端口 |
| -------- | ------------- | ------------ | --------------------- |
| node-1   | 192.168.6.100 | 9201         | 9301                  |
| node-2   | 192.168.6.100 | 9202         | 9302                  |
| node-3   | 192.168.6.100 | 9203         | 9303                  |

**各个机器集群环境安装之前，先把之前的data数据目录删除，要保重之前节点内没数据才可以搭建。**⚠️

向Linux服务器传输elasticsearch安装包并解压，并重命名复制三份。

```shell
tar -zxvf elasticsearch-7.8.0-linux-x86_64.tar.gz
```

打开解压后的文件夹config/elasticsearch.yml文件，将注释掉的集群名称、节点名称、允许访问的网络、端口等信息，按照以下配置信息，对三个节点的配置文件内容取消注释并进行修改。

**注意：**内存不足时可在配置文件中修改elasticsearch的占用内存大小，默认4g。⚠️

```shell
-Xms1g
```

**node-1:**

```shell
#集群名称
cluster.name: my-application
#节点名称
node.name: node-1
#配置允许的访问网络
network.host: 0.0.0.0
#http服务端口
http.port: 9201
#配置集群间通信的端口号
transport.tcp.port: 9301
#是否允许为主节点，默认true
node.master: true
#是否为数据节点，默认true
node.data: true
#初始配置选举master节点
cluster.initial_master_nodes: ["node-1"]
#节点发现
discovery.seed_hosts: ["localhost:9301", "localhost:9302","localhost:9303"]
#elasticsearch-head 跨域解决
http.cors.allow-origin: "*"
http.cors.enabled: true
```

**node-2:**

```shell
#集群名称
cluster.name: my-application
#节点名称
node.name: node-2
#配置允许的访问网络
network.host: 0.0.0.0
#http服务端口
http.port: 9202
#配置集群间通信的端口号
transport.tcp.port: 9302
#是否允许为主节点，默认true
node.master: true
#是否为数据节点，默认true
node.data: true
#初始配置选举master节点
cluster.initial_master_nodes: ["node-1"]
#节点发现
discovery.seed_hosts: ["localhost:9301", "localhost:9302","localhost:9303"]
```

**node-3:**

```shell
#集群名称
cluster.name: my-application
#节点名称
node.name: node-3
#配置允许的访问网络
network.host: 0.0.0.0
#http服务端口
http.port: 9203
#配置集群间通信的端口号
transport.tcp.port: 9303
#是否允许为主节点，默认true
node.master: true
#是否为数据节点，默认true
node.data: true
#初始配置选举master节点
cluster.initial_master_nodes: ["node-1"]
#节点发现
discovery.seed_hosts: ["localhost:9301", "localhost:9302","localhost:9303"]
```

**给普通用户授权**：分别将三个文件夹的用户和组都改成es

```shell
chown -R es:es xxx/
```

切换普通用户(es)登入⚠️，依次启动node-1、node-2、node-3节点。

```shell
#执行bin目录下的elasticsearch(注意，不能使用root账号启动)，-d代表后台启动
./xxx/elasticsearch -d
```

启动完毕后可使用谷歌插件查看节点健康状态



#### 6.1.2 head插件

kibana中查看集群相关的信息不是那么的直观，这里介绍一款第三方浏览器插件(elasticsearch-head)来查看和管理集群。

elasticsearch-head是一个nodesjs项目，所以要先安装nodejs环境。

nodejs安装

```shell
#下载nodejs(可以直接copy资料中的node-v12.18.1-linux-x64.tar.xz):
#注意:我的软件放在/opt目录下。
wget https://nodejs.org/dist/v12.18.1/node-v12.18.1-linux-x64.tar.xz

#解压文件：
tar -Jxf node-v12.18.1-linux-x64.tar.xz
mv  node-v12.18.1-linux-x64 nodejs

#配置软链接环境(注意目录地址是否正确)：
ln -s /opt/nodejs/bin/node /usr/local/bin/node
ln -s /opt/nodejs/bin/npm /usr/local/bin/npm
```

验证nodejs环境(node -v):

elasticsearch-head插件安装

```shell
#上传资料中的文件(该文件是nodejs编译后的)elasticsearch-head-compile-after.tar.gz
#1.解压文件
tar zxvf elasticsearch-head-compile-after.tar.gz

#2.修改Gruntfile.js(/解压目录/Gruntfile.js)找到代码中的93行[:93],将192.168.100.100修改为自己的IP
 server: {
     options: {
         hostname: '192.168.6.100',
         port: 9100,
         base: '.',
         keepalive: true
     }
 }
 
#3.修改app.js(/解压目录/_stie/app.js)找到4354行[:4354],将http://localhost:9200修改为对应的IP
this.base_uri = this.config.base_uri || this.prefs.get("app-base_uri") || "http://192.168.6.100:9200";

#4.启动head(/opt/es/elasticsearch-head为解压目录)
cd /opt/es/elasticsearch-head/node_modules/grunt
#后台启动
nohup ./grunt server >/dev/null 2>&1 &
```

测试访问(head端口9100):http://192.1686.100:9100/



## 6.2 集群核心概念

### 6.2.1集群概念术语

**集群Cluster**

一个集群就是由一个或多个服务器节点组织在一起，共同持有整个的数据，并一起提供索引和搜索功能。一个Elasticsearch集群有一个唯一的名字标识，这个名字默认就是”elasticsearch”。这个名字是重要的，因为一个节点只能通过指定某个集群的名字，来加入这个集群。

**节点Node**

​	集群中包含很多服务器，一个节点就是其中的一个服务器。作为集群的一部分，它存储数据，参与集群的索引和搜索功能。

​	一个节点也是由一个名字来标识的，默认情况下，这个名字是一个随机的漫威漫画角色的名字，这个名字会在启动的时候赋予节点。这个名字对于管理工作来说挺重要的，因为在这个管理过程中，你会去确定网络中的哪些服务器对应于Elasticsearch集群中的哪些节点。

​	一个节点可以通过配置集群名称的方式来加入一个指定的集群。默认情况下，每个节点都会被安排加入到一个叫做“elasticsearch”的集群中，这意味着，如果你在你的网络中启动了若干个节点，并假定它们能够相互发现彼此，它们将会自动地形成并加入到一个叫做“elasticsearch”的集群中。

​	在一个集群里，只要你想，可以拥有任意多个节点。而且，如果当前你的网络中没有运行任何Elasticsearch节点，这时启动一个节点，会默认创建并加入一个叫做“elasticsearch”的集群。

**分片(Shards)**

​	一个索引可以存储超出单个节点硬件限制的大量数据。比如，一个具有10亿文档数据的索引占据1TB的磁盘空间，而任一节点都可能没有这样大的磁盘空间。或者单个节点处理搜索请求，响应太慢。为了解决这个问题，Elasticsearch提供了将索引划分成多份的能力，每一份就称之为分片⚠️。当你创建一个索引的时候，你可以指定你想要的分片的数量。每个分片本身也是一个功能完善并且独立的“索引”，这个“索引”可以被放置到集群中的任何节点上。

分片很重要，主要有两方面的原因：

1）允许你水平分割 / 扩展你的内容容量。

2）允许你在分片之上进行分布式的、并行的操作，进而提高性能/吞吐量。

至于一个分片怎样分布，它的文档怎样聚合和搜索请求，是完全由Elasticsearch管理的，对于作为用户的你来说，这些都是透明的，无需过分关心。

**副本(Replicas)**

​	在一个网络 / 云的环境里，失败随时都可能发生，在某个分片/节点不知怎么的就处于离线状态，或者由于任何原因消失了，这种情况下，有一个故障转移机制是非常有用并且是强烈推荐的。为此目的，Elasticsearch允许你创建分片的一份或多份拷贝，这些拷贝叫做复制分片(副本)⚠️。

​	复制分片之所以重要，有两个主要原因：

- 在分片/节点失败的情况下，提供了高可用性。因为这个原因，注意到复制分片从不与原/主要（original/primary）分片置于同一节点上是非常重要的。
- 扩展你的搜索量/吞吐量，因为搜索可以在所有的副本上并行运行。

总之，每个索引可以被分成多个分片。一个索引也可以被复制0次（意思是没有复制）或多次。一旦复制了，每个索引就有了主分片（作为复制源的原来的分片）和复制分片（主分片的拷贝）之别。分片和复制的数量可以在索引创建的时候指定。在索引创建之后，你可以在任何时候动态地改变复制的数量，但是你事后不能改变分片的数量。默认情况下，Elasticsearch中的每个索引被分片1个主分片和1个复制，这意味着，如果你的集群中至少有两个节点，你的索引将会有1个主分片和另外1个复制分片（1个完全拷贝），这样的话每个索引总共就有2个分片，我们需要根据索引需要确定分片个数。

**分配(Allocation)**

将分片分配给某个节点的过程，包括分配主分片或者副本。如果是副本，还包含从主分片复制数据的过程。这个过程是由master节点完成的。

**节点类型**

es集群中的节点类型分为:Master、DataNode。

- master:

  Elasticsearch启动时，会选举出来一个Master节点。当某个节点启动后,使用Zen Discovery机制找到集群中的其他节点，并建立连接。 

  discovery.seed_hosts: ["host1", "host2", "host3"] 

  并从候选主节点中选举出一个主节点。 

  cluster.initial_master_nodes: ["node-1", "node-2","node-3"]

  > Master节点主要负责： 
  >
  > - 管理索引（创建索引、删除索引）、分配分片 
  >
  > - 维护元数据 
  >
  > - 管理集群节点状态 
  >
  > 不负责数据写入和查询，比较轻量级。一个ElasticSearch集群中，只有一个Master节点。在生产环境中，内存可以相对 
  >
  > 小一点，但要确保机器稳定。

- DataNode:

  在Elasticsearch集群中，会有N个DataNode节点。DataNode节点主要负责： 

  - 数据写入、数据检索，大部分Elasticsearch的压力都在DataNode节点上 

  在生产环境中，内存最好配置大一些。



### 6.2.2系统架构

​	一个运行中的 Elasticsearch 实例称为一个节点，而集群是由一个或者多个拥有相同 cluster.name 配置的节点组成， 它们共同承担数据和负载的压力。当有节点加入集群中或者从集群中移除节点时，集群将会重新平均分布所有的数据。

​	当一个节点被选举成为主节点时， 它将负责管理集群范围内的所有变更，例如增加、删除索引，或者增加、删除节点等。 而主节点并不需要涉及到文档级别的变更和搜索等操作，所以当集群只拥有一个主节点的情况下，即使流量的增加它也不会成为瓶颈。 任何节点都可以成为主节点。我们的示例集群就只有一个节点，所以它同时也成为了主节点。

​	作为用户，我们可以将请求发送到集群中的任何节点 ，包括主节点。 每个节点都知道任意文档所处的位置，并且能够将我们的请求直接转发到存储我们所需文档的节点。 无论我们将请求发送到哪个节点，它都能负责从各个包含我们所需文档的节点收集回数据，并将最终结果返回給客户端。 Elasticsearch 对这一切的管理都是透明的。



### 6.3elasticsearch分片

我们可以在建立索引的时候创建分片信息：

```shell
#number_of_shards：主分片数量(7.x版本之后如果不指定数量默认为1)
#number_of_replicas：每个主分片对应的副本数量
PUT /users
{
  "settings": {
    "number_of_shards": 3,
    "number_of_replicas": 2
  }
}
```

head中数据查看说明:

★ 代表当前节点为master节点

● 表示DataNode节点

**粗线框格子为主分片，细线框为副本分片，主分片与副本分片不能同时在一台机器上。**

> 刚才的例子中，我们创建了3个主分片，然后又为每个主分片配置了两个副本分片，加起来一共9个分片。分片序号分别为0、1、2代表不同的数据段存储。其中0号分片的主分片在node-3机器上，node-1和node-2是它的备份分片。

**<font color='red'>注意:主分片数量一旦指定后就不允许更改，否则会影响后续的数据操作(分片位置路由是取模主分片数量)。</font>**

虽然主分片数量不可用更改，但是副本数量可以修改:

```json
#修改副本数
PUT users/_settings
{
  "number_of_replicas": 0
}
```



### 6.4分片控制

#### 6.4.1 写流程

**新建和删除请求都是写操作， 必须在主分片上面完成之后才能被复制到相关的副本分片**⚠️

- 第一步: 客户端选择DataNode节点发送请求，假设发送到node-2节点上。此时被选择的node-2节点也称为coordinating node(协调节点)
- 第二步: 协调节点根据路由规则计算分片索引位置。并将请求发送到分片索引对应的主分片机器上(这里假设分片计算后的值为0，那么请求会命中到node-3节点上)。写和读都需要模分片的数量，所以分片不能改⚠️
  - 计算分片索引位置: shard = hash(routing) % number_of_primary_shards,routing可以自己设定，一般默认为文档的ID。
  - 即指的是文档ID的哈希值 模 主分片的数量，得到数是多少就存在哪个主分片上。⚠️

- 第三步: 当主分片文档写入完成后，同时将数据推送到与之对应的副本分片进行写入操作
- 第四步: 当分片完成了写入后再由协调节点将操作结果返回给客户端



#### 6.4.2 读流程

- 第一步:客户端选择DataNode节点发送请求，假设发送到node-2节点上。此时被选择的node-2节点也称为coordinating node(协调节点)
- 第二步: 协调节点将从客户端获取到的请求数据转发到其它节点
- 第三步: 其它节点将查询结果文档ID、节点、分片信息返回给协调节点
- 第四步: 协调节点通过文档ID、节点信息等发送get请求给其它节点进行数据获取，最后进行汇总排序将数据返回给客户端





# 7 分片原理

## 7.1 倒排索引★

Elasticsearch 使用一种称为**倒排索引**的结构，它适用于快速的全文搜索。

见其名，知其意，有倒排索引，肯定会对应有正向索引。正向索引（forward index），反向索引（inverted index）更熟悉的名字是倒排索引。

所谓的正向索引，就是搜索引擎会将待搜索的文件都对应一个文件ID，搜索时将这个ID和搜索关键字进行对应，形成K-V对，然后对关键字进行统计计数

但是互联网上收录在搜索引擎中的文档的数目是个天文数字，这样的索引结构根本无法满足实时返回排名结果的要求。所以，搜索引擎会将正向索引重新构建为倒排索引，即把文件ID对应到关键词的映射转换为关键词到文件ID的映射，每个关键词都对应着一系列的文件，这些文件中都出现这个关键词。

一个倒排索引由文档中所有不重复词的列表构成，对于其中每个词，有一个包含它的文档列表。例如，假设我们有两个文档，每个文档的 content 域包含如下内容：

- The quick brown fox jumped over the lazy dog

- Quick brown foxes leap over lazy dogs in summer

为了创建倒排索引，我们首先将每个文档的 content 域拆分成单独的 词（我们称它为 词条 或 tokens ），创建一个包含所有不重复词条的排序列表，然后列出每个词条出现在哪个文档。

| 词条  | 文档1  | 文档2  |
| :---: | :----: | :----: |
|  The  | 未出现 | 出现⚠️  |
| quick | 出现⚠️  | 未出现 |
| brown | 出现⚠️  | 未出现 |

现在，如果我们想搜索 quick brown ，我们只需要查找包含每个词条的文档：

两个文档都匹配，但是第一个文档比第二个匹配度更高。如果我们使用仅计算匹配词条数量的简单相似性算法，那么我们可以说，对于我们查询的相关性来讲，第一个文档比第二个文档更佳。

所以倒排索引查询快的代价是，存储时更加麻烦，需要创建更新索引。



## 7.2 文档搜索

早期的全文检索会为整个文档集合建立一个很大的倒排索引并将其写入到磁盘。 一旦新的索引就绪，旧的就会被其替换，这样最近的变化便可以被检索到。

倒排索引被写入磁盘后是 不可改变 的，它永远不会修改。 

不变性有重要的价值：

- 不需要锁。如果你从来不更新索引，你就不需要担心多进程同时修改数据的问题。

- 一旦索引被读入内核的文件系统缓存，便会留在哪里，由于其不变性。只要文件系统缓存中还有足够的空间，那么大部分读请求会直接请求内存，而不会命中磁盘。这提供了很大的性能提升。

- 其它缓存(像filter缓存)，在索引的生命周期内始终有效。它们不需要在每次数据改变时被重建，因为数据不会变化。

- 写入单个大的倒排索引允许数据被压缩，减少磁盘 I/O 和 需要被缓存到内存的索引的使用量。

当然，一个不变的索引也有不好的地方。主要事实是它是不可变的! 你不能修改它。如果你需要让一个新的文档可被搜索，你需要重建整个索引。这要么对一个索引所能包含的数据量造成了很大的限制，要么对索引可被更新的频率造成了很大的限制。



## 7.3 动态更新索引

如何在保留不变性的前提下实现倒排索引的更新？

答案是: **用更多的索引。**通过增加新的补充索引来反映新近的修改，而不是直接重写整个倒排索引。每一个倒排索引都会被轮流查询到，从最早的开始查询完后再对结果进行合并。

Elasticsearch 基于 Lucene, 这个java库引入了**按段搜索**的概念。 每一 段本身都是一个倒排索引。 但索引在 Lucene 中除表示所有段的集合外， 还增加了提交点的概念 （ 每次添加文档更新提交点，并同时创建新的索引）⚠️。

**按段搜索**会以如下流程执行：

1. 新文档被收集到内存索引缓存

2. 不时地, 缓存被 提交 

(1) 一个新的段—一个追加的倒排索引—被写入磁盘。

(2) 一个新的包含新段名字的 提交点 被写入磁盘

(3) 磁盘进行同步 — 所有在文件系统缓存中等待的写入都刷新到磁盘，以确保它们被写入物理文件

3. 新的段被开启，让它包含的文档可见以被搜索

4. 内存缓存被清空，等待接收新的文档 

当一个查询被触发，所有已知的段按顺序被查询。词项统计会对所有段的结果进行聚合，以保证每个词和每个文档的关联都被准确计算。 这种方式可以用相对较低的成本将新文档添加到索引。

段是不可改变的，所以既不能把文档从旧的段中移除，也不能修改旧的段来进行反映文档的更新。 取而代之的是，每个提交点会包含一个 .del 文件，文件中会列出这些被删除文档的段信息。

当一个文档被 “删除” 时，它实际上只是在 .del 文件中被标记删除。一个被标记删除的文档仍然可以被查询匹配到，但它会在最终结果被返回前从结果集中移除（虽然删除的文档也会通过索引被搜索出来，但会被过滤掉，无用的段对应的索引会在段合并时被清除）⚠️。

文档更新也是类似的操作方式：当一个文档被更新时，旧版本文档被标记删除，文档的新版本被索引到一个新的段中。 可能两个版本的文档都会被一个查询匹配到，但被删除的那个旧版本文档在结果集返回前就已经被移除。



## 7.4 近实时搜索

- 分段数据先写入到内存缓存中，同时文档操作也会记录translog日志
- 内存的数据对查询不可见，默认间隔1s将内存中数据写入到文件系统缓存中，这里面的数据对查询可见。
- 文件系统缓存数据间隔30分钟再将数据刷入磁盘中。
- 如果文件系统缓存数据在没有刷新到硬盘时宕机了，可以从translog中恢复数据到磁盘，数据恢复完成后translog数据也会清理。



## 7.5 段合并

​	由于自动刷新流程每秒会创建一个新的段 ，这样会导致短时间内的段数量暴增。而段数目太多会带来较大的麻烦。 每一个段都会消耗文件句柄、内存和cpu运行周期。更重要的是，每个搜索请求都必须轮流检查每个段；所以段越多，搜索也就越慢。

​	Elasticsearch通过在后台进行段合并来解决这个问题。小的段被合并到大的段，然后这些大的段再被合并到更大的段。

​	段合并的时候会将那些旧的已删除文档从文件系统中清除。被删除的文档（或被更新文档的旧版本）不会被拷贝到新的大段中。

​	启动段合并不需要你做任何事。进行索引和搜索时会自动进行。

1. 当索引的时候，刷新（refresh）操作会创建新的段并将段打开以供搜索使用。

2. 合并进程选择一小部分大小相似的段，并且在后台将它们合并到更大的段中。这并不会中断索引和搜索。

3. 一旦合并结束，老的段被删除

- 新的段被刷新（flush）到了磁盘。  ** 写入一个包含新段且排除旧的和较小的段的新提交点。

- 新的段被打开用来搜索。

- 老的段被删除。

合并大的段需要消耗大量的I/O和CPU资源，如果任其发展会影响搜索性能。Elasticsearch在默认情况下会对合并流程进行资源限制，所以搜索仍然 有足够的资源很好地执行。





# 8 优化建议

## 8.1 硬件选择

Elasticsearch的基础是 Lucene，所有的索引和文档数据是存储在本地的磁盘中，具体的路径可在 ES 的配置文件../config/elasticsearch.yml中配置，如下：

```shell
\#----------------------------------- Paths ------------------------------------

\#

\# Path to directory where to store the data (separate multiple locations by comma):

\#

\#path.data: /path/to/data

\#

\# Path to log files:

\#

\#path.logs: /path/to/logs

\#
```

1. 磁盘在现代服务器上通常都是瓶颈。Elasticsearch 重度使用磁盘，你的磁盘能处理的吞吐量越大，你的节点就越稳定。这里有一些优化磁盘 I/O 的技巧：

2. 使用 SSD。就像其他地方提过的， 他们比机械磁盘优秀多了。

3. 使用 RAID 0。条带化 RAID 会提高磁盘 I/O，代价显然就是当一块硬盘故障时整个就故障了。不要使用镜像或者奇偶校验 RAID 因为副本已经提供了这个功能。

4. 另外，使用多块硬盘，并允许 Elasticsearch 通过多个 path.data 目录配置把数据条带化分配到它们上面。

5. 不要使用远程挂载的存储，比如 NFS 或者 SMB/CIFS。这个引入的延迟对性能来说完全是背道而驰的。




## 8.2 分片策略★

### 8.2.1 合理设置分片数

分片和副本的设计为 ES 提供了支持分布式和故障转移的特性，但并不意味着分片和副本是可以无限分配的。而且索引的分片完成分配后由于索引的路由机制，我们是不能重新修改分片数的。

可能有人会说，我不知道这个索引将来会变得多大，并且过后我也不能更改索引的大小，所以为了保险起见，还是给它设为 1000 个分片吧。但是需要知道的是，一个分片并不是没有代价的。需要了解：

（1）一个分片的底层即为一个 Lucene 索引，会消耗一定文件句柄、内存、以及 CPU 运转。

每一个搜索请求都需要命中索引中的每一个分片，如果每一个分片都处于不同的节点还好， 但如果多个分片都需要在同一个节点上竞争使用相同的资源就有些糟糕了。

（2）用于计算相关度的词项统计信息是基于分片的。如果有许多分片，每一个都只有很少的数据会导致很低的相关度。

一个业务索引具体需要分配多少分片可能需要架构师和技术人员对业务的增长有个预先的判断，横向扩展应当分阶段进行。为下一阶段准备好足够的资源。 只有当你进入到下一个阶段，你才有时间思考需要作出哪些改变来达到这个阶段。一般来说，我们遵循一些原则：

（3）控制每个分片占用的硬盘容量不超过ES的最大JVM的堆空间设置（一般设置不超过32G，参考下文的JVM设置原则），因此，如果索引的总容量在500G左右，那分片大小在16个左右即可；当然，最好同时考虑原则2。

考虑一下node数量，一般一个节点有时候就是一台物理机，如果分片数过多，大大超过了节点数，很可能会导致一个节点上存在多个分片，一旦该节点故障，即使保持了1个以上的副本，同样有可能会导致数据丢失，集群无法恢复。所以， 一般都设置分片数不超过节点数的3倍。

（4）主分片，副本和节点最大数之间数量，我们分配的时候可以参考以下关系：

节点数<=主分片数*（副本数+1）



### 8.2.2 推迟分片分配

对于节点瞬时中断的问题，默认情况，集群会等待一分钟来查看节点是否会重新加入，如果这个节点在此期间重新加入，重新加入的节点会保持其现有的分片数据，不会触发新的分片分配。这样就可以减少 ES 在自动再平衡可用分片时所带来的极大开销。

通过修改参数 delayed_timeout ，可以延长再均衡的时间，可以全局设置也可以在索引级别进行修改:

```json
PUT /_all/_settings 

{
 "settings": {
  "index.unassigned.node_left.delayed_timeout": "5m" 
 }
}
```



### 8.2.3 路由选择

当我们查询文档的时候，Elasticsearch 如何知道一个文档应该存放到哪个分片中呢？它其实是通过下面这个公式来计算出来:

shard = hash(routing) % number_of_primary_shards

routing 默认值是文档的 id，也可以采用自定义值，比如用户 id。

即指的是文档ID的哈希值 模 主分片的数量，得到数是多少就存在哪个主分片上。⚠️

**不带 routing 查询**

在查询的时候因为不知道要查询的数据具体在哪个分片上，所以整个过程分为 2 个步骤

- 分发：请求到达协调节点后，协调节点将查询请求分发到每个分片上。

- 聚合: 协调节点搜集到每个分片上查询结果，在将查询的结果进行排序，之后给用户返回结果。

**带 routing 查询**

查询的时候，可以直接根据 routing 信息定位到某个分配查询，不需要查询所有的分配，经过协调节点排序。

向上面自定义的用户查询，如果 routing 设置为 userid 的话，就可以直接查询出数据来，效率提升很多。



## 8.3写入速度优化

### 8.3.1优化方向

ES的默认配置，是综合了数据可靠性、写入速度、搜索实时性等因素。实际使用时，我们需要根据公司要求，进行偏向性的优化。

针对于搜索性能要求不高，但是对写入要求较高的场景，我们需要尽可能的选择恰当写优化策略。综合来说，可以考虑以下几个方面来提升写索引的性能：

- 加大 Translog Flush ，目的是降低 Iops、Writeblock。

- 增加 Index Refresh 间隔，目的是减少 Segment Merge 的次数。

- 调整 Bulk 线程池和队列。

- 优化节点间的任务分布。

- 优化 Lucene 层的索引建立，目的是降低 CPU 及 IO。



### 8.3.2批量数据提交

ES 提供了 Bulk API 支持批量操作，当我们有大量的写任务时，可以使用 Bulk 来进行批量写入。

通用的策略如下：Bulk 默认设置批量提交的数据量不能超过 100M。数据条数一般是根据文档的大小和服务器性能而定的，但是单次批处理的数据大小应从 5MB～15MB 逐渐增加，当性能没有提升时，把这个数据量作为最大值。



### 8.3.3优化存储设备

ES 是一种密集使用磁盘的应用，在段合并的时候会频繁操作磁盘，所以对磁盘要求较高，当磁盘速度提升之后，集群的整体性能会大幅度提高。



### 8.3.4减少Refresh的次数

Lucene 在新增数据时，采用了延迟写入的策略，默认情况下索引的 refresh_interval 为 1 秒。

Lucene 将待写入的数据先写到内存中，超过 1 秒（默认）时就会触发一次 Refresh，然后 Refresh 会把内存中的的数据刷新到操作系统的文件缓存系统中。

如果我们对搜索的实效性要求不高，可以将 Refresh 周期延长，例如 30 秒。

这样还可以有效地减少段刷新次数，但这同时意味着需要消耗更多的Heap内存。



### 8.3.5加大Flush设置

Flush 的主要目的是把文件缓存系统中的段持久化到硬盘，当 Translog 的数据量达到 512MB 或者 30 分钟时，会触发一次 Flush。

index.translog.flush_threshold_size 参数的默认值是 512MB，我们进行修改。

增加参数值意味着文件缓存系统中可能需要存储更多的数据，所以我们需要为操作系统的文件缓存系统留下足够的空间。



### 8.3.6减少副本的数量

ES 为了保证集群的可用性，提供了 Replicas（副本）支持，然而每个副本也会执行分析、索引及可能的合并过程，所以 Replicas 的数量会严重影响写索引的效率。

当写索引时，需要把写入的数据都同步到副本节点，副本节点越多，写索引的效率就越慢。

如果我们需要大批量进行写入操作，可以先禁止 Replica 复制，设置 index.number_of_replicas: 0 关闭副本。在写入完成后，Replica 修改回正常的状态。



### 8.3.7内存设置

ES 默认安装后设置的内存是 1GB，对于任何一个现实业务来说，这个设置都太小了。

如果是通过解压安装的 ES，则在 ES 安装文件中包含一个 jvm.option 文件，添加如下命令来设置 ES 的堆大小，Xms 表示堆的初始大小，Xmx 表示可分配的最大内存，都是 1GB。

确保 Xmx 和 Xms 的大小是相同的，其目的是为了能够在 Java 垃圾回收机制清理完堆区后不需要重新分隔计算堆区的大小而浪费资源，可以减轻伸缩堆大小带来的压力。

假设你有一个 64G 内存的机器，按照正常思维思考，你可能会认为把 64G 内存都给 ES 比较好，但现实是这样吗， 越大越好？虽然内存对 ES 来说是非常重要的，但是答案是否定的！

因为 ES 堆内存的分配需要满足以下两个原则：

- 不要超过物理内存的 50%：Lucene 的设计目的是把底层 OS 里的数据缓存到内存中。

Lucene 的段是分别存储到单个文件中的，这些文件都是不会变化的，所以很利于缓存，同时操作系	统也会把这些段文件缓存起来，以便更快的访问。

如果我们设置的堆内存过大，Lucene 可用的内存将会减少，就会严重影响降低 Lucene 的全文本查	询性能。

- 堆内存的大小最好不要超过 32GB：在 Java 中，所有对象都分配在堆上，然后有一个 Klass Pointer 指针指向它的类元数据。

这个指针在 64 位的操作系统上为 64 位，64 位的操作系统可以使用更多的内存（2^64）。在 32 位	的系统上为 32 位，32 位的操作系统的最大寻址空间为 4GB（2^32）。

但是 64 位的指针意味着更大的浪费，因为你的指针本身大了。浪费内存不算，更糟糕的是，更大的	指针在主内存和缓存器（例如 LLC, L1等）之间移动数据的时候，会占用更多的带宽。

最终我们都会采用 31 G 设置

-Xms 31g

-Xmx 31g

假设你有个机器有 128 GB 的内存，你可以创建两个节点，每个节点内存分配不超过 32 GB。 也就是说不超过 64 GB 内存给 ES 的堆内存，剩下的超过 64 GB 的内存给 Lucene



## 8.4重要配置★

| 参数名                             | 参数值        | 说明                                                         |
| ---------------------------------- | ------------- | ------------------------------------------------------------ |
| cluster.name                       | elasticsearch | 配置 ES 的集群名称，默认值是ES，建议改成与所存数据相关的名称，ES 会自动发现在同一网段下的集群名称相同的节点 |
| node.name                          | node-1        | 集群中的节点名，在同一个集群中不能重复。节点的名称一旦设置，就不能再改变了。当然，也可以设置成服务器的主机名称，例如 node.name:${HOSTNAME}。 |
| node.master                        | true          | 指定该节点是否有资格被选举成为 Master 节点，默认是 True，如果被设置为 True，则只是有资格成为 Master 节点，具体能否成为 Master 节点，需要通过选举产生。 |
| node.data                          | true          | 指定该节点是否存储索引数据，默认为 True。数据的增、删、改、查都是在 Data 节点完成的。 |
| index.number_of_shards             | 1             | 设置都索引分片个数，默认是 1 片。也可以在创建索引时设置该值，具体设置为多大都值要根据数据量的大小来定。如果数据量不大，则设置成 1 时效率最高 |
| index.number_of_replicas           | 1             | 设置默认的索引副本个数，默认为 1 个。副本数越多，集群的可用性越好，但是写索引时需要同步的数据越多。 |
| transport.tcp.compress             | true          | 设置在节点间传输数据时是否压缩，默认为 False，不压缩         |
| discovery.zen.minimum_master_nodes | 1             | 设置在选举 Master 节点时需要参与的最少的候选主节点数，默认为 1。如果使用默认值，则当网络不稳定时有可能会出现脑裂。合理的数值为(master_eligible_nodes/2)+1，其中 master_eligible_nodes 表示集群中的候选主节点数 |
| discovery.zen.ping.timeout         | 3s            | 设置在集群中自动发现其他节点时 Ping 连接的超时时间，默认为 3 秒。在较差的网络环境下需要设置得大一点，防止因误判该节点的存活状态而导致分片的转移 |





# 9 面试题

## 9.1 为什么要使用Elasticsearch?

系统中的数据，随着业务的发展，时间的推移，将会非常多，而业务中往往采用模糊查询进行数据的搜索，而模糊查询会导致查询引擎放弃索引，导致系统查询数据时都是全表扫描，在百万级别的数据库中，查询效率是非常低下的，而我们使用ES做一个全文索引，将经常查询的系统功能的某些字段，比如说电商系统的商品表中商品名，描述、价格还有id这些字段我们放入ES索引库里，可以提高查询速度。



## 9.2 Elasticsearch的master选举流程？

参考: https://blog.csdn.net/qq_33330687/article/details/105681994

7.x之前选主流程

采用Bully算法，它假定所有节点都有一个唯一的ID，使用该ID对节点进行排序。任何时候的当前Leader都是参与集群的最高ID节点。该算法的优点是易于实现。但是，当拥有最大ID的节点处于不稳定状态的场景下会有问题。例如，Master负载过重而假死，集群拥有第二大ID的节点被选为新主，这时原来的Master恢复，再次被选为新主，然后又假死。
ES 通过推迟选举，直到当前的 Master 失效来解决上述问题，只要当前主节点不挂掉，就不重新选主。但是容易产生脑裂（双主），为此，再通过“法定得票人数过半”解决脑裂问题。

7.x之后选主流程

7.X之后的ES，采用一种新的选主算法，实际上是 Raft 的实现，但并非严格按照 Raft 论文实现，而是做了一些调整。

raft选举过程:

- 选举超时时间:150~300ms的随机数

- term每次选举+1

- 同一个任期内每个节点只能有一张选票

- 选票超过半数则当选为leader

- 当leader被选举后，其它剩余投票将废弃

  

## 9.3 Elasticsearch集群脑裂问题？

**“脑裂”问题可能的成因:**

- 网络问题：集群间的网络延迟导致一些节点访问不到master，认为master挂掉了从而选举出新的master，并对master上的分片和副本标红，分配新的主分片

- 节点负载：主节点的角色既为master又为data，访问量较大时可能会导致ES停止响应造成大面积延迟，此时其他节点得不到主节点的响应认为主节点挂掉了，会重新选取主节点。

- 内存回收：data节点上的ES进程占用的内存较大，引发JVM的大规模内存回收，造成ES进程失去响应。

**脑裂问题解决方案：**

**减少误判：**discovery.zen.ping_timeout节点状态的响应时间，默认为3s，可以适当调大，如果master在该响应时间的范围内没有做出响应应答，判断该节点已经挂掉了。调大参数（如6s，discovery.zen.ping_timeout:6），可适当减少误判。

**选举触发**: discovery.zen.minimum_master_nodes:1

> 7.x之前版本:
>
> 该参数是用于控制选举行为发生的最小集群主节点数量。当备选主节点的个数大于等于该参数的值，	且备选主节点中有该参数个节点认为主节点挂了，进行选举。官方建议为（n/2）+1，n为主节点个数	（即有资格成为主节点的节点个数）
>
> 7.x之后版本:
>
> 不需要手动配置，系统自己会维护最小集群节点数

**角色分离**：即master节点与data节点分离，限制角色

主节点配置为：node.master: true node.data: false

从节点配置为：node.master: false node.data: true



## 9.4 Elasticsearch索引文档的流程？

- 协调节点默认使用文档ID参与计算（也支持通过routing），以便为路由提供合适的分片：

**shard = hash(document_id) % (num_of_primary_shards)**

- 当分片所在的节点接收到来自协调节点的请求后，会将请求写入到Memory Buffer，然后定时（默认是每隔1秒）写入到Filesystem Cache，这个从Memory Buffer到Filesystem Cache的过程就叫做refresh；

- 当然在某些情况下，存在Momery Buffer和Filesystem Cache的数据可能会丢失，ES是通过translog的机制来保证数据的可靠性的。其实现机制是接收到请求后，同时也会写入到translog中，当Filesystem cache中的数据写入到磁盘中时，才会清除掉，这个过程叫做flush；

- 在flush过程中，内存中的缓冲将被清除，内容被写入一个新段，段的fsync将创建一个新的提交点，并将内容刷新到磁盘，旧的translog将被删除并开始一个新的translog。

- flush触发的时机是定时触发（默认30分钟）或者translog变得太大（默认为512M）时；



## 9.5 **Elasticsearch更新和删除文档的流程？**

- 删除和更新也都是写操作，但是Elasticsearch中的文档是不可变的，因此不能被删除或者改动以展示其变更；

- 磁盘上的每个段都有一个相应的.del文件。当删除请求发送后，文档并没有真的被删除，而是在.del文件中被标记为删除。该文档依然能匹配查询，但是会在结果中被过滤掉。当段合并时，在.del文件中被标记为删除的文档将不会被写入新段。

- 在新的文档被创建时，Elasticsearch会为该文档指定一个版本号，当执行更新时，旧版本的文档在.del文件中被标记为删除，新版本的文档被索引到一个新段。旧版本的文档依然能匹配查询，但是会在结果中被过滤掉。

 

## 9.6 Elasticsearch搜索的流程？

- 搜索被执行成一个两阶段过程，我们称之为 Query Then Fetch；

- 在初始查询阶段时，查询会广播到索引中每一个分片拷贝（主分片或者副本分片）。 每个分片在本地执行搜索并构建一个匹配文档的大小为 from + size 的优先队列。PS：在搜索的时候是会查询Filesystem Cache的，但是有部分数据还在Memory Buffer，所以搜索是近实时的。

- 每个分片返回各自优先队列中 所有文档的 ID 和排序值 给协调节点，它合并这些值到自己的优先队列中来产生一个全局排序后的结果列表。

- 接下来就是取回阶段，协调节点辨别出哪些文档需要被取回并向相关的分片提交多个 GET 请求。每个分片加载并丰富文档，如果有需要的话，接着返回文档给协调节点。一旦所有的文档都被取回了，协调节点返回结果给客户端。



## 9.7 Elasticsearch 在部署时，对 Linux 的设置有哪些优化方法？

- 64 GB 内存的机器是非常理想的， 但是32 GB 和16 GB 机器也是很常见的。少于8 GB 会适得其反。

- 如果你要在更快的 CPUs 和更多的核心之间选择，选择更多的核心更好。多个内核提供的额外并发远胜过稍微快一点点的时钟频率。

- 如果你负担得起 SSD，它将远远超出任何旋转介质。 基于 SSD 的节点，查询和索引性能都有提升。如果你负担得起，SSD 是一个好的选择。

- 即使数据中心们近在咫尺，也要避免集群跨越多个数据中心。绝对要避免集群跨越大的地理距离。

- 通过设置gateway.recover_after_nodes、gateway.expected_nodes、gateway.recover_after_time可以在集群重启的时候避免过多的分片交换，这可能会让数据恢复从数个小时缩短为几秒钟。

- 不要随意修改垃圾回收器（CMS）和各个线程池的大小。

**补充：索引阶段性能提升方法**

- 使用批量请求并调整其大小：每次批量数据 5–15 MB 大是个不错的起始点。

- 如果你的搜索结果不需要近实时的准确度，考虑把每个索引的index.refresh_interval 改到30s。

- 如果你在做大批量导入，考虑通过设置index.number_of_replicas: 0 关闭副本。

 

## 9.8 GC方面，在使用Elasticsearch时要注意什么？

Ø 倒排词典的索引需要常驻内存，无法GC，需要监控data node上segment memory增长趋势。

Ø 各类缓存，field cache, filter cache, indexing cache, bulk queue等等，要设置合理的大小，并且要应该根据最坏的情况来看heap是否够用，也就是各类缓存全部占满的时候，还有heap空间可以分配给其他任务吗？避免采用clear cache等“自欺欺人”的方式来释放内存。

Ø 避免返回大量结果集的搜索与聚合。确实需要大量拉取数据的场景，可以采用scan & scroll api来实现。

Ø cluster stats驻留内存并无法水平扩展，超大规模集群可以考虑分拆成多个集群通过tribe node连接。

Ø 想知道heap够不够，必须结合实际应用场景，并对集群的heap使用情况做持续的监控。

 

## 9.9 在并发情况下，Elasticsearch如果保证读写一致？

- 可以通过版本号使用乐观并发控制，以确保新版本不会被旧版本覆盖，由应用层来处理具体的冲突；

- 另外对于写操作，一致性级别支持quorum/one/all，默认为quorum，即只有当大多数分片可用时才允许写操作。但即使大多数可用，也可能存在因为网络等原因导致写入副本失败，这样该副本被认为故障，分片将会在一个不同的节点上重建。

- 对于读操作，可以设置replication为sync(默认)，这使得操作在主分片和副本分片都完成后才会返回；如果设置replication为async时，也可以通过设置搜索请求参数_preference为primary来查询主分片，确保文档是最新版本。

 

## 9.10 如何监控 Elasticsearch 集群状态？

elasticsearch-head插件

通过 Kibana 监控 Elasticsearch。你可以实时查看你的集群健康状态和性能，也可以分析过去的集群、索引和节点指标

```shell
GET /_cluster/health
```



## 9.11 Elasticsearch中的集群、节点、索引、文档、类型是什么？

- 集群是一个或多个节点（服务器）的集合，它们共同保存您的整个数据，并提供跨所有节点的联合索引和搜索功能。群集由唯一名称标识，默认情况下为“elasticsearch”。此名称很重要，因为如果节点设置为按名称加入群集，则该节点只能是群集的一部分。

- 节点是属于集群一部分的单个服务器。它存储数据并参与群集索引和搜索功能。

- 索引就像关系数据库中的“数据库”。它有一个定义多种类型的映射。索引是逻辑名称空间，映射到一个或多个主分片，并且可以有零个或多个副本分片。 MySQL =>数据库 Elasticsearch =>索引

- 文档类似于关系数据库中的一行。不同之处在于索引中的每个文档可以具有不同的结构（字段），但是对于通用字段应该具有相同的数据类型。 MySQL => Databases => Tables => Columns / Rows Elasticsearch => Indices => Types =>具有属性的文档

- 类型是索引的逻辑类别/分区，其语义完全取决于用户。

  

## 9.12 Elasticsearch中的倒排索引是什么？

倒排索引是搜索引擎的核心。搜索引擎的主要目标是在查找发生搜索条件的文档时提供快速搜索。ES中的倒排索引其实就是lucene的倒排索引，区别于传统的正向索引，倒排索引会再存储数据时将关键词和数据进行关联，保存到倒排表中，然后查询时，将查询内容进行分词后在倒排表中进行查询，最后匹配数据即可。
