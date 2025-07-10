package com.lwd.gsc.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class ConfigProperties {
    private String featureToggle;
    private int retryLimit;

    // Getter and Setter
}
