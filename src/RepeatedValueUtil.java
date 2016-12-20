import java.util.ArrayList;
import java.util.List;


public class RepeatedValueUtil {
	

	public static boolean isInRepeatedList(Integer repeatValue) {
		List<Integer> repeatList = new ArrayList<Integer>();
		populateRepeatList(repeatList); 
		boolean isInRepeatList = false;
		if(repeatList.contains(repeatValue)) {
			isInRepeatList  = true;
		}
		return isInRepeatList;
	}
	
	private static void populateRepeatList(List<Integer> repeatList) {
		repeatList.add(1000);
		repeatList.add(100);
		repeatList.add(10);
		repeatList.add(1);		
	}
	
	public static boolean isNonRepeatedValues(int currentValue, int nextValue) {
		
		boolean isRepeated = false;
		if(currentValue == nextValue) {
			isRepeated = true;
		}
		
		return isRepeated;
	}
	
	public static boolean isInNonRepeatedList(Integer repeatValue) {
		List<Integer> nonRepeatList = new ArrayList<Integer>();
		populateNonRepeatList(nonRepeatList); 
		boolean isInNonRepeatList = false;
		if(nonRepeatList.contains(repeatValue)) {
			isInNonRepeatList  = true;
		}
		return isInNonRepeatList;
	}	
	
	private static void populateNonRepeatList(List<Integer> nonRepeatList) {
		nonRepeatList.add(500);
		nonRepeatList.add(50);
		nonRepeatList.add(5);
	}
	

	public static void isValueInNonRepeatedList(int currentValue, int nextValue) {
		if (RepeatedValueUtil.isInNonRepeatedList(currentValue)) {
			if (RepeatedValueUtil.isNonRepeatedValues(currentValue, nextValue)) {
				System.out.println(currentValue + " cannot be repeated at all");
			}
		}
	}

	public static void isValueInRepeatedList(List<Integer> amountList,
			int listSize, int count, int currentValue, int nextValue,
			int previousValue) {
		
		if (RepeatedValueUtil.isInRepeatedList(currentValue)) {
			if (SubtractionRulesUtil.isRepeatedValues(previousValue, currentValue, nextValue)) {
				if(listSize > count+2) {
					int lastRepeatCheck = amountList.get(count+2);
					if (SubtractionRulesUtil.isRepeatedValues(currentValue, nextValue, lastRepeatCheck)) {
						System.out.println(currentValue + " is repeated more than 3 times in a row");
					}
				}
			}
		}
	}

}
