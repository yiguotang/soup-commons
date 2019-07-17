package com.soup.commons.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据返回
 *
 * @author zhaoyi
 */
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagerResponse<T> implements Serializable {

    private static final long serialVersionUID = 8551805407870871605L;

    /**
     * 记录
     */
    private List<T> list;

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     * 总条数
     */
    private long count;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 额外数据
     */
    private Object ext;

    public PagerResponse(List<T> list, int pageNum, int pageSize, long count, int pages) {
        this.list = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
        this.pages = pages;
    }
}
