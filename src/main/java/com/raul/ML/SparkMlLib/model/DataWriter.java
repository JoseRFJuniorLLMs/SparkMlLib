package com.raul.ML.SparkMlLib.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class DataWriter {

	public void writeto(String data) {
		try {
			//String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

			System.out.println("Received req in show5 ip: ");
			File file = new File("C:\\Testing\\dataset\\event5.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

			StringBuilder sb = new StringBuilder();
			//sb.append(System.lineSeparator() + timeStamp + System.lineSeparator());
			sb.append(data);

			writer.write(sb.toString());

			writer.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
