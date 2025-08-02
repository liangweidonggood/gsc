package com.lwd.gsc.sys.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求方式枚举
 * @author lwd
 *
 */
@Getter
@AllArgsConstructor
public enum RequestMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE");
    private final String description;
    /**
     * 获取字典
     * @return 字典
     */
    public static List<Map<String, String>> getDict() {
        List<Map<String, String>> dict = new ArrayList<>();
        for (RequestMethod enu : values()) {
            Map<String, String> item = new HashMap<>();
            item.put("name", enu.name());
            item.put("description", enu.getDescription());
            dict.add(item);
        }
        return dict;
    }

}
