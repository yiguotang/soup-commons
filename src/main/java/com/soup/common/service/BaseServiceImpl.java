package com.soup.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soup.common.entity.QueryParam;
import com.soup.common.entity.QueryRequest;
import com.soup.common.entity.Result;
import com.soup.common.mapper.CustomMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * base service impl
 *
 * @author zhaoyi
 * @param <T> 实体模型类
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected CustomMapper<T> mapper;

    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] types = type.getActualTypeArguments();
        if (null != types && types.length > 0) {
            this.clazz = (Class<T>) types[0];
        }
    }

    @Override
    public Result page(QueryRequest queryParam) {
        // 分页查询
        PageHelper.startPage(queryParam.getPage(), queryParam.getLimit());
        List<T> entities = mapper.list(queryParam);
        PageInfo<T> pageInfo = new PageInfo<>(entities);

        return new Result().success(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int save(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int deleteByQueryParam(Map<String, String> equalMap) {
        QueryParam queryParam = new QueryParam(clazz);
        buidEqualCrieria(queryParam, equalMap);
        return mapper.deleteByExample(queryParam);
    }

    @Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectByQueryParam(Object queryParam) {
        return mapper.selectByExample(queryParam);
    }

    @Override
    public T select(T entity) {
        return mapper.selectOne(entity);
    }

    @Override
    public int countByEqual(Map<String, String> equalMap) {
        QueryParam queryParam = new QueryParam(clazz);
        buidEqualCrieria(queryParam, equalMap);
        return mapper.selectCountByExample(queryParam);
    }

    @Override
    public List<T> selectByName(String name) {
        QueryParam queryParam = new QueryParam(clazz);
        QueryParam.Criteria criteria = queryParam.createCriteria();
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        return selectByQueryParam(queryParam);
    }

    @Override
    public List<T> selectByEqual(Map<String, String> equalMap) {
        QueryParam queryParam = new QueryParam(clazz);
        buidEqualCrieria(queryParam, equalMap);
        return selectByQueryParam(queryParam);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    private void buidEqualCrieria(QueryParam queryParam, Map<String, String> equalMap) {
        QueryParam.Criteria criteria = queryParam.createCriteria();
        for (Map.Entry<String, String> item : equalMap.entrySet()) {
            criteria.andEqualTo(item.getKey(), item.getValue());
        }
    }
}
