package com.lwd.gsc.config.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * @author lwd
 */
@RequiredArgsConstructor
@Component
public class PermissionFilter extends OncePerRequestFilter {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return !path.startsWith("/api/v1/");
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        // 获取请求路径和方法
        String path = request.getRequestURI();
        String method = request.getMethod();

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
                    request,
                    response,
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
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            jwtAuthenticationEntryPoint.commence(
                    request,
                    response,
                    new AuthenticationCredentialsNotFoundException("权限不足")
            );
            return;
        }
        chain.doFilter(request, response);
    }
}
