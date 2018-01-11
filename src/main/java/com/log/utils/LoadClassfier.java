package com.log.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.ml.classification.OneVsRestModel;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.log.pojo.ClassifierPojo;

import scala.Tuple2;



public class LoadClassfier {
	public static void main(String[] args){
//		String datapath="file:///home/hadoop/EclipseJava/workspace/storm/profileData/profieData.txt";
//		String modelpath="model/profle1";
//		predict(modelpath,datapath);
	}

	public static String predict(String modelpath,String datapath){
		//创建session
				SparkSession spark = SparkSession
						.builder()
						.appName("Sparkprofile")
						//指名在本地运行
						.master("local")
						//此处需要将spark.sql.warehouse.dir指定到本地正确的仓库地址
						.config("spark.sql.warehouse.dir","file:///home/hadoop/data")
						.getOrCreate();
				OneVsRestModel ovrModel =OneVsRestModel.load(modelpath);
			    Dataset<Row> inputData = spark.read().format("libsvm")
					      .load(datapath);
			    Dataset<Row> predictions = ovrModel.transform(inputData)
					      .select("prediction", "label");
			    predictions.show();
			    //String  lable=predictions.select("prediction").collect().toString();
			    List<Row> list = predictions.takeAsList(1);
			    String lable=list.get(0).get(0).toString();
			    //System.out.println(list.get(0).get(0));
			   // System.out.println("*******************"+lable);
			    return lable;
	}
}
