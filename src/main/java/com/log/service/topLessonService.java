package com.log.service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.log.pojo.topLessonPojo;


public interface topLessonService {

    	   public List<LinkedHashMap<String,Object>> topLesson();
    	   public Map<String, String> transfor(List<LinkedHashMap<String, Object>> list,  
    	            String key, String property);
    	   public List<topLessonPojo> randomTopLesson(String startTime,String endTime);
}
