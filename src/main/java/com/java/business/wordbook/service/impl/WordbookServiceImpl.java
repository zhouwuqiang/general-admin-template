package com.java.business.wordbook.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.wordbook.dto.WordbookAttributeTableRequestDto;
import com.java.business.wordbook.dto.WordbookTableRequestDto;
import com.java.business.wordbook.entity.Wordbook;
import com.java.business.wordbook.entity.WordbookAttribute;
import com.java.business.wordbook.mapper.WordbookAttributeMapper;
import com.java.business.wordbook.mapper.WordbookMapper;
import com.java.business.wordbook.service.WordbookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/27 16:56
 */
@Service
public class WordbookServiceImpl implements WordbookService {

    @Autowired
    private WordbookMapper wordbookMapper;

    @Autowired
    private WordbookAttributeMapper wordbookAttributeMapper;

    @Override
    public PageInfo queryTable(WordbookTableRequestDto requestDto) {

        Example example = new Example(Wordbook.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteFlag", "00");
        criteria.andEqualTo("attributeCode",requestDto.getAttributeCode());
        criteria.andEqualTo("attributeType",requestDto.getAttributeType());

        if (StringUtils.isNotBlank(requestDto.getAttributeName())) {
            criteria.andCondition("ATTRIBUTE_NAME like", "%" + requestDto.getAttributeName() + "%");
        }

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<Wordbook> queryList = wordbookMapper.selectByExample(example);

        return  new PageInfo<>(queryList);
    }

    @Override
    public PageInfo queryAttributeTable(WordbookAttributeTableRequestDto requestDto) {
        Example example = new Example(WordbookAttribute.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteFlag", "00");
        criteria.andEqualTo("attributeCode",requestDto.getAttributeCode());
        criteria.andEqualTo("attributeValue",requestDto.getAttributeValue());

        if (StringUtils.isNotBlank(requestDto.getAttributeName())) {
            criteria.andCondition("ATTRIBUTE_NAME like", "%" + requestDto.getAttributeName() + "%");
        }

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<WordbookAttribute> queryList = wordbookAttributeMapper.selectByExample(example);

        return  new PageInfo<>(queryList);
    }

    @Override
    public List<WordbookAttribute> querySelect(String code) {
        WordbookAttribute wordbook=new WordbookAttribute();
        wordbook.setAttributeCode(code);
        return wordbookAttributeMapper.select(wordbook);
    }
}
