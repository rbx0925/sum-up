package com.ikang.idata.search.search.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ImagereadDetailsEnumTest {

    @Test
    void testGetFields() {
        assertThat(ImagereadDetailsEnum.workno_itemcode.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.exam_date.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.areaid_code.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.areaname_name.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.institute_id.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.hospname_name.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.locid_id.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.locationname_name.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.reader_locationname_name.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.work_no.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.department_name.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.item_name.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.paipian_time.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.fadan_success.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.worklist_success.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.paipian_first.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.paipian_last.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.upload_begin.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.upload_success.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.download_begin.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.download_succ_first.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.download_succ_last.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.reader_time.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.diff_trans.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.diff_first.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.diff_last.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.diff_baoqie.getFields()).isEqualTo("fields");
        assertThat(ImagereadDetailsEnum.diff_paipian.getFields()).isEqualTo("fields");
    }

    @Test
    void testSetFields() {
        // Run the test
        ImagereadDetailsEnum.workno_itemcode.setFields("fields");
        ImagereadDetailsEnum.exam_date.setFields("fields");
        ImagereadDetailsEnum.areaid_code.setFields("fields");
        ImagereadDetailsEnum.areaname_name.setFields("fields");
        ImagereadDetailsEnum.institute_id.setFields("fields");
        ImagereadDetailsEnum.hospname_name.setFields("fields");
        ImagereadDetailsEnum.locid_id.setFields("fields");
        ImagereadDetailsEnum.locationname_name.setFields("fields");
        ImagereadDetailsEnum.reader_locationname_name.setFields("fields");
        ImagereadDetailsEnum.work_no.setFields("fields");
        ImagereadDetailsEnum.department_name.setFields("fields");
        ImagereadDetailsEnum.item_name.setFields("fields");
        ImagereadDetailsEnum.paipian_time.setFields("fields");
        ImagereadDetailsEnum.fadan_success.setFields("fields");
        ImagereadDetailsEnum.worklist_success.setFields("fields");
        ImagereadDetailsEnum.paipian_first.setFields("fields");
        ImagereadDetailsEnum.paipian_last.setFields("fields");
        ImagereadDetailsEnum.upload_begin.setFields("fields");
        ImagereadDetailsEnum.upload_success.setFields("fields");
        ImagereadDetailsEnum.download_begin.setFields("fields");
        ImagereadDetailsEnum.download_succ_first.setFields("fields");
        ImagereadDetailsEnum.download_succ_last.setFields("fields");
        ImagereadDetailsEnum.reader_time.setFields("fields");
        ImagereadDetailsEnum.diff_trans.setFields("fields");
        ImagereadDetailsEnum.diff_first.setFields("fields");
        ImagereadDetailsEnum.diff_last.setFields("fields");
        ImagereadDetailsEnum.diff_baoqie.setFields("fields");
        ImagereadDetailsEnum.diff_paipian.setFields("fields");

        // Verify the results
    }

    @Test
    void testGetDesc() {
        assertThat(ImagereadDetailsEnum.workno_itemcode.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.exam_date.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.areaid_code.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.areaname_name.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.institute_id.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.hospname_name.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.locid_id.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.locationname_name.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.reader_locationname_name.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.work_no.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.department_name.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.item_name.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.paipian_time.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.fadan_success.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.worklist_success.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.paipian_first.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.paipian_last.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.upload_begin.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.upload_success.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.download_begin.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.download_succ_first.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.download_succ_last.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.reader_time.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.diff_trans.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.diff_first.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.diff_last.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.diff_baoqie.getDesc()).isEqualTo("desc");
        assertThat(ImagereadDetailsEnum.diff_paipian.getDesc()).isEqualTo("desc");
    }

    @Test
    void testSetDesc() {
        // Run the test
        ImagereadDetailsEnum.workno_itemcode.setDesc("desc");
        ImagereadDetailsEnum.exam_date.setDesc("desc");
        ImagereadDetailsEnum.areaid_code.setDesc("desc");
        ImagereadDetailsEnum.areaname_name.setDesc("desc");
        ImagereadDetailsEnum.institute_id.setDesc("desc");
        ImagereadDetailsEnum.hospname_name.setDesc("desc");
        ImagereadDetailsEnum.locid_id.setDesc("desc");
        ImagereadDetailsEnum.locationname_name.setDesc("desc");
        ImagereadDetailsEnum.reader_locationname_name.setDesc("desc");
        ImagereadDetailsEnum.work_no.setDesc("desc");
        ImagereadDetailsEnum.department_name.setDesc("desc");
        ImagereadDetailsEnum.item_name.setDesc("desc");
        ImagereadDetailsEnum.paipian_time.setDesc("desc");
        ImagereadDetailsEnum.fadan_success.setDesc("desc");
        ImagereadDetailsEnum.worklist_success.setDesc("desc");
        ImagereadDetailsEnum.paipian_first.setDesc("desc");
        ImagereadDetailsEnum.paipian_last.setDesc("desc");
        ImagereadDetailsEnum.upload_begin.setDesc("desc");
        ImagereadDetailsEnum.upload_success.setDesc("desc");
        ImagereadDetailsEnum.download_begin.setDesc("desc");
        ImagereadDetailsEnum.download_succ_first.setDesc("desc");
        ImagereadDetailsEnum.download_succ_last.setDesc("desc");
        ImagereadDetailsEnum.reader_time.setDesc("desc");
        ImagereadDetailsEnum.diff_trans.setDesc("desc");
        ImagereadDetailsEnum.diff_first.setDesc("desc");
        ImagereadDetailsEnum.diff_last.setDesc("desc");
        ImagereadDetailsEnum.diff_baoqie.setDesc("desc");
        ImagereadDetailsEnum.diff_paipian.setDesc("desc");

        // Verify the results
    }
}
