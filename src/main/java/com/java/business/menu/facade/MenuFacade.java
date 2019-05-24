package com.java.business.menu.facade;

import com.github.pagehelper.PageInfo;
import com.java.business.menu.dto.MenuTableRequestDto;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/24 18:23
 */
public interface MenuFacade {

    /**
     * 查询菜单列表
     * @param requestDto
     * @return
     */
    PageInfo queryTable(MenuTableRequestDto requestDto);
}
