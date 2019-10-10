package com.java.general.request.dto;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "每页大小",example = "10",required = false)
    private int pageSize;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码",example = "1",required = false)
    private int pageNum;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段",example = "name",required = false)
    private String sort;

    /**
     * 排序方式
     */
    @ApiModelProperty(value = "排序方式",example = "desc",required = false)
    private String order;

}
