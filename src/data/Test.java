package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Test {

	public static void main(String[] args) {
//		Arrays.asList(a);
		List<Integer> list = new ArrayList<>();
		list.add(556);
		list.add(320);
		list.add(15);
		list.add(15);
		list.add(15);
		list.add(245);
		list.add(547);
		for(int x=0;x < list.size() ;) {
			if(15 ==list.get(x)) {
				list.remove(x);
			}else {
				x++;
			}
		}
		System.out.println(list);
	}

}
