package com.lwd.gsc.module.system.service;

import com.lwd.gsc.module.system.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lwd
 */
public interface UserService {
    /**
     * 获取用户列表
     * @return 用户列表
     */
    List<User> list();

    /**
     * 根据用户ID获取用户
     * @param id 用户ID
     * @return 用户
     */
    User getUserById(Long id);
}
