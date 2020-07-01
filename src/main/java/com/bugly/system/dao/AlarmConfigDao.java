package com.bugly.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugly.system.entity.AlarmConfig;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * @author no_f
 * @since 2020-07-01
 */
@Repository
public interface AlarmConfigDao extends BaseMapper<AlarmConfig> {

    @Select("SELECT * FROM `alarm_config` where type=0 limit 1")
    AlarmConfig findDingDingConfig();
}
