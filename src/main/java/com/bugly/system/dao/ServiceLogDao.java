package com.bugly.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugly.system.dto.DealWithServerLogDto;
import com.bugly.system.dto.GetServerLogDto;
import com.bugly.system.model.ServiceLog;
import com.bugly.system.provider.ServiceLogProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author no_f
 * @create 2020-06-19 11:29
 */
@Repository
public interface ServiceLogDao extends BaseMapper<ServiceLog> {

    @SelectProvider(type = ServiceLogProvider.class, method = "findByCondition")
    List<ServiceLog> findByCondition(GetServerLogDto getServerLogDto);

    @Select("SELECT machine_address FROM `service_log` WHERE exception_type_id=#{id} LIMIT 1")
    ServiceLog findOneByExceptionTypeId(String id);

    @Select("SELECT * FROM `service_log` WHERE exception_type_id=#{exceptionTypeId} ORDER BY ctime DESC LIMIT #{pageSize}")
    List<ServiceLog> findByExceptionTypeId(String exceptionTypeId, Integer pageSize);

    @Select("SELECT * FROM `service_log` ORDER BY ctime DESC LIMIT #{pageSize}")
    List<ServiceLog> findAll(Integer pageSize);

    @SelectProvider(type = ServiceLogProvider.class, method = "countCondition")
    int countCondition(GetServerLogDto getServerLogDto);

    @Select("SELECT num FROM `service_log` WHERE error_location=#{location} ORDER BY ctime DESC LIMIT 1")
    int findSameExceptionNum(@Param("location") String location);

    @UpdateProvider(type = ServiceLogProvider.class, method = "update")
    int update(DealWithServerLogDto dealWithServerLogDto);
}
