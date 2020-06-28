package com.bugly.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugly.system.dao.SysUserDao;
import com.bugly.system.entity.SysUser;
import com.bugly.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author no_f
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2020/62/11 10:30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserServiceImpl implements SysUserService {

    private final SysUserDao sysUserDao;

    @Override
    public SysUser findByName(String name) {
        return sysUserDao.findByName(name);
    }

    @Override
    public IPage<SysUser> getAll(Page page) {
        return sysUserDao.getAll(page);
    }

    @Override
    public SysUser getById(String id) {
        return sysUserDao.getById(id);
    }

    @Override
    public int deleteById(String id) {
        return sysUserDao.deleteById(id);
    }

    @Override
    public int updateById(SysUser sysUser) {
        return sysUserDao.updateById(sysUser);
    }

    @Override
    public int insert(SysUser sysUser) {
        return sysUserDao.insert(sysUser);
    }

    @Override
    public int updatePasswordById(String password, String id) {
        return sysUserDao.updatePasswordById(password,id);
    }
}
