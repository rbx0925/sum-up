package com.ikang.idata.search.search.common;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/10/8
 */
@Data
public class EmployeeTest implements Serializable {

    //姓名
    private String name;
    //年龄
    private Integer age;
    //性别
    private Integer sex;
    //地址
    private String address;
    //赏金
    private BigDecimal money;

    public EmployeeTest(String name, Integer age, Integer sex, String address,BigDecimal money) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", money=" + money +
                ", address='" + address + '\'' +
                '}';
    }
}
