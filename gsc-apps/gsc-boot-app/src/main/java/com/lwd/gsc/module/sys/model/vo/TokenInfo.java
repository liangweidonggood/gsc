package com.lwd.gsc.module.sys.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author Administrator
 */
@Builder
@Data
public class TokenInfo {
    private String accessToken;
    private String refreshToken;
}
