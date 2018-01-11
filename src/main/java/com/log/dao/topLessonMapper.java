package com.log.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.log.model.topLesson;
import com.log.model.topLessonKey;
import com.log.pojo.topLessonPojo;

public interface topLessonMapper {
    int deleteByPrimaryKey(topLessonKey key);

    int insert(topLesson record);

    int insertSelective(topLesson record);

    topLesson selectByPrimaryKey(topLessonKey key);

    int updateByPrimaryKeySelective(topLesson record);

    int updateByPrimaryKey(topLesson record);
    
    List<LinkedHashMap<String,Object>> topLesson();
    
    List<topLessonPojo> randomTopLesson(@Param("startTime") String startTime,@Param("endTime") String endTime);
    
}