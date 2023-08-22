package com.ikang.idata.search.search.service;

import com.ikang.idata.search.search.entity.vo.PayCardSign;
import com.ikang.idata.search.search.entity.vo.PayCardSignInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PayCardSignServiceTest {

    private PayCardSignService payCardSignServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        payCardSignServiceUnderTest = new PayCardSignService();
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final PayCardSign payCardSign = new PayCardSign();
        payCardSign.setBrmZone("brmZone");
        payCardSign.setChiefZone("chiefZone");
        payCardSign.setSignStartDate("signStartDate");
        payCardSign.setSignEndDate("signEndDate");

        // Run the test
        final PayCardSignInfo result = payCardSignServiceUnderTest.find(payCardSign);

        // Verify the results
    }
}
