package com.raul.ML.SparkMlLib.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.raul.ML.SparkMlLib.clustering.BiKMeansClustering;
import com.raul.ML.SparkMlLib.clustering.GMMClustering;
import com.raul.ML.SparkMlLib.clustering.IClusteringType;
import com.raul.ML.SparkMlLib.clustering.KMeansClustering;

@Component
public class ClusteringFactory {
	

	@Autowired
	KMeansClustering kMeansClustering;
	
	@Autowired
	GMMClustering gmmClustering;
	
	@Autowired
	BiKMeansClustering biKMeansClustering;
	
	public IClusteringType get(String appName)
	{
		switch (appName.toUpperCase()) {

		case "KMEANS":
			return kMeansClustering;
		case "GMM":
			return gmmClustering;
		case "BIKMEANS":
			return biKMeansClustering;
		default:
			throw new IllegalArgumentException("No driver available for " + appName);

		}
		
	}
}
