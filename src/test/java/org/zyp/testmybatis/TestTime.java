package org.zyp.testmybatis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StopWatch;

import com.log.pojo.topLessonPojo;
import com.log.service.topLessonService;

public class TestTime {
	
    @Resource  
    private topLessonService toplessonService = null;

	public void testTopLesson(){
	
		StopWatch test=new StopWatch("search");
		
        //测试全站最受欢迎的课程
        test.start("topLesson");
      
	       String startTime="2013-05";
			String endTime="2017-12";
			System.out.println(startTime+"------"+endTime);
			List<topLessonPojo> lessonList=new ArrayList<topLessonPojo>();
			lessonList=this.toplessonService.randomTopLesson(startTime, endTime);
			for(int i=0;i<10;i++){
				System.out.println(lessonList.get(i).getBook_name()+"*****"+lessonList.get(i).getRank());
			}
	       test.stop();
	       System.out.println(test.prettyPrint());
	}
	public static void main(String[] args){
		TestTime test=new TestTime();
		test.testTopLesson();
	}
        
        
}
