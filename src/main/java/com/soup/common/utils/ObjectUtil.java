package com.soup.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * <p>
 *  对象工具类
 * </p>
 *
 * @author zhaoyi
 * @date 2019-08-30 19:14
 * @since 1.0
 */
@Slf4j
public final class ObjectUtil {

    /**
     * 判断对象的字段是否都为空值
     */
    public static boolean validateField(Object obj) {
        boolean valid = false;
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (null != field.get(obj)) {
                    valid = true;
                    break;
                }
            } catch (IllegalAccessException e) {
                log.error("对象属性解析异常: {}", e);
            }
        }

        return valid;
    }
}
