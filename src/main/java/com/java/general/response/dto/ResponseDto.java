package com.java.general.response.dto;

/**
 * @description 封装请求返回对象
 * @author alger
 * @date 2018/12/19 16:57
 * @version 1.0.0
 */
public class ResponseDto <T> {

    /**
     * 响应状态
     */
    private boolean success;

    /**
     * 响应编号
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
