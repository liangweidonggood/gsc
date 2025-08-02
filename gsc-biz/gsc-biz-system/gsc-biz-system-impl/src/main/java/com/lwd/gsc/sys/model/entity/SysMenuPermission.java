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
 * 菜单权限关联表
 * 用于关联菜单和具体权限
 * @author lwd
 */
@Entity
@Table(name = "sys_menu_permission", schema = "gsc_sys", uniqueConstraints = {
        @UniqueConstraint(columnNames = {SysMenuPermission_.MENU_ID, SysMenuPermission_.PERMISSION_ID})
})
@Comment("菜单权限关联表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenuPermission extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -903941765231657543L;

    /**菜单ID**/
    @Column(nullable = false)
    @Comment("菜单ID")
    private Long menuId;

    /**权限ID**/
    @Column(nullable = false)
    @Comment("权限ID")
    private Long permissionId;
}
