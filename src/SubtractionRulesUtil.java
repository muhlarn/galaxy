import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SubtractionRulesUtil {
	
	public static boolean isValidSubtraction(Integer subtractFrom, Integer subtractValue) {
		boolean isValidSubtraction = false;
		
		Map<Integer, List<Integer>> subtractMap = getSubtractionRules();
		for (Integer key : subtractMap.keySet()) {
			if (subtractValue == key && subtractMap.get(key).contains(subtractFrom)) {
				isValidSubtraction =  true;
			}
		}
		return isValidSubtraction;		
	}
	
	public static Map<Integer, List<Integer>> getSubtractionRules() {
		Map<Integer, List<Integer>> subtractMap = new HashMap<Integer, List<Integer>>();
		subtractMap.put(1, getOneSubtractRules());
		subtractMap.put(10, getTenSubtractRules());
		subtractMap.put(100, getHundrerdSubtractRules());
		
		return subtractMap;
	}
	
	public static List<Integer> getOneSubtractRules() {
		List<Integer> oneValueList = new ArrayList<Integer>();
		oneValueList.add(5);
		oneValueList.add(10);		
		return oneValueList;
	}
	
	public static List<Integer> getTenSubtractRules() {
		List<Integer> tenValueList = new ArrayList<Integer>();
		tenValueList.add(50);
		tenValueList.add(100);
		
		return tenValueList;		
	}
	
	public static List<Integer> getHundrerdSubtractRules() {
		List<Integer> hundrerdValueList = new ArrayList<Integer>();
		hundrerdValueList.add(500);
		hundrerdValueList.add(1000);
		
		return hundrerdValueList;
	}
	
	public static int subtractValue(int subtractFrom, int subtractValue) {
		int total = subtractFrom - subtractValue;
		
		return total;
	}
	
	public static boolean isRepeatedValues(int previousValue, int currentValue, int nextValue) {		
		boolean isRepeated = false;
		if(previousValue == currentValue && currentValue == nextValue) {
			isRepeated = true;
		}
		
		return isRepeated;
	}

}
