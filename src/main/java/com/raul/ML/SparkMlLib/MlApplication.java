package com.raul.ML.SparkMlLib;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.raul.ML.SparkMlLib.Analyzer.ClusterSurvivalRate;
import com.raul.ML.SparkMlLib.clustering.IClusteringType;
import com.raul.ML.SparkMlLib.config.ClusteringFactory;
import com.raul.ML.SparkMlLib.converter.CancerDataPreProcessor;
import com.raul.ML.SparkMlLib.converter.RowToVector;

import scala.Tuple2;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Vector;

import java.io.Serializable;

import java.util.List;

@SpringBootApplication
public class MlApplication implements CommandLineRunner, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	SparkSession sparkSession;

	@Autowired
	CancerDataPreProcessor cancerDataPreProcessor;

	@Autowired
	RowToVector rowToVector;

	@Autowired
	ClusterSurvivalRate clusterSurvivalRate;

	@Autowired
	ClusteringFactory clusteringFactory;

	// private static final String PATH =
	// "file:///C:/Testing/dataset/p1";

	String cancerdata = "file:///C:/Testing/dataset/seer5Params.txt";

	public static void main(String[] args) {
		SpringApplication.run(MlApplication.class, args);
	}

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		sparkSession.sparkContext().conf().setAppName("CancerSurviavlRatePredictor");
		Dataset<String> cancerRawset = sparkSession.read().textFile(cancerdata).as(Encoders.STRING());

		Dataset<Row> cancerDataset = cancerDataPreProcessor.preProcessor(cancerRawset);

		// cancerDataset.toJavaRDD().saveAsTextFile(PATH);

		JavaRDD<Vector> data = rowToVector.RDDmapper(cancerDataset);

		data.cache(); // data` should be cached for high performance, because this is an iterative
						// algorithm.

		// int numClusters = 6;
		int numIterations = 30;

		IClusteringType driver = clusteringFactory.get("BIKMEANS");

		// Rely on concrete subclasses for this method.
		for (int i = 6; i < 13; i++) {
			Tuple2<JavaRDD<Integer>, Vector[]> pair = driver.dataCenters(data, i, numIterations);
			JavaRDD<Integer> clusterIndexes = pair._1();

			// Bring all data to driver node for displaying results.
			List<Row> collectedTempData = cancerDataset.collectAsList();
			List<Integer> collectedClusterIndexes = clusterIndexes.collect();
			clusterSurvivalRate.survivalRateCal(collectedTempData, collectedClusterIndexes);
		}
	}

}
