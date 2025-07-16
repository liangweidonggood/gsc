package com.lwd.gsc.auth.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            return User.withUsername("user")
                    .password("{noop}gsc123456") // 使用明文密码测试
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}
