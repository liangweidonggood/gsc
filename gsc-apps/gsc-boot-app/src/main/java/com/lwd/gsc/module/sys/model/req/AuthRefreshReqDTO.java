package com.lwd.gsc.module.sys.model.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class AuthRefreshReqDTO {
    @NotBlank(message = "刷新令牌不能为空")
    @Size(min = 6, max = 200, message = "刷新令牌长度错误")
    private String refreshToken;
}
