package com.soup.commons.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * JsonUtil test case
 *
 * @author zhaoyi
 */
public class JsonUtilTest {

    @Test
    public void obj2JsonTest() {
        TempRowModel temp = new TempRowModel();
        temp.setCreateTime("2019-07-17");
        temp.setCreateUser("zhangsan");
        System.out.println(JsonUtil.obj2Json(temp));
    }
}
