package com.ikang.idata.search.search.controller;

import cn.hutool.core.lang.Pair;
import com.ikang.idata.search.search.entity.vo.CustomerSatisfactionSearchVo;
import com.ikang.idata.search.search.entity.vo.MainCustomerSatisfactionVo;
import com.ikang.idata.search.search.service.MainCustomerSatisfactionService;
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
@WebMvcTest(SaleWorkCustomerSatisfactionController.class)
class SaleWorkCustomerSatisfactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MainCustomerSatisfactionService mockMainCustomerSatisfactionService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure MainCustomerSatisfactionService.find(...).
        final MainCustomerSatisfactionVo vo = new MainCustomerSatisfactionVo();
        vo.setSatisfiedSmsReply(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        vo.setCheckingSatisfaction(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        when(mockMainCustomerSatisfactionService.find(new CustomerSatisfactionSearchVo())).thenReturn(vo);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/saleWork/mainCustomerSatisfaction")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
