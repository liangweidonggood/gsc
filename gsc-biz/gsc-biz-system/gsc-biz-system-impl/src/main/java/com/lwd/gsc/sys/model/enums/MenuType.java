package com.lwd.gsc.sys.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单类型枚举（扩展版）
 * @author lwd
 */
@Getter
@AllArgsConstructor
public enum MenuType {
    /**
     * 目录
     */
    DIR("目录"),

    /**
     * 菜单
     */
    MENU("菜单"),

    /**
     * 按钮
     */
    BUTTON( "按钮"),

    /**
     * 外链
     */
    LINK("外链"),

    /**
     * iframe
     */
    IFRAME("iframe");

    private final String description;
    /**
     * 获取字典
     * @return 字典
     */
    public static List<Map<String, String>> getDict() {
        List<Map<String, String>> dict = new ArrayList<>();
        for (MenuType enu : values()) {
            Map<String, String> item = new HashMap<>();
            item.put("name", enu.name());
            item.put("description", enu.getDescription());
            dict.add(item);
        }
        return dict;
    }

}
