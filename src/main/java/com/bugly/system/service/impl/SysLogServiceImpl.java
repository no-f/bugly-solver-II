package com.bugly.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugly.common.utils.UUIDUtils;
import com.bugly.common.utils.IpInfoUtils;
import com.bugly.system.dao.SysLogDao;
import com.bugly.system.entity.SysLog;
import com.bugly.system.service.SysLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author no_f
 * @ClassName SysLogServiceImpl
 * @Description TODO
 * @Date 2020/6/9 16:23
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysLogServiceImpl implements SysLogService {

    private final SysLogDao sysLogDao;

    @Override
    public int saveLoginLog(HttpServletRequest request, String message,String name) {
        try {
            // 获取ip地址
            String ipAddr = IpInfoUtils.getIpAddr(request);
            // 获取ip来源 目前不需要
//            String ipSource = IpInfoUtils.getipSource(ipAddr);
            String ipSource = "";
            //获取浏览器信息
            String browser = IpInfoUtils.getBrowser(request);
            // 获取系统名称
            String systemName = IpInfoUtils.getSystemName(request);
            SysLog sysLog = SysLog.builder()
                    .username(name)
                    .browserName(browser)
                    .createDate(new Date())
                    .id(UUIDUtils.getUUID())
                    .ipAddress(ipAddr)
                    .ipSource(ipSource)
                    .message(message)
                    .systemName(systemName)
                    .build();
            return sysLogDao.insert(sysLog);
        }catch (Exception e){
            log.error("获取ip来源出错");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public IPage<SysLog> findSysLogPage(Page page) {
        return sysLogDao.findSysLogPage(page);
    }
}
