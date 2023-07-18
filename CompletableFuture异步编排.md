## 第7章-CompletableFuture异步编排

 **学习目标：**

- CompletableFuture异步任务应用场景
- 掌握CompletableFuture相关API的应用
- 基于CompletableFuture+自定义线程池实现优化商品数据接口调用
- 基于CompletableFuture实现首页商品分类

# 1、CompletableFuture异步编排

问题：查询商品详情页的逻辑非常复杂，数据的获取都需要远程调用，必然需要花费更多的时间。

假如商品详情页的每个查询，需要如下标注的时间才能完成

1. 获取sku的基本信息+sku的图片信息	1s
2. 获取商品所属三级分类	0.5s 
3. 获取spu的所有销售属性	1s 
4. 商品sku价格	0.5s
5. 获取商品海报列表 0.5s
6. 获取商品Sku平台属性以及值 0.5s
7. ......

那么，用户需要4s后才能看到商品详情页的内容。很显然是不能接受的。如果有多个线程同时完成这4步操作，也许只需要1.5s即可完成响应。

## 1.1 CompletableFuture介绍

Future是Java 5添加的接口，用来描述一个异步计算的结果。你可以使用isDone方法检查计算是否完成，或者使用get阻塞住调用线程，直到计算完成返回结果，你也可以使用cancel方法停止任务的执行。

在Java 8中, 新增加了一个包含50个方法左右的类: CompletableFuture，提供了非常强大的Future的扩展功能，可以帮助我们简化异步编程的复杂性，提供了函数式编程的能力，可以通过回调的方式处理计算结果，并且提供了转换和组合CompletableFuture的方法。

CompletableFuture类实现了Future接口，所以你还是可以像以前一样通过get方法阻塞或者轮询的方式获得结果，但是这种方式不推荐使用。

CompletableFuture和FutureTask同属于Future接口的实现类，都可以获取线程的执行结果。

![img](assets/day07/wps1.jpg) 

## 1.2 创建异步对象

CompletableFuture 提供了四个静态方法来创建一个异步操作。

![img](assets/day07/wps2.jpg) 

没有指定Executor的方法会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码。如果指定线程池，则使用指定的线程池运行。以下所有的方法都类同。

-  runAsync方法不支持返回值。
-  supplyAsync可以支持返回值。

```java
package com.atguigu.gmall;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: atguigu
 * @create: 2023-02-28 15:40
 */
public class CompletableFutureTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.创建异步操作对象 CompletableFuture对象
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 异步任务执行,无返回值");
        });


        //2.不需要获取线程执行结果
        //future.get();
        //future.join();

        //3.需要获取线程执行结果
        CompletableFuture<String> futureResult = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 异步任务执行,有返回值");
            return "atguigu";
        });
        String s = futureResult.get();
        System.out.println(s);
    }
}
```

## 1.3 计算完成时回调方法

当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action。主要是下面的方法：

![img](assets/day07/wps3.jpg) 

- whenComplete可以处理正常或异常的计算结果

- exceptionally处理异常情况。BiConsumer<? super T,? super Throwable>可以定义处理业务

 

**whenComplete 和 whenCompleteAsync 的区别**：

whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。

whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。

方法不以Async结尾，意味着Action使用相同的线程执行，而Async可能会使用其他线程执行（如果是使用相同的线程池，也可能会被同一个线程选中执行）

代码示例：

```java
package com.atguigu.gmall;

import javax.sound.midi.Soundbank;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步任务执行后,指定回调方法
 *
 * @author: atguigu
 * @create: 2023-02-28 15:40
 */
public class CompletableFutureTestAction {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.创建异步操作对象 CompletableFuture对
        //2.需要获取线程执行结果
        CompletableFuture<String> futureResult = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 异步任务执行,有返回值");
            int i = 1 / 0;
            return "atguigu";
        });
        //异步操作任务执行完毕后,继续执行其他业务 r:线程异步执行结果  e:如果异步任务发生异常
        //futureResult.whenCompleteAsync((r, e) -> {
        //    System.out.println(Thread.currentThread().getName() + "\t 异步任务回调方法:"+r);
        //    System.out.println(Thread.currentThread().getName() + "\t" + e);
        //});
        //线程任务异常回调 e:异常信息
        CompletableFuture<String> exceptionally = futureResult.exceptionally((e) -> {
            System.out.println(Thread.currentThread().getName() + "\t 异步任务回调方法:" + e);
            return "6666";
        });

        String s = exceptionally.get();
        System.out.println(s);
    }

}
```

