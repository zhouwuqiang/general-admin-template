package com.java.general.config.security.utils;

import com.java.business.menu.entity.MenuBasicFace;
import com.java.general.config.security.dto.Menu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/5 0:44
 */
public class MenuUtils {

    /**
     * 构建菜单
     * @param menuList
     * @return
     */
    public static List<Menu> buildMenu(List<MenuBasicFace> menuList) {
        List<Menu> result = new ArrayList<>();

        for (MenuBasicFace item : menuList) {
            if (StringUtils.isBlank(item.getParentMenuCode())) {
                Menu menu = new Menu();
                BeanUtils.copyProperties(item, menu);
                menu.setText(item.getMenuName());
                menu.setRoot(true);
                result.add(menu);
            }
        }

        for (Menu menu : result) {
            menu.addChild(menuList);
        }

        return result;
    }
}
