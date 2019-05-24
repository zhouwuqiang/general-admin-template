package com.java.business.menu.dto;

import com.java.business.user.dto.UserTableRequestDto;
import com.java.general.request.dto.PageRequestDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/24 18:27
 */
@Data
public class MenuTableRequestDto extends PageRequestDto {

    /**
     * 用户账号
     */
    @NotNull(message = "用户账号不能为空!")
    private String userName;

}
