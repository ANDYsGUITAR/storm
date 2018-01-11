/**
 * 最原始的数据的清理函数, 用mapreduce完成,
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

package com.log.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;




public class InitialClean {
	public static void main(String[] args) throws Exception{
		initialClean(args);
	}
	

	
	public static  void initialClean(String[] args) throws Exception {
        Configuration conf = new Configuration();
        //获得Configuration配置 Configuration: core-default.xml, core-site.xml
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
      //获得输入参数 [hdfs://localhost:9000/user/hadoop/input, hdfs://localhost:9000/user/hadoop/output]
        if (otherArgs.length != 2) {//判断输入参数个数，不为两个异常退出
            System.err.println("Usage: Multiple Table Join <in> <out>");
            System.exit(2);
        }
        @SuppressWarnings("deprecation")
		Job job = new Job(conf, "Multiple Table Join");
        job.setJarByClass(InitialClean.class);
        // 设置Map和Reduce处理类
        job.setMapperClass(MyMapper.class);       
        job.setReducerClass(MyReducer.class);        
        job.setMapOutputKeyClass(LongWritable.class);  
        job.setMapOutputValueClass(Text.class);             
        // 设置输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        // 设置输入和输出目录
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));  //传入input path
        //FileInputFormat.addInputPath(job,new Path(otherArgs[1]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        //传入output path，输出路径应该为空，否则报错org.apache.hadoop.mapred.FileAlreadyExistsException。
        System.exit(job.waitForCompletion(true) ? 0 : 1);//是否正常退出
    }
/*************************************过滤学习时间为NaN和小于分钟的数据********************************************************/	
    private static int parsePhraseTime(String  line,int n){
		   if(line.indexOf("小")==-1){
			  // System.out.println("运行时间"+line);
			   return 0;
		   }else{
			   final String trim=line.substring(line.indexOf("小")-1, line.indexOf("小"));
			   if(!trim.equals("N")){
				   int i=Integer.parseInt(trim);
				   if(i>0) return 1;
			   }
		   }		   
		    if(line.indexOf("时")==-1||line.indexOf("分")==-1){
			   return 0;
		   }else{
			   final String trim=line.substring(line.indexOf("时")+1, line.indexOf("分"));
			   if(trim.equals("NaN")){
				   return 0;
			   }else{
				   int i=Integer.parseInt(trim);
				   if(i<n) return 0;
				   else return 1;
			   }
		   }
	   }
 
	
/************************************* MapL类的实现**************************************************************/	
	
	 static class MyMapper extends Mapper<LongWritable, Text, LongWritable, Text> {		 
		 LogParser logParser = new LogParser();
		 Text outputValue = new Text();
		 protected void map(
				 LongWritable key,
				 Text value,
				 org.apache.hadoop.mapreduce.Mapper<LongWritable, Text, LongWritable, Text>.Context context)
						 throws java.io.IOException, InterruptedException {
	 
			 							final String temp=value.toString();
			 							// step1.过滤掉非插入语句
			 							if (!temp.startsWith("INSERT"))
			 							{
			 								return;
			 							}
			 							
			 							final String[] parsed = logParser.parse(temp.toString());
			 						
                                        if(parsePhraseTime(parsed[1],5)==0){
                                        	return;
                                        }
                                        
			 							// step2.写入
			 							outputValue.set(parsed[1]);
			 							context.write(key, outputValue);
		 }
	 }
/******************************************Reduce类的实现********************************************************/	 
	  static class MyReducer extends Reducer<LongWritable, Text, Text, NullWritable> {
		  protected void reduce(
				  LongWritable k2,
				  java.lang.Iterable<Text> v2s,
				  org.apache.hadoop.mapreduce.Reducer<LongWritable, Text, Text, NullWritable>.Context context)
						  throws java.io.IOException, InterruptedException {
			  								for (Text v2 : v2s) {
			  									context.write(v2, NullWritable.get());
			  								}
		  }; 
	  }
	 
