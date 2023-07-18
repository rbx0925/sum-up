# Es-直方图聚合-date_histogram

# 1、背景

此处来简单学习一下 elasticsearch 的 date_histogram直方图聚合。它和普通的直方图histogram聚合差不多，但是date_histogram只可于 日期或日期范围 类型的值一起使用。

# 2、bucket_key如何计算

1. 假设我们存在如下时间 2022-11-29 23:59:59。
2. 在 es中时间为 2022-11-29 23:59:59 +0000，因为上方的时间没有时区，所以会自动加上0时区，对应的时间戳为 1669766399000
3. 此处假设以 1d 为单位来聚合
4. 聚合统计中 time_zone的值为+0800
5. bucket_key计算公式为 bucket_key = localToUtc(Math.floor(utcToLocal(value) / interval) * interval))

计算步骤如下：(此处是我自己的理解)

- **utcToLocal(value)** = 1669766399000(utc的值) + 8*60*60*1000(time_zone +8的值) = 1669795199000
- **Math.floor(utcToLocal(value) / interval) * interval)** = Math.floor(1669795199000 / (24*60*60*1000)) * (24*60*60*1000) = 1669766400000
- **localToUtc(...)**=1669766400000-86060*1000=1669737600000
- **key_as_string**=utc时间1669737600000转换成东八区时间展示为=2022/11/30 00:00:00

