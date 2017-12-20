package com.log.dao;

import com.log.model.preBook;

public interface preBookMapper {
    int deleteByPrimaryKey(Integer user_id);

    int insert(preBook record);

    int insertSelective(preBook record);

    preBook selectByPrimaryKey(Integer user_id);

    int updateByPrimaryKeySelective(preBook record);

    int updateByPrimaryKey(preBook record);
}