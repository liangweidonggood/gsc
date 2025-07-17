package com.lwd.gsc.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author lwd
 */
@Data
@Component
@ConfigurationProperties(prefix = "auth-token")
public class AuthTokenConfig {
    private String publicKey;
    private String privateKey;
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration accessTokenExpiration = Duration.ofMinutes(30);
    @DurationUnit(ChronoUnit.DAYS)
    private Duration refreshTokenExpiration= Duration.ofDays(30);
}
