package com.log.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.log.model.topLesson;
import com.log.model.topLessonKey;

public interface topLessonMapper {
    int deleteByPrimaryKey(topLessonKey key);

    int insert(topLesson record);

    int insertSelective(topLesson record);

    topLesson selectByPrimaryKey(topLessonKey key);

    int updateByPrimaryKeySelective(topLesson record);

    int updateByPrimaryKey(topLesson record);
    /**********************************************************/
    //统计每年topN书
    List<topLesson> topOnYear(String year);
    //统计至今为止的topN书
     Map<String,Integer>  topLesson();
}