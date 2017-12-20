package com.log.service.impl;

import com.log.dao.topLessonMapper;
import com.log.model.topLesson;
import com.log.pojo.topLessonPojo;
import com.log.service.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("topLessonService")
public class topLessonImpl  implements topLessonService{
   @Resource
   private topLessonMapper  toplessonMapper;
	@Override
	public List<LinkedHashMap<String,Object>> topLesson() {
		// TODO Auto-generated method stub
		return this.toplessonMapper.topLesson();
	}
	//将list<map>转换为map
	@Override
	 public Map<String, String> transfor(List<LinkedHashMap<String, Object>> list,  
            String key1, String key2) {  
        Map<String, String> map = new LinkedHashMap<String, String>();  
        for (Map<String, Object> mapTemp : list) {  
            map.put(mapTemp.get(key1).toString(), mapTemp.get(key2).toString());  
        }  
        return map; 
	 }
	@Override
	public List<topLessonPojo> randomTopLesson(String startTime, String endTime) {
		// TODO Auto-generated method stub
		return this.toplessonMapper.randomTopLesson(startTime, endTime);
	}  
}
