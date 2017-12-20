package org.zyp.testmybatis;

import java.util.ArrayList;
import java.util.List;

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
import com.log.model.MonthyLearnTime;
import com.log.pojo.LearnStatisticsPojo;
import com.log.service.MonthyLearnTimeService;




  
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
  
public class TestMonthly {  
    private static Logger logger = Logger.getLogger(TestMonthly.class);  
//  private ApplicationContext ac = null;  
    @Resource  
    private MonthyLearnTimeService service = null;  
  
//  @Before  
//  public void before() {  
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//      userService = (IUserService) ac.getBean("userService");  
//  }  
  
    @Test  
    public void test1() {  
    	String account_no = "lllijie";
    	String year = "2013";

	    	List<MonthyLearnTime> MonthyLearnTimeList=new ArrayList<MonthyLearnTime>();
	    	//建立一个含有12个对象的数组(MonthyLearnTime)
//	    	List<MonthyLearnTime> twelveMonthLearnTimeList=new ArrayList<MonthyLearnTime>();
//	    	for(int i=1;i<=12;i++){
//	    		String month=String.valueOf(i);
//	    		MonthyLearnTime monthyLearnTimeNull=new MonthyLearnTime(account_no,month,"0");	    		
//	    		twelveMonthLearnTimeList.add(monthyLearnTimeNull);
//	    	}
	
	    	MonthyLearnTimeList=this.service.selectByYear(year, account_no);
//	    	for(int i=0;i<MonthyLearnTimeList.size();i++){
//	    		String date=MonthyLearnTimeList.get(i).getDate();
//	    		String[] temp=date.split("-");
//	    		date=temp[1];
//	    		int month=Integer.parseInt(date);
//	    		date=String.valueOf(month);
//	    		month--;
//	    		twelveMonthLearnTimeList.get(month).setDate(date);
//	    		twelveMonthLearnTimeList.get(month).setRuntime(MonthyLearnTimeList.get(i).getRuntime());
//	    	}
//	    	//输出monthylearntime
//	    	for(int i=0;i<12;i++){
//	    		System.out.println(twelveMonthLearnTimeList.get(i).getDate()+"****"+twelveMonthLearnTimeList.get(i).getRuntime());	
//
//	    	}
        logger.info(JSON.toJSONString(MonthyLearnTimeList));  
    }  
}  

