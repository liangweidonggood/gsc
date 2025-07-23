package com.lwd.gsc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lwd
 */

@Getter
@AllArgsConstructor
public enum EnableStatus {
    DISABLED(0, "未启用"),
    ENABLED(1, "已启用");

    private final int code;
    private final String desc;

    /**
     * 根据 code 获取对应的枚举
     */
    public static EnableStatus fromCode(int code) {
        for (EnableStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    /**
     * 判断是否为启用状态
     */
    public boolean isEnabled() {
        return this == ENABLED;
    }
}
