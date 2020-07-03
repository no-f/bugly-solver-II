package com.bugly.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugly.system.entity.ExceptionReport;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author no_f
 * @since 2020-07-01
 */
@Repository
public interface ExceptionReportDao extends BaseMapper<ExceptionReport> {

    @Select("SELECT * FROM `exception_report` order by day")
    List<ExceptionReport> findAll();

}
