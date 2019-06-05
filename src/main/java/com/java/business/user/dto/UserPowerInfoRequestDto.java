package com.java.business.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/14 17:24
 */
@Getter
@Setter
@ToString
public class UserPowerInfoRequestDto {

    /**
     * 组织编号
     */
    private String organizationCode;

    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String  roleName;

    /**
     * 是否锁定
     */
    private String isLock;
}
