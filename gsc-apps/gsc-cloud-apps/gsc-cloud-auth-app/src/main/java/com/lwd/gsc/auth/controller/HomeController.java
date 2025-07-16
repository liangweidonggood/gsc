package com.lwd.gsc.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lwd
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String root(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            return "redirect:/home"; // 已登录用户直接跳转首页
        }
        return "redirect:/login"; // 未登录用户跳转登录页
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
