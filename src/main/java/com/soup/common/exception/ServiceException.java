package com.soup.common.exception;

import lombok.Getter;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author zhaoyi
 */
@Getter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 3854724437287450254L;

    private int code;
    private String message;
    private Object data;

    public ServiceException() {
    }

    public ServiceException(Object data, String message) {
        this.code = 400;
        this.message = message;
        this.data = data;
    }

    public ServiceException(Object data, String message, int code) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ServiceException(String message) {
        this.code = 400;
        this.message = message;
    }

    public ServiceException(String message, int code) {
        this.code = code;
        this.message = message;
    }
}
