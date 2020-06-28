package com.bugly.system.vo;

import com.bugly.system.entity.SysMenu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author no_f
 * @ClassName MenuVo
 * @Description TODO
 * @Date 2020/62/24 15:54
 */
@Data
@Builder
public class MenuVo {

    private String name;

    private String icon;

    private String code;

    private List<SysMenu> sysMenus;

}
