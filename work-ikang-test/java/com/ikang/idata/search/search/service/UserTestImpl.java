package com.ikang.idata.search.search.service;

import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/8/18
 */
@Service
public class UserTestImpl {

//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    public boolean insert(User user) {
//        boolean falg = false;
//        try {
//            userDao.save(user);
//            falg = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return falg;
//    }
//
//    @Override
//    public List<User> search(String searchContent) {
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
//        System.out.println("查询的语句:" + builder);
//        Iterable<User> searchResult = userDao.search(builder);
//        Iterator<User> iterator = searchResult.iterator();
//        List<User> list = new ArrayList<User>();
//        while (iterator.hasNext()) {
//            list.add(iterator.next());
//        }
//        return list;
//    }
//
//
//    @Override
//    public List<User> searchUser(Integer pageNumber, Integer pageSize, String searchContent) {
//        // 分页参数
//        Pageable pageable = new PageRequest(pageNumber, pageSize);
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
//        System.out.println("查询的语句:" + searchQuery.getQuery().toString());
//        Page<User> searchPageResults = userDao.search(searchQuery);
//        return searchPageResults.getContent();
//    }
//
//
//    @Override
//    public List<User> searchUserByWeight(String searchContent) {
//        // 根据权重进行查询
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", searchContent)),
//                        ScoreFunctionBuilders.weightFactorFunction(10))
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
//                        ScoreFunctionBuilders.weightFactorFunction(100)).setMinScore(2);
//        System.out.println("查询的语句:" + functionScoreQueryBuilder.toString());
//        Iterable<User> searchResult = userDao.search(functionScoreQueryBuilder);
//        Iterator<User> iterator = searchResult.iterator();
//        List<User> list = new ArrayList<User>();
//        while (iterator.hasNext()) {
//            list.add(iterator.next());
//        }
//        return list;
//    }

    //POST http://localhost:8086/api/user
    //{"id":1,"name":"张三","age":20,"description":"张三是个Java开发工程师","createtm":"2018-4-25 11:07:42"}
    //{"id":2,"name":"李四","age":24,"description":"李四是个测试工程师","createtm":"1980-2-15 19:01:32"}
    //{"id":3,"name":"王五","age":25,"description":"王五是个运维工程师","createtm":"2016-8-21 06:11:32"}


    //http://localhost:8086/api/user?searchContent=工程师
    //[{"id":2,"name":"李四","age":14,"description":"李四是个测试工程师","createtm": "1980-2-15 19:01:32"},
    //{"id":1,"name":"张三","age":20,"description":"张三是个Java开发工程师", "createtm": "2018-4-25 11:07:42"},
    //{"id":3,"name":"王五","age":25,"description":"王五是个运维工程师","createtm": "2016-8-21 06:11:32"}]


    //http://localhost:8086/api/user?pageNumber=0&pageSize=2&searchContent=工程师
    //[{"id":2,"name":"李四","age":14,"description":"李四是个测试工程师"},{"id":1,"name":"张三","age":20,"description":"张三是个Java开发工程师"}]
    //http://localhost:8086/api/user2?searchContent=李四
    //[{"id":2,"name":"李四","age":24,"description":"李四是个测试工程师","createtm":"1980-2-15 19:01:32"}]



    //查询的语句:{{
    //  "function_score" : {
    //    "functions" : [ {
    //      "filter" : {
    //        "bool" : {
    //          "should" : {
    //            "match" : {
    //              "name" : {
    //                "query" : "李四",
    //                "type" : "boolean"
    //              }
    //            }
    //          }
    //        }
    //      },
    //      "weight" : 10.0
    //    }, {
    //      "filter" : {
    //        "bool" : {
    //          "should" : {
    //            "match" : {
    //              "description" : {
    //                "query" : "李四",
    //                "type" : "boolean"
    //              }
    //            }
    //          }
    //        }
    //      },
    //      "weight" : 100.0
    //    } ],
    //    "min_score" : 2.0
    //  }
    //}
}

