package com.java.general.response.code;

/**
 * description :
 *      定义responseCode 格式规范
 *      在自己模块中新增返回值,新增enum 返回 继承该接口
 *      6位返回码  00 使用范围  0000 内容编号
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/1/4 10:51
 */
public interface ResponseCode {

    /**
     * 编码
     * @return
     */
    String getCode();

    /**
     * 返回消息
     * @return
     */
    String getMessage();

    /**
     * 是否成功
     * @return
     */
    boolean isSuccess();

}
