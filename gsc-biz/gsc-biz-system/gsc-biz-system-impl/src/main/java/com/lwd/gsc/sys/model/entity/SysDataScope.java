package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
/**
 * 数据权限范围
 * @author lwd
 */
@Entity
@Table(name = "sys_data_scope",schema = "gsc_sys")
@Comment("数据权限范围表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysDataScope extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 8754661803926258882L;

    /**数据权限范围名称**/
    @Column(length = 50,unique = true,nullable = false)
    @Comment("名称")
    private String name;

    /**数据权限范围编码**/
    @Column(length = 50,unique = true,nullable = false)
    @Comment("编码")
    private String code;

    @JdbcTypeCode(SqlTypes.LONG32VARCHAR)
    @Comment("自定义范围规则（如SQL条件表达式）")
    private String customRule;

    /**备注**/
    @Column(length = 500)
    @Comment("备注")
    private String remark;
}
