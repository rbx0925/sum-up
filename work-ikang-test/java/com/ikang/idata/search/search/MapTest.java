package com.ikang.idata.search.search;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.google.common.primitives.Ints;
import com.ikang.idata.common.entity.Dname;
import com.ikang.idata.common.entity.ResponseImpalaDTO;
import com.ikang.idata.common.entity.UserConfig;
import com.ikang.idata.common.entity.vo.DepartmentRankResultDTO;
import com.ikang.idata.common.entity.vo.DepartmentRankSearchVO;
import com.ikang.idata.common.enums.DepartmentRankFilmEnum;
import com.ikang.idata.common.exceptions.BusinessException;
import com.ikang.idata.common.utils.DoubleUtils;
import com.ikang.idata.common.utils.IdataSecurityUtil;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.SystemUserConfigFeignServiceImpl;
import com.ikang.idata.search.search.service.ESHttpClientService;
import com.ikang.idata.search.search.service.ImpalaCheckService;
import com.ikang.idata.search.search.service.SatisfiedHospitalService;
import com.ikang.idata.search.search.util.ImpalaParamUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.ikang.idata.common.consts.MagicConst.COMMA_SPLIT;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/4/20
 */
@Slf4j
public class MapTest {

    public static void main(String[] args) {
        //创建一个 HashMap 对象
        HashMap<String,Integer> hashmap=new HashMap<>();
        //往hashmap中插入数据使用put()方法
        hashmap.put("数学", 78);
        hashmap.put("英语", 88);
        hashmap.put("语文", 86);
        //遍历hashmap，这个估计是大多数人都不会的
        for (Map.Entry<String,Integer> item : hashmap.entrySet()) {
            System.out.println(item.getKey()+" "+item.getValue());
        }
		/*   以下上面遍历的是输出
		 数学 78
		语文 86
		英语 88
		 */
        // 通过上面的遍历我们可以知道hashmap中以及存在了上述数据
        // 下面在来插入一个数据看看会有什么不同
        hashmap.put("语文", 100);
        for (Map.Entry<String,Integer> item : hashmap.entrySet()) {
            System.out.println(item.getKey()+" "+item.getValue());
        }
		/*    通过下面我们可以看到语文的成绩变成了100，原来的86没有了，这就说明在 HashMap 中，key 的值不能重复，如果存在重复值，则后面的值会把前面的值给覆盖掉
		 数学 78
		语文 100
		英语 88
		*/

        // 我们可以用 containskey()方法来判断里面是否存在某个key
        if(hashmap.containsKey("数学"))
            System.out.println("存在key=数学");
        else
            System.out.println("不存在该key=数学");
        /**
         * 结果
         *
         * 数学 78
         * 语文 86
         * 英语 88
         * 数学 78
         * 语文 100
         * 英语 88
         * 存在key=数学
         */
    }


//    HashTable：线程安全，效率低。允许null键和null值。HashTable已经被淘汰了，不要在代码中再使用它。
//    HashMap：线程不安全，效率高。不允许null键和null值。


    @Test
    public void StringTest1(){
        //创建集合
        HashMap<String,String> hashMap = new HashMap<>();
        //添加元素
        hashMap.put("杨绛","钱钟书");
        hashMap.put("林徽因","徐志摩");
        hashMap.put("陈红","陈凯歌");
        hashMap.put("袁咏仪","张智霖");
        hashMap.put("孙俪","邓超");
        hashMap.put("彭麻麻","习大大");
        hashMap.put("加油呀","最棒的");

        //遍历
        Set<String>  keys = hashMap.keySet();
        for (String key : keys) {
            String value = hashMap.get(key);
            System.out.println(key + "=======" + value);
        }

        /**
         * 结果
         *
         * 加油呀=======最棒的
         * 袁咏仪=======张智霖
         * 彭麻麻=======习大大
         * 陈红=======陈凯歌
         * 林徽因=======徐志摩
         * 孙俪=======邓超
         * 杨绛=======钱钟书
         */
    }

