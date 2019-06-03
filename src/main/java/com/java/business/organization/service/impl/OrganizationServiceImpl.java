package com.java.business.organization.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.menu.entity.MenuBasicFace;
import com.java.business.organization.dto.OrganizationTableRequestDto;
import com.java.business.organization.entity.OrganizationBasicFace;
import com.java.business.organization.mapper.OrganizationBasicFaceMapper;
import com.java.business.organization.service.OrganizationService;
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

    @Override
    public PageInfo queryTable(OrganizationTableRequestDto requestDto) {


        Example example = new Example(OrganizationBasicFace.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteFlag", "00");
        criteria.andEqualTo("organizationCode", requestDto.getOrganizationCode());
        criteria.andEqualTo("parenCode", requestDto.getParenCode());

        if (StringUtils.isNotBlank(requestDto.getOrganizationName())) {
            criteria.andCondition("MENU_NAME like", "%" + requestDto.getOrganizationName() + "%");
        }

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<OrganizationBasicFace> queryList = organizationBasicFaceMapper.selectByExample(example);

        return new PageInfo<>(queryList);
    }
}
