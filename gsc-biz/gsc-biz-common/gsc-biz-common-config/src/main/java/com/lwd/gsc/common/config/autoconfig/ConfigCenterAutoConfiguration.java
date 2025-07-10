package com.lwd.gsc.common.config.autoconfig;

import com.lwd.gsc.common.config.ConfigProperties;
import com.lwd.gsc.common.config.nacos.NacosDataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCenterAutoConfiguration {

    @Bean
    public ConfigProperties configProperties() {
        return new ConfigProperties();
    }

    @Bean
    public NacosDataSourceProperties nacosDataSourceProperties() {
        return new NacosDataSourceProperties();
    }
}
