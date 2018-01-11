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

package com.log.utils;

import java.io.IOException;

// $example on$
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.OneVsRest;
import org.apache.spark.ml.classification.OneVsRestModel;
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
// $example off$
import org.apache.spark.sql.SparkSession;


/**
 * An example of Multiclass to Binary Reduction with One Vs Rest,
 * using Logistic Regression as the base classifier.
 * Run with
 * <pre>
 * bin/run-example ml.JavaOneVsRestExample
 * </pre>
 */
public class Classifier {
	
	public void buildmodel(String inpath,String outpath) throws IOException{
		  SparkSession spark = SparkSession
			      .builder()
			      .appName("JavaOneVsRestExample")
			      .master("local")
			      .getOrCreate();

			    // $example on$
			    // load data file.
			    Dataset<Row> inputData = spark.read().format("libsvm")
			      .load(inpath);

			    // generate the train/test split.
			    Dataset<Row>[] tmp= inputData.randomSplit(new double[]{0.8, 0.2});
			    Dataset<Row> train = tmp[0];
			    Dataset<Row> test = tmp[1];


			    // configure the base classifier.
			    LogisticRegression classifier = new LogisticRegression()
			      .setMaxIter(10)
			      .setTol(1E-6)
			      .setFitIntercept(true);

			    // instantiate the One Vs Rest Classifier.
			    OneVsRest ovr = new OneVsRest().setClassifier(classifier);

			    // train the multiclass model.
			    OneVsRestModel ovrModel = ovr.fit(train);
			    ovrModel.save(outpath);
			    // score the model on test data.
			    Dataset<Row> predictions = ovrModel.transform(test)
			      .select("prediction", "label");
			    //predictions.show(50);
			    // obtain evaluator.
			    MulticlassClassificationEvaluator evaluator = new MulticlassClassificationEvaluator()
			            .setMetricName("accuracy");


			    // compute the classification error on test data.
			    double accuracy = evaluator.evaluate(predictions);
			    System.out.println("Test Error = " + (1 - accuracy));
			    
			    // $example off$

			    spark.stop();
	}
	
  public static void main(String[] args) throws IOException {
	  	String path1 = "output/clean/CleanProfile1/part-r-00000";
	  	String path2 = "output/clean/CleanProfile2/part-r-00000";
	  	String path3 = "output/clean/CleanProfile3/part-r-00000";
	  	
	  	//book和runtime结合的标签
	  	String outpath1="model/profileBookOrRunTime";
	  	//sumbook>20 then 2  when sumbook >=8 then 1 else 0 
	  	String outpath2="model/profileBook";
	  	//allruntime>50 then 2  when allruntime >=20 then 1 else 0
	  	String outpath3="model/profileRuntime";
	  	Classifier classifier1 = new Classifier();
	  	Classifier classifier2 = new Classifier();
	  	Classifier classifier3 = new Classifier();
	  	
	  	classifier1.buildmodel(path1,outpath1);
	  	classifier2.buildmodel(path2,outpath2);
	  	classifier3.buildmodel(path3,outpath3);
  }

}