/*****************************************分析类的实现************************************************/
	static class LogParser{
    public static void main(String[] args) throws ParseException {
    	/**例子**/
        final String S1 = "INSERT INTO `eb_learnlog` VALUES ("
        		+ "'000000c5b2494d388f4709a2e0561eb2', "
        		+ "'3cd52a9f8c0c425e87a24e98aa4d49e1',"
        		+ " '140caf5c6a7f408e884a40eed477d522', "
        		+ "null, "
        		+ "'c4149360a00047babaed60b564bd9c94' ,"
        		+ " 'e2e9e6d645d94f7aa33c4902380c0abe', "
        		+ "'e0678a9179fd4b8792fe0a48df4fb696',"
        		+ " 'd69adc48aefe11e7bb6a00163e0236fc',"
        		+ " '2017-10-29 00:15:05', "
        		+ "'2017-10-29 00:15:05',"
        		+ "null,"
        		+ "'NaN小时NaN分钟NaN秒', "
        		+ "'-d69adc48aefe11e7bb6a00163e0236fc_', "
        		+ "'计算机应用与编程综合实训_1707班', "
        		+ "'hust2017115060702', "
        		+ "'王雪琳');";
          LogParser parser=new LogParser();
          String[] temp=parser.parse(S1);
          System.out.println("样例数据："+S1);
          System.out.println("输出数据"+temp[2]);
    }   
    public String[] parse(String line) {  	
    	String phrase1=parsePhrase1(line);
    	ArrayList<String>  tempList = parsePhrase2(line);
    	String phrase2="";
    	for(int i=0;i<tempList.size();i++){
    		if(i!=tempList.size()-1)
    		{phrase2=phrase2+tempList.get(i)+",";    		}
    		else
    		{phrase2=phrase2+tempList.get(i);}
    	}    	
    	String  phrase3 = parsePhrase3(line);
        return new String[] {phrase1,phrase2,phrase3};
    }
  /*************************第一个分析函数（从insert到"("）************************/     
   private String  parsePhrase1( String line){
	   final String trim = line.substring(0,line.indexOf("(")+1)
               .trim();
      return trim;
    }
 /*************************第二个分析函数（从“（“+1到“）“-1）**************************/
   private ArrayList<String>  parsePhrase2( String line){
   	   String[] arr=null;
   	   ArrayList<String> save=new ArrayList();
   	   final String trim=line.substring(line.indexOf("(")+1,line.lastIndexOf(")"));
   	   arr=trim.split(",");
   	   for(int i=0;i<arr.length;i++){
   		   //id,user_id,book_id,in_time.out_time,run_time,schedule_id,schedule_remark,account_no

   		   if(i==0||i==1||i==4||i==8||i==9||i==12||i==13||i==11||i==14){			 
 	           //save.add(arr[i]);  
   			 if(arr[i].indexOf("'")==-1||arr[i].lastIndexOf("'")==-1){
   				 save.add(arr[i]);
   			 }else  {
   				//System.out.println(i+arr[i]);
   			    arr[i]=arr[i].substring(arr[i].indexOf("'")+1);
   			   
   			    if(arr[i].lastIndexOf("'")!=-1)
   			    { arr[i]=arr[i].substring(0, arr[i].lastIndexOf("'"));
 	           save.add(arr[i]); }
   			    else if(arr[i].lastIndexOf("'")==0)
   			    {
   			    	arr[i]="null"+arr[i];
   			    	arr[i]=arr[i].substring(0, arr[i].lastIndexOf("'"));
   			    	save.add(arr[i]);
   			    }
   			    else{
   			    	save.add(arr[i]);
   			    	
   			    }
 	           }	    
   		   }
   	   }
//   	   if(save.get(3)!=null){
//   		      String time=save.get(3); 		
//   			   String[] buff=time.split(" ");
//   			   time=buff[0];	
//   			  String[] buff2=time.split("-");
//   			  time=buff2[0];
//   			   save.add(time);
//   	   }else{
//   		   save.add(null);
//   	   }
   	  
   	   return save;
   }
   /*******************************第三个分析函数（从“）“到“；“）***************************/
   private String  parsePhrase3(String line){
	   final String trim = line.substring(line.lastIndexOf(")"))
               .trim();
	   return trim;
   }    
	}
}

   
    


