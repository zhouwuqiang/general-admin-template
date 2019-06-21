package com.java.general.response.code.impl;


import com.java.general.response.code.ResponseCode;

/**
 * description  常用响应返回枚举
 * @author alger
 * @date 2018/12/19 17:11
 * @version 1.0.0
 */
public enum ResponseEnum implements ResponseCode {

    /**
     * 处理成功
     */
    SUCCESS("000000","处理成功!",true),

    /**
     * 已接收 后台处理中
     */
    RECEIVED("000001","已接收!",true),

    /**
     * 参数校验失败
     */
    PARAM_ERROR("000002","参数异常!",false),

    /**
     * 处理失败
     */
    FAIL("000003","处理失败!",false),


    /**
     * 登录超时
     */
    USER_SESSION_TIME_OUT("000004","用户登录超时!",false)
    ;

    ResponseEnum(String code, String message, boolean success) {
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
