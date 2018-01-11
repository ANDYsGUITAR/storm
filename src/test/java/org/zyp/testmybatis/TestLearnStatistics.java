package org.zyp.testmybatis;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.util.StopWatch;

import com.alibaba.fastjson.JSON;
import com.log.model.MonthyLearnTime;
import com.log.pojo.LearnStatisticsPojo;
import com.log.service.LearnStatisticsService;
import com.log.service.MonthyLearnTimeService;




  
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
  
public class TestLearnStatistics {  
    private static Logger logger = Logger.getLogger(TestLearnStatistics.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private LearnStatisticsService service = null;  
    @Resource 
	private MonthyLearnTimeService monthLearnTimeService = null;
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
	StopWatch test=new StopWatch("search");
        test.start("LearnStatistics");
        
        
       int booknum;
       String learntime;
       String year="2017";
   
    	String account_no="ruanko100";
        //用ServletRequest接收参数
		   booknum=service.learnBookNum(account_no);
	      learntime=service.sumLearnTime(account_no);
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
//	    	//输出monthylearntime
//	    	for(int i=0;i<12;i++){
//	    		System.out.println(twelveMonthLearnTimeList.get(i).getDate()+"****"+twelveMonthLearnTimeList.get(i).getRuntime());	
//	    	}
//	    	
	      Map<String,Object>allStatistics=new HashMap<String,Object>();
	      allStatistics.put("booknum", booknum);
	//	 System.out.println(allStatistics.get("booknum"));
		 allStatistics.put("allRuntime", learntime);
	//	 System.out.println(allStatistics.get("allRuntime"));
		 List<Object> list=new ArrayList<Object>();
		 allStatistics.put("singleBookList", singleBookList);
		 allStatistics.put("twelveMonthLearnTimeList", twelveMonthLearnTimeList);
		list.add(allStatistics);
    	 test.stop();
	       System.out.println(test.prettyPrint());
        logger.info(JSON.toJSONString(list));  
    }  
}  

