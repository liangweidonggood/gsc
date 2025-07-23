package com.lwd.gsc.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lwd
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "gsc")
public class GscConfig {
    private List<String> excludeUrls = new ArrayList<>();
}
