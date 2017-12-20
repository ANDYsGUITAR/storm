package com.log.controller;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.log.model.topLesson;
import com.log.pojo.topLessonPojo;
import com.log.service.topLessonService;

@Controller

@RequestMapping("topLesson")
public class topLessonController {
	@Resource
	private topLessonService toplessonService;
	 @ResponseBody
	 @RequestMapping(value="showtopLesson", method = RequestMethod.POST)
	 public List<topLessonPojo> showtopLesson(ServletRequest request) {
          System.out.println("coming");
	     //用ServletRequest接收参数
	     String startYear = request.getParameter("startYear");
	     String startMonth = request.getParameter("startMonth");
	  
	     String endYear= request.getParameter("endYear");
	     String endMonth= request.getParameter("endMonth");
	     String startTime=startYear+"-"+startMonth;
			String endTime=endYear+"-"+endMonth;
			System.out.println(startTime+"------"+endTime);
			List<topLessonPojo> lessonList=new ArrayList<topLessonPojo>();
			lessonList=this.toplessonService.randomTopLesson(startTime, endTime);
			for(int i=0;i<lessonList.size();i++){
				System.out.println(lessonList.get(i).getBook_id()+"*****"+lessonList.get(i).getRank());
			}
	            return lessonList;
   
	    
	 }
//	//获取数据
//	@RequestMapping(value="/showtopLesson")
//	@ResponseBody
//	public List<topLessonPojo> showtopLesson(ModelMap model,HttpServletRequest req,HttpServletResponse resp,
//			String startYear,String startMonth,String endYear,String endMonth) throws UnsupportedEncodingException{
//		System.out.println("coming!!!!!!!!!!!!!");
//		req.setCharacterEncoding("UTF-8");
//		String startTime=startYear+"-"+startMonth;
//		String endTime=endYear+"-"+endMonth;
//		System.out.println(startTime+"------"+endTime);
//		List<topLessonPojo> lessonList=new ArrayList<topLessonPojo>();
//		lessonList=this.toplessonService.randomTopLesson(startTime, endTime);
//		for(int i=0;i<lessonList.size();i++){
//			System.out.println(lessonList.get(i).getBook_id()+"*****"+lessonList.get(i).getRank());
//		}
//	            model.addAttribute("lessonList", lessonList);
//	            if(!model.isEmpty()) System.out.println("okokokokkokoko");               
////	            return "showtopLesson";
//            return lessonList;
//	}
}