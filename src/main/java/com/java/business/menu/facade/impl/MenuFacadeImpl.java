package com.java.business.menu.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.menu.dto.MenuListRequestDto;
import com.java.business.menu.dto.MenuSaveRequestDto;
import com.java.business.menu.dto.MenuTableRequestDto;
import com.java.business.menu.entity.MenuBasicFace;
import com.java.business.menu.facade.MenuFacade;
import com.java.business.menu.service.MenuService;
import com.java.business.utils.tree.dto.Tree;
import com.java.general.config.security.dto.Menu;
import com.java.general.config.security.utils.MenuUtils;
import com.java.general.constant.SystemCommonConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/24 18:23
 */
@Service
public class MenuFacadeImpl implements MenuFacade {

    @Autowired
    private MenuService menuService;

    @Override
    public PageInfo queryTable(MenuTableRequestDto requestDto) {
        return menuService.queryTable(requestDto);
    }


    @Override
    public void save(MenuSaveRequestDto requestDto) {
        MenuBasicFace menuBasicFace = new MenuBasicFace();
        BeanUtils.copyProperties(requestDto, menuBasicFace);
        menuService.save(menuBasicFace);
    }

    @Override
    public void delete(MenuSaveRequestDto requestDto) {
        MenuBasicFace menuBasicFace = new MenuBasicFace();
        menuBasicFace.setId(requestDto.getId());
        menuBasicFace.setDeleteFlag(SystemCommonConstant.DeleteFlag.DELETE);
        menuService.save(menuBasicFace);
    }

    @Override
    public List<Tree> queryListTree(MenuListRequestDto menuListRequestDto) {

        List<MenuBasicFace> menuBasicFaces = menuService.queryList(menuListRequestDto);

        List<Menu> menuList = MenuUtils.buildMenu(menuBasicFaces);

        return convent(menuList);
    }

    private List<Tree> convent(List<Menu> menuList) {

        List<Tree> result= new ArrayList<>();

        for (Menu item : menuList) {
            Tree tree = new Tree();
            tree.setText(item.getMenuName());
            tree.setCode(item.getMenuCode());
            tree.setChecked();
            if (item.isHasChild()){
                tree.setNodes(convent(item.getChildMenus()));
            }
            result.add(tree);
        }

        return result;
    }
}
