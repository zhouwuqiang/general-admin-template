package com.java.business.role.dto;

import com.java.general.request.dto.PageRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 20:08
 */
@Getter
@Setter
@ToString
public class RoleTableRequestDto extends PageRequestDto {

    private Integer id;

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色备注
     */
    private String roleMemo;
}
