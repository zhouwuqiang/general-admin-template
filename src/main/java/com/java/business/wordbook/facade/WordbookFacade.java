package com.java.business.wordbook.facade;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserSaveRequestDto;
import com.java.business.wordbook.dto.WordbookAttributeSaveRequestDto;
import com.java.business.wordbook.dto.WordbookAttributeTableRequestDto;
import com.java.business.wordbook.dto.WordbookSaveRequestDto;
import com.java.business.wordbook.dto.WordbookTableRequestDto;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/27 16:55
 */
public interface WordbookFacade {

    /**
     * 查询列表
     * @param requestDto
     * @return
     */
    PageInfo queryTable(WordbookTableRequestDto requestDto);

    /**
     * 保存字典
     * @param requestDto
     */
    void save(WordbookSaveRequestDto requestDto);

    /**
     * 删除字典
     * @param requestDto
     */
    void delete(UserSaveRequestDto requestDto);

    /**
     * 查询属性列表
     * @param requestDto
     * @return
     */
    PageInfo queryAttributeTable(WordbookAttributeTableRequestDto requestDto);

    /**
     * 删除属性
     * @param requestDto
     */
    void deleteAttribute(WordbookAttributeSaveRequestDto requestDto);
}
