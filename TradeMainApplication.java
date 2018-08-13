package com.trade.sample;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TradeMainApplication {

	public static void main(String args[]) {
	
		System.out.println("In Trade application");
		ProcessTradeFile p = new ProcessTradeFile();
		CSVFileUtility fileReadWrite = new CSVFileUtility();
		
		//Accept input/output file location
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter input file complete path including file name.");  

		File file = new File(sc.nextLine());
		
		System.out.println("Enter output file complete path including file name.");
		String outputFileName = sc.nextLine();
		
		Map<String, List<TradeObj>> tradeObjMap = fileReadWrite.readFileContent(file);
		
		//Compute values
		List<TradeComputedObject> comptedValueList = p.computeTradeValues(tradeObjMap);
		
		//Write CSV file
		fileReadWrite.writeFile(comptedValueList, outputFileName);
		
	}
}
