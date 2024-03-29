package com.java.business.user.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user_role_relation")
public class UserRoleRelation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色编号
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 用户编号
     */
    @Column(name = "user_code")
    private String userCode;
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
     * 获取角色编号
     *
     * @return ROLE_CODE - 角色编号
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色编号
     *
     * @param roleCode 角色编号
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取菜单编号
     *
     * @return MENU_CODE - 菜单编号
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置菜单编号
     *
     * @param menuCode 菜单编号
     */
    public void setUserCode(String menuCode) {
        this.userCode = menuCode;
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
