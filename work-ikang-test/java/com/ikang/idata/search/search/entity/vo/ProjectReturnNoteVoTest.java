package com.ikang.idata.search.search.entity.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectReturnNoteVoTest {

    private ProjectReturnNoteVo projectReturnNoteVoUnderTest;

    @BeforeEach
    void setUp() {
        projectReturnNoteVoUnderTest = new ProjectReturnNoteVo();
    }

    @Test
    void testEquals() {
        assertThat(projectReturnNoteVoUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(projectReturnNoteVoUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(projectReturnNoteVoUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(projectReturnNoteVoUnderTest.toString()).isEqualTo("result");
    }
}
