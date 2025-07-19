package com.lwd.gsc.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.test")
public class TestRefreshProperties {
    private String message;
    private int counter;
}
