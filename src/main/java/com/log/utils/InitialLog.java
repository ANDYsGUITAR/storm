/**
 * 对第一次清洗完的原始数据做spark分析
 * **/

package com.log.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;

/** 最原始的数据的清理函数, 用mapreduce完成,
 * 保留的字段:字段1:id                                   	//唯一的表示一条数据;
 *                        字段2:user_id             				//唯一的表示一个用户
 *                        字段5:book_id           				//唯一的表示一本书
 *                        字段9:in_time            				//表示打开视频的时间
 *                        字段10:out_time       				//表示关闭视频的时间
 *                        字段12:run_time       				//表示视频播放的时间
 *                        字段13:schedule_id 				//表示课堂的id
 *                        字段14:schedule_remark 	//表示课堂的名称
 *                        字段15:account_no 				//用户帐号
 * **/
 public class InitialLog  implements Serializable{
	        //创建session
			static SparkSession spark = SparkSession
					.builder()
					.appName("Sparkprofile")
					//指名在本地运行
					.master("local")
					//此处需要将spark.sql.warehouse.dir指定到本地正确的仓库地址
					.config("spark.sql.warehouse.dir","file:///home/hadoop/data")
					.getOrCreate();		
 	private static final long serialVersionUID = 1L;
    String id;
 	String user_id;
 	String book_id;
 	String in_time;
 	String out_time;
 	String run_time;
 	String schedule_id;
 	String schedule_remark;
 	String account_no;

 	public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
		public String getBook_id() {
			return book_id;
		}
		public void setBook_id(String book_id) {
			this.book_id = book_id;
		}
		public String getIn_time() {
			return in_time;
		}
		public void setIn_time(String in_time) {
			this.in_time = in_time;
		}
		public String getOut_time() {
			return out_time;
		}
		public void setOut_time(String out_time) {
			this.out_time = out_time;
		}
		public String getRun_time() {
			return run_time;
		}
		public void setRun_time(String run_time) {
			this.run_time = run_time;
		}
		public String getSchedule_id() {
			return schedule_id;
		}
		public void setSchedule_id(String schedule_id) {
			this.schedule_id = schedule_id;
		}
		public String getSchedule_remark() {
			return schedule_remark;
		}
		public void setSchedule_remark(String schedule_remark) {
			this.schedule_remark = schedule_remark;
		}
		public String getAccount_no() {
			return account_no;
		}
		public void setAccount_no(String account_no) {
			this.account_no = account_no;
		}
 /*******************************************测试**********************************************************************/  
  public static void main(String[] args){
	  InitialLog log=new InitialLog();
	  buildInitialLog();
	  //topLesson
//	  String outpath="spark/topLesson";
//	  log.buildtopLesson(outpath);
	  //learnStatistics
//	  String learnStatisticsOutpath="spark/learnStatistics";
//	  log.buildLearnStatistics(learnStatisticsOutpath);
	  String MonthyLearnTimeOutpath="spark/monthyLearntime";
	  log.buildMonthyLearnTime(MonthyLearnTimeOutpath);
  }
  /*******************************************建立topLesson并存放到hdfs上**********************************************/		
  public static void  buildtopLesson(String outpath){
	  
	  JavaRDD<InitialLog> LogRDD=buildInitialLog();
      JavaRDD<String> topLessonRDD=LogRDD.map(
      		new Function<InitialLog,String>(){
      			 public String call(InitialLog log){
      				 String time=log.in_time;
      				 String[] temp=time.split(" ");
      				 time=temp[0];
      				 temp=time.split("-");
      				 String year=temp[0];
      				 String month=temp[1];
      				 String line=log.book_id+","+year+","+month;
      				 return line;
      			 }
      		}
      		);
  	String schemaString = "book_id year month";
  	List<StructField> fields = new ArrayList<>();
  	//基于用字符串定义的schema生成structType
	for(String fieldName : schemaString.split(" ")){
			StructField field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
			fields.add(field);
	}
	StructType schema = DataTypes.createStructType(fields);
	//把RDD （people）转换为rows
	JavaRDD<Row> rowRDD = topLessonRDD.map(record -> {
			String[] attributes = record.split(",");
			return RowFactory.create(attributes[0],attributes[1],attributes[2]);
	});		
	//对RDD应用schema
	Dataset<Row> topLessonDF= spark.createDataFrame(rowRDD, schema);	
	//对DF创建临时视图
	topLessonDF.createOrReplaceTempView("TopLessonView");
	 // topLessonDF.show(20);
     //统计每年每月每本书的学习次数，生成topLesson文件
	Dataset<Row> topLesson=spark.sql("select book_id,year,month,count(book_id) as rate from TopLessonView group by book_id,year,month");
	//topLesson.show(20);
    topLesson.toJavaRDD().coalesce(1).saveAsTextFile(outpath);
  }
  /******************************************建立learnstatistics并存放在hdfs上****************************************/
 public static void buildLearnStatistics(String outpath){
	  JavaRDD<InitialLog> LogRDD=buildInitialLog();
	  JavaRDD<String> learnstatisticsRDD=LogRDD.map(
	      		new Function<InitialLog,String>(){
	      			 public String call(InitialLog log){
	      				 String runtime=log.run_time;      				 
	      	   			 double runtime1= Double.parseDouble(runtime.substring(0, runtime.indexOf("小")));
	      	   			 double runtime2=Double.parseDouble(runtime.substring(runtime.indexOf("时")+1,runtime.indexOf("分")));
	      	   			 double runtime3=Double.parseDouble(runtime.substring(runtime.indexOf("钟")+1,runtime.indexOf("秒")));
	      	   			 double time=runtime1+runtime2/60+runtime3/3600;
	      				 String account_no=log.account_no;
	      				 String book_id=log.book_id;
	      				 String line=account_no+","+book_id+","+String.valueOf(time);
	      				 return line;
	      			 }
	      		}
	      		);
	  String schemaString="account_no book_id runtime";
		List<StructField> fields = new ArrayList<>();
	  	//基于用字符串定义的schema生成structType
		for(String fieldName : schemaString.split(" ")){
				StructField field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
				fields.add(field);
		}
		StructType schema = DataTypes.createStructType(fields);
		//把RDD （people）转换为rows
		JavaRDD<Row> rowRDD = learnstatisticsRDD.map(record -> {
				String[] attributes = record.split(",");
				return RowFactory.create(attributes[0],attributes[1],attributes[2]);
		});	
		//对RDD应用schema
		Dataset<Row> learnStatisticsDF= spark.createDataFrame(rowRDD, schema);	
		//对DF创建临时视图
		 learnStatisticsDF.createOrReplaceTempView("learnStatisticsView");
		 // topLessonDF.show(20);
	     //统计每年每月每本书的学习次数，生成topLesson文件
		Dataset<Row> learnStatistics=spark.sql("select account_no,book_id,sum(runtime) as time  from learnStatisticsView group by account_no,book_id");
		//topLesson.show(20);
	    learnStatistics.toJavaRDD().coalesce(1).saveAsTextFile(outpath);
	  
 }
 /********************************************生成学习统计表的monthylearntime*****************************************************/
 public static  void buildMonthyLearnTime(String outpath){
	  JavaRDD<InitialLog> LogRDD=buildInitialLog();
	  JavaRDD<String> MonthyLearnTimeRDD=LogRDD.map(
	      		new Function<InitialLog,String>(){
	      			 public String call(InitialLog log){
	      				 String runtime=log.run_time;      				 
	      	   			 double runtime1= Double.parseDouble(runtime.substring(0, runtime.indexOf("小")));
	      	   			 double runtime2=Double.parseDouble(runtime.substring(runtime.indexOf("时")+1,runtime.indexOf("分")));
	      	   			 double runtime3=Double.parseDouble(runtime.substring(runtime.indexOf("钟")+1,runtime.indexOf("秒")));
	      	   			 double time=runtime1+runtime2/60+runtime3/3600;
	      				 String account_no=log.account_no;
	      				 String date=log.in_time;
	      				 String[] timetemp=date.split(" ");
	      				 date=timetemp[0];
	      				 String[] datetemp=date.split("-");
	      				 date=datetemp[0]+"-"+datetemp[1];
	      				 String line=account_no+","+date+","+String.valueOf(time);
	      				 return line;
	      			 }
	      		}
	      		);
	  String schemaString="account_no time runtime";
		List<StructField> fields = new ArrayList<>();
	  	//基于用字符串定义的schema生成structType
		for(String fieldName : schemaString.split(" ")){
				StructField field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
				fields.add(field);
		}
		StructType schema = DataTypes.createStructType(fields);
		//把RDD （people）转换为rows
		JavaRDD<Row> rowRDD = MonthyLearnTimeRDD.map(record -> {
				String[] attributes = record.split(",");
				return RowFactory.create(attributes[0],attributes[1],attributes[2]);
		});	
		//对RDD应用schema
		Dataset<Row> monthyLearntimeDF= spark.createDataFrame(rowRDD, schema);	
		//对DF创建临时视图
		 monthyLearntimeDF.createOrReplaceTempView("MonthyLearnTimeView");
		 // topLessonDF.show(20);
	     //统计每年每月每本书的学习次数，生成topLesson文件
		Dataset<Row> learnStatistics=spark.sql("select account_no,time,sum(runtime) as time  from MonthyLearnTimeView group by account_no,time");
		//topLesson.show(20);
	    learnStatistics.toJavaRDD().coalesce(1).saveAsTextFile(outpath);
 }
	  
  /*******************************************将原来的LOG生成JAVARDD**********************************************/	
 	public static JavaRDD<InitialLog> buildInitialLog(){
		//1.创建一个存储person的RDD(通过文本文件)
		JavaRDD<InitialLog>  LogRDD = spark.read()
																.textFile("output/clean/log2013-2017")
																.javaRDD()
																.map(line ->{
																		String[] parts = line.split(",");
																		InitialLog log=new InitialLog();
																		log.setId(parts[0]);
																		log.setUser_id(parts[1]);
																		log.setBook_id(parts[2]);
																		log.setIn_time(parts[3]);
																		log.setOut_time(parts[4]);
																		log.setRun_time(parts[5]);
																		log.setSchedule_id(parts[6]);
																		log.setSchedule_remark(parts[7]);
																		log.setAccount_no(parts[8]);																	
																		return log;
																});
		return LogRDD;
 	}
 }

