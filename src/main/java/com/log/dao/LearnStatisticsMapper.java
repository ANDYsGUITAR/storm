package com.log.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.log.model.LearnStatistics;
import com.log.model.LearnStatisticsKey;
import com.log.pojo.LearnStatisticsPojo;

public interface LearnStatisticsMapper {
    int deleteByPrimaryKey(LearnStatisticsKey key);

    int insert(LearnStatistics record);

    int insertSelective(LearnStatistics record);

    LearnStatistics selectByPrimaryKey(LearnStatisticsKey key);

    int updateByPrimaryKeySelective(LearnStatistics record);

    int updateByPrimaryKey(LearnStatistics record);
    
    List<LearnStatisticsPojo> selectByAccount(@Param("account_no")String account_no);
    
    int learnBookNum(@Param("account_no")String account_no);
    
    String sumLearnTime(@Param("account_no")String account_no);
}