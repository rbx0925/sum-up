package com.ikang.idata.search.search.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class SaleItemReadyPayMentSigningDimensionControllerTestTest {

    @InjectMocks
    private SaleItemReadyPayMentSigningDimensionControllerTest saleItemReadyPayMentSigningDimensionControllerTestUnderTest;

    @Test
    void testTestFindItemReadyPaymentAgg() throws Exception {
        // Setup
        // Run the test
        saleItemReadyPayMentSigningDimensionControllerTestUnderTest.testFindItemReadyPaymentAgg();

        // Verify the results
    }

    @Test
    void testTestFindItemReadyPaymentAgg_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(
                () -> saleItemReadyPayMentSigningDimensionControllerTestUnderTest.testFindItemReadyPaymentAgg())
                .isInstanceOf(Exception.class);
    }

    @Test
    void testTestFindItemReadyPayMent() throws Exception {
        // Setup
        // Run the test
        saleItemReadyPayMentSigningDimensionControllerTestUnderTest.testFindItemReadyPayMent();

        // Verify the results
    }

    @Test
    void testTestFindItemReadyPayMent_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(
                () -> saleItemReadyPayMentSigningDimensionControllerTestUnderTest.testFindItemReadyPayMent())
                .isInstanceOf(Exception.class);
    }

    @Test
    void testTestDepartmentExportExcel() throws Exception {
        // Setup
        // Run the test
        saleItemReadyPayMentSigningDimensionControllerTestUnderTest.testDepartmentExportExcel();

        // Verify the results
    }

    @Test
    void testTestDepartmentExportExcel_ThrowsExceptio() {
        // Setup
        // Run the test
        assertThatThrownBy(
                () -> saleItemReadyPayMentSigningDimensionControllerTestUnderTest.testDepartmentExportExcel())
                .isInstanceOf(Exception.class);
    }

    @Test
    void testTestDepartmentExportExcel_ThrowsException() throws Exception {
        // Setup
        // Run the test
        saleItemReadyPayMentSigningDimensionControllerTestUnderTest.testDepartmentExportExcel_ThrowsException();

        // Verify the results
    }

    @Test
    void testTestDepartmentExportExcel_ThrowsException_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(
                () -> saleItemReadyPayMentSigningDimensionControllerTestUnderTest.testDepartmentExportExcel_ThrowsException())
                .isInstanceOf(Exception.class);
    }
}
