package com.test.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.model.User;
import com.test.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		int userId= Integer.parseInt(request.getParameter("id"));
		User user=this.userService.getUserById(userId);
		 if(user != null){
	            request.setAttribute("name", user.getUser_name());
	            model.addAttribute("name", user.getUser_name());
	            return "showUser";
	        }
	        request.setAttribute("error", "没有找到该用户！");
	        return "error";
	}
}
