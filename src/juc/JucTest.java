package juc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class JucTest {
	static ConcurrentHashMap<String,Integer> cur;
	public static void main(String[] args) throws Exception {
		cur = new ConcurrentHashMap<>();
		cur.put("one",1);
		ConcurrentHashMapTest concurrentHashMapTest = new ConcurrentHashMapTest();
		concurrentHashMapTest.start();
		ConcurrentHashMapTest concurrentHashMapTest2 = new ConcurrentHashMapTest();
		concurrentHashMapTest2.start();
		concurrentHashMapTest.join();
		concurrentHashMapTest2.join();
		System.out.println(cur.get("one"));
	}
	
	static class ConcurrentHashMapTest extends Thread{

		@Override
		public void run() {
			for(int x=0;x<10000;x++) {
				Integer integer = cur.get("one");
				integer++;
				cur.put("one", integer);
			}
		}
		
	}

}