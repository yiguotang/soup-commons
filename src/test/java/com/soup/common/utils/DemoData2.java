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
public class DemoData2 {
    @ExcelProperty("序号")
    private String orderNo;
    @ExcelProperty("下单时间")
    private Date orderTime;
    @ExcelProperty("客户名称")
    private String clientName;
}
