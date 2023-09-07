package com.ikang.idata.search.search.service;

import com.ikang.idata.common.entity.DepartmentFilm;
import com.ikang.idata.common.entity.ReturnData;
import com.ikang.idata.common.entity.vo.DepartmentFilmVo;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.ReturnDataFeignServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentFilmServiceTest {

    @Mock
    private ReturnDataFeignServiceImpl mockReturnDataFeignService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;
    @Mock
    private UserSearchService mockUserSearchService;

    @InjectMocks
    private DepartmentFilmService departmentFilmServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final DepartmentFilmVo departmentFilmVo = new DepartmentFilmVo();
        departmentFilmVo.setExamDateStart("examDateStart");
        departmentFilmVo.setExamDateEnd("examDateEnd");
        departmentFilmVo.setExamineDateStart("examineDateStart");
        departmentFilmVo.setExamineDateEnd("examineDateEnd");
        departmentFilmVo.setDoctorNcc("doctorNcc");
        departmentFilmVo.setInstituteId("instituteId");
        departmentFilmVo.setLocId("locId");
        departmentFilmVo.setDepartmentName("departmentName");
        departmentFilmVo.setDepartmentId("departmentId");
        departmentFilmVo.setDoctorName("doctorName");
        departmentFilmVo.setItemName("itemName");
        departmentFilmVo.setType(0);
        departmentFilmVo.setPageNum(0);
        departmentFilmVo.setPageSize(0);
        departmentFilmVo.setGroupBy("groupBy");

        final DepartmentFilm expectedResult = new DepartmentFilm();
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setTotalStr("totalStr");
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());
        expectedResult.setReturnData(Arrays.asList(new HashMap<>()));
        expectedResult.setSumOrAvgMap(new HashMap<>());
        expectedResult.setListMap(Arrays.asList(new HashMap<>()));

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("returnDataCode");
        returnData1.setReturnDataName("returnDataName");
        returnData1.setDataType(0);
        returnData1.setDataValue("dataValue");
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setReturnDataMemo("returnDataMemo");
        returnData1.setSort(0);
        returnData1.setDeleteStatus(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("reader_department")).thenReturn(returnData);

        when(mockUserSearchService.processTableList(Arrays.asList(new ReturnData()),
                Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(new HashMap<>()));

        // Run the test
        final DepartmentFilm result = departmentFilmServiceUnderTest.find(departmentFilmVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        final DepartmentFilmVo departmentFilmVo = new DepartmentFilmVo();
        departmentFilmVo.setExamDateStart("examDateStart");
        departmentFilmVo.setExamDateEnd("examDateEnd");
        departmentFilmVo.setExamineDateStart("examineDateStart");
        departmentFilmVo.setExamineDateEnd("examineDateEnd");
        departmentFilmVo.setDoctorNcc("doctorNcc");
        departmentFilmVo.setInstituteId("instituteId");
        departmentFilmVo.setLocId("locId");
        departmentFilmVo.setDepartmentName("departmentName");
        departmentFilmVo.setDepartmentId("departmentId");
        departmentFilmVo.setDoctorName("doctorName");
        departmentFilmVo.setItemName("itemName");
        departmentFilmVo.setType(0);
        departmentFilmVo.setPageNum(0);
        departmentFilmVo.setPageSize(0);
        departmentFilmVo.setGroupBy("groupBy");

        final DepartmentFilm expectedResult = new DepartmentFilm();
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setTotalStr("totalStr");
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());
        expectedResult.setReturnData(Arrays.asList(new HashMap<>()));
        expectedResult.setSumOrAvgMap(new HashMap<>());
        expectedResult.setListMap(Arrays.asList(new HashMap<>()));

        when(mockReturnDataFeignService.listByReturnDataIndex("reader_department")).thenReturn(Collections.emptyList());
        when(mockUserSearchService.processTableList(Arrays.asList(new ReturnData()),
                Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(new HashMap<>()));

        // Run the test
        final DepartmentFilm result = departmentFilmServiceUnderTest.find(departmentFilmVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_UserSearchServiceReturnsNoItems() {
        // Setup
        final DepartmentFilmVo departmentFilmVo = new DepartmentFilmVo();
        departmentFilmVo.setExamDateStart("examDateStart");
        departmentFilmVo.setExamDateEnd("examDateEnd");
        departmentFilmVo.setExamineDateStart("examineDateStart");
        departmentFilmVo.setExamineDateEnd("examineDateEnd");
        departmentFilmVo.setDoctorNcc("doctorNcc");
        departmentFilmVo.setInstituteId("instituteId");
        departmentFilmVo.setLocId("locId");
        departmentFilmVo.setDepartmentName("departmentName");
        departmentFilmVo.setDepartmentId("departmentId");
        departmentFilmVo.setDoctorName("doctorName");
        departmentFilmVo.setItemName("itemName");
        departmentFilmVo.setType(0);
        departmentFilmVo.setPageNum(0);
        departmentFilmVo.setPageSize(0);
        departmentFilmVo.setGroupBy("groupBy");

        final DepartmentFilm expectedResult = new DepartmentFilm();
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setTotalStr("totalStr");
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());
        expectedResult.setReturnData(Arrays.asList(new HashMap<>()));
        expectedResult.setSumOrAvgMap(new HashMap<>());
        expectedResult.setListMap(Arrays.asList(new HashMap<>()));

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("returnDataCode");
        returnData1.setReturnDataName("returnDataName");
        returnData1.setDataType(0);
        returnData1.setDataValue("dataValue");
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setReturnDataMemo("returnDataMemo");
        returnData1.setSort(0);
        returnData1.setDeleteStatus(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("reader_department")).thenReturn(returnData);

        when(mockUserSearchService.processTableList(Arrays.asList(new ReturnData()),
                Arrays.asList(new HashMap<>()))).thenReturn(Collections.emptyList());

        // Run the test
        final DepartmentFilm result = departmentFilmServiceUnderTest.find(departmentFilmVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExportExcel() throws Exception {
        // Setup
        final DepartmentFilmVo departmentFilmVo = new DepartmentFilmVo();
        departmentFilmVo.setExamDateStart("examDateStart");
        departmentFilmVo.setExamDateEnd("examDateEnd");
        departmentFilmVo.setExamineDateStart("examineDateStart");
        departmentFilmVo.setExamineDateEnd("examineDateEnd");
        departmentFilmVo.setDoctorNcc("doctorNcc");
        departmentFilmVo.setInstituteId("instituteId");
        departmentFilmVo.setLocId("locId");
        departmentFilmVo.setDepartmentName("departmentName");
        departmentFilmVo.setDepartmentId("departmentId");
        departmentFilmVo.setDoctorName("doctorName");
        departmentFilmVo.setItemName("itemName");
        departmentFilmVo.setType(0);
        departmentFilmVo.setPageNum(0);
        departmentFilmVo.setPageSize(0);
        departmentFilmVo.setGroupBy("groupBy");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("returnDataCode");
        returnData1.setReturnDataName("returnDataName");
        returnData1.setDataType(0);
        returnData1.setDataValue("dataValue");
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setReturnDataMemo("returnDataMemo");
        returnData1.setSort(0);
        returnData1.setDeleteStatus(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("reader_department")).thenReturn(returnData);

        when(mockUserSearchService.processTableList(Arrays.asList(new ReturnData()),
                Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(new HashMap<>()));

        // Run the test
        departmentFilmServiceUnderTest.exportExcel(departmentFilmVo, response);

        // Verify the results
    }

    @Test
    void testExportExcel_ReturnDataFeignServiceImplReturnsNoItems() throws Exception {
        // Setup
        final DepartmentFilmVo departmentFilmVo = new DepartmentFilmVo();
        departmentFilmVo.setExamDateStart("examDateStart");
        departmentFilmVo.setExamDateEnd("examDateEnd");
        departmentFilmVo.setExamineDateStart("examineDateStart");
        departmentFilmVo.setExamineDateEnd("examineDateEnd");
        departmentFilmVo.setDoctorNcc("doctorNcc");
        departmentFilmVo.setInstituteId("instituteId");
        departmentFilmVo.setLocId("locId");
        departmentFilmVo.setDepartmentName("departmentName");
        departmentFilmVo.setDepartmentId("departmentId");
        departmentFilmVo.setDoctorName("doctorName");
        departmentFilmVo.setItemName("itemName");
        departmentFilmVo.setType(0);
        departmentFilmVo.setPageNum(0);
        departmentFilmVo.setPageSize(0);
        departmentFilmVo.setGroupBy("groupBy");

        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockReturnDataFeignService.listByReturnDataIndex("reader_department")).thenReturn(Collections.emptyList());
        when(mockUserSearchService.processTableList(Arrays.asList(new ReturnData()),
                Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(new HashMap<>()));

        // Run the test
        departmentFilmServiceUnderTest.exportExcel(departmentFilmVo, response);

        // Verify the results
    }

    @Test
    void testExportExcel_UserSearchServiceReturnsNoItems() throws Exception {
        // Setup
        final DepartmentFilmVo departmentFilmVo = new DepartmentFilmVo();
        departmentFilmVo.setExamDateStart("examDateStart");
        departmentFilmVo.setExamDateEnd("examDateEnd");
        departmentFilmVo.setExamineDateStart("examineDateStart");
        departmentFilmVo.setExamineDateEnd("examineDateEnd");
        departmentFilmVo.setDoctorNcc("doctorNcc");
        departmentFilmVo.setInstituteId("instituteId");
        departmentFilmVo.setLocId("locId");
        departmentFilmVo.setDepartmentName("departmentName");
        departmentFilmVo.setDepartmentId("departmentId");
        departmentFilmVo.setDoctorName("doctorName");
        departmentFilmVo.setItemName("itemName");
        departmentFilmVo.setType(0);
        departmentFilmVo.setPageNum(0);
        departmentFilmVo.setPageSize(0);
        departmentFilmVo.setGroupBy("groupBy");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("returnDataCode");
        returnData1.setReturnDataName("returnDataName");
        returnData1.setDataType(0);
        returnData1.setDataValue("dataValue");
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setReturnDataMemo("returnDataMemo");
        returnData1.setSort(0);
        returnData1.setDeleteStatus(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("reader_department")).thenReturn(returnData);

        when(mockUserSearchService.processTableList(Arrays.asList(new ReturnData()),
                Arrays.asList(new HashMap<>()))).thenReturn(Collections.emptyList());

        // Run the test
        departmentFilmServiceUnderTest.exportExcel(departmentFilmVo, response);

        // Verify the results
    }

    @Test
    void testExportExcel_ThrowsException() throws Exception {
        // Setup
        final DepartmentFilmVo departmentFilmVo = new DepartmentFilmVo();
        departmentFilmVo.setExamDateStart("examDateStart");
        departmentFilmVo.setExamDateEnd("examDateEnd");
        departmentFilmVo.setExamineDateStart("examineDateStart");
        departmentFilmVo.setExamineDateEnd("examineDateEnd");
        departmentFilmVo.setDoctorNcc("doctorNcc");
        departmentFilmVo.setInstituteId("instituteId");
        departmentFilmVo.setLocId("locId");
        departmentFilmVo.setDepartmentName("departmentName");
        departmentFilmVo.setDepartmentId("departmentId");
        departmentFilmVo.setDoctorName("doctorName");
        departmentFilmVo.setItemName("itemName");
        departmentFilmVo.setType(0);
        departmentFilmVo.setPageNum(0);
        departmentFilmVo.setPageSize(0);
        departmentFilmVo.setGroupBy("groupBy");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("returnDataCode");
        returnData1.setReturnDataName("returnDataName");
        returnData1.setDataType(0);
        returnData1.setDataValue("dataValue");
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setReturnDataMemo("returnDataMemo");
        returnData1.setSort(0);
        returnData1.setDeleteStatus(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("reader_department")).thenReturn(returnData);

        when(mockUserSearchService.processTableList(Arrays.asList(new ReturnData()),
                Arrays.asList(new HashMap<>()))).thenReturn(Arrays.asList(new HashMap<>()));

        // Run the test
        assertThatThrownBy(() -> departmentFilmServiceUnderTest.exportExcel(departmentFilmVo, response))
                .isInstanceOf(Exception.class);
    }
}
