package com.java.business.menu.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "menu_basic_face")
public class MenuBasicFace {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜单编号
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单图标
     */
    @Column(name = "menu_icon")
    private String menuIcon;

    /**
     * 菜单地址
     */
    @Column(name = "menu_action")
    private String menuAction;

    /**
     * 上级菜单编号
     */
    @Column(name = "parent_menu_code")
    private String parentMenuCode;

    /**
     * 是否显示(00-显示 01-不显示)
     */
    @Column(name = "is_display")
    private String isDisplay;

    /**
     * 显示顺序
     */
    @Column(name = "display_index")
    private Integer displayIndex;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新用户编号
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建用户编号
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 删除标识(00-正常 01-删除)
     */
    @Column(name = "delete_flag")
    private String deleteFlag;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取菜单编号
     *
     * @return MENU_CODE - 菜单编号
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置菜单编号
     *
     * @param menuCode 菜单编号
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * 获取菜单名称
     *
     * @return MENU_NAME - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取菜单图标
     *
     * @return MENU_ICON - 菜单图标
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 设置菜单图标
     *
     * @param menuIcon 菜单图标
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    /**
     * 获取菜单地址
     *
     * @return MENU_ACTION - 菜单地址
     */
    public String getMenuAction() {
        return menuAction;
    }

    /**
     * 设置菜单地址
     *
     * @param menuAction 菜单地址
     */
    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }

    /**
     * 获取上级菜单编号
     *
     * @return PARENT_MENU_CODE - 上级菜单编号
     */
    public String getParentMenuCode() {
        return parentMenuCode;
    }

    /**
     * 设置上级菜单编号
     *
     * @param parentMenuCode 上级菜单编号
     */
    public void setParentMenuCode(String parentMenuCode) {
        this.parentMenuCode = parentMenuCode;
    }

    /**
     * 获取是否显示(00-显示 01-不显示)
     *
     * @return IS_DISPLAY - 是否显示(00-显示 01-不显示)
     */
    public String getIsDisplay() {
        return isDisplay;
    }

    /**
     * 设置是否显示(00-显示 01-不显示)
     *
     * @param isDisplay 是否显示(00-显示 01-不显示)
     */
    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    /**
     * 获取显示顺序
     *
     * @return DISPLAY_INDEX - 显示顺序
     */
    public Integer getDisplayIndex() {
        return displayIndex;
    }

    /**
     * 设置显示顺序
     *
     * @param displayIndex 显示顺序
     */
    public void setDisplayIndex(Integer displayIndex) {
        this.displayIndex = displayIndex;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_TIME - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取更新用户编号
     *
     * @return UPDATE_USER - 更新用户编号
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新用户编号
     *
     * @param updateUser 更新用户编号
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建用户编号
     *
     * @return CREATE_USER - 创建用户编号
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建用户编号
     *
     * @param createUser 创建用户编号
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取删除标识(00-正常 01-删除)
     *
     * @return DELETE_FLAG - 删除标识(00-正常 01-删除)
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标识(00-正常 01-删除)
     *
     * @param deleteFlag 删除标识(00-正常 01-删除)
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
