package com.raul.ML.SparkMlLib.Analyzer;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;

import com.raul.ML.SparkMlLib.model.DataWriter;

public class ClusterSurvivalRate {
	
	@Autowired
	DataWriter dataWriter;
	
	public void survivalRateCal(List<Row> collectedTempData, List<Integer> collectedClusterIndexes) {

		// Total number of data points.
		System.out.println("\nTotal # patients: " + collectedClusterIndexes.size());

		// For each cluster center, this data structure will contain the corresponding
		// survival months in an ArrayList<Integer>.
		// For each data point in the cluster, the corresponding survival months will be
		// a distinct element in the ArrayList<Integer>.
		Hashtable<Integer, ArrayList<Integer>> cl = new Hashtable<Integer, ArrayList<Integer>>();
		int j = 0;

		for (Integer i : collectedClusterIndexes) {
			// This ArrayList<Integer> stores individual survival months for each data
			// point.
			ArrayList<Integer> srvMnths = cl.get(i);
			if (srvMnths == null) {
				srvMnths = new ArrayList<Integer>();
				cl.put(i, srvMnths);
			}

			// For a data point, get the corresponding survival months, 'stage group' and
			// 'regional nodes positive'.
			String tempRow = collectedTempData.get(j++).toString();
			StringTokenizer strTok = new StringTokenizer(tempRow, ",");
			String regNodes = strTok.nextToken();
			regNodes = regNodes.replace("[", "");
			String stage = strTok.nextToken();
			String tumor = strTok.nextToken();
			String age = strTok.nextToken();
			String survivalMonths = strTok.nextToken();
			survivalMonths = survivalMonths.replace("]", "");
			try {
				srvMnths.add(Integer.parseInt(survivalMonths));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error !!!" + survivalMonths + "  ->" + tempRow);
				survivalMonths = "1";
			}
		}

		// Display average survival months and # data points in each cluster.
		Enumeration<Integer> keys = cl.keys();
		while (keys.hasMoreElements()) {
			Integer i = keys.nextElement();
			System.out.println("\nCluster " + i);
			System.out.println("# points: " + cl.get(i).size());
			System.out.println("Average survival months: " + avg(cl.get(i)));
			
			dataWriter.writeto("\nCluster,"+i+",points,"+cl.get(i).size()+",survivalMonths,"+avg(cl.get(i)));
		}
		
		dataWriter.writeto("\n--------------------------------------------------------\n");
	}
	
	private static double avg(ArrayList<Integer> in) {

		if (in == null || in.size() == 0) {
			return -1.;
		}

		double sum = 0.;
		for (Integer i : in) {
			sum += i;
		}
		return (sum / in.size());
	}



}
