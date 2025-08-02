package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.common.enums.EnableStatus;
import com.lwd.gsc.sys.model.enums.YesNoType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据字典分类表
 * 用于定义字典分类，管理字典结构，支持树形结构
 * @author lwd
 */
@Entity
@Table(name = "sys_dict", schema = "gsc_sys")
@Comment("数据字典分类表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDict extends BaseEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = 6277493008311416167L;
    /**父级ID，用于构建字典分类的树形结构**/
    @Column( nullable = false)
    @Comment("父级ID")
    private Long pid = 0L;

    /**字典分类编码，同一层级下唯一**/
    @Column(length = 100, nullable = false, unique = true)
    @Comment("字典分类编码")
    private String code;

    /**字典分类名称**/
    @Column(length = 100, nullable = false)
    @Comment("字典分类名称")
    private String name;

    /**是否叶子节点，只有叶子节点才能关联字典项**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable =  false)
    @Comment("是否叶子节点,YES=是;NO=否")
    private YesNoType isLeaf=YesNoType.NO;

    /**状态**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable =  false)
    @Comment("状态(DISABLED=禁用;ENABLED=启用)")
    private EnableStatus isEnable=EnableStatus.ENABLED;

    /**排序号**/
    @Column(nullable = false)
    @Comment("排序号")
    private Integer orderNum = 1;

    /**备注**/
    @Column(length = 500)
    @Comment("备注")
    private String remark;
}
