package com.bugly.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugly.common.base.Constants;
import com.bugly.system.service.SysMenuService;
import com.bugly.system.dao.SysMenuDao;
import com.bugly.system.dao.SysRoleDao;
import com.bugly.system.entity.SysMenu;
import com.bugly.system.entity.SysRole;
import com.bugly.system.vo.MenuNameVO;
import com.bugly.system.vo.MenuVo;
import com.bugly.system.vo.SysMenuVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author no_f
 * @ClassName SysMenuServiceImpl
 * @Description TODO
 * @Date 2020/62/19 15:43
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuDao sysMenuDao;

    private final SysRoleDao sysRoleDao;


    @Override
    public List<MenuVo> getMenuByUser(String username) {
        //获取用户Role
        SysRole sysRole = sysRoleDao.findByName(username);
        List<SysMenu> sysMenus = sysMenuDao.findByRoleId(sysRole.getId());
        List<MenuVo> menuVoList = new ArrayList<>();

        // 取出一级菜单
        List<SysMenu> firstLevel = sysMenus.stream().filter(o -> o.getParentId() == null).collect(Collectors.toList());
        // 拼装二级菜单
        for (SysMenu sysMenu : firstLevel) {
            List<SysMenu> secodeMenuList = new LinkedList<>();
            for (SysMenu menu : sysMenus) {
                if (StringUtils.equals(menu.getParentId(),sysMenu.getId())){
                    secodeMenuList.add(menu);
                }
            }
            menuVoList.add(MenuVo.builder()
                    .name(sysMenu.getMenuName())
                    .code(sysMenu.getMenuCode())
                    .icon(Constants.MENU_ICON_PREFIX + sysMenu.getMenuIcon())
                    .sysMenus(secodeMenuList)
                    .build());
        }
        return menuVoList;
    }

    @Override
    public List<SysMenu> findMenuListByUser(String username) {
        //获取用户Role
        SysRole sysRole = sysRoleDao.findByName(username);
        return sysMenuDao.findByRoleId(sysRole.getId());
    }

    @Override
    public IPage<SysMenu> findFirstMenu(Page page) {
        return sysMenuDao.findFirstMenu(page);
    }

    @Override
    public List<SysMenu> findByParentId(String parentId) {
        return sysMenuDao.findByParentId(parentId);
    }

    @Override
    public int updateMenu(SysMenuVO sysMenu) {
        return sysMenuDao.updateMenu(sysMenu);
    }

    @Override
    public int addMenu(SysMenuVO sysMenu) {
        return sysMenuDao.addMenu(sysMenu);
    }

    @Override
    public SysMenu getByName(String menuName, String menuCode, String menuHref) {
        return sysMenuDao.getByName(menuName, menuCode, menuHref);
    }

    @Override
    public SysMenu getById(String id) {
        return sysMenuDao.getById(id);
    }

    @Override
    public List<SysMenu> getFirstMenu() {
        return sysMenuDao.getFirstMenu();
    }

    @Override
    public List<SysMenu> getSecondMenu() {
        return sysMenuDao.getSecondMenu();
    }

    @Override
    public List<String> getRoleMenu(String roleId) {
        return sysMenuDao.getRoleMenu(roleId);
    }

    @Override
    public List<String> getMenuLevel() {
        return sysMenuDao.getMenuLevel();
    }

    @Override
    public List<MenuNameVO> getPreviousMenu(String menuLevel) {
        return sysMenuDao.getPreviousMenu(menuLevel);
    }

    @Override
    public String getByMenuName(String menuNames) {
        return sysMenuDao.getByMenuName(menuNames);
    }

    @Override
    public int deleteMenuById(String id) {
        return sysMenuDao.deleteMenuById(id);
    }
}
