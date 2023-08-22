package com.ikang.idata.search.search.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型
 * 通俗地说，在Java中泛型强制约束了类型安全。
 * @author <a href="jiangfeng.yan@ikang.com">jiangfeng</a>
 * @version 2022年10月14日 上午 9:55
 */
public class Generics {
    public static void main1(String[] args) {
        List list = new ArrayList();   // 没有泛型类型约束，列表元素默认是Object类型的
        list.add(new String("First MyObject"));
        String myObject = (String) list.get(0);  // 使用列表元素时需要进行类型转换
        for(Object obj : list){
            // 使用前进行类型转换
            String theMyObject = (String) obj;

        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add(new String("First MyObject"));

        String myObject = list.get(0);

        for(String obj : list){

        }

    }
}
