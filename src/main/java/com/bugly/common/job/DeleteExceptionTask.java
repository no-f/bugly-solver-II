package com.bugly.common.job;

import com.bugly.system.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author no_f
 *
 * @since 2020-06-30
 */
@Component
public class DeleteExceptionTask {

    @Autowired
    private ServiceLogDao serviceLogDao;

    /**
     * 删除异常记录
     */
    @Scheduled(cron="0 0 3 1/1 * ?")
    private void deleteData() {
        serviceLogDao.deleteServiceLog();
    }

}
