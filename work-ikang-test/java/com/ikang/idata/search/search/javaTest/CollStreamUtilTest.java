package com.ikang.idata.search.search.javaTest;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.IterUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.csp.sentinel.node.IntervalProperty;
import com.alibaba.csp.sentinel.node.OccupyTimeoutProperty;
import com.alibaba.csp.sentinel.node.SampleCountProperty;
import com.alibaba.csp.sentinel.node.metric.MetricNode;
import com.alibaba.csp.sentinel.slots.statistic.base.LongAdder;
import com.alibaba.csp.sentinel.slots.statistic.metric.ArrayMetric;
import com.alibaba.csp.sentinel.slots.statistic.metric.Metric;
import com.alibaba.csp.sentinel.util.TimeUtil;
import com.alibaba.csp.sentinel.util.function.Predicate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.hutool.core.collection.CollUtil.set;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/11/21
 */
public class CollStreamUtilTest {

    /**
     * 将collection转化为类型不变的map<br>
     * <B>{@code Collection<V>  ---->  Map<K,V>}</B>
     *
     * @param collection 需要转化的集合
     * @param key        V类型转化为K类型的lambda方法
     * @param <V>        collection中的泛型
     * @param <K>        map中的key类型
     * @return 转化后的map
     */
    public static <V, K> Map<K, V> toIdentityMap(Collection<V> collection, Function<V, K> key) {
        if (CollUtil.isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream().collect(Collectors.toMap(key, Function.identity(), (l, r) -> l));
    }

    /**
     * 将Collection转化为map(value类型与collection的泛型不同)<br>
     * <B>{@code Collection<E> -----> Map<K,V>  }</B>
     *
     * @param collection 需要转化的集合
     * @param key        E类型转化为K类型的lambda方法
     * @param value      E类型转化为V类型的lambda方法
     * @param <E>        collection中的泛型
     * @param <K>        map中的key类型
     * @param <V>        map中的value类型
     * @return 转化后的map
     */
    public static <E, K, V> Map<K, V> toMap(Collection<E> collection, Function<E, K> key, Function<E, V> value) {
        if (CollUtil.isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection.stream().collect(Collectors.toMap(key, value, (l, r) -> l));
    }

    /**
     * 将collection按照规则(比如有相同的班级id)分类成map<br>
     * <B>{@code Collection<E> -------> Map<K,List<E>> } </B>
     *
     * @param collection 需要分类的集合
     * @param key        分类的规则
     * @param <E>        collection中的泛型
     * @param <K>        map中的key类型
     * @return 分类后的map
     */
    public static <E, K> Map<K, List<E>> groupByKey(Collection<E> collection, Function<E, K> key) {
        if (CollUtil.isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection
                .stream()
                .collect(Collectors.groupingBy(key, Collectors.toList()));
    }

    /**
     * 将collection按照两个规则(比如有相同的年级id,班级id)分类成双层map<br>
     * <B>{@code Collection<E>  --->  Map<T,Map<U,List<E>>> } </B>
     *
     * @param collection 需要分类的集合
     * @param key1       第一个分类的规则
     * @param key2       第二个分类的规则
     * @param <E>        集合元素类型
     * @param <K>        第一个map中的key类型
     * @param <U>        第二个map中的key类型
     * @return 分类后的map
     */
    public static <E, K, U> Map<K, Map<U, List<E>>> groupBy2Key(Collection<E> collection, Function<E, K> key1, Function<E, U> key2) {
        if (CollUtil.isEmpty(collection)) {
            return Collections.emptyMap();
        }
        return collection
                .stream()
                .collect(Collectors.groupingBy(key1, Collectors.groupingBy(key2, Collectors.toList())));
    }

    /**
     * 将collection按照两个规则(比如有相同的年级id,班级id)分类成双层map<br>
     * <B>{@code Collection<E>  --->  Map<T,Map<U,E>> } </B>
     *
     * @param collection 需要分类的集合
     * @param key1       第一个分类的规则
     * @param key2       第二个分类的规则
     * @param <T>        第一个map中的key类型
     * @param <U>        第二个map中的key类型
     * @param <E>        collection中的泛型
     * @return 分类后的map
     */
    public static <E, T, U> Map<T, Map<U, E>> group2Map(Collection<E> collection, Function<E, T> key1, Function<E, U> key2) {
        if (CollUtil.isEmpty(collection) || key1 == null || key2 == null) {
            return Collections.emptyMap();
        }
        return collection
                .stream()
                .collect(Collectors.groupingBy(key1, Collectors.toMap(key2, Function.identity(), (l, r) -> l)));
    }

    /**
     * 将collection转化为List集合，但是两者的泛型不同<br>
     * <B>{@code Collection<E>  ------>  List<T> } </B>
     *
     * @param collection 需要转化的集合
     * @param function   collection中的泛型转化为list泛型的lambda表达式
     * @param <E>        collection中的泛型
     * @param <T>        List中的泛型
     * @return 转化后的list
     */
    public static <E, T> List<T> toList(Collection<E> collection, Function<E, T> function) {
        if (CollUtil.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection
                .stream()
                .map(function)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 将collection转化为Set集合，但是两者的泛型不同<br>
     * <B>{@code Collection<E>  ------>  Set<T> } </B>
     *
     * @param collection 需要转化的集合
     * @param function   collection中的泛型转化为set泛型的lambda表达式
     * @param <E>        collection中的泛型
     * @param <T>        Set中的泛型
     * @return 转化后的Set
     */
    public static <E, T> Set<T> toSet(Collection<E> collection, Function<E, T> function) {
        if (CollUtil.isEmpty(collection) || function == null) {
            return Collections.emptySet();
        }
        return collection
                .stream()
                .map(function)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }


    /**
     * 合并两个相同key类型的map
     *
     * @param map1  第一个需要合并的 map
     * @param map2  第二个需要合并的 map
     * @param merge 合并的lambda，将key  value1 value2合并成最终的类型,注意value可能为空的情况
     * @param <K>   map中的key类型
     * @param <X>   第一个 map的value类型
     * @param <Y>   第二个 map的value类型
     * @param <V>   最终map的value类型
     * @return 合并后的map
     */
    public static <K, X, Y, V> Map<K, V> merge(Map<K, X> map1, Map<K, Y> map2, BiFunction<X, Y, V> merge) {
        if (MapUtil.isEmpty(map1) && MapUtil.isEmpty(map2)) {
            return Collections.emptyMap();
        } else if (MapUtil.isEmpty(map1)) {
            map1 = Collections.emptyMap();
        } else if (MapUtil.isEmpty(map2)) {
            map2 = Collections.emptyMap();
        }
        Set<K> key = new HashSet<>();
        key.addAll(map1.keySet());
        key.addAll(map2.keySet());
        Map<K, V> map = new HashMap<>();
        for (K t : key) {
            X x = map1.get(t);
            Y y = map2.get(t);
            V z = merge.apply(x, y);
            if (z != null) {
                map.put(t, z);
            }
        }
        return map;
    }




    /**
     * 多个集合的交集<br>
     * 针对一个集合中存在多个相同元素的情况，计算两个集合中此元素的个数，保留最少的个数<br>
     * 例如：集合1：[a, b, c, c, c]，集合2：[a, b, c, c]<br>
     * 结果：[a, b, c, c]，此结果中只保留了两个c
     *
     * @param <T>        集合元素类型
     * @param coll1      集合1
     * @param coll2      集合2
     * @param otherColls 其它集合
     * @return 交集的集合，返回 {@link ArrayList}
     */
    @SafeVarargs
    public static <T> Collection<T> intersection(Collection<T> coll1, Collection<T> coll2, Collection<T>... otherColls) {
        Collection<T> intersection = intersection(coll1, coll2);
        if (isEmpty(intersection)) {
            return intersection;
        }
        for (Collection<T> coll : otherColls) {
            intersection = intersection(intersection, coll);
            if (isEmpty(intersection)) {
                return intersection;
            }
        }
        return intersection;
    }

    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }


    /**
     * 集合是否为非空
     *
     * @param collection 集合
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return false == isEmpty(collection);
    }

    /**
     * 根据集合返回一个元素计数的 {@link Map}<br>
     * 所谓元素计数就是假如这个集合中某个元素出现了n次，那将这个元素做为key，n做为value<br>
     * 例如：[a,b,c,c,c] 得到：<br>
     * a: 1<br>
     * b: 1<br>
     * c: 3<br>
     *
     * @param <T>        集合元素类型
     * @param collection 集合
     * @return {@link Map}
     * @see IterUtil#countMap(Iterator)
     */
    public static <T> Map<T, Integer> countMap(Iterable<T> collection) {
        return IterUtil.countMap(null == collection ? null : collection.iterator());
    }

    /**
     * 新建一个HashSet
     *
     * @param <T> 集合元素类型
     * @param ts  元素数组
     * @return HashSet对象
     */
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... ts) {
        return set(false, ts);
    }


    private transient volatile Metric rollingCounterInSecond;
    private transient Metric rollingCounterInMinute;
    private LongAdder curThreadNum;
    private long lastFetchTime;

    private static final long serialVersionUID = 1L;

    /**
     * 数组
     */
    private  Object array;
    /**
     * 起始位置
     */
    private int startIndex;
    /**
     * 结束位置
     */
    private int endIndex;
    /**
     * 当前位置
     */
    private int index;

   /**
     * 不允许操作数组元素
     *
     * @throws UnsupportedOperationException always
     */
    public void remove() {
        throw new UnsupportedOperationException("remove() method is not supported");
    }

    // Properties
    // -----------------------------------------------------------------------

    /**
     * 获得原始数组对象
     *
     * @return 原始数组对象
     */
    public Object getArray() {
        return array;
    }

    /**
     * 重置数组位置
     */
    public void CollStream() {
        this.index = this.startIndex;
    }


    public CollStreamUtilTest() {
        this.rollingCounterInSecond = new ArrayMetric(SampleCountProperty.SAMPLE_COUNT, IntervalProperty.INTERVAL);
        this.rollingCounterInMinute = new ArrayMetric(60, 60000, false);
        this.curThreadNum = new LongAdder();
        this.lastFetchTime = -1L;
    }

    public Map<Long, MetricNode> metrics() {
        long currentTime = TimeUtil.currentTimeMillis();
        currentTime -= currentTime % 1000L;
        Map<Long, MetricNode> metrics = new ConcurrentHashMap();
        List<MetricNode> nodesOfEverySecond = this.rollingCounterInMinute.details();
        long newLastFetchTime = this.lastFetchTime;
        Iterator var7 = nodesOfEverySecond.iterator();

        while(var7.hasNext()) {
            MetricNode node = (MetricNode)var7.next();
            if (this.isNodeInTime(node, currentTime) && this.isValidMetricNode(node)) {
                metrics.put(node.getTimestamp(), node);
                newLastFetchTime = Math.max(newLastFetchTime, node.getTimestamp());
            }
        }

        this.lastFetchTime = newLastFetchTime;
        return metrics;
    }

    public List<MetricNode> rawMetricsInMin(Predicate<Long> timePredicate) {
        return this.rollingCounterInMinute.detailsOnCondition(timePredicate);
    }

    private boolean isNodeInTime(MetricNode node, long currentTime) {
        return node.getTimestamp() > this.lastFetchTime && node.getTimestamp() < currentTime;
    }

    private boolean isValidMetricNode(MetricNode node) {
        return node.getPassQps() > 0L || node.getBlockQps() > 0L || node.getSuccessQps() > 0L || node.getExceptionQps() > 0L || node.getRt() > 0L || node.getOccupiedPassQps() > 0L;
    }

    public void reset() {
        this.rollingCounterInSecond = new ArrayMetric(SampleCountProperty.SAMPLE_COUNT, IntervalProperty.INTERVAL);
    }

    public long totalRequest() {
        return this.rollingCounterInMinute.pass() + this.rollingCounterInMinute.block();
    }

    public long blockRequest() {
        return this.rollingCounterInMinute.block();
    }

    public double blockQps() {
        return (double)this.rollingCounterInSecond.block() / this.rollingCounterInSecond.getWindowIntervalInSec();
    }

    public double previousBlockQps() {
        return (double)this.rollingCounterInMinute.previousWindowBlock();
    }

    public double previousPassQps() {
        return (double)this.rollingCounterInMinute.previousWindowPass();
    }

    public double totalQps() {
        return this.passQps() + this.blockQps();
    }

    public long totalSuccess() {
        return this.rollingCounterInMinute.success();
    }

    public double exceptionQps() {
        return (double)this.rollingCounterInSecond.exception() / this.rollingCounterInSecond.getWindowIntervalInSec();
    }

    public long totalException() {
        return this.rollingCounterInMinute.exception();
    }

    public double passQps() {
        return (double)this.rollingCounterInSecond.pass() / this.rollingCounterInSecond.getWindowIntervalInSec();
    }

    public long totalPass() {
        return this.rollingCounterInMinute.pass();
    }

    public double successQps() {
        return (double)this.rollingCounterInSecond.success() / this.rollingCounterInSecond.getWindowIntervalInSec();
    }

    public double maxSuccessQps() {
        return (double)this.rollingCounterInSecond.maxSuccess() * (double)this.rollingCounterInSecond.getSampleCount() / this.rollingCounterInSecond.getWindowIntervalInSec();
    }

    public double occupiedPassQps() {
        return (double)this.rollingCounterInSecond.occupiedPass() / this.rollingCounterInSecond.getWindowIntervalInSec();
    }

    public double avgRt() {
        long successCount = this.rollingCounterInSecond.success();
        return successCount == 0L ? 0.0D : (double)this.rollingCounterInSecond.rt() * 1.0D / (double)successCount;
    }

    public double minRt() {
        return (double)this.rollingCounterInSecond.minRt();
    }

    public int curThreadNum() {
        return (int)this.curThreadNum.sum();
    }

    public void addPassRequest(int count) {
        this.rollingCounterInSecond.addPass(count);
        this.rollingCounterInMinute.addPass(count);
    }

    public void addRtAndSuccess(long rt, int successCount) {
        this.rollingCounterInSecond.addSuccess(successCount);
        this.rollingCounterInSecond.addRT(rt);
        this.rollingCounterInMinute.addSuccess(successCount);
        this.rollingCounterInMinute.addRT(rt);
    }

    public void increaseBlockQps(int count) {
        this.rollingCounterInSecond.addBlock(count);
        this.rollingCounterInMinute.addBlock(count);
    }

    public void increaseExceptionQps(int count) {
        this.rollingCounterInSecond.addException(count);
        this.rollingCounterInMinute.addException(count);
    }

    public void increaseThreadNum() {
        this.curThreadNum.increment();
    }

    public void decreaseThreadNum() {
        this.curThreadNum.decrement();
    }

    public void debug() {
        this.rollingCounterInSecond.debug();
    }

    public long tryOccupyNext(long currentTime, int acquireCount, double threshold) {
        double maxCount = threshold * (double)IntervalProperty.INTERVAL / 1000.0D;
        long currentBorrow = this.rollingCounterInSecond.waiting();
        if ((double)currentBorrow >= maxCount) {
            return (long) OccupyTimeoutProperty.getOccupyTimeout();
        } else {
            int windowLength = IntervalProperty.INTERVAL / SampleCountProperty.SAMPLE_COUNT;
            long earliestTime = currentTime - currentTime % (long)windowLength + (long)windowLength - (long)IntervalProperty.INTERVAL;
            int idx = 0;

            for(long currentPass = this.rollingCounterInSecond.pass(); earliestTime < currentTime; ++idx) {
                long waitInMs = (long)(idx * windowLength + windowLength) - currentTime % (long)windowLength;
                if (waitInMs >= (long)OccupyTimeoutProperty.getOccupyTimeout()) {
                    break;
                }

                long windowPass = this.rollingCounterInSecond.getWindowPass(earliestTime);
                if ((double)(currentPass + currentBorrow + (long)acquireCount - windowPass) <= maxCount) {
                    return waitInMs;
                }

                earliestTime += (long)windowLength;
                currentPass -= windowPass;
            }

            return (long)OccupyTimeoutProperty.getOccupyTimeout();
        }
    }

    public long waiting() {
        return this.rollingCounterInSecond.waiting();
    }

    public void addWaitingRequest(long futureTime, int acquireCount) {
        this.rollingCounterInSecond.addWaiting(futureTime, acquireCount);
    }

    public void addOccupiedPass(int acquireCount) {
        this.rollingCounterInMinute.addOccupiedPass(acquireCount);
        this.rollingCounterInMinute.addPass(acquireCount);
    }
}

