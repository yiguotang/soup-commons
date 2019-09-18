package com.soup.common.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.Map;

/**
 * 通用请求参数类
 *
 * @author zhaoyi
 */
@Data
public class QueryRequest implements Serializable {
    /**
     * 页码
     */
    @Range(min = 1, message = "页码最小为1")
    private int page;

    /**
     * 每页大小
     */
    @Range(min = 1, message = "每页大小为1")
    private int limit;

    /**
     * 查询关键字
     */
    private String keywords;

    /**
     * 排序字段名
     */
    private String sortField;

    /**
     * 排序方向
     */
    private String sortOrder = "desc";

    private String startTime;

    private String endTime;

    /**
     * 其他查询条件
     */
    private Map<String, Object> conditions;

    public void setKeywords(String keywords) {
        if (StringUtils.isNotEmpty(keywords)) {
            this.keywords = keywords.trim();
        }
    }
}
