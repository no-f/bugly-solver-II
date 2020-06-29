package com.bugly.system.controller;

import com.bugly.common.utils.SecurityUtils;
import com.bugly.system.entity.SysUser;
import com.bugly.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author no_f
 * @create 2020-06-16 17:46
 */
@Controller
@RequestMapping("/bugly/exception")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionController {

    private final SysUserService sysUserService;

    @GetMapping("/list")
    public String index(){
        return "module/bugly/bugly";
    }

    @GetMapping("/update")
    public String update(String id, Model model){
        Authentication authentication = SecurityUtils.getCurrentUserAuthentication();
        String username = (String)authentication.getPrincipal();
        SysUser sysUser = sysUserService.findByName(username);
        model.addAttribute("sysUser", sysUser);
        return "module/bugly/updaterBugly";
    }

    @GetMapping("/detail")
    public String detail(String id){
        return "module/bugly/detail";
    }
}
