package com.lwd.gsc.module.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.common.converter.EnableStatusConverter;
import com.lwd.gsc.common.enums.EnableStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lwd
 */
@Entity
@Table(name = "sys_user",schema = "gsc_sys")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

    @Column(nullable = false, unique = true,length = 100)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "perm_version")
    private Integer permVersion;

    @Convert(converter = EnableStatusConverter.class)
    @Column(name="is_enable")
    private EnableStatus isEnable;

    public boolean isEnabled() {
        return isEnable != null && isEnable.isEnabled();
    }

}
