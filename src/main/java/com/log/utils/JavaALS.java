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

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

// $example on$
import java.io.Serializable;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
// $example off$

public class JavaALS {

  // $example on$
  public static class Rating implements Serializable {
    private int userId;
    private int bookId;
    private float rating;
    private long timestamp;

    public Rating() {}

    public Rating(int userId, int bookId, float rating) {
      this.userId = userId;
      this.bookId = bookId;
      this.rating = rating;
    }

    public int getUserId() {
      return userId;
    }

    public int getbookId() {
      return bookId;
    }

    public float getRating() {
      return rating;
    }


    public static Rating parseRating(String str) {
      String[] fields = str.split(",");
      if (fields.length != 5) {
        throw new IllegalArgumentException("Each line must contain 4 fields");
      }
      int userId = Integer.parseInt(fields[0]);
      int bookId = Integer.parseInt(fields[3]);
      float rating = Float.parseFloat(fields[4]);
      return new Rating(userId, bookId, rating);
    }
  }
  // $example off$

  public static void main(String[] args) {
    SparkSession spark = SparkSession
      .builder()
      .appName("JavaALSExample")
      .master("local")
      .getOrCreate();

    // $example on$
    JavaRDD<Rating> ratingsRDD = spark
      .read().textFile("data/demo.csv").javaRDD()
      .map(new Function<String, Rating>() {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Rating call(String str) {
          return Rating.parseRating(str);
        }
      });
    Dataset<Row> ratings = spark.createDataFrame(ratingsRDD, Rating.class);
    Dataset<Row>[] splits = ratings.randomSplit(new double[]{0.8, 0.2});
    Dataset<Row> training = splits[0];
    Dataset<Row> test = splits[1];

    // Build the recommendation model using ALS on the training data
    ALS als = new ALS()
      .setMaxIter(10)
      .setRegParam(0.01)
      .setUserCol("userId")
      .setItemCol("bookId")
      .setRatingCol("rating");
    ALSModel model = als.fit(training);

    // Evaluate the model by computing the RMSE on the test data
    Dataset<Row> predictions = model.transform(test);

    RegressionEvaluator evaluator = new RegressionEvaluator()
      .setMetricName("rmse")
      .setLabelCol("rating")
      .setPredictionCol("prediction");
    Double rmse = evaluator.evaluate(predictions);
    System.out.println("Root-mean-square error = " + rmse);
    
 // Generate top 10 movie recommendations for each user
    Dataset<Row> userRecs = model.recommendForAllUsers(10);
    userRecs.show();
    //userRecs.toJavaRDD().coalesce(1).saveAsTextFile("data/userRecsData.txt");
    // Generate top 10 user recommendations for each movie
    Dataset<Row> movieRecs = model.recommendForAllItems(10);
    movieRecs.show();
    // $example off$
    spark.stop();
  }
}