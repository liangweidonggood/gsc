package com.lwd.gsc.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author lwd
 */
@Configuration
public class WebClientConfig {

    // 关键：添加 @LoadBalanced 注解，让 WebClient 支持 lb:// 协议
    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
}
