package com.ikang.idata.search.search.fallback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorityFeignServiceFallBackImplTest {

    private AuthorityFeignServiceFallBackImpl authorityFeignServiceFallBackImplUnderTest;

    @BeforeEach
    void setUp() {
        authorityFeignServiceFallBackImplUnderTest = new AuthorityFeignServiceFallBackImpl();
    }

    @Test
    void testGetChannelOrHospitals() {
        assertThat(authorityFeignServiceFallBackImplUnderTest.getChannelOrHospitals(0L)).isNull();
    }
}
