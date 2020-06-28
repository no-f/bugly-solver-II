package com.bugly.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author no_f
 * @ClassName LoginController
 * @Description TODO
 * @Date 2020/62/11 10:50
 */
@Controller
@Slf4j
public class IndexController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping("/")
    public String index(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        return "index";
    }

    @RequestMapping("/console")
    public String home(){
        return "home";
    }

    @RequestMapping("/introduce")
    public String introduce(){
        return "introduce";
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin(){
        return "是管理员";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String user(){
        return "是普通用户";
    }
}
