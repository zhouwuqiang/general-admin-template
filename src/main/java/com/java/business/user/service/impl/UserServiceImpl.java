package com.java.business.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.entity.UserBasicFace;
import com.java.business.user.mapper.UserBasicFaceMapper;
import com.java.business.user.service.UserService;
import com.java.general.exception.BusinessException;
import com.java.general.utils.UuidCodeWorker;
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
        criteria.andEqualTo("deleteFlag", "00");
        if (StringUtils.isNotBlank(requestDto.getUserName())) {
            criteria.andCondition("USER_LABEL like", requestDto.getUserName() + "%");
        }

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<UserBasicFace> queryList = userBasicFaceMapper.selectByExample(example);
        PageInfo<UserBasicFace> pageInfo = new PageInfo<>(queryList);

        return pageInfo;
    }

    @Override
    public UserBasicFace save(UserBasicFace userBasicFace) {
        int result;

        if (StringUtils.isBlank(userBasicFace.getUserCode())) {
            userBasicFace.setUserCode(UuidCodeWorker.nextCode("USER"));
            result = userBasicFaceMapper.insertSelective(userBasicFace);
        } else {
            Example example = new Example(UserBasicFace.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userCode", userBasicFace.getUserCode());
            result = userBasicFaceMapper.updateByExampleSelective(userBasicFace, example);
        }

        if (result == 1) {
            return userBasicFace;
        }

        throw new BusinessException("保存用户异常!" + JSON.toJSONString(userBasicFace));
    }
}