    @Test
    public void IntegerTest1(){
        //创建集合
        HashMap<Integer,String> hashMap = new HashMap<Integer,String>();
        //添加元素
        hashMap.put(1,"钱钟书");
        hashMap.put(2,"徐志摩");
        hashMap.put(3,"陈凯歌");
        hashMap.put(4,"张智霖");
        hashMap.put(5,"邓超");
        hashMap.put(0,"习大大");
        hashMap.put(9,"最棒的");
        hashMap.put(9,"加油呀");

        //遍历
        Set<Integer>  keys = hashMap.keySet();
        for (Integer key : keys) {
            String value = hashMap.get(key);
            System.out.println(key + "=======" + value);
        }
        /**
         * 结果
         *
         * 0=======习大大
         * 1=======钱钟书
         * 2=======徐志摩
         * 3=======陈凯歌
         * 4=======张智霖
         * 5=======邓超
         * 9=======加油呀
         *
         * hashMap.put(9,"最棒的");
         * hashMap.put(9,"加油呀");
         * 被覆盖  显示最后一个
         *
         */
    }



      @Test
    public void ObjectTest1(){
        //创建集合
        HashMap<String,Student> hashMap = new HashMap<String,Student>();
        //添加元素
        hashMap.put("1",new Student(1,"杨绛","女",18F));
        hashMap.put("2",new Student(2,"hahada","男",138F));
        hashMap.put("3",new Student(3,"lalax","女",184F));
        hashMap.put("4",new Student(4,"heihe ya","男",8F));
        hashMap.put("5",new Student(5,"dangdang","女",167F));
        hashMap.put("6",new Student(6,"guigui","男",47F));
        hashMap.put("7",new Student(7,"yaya","女",98F));


        //遍历
        Set<String>  keys = hashMap.keySet();
        for (String key : keys) {
            Student value = hashMap.get(key);
            System.out.println(key + "=======" + value.getNo() + "=======" + value.getName()+ "=======" + value.getSex() + "=======" + value.getHeight());
        }

          /**
           * 结果
           *
           * 1=======1=======杨绛=======女=======18.0
           * 2=======2=======hahada=======男=======138.0
           * 3=======3=======lalax=======女=======184.0
           * 4=======4=======heihe ya=======男=======8.0
           * 5=======5=======dangdang=======女=======167.0
           * 6=======6=======guigui=======男=======47.0
           * 7=======7=======yaya=======女=======98.0
           */
      }


    @Test
    public void ObjectTest(){
        //创建集合
        HashMap<Student,String> hashMap = new HashMap<Student,String>();
        //添加元素
        hashMap.put(new Student(1,"杨绛","女",18F),"加油");
        hashMap.put(new Student(2,"hahada","男",138F),"当当当");
        hashMap.put(new Student(3,"lalax","女",184F),"哈哈哈哈");
        hashMap.put(new Student(4,"heihe ya","男",8F),"嘻嘻嘻嘻");
        hashMap.put(new Student(5,"dangdang","女",167F),"啦啦啦");
        hashMap.put(new Student(6,"guigui","男",47F),"呀呀呀呀");
        hashMap.put(new Student(7,"yaya","女",98F),"嘿嘿嘿嘿");


        //遍历
        Set<Student>  keys = hashMap.keySet();
        for (Student key : keys) {
            String value = hashMap.get(key);
            System.out.println(key + "=======" + key.getNo()+ "=======" + key.getName() + "=======" + key.getSex() + "=======" + key.getHeight() + "===========" + value);
        }

        /**
         * 结果
         *
         * Student(no=6, name=guigui, sex=男, height=47.0)=======6=======guigui=======男=======47.0===========呀呀呀呀
         * Student(no=3, name=lalax, sex=女, height=184.0)=======3=======lalax=======女=======184.0===========哈哈哈哈
         * Student(no=1, name=杨绛, sex=女, height=18.0)=======1=======杨绛=======女=======18.0===========加油
         * Student(no=4, name=heihe ya, sex=男, height=8.0)=======4=======heihe ya=======男=======8.0===========嘻嘻嘻嘻
         * Student(no=2, name=hahada, sex=男, height=138.0)=======2=======hahada=======男=======138.0===========当当当
         * Student(no=5, name=dangdang, sex=女, height=167.0)=======5=======dangdang=======女=======167.0===========啦啦啦
         * Student(no=7, name=yaya, sex=女, height=98.0)=======7=======yaya=======女=======98.0===========嘿嘿嘿嘿
         */
    }

