package com.ikang.idata.search.search;

import com.alibaba.fastjson.JSONPath;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/8/1
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JSONPasthTest {
    public static void main(String[] args) {
        //singletonMap
        //返回一个不可变的地图，只将指定的键映射到指定的值。
        Map root = Collections.singletonMap("company",
                Collections.singletonMap("departs",
                        Arrays.asList(Collections.singletonMap("id", 1001),
                                Collections.singletonMap("id", 1002),
                                Collections.singletonMap("id", 1003))));

        List<Object> ids = (List<Object>) JSONPath.eval(root, "$..id");
        System.out.println(  "ids" + ids);

        assertEquals(3, ids.size());
        System.out.println( "ids.size()" + ids.size());

        assertEquals(1001, ids.get(0));
        System.out.println( "1001" + ids.get(0));

        assertEquals(1002, ids.get(1));
        System.out.println( "1002" + ids.get(1));

        assertEquals(1003, ids.get(2));
        System.out.println( "1003" + ids.get(2));

        /**
         * ids[1001, 1002, 1003]
         * ids.size()3
         * 10011001
         * 10021002
         * 10031003
         */
    }



    @Test
    public void test1() throws Exception{


        /**
         *         Root root = new Root();
         *         root.company = new Company();
         *         root.company.departs.add(new Department(1001));
         *         root.company.departs.add(new Department(1002));
         *         root.company.departs.add(new Department(1003));
         *
         *
         *         List<Object> ids = (List<Object>) JSONPath.eval(root, "$..id");
         *         Assert.assertEquals(3, ids.size());
         *         Assert.assertEquals(1001, ids.get(0));
         *         Assert.assertEquals(1002, ids.get(1));
         *         Assert.assertEquals(1003, ids.get(2));
         */

    }

    @Test
    public void  testListLikeExtract(){
//        JSONPath path = new JSONPath("$[name like 'ljw2083']");
//
//        List<Person> entities = new ArrayList<Person>();
//        entities.add(new Person(1001, "ljw2083"));
//        entities.add(new Person(1002, "wenshao"));
//        entities.add(new Person(1003, null));
//        entities.add(new Person(null, null));
//
//        List<Object> result = (List<Object>) path.eval(entities);
//        Assert.assertEquals(1, result.size());
//        Assert.assertSame(entities.get(0), result.get(0));

    }

    @Test
    public void testListLikeLeftNotMatch(){
//        JSONPath path = new JSONPath("$[name not like 'ljw%']");
//
//        List<Entity> entities = new ArrayList<Entity>();
//        entities.add(new Entity(1001, "ljw2083"));
//        entities.add(new Entity(1002, "wenshao"));
//        entities.add(new Entity(1003, "yakolee"));
//        entities.add(new Entity(null, null));
//
//        List<Object> result = (List<Object>) path.eval(entities);
//        Assert.assertEquals(2, result.size());
//        Assert.assertSame(entities.get(1), result.get(0));
//        Assert.assertSame(entities.get(2), result.get(1));


    }

    @Test
    public void testEntity(){
        Entity entity = new Entity();
        Assert.assertSame(entity.getValue(),JSONPath.eval(entity,"$.value"));
        System.out.println("entity.getValue()" + JSONPath.eval(entity,"$.value"));
        Assert.assertTrue(JSONPath.contains(entity,"$.value"));
        System.out.println("$.value");
        Assert.assertTrue(JSONPath.containsValue(entity, "$.id", 123));
        System.out.println(" \"$.id\" " + 123);
        Assert.assertTrue(JSONPath.containsValue(entity, "$.value", entity.getValue()));
        System.out.println(" \"$.value\" " + entity.getValue());
        Assert.assertEquals(2, JSONPath.size(entity, "$"));
        System.out.println(JSONPath.size(entity, "$") + "$");
        Assert.assertEquals(0, JSONPath.size(new Entity(),"$"));
        System.out.println("JSONPath.size(new Entity()" + "$");


    }

    @Test
    public void testBetween(){
        List list = new ArrayList();
        list.add(new Entity(101,"kki","空"));
        list.add(new Entity(102,"ljw55","dadad"));
        list.add(new Entity(103,"name","value"));
        List<Object> result = (List<Object>) JSONPath.eval(list,"$.[id between 101 and 102]");
        assertEquals(2,result.size());
        Assert.assertSame(list.get(0),result.get(0));
        Assert.assertSame(list.get(1),result.get(1));
        /**
         *
         */
    }

    @Test
    public void testRangeStep(){
        List list = new ArrayList();
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());

        JSONPath jsonPath = new JSONPath("$[2:8:2]");
        List<Object> result = (List<Object>) jsonPath.eval(list);
        assertEquals(2,result.size());
        Assert.assertSame(list.get(0),result.get(0));
        Assert.assertSame(list.get(1),result.get(1));
        /**
         *
         */
    }

    @Test
    public void testListMapGe() throws Exception{
        JSONPath path = new JSONPath("$[name != null]");
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001,"fghjk","hehhe"));
        entities.add(new Entity(1002,"null","ytre"));
        entities.add(new Entity(1003,"iuytrew","bvcx"));
        entities.add(new Entity(1004,"nbv","gfd"));

        List<Object> result = (List<Object>) path.eval(entities);
        assertEquals(2,result.size());
        Assert.assertSame(entities.get(0),result.get(0));
        Assert.assertSame(entities.get(1),result.get(1));

        /**
         *
         */

    }

    @Test
    public void testListNotIn(){
        JSONPath path = new JSONPath("[name not in ('fghjk')]");

        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001,"fghjk","hehhe"));
        entities.add(new Entity(1002,"null","ytre"));
        entities.add(new Entity(1003,"iuytrew","bvcx"));
        entities.add(new Entity(1004,"nbv","gfd"));

        List<Object> result = (List<Object>) path.eval(entities);
        assertEquals(2,result.size());
        Assert.assertSame(entities.get(0),result.get(0));
        Assert.assertSame(entities.get(1),result.get(1));

    }

    @Test
    public void testListLikeRightMatch(){
        JSONPath path = new JSONPath("$[?(@.name like '%2083')]");

        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001,"FGHJK2083","hehhe"));
        entities.add(new Entity(1002,"null","ytre"));
        entities.add(new Entity(1003,"iuytrew","bvcx"));
        entities.add(new Entity(1004,"nbv","gfd"));

        List<Object> result = (List<Object>) path.eval(entities);
        assertEquals(1, result.size());
        Assert.assertSame(entities.get(0), result.get(0));

    }

    @Test
    public void testListLikeNotContains(){
        JSONPath path = new JSONPath("$[name not like '%208%']");

        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001,"FGHJK2083","hehhe"));
        entities.add(new Entity(1002,"null","ytre"));
        entities.add(new Entity(1003,"iuytrew","bvcx"));
        entities.add(new Entity(1004,"nbv","gfd"));

        List<Object> result = (List<Object>) path.eval(entities);
        assertEquals(2, result.size());
        Assert.assertSame(entities.get(1), result.get(0));
        Assert.assertSame(entities.get(2), result.get(1));

    }


    @Test
    public void testListIn(){
        JSONPath path = new JSONPath("[id in (1001, 1003, 1004)]");

        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001,"FGHJK2083","hehhe"));
        entities.add(new Entity(1002,"null","ytre"));
        entities.add(new Entity(1003,"iuytrew","bvcx"));
        entities.add(new Entity(1004,"nbv","gfd"));

        List<Object> result = (List<Object>) path.eval(entities);
        assertEquals(3, result.size());
        Assert.assertSame(entities.get(0), result.get(0));
        Assert.assertSame(entities.get(2), result.get(1));
        Assert.assertSame(entities.get(3), result.get(2));

    }



    @Test
    public void testBetweenIn(){
        List list = new ArrayList();
        list.add(new Entity(1001,"FGHJK2083","hehhe"));
        list.add(new Entity(1002,"null","ytre"));
        list.add(new Entity(1003,"iuytrew","bvcx"));
        list.add(new Entity(1004,"nbv","gfd"));

        List<Object> result = (List<Object>) JSONPath.eval(list, "$[id not between 101 and 102]");
        assertEquals(1, result.size());
        Assert.assertSame(list.get(2), result.get(0));

    }

    @Test
    public void testListEqNull(){
        JSONPath path = new JSONPath("$[name = null]");

        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001,"FGHJK2083","hehhe"));
        entities.add(new Entity(1002,"null","ytre"));
        entities.add(new Entity(1003,"iuytrew","bvcx"));
        entities.add(new Entity(1004,"nbv","gfd"));

        List<Object> result = (List<Object>) path.eval(entities);
        assertEquals(2, result.size());
        Assert.assertSame(entities.get(2), result.get(0));
        Assert.assertSame(entities.get(3), result.get(1));

    }

    @Test
    public void testListMap(){
        Entity entity = new Entity(123, "wenshao","sdfghjkl");
        JSONPath path = new JSONPath("$['id','name']");

        List<Object> result = (List<Object>) path.eval(entity);
        Assert.assertSame(entity.getId(), result.get(0));
        Assert.assertSame(entity.getName(), result.get(1));

    }
    @Test
    public void testEntity1(){
        Entity entity = new Entity(123,"dfghjk", new Object());

        Assert.assertSame(entity.getValue(), JSONPath.eval(entity, "$.value"));
        Assert.assertTrue(JSONPath.contains(entity, "$.value"));
        Assert.assertTrue(JSONPath.containsValue(entity, "$.id", 123));
        Assert.assertTrue(JSONPath.containsValue(entity, "$.value", entity.getValue()));
        assertEquals(2, JSONPath.size(entity, "$"));
        //Assert.assertEquals(0, JSONPath.size(new Object[], "$"));
    }

    //读取集合多个元素的某个属性
    @Test
    public void testMore(){
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001,"FGHJK2083","hehhe"));
        entities.add(new Entity(1002,"null","ytre"));
        entities.add(new Entity(1003,"iuytrew","bvcx"));
        entities.add(new Entity(1004,"nbv","gfd"));

        List<String> names = (List<String>)JSONPath.eval(entities, "$.name"); // 返回enties的所有名称
        Assert.assertSame(entities.get(0).getName(), names.get(0));
        Assert.assertSame(entities.get(1).getName(), names.get(1));
    }

    //返回集合中多个元素
    @Test
    public void testToMore(){
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001,"FGHJK2083","hehhe"));
        entities.add(new Entity(1002,"null","ytre"));
        entities.add(new Entity(1003,"iuytrew","bvcx"));
        entities.add(new Entity(1004,"nbv","gfd"));

        List<Entity> result = (List<Entity>)JSONPath.eval(entities, "[1,2]"); // 返回下标为1和2的元素
        assertEquals(2, result.size());
        Assert.assertSame(entities.get(1), result.get(0));
        Assert.assertSame(entities.get(2), result.get(1));

    }

    //读取集合多个元素的某个属性
    @Test
    public void testToMore1(){
        List<Entity> entities = new ArrayList<Entity>();
        entities.add(new Entity(1001,"FGHJK2083","hehhe"));
        entities.add(new Entity(1002,"null","ytre"));
        entities.add(new Entity(1003,"iuytrew","bvcx"));
        entities.add(new Entity(1004,"nbv","gfd"));

        List<String> names = (List<String>)JSONPath.eval(entities, "$.name"); // 返回enties的所有名称
        Assert.assertSame(entities.get(0).getName(), names.get(0));
        Assert.assertSame(entities.get(1).getName(), names.get(1));

    }

    //根据属性值过滤条件判断是否返回对象，修改对象，数组属性添加元素
    @Test
    public void testToMore2(){
        Entity entity = new Entity(1001, "ljw2083","calue");
        Assert.assertSame(entity , JSONPath.eval(entity, "[id = 1001]"));
        Assert.assertNull(JSONPath.eval(entity, "[id = 1002]"));

        JSONPath.set(entity, "id", 123456); //将id字段修改为123456
        assertEquals(123456, entity.getId().intValue());

        JSONPath.set(entity, "value", new int[0]); //将value字段赋值为长度为0的数组
        JSONPath.arrayAdd(entity, "value", 1, 2, 3); //将value字段的数组添加元素1,2,3

    }


    @Test
    public void testToMore3(){
        Map root = Collections.singletonMap("company", //
                Collections.singletonMap("departs", //
                        Arrays.asList( //
                                Collections.singletonMap("id",
                                        1001), //
                                Collections.singletonMap("id",
                                        1002), //
                                Collections.singletonMap("id", 1003) //
                        ) //
                ));

        List<Object> ids = (List<Object>) JSONPath.eval(root, "$..id");
        assertEquals(3, ids.size());
        assertEquals(1001, ids.get(0));
        assertEquals(1002, ids.get(1));
        assertEquals(1003, ids.get(2));
    }

    @Test
    public void testToMore4(){
        //
        //User user = new User("itguang", "123456", "123@qq.com");
        //        String username = (String) JSONPath.eval(user, "$.username");
        //
        //        log.info("$.username = {}", username);
        //
        //        Entity entity = new Entity(123, user);
        //        User user1 = (User) JSONPath.eval(entity, "$.value");
        //        log.info("user={}", user1.toString());
        //



        //User user = new User("itguang", "123456", "123@qq.com");
        //        Entity entity = new Entity(123, user);
        //
        //        //判断entity中是否有 data
        //        boolean contains = JSONPath.contains(entity, "$.data");
        //        Assert.assertTrue(contains);
        //
        //        //判断 entity.data.username 属性值是否为 itguang
        //        boolean containsValue = JSONPath.containsValue(entity, "$.data.username", "itguang");
        //        Assert.assertTrue(containsValue);
        //
        //        Assert.assertEquals(2, JSONPath.size(entity, "$"));

        // List<Entity> entities = new ArrayList<Entity>();
        //        entities.add(new Entity("逻辑"));
        //        entities.add(new Entity("叶文杰"));
        //        entities.add(new Entity("程心"));
        //
        //        //返回集合中多个元素
        //        List<String> names = (List<String>) JSONPath.eval(entities, "$.name");
        //        log.info("返回集合中多个元素names={}", names);
        //
        //
        //        //返回下标 0 和 2 的元素
        //        List<Entity> result = (List<Entity>) JSONPath.eval(entities, "[0,2]");
        //        log.info("返回下标 0 和 2 的元素={}", result);
        //
        //        // 返回下标从0到2的元素
        //        List<Entity> result2 = (List<Entity>) JSONPath.eval(entities, "[0:2]");
        //
        //        log.info("返回下标从0到2的元素={}", result2);

        // List<Entity> entities = new ArrayList<Entity>();
        //        entities.add(new Entity(1001, "逻辑"));
        //        entities.add(new Entity(1002, "程心"));
        //        entities.add(new Entity(1003, "叶文杰"));
        //        entities.add(new Entity(1004, null));
        //
        //        //通过条件过滤，返回集合的子集
        //
        //        List<Entity> result = (List<Entity>) JSONPath.eval(entities, "[id in (1001)]");
        //        log.info("通过条件过滤，返回集合的子集={}", result);


        /**
         * 使用JSONPrase 解析JSON字符串或者Object对象
         * <p>
         * read(String json, String path)//直接使用json字符串匹配
         * <p>
         * eval(Object rootObject, String path) //直接使用 对象匹配
         * <p>
         * <p>
         * {"store":{"bicycle":{"color":"red","price":19.95},"book":[{"author":"Nigel Rees","price":8.95,"category":"reference","title":"Sayings of the Century"},{"author":"Evelyn Waugh","price":12.99,"isbn":"0-553-21311-3","category":"fiction","title":"Sword of Honour"}]}}
         */


        //String jsonStr = "{\n" +
        //                "    \"store\": {\n" +
        //                "        \"bicycle\": {\n" +
        //                "            \"color\": \"red\",\n" +
        //                "            \"price\": 19.95\n" +
        //                "        },\n" +
        //                "        \"book\": [\n" +
        //                "            {\n" +
        //                "                \"author\": \"刘慈欣\",\n" +
        //                "                \"price\": 8.95,\n" +
        //                "                \"category\": \"科幻\",\n" +
        //                "                \"title\": \"三体\"\n" +
        //                "            },\n" +
        //                "            {\n" +
        //                "                \"author\": \"itguang\",\n" +
        //                "                \"price\": 12.99,\n" +
        //                "                \"category\": \"编程语言\",\n" +
        //                "                \"title\": \"go语言实战\"\n" +
        //                "            }\n" +
        //                "        ]\n" +
        //                "    }\n" +
        //                "}";
        //
        //        JSONObject jsonObject = JSON.parseObject(jsonStr);
        //
        //        log.info(jsonObject.toString());
        //
        //        //得到所有的书
        //        List<Book> books = (List<Book>) JSONPath.eval(jsonObject, "$.store.book");
        //        log.info("books={}", books);
        //
        //        //得到所有的书名
        //        List<String> titles = (List<String>) JSONPath.eval(jsonObject, "$.store.book.title");
        //        log.info("titles={}", titles);
        //
        //        //第一本书title
        //        String title = (String) JSONPath.read(jsonStr, "$.store.book[0].title");
        //        log.info("title={}", title);
        //
        //        //price大于10元的book
        //        List<Book> list = (List<Book>) JSONPath.read(jsonStr, "$.store.book[price > 10]");
        //        log.info("price大于10元的book={}",list);
        //
        //        //price大于10元的title
        //        List<String> list2 =(List<String>) JSONPath.read(jsonStr, "$.store.book[price > 10].title");
        //        log.info("price大于10元的title={}",list2);
        //
        //        //category(类别)为科幻的book
        //        List<Book> list3 = (List<Book>) JSONPath.read(jsonStr,"$.store.book[category = '科幻']");
        //        log.info("category(类别)为科幻的book={}",list3);
        //
        //
        //        //bicycle的所有属性值
        //
        //        Collection<String> values = (Collection<String>) JSONPath.eval(jsonObject, "$.store.bicycle.*");
        //
        //        log.info("bicycle的所有属性值={}",values);
        //
        //
        //        //bicycle的color和price属性值
        //        List<String> read =(List<String>) JSONPath.read(jsonStr, "$.store.bicycle['color','price']");
        //
        //        log.info("bicycle的color和price属性值={}",read);
    }
}

