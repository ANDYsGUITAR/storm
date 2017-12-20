package org.zyp.testmybatis;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;  

import org.apache.log4j.Logger;  
import org.junit.Before;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import com.alibaba.fastjson.JSON;
import com.log.model.book;
import com.log.model.preBook;
import com.log.model.user;
import com.log.pojo.preBookPojo;
import com.log.service.BookService;
import com.log.service.UserService;
import com.log.service.preBookService;  


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  

public class TestPreBook {  
  private static Logger logger = Logger.getLogger(TestMyBatis.class);  
//private ApplicationContext ac = null;  
  @Resource  
  private UserService userService = null;  
  @Resource
  private preBookService prebookservice=null;
  @Resource
  private BookService bookService=null;

//@Before  
//public void before() {  
//    ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//    userService = (IUserService) ac.getBean("userService");  
//}  

  @Test  
  public void test1() {  
      //user user = userService.selectByPrimaryKey(1);
       //user user=userService.getUser("163ulcljupm");
	    Integer id=userService.getUserIndex("163ulcljupm");
	   System.out.println(id);
		preBook prebookTest=prebookservice.selectByPrimaryKey(id);
    	List<String> booklist=new ArrayList<String>();
       booklist.add(prebookTest.getBook1_no());
       booklist.add(prebookTest.getBook2_no());  
       booklist.add(prebookTest.getBook3_no());  
       booklist.add(prebookTest.getBook4_no());  
       booklist.add(prebookTest.getBook5_no());  
       booklist.add(prebookTest.getBook6_no());  
       booklist.add(prebookTest.getBook7_no());  
       booklist.add(prebookTest.getBook8_no());  
       booklist.add(prebookTest.getBook9_no());  
       booklist.add(prebookTest.getBook10_no()); 
       LinkedHashMap bookMap=new LinkedHashMap<String,Object>();
	      List<preBookPojo> bookList= new ArrayList<preBookPojo>();
	       for(int i=0;i<booklist.size();i++){
	       	String temp=booklist.get(i);
	      // 	System.out.println(temp);
	         String[] arr=temp.split("-");
	          int book_intid=Integer.parseInt(arr[0]);
	          book bookTest=bookService.getbook(book_intid);
	          String book_name=bookTest.getBook_name();
	          System.out.println(book_intid+book_name);
	          String score;
	          double tmp=Double.valueOf(arr[1]);
	         // System.out.println(tmp);
	          if(tmp<3.0){
	        	  score="低";
	          }else if((tmp>=3.0)&&(tmp<6.0)){
	        	  score="中";
	          }else if(tmp<9.0){
	        	  score="高";
	          }else{
	        	  score="极高";
	          }
	          preBookPojo book=new preBookPojo(book_name,score,arr[1],i+1);
	        
	          System.out.println(book_name+score+i);
	          bookList.add(book);
	       //   preBookController bookTemp=new preBookController(book_stringid, score, i);
	         // bookList.add(bookTemp);
	       }
      logger.info(JSON.toJSONString(booklist));  
  }  
}  
