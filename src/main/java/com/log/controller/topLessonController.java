//package com.log.controller;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.log.model.topLesson;
//import com.log.service.topLessonService;
//import com.test.model.User;
//import com.test.service.UserService;
//
//@Controller
//@RequestMapping("/topLesson")
//public class topLessonController {
//	
//	@Resource
//	private topLessonService toplessonService;
//	
//	@RequestMapping(value="/showtopLesson")
//	public String getTopLesson(ModelMap model){
//		List<topLesson> lessonList=new ArrayList<topLesson>();
//				lessonList=this.toplessonService.gettopLesson();
////		for(int i=0;i<lessonList.size();i++){
////			System.out.println(lessonList.get(i).getBook_rank());
////		}
//	            model.addAttribute("lessonList", lessonList);
//	            if(!model.isEmpty()) System.out.println("okokokokkokoko");
//
//	            return "showtopLesson";
//	}
//}