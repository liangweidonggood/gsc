package com.lwd.gsc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author lwd
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> {
            // 明确需要拦截的路径
            requests.requestMatchers(
                    "/api/secured",
                    "/admin/**"
            ).authenticated();
            // 其他所有路径都不拦截
            requests.anyRequest().permitAll();
        });
        return http.build();
    }
}
