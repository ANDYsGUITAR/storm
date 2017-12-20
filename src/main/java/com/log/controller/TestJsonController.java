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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.log.model.user;
import com.log.service.UserService;

@Controller

@RequestMapping("/test")
public class TestJsonController {
	
	
	 @Resource  
	  private UserService userService = null;  
	 @ResponseBody
	 @RequestMapping(value = "delete", method = RequestMethod.POST)
	 public Map<String, Object> getSections(ServletRequest request) {
          System.out.println("coming");
	     //用ServletRequest接收参数
	     String strRailwayId = request.getParameter("param1");
	     String strAreaId = request.getParameter("areaId");
	     Long areaId= new Long(strAreaId );
	     String strDeleteId = request.getParameter("deleteId");
	     Long deleteId= new Long(strDeleteId );
	     System.out.println(strRailwayId+areaId+deleteId);
	      //处理参数 
	     Map<String,Object> map = new HashMap<String,Object>(); 

         map.put("param1",strRailwayId); 

         map.put("areaId",areaId);
         map.put("deleteId",deleteId);
   
	     return map;
	 }
	 /**通过@RequestBody接收参数*/
	 @ResponseBody
	 @RequestMapping(value = "area/delete", method = RequestMethod.POST)
	 public String editAreaWithFile(@RequestBody Map<String, Object> map) {
		 System.out.println("coming");
	     String param1 = (String) map.get("param1");
	     Integer strAreaId = (Integer) map.get("areaId");
	     Integer strDeleteId = (Integer) map.get("deleteId");
	     System.out.println("收到的参数==" + param1 + strAreaId + strDeleteId);
	     // 处理参数
	     return "成功";
	 }

	 @RequestMapping(value="/JsonTest")
	 @ResponseBody
	   public List<user>  JsonTest(ModelMap model,ServletRequest req){
		 System.out.println("testjson");
		 System.out.println("coming");
		 user u1=userService.selectByPrimaryKey(1);
		 user u2=userService.selectByPrimaryKey(2);
//		 Map<String,String>usermap=new HashMap<String,String>();
//		 usermap.put("name", "zyp");
//		 usermap.put("age", "20");
		 String name=req.getParameter("name");
		 String  age=req.getParameter("age");
		 System.out.println(name+age);
		 List<user> userlist=new ArrayList<user>();
		 userlist.add(u1);
		 userlist.add(u2);
		 return userlist;
	 }
   
}
