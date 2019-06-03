package com.java.business.wordbook.service;

import com.github.pagehelper.PageInfo;
import com.java.business.wordbook.dto.WordbookAttributeTableRequestDto;
import com.java.business.wordbook.dto.WordbookSaveRequestDto;
import com.java.business.wordbook.dto.WordbookTableRequestDto;
import com.java.business.wordbook.entity.Wordbook;
import com.java.business.wordbook.entity.WordbookAttribute;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/27 16:56
 */
public interface WordbookService {

    /**
     * 列表查询
     * @param requestDto
     * @return
     */
    PageInfo queryTable(WordbookTableRequestDto requestDto);

    /**
     * 列表详情查询
     * @param requestDto
     * @return
     */
    PageInfo queryAttributeTable(WordbookAttributeTableRequestDto requestDto);

    /**
     * select 枚举查询
     * @param code
     * @return
     */
    List<WordbookAttribute> querySelect(String code);

    /**
     * 保存字典
     * @param requestDto
     */
    void save(WordbookSaveRequestDto requestDto);
}
