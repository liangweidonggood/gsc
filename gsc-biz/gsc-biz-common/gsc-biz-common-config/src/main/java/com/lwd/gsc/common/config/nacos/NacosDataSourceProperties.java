package com.lwd.gsc.common.config.nacos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class NacosDataSourceProperties {
    private String url;
    private String username;
    private String password;

    // Getter and Setter
}
