package com.java.business.wordbook.dto;

import com.java.general.request.dto.PageRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/27 19:40
 */
@Setter
@Getter
@ToString
public class WordbookTableRequestDto extends PageRequestDto {

    /**
     * 属性编码
     */
    private String attributeCode;

    /**
     * 属性名称
     */
    private String attributeName;

    /**
     * 属性类型(00-枚举,01-开关)
     */
    private String attributeType;

}
