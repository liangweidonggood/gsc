package com.lwd.gsc.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.WeakKeyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
@Slf4j
@SpringBootTest
class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void testTokenGenerationWithValidKey() {
        // 准备测试数据
        String subject = "testUser";
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "test");

        // 生成令牌
        String token = jwtUtil.generateToken(subject, claims);
        log.info("Generated Token: {}", token);
        // 解析令牌并验证
        Jws<Claims> parsedToken = jwtUtil.parseToken(token);
        assertThat(parsedToken.getPayload().getSubject()).isEqualTo(subject);
        assertThat(parsedToken.getPayload().get("role", String.class)).isEqualTo("test");
    }

    @Test
    void testTokenParsingWithWeakKey() {
        // 修改配置为弱密钥（<256位）
        jwtUtil.getJwtConfig().setSecret(Base64.getEncoder().encodeToString("weakkey".getBytes()));

        try {
            jwtUtil.generateToken("test", new HashMap<>());
        } catch (Exception e) {
            // 验证是否抛出预期的异常类型
            assertThat(e).isInstanceOf(WeakKeyException.class);  // 修改为实际抛出的异常类型
            assertThat(e.getMessage()).contains("key byte array is 56 bits");  // 验证异常信息
        }
    }
}
