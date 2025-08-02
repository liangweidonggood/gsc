package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lwd
 */
@Entity
@Table(name = "sys_role_permisstion",schema = "gsc_sys",uniqueConstraints = {
        @UniqueConstraint(columnNames ={SysRolePermission_.ROLE_ID,SysRolePermission_.PERMISSION_ID})
})
@Comment("角色权限关联表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRolePermission extends BaseEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = -5365301847860203764L;

    /**角色id**/
    @Column(nullable = false)
    @Comment("角色id")
    private Long roleId;

    /**权限id**/
    @Column(nullable = false)
    @Comment("权限id")
    private Long permissionId;
}
