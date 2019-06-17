package com.java.general.config.security.dto;

import com.java.business.role.entity.RoleBasicFace;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/6 14:10
 */
@Getter
@Setter
public class User implements UserDetails {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户角色
     */
    private Set<GrantedAuthority> authorities;

    /**
     * 账号未过期
     */
    private boolean accountNonExpired = true;

    /**
     * 账号非锁定
     */
    private boolean accountNonLocked = true;

    /**
     * 凭证未过期
     */
    private boolean credentialsNonExpired = true;

    /**
     * 启用
     */
    private boolean enabled = true;


    /********************************************** 自定义携带数据字段 ***************************************/


    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 用户中文名
     */
    private String userLabel;


    /**
     * 用户角色集合
     */
    List<RoleBasicFace> roleList = new ArrayList<>();

    /**
     * 用户菜单集已整理
     */
    List<Menu> menus = new ArrayList<>();

    /**
     * 用户菜单地址集合
     */
    Set<String> urls = new HashSet<>();


    @Override
    public String toString() {
        return userCode;
    }

    @Override
    public int hashCode() {
        return userCode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
