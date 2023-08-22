package com.ikang.idata.search.search.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReaderEnumTest {

    @Test
    void testGetFields() {
        assertThat(ReaderEnum.type.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.reader_locationname.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.reader_reader_area.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.doctor_ncc.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.doctor_name.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.doctor_id.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.doctor_loginid.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.doctor_account_type.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.exam_date.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.examine_date.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.institute_id.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.hospname_name.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.areaname_name.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.areaname_id.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.locid_id.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.locationname_name.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.item_code.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.item_name.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.item_status.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.department_name.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.department_id.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.agg_doctorSWorkload.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.is_outside.getFields()).isEqualTo("fields");
        assertThat(ReaderEnum.ct.getFields()).isEqualTo("fields");
    }

    @Test
    void testSetFields() {
        // Run the test
        ReaderEnum.type.setFields("fields");
        ReaderEnum.reader_locationname.setFields("fields");
        ReaderEnum.reader_reader_area.setFields("fields");
        ReaderEnum.doctor_ncc.setFields("fields");
        ReaderEnum.doctor_name.setFields("fields");
        ReaderEnum.doctor_id.setFields("fields");
        ReaderEnum.doctor_loginid.setFields("fields");
        ReaderEnum.doctor_account_type.setFields("fields");
        ReaderEnum.exam_date.setFields("fields");
        ReaderEnum.examine_date.setFields("fields");
        ReaderEnum.institute_id.setFields("fields");
        ReaderEnum.hospname_name.setFields("fields");
        ReaderEnum.areaname_name.setFields("fields");
        ReaderEnum.areaname_id.setFields("fields");
        ReaderEnum.locid_id.setFields("fields");
        ReaderEnum.locationname_name.setFields("fields");
        ReaderEnum.item_code.setFields("fields");
        ReaderEnum.item_name.setFields("fields");
        ReaderEnum.item_status.setFields("fields");
        ReaderEnum.department_name.setFields("fields");
        ReaderEnum.department_id.setFields("fields");
        ReaderEnum.agg_doctorSWorkload.setFields("fields");
        ReaderEnum.is_outside.setFields("fields");
        ReaderEnum.ct.setFields("fields");

        // Verify the results
    }

    @Test
    void testGetDesc() {
        assertThat(ReaderEnum.type.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.reader_locationname.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.reader_reader_area.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.doctor_ncc.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.doctor_name.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.doctor_id.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.doctor_loginid.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.doctor_account_type.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.exam_date.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.examine_date.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.institute_id.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.hospname_name.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.areaname_name.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.areaname_id.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.locid_id.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.locationname_name.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.item_code.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.item_name.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.item_status.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.department_name.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.department_id.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.agg_doctorSWorkload.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.is_outside.getDesc()).isEqualTo("desc");
        assertThat(ReaderEnum.ct.getDesc()).isEqualTo("desc");
    }

    @Test
    void testSetDesc() {
        // Run the test
        ReaderEnum.type.setDesc("desc");
        ReaderEnum.reader_locationname.setDesc("desc");
        ReaderEnum.reader_reader_area.setDesc("desc");
        ReaderEnum.doctor_ncc.setDesc("desc");
        ReaderEnum.doctor_name.setDesc("desc");
        ReaderEnum.doctor_id.setDesc("desc");
        ReaderEnum.doctor_loginid.setDesc("desc");
        ReaderEnum.doctor_account_type.setDesc("desc");
        ReaderEnum.exam_date.setDesc("desc");
        ReaderEnum.examine_date.setDesc("desc");
        ReaderEnum.institute_id.setDesc("desc");
        ReaderEnum.hospname_name.setDesc("desc");
        ReaderEnum.areaname_name.setDesc("desc");
        ReaderEnum.areaname_id.setDesc("desc");
        ReaderEnum.locid_id.setDesc("desc");
        ReaderEnum.locationname_name.setDesc("desc");
        ReaderEnum.item_code.setDesc("desc");
        ReaderEnum.item_name.setDesc("desc");
        ReaderEnum.item_status.setDesc("desc");
        ReaderEnum.department_name.setDesc("desc");
        ReaderEnum.department_id.setDesc("desc");
        ReaderEnum.agg_doctorSWorkload.setDesc("desc");
        ReaderEnum.is_outside.setDesc("desc");
        ReaderEnum.ct.setDesc("desc");

        // Verify the results
    }
}
