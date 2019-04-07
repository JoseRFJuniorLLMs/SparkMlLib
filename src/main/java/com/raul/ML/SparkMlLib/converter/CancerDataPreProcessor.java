package com.raul.ML.SparkMlLib.converter;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.split;
import static org.apache.spark.sql.functions.when;

import java.io.Serializable;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import com.raul.ML.SparkMlLib.dto.CancerListingCriteria;
import com.raul.ML.SparkMlLib.model.CancerDataMapper;

public class CancerDataPreProcessor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Dataset<Row> preProcessor(Dataset<String> cancerRawData) {

		System.out.println("Inside PreProcessor");
		Dataset<Row> cancer = cancerRawData
				.withColumn(CancerListingCriteria.REGIONALNODESPOSITIVE.name(), split(cancerRawData.col("value"), "\\,").getItem(0))
				.withColumn(CancerListingCriteria.DERIVEDAJCCSTAGEGROUP.name(), split(cancerRawData.col("value"), "\\,").getItem(1))
				.withColumn(CancerListingCriteria.TUMORSIZE.name(), split(cancerRawData.col("value"), "\\,").getItem(2))
				.withColumn(CancerListingCriteria.AGE.name(), split(cancerRawData.col("value"), "\\,").getItem(3))
				.withColumn(CancerListingCriteria.SURVIVALMONTHS.name(), split(cancerRawData.col("value"), "\\,").getItem(4))
				.selectExpr(
						"cast(" + CancerListingCriteria.REGIONALNODESPOSITIVE.name() + " as int) "
								+ CancerListingCriteria.REGIONALNODESPOSITIVE.name(),
						CancerListingCriteria.DERIVEDAJCCSTAGEGROUP.name(),
						"cast(" + CancerListingCriteria.TUMORSIZE.name() + " as int) " + CancerListingCriteria.TUMORSIZE.name(),
						CancerListingCriteria.AGE.name(),
						"cast(" + CancerListingCriteria.SURVIVALMONTHS.name() + " as int) " + CancerListingCriteria.SURVIVALMONTHS.name());

		cancer.show();
		/*
		 * cancer =
		 * cancer.filter(cancer.col(CancerDB.REGIONALNODESPOSITIVE.name()).$less("90"))
		 * .filter(cancer.col(CancerDB.TUMORSIZE.name()).$less("996"));
		 */
		// .and(cancer.col(CancerDB.REGIONALNODESPOSITIVE.name()).$greater("0")));

		System.out.println("After filter");
		cancer.show();
		CancerDataMapper.setCancerStageVal();

		cancer = cancer.withColumn(CancerListingCriteria.DERIVEDAJCCSTAGEGROUP.name(),
				when(col(CancerListingCriteria.DERIVEDAJCCSTAGEGROUP.name()).isin(CancerDataMapper.stage0.toArray()), 0)
						.when(col(CancerListingCriteria.DERIVEDAJCCSTAGEGROUP.name()).isin(CancerDataMapper.stage1.toArray()), 1)
						.when(col(CancerListingCriteria.DERIVEDAJCCSTAGEGROUP.name()).isin(CancerDataMapper.stage2.toArray()), 2)
						.when(col(CancerListingCriteria.DERIVEDAJCCSTAGEGROUP.name()).isin(CancerDataMapper.stage3.toArray()), 3)
						.when(col(CancerListingCriteria.DERIVEDAJCCSTAGEGROUP.name()).isin(CancerDataMapper.stage4.toArray()), 4)
						.otherwise(5));

		cancer.show();

		CancerDataMapper.setCancerAgeVal();
		cancer = cancer.withColumn(CancerListingCriteria.AGE.name(),
				when(col(CancerListingCriteria.AGE.name()).isin(CancerDataMapper.age0.toArray()), 0)
						.when(col(CancerListingCriteria.AGE.name()).isin(CancerDataMapper.age1.toArray()), 1)
						.when(col(CancerListingCriteria.AGE.name()).isin(CancerDataMapper.age2.toArray()), 2)
						.when(col(CancerListingCriteria.AGE.name()).isin(CancerDataMapper.age3.toArray()), 3)
						.when(col(CancerListingCriteria.AGE.name()).isin(CancerDataMapper.age4.toArray()), 4).otherwise(5));

		cancer = cancer.filter(cancer.col(CancerListingCriteria.REGIONALNODESPOSITIVE.name()).$less("90"))
				.filter(cancer.col(CancerListingCriteria.TUMORSIZE.name()).$less("990"))
				.filter(cancer.col(CancerListingCriteria.DERIVEDAJCCSTAGEGROUP.name()).$less("5"))
				.filter(cancer.col(CancerListingCriteria.AGE.name()).$less("5"));

		//cancer = cancer.filter(cancer.col(CancerDB.DERIVEDAJCCSTAGEGROUP.name()).$less("5"));
		return cancer;

	}
}
