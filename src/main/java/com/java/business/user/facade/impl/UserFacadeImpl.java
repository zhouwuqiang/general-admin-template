package com.java.business.user.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.organization.service.OrganizationService;
import com.java.business.role.entity.RoleBasicFace;
import com.java.business.role.service.RoleService;
import com.java.business.user.dto.*;
import com.java.business.user.entity.UserBasicFace;
import com.java.business.user.entity.UserOrganizationRelation;
import com.java.business.user.entity.UserRoleRelation;
import com.java.business.user.facade.UserFacade;
import com.java.business.user.service.UserService;
import com.java.general.config.security.dto.User;
import com.java.general.constant.SystemCommonConstant;
import com.java.general.utils.MD5Util;
import com.java.general.utils.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/14 17:23
 */
@Service
public class UserFacadeImpl implements UserFacade {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrganizationService organizationService;

    @Override
    public PageInfo queryTable(UserTableRequestDto requestDto) {

        if(StringUtils.isNotBlank(requestDto.getOrganizationCode())){
            requestDto.setOrganizationCodeList(organizationService.getSubOrganizationCode(requestDto.getOrganizationCode()));
        }


        return userService.queryTable(requestDto);
    }

    @Override
    public void save(UserSaveRequestDto requestDto) {
        UserBasicFace userBasicFace = saveBasicInfo(requestDto);
        savePowerInfo(requestDto, userBasicFace.getUserCode());
    }

    @Override
    public UserDetailResponseDto detail(UserDetailRequestDto requestDto) {
        UserDetailResponseDto responseDto = new UserDetailResponseDto();

        UserBasicInfoRequestDto basicInfo = new UserBasicInfoRequestDto();
        UserBasicFace userBasicFace = new UserBasicFace();
        userBasicFace.setUserCode(requestDto.getUserCode());
        userBasicFace = userService.queryBasicInfo(userBasicFace);
        BeanUtils.copyProperties(userBasicFace, basicInfo);
        responseDto.setBasicInfo(basicInfo);

        UserPowerInfoRequestDto powerInfo = new UserPowerInfoRequestDto();

        UserRoleRelation roleRelation = new UserRoleRelation();
        roleRelation.setUserCode(requestDto.getUserCode());
        roleRelation = userService.queryRoleRelation(roleRelation);
        if (roleRelation != null){
            RoleBasicFace roleBasicFace = new RoleBasicFace();
            roleBasicFace.setRoleCode(roleRelation.getRoleCode());
            roleBasicFace = roleService.queryDetail(roleBasicFace);
            powerInfo.setRoleCode(roleRelation.getRoleCode());
            powerInfo.setRoleName(roleBasicFace.getRoleName());
        }



        UserOrganizationRelation organizationRelation = new UserOrganizationRelation();
        organizationRelation.setUserCode(requestDto.getUserCode());
        organizationRelation = userService.queryOrganizationRelation(organizationRelation);
        if (organizationRelation != null){
            powerInfo.setIsLock(basicInfo.getIsLock());
            powerInfo.setOrganizationCode(organizationRelation.getOrganizationCode());
            powerInfo.setPostName(organizationRelation.getPostName());
        }

        responseDto.setPowerInfo(powerInfo);

        return responseDto;
    }

    private void savePowerInfo(UserSaveRequestDto requestDto, String userCode) {
        UserPowerInfoRequestDto powerInfo = requestDto.getPowerInfo();

        UserRoleRelation roleRelation = new UserRoleRelation();
        roleRelation.setUserCode(userCode);
        userService.deleteRoleRelation(roleRelation);

        roleRelation.setRoleCode(powerInfo.getRoleCode());
        userService.saveRoleRelation(roleRelation);

        UserOrganizationRelation organizationRelation = new UserOrganizationRelation();
        organizationRelation.setUserCode(userCode);
        userService.deleteOrganizationRelation(organizationRelation);

        organizationRelation.setOrganizationCode(powerInfo.getOrganizationCode());
        organizationRelation.setPostName(powerInfo.getPostName());
        userService.saveOrganizationRelation(organizationRelation);
    }

    private UserBasicFace saveBasicInfo(UserSaveRequestDto requestDto) {

        UserBasicInfoRequestDto basicInfo = requestDto.getBasicInfo();
        UserPowerInfoRequestDto powerInfo = requestDto.getPowerInfo();
        UserBasicFace userBasicFace = new UserBasicFace();
        userBasicFace.setUserCode(basicInfo.getUserCode());
        userBasicFace.setUserName(basicInfo.getUserName());
        userBasicFace.setUserLabel(basicInfo.getUserLabel());
        userBasicFace.setIsLock(powerInfo.getIsLock());

        User loginUser = SpringContextUtil.getLoginUser();
        if (StringUtils.isBlank(basicInfo.getUserCode())) {
            userBasicFace.setCreateUser(loginUser.getUsername());
        }else{
            userBasicFace.setUpdateUser(loginUser.getUsername());
        }

        if (StringUtils.isNotBlank(basicInfo.getLoginPassword())){
            userBasicFace.setLoginPassword(MD5Util.MD5(basicInfo.getLoginPassword()));
        }

        return userService.save(userBasicFace);
    }
}
