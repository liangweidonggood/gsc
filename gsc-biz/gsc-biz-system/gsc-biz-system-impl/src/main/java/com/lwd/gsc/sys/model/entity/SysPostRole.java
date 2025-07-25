package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
/**
 * 岗位角色关联表
 * @author lwd
 */
@Entity
@Table(name = "sys_post_role",schema = "gsc_sys")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPostRole extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**岗位id**/
    private Long postId;

    /**角色id**/
    private Long roleId;
}
