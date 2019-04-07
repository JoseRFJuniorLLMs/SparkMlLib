package com.raul.ML.SparkMlLib.clustering;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Vector;

import scala.Tuple2;

public interface IClusteringType {
	public Tuple2<JavaRDD<Integer>, Vector[]> dataCenters(JavaRDD<Vector> data, int numClusters, int numIterations);	

}
