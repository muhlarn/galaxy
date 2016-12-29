package co.za.merchant.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import co.za.merchant.constant.SymbolValues;
import co.za.merchant.util.FileUtil;
import co.za.merchant.util.RepeatedValueUtil;
import co.za.merchant.util.SubtractionRulesUtil;

/**
 * 
 * @author bay
 *
 */
public class MerchantConvertionApp {
	
	public static void main(String[] args) {
		runFromFile();
	}
	
	/**
	 * 
	 * @return
	 */
	public static String runFromFile() {
		
		String total = "";		
		try {
			
			List<String> inputTextList = new ArrayList<String>(); 
			List<String> inputQuestionList = new ArrayList<String>();
			FileUtil.setFileContentIntoList(inputTextList, inputQuestionList);
			
			Map<String, String> conversionMap = populateMapFromFileContent(inputTextList);
			total = getTotalValueFromMerchantValues(total, inputQuestionList,
					conversionMap);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("I have no idea what you are talking about");
			System.exit(0);
		} 
		
		return total;
	}

	private static Map<String, String> populateMapFromFileContent(
			List<String> inputTextList) {
		Map<String, String> conversionMap = new HashMap<String, String>();
		
		for(String sentence : inputTextList) {
			String[] globSplit = sentence.split(" ");
			String item = null;
			String amountLetter = null;
			
			if (globSplit.length == 3) {
				amountLetter = globSplit[2].trim();
				item = globSplit[0];
			} else {	
				int multiplier = addNumbers(conversionMap.get(globSplit[0]) + conversionMap.get(globSplit[1]));
				item = globSplit[2];
				int realValue = Integer.parseInt(globSplit[4]) / multiplier;
				amountLetter = realValue + "";
			}
			conversionMap.put(item, amountLetter); 
		}
		return conversionMap;
	}

	private static String getTotalValueFromMerchantValues(String total,
			List<String> inputQuestionList, Map<String, String> conversionMap) {
		String items;
		for(String question : inputQuestionList) {
			boolean isSkip = false;
			String[] questionSplit = question.split(" is ");
			items = questionSplit[1];

			for (String item : items.split(" ")) {
				String amountLetter = conversionMap.get(item);
				if (StringUtils.isNumeric(amountLetter)) {
					isSkip = true;
					int totalAmount = addNumbers(total);
					totalAmount = totalAmount*Integer.parseInt(amountLetter);
					System.out.println(items + "is " + totalAmount + " Credits");
					total = new String();
				} else {
					total = total + amountLetter;
				}
			}		
			if (!isSkip) {
				System.out.println(items + "is " + addNumbers(total));
				total = new String();
			}
		}
		return total;
	}
	
	public static int addNumbers(String amount) {
		
		String[] strValues = amount.split("");
		int total = 0;
		try {
			boolean hasAlreadySubtracted = false;
			List<Integer> amountList = new ArrayList<Integer>();
			populateListOfAmounts(strValues, amountList);
			
			int listSize = amountList.size();
			for(int count=0; count<listSize; count++) {
				
				int currentValue = amountList.get(count);
				int nextValue = 0;
				int previousValue = 0;
				
				nextValue = getNextValue(amountList, listSize, count, nextValue);				
				previousValue = getPreviousValue(amountList, count,
						previousValue);
				
				RepeatedValueUtil.isValueInRepeatedList(amountList, listSize, count, currentValue,
						nextValue, previousValue);				
				RepeatedValueUtil.isValueInNonRepeatedList(currentValue, nextValue);
				
				if (nextValue > currentValue) {
					if (SubtractionRulesUtil.isValidSubtraction(nextValue, currentValue) && !hasAlreadySubtracted) {
						total = total + SubtractionRulesUtil.subtractValue(nextValue, currentValue);
						hasAlreadySubtracted = true;
					} else {
						System.out.println(currentValue + " cannot be subtracted from " + nextValue);
						return 0;
					}
				} else if(currentValue > previousValue && count>0){
					//continue;
				} else {
					total = total + currentValue;
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("I have no idea what you are talking about");
			System.exit(0);
		}
		
		return total;
	}

	private static void populateListOfAmounts(String[] strValues,
			List<Integer> amountList) {
		for(String symbol: strValues) {
			amountList.add(SymbolValues.valueOf(symbol).getAmount());
		}
	}

	private static int getPreviousValue(List<Integer> amountList, int count,
			int previousValue) {
		if (count > 0) {
			previousValue = amountList.get(count-1);
		}
		return previousValue;
	}

	private static int getNextValue(List<Integer> amountList, int listSize,
			int count, int nextValue) {
		if (listSize > count+1) {
			nextValue = amountList.get(count+1);
		}
		return nextValue;
	}


}
