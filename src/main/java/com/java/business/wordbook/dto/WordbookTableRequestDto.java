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
     * 字典编码
     */
    private String wordbookCode;

    /**
     * 字典名称
     */
    private String wordbookName;

    /**
     * 字典状态(00-正常 01-禁用)
     */
    private String wordbookStatus;

}
