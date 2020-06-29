package com.bugly.system.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugly.system.model.ProcessingRecord;
import com.bugly.system.model.ServiceLog;
import com.bugly.system.provider.ProcessingRecordProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

/**
 * @author no_f
 * @create 2020-06-19 19:02
 */
@Repository
public interface ProcessingRecordDao extends BaseMapper<ProcessingRecord> {

    @Select("SELECT num FROM `service_log` WHERE error_location=#{location} ORDER BY ctime DESC LIMIT 1")
    int findSameExceptionNum(@Param("location") String location);

    @Select("SELECT * FROM `service_log` WHERE error_location=#{location} AND TO_DAYS(ctime) = TO_DAYS(NOW())")
    ServiceLog findByTimeAndLocation(@Param("location") String location);

    @UpdateProvider(type = ProcessingRecordProvider.class, method = "update")
    int update(ProcessingRecord processingRecord);
}
