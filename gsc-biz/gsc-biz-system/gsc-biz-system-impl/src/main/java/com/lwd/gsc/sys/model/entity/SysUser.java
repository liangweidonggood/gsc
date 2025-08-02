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
 * 用户表
 * @author lwd
 */
@Entity
@Table(name = "sys_user",schema = "gsc_sys")
@Comment("用户表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = 5795885714499861446L;

    /**关联认证中心的用户表**/
    @Column(unique = true,nullable = false)
    @Comment("关联认证中心的用户ID")
    private Long authId;

    /**用户名**/
    @Column(length = 50,unique = true,nullable = false)
    @Comment("用户名")
    private String username;

    /**所属组织ID**/
    @Column(nullable = false)
    @Comment("所属组织ID")
    private Long orgId;

    /**真实姓名**/
    @Column(length = 50,nullable = false)
    @Comment("真实姓名")
    private String realName;

    /**手机号**/
    @Column(length = 11,unique = true,nullable = false)
    @Comment("手机号")
    private String phone;

    /**邮箱**/
    @Column(length = 50,unique = true,nullable = false)
    @Comment("邮箱")
    private String email;

    /**头像**/
    @Column(length = 255)
    @Comment("头像")
    private String avatar;

    /**状态**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable =  false)
    @Comment("状态(DISABLED=禁用;ENABLED=启用)")
    private EnableStatus isEnable=EnableStatus.ENABLED;
}
