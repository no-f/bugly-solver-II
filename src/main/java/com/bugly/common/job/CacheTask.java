package com.bugly.common.job;

import com.bugly.system.bo.ServiceDetail;
import com.bugly.system.dao.ServiceTypeDao;
import com.bugly.system.model.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.bugly.common.utils.TimeUtils.getFirstDay;
import static com.bugly.common.utils.TimeUtils.getLastDay;

/**
 * @author no_f
 * @since 2020-08-26
 */
@Component
public class CacheTask {

    @Autowired
    private ServiceTypeDao serviceTypeDao;

    @Autowired
    private HomeCacheTask homeCacheTask;

    private static Map<String, List<String>> serverNamePhoneMap = new HashMap<>();

    public static Map<String, List<ServiceDetail>> stringListMap = new HashMap<>();

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
            return phones;
        } else {
            return serverNamePhoneMap.get(serviceName);
        }
    }

    @Scheduled(cron="0 0 0/1 * * ?")
    public void xx() {
        stringListMap.put("serviceExcNum", getData());
    }

    public List<ServiceDetail> getServiceExcNum() {
        if (!stringListMap.isEmpty()) {
            return stringListMap.get("serviceExcNum");
        } else {
            return getData();
        }
    }

    private List<ServiceDetail> getData() {
        List<ServiceType> serviceTypes = serviceTypeDao.findAll(0, 50);
        if (null == serviceTypes || serviceTypes.isEmpty()) {
            return null;
        }
        List<ServiceDetail> serviceDetails = new ArrayList<>();
        Date sTime = getFirstDay();
        Date eTime = getLastDay();

        serviceTypes.forEach(serviceType -> {
            ServiceDetail serviceDetail = new ServiceDetail();
            serviceDetail.setName(serviceAbbreviation(serviceType.getServiceName()));
            serviceDetail.setNum(homeCacheTask.getServiceNNum(sTime, eTime, serviceType.getServiceName()));
            serviceDetails.add(serviceDetail);
        });
        return serviceDetails;
    }

    private String serviceAbbreviation(String serviceName) {
        if (serviceName.contains("traffic")) {
            return "tfc";
        }

        String[] strings = serviceName.split("-");
        StringBuffer stringBuffer = new StringBuffer();
        for (String str: strings) {
            stringBuffer.append(str, 0, 1);
        }

        if (stringBuffer.toString().equals("ires")) {
            return "iot";
        } else if (stringBuffer.toString().equals("os")) {
            return "order";
        } else if (stringBuffer.toString().equals("dns")) {
            return "node";
        } else if (stringBuffer.toString().equals("ts")) {
            return "task";
        }else if (stringBuffer.toString().equals("rs")) {
            return "rest";
        }
        return stringBuffer.toString();
    }

}
