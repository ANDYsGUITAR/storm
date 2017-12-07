package com.log.service.impl;

import com.log.dao.topLessonMapper;
import com.log.model.topLesson;
import com.log.service.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("topLessonService")
public class topLessonImpl  implements topLessonService{
   @Resource
   private topLessonMapper  toplessonMapper;
	@Override
	public Map<String,Integer> topLesson() {
		// TODO Auto-generated method stub
		return this.toplessonMapper.topLesson();
	}
       
}
