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
    USER_ERROR(0,"用户不存在!",false),

    /**
     * 密码不正确
     */
    PASSWORD_ERROR(0,"旧密码输入不正确!",false),


    /**
     * 修改密码相同
     */
    PASSWORD_SAME(0,"新密码不能和原密码相同!",false),


    ;

    UserResponseEnum(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    private Integer code;

    private String message;

    private boolean success;

    @Override
    public Integer getCode() {
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
