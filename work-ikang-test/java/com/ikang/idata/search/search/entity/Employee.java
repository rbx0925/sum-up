package com.ikang.idata.search.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;
 
    public Employee(String name) {
        this.name = name;
    }
 
    public Employee(String name,int id ) {
        this.id = id;
        this.name = name;
    }

    public String show( ) {
        System.out.println("--------------------111111----------");
        return "你好";
    }
 
}