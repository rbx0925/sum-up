package com.ikang.idata.search.search.common;

import com.ikang.idata.common.entity.ReturnData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FieldInfoTest {

    @Mock
    private ReturnData mockReturnData;

    private FieldInfo fieldInfoUnderTest;

    @BeforeEach
    void setUp() {
        fieldInfoUnderTest = new FieldInfo(mockReturnData);
    }

    @Test
    void testEquals() {
        assertThat(fieldInfoUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(fieldInfoUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(fieldInfoUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(fieldInfoUnderTest.toString()).isEqualTo("result");
    }
}
