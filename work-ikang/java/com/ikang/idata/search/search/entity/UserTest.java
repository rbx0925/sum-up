package com.ikang.idata.search.search.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/8/18
 */
//@Document(indexName = "userindex", type = "user")
@Data
public class UserTest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 编号
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private String createtm;

    // getter和setter 略
}

