package com.java.business.organization.service;

import com.github.pagehelper.PageInfo;
import com.java.business.organization.dto.OrganizationTableRequestDto;
import com.java.business.organization.entity.OrganizationBasicFace;
import com.java.business.organization.entity.OrganizationMenuRelation;
import com.java.business.role.entity.RoleMenuRelation;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 1:07
 */
public interface OrganizationService {

    /**
     * 查询组织列表
     * @param requestDto
     * @return
     */
    PageInfo queryTable(OrganizationTableRequestDto requestDto);

    /**
     * 保存组织
     * @param requestDto
     */
    OrganizationBasicFace save(OrganizationBasicFace requestDto);

    /**
     * 删除所有组织菜单关联
     * @param organizationCode
     */
    void deleteRelation(String organizationCode);

    /**
     * 查询组织菜单关系
     * @param relation
     * @return
     */
    OrganizationMenuRelation selectRelation(OrganizationMenuRelation relation);

    /**
     * 保存组织菜单关系
     * @param relation
     * @return
     */
    OrganizationMenuRelation saveRelation(OrganizationMenuRelation relation);

    /**
     * 查询组织菜单列表
     * @param relation
     * @return
     */
    List<OrganizationMenuRelation> queryRelationList(OrganizationMenuRelation relation);

    /**
     * 查询组织列表
     * @param organizationBasicFace
     * @return
     */
    List<OrganizationBasicFace> selectList(OrganizationBasicFace organizationBasicFace);

    /**
     * 查询组织code
     * @param organizationCode
     * @return
     */
    List<String> getSubOrganizationCode(String organizationCode);
}
