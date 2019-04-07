package com.raul.ML.SparkMlLib.converter;

import java.io.Serializable;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class RowToVector implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JavaRDD<Vector> RDDmapper(Dataset<Row> cancerDataset)
	{
		System.out.println("RowToVector RDDmapper");
		
		@SuppressWarnings("serial")
		JavaRDD<Vector> data = cancerDataset.toJavaRDD().map(new Function<Row, Vector>() {
			public Vector call(Row row) throws Exception {
				double[] values = new double[4];
				for (int i = 0; i < 4; i++)
					values[i] = Double.parseDouble(row.get(i).toString());
				return Vectors.dense(values);
            }
		});
		return data;
	}

}
