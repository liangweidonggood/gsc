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
 * 子系统表
 * 用于管理大型系统的各个子系统模块
 * @author lwd
 */
@Entity
@Table(name = "sys_subsystem", schema = "gsc_sys")
@Comment("子系统表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysSubSystem extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -7877917420756602064L;

    /**子系统名称**/
    @Column(length = 100, nullable = false, unique = true)
    @Comment("子系统名称")
    private String name;

    /**子系统编码**/
    @Column(length = 50, nullable = false, unique = true)
    @Comment("子系统编码")
    private String code;

    /**子系统描述**/
    @Column(length = 500)
    @Comment("子系统描述")
    private String description;

    /**显示顺序**/
    @Column(nullable = false)
    @Comment("显示顺序")
    private Integer orderNum = 1;

    /**状态**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable =  false)
    @Comment("状态(DISABLED=禁用;ENABLED=启用)")
    private EnableStatus isEnable = EnableStatus.ENABLED;

    /**子系统图标**/
    @Column(length = 100)
    @Comment("子系统图标")
    private String icon;
}
