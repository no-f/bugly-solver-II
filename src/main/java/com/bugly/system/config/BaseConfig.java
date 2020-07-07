package com.bugly.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author bbb
 * @since 2020-06-01
 */
@Component
public class BaseConfig {

    public static String httpUrl;

    @Value("${bugly.httpUrl}")
    public static void setHttpUrl(String url) {
        BaseConfig.httpUrl = url;
    }
}
