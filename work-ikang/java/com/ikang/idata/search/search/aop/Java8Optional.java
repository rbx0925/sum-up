package com.ikang.idata.search.search.aop;

import java.util.*;

/**
 * optional 是 java 8 新的判空特性
 *
 * @author <a href="jiangfeng.yan@ikang.com">jiangfeng</a>
 * @version 2022年09月08日 上午 10:22
 */
public class Java8Optional {
    public void publicMethod() {
        Integer localVariable1 = 10;
        Integer localVariable2 = 10;
        Integer localVariable3 = 10;

        Map<String, Integer> map = new HashMap<String, Integer>() {
            {
                put("a", localVariable1);
                put("b", localVariable2);
                put("c", localVariable3);
            }
        };

        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println(localVariable1);
            }
        });

        List<String> list = Arrays.asList("A", "B", "C");

        Collections.sort(list, new Comparator<String>() {
            public int compare(String p1, String p2) {
                return p1.compareTo(p2);
            }
        });
    }

}

//class OptionalDemo {
//    static class A {
//        B b;
//    }
//
//    static class B {
//        Integer c;
//    }
//
//    private static A getA() {
//        return new A();
//    }
//
//    public static void main(String[] args) {
//        A a = getA();
//        if (a != null) {
//            B b = a.b;
//            if (b != null) {
//                System.out.println(a.b.c);
//            }
//        }
//    }
//}

//public OptionalDemo {
//    static class A {
//        B b;
//    }
//
//    static class B {
//        Integer c;
//    }
//
//    private static A getA() {
//        return new A(new B(1));
//    }
//
//    public static void main(String[] args) {
//        A a = getA();
//        Optional.ofNullable(a).map(A::getB).map(B::getC).ifPresent(System.out::println);
//    }
//}
// 空对象对应的 Optional 实例private static final Optional<?> EMPTY = new Optional<>();
// Optional 实例的操作对象，可能为 null，也可能有值private final T value;
// 构造函数，value 不能为空，为空抛出 npe 异常
// private Optional(T value) {  this.value = Objects.requireNonNull(value);}
// 如果 value 为空，则返回 empty 对象，否则构造一个 Optional 实例public static <T> Optional<T> ofNullable(T value) {
// return value == null ? empty() : of(value);}
// 返回一个 EMPTY 对象public static<T> Optional<T> empty() {  @SuppressWarnings("unchecked")  Optional<T> t = (Optional<T>) EMPTY;  return t;}
// 构造一个 Optional 实例，如果 value 为空，则抛出 npe 异常public static <T> Optional<T> of(T value) {  return new Optional<>(value);}
