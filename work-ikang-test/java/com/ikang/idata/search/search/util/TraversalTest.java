package com.ikang.idata.search.search.util;

import java.util.*;

public class TraversalTest {

    ArrayList<String> arrayList = new ArrayList<String>();
    LinkedList<String> linkedList = new LinkedList<String>();
    HashMap<String, String> map = new HashMap<String, String>();

    public TraversalTest() {
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");

        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");

        System.out.println("Data has init over ...");
    }

    /**
     * ForEach Traversal.
     * The Effective-Java recommended this traversal type.
     * */
    public void arrayListTraversalNormal1() {
        System.out.println("ArrayList ForEach Traversal.");
        for(String str : arrayList){
            System.out.println(str);
        }
    }

    /**
     * Iterator Traversal.
     * */
    public void arrayListTraversalNormal2() {
        System.out.println("ArrayList Iterator Traversal.");
        Iterator<String> itor = arrayList.iterator();
        while(itor.hasNext()){
            System.out.println(itor.next().toString());
        }
    }

    /**
     * ForEach Traversal.
     * The Effective-Java recommended this traversal type.
     * */
    public void linkedListTraversalNormal1() {
        System.out.println("LinkedList ForEach Traversal.");
        for(String str : linkedList){
            System.out.println(str);
        }
    }

    /**
     * Iterator Traversal.
     * */
    public void linkedListTraversalNormal2() {
        System.out.println("LinkedList Iterator Traversal.");
        Iterator<String> itor = linkedList.iterator();
        while(itor.hasNext()){
            System.out.println(itor.next().toString());
        }
    }

    public void mapTraversalNormal() {
        System.out.println("HashMap Iterator Traversal.");
        Iterator<Map.Entry<String, String>> itor = map.entrySet().iterator();
        Map.Entry<String, String> entry = null;
        String key = null;
        String value = null;

        while(itor.hasNext()){
            entry = itor.next();
            key = entry.getKey();
            value = entry.getValue();

            System.out.println("key is " + key + ", value is " + value);
        }
    }

    /**
     * While traversing the arrayList, add '33' behind the '3' element.
     * */
    public void arrayListTraversalDynamic1() {
        ListIterator<String> itor = arrayList.listIterator();
        String str = null;

        while (itor.hasNext()) {
            str = itor.next().toString();
            if(str.equals("3")){
                itor.add("33");
                break;
            }
        }
    }
    /**
     * While traversing the arrayList, remove the '3' element.
     * */
    public void arrayListTraversalDynamic2() {
        ListIterator<String> itor = arrayList.listIterator();
        String str = null;

        while (itor.hasNext()) {
            str = itor.next().toString();
            if(str.equals("3")){
                itor.remove();
                break;
            }
        }
    }

    /**
     * While traversing the linkedList, add '33' behind the '3' element.
     * */
    public void linkedListTraversalDynamic1() {
        ListIterator<String> itor = linkedList.listIterator();
        String str = null;

        while (itor.hasNext()) {
            str = itor.next().toString();
            if(str.equals("3")){
                itor.add("33");
                break;
            }
        }
    }

    /**
     * While traversing the linkedList, remove the '3' element.
     * */
    public void linkedListTraversalDynamic2() {
        ListIterator<String> itor = linkedList.listIterator();
        String str = null;

        while (itor.hasNext()) {
            str = itor.next().toString();
            if(str.equals("3")){
                itor.remove();
                break;
            }
        }
    }

    /**
     * While traversing the arrayList, add '33' when we get the '3' element.
     * */
    @SuppressWarnings("rawtypes")
    public void mapTraversalDynamic1() {
        LinkedList<Map.Entry<String, String>> tempList = new LinkedList<Map.Entry<String, String>>();
        tempList.addAll(map.entrySet());
        ListIterator<Map.Entry<String, String>> itor = tempList.listIterator();
        Map.Entry entry = null;

        while (itor.hasNext()) {
            entry = (Map.Entry) itor.next();
            Object key = entry.getKey();

            if (key.toString().equals("3")) {
                map.put("33", "33");
            }
        }
    }