## 1.4 线程串行化与并行化方法

thenApply 方法：当一个线程依赖另一个线程时，获取上一个任务返回的结果，并返回当前任务的返回值。

![img](assets/day07/wps4.jpg) 

thenAccept方法：消费处理结果。接收任务的处理结果，并消费处理，无返回结果。

![img](assets/day07/wps5.jpg) 

thenRun方法：只要上面的任务执行完成，就开始执行thenRun，只是处理完任务后，执行 thenRun的后续操作

![img](assets/day07/wps6.jpg) 

带有Async默认是异步执行的。这里所谓的异步指的是不在当前线程内执行。

 

Function<? super T,? extends U> 

T：上一个任务返回结果的类型 

U：当前任务的返回值类型

代码演示：

```java
package com.atguigu.gmall;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: atguigu
 * @create: 2023-03-01 09:07
 */
public class CompletableFutureReturnAccpectAsync {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1.创建线程A异步操作对象-需求线程A有返回结果
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t线程A执行了");
            return "AAA";
        });

        //2.基于线程A异步操作对象 构建线程B的异步操作对象 需求:B线程需要A线程返回结果,但是B线程没有结果返回

        CompletableFuture<Void> futureB = futureA.thenAcceptAsync((futureAResult -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t线程B执行了,获取到线程A结果:" + futureAResult);
        }));

        CompletableFuture.anyOf(futureA, futureB);

        System.out.println("主线程执行");
        //futureB.get();
    }
}

```

 

```java
package com.atguigu.gmall;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: atguigu
 * @create: 2023-03-01 09:07
 */
public class CompletableFutureReturnApplyAsync {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1.创建线程A异步操作对象-需求线程A有返回结果
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t线程A执行了");
            return "AAA";
        });

        //2.基于上面A异步对象创建线程B的异步操作对象-需求:需要获取A线程结果,在B线程中返回新的结果
        CompletableFuture<String> futureB = futureA.thenApplyAsync(resultA -> {
            System.out.println(Thread.currentThread().getName() + "\t线程B执行了,得到A的结果:" + resultA);
            return "BBB";
        });

        //3.基于上面A异步对象创建线程C的异步操作对象-需求:需要获取A线程结果,在C线程中返回新的结果
        CompletableFuture<String> futureC = futureA.thenApplyAsync(resultA -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t线程C执行了,得到A的结果:" + resultA);
            return "CCC";
        });


        CompletableFuture.allOf(futureA, futureB, futureC).join();
        //CompletableFuture.anyOf(futureA, futureB, futureC);

        //4.分别获取B线程 C线程结果
        //String b = futureB.get();
        //System.out.println("线程B执行结果:"+b);
        //String c = futureC.get();
        //System.out.println("线程C执行结果:"+c);

    }
}
```

## 1.5  多任务组合

```java
public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs);
public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs);
```

- allOf：等待所有任务完成
- anyOf：只要有一个任务完成

```java
package com.atguigu.gmall;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步任务 串行，并行
 * @author: atguigu
 * @create: 2023-01-03 15:36
 */
public class CompletableFutureDemo1 {

    /**
     * A 线程有计算结果
     * B 线程依赖A线程计算结果，执行B线程任务
     * C 线程依赖A线程计算结果，执行C线程任务
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       //1.创建异步任务对象 CompletableFuture  A任务需要返回值
        CompletableFuture<Long> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A任务执行");
            return 29L;
        });

        //2.基于上面对象 构建 B任务对象
        CompletableFuture<String> futureB = futureA.thenApplyAsync((aResult) -> {
            try {
                Thread.sleep(5000);
                System.out.println("B任务执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "skuID为" + aResult + "的销售属性";
        });


        //3.基于上面对象 构建 C任务对象
        CompletableFuture<String> futureC = futureA.thenApplyAsync((aResult) -> {
            try {
                Thread.sleep(11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("C任务执行");
            return "skuID为" + aResult + "的海报列表";
        });

        //futureA.join();
        //futureB.join();
        //futureC.join();

        //Long a = futureA.get();
        //System.out.println(a);
        //String b = futureB.get();
        //System.out.println(b);
        //String c = futureC.get();
        //System.out.println(c);


        CompletableFuture.anyOf(futureA, futureB, futureC).join();
        CompletableFuture.allOf(futureA, futureB, futureC).join();
        System.out.println("执行后续业务代码");
    }
}
```

