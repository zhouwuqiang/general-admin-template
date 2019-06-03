package com.java.business.wordbook.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.wordbook.dto.WordbookAttributeTableRequestDto;
import com.java.business.wordbook.dto.WordbookSaveRequestDto;
import com.java.business.wordbook.dto.WordbookTableRequestDto;
import com.java.business.wordbook.entity.Wordbook;
import com.java.business.wordbook.entity.WordbookAttribute;
import com.java.business.wordbook.mapper.WordbookAttributeMapper;
import com.java.business.wordbook.mapper.WordbookMapper;
import com.java.business.wordbook.service.WordbookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
        criteria.andEqualTo("wordbookCode", requestDto.getWordbookCode());

        if (StringUtils.isNotBlank(requestDto.getWordbookName())) {
            criteria.andCondition("wordbook_name like", "%" + requestDto.getWordbookName() + "%");
        }

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<Wordbook> queryList = wordbookMapper.selectByExample(example);

        return new PageInfo<>(queryList);
    }

    @Override
    public PageInfo queryAttributeTable(WordbookAttributeTableRequestDto requestDto) {
        Example example = new Example(WordbookAttribute.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteFlag", "00");
        criteria.andEqualTo("wordbookCode", requestDto.getWordbookCode());
        criteria.andEqualTo("attributeValue", requestDto.getAttributeValue());

        if (StringUtils.isNotBlank(requestDto.getAttributeName())) {
            criteria.andCondition("ATTRIBUTE_NAME like", "%" + requestDto.getAttributeName() + "%");
        }

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());
        List<WordbookAttribute> queryList = wordbookAttributeMapper.selectByExample(example);

        return new PageInfo<>(queryList);
    }

    @Override
    public List<WordbookAttribute> querySelect(String code) {
        WordbookAttribute wordbook = new WordbookAttribute();
        wordbook.setWordbookCode(code);
        return wordbookAttributeMapper.select(wordbook);
    }

    @Override
    public void save(WordbookSaveRequestDto requestDto) {
        Wordbook wordbook = new Wordbook();
        BeanUtils.copyProperties(requestDto,wordbook);
        if (requestDto.getId() == null) {
            wordbookMapper.insertSelective(wordbook);
        } else {
            wordbookMapper.updateByPrimaryKeySelective(wordbook);
        }

        List<WordbookAttribute> attributeList = requestDto.getAttributeList();

        for (WordbookAttribute attribute : attributeList) {
            WordbookAttribute wordbookAttribute= new WordbookAttribute();
            BeanUtils.copyProperties(attribute,wordbookAttribute);
            wordbookAttribute.setWordbookCode(wordbook.getWordbookCode());
            if (wordbookAttribute.getId() == null) {
                wordbookAttributeMapper.insertSelective(wordbookAttribute);
            } else {
                wordbookAttributeMapper.updateByPrimaryKeySelective(wordbookAttribute);
            }
        }

    }
}
