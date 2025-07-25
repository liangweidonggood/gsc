package com.lwd.gsc.common.base;

import com.lwd.gsc.common.utils.SnowflakeIdGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * JPA雪花算法ID生成器适配器
 * 功能：适配Hibernate的ID生成接口，将雪花算法集成到JPA实体主键生成
 * @author lwd
 */
public class JpaSnowflakeIdGenerator implements IdentifierGenerator {

    /**
     * 生成实体主键ID
     * @param session Hibernate会话
     * @param object 实体对象
     * @return 雪花算法生成的唯一ID
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // 直接使用雪花算法单例生成ID
        return SnowflakeIdGenerator.getInstance().nextId();
    }
}
