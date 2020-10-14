package com.bugly.common.aop;


import net.sf.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.com.google.common.util.concurrent.RateLimiter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author no_f
 * @since 2020-10-14
 */
@Component
@Scope
@Aspect
public class RateLimitAop {

    @Autowired
    private HttpServletResponse response;

    private RateLimiter rateLimiter = RateLimiter.create(50.0);

    @Pointcut("@annotation(com.bugly.common.annotation.RateLimitAspect)")
    public void serviceLimit() {

    }

    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Boolean flag = rateLimiter.tryAcquire();
        Object obj = null;
        try {
            if (flag) {
                obj = joinPoint.proceed();
            }else{
                String result = JSONObject.fromObject("{\n" +
                        "    \"code\": 5000,\n" +
                        "    \"message\": \"Concurrency limit exceeded\"" +
                        "}").toString();
                output(response, result);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("flag=" + flag + ",obj=" + obj);
        return obj;
    }

    public void output(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
}
