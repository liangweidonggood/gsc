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
 * 组织表
 * @author lwd
 */
@Entity
@Table(name = "sys_org",schema = "gsc_sys")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysOrg extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**组织名称**/
    private String name;

    /**组织编码**/
    private String code;

    /**父级ID**/
    private Long pid;

    /**层级(1=集团;2=子公司;3=部门)**/
    private Integer level;

    /**排序号**/
    private Integer sort;

    /**状态(0=禁用;1=正常)**/
    @Convert(converter = EnableStatusConverter.class)
    @Column(name="is_enable")
    private EnableStatus isEnable;
}
