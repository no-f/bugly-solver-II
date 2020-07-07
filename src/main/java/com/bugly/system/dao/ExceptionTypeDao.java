package com.bugly.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugly.system.model.ExceptionType;
import com.bugly.system.provider.ExceptionTypeProvider;
import com.bugly.system.vo.BuglySearchVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author no_f
 * @create 2020-06-19 11:29
 */
@Repository
public interface ExceptionTypeDao extends BaseMapper<ExceptionType> {

    @Select("SELECT * FROM `exception_type` order by mtime desc limit #{no}, #{pageSize}")
    List<ExceptionType> findAll(Integer no, Integer pageSize);

    @Select("SELECT count(id) FROM `exception_type`")
    int findAllNum();

    @Select("SELECT count(id) FROM `exception_type` where mtime >=#{startTime} and mtime <=#{endTime}")
    int findAllNumByTime(Date startTime, Date endTime);

    @Select("SELECT count(id) FROM `exception_type` where `state`=0 and mtime >=#{startTime} and mtime <=#{endTime}")
    int findUnSolveNumByTime(Date startTime, Date endTime);

    @Select("SELECT * FROM `exception_type` where mtime >=#{startTime} and mtime <=#{endTime} and service_name =#{serviceName} order by num desc")
    List<ExceptionType> findByToday(Date startTime, Date endTime, String serviceName);

    @SelectProvider(type = ExceptionTypeProvider.class, method = "findByCondition")
    List<ExceptionType> findByCondition(BuglySearchVo buglySearchVo);

    @Select("SELECT * FROM `exception_type` WHERE error_location=#{location} LIMIT 1")
    ExceptionType findByLocal(@Param("location") String location);

    @SelectProvider(type = ExceptionTypeProvider.class, method = "countCondition")
    int countCondition(BuglySearchVo buglySearchVo);
}
