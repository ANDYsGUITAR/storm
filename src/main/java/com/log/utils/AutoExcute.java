package com.log.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class AutoExcute {
	public static void main(String[] args){
		timeUpdated(17, 56, 59);
	}
     
	/**
	 * 调用清洗原始数据的方法
	 * 设置输入输出路径：
	 * input:data/eb_learnlog.sql
	 * output:Log/output/clean/InitialCleanResults
	 * **/
	static String[] initialcleanargs={"data/eb_learnlog.sql","Log/output/clean/InitialCleanResults"};
	static String  topLessonOutpath="spark/topLesson";
	static String learnStatisticsOutpath="spark/learnStatistics";
	static String monthyLearntimeOutpath="spark/monthyLearntime";
	static String[] sparkCleanTopLessonArgs={"spark/topLesson","Log/output/clean/topLesson"};
	static String[] sparkCleanLearnStatisticsArgs={"spark/learnStatistics","Log/output/clean/learnStatistics"};
	static String[] saprkCleanMonthyLearnTimeArgs={"spark/monthyLearntime","Log/output/clean/monthyLearntime"};
	public InitialClean clean=null;
	public InitialLog initilaLog=new InitialLog();
	 public static void timeUpdated(int hour,int minute,int second){
		 Calendar cal=Calendar.getInstance();
		 cal.set(Calendar.HOUR_OF_DAY, hour);
		 cal.set(Calendar.MINUTE, minute);
		 cal.set(Calendar.SECOND, second);
		 Timer timer=new Timer();
		 timer.schedule(new TimerTask(){
	    		public void run(){
	    			try {
	    				//清洗原始数据
						InitialClean.initialClean(initialcleanargs);
						//将原始数据生成RDD
						InitialLog.buildInitialLog();
						//用spark根据不同的功能需要统计出所需数据，并保存为hdfs文件
						InitialLog.buildtopLesson(topLessonOutpath);
						InitialLog.buildLearnStatistics(learnStatisticsOutpath);
						InitialLog.buildMonthyLearnTime(monthyLearntimeOutpath);
						//将上一步的hdfs文件清理为mysql数据库可以识别的数据
						SparkResultsClean.sparkClean(sparkCleanTopLessonArgs);
						SparkResultsClean.sparkClean(sparkCleanLearnStatisticsArgs);
						SparkResultsClean.sparkClean(saprkCleanMonthyLearnTimeArgs);
					    //将上一步清洗的数据通过sqoop传入mysql
						Transfer.HDFStoMySQL(sparkCleanTopLessonArgs[1],"topLesson");
						Transfer.HDFStoMySQL(sparkCleanLearnStatisticsArgs[1], "learnStatistics");
						Transfer.HDFStoMySQL(saprkCleanMonthyLearnTimeArgs[1], "MonthyLearnTime");
						//将以上产生的hdfs文件清理掉
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	    	     
	    	        }
	    		}	    	
	    		,cal.getTime());
	 }
}
