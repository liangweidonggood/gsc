package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.sys.model.enums.YesNoType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户岗位关联表
 * @author lwd
 */
@Entity
@Table(name = "sys_user_post",schema = "gsc_sys",uniqueConstraints = {
        @UniqueConstraint(columnNames ={SysUserPost_.USER_ID,SysUserPost_.POST_ID})
})
@Comment("用户岗位关联表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserPost extends BaseEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = 8162629864281203462L;

    /**关联用户id**/
    @Column(nullable = false)
    @Comment("关联用户id")
    private Long userId;

    /**关联岗位id**/
    @Column(nullable = false)
    @Comment("关联岗位id")
    private Long postId;

    /**是否主岗位(1=是;0=否)**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable =  false)
    @Comment("是否主岗位(YES=是;NO=否)")
    private YesNoType isPrimary=YesNoType.NO;
}
