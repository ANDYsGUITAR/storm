package org.zyp.testmybatis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;  

import org.apache.log4j.Logger;  
import org.junit.Before;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import com.alibaba.fastjson.JSON;
import com.log.pojo.topLessonPojo;
import com.log.service.topLessonService;




  
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
  
public class TestTopLesson {  
    private static Logger logger = Logger.getLogger(TestTopLesson.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private topLessonService service = null;  
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
	StopWatch test=new StopWatch("search");
		
        
        test.start("topLesson");
        
    	  String startYear="2013";
 	     String startMonth="03";
 	  
 	     String endYear="2017";
 	     String endMonth= "12";
 	     String startTime=startYear+"-"+startMonth;
 			String endTime=endYear+"-"+endMonth;
 			//System.out.println(startTime+"------"+endTime);
 			List<topLessonPojo> lessonList=new ArrayList<topLessonPojo>();
 			lessonList=this.service.randomTopLesson(startTime, endTime);
// 			for(int i=0;i<lessonList.size();i++){
// 				System.out.println(lessonList.get(i).getBook_name()+"*****"+lessonList.get(i).getRank());
// 			}
 			 test.stop();
 		       System.out.println(test.prettyPrint());
        logger.info(JSON.toJSONString(lessonList));  
    }  
}  


