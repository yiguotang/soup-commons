package com.soup.common.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Date;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class TempRowModel extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String orderNumber;

    @ExcelProperty(index = 2)
    private String createUser;

    @ExcelProperty(index = 3, format = "yyyy/MM/dd")
    private String createTime;

    @ExcelProperty(index = 19, format = "yyyy/MM/dd")
    private Date date;

    public void setCreateTime(String createTime) {
        if (NumberUtils.isCreatable(createTime)) {
            double time = Double.parseDouble(createTime);
            this.createTime = DateUtil.getPOIDate(false, time, null);
        }
    }
}
