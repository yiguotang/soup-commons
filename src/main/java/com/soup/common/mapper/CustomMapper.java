package com.soup.common.mapper;

import com.soup.common.entity.QueryRequest;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 *
 * base mapper
 *
 * @author zhaoyi
 * @param <T> 实体类泛型
 */
public interface CustomMapper<T> extends Mapper<T>, BaseMapper<T>, MySqlMapper<T>, ConditionMapper<T> {

    /**
     * 根据分页参数查询列表
     *
     * @param queryParam 分页参数
     * @return 对象列表
     */
    List<T> list(QueryRequest queryParam);
}
