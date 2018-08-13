package com.trade.sample;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProcessTradeFile {

	public List<TradeComputedObject> computeTradeValues(Map<String, List<TradeObj>> tradeObjMap) {

		// get list of trades based on symbol here.
		List<TradeComputedObject> computedObjList = new ArrayList<TradeComputedObject>();
		boolean isExchangeNeeded= true;

		for (String symbolKey : tradeObjMap.keySet()) {

			List<TradeObj> matchingTradeList = tradeObjMap.get(symbolKey);
			if (!matchingTradeList.isEmpty()) {
				isExchangeNeeded = false;
				getcomputedValues(matchingTradeList, computedObjList, isExchangeNeeded);
				
				Map<String, List<TradeObj>> tradesByExchange = matchingTradeList.stream().collect(
				        Collectors.groupingBy(TradeObj::getExchange));
				
				if(!tradesByExchange.isEmpty()) {
					for (List<TradeObj> exchangeValuesList : tradesByExchange.values()) {
						isExchangeNeeded = true;
						getcomputedValues(exchangeValuesList, computedObjList, isExchangeNeeded);
					}
				}
				
			}	

		} // main for loop ends
		for (int i=0 ; i < computedObjList.size() ; i++) {
			System.out.println("Final List: "+computedObjList.get(i));
		}
		
		return computedObjList;
		
	}

	private void getcomputedValues(List<TradeObj> matchingTradeList, List<TradeComputedObject> computedObjList, boolean flag) {
		double average = 0.0;
		int totalQuantity = 0;
		double computedPrice = 0.0;
		double min = 0.0;
		double max = 0.0;
		DecimalFormat df = new DecimalFormat("0.00");  
		
		// compute average, min, max and quantity based on symbol.
			min = matchingTradeList.get(0).getPrice();
			max = matchingTradeList.get(0).getPrice();

			for (TradeObj tradeObj : matchingTradeList) {
				totalQuantity = tradeObj.getQuantity() + totalQuantity;
				computedPrice = tradeObj.getPrice() + computedPrice;
				min = tradeObj.getPrice() <= min ? tradeObj.getPrice() : min;
				max = tradeObj.getPrice() >= max ? tradeObj.getPrice() : max;
			}

			average = computedPrice / matchingTradeList.size();
			TradeComputedObject finalObject = new TradeComputedObject();
			finalObject.setAverage(df.format(average));
			finalObject.setMaximum(df.format(max));
			finalObject.setMinimum(df.format(min));
			finalObject.setTotal(totalQuantity);
			finalObject.setSymbol(matchingTradeList.get(0).getSymbol());
			if(flag) {
				finalObject.setExchange(matchingTradeList.get(0).getExchange());
			}
			else {
				finalObject.setExchange("");
			}
			computedObjList.add(finalObject);	
	}
}


