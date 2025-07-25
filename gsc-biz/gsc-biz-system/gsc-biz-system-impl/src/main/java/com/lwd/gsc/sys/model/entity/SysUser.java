package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.common.converter.EnableStatusConverter;
import com.lwd.gsc.common.enums.EnableStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
/**
 * 用户表
 * @author lwd
 */
@Entity
@Table(name = "sys_user",schema = "gsc_sys")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**关联认证中心的用户表**/
    private Long authId;

    /**用户名**/
    private String username;

    /**所属组织ID**/
    private Long orgId;

    /**真实姓名**/
    private String realName;

    /**手机号**/
    private String phone;

    /**邮箱**/
    private String email;

    /**头像**/
    private String avatar;

    /**状态(0=禁用;1=正常)**/
    @Convert(converter = EnableStatusConverter.class)
    @Column(name="is_enable")
    private EnableStatus isEnable;
}
