package com.java.business.menu.dto;

import com.java.general.request.dto.PageRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/24 18:27
 */
@Getter
@Setter
@ToString
@ApiModel("菜单列表查询对象")
public class MenuTableRequestDto extends PageRequestDto {

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称",example = "菜单管理",required = false)
    private String menuName;
}
