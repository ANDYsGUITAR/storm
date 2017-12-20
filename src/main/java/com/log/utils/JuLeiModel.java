package com.log.utils;

import java.util.List;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
// $example on$
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
// $example off$

public class JuLeiModel {
  public static void main(String[] args) {


    
    SparkConf conf = new SparkConf().setAppName("JavaKMeansExample").setMaster("local");;
    JavaSparkContext jsc = new JavaSparkContext(conf);

    // $example on$
    // Load and parse data
    String path = "output/clean/JuLei";
    JavaRDD<String> data = jsc.textFile(path);
    JavaRDD<Vector> parsedData = data.map(
      new Function<String, Vector>() {
        public Vector call(String s) {
          String[] sarray = s.split(",");
          double[] values = new double[sarray.length];
          for (int i = 0; i < sarray.length; i++) {
            values[i] = Double.parseDouble(sarray[i]);
          }
          return Vectors.dense(values);
        }
      }
    );
    parsedData.cache();

    // Cluster the data into two classes using KMeans
//    int runs=10;
//    int numClusters = 4;
//    int numIterations = 500;
//    KMeansModel clusters = KMeans.train(parsedData.rdd(), numClusters, numIterations,runs);

//    System.out.println("Cluster centers:");
//    System.out.println("*******************************************"+
//    										"*******************************************"+
//    										"*******************************************");
//    System.out.println("");
//    System.out.println("");
//    for (Vector center: clusters.clusterCenters()) {
//      System.out.println(" " + center);
//    }
//    System.out.println("");
//    System.out.println("");
//    System.out.println("*******************************************"+
//											"*******************************************"+
//											"*******************************************");

   
    KMeansModel sameModel = KMeansModel.load(jsc.sc(),
      "target/org/apache/spark/JavaKMeansExample/KMeansModel");
    
    // $example off$
    System.out.println("sameModel centers:");
    for (Vector center: sameModel.clusterCenters()) {
      System.out.println(" " + center);
    }
    
   // System.out.println(parsedData.map(v -> v.toString()+" belong to cluster :"+ clusters.predict(v)).collect());
    List<String> dataList=parsedData.map(v -> v.toString()+" belong to cluster :"+ sameModel.predict(v)).collect();
    long cluster[]=new long[4];
    System.out.println("*********************************************************");
    for(int i=0;i<dataList.size();i++){
    	//System.out.println(dataText.get(i));
    	String[] temp=dataList.get(i).split(":");
    	int clusterNum=Integer.parseInt(temp[1]);
    	//System.out.println(clusterNum);
        if(clusterNum==0) {cluster[0]++;}
        else if(clusterNum==1) {cluster[1]++;}
        else if(clusterNum==2) {cluster[2]++;}
        else if(clusterNum==3) {cluster[3]++;}
    }
    System.out.println("*********************************************************");
    for(int i=0;i<cluster.length;i++){
    	System.out.println(cluster[i]);
    }
//System.out.println("Prediction of (2,1): "+ clusters.predict(Vectors.dense(new double[]{2,1})));
//    System.out.println("Prediction of (10.1, 9.1, 11.1): "+ clusters.predict(Vectors.dense(new double[]{10.1, 9.1, 11.1})));
    jsc.stop();
  }
}