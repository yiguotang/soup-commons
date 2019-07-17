package com.soup.commons.util;

import com.alibaba.excel.metadata.BaseRowModel;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * excel tool test case
 *
 * @author zhaoyi
 */
public class ExcelUtilTest {

    /**
     * resource下的excel文件，使用带缓存的输入流包装
     */
    private InputStream is = new BufferedInputStream(this.getClass().getResourceAsStream("/test.xlsx"));

    /**
     * 读取带模型的excel
     */
    @Test
    public void readRowModelExcelTest() {
        List<? extends BaseRowModel> excelDate = ExcelUtil.readExcel(is, TempRowModel.class);
        System.out.println(excelDate);
    }

    /**
     * 读取字符串格式的excel
     */
    @Test
    public void readStringExceTest() {
        List<List<String>> data = ExcelUtil.readExcel(is, null, 1);
        System.out.println(data);
    }
}