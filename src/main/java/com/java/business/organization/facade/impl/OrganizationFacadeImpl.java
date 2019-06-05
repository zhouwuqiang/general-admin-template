package com.java.business.organization.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.organization.dto.*;
import com.java.business.organization.entity.OrganizationBasicFace;
import com.java.business.organization.entity.OrganizationMenuRelation;
import com.java.business.organization.facade.OrganizationFacade;
import com.java.business.organization.service.OrganizationService;
import com.java.business.utils.tree.dto.Tree;
import com.java.general.constant.SystemCommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 1:03
 */
@Service
public class OrganizationFacadeImpl implements OrganizationFacade {

    @Autowired
    private OrganizationService organizationService;

    @Override
    public PageInfo queryTable(OrganizationTableRequestDto requestDto) {
        return organizationService.queryTable(requestDto);
    }

    @Override
    public void save(OrganizationSaveRequestDto requestDto) {
        OrganizationBasicFace organizationBasicFace = new OrganizationBasicFace();
        BeanUtils.copyProperties(requestDto, organizationBasicFace);
        organizationService.save(organizationBasicFace);
    }

    @Override
    public void delete(OrganizationSaveRequestDto requestDto) {
        OrganizationBasicFace organizationBasicFace = new OrganizationBasicFace();
        organizationBasicFace.setId(requestDto.getId());
        organizationBasicFace.setDeleteFlag(SystemCommonConstant.DeleteFlag.DELETE);
        organizationService.save(organizationBasicFace);
    }

    @Override
    public void relationSave(RelationSaveRequestDto requestDto) {

        //1.删除所有角色关联关系
        organizationService.deleteRelation(requestDto.getOrganizationCode());

        //2.查询关联关系是否存在

        for (String menuCode : requestDto.getMenuCodeList()) {
            OrganizationMenuRelation relation = new OrganizationMenuRelation();
            relation.setOrganizationCode(requestDto.getOrganizationCode());
            relation.setMenuCode(menuCode);
            relation.setDeleteFlag(SystemCommonConstant.DeleteFlag.DELETE);
            OrganizationMenuRelation oldRelation = organizationService.selectRelation(relation);

            if (oldRelation != null) {
                relation = oldRelation;
                relation.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
            }

            relation.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
            organizationService.saveRelation(relation);
        }
    }

    @Override
    public List<Organization> queryListTree(OrganizationTreeRequestDto requestDto) {
        OrganizationBasicFace organizationBasicFace = new OrganizationBasicFace();
        organizationBasicFace.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
        List<OrganizationBasicFace> organizationBasicFaceList = organizationService.selectList(organizationBasicFace);

        List<Organization> organizationTree = builderOrganizationTree(organizationBasicFaceList);

        return organizationTree;
    }

    private List<Organization> builderOrganizationTree(List<OrganizationBasicFace> organizationBasicFaceList) {
        //转换为组织对象
        List<Organization> result = new ArrayList<>();
        List<Organization> organizationList = new ArrayList<>();

        for (OrganizationBasicFace item : organizationBasicFaceList) {
            Organization organization = new Organization();
            BeanUtils.copyProperties(item, organization);
            organization.setText(item.getOrganizationName());
            if (StringUtils.isBlank(organization.getParenCode())) {
                organization.setRoot(true);
                result.add(organization);
            } else {
                organizationList.add(organization);
            }

        }

        for (Organization item : result) {
            item.addChild(organizationList);
        }

        return result;
    }
}
