package com.lwd.gsc.auth.controller;

import com.lwd.gsc.auth.config.AuthTokenConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lwd
 */
@RequiredArgsConstructor
@RequestMapping("/public")
@RestController
public class PublicController {

    private final AuthTokenConfig authTokenConfig;

    @GetMapping("/getKey")
    public String getKey(){
        return authTokenConfig.getPublicKey();
    }
}
