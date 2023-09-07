package com.ikang.idata.search.search.javaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CommonPageTest {

    private CommonPage<String> commonPageUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        commonPageUnderTest = new CommonPage<>();
    }

    @Test
    void testRestPage1() {
        // Run the test
        final CommonPage<String> result = CommonPage.restPage(Arrays.asList("value"));
        assertThat(result.getPageNum()).isEqualTo(0);
        assertThat(result.getPageSize()).isEqualTo(0);
        assertThat(result.getTotalPage()).isEqualTo(0);
        assertThat(result.getList()).isEqualTo(Arrays.asList("value"));
        assertThat(result.getTotal()).isEqualTo(0L);
    }

    @Test
    void testRestPage2() {
        // Setup
        final Page<String> pageInfo = new PageImpl<>(Arrays.asList("value"));

        // Run the test
        final CommonPage<String> result = CommonPage.restPage(pageInfo);
        assertThat(result.getPageNum()).isEqualTo(0);
        assertThat(result.getPageSize()).isEqualTo(0);
        assertThat(result.getTotalPage()).isEqualTo(0);
        assertThat(result.getList()).isEqualTo(Arrays.asList("value"));
        assertThat(result.getTotal()).isEqualTo(0L);
    }
}
