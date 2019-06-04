package com.java.business.role.service;

import com.github.pagehelper.PageInfo;
import com.java.business.role.dto.RoleTableRequestDto;
import com.java.business.role.entity.RoleBasicFace;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 20:11
 */
public interface RoleService {
    /**
     * 列表查询
     * @param requestDto
     * @return
     */
    PageInfo queryTable(RoleTableRequestDto requestDto);

    /**
     * 角色保存
     * @param requestDto
     */
    RoleBasicFace save(RoleBasicFace requestDto);
}
