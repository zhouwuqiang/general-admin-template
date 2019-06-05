package com.java.business.organization.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.organization.dto.OrganizationTableRequestDto;
import com.java.business.organization.entity.OrganizationBasicFace;
import com.java.business.organization.entity.OrganizationMenuRelation;
import com.java.business.organization.mapper.OrganizationBasicFaceMapper;
import com.java.business.organization.mapper.OrganizationMenuRelationMapper;
import com.java.business.organization.service.OrganizationService;
import com.java.general.utils.UuidCodeWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 1:07
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationBasicFaceMapper organizationBasicFaceMapper;

    @Autowired
    private OrganizationMenuRelationMapper organizationMenuRelationMapper;

    @Override
    public PageInfo queryTable(OrganizationTableRequestDto requestDto) {


        Example example = new Example(OrganizationBasicFace.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteFlag", "00");
        criteria.andEqualTo("organizationCode", requestDto.getOrganizationCode());
        criteria.andEqualTo("parenCode", requestDto.getParenCode());
        criteria.andEqualTo("organizationType", requestDto.getOrganizationType());

        if (StringUtils.isNotBlank(requestDto.getOrganizationName())) {
            criteria.andCondition("ORGANIZATION_NAME like", "%" + requestDto.getOrganizationName() + "%");
        }

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<OrganizationBasicFace> queryList = organizationBasicFaceMapper.selectByExample(example);

        return new PageInfo<>(queryList);
    }

    @Override
    public OrganizationBasicFace save(OrganizationBasicFace organizationBasicFace) {

        if (organizationBasicFace.getId() == null){
            organizationBasicFace.setOrganizationCode(UuidCodeWorker.nextCode("ORG"));
            organizationBasicFaceMapper.insertSelective(organizationBasicFace);
        }else{
            organizationBasicFaceMapper.updateByPrimaryKeySelective(organizationBasicFace);
        }

        return organizationBasicFace;
    }

    @Override
    public void deleteRelation(String organizationCode) {
        OrganizationMenuRelation relation = new OrganizationMenuRelation();
        relation.setDeleteFlag("01");

        Example example = new Example(OrganizationMenuRelation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("organizationCode", organizationCode);
        organizationMenuRelationMapper.updateByExampleSelective(relation, example);
    }

    @Override
    public OrganizationMenuRelation selectRelation(OrganizationMenuRelation relation) {
        return organizationMenuRelationMapper.selectOne(relation);
    }

    @Override
    public OrganizationMenuRelation saveRelation(OrganizationMenuRelation relation) {
        if (relation.getId() == null) {
            organizationMenuRelationMapper.insertSelective(relation);
        }else{
            organizationMenuRelationMapper.updateByPrimaryKeySelective(relation);
        }
        return relation;
    }

    @Override
    public List<OrganizationMenuRelation> queryRelationList(OrganizationMenuRelation relation) {
        return organizationMenuRelationMapper.select(relation);
    }
}
