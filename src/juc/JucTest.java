package juc;

import java.util.concurrent.ConcurrentSkipListMap;

public class JucTest {
	static ConcurrentSkipListMap<String,Integer> cur;
	public static void main(String[] args) throws Exception {
		cur = new ConcurrentSkipListMap<>();
		cur.put("one", 1);
		cur.put("two", 2);
		cur.put("three", 3);
		cur.put("four", 4);
		cur.put("five", 5);
		cur.put("erser", 6);
		cur.put("fgdfg", 7);
		cur.put("er", 8);
		cur.put("99", 9);
		cur.put("dsse", 10);
	}

}