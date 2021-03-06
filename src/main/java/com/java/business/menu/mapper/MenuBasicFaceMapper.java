package com.java.business.menu.mapper;

import com.java.business.menu.entity.MenuBasicFace;
import com.java.business.organization.entity.OrganizationBasicFace;
import com.java.business.role.entity.RoleBasicFace;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MenuBasicFaceMapper extends Mapper<MenuBasicFace> {

    /**
     * 根据角色集合查询菜单集合
     * @param roleList
     * @return
     */
    List<MenuBasicFace> selectUserMenu(List<RoleBasicFace> roleList);

    /**
     * 根据组织查询用户菜单集合
     * @param organizationList
     * @return
     */
    List<MenuBasicFace> selectUserMenuByOrganization(List<OrganizationBasicFace> organizationList);
}
