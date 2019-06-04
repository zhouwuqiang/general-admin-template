package com.java.business.role.facade;

import com.github.pagehelper.PageInfo;
import com.java.business.role.dto.RoleSaveRequestDto;
import com.java.business.role.dto.RoleTableRequestDto;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 20:09
 */
public interface RoleFacade {

    /**
     * 角色列表
     * @param requestDto
     * @return
     */
    PageInfo queryTable(RoleTableRequestDto requestDto);

    /**
     * 保存角色
     * @param requestDto
     */
    void save(RoleSaveRequestDto requestDto);

    /**
     * 删除角色
     * @param requestDto
     */
    void delete(RoleSaveRequestDto requestDto);
}
