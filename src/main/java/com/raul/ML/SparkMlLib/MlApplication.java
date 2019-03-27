package com.raul.ML.SparkMlLib;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.apache.spark.sql.functions.split;

@SpringBootApplication
public class MlApplication implements CommandLineRunner {
	@Autowired
	SparkSession sparkSession;

	String cancerdata = "file:///Users/rahulgupta/eclipse-workspace/SparkMlLib/src/main/resources/cancerData/seer1.txt";

	public static void main(String[] args) {
		System.out.println("Starting!!!");
		SpringApplication.run(MlApplication.class, args);
		System.out.println("Ending!!!");
	}

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Inside Run!!!");
		sparkSession.sparkContext().conf().setAppName("RaulSparkTesting");
		Dataset<String> cancerDataset = sparkSession.read().textFile(cancerdata).as(Encoders.STRING());

		System.out.println(cancerDataset.count());

		cancerDataset.printSchema();

		Dataset<Row> cancer = cancerDataset.withColumn("col1", split(cancerDataset.col("value"), "\\,").getItem(0))
				.withColumn("col2", split(cancerDataset.col("value"), "\\,").getItem(1))
				.withColumn("col3", split(cancerDataset.col("value"), "\\,").getItem(2));
		cancer.show();

		
		cancer = cancer.selectExpr("cast(col1 as int) col1", 
                "col2", 
                "cast(col3 as int) col3");
		cancer.show();
		
		cancer = cancer.filter(cancer.col("col1").$less("90"));
		
				//cast(DataTypes.IntegerType)).withColumn("col3", cancer.col("col3").cast(DataTypes.IntegerType));
		
				
		cancer.show();
		
		// cancerDataset.withColumn("_tmp", split(cancerDataset.col("value"), "
		// ")).select(
		// cancerDataset.col("_tmp").getItem(0).as("col1"),
		// cancerDataset.col("_tmp").getItem(1).as("col2"),
		// cancerDataset.col("_tmp").getItem(2).as("col3"));
		cancer.printSchema();
	}
}
