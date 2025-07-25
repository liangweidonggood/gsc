package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色数据权限范围
 * @author lwd
 */
@Entity
@Table(name = "sys_role_data_scope",schema = "gsc_sys")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleDataScope extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**角色ID**/
    private Long roleId;

    /**数据权限范围ID**/
    private Long scopeId;

    /**自定义数据权限范围组织ID**/
    private String customOrgIds;

}
