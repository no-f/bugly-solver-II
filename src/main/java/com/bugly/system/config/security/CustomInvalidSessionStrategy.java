package com.bugly.system.config.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author no_f
 * @ClassName CustomInvalidSessionStrategy
 * @Description TODO
 * @Date 2020/62/18 20:37
 */
@Component
public class CustomInvalidSessionStrategy implements InvalidSessionStrategy {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/invalid_session").forward(request,response);
    }
}
