package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lwd
 */
@Entity
@Table(name = "sys_role_permisstion",schema = "gsc_sys")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRolePermisstion extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**角色id**/
    private Long roleId;

    /**权限id**/
    private Long permissionId;
}
