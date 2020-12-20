package juc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class JucTest {
	static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new Runnable() {
		
		@Override
		public void run() {
			System.out.println("成团");
			
		}
	});

	public static void main(String[] args) throws Exception {

		ConcurrentHashMapTest concurrentHashMapTest = new ConcurrentHashMapTest();
		concurrentHashMapTest.start();
		ConcurrentHashMapTest concurrentHashMapTest1 = new ConcurrentHashMapTest();
		concurrentHashMapTest1.start();
		ConcurrentHashMapTest concurrentHashMapTest2 = new ConcurrentHashMapTest();
		concurrentHashMapTest2.start();
		ConcurrentHashMapTest concurrentHashMapTest3 = new ConcurrentHashMapTest();
		concurrentHashMapTest3.start();
		for(int x=0;x<11;x++) {
			ConcurrentHashMapTest temp = new ConcurrentHashMapTest();
			temp.start();
		}

	}
	
	static class ConcurrentHashMapTest extends Thread{

		@Override
		public void run() {
			try {
				cyclicBarrier.await();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}