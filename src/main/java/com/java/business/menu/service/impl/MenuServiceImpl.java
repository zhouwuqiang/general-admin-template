package com.java.business.menu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.menu.dto.MenuListRequestDto;
import com.java.business.menu.dto.MenuTableRequestDto;
import com.java.business.menu.entity.MenuBasicFace;
import com.java.business.menu.mapper.MenuBasicFaceMapper;
import com.java.business.menu.service.MenuService;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.entity.UserBasicFace;
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
 * @date 2019/5/25 14:28
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuBasicFaceMapper menuBasicFaceMapper;

    @Override
    public PageInfo queryTable(MenuTableRequestDto requestDto) {

        Example example = new Example(MenuBasicFace.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteFlag", "00");
        if (StringUtils.isNotBlank(requestDto.getMenuName())) {
            criteria.andCondition("MENU_NAME like", "%" + requestDto.getMenuName() + "%");
        }

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<MenuBasicFace> queryList = menuBasicFaceMapper.selectByExample(example);
        PageInfo<MenuBasicFace> pageInfo = new PageInfo<>(queryList);

        return pageInfo;
    }



    @Override
    public MenuBasicFace save(MenuBasicFace menuBasicFace) {
        if (menuBasicFace.getId() == null){
            menuBasicFace.setMenuCode(UuidCodeWorker.nextCode("MENU"));
            menuBasicFaceMapper.insertSelective(menuBasicFace);
        }else{
            menuBasicFaceMapper.updateByPrimaryKeySelective(menuBasicFace);
        }
        return menuBasicFace;
    }

    @Override
    public List<MenuBasicFace> queryList() {
        Example example = new Example(MenuBasicFace.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteFlag", "00");

        return menuBasicFaceMapper.selectByExample(example);
    }


}
