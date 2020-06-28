package com.bugly.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugly.system.entity.SysLog;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author no_f
 * @ClassName SysLogDao
 * @Description TODO
 * @Date 2020/6/9 16:22
 */
@Repository
public interface SysLogDao extends BaseMapper<SysLog> {

    @Select("SELECT * FROM sys_log ORDER BY create_date desc")
    IPage<SysLog> findSysLogPage(Page page);

}
