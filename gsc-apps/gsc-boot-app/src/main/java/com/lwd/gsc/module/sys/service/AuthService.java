package com.lwd.gsc.module.sys.service;

import com.lwd.gsc.module.sys.model.vo.TokenInfo;

public interface AuthService {
    TokenInfo login(String username, String password);
}