## 1.6  优化商品详情页

### 1.6.1. 自定义线程池

在`service-item`模块中新建包名：com.atguigu.gmall.item.config 新增线程池配置类：ThreadPoolConfig

```java
package com.atguigu.gmall.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 全局自定义线程池配置
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        //动态获取服务器核数
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                processors+1, // 核心线程个数 io:2n ,cpu: n+1  n:内核数据
                processors+1,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        //  返回线程池对象
        return threadPoolExecutor;
    }
}
```

### 1.6.2. 优化商品详情数据

 对`service-item`模块中`ItemServiceImpl`类中的getBySkuId方法进行优化

```java
@Autowired
private ThreadPoolExecutor executor;
/**
 * 汇总商品详情页所需数据
 *
 * @param skuId - **skuInfo**：当前商品SKU信息包含SKU图片列表
 *              - **categoryView**：当前商品所属的分类信息（包含三级）
 *              - **price**：当前商品最新价格
 *              - **spuPosterList**：当前商品海报图片集合
 *              - **skuAttrList**：当前商品平台属性及属性值集合--- 规格与参数
 *              - **spuSaleAttrList**：当前商品销售属性集合选中效果
 *              - **valuesSkuJson**：切换SKU转换SKU商品json字符串信息
 * @param skuId
 * @return
 */
@Override
public Map<String, Object> getItemAllData(Long skuId) {
    HashMap<String, Object> data = new HashMap<>();
    //0.判断用户要查询的商品是否不存在,如果不存在直接返回null TODO 开发阶段为了方便测试,暂时注释,测试阶段再放开
    //RBloomFilter<Long> bloomFilter = redissonClient.getBloomFilter(RedisConst.SKU_BLOOM_FILTER);
    //if (!bloomFilter.contains(skuId)) {
    //    return data;
    //}

    //0.supplyAsync构建有返回值异步操作对象
    CompletableFuture<SkuInfo> skuInfoCompletableFuture = CompletableFuture.supplyAsync(() -> {
        //1.远程调用商品服务-根据skuID查询商品sku信息
        SkuInfo skuInfo = productFeignClient.getSkuInfoAndImages(skuId);
        if (skuInfo != null) {
            data.put("skuInfo", skuInfo);
        }
        return skuInfo;
    }, executor);

    //2.根据商品Sku三家分类ID查询分类信息
    CompletableFuture<Void> categoryViewCompletableFuture = skuInfoCompletableFuture.thenAcceptAsync((skuInfo -> {
        BaseCategoryView categoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
        if (categoryView != null) {
            data.put("categoryView", categoryView);
        }
    }), executor);

    //3.根据SKuID查询价格
    CompletableFuture<Void> priceCompletableFuture = CompletableFuture.runAsync(() -> {
        BigDecimal price = productFeignClient.getSkuPrice(skuId);
        if (price != null) {
            data.put("price", price);
        }
    }, executor);


    //4.根据Sku所属的SpuID查询海报图片列表
    CompletableFuture<Void> spuPosterListCompletableFuture = skuInfoCompletableFuture.thenAcceptAsync(skuInfo -> {
        List<SpuPoster> spuPosterList = productFeignClient.getSpuPosterBySpuId(skuInfo.getSpuId());
        if (!CollectionUtils.isEmpty(spuPosterList)) {
            data.put("spuPosterList", spuPosterList);
        }
    }, executor);

    //5.根据SkuID查询商品平台属性列表
    CompletableFuture<Void> skuAttrListCompletableFuture = CompletableFuture.runAsync(() -> {
        List<BaseAttrInfo> attrList = productFeignClient.getAttrList(skuId);
        if (!CollectionUtils.isEmpty(attrList)) {
            data.put("skuAttrList", attrList);
        }
    }, executor);

    //6.根据spuId,skuId查询当前商品销售属性(带选中效果)
    CompletableFuture<Void> spuSaleAttrListCompletableFuture = skuInfoCompletableFuture.thenAcceptAsync(skuInfo -> {
        List<SpuSaleAttr> listCheckBySku = productFeignClient.getSpuSaleAttrListCheckBySku(skuId, skuInfo.getSpuId());
        if (!CollectionUtils.isEmpty(listCheckBySku)) {
            data.put("spuSaleAttrList", listCheckBySku);
        }
    }, executor);

    //7.切换SKU转换SKU商品json字符串信息
    CompletableFuture<Void> valuesSkuJsonCompletableFuture = skuInfoCompletableFuture.thenAcceptAsync(skuInfo -> {
        String valuesSkuJson = productFeignClient.getSkuValueIdsMap(skuInfo.getSpuId());
        if (StringUtils.isNotBlank(valuesSkuJson)) {
            data.put("valuesSkuJson", valuesSkuJson);
        }
    }, executor);

    //8.组合多个异步任务对象 ,必须等待所有任务执行完毕
    CompletableFuture.allOf(
            skuAttrListCompletableFuture,
            categoryViewCompletableFuture,
            spuPosterListCompletableFuture,
            spuSaleAttrListCompletableFuture,
            valuesSkuJsonCompletableFuture,
            priceCompletableFuture,
            skuAttrListCompletableFuture
    ).join();
    return data;
}
```

