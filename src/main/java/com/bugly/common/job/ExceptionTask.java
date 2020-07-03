package com.bugly.common.job;

import com.bugly.common.sms.EmailUtil;
import com.bugly.common.utils.TimeUtils;
import com.bugly.system.dao.ExceptionReportDao;
import com.bugly.system.dao.ExceptionTypeDao;
import com.bugly.system.dao.ServiceLogDao;
import com.bugly.system.entity.ExceptionReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class ExceptionTask {

    @Autowired
    private ServiceLogDao serviceLogDao;

    @Autowired
    private ExceptionTypeDao exceptionTypeDao;

    @Autowired
    private ExceptionReportDao exceptionReportDao;

    @Value("${email.sender}")
    private String emailSender;

    /**
     * 首页统计
     */
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


    /**
     * 每天发送邮件
     */
    @Scheduled(cron="0/30 * * * * ?")
    public void day() {
        //发送邮件聚合数据邮件每天早上
        //异常种类 数据  异常数量 未解决的数量 比昨日新增的异常类型


        EmailUtil.sendEmail("just a test", emailSender, "jiangbenli@bullyun.com");
    }

    private String getId() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String a = sf.format(TimeUtils.getFirstDay());
        return a.substring(5,7).replace("0","");
    }

}
