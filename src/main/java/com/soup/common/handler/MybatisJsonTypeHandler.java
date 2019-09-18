package com.soup.common.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mybatis mysql json data type handler
 *
 * @author zhaoyi
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MybatisJsonTypeHandler<T> extends BaseTypeHandler<T> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Class<T> clazz;

    static {
        MAPPER.configure(JsonParser.Feature.ALLOW_MISSING_VALUES, false);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public MybatisJsonTypeHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T t, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, this.obj2Json(t));
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return this.json2Obj(resultSet.getString(s), clazz);
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return this.json2Obj(resultSet.getString(i), clazz);
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return this.json2Obj(callableStatement.getString(i), clazz);
    }

    private String obj2Json(T object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private T json2Obj(String content, Class<?> clazz) {
        if (content != null && !content.isEmpty()) {
            try {
                return (T) MAPPER.readValue(content, clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }
}
