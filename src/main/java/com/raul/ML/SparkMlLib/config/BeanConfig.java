package com.raul.ML.SparkMlLib.config;

import org.springframework.context.annotation.Configuration;


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
	
	
}
