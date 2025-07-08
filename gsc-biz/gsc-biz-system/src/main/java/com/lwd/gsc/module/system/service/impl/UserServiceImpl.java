package com.lwd.gsc.module.system.service.impl;

import com.lwd.gsc.module.system.model.User;
import com.lwd.gsc.module.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author lwd
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Override
    public List<User> list() {
        log.info("system-service-UserServiceImpl-list");
        return null;
    }

    @Override
    public User getUserById(Long id) {
        //userMapper.selectById(id);
        log.info("system-service-UserServiceImpl-getUserById");
        User user=new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("123");
        return user;
    }
}
