package com.ikang.idata.search.search;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.entity.BRMResourceVO;
import com.ikang.idata.common.enums.BRMResourceEnum;
import com.ikang.idata.common.utils.CheckUtil;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.service.ConstantService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/4/25
 */

@Slf4j
@Service
public class MapDemo {

    @Value("${bigdata.url.searchpower.searchpowerUrl}")
    private String searchpowerUrl;


//    @Value("${profile.profileZone}")
//    private String profileZone;

    @Value("${districtTotalArea.profile.condition}")
    private String profileCondition;


    @Autowired
    private ConstantService constantService;
    public List<String> findBRMzoneByName(BRMResourceVO brmResource) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        builder.size(MagicConst.PAGE_SIZE_10000);
        List<QueryBuilder> must = queryBuilder.must();
        //区总区域
        CheckUtil.isTrue(StringUtil.isNotEmpty(brmResource.getChiefZone()),
                () -> must.add(QueryBuilders.termsQuery(BRMResourceEnum.CHIEFZONE.getFields(), brmResource.getChiefZone().split(MagicConst.COMMA_SPLIT))));
        //区域
        CheckUtil.isTrue(StringUtil.isNotEmpty(brmResource.getBrmZone()),
                () -> must.add(QueryBuilders.termsQuery(BRMResourceEnum.BRMZONE.getFields(), brmResource.getBrmZone().split(MagicConst.COMMA_SPLIT))));
        //部门
        CheckUtil.isTrue(StringUtil.isNotEmpty(brmResource.getBrmDepartment()),
                () -> must.add(QueryBuilders.termsQuery(BRMResourceEnum.BRMDEPARTMENT.getFields(), brmResource.getBrmDepartment().split(MagicConst.COMMA_SPLIT))));
        //小组
        CheckUtil.isTrue(StringUtil.isNotEmpty(brmResource.getBrmGroup()),
                () -> must.add(QueryBuilders.termQuery(BRMResourceEnum.BRMGROUP.getFields(),brmResource.getBrmGroup())));
        //领导人
        CheckUtil.isTrue(StringUtil.isNotEmpty(brmResource.getLeaderid()),
                () -> must.add(QueryBuilders.termQuery(BRMResourceEnum.LEADERID.getFields(),brmResource.getLeaderid())));
        //name
        CheckUtil.isTrue(StringUtil.isNotEmpty(brmResource.getName()),
                () -> must.add(QueryBuilders.termQuery(BRMResourceEnum.NAME.getFields(),brmResource.getName())));
        //id
        CheckUtil.isTrue(StringUtil.isNotEmpty(brmResource.getId()),
                () -> must.add(QueryBuilders.termQuery(BRMResourceEnum.ID.getFields(),brmResource.getId())));
//        must.add(QueryBuilders.termsQuery(BRMResourceEnum.PROFILE.getFields(),profileCondition.split(MagicConst.COMMA)));
        //员工是否有效
        must.add(QueryBuilders.termQuery(BRMResourceEnum.ISUSING.getFields(), MagicConst.INT_1));
        builder.collapse(new CollapseBuilder("name")).fetchSource("name", null);
        builder.query(queryBuilder);
        return this.invokeAndGetResultfindBRMzoneByName(builder.toString());
    }

    private List<String> invokeAndGetResultfindBRMzoneByName(String queryDsl) {
        log.info("=======================BRMResource =====销售姓名=======BRMzoneByName=============================================================");
        List<JSONObject> sourceByCondition = constantService.getSourceByCondition(searchpowerUrl, queryDsl);



        return sourceByCondition.stream().map( jsonObject ->jsonObject.getString("name"))
                .filter(jsonObject -> jsonObject!=null ).collect(Collectors.toList());

    }


    public static void main(String[] args) {
        //创建一个 HashMap 对象
        Map<String, Integer> hashmap = new HashMap<String, Integer>();
        //往hashmap中插入数据使用put()方法
        hashmap.put("数学", 78);
        hashmap.put("英语", 88);
        hashmap.put("语文", 86);
        //遍历hashmap，这个估计是大多数人都不会的
        for (Map.Entry<String, Integer> item : hashmap.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue());
        }
		/*   以下上面遍历的是输出
		 数学 78
		语文 86
		英语 88
		 */
        // 通过上面的遍历我们可以知道hashmap中以及存在了上述数据
        // 下面在来插入一个数据看看会有什么不同
        hashmap.put("语文", 100);
        for (Map.Entry<String, Integer> item : hashmap.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue());
        }
		/*    通过下面我们可以看到语文的成绩变成了100，原来的86没有了，这就说明在 HashMap 中，key 的值不能重复，如果存在重复值，则后面的值会把前面的值给覆盖掉
		 数学 78
		语文 100
		英语 88
		*/

        // 我们可以用 containskey()方法来判断里面是否存在某个key
        if (hashmap.containsKey("数学"))
            System.out.println("存在key=数学");
        else
            System.out.println("不存在该key=数学");


        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }

        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }


        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        int n = 10000000;
        for (int i = 1; i <= n; i++) {
            map1.put(i, i);
        }

        System.out.println("======== 1 entrySet 最常用 ==========");
        entrySetTest(map1);
        System.out.println("======== 2 keySet Or Values 取keys或values 时,效率最高 ==========");
        keySetOrValuesTest(map1);
        //System.out.println("======== keySetForValueTest ==========");
        keySetForValueTest(map1);
        System.out.println("======== 3 iterator Type ==========");
        iteratorTypeTest(map1);
        System.out.println("======== 4 forEach java 8 效率最低 ==========");
        forEachTest(map1);
        System.out.println("======== 5 stream ForEach java 8 流式 ==========");
        streamForEachTest(map1);
    }
    /**
     * 1
     * entrySet 这是最常用的方式，在键值都需要的时候
     * @param map
     */
    public static void entrySetTest(Map<Integer, Integer> map){
        long before = System.currentTimeMillis();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.getKey();
            entry.getValue();
            // System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        long after = System.currentTimeMillis();
        System.out.println("map.entrySet() time=\t"  + (after - before));
    }
    /**
     * 2（1）keySet values
     * 如果只需要键或者值，这个比键值对的要快
     * @param map
     */
    public static void keySetOrValuesTest(Map<Integer, Integer> map){
        long before = System.currentTimeMillis();
        // 遍历map中的键
        for (Integer key : map.keySet()) {
            //System.out.println("Key = " + key);
        }
        long after = System.currentTimeMillis();
        System.out.println("map.keySet() time=\t"  + (after - before));

        before = System.currentTimeMillis();
        // 遍历map中的键
        Iterator<Integer> iteratorKeySet = map.keySet().iterator();
        while (iteratorKeySet.hasNext()) {
            iteratorKeySet.next();
            //System.out.println("key = " + iteratorKeySet.next());
        }

        after = System.currentTimeMillis();
        System.out.println("map.keySet().iterator() time=\t"  + (after - before));


        before = System.currentTimeMillis();
        // 遍历map中的值
        for (Integer value : map.values()) {
            //System.out.println("Value = " + value);
        }
        after = System.currentTimeMillis();
        System.out.println("map.values() time=\t"  + (after - before));

        before = System.currentTimeMillis();
        // 遍历map中的值
        Iterator<Integer> iteratorValues = map.values().iterator();
        while (iteratorValues.hasNext()) {
            iteratorValues.next();
            //System.out.println("key = " + iterator.next());
        }
        after = System.currentTimeMillis();
        System.out.println("map.values().iterator() time=\t"  + (after - before));
    }


    /**
     * 2（2）keySet get
     * 根据键找值，效率很低
     * @param map
     */
    public static void keySetForValueTest(Map<Integer, Integer> map){
        long before = System.currentTimeMillis();
        //遍历map中的键
        for (Integer key : map.keySet()) {
            Integer value = map.get(key);
            //System.out.println("Key = " + key + ", Value = " + value);
        }
        long after = System.currentTimeMillis();
        System.out.println("map.keySet() -> map.get(key) time=\t"  + (after - before));
    }


    /**
     * 3（1）iterator type
     * 迭代器 带泛型
     * @param map
     */
    public static void iteratorTypeTest(Map<Integer, Integer> map){
        long before = System.currentTimeMillis();
        Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        long after = System.currentTimeMillis();
        System.out.println("map.entrySet().iterator() time=\t"  + (after - before));
    }

    /**
     * 4 forEach 遍历的是键值，所以是两个参数
     * forEach java8 lambda
     * @param map
     */
    public static void forEachTest(Map<Integer, Integer> map){
        long before = System.currentTimeMillis();
        //map.forEach( (key,value) -> {System.out.println("Key = " + key+ ", Value = "+ value);} );
        //map.forEach( (key,value) -> {} );
        long after = System.currentTimeMillis();
        System.out.println("map.forEach time=\t"  + (after - before));

        // 一条语句可以省略{}
        //map.forEach( (key,value) -> System.out.println("Key = " + key+ ", Value = "+ value) );
    }


    /**
     * 5 stream forEach 遍历的是entry 所以是一个参数
     * stream forEach java8 lambda
     * @param map
     */
    public static void streamForEachTest(Map<Integer, Integer> map){
        long before = System.currentTimeMillis();
        //map.entrySet().stream().forEach( (entry) -> {System.out.println("Key = " + entry.getKey()+ ", Value = "+ entry.getValue());} );
        //map.entrySet().stream().forEach( System.out::println);
        //map.entrySet().stream().forEach( (entry) -> {} );
        long after = System.currentTimeMillis();
        System.out.println("map.entrySet().stream().forEach time=\t"  + (after - before));

        // 一个参数可以省略()  一条语句可以省略{}
        //map.entrySet().stre
     }
}

