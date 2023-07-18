package com.atguigu.bean;

import java.util.List;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.bean.Department
 */
public class Department {
    private Integer id;
    private String name;

    private List<Employee> emps;

    public Department() {
    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}