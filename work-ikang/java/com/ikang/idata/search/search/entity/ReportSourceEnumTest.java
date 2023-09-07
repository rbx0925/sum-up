package com.ikang.idata.search.search.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReportSourceEnumTest {

    @Test
    void testGetFields() {
        assertThat(ReportSourceEnum.id.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.channel_code.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.channel_name.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.download_user.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.download_ip.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.work_no.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.card_num.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.mobile.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.id_type.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.id_type_name.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.id_number.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.tjy_report_id.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.tjy_report_type.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.report_type.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.report_type_name.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.report_fid.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.report_upload_time.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.security_code_salt.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.security_code.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.simple_security_code.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.hosp_id.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.hosp_name.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.locid.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.locname.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.areaid.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.areaname.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.doc_summary.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.download_time.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.create_time.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.uni_type.getFields()).isEqualTo("fields");
        assertThat(ReportSourceEnum.uni_type_name.getFields()).isEqualTo("fields");
    }

    @Test
    void testSetFields() {
        // Run the test
        ReportSourceEnum.id.setFields("fields");
        ReportSourceEnum.channel_code.setFields("fields");
        ReportSourceEnum.channel_name.setFields("fields");
        ReportSourceEnum.download_user.setFields("fields");
        ReportSourceEnum.download_ip.setFields("fields");
        ReportSourceEnum.work_no.setFields("fields");
        ReportSourceEnum.card_num.setFields("fields");
        ReportSourceEnum.mobile.setFields("fields");
        ReportSourceEnum.id_type.setFields("fields");
        ReportSourceEnum.id_type_name.setFields("fields");
        ReportSourceEnum.id_number.setFields("fields");
        ReportSourceEnum.tjy_report_id.setFields("fields");
        ReportSourceEnum.tjy_report_type.setFields("fields");
        ReportSourceEnum.report_type.setFields("fields");
        ReportSourceEnum.report_type_name.setFields("fields");
        ReportSourceEnum.report_fid.setFields("fields");
        ReportSourceEnum.report_upload_time.setFields("fields");
        ReportSourceEnum.security_code_salt.setFields("fields");
        ReportSourceEnum.security_code.setFields("fields");
        ReportSourceEnum.simple_security_code.setFields("fields");
        ReportSourceEnum.hosp_id.setFields("fields");
        ReportSourceEnum.hosp_name.setFields("fields");
        ReportSourceEnum.locid.setFields("fields");
        ReportSourceEnum.locname.setFields("fields");
        ReportSourceEnum.areaid.setFields("fields");
        ReportSourceEnum.areaname.setFields("fields");
        ReportSourceEnum.doc_summary.setFields("fields");
        ReportSourceEnum.download_time.setFields("fields");
        ReportSourceEnum.create_time.setFields("fields");
        ReportSourceEnum.uni_type.setFields("fields");
        ReportSourceEnum.uni_type_name.setFields("fields");

        // Verify the results
    }

    @Test
    void testGetDesc() {
        assertThat(ReportSourceEnum.id.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.channel_code.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.channel_name.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.download_user.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.download_ip.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.work_no.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.card_num.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.mobile.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.id_type.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.id_type_name.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.id_number.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.tjy_report_id.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.tjy_report_type.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.report_type.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.report_type_name.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.report_fid.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.report_upload_time.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.security_code_salt.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.security_code.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.simple_security_code.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.hosp_id.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.hosp_name.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.locid.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.locname.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.areaid.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.areaname.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.doc_summary.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.download_time.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.create_time.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.uni_type.getDesc()).isEqualTo("desc");
        assertThat(ReportSourceEnum.uni_type_name.getDesc()).isEqualTo("desc");
    }

    @Test
    void testSetDesc() {
        // Run the test
        ReportSourceEnum.id.setDesc("desc");
        ReportSourceEnum.channel_code.setDesc("desc");
        ReportSourceEnum.channel_name.setDesc("desc");
        ReportSourceEnum.download_user.setDesc("desc");
        ReportSourceEnum.download_ip.setDesc("desc");
        ReportSourceEnum.work_no.setDesc("desc");
        ReportSourceEnum.card_num.setDesc("desc");
        ReportSourceEnum.mobile.setDesc("desc");
        ReportSourceEnum.id_type.setDesc("desc");
        ReportSourceEnum.id_type_name.setDesc("desc");
        ReportSourceEnum.id_number.setDesc("desc");
        ReportSourceEnum.tjy_report_id.setDesc("desc");
        ReportSourceEnum.tjy_report_type.setDesc("desc");
        ReportSourceEnum.report_type.setDesc("desc");
        ReportSourceEnum.report_type_name.setDesc("desc");
        ReportSourceEnum.report_fid.setDesc("desc");
        ReportSourceEnum.report_upload_time.setDesc("desc");
        ReportSourceEnum.security_code_salt.setDesc("desc");
        ReportSourceEnum.security_code.setDesc("desc");
        ReportSourceEnum.simple_security_code.setDesc("desc");
        ReportSourceEnum.hosp_id.setDesc("desc");
        ReportSourceEnum.hosp_name.setDesc("desc");
        ReportSourceEnum.locid.setDesc("desc");
        ReportSourceEnum.locname.setDesc("desc");
        ReportSourceEnum.areaid.setDesc("desc");
        ReportSourceEnum.areaname.setDesc("desc");
        ReportSourceEnum.doc_summary.setDesc("desc");
        ReportSourceEnum.download_time.setDesc("desc");
        ReportSourceEnum.create_time.setDesc("desc");
        ReportSourceEnum.uni_type.setDesc("desc");
        ReportSourceEnum.uni_type_name.setDesc("desc");

        // Verify the results
    }
}
