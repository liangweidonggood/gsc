package com.lwd.gsc.common.result;

import lombok.Data;

/**
 * @author lwd
 */
@Data
public class ResResult<T> {
    private Integer code;
    private String msg;
    private T data;

    private ResResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T>ResResult<T> success() {
        return new ResResult<>(ResultCode.SUCCESS.code(), "success", null);
    }

    public static <T>ResResult<T> success(T data) {
        return new ResResult<>(ResultCode.SUCCESS.code(), "success", data);
    }

    public static <T>ResResult<T> fail() {
        return new ResResult<>(ResultCode.FAIL.code(), "fail", null);
    }

    public static <T>ResResult<T> fail(String message) {
        return new ResResult<>(ResultCode.FAIL.code(), message, null);
    }

    public static <T>ResResult<T> fail(Integer code, String message) {
        return new ResResult<>(code, message, null);
    }

    public static <T>ResResult<T> fail(ResultCode resultCode) {
        return new ResResult<>(resultCode.code(), resultCode.message(), null);
    }

    public static <T>ResResult<T> fail(ResultCode resultCode, T data) {
        return new ResResult<>(resultCode.code(), resultCode.message(), data);
    }
    public ResResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ResResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }
}