    @Test
    public void LinkedHashMapTest(){
        //创建集合
        LinkedHashMap<String,String> lMap = new LinkedHashMap<>();
        //添加元素
        lMap.put("杨绛","钱钟书");
        lMap.put("林徽因","徐志摩");
        lMap.put("陈红","陈凯歌");
        lMap.put("袁咏仪","张智霖");
        lMap.put("孙俪","邓超");
        lMap.put("彭麻麻","习大大");
        lMap.put("加油呀","最棒的");

        //遍历
        Set<String>  keys = lMap.keySet();
        for (String key : keys) {
            String value = lMap.get(key);
            System.out.println(key + "=======" + value);
        }

        /**
         *  结果
         *
         *  杨绛=======钱钟书
         * 林徽因=======徐志摩
         * 陈红=======陈凯歌
         * 袁咏仪=======张智霖
         * 孙俪=======邓超
         * 彭麻麻=======习大大
         * 加油呀=======最棒的
         */

    }


    /**
     * 这是最常见的方法，并在大多数情况下更可取的。当你在循环中需要使用Map的键和值时，就可以使用这个方法
     * For-Each循环是Java5新引入的，所以只能在Java5以上的版本中使用。如果你遍历的map是null的话，For-Each循环会抛出NullPointerException异常，所以在遍历之前你应该判断是否为空引用。
     */
    //使用For-Each迭代entries
    @Test
    public void ForEachTest(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,100);
        map.put(2,200);
        map.put(3,300);
        map.put(4,400);
        map.put(5,500);
        map.put(6,600);
        map.put(7,700);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + "va  lue = " + entry.getValue() );
        }

        /**
         * 结果
         *
         * key = 1va  lue = 100
         * key = 2va  lue = 200
         * key = 3va  lue = 300
         * key = 4va  lue = 400
         * key = 5va  lue = 500
         * key = 6va  lue = 600
         * key = 7va  lue = 700
         */
    }


    //使用For-Each迭代keys和values
    //这个方法比entrySet迭代具有轻微的性能优势(大约快10%)并且代码更简洁
    @Test
    public void ForEachTest1(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(11,1100);
        map.put(22,2200);
        map.put(33,3300);
        map.put(44,4400);
        map.put(55,5500);
        map.put(66,6600);
        map.put(77,7700);

        //iterating over keys only
        for (Integer key : map.keySet()) {
            System.out.println("key = " + key);
        }

        /**
         * 结果
         *
         * key = 33
         * key = 66
         * key = 22
         * key = 55
         * key = 11
         * key = 44
         * key = 77
         */

        //iterating over value only
        for (Integer value : map.values()) {
            System.out.println("value = " + value);
        }

        /**
         * 结果
         *
         * value = 3300
         * value = 6600
         * value = 2200
         * value = 5500
         * value = 1100
         * value = 4400
         * value = 7700
         */
    }


    // 使用Iterator迭代
    //使用泛型
    @Test
    public void IteratorTest1(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(111,11100);
        map.put(222,22200);
        map.put(333,33300);
        map.put(444,44400);
        map.put(555,55500);
        map.put(666,66600);
        map.put(777,77700);

        Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<Integer, Integer> entry = entries.next();
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }

        /**
         * 结果
         *
         * key = 777, value = 77700
         * key = 666, value = 66600
         * key = 555, value = 55500
         * key = 444, value = 44400
         * key = 333, value = 33300
         * key = 222, value = 22200
         * key = 111, value = 11100
         */
    }


    // 使用Iterator迭代
    //不使用泛型
    @Test
    public void IteratorTest(){
        Map map = new HashMap<>();
        map.put(111,11100);
        map.put(222,22200);
        map.put(333,33300);
        map.put(444,44400);
        map.put(555,55500);
        map.put(666,66600);
        map.put(777,77700);

        Iterator entries = map.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry entry =(Map.Entry) entries.next();
            Integer key = (Integer) entry.getKey();
            Integer value = (Integer) entry.getValue();
            System.out.println("key = " + key + ", value = " + value);
        }

        /**
         * 结果
         *
         *  key = 777, value = 77700
         * key = 666, value = 66600
         * key = 555, value = 55500
         * key = 444, value = 44400
         * key = 333, value = 33300
         * key = 222, value = 22200
         * key = 111, value = 11100
         */

    }

    // 迭代keys并搜索values（低效的）
    @Test
    public void IteratorTest3(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(111,11100);
        map.put(222,22200);
        map.put(333,33300);
        map.put(444,44400);
        map.put(555,55500);
        map.put(666,66600);
        map.put(777,77700);

        for (Integer key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println("key = " + key + ", value = " + value);
        }

        /**
         * 结果
         *
         * key = 777, value = 77700
         * key = 666, value = 66600
         * key = 555, value = 55500
         * key = 444, value = 44400
         * key = 333, value = 33300
         * key = 222, value = 22200
         * key = 111, value = 11100
         */
    }


    /**
     * 这个方法看上去比方法#1更简洁，但是实际上它更慢更低效，通过key得到value值更耗时（这个方法在所有实现map接口的map中比方法#1慢20%-200%）。如果你安装了FindBugs，它将检测并警告你这是一个低效的迭代。这个方法应该避免
     */


    @Test
    public void test(){

        //创建集合
        HashMap<Integer,String> hashMap = new HashMap<Integer,String>();
        //添加元素
        hashMap.put(11,"钱钟书");
        hashMap.put(22,"徐志摩");
        hashMap.put(33,"陈凯歌");
        hashMap.put(44,"张智霖");
        hashMap.put(55,"邓超");
        hashMap.put(01,"习大大");
        hashMap.put(90,"最棒的");
        hashMap.put(90,"加油呀");

       //方法一
        for (Map.Entry<Integer,String> entry : hashMap.entrySet()) {
            System.out.println("方法一 : key = " + entry.getKey() + "------- value = " +entry.getValue());
        }

        /**
         * 结果
         *
         * 方法一 : key = 33------- value = 陈凯歌
         * 方法一 : key = 1------- value = 习大大
         * 方法一 : key = 22------- value = 徐志摩
         * 方法一 : key = 55------- value = 邓超
         * 方法一 : key = 90------- value = 加油呀
         * 方法一 : key = 11------- value = 钱钟书
         * 方法一 : key = 44------- value = 张智霖
         */

        //方法二
        //key
        for (Integer key : hashMap.keySet()) {
            System.out.println("方法二 : key" + key);
        }

        /**
         * 结果
         *
         *方法二 : key33
         * 方法二 : key1
         * 方法二 : key22
         * 方法二 : key55
         * 方法二 : key90
         * 方法二 : key11
         * 方法二 : key44
         */

        //value
        for (String value : hashMap.values()) {
            System.out.println("方法二 : value" + value);
        }

        /**
         * 结果
         *
         *方法二 : value陈凯歌
         * 方法二 : value习大大
         * 方法二 : value徐志摩
         * 方法二 : value邓超
         * 方法二 : value加油呀
         * 方法二 : value钱钟书
         * 方法二 : value张智霖
         */


        //方法三
        Iterator<Map.Entry<Integer, String>> entries = hashMap.entrySet().iterator();
        while (entries.hasNext()){
            Map.Entry<Integer, String> entry = entries.next();
            System.out.println(" 方法三 : key = " + entry.getKey() + "------- value = " + entry.getValue());
        }

        /**
         * 结果
         *
         *  方法三 : key = 33------- value = 陈凯歌
         *  方法三 : key = 1------- value = 习大大
         *  方法三 : key = 22------- value = 徐志摩
         *  方法三 : key = 55------- value = 邓超
         *  方法三 : key = 90------- value = 加油呀
         *  方法三 : key = 11------- value = 钱钟书
         *  方法三 : key = 44------- value = 张智霖
         */

        //方法四
        for (Integer key : hashMap.keySet()) {
            String value = hashMap.get(key);
            System.out.println(" 方法四 : key = " + key + " , value = " + value);
        }

        /**
         * 结果
         *
         * 方法四 : key = 33 , value = 陈凯歌
         *  方法四 : key = 1 , value = 习大大
         *  方法四 : key = 22 , value = 徐志摩
         *  方法四 : key = 55 , value = 邓超
         *  方法四 : key = 90 , value = 加油呀
         *  方法四 : key = 11 , value = 钱钟书
         *  方法四 : key = 44 , value = 张智霖
         */


    }

    @Test
    public void MapTest(){

        //key -> value 键值对的数据结构
        //key重复的覆盖
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("s01",100);
        map.put("s02",200);
        map.put("s03",300);
        map.put("s03",900);
        map.put("s05",500);
        //清空map集合所有元素
        //map.clear();
        //判断指定的key是不是存在
        //map.containsKey()
        //判断指定的value是不是存在
        //map.containsValue()
        //返回元素的个数
        //System.out.println(map.size());

        System.out.println(map.get("s05"));
        //判断map是不是空的 size() == 0
        //map.isEmpty()
        //删除元素，指定key
        map.remove("s01");
        //删除元素，指定key并指定值，删除失败
        map.remove("s02",200);

        System.out.println(map.size());

        map.forEach((k,v)->System.out.printf("k"+k,"v"+ v));
        //map.keySet()  返回key组成的Set集合
        //map.values() 返回的values组成的Collection<Integer>集合
        for(String k : map.keySet()){
            System.out.printf("k"+ k,map.get(k));
        }


        for(int i : map.values()){
            System.out.println(i);
        }

    }

    @Test
    public void  MapTest2(){

        //HashMap的创建和值添加
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("appName", "IPAD APP");
        hashMap.put("appVer", "2.0");
        hashMap.put("cmd","CV_Services.GetIndustryNews('consumer discretionary','en',0,10,'','XSHG,XSHE')");
        hashMap.put("alg", "alg");
        hashMap.put("appID", "device02154698");

        //泛型HashMap的遍历
        StringBuffer urlBuffer = new StringBuffer();
        Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            urlBuffer.append(entry.getKey());
            urlBuffer.append("&");
            urlBuffer.append(entry.getValue());
            urlBuffer.append("&;");
        }

        String url="";
        try {
            url= URLEncoder.encode(urlBuffer.toString(), "UTF-8");
            //URL编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private final SatisfiedHospitalService satisfiedHospitalService;
    private final AuthorityFeignServiceImpl authorityFeignService;
    private final ESHttpClientService esHttpClientService;
    private final SystemUserConfigFeignServiceImpl systemUserConfigFeignService;
    private final ImpalaCheckService impalaCheckService;

    public MapTest(AuthorityFeignServiceImpl authorityFeignService,
                                 ESHttpClientService esHttpClientService,
                                 SystemUserConfigFeignServiceImpl systemUserConfigFeignService,
                                 ImpalaCheckService impalaCheckService,
                                 SatisfiedHospitalService satisfiedHospitalService) {
        this.authorityFeignService = authorityFeignService;
        this.esHttpClientService = esHttpClientService;
        this.systemUserConfigFeignService = systemUserConfigFeignService;
        this.impalaCheckService = impalaCheckService;
        this.satisfiedHospitalService = satisfiedHospitalService;
    }




    /**
     * 科室看板实时接口查询
     */
    @Value("${kanban.departmentrank.technicalofficeaggImpalaUrl}")
    private String technicalofficeaggImpalaUrl;



    public List<DepartmentRankResultDTO> findRealTime(DepartmentRankSearchVO vo) {
        Map<String, Object> departmentRankImpala = ImpalaParamUtil.convertImpalaParam(vo);
        // 科室code
        UserConfig userConfig = systemUserConfigFeignService.queryUserConfig(IdataSecurityUtil.getUserId());
        String departmentIds = userConfig.getDepartmentId();
        // 没有关注的科室 直接返回
        if (StringUtil.isEmpty(departmentIds)) {
            throw new BusinessException("该分院没有科室数据,请检查!");
        }
        departmentRankImpala.put(DepartmentRankFilmEnum.DEPARTMENT_CODE.getFields(), departmentIds);
        ResponseImpalaDTO impalaDTO = esHttpClientService.exchange(technicalofficeaggImpalaUrl,
                HttpMethod.POST,
                ResponseImpalaDTO.class,
                departmentRankImpala
        );
        List<DepartmentRankResultDTO> returnList = new ArrayList<>();
        List<DepartmentRankResultDTO> departmentRankImpalaList = Convert.convert(new TypeReference<List<DepartmentRankResultDTO>>() {}, impalaDTO.getData());
        //拿到对应的科室 和 可是对应的数据
        Map<String, List<DepartmentRankResultDTO>> codeToDepartment = departmentRankImpalaList.stream().collect(Collectors.groupingBy(DepartmentRankResultDTO::getDepartmentCode));
        //循环遍历 科室code  如果有重复的科室 就累加  得到对应的数据值
        List<DepartmentRankResultDTO> departmentImpalaList = new ArrayList<>();
        for (String s : codeToDepartment.keySet()) {
            DepartmentRankResultDTO departmentDTO = new DepartmentRankResultDTO();
            for (int i = 0; i <departmentRankImpalaList.size() ; i++) {
                DepartmentRankResultDTO departmentRankResultDTO = departmentRankImpalaList.get(i);
                if (s.equals(departmentRankResultDTO.getDepartmentCode())) {
                    departmentDTO.setDepartmentName(departmentRankResultDTO.getDepartmentName());
                    departmentDTO.setDepartmentCode(departmentRankResultDTO.getDepartmentCode());
                    BigDecimal regNum = departmentDTO.getRegNum();
                    departmentDTO.setRegNum(BigInteger.ZERO.equals(regNum)?departmentRankResultDTO.getRegNum():regNum.add(departmentRankResultDTO.getRegNum()));
                    BigDecimal yuYueNum = departmentDTO.getYuyueNum();
                    departmentDTO.setYuyueNum(BigInteger.ZERO.equals(yuYueNum)?departmentRankResultDTO.getYuyueNum():yuYueNum.add(departmentRankResultDTO.getYuyueNum()));
                    BigDecimal waitNum = departmentDTO.getWaitNum();
                    departmentDTO.setWaitNum(BigInteger.ZERO.equals(waitNum)?departmentRankResultDTO.getWaitNum():waitNum.add(departmentRankResultDTO.getWaitNum()));
                    BigDecimal totalNum = departmentDTO.getTotalNum();
                    departmentDTO.setTotalNum(BigInteger.ZERO.equals(totalNum)?departmentRankResultDTO.getTotalNum():totalNum.add(departmentRankResultDTO.getTotalNum()));
                    BigDecimal finishNum = departmentDTO.getFinishNum();
                    departmentDTO.setFinishNum(BigInteger.ZERO.equals(finishNum)?departmentRankResultDTO.getFinishNum():finishNum.add(departmentRankResultDTO.getFinishNum()));
                    BigDecimal girlTotalNum = departmentDTO.getGirlTotalNum();
                    departmentDTO.setGirlTotalNum(BigInteger.ZERO.equals(girlTotalNum)?departmentRankResultDTO.getGirlTotalNum():girlTotalNum.add(departmentRankResultDTO.getGirlTotalNum()));
                    BigDecimal yuyueGirlNum = departmentDTO.getYuyueGirlNum();
                    departmentDTO.setYuyueGirlNum(BigInteger.ZERO.equals(yuyueGirlNum)?departmentRankResultDTO.getYuyueGirlNum():yuyueGirlNum.add(departmentRankResultDTO.getYuyueGirlNum()));
                    BigDecimal yuyueItemGirlNum = departmentDTO.getYuyueItemGirlNum();
                    departmentDTO.setYuyueItemGirlNum(BigInteger.ZERO.equals(yuyueItemGirlNum)?departmentRankResultDTO.getYuyueItemGirlNum():yuyueItemGirlNum.add(departmentRankResultDTO.getYuyueItemGirlNum()));
                    BigDecimal yuyueItemNum = departmentDTO.getYuyueItemNum();
                    departmentDTO.setYuyueItemNum(BigInteger.ZERO.equals(yuyueItemNum)?departmentRankResultDTO.getYuyueItemNum():yuyueItemNum.add(departmentRankResultDTO.getYuyueItemNum()));
                    BigDecimal regGirlNum = departmentDTO.getRegGirlNum();
                    departmentDTO.setRegGirlNum(BigInteger.ZERO.equals(regGirlNum)?departmentRankResultDTO.getRegGirlNum():regGirlNum.add(departmentRankResultDTO.getRegGirlNum()));
                    BigDecimal abandonNum = departmentDTO.getAbandonNum();
                    departmentDTO.setAbandonNum(BigInteger.ZERO.equals(abandonNum)?departmentRankResultDTO.getAbandonNum():abandonNum.add(departmentRankResultDTO.getAbandonNum()));
                }
            }
            departmentImpalaList.add(departmentDTO);
        }
        //最后在放在需要计算出结果的值
        for (int i = 0; i <departmentImpalaList.size() ; i++) {
            DepartmentRankResultDTO departmentRankResultDTO = departmentImpalaList.get(i);
            //regNum
            BigDecimal regNum = departmentRankResultDTO.getRegNum();
            //yuYueNum
            BigDecimal yuyueNum = departmentRankResultDTO.getYuyueNum();
            //waitNum
            BigDecimal waitNum = departmentRankResultDTO.getWaitNum();
            //totalNum
            BigDecimal totalNum = departmentRankResultDTO.getTotalNum();
            //finishNum
            BigDecimal finishNum = departmentRankResultDTO.getFinishNum();
            //abandonNum
            BigDecimal abandonNum = departmentRankResultDTO.getAbandonNum();
            //检项数量-待检查项目数量-已完成项目数量-放弃/延期项目数量 = 检查中 4.18号新加
            //待检查项目数量+已完成项目数量 = waitAndFinishAdd
            BigDecimal waitAndFinishAdd = DoubleUtils.add(String.valueOf(waitNum), String.valueOf(finishNum));
            //waitAndFinishAdd+放弃/延期项目数量 = AbandonAndWaitAdd
            BigDecimal abandonAndWaitAdd = DoubleUtils.add(String.valueOf(waitAndFinishAdd), String.valueOf(abandonNum));
            //检项数量-AbandonAndWaitAdd
            BigDecimal examinationBigDecimal = DoubleUtils.subtract4(String.valueOf(totalNum), String.valueOf(abandonAndWaitAdd));
            departmentRankResultDTO.setExamination(examinationBigDecimal);
            //检查中占比   检查中/总需完成项目
            BigDecimal examinationRate = DoubleUtils.divide4(String.valueOf(examinationBigDecimal), String.valueOf(totalNum));
            departmentRankResultDTO.setExaminationRate(examinationRate);
            // 预约到检率 已登记人数/预约人数
            BigDecimal arrivalRateBigDecimal = DoubleUtils.divide4(String.valueOf(yuyueNum), String.valueOf(regNum));
            departmentRankResultDTO.setArrivalRate(arrivalRateBigDecimal);
            // 待检查项目/总需完成项目
            BigDecimal waitNumRateBigDecimal = DoubleUtils.divide4(String.valueOf(waitNum), String.valueOf(totalNum));
            departmentRankResultDTO.setWaitNumRate(waitNumRateBigDecimal);
            // 已完成项目/总需完成项目
            BigDecimal finishNumRateBigDecimal = DoubleUtils.divide4(String.valueOf(finishNum), String.valueOf(totalNum));
            departmentRankResultDTO.setFinishNumRate(finishNumRateBigDecimal);
            // 放弃\延期项目/总需完成项目
            BigDecimal abandonNumRateBigDecimal = DoubleUtils.divide4(String.valueOf(abandonNum), String.valueOf(totalNum));
            departmentRankResultDTO.setAbandonNumRate(abandonNumRateBigDecimal);
        }
        Map<String, String> idAndName = satisfiedHospitalService.showDname().stream().collect(Collectors.toMap(Dname::getDcode, Dname::getDname));
        List<String> departmentCodes = departmentImpalaList.stream().map(DepartmentRankResultDTO::getDepartmentCode).filter(StringUtil::isNotEmpty).collect(Collectors.toList());
        departmentImpalaList.addAll(Stream.of(userConfig.getDepartmentId().split(COMMA_SPLIT)).filter(StringUtil::isNotEmpty)
                .filter(s -> !departmentCodes.contains(s))
                .map(s -> new DepartmentRankResultDTO(s, idAndName.get(s)))
                .collect(Collectors.toList()));

        //页面科室位置的固定
        List<String> collect = Stream.of(userConfig.getDepartmentId().split(COMMA_SPLIT)).collect(Collectors.toList());
        for (String s : collect) {
            for (DepartmentRankResultDTO depart: departmentImpalaList) {
                if (s.equals(depart.getDepartmentCode())) {
                    returnList.add(depart);
                }
            }
        }
        return returnList;
    }

    @Test
    public void testK() {
        {
            List<String[]> eggs = new ArrayList<>();

            // 第一箱鸡蛋
            eggs.add(new String[]{"鸡蛋_1", "鸡蛋_1", "鸡蛋_1", "鸡蛋_1", "鸡蛋_1"});
            // 第二箱鸡蛋
            eggs.add(new String[]{"鸡蛋_2", "鸡蛋_2", "鸡蛋_2", "鸡蛋_2", "鸡蛋_2"});

            // 自增生成组编号
            AtomicInteger group = new AtomicInteger(1);
            // 自增生成学生编号
            AtomicInteger student = new AtomicInteger(1);

            eggs.stream()
                    .map(x -> Arrays.stream(x)
                            .map(y -> y.replace("鸡", "煎")))
                    .forEach(x -> System.out.println("组" + (group.getAndIncrement()) + ":" + Arrays.toString(x.toArray())));
        /*
         控制台打印：------------
         组1:[煎蛋_1, 煎蛋_1, 煎蛋_1, 煎蛋_1, 煎蛋_1]
         组2:[煎蛋_2, 煎蛋_2, 煎蛋_2, 煎蛋_2, 煎蛋_2]
        */

            eggs.stream()
                    .flatMap(x -> Arrays.stream(x)
                            .map(y -> y.replace("鸡", "煎")))
                    .forEach(x -> System.out.println("学生" + student.getAndIncrement() + ":" + x));
        /*
43         控制台打印：------------
44         学生1:煎蛋_1
45         学生2:煎蛋_1
46         学生3:煎蛋_1
47         学生4:煎蛋_1
48         学生5:煎蛋_1
49         学生6:煎蛋_2
50         学生7:煎蛋_2
51         学生8:煎蛋_2
52         学生9:煎蛋_2
53         学生10:煎蛋_2
54          */


        }
    }



}
