package com.bugly.system.config.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author no_f
 * @ClassName ValidateCodeException
 * @Description TODO
 * @Date 2020/06/17 21:25
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String message) {
        super(message);
    }
}
