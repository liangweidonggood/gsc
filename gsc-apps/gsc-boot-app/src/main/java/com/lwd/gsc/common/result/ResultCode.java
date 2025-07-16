package com.lwd.gsc.common.result;

/**
 * @author lwd
 */

public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "操作成功"),



    INVALID_CREDENTIALS(40101, "用户名或密码错误"),
    PARAM_ERROR(400, "参数错误"),
    AUTH_ERROR(401, "请求未授权"),
    AUTH_FORBIDDEN(403, "权限不足"),
    NOT_FOUND(404, "Not Found"),
    FAIL(500, "系统异常"),
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
