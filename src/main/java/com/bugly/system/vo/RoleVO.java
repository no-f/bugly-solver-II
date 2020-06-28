package com.bugly.system.vo;

import com.bugly.system.entity.SysRole;
import lombok.Data;

/**
 * @author cuiyating
 * @date 2020/6/16 16:45
 */
@Data
public class RoleVO extends SysRole {

    private String[] ids;
}
