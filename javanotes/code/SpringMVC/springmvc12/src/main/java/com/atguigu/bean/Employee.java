package com.atguigu.bean;

import java.io.Serializable;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.bean.Employee
 */
public class Employee implements Serializable {
    private Integer id;
    private Integer gender;
    private String lastName;
    private String email;
    private Double salary;
    private Integer deptId;

    public Employee() {
    }

    public Employee(Integer id, Integer gender, String lastName, String email, Double salary, Integer deptId) {
        this.id = id;
        this.gender = gender;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.deptId = deptId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", gender=" + gender +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", deptId=" + deptId +
                '}';
    }
}
