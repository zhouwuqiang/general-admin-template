package com.java.business.log.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "user_login_log")
public class UserLoginLog {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 登录用户编号
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 登录地址
     */
    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    /**
     * 系统类型
     */
    @Column(name = "SYSTEM_TYPE")
    private String systemType;

    /**
     * 浏览器类型
     */
    @Column(name = "BROWSER_TYPE")
    private String browserType;

    /**
     * 登录状态
     */
    @Column(name = "LOGIN_STATUS")
    private String loginStatus;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 登录时间
     */
    @Column(name = "LOGIN_TIME")
    private Date loginTime;

    /**
     * 退出时间
     */
    @Column(name = "LOGIN_OUT_TIME")
    private Date loginOutTime;
}
