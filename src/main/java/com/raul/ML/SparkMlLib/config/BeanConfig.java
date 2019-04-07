package com.raul.ML.SparkMlLib.config;

import org.springframework.context.annotation.Configuration;

import com.raul.ML.SparkMlLib.Analyzer.ClusterSurvivalRate;
import com.raul.ML.SparkMlLib.clustering.BiKMeansClustering;
import com.raul.ML.SparkMlLib.clustering.GMMClustering;
import com.raul.ML.SparkMlLib.clustering.KMeansClustering;
import com.raul.ML.SparkMlLib.converter.CancerDataPreProcessor;
import com.raul.ML.SparkMlLib.converter.RowToVector;
import com.raul.ML.SparkMlLib.model.DataWriter;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;;

@Configuration
public class BeanConfig {
	
	@Bean
	public SparkConf sparkConf(SparkConfigurations sparkConfigurations)
	{
		SparkConf sparkConf = new SparkConf();
		sparkConfigurations.getConfig().forEach(sparkConf::set);
		return sparkConf;
	}
	
	@Bean
	public SparkSession sparkSession(SparkConf sparkConf)
	{
		return SparkSession.builder().config(sparkConf).getOrCreate();
	}
	
	@Bean
	public org.apache.hadoop.conf.Configuration conf(SparkSession sparkSession)
	{
		return sparkSession.sparkContext().hadoopConfiguration();
	}
	
	@Bean
	public CancerDataPreProcessor cancerDataPreProcessor() {
		return new CancerDataPreProcessor();	
	}
	
	@Bean
	public RowToVector rowToVector()
	{
		return new RowToVector();
	}
	
	@Bean
	public DataWriter dataWriter() {
		return new DataWriter();
	}
	
	@Bean
	public KMeansClustering kMeansClustering() {
		return new KMeansClustering();
	}
	
	@Bean
	public GMMClustering gmmClustering()
	{
		return new GMMClustering();
	}
	
	@Bean
	public BiKMeansClustering biKMeansClustering()
	{
		return new BiKMeansClustering();
	}
	
	@Bean
	public ClusterSurvivalRate clusterSurvivalRate()
	{
		return new ClusterSurvivalRate();
	}
	
	@Bean
	public ClusteringFactory clusteringFactory()
	{
		return new ClusteringFactory();
	}
}
