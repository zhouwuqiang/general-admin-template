package com.java.business.wordbook.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserSaveRequestDto;
import com.java.business.wordbook.dto.WordbookAttributeSaveRequestDto;
import com.java.business.wordbook.dto.WordbookAttributeTableRequestDto;
import com.java.business.wordbook.dto.WordbookSaveRequestDto;
import com.java.business.wordbook.dto.WordbookTableRequestDto;
import com.java.business.wordbook.facade.WordbookFacade;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/27 16:55
 */
@Service
public class WordbookFacadeImpl implements WordbookFacade {

    @Override
    public PageInfo queryTable(WordbookTableRequestDto requestDto) {
        return null;
    }

    @Override
    public void save(WordbookSaveRequestDto requestDto) {

    }

    @Override
    public void delete(UserSaveRequestDto requestDto) {

    }

    @Override
    public PageInfo queryAttributeTable(WordbookAttributeTableRequestDto requestDto) {
        return null;
    }

    @Override
    public void deleteAttribute(WordbookAttributeSaveRequestDto requestDto) {

    }
}
