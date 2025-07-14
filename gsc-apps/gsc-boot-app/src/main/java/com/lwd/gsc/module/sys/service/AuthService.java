package com.lwd.gsc.module.sys.service;

import com.lwd.gsc.module.sys.model.vo.TokenInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    TokenInfo login(String username, String password);
}
