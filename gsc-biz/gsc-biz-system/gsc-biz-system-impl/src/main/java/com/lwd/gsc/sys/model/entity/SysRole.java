package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.common.enums.EnableStatus;
import com.lwd.gsc.sys.model.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色表
 * @author lwd
 */
@Entity
@Table(name = "sys_role",schema = "gsc_sys")
@Comment("角色表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends BaseEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = -7133004924763135289L;

    /**角色名称**/
    @Column(length = 50,unique = true, nullable = false)
    @Comment("角色名称")
    private String name;

    /**角色编码**/
    @Column(length = 50,unique = true, nullable = false)
    @Comment("角色编码")
    private String code;

    /**角色类型(1=系统内置;2=自定义)**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    @Comment("角色类型(SYSTEM=系统内置;CUSTOM=自定义)")
    private RoleType roleType = RoleType.CUSTOM;

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
