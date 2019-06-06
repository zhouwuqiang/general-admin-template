package com.java.business.user.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "user_organization_relation")
public class UserOrganizationRelation {
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
     * 用户编号
     */
    @Column(name = "USER_CODE")
    private String userCode;

    /**
     * 岗位名称
     */
    @Column(name = "POST_NAME")
    private String postName;

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