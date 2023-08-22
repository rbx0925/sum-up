package com.ikang.idata.search.search.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.ikang.idata.search.search.Java8;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;


public class Java8Test {

    private Java8 java8UnderTest;

    @BeforeEach
    void setUp() {
        java8UnderTest = new Java8();
    }

    @Test
    void testTestPage() {
        // Setup
        // Run the test
        java8UnderTest.testPage();

        // Verify the results
    }

    @Test
    void testTestJava() throws Exception {
        // Setup
        // Run the test
        java8UnderTest.testJava();

        // Verify the results
    }

    @Test
    void testTestJava_ThrowsInterruptedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> java8UnderTest.testJava()).isInstanceOf(InterruptedException.class);
    }

    @Test
    void testTeststart() throws Exception {
        // Setup
        // Run the test
        java8UnderTest.teststart();

        // Verify the results
    }

    @Test
    void testTeststart_ThrowsInterruptedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> java8UnderTest.teststart()).isInstanceOf(InterruptedException.class);
    }

    @Test
    void testTestM() {
        // Setup
        // Run the test
        java8UnderTest.testM();

        // Verify the results
    }

    @Test
    void testTestJava8() {
        // Setup
        // Run the test
        java8UnderTest.testJava8();

        // Verify the result
    }

    @Test
    void testTestCe() {
        // Setup
        // Run the test
        java8UnderTest.testCe();

        // Verify the results
    }

    @Test
    void testTest() {
        // Setup
        // Run the test
        java8UnderTest.test();

        // Verify the results
    }

    @Test
    void testTest7Vs8() {
        // Setup
        // Run the test
        java8UnderTest.test7Vs8();

        // Verify the results
    }

    @Test
    void testTestReduce() {
        // Setup
        // Run the test
        java8UnderTest.testReduce();

        // Verify the results
    }

    @Test
    void testTestDouble() {
        // Setup
        // Run the test
        java8UnderTest.testDouble();

        // Verify the results
    }

    @Test
    void testTestInt() {
        // Setup
        // Run the test
        java8UnderTest.testInt();

        // Verify the results
    }

    @Test
    void testTestParallel() {
        // Setup
        // Run the test
        java8UnderTest.testParallel();

        // Verify the results
    }

    @Test
    void testTestParallelStream() {
        // Setup
        // Run the test
        java8UnderTest.testParallelStream();

        // Verify the results
    }

    @Test
    void testProcess() {
        // Setup
        // Run the test
        java8UnderTest.process();

        // Verify the results
    }

    @Test
    void testMerge() {
        // Setup
        // Run the test
        java8UnderTest.merge();

        // Verify the results
    }

    @Test
    void testTestMock() {
        // Setup
        // Run the test
        java8UnderTest.testMock();

        // Verify the results
    }

    @Test
    void testTestMockTwo() {
        // Setup
        // Run the test
        java8UnderTest.testMockTwo();

        // Verify the results
    }

    @Test
    void testTestInOrder() {
        // Setup
        // Run the test
        java8UnderTest.testInOrder();

        // Verify the results
    }

    @Test
    void testTestNumberOfCalls() {
        // Setup
        // Run the test
        java8UnderTest.testNumberOfCalls();

        // Verify the results
    }

    @Test
    void testTestAssertThat() {
        // Setup
        // Run the test
        java8UnderTest.testAssertThat();

        // Verify the results
    }

    @Test
    void testTestContinuous() {
        // Setup
        // Run the test
        java8UnderTest.testContinuous();

        // Verify the results
    }

    @Test
    void testTestSort() {
        // Setup
        // Run the test
        java8UnderTest.testSort();

        // Verify the results
    }

    @Test
    void testTestPractice() {
        // Setup
        // Run the test
        java8UnderTest.testPractice();

        // Verify the results
    }

    @Test
    void testTestTree() {
        // Setup
        // Run the test
        java8UnderTest.testTree();

        // Verify the results
    }

    @Test
    void testTestStringChar() {
        // Setup
        // Run the test
        java8UnderTest.testStringChar();

        // Verify the results
    }

    @Test
    void testTestSocialCredit() {
        // Setup
        // Run the test
        java8UnderTest.testSocialCredit();

        // Verify the results
    }

    @Test
    void testTestGuava() {
        // Setup
        // Run the test
        java8UnderTest.testGuava();

        // Verify the results
    }

    @Test
    void testTestGuavaCollect() {
        // Setup
        // Run the test
        java8UnderTest.testGuavaCollect();

        // Verify the results
    }

    @Test
    void testTestGuavaSets() {
        // Setup
        // Run the test
        java8UnderTest.testGuavaSets();

        // Verify the results
    }

    @Test
    void testTesttest() {
        // Setup
        // Run the test
        java8UnderTest.testtest();

        // Verify the results
    }

    @Test
    void testTestPrimitive() {
        // Setup
        // Run the test
        java8UnderTest.testPrimitive();

        // Verify the results
    }

    @Test
    void testTestString() {
        // Setup
        // Run the test
        java8UnderTest.testString();

        // Verify the results
    }

    @Test
    void testTestStr() {
        // Setup
        // Run the test
        java8UnderTest.testStr();

        // Verify the results
    }

    @Test
    void testTestX() {
        // Setup
        // Run the test
        java8UnderTest.testX();

        // Verify the results
    }

    @Test
    void testTestIP() {
        // Setup
        // Run the test
        java8UnderTest.testIP();

        // Verify the results
    }

    @Test
    void testTestc() {
        // Setup
        // Run the test
        java8UnderTest.testc();

        // Verify the results
    }

    @Test
    void testTestEnum() {
        // Setup
        // Run the test
        java8UnderTest.testEnum();

        // Verify the results
    }

    @Test
    void testTestSecretCode() throws Exception {
        // Setup
        // Run the test
        java8UnderTest.testSecretCode();

        // Verify the results
    }

    @Test
    void testTestSecretCode_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> java8UnderTest.testSecretCode()).isInstanceOf(Exception.class);
    }

    @Test
    void testTestencrypt() {
        // Setup
        // Run the test
        java8UnderTest.testencrypt();

        // Verify the results
    }

    @Test
    void testTestTimingWheel() throws Exception {
        // Setup
        // Run the test
        java8UnderTest.testTimingWheel();

        // Verify the results
    }

    @Test
    void testTestTimingWheel_ThrowsInterruptedException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> java8UnderTest.testTimingWheel()).isInstanceOf(InterruptedException.class);
    }

    @Test
    void testGetPageLimit() {
        assertThat(Java8.getPageLimit(Arrays.asList("value"), 0L, 0L)).isEqualTo(Arrays.asList("value"));
        assertThat(Java8.getPageLimit(Arrays.asList("value"), 0L, 0L)).isEqualTo(Collections.emptyList());
    }

    @Test
    void testMain() {
        // Setup
        // Run the test
        Java8.main(new String[]{"args"});

        // Verify the results
    }

    @Test
    void testIsContinuous() {
        assertThat(Java8.IsContinuous(new int[]{0})).isFalse();
    }

    @Test
    void testClone() {
        // Setup
        final Consumer<String> mockConsumer = mock(Consumer.class);

        // Run the test
        final String result = Java8.clone("object", mockConsumer);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testMax() {
        assertThat(Java8.max("obj1", "obj2")).isEqualTo("result");
    }

    @Test
    void testGetList() {
        // Setup
        final Multimap<String, String> multimap = ImmutableMultimap.of("value", "value");

        // Run the test
        final List<String> result = Java8.getList(multimap, Arrays.asList("value"));

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testFindAndThen() {
        // Setup
        final Map<String, String> map = new HashMap<>();
        final Consumer<String> mockConsumer = mock(Consumer.class);

        // Run the test
        Java8.findAndThen(map, "key", mockConsumer);

        // Verify the results
    }

    @Test
    void testInit() {
        // Setup
        final ObjectMapper objectMapper = new ObjectMapper();

        // Run the test
        Java8.init(objectMapper);

        // Verify the results
    }

    @Test
    void testToJsonString() {
        assertThat(Java8.toJsonString("object")).isEqualTo("result");
    }

    @Test
    void testParseObject1() {
        assertThat(Java8.parseObject("text", String.class)).isEqualTo("result");
    }

    @Test
    void testParseObject2() {
        assertThat(Java8.parseObject("content".getBytes(), String.class)).isEqualTo("result");
    }

    @Test
    void testParseObject3() {
        // Setup
        final TypeReference<String> typeReference = null;

        // Run the test
        final String result = Java8.parseObject("text", typeReference);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testParseArray() {
        assertThat(Java8.parseArray("text", String.class)).isEqualTo(Arrays.asList("value"));
        assertThat(Java8.parseArray("text", String.class)).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetLocalIP() {
        assertThat(Java8.getLocalIP()).isEqualTo("");
    }

    @Test
    void testGetLocalIPS() {
        assertThat(Java8.getLocalIPS()).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testGetKey() {
        assertThat(Java8.getKey()).isEqualTo("AnjiPLUSAjReport");
    }

    @Test
    void testBinary() {
        assertThat(Java8.binary("content".getBytes(), 0)).isEqualTo("result");
    }

    @Test
    void testBase64Encode() {
        assertThat(Java8.base64Encode("content".getBytes())).isEqualTo("result");
    }

    @Test
    void testBase64Decode() throws Exception {
        assertThat(Java8.base64Decode("base64Code")).isEqualTo("content".getBytes());
        assertThatThrownBy(() -> Java8.base64Decode("base64Code")).isInstanceOf(Exception.class);
    }

    @Test
    void testAesEncryptToBytes() throws Exception {
        assertThat(Java8.aesEncryptToBytes("content", "encryptKey")).isEqualTo("content".getBytes());
        assertThatThrownBy(() -> Java8.aesEncryptToBytes("content", "encryptKey")).isInstanceOf(Exception.class);
    }

    @Test
    void testAesEncrypt() throws Exception {
        assertThat(Java8.aesEncrypt("content", "encryptKey")).isEqualTo("content");
        assertThatThrownBy(() -> Java8.aesEncrypt("content", "encryptKey")).isInstanceOf(Exception.class);
    }

    @Test
    void testAesDecryptByBytes() throws Exception {
        assertThat(Java8.aesDecryptByBytes("content".getBytes(), "decryptKey")).isEqualTo("result");
        assertThatThrownBy(() -> Java8.aesDecryptByBytes("content".getBytes(), "decryptKey"))
                .isInstanceOf(Exception.class);
    }

    @Test
    void testAesDecrypt() throws Exception {
        assertThat(Java8.aesDecrypt("encryptStr", "decryptKey")).isEqualTo("encryptStr");
        assertThatThrownBy(() -> Java8.aesDecrypt("encryptStr", "decryptKey")).isInstanceOf(Exception.class);
    }
}
