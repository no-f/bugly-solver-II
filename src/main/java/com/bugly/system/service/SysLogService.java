package com.bugly.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugly.system.entity.SysLog;

import javax.servlet.http.HttpServletRequest;

/**
 * @author no_f
 * @ClassName SysLogService
 * @Description TODO
 * @Date 2020/6/9 16:23
 */
public interface SysLogService {

    int saveLoginLog(HttpServletRequest request,String message,String name);


    IPage<SysLog> findSysLogPage(Page page);

}
