package com.bugly.system.service;

import com.bugly.system.entity.SysUserRole;

/**
 * @author no_f
 * @date 2020/6/12 0:43
 */
public interface SysUserRoleService {

    /**
     * 添加用户和角色的联系
     * @param sysUserRole 用户角色
     * @return 返回值
     */
    int insert(SysUserRole sysUserRole);

    /**
     * 根据用户id删除用户和角色的联系
     * @param userId 用户id
     * @return 返回值
     */
    int deleteByUserId(String userId);
}
