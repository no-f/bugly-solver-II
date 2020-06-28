package com.bugly.system.config.filter;

import com.bugly.common.base.Constants;
import com.bugly.system.config.exception.ValidateCodeException;
import com.bugly.system.config.security.handler.AuthenticationFailureHandler;
import com.bugly.system.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author no_f
 * @ClassName ValidateCodeFilter
 * @Description TODO
 * @Date 2020/06/17 20:53
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ValidateCodeFilter extends OncePerRequestFilter {

    private final RedisService redisService;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private static final PathMatcher PATHMATCHER = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        图形验证码注释
//        if (Constants.REQUEST_MODE_POST.equals(request.getMethod()) && PATHMATCHER.match(Constants.LOGIN_URL,request.getServletPath())){
//            try {
//                codeValidate(request);
//            } catch (ValidateCodeException e){
//                //验证码不通过，跳到错误处理器处理
//                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
//                return;
//            }
//        }
        doFilter(request,response,filterChain);
    }

    /**
     * 暂时去掉验证码功能 不需要
     * @param request
     */
    private void codeValidate(HttpServletRequest request) {
        //获取传入的验证码
        String code = request.getParameter("code");
        String uuidCode = request.getParameter("uuidCode");
        if (StringUtils.isEmpty(code)){
            throw new ValidateCodeException("验证码的值不能为空");
        }
        String codeVal = redisService.getCodeVal(uuidCode);
        if (StringUtils.isEmpty(codeVal)) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeVal,code)) {
            throw new ValidateCodeException("验证码不匹配");
        }
    }
}

