import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;

public class MapDemo {
	
	public static void main(String[] args) {
		
//		HashMap<Integer, String> map = new HashMap<Integer, String>();
		// Integer behaves as a key.
		
//		HashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
		// If we use LinkedHashMap ==> it will retain the insertion order.
		
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		// when giving null to key in treeMap it will give NPE ==> When storing treeMap compares and we can't compare null value.

		
		map.put(10, "Raghav");
		// 10 object ==> Integer Object ==> we are not using new keyword??
		// interpret as a new Integer(10) ==> AutoBoxing ==> conversion of primitive to Object
		// UnBoxing ==> Object to Primitive
		
		//map.put(null, null);
		// "null" ==> It is a string.
		map.put(40, null);
		// null as a value is allowed in all maps.
		//map.put(null, "Tester");
		// null a key allowed in all maps except TreeMap ==> Throws NPE.
		
		map.put(13, "Raghav");
		map.put(16, "Raghav");
		map.put(8, "Raghav");
		// key value pair stored across buckets same as HashSet. There is no order. <Key,Value> ==> Entry
		// we can insert duplicate values
		
		System.out.println(map);

		map.put(10, "RaghavNew");
		// If we repeat the key it will replace old value with new value.
		// we cannot insert duplicate key, it is unique.
		

		System.out.println(map);
		
		//extract only keys
		map.keySet().forEach(e->System.out.println(e));
		Set<Integer> allKeys = map.keySet();
		
		// Key present of not ==> containKey method
		System.out.println(map.containsKey(40));
		System.out.println(map.containsKey(50));
		
		// retrieve all the values
		System.out.println(map.values());
		
		// traverse the map using forEach method
		map.forEach((k,v)->System.out.println(k + " " + v));
	}

}
