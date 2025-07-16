package com.lwd.gsc.config.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lwd.gsc.utils.JwtUtil;
import com.lwd.gsc.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * @author Administrator
 */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final RedisUtils redisUtils;
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return !path.startsWith("/api/v1/") && !"/auth/refresh".equals(path);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (token==null){
            // Token为空，立即返回401错误
            jwtAuthenticationEntryPoint.commence(
                    request,
                    response,
                    new AuthenticationCredentialsNotFoundException("token is missing")
            );
            return;
        }
        try {
            Jws<Claims> parsedToken = jwtUtil.parseToken(token);
            //验证token的有效性
            if (!jwtUtil.isTokenValid(parsedToken)) {
                jwtAuthenticationEntryPoint.commence(
                        request,
                        response,
                        new AuthenticationCredentialsNotFoundException("token is invalid")
                );
                return;
            }
            String username = parsedToken.getPayload().getSubject();
            List<String> userPerms = redisUtils.getAs("user:permissions:" + username, new TypeReference<>() {});
            if (userPerms == null || userPerms.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                jwtAuthenticationEntryPoint.commence(
                        request,
                        response,
                        new AuthenticationCredentialsNotFoundException("用户没有分配权限")
                );
                return;
            }
            Collection<? extends GrantedAuthority> authorities = userPerms.stream().map(SimpleGrantedAuthority::new).toList();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, authorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (JwtException e){
            jwtAuthenticationEntryPoint.commence(
                    request,
                    response,
                    new AuthenticationCredentialsNotFoundException("token is invalid")
            );
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
