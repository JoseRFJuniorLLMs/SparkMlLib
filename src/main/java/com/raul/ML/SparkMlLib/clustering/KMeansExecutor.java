package com.raul.ML.SparkMlLib.clustering;

import java.io.File;
import java.io.Serializable;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class KMeansExecutor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String PATH="file:///C:/Testing/dataset/";


	public JavaRDD<Vector> RDDmapper(Dataset<Row> cancerDataset)
	{
		System.out.println("KMeans RDDmapper");
		
		@SuppressWarnings("serial")
		JavaRDD<Vector> data = cancerDataset.toJavaRDD().map(new Function<Row, Vector>() {
			public Vector call(Row row) throws Exception {
				double[] values = new double[4];
				for (int i = 0; i < 4; i++)
					values[i] = Double.parseDouble(row.get(i).toString());
				return Vectors.dense(values);
            }
		});
		
		deleteDirectory(PATH+"p2");
		//data.saveAsTextFile(PATH+"p2");
		return data;
	}
	
	boolean deleteDirectory(String directoryToBeDeleted) {
		File deleteDir = new File(directoryToBeDeleted);
		File[] allContents = deleteDir.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				deleteDirectory(file.getPath());
			}
		}
		return deleteDir.delete();
	}
}
