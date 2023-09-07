package com.ikang.idata.search.search.common;

@FunctionalInterface
public interface MyFunction2<T,R> {
 
    public R getValue(T t1,T t2);
 
}