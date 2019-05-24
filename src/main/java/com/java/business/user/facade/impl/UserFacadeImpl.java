package com.java.business.user.facade.impl;

import com.github.pagehelper.PageInfo;
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

        UserBasicFace userBasicFace = new UserBasicFace();

        userBasicFace.setUserCode(requestDto.getUserCode());
        userBasicFace.setUserName(requestDto.getUserName());
        userBasicFace.setUserLabel(requestDto.getUserLabel());
        userBasicFace.setIsLock(requestDto.getIsLock());

        if (StringUtils.isNotBlank(requestDto.getUserCode())){
            User loginUser = SpringContextUtil.getLoginUser();
            userBasicFace.setCreateUser(loginUser.getUsername());
            userBasicFace.setLoginPassword(MD5Util.MD5(requestDto.getLoginPassword()));
        }


        if (StringUtils.isNotBlank(requestDto.getDeleteFlag())){
            userBasicFace.setDeleteFlag(requestDto.getDeleteFlag());
        }
        userService.save(userBasicFace);
    }
}
