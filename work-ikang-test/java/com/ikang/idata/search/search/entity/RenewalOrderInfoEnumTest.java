package com.ikang.idata.search.search.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RenewalOrderInfoEnumTest {

    @Test
    void testGetFields() {
        assertThat(RenewalOrderInfoEnum.esid.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.sign_id.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.sign_project_id.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.project_zone.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.sign_money.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.sign_date.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.customer_type.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.industry_type.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.brm_opportunity_id.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.brm_opportunity_name.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.newoldtype.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.newyear_signinfo.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.newyear_sign_project_id.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.newyear_id.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.newyear_name.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.newyear_newoldtype.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.newyear_enterprise_full_name.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.newyear_customer_type.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.nextyear_stage.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.nextyear_fail_reason.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.nextyear_estimated_signmoney.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.newyear_signmoney.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.sign_project_id_num.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.project_scale.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.newyear_sign_date.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.renewal_state.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.renewal_lose_state.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.renewal_lose_money.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.project_business_line.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.project_classification.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.project_zone_type.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.ownerid.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.leaderid.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.chief_zone.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.brm_zone.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.brm_department.getFields()).isEqualTo("fields");
        assertThat(RenewalOrderInfoEnum.brm_group.getFields()).isEqualTo("fields");
    }

    @Test
    void testSetFields() {
        // Run the test
        RenewalOrderInfoEnum.esid.setFields("fields");
        RenewalOrderInfoEnum.sign_id.setFields("fields");
        RenewalOrderInfoEnum.sign_project_id.setFields("fields");
        RenewalOrderInfoEnum.project_zone.setFields("fields");
        RenewalOrderInfoEnum.sign_money.setFields("fields");
        RenewalOrderInfoEnum.sign_date.setFields("fields");
        RenewalOrderInfoEnum.customer_type.setFields("fields");
        RenewalOrderInfoEnum.industry_type.setFields("fields");
        RenewalOrderInfoEnum.brm_opportunity_id.setFields("fields");
        RenewalOrderInfoEnum.brm_opportunity_name.setFields("fields");
        RenewalOrderInfoEnum.newoldtype.setFields("fields");
        RenewalOrderInfoEnum.newyear_signinfo.setFields("fields");
        RenewalOrderInfoEnum.newyear_sign_project_id.setFields("fields");
        RenewalOrderInfoEnum.newyear_id.setFields("fields");
        RenewalOrderInfoEnum.newyear_name.setFields("fields");
        RenewalOrderInfoEnum.newyear_newoldtype.setFields("fields");
        RenewalOrderInfoEnum.newyear_enterprise_full_name.setFields("fields");
        RenewalOrderInfoEnum.newyear_customer_type.setFields("fields");
        RenewalOrderInfoEnum.nextyear_stage.setFields("fields");
        RenewalOrderInfoEnum.nextyear_fail_reason.setFields("fields");
        RenewalOrderInfoEnum.nextyear_estimated_signmoney.setFields("fields");
        RenewalOrderInfoEnum.newyear_signmoney.setFields("fields");
        RenewalOrderInfoEnum.sign_project_id_num.setFields("fields");
        RenewalOrderInfoEnum.project_scale.setFields("fields");
        RenewalOrderInfoEnum.newyear_sign_date.setFields("fields");
        RenewalOrderInfoEnum.renewal_state.setFields("fields");
        RenewalOrderInfoEnum.renewal_lose_state.setFields("fields");
        RenewalOrderInfoEnum.renewal_lose_money.setFields("fields");
        RenewalOrderInfoEnum.project_business_line.setFields("fields");
        RenewalOrderInfoEnum.project_classification.setFields("fields");
        RenewalOrderInfoEnum.project_zone_type.setFields("fields");
        RenewalOrderInfoEnum.ownerid.setFields("fields");
        RenewalOrderInfoEnum.leaderid.setFields("fields");
        RenewalOrderInfoEnum.chief_zone.setFields("fields");
        RenewalOrderInfoEnum.brm_zone.setFields("fields");
        RenewalOrderInfoEnum.brm_department.setFields("fields");
        RenewalOrderInfoEnum.brm_group.setFields("fields");

        // Verify the results
    }

    @Test
    void testGetDesc() {
        assertThat(RenewalOrderInfoEnum.esid.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.sign_id.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.sign_project_id.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.project_zone.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.sign_money.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.sign_date.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.customer_type.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.industry_type.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.brm_opportunity_id.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.brm_opportunity_name.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.newoldtype.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.newyear_signinfo.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.newyear_sign_project_id.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.newyear_id.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.newyear_name.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.newyear_newoldtype.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.newyear_enterprise_full_name.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.newyear_customer_type.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.nextyear_stage.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.nextyear_fail_reason.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.nextyear_estimated_signmoney.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.newyear_signmoney.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.sign_project_id_num.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.project_scale.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.newyear_sign_date.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.renewal_state.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.renewal_lose_state.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.renewal_lose_money.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.project_business_line.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.project_classification.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.project_zone_type.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.ownerid.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.leaderid.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.chief_zone.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.brm_zone.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.brm_department.getDesc()).isEqualTo("desc");
        assertThat(RenewalOrderInfoEnum.brm_group.getDesc()).isEqualTo("desc");
    }

    @Test
    void testSetDesc() {
        // Run the test
        RenewalOrderInfoEnum.esid.setDesc("desc");
        RenewalOrderInfoEnum.sign_id.setDesc("desc");
        RenewalOrderInfoEnum.sign_project_id.setDesc("desc");
        RenewalOrderInfoEnum.project_zone.setDesc("desc");
        RenewalOrderInfoEnum.sign_money.setDesc("desc");
        RenewalOrderInfoEnum.sign_date.setDesc("desc");
        RenewalOrderInfoEnum.customer_type.setDesc("desc");
        RenewalOrderInfoEnum.industry_type.setDesc("desc");
        RenewalOrderInfoEnum.brm_opportunity_id.setDesc("desc");
        RenewalOrderInfoEnum.brm_opportunity_name.setDesc("desc");
        RenewalOrderInfoEnum.newoldtype.setDesc("desc");
        RenewalOrderInfoEnum.newyear_signinfo.setDesc("desc");
        RenewalOrderInfoEnum.newyear_sign_project_id.setDesc("desc");
        RenewalOrderInfoEnum.newyear_id.setDesc("desc");
        RenewalOrderInfoEnum.newyear_name.setDesc("desc");
        RenewalOrderInfoEnum.newyear_newoldtype.setDesc("desc");
        RenewalOrderInfoEnum.newyear_enterprise_full_name.setDesc("desc");
        RenewalOrderInfoEnum.newyear_customer_type.setDesc("desc");
        RenewalOrderInfoEnum.nextyear_stage.setDesc("desc");
        RenewalOrderInfoEnum.nextyear_fail_reason.setDesc("desc");
        RenewalOrderInfoEnum.nextyear_estimated_signmoney.setDesc("desc");
        RenewalOrderInfoEnum.newyear_signmoney.setDesc("desc");
        RenewalOrderInfoEnum.sign_project_id_num.setDesc("desc");
        RenewalOrderInfoEnum.project_scale.setDesc("desc");
        RenewalOrderInfoEnum.newyear_sign_date.setDesc("desc");
        RenewalOrderInfoEnum.renewal_state.setDesc("desc");
        RenewalOrderInfoEnum.renewal_lose_state.setDesc("desc");
        RenewalOrderInfoEnum.renewal_lose_money.setDesc("desc");
        RenewalOrderInfoEnum.project_business_line.setDesc("desc");
        RenewalOrderInfoEnum.project_classification.setDesc("desc");
        RenewalOrderInfoEnum.project_zone_type.setDesc("desc");
        RenewalOrderInfoEnum.ownerid.setDesc("desc");
        RenewalOrderInfoEnum.leaderid.setDesc("desc");
        RenewalOrderInfoEnum.chief_zone.setDesc("desc");
        RenewalOrderInfoEnum.brm_zone.setDesc("desc");
        RenewalOrderInfoEnum.brm_department.setDesc("desc");
        RenewalOrderInfoEnum.brm_group.setDesc("desc");

        // Verify the results
    }
}
