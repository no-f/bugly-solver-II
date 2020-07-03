package com.bugly.system.controller;

import com.bugly.common.utils.SecurityUtils;
import com.bugly.system.dao.*;
import com.bugly.system.entity.AlarmConfig;
import com.bugly.system.entity.SysUser;
import com.bugly.system.model.ServiceLog;
import com.bugly.system.model.ServiceType;
import com.bugly.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static org.apache.commons.lang3.CharUtils.LF;


/**
 * @author no_f
 * view
 * @create 2020-06-16 17:46
 */
@Controller
@RequestMapping("/bugly/exception")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionController {

    private final SysUserService sysUserService;

    private final SysUserDao sysUserDao;

    private final ServiceLogDao serviceLogDao;

    private final ServiceTypeUserDao serviceTypeUserDao;

    private final ServiceTypeDao serviceTypeDao;

    private final AlarmConfigDao alarmConfigDao;

    @GetMapping("/list")
    public String index(){
        return "module/bugly/bugly";
    }

    @GetMapping("/update")
    public String update(String id, Model model){
        Authentication authentication = SecurityUtils.getCurrentUserAuthentication();
        String username = (String)authentication.getPrincipal();
        SysUser sysUser = sysUserService.findByName(username);
        model.addAttribute("exceptionTypeId", id);
        model.addAttribute("sysUser", sysUser);
        return "module/bugly/updaterBugly";
    }

    @GetMapping("/detail")
    public String detail(String id, Model model){
        model.addAttribute("exceptionTypeId", id);
        return "module/bugly/detail";
    }

    @GetMapping("/detail_show")
    public String detailShow(String id, Model model){
        ServiceLog serviceLog = serviceLogDao.selectById(id);
        StringBuffer stringBuffer = new StringBuffer(" ");
        Optional.ofNullable(serviceLog).ifPresent(s->{
            stringBuffer.append("信息:").append(LF).append(serviceLog.getErrorMessage()).append(LF)
                    .append("异常：").append(LF).append(serviceLog.getErrorException());
        });
        model.addAttribute("serviceException", stringBuffer.toString());
        return "module/bugly/detailShow";
    }

    @GetMapping("/alarmConfig")
    public String listAlarmConfig(){
        return "module/bugly/buglyConfig";
    }

    @GetMapping("/updateAlarmConfig")
    public String updateAlarmConfig(String id, Model model){
        model.addAttribute("id", id);
        ServiceType serviceType = serviceTypeDao.selectById(id);
        AlarmConfig alarmConfig = alarmConfigDao.findByServiceTypeId(id);
        model.addAttribute("serviceName", serviceType.getServiceName());
        model.addAttribute("webhookUrl", alarmConfig == null ? "" : alarmConfig.getWebhookUrl());
        return "module/bugly/updateAlarmConfig";
    }

}
