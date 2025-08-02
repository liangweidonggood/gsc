package com.lwd.gsc.common.base;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * @author lwd
 */
@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "snowflake")
    @GenericGenerator(name = "snowflake", type = JpaSnowflakeIdGenerator.class)
    @Column(name = "id")
    @Comment("主键,默认使用雪花id")
    private Long id;

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    @Comment("创建时间,yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    @CreatedBy
    @Column(name = "create_user_id", updatable = false, length = 50)
    @Comment("创建人id")
    private Long createUserId;

    @LastModifiedDate
    @Column(name = "update_time")
    @Comment("更新时间,yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;

    @LastModifiedBy
    @Column(name = "update_user_id", length = 50)
    @Comment("更新人id")
    private Long updateUserId;
}
