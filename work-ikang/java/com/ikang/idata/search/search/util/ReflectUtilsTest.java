package com.ikang.idata.search.search.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReflectUtilsTest {

    @Test
    void testSpringInvokeMethod1() throws Exception {
        assertThat(ReflectUtils.springInvokeMethod("serviceName", "methodName", "params")).isEqualTo("result");
        assertThatThrownBy(() -> ReflectUtils.springInvokeMethod("serviceName", "methodName", "params"))
                .isInstanceOf(Exception.class);
    }

    @Test
    void testSpringInvokeMethod2() {
//        assertThat(ReflectUtils.springInvokeMethod(String.class, "methodName", "params")).isEqualTo("result");
    }

    @Test
    void testSpringInvokeMethod3() throws Exception {
        assertThat(ReflectUtils.springInvokeMethod("service", "methodName", "params")).isEqualTo("result");
    }
}
