package com.bugly.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugly.system.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author no_f
 * @ClassName SysUserDao
 * @Description TODO
 * @Date 2020/62/12 21:50
 */
@Repository
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 根据姓名查询
     * @param name 姓名
     * @return 用户实例
     */
    @Select("SELECT * FROM sys_user WHERE name= #{name}")
    SysUser findByName(@Param("name") String name);

    @Select("SELECT * FROM sys_user WHERE nick_name= #{nickame}")
    SysUser findByNickame(@Param("nickame") String nickame);

    @Select("SELECT nick_name FROM sys_user ")
    List<String> findAllNickame();

    @Select("SELECT * FROM sys_user")
    List<SysUser> findAllBy();

    /**
     * 查询所有用户
     * @param page 分页数据
     * @return 所有用户集合
     */
    @Select("SELECT * FROM sys_user")
    IPage<SysUser> getAll(Page page);

    /**
     * 根据id查用户
     * @param id id
     * @return 用户集合
     */
    @Select("select * from sys_user where id = #{id}")
    SysUser getById(@Param("id")String id);

    /**
     * 更新用户密码
     * @param password 密码
     * @param id id
     * @return 返回值
     */
    @Update("update sys_user set password = #{password} where id = #{id}")
    int updatePasswordById(@Param("password") String password,
                           @Param("id") String id);

}
