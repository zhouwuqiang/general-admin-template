package com.java.business.menu.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "menu_basic_face")
public class MenuBasicFace {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜单编号
     */
    @Column(name = "MENU_CODE")
    private String menuCode;

    /**
     * 菜单名称
     */
    @Column(name = "MENU_NAME")
    private String menuName;

    /**
     * 菜单图标
     */
    @Column(name = "MENU_ICON")
    private String menuIcon;

    /**
     * 菜单地址
     */
    @Column(name = "MENU_ACTION")
    private String menuAction;

    /**
     * 上级菜单编号
     */
    @Column(name = "PARENT_MENU_CODE")
    private String parentMenuCode;

    /**
     * 菜单类型(00-目录 01-菜单 02-按钮)
     */
    @Column(name = "MENU_TYPE")
    private String menuType;

    /**
     * 显示顺序
     */
    @Column(name = "DISPLAY_INDEX")
    private Integer displayIndex;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 更新用户编号
     */
    @Column(name = "UPDATE_USER")
    private String updateUser;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 创建用户编号
     */
    @Column(name = "CREATE_USER")
    private String createUser;

    /**
     * 删除标识(00-正常 01-删除)
     */
    @Column(name = "DELETE_FLAG")
    private String deleteFlag;
}
