package com.java.business.organization.dto;

import com.java.general.request.dto.PageRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 1:04
 */
@Getter
@Setter
@ToString
public class OrganizationTableRequestDto extends PageRequestDto {

    /**
     * 编号
     */
    private String organizationCode;

    /**
     * 名称
     */
    private String organizationName;

    /**
     * 上级编号
     */
    private String parenCode;
}
