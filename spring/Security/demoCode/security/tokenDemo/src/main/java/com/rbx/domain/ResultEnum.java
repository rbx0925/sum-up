package com.rbx.domain;

public enum ResultEnum {
    SUCCESS(200, "成功"),
    ERROR(400, "出错"),
    PARAM_ERROR(-1, "参数不正确"),
    UN_AUTHC(401, "未认证（签名错误）"),
    UN_AUTHZ(402, "未授权，无权限访问"),
    UNAUTHEN(4401, "未登录"),
    NOTUPDATE(4404, "未更新数据"),
    NOTADD(4405, "未新增数据"),
    NOTNORMAL(4405, "数据异常"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    MAIL_ERROR(5001, "不是有效的邮箱信息"),
    USER_UN_ENABLE(1001, "账号已失效！"),
    DATA_TOO_LONG(500, "数据库字段长度不够啦！"),
    ACTIVITY_PAGE_EXISTS(2000, "活动页面标题已存在！");

    private Integer code;
    private String message;

    private ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}