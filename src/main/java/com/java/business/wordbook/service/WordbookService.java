package com.java.business.wordbook.service;

import com.github.pagehelper.PageInfo;
import com.java.business.wordbook.dto.WordbookAttributeTableRequestDto;
import com.java.business.wordbook.dto.WordbookTableRequestDto;

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
}
