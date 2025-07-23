package com.lwd.gsc.common.exception;

import com.lwd.gsc.common.result.ResultCode;
import lombok.Getter;

/**
 * 业务异常类
 * @author lwd
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ResultCode resultCode;
    private final Object data;

    public BusinessException(ResultCode resultCode) {
        this(resultCode, null);
    }

    public BusinessException(ResultCode resultCode, Object data) {
        super(resultCode.message());
        this.resultCode = resultCode;
        this.data = data;
    }

}

