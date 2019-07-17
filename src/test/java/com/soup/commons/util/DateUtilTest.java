package com.soup.commons.util;

import org.junit.Test;

/**
 * DateUtil工具类测试类
 *
 * @author zhaoyi
 */
public class DateUtilTest {

    @Test
    public void getPOIDateTest() {
        double d = 43647;
        String date = DateUtil.getPOIDate(false, d, null);
        System.out.println(date);
    }
}
