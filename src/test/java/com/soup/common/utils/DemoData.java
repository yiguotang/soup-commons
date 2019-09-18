package com.soup.common.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaoyi
 * @date 2019-09-15 19:40
 * @since 1.0
 */
@Data
public class DemoData {
    @ExcelProperty("序号")
    private String productNo;
    @ExcelProperty("设备名称")
    private Date deviceName;
    @ExcelProperty("型号规格")
    private String modelSpecif;

    public DemoData(String productNo, Date deviceName, String modelSpecif) {
        this.productNo = productNo;
        this.deviceName = deviceName;
        this.modelSpecif = modelSpecif;
    }
}
