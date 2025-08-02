package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.common.enums.EnableStatus;
import com.lwd.gsc.sys.model.enums.PermissionType;
import com.lwd.gsc.sys.model.enums.RequestMethod;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;
/**
 * 权限表
 * @author lwd
 */
@Entity
@Table(name = "sys_permission",schema = "gsc_sys")
@Comment("权限表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPermission extends BaseEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = -47097757244759954L;

    /**权限名称**/
    @Column(length = 500,unique = true,nullable = false)
    @Comment("名称")
    private String name;

    /**权限编码**/
    @Column(length = 500,unique = true,nullable = false)
    @Comment("编码")
    private String code;

    /**权限类型(1=菜单;2=按钮;3=接口)**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    @Comment("权限类型（MENU=菜单权限;BUTTON=按钮权限;API=接口权限;DATA=数据权限;FIELD=字段权限）")
    private PermissionType permissionType = PermissionType.MENU;

    /**HTTP方法（针对接口权限）**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = true)
    @Comment("HTTP方法(GET,POST,PUT,DELETE,PATCH,HEAD,OPTIONS,TRACE)")
    private RequestMethod requestMethod;

    /**字段名称（针对字段权限）**/
    @Column(length = 100)
    @Comment("字段名称（针对字段权限）")
    private String fieldName;

    /**目标实体/表名（针对字段权限）**/
    @Column(length = 100)
    @Comment("目标实体/表名（针对字段权限）")
    private String targetEntity;

    /**排序号**/
    @Comment("排序号")
    private Integer orderNum=1;

    /**状态**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable =  false)
    @Comment("状态(DISABLED=禁用;ENABLED=启用)")
    private EnableStatus isEnable=EnableStatus.ENABLED;
}
