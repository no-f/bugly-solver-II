package com.bugly.system.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author no_f
 * @date 2020/6/8 14:21
 */
@Data
@Builder
public class SysMenuRole {

    private String menuId;

    private String roleId;

    public SysMenuRole(String menuId, String roleId){
        this.menuId = menuId;
        this.roleId = roleId;
    }

    public SysMenuRole(String menuId){
        this.menuId = menuId;
    }
}
