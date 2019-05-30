package com.java.business.menu.dto;

import com.java.general.request.dto.PageRequestDto;
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
public class MenuTableRequestDto extends PageRequestDto {

    /**
     * 菜单名称
     */
    private String menuName;
}
