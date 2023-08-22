package com.ikang.idata.search.search;

import com.ikang.idata.search.search.common.ExcelExportService;
import com.ikang.idata.search.search.service.BaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ExcelExportServiceTest {

    @Mock
    private BaseService mockBaseService;

    private ExcelExportService excelExportServiceUnderTest;

    @BeforeEach
    void setUp() {
        excelExportServiceUnderTest = new ExcelExportService(mockBaseService);
    }

    @Test
    void testExport1() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        excelExportServiceUnderTest.export(response, Object.class, "param", "title");

        // Verify the results
    }

    @Test
    void testExport1_BaseServiceHeadReturnsNoItems() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Collections.emptyList());
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        excelExportServiceUnderTest.export(response, Object.class, "param", "title");

        // Verify the results
    }

    @Test
    void testExport1_BaseServiceDataListGroupReturnsNoItems() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Collections.emptyList());
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        excelExportServiceUnderTest.export(response, Object.class, "param", "title");

        // Verify the results
    }

    @Test
    void testExport1_BaseServiceDataListReturnsNoItems() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Collections.emptyList());

        // Run the test
        excelExportServiceUnderTest.export(response, Object.class, "param", "title");

        // Verify the results
    }

    @Test
    void testExport1_ThrowsIOException() {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        assertThatThrownBy(
                () -> excelExportServiceUnderTest.export(response, Object.class, "param", "title"))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testExport2() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        excelExportServiceUnderTest.export(response, "service", "param", "title");

        // Verify the results
    }

    @Test
    void testExport2_BaseServiceHeadReturnsNoItems() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Collections.emptyList());
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        excelExportServiceUnderTest.export(response, "service", "param", "title");

        // Verify the results
    }

    @Test
    void testExport2_BaseServiceDataListGroupReturnsNoItems() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Collections.emptyList());
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        excelExportServiceUnderTest.export(response, "service", "param", "title");

        // Verify the results
    }

    @Test
    void testExport2_BaseServiceDataListReturnsNoItems() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Collections.emptyList());

        // Run the test
        excelExportServiceUnderTest.export(response, "service", "param", "title");

        // Verify the results
    }

    @Test
    void testExport2_ThrowsIOException() {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        assertThatThrownBy(
                () -> excelExportServiceUnderTest.export(response, "service", "param", "title"))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testExport3() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        excelExportServiceUnderTest.export(response, "service", "param", "title", "findMethod", "aggrMethod");

        // Verify the results
    }

    @Test
    void testExport3_BaseServiceHeadReturnsNoItems() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Collections.emptyList());
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        excelExportServiceUnderTest.export(response, "service", "param", "title", "findMethod", "aggrMethod");

        // Verify the results
    }

    @Test
    void testExport3_BaseServiceDataListGroupReturnsNoItems() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Collections.emptyList());
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        excelExportServiceUnderTest.export(response, "service", "param", "title", "findMethod", "aggrMethod");

        // Verify the results
    }

    @Test
    void testExport3_BaseServiceDataListReturnsNoItems() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Collections.emptyList());

        // Run the test
        excelExportServiceUnderTest.export(response, "service", "param", "title", "findMethod", "aggrMethod");

        // Verify the results
    }

    @Test
    void testExport3_ThrowsIOException() {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBaseService.head(Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(
                Arrays.asList("value")));
        when(mockBaseService.dataListGroup(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));
        when(mockBaseService.dataList(Arrays.asList(new HashMap<>()), Arrays.asList(new HashMap<>())))
                .thenReturn(Arrays.asList(
                        Arrays.asList("value")));

        // Run the test
        assertThatThrownBy(() -> excelExportServiceUnderTest.export(response, "service", "param", "title", "findMethod",
                "aggrMethod"))
                .isInstanceOf(IOException.class);
    }

    @Test
    void testSetHeader() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        excelExportServiceUnderTest.setHeader(response, "title");

        // Verify the results
    }

    @Test
    void testSetHeader_ThrowsUnsupportedEncodingException() {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> excelExportServiceUnderTest.setHeader(response, "title"))
                .isInstanceOf(UnsupportedEncodingException.class);
    }
}