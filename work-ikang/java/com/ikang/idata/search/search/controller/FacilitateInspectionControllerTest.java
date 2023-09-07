package com.ikang.idata.search.search.controller;

import com.ikang.idata.search.search.common.PageResult;
import com.ikang.idata.search.search.entity.vo.FacilitateInspectionTableSearchVo;
import com.ikang.idata.search.search.entity.vo.FacilitateInspectionTableVo;
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

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FacilitateInspectionController.class)
class FacilitateInspectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacilitateInspectionService mockService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure FacilitateInspectionService.find(...).
        final PageResult<FacilitateInspectionTableVo> pageResult = new PageResult<>();
        pageResult.setResult(Arrays.asList());
        pageResult.setPageSize(0);
        pageResult.setPageNum(0);
        pageResult.setTotal(0);
        pageResult.setTotalStr("totalStr");
        final FacilitateInspectionTableSearchVo vo = new FacilitateInspectionTableSearchVo();
        vo.setName("name");
        vo.setQysjStart("qysjStart");
        vo.setQysjEnd("qysjEnd");
        vo.setXmjsrqStart("xmjsrqStart");
        vo.setXmjsrqEnd("xmjsrqEnd");
        when(mockService.find(vo)).thenReturn(pageResult);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/facilitateInspection/find")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
