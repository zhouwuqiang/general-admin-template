package com.java.business.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.entity.UserBasicFace;
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
 * @date 2019/3/14 17:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBasicFaceMapper userBasicFaceMapper;

    @Override
    public PageInfo queryTable(UserTableRequestDto requestDto) {
        Example example = new Example(UserBasicFace.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", requestDto.getUserName());
        if (StringUtils.isNotBlank(requestDto.getUserName())) {
            criteria.andCondition("USER_LABEL like", requestDto.getUserName() + "%");
        }

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<UserBasicFace> queryList = userBasicFaceMapper.selectByExample(example);
        PageInfo<UserBasicFace> pageInfo = new PageInfo<>(queryList);

        return pageInfo;
    }
}
