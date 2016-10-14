package com.example.jian.myapplication.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Created by jian on 2016/10/14.
 */
public class MoneyFormatter {

    private static NumberFormat getFormatWithoutSign() {
        final NumberFormat format = NumberFormat.getNumberInstance();
        format.setRoundingMode(RoundingMode.FLOOR);
        return format;
    }

    /**
     * 格式化显示钱数
     *
     * @return 输入 12312 输出 12,312
     */
    public static String formatMoney(int money) { return getFormatWithoutSign().format(money); }

    /**
     * 格式化显示钱数
     *
     * @return 输入 324444.234 输出 324,444
     */
    public static String formatMoney(double money) { return getFormatWithoutSign().format(money); }

    /**
     * 格式化显示钱数
     *
     * @return 输入 324444.234 输出 324,444
     */
    public static String formatMoney(BigDecimal money) { return getFormatWithoutSign().format(money); }

    /**
     * 格式化需要显示小数的钱数
     *
     * @param money 123123.234234234234
     * @return 123, 123.23
     */
    public static String formatDecimalMoney(double money) {
        final NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        format.setRoundingMode(RoundingMode.FLOOR);
        return format.format(money);
    }
}