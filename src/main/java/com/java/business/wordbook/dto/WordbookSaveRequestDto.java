package com.java.business.wordbook.dto;

import com.java.business.wordbook.entity.WordbookAttribute;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/27 19:40
 */
@Getter
@Setter
@ToString
public class WordbookSaveRequestDto {

    private Integer id;

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

    /**
     * 备注
     */
    private String memo;

    /**
     * 字典结合
     */
    private List<WordbookAttribute> attributeList;

}
