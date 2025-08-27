ik工作问题总结

遍历JSONArray

问题:数组内的元素均为Object类型 , 需要转换为JSONObject进行处理

![1708306858005](C:\Users\11860\AppData\Roaming\Typora\typora-user-images\1708306858005.png)

解决方式:

1.使用Java8流式处理

![1708307110131](C:\Users\11860\AppData\Roaming\Typora\typora-user-images\1708307110131.png)

2.使用hutool包中的Convert方法进行转换

![1708307147076](C:\Users\11860\AppData\Roaming\Typora\typora-user-images\1708307147076.png)



Date转LocalDate

使用`java.util.Date`的`toInstant()`方法将`Date`对象转换为`Instant`对象，再使用`Instant`对象的`atZone()`方法将其转换为`ZonedDateTime`对象，最后使用`ZonedDateTime`对象的`toLocalDate()`方法将其转换为`LocalDate`对象。

```java
// 创建一个java.util.Date对象
Date date = new Date();
// 将java.util.Date转换为java.time.LocalDate
LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
```



计算不同年不同月两个日期所差天数

```java
ChronoUnit.DAYS.between(date1, date2)
```



3.mysql中的排序函数

对某一字段的指定顺序进行排序 

**field() 函数**：是将查询的结果集按照指定顺序排序

**格式**： `FIELD(str,str1,str2,str3,...)`

**什么时候用**： 想让某几个特定的字段值放在最后，用field()函数

**解释**： `str`是字段名字，[字符串](https://so.csdn.net/so/search?q=字符串&spm=1001.2101.3001.7020)`str1,str2,str3`等等，是该字段的值

**函数意思**： 匹配到`str1`，将其放到结果集最后返回

**详细解析**： 当字段值没有匹配到str1，str2或者str3的时候，按照正常排序；当匹配到这些的时候，会把没有匹配的值放到最前面，匹配到的放到后边，并且以写的顺序排序[返回结果](https://so.csdn.net/so/search?q=返回结果&spm=1001.2101.3001.7020)集。

**场景**： [数据库](https://so.csdn.net/so/search?q=数据库&spm=1001.2101.3001.7020)有字段model，代表手机型号，值有很多，和更多型号；现在根据model字段排序，查询结果集中，’‘更多型号’’ 必须放最后。

```
使用示例:
比如approval_status中的字段值为0,1,2,3,4,5 想让顺序为2,1,3,4,5,0
ORDER BY FIELD(pid.approval_status,2,1,3,4,5,0)
```



4.LambdaQueryWrapper传值

![1716790286228](C:\Users\11860\AppData\Roaming\Typora\typora-user-images\1716790286228.png)

在Mapper文件中

![1716791237510](C:\Users\11860\AppData\Roaming\Typora\typora-user-images\1716791237510.png)

在xml文件中

![1716791269148](C:\Users\11860\AppData\Roaming\Typora\typora-user-images\1716791269148.png)