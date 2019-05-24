package com.java.business.user.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "user_basic_face")
public class UserBasicFace {
    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户编号
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 用户中文名称
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
     * 是否记住登录状态(00-记录 01-不记录)
     */
    @Column(name = "is_remember")
    private String isRemember;

    /**
     * 是否锁定(00-未锁定 01-锁定)
     */
    @Column(name = "is_lock")
    private String isLock;

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
}