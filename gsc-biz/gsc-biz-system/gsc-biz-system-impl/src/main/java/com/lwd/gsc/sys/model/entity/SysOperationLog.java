package com.lwd.gsc.sys.model.entity;

import com.lwd.gsc.common.base.BaseEntity;
import com.lwd.gsc.sys.model.enums.BusinessType;
import com.lwd.gsc.sys.model.enums.OperStatus;
import com.lwd.gsc.sys.model.enums.RequestMethod;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统操作日志表
 * 记录用户在系统中的各种操作日志
 * @author lwd
 */
@Entity
@Table(name = "sys_operation_log", schema = "gsc_sys")
@Comment("系统操作日志表")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysOperationLog  extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 7656638683832747148L;

    /**用户ID**/
    @Column(nullable = false)
    @Comment("用户ID")
    private Long userId;

    /**用户名称**/
    @Column(length = 50, nullable = false)
    @Comment("用户名称")
    private String userName;

    /**部门ID**/
    @Column(nullable = false)
    @Comment("部门ID")
    private Long deptId;

    /**部门名称**/
    @Column(length = 50)
    @Comment("部门名称")
    private String deptName;

    /**模块标题**/
    @Column(length = 50, nullable = false)
    @Comment("模块标题")
    private String title;

    /**业务类型（0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据）**/
    @Enumerated(EnumType.STRING)
    @Column( nullable = false)
    @Comment("业务类型（0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据）")
    private BusinessType businessType=BusinessType.OTHER;

    /**方法名称**/
    @Column( length = 100, nullable = false)
    @Comment("方法名称")
    private String methodName;

    /**请求方式（GET POST PUT DELETE）**/
    @Enumerated(EnumType.STRING)
    @Column( length = 10, nullable = false)
    @Comment("请求方式（GET POST PUT DELETE..）")
    private RequestMethod requestMethod=RequestMethod.GET;

    /**操作内容**/
    @Column( length = 2000)
    @Comment("操作内容")
    private String content;

    /**请求URL**/
    @Column(length = 255, nullable = false)
    @Comment("请求URL")
    private String operUrl;

    /**主机地址**/
    @Column(length = 128, nullable = false)
    @Comment("主机地址")
    private String operIp;

    /**操作地点**/
    @Column( length = 255)
    @Comment("操作地点")
    private String operLocation;

    /**请求参数**/
    @Lob
    @Column(nullable = true)
    @Comment("请求参数")
    private String operParam;

    /**返回参数**/
    @JdbcTypeCode(SqlTypes.LONG32VARCHAR)
    @Column(nullable = true)
    @Comment("返回参数")
    private String jsonResult;

    /**操作状态（0=正常,1=异常）**/
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    @Comment("操作状态（SUCCESS=正常;FAIL=异常）")
    private OperStatus operStatus = OperStatus.SUCCESS;

    /**错误消息**/
    @JdbcTypeCode(SqlTypes.LONG32VARCHAR)
    @Column(nullable = true)
    @Comment("错误消息")
    private String errorMsg;

    /**消耗时间（毫秒）**/
    @Column( nullable = false)
    @Comment("消耗时间（毫秒）")
    private Long costTime = 0L;

    /**浏览器类型**/
    @Column(length = 50)
    @Comment("浏览器类型")
    private String browser;

    /**操作系统**/
    @Column(length = 50)
    @Comment("操作系统")
    private String os;

    /**平台类型**/
    @Column( length = 50)
    @Comment("平台类型")
    private String platform;
}
