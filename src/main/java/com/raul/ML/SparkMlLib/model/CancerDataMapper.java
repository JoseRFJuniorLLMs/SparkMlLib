	package com.raul.ML.SparkMlLib.model;

import java.util.ArrayList;
import java.util.List;

public class CancerDataMapper {
	
	public static List<String> stage0=new ArrayList<>();	
	public static List<String> stage1=new ArrayList<>();	
	public static List<String> stage2=new ArrayList<>();	
	public static List<String> stage3=new ArrayList<>();
	public static List<String> stage4=new ArrayList<>();	
	
	public static List<String> age0=new ArrayList<>();	
	public static List<String> age1=new ArrayList<>();	
	public static List<String> age2=new ArrayList<>();	
	public static List<String> age3=new ArrayList<>();
	public static List<String> age4=new ArrayList<>();	
	
	
	public static void setCancerAgeVal()
	{
		System.out.println("Inside setCancerStageVal");
		age0.add("00 years");
		age0.add("01-04 years");
		age0.add("05-09 years");
		age0.add("10-14 years");
		age0.add("15-19 years");
		
		age1.add("20-24 years");
		age1.add("25-29 years");
		age1.add("30-34 years");
		age1.add("35-39 years");
		
		age2.add("40-44 years");
		age2.add("45-49 years");
		age2.add("50-54 years");
		age2.add("55-59 years");
		
		age3.add("60-64 years");
		age3.add("65-69 years");
		age3.add("70-74 years");
		age3.add("75-79 years");
		
		age4.add("80-84 years");
		age4.add("85+ years");
		
	}
	
	
	public static void setCancerStageVal()
	{
		System.out.println("Inside setCancerStageVal");
		stage0.add("0");
		stage0.add("0a");
		stage0.add("0is");
		
		
		
		stage1.add("I");
		stage1.add("INOS");
		stage1.add("IA");
		stage1.add("IA1");
		stage1.add("IA2");
		stage1.add("IB");
		stage1.add("IB1");
		stage1.add("IB2");
		stage1.add("IC");
		stage1.add("IS");
		stage1.add("ISA");
		stage1.add("ISB");
		stage1.add("IEA");
		stage1.add("IEB");
		stage1.add("IE");
		
		stage2.add("II");
		stage2.add("IINOS");
		stage2.add("IIA");
		stage2.add("IIB");
		stage2.add("IIC");
		stage2.add("IIEA");
		stage2.add("IIEB");
		stage2.add("IIE");
		stage2.add("IISA");
		stage2.add("IISB");
		stage2.add("IIS");
		stage2.add("IIESA");
		stage2.add("IIESB");
		stage2.add("IIES");
		
		stage3.add("III");
		stage3.add("IIINOS");
		stage3.add("IIIA");
		stage3.add("IIIB");
		stage3.add("IIIC");
		stage3.add("IIIEA");
		stage3.add("IIIEB");
		stage3.add("IIIE");
		stage3.add("IIISA");
		stage3.add("IIISB");
		stage3.add("IIIS");
		stage3.add("IIIESA");
		stage3.add("IIIESB");
		stage3.add("IIIES");
		
		stage4.add("IV");
		stage4.add("IVNOS");
		stage4.add("IVA");
		stage4.add("IVB");
		stage4.add("IVC");
	}
	
	

}
