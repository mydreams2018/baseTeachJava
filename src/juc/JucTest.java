package juc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class JucTest {
	static CountDownLatch countDown = new CountDownLatch(1);
	static ConcurrentHashMap<String,Integer> cur;
	public static void main(String[] args) throws Exception {
		cur = new ConcurrentHashMap<>();
		cur.put("one",1);
		ConcurrentHashMapTest concurrentHashMapTest = new ConcurrentHashMapTest();
		concurrentHashMapTest.start();
		countDown.await(2,TimeUnit.SECONDS);
		System.out.println(cur.get("one"));
	}
	
	static class ConcurrentHashMapTest extends Thread{

		@Override
		public void run() {
			try {
				Thread.sleep(3000);
				cur.put("one",3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			countDown.countDown();
		}
		
	}

}