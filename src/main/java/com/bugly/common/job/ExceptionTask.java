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
    @Scheduled(cron="0 0/10 * * * ?")
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
    @Scheduled(cron="0 0 0 * * ?")
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
            StringBuffer stringBuffer = getHeader(today);

            serviceTypes.forEach(serviceType -> {
                List<ExceptionType> exceptionTypes = exceptionTypeDao
                        .findByToday(getTheDayBeforeStart(), getTheDayBeforeEnd(), serviceType.getServiceName());
                if (null == exceptionTypes || exceptionTypes.isEmpty()) {
                    return;
                }
                stringBuffer.append(getServiceTdHeader(serviceType.getServiceName()));
                int exceptionTypeSize = exceptionTypes.size();
                for (int i=0;i<exceptionTypeSize;i++) {
                    stringBuffer.append(getServiceTdContent(i +1,
                            exceptionTypes.get(i).getErrorLocation(),
                            exceptionTypes.get(i).getNum()))
                            .append("<br/>");
                }
                stringBuffer.append(getServiceTdEnd());
            });
            stringBuffer.append(getEnd());
            if (!stringBuffer.toString().contains("次数")) {
                return;
            }

            EmailUtil.sendEmail(stringBuffer.toString(), emailSender, sysUser.getEmail());
        });
    }

    private String getId() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String a = sf.format(TimeUtils.getFirstDay());
        return a.substring(a.indexOf("-") + 1, a.lastIndexOf("-"));
    }

    private String getServiceTdHeader(String serviceName) {
        String content = "\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t<td style=\"padding-bottom:20px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\" lsm:container lsm:editable lsm:repeatable lsm:move lsm:delete>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td style=\"border:1px solid #ccc; background:#fff;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"left\" valign=\"top\" style=\"padding:20px;\" width=\"150\" lsm:grid lsm:image>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<img src=\"https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture&_t="+ new Date().getTime() +"\" width=\"150\" alt=\"\" />\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td align=\"left\" valign=\"top\" style=\"word-wrap:break-word;word-break:break-all;font-size:14px; padding:20px; font-family:'open sans'; color:#666\" width=\"450\" lsm:grid lsm:content>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<h4 style=\"color:#333; font-size:16px;\">" + serviceName + "</h4> <br />\n";
        return content;
    }

    private String getServiceTdContent(int num, String localtion, int eventNum) {
        StringBuffer contentStr = new StringBuffer("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t")
                .append(num)
                .append(".")
                .append(localtion).append(" 触发次数： ").append(eventNum);
        return contentStr.toString();

    }

    private String getServiceTdEnd() {
        return
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "\n";
    }

    private StringBuffer getHeader(String today) {
        String str = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "\t<head>\n" +
                "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "        <title></title>\n" +
                "\t\t<!--template-static-->\n" +
                "        <style type=\"text/css\">\n" +
                "\t\t\tbody{\n" +
                "\t\t\t\theight: 100%!important;\n" +
                "\t\t\t\tmargin:0;\n" +
                "\t\t\t\tpadding:0;\n" +
                "\t\t\t\twidth: 100%!important;\n" +
                "\t\t\t}\n" +
                "\t\t\ttable{\n" +
                "\t\t\t\tborder-collapse:collapse;\n" +
                "\t\t\t}\n" +
                "\t\t\timg,a img{\n" +
                "\t\t\t\tborder:0;\n" +
                "\t\t\t\toutline:0;\n" +
                "\t\t\t\ttext-decoration: none;\n" +
                "\t\t\t}\n" +
                "\t\t\th1,h2,h3,h4,h5,h6{\n" +
                "\t\t\t\tmargin:0;\n" +
                "\t\t\t\tpadding:0;\n" +
                "\t\t\t}\n" +
                "\t\t\tp{\n" +
                "\t\t\t\tmargin:1em 0;\n" +
                "\t\t\t\tpadding:0;\n" +
                "\t\t\t}\n" +
                "\t\t\ttable,td{\n" +
                "\t\t\t\tmso-table-lspace : 0;\n" +
                "\t\t\t\tmso-table-rspace : 0;\n" +
                "\t\t\t}\n" +
                "\t\t\t#outlook a{\n" +
                "\t\t\t\tpadding:0;\n" +
                "\t\t\t}\n" +
                "\t\t\timg{\n" +
                "\t\t\t\t-ms-interpolation-mode : bicubic;\n" +
                "\t\t\t}\n" +
                "\t\t\tbody,table,td,p,a,li,blockquote{\n" +
                "\t\t\t\t-ms-text-size-adjust:100%;\n" +
                "\t\t\t\t-webkit-text-size-adjust:100%;\n" +
                "\t\t\t}\n" +
                "        </style>\n" +
                "\t</head>\n" +
                "\t<body leftmargin=\"0\" marginwidth=\"0\" topmargin=\"0\" marginheight offset=\"0\" style=\"background:#ebebeb\">\n" +
                "\t\t<center>\n" +
                "\t\t\t<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\" style=\"background:#ebebeb\" id=\"bodyTable\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t\t<td align=\"center\" style=\"padding:20px 0;\">\n" +
                "\t\t\t\t\t\t<table cellspacing=\"0\" cellpadding=\"0\" width=\"600\" border=\"0\">\n" +
                "\n" +
                "\n" +
                "\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t<td align=\"left\" valign=\"top\" style=\"padding-bottom:20px;\">\n" +
                "\t\t\t\t\t\t\t\t\t<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" border=\"0\" bgcolor=\"#2e3639\" style=\"background:#2e3639\" lsm:container lsm:editable lsm:repeatable lsm:move lsm:delete>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td valign=\"middle\" align=\"left\" lsm:grid lsm:image style=\"background-color: #3f53b5;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t<img src=\"https://www.bullyun.com/_nuxt/img/bcae00c.svg\" border=\"0\" height=\"100\" width=\"600\" alt=\"\" />\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td valign=\"middle\" align=\"right\" lsm:grid lsm:image style=\"background-color: #3f53b5;color: floralwhite;\">\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t" + today + "\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "\n";
        return new StringBuffer(str);
    }

    private String getEnd() {
        return "\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t<td align=\"center\" valign=\"top\">\n" +
                "\t\t\t\t\t\t\t\t\t<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" bgcolor=\"#dfdfdf\" style=\"background:#ebebeb\" lsm:container lsm:editable lsm:repeatable lsm:move lsm:delete>\n" +
                "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t<td align=\"center\" style=\"padding-bottom:20px; font-size:14px; color:#666; font-family:'open sans'\" lsm:grid lsm:content>\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t杭州蛮牛网络技术有限公司 © 2020\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</table>\n" +
                "\t\t</center>\n" +
                "\t</body>\n" +
                "</html>";
    }


}
