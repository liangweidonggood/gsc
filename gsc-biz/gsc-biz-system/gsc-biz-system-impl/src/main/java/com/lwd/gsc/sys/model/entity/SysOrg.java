package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.common.enums.EnableStatus;
import com.lwd.gsc.sys.model.enums.OrgLevel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;

/**
 * 组织表
 * @author lwd
 */
@Entity
@Table(name = "sys_org", schema = "gsc_sys")
@Comment("组织表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysOrg extends BaseEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = -8091212417123136600L;

    /**组织名称**/
    @Column(length = 50,unique = true,nullable = false)
    @Comment("组织名称")
    private String name;

    /**组织编码**/
    @Column(length = 50,unique = true,nullable = false)
    @Comment("组织编码")
    private String code;

    /**父级ID**/
    @Column(nullable = false)
    @Comment("父级ID")
    private Long pid;

    /**层级(1=集团;2=子公司;3=部门)**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    @Comment("层级(GROUP=集团;SUBSIDIARY=子公司;DEPARTMENT=部门)")
    private OrgLevel orgLevel=OrgLevel.DEPARTMENT;

    /**排序号**/
    @Comment("排序号")
    private Integer orderNum=1;

    /**状态**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable =  false)
    @Comment("状态(DISABLED=禁用;ENABLED=启用)")
    private EnableStatus isEnable=EnableStatus.ENABLED;
}
