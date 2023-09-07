package com.ikang.idata.search.search.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SearchResourceEnumTest {

    @Test
    void testGetFields() throws Exception {
        assertThat(SearchResourceEnum.PROJECT_ZONE_TYPE.getFields()).isEqualTo("fields");
        assertThat(SearchResourceEnum.chief_zone.getFields()).isEqualTo("fields");
    }

    @Test
    void testSetFields() throws Exception {
        // Run the test
        SearchResourceEnum.PROJECT_ZONE_TYPE.setFields("fields");
        SearchResourceEnum.chief_zone.setFields("fields");

        // Verify the results
    }

    @Test
    void testGetDesc() throws Exception {
        assertThat(SearchResourceEnum.PROJECT_ZONE_TYPE.getDesc()).isEqualTo("desc");
        assertThat(SearchResourceEnum.chief_zone.getDesc()).isEqualTo("desc");
    }

    @Test
    void testSetDesc() throws Exception {
        // Run the test
        SearchResourceEnum.PROJECT_ZONE_TYPE.setDesc("desc");
        SearchResourceEnum.chief_zone.setDesc("desc");

        // Verify the results
    }
}