# 2、首页商品分类实现

![img](assets/day07/wps7.jpg) 

前面做了商品详情，我们现在来做首页分类，我先看看京东的首页分类效果，我们如何实现类似效果：

![img](assets/day07/wps8.jpg) 

思路：

1，首页属于并发量比较高的访问页面，我看可以采取页面静态化方式实现，或者把数据放在缓存中实现

2，我们把生成的静态文件可以放在nginx访问或者放在web-index模块访问

## 2.1  修改pom.xml

在`web-all`模块中新增商品服务依赖

```xml
<dependency>
    <groupId>com.atguigu.gmall</groupId>
    <artifactId>service-product-client</artifactId>
    <version>1.0</version>
</dependency>
```

## 2.2  封装数据接口

由于商品分类信息在service-product模块，我们在该模块封装数据，数据结构为父子层级

商品分类保存在base_category1、base_category2和base_category3表中，由于需要静态化页面，我们需要一次性加载所有数据，前面我们使用了一个视图base_category_view，所有我从视图里面获取数据，然后封装为父子层级

数据结构如下：json 数据结构

```json
[
    {
        "index":1,     #序号
        "categoryName":"图书、音像、电子书刊",   #一级分类名称
        "categoryId":1,                       #一级分类ID
        "categoryChild":[                     #当前一级分类包含的二级分类集合
            {
                "categoryName":"电子书刊",     #二级分类名称
                "categoryId":1,               #二级分类ID
                "categoryChild":[             #当前二级分类包含的三级分类集合
                    {
                        "categoryName":"电子书",#三级分类名称
                        "categoryId":1         #三级分类ID
                    },
                    {
                        "categoryName":"网络原创",
                        "categoryId":2
                    }
                ]
            }
        ]
    },
    {
        "index":2,
        "categoryName":"手机",
        "categoryId":2,
        "categoryChild":[
            {
                "categoryName":"手机通讯",
                "categoryId":13,
                "categoryChild":[
                    {
                        "categoryName":"手机",
                        "categoryId":61
                    }
                ]
            },
            {
                "categoryName":"运营商",
                "categoryId":14
            },
            {
                "categoryName":"手机配件",
                "categoryId":15
            }
        ]
    }
]
```

###  2.2.1  控制器

> YAPI接口地址：http://192.168.200.128:3000/project/11/interface/api/651

`service-product`模块中ProductApiController

```java
/**
 * 查询所有分类列表 分类嵌套结果:一级分类分类对象中包含二级分类集合;在二级分类对象中包含三级分类集合
 * @return
 */
@GetMapping("/inner/getBaseCategoryList")
public List<JSONObject> getBaseCategoryList(){
    return baseCategoryViewService.getBaseCategoryList();
}
```

 

### 2.2.2 BaseCategoryViewService接口

```java
package com.atguigu.gmall.product.service;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmall.product.model.BaseCategoryView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: atguigu
 * @create: 2023-02-25 10:14
 */
public interface BaseCategoryViewService extends IService<BaseCategoryView> {
    /**
     * 查询所有分类列表 分类嵌套结果:一级分类分类对象中包含二级分类集合;在二级分类对象中包含三级分类集合
     * @return
     */
    List<JSONObject> getBaseCategoryList();
}

```

 

