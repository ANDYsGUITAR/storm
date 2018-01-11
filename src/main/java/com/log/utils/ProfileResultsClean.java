/**
 * 这是一个mapreduce函数,
 * 主要功能是对spark统计后的数据作清洗;
 * 因为spark统计完数据存成文件后,会带有中括号,清洗掉中括号;
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




public class ProfileResultsClean {
	public static void main(String[] args) throws Exception{
		sparkClean(args);
	}
	
	public static void sparkClean(String[] args) throws Exception {
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
        job.setJarByClass(ProfileResultsClean.class);
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
			 						
			 							final String parsed = logParser. parsePhrase2(temp.toString());    
			 							
			 							// step2.写入
			 							outputValue.set(parsed);
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
        final String S1 = "[0,3,7.485555555555556]";
        		
          LogParser parser=new LogParser();
          String temp=parser. parsePhrase2(S1);
          System.out.println("样例数据："+S1);
          System.out.println("输出数据"+temp);
    }   

 /*************************第二个分析函数（从“[“+1到“]“-1）**************************/
   private String  parsePhrase2( String line){
   	   String[] arr=null;
   	   ArrayList<String> save=new ArrayList();
      String temp=line.substring(line.indexOf("[")+1, line.lastIndexOf("]"));
      arr=temp.split(",");
      String results = arr[0]+" "+"1:"+arr[1]+" "+"2:"+arr[2];
   	   return results;
   }
   
   
   
}
}

   
    



