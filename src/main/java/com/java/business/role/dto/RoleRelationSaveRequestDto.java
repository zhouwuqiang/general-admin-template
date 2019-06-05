package com.java.business.role.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 20:10
 */
@Getter
@Setter
@ToString
public class RoleRelationSaveRequestDto {

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 菜单list集合
     */
    private List<String> menuCodeList;
}
