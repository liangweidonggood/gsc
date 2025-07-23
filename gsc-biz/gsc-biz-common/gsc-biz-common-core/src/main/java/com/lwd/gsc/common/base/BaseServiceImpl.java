package com.lwd.gsc.common.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 通用 BaseServiceImpl 实现类
 *
 * @author lwd
 * @param <T>  实体类型
 * @param <ID> 主键类型，必须是 Serializable
 */
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    private final BaseRepository<T, ID> repository;

    public BaseServiceImpl(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    /* ========== 基础操作 ========== */

    @Override
    public T saveOrUpdate(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> saveOrUpdateAll(Iterable<T> entities) {
        List<T> savedEntities = new ArrayList<>();
        for (T entity : entities) {
            savedEntities.add(repository.save(entity));
        }
        return savedEntities;
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByIds(Iterable<ID> ids) {
        for (ID id : ids) {
            repository.deleteById(id);
        }
    }

    /* ========== 查询相关 ========== */

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return repository.findOne(spec);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    /* ========== 分页 & 动态查询 ========== */

    @Override
    public Page<T> search(Specification<T> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public List<T> searchAll(Specification<T> spec) {
        return repository.findAll(spec);
    }

    @Override
    public long count(Specification<T> spec) {
        return repository.count(spec);
    }
}
