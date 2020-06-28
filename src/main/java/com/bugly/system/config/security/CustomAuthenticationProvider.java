package com.bugly.system.config.security;

import com.bugly.system.service.SysRoleService;
import com.bugly.system.service.SysUserService;
import com.bugly.system.entity.SysRole;
import com.bugly.system.entity.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author no_f
 * @ClassName CustomAuthenticationProvider
 * @Description TODO
 * @Date 2020/62/17 22:12
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String name = token.getName();
        String password = token.getCredentials().toString();
        SysUser sysUser = sysUserService.findByName(name);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //判断密码
        if (!new BCryptPasswordEncoder().matches(password,sysUser.getPassword())){
            throw new UsernameNotFoundException("密码不正确");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 查询权限
        SysRole sysRole = sysRoleService.findByUserId(sysUser.getId());
        authorities.add(new SimpleGrantedAuthority(sysRole.getAuthority()));

        return new UsernamePasswordAuthenticationToken(name,password,authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
