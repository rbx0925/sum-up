package com.ikang.bprom.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rbx
 * @title
 * @Create 2024-03-28 18:08
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String name;
    private String category;
    private Integer score;
    private String name2;
}
