package com.lwd.gsc.module.sys.service.impl;

import com.lwd.gsc.module.sys.model.vo.TokenInfo;
import com.lwd.gsc.module.sys.service.AuthService;
import com.lwd.gsc.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    @Override
    public TokenInfo login(String username, String password) {
        // 验证用户名和密码是否正确
        // 验证通过后生成token
        String subject = "testUser";
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "test");

        // 生成令牌
        String accessToken = jwtUtil.generateToken(subject, claims);
        return TokenInfo.builder()
                .accessToken(accessToken)
                .build();
    }
}
