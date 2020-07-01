package com.bugly.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugly.system.model.ExceptionType;
import com.bugly.system.provider.ExceptionTypeProvider;
import com.bugly.system.vo.BuglySearchVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author no_f
 * @create 2020-06-19 11:29
 */
@Repository
public interface ExceptionTypeDao extends BaseMapper<ExceptionType> {

    @Select("SELECT * FROM `exception_type` order by mtime desc limit 0, 10")
    List<ExceptionType> findAll();

    @Select("SELECT count(id) FROM `exception_type`")
    int findAllNum();

    @SelectProvider(type = ExceptionTypeProvider.class, method = "findByCondition")
    List<ExceptionType> findByCondition(BuglySearchVo buglySearchVo);

    @Select("SELECT * FROM `exception_type` WHERE error_location=#{location} LIMIT 1")
    ExceptionType findByLocal(@Param("location") String location);

    @SelectProvider(type = ExceptionTypeProvider.class, method = "countCondition")
    int countCondition(BuglySearchVo buglySearchVo);
}