### 2.2.3 BaseCategoryViewServiceImpl实现类

```java
package com.atguigu.gmall.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmall.common.cache.GmallCache;
import com.atguigu.gmall.product.mapper.BaseCategoryViewMapper;
import com.atguigu.gmall.product.model.BaseCategoryView;
import com.atguigu.gmall.product.service.BaseCategoryViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: atguigu
 * @create: 2023-02-25 10:14
 */
@Service
public class BaseCategoryViewServiceServiceImpl extends ServiceImpl<BaseCategoryViewMapper, BaseCategoryView> implements BaseCategoryViewService {


    /**
     * 查询所有分类列表 分类嵌套结果:一级分类分类对象中包含二级分类集合;在二级分类对象中包含三级分类集合
     *
     * @return
     */
    @Override
    @GmallCache(prefix = "baseCategoryList")
    public List<JSONObject> getBaseCategoryList() {
        //1.构建所有一级分类集合对象
        List<JSONObject> resultList = new ArrayList<>();

        //2.查询分类视图获取所有的分类集合
        List<BaseCategoryView> allCategoryList = this.list();

        //3.对所有分类集合进行分组:根据一级分类ID分组
        Map<Long, List<BaseCategoryView>> category1ListMap =
                allCategoryList.stream().collect(Collectors.groupingBy(BaseCategoryView::getCategory1Id));
        int index = 1;
        //4.遍历分组后Map处理一级分类数据
        for (Map.Entry<Long, List<BaseCategoryView>> category1Entry : category1ListMap.entrySet()) {
            JSONObject category1 = new JSONObject();
            //4.1 获取一级分类ID
            Long category1Id = category1Entry.getKey();
            //4.2 获取一级分类名称
            String category1Name = category1Entry.getValue().get(0).getCategory1Name();
            category1.put("index", index++);
            category1.put("categoryId", category1Id);
            category1.put("categoryName", category1Name);

            //5.处理当前一级分类中二级分类
            Map<Long, List<BaseCategoryView>> category2ListMap = category1Entry.getValue().stream()
                    .collect(Collectors.groupingBy(BaseCategoryView::getCategory2Id));
            //5.1 遍历二级分类Map 获取二级分类ID以及名称

            List<JSONObject> category2JsonList = new ArrayList<>();
            for (Map.Entry<Long, List<BaseCategoryView>> category2Entry : category2ListMap.entrySet()) {
                //5.1.1 构建二级分类JSon对象
                JSONObject category2 = new JSONObject();
                //5.1.2 获取二级分类ID以及名称
                Long category2Id = category2Entry.getKey();
                String category2Name = category2Entry.getValue().get(0).getCategory2Name();
                //5.1.3 封装二级分类JSON对象
                category2.put("categoryId", category2Id);
                category2.put("categoryName", category2Name);
                category2JsonList.add(category2);

                //6.处理当前二级分类中包含三级分类
                List<JSONObject> category3JsonList = new ArrayList<>();
                List<BaseCategoryView> category3List = category2Entry.getValue();
                //6.1 遍历三级分类集合 构建三级分类对象;将三级分类集合 放入二级分类对象 categoryChild属性中
                for (BaseCategoryView baseCategoryView : category3List) {
                    JSONObject category3 = new JSONObject();
                    category3.put("categoryId", baseCategoryView.getCategory3Id());
                    category3.put("categoryName", baseCategoryView.getCategory3Name());
                    category3JsonList.add(category3);
                }

                //将处理后三级分类加入当前二级分类中
                category2.put("categoryChild", category3JsonList);
            }
            // 将二级分类集合加入到一级分类对象 categoryChild属性
            category1.put("categoryChild", category2JsonList);
            //7.将一级分类对象加入总结果集合中
            resultList.add(category1);
        }
        return resultList;
    }
}
```

## 2.3  service-product-client添加接口

在`service-product-client`模块中ProductFeignClient，提供远程调用FeignAPI接口以及服务降级方法

```java
/**
 * 查询所有分类列表 分类嵌套结果:一级分类分类对象中包含二级分类集合;在二级分类对象中包含三级分类集合
 * @return
 */
@GetMapping("/api/product/inner/getBaseCategoryList")
public List<JSONObject> getBaseCategoryList();
```

 

