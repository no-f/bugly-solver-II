package com.bugly.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugly.system.dto.GetServerLogDto;
import com.bugly.system.model.ServiceLog;
import com.bugly.system.provider.ServiceLogProvider;
import com.bugly.system.vo.BuglyDetailSearchVo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
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

    @Select("SELECT * FROM `service_log` WHERE exception_type_id=#{exceptionTypeId} ORDER BY ctime DESC LIMIT #{no},#{pageSize}")
    List<ServiceLog> findByExceptionTypeId(String exceptionTypeId, Integer no, Integer pageSize);

    @Select("SELECT count(id) FROM `service_log` WHERE exception_type_id=#{exceptionTypeId}")
    int findCountByExceptionTypeId(String exceptionTypeId);

    @Select("SELECT * FROM `service_log` ORDER BY ctime DESC LIMIT #{no}, #{pageSize}")
    List<ServiceLog> findAll(Integer no, Integer pageSize);

    @Select("SELECT count(id) FROM `service_log`")
    int findAllNum();

    @SelectProvider(type = ServiceLogProvider.class, method = "countCondition")
    int countCondition(GetServerLogDto getServerLogDto);
}
