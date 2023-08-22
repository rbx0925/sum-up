package com.ikang.idata.search.search.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 测试计算器
 *
 * @author <a href="jiangfeng.yan@ikang.com">jiangfeng</a>
 * @version 2023年05月09日 上午 10:12
 */
public class CalculatorTest {
    @Test
    public void testAddition() {
        Calculator calculator = new Calculator();
        double num1 = 3.0;
        String operator = "+";
        double num2 = 4.0;
        double result = calculator.calculate(num1, operator, num2);
        assertEquals(7.0, result); // 断言比较计算结果和预期结果是否相等
    }
}
