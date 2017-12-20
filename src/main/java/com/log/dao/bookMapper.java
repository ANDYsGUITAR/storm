package com.log.dao;

import com.log.model.book;

public interface bookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(book record);

    int insertSelective(book record);

    book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(book record);

    int updateByPrimaryKey(book record);
}