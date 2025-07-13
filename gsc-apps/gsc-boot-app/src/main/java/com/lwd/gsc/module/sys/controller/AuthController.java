package com.lwd.gsc.module.sys.controller;

import com.lwd.gsc.common.result.ResResult;
import com.lwd.gsc.module.sys.model.req.AuthRefreshReqDTO;
import com.lwd.gsc.module.sys.model.req.AuthReqDTO;
import com.lwd.gsc.module.sys.model.vo.TokenInfo;
import com.lwd.gsc.module.sys.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResResult<TokenInfo> login(@Valid @RequestBody AuthReqDTO authReqDTO) {
        return ResResult.success(authService.login(authReqDTO.getUsername(), authReqDTO.getPassword()));
    }
    @PostMapping("/refresh")
    public ResResult<String> refresh(@Valid @RequestBody AuthRefreshReqDTO req) {
        log.info("refreshToken: {}", req.getRefreshToken());
        return ResResult.success("abc");
    }
}
