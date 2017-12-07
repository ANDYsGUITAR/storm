package com.log.dao;

import com.log.model.clickRate;
import com.log.model.clickRateKey;

public interface clickRateMapper {
    int deleteByPrimaryKey(clickRateKey key);

    int insert(clickRate record);

    int insertSelective(clickRate record);

    clickRate selectByPrimaryKey(clickRateKey key);

    int updateByPrimaryKeySelective(clickRate record);

    int updateByPrimaryKey(clickRate record);
}