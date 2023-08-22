package com.ikang.idata.search.search;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.*;
import com.ikang.idata.search.search.entity.vo.RegisterDetail;
import com.ikang.idata.search.search.entity.vo.RegisterDetailSearchVo;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description 测试股
 * @date 2022/3/18 16:49
 */
public class TransformTest {

    @Test
    public void test123() {
        HashSet<String> set = CollUtil.newHashSet("1", "2");
        set.retainAll(CollUtil.newHashSet("3"));
        System.out.println(set);
        System.out.println(set.size());
    }

    @Test
    public void test() {

        RegisterDetailSearchVo searchVo = new RegisterDetailSearchVo();
        searchVo.setResourceId(1L);
        searchVo.setPageNum(10);
        searchVo.setPageSize(10);
        searchVo.setRegistrationDate("1999-10-10");
        searchVo.setInstituteId("10,11");
        System.out.println(JSON.toJSONString(searchVo.convertImpalaParam()));
    }


    @Test
    public void testTable() {
        Table<String, String, Integer> table = HashBasedTable.create();
//存放元素
        table.put("Hydra", "Jan", 20);
        table.put("Hydra", "Feb", 28);

        table.put("Trunks", "Jan", 28);
        table.put("Trunks", "Feb", 16);

        //取出元素
        Integer dayCount = table.get("Hydra", "Feb");
        System.out.println(dayCount);


        System.out.println(table.rowKeySet());
        System.out.println(table.columnKeySet());
        System.out.println(table.values());


        System.out.println(table.rowMap());
        System.out.println(table.columnMap());
    }

    @Test
    public void testbigMap() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("key1", "value1");
        biMap.put("key2", "value2");
        biMap.put("key3", "value3");
        biMap.put("key4", "value4");
        biMap.put("key5", "value5");
        biMap.put("key6", "value6");
        biMap.put("key7", "value7");
        biMap.put("key8", "value8");

        System.out.println(biMap.get("key1"));
//        调转
        BiMap<String, String> inverse = biMap.inverse();
        System.out.println(inverse.get("value1"));
        inverse.put("value8", "key9");
        System.out.println(inverse);
        System.out.println(biMap);
        // 强制put  会把原先的value1和key1删除掉
        biMap.forcePut("key9", "value1");
        System.out.println(biMap);
    }


    @Test
    public void testMultiMap() {
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("key1", "value");
        multimap.put("key1", "value");
        multimap.put("key1", "value");
        multimap.put("key1", "value");
        multimap.put("key2", "value1");
        System.out.println(multimap);

        List<String> strings = multimap.get("key1");
        System.out.println(strings);


        HashMultimap<String, String> hashMultimap = HashMultimap.create();
        hashMultimap.put("key1", "value");
        hashMultimap.put("key1", "value");
        hashMultimap.put("key1", "value");
        hashMultimap.put("key1", "value1");
        hashMultimap.put("key2", "value2");
        System.out.println(hashMultimap);
        Set<String> key1 = hashMultimap.get("key1");
        System.out.println(key1);


        TreeMultimap<String, String> treeMultimap = TreeMultimap.create();
        treeMultimap.put("key1", "value4");
        treeMultimap.put("key1", "value");
        treeMultimap.put("key1", "value2");
        treeMultimap.put("key1", "value");
        System.out.println(treeMultimap);
        NavigableSet<String> x = treeMultimap.get("key1");
        System.out.println(x);

        NavigableMap<String, Collection<String>> x1 = treeMultimap.asMap();
        System.out.println(x1);

        System.out.println("value的size" + treeMultimap.size());
    }


    @Test
    public void testRangMap() {
        TreeRangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.open(0, 10), "第一档");
        rangeMap.put(Range.closedOpen(10, 20), "第二档");
        rangeMap.put(Range.closedOpen(20, 50), "第三档");
        rangeMap.put(Range.atLeast(50), "第四档");
        System.out.println(rangeMap.get(50));
    }


    @Test
    public void testClassToinstanceMap() {

        MutableClassToInstanceMap<Object> instanceMap = MutableClassToInstanceMap.create();
        RegisterDetail detail = new RegisterDetail();
        instanceMap.putInstance(RegisterDetail.class, detail);

        System.out.println(instanceMap.getInstance(RegisterDetail.class) == detail);


        //超类也是可以使用的
        ClassToInstanceMap<Map> map = MutableClassToInstanceMap.create();
        HashMap<String, Object> hashMap = new HashMap<>();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        ArrayList<Object> list = new ArrayList<>();

        map.putInstance(HashMap.class,hashMap);
        map.putInstance(TreeMap.class,treeMap);
    }


}
