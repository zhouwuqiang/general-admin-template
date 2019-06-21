package com.java.business.user.dto;

import com.java.general.response.code.ResponseCode;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/7 0:22
 */
public enum UserResponseEnum implements ResponseCode {

    /**
     * 用户不存在
     */
    USER_ERROR("010001","用户不存在!",false),

    /**
     * 密码不正确
     */
    PASSWORD_ERROR("010002","旧密码输入不正确!",false),


    /**
     * 修改密码相同
     */
    PASSWORD_SAME("010003","新密码不能和原密码相同!",false),

    /**
     * 用户名已存在
     */
    USER_NAME_EXIST("010003","用户名已存在!",false),

    /**
     * 用户名不存在
     */
    USER_NAME_NOT_EXIST("010005","用户名不存在!",true),

    ;

    UserResponseEnum(String code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    private String code;

    private String message;

    private boolean success;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }
}
