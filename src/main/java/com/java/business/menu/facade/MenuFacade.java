package com.java.business.menu.facade;

import com.github.pagehelper.PageInfo;
import com.java.business.menu.dto.MenuListRequestDto;
import com.java.business.menu.dto.MenuSaveRequestDto;
import com.java.business.menu.dto.MenuTableRequestDto;
import com.java.business.utils.tree.dto.Tree;
import com.java.general.config.security.dto.Menu;

import java.util.List;

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

    /**
     * 保存菜单
     * @param requestDto
     */
    void save(MenuSaveRequestDto requestDto);

    /**
     * 删除菜单
     * @param requestDto
     */
    void delete(MenuSaveRequestDto requestDto);

    /**
     * 查询菜单树
     * @param menuListRequestDto
     * @return
     */
    List<Menu> queryListTree(MenuListRequestDto menuListRequestDto);
}
