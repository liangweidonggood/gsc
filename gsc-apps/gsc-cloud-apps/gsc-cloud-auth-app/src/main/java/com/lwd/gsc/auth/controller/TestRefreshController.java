package com.lwd.gsc.auth.controller;


import com.lwd.gsc.auth.config.TestRefreshProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwd
 */
@RequiredArgsConstructor
@RestController
public class TestRefreshController {
    private final TestRefreshProperties testRefreshProperties;
    @GetMapping("/test-config")
    public String getTestConfig() {
        return "Message: " + testRefreshProperties.getMessage() +
                ", Counter: " + testRefreshProperties.getCounter();
    }
}
