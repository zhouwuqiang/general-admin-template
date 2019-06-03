package com.java.business.wordbook.dto;

import com.java.general.request.dto.PageRequestDto;
import lombok.Data;
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
public class WordbookAttributeTableRequestDto extends PageRequestDto {
    /**
     * 字典编码
     */
    private String wordbookCode;

    /**
     * 属性值
     */
    private String attributeValue;

    /**
     * 属性名称
     */
    private String attributeName;


}
