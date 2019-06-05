package com.java.business.menu.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.menu.dto.MenuListRequestDto;
import com.java.business.menu.dto.MenuSaveRequestDto;
import com.java.business.menu.dto.MenuTableRequestDto;
import com.java.business.menu.entity.MenuBasicFace;
import com.java.business.menu.facade.MenuFacade;
import com.java.business.menu.service.MenuService;
import com.java.business.role.entity.RoleMenuRelation;
import com.java.business.role.service.RoleService;
import com.java.business.utils.tree.dto.Tree;
import com.java.general.config.security.dto.Menu;
import com.java.general.config.security.utils.MenuUtils;
import com.java.general.constant.SystemCommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Autowired
    private RoleService roleService;


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

        List<MenuBasicFace> allMenu = menuService.queryList();
        List<Menu> menuList = MenuUtils.buildMenu(allMenu);

        Set<String> selectedSet = getSelectedSet(menuListRequestDto.getRoleCode());

        return convent(menuList, selectedSet);
    }

    /**
     * 根据角色编号,查询角色拥有的菜单集合
     * @param roleCode
     * @return
     */
    private Set<String> getSelectedSet(String roleCode) {
        Set<String> selectedSet = new HashSet<>();
        if (StringUtils.isNotBlank(roleCode)) {
            RoleMenuRelation roleMenuRelation = new RoleMenuRelation();
            roleMenuRelation.setRoleCode(roleCode);
            roleMenuRelation.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
            List<RoleMenuRelation> selected = roleService.queryRelationList(roleMenuRelation);
            selectedSet = selected.stream().map(RoleMenuRelation::getMenuCode).collect(Collectors.toSet());
        }
        return selectedSet;
    }

    /**
     * 查单转换为页面tree对象
     * @param menuList
     * @param codeSet
     * @return
     */
    private List<Tree> convent(List<Menu> menuList, Set<String> codeSet) {

        List<Tree> result = new ArrayList<>();

        for (Menu item : menuList) {
            Tree tree = new Tree();
            tree.setText(item.getMenuName());
            tree.setCode(item.getMenuCode());
            if (codeSet.contains(item.getMenuCode())) {
                tree.setChecked();
            }
            if (item.isRoot()) {
                tree.setExpanded();
            }
            if (item.isHasChild()) {
                tree.setNodes(convent(item.getChildMenus(), codeSet));
            }
            result.add(tree);
        }

        return result;
    }
}
