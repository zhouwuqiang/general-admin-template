package com.java.business.log.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.log.dto.LoginLogTableRequestDto;
import com.java.business.log.entity.UserLoginLog;
import com.java.business.log.mapper.UserLoginLogMapper;
import com.java.business.log.service.LoginLogService;
import com.java.business.menu.entity.MenuBasicFace;
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
 * @date 2019/6/21 18:22
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Override
    public PageInfo queryTable(LoginLogTableRequestDto requestDto) {
        Example example = new Example(UserLoginLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", requestDto.getUserName());

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<UserLoginLog> queryList = userLoginLogMapper.selectByExample(example);

        return new PageInfo<>(queryList);

    }
}
