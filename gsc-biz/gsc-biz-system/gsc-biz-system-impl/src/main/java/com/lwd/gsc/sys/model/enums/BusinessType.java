package com.lwd.gsc.sys.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务类型,其它,新增,修改,删除,授权,导出,导入,强退,生成代码,清空数据
 * @author lwd
 */
@Getter
@AllArgsConstructor
public enum BusinessType {
    OTHER("其它"),
    ADD("新增"),
    UPDATE("修改"),
    DELETE("删除"),
    GRANT("授权"),
    EXPORT("导出"),
    IMPORT("导入"),
    FORCE("强退"),
    GENCODE("生成代码"),
    CLEAN("清空数据");
    private final String description;
    /**
     * 获取字典
     * @return 字典
     */
    public static List<Map<String, String>> getDict() {
        List<Map<String, String>> dict = new ArrayList<>();
        for (BusinessType enu : values()) {
            Map<String, String> item = new HashMap<>();
            item.put("name", enu.name());
            item.put("description", enu.getDescription());
            dict.add(item);
        }
        return dict;
    }
}
