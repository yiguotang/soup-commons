package com.soup.commons.entity;

import lombok.Data;
import lombok.ToString;
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
@ToString
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -6970022835491029790L;

    /**
     * 页码
     */
    @Range(min = 1, message = "页码最小为1")
    private int pageIndex;

    /**
     * 每页大小
     */
    @Range(min = 1, message = "每页大小为1")
    private int pageSize;

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

    /**
     * 开始时间字符串
     */
    private String startTime;

    /**
     * 结束时间字符串
     */
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
