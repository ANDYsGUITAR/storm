package org.zyp.testmybatis;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;  
//import org.junit.Before;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
//import org.springframework.context.ApplicationContext;  
//import org.springframework.context.support.ClassPathXmlApplicationContext;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import com.alibaba.fastjson.JSON;
import com.log.model.topLesson;
import com.log.service.topLessonService;



//@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类 
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestMybBatis {
       private static Logger logger = Logger.getLogger(TestMybBatis.class);
      
    
    
 
       public static   void main(String[] args){
    	   //List<topLesson> topLessonList= toplessonService.gettopLesson();
    	   topLessonService toplessonService= null;
    	   Map<String,Integer> topLessonMap=toplessonService.topLesson();
    	   if(topLessonMap.isEmpty()) System.out.println("空");
    	  // logger.info(JSON.toJSONString(topLessonMap));
    	   if(topLessonMap.isEmpty()) System.out.println("空");
    	   for(Map.Entry<String, Integer>entry:topLessonMap.entrySet()){
    		   System.out.println("key="+entry.getKey()+"and value="+entry.getValue());
    	   }
       }
}
