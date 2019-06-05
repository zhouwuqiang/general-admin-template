package com.java.business.organization.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "organization_menu_relation")
public class OrganizationMenuRelation {
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
     * 菜单编号
     */
    @Column(name = "MENU_CODE")
    private String menuCode;

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