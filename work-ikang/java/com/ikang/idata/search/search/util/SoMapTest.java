package com.ikang.idata.search.search.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class SoMapTest {

    private SoMap soMapUnderTest;

    @BeforeEach
    void setUp() {
        soMapUnderTest = new SoMap();
    }

    @Test
    void testGet1() {
        assertThat(soMapUnderTest.get("key")).isEqualTo("result");
        assertThat(soMapUnderTest.get("key")).isNull();
    }

    @Test
    void testGet2() {
        assertThat(soMapUnderTest.get("key", "defaultValue")).isEqualTo("defaultValue");
    }

    @Test
    void testGetString1() {
        assertThat(soMapUnderTest.getString("key")).isEqualTo("result");
    }

    @Test
    void testGetString2() {
        assertThat(soMapUnderTest.getString("key", "defaultValue")).isEqualTo("defaultValue");
    }

    @Test
    void testGetInt1() {
        assertThat(soMapUnderTest.getInt("key")).isEqualTo(0);
    }

    @Test
    void testGetInt2() {
        assertThat(soMapUnderTest.getInt("key", 0)).isEqualTo(0);
    }

    @Test
    void testGetLong() {
        assertThat(soMapUnderTest.getLong("key")).isEqualTo(0L);
    }

    @Test
    void testGetDouble() {
        assertThat(soMapUnderTest.getDouble("key")).isEqualTo(0.0, within(0.0001));
    }

    @Test
    void testGetBoolean() {
        assertThat(soMapUnderTest.getBoolean("key")).isFalse();
    }

    @Test
    void testGetDateByFormat() {
        assertThat(soMapUnderTest.getDateByFormat("key", "format"))
                .isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testGetDate() {
        assertThat(soMapUnderTest.getDate("key")).isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testGetDateTime() {
        assertThat(soMapUnderTest.getDateTime("key"))
                .isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
    }

    @Test
    void testGetList1() {
        assertThat(soMapUnderTest.getList("key")).isEqualTo(Arrays.asList("value"));
        assertThat(soMapUnderTest.getList("key")).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetList2() {
        assertThat(soMapUnderTest.getList("key", String.class)).isEqualTo(Arrays.asList("value"));
        assertThat(soMapUnderTest.getList("key", String.class)).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetListByComma() {
        assertThat(soMapUnderTest.getListByComma("key", String.class)).isEqualTo(Arrays.asList("value"));
        assertThat(soMapUnderTest.getListByComma("key", String.class)).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetModel() {
        assertThat(soMapUnderTest.getModel(String.class)).isEqualTo("result");
    }

    @Test
    void testGetModelByObject() {
        assertThat(soMapUnderTest.getModelByObject("obj")).isEqualTo("result");
    }

    @Test
    void testSetDefaultValue() {
        // Setup
        // Run the test
        soMapUnderTest.setDefaultValue("key", "defaultValue");

        // Verify the results
    }

    @Test
    void testSet() {
        // Setup
        final SoMap expectedResult = new SoMap();

        // Run the test
        final SoMap result = soMapUnderTest.set("key", "value");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSetMap() {
        // Setup
        final Map<String, ?> map = new HashMap<>();
        final SoMap expectedResult = new SoMap();

        // Run the test
        final SoMap result = soMapUnderTest.setMap(map);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSetModel() {
        // Setup
        final SoMap expectedResult = new SoMap();

        // Run the test
        final SoMap result = soMapUnderTest.setModel("model");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testIsNotNull() {
        assertThat(soMapUnderTest.isNotNull("key")).isFalse();
    }

    @Test
    void testHas() {
        assertThat(soMapUnderTest.has("key")).isFalse();
    }

    @Test
    void testValueIsNull() {
        assertThat(soMapUnderTest.valueIsNull("value")).isFalse();
    }

    @Test
    void testIsNull() {
        assertThat(soMapUnderTest.isNull("key")).isFalse();
    }

    @Test
    void testCheckNull() {
        // Setup
        final SoMap expectedResult = new SoMap();

        // Run the test
        final SoMap result = soMapUnderTest.checkNull("keys");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testIsNumber() {
        assertThat(soMapUnderTest.isNumber("key")).isFalse();
    }

    @Test
    void testToJsonString() {
        assertThat(soMapUnderTest.toJsonString()).isEqualTo("result");
    }

    @Test
    void testToJsonFormatString() {
        assertThat(soMapUnderTest.toJsonFormatString()).isEqualTo("result");
    }

    @Test
    void testGetKeyPageNo() {
        assertThat(soMapUnderTest.getKeyPageNo()).isEqualTo(0);
    }

    @Test
    void testGetKeyPageSize() {
        assertThat(soMapUnderTest.getKeyPageSize()).isEqualTo(0);
    }

    @Test
    void testGetKeySortType() {
        assertThat(soMapUnderTest.getKeySortType()).isEqualTo(0);
    }

    @Test
    void testGetParam() {
        // Setup
        final HttpServletRequest request = new MockHttpServletRequest();

        // Run the test
        final String result = SoMap.getParam(request, "key");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testGetValueByClass() {
        assertThat(SoMap.getValueByClass("obj", String.class)).isEqualTo("result");
    }

    @Test
    void testGetRequestSoMap() {
        // Setup
        final com.ikang.idata.search.search.common.SoMap expectedResult = new com.ikang.idata.search.search.common.SoMap();

        // Run the test
        final com.ikang.idata.search.search.common.SoMap result = SoMap.getRequestSoMap();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testIsJavaWeb() {
        assertThat(SoMap.isJavaWeb()).isFalse();
    }

    @Test
    void testListToTree() {
        // Setup
        final List<com.ikang.idata.search.search.common.SoMap> list = Arrays.asList(
                new com.ikang.idata.search.search.common.SoMap());
        final List<com.ikang.idata.search.search.common.SoMap> expectedResult = Arrays.asList(
                new com.ikang.idata.search.search.common.SoMap());

        // Run the test
        final List<com.ikang.idata.search.search.common.SoMap> result = SoMap.listToTree(list, "idKey", "parentIdKey",
                "childListKey");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
