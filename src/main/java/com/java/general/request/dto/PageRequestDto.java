package com.java.general.request.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author alger
 * @version : 1.0.0
 * @description 数据请求DTO 所有请求传输对象继承该类
 * @date 2018/12/17 19:10
 */
@Getter
@Setter
@ToString
public class PageRequestDto {

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     * 当前页码
     */
    private int pageNum;

}
