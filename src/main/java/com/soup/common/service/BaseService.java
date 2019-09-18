package com.soup.common.service;

import com.soup.common.entity.QueryRequest;
import com.soup.common.entity.Result;

import java.util.List;
import java.util.Map;

/**
 * Base Serivce
 *
 * @author zhaoyi
 * @param <T> 实体模型泛型
 */
public interface BaseService<T> {

    /**
     * 分页查询
     *
     * @param queryParam 分页查询参数
     * @return 分页结果
     */
    Result page(QueryRequest queryParam);

    /**
     * 根据主键查询
     *
     * @param key 主键key
     * @return 实体对象
     */
    T selectByKey(Object key);

    /**
     * 根据实体查询
     *
     * @param entity 实体参数对象
     * @return 实体对象
     */
    T select(T entity);

    /**
     * 保存
     *
     * @param entity 待保存的实体参数对象
     * @return 保存条数
     */
    int save(T entity);

    /**
     * 根据主键删除对象
     *
     * @param key 主键
     * @return 删除条数
     */
    int delete(Object key);

    /**
     * 根据参数删除
     *
     * @param equalMap 等值字段map，key是实体类的字典非数据库表字典名称
     * @return 删除条数
     */
    int deleteByQueryParam(Map<String, String> equalMap);

    /**
     * 根据实体对象更新，包括null的字段
     *
     * @param entity 实体参数
     * @return 更新条数
     */
    int updateAll(T entity);

    /**
     * 根据实体对象更新，不包括null的字段
     *
     * @param entity 实体参数
     * @return 更新条数
     */
    int updateNotNull(T entity);

    /**
     * 根据参数查询列表
     *
     * @param example 参数对象
     * @return 对象列表
     */
    List<T> selectByQueryParam(Object example);

    /**
     * 根据名称模糊搜索
     *
     * @param name 名称关键字
     * @return 对象列表
     */
    List<T> selectByName(String name);

    /**
     * 等值字段查询
     *
     * @param equalMap 等值字段map，key是实体类的字典非数据库表字典名称
     * @return 对象列表
     */
    List<T> selectByEqual(Map<String, String> equalMap);

    /**
     * 查询所有
     *
     * @return 对象列表
     */
    List<T> selectAll();

    /**
     * 根据等值字典进行count
     *
     * @param equalMap 等值字段map，key是实体类的字典非数据库表字典名称
     * @return 统计数量
     */
    int countByEqual(Map<String, String> equalMap);
}
