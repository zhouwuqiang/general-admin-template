package com.java.business.menu.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.menu.dto.MenuListRequestDto;
import com.java.business.menu.dto.MenuSaveRequestDto;
import com.java.business.menu.dto.MenuTableRequestDto;
import com.java.business.menu.entity.MenuBasicFace;
import com.java.business.menu.facade.MenuFacade;
import com.java.business.menu.service.MenuService;
import com.java.business.organization.entity.OrganizationMenuRelation;
import com.java.business.organization.service.OrganizationService;
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

    @Autowired
    private OrganizationService organizationService;

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
    public List<Menu> queryListTree(MenuListRequestDto menuListRequestDto) {

        List<MenuBasicFace> allMenu = menuService.queryList();
        List<Menu> menuList = MenuUtils.buildMenu(allMenu);

        Set<String> selectedSet = roleSelectedSet(menuListRequestDto.getRoleCode());
        selectedSet.addAll(organizationSelectedSet(menuListRequestDto.getOrganizationCode()));

        return convent(menuList, selectedSet);
    }

    /**
     * 根据角色编号,查询角色拥有的菜单集合
     * @param roleCode
     * @return
     */
    private Set<String> roleSelectedSet(String roleCode) {
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
     * 根据组织编号,查询角色拥有的菜单集合
     * @param organizationCode
     * @return
     */
    private Set<String> organizationSelectedSet(String organizationCode) {
        Set<String> selectedSet = new HashSet<>();
        if (StringUtils.isNotBlank(organizationCode)) {
            OrganizationMenuRelation relation = new OrganizationMenuRelation();
            relation.setOrganizationCode(organizationCode);
            relation.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
            List<OrganizationMenuRelation> selected = organizationService.queryRelationList(relation);
            selectedSet = selected.stream().map(OrganizationMenuRelation::getMenuCode).collect(Collectors.toSet());
        }
        return selectedSet;
    }

    /**
     * 查单转换为页面tree对象
     * @param menuList
     * @param codeSet
     * @return
     */
    private List<Menu> convent(List<Menu> menuList, Set<String> codeSet) {


        for (Menu item : menuList) {
            if (codeSet.contains(item.getMenuCode())) {
                item.setChecked();
            }
            if (item.isRoot()) {
                item.setExpanded();
            }
        }

        return menuList;
    }
}