![bucket_key的计算](https://img-blog.csdnimg.cn/a0002c4c124440ba9e959f16897b2bd4.png)

# 3、前置知识

1. 日期(`date`)类型的字段在 `es`中是以 `long`类型的值保存的。
2. `es`中默认 默认的时区是 `0时区`。
3. 如果我们有一个东八区的时间，那么在es中是如何存储的呢？

- 假设存在如下mapping

- 如果我们此时存在 如下 `东八区`时间 `2022-11-29 12:12:12`，那么在 es 会存储为 `2022-11-29 12:12:12 +0000` 对应的时间戳，为什么会加上`+0000`，因为我们自己的时间字符串中没有时区，就会加上默认的0时区。

  ```
  "invoked_time": {
    "type": "date",
    "format": ["yyyy-MM-dd HH:mm:ss"]
  }
  ```

# 4、日历和固定时间间隔

既然我们是根据时间来进行聚合，那么必然就会涉及到这么一个问题。假设以天为单位来聚合，那么1天到底是固定的24小时呢，还是可变的呢? 因为存在时区的关系，在有的国家，在某些时区下，一天就不一定是24个小时。因此在es中提供了**calendar-aware time intervals**, 和 **fixed time intervals**. 两种类型。

## 4.1 Calendar intervals 日历间隔

日历感知间隔使用calendar_interval参数配置。 它可以自动感应到日历中的时区变化。它的单位只能是单数，不可是复数，比如2d就是错误的。

日历间隔 可用的单位为：**分钟 (1m)、小时 (1h)、天 (1d)、星期 (1w)、月 (1M)、季度 (1q)、年 (1y)**



举个例子：1m 是从何时开始的，何时结束的？.
所有的分钟都从00秒开始。一分钟是指定时区中第一分钟的00秒和下一分钟的00秒之间的时间间隔，用于补偿任何介于其间的闰秒，因此整点后的分钟数和秒数在开始和结束时是相同的。

## 4.2 Fixed intervals 固定间隔

固定间隔使用**fixed_interval**参数进行配置。

与日历感知间隔相比，固定间隔是固定数量的SI单位，无论它们落在日历的哪个位置，都不会偏离。一秒总是由1000ms组成。这允许以支持的单位的任意倍数指定固定间隔。但是，这意味着固定间隔不能表示其他单位，例如月，因为一个月的持续时间不是固定的数量。尝试指定月或季度等日历间隔将引发异常。

固定间隔 可用的单位为：
**毫秒 (ms)**
**秒 (s)**
          定义为每个1000毫秒
**分钟 (m)**
          所有分钟都从00秒开始。 定义为每个60秒(60,000毫秒)
**小时 (h)**
          所有小时都从00分00秒开始。 定义为每60分钟(3,600,000毫秒)
**天 (d)**
          所有天都在尽可能早的时间开始，通常是00:00:00(午夜)。 定义为24小时(86,400,000毫秒)

# 5、数据准备

```
PUT /index_api_invoked_time
{
  "settings": {
    "number_of_shards": 1
  },
  "mappings": {
    "properties": {
      "id": {
        "type": "long"
      },
      "api": {
        "type": "keyword"
      },
      "invoked_time": {
        "type": "date",
        "format": ["yyyy-MM-dd HH:mm:ss"]
      }
    }
  }
}
```

```
PUT /index_api_invoked_time/_bulk
{"index":{"_id":1}}
{"api":"/user/infos","invoked_time": "2022-11-26 00:00:00"}
{"index":{"_id":2}}
{"api":"/user/add"}
{"index":{"_id":3}}
{"api":"/user/update","invoked_time": "2022-11-26 23:59:59"}
{"index":{"_id":4}}
{"api":"/user/list","invoked_time": "2022-11-27 00:00:00"}
{"index":{"_id":5}}
{"api":"/user/export","invoked_time": "2022-11-29 23:59:59"}
{"index":{"_id":6}}
{"api":"/user/detail","invoked_time": "2022-12-01 01:00:00"}
```

# 6、聚合案例

```
POST /index_api_invoked_time/_search 
{
  "size": 0, 
  "aggregations": {
    "agg_01": {
      "date_histogram": {
        "field": "invoked_time",
        "calendar_interval": "1d",
        "min_doc_count": 0,
        "missing": "2022-11-27 23:59:59",
        "time_zone": "+08:00",
        "offset":"+10h",
        "extended_bounds": {
          "min": "2022-11-26 10:00:00",
          "max": "2022-12-03 10:00:00"
        }
      }
    }
  }
}
```

```java
@Test
@DisplayName("日期直方图聚合")
public void test01() throws IOException {
    SearchRequest request = SearchRequest.of(searchRequest ->
            searchRequest.index("index_api_invoked_time")
                    .size(0)
                    .aggregations("agg_01", agg ->
                            agg.dateHistogram(dateAgg ->
                                    // 聚合的字段
                                    dateAgg.field("invoked_time")
                                            // 聚合的单位，日历感知 单位为天，此时的一天不一定为24小时，因为夏令时时，有些国家一天可能只有23个小时
                                            .calendarInterval(CalendarInterval.Day)
                                            // 固定间隔， 此处可以指定 1天就是24小时
                                            // .fixedInterval()
                                            // 如果聚合的桶中，没有文档也返回
                                            .minDocCount(0)
                                            // 对于文档中，聚合字段缺失，此处给一个默认值，默认情况是此文档不参与聚合
                                            .missing(DateTime.of("2022-11-27 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                            // 时区
                                            .timeZone("+08:00")
                                            // 偏移，偏移是在时间在对应的时区调整之后，再去偏移
                                            .offset(time -> time.time("+10h"))
                                            // 如果返回的桶数据不在这个边界中，则给默认值，不会对数据进行过滤。
                                            .extendedBounds(bounds ->
                                                    bounds.min(FieldDateMath.of(f -> f.expr("2022-11-26 10:00:00")))
                                                            .max(FieldDateMath.of(f -> f.expr("2022-12-03 10:00:00")))
                                            )
                            )
                    )
    );
    System.out.println("request: " + request);
    SearchResponse<String> response = client.search(request, String.class);
    System.out.println("response: " + response);
}
```

![聚合结果](https://img-blog.csdnimg.cn/027c846787d14dccb56b0b806b0bd4e7.png)

# 7、参考文档

https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations-bucket-datehistogram-aggregation.html#date-histogram-missing-value
https://www.pipiho.com/es/7.7/cn/search-aggregations-bucket-datehistogram-aggregation.html