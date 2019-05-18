package com.java.business.user.facade.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.entity.UserBasicFace;
import com.java.business.user.facade.UserFacade;
import com.java.business.user.mapper.UserBasicFaceMapper;
import com.java.business.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

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
        PageInfo result=userService.queryTable(requestDto);

        return result;
    }
}
