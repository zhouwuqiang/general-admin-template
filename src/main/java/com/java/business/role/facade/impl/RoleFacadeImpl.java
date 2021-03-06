package com.java.business.role.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.role.dto.RoleRelationSaveRequestDto;
import com.java.business.role.dto.RoleSaveRequestDto;
import com.java.business.role.dto.RoleTableRequestDto;
import com.java.business.role.entity.RoleBasicFace;
import com.java.business.role.entity.RoleMenuRelation;
import com.java.business.role.facade.RoleFacade;
import com.java.business.role.service.RoleService;
import com.java.general.constant.SystemCommonConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 20:09
 */
@Service
public class RoleFacadeImpl implements RoleFacade {

    @Autowired
    private RoleService roleService;

    @Override
    public PageInfo queryTable(RoleTableRequestDto requestDto) {
        return roleService.queryTable(requestDto);
    }

    @Override
    public void save(RoleSaveRequestDto requestDto) {
        RoleBasicFace roleBasicFace = new RoleBasicFace();
        BeanUtils.copyProperties(requestDto, roleBasicFace);
        roleService.save(roleBasicFace);
    }

    @Override
    public void delete(RoleSaveRequestDto requestDto) {
        RoleBasicFace roleBasicFace = new RoleBasicFace();
        roleBasicFace.setId(requestDto.getId());
        roleBasicFace.setDeleteFlag(SystemCommonConstant.DeleteFlag.DELETE);
        BeanUtils.copyProperties(roleBasicFace, requestDto);
        roleService.save(roleBasicFace);
    }

    @Override
    public void relationSave(RoleRelationSaveRequestDto requestDto) {

        //1.删除所有角色关联关系
        roleService.deleteRelation(requestDto.getRoleCode());

        //2.查询关联关系是否存在

        for (String menuCode : requestDto.getMenuCodeList()) {
            RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
            roleMenuRelation.setRoleCode(requestDto.getRoleCode());
            roleMenuRelation.setMenuCode(menuCode);
            roleMenuRelation.setDeleteFlag(SystemCommonConstant.DeleteFlag.DELETE);
            RoleMenuRelation oldRelation = roleService.selectRelation(roleMenuRelation);

            if (oldRelation != null) {
                roleMenuRelation = oldRelation;
                roleMenuRelation.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
            }

            roleMenuRelation.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
            roleService.saveRelation(roleMenuRelation);
        }
    }
}
