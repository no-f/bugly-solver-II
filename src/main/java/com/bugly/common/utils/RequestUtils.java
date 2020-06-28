package com.bugly.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author no_f
 * @ClassName RequestUtils
 * @Description TODO
 * @Date 2020/62/11 11:33
 */
public class RequestUtils {
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }
}
