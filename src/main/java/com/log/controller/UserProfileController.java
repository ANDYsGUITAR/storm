package com.log.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.log.service.LearnStatisticsService;
import com.log.utils.LoadClassfier;



@Controller

@RequestMapping("figure")

public class UserProfileController {
	
	@Resource  
	  private LearnStatisticsService service = null;  

	@RequestMapping(value="UserProfile")
	public String  UserProfile(HttpServletRequest request,ModelMap map) throws IOException{
		HttpSession session=request.getSession();
		String account_no = request.getParameter("student_no");
		 System.out.println(account_no);
		 int booknum=service.learnBookNum(account_no);
		 String learntimeTemp=service.sumLearnTime(account_no);//返回的是“xx小时xx分钟“
		 learntimeTemp=learntimeTemp.substring(0,learntimeTemp.indexOf("小"))+"."+learntimeTemp.substring(learntimeTemp.indexOf("时")+1,learntimeTemp.indexOf("分"));
		 double learntime=Double.valueOf(learntimeTemp);
		 
	      System.out.println(account_no+" has learned "+booknum+" books and has learned about "+learntime+" hours.");
	      File dicPath=new File("profileData///");
			 if(!dicPath.exists()){
			   dicPath.mkdirs();
			  }
			  File filePath=new File("profileData///profieData.txt");
			 if (filePath.exists()) {
				filePath.delete();
				System.out.println("Have been Deleted!");
			} 
				  filePath.createNewFile(); 
				  System.out.println(filePath.exists());
				  System.out.println(filePath.getAbsolutePath());
				  
				BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
				out.write("0"+" 1:"+booknum+" 2:"+learntime);
				out.flush();
				out.close();
				String datapath="file:///home/hadoop/eclipse/profileData/profieData.txt";
				//book和runtime结合的标签
				String modelpath1="model/profileBookOrRunTime";
				String lable1=LoadClassfier.predict(modelpath1, datapath);
				System.out.println("OKOKOKOKO");
				//sumbook>20 then 2  when sumbook >=8 then 1 else 0 
				String modepath2="model/profileBook";
				String lable2=LoadClassfier.predict(modepath2, datapath);
				//allruntime>50 then 2  when allruntime >=20 then 1 else 0
				String modelpath3="model/profileRuntime";
				String lable3=LoadClassfier.predict(modelpath3, datapath);
				
				List<String> TagList = new ArrayList<>();
				if(lable1.equals("1.0")){
					TagList.add("学霸");
				}else{
					TagList.add("成为学霸的路上");
				}
				if(lable2.equals("2.0")){
					TagList.add("博览群书");
				}else if(lable2.equals("1.0")){
					TagList.add("涉猎甚广");
				}else{
					TagList.add("入门小白");
				}
				if(lable3.equals("2.0")){
				   TagList.add("废寝忘食学习");
				}else if(lable3.equals("1.0")){
					TagList.add("勤奋的孩子");
				}else{
					TagList.add("这个人有点儿懒");
				}

	      for(String s:TagList){
	    	  System.out.println(s);
	      }
//	      if (filePath.exists()) {
//				filePath.delete();
//				System.out.println("Have been Deleted!");
//			} 
	      
//	      List<String> TagList = new ArrayList<>();
//	      for(int i = 0;i<3;i++){
//	    	  TagList.add(" ");
//	      }
//	      //设定三个标签，依次根据booknum,learntime,结合booknum和learntime.
//	      //根据booknum设定一个标签
//	      if(booknum>20){
//	    	  TagList.set(0, "博览群书");
//	      }
//	      else if(booknum>=8){
//	    	  TagList.set(0, "涉猎甚广");
//	      }
//	      else{
//	    	  TagList.set(0, "入门小白");
//	      }
//	      //根据learntime设定一个标签
//	      if (learntime>50) {
//	    	  TagList.set(1, "废寝忘食学习");
//		}
//	      else if(learntime>=20){
//	    	  TagList.set(1, "勤奋的孩子");
//	      }
//	      else {
//	    	  TagList.set(1, "这个人有点儿懒");
//		}
//	      //3
//	      if(learntime>=20 ||booknum>=8){
//	    	  TagList.set(2, "学霸");
//	      }
//	      else{
//	    	  TagList.set(2, "成为学霸的路上");
//	      }
//	      for(String s:TagList){
//	    	  System.out.println(s);
//	      }
	      List<Object> list=new ArrayList<Object>();
	      
	      list.add(TagList);
	      map.addAttribute("TagList", TagList);
		return "figure";
	}

}
