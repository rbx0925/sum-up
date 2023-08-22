package com.ikang.idata.search.search.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BrmUserAuthEnumTest {

    @Test
    void testGetFields() {
        assertThat(BrmUserAuthEnum.esid.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.leaderid.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.leadername.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.leaderprofile.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.leaderisusing.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.id.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.name.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.profile.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.isusing.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.chief_zone.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.brm_zone.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.brm_department.getFields()).isEqualTo("fields");
        assertThat(BrmUserAuthEnum.brm_group.getFields()).isEqualTo("fields");
    }

    @Test
    void testSetFields() {
        // Run the test
        BrmUserAuthEnum.esid.setFields("fields");
        BrmUserAuthEnum.leaderid.setFields("fields");
        BrmUserAuthEnum.leadername.setFields("fields");
        BrmUserAuthEnum.leaderprofile.setFields("fields");
        BrmUserAuthEnum.leaderisusing.setFields("fields");
        BrmUserAuthEnum.id.setFields("fields");
        BrmUserAuthEnum.name.setFields("fields");
        BrmUserAuthEnum.profile.setFields("fields");
        BrmUserAuthEnum.isusing.setFields("fields");
        BrmUserAuthEnum.chief_zone.setFields("fields");
        BrmUserAuthEnum.brm_zone.setFields("fields");
        BrmUserAuthEnum.brm_department.setFields("fields");
        BrmUserAuthEnum.brm_group.setFields("fields");

        // Verify the results
    }

    @Test
    void testGetDesc() {
        assertThat(BrmUserAuthEnum.esid.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.leaderid.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.leadername.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.leaderprofile.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.leaderisusing.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.id.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.name.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.profile.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.isusing.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.chief_zone.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.brm_zone.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.brm_department.getDesc()).isEqualTo("desc");
        assertThat(BrmUserAuthEnum.brm_group.getDesc()).isEqualTo("desc");
    }

    @Test
    void testSetDesc() {
        // Run the test
        BrmUserAuthEnum.esid.setDesc("desc");
        BrmUserAuthEnum.leaderid.setDesc("desc");
        BrmUserAuthEnum.leadername.setDesc("desc");
        BrmUserAuthEnum.leaderprofile.setDesc("desc");
        BrmUserAuthEnum.leaderisusing.setDesc("desc");
        BrmUserAuthEnum.id.setDesc("desc");
        BrmUserAuthEnum.name.setDesc("desc");
        BrmUserAuthEnum.profile.setDesc("desc");
        BrmUserAuthEnum.isusing.setDesc("desc");
        BrmUserAuthEnum.chief_zone.setDesc("desc");
        BrmUserAuthEnum.brm_zone.setDesc("desc");
        BrmUserAuthEnum.brm_department.setDesc("desc");
        BrmUserAuthEnum.brm_group.setDesc("desc");

        // Verify the results
    }
}
