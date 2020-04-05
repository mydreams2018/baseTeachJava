package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Test {

	public static void main(String[] args) {

		Map<String, Integer> mpAll = new TreeMap<>();
		mpAll.put("one", 20);
		ArrayList<Object> arrayList = new ArrayList<Object>();
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(4);
		arrayList.add(5);
		arrayList.add(6);
		List<Object> subList = arrayList.subList(0, 6);
		
	}

}