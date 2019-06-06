package com.java.business.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.entity.UserBasicFace;
import com.java.business.user.entity.UserOrganizationRelation;
import com.java.business.user.entity.UserRoleRelation;
import com.java.business.user.mapper.UserBasicFaceMapper;
import com.java.business.user.mapper.UserOrganizationRelationMapper;
import com.java.business.user.mapper.UserRoleRelationMapper;
import com.java.business.user.service.UserService;
import com.java.general.constant.SystemCommonConstant;
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

    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Autowired
    private UserOrganizationRelationMapper userOrganizationRelationMapper;

    @Override
    public PageInfo queryTable(UserTableRequestDto requestDto) {
        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<UserBasicFace> queryList = userBasicFaceMapper.selectUserList(requestDto);
        return new PageInfo<>(queryList);
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

    @Override
    public UserRoleRelation deleteRoleRelation(UserRoleRelation roleRelation) {
        UserRoleRelation deleteRelation = new UserRoleRelation();
        deleteRelation.setDeleteFlag(SystemCommonConstant.DeleteFlag.DELETE);
        Example example = new Example(UserRoleRelation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userCode", roleRelation.getUserCode());
        userRoleRelationMapper.updateByExampleSelective(deleteRelation,example);
        return roleRelation;
    }

    @Override
    public UserRoleRelation saveRoleRelation(UserRoleRelation roleRelation) {
        if (roleRelation.getId() == null){
            userRoleRelationMapper.insertSelective(roleRelation);
        }else{
            userRoleRelationMapper.updateByPrimaryKeySelective(roleRelation);
        }
        return roleRelation;
    }

    @Override
    public UserOrganizationRelation deleteOrganizationRelation(UserOrganizationRelation organizationRelation) {
        UserOrganizationRelation deleteRelation = new UserOrganizationRelation();
        deleteRelation.setDeleteFlag(SystemCommonConstant.DeleteFlag.DELETE);
        Example example = new Example(UserOrganizationRelation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userCode", organizationRelation.getUserCode());
        userOrganizationRelationMapper.updateByExampleSelective(deleteRelation,example);
        return organizationRelation;
    }

    @Override
    public UserOrganizationRelation saveOrganizationRelation(UserOrganizationRelation organizationRelation) {
        if (organizationRelation.getId() == null){
            userOrganizationRelationMapper.insertSelective(organizationRelation);
        }else{
            userOrganizationRelationMapper.updateByPrimaryKeySelective(organizationRelation);
        }
        return organizationRelation;
    }

    @Override
    public UserBasicFace queryBasicInfo(UserBasicFace userBasicFace) {
        userBasicFace.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
        return userBasicFaceMapper.selectOne(userBasicFace);
    }

    @Override
    public UserRoleRelation queryRoleRelation(UserRoleRelation roleRelation) {
        roleRelation.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
        return userRoleRelationMapper.selectOne(roleRelation);
    }

    @Override
    public UserOrganizationRelation queryOrganizationRelation(UserOrganizationRelation organizationRelation) {
        organizationRelation.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
        return userOrganizationRelationMapper.selectOne(organizationRelation);
    }
}
