package com.lwd.gsc.sys.model.entity;


import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.common.enums.EnableStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据字典项表
 * 用于存储具体字典项数据，关联到字典分类（必须是叶子节点分类）
 * @author lwd
 */
@Entity
@Table(name = "sys_dict_item", schema = "gsc_sys")
@Comment("数据字典项表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDictItem extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1406180210239801689L;
    /**字典分类ID，关联sys_dict表的ID（必须是叶子节点）**/
    @Column(nullable = false)
    @Comment("字典分类ID")
    private Long dictId;

    /**名称**/
    @Column(length = 50,nullable = false)
    @Comment("名称")
    private String name;

    /**组织编码**/
    @Column(length = 50,nullable = false)
    @Comment("编码")
    private String code;

    /**字典项值，程序中实际使用的值**/
    @Column(length = 100, nullable = false)
    @Comment("字典项值")
    private String itemValue;

    /**字典项排序**/
    @Column(nullable = false)
    @Comment("字典项排序")
    private Integer orderNum = 1;

    /**状态**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable =  false)
    @Comment("状态(DISABLED=禁用;ENABLED=启用)")
    private EnableStatus isEnable=EnableStatus.ENABLED;


    /**备注**/
    @Column(length = 500)
    @Comment("备注")
    private String remark;

}
