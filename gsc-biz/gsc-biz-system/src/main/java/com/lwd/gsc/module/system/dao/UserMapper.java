package com.lwd.gsc.module.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwd.gsc.module.system.model.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author lwd
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
