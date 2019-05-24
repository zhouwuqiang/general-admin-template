package com.java.general.config.security.service;

import com.java.business.menu.entity.MenuBasicFace;
import com.java.business.menu.mapper.MenuBasicFaceMapper;
import com.java.business.role.entity.RoleBasicFace;
import com.java.business.role.mapper.RoleBasicFaceMapper;
import com.java.business.user.entity.UserBasicFace;
import com.java.business.user.mapper.UserBasicFaceMapper;
import com.java.general.config.security.dto.Menu;
import com.java.general.config.security.dto.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/6 13:56
 */
@Service
public class CustomUserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserService.class);

    @Autowired
    private UserBasicFaceMapper userBasicFaceMapper;

    @Autowired
    private RoleBasicFaceMapper roleBasicFaceMapper;

    @Autowired
    private MenuBasicFaceMapper menuBasicFaceMapper;

    /**
     * 重写loadUserByUsername 方法获得 userdetails 类型用户
     *
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {


        User user = findByUserName(username);

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (RoleBasicFace role : user.getRoleList()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
        }
        user.setAuthorities(authorities);
        return user;
    }

    private User findByUserName(String username) {
        User result = new User();
        UserBasicFace userBasicFace = new UserBasicFace();
        userBasicFace.setUserName(username);
        userBasicFace = userBasicFaceMapper.selectOne(userBasicFace);

        if (userBasicFace == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }

        result.setUserCode(userBasicFace.getUserCode());
        result.setUsername(userBasicFace.getUserName());
        result.setUserLabel(userBasicFace.getUserLabel());
        result.setPassword(userBasicFace.getLoginPassword());


        List<RoleBasicFace> roleList = roleBasicFaceMapper.selectUserRole(userBasicFace);
        if (roleList == null || roleList.isEmpty()) {
            return result;
        }
        result.setRoleList(roleList);

        List<MenuBasicFace> menuList = menuBasicFaceMapper.selectUserMenu(roleList);
        if (menuList == null || menuList.isEmpty()) {
            return result;
        }

        result.setMenus(buildMenu(menuList));

        return result;
    }

    /**
     * 构建菜单
     * @param menuList
     * @return
     */
    private List<Menu> buildMenu(List<MenuBasicFace> menuList) {
        List<Menu> result = new ArrayList<>();

        for (MenuBasicFace item : menuList) {
            if (StringUtils.isBlank(item.getParentMenuCode())) {
                Menu menu = new Menu();
                BeanUtils.copyProperties(item, menu);
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
