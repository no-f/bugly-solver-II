package com.bugly.system.vo;

import com.bugly.system.entity.SysMenu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author no_f
 * @ClassName MenuListVo
 * @Description TODO
 * @Date 2020/62/31 16:25
 */
@Data
@Builder
public class MenuListVo {

    /**
     * 菜单主键
     */
    private String id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单别名
     */
    private String menuCode;

    /**
     * 菜单链接
     */
    private String menuHref;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单级别
     */
    private String menuLevel;

    /**
     * 菜单权重
     */
    private int menuWeight;

    /**
     * 菜单是否显示
     */
    private Boolean isShow;

    /**
     * 子菜单
     */
    private List<SysMenu> children;

}
