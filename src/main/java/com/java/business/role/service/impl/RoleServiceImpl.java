package com.java.business.role.service.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.role.dto.RoleSaveRequestDto;
import com.java.business.role.dto.RoleTableRequestDto;
import com.java.business.role.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 20:11
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public PageInfo queryTable(RoleTableRequestDto requestDto) {
        return null;
    }

    @Override
    public void save(RoleSaveRequestDto requestDto) {

    }
}
