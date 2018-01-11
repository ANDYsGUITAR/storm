package com.log.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.log.model.book;
import com.log.model.preBook;
import com.log.pojo.PreApiPojo;
import com.log.service.BookService;
import com.log.service.UserService;
import com.log.service.preBookService;

@Controller

@RequestMapping("predict")
public class PreApi {

	@Resource  
	  private UserService userService = null;  
	  @Resource
	  private preBookService prebookservice=null;
	  @Resource
	  private BookService bookService=null;
	  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value="showPredictBooks")
	//访问形式：http://localhost:8080/storm/predict/showPredictBooks?student_no=ruanko100
   public List<PreApiPojo>  recommendation(HttpServletRequest request) throws UnsupportedEncodingException{
		   System.out.println("coming");
	     //用ServletRequest接收参数
	       String student_no = request.getParameter("student_no");		
		    Integer id=userService.getUserIndex(student_no);
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
//	      LinkedHashMap bookMap=new LinkedHashMap<String,Object>();
	      List<PreApiPojo> bookList= new ArrayList<PreApiPojo>();
	       for(int i=0;i<booklist.size();i++){
	       	String temp=booklist.get(i);
	      // 	System.out.println(temp);
	         String[] arr=temp.split("-");
	          int book_intid=Integer.parseInt(arr[0]);
	          book bookTest=bookService.getbook(book_intid);
	          String book_name=bookTest.getBook_name();
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
	          PreApiPojo book=new PreApiPojo(bookTest.getBook_id(),book_name,score,arr[1],i+1);
	        
	          System.out.println(book_name+score+i);
	          bookList.add(book);
	       //   preBookController bookTemp=new preBookController(book_stringid, score, i);
	         // bookList.add(bookTemp);
	       }
//	       for(int i=0;i<bookList.size();i++){
//	    	   for(Map.Entry<String,Object>entry:bookList.get(i).entrySet()){
//	    		   System.out.println(entry.);
//	    	   }
//	       }
		return bookList;
	}
	
}
