package com.log.service;

import java.util.List;


import com.log.pojo.LearnStatisticsPojo;

public interface LearnStatisticsService {
      public List<LearnStatisticsPojo> selectByAccount(String account_no);
      
     public int learnBookNum(String account_no);
      
     public  String sumLearnTime(String account_no);
     
    // List<LearnStatisticsPojo> allStatistics(String account_no);
}
