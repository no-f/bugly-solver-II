package com.bugly.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugly.system.dto.GetServerLogDto;
import com.bugly.system.model.ExceptionType;
import com.bugly.system.provider.ExceptionTypeProvider;
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
public interface ExceptionTypeDao extends BaseMapper<ExceptionType> {


    @Select("SELECT * FROM `exception_type`")
    List<ExceptionType> findAll();


    @SelectProvider(type = ExceptionTypeProvider.class, method = "findByCondition")
    List<ExceptionType> findByCondition(GetServerLogDto getServerLogDto);

    @Select("SELECT id FROM `exception_type` WHERE error_location=#{location} LIMIT 1")
    ExceptionType findByLocal(@Param("location") String location);

    @SelectProvider(type = ExceptionTypeProvider.class, method = "countCondition")
    int countCondition(GetServerLogDto getServerLogDto);

    @Select("SELECT num FROM `exception_type` WHERE error_location=#{location} ORDER BY ctime DESC LIMIT 1")
    int findSameExceptionNum(@Param("location") String location);

    @UpdateProvider(type = ExceptionTypeProvider.class, method = "update")
    int update(ExceptionType exceptionType);
}
