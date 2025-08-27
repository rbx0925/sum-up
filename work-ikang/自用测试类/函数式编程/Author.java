package com.ikang.bprom.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author rbx
 * @title
 * @Create 2024-03-28 18:06
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private Long id;
    private String name;
    private Integer age;
    private String desc;
    //private Book book;
    private List<Book> books;

}
