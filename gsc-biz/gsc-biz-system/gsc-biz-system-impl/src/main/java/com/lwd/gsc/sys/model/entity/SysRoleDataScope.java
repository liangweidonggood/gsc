package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色数据权限范围
 * @author lwd
 */
@Entity
@Table(name = "sys_role_data_scope",schema = "gsc_sys",uniqueConstraints = {
        @UniqueConstraint(columnNames ={SysRoleDataScope_.ROLE_ID,SysRoleDataScope_.SCOPE_ID})
})
@Comment("角色数据权限范围关联表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleDataScope extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4643343660883945733L;

    /**角色ID**/
    @Column(nullable = false)
    @Comment("角色ID")
    private Long roleId;

    /**数据权限范围ID**/
    @Column(nullable = false)
    @Comment("数据权限范围ID")
    private Long scopeId;

    /**自定义数据权限范围组织ID**/
    @JdbcTypeCode(SqlTypes.LONG32VARCHAR)
    @Comment("自定义数据权限范围组织ID")
    private String customOrgIds;

}
