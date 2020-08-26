package com.bugly.common.job;

import com.bugly.system.dao.ServiceTypeDao;
import com.bugly.system.model.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author no_f
 * @since 2020-08-26
 */
@Component
public class CacheTask {

    @Autowired
    private ServiceTypeDao serviceTypeDao;

    private static Map<String, List<String>> serverNamePhoneMap = new HashMap<>();

    /**
     * 缓存
     */
    @Scheduled(cron="0 0 0 * * ?")
    private void deleteData() {

        List<ServiceType> serviceTypes = serviceTypeDao.findAll(0, 50);
        if (serviceTypes.size() == 0) {
            return;
        }
        serviceTypes.forEach(serviceType -> {
            List<String> phones = serviceTypeDao.findMobilesByServiceName(serviceType.getServiceName());
            serverNamePhoneMap.put(serviceType.getServiceName(), phones);
        });
    }

    public List<String> getServerNamePhoneMap(String serviceName) {
        if (serverNamePhoneMap.size() == 0 || !serverNamePhoneMap.containsKey(serviceName)) {
            List<String> phones = serviceTypeDao.findMobilesByServiceName(serviceName);
            serverNamePhoneMap.put(serviceName, phones);
        } else {
            return serverNamePhoneMap.get(serviceName);
        }
        return null;
    }
}
