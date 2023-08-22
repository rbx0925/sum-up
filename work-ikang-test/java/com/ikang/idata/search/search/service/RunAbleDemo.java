package com.ikang.idata.search.search.service;

import cn.hutool.core.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.util.*;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/6/30
 */
public class RunAbleDemo implements Runnable{
        private Thread t;
        private String threadName;

    RunAbleDemo( String name) {
            threadName = name;
            System.out.println("Creating " +  threadName );
        }

        public void run() {
            System.out.println("Running " +  threadName );
            try {
                for(int i = 4; i > 0; i--) {
                    System.out.println("Thread: " + threadName + ", " + i);
                    // 让线程睡眠一会
                    Thread.sleep(50);
                }
            }catch (InterruptedException e) {
                System.out.println("Thread " +  threadName + " interrupted.");
            }
            System.out.println("Thread " +  threadName + " exiting.");
        }

        public void start () {
            System.out.println("Starting " +  threadName );
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }
    }

    class TestThread {

        public static void main(String args[]) {
            DateMethod();
            HashtableMethod();
            RunAbleDemo R1 = new RunAbleDemo("Thread-1");
            R1.start();

            RunAbleDemo R2 = new RunAbleDemo("Thread-2");
            R2.start();
        }

        /**
         * Creating Thread-1
         * Starting Thread-1
         * Creating Thread-2
         * Starting Thread-2
         * Running Thread-1
         * Thread: Thread-1, 4
         * Running Thread-2
         * Thread: Thread-2, 4
         * Thread: Thread-1, 3
         * Thread: Thread-2, 3
         * Thread: Thread-1, 2
         * Thread: Thread-2, 2
         * Thread: Thread-1, 1
         * Thread: Thread-2, 1
         * Thread Thread-1 exiting.
         * Thread Thread-2 exiting.
         */


        @Test
        public void test() {
            String dirname = "/tmp";
            File f1 = new File(dirname);
            if (f1.isDirectory()) {
                System.out.println("目录 " + dirname);
                String s[] = f1.list();
                for (int i = 0; i < s.length; i++) {
                    File f = new File(dirname + "/" + s[i]);
                    if (f.isDirectory()) {
                        System.out.println(s[i] + " 是一个目录");
                    } else {
                        System.out.println(s[i] + " 是一个文件");
                    }
                }
            } else {
                System.out.println(dirname + " 不是一个目录");
            }
        }

        /**
         * 目录 /tmp
         * bin 是一个目录
         * lib 是一个目录
         * demo 是一个目录
         * test.txt 是一个文件
         * README 是一个文件
         * index.html 是一个文件
         * include 是一个目录
         */

        private TransportClient client;
        /**
         * 使用QueryBuilder
         * termQuery("key", obj) 完全匹配
         * termsQuery("key", obj1, obj2..)   一次匹配多个值
         * matchQuery("key", Obj) 单个匹配, field不支持通配符, 前缀具高级特性
         * multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行
         * matchAllQuery();         匹配所有文件
         */
        @org.junit.Test
        public void testQueryBuilder() {
            QueryBuilder queryBuilder1 = QueryBuilders.termQuery("user", "kimchy");
            //QueryBuilders queryBuilder3 = QueryBuilders.termQuery("user", "kimchy");
            QueryBuilders.termsQuery("user", new ArrayList<String>().add("kimchy"));
            QueryBuilder queryBuilder4 = QueryBuilders.matchQuery("user", "kimchy");
            QueryBuilder queryBuilderOne = QueryBuilders.multiMatchQuery("kimchy", "user", "message", "gender");
            QueryBuilder queryBuilderTwo = QueryBuilders.matchAllQuery();
            searchFunction(queryBuilderOne);

        }

        /**
         * 组合查询
         * must(QueryBuilders) :   AND
         * mustNot(QueryBuilders): NOT
         * should:                  : OR
         */
        @org.junit.Test
        public void testQueryBuilder2() {
            QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                    .must(QueryBuilders.termQuery("user", "kimchy"))
                    .mustNot(QueryBuilders.termQuery("message", "nihao"))
                    .should(QueryBuilders.termQuery("gender", "male"));
            searchFunction(queryBuilder);
        }

        /**
         * 只查询一个id的
         * QueryBuilders.idsQuery(String...type).ids(Collection<String> ids)
         */
//    @Test
//    public void testIdsQuery() {
//        QueryBuilder queryBuilder = QueryBuilders.idsQuery().ids("0");
//        searchFunction(queryBuilder);
//    }

        /**
         * 包裹查询, 高于设定分数, 不计算相关性
         */
        @org.junit.Test
        public void testConstantScoreQuery() {
            QueryBuilder queryBuilder = QueryBuilders.constantScoreQuery(QueryBuilders.termQuery("name", "kimchy")).boost(2.0f);
            searchFunction(queryBuilder);
            // 过滤查询
//        QueryBuilders.constantScoreQuery(FilterBuilders.termQuery("name", "kimchy")).boost(2.0f);

        }

        /**
         * disMax查询
         * 对子查询的结果做union, score沿用子查询score的最大值,
         * 广泛用于muti-field查询
         */
        @org.junit.Test
        public void testDisMaxQuery() {
            QueryBuilder queryBuilder = QueryBuilders.disMaxQuery()
                    .add(QueryBuilders.termQuery("user", "kimch"))  // 查询条件
                    .add(QueryBuilders.termQuery("message", "hello"))
                    .boost(1.3f)
                    .tieBreaker(0.7f);
            searchFunction(queryBuilder);
        }

        /**
         * 模糊查询
         * 不能用通配符, 不知道干啥用
         */
        @org.junit.Test
        public void testFuzzyQuery() {
            QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("user", "kimch");
            searchFunction(queryBuilder);
        }

        /**
         * 父或子的文档查询
         */
//    @Test
//    public void testChildQuery() {
//        QueryBuilder queryBuilder = QueryBuilders.hasChildQuery("sonDoc", QueryBuilders.termQuery("name", "vini"));
//        searchFunction(queryBuilder);
//    }

        /**
         * moreLikeThisQuery: 实现基于内容推荐, 支持实现一句话相似文章查询
         * {
         "more_like_this" : {
         "fields" : ["title", "content"],   // 要匹配的字段, 不填默认_all
         "like_text" : "text like this one",   // 匹配的文本
         }
         }

         percent_terms_to_match：匹配项（term）的百分比，默认是0.3

         min_term_freq：一篇文档中一个词语至少出现次数，小于这个值的词将被忽略，默认是2

         max_query_terms：一条查询语句中允许最多查询词语的个数，默认是25

         stop_words：设置停止词，匹配时会忽略停止词

         min_doc_freq：一个词语最少在多少篇文档中出现，小于这个值的词会将被忽略，默认是无限制

         max_doc_freq：一个词语最多在多少篇文档中出现，大于这个值的词会将被忽略，默认是无限制

         min_word_len：最小的词语长度，默认是0

         max_word_len：最多的词语长度，默认无限制

         boost_terms：设置词语权重，默认是1

         boost：设置查询权重，默认是1

         analyzer：设置使用的分词器，默认是使用该字段指定的分词器
         */
//    @Test
//    public void testMoreLikeThisQuery() {
//        QueryBuilder queryBuilder = QueryBuilders.moreLikeThisQuery("user")
//                .like("kimchy");
////                            .minTermFreq(1)         //最少出现的次数
////                            .maxQueryTerms(12);        // 最多允许查询的词语
//        searchFunction(queryBuilder);
//    }

        /**
         * 前缀查询
         */
        @org.junit.Test
        public void testPrefixQuery() {
            QueryBuilder queryBuilder = QueryBuilders.matchQuery("user", "kimchy");
            searchFunction(queryBuilder);
        }

        /**
         * 查询解析查询字符串
         */
        @org.junit.Test
        public void testQueryString() {
            QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("+kimchy");
            searchFunction(queryBuilder);
        }

        /**
         * 范围内查询
         */
        public void testRangeQuery() {
            QueryBuilder queryBuilder = QueryBuilders.rangeQuery("user")
                    .from("kimchy")
                    .to("wenbronk")
                    .includeLower(true)     // 包含上界
                    .includeUpper(true);      // 包含下届
            searchFunction(queryBuilder);
        }

        /**
         * 跨度查询
         */
//    @Test
//    public void testSpanQueries() {
//        QueryBuilder queryBuilder1 = QueryBuilders.spanFirstQuery(QueryBuilders.spanTermQuery("name", "葫芦580娃"), 30000);     // Max查询范围的结束位置
//
//        QueryBuilder queryBuilder2 = QueryBuilders.spanNearQuery()
//                .clause(QueryBuilders.spanTermQuery("name", "葫芦580娃")) // Span Term Queries
//                .clause(QueryBuilders.spanTermQuery("name", "葫芦3812娃"))
//                .clause(QueryBuilders.spanTermQuery("name", "葫芦7139娃"))
//                .slop(30000)                                               // Slop factor
//                .inOrder(false)
//                .collectPayloads(false);
//
//        // Span Not
//        QueryBuilder queryBuilder3 = QueryBuilders.spanNotQuery()
//                .include(QueryBuilders.spanTermQuery("name", "葫芦580娃"))
//                .exclude(QueryBuilders.spanTermQuery("home", "山西省太原市2552街道"));
//
//        // Span Or
//        QueryBuilder queryBuilder4 = QueryBuilders.spanOrQuery()
//                .clause(QueryBuilders.spanTermQuery("name", "葫芦580娃"))
//                .clause(QueryBuilders.spanTermQuery("name", "葫芦3812娃"))
//                .clause(QueryBuilders.spanTermQuery("name", "葫芦7139娃"));
//
//        // Span Term
//        QueryBuilder queryBuilder5 = QueryBuilders.spanTermQuery("name", "葫芦580娃");
//    }
//
//    /**
//     * 测试子查询
//     */
//    @Test
//    public void testTopChildrenQuery() {
//        QueryBuilders.hasChildQuery("tweet",
//                QueryBuilders.termQuery("user", "kimchy"))
//                .scoreMode("max");
//    }

        /**
         * 通配符查询, 支持 *
         * 匹配任何字符序列, 包括空
         * 避免* 开始, 会检索大量内容造成效率缓慢
         */
        @org.junit.Test
        public void testWildCardQuery() {
            QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("user", "ki*hy");
            searchFunction(queryBuilder);
        }

        /**
         * 嵌套查询, 内嵌文档查询
         */
//    @Test
//    public void testNestedQuery() {
//        QueryBuilder queryBuilder = QueryBuilders.nestedQuery("location", QueryBuilders.boolQuery()
//                        .must(QueryBuilders.matchQuery("location.lat", 0.962590433140581))
//                        .must(QueryBuilders.rangeQuery("location.lon").lt(36.0000).gt(0.000)))
//                .scoreMode("total");
//
//    }

        /**
         * 测试索引查询
         */
//    @Test
//    public void testIndicesQueryBuilder () {
//        QueryBuilder queryBuilder = QueryBuilders.indicesQuery(
//                QueryBuilders.termQuery("user", "kimchy"), "index1", "index2")
//                .noMatchQuery(QueryBuilders.termQuery("user", "kimchy"));
//
//    }



        /**
         * 查询遍历抽取
         * @param queryBuilder
         */
        private void searchFunction(QueryBuilder queryBuilder) {
            SearchResponse response = client.prepareSearch("twitter")
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setScroll(new TimeValue(60000))
                    .setQuery(queryBuilder)
                    .setSize(100).execute().actionGet();

//        while(true) {
//            response = client.prepareSearchScroll(response.getScrollId())
//                    .setScroll(new TimeValue(60000)).execute().actionGet();
//            for (SearchHit hit : response.getHits()) {
//                Iterator<Entry<String, Object>> iterator = hit.getSource().entrySet().iterator();
//                while(iterator.hasNext()) {
//                    Entry<String, Object> next = iterator.next();
//                    System.out.println(next.getKey() + ": " + next.getValue());
//                    if(response.getHits().hits().length == 0) {
//                        break;
//                    }
//                }
//            }
//            break;
        }
//        testResponse(response);
        // }


        /**
         * 对response结果的分析
         * @param response
         */
//    public void testResponse(SearchResponse response) {
//        // 命中的记录数
//        long totalHits = response.getHits().totalHits();
//
//        for (SearchHit searchHit : response.getHits()) {
//            // 打分
//            float score = searchHit.getScore();
//            // 文章id
//            int id = Integer.parseInt(searchHit.getSource().get("id").toString());
//            // title
//            String title = searchHit.getSource().get("title").toString();
//            // 内容
//            String content = searchHit.getSource().get("content").toString();
//            // 文章更新时间
//            long updatetime = Long.parseLong(searchHit.getSource().get("updatetime").toString());
//        }
//    }

        /**
         * 对结果设置高亮显示
         */
        public void testHighLighted() {
        /*  5.0 版本后的高亮设置
         * client.#().#().highlighter(hBuilder).execute().actionGet();
        HighlightBuilder hBuilder = new HighlightBuilder();
        hBuilder.preTags("<h2>");
        hBuilder.postTags("</h2>");
        hBuilder.field("user");        // 设置高亮显示的字段
        */
//        // 加入查询中
//        SearchResponse response = client.prepareSearch("blog")
//                .setQuery(QueryBuilders.matchAllQuery())
//                .addHighlightedField("user")        // 添加高亮的字段
//                .setHighlighterPreTags("<h1>")
//                .setHighlighterPostTags("</h1>")
//                .execute().actionGet();

//        // 遍历结果, 获取高亮片段
//        SearchHits searchHits = response.getHits();
//        for(SearchHit hit:searchHits){
//            System.out.println("String方式打印文档搜索内容:");
//            System.out.println(hit.getSourceAsString());
//            System.out.println("Map方式打印高亮内容");
//            System.out.println(hit.getHighlightFields());
//
//            System.out.println("遍历高亮集合，打印高亮片段:");
//            Text[] text = hit.getHighlightFields().get("title").getFragments();
//            for (Text str : text) {
//                System.out.println(str.string());
//            }
//        }
        }

        @org.junit.Test
        public void test01() {
            // 创建一个数组
            ArrayList<String> sites = new ArrayList<>();

            sites.add("Google");
            sites.add("Runoob");
            sites.add("Taobao");
            System.out.println("网站列表: " + sites);


            // 删除元素 Taobao
            boolean result = sites.remove("Taobao");
            System.out.println("Taoabo 是否被删除? " + result);
            System.out.println("使用 remove() 后: " + sites);
        }

        @org.junit.Test
        public void test02() {
            // 创建一个数组
            ArrayList<String> sites = new ArrayList<>();

            sites.add("Google");
            sites.add("Runoob");
            sites.add("Taobao");
            System.out.println("网站列表: " + sites);


            // 删除位置索引为 2 的元素
            String element = sites.remove(2);
            System.out.println("使用 remove() 后: " + sites);
            System.out.println("移除的元素: " + element);

        }

        private static void HashtableMethod() {
            Hashtable hash=new Hashtable(2,(float)0.8);
//创建了一个哈希表的对象hash，初始容量为2，装载因子为0.8

            hash.put("Jiangsu","Nanjing");
//将字符串对象"Jiangsu"给定一关键字"Nanjing",并将它加入hash
            hash.put("Beijing","Beijing");
            hash.put("Zhejiang","Hangzhou");

            System.out.println("The hashtable hash1 is: "+hash);
            System.out.println("The size of this hash table is "+hash.size());
//打印hash的内容和大小
//------------------------------------分割线--------------------------------------------------------
            Enumeration enum1=hash.elements();
            System.out.print("The element of hash is: ");
            while(enum1.hasMoreElements())
                System.out.print(enum1.nextElement()+" ");
            System.out.println();
//依次打印hash中的内容
            if(hash.containsKey("Jiangsu"))
                System.out.println("The capatial of Jiangsu is "+hash.get("Jiangsu"));
            hash.remove("Beijing");
//删除关键字Beijing对应对象
            System.out.println("The hashtable hash2 is: "+hash);
            System.out.println("The size of this hash table is "+hash.size());
        }

        private static void DateMethod() {
            Date today=new Date();
            //today中的日期被设成创建时刻的日期和时间，假设创建时刻为1997年3月23日17时51分54秒。
            System.out.println("Today's date is "+today);
            //返回一般的时间表示法，本例中结果为Today's date is Fri May 23 17:51:54 1997
            System.out.println("Today's date(Internet GMT)is:" +today.toGMTString());
            //返回结果为GMT时间表示法，本例中结果为Today's date(Internet GMT)is: 23 May 1997 09:51:54:GMT
            System.out.println("Today's date(Locale) is:" +today.toLocaleString());
            //返回结果为本地习惯的时间表示法，结果为Today's date(Locale)is:05/23/97 17:51:54
            System.out.println("Today's year is: "+today.getYear());
            System.out.println("Today's month is: "+(today.getMonth()+1));
            System.out.println("Today's date is: "+today.getDate());
            //调用Date类中方法，获取年月日的值。下面调用了不同的构造方法来创建Date类的对象。
            Date day1=new Date(100,1,23,10,12,34);
            System.out.println("Day1's date is: "+day1);
            Date day2=new Date("Sat 12 Aug 1996 13:3:00");
            System.out.println("Day2's date is: "+day2);
            long l= Date.parse("Sat 5 Aug 1996 13:3:00 GMT+0800");
            Date day3= new Date(l);
            System.out.println("Day3's date(GMT)is: "+day3.toGMTString());
            System.out.println("Day3's date(Locale)is: "+day3.toLocaleString());
            System.out.println("Day3's time zone offset is:"+day3.getTimezoneOffset());
            System.out.println("90 度的正弦值：" + Math.sin(Math.PI/2));
            System.out.println("0度的余弦值：" + Math.cos(0));
            System.out.println("60度的正切值：" + Math.tan(Math.PI/3));
            System.out.println("1的反正切值： " + Math.atan(1));
            System.out.println("π/2的角度值：" + Math.toDegrees(Math.PI/2));
            System.out.println(Math.PI);
            //------------------------------------分割线--------------------------------------------------------
            // 初始化 Date 对象
            Date dateLast = new Date();

            //c的使用
            System.out.printf("全部日期和时间信息：%tc%n",dateLast);
            //f的使用
            System.out.printf("年-月-日格式：%tF%n",dateLast);
            //d的使用
            System.out.printf("月/日/年格式：%tD%n",dateLast);
            //r的使用
            System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",dateLast);
            //t的使用
            System.out.printf("HH:MM:SS格式（24时制）：%tT%n",dateLast);
            //R的使用
            System.out.printf("HH:MM格式（24时制）：%tR",dateLast);
//------------------------------------分割线--------------------------------------------------------
            Date dateOne=new Date();
            //b的使用，月份简称
            String str=String.format(Locale.US,"英文月份简称：%tb",dateOne);
            System.out.println(str);
            System.out.printf("本地月份简称：%tb%n",dateOne);
            //B的使用，月份全称
            str=String.format(Locale.US,"英文月份全称：%tB",dateOne);
            System.out.println(str);
            System.out.printf("本地月份全称：%tB%n",dateOne);
            //a的使用，星期简称
            str=String.format(Locale.US,"英文星期的简称：%ta",dateOne);
            System.out.println(str);
            //A的使用，星期全称
            System.out.printf("本地星期的简称：%tA%n",dateOne);
            //C的使用，年前两位
            System.out.printf("年的前两位数字（不足两位前面补0）：%tC%n",dateOne);
            //y的使用，年后两位
            System.out.printf("年的后两位数字（不足两位前面补0）：%ty%n",dateOne);
            //j的使用，一年的天数
            System.out.printf("一年中的天数（即年的第几天）：%tj%n",dateOne);
            //m的使用，月份
            System.out.printf("两位数字的月份（不足两位前面补0）：%tm%n",dateOne);
            //d的使用，日（二位，不够补零）
            System.out.printf("两位数字的日（不足两位前面补0）：%td%n",dateOne);
            //e的使用，日（一位不补零）
            System.out.printf("月份的日（前面不补0）：%te",dateOne);
            /**
             * 英文月份简称：May
             * 本地月份简称：五月
             * 英文月份全称：May
             * 本地月份全称：五月
             * 英文星期的简称：Thu
             * 本地星期的简称：星期四
             * 年的前两位数字（不足两位前面补0）：20
             * 年的后两位数字（不足两位前面补0）：17
             * 一年中的天数（即年的第几天）：124
             * 两位数字的月份（不足两位前面补0）：05
             * 两位数字的日（不足两位前面补0）：04
             * 月份的日（前面不补0）：4
             */
//------------------------------------分割线--------------------------------------------------------
            Calendar c1 = Calendar.getInstance();
// 获得年份
            int year = c1.get(Calendar.YEAR);
// 获得月份
            int month = c1.get(Calendar.MONTH) + 1;
// 获得日期
            int dateTwo = c1.get(Calendar.DATE);
// 获得小时
            int hour = c1.get(Calendar.HOUR_OF_DAY);
// 获得分钟
            int minute = c1.get(Calendar.MINUTE);
// 获得秒
            int second = c1.get(Calendar.SECOND);
// 获得星期几（注意（这个与Date类是不同的）：1代表星期日、2代表星期1、3代表星期二，以此类推）
            int day = c1.get(Calendar.DAY_OF_WEEK);
//------------------------------------分割线--------------------------------------------------------
            String months[] = {
                    "Jan", "Feb", "Mar", "Apr",
                    "May", "Jun", "Jul", "Aug",
                    "Sep", "Oct", "Nov", "Dec"};

            int yearTwo;
            // 初始化 Gregorian 日历
            // 使用当前时间和日期
            // 默认为本地时间和时区
            GregorianCalendar gcalendar = new GregorianCalendar();
            // 显示当前时间和日期的信息
            System.out.print("Date: ");
            System.out.print(months[gcalendar.get(Calendar.MONTH)]);
            System.out.print(" " + gcalendar.get(Calendar.DATE) + " ");
            System.out.println(yearTwo = gcalendar.get(Calendar.YEAR));
            System.out.print("Time: ");
            System.out.print(gcalendar.get(Calendar.HOUR) + ":");
            System.out.print(gcalendar.get(Calendar.MINUTE) + ":");
            System.out.println(gcalendar.get(Calendar.SECOND));
//------------------------------------分割线--------------------------------------------------------
            // 测试当前年份是否为闰年
            if(gcalendar.isLeapYear(yearTwo)) {
                System.out.println("当前年份是闰年");
            }
            else {
                System.out.println("当前年份不是闰年");
            }

            //2017-1-1
            //2017-0-31
            Calendar cOne = Calendar.getInstance();
            cOne.set(2017, 1, 1);
            System.out.println(cOne.get(Calendar.YEAR)
                    +"-"+cOne.get(Calendar.MONTH)
                    +"-"+cOne.get(Calendar.DATE));
            cOne.set(2017, 1, 0);
            System.out.println(cOne.get(Calendar.YEAR)
                    +"-"+cOne.get(Calendar.MONTH)
                    +"-"+cOne.get(Calendar.DATE));
            //------------------------------------分割线--------------------------------------------------------
            //2017-2-1
            //2017-1-28
            Calendar cTwo = Calendar.getInstance();
            cTwo.set(2017, 2, 1);
            System.out.println(cTwo.get(Calendar.YEAR)
                    +"-"+cTwo.get(Calendar.MONTH)
                    +"-"+cTwo.get(Calendar.DATE));
            cTwo.set(2017, 2, -10);
            System.out.println(cTwo.get(Calendar.YEAR)
                    +"-"+cTwo.get(Calendar.MONTH)
                    +"-"+cTwo.get(Calendar.DATE));
//------------------------------------分割线--------------------------------------------------------
            // 初始化 Date 对象
            Date dateThree = new Date();

            //c的使用
            System.out.printf("全部日期和时间信息：%tc%n",dateThree);
            //f的使用
            System.out.printf("年-月-日格式：%tF%n",dateThree);
            //d的使用
            System.out.printf("月/日/年格式：%tD%n",dateThree);
            //r的使用
            System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",dateThree);
            //t的使用
            System.out.printf("HH:MM:SS格式（24时制）：%tT%n",dateThree);
            //R的使用
            System.out.printf("HH:MM格式（24时制）：%tR",dateThree);
            /**
             * 全部日期和时间信息：周六 8月 22 11:53:36 CST 2020
             * 年-月-日格式：2020-08-22
             * 月/日/年格式：08/22/20
             * HH:MM:SS PM格式（12时制）：11:53:36 上午
             * HH:MM:SS格式（24时制）：11:53:36
             * HH:MM格式（24时制）：11:53
             */
        }

        @Test
        public void testIP() {
            //System.out.println(getLocalIP());
            System.out.println(NumberUtil.isNumber("1"));
        }


        @Test
        public void testc() {
            System.out.println(NumberUtil.round(0.2406000000008 * 100, 2).doubleValue());
            System.out.println(NumberUtil.round(0.24060050000008 * 100, 2).toString());
            Double value = Double.valueOf("0.44444");
            System.out.println(value);
        }


        /**
         * AES加密
         *
         * @param content    待加密的内容
         * @param encryptKey 加密密钥
         * @return 加密后的byte[]
         * @throws Exception
         */
        public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

            return cipher.doFinal(content.getBytes("utf-8"));
        }
        /**
         * base 64 encode
         *
         * @param bytes 待编码的byte[]
         * @return 编码后的base 64 code
         */
        public static String base64Encode(byte[] bytes) {
            //return Base64.encodeBase64String(bytes);
            return Base64.getEncoder().encodeToString(bytes);
        }

        /**
         * AES加密为base 64 code
         *
         * @param content    待加密的内容
         * @param encryptKey 加密密钥
         * @return 加密后的base 64 code
         * @throws Exception
         */
        public static String aesEncrypt(String content, String encryptKey) throws Exception {
            if (StringUtils.isBlank(encryptKey)) {
                return content;
            }
            return base64Encode(aesEncryptToBytes(content, encryptKey));
        }

        /**
         * AES解密
         *
         * @param encryptBytes 待解密的byte[]
         * @param decryptKey   解密密钥
         * @return 解密后的String
         * @throws Exception
         */
        public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return new String(decryptBytes);
        }

        /**
         * base 64 decode
         *
         * @param base64Code 待解码的base 64 code
         * @return 解码后的byte[]
         * @throws Exception
         */
        public static byte[] base64Decode(String base64Code) throws Exception {
            Base64.Decoder decoder = Base64.getDecoder();
            return StringUtils.isEmpty(base64Code) ? null : decoder.decode(base64Code);
        }

        /**
         * 将base 64 code AES解密
         *
         * @param encryptStr 待解密的base 64 code
         * @param decryptKey 解密密钥
         * @return 解密后的string
         * @throws Exception
         */
        public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
            if (StringUtils.isBlank(decryptKey)) {
                return encryptStr;
            }
            return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
        }

    }

