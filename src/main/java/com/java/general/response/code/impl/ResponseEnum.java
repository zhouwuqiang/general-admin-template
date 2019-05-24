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
    SUCCESS(0,"处理成功!",true),

    /**
     * 已接收 后台处理中
     */
    RECEIVED(100000,"已接收!",true),

    /**
     * 参数校验失败
     */
    PARAM_ERROR(-2,"参数异常!",false),

    /**
     * 处理失败
     */
    FAIL(1,"处理失败!",false)

    ;

    ResponseEnum(Integer code, String message, boolean success) {
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
