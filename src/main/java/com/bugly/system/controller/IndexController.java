package com.bugly.system.controller;

import com.bugly.common.utils.TimeUtils;
import com.bugly.system.dao.ExceptionReportDao;
import com.bugly.system.entity.ExceptionReport;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Autowired
    private ExceptionReportDao exceptionReportDao;

    @RequestMapping("/")
    public String index(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        return "index";
    }

    @RequestMapping("/console")
    public String home(Model model){
        List<ExceptionReport> exceptionReports = exceptionReportDao.findAll();
        int exceptionTotal = 0;
        int exceptionTypeTotal = 0;
        int unsolvedException = 0;
        int exceptionMonth = -1;

        for (ExceptionReport exceptionReport : exceptionReports) {
            exceptionTotal += exceptionReport.getExceptionTotal();
            exceptionTypeTotal += exceptionReport.getExceptionTypeNum();
            unsolvedException += exceptionReport.getUnsolvedExceptionNum();
            if (TimeUtils.compare(exceptionReport.getDay())) {
                exceptionMonth = exceptionReport.getExceptionTotal();
            }
        }

        model.addAttribute("exception_total", exceptionTotal);
        model.addAttribute("exception_type_total", exceptionTypeTotal);
        model.addAttribute("unsolved_exception", unsolvedException);
        model.addAttribute("exception_month", exceptionMonth == -1 ? 0 : exceptionMonth);

        model.addAttribute("list_exception", JSONArray.fromObject(exceptionReports).toString());
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
