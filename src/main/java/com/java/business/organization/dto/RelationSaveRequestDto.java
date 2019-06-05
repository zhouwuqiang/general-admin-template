package com.java.business.organization.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/5 21:10
 */
@Getter
@Setter
@ToString
public class RelationSaveRequestDto {


    /**
     * 组织编号
     */
    private String organizationCode;

    /**
     * 菜单list集合
     */
    private List<String> menuCodeList;

}
