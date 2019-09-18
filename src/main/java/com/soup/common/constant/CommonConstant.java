package com.soup.common.constant;

/**
 * 通用常量类
 *
 * @author zhaoyi
 */
public interface CommonConstant {

    /**
     * 跨域允许时间，毫秒
     */
    long DEFAULT_CORS_MAX_AGE = 3600;

    /**
     * 菜单类型：1方法，2控制器, 3分组
     */
    int METHOD_MENU = 1;
    int CONTROLLER_MENU = 2;
    int GROUP_MENU = 3;

    /**
     * int 常量
     */
    int NUM_0 = 0;
    int NUM_1 = 1;

    /**
     * 字符串常量
     */
    String BLANK = "";
}
