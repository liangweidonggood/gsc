package com.lwd.gsc.sys.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限类型枚举
 * @author lwd
 */
@Getter
@AllArgsConstructor
public enum PermissionType {
    /**
     * 菜单权限 - 控制菜单显示/隐藏
     */
    MENU("菜单权限"),

    /**
     * 按钮权限 - 控制页面内按钮、链接等操作元素的显示/隐藏。
     */
    BUTTON("按钮权限"),

    /**
     * 接口权限 - 控制后端API接口的访问权限。
     */
    API("接口权限"),

    /**
     * 数据权限 - 控制数据访问范围
     */
    DATA("数据权限"),

    /**
     * 字段权限 - 控制字段可见性
     */
    FIELD("字段权限");

    private final String description;

    /**
     * 获取字典
     * @return 字典
     */
    public static List<Map<String, String>> getDict() {
        List<Map<String, String>> dict = new ArrayList<>();
        for (PermissionType enu : values()) {
            Map<String, String> item = new HashMap<>();
            item.put("name", enu.name());
            item.put("description", enu.getDescription());
            dict.add(item);
        }
        return dict;
    }
}
