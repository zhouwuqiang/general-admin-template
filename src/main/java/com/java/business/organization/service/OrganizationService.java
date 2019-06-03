package com.java.business.organization.service;

import com.github.pagehelper.PageInfo;
import com.java.business.organization.dto.OrganizationTableRequestDto;

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
}
