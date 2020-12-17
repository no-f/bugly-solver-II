package com.bugly.common.utils;

import com.bugly.system.dao.AlarmConfigDao;
import com.bugly.system.entity.AlarmConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author no_f
 * @since 2020-08-26
 */
@Component
public class CacheUtil {

    @Autowired
    private AlarmConfigDao alarmConfigDao;

    Map<String, AlarmConfig> alarmConfigMap = new ConcurrentHashMap<>(2);
    private final static String COMMON = "common";
    private final static String TIME_OUT = "timeOut";

    public AlarmConfig getAlarmConfig(String key) {
        if (key.equals(COMMON)) {
            if (alarmConfigMap.size() == 0 || !alarmConfigMap.containsKey("config")) {
                List<AlarmConfig> alarmConfig = alarmConfigDao.findDingDingConfigs();
                if (!alarmConfig.isEmpty()) {
                    alarmConfig.forEach(c-> {
                        if ("1".equals(c.getServiceTypeId())) {
                            alarmConfigMap.put("timeOut", c);
                        } else {
                            alarmConfigMap.put("config", c);
                        }
                    });
                    return alarmConfigMap.get("config");
                }
            } else {
                return alarmConfigMap.get("config");
            }
        }

        if (key.equals(TIME_OUT)) {
            if (alarmConfigMap.size() == 0 || !alarmConfigMap.containsKey("timeOut")) {
                List<AlarmConfig> alarmConfig = alarmConfigDao.findDingDingConfigs();
                if (!alarmConfig.isEmpty()) {
                    alarmConfig.forEach(c-> {
                        if ("1".equals(c.getServiceTypeId())) {
                            alarmConfigMap.put("timeOut", c);
                        } else {
                            alarmConfigMap.put("config", c);
                        }
                    });
                }
                return alarmConfigMap.get("timeOut");
            } else {
                return alarmConfigMap.get("timeOut");
            }
        }
        return alarmConfigDao.findDingDingConfig();

    }


}
