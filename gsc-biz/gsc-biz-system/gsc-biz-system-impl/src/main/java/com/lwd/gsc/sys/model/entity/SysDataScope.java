package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
/**
 * 数据权限范围
 * @author lwd
 */
@Entity
@Table(name = "sys_data_scope",schema = "gsc_sys")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDataScope extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**数据权限范围名称**/
    private String name;

    /**数据权限范围编码**/
    private String code;

    /**备注**/
    private String remark;
}