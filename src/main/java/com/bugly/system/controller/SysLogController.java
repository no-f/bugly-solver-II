package com.bugly.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author no_f
 * @ClassName SysLogController
 * @Description TODO
 * @Date 2020/6/9 16:24
 */
@Controller
@RequestMapping("/sys_log")
public class SysLogController {

    @GetMapping("/list")
    public String index(){
        return "module/syslog/syslog";
    }
}
