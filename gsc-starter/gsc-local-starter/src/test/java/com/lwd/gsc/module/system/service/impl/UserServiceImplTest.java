package com.lwd.gsc.module.system.service.impl;

import com.lwd.gsc.StartApplication;
import com.lwd.gsc.module.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest(
        //如果不写这个的话，下面的service就无法自动注入。
        classes = StartApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
class UserServiceImplTest {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Test
    void testList() {
        logger.info("123");
        userService.list();
    }

}
