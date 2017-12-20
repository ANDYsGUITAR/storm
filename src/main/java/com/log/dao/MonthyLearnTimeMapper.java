package com.log.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.log.model.MonthyLearnTime;
import com.log.model.MonthyLearnTimeKey;

public interface MonthyLearnTimeMapper {
    int deleteByPrimaryKey(MonthyLearnTimeKey key);

    int insert(MonthyLearnTime record);

    int insertSelective(MonthyLearnTime record);

    MonthyLearnTime selectByPrimaryKey(MonthyLearnTimeKey key);

    int updateByPrimaryKeySelective(MonthyLearnTime record);

    int updateByPrimaryKey(MonthyLearnTime record);
    
    List< MonthyLearnTime> selectByYear(@Param("year")String  year,@Param("account_no")String account_no);
}