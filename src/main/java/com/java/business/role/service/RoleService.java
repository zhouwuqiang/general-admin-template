package com.java.business.role.service;

import com.github.pagehelper.PageInfo;
import com.java.business.role.dto.RoleSaveRequestDto;
import com.java.business.role.dto.RoleTableRequestDto;

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
    void save(RoleSaveRequestDto requestDto);
}
