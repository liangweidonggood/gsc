package com.lwd.gsc.utils;

import com.lwd.gsc.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author Administrator
 */
@Getter
@Slf4j
@Component
public class JwtUtil {
    private final JwtConfig jwtConfig;
    private final SecretKey signingKey;

    @Autowired
    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.signingKey = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public boolean isTokenValid(Jws<Claims> parsedToken) {
        Claims claims = parsedToken.getPayload();
        Date now = new Date();

        // 1. 校验令牌是否已过期（过期时间 <= 当前时间）
        if (claims.getExpiration() != null && claims.getExpiration().before(now)) {
            log.warn("Token has expired. Expiration time: {}", claims.getExpiration());
            return false;
        }

        // 2. 校验令牌是否未生效（签发时间 > 当前时间，适用于未来生效的令牌）
        if (claims.getIssuedAt() != null && claims.getIssuedAt().after(now)) {
            log.warn("Token is not yet valid. Issued time: {}", claims.getIssuedAt());
            return false;
        }

        // 3. 校验令牌主题（subject）是否为空（根据业务需求可选）
        if (StringUtils.hasLength(claims.getSubject())) {
            log.warn("Token subject is empty");
            return false;
        }
        return true;
    }

    /**
     * 生成JWT令牌
     *
     * @param subject 令牌主题（如用户名）
     * @param claims  自定义声明
     * @return 生成的JWT令牌
     */
    public String generateToken(String subject, java.util.Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getAccessTokenExpiration().toMillis()))
                .signWith(signingKey)
                .compact();
    }

    /**
     * 生成刷新令牌
     *
     * @param subject 令牌主题（如用户名）
     * @return 生成的刷新令牌
     */
    public String generateRefreshToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getRefreshTokenExpiration().toMillis()))
                .signWith(signingKey)
                .compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param token 待解析的JWT令牌
     * @return 解析后的JWT对象
     * @throws JwtException 如果解析失败
     */
    public Jws<Claims> parseToken(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token);
    }

    /**
     * 获取令牌中的主题（subject）
     *
     * @param token JWT令牌
     * @return 令牌中的主题
     */
    public String getSubject(String token) {
        return parseToken(token).getPayload().getSubject();
    }
}
