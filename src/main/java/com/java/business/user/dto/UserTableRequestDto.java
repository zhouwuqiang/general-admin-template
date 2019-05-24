package com.java.business.user.dto;

import com.java.general.request.dto.PageRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/14 17:24
 */
@Getter
@Setter
@ToString
public class UserTableRequestDto extends PageRequestDto {

    /**
     * 用户账号
     */
    @NotNull(message = "用户账号不能为空!",groups = {UserTableRequestDto.Insert.class,UserTableRequestDto.Update.class})
    private String userName;

    /**
     * 用户中文名
     */
    @NotNull(message = "用户中文名不能为空!",groups = {UserTableRequestDto.Insert.class,UserTableRequestDto.Update.class})
    private String userLabel;




    public interface Table{}

    public interface Insert{}

    public interface Update{}

    public interface Delete{}

    public interface Set{}
}
