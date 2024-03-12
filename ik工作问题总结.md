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

