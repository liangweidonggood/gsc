package com.lwd.gsc.common.result;

/**
 * @author lwd
 */

public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "操作成功"),
    FAIL(500, "系统异常"),
    PARAM_ERROR(400, "参数错误"),
    AUTH_ERROR(401, "用户名或密码错误"),
    NOT_FOUND(404, "Not Found"),
    NULL_POINTER(500, "空指针异常");

    private final Integer code;
    private final String message;
    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }

}
