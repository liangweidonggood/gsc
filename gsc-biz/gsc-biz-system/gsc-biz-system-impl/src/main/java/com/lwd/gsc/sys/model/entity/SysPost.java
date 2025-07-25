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
 * 岗位表
 * @author lwd
 */
@Entity
@Table(name = "sys_post",schema = "gsc_sys")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPost extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**所属组织ID**/
    private Long orgId;

    /**岗位名称**/
    private String name;

    /**岗位编码**/
    private String code;

    /**排序号**/
    private Integer sort;

    /**状态(0=禁用;1=正常)**/
    @Convert(converter = EnableStatusConverter.class)
    @Column(name="is_enable")
    private EnableStatus isEnable;

}
