package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户岗位关联表
 * @author lwd
 */
@Entity
@Table(name = "sys_user_post",schema = "gsc_sys")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserPost extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**关联用户id**/
    private Long userId;

    /**关联岗位id**/
    private Long postId;

    /**是否主岗位(1=是;0=否)**/
    private Integer isPrimary;
}
