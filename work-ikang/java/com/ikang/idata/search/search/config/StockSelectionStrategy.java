package com.ikang.idata.search.search.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 *
 * @author <a href="jiangfeng.yan@ikang.com">jiangfeng</a>
 * @version 2023年05月11日 下午 2:09
 */
public class StockSelectionStrategy {
    private static final int SHORT_TIMEFRAME = 10; // 短期时间框架，单位为天
    private static final int LONG_TIMEFRAME = 30; // 长期时间框架，单位为天
    private static final int MA_SHORT_AVG = 5; // 短期移动平均线的周期
    private static final int MA_LONG_AVG = 20; // 长期移动平均线的周期

    private List<Double> shortMAs; // 短期移动平均线列表
    private List<Double> longMAs; // 长期移动平均线列表

    public StockSelectionStrategy() {
        shortMAs = new ArrayList<>();
        longMAs = new ArrayList<>();

        // 初始化短期和长期移动平均线列表
        for (int i = 0; i < MA_SHORT_AVG; i++) {
            shortMAs.add(0.0);
        }
        for (int i = 0; i < MA_LONG_AVG; i++) {
            longMAs.add(0.0);
        }
    }

    public void update(double price, int timeFrame) {
        double currentSMA; // 当前移动平均线值
        double currentEMA= 0.0d; // 当前列的移动平均线值

        if (timeFrame == SHORT_TIMEFRAME) { // 如果是短期时间框架，使用短期移动平均线计算当前值
            currentSMA = getShortMA(price);
        } else if (timeFrame == LONG_TIMEFRAME) { // 如果是长期时间框架，使用长期移动平均线计算当前值
            currentSMA = 0.2d;
        } else { // 否则抛出异常或忽略该价格数据
            throw new IllegalArgumentException("Invalid time frame: " + timeFrame);
        }

        if (currentSMA > currentEMA) { // 如果当前移动平均线值大于前一个移动平均线值，发出买入信号
            System.out.println("Buy at " + price);
        } else if (currentSMA < currentEMA) { // 如果当前移动平均线值小于前一个移动平均线值，发出卖出信号
            System.out.println("Sell at " + price);
        } else { // 否则不做任何操作，继续观察价格变化
            System.out.println("No signal at " + price);
        }
    }

    private double getShortMA(double price) { // 获取短期移动平均线值的方法，假设短期移动平均线是线性的，可以使用简单的算术平均公式计算
        double sum = price; // 总和初始化为价格本身
        for (int i = MA_SHORT_AVG-1; i >= 0; i--) { // 从后往前遍历短期移动平均线列表，累加每个周期内的总和
            sum += shortMAs.get(i);
        }
        return sum/MA_SHORT_AVG; // 返回短期移动平均线的值
    }
}
