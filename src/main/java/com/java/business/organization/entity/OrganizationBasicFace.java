package com.java.business.organization.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "organization_basic_face")
public class OrganizationBasicFace {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 编号
     */
    @Column(name = "ORGANIZATION_CODE")
    private String organizationCode;

    /**
     * 名称
     */
    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;

    /**
     * 上级编号
     */
    @Column(name = "PAREN_CODE")
    private String parenCode;

    /**
     * 状态
     */
    @Column(name = "ORGANIZATION_STATUS")
    private String organizationStatus;

    /**
     * 类型(00-部门,01-岗位)
     */
    @Column(name = "ORGANIZATION_TYPE")
    private String organizationType;

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