package com.soup.common.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.google.common.collect.Lists;
import com.soup.common.constant.CommonConstant;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * excel tool
 *
 * @author zhaoyi
 */
@Slf4j
public final class ExcelUtil {

    /**
     * 默认表头的
     */
    private static final int DEFAULT_READ_EXCEL_HEADLINE_NO = 1;

    /**
     * 构建excel sheet数据
     */
    public static Sheet buildSheet() {
        return null;
    }

    /**
     * 读取excel，默认读取第一个sheet，指定行号
     *
     * @param is excel文件输入流
     * @param rowModelClazz 实体映射类
     * @return Excel数据list
     */
    public static List<? extends BaseRowModel> read(InputStream is, Class<? extends BaseRowModel> rowModelClazz, int headLineNo) {
        return readExcel(is, null, headLineNo, rowModelClazz);
    }

    /**
     * 读取excel，默认读取第一个sheet，从第二行开始读取
     *
     * @param is excel文件输入流
     * @param rowModelClazz 实体映射类
     * @return Excel数据list
     */
    public static List<? extends BaseRowModel> read(InputStream is, Class<? extends BaseRowModel> rowModelClazz) {
        return readExcel(is, null, DEFAULT_READ_EXCEL_HEADLINE_NO, rowModelClazz);
    }

    /**
     * 将的excel文件转换成集合数据返回
     *
     * @param is excel文件输入流
     * @param rowModelClazz 实体映射类
     * @param dealSheetName 待解析的sheet名称
     * @param headLineNo 表头的行数
     * @return excel中的文件内容
     */
    public static List<? extends BaseRowModel> readExcel(InputStream is, String dealSheetName, int headLineNo,
            Class<? extends BaseRowModel> rowModelClazz) {
        // 工作表的内容
        List<? extends BaseRowModel> sheetData = Lists.newArrayList();

        ExcelListener excelListener = new ExcelListener();
        ExcelReader excelReader = EasyExcelFactory.getReader(is, excelListener);

        // 获取所有的工作表
        Optional<Sheet> sheet = getSheet(dealSheetName, excelReader);

        // 读取excel
        if (sheet.isPresent()) {
            Sheet dealSheet = sheet.get();
            dealSheet.setHeadLineMun(headLineNo);
            dealSheet.setClazz(rowModelClazz);
            excelReader.read(dealSheet);
            sheetData = Lists.newArrayList(excelListener.getDataList());
            excelListener.clear();
        }

        return sheetData;
    }

    /**
     * 读取excel
     *
     * @param is excel文件输入流
     * @param dealSheetName 待解析的sheet名称
     * @param headLineNo 表头的行数
     * @return excel数据
     */
    public static List<List<String>> readExcel(InputStream is, String dealSheetName, int headLineNo) {
        List<List<String>> sheetData = Lists.newArrayList();
        StringExcelListener excelListener = new StringExcelListener();
        ExcelReader excelReader = EasyExcelFactory.getReader(is, excelListener);
        Optional<Sheet> sheet = getSheet(dealSheetName, excelReader);

        // 读取excel
        if (sheet.isPresent()) {
            Sheet dealSheet = sheet.get();
            dealSheet.setHeadLineMun(headLineNo);
            excelReader.read(dealSheet);
            sheetData = Lists.newArrayList(excelListener.getDataList());
            excelListener.clear();
        }

        return sheetData;
    }

    /**
     * 获取要读取的工作表sheet
     *
     * @param sheetName 工作表名称，如果为空，则获取第一个sheet
     * @param excelReader ExcelReader
     * @return 工作表
     */
    private static Optional<Sheet> getSheet(String sheetName, ExcelReader excelReader) {
        // 获取所有的工作表
        List<Sheet> sheets = excelReader.getSheets();

        // 默认解析第一个工作表
        Optional<Sheet> sheet = Optional.of(sheets.get(0));
        if (StringUtils.isNotEmpty(sheetName)) {
            // 待解析的工作表名不为空，遍历比较
            sheet = sheets.stream().filter(item -> StringUtils.equals(item.getSheetName(), sheetName)).findFirst();
        }

        return sheet;
    }

    /**
     * 模板类型的excel监听类
     */
    @Getter
    private static class ExcelListener extends AnalysisEventListener<BaseRowModel> {
        /**
         * 存储临时excel数据
         */
        private List<BaseRowModel> dataList = Lists.newArrayList();

        @Override
        public void invoke(BaseRowModel rowInfo, AnalysisContext context) {
            // 统计该行非空元素个数，如果每个字段都是空值则不添加到集合中
            if (ObjectUtil.validateField(rowInfo)) {
                dataList.add(rowInfo);
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            // 清理临时excel数据
        }

        /**
         * 清空数据
         */
        void clear() {
            dataList.clear();
        }

        private List<? extends BaseRowModel> getDataList() {
            return dataList;
        }
    }

    /**
     * String类,解析监听器
     */
    @Getter
    private static class StringExcelListener extends AnalysisEventListener<List<String>> {
        /**
         * 自定义用于暂时存储data 可以通过实例获取该值
         */
        private List<List<String>> dataList = Lists.newArrayList();

        @Override
        public void invoke(List<String> rowInfo, AnalysisContext context) {
            // 统计改行的非空元素的个数，只有非空个数大于0才添加到解析集合中
            long nonNullRowItemCount = rowInfo.stream().filter(StringUtils::isNotEmpty).count();
            // 数据存储到list，供批量处理，或后续自己业务逻辑处理。
            if (nonNullRowItemCount > CommonConstant.NUM_0) {
                dataList.add(rowInfo);
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            // 解析结束销毁不用的资源
        }

        void clear() {
            dataList.clear();
        }
    }
}