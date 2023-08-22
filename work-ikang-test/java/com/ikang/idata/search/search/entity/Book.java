package com.ikang.idata.search.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode//用于后期的去重使用
public class Book {
    //id
    private Long id;
    //书名
    private String name;

    //分类
    private String category;//"哲学,小说"

    //评分
    private Integer score;

    //简介
    private String intro;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number: ");
        int number = scanner.nextInt();

        System.out.print("Enter the base of the number: ");
        int base = scanner.nextInt();

        System.out.print("Enter the target base: ");
        int targetBase = scanner.nextInt();

        String convertedNumber = convertBase(number, base, targetBase);

        System.out.println("Converted number: " + convertedNumber);

        scanner.close();
    }

    public static String convertBase(int number, int base, int targetBase) {
        return Integer.toString(Integer.parseInt(Integer.toString(number), base), targetBase);
    }
}
