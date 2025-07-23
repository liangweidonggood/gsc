package com.lwd.gsc.auth.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lwd
 */
@Entity
@Table(name = "auth_user",schema = "gsc_auth")
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends BaseEntity {

    @Column(nullable = false, unique = true,length = 100)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "perm_version")
    private Integer permVersion;
}
