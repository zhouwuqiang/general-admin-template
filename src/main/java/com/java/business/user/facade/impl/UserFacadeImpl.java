package com.java.business.user.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserBasicInfoRequestDto;
import com.java.business.user.dto.UserPowerInfoRequestDto;
import com.java.business.user.dto.UserSaveRequestDto;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.entity.UserBasicFace;
import com.java.business.user.facade.UserFacade;
import com.java.business.user.service.UserService;
import com.java.general.config.security.dto.User;
import com.java.general.utils.MD5Util;
import com.java.general.utils.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public PageInfo queryTable(UserTableRequestDto requestDto) {
        PageInfo result = userService.queryTable(requestDto);
        return result;
    }

    @Override
    public void save(UserSaveRequestDto requestDto) {
        UserBasicFace userBasicFace = saveBasicInfo(requestDto);
        savePowerInfo(requestDto,userBasicFace.getUserCode() );
    }

    private void savePowerInfo(UserSaveRequestDto requestDto,String userCode) {
        UserPowerInfoRequestDto powerInfo = requestDto.getPowerInfo();




    }

    private UserBasicFace saveBasicInfo(UserSaveRequestDto requestDto) {
        UserBasicInfoRequestDto basicInfo = requestDto.getBasicInfo();
        UserPowerInfoRequestDto powerInfo = requestDto.getPowerInfo();
        UserBasicFace userBasicFace = new UserBasicFace();
        userBasicFace.setUserCode(basicInfo.getUserCode());
        userBasicFace.setUserName(basicInfo.getUserName());
        userBasicFace.setUserLabel(basicInfo.getUserLabel());
        userBasicFace.setIsLock(powerInfo.getIsLock());

        if (StringUtils.isNotBlank(basicInfo.getUserCode())) {
            User loginUser = SpringContextUtil.getLoginUser();
            userBasicFace.setCreateUser(loginUser.getUsername());
            userBasicFace.setLoginPassword(MD5Util.MD5(basicInfo.getLoginPassword()));
        }
        return userService.save(userBasicFace);
    }
}
