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
 * 角色表
 * @author lwd
 */
@Entity
@Table(name = "sys_role",schema = "gsc_sys")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**角色名称**/
    private String name;

    /**角色编码**/
    private String code;

    /**角色类型(1=系统内置;2=自定义)**/
    private Integer type;

    /**状态(0=禁用;1=正常)**/
    @Convert(converter = EnableStatusConverter.class)
    @Column(name="is_enable")
    private EnableStatus isEnable;

    /**备注**/
    private String remark;
}