```java
@Override
public List<JSONObject> getBaseCategoryList() {
    return null;
}
```

## 2.4  页面渲染

**第一种缓存渲染方式**：

`web-all`模块中编写控制器

```java
package com.atguigu.gmall.web;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author: atguigu
 * @create: 2023-03-01 11:44
 */
@Controller
public class IndexController {


    @Autowired
    private ProductFeignClient productFeignClient;


    /**
     * 首页index渲染
     *
     * @param model
     * @return
     */
    @GetMapping({"/", "/index.html"})
    public String index(Model model) {
        //todo 获取渲染首页-商品数据 将来为了提高首页数据渲染,必然调用多个服务feign接口 采用异步服务编排进行优化
        //todo 获取渲染首页-广告数据
        //获取渲染首页-分类数据
        List<JSONObject> list = productFeignClient.getBaseCategoryList();
        model.addAttribute("list", list);
        return "/index/index";
    }

}

```

**第二种方式nginx做静态代理方式：**

1. 生成静态文件

```java
@Autowired
private TemplateEngine templateEngine;

@GetMapping("createIndex")
@ResponseBody
public Result createIndex(){
    //  获取后台存储的数据
    Result result = productFeignClient.getBaseCategoryList();
    //  设置模板显示的内容
    Context context = new Context();
    context.setVariable("list",result.getData());

    //  定义文件输入位置
    FileWriter fileWriter = null;
    try {
        fileWriter = new FileWriter("D:\\index.html");
    } catch (IOException e) {
        e.printStackTrace();
    }
    //  调用process();方法创建模板
    templateEngine.process("index/index.html",context,fileWriter);
    return Result.ok();
}
```

2. 解压课后资料中nginx压缩 不要中文空格

3. 将静态文件拷贝到nginx/html目录下 包含js,css等文件夹

   ![image-20230104161748678](assets/image-20230104161748678.png)

4. 启动Nginx服务

5. 访问首页

   ![image-20230104161906057](assets/image-20230104161906057.png)

**Nginx反向代理配置-了解**

1. 启动nginx，nginx目录下打开命令行

   ```
   start nginx
   ```

2. 关闭nginx

   ```
   nginx -s stop
   ```

3. 重新加载nginx配置文件

   ```
   nginx -s reload
   ```

4. nginx.conf配置文件

   ```properties
   	#配置集群列表 默认负载均衡策略为轮询
   	upstream gatewayUpstream {
   	   server 127.0.0.1:7777 weight=3;
   	   server 127.0.0.1:8888 weight=1;
   	   server 127.0.0.1:9999 weight=1;
   	}
   	
       server {
           listen       80;
           server_name  localhost;
   
           #charset koi8-r;
   
           #access_log  logs/host.access.log  main;
   		//localhost/abc/def
   		#配置监听的请求路径
           location / {
               #root   html;
               #index  index.html index.htm;
               #将请求地址以"/"开头的全部反向代理到网关集群服务列表
   			proxy_pass http://gatewayUpstream;
           }
    }
   ```

5. 后端的网关服务要搭建集群

   ![image-20230104162157199](assets/image-20230104162157199.png)

6. 通过Nginx访问服务测试 http://localhost/api/product/inner/getBaseCategoryList

    ![image-20230104162327323](assets/image-20230104162327323.png)

7. 网关中配置全局过滤器可以确定访问的是哪个服务

   ```java
   package com.atguigu.gmall.gateway.filter;
   
   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.cloud.gateway.filter.GatewayFilterChain;
   import org.springframework.cloud.gateway.filter.GlobalFilter;
   import org.springframework.core.Ordered;
   import org.springframework.stereotype.Component;
   import org.springframework.web.server.ServerWebExchange;
   import reactor.core.publisher.Mono;
   
   /**
    * @author: atguigu
    * @create: 2023-01-04 15:54
    */
   @Component
   public class TestFilter implements GlobalFilter, Ordered {
   
       @Value("${server.port}")
       private Integer port;
   
       @Override
       public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
           System.out.println("服务被请求,端口："+port);
           return chain.filter(exchange);
       }
   
       @Override
       public int getOrder() {
           return -100;
       }
   }
   ```

