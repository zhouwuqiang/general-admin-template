package com.java.business.wordbook.mapper;

import com.java.business.wordbook.entity.Wordbook;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface WordbookMapper extends Mapper<Wordbook> {
}
