package com.bugly.common.job;

import com.bugly.common.sms.EmailUtil;
import com.bugly.common.utils.TimeUtils;
import com.bugly.system.dao.*;
import com.bugly.system.entity.ExceptionReport;
import com.bugly.system.entity.SysUser;
import com.bugly.system.model.ExceptionType;
import com.bugly.system.model.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.bugly.common.utils.TimeUtils.getTheDayBeforeEnd;
import static com.bugly.common.utils.TimeUtils.getTheDayBeforeStart;

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
    private SysUserDao sysUserDao;

    @Autowired
    private ServiceTypeDao serviceTypeDao;

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
    @Scheduled(cron="0 0 10 * * ?")
    public void day() {
        List<SysUser> sysUsers = sysUserDao.findAllBy();

        if (null == sysUsers || sysUsers.isEmpty()) {
            return;
        }

        String today = TimeUtils.theDayBefore();
        sysUsers.forEach(sysUser -> {
            if (sysUser.getName().equals("admin")) {
                return;
            }
            List<ServiceType> serviceTypes =  serviceTypeDao.findByUserId(sysUser.getId());
            if (null == serviceTypes || serviceTypes.isEmpty()) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer("<div>" + today + "-异常位置:<br/>");

            serviceTypes.forEach(serviceType -> {
                List<ExceptionType> exceptionTypes = exceptionTypeDao
                        .findByToday(getTheDayBeforeStart(), getTheDayBeforeEnd(), serviceType.getServiceName());
                if (null == exceptionTypes || exceptionTypes.isEmpty()) {
                    return;
                }
                stringBuffer.append(serviceType.getServiceName()).append("<br/>");
                int exceptionTypeSize = exceptionTypes.size();
                for (int i=0;i<exceptionTypeSize;i++) {
                    stringBuffer.append(i +1).append(":")
                            .append(exceptionTypes.get(i).getErrorLocation())
                            .append("次数：").append(exceptionTypes.get(i).getNum())
                            .append("<br/>");
                }
            });
            stringBuffer.append("</div>").append("<br/>").append("<hr/>");
            if (!stringBuffer.toString().contains("次数")) {
                return;
            }

            EmailUtil.sendEmail(stringBuffer.toString(), emailSender, sysUser.getEmail());
        });
    }

    private String getId() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String a = sf.format(TimeUtils.getFirstDay());
        return a.substring(5,7).replace("0","");
    }

    public static void main(String[] args) throws ParseException {
        String aaa = "<div style='background-color: crimson;'>" + new Date() + "-异常位置:<br/>" +
                "rest-服务:<br/>" +
                "1: com.manniu.8.core.service.XiaoMiPush.【行号=】"+ "次数：8"+"<br/>" +
                "2: com.manniu.8.core.service.XiaoMiPush.【行号=】"+ "次数：8"+"<br/>" +
                "3: com.manniu.8.core.service.XiaoMiPush.【行号=】"+ "次数：8"+"<br/>" +
                "face-服务:<br/>" +
                "1: com.manniu.8.core.service.XiaoMiPush.【行号=】"+ "次数：8"+"<br/>" +
                "2: com.manniu.8.core.service.XiaoMiPush.【行号=】"+ "次数：8"+"<br/>" +
                "</div>" +
                "<br/>" +
                "<hr/>" +
                "<div>与之前一天新增的异常</div><br/>" +
                "1: com.manniu.8.core.service.XiaoMiPush.【行号=】<br/>"+
                "2: com.manniu.8.core.service.XiaoMiPush.【行号=】<br/>";
    }

}
