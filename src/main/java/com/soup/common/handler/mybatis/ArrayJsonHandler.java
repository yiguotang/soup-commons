package com.soup.common.handler.mybatis;

import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JSONArray类型的json转换handler
 *
 * @author zhaoyi
 */
@MappedTypes(JSONArray.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ArrayJsonHandler extends BaseTypeHandler<JSONArray> {

    /**
     * 设置非空参数
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, JSONArray jsonArray, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, jsonArray.toJSONString());
    }

    @Override
    public JSONArray getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String sqlJson = resultSet.getString(columnName);
        if (null != sqlJson){
            return JSONArray.parseArray(sqlJson);
        }

        return null;
    }

    @Override
    public JSONArray getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String sqlJson = resultSet.getString(columnIndex);
        if (null != sqlJson){
            return JSONArray.parseArray(sqlJson);
        }
        return null;
    }

    @Override
    public JSONArray getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String sqlJson = callableStatement.getString(columnIndex);
        if (null != sqlJson){
            return JSONArray.parseArray(sqlJson);
        }
        return null;
    }
}
