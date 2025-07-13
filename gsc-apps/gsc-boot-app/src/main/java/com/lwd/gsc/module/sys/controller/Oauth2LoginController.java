package com.lwd.gsc.module.sys.controller;

import com.lwd.gsc.common.result.ResResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@RequestMapping("/oauth2")
@RestController
public class Oauth2LoginController {
    @GetMapping("/login")
    public ResResult<Map<String, Object>> login(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                           @AuthenticationPrincipal OidcUser  user) {

        Map<String, Object> userInfo = null;
        if (user != null) {
            userInfo = Map.of(
                    "name", user.getFullName(),
                    "email", user.getEmail(),
                    "attributes", user.getAttributes()
            );
        }

        Map<String, Object> clientInfo = null;
        if (authorizedClient != null) {
            clientInfo = Map.of(
                    "clientName", authorizedClient.getClientRegistration().getClientName(),
                    "scopes", authorizedClient.getAccessToken().getScopes()
            );
        }

        Map<String, Object> result = new HashMap<>();
        result.put("user", userInfo);
        result.put("client", clientInfo);

        return ResResult.success(result);
    }
}
