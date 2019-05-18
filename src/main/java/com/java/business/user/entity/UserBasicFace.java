package com.java.business.user.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_basic_face")
public class UserBasicFace {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户编号
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 用户中文名
     */
    @Column(name = "user_label")
    private String userLabel;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 账号登录密码
     */
    @Column(name = "login_password")
    private String loginPassword;

    /**
     * 安全密码
     */
    @Column(name = "security_password")
    private String securityPassword;

    /**
     * 是否锁定(00-未锁定 01-锁定)
     */
    @Column(name = "is_lock")
    private String isLock;

    /**
     * 是否记住登录状态(00-记录 01-不记录)
     */
    @Column(name = "is_remember")
    private String isRemember;

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
     * 获取用户编号
     *
     * @return USER_CODE - 用户编号
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户编号
     *
     * @param userCode 用户编号
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取用户中文名
     *
     * @return USER_LABEL - 用户中文名
     */
    public String getUserLabel() {
        return userLabel;
    }

    /**
     * 设置用户中文名
     *
     * @param userLabel 用户中文名
     */
    public void setUserLabel(String userLabel) {
        this.userLabel = userLabel;
    }

    /**
     * 获取用户名称
     *
     * @return USER_NAME - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取账号登录密码
     *
     * @return LOGIN_PASSWORD - 账号登录密码
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * 设置账号登录密码
     *
     * @param loginPassword 账号登录密码
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    /**
     * 获取安全密码
     *
     * @return SECURITY_PASSWORD - 安全密码
     */
    public String getSecurityPassword() {
        return securityPassword;
    }

    /**
     * 设置安全密码
     *
     * @param securityPassword 安全密码
     */
    public void setSecurityPassword(String securityPassword) {
        this.securityPassword = securityPassword;
    }

    /**
     * 获取是否锁定(00-未锁定 01-锁定)
     *
     * @return IS_LOcK - 是否锁定(00-未锁定 01-锁定)
     */
    public String getIsLock() {
        return isLock;
    }

    /**
     * 设置是否锁定(00-未锁定 01-锁定)
     *
     * @param isLock 是否锁定(00-未锁定 01-锁定)
     */
    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    /**
     * 获取是否记住登录状态(00-记录 01-不记录)
     *
     * @return IS_REMEMBER - 是否记住登录状态(00-记录 01-不记录)
     */
    public String getIsRemember() {
        return isRemember;
    }

    /**
     * 设置是否记住登录状态(00-记录 01-不记录)
     *
     * @param isRemember 是否记住登录状态(00-记录 01-不记录)
     */
    public void setIsRemember(String isRemember) {
        this.isRemember = isRemember;
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
