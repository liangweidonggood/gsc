package com.lwd.gsc.module.sys.service.impl;

import com.lwd.gsc.module.sys.model.dto.CustomUserDetails;
import com.lwd.gsc.module.sys.model.entity.SysUser;
import com.lwd.gsc.module.sys.model.entity.SysUser_;
import com.lwd.gsc.module.sys.model.vo.TokenInfo;
import com.lwd.gsc.module.sys.service.AuthService;
import com.lwd.gsc.module.sys.service.SysUserService;
import com.lwd.gsc.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final SysUserService sysUserService;
    private final RedisTemplate<String,Object> redisTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Specification<SysUser> spec = (root, query, cb) ->
                cb.equal(root.get(SysUser_.USERNAME), username);
        SysUser user = sysUserService.findOne(spec).orElseThrow(() -> new UsernameNotFoundException("用户不存在: " + username));
        //todo 查找用户权限并存入redis
        List<String> userPerms= Arrays.asList("USER_ADD","USER_UPDATE");
        redisTemplate.opsForValue().set("user:permissions:"+username,userPerms,30, TimeUnit.DAYS);
        Collection<? extends GrantedAuthority> authorities = userPerms.stream().map(SimpleGrantedAuthority::new).toList();
        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                authorities,
                true,
                true,
                true,
                user.isEnabled()
        );
    }

    @Override
    public TokenInfo login(String username, String password) {
        String accessToken = jwtUtil.generateToken(username, null);
        return TokenInfo.builder()
                .accessToken(accessToken)
                .build();
    }
}
