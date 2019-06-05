package com.java.business.user.dto;

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
public class UserBasicInfoRequestDto {

    private Integer id;

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空!",groups = {UserBasicInfoRequestDto.Delete.class})
    private String userCode;

    /**
     * 用户账号
     */
    @NotNull(message = "用户账号不能为空!",groups = {UserBasicInfoRequestDto.Insert.class, UserBasicInfoRequestDto.Update.class})
    private String userName;

    /**
     * 用户中文名
     */
    @NotNull(message = "用户中文名不能为空!",groups = {UserBasicInfoRequestDto.Insert.class, UserBasicInfoRequestDto.Update.class})
    private String userLabel;

    /**
     * 是否锁定
     */
    @NotNull(message = "用户状态不能为空!",groups = {UserBasicInfoRequestDto.Insert.class, UserBasicInfoRequestDto.Update.class})
    private String isLock;

    /**
     * 密码
     */
    @NotNull(message = "用户密码不能为空!",groups = {UserBasicInfoRequestDto.Insert.class, UserBasicInfoRequestDto.Update.class})
    private String loginPassword;

    /**
     * 删除数据
     */
    @NotNull(message = "删除状态不能为空!",groups = {UserBasicInfoRequestDto.Delete.class})
    private String deleteFlag;

    public interface Table{}

    public interface Insert{}

    public interface Update{}

    public interface Delete{}

    public interface Set{}
}