    /**
     * While traversing the hashMap, remove the '3' element.
     * */
    @SuppressWarnings("rawtypes")
    public void mapTraversalDynamic2() {
        LinkedList<Map.Entry<String, String>> tempList = new LinkedList<Map.Entry<String, String>>();
        tempList.addAll(map.entrySet());
        ListIterator<Map.Entry<String, String>> itor = tempList.listIterator();
        Map.Entry entry = null;

        while (itor.hasNext()) {
            entry = (Map.Entry) itor.next();
            Object key = entry.getKey();

            if(key.toString().equals("3")){
                map.remove("3");
                break;
            }
        }
    }

    public static void main(String[] args) {
        com.ikang.idata.search.search.TraversalTest test = new com.ikang.idata.search.search.TraversalTest();

        test.arrayListTraversalNormal1();
        test.arrayListTraversalNormal2();

        System.out.println("While traversing the arrayList, add '33' behind the '3' element. The result is:");
        test.arrayListTraversalDynamic1();
        test.arrayListTraversalNormal1();

        System.out.println("While traversing the arrayList, remove the '3' element. The result is:");
        test.arrayListTraversalDynamic2();
        test.arrayListTraversalNormal1();

        System.out.println("-----------------------------------");

        test.linkedListTraversalNormal1();
        test.linkedListTraversalNormal2();

        System.out.println("While traversing the linkedList, add '33' behind the '3' element. The result is:");
        test.linkedListTraversalDynamic1();
        test.linkedListTraversalNormal1();

        System.out.println("While traversing the linkedList, remove the '3' element. The result is:");
        test.linkedListTraversalDynamic2();
        test.linkedListTraversalNormal1();

        System.out.println("-----------------------------------");
        test.mapTraversalNormal();
        System.out.println("While traversing the hashMap, add '33' when we get the '3' element. The result is:");
        test.mapTraversalDynamic1();
        test.mapTraversalNormal();

        System.out.println("While traversing the hashMap, remove the '3' element. The result is:");
        test.mapTraversalDynamic2();
        test.mapTraversalNormal();
    }
    /**
     * 结果
     * ArrayList ForEach Traversal.
     * 1
     * 2
     * 3
     * 4
     * 5
     * ArrayList Iterator Traversal.
     * 1
     * 2
     * 3
     * 4
     * 5
     * While traversing the arrayList, add '33' behind the '3' element. The result is:
     * ArrayList ForEach Traversal.
     * 1
     * 2
     * 3
     * 33
     * 4
     * 5
     * While traversing the arrayList, remove the '3' element. The result is:
     * ArrayList ForEach Traversal.
     * 1
     * 2
     * 33
     * 4
     * 5
     * -----------------------------------
     * LinkedList ForEach Traversal.
     * 1
     * 2
     * 3
     * 4
     * 5
     * LinkedList Iterator Traversal.
     * 1
     * 2
     * 3
     * 4
     * 5
     * While traversing the linkedList, add '33' behind the '3' element. The result is:
     * LinkedList ForEach Traversal.
     * 1
     * 2
     * 3
     * 33
     * 4
     * 5
     * While traversing the linkedList, remove the '3' element. The result is:
     * LinkedList ForEach Traversal.
     * 1
     * 2
     * 33
     * 4
     * 5
     * -----------------------------------
     * HashMap Iterator Traversal.
     * key is 1, value is 1
     * key is 2, value is 2
     * key is 3, value is 3
     * key is 4, value is 4
     * key is 5, value is 5
     * While traversing the hashMap, add '33' when we get the '3' element. The result is:
     * HashMap Iterator Traversal.
     * key is 33, value is 33
     * key is 1, value is 1
     * key is 2, value is 2
     * key is 3, value is 3
     * key is 4, value is 4
     * key is 5, value is 5
     * While traversing the hashMap, remove the '3' element. The result is:
     * HashMap Iterator Traversal.
     * key is 33, value is 33
     * key is 1, value is 1
     * key is 2, value is 2
     * key is 4, value is 4
     * key is 5, value is 5
     */
}