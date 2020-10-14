package com.bugly.common.annotation;

import java.lang.annotation.*;

/**
 * @author no_f
 * @since 2020-10-14
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitAspect {

}
