package com.trade.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVFileUtility {
	
	//Delimiter used in CSV file
			private static final String COMMA_DELIMITER = ",";
			private static final String NEW_LINE_SEPARATOR = "\n";
			
	//CSV file header
			private static final String FILE_HEADER = "Symbol,Exchange,Min Price,Avg Price,Max Price,Total Qty";
		
		public Map<String, List<TradeObj>> readFileContent(File file) {

			Map<String, List<TradeObj>> tradeObjMap = new HashMap<String, List<TradeObj>>();
			BufferedReader fileReader = null;

			try {
				// Create the file reader
				fileReader = new BufferedReader(new FileReader(file));

				// Read the CSV file header to skip it
				fileReader.readLine();

				String line;

				// Read the file line by line starting from the second line
				while ((line = fileReader.readLine()) != null) {
					String[] arrStr = line.split(COMMA_DELIMITER);
					TradeObj tObj = new TradeObj();
					tObj.setSymbol(arrStr[0]);
					tObj.setExchange(arrStr[1]);
					tObj.setPrice(Float.parseFloat(arrStr[2]));
					tObj.setQuantity(Integer.parseInt(arrStr[3]));

					if (!tradeObjMap.containsKey(tObj.getSymbol())) {
						List<TradeObj> objList = new ArrayList<TradeObj>();
						objList.add(tObj);
						tradeObjMap.put(tObj.getSymbol(), objList);
					} else if (tradeObjMap.containsKey(tObj.getSymbol())) {
						List<TradeObj> objList = tradeObjMap.get(tObj.getSymbol());
						objList.add(tObj);
						tradeObjMap.put(tObj.getSymbol(), objList);
					}

				}
				for (Map.Entry<String, List<TradeObj>> entry : tradeObjMap.entrySet()) {
					System.out.println(entry.getKey() + " : " + entry.getValue());
				}

			} catch (IOException e) {
				System.out.println("Error in TradeFileReader!");
				e.printStackTrace();
			} finally {
				try {
					fileReader.close();
				} catch (IOException e) {
					System.out.println("Error while closing fileReader !");
					e.printStackTrace();
				}
			}
			return tradeObjMap;
		}
		
		
		public void writeFile(List<TradeComputedObject> comptedValueList, String outputFileName) {
			FileWriter fileWriter = null;
			
			try {
				fileWriter = new FileWriter(outputFileName);

				//Write the CSV file header
				fileWriter.append(FILE_HEADER.toString());
				
				//Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);
				
				//Write trade list
				for (TradeComputedObject tradeObj : comptedValueList) {
					fileWriter.append(tradeObj.getSymbol());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tradeObj.getExchange());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tradeObj.getMinimum());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tradeObj.getAverage());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(tradeObj.getMaximum());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(tradeObj.getTotal()));
					fileWriter.append(NEW_LINE_SEPARATOR);
				}

				System.out.println("Trade CSV file was created successfully !!!");
				
			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !");
				e.printStackTrace();
			} finally {
				
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !");
	                e.printStackTrace();
				}
				
			}
		}

}
