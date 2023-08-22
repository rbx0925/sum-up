package com.ikang.idata.search.search.util;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.event.WriteHandler;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterTableBuilder;
import org.apache.commons.io.input.BrokenInputStream;
import org.apache.commons.io.input.NullInputStream;
import org.apache.commons.io.output.BrokenOutputStream;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class ExcelTestTest {

    @Test
    void testRead1() {
        // Setup
        final InputStream in = new ByteArrayInputStream("content".getBytes());
        final Sheet sheet = new Sheet(0, 0, BaseRowModel.class, "sheetName", Arrays.asList(Arrays.asList("value")));

        // Run the test
        final List<Object> result = ExcelTest.read(in, sheet);

        // Verify the results
    }

    @Test
    void testRead1_EmptyIn() {
        // Setup
        final InputStream in = new NullInputStream(0L);
        final Sheet sheet = new Sheet(0, 0, BaseRowModel.class, "sheetName", Arrays.asList(Arrays.asList("value")));

        // Run the test
        final List<Object> result = ExcelTest.read(in, sheet);

        // Verify the results
    }

    @Test
    void testRead1_BrokenIn() {
        // Setup
        final InputStream in = new BrokenInputStream();
        final Sheet sheet = new Sheet(0, 0, BaseRowModel.class, "sheetName", Arrays.asList(Arrays.asList("value")));

        // Run the test
        final List<Object> result = ExcelTest.read(in, sheet);

        // Verify the results
    }

    @Test
    void testReadBySax() {
        // Setup
        final InputStream in = new ByteArrayInputStream("content".getBytes());
        final Sheet sheet = new Sheet(0, 0, BaseRowModel.class, "sheetName", Arrays.asList(Arrays.asList("value")));
        final AnalysisEventListener mockListener = mock(AnalysisEventListener.class);

        // Run the test
        ExcelTest.readBySax(in, sheet, mockListener);

        // Verify the results
    }

    @Test
    void testReadBySax_EmptyIn() {
        // Setup
        final InputStream in = new NullInputStream(0L);
        final Sheet sheet = new Sheet(0, 0, BaseRowModel.class, "sheetName", Arrays.asList(Arrays.asList("value")));
        final AnalysisEventListener mockListener = mock(AnalysisEventListener.class);

        // Run the test
        ExcelTest.readBySax(in, sheet, mockListener);

        // Verify the results
    }

    @Test
    void testReadBySax_BrokenIn() {
        // Setup
        final InputStream in = new BrokenInputStream();
        final Sheet sheet = new Sheet(0, 0, BaseRowModel.class, "sheetName", Arrays.asList(Arrays.asList("value")));
        final AnalysisEventListener mockListener = mock(AnalysisEventListener.class);

        // Run the test
        ExcelTest.readBySax(in, sheet, mockListener);

        // Verify the results
    }

    @Test
    void testGetReader() {
        // Setup
        final InputStream in = new ByteArrayInputStream("content".getBytes());
        final AnalysisEventListener mockListener = mock(AnalysisEventListener.class);

        // Run the test
        final ExcelReader result = ExcelTest.getReader(in, mockListener);

        // Verify the results
    }

    @Test
    void testGetReader_EmptyIn() {
        // Setup
        final InputStream in = new NullInputStream(0L);
        final AnalysisEventListener mockListener = mock(AnalysisEventListener.class);

        // Run the test
        final ExcelReader result = ExcelTest.getReader(in, mockListener);

        // Verify the results
    }

    @Test
    void testGetReader_BrokenIn() {
        // Setup
        final InputStream in = new BrokenInputStream();
        final AnalysisEventListener mockListener = mock(AnalysisEventListener.class);

        // Run the test
        final ExcelReader result = ExcelTest.getReader(in, mockListener);

        // Verify the results
    }

    @Test
    void testGetWriter1() {
        // Setup
        final OutputStream outputStream = new ByteArrayOutputStream();

        // Run the test
        final ExcelWriter result = ExcelTest.getWriter(outputStream);

        // Verify the results
    }

    @Test
    void testGetWriter1_BrokenOutputStream() {
        // Setup
        final OutputStream outputStream = new BrokenOutputStream();

        // Run the test
        final ExcelWriter result = ExcelTest.getWriter(outputStream);

        // Verify the results
    }

    @Test
    void testGetWriter2() {
        // Setup
        final OutputStream outputStream = new ByteArrayOutputStream();

        // Run the test
        final ExcelWriter result = ExcelTest.getWriter(outputStream, ExcelTypeEnum.XLS, false);

        // Verify the results
    }

    @Test
    void testGetWriter2_BrokenOutputStream() {
        // Setup
        final OutputStream outputStream = new BrokenOutputStream();

        // Run the test
        final ExcelWriter result = ExcelTest.getWriter(outputStream, ExcelTypeEnum.XLS, false);

        // Verify the results
    }

    @Test
    void testGetWriterWithTemp() {
        // Setup
        final InputStream temp = new ByteArrayInputStream("content".getBytes());
        final OutputStream outputStream = new ByteArrayOutputStream();

        // Run the test
        final ExcelWriter result = ExcelTest.getWriterWithTemp(temp, outputStream, ExcelTypeEnum.XLS, false);

        // Verify the results
    }

    @Test
    void testGetWriterWithTemp_EmptyTemp() {
        // Setup
        final InputStream temp = new NullInputStream(0L);
        final OutputStream outputStream = new ByteArrayOutputStream();

        // Run the test
        final ExcelWriter result = ExcelTest.getWriterWithTemp(temp, outputStream, ExcelTypeEnum.XLS, false);

        // Verify the results
    }

    @Test
    void testGetWriterWithTemp_BrokenTemp() {
        // Setup
        final InputStream temp = new BrokenInputStream();
        final OutputStream outputStream = new ByteArrayOutputStream();

        // Run the test
        final ExcelWriter result = ExcelTest.getWriterWithTemp(temp, outputStream, ExcelTypeEnum.XLS, false);

        // Verify the results
    }

    @Test
    void testGetWriterWithTemp_BrokenOutputStream() {
        // Setup
        final InputStream temp = new ByteArrayInputStream("content".getBytes());
        final OutputStream outputStream = new BrokenOutputStream();

        // Run the test
        final ExcelWriter result = ExcelTest.getWriterWithTemp(temp, outputStream, ExcelTypeEnum.XLS, false);

        // Verify the results
    }

    @Test
    void testGetWriterWithTempAndHandler() {
        // Setup
        final InputStream temp = new ByteArrayInputStream("content".getBytes());
        final OutputStream outputStream = new ByteArrayOutputStream();
        final WriteHandler handler = null;

        // Run the test
        final ExcelWriter result = ExcelTest.getWriterWithTempAndHandler(temp, outputStream, ExcelTypeEnum.XLS, false,
                handler);

        // Verify the results
    }

    @Test
    void testGetWriterWithTempAndHandler_EmptyTemp() {
        // Setup
        final InputStream temp = new NullInputStream(0L);
        final OutputStream outputStream = new ByteArrayOutputStream();
        final WriteHandler handler = null;

        // Run the test
        final ExcelWriter result = ExcelTest.getWriterWithTempAndHandler(temp, outputStream, ExcelTypeEnum.XLS, false,
                handler);

        // Verify the results
    }

    @Test
    void testGetWriterWithTempAndHandler_BrokenTemp() {
        // Setup
        final InputStream temp = new BrokenInputStream();
        final OutputStream outputStream = new ByteArrayOutputStream();
        final WriteHandler handler = null;

        // Run the test
        final ExcelWriter result = ExcelTest.getWriterWithTempAndHandler(temp, outputStream, ExcelTypeEnum.XLS, false,
                handler);

        // Verify the results
    }

    @Test
    void testGetWriterWithTempAndHandler_BrokenOutputStream() {
        // Setup
        final InputStream temp = new ByteArrayInputStream("content".getBytes());
        final OutputStream outputStream = new BrokenOutputStream();
        final WriteHandler handler = null;

        // Run the test
        final ExcelWriter result = ExcelTest.getWriterWithTempAndHandler(temp, outputStream, ExcelTypeEnum.XLS, false,
                handler);

        // Verify the results
    }

    @Test
    void testWrite1() {
        // Setup
        // Run the test
        final ExcelWriterBuilder result = ExcelTest.write();

        // Verify the results
    }

    @Test
    void testWrite2() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        final ExcelWriterBuilder result = ExcelTest.write(file);

        // Verify the results
    }

    @Test
    void testWrite3() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        final ExcelWriterBuilder result = ExcelTest.write(file, Object.class);

        // Verify the results
    }

    @Test
    void testWrite4() {
        // Setup
        // Run the test
        final ExcelWriterBuilder result = ExcelTest.write("pathName");

        // Verify the results
    }

    @Test
    void testWrite5() {
        // Setup
        // Run the test
        final ExcelWriterBuilder result = ExcelTest.write("pathName", Object.class);

        // Verify the results
    }

    @Test
    void testWrite6() {
        // Setup
        final OutputStream outputStream = new ByteArrayOutputStream();

        // Run the test
        final ExcelWriterBuilder result = ExcelTest.write(outputStream);

        // Verify the results
    }

    @Test
    void testWrite6_BrokenOutputStream() {
        // Setup
        final OutputStream outputStream = new BrokenOutputStream();

        // Run the test
        final ExcelWriterBuilder result = ExcelTest.write(outputStream);

        // Verify the results
    }

    @Test
    void testWrite7() {
        // Setup
        final OutputStream outputStream = new ByteArrayOutputStream();

        // Run the test
        final ExcelWriterBuilder result = ExcelTest.write(outputStream, Object.class);

        // Verify the results
    }

    @Test
    void testWrite7_BrokenOutputStream() {
        // Setup
        final OutputStream outputStream = new BrokenOutputStream();

        // Run the test
        final ExcelWriterBuilder result = ExcelTest.write(outputStream, Object.class);

        // Verify the results
    }

    @Test
    void testWriterSheet1() {
        // Setup
        // Run the test
        final ExcelWriterSheetBuilder result = ExcelTest.writerSheet();

        // Verify the results
    }

    @Test
    void testWriterSheet2() {
        // Setup
        // Run the test
        final ExcelWriterSheetBuilder result = ExcelTest.writerSheet(0);

        // Verify the results
    }

    @Test
    void testWriterSheet3() {
        // Setup
        // Run the test
        final ExcelWriterSheetBuilder result = ExcelTest.writerSheet("sheetName");

        // Verify the results
    }

    @Test
    void testWriterSheet4() {
        // Setup
        // Run the test
        final ExcelWriterSheetBuilder result = ExcelTest.writerSheet(0, "sheetName");

        // Verify the results
    }

    @Test
    void testWriterTable1() {
        // Setup
        // Run the test
        final ExcelWriterTableBuilder result = ExcelTest.writerTable();

        // Verify the results
    }

    @Test
    void testWriterTable2() {
        // Setup
        // Run the test
        final ExcelWriterTableBuilder result = ExcelTest.writerTable(0);

        // Verify the results
    }

    @Test
    void testRead2() {
        // Setup
        // Run the test
        final ExcelReaderBuilder result = ExcelTest.read();

        // Verify the results
    }

    @Test
    void testRead3() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        final ExcelReaderBuilder result = ExcelTest.read(file);

        // Verify the results
    }

    @Test
    void testRead4() {
        // Setup
        final File file = new File("filename.txt");
        final ReadListener mockReadListener = mock(ReadListener.class);

        // Run the test
        final ExcelReaderBuilder result = ExcelTest.read(file, mockReadListener);

        // Verify the results
    }

    @Test
    void testRead5() {
        // Setup
        final File file = new File("filename.txt");
        final ReadListener mockReadListener = mock(ReadListener.class);

        // Run the test
        final ExcelReaderBuilder result = ExcelTest.read(file, Object.class, mockReadListener);

        // Verify the results
    }




}
