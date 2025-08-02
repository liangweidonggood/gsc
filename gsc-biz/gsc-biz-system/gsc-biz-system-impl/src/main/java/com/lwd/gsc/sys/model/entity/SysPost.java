package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.common.enums.EnableStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;


/**
 * 岗位表
 * @author lwd
 */
@Entity
@Table(name = "sys_post",schema = "gsc_sys")
@Comment("岗位表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPost extends BaseEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = 6552065604770556606L;

    /**所属组织ID**/
    @Column(length = 50,nullable = false)
    @Comment("所属组织ID")
    private Long orgId;

    /**岗位名称**/
    @Column(length = 50,unique = true,nullable = false)
    @Comment("岗位名称")
    private String name;

    /**岗位编码**/
    @Column(length = 50,unique = true,nullable = false)
    @Comment("岗位编码")
    private String code;

    /**排序号**/
    @Comment("排序号")
    private Integer orderNum=1;

    /**状态**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable =  false)
    @Comment("状态(DISABLED=禁用;ENABLED=启用)")
    private EnableStatus isEnable=EnableStatus.ENABLED;

}
