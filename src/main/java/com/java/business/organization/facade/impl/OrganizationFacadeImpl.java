package com.java.business.organization.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.organization.dto.OrganizationSaveRequestDto;
import com.java.business.organization.dto.OrganizationTableRequestDto;
import com.java.business.organization.entity.OrganizationBasicFace;
import com.java.business.organization.facade.OrganizationFacade;
import com.java.business.organization.service.OrganizationService;
import com.java.general.constant.SystemCommonConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
