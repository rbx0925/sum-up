package com.ikang.idata.common.utils;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "响应结果",description = "响应结果")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 6288374846131788743L;

    @ApiModelProperty(value = "响应码,200成功，400业务错误，401需要登录，402无权限访问，-1参数错误，500系统异常，404请求路径不存在",example = "200")
    private Integer code;

    @ApiModelProperty(value = "提示消息",example = "操作成功")
    private String msg;

    @ApiModelProperty(value = "响应成功时返回的数据结构")
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
