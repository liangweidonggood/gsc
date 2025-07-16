package com.lwd.gsc.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lwd
 */
@RequiredArgsConstructor
@Controller
public class LoginController {
    private final AuthenticationManager authenticationManager;
/*    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false) String redirectUrl,
            HttpServletRequest request) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("username", username);
            response.put("token", "your-generated-jwt-token-here"); // 替换为真实 Token 生成逻辑

            if (redirectUrl != null && !redirectUrl.isEmpty()) {
                response.put("redirect_url", redirectUrl + "?token=your-generated-jwt-token-here");
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "用户名或密码错误");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }*/

    @GetMapping("/login")
    public String login() {
        return "login"; // 👈 返回 Thymeleaf 模板名（src/main/resources/templates/login.html）
    }
}
