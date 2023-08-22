package com.ikang.idata.search.search.controller;

import com.ikang.idata.search.search.entity.dto.PlusRankDTO;
import com.ikang.idata.search.search.entity.vo.PlusRankVo;
import com.ikang.idata.search.search.service.BranchPlusRankService;
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
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlusRankController.class)
class PlusRankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchPlusRankService mockBranchPlusRankService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure BranchPlusRankService.find(...).
        final PlusRankDTO plusRankDTO = new PlusRankDTO();
        final PlusRankDTO.RankTable rankTable = new PlusRankDTO.RankTable();
        rankTable.setTotalNumber(new BigDecimal("0.00"));
        rankTable.setTotalAmount(new BigDecimal("0.00"));
        rankTable.setCheckItemName("checkItemName");
        rankTable.setDepartmentName("departmentName");
        plusRankDTO.setNumber(Arrays.asList(rankTable));
        final PlusRankVo vo = new PlusRankVo();
        vo.setHospid("hospid");
        vo.setAreaid("areaid");
        vo.setCheckStartTime("checkStartTime");
        vo.setCheckEndTime("checkEndTime");
        when(mockBranchPlusRankService.find(vo)).thenReturn(plusRankDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/branchKanban/plusRank")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
