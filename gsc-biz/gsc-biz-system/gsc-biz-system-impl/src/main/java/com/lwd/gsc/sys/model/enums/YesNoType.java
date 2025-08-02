package com.lwd.gsc.sys.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 是否枚举
 * @author lwd
 *
 */
@Getter
@AllArgsConstructor
public enum YesNoType {
    YES("是"),
    NO("否");
    private final String description;
    /**
     * 获取字典
     * @return 字典
     */
    public static List<Map<String, String>> getDict() {
        List<Map<String, String>> dict = new ArrayList<>();
        for (YesNoType enu : values()) {
            Map<String, String> item = new HashMap<>();
            item.put("name", enu.name());
            item.put("description", enu.getDescription());
            dict.add(item);
        }
        return dict;
    }
}
