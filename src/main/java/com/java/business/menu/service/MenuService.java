package com.java.business.menu.service;

import com.github.pagehelper.PageInfo;
import com.java.business.menu.dto.MenuTableRequestDto;
import com.java.business.menu.entity.MenuBasicFace;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/25 14:27
 */
public interface MenuService {

    /**
     * 主列表查询
     * @param requestDto
     * @return
     */
    PageInfo queryTable(MenuTableRequestDto requestDto);

    /**
     * 保存菜单
     * @param menuBasicFace
     */
    MenuBasicFace save(MenuBasicFace menuBasicFace);
}
