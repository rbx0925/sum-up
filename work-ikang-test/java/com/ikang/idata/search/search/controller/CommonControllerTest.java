package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.DictInfo;
import com.ikang.idata.common.entity.dto.BSidePushRecordsEsResults;
import com.ikang.idata.common.entity.dto.MobilePhoneNumberInfo;
import com.ikang.idata.common.entity.vo.FacilitateInspectionVo;
import com.ikang.idata.common.entity.vo.PerformanceStatisticsVo;
import com.ikang.idata.common.entity.vo.TelemarketingVo;
import com.ikang.idata.search.search.entity.vo.BRMZone;
import com.ikang.idata.search.search.entity.vo.BRMZoneDTO;
import com.ikang.idata.search.search.feign.impl.DictFeignServiceImpl;
import com.ikang.idata.search.search.service.BRMResourceService;
import com.ikang.idata.search.search.service.CommonService;
import com.ikang.idata.search.search.service.FacilitateInspectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommonController.class)
class CommonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommonService mockCommonService;
    @MockBean
    private BRMResourceService mockBrmResourceService;
    @MockBean
    private DictFeignServiceImpl mockDictFeignService;
    @MockBean
    private FacilitateInspectionService mockInspectionService;

    @Test
    void testQueryPhoneNumber() throws Exception {
        // Setup
        // Configure CommonService.queryPhoneNumber(...).
        final TelemarketingVo vo = new TelemarketingVo();
        vo.setServiceName("serviceName");
        vo.setPhoneNumberTypeField("phoneNumberTypeField");
        vo.setConditionRecord("conditionRecord");
        vo.setProjectName("projectName");
        vo.setProjectDescribe("projectDescribe");
        when(mockCommonService.queryPhoneNumber(vo, 0L, "serviceName")).thenReturn(new HashMap<>());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                post("/queryPhoneNumber/{resourceId}/{serviceName}", 0, "serviceName")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testQueryPhoneNumber_CommonServiceThrowsException() throws Exception {
        // Setup
        // Configure CommonService.queryPhoneNumber(...).
        final TelemarketingVo vo = new TelemarketingVo();
        vo.setServiceName("serviceName");
        vo.setPhoneNumberTypeField("phoneNumberTypeField");
        vo.setConditionRecord("conditionRecord");
        vo.setProjectName("projectName");
        vo.setProjectDescribe("projectDescribe");
        when(mockCommonService.queryPhoneNumber(vo, 0L, "serviceName")).thenThrow(Exception.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                post("/queryPhoneNumber/{resourceId}/{serviceName}", 0, "serviceName")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindReport() throws Exception {
        // Setup
        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("brmZone");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/getZone")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");

        // Confirm BRMResourceService.compareToSort(...).
        final BRMZoneDTO brmZoneDTO = new BRMZoneDTO();
        final BRMZone brmZone1 = new BRMZone();
        brmZone1.setBrmZone("brmZone");
        brmZoneDTO.setBrmZone(Arrays.asList(brmZone1));
        brmZoneDTO.setProjectZoneType("projectZoneType");
        final List<BRMZoneDTO> brmZone = Arrays.asList(brmZoneDTO);
        verify(mockBrmResourceService).compareToSort(brmZone);
    }

    @Test
    void testFindReport_DictFeignServiceImplReturnsNoItems() throws Exception {
        // Setup
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/getZone")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");

        // Confirm BRMResourceService.compareToSort(...).
        final BRMZoneDTO brmZoneDTO = new BRMZoneDTO();
        final BRMZone brmZone1 = new BRMZone();
        brmZone1.setBrmZone("brmZone");
        brmZoneDTO.setBrmZone(Arrays.asList(brmZone1));
        brmZoneDTO.setProjectZoneType("projectZoneType");
        final List<BRMZoneDTO> brmZone = Arrays.asList(brmZoneDTO);
        verify(mockBrmResourceService).compareToSort(brmZone);
    }

    @Test
    void testPhoneQuantity() throws Exception {
        // Setup
        // Configure FacilitateInspectionService.queryPhoneQuantity(...).
        final MobilePhoneNumberInfo mobilePhoneNumberInfo = new MobilePhoneNumberInfo();
        mobilePhoneNumberInfo.setMobilePhoneNumber(new HashSet<>(Arrays.asList("value")));
        final MobilePhoneNumberInfo.Record record = new MobilePhoneNumberInfo.Record();
        record.setProjectNumber("projectNumber");
        record.setMobilePhoneNumber("mobilePhoneNumber");
        record.setCardNumber("cardNumber");
        mobilePhoneNumberInfo.setRecords(Arrays.asList(record));
        final FacilitateInspectionVo vo = new FacilitateInspectionVo();
        vo.setName("name");
        vo.setQysjStart("qysjStart");
        vo.setQysjEnd("qysjEnd");
        vo.setXmjsrqStart("xmjsrqStart");
        vo.setXmjsrqEnd("xmjsrqEnd");
        when(mockInspectionService.queryPhoneQuantity(vo)).thenReturn(mobilePhoneNumberInfo);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/facilitateInspection/phoneQuantity")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testPhoneQuantity_FacilitateInspectionServiceThrowsException() throws Exception {
        // Setup
        // Configure FacilitateInspectionService.queryPhoneQuantity(...).
        final FacilitateInspectionVo vo = new FacilitateInspectionVo();
        vo.setName("name");
        vo.setQysjStart("qysjStart");
        vo.setQysjEnd("qysjEnd");
        vo.setXmjsrqStart("xmjsrqStart");
        vo.setXmjsrqEnd("xmjsrqEnd");
        when(mockInspectionService.queryPhoneQuantity(vo)).thenThrow(Exception.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/facilitateInspection/phoneQuantity")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testBSidePushRecords() throws Exception {
        // Setup
        // Configure FacilitateInspectionService.bSidePushRecords(...).
        final BSidePushRecordsEsResults bSidePushRecordsEsResults1 = new BSidePushRecordsEsResults();
        bSidePushRecordsEsResults1.setId(0L);
        bSidePushRecordsEsResults1.setSuccessfulReach(new BigDecimal("0.00"));
        bSidePushRecordsEsResults1.setSmsReach(new BigDecimal("0.00"));
        bSidePushRecordsEsResults1.setReservedCards(new BigDecimal("0.00"));
        bSidePushRecordsEsResults1.setAppointmentRate(new BigDecimal("0.00"));
        final List<BSidePushRecordsEsResults> bSidePushRecordsEsResults = Arrays.asList(bSidePushRecordsEsResults1);
        when(mockInspectionService.bSidePushRecords(Arrays.asList(0L))).thenReturn(bSidePushRecordsEsResults);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/bSidePushRecords/partialResults")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testBSidePushRecords_FacilitateInspectionServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockInspectionService.bSidePushRecords(Arrays.asList(0L))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/bSidePushRecords/partialResults")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testStatistics() throws Exception {
        // Setup
        // Configure FacilitateInspectionService.effectStatistics(...).
        final PerformanceStatisticsVo performanceStatisticsVo = new PerformanceStatisticsVo();
        performanceStatisticsVo.setId(0L);
        performanceStatisticsVo.setTitle("title");
        performanceStatisticsVo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        performanceStatisticsVo.setPushNum("pushNum");
        performanceStatisticsVo.setSuccessfulReach(new BigDecimal("0.00"));
        when(mockInspectionService.effectStatistics(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(performanceStatisticsVo);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/bSidePushRecords/effectStatistics/{id}", 0)
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
