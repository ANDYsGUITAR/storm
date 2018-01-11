package org.zyp.testmybatis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;

import com.log.service.LearnStatisticsService;
import com.log.utils.LoadClassfier;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  

public class TestUserProfile {
	
	  private static Logger logger = Logger.getLogger(TestUserProfile.class);  
	@Resource  
	  private LearnStatisticsService service = null;  

	  @Test  
	public void test() throws IOException {
		 StopWatch test=new StopWatch("search");
	      test.start("Userprofile");
		
		String account_no="ruanko100";
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
				String datapath="file:///home/hadoop/EclipseJava/workspace/storm/profileData/profieData.txt";
				//book和runtime结合的标签
				String modelpath1="model/profileBookOrRunTime";
				String lable1=LoadClassfier.predict(modelpath1, datapath);
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
	      if (filePath.exists()) {
				filePath.delete();
				System.out.println("Have been Deleted!");
			} 
//	      List<Object> list=new ArrayList<Object>();
//	      
//	      list.add(TagList);
//	      test.stop();
//	       System.out.println(test.prettyPrint());
	
	}
}
