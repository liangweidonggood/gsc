package com.lwd.gsc.module.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.common.enums.EnableStatus;
import jakarta.persistence.*;
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

    @Column(nullable = false, unique = true,length = 50)
    private String username;

    @Column(nullable = false,length = 50)
    private String password;

    @Column(name = "perm_version")
    private Integer permVersion;

    @Column(name="is_enable")
    private EnableStatus isEnable;

}
