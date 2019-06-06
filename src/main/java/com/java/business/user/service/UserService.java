package com.java.business.user.service;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.entity.UserBasicFace;
import com.java.business.user.entity.UserOrganizationRelation;
import com.java.business.user.entity.UserRoleRelation;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/14 17:26
 */
public interface UserService {

    /**
     * 列表查询
     * @param requestDto
     * @return
     */
    PageInfo queryTable(UserTableRequestDto requestDto);

    /**
     * 保存用户
     * @param userBasicFace
     * @return
     */
    UserBasicFace save(UserBasicFace userBasicFace);

    /**
     * 删除用户角色关系
     * @param roleRelation
     * @return
     */
    UserRoleRelation deleteRoleRelation(UserRoleRelation roleRelation);
    /**
     * 保存用户角色关系
     * @param roleRelation
     * @return
     */
    UserRoleRelation saveRoleRelation(UserRoleRelation roleRelation);

    /**
     * 保存用户组织关系
     * @param organizationRelation
     * @return
     */
    UserOrganizationRelation deleteOrganizationRelation(UserOrganizationRelation organizationRelation);
    /**
     * 保存用户组织关系
     * @param organizationRelation
     * @return
     */
    UserOrganizationRelation saveOrganizationRelation(UserOrganizationRelation organizationRelation);

    /**
     * 查询用户详情
     * @param userBasicFace
     * @return
     */
    UserBasicFace queryBasicInfo(UserBasicFace userBasicFace);

    /**
     * 查询用户角色关系
     * @param roleRelation
     * @return
     */
    UserRoleRelation queryRoleRelation(UserRoleRelation roleRelation);

    /**
     * 查询用户菜单关系
     * @param organizationRelation
     * @return
     */
    UserOrganizationRelation queryOrganizationRelation(UserOrganizationRelation organizationRelation);
}
