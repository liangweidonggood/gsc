package com.lwd.gsc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author Administrator
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secret;
    @DurationUnit(ChronoUnit.MINUTES)
    private Duration accessTokenExpiration = Duration.ofMinutes(30);
    @DurationUnit(ChronoUnit.DAYS)
    private Duration refreshTokenExpiration= Duration.ofDays(30);
}
