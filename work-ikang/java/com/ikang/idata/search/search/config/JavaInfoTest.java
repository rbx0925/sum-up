package com.ikang.idata.search.search.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class JavaInfoTest {

    private JavaInfo javaInfoUnderTest;

    @BeforeEach
    void setUp() {
        javaInfoUnderTest = new JavaInfo();
    }

    @Test
    void testGetVersion() {
        assertThat(javaInfoUnderTest.getVersion()).isEqualTo("JAVA_VERSION");
    }

    @Test
    void testGetVersionFloat() {
        assertThat(javaInfoUnderTest.getVersionFloat()).isEqualTo(0.0f, within(0.0001f));
    }

    @Test
    void testGetVersionInt() {
        assertThat(javaInfoUnderTest.getVersionInt()).isEqualTo(0);
    }

    @Test
    void testGetVendor() {
        assertThat(javaInfoUnderTest.getVendor()).isEqualTo("JAVA_VENDOR");
    }

    @Test
    void testGetVendorURL() {
        assertThat(javaInfoUnderTest.getVendorURL()).isEqualTo("JAVA_VENDOR_URL");
    }

    @Test
    void testIsJava1_1() {
        assertThat(javaInfoUnderTest.isJava1_1()).isTrue();
    }

    @Test
    void testIsJava1_2() {
        assertThat(javaInfoUnderTest.isJava1_2()).isTrue();
    }

    @Test
    void testIsJava1_3() {
        assertThat(javaInfoUnderTest.isJava1_3()).isTrue();
    }

    @Test
    void testIsJava1_4() {
        assertThat(javaInfoUnderTest.isJava1_4()).isTrue();
    }

    @Test
    void testIsJava1_5() {
        assertThat(javaInfoUnderTest.isJava1_5()).isTrue();
    }

    @Test
    void testIsJava1_6() {
        assertThat(javaInfoUnderTest.isJava1_6()).isTrue();
    }

    @Test
    void testIsJava1_7() {
        assertThat(javaInfoUnderTest.isJava1_7()).isTrue();
    }

    @Test
    void testIsJava1_8() {
        assertThat(javaInfoUnderTest.isJava1_8()).isTrue();
    }

    @Test
    void testIsJava9() {
        assertThat(javaInfoUnderTest.isJava9()).isTrue();
    }

    @Test
    void testIsJava10() {
        assertThat(javaInfoUnderTest.isJava10()).isTrue();
    }

    @Test
    void testIsJava11() {
        assertThat(javaInfoUnderTest.isJava11()).isTrue();
    }

    @Test
    void testIsJava12() {
        assertThat(javaInfoUnderTest.isJava12()).isTrue();
    }

    @Test
    void testIsJavaVersionAtLeast1() {
        assertThat(javaInfoUnderTest.isJavaVersionAtLeast(0.0f)).isTrue();
    }

    @Test
    void testIsJavaVersionAtLeast2() {
        assertThat(javaInfoUnderTest.isJavaVersionAtLeast(0)).isTrue();
    }

    @Test
    void testToString() {
        assertThat(javaInfoUnderTest.toString()).isEqualTo("result");
    }
}
