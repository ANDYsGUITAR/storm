package com.log.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.log.model.MonthyLearnTime;
import com.log.pojo.LearnStatisticsPojo;
import com.log.service.LearnStatisticsService;
import com.log.service.MonthyLearnTimeService;


@Controller

@RequestMapping("Statistics")
public class LearnStatisticsController {

	@Resource  
	  private LearnStatisticsService service = null;  
	@Resource 
	private MonthyLearnTimeService monthLearnTimeService = null;
	  
	@ResponseBody
	@RequestMapping(value="LearnStatistics",method = RequestMethod.POST)
   public List<Object>  LearnStatistics(ServletRequest request) throws UnsupportedEncodingException{
		   System.out.println("coming");
		   String account_no = request.getParameter("student_no");		
		   String year = request.getParameter("year");
		   System.out.println(account_no);
		   System.out.println(year);
	     //用ServletRequest接收参数
		   int booknum=service.learnBookNum(account_no);
	       String learntime=service.sumLearnTime(account_no);
	    	List<LearnStatisticsPojo> singleBookList=new ArrayList<LearnStatisticsPojo>();
	    	List<MonthyLearnTime> MonthyLearnTimeList=new ArrayList<MonthyLearnTime>();
	    	//建立一个含有12个对象的数组(MonthyLearnTime)
	    	List<MonthyLearnTime> twelveMonthLearnTimeList=new ArrayList<MonthyLearnTime>();
	    	for(int i=1;i<=12;i++){
	    		String month=String.valueOf(i);
	    		MonthyLearnTime monthyLearnTimeNull=new MonthyLearnTime();
	    		monthyLearnTimeNull.setAccount_no(account_no);
	    		monthyLearnTimeNull.setDate(month);
	    		monthyLearnTimeNull.setRuntime("0");
	    		twelveMonthLearnTimeList.add(monthyLearnTimeNull);
	    	}
	    	singleBookList=this.service.selectByAccount(account_no);
	    	MonthyLearnTimeList=this.monthLearnTimeService.selectByYear(year, account_no);
	    	for(int i=0;i<MonthyLearnTimeList.size();i++){
	    		String date=MonthyLearnTimeList.get(i).getDate();
	    		String[] temp=date.split("-");
	    		date=temp[1];
	    		int month=Integer.parseInt(date);
	    		date=String.valueOf(month);
	    		month--;
	    		twelveMonthLearnTimeList.get(month).setAccount_no(account_no);
	    		twelveMonthLearnTimeList.get(month).setDate(date);
	    		twelveMonthLearnTimeList.get(month).setRuntime(MonthyLearnTimeList.get(i).getRuntime());
	    	}
	    	//输出monthylearntime
	    	for(int i=0;i<12;i++){
	    		System.out.println(twelveMonthLearnTimeList.get(i).getDate()+"****"+twelveMonthLearnTimeList.get(i).getRuntime());	
	    	}
	    	
	      Map<String,Object>allStatistics=new HashMap<String,Object>();
	      allStatistics.put("booknum", booknum);
		 System.out.println(allStatistics.get("booknum"));
		 allStatistics.put("allRuntime", learntime);
		 System.out.println(allStatistics.get("allRuntime"));
		 List<Object> list=new ArrayList<Object>();
		 allStatistics.put("singleBookList", singleBookList);
		 allStatistics.put("twelveMonthLearnTimeList", twelveMonthLearnTimeList);
		list.add(allStatistics);
		return list;
	}
	
}
