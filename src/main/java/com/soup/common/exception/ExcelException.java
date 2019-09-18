package com.soup.common.exception;

import com.soup.common.entity.ErrorCode;

/**
 * excel data exception
 *
 * @author zhaoyi
 */
public class ExcelException extends ServiceException {
    public ExcelException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode.getCode());
    }
}
