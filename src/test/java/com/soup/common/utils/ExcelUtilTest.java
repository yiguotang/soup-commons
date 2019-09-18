package com.soup.common.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
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
        List<? extends BaseRowModel> excelDate = ExcelUtil.read(is, TempRowModel.class);
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

    /**
     * 导出测试
     */
    @Test
    public void exportExcelTest() throws Exception {
        OutputStream out = new FileOutputStream("/testexport.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);

        // 动态添加表头，适用一些表头动态变化的场景
        Sheet sheet1 = new Sheet(1, 0);

        sheet1.setSheetName("第一个sheet");

        // 创建一个表格，用于 Sheet 中使用
        Table table1 = new Table(1);

        // 无注解的模式，动态添加表头
        table1.setHead(createTestListStringHead());
        // 写数据
        writer.write1(createDynamicModelList(), sheet1, table1);

        // 将上下文中的最终 outputStream 写入到指定文件中
        writer.finish();

        // 关闭流
        out.close();
    }

    private List<List<Object>> createDynamicModelList() {
        List<List<Object>> rows = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            List<Object> row = Lists.newArrayList();
            row.add("字符串" + i);
            row.add(123456L + i);
            row.add(2324 + i);
            row.add("test");
            row.add("test_t");
            rows.add(row);
        }
        return rows;
    }

    private static List<List<String>> createTestListStringHead(){
        // 模型上没有注解，表头数据动态传入
        List<List<String>> head = Lists.newArrayList();
        List<String> headCoulumn1 = Lists.newArrayList();
        List<String> headCoulumn2 = Lists.newArrayList();
        List<String> headCoulumn3 = Lists.newArrayList();
        List<String> headCoulumn4 = Lists.newArrayList();
        List<String> headCoulumn5 = Lists.newArrayList();

        headCoulumn1.add("第一列");headCoulumn1.add("第一列");headCoulumn1.add("第一列");
        headCoulumn2.add("第一列");headCoulumn2.add("第一列");headCoulumn2.add("第一列");

        headCoulumn3.add("第二列");headCoulumn3.add("第二列");headCoulumn3.add("第二列");
        headCoulumn4.add("第三列");headCoulumn4.add("第三列2");headCoulumn4.add("第三列2");
        headCoulumn5.add("第一列");headCoulumn5.add("第3列");headCoulumn5.add("第4列");

        head.add(headCoulumn1);
        head.add(headCoulumn2);
        head.add(headCoulumn3);
        head.add(headCoulumn4);
        head.add(headCoulumn5);
        return head;
    }

    private static TableStyle createTableStyle() {
        TableStyle tableStyle = new TableStyle();
        // 设置表头样式
        Font headFont = new Font();
        // 字体是否加粗
        headFont.setBold(true);
        // 字体大小
        headFont.setFontHeightInPoints((short)12);
        // 字体
        headFont.setFontName("楷体");
        tableStyle.setTableHeadFont(headFont);
        // 背景色
        tableStyle.setTableHeadBackGroundColor(IndexedColors.BLUE);

        // 设置表格主体样式
        Font contentFont = new Font();
        contentFont.setBold(true);
        contentFont.setFontHeightInPoints((short)12);
        contentFont.setFontName("黑体");
        tableStyle.setTableContentFont(contentFont);
        tableStyle.setTableContentBackGroundColor(IndexedColors.GREEN);
        return tableStyle;
    }

    /*@Test
    public void wirteTest() {
        String fileName = "/testexport.xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可

        TempRowModel row = new TempRowModel();
        row.setCreateTime("");
        row.setOrderNumber("test");
        EasyExcel.write(fileName, TempRowModel.class).sheet("模板").doWrite(Lists.newArrayList(row));
    }*/

    @Test
    public void tableWrite() {
        String fileName = "d:/testexport.xlsx";
        // 这里直接写多个table的案例了，如果只有一个 也可以直一行代码搞定，参照其他案例
        // 这里 需要指定写用哪个class去读
        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        ExcelWriter excelWriter2 = EasyExcel.write(fileName, DemoData2.class).build();
        // 把sheet设置为不需要头 不然会输出sheet的头 这样看起来第一个table 就有2个头了
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").needHead(Boolean.FALSE).build();
        // 这里必须指定需要头，table 会继承sheet的配置，sheet配置了不需要，table 默认也是不需要
        WriteTable writeTable0 = EasyExcel.writerTable(0).needHead(Boolean.TRUE).build();
        WriteTable writeTable1 = EasyExcel.writerTable(1).needHead(Boolean.TRUE).build();
        // 第一次写入会创建头
        List<DemoData> data = Lists.newArrayList();
        excelWriter.write(data, writeSheet, writeTable0);
        // 第二次写如也会创建头，然后在第一次的后面写入数据
        List<DemoData2> data2 = Lists.newArrayList();
        excelWriter2.write(data2, writeSheet, writeTable1);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    /**
     * 合并单元格
     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>2. 创建一个merge策略 并注册
     * <p>3. 直接写即可
     */
    @Test
    public void mergeWrite() {
        String fileName = "d:/testexport.xlsx";
        // 每隔2行会合并 把eachColumn 设置成 3 也就是我们数据的长度，所以就第一列会合并。当然其他合并策略也可以自己写
        LoopMergeStrategy loopMergeStrategy = new LoopMergeStrategy(2, 0);
        LoopMergeStrategy loopMergeStrategy1 = new LoopMergeStrategy(2, 1);
        LoopMergeStrategy loopMergeStrategy2 = new LoopMergeStrategy(2, 2);
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        List<DemoData> data = Lists.newArrayList();
        data.add(new DemoData("1", new Date(), "test"));
        data.add(new DemoData("2", new Date(), "test2"));
        data.add(new DemoData("3", new Date(), "test3"));
        data.add(new DemoData("4", new Date(), "test4"));
        data.add(new DemoData("5", new Date(), "test5"));
        data.add(new DemoData("6", new Date(), "test6"));
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write(fileName, DemoData.class).registerWriteHandler(loopMergeStrategy)
                .registerWriteHandler(loopMergeStrategy1).registerWriteHandler(loopMergeStrategy2);
        excelWriterBuilder.sheet(0, "test1").doWrite(data);
    }
}