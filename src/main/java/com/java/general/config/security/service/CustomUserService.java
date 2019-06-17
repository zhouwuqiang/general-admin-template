package com.java.general.config.security.service;

import com.java.business.menu.entity.MenuBasicFace;
import com.java.business.menu.mapper.MenuBasicFaceMapper;
import com.java.business.organization.entity.OrganizationBasicFace;
import com.java.business.organization.mapper.OrganizationBasicFaceMapper;
import com.java.business.role.entity.RoleBasicFace;
import com.java.business.role.mapper.RoleBasicFaceMapper;
import com.java.business.user.entity.UserBasicFace;
import com.java.business.user.mapper.UserBasicFaceMapper;
import com.java.general.config.security.dto.User;
import com.java.general.config.security.utils.MenuUtils;
import com.java.general.constant.SystemCommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private OrganizationBasicFaceMapper organizationBasicFaceMapper;

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
        userBasicFace.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
        userBasicFace = userBasicFaceMapper.selectOne(userBasicFace);

        if (userBasicFace == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }

        result.setUserCode(userBasicFace.getUserCode());
        result.setUsername(userBasicFace.getUserName());
        result.setUserLabel(userBasicFace.getUserLabel());
        result.setPassword(userBasicFace.getLoginPassword());


        List<MenuBasicFace> menuList = new ArrayList<>();

        //查询角色菜单
        List<RoleBasicFace> roleList = roleBasicFaceMapper.selectUserRole(userBasicFace);
        if (!(roleList == null || roleList.isEmpty())) {
            result.setRoleList(roleList);
            List<MenuBasicFace> roleMenuList = menuBasicFaceMapper.selectUserMenu(roleList);
            if (roleMenuList != null) {
                addMenu(menuList, roleMenuList);
            }
        }

        //查询组织菜单
        List<OrganizationBasicFace> organizationList = organizationBasicFaceMapper.selectUserOrganization(userBasicFace);
        if (!(organizationList == null || organizationList.isEmpty())) {
            List<MenuBasicFace> organizationMenuList = menuBasicFaceMapper.selectUserMenuByOrganization(organizationList);
            if (organizationMenuList != null) {
                addMenu(menuList, organizationMenuList);
            }
        }


        if (menuList.isEmpty()) {
            return result;
        }


        //权限地址结合
        result.setUrls(menuList.stream().map(MenuBasicFace::getMenuAction).collect(Collectors.toSet()));

        //构建菜单
        result.setMenus(MenuUtils.buildMenu(menuList));

        return result;
    }

    /**
     * 合并菜单
     *
     * @param target
     * @param menuList
     * @return
     */
    private void addMenu(List<MenuBasicFace> target, List<MenuBasicFace> menuList) {
        Set<String> menuCodeSet = target.stream().map(MenuBasicFace::getMenuCode).collect(Collectors.toSet());
        for (MenuBasicFace item : menuList){
            if (menuCodeSet.contains(item.getMenuCode())){
                continue;
            }
            target.add(item);
        }
    }
}
