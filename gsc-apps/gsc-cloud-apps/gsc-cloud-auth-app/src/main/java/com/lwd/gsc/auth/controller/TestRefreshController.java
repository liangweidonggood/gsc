package com.lwd.gsc.auth.controller;


import com.lwd.gsc.common.config.TestRefreshProperties;
import com.lwd.gsc.common.result.ResResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwd
 */
@RequestMapping("/public")
@RequiredArgsConstructor
@RestController
public class TestRefreshController {
    private final TestRefreshProperties testRefreshProperties;
    @GetMapping("/test-config")
    public ResResult<String> getTestConfig() {
        return ResResult.success("Message: " + testRefreshProperties.getMessage() +
                ", Counter: " + testRefreshProperties.getCounter());
    }
}
