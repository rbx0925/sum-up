package com.ikang.idata.search.search.config;

import java.util.Scanner;

/**
 * 计算器
 *
 * @author <a href="jiangfeng.yan@ikang.com">jiangfeng</a>
 * @version 2023年05月08日 下午 6:34
 */
public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎使用计算器！");
        System.out.print("请输入第一个数字：");
        double num1 = scanner.nextDouble();
        System.out.print("请输入操作符(+、-、*、/):");
        String operator = scanner.next();
        System.out.print("请输入第二个数字：");
        double num2 = scanner.nextDouble();
        double result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    System.out.println("除数不能为0!");
                    return;
                } else {
                    result = num1 / num2;
                }
                break;
            default:
                System.out.println("无效的操作符！");
                return;
        }
        System.out.println("计算结果为：" + result);
    }

    public static double calculate(double num1, String operator, double num2) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    System.out.println("除数不能为0!");
                    return 0; // 如果除数为0,则直接返回0
                } else {
                    return num1 / num2;
                }
            default:
                System.out.println("无效的操作符！");
                return 0; // 如果操作符无效，则直接返回0
        }
    }
}
