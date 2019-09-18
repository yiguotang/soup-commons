package com.soup.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 统一接口返回模型
 *
 * @author zhaoyi
 */
@Data
public class Result implements Serializable {

    private int code;

    private String msg;

    /**
     * 分页返回的总条数
     */
    private Long count;

    private Object data;

    private Map<String, Object> ext;

    public Result() {
    }

    private Result setResult(ErrorCode errorCode, Object data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
        this.data = data;
        return this;
    }

    private Result setResult(int code, Object data, Long count) {
        this.code = code;
        this.data = data;
        this.count = count;
        return this;
    }

    private Result setResult(int code, Object data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public Result success() {
        return setResult(ErrorCode.SUCCESS, null);
    }

    public Result success(Object data) {
        return setResult(ErrorCode.SUCCESS.getCode(), data);
    }

    public Result success(Object data, Long count) {
        return setResult(ErrorCode.SUCCESS.getCode(), data, count);
    }

    public Result fail() {
        return setResult(ErrorCode.FAILURE, null);
    }

    public Result fail(ErrorCode errorCode) {
        return setResult(errorCode, null);
    }

    public Result fail(ErrorCode errorCode, Object data) {
        return setResult(errorCode, data);
    }

    public Result fail(String message) {
        Result result = setResult(ErrorCode.FAILURE, null);
        result.msg = message;
        return result;
    }

    public Result fail(Object data, String message, int code) {
        this.code = code;
        this.msg = message;
        this.data = data;
        return this;
    }
}
