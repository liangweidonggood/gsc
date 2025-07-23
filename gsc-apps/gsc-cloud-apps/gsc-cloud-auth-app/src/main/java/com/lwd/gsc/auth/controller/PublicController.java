package com.lwd.gsc.auth.controller;


import com.lwd.gsc.common.config.AuthTokenConfig;
import com.lwd.gsc.common.exception.BusinessException;
import com.lwd.gsc.common.result.ResResult;
import com.lwd.gsc.common.result.ResultCode;
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
    public ResResult<String> getKey(){
        return ResResult.success(authTokenConfig.getPrivateKey());
    }
    @GetMapping("/extest")
    public ResResult<String> extest(){
        throw new BusinessException(ResultCode.FAIL);
    }
}
