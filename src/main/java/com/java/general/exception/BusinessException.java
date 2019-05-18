package com.java.general.exception;

/**
 * @author alger
 * @version : 1.0.0
 * @description 系统业务自定义异常父类.所有异常都需要继承该类.
 * @date 2018/12/17 19:11
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2332608236621015980L;

    /**
     * 异常编号
     */
    private String code;

    /**
     * 异常信息
     */
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super();
        this.message = message;
        this.code = "9999";
    }

    public BusinessException(String message,String code) {
        super();
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
