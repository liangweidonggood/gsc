package com.lwd.gsc.common.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author lwd
 */
public interface BaseService<T, ID extends Serializable> {
    /* ========== 基础操作 ========== */

    /**
     * 保存或更新实体
     * @param entity 实体
     * @return 操作成功后的实体
     */
    T saveOrUpdate(T entity);

    /**
     * 批量保存或更新实体
     * @param entities 实体集合
     * @return 操作成功后的实体集合
     */
    List<T> saveOrUpdateAll(Iterable<T> entities);

    /**
     * 删除指定ID的实体
     * @param id 实体ID
     */
    void deleteById(ID id);

    /**
     * 批量删除指定ID集合的实体
     * @param ids 实体ID集合
     */
    void deleteByIds(Iterable<ID> ids);


    /* ========== 查询相关 ========== */
    /**
     * 根据动态查询条件查询单个对象
     * @param spec 查询条件
     * @return 匹配的单个对象，如果没有匹配项则返回空 Optional
     */
    Optional<T> findOne(Specification<T> spec);
    /**
     * 根据ID查找实体
     * @param id 实体ID
     * @return 实体对象
     */
    Optional<T> findById(ID id);

    /**
     * 查找所有实体
     * @return 实体集合
     */
    List<T> findAll();

    /**
     * 根据ID列表查找实体
     * @param ids ID列表
     * @return 实体集合
     */
    List<T> findAllById(Iterable<ID> ids);

    /**
     * 判断某个ID是否存在
     * @param id 实体ID
     * @return 是否存在
     */
    boolean existsById(ID id);

    /* ========== 分页 & 动态查询 ========== */

    /**
     * 使用 Specification 进行分页查询
     * @param spec     动态查询条件
     * @param pageable 分页参数
     * @return         分页结果
     */
    Page<T> search(Specification<T> spec, Pageable pageable);

    /**
     * 使用 Specification 查询全部匹配结果
     * @param spec 动态查询条件
     * @return 匹配结果列表
     */
    List<T> searchAll(Specification<T> spec);

    /**
     * 统计满足条件的记录数
     * @param spec 动态查询条件
     * @return 记录数
     */
    long count(Specification<T> spec);

    /* ========== 高级扩展方法（可选） ========== */
}
