package com.soup.commons.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * 通用excetion
 *
 * @author zhaoyi
 */
@Getter
@ToString
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 3854724437287450254L;

    private int code;
    private String message;
    private Object data;

    public CommonException(Object data, String message) {
        this.code = 400;
        this.message = message;
        this.data = data;
    }

    public CommonException(Object data, String message, int code) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonException(String message) {
        this.code = 400;
        this.message = message;
    }

    public CommonException(String message, int code) {
        this.code = code;
        this.message = message;
    }
}
