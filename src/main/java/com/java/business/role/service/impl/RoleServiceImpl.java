package com.java.business.role.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.role.dto.RoleTableRequestDto;
import com.java.business.role.entity.RoleBasicFace;
import com.java.business.role.mapper.RoleBasicFaceMapper;
import com.java.business.role.service.RoleService;
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
 * @date 2019/6/4 20:11
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleBasicFaceMapper roleBasicFaceMapper;

    @Override
    public PageInfo queryTable(RoleTableRequestDto requestDto) {

        Example example = new Example(RoleBasicFace.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteFlag", "00");
        criteria.andEqualTo("roleCode", requestDto.getRoleCode());
        if (StringUtils.isNotBlank(requestDto.getRoleName())) {
            criteria.andCondition("role_name like", "%" + requestDto.getRoleName() + "%");
        }

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<RoleBasicFace> queryList = roleBasicFaceMapper.selectByExample(example);
        return new PageInfo<>(queryList);
    }

    @Override
    public RoleBasicFace save(RoleBasicFace requestDto) {
        if (requestDto.getId() == null){
            requestDto.setRoleCode(UuidCodeWorker.nextCode("ROLE"));
            roleBasicFaceMapper.insertSelective(requestDto);
        }else{
            roleBasicFaceMapper.updateByPrimaryKeySelective(requestDto);
        }
        return requestDto;
    }
}
