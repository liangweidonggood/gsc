package com.lwd.gsc.module.system.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lwd
 */
@Slf4j
@Component
public class TestService {
    @PostConstruct
    public void test(){
        log.info("system-service-test");
    }
}
