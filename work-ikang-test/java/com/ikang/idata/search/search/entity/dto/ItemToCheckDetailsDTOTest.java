package com.ikang.idata.search.search.entity.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ItemToCheckDetailsDTOTest {

    private ItemToCheckDetailsDTO itemToCheckDetailsDTOUnderTest;

    @BeforeEach
    void setUp() {
        itemToCheckDetailsDTOUnderTest = new ItemToCheckDetailsDTO();
    }

    @Test
    void testGetProjectAmount() {
        assertThat(itemToCheckDetailsDTOUnderTest.getProjectAmount()).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testGetActivateCardAmount() {
        assertThat(itemToCheckDetailsDTOUnderTest.getActivateCardAmount()).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testGetNotBookedAmount() {
        assertThat(itemToCheckDetailsDTOUnderTest.getNotBookedAmount()).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testGetNotArrivedAmount() {
        assertThat(itemToCheckDetailsDTOUnderTest.getNotArrivedAmount()).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testGetCheckInAmount() {
        assertThat(itemToCheckDetailsDTOUnderTest.getCheckInAmount()).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testEquals() {
        assertThat(itemToCheckDetailsDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(itemToCheckDetailsDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(itemToCheckDetailsDTOUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(itemToCheckDetailsDTOUnderTest.toString()).isEqualTo("result");
    }
}
