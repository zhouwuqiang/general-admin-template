package com.java.general.config.security.dto;

import com.java.business.menu.entity.MenuBasicFace;
import com.java.business.utils.tree.dto.State;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/7 14:28
 */
@Getter
@Setter
public class Menu {

    private String text;

    /**
     * 是否有子菜单
     */
    private boolean hasChild = false;

    /**
     * 是否是顶级菜单
     */
    private boolean isRoot = false;

    /**
     * 菜单编号
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单地址
     */
    private String menuAction;

    /**
     * 上级菜单编号
     */
    private String parentMenuCode;

    /**
     * 菜单类型(00-目录 01-菜单 02-按钮)
     */
    private String menuType;

    /**
     * 显示顺序
     */
    private Integer displayIndex;

    /**
     * 子对象
     */
    private List<Menu> nodes;

    /**
     * 一个节点的初始状态。
     */
    private State state = new State();

    /**
     * 添加子菜单
     *
     * @param menuList
     */
    public void addChild(List<MenuBasicFace> menuList) {
        for (MenuBasicFace item : menuList) {
            if (StringUtils.equals(item.getParentMenuCode(), this.menuCode)) {

                if (!this.hasChild) {
                    this.hasChild = true;
                    this.nodes = new ArrayList<>();
                }

                Menu menu = new Menu();
                BeanUtils.copyProperties(item, menu);
                menu.setText(item.getMenuName());
                this.nodes.add(menu);
            }
        }
        if (this.hasChild && nodes.size() > 0) {
            for (Menu childMenu : nodes) {
                childMenu.addChild(menuList);
            }
        }
    }

    public void setChecked() {
        this.state.setChecked(true);
    }

    public void setExpanded() {
        this.state.setExpanded(true);
    }
}
