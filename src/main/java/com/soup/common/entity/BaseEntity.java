package com.soup.common.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 模型基类，所有数据表模型的基类
 *
 * @author zhaoyi
 */
@Data
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

}
