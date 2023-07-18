package com.ikang.idata.common.utils;


import com.ikang.idata.common.enums.ResultEnum;

/**
 * 返回结果工具类
 *
 * @author yanjf
 */
public class ResultUtil {
    public static<T> Result<T> success(T object) {
        Result<T> result = new Result<>();
        result.setData(object);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMessage());
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(ResultEnum.ERROR.getMessage());
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }
}
