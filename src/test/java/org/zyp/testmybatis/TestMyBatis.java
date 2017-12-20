package org.zyp.testmybatis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import com.log.model.book;
import com.log.model.preBook;
import com.log.model.topLesson;
import com.log.model.user;
import com.log.pojo.topLessonPojo;
import com.log.service.BookService;
import com.log.service.UserService;
import com.log.service.preBookService;
import com.log.service.topLessonService;



@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
  
public class TestMyBatis {  
    private static Logger logger = Logger.getLogger(TestMyBatis.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private topLessonService  Service = null;  
    private BookService bookService = null;
     private UserService userService=null;
    private preBookService prebookservice=null;
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
      
//        List<LinkedHashMap<String, Object>> lessonlist=Service.topLesson();
//        Map<String,String>lessonMap=new LinkedHashMap<String,String>();
//        lessonMap=Service.transfor(lessonlist, "book_id", "num");
    	
       List<topLessonPojo> topLessonList=new ArrayList<topLessonPojo>();
       topLessonList=Service.randomTopLesson("2013-09", "2014-02");
        logger.info(JSON.toJSONString(topLessonList));  
    }  
   
} 
