package com.java.business.organization.service;

import com.github.pagehelper.PageInfo;
import com.java.business.organization.dto.OrganizationTableRequestDto;
import com.java.business.organization.entity.OrganizationBasicFace;

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
}
