package com.ikang.idata.search.search.aop;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.exception.ExcelGenerateException;
import com.alibaba.excel.write.builder.ExcelWriterTableBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/9/6
 */
public class ExcelWriterSheetBuilderTest {
    private ExcelWriter excelWriter;
    /**
     * Sheet
     */
    private WriteSheet writeSheet;

    public ExcelWriterSheetBuilderTest() {
        this.writeSheet = new WriteSheet();
    }

    public ExcelWriterSheetBuilderTest(ExcelWriter excelWriter) {
        this.writeSheet = new WriteSheet();
        this.excelWriter = excelWriter;
    }

    /**
     * Writes the head relative to the existing contents of the sheet. Indexes are zero-based.
     *
     * @param relativeHeadRowIndex
     * @return
     */
    public ExcelWriterSheetBuilderTest relativeHeadRowIndex(Integer relativeHeadRowIndex) {
        writeSheet.setRelativeHeadRowIndex(relativeHeadRowIndex);
        return this;
    }

    /**
     * You can only choose one of the {@link ExcelWriterSheetBuilderTest#head(List)} and
     * {@link ExcelWriterSheetBuilderTest#head(Class)}
     *
     * @param head
     * @return
     */
    public ExcelWriterSheetBuilderTest head(List<List<String>> head) {
        writeSheet.setHead(head);
        return this;
    }

    /**
     * You can only choose one of the {@link ExcelWriterSheetBuilderTest#head(List)} and
     * {@link ExcelWriterSheetBuilderTest#head(Class)}
     *
     * @param clazz
     * @return
     */
    public ExcelWriterSheetBuilderTest head(Class clazz) {
        writeSheet.setClazz(clazz);
        return this;
    }

    /**
     * Need Head
     */
    public ExcelWriterSheetBuilderTest needHead(Boolean needHead) {
        writeSheet.setNeedHead(needHead);
        return this;
    }

    /**
     * Use the default style.Default is true.
     *
     * @param useDefaultStyle
     * @return
     */
    public ExcelWriterSheetBuilderTest useDefaultStyle(Boolean useDefaultStyle) {
        writeSheet.setUseDefaultStyle(useDefaultStyle);
        return this;
    }

    /**
     * Whether to automatically merge headers.Default is true.
     *
     * @param automaticMergeHead
     * @return
     */
    public ExcelWriterSheetBuilderTest automaticMergeHead(Boolean automaticMergeHead) {
        writeSheet.setAutomaticMergeHead(automaticMergeHead);
        return this;
    }

    /**
     * Custom type conversions override the default.
     *
     * @param converter
     * @return
     */
    public ExcelWriterSheetBuilderTest registerConverter(Converter converter) {
        if (writeSheet.getCustomConverterList() == null) {
            writeSheet.setCustomConverterList(new ArrayList<Converter>());
        }
        writeSheet.getCustomConverterList().add(converter);
        return this;
    }

    /**
     * Custom write handler
     *
     * @param writeHandler
     * @return
     */
    public ExcelWriterSheetBuilderTest registerWriteHandler(WriteHandler writeHandler) {
        if (writeSheet.getCustomWriteHandlerList() == null) {
            writeSheet.setCustomWriteHandlerList(new ArrayList<WriteHandler>());
        }
        writeSheet.getCustomWriteHandlerList().add(writeHandler);
        return this;
    }

    /**
     * Starting from 0
     *
     * @param sheetNo
     * @return
     */
    public ExcelWriterSheetBuilderTest sheetNo(Integer sheetNo) {
        writeSheet.setSheetNo(sheetNo);
        return this;
    }

    /**
     * sheet name
     *
     * @param sheetName
     * @return
     */
    public ExcelWriterSheetBuilderTest sheetName(String sheetName) {
        writeSheet.setSheetName(sheetName);
        return this;
    }

    /**
     * Ignore the custom columns.
     */
    public ExcelWriterSheetBuilderTest excludeColumnIndexes(Collection<Integer> excludeColumnIndexes) {
        writeSheet.setExcludeColumnIndexes(excludeColumnIndexes);
        return this;
    }

    /**
     * Ignore the custom columns.
     */
    public ExcelWriterSheetBuilderTest excludeColumnFiledNames(Collection<String> excludeColumnFiledNames) {
        writeSheet.setExcludeColumnFiledNames(excludeColumnFiledNames);
        return this;
    }

    /**
     * Only output the custom columns.
     */
    public ExcelWriterSheetBuilderTest includeColumnIndexes(Collection<Integer> includeColumnIndexes) {
        writeSheet.setIncludeColumnIndexes(includeColumnIndexes);
        return this;
    }

    /**
     * Only output the custom columns.
     */
    public ExcelWriterSheetBuilderTest includeColumnFiledNames(Collection<String> includeColumnFiledNames) {
        writeSheet.setIncludeColumnFiledNames(includeColumnFiledNames);
        return this;
    }

    public WriteSheet build() {
        return writeSheet;
    }

    public void doWrite(List data) {
        if (excelWriter == null) {
            throw new ExcelGenerateException("Must use 'EasyExcelFactory.write().sheet()' to call this method");
        }
        excelWriter.write(data, build());
        excelWriter.finish();
    }

    public void doFill(Object data) {
        doFill(data, null);
    }

    public void doFill(Object data, FillConfig fillConfig) {
        if (excelWriter == null) {
            throw new ExcelGenerateException("Must use 'EasyExcelFactory.write().sheet()' to call this method");
        }
        excelWriter.fill(data, fillConfig, build());
        excelWriter.finish();
    }

    public ExcelWriterTableBuilder table() {
        return table(null);
    }

    public ExcelWriterTableBuilder table(Integer tableNo) {
        ExcelWriterTableBuilder excelWriterTableBuilder = new ExcelWriterTableBuilder(excelWriter, build());
        if (tableNo != null) {
            excelWriterTableBuilder.tableNo(tableNo);
        }
        return excelWriterTableBuilder;
    }

}

