package com.soup.commons.util;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.text.JsonMapper;

import java.util.Objects;
import java.util.Optional;

/**
 * json工具类
 *
 * @author zhaoyi
 */
public final class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static JsonMapper jsonMapper = new JsonMapper();

    private JsonUtil() {
    }

    /**
     * 对象转换成json字符串
     *
     * @param obj 序列化json的对象
     * @return 序列化json字符串
     */
    public static String obj2Json(Object obj) {
        String json = null;
        if (!Objects.isNull(obj)) {
            json = jsonMapper.toJson(obj);
        }
        return json;
    }
}
