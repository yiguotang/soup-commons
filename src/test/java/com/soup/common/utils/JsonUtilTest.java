package com.soup.common.utils;

import org.junit.Test;

/**
 * JsonUtil test case
 *
 * @author zhaoyi
 */
public class JsonUtilTest {

    @Test
    public void obj2JsonTest() {
        com.soup.common.utils.TempRowModel temp = new TempRowModel();
        temp.setCreateTime("2019-07-17");
        temp.setCreateUser("zhangsan");
        System.out.println(JsonUtil.obj2Json(temp));
    }
}
