package com.bugly.common.job;

import com.bugly.system.dao.ServiceLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author no_f
 * @since 2020-12-24
 */
@Component
public class HomeCacheTask {

    @Autowired
    private ServiceLogDao serviceLogDao;

    private static Map<String, Integer> serverNamePhoneMap = new ConcurrentHashMap<>();



    public Integer getServiceNNum(Date startTime, Date endTime, String serverName) {
        if (serverNamePhoneMap.containsKey(serverName)) {
            return serverNamePhoneMap.get(serverName);
        } else {
            int num = serviceLogDao.findAllNumByTimeAndServiceName(startTime, endTime, serverName);
            serverNamePhoneMap.put(serverName, num);
            return num;
        }
    }

    @Scheduled(cron="0 0 0 * * ?")
    private void deleteData() {

        if (serverNamePhoneMap.size() == 0) {
            return;
        }
        serverNamePhoneMap.clear();
    }
}
