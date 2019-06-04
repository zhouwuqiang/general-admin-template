package com.java.business.menu.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/24 18:27
 */
@Getter
@Setter
@ToString
public class MenuSaveRequestDto{

    private Integer id;

    /**
     * 菜单编号
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单地址
     */
    private String menuAction;

    /**
     * 上级菜单编号
     */
    private String parentMenuCode;

    /**
     * 菜单类型(01-目录 02-菜单 03-按钮)
     */
    private String menuType;

    /**
     * 显示顺序
     */
    private Integer displayIndex;

}
