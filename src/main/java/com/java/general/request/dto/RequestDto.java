package com.java.general.request.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/19 18:56
 */
@Getter
@Setter
@ToString
public class RequestDto<T> {

    /**
     * 签名
     */
    private String sign;

    /**
     * 请求时间戳
     */
    private Long timestamp;

    /**
     * 请求参数
     */
    private T data;

}
