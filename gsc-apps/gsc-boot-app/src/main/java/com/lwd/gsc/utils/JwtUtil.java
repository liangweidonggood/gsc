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
