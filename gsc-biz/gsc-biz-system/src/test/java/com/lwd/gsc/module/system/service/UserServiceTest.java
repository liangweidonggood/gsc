package com.lwd.gsc.module.system.service;

import com.lwd.gsc.module.system.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        classes = UserServiceImpl.class
)
class UserServiceTest {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Test
    void testList() {
        logger.info("123");
        userService.list();
    }
}
