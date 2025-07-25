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
 * 权限表
 * @author lwd
 */
@Entity
@Table(name = "sys_permission",schema = "gsc_sys")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPermission extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**权限名称**/
    private String name;

    /**权限编码**/
    private String code;

    /**权限类型(1=菜单;2=按钮;3=接口)**/
    private Integer type;

    /**父级权限ID**/
    private Long pid;

    /**权限路径**/
    private String url;

    /**权限图标**/
    private String icon;

    /**排序号**/
    private Integer sort;

    /**状态(0=禁用;1=正常)**/
    @Convert(converter = EnableStatusConverter.class)
    @Column(name="is_enable")
    private EnableStatus isEnable;
}
