package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.sys.model.enums.LoginStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录日志
 * @author lwd
 */
@Entity
@Table(name = "sys_login_log", schema = "gsc_sys")
@Comment("登录日志表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysLoginLog extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5996788520002451068L;

    /**用户ID**/
    @Column(nullable = false)
    @Comment("用户ID")
    private Long userId;

    /**登录IP地址**/
    @Column(length = 128, nullable = false)
    @Comment("登录IP地址")
    private String ipaddr;

    /**浏览器类型**/
    @Column(length = 50)
    @Comment("浏览器类型")
    private String browser;

    /**操作系统**/
    @Column(length = 50)
    @Comment("操作系统")
    private String os;

    /**登录状态**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable =  false)
    @Comment("登录状态（SUCCESS=成功;FAIL=失败）")
    private LoginStatus loginStatus;

}
