package com.bugly.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bugly.system.model.ExceptionType;
import com.bugly.system.model.ExceptionTypeUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author no_f
 * @since 2020-07-01
 */
@Repository
public interface ExceptionTypeUserDao extends BaseMapper<ExceptionTypeUser> {

    @Select("SELECT * FROM `exception_type_user` where exception_type_id=#{exceptionTypeId} order by ctime desc")
    List<ExceptionTypeUser> findByExceptionTypeId(String exceptionTypeId);

}
