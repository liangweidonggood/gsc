package com.lwd.gsc.config.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Component
public class PermissionFilter implements Filter {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取请求路径和方法
        String path = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();

        if (!path.startsWith("/api/v1/") && !"/auth/refresh".equals(path)) {
            chain.doFilter(request, response);
            return;
        }

        //todo 查询该路径需要的权限
        String reqPerm="USER:ADD";
        if (reqPerm == null) {
            // 无需特定权限，直接放行
            chain.doFilter(request, response);
            return;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            // 用户未认证（应被JwtAuthFilter拦截，这里做双重保险）
            jwtAuthenticationEntryPoint.commence(
                    httpRequest,
                    httpResponse,
                    new AuthenticationCredentialsNotFoundException("未认证，请先登录")
            );
            return;
        }

        Set<String> userPermissions = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        //判断userPermissions里有没有reqPerm
        if(!userPermissions.contains(reqPerm)){
            //没有权限
            jwtAuthenticationEntryPoint.commence(
                    httpRequest,
                    httpResponse,
                    new AuthenticationCredentialsNotFoundException("权限不足")
            );
            return;
        }
        chain.doFilter(request, response);
    }
}
