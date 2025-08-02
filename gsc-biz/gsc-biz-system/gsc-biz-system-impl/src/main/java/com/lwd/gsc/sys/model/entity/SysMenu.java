package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.common.enums.EnableStatus;
import com.lwd.gsc.sys.model.enums.MenuType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;


/**
 * 系统菜单表
 * 用于管理系统的菜单结构，支持多级菜单
 * @author lwd
 */
@Entity
@Table(name = "sys_menu", schema = "gsc_sys")
@Comment("系统菜单表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenu  extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 5787760133865425438L;

    /**子系统ID，关联sys_subsystem表**/
    @Column(nullable = false)
    @Comment("子系统ID")
    private Long subsystemId;

    /**菜单名称**/
    @Column(length = 100, nullable = false)
    @Comment("菜单名称")
    private String name;

    /**父菜单ID**/
    @Column(nullable = false)
    @Comment("父菜单ID")
    private Long pid = 0L;

    /**菜单类型**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    @Comment("菜单类型(DIR=目录;MENU=菜单;BUTTON=按钮;LINK=外链;IFRAME=iframe)")
    private MenuType menuType = MenuType.DIR;

    /**显示顺序**/
    @Column(nullable = false)
    @Comment("显示顺序")
    private Integer orderNum = 1;

    /**路由地址**/
    @Column(length = 255,unique = true,nullable = false)
    @Comment("路由地址")
    private String path;

    /**菜单图标**/
    @Column(length = 100)
    @Comment("菜单图标")
    private String icon;

    /**备注**/
    @Column(length = 500)
    @Comment("备注")
    private String remark;

    /**状态**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable =  false)
    @Comment("状态(DISABLED=禁用;ENABLED=启用)")
    private EnableStatus isEnable=EnableStatus.ENABLED;


}
