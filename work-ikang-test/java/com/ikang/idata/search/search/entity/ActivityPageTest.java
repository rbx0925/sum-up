package com.ikang.idata.search.search.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ActivityPageTest {

    private ActivityPage activityPageUnderTest;

    @BeforeEach
    void setUp() {
        activityPageUnderTest = new ActivityPage();
    }

    @Test
    void testEquals() {
        assertThat(activityPageUnderTest.equals("o")).isTrue();
    }

    @Test
    void testCanEqual() {
        assertThat(activityPageUnderTest.canEqual("other")).isTrue();
    }

    @Test
    void testHashCode() {
        assertThat(activityPageUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(activityPageUnderTest.toString()).isEqualTo("result");
    }

    private Long id;
    private String name;
    private String age;
    private String phoneNumber;
    private String mailbox;

    public ActivityPage getActivityPageUnderTest() {
        return activityPageUnderTest;
    }

    public void setActivityPageUnderTest(ActivityPage activityPageUnderTest) {
        this.activityPageUnderTest = activityPageUnderTest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
}
