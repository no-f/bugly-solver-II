package com.bugly.system.rest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bugly.common.base.ApiResponse;
import com.bugly.common.utils.UUIDUtils;
import com.bugly.system.entity.SysMenu;
import com.bugly.system.entity.SysMenuRole;
import com.bugly.system.entity.SysRole;
import com.bugly.system.service.SysMenuRoleService;
import com.bugly.system.service.SysMenuService;
import com.bugly.system.service.SysRoleService;
import com.bugly.system.vo.RoleVO;
import com.bugly.system.vo.MenuListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author no_f
 * @date 2020/6/3 15:45
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleRestController {

    private final SysRoleService sysRoleService;

    private final SysMenuService sysMenuService;

    private final SysMenuRoleService sysMenuRoleService;

    @GetMapping("/getRoleInfo")
    public ApiResponse getRoleInfo(@RequestParam("page") int page,
                                   @RequestParam("page_size") int pageSize) {
        JSONObject jsonObject = new JSONObject();
        IPage<SysRole> sysRoleList = sysRoleService.getAll(new Page(page, pageSize));
        jsonObject.put("total",sysRoleList.getTotal());
        jsonObject.put("page",sysRoleList.getCurrent());
        jsonObject.put("page_size",sysRoleList.getSize());
        jsonObject.put("sysRoleList",sysRoleList.getRecords());
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/deleteRole")
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse deleteRole(@RequestParam("id")String id){
        JSONObject jsonObject = new JSONObject();
        try{
            sysMenuRoleService.deleteByRoleId(id);
            sysRoleService.deleteById(id);
            jsonObject.put("code",200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/updateRole")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse updateRole(@RequestBody RoleVO roleVO){
        JSONObject jsonObject = new JSONObject();
        try{
            sysMenuRoleService.deleteByRoleId(roleVO.getId());
            for (String menuId : roleVO.getIds()){
                SysMenuRole sysMenuRole = new SysMenuRole(menuId, roleVO.getId());
                sysMenuRoleService.addMenuRole(sysMenuRole);
            }
            SysRole sysRole = new SysRole();
            sysRole.setId(roleVO.getId());
            sysRole.setName(roleVO.getName());
            sysRole.setAuthority(roleVO.getAuthority());
            sysRoleService.updateById(sysRole);
            jsonObject.put("code", 200);
        }catch (Exception e) {
            jsonObject.put("code", 500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @PostMapping("/addRole")
    @ResponseBody
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public ApiResponse addRole(@RequestBody RoleVO roleVO){
        JSONObject jsonObject = new JSONObject();
        try{
            SysRole role = sysRoleService.getByName(roleVO.getName());
            if (role == null){
                String id = UUIDUtils.getUUID();
                for (String menuId : roleVO.getIds()){
                    SysMenuRole sysMenuRole = new SysMenuRole(menuId, id);
                    sysMenuRoleService.addMenuRole(sysMenuRole);
                }
                SysRole sysRole = new SysRole(id, roleVO.getName(), roleVO.getAuthority(), new Date());
                sysRoleService.insert(sysRole);
                jsonObject.put("code",200);
            } else {
                // 501 角色已存在
                jsonObject.put("code",501);
            }
        }catch (Exception e){
            jsonObject.put("code",500);
            e.printStackTrace();
        }
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/getData")
    public ApiResponse getData(){
        JSONObject jsonObject = new JSONObject();
        List<MenuListVo> listVoList = getMenu();
        jsonObject.put("menuList",listVoList);
        return ApiResponse.ofSuccess(jsonObject);
    }

    @GetMapping("/getRoleMenu")
    public ApiResponse getRoleMenu(@RequestParam("roleId")String roleId){
        JSONObject jsonObject = new JSONObject();
        List<MenuListVo> listVoList = getMenu();
        List<String> parentIds = sysMenuService.getRoleMenu(roleId);
        List<String> roleMenuIds = sysMenuRoleService.getAllMenuId(roleId, parentIds);
        jsonObject.put("ids", roleMenuIds);
        jsonObject.put("parentIds", parentIds);
        jsonObject.put("menuList",listVoList);
        return ApiResponse.ofSuccess(jsonObject);
    }

    private List<MenuListVo> getMenu(){
        List<MenuListVo> listVoList = new LinkedList<>();
        List<SysMenu> firstMenuList = sysMenuService.getFirstMenu();
        //组装数据
        for (SysMenu sysMenu : firstMenuList) {
            List<SysMenu> secondMenu = sysMenuService.findByParentId(sysMenu.getId());
            listVoList.add(MenuListVo.builder().id(sysMenu.getId())
                    .children(secondMenu)
                    .isShow(sysMenu.getIsShow())
                    .menuCode(sysMenu.getMenuCode())
                    .menuHref(sysMenu.getMenuHref())
                    .menuIcon(sysMenu.getMenuIcon())
                    .menuLevel(sysMenu.getMenuLevel())
                    .menuName(sysMenu.getMenuName())
                    .menuWeight(sysMenu.getMenuWeight()).build());
        }
        return listVoList;
    }

}
