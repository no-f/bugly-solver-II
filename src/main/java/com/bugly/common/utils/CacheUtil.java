package com.bugly.common.utils;

import com.bugly.system.dao.AlarmConfigDao;
import com.bugly.system.entity.AlarmConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author no_f
 * @since 2020-08-26
 */
@Component
public class CacheUtil {

    @Autowired
    private AlarmConfigDao alarmConfigDao;

    Map<String, AlarmConfig> alarmConfigMap = new HashMap<>(1);

    Map<String, AlarmConfig> dubboAlarmConfigMap = new HashMap<>(1);


    public AlarmConfig getAlarmConfig() {
        if (alarmConfigMap.size() == 0 || !alarmConfigMap.containsKey("config")) {
            AlarmConfig alarmConfig = alarmConfigDao.findDingDingConfig();
            alarmConfigMap.put("config", alarmConfig);
           return alarmConfig;
        } else {
            return alarmConfigMap.get("config");
        }
    }

    public AlarmConfig getDubboAlarmConfig() {
        if (dubboAlarmConfigMap.size() == 0 || !dubboAlarmConfigMap.containsKey("config")) {
            AlarmConfig alarmConfig = alarmConfigDao.findByServiceTypeId("1");
            dubboAlarmConfigMap.put("config", alarmConfig);
            return alarmConfig;
        } else {
            return dubboAlarmConfigMap.get("config");
        }
    }



}
