package com.java.business.wordbook.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserSaveRequestDto;
import com.java.business.wordbook.dto.WordbookAttributeSaveRequestDto;
import com.java.business.wordbook.dto.WordbookAttributeTableRequestDto;
import com.java.business.wordbook.dto.WordbookSaveRequestDto;
import com.java.business.wordbook.dto.WordbookTableRequestDto;
import com.java.business.wordbook.entity.Wordbook;
import com.java.business.wordbook.entity.WordbookAttribute;
import com.java.business.wordbook.facade.WordbookFacade;
import com.java.business.wordbook.service.WordbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/27 16:55
 */
@Service
public class WordbookFacadeImpl implements WordbookFacade {


    @Autowired
    private WordbookService wordbookService;

    @Override
    public PageInfo queryTable(WordbookTableRequestDto requestDto) {

        return wordbookService.queryTable(requestDto);
    }

    @Override
    public void save(WordbookSaveRequestDto requestDto) {

    }

    @Override
    public void delete(UserSaveRequestDto requestDto) {

    }

    @Override
    public PageInfo queryAttributeTable(WordbookAttributeTableRequestDto requestDto) {
        return wordbookService.queryAttributeTable(requestDto);
    }

    @Override
    public void deleteAttribute(WordbookAttributeSaveRequestDto requestDto) {

    }

    @Override
    public List<WordbookAttribute> querySelect(String code) {
        return wordbookService.querySelect(code);
    }
}
