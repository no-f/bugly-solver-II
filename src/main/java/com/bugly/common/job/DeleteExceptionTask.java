package com.bugly.common.job;

import com.bugly.common.utils.TimeUtils;
import com.bugly.system.dao.ExceptionReportDao;
import com.bugly.system.dao.ExceptionTypeDao;
import com.bugly.system.dao.ServiceLogDao;
import com.bugly.system.entity.ExceptionReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author no_f
 *
 * @since 2020-06-30
 */
@Component
public class DeleteExceptionTask {

    @Autowired
    private ServiceLogDao serviceLogDao;

    @Autowired
    private ExceptionTypeDao exceptionTypeDao;

    @Autowired
    private ExceptionReportDao exceptionReportDao;

    @Scheduled(cron="0 0/3 * * * ?")
    public void statusCheck() {
        //获取当月的 异常总数
        Date startTime = TimeUtils.getFirstDay();
        Date endTime = TimeUtils.getLastDay();
        int exceptionTotal = serviceLogDao.findAllNumByTime(startTime, endTime);
        int exceptionTypeNum = exceptionTypeDao.findAllNumByTime(startTime, endTime);
        int unsolvedExceptionNum = exceptionTypeDao.findUnSolveNumByTime(startTime, endTime);

        ExceptionReport exceptionReport = new ExceptionReport();
        exceptionReport.setId(getId());
        exceptionReport.setExceptionTotal(exceptionTotal);
        exceptionReport.setExceptionTypeNum(exceptionTypeNum);
        exceptionReport.setUnsolvedExceptionNum(unsolvedExceptionNum);
        exceptionReport.setMtime(new Date());
        exceptionReportDao.updateById(exceptionReport);
    }

    private String getId() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String a = sf.format(TimeUtils.getFirstDay());
        return a.substring(5,7).replace("0","");
    }

}
