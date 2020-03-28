package data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class Test {

	public static void main(String[] args) {
		/*
		 * Map<String,Integer> mpAll = new HashMap<>(); mpAll.put("dfs", 2);
		 * mpAll.put("tgfwo", 3);
		 * 
		 * Map<String,Integer> mp = new HashMap<>(); mp.put("one", 1);
		 * mp.putIfAbsent("one", 10); mp.putAll(mpAll); mp.compute("one", new
		 * BiFunction() {
		 * 
		 * @Override public Object apply(Object t, Object u) { System.out.println(t);
		 * System.out.println(u); return u; } }); System.out.println(mp);
		 */
		Map<String,Integer> temp = new HashMap<String,Integer>();
		temp.put("ten", 100);
		temp.put("nine", 90);
		
		Map<String,Integer> mpAll = new MyHashMap<>();
		mpAll.put("dfs", 200);
		mpAll.put("dfgdfs", 20);
		mpAll.put("1212", 30);
		mpAll.put("5645", 50);
		mpAll.put("15", 5000);
		mpAll.putAll(temp);

		for(String s : mpAll.keySet()) {
			System.out.println(s);
		}
	}

}
