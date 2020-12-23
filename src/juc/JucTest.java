package juc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class JucTest {
	static Semaphore semaphore = new Semaphore(2);

	public static void main(String[] args) throws Exception {
		semaphore.release();
		semaphore.release();
		ConcurrentHashMapTest concurrentHashMapTest = new ConcurrentHashMapTest();
		concurrentHashMapTest.start();
//		semaphore.drainPermits();
		ConcurrentHashMapTest concurrentHashMapTest1 = new ConcurrentHashMapTest();
		concurrentHashMapTest1.start();
		ConcurrentHashMapTest concurrentHashMapTest2 = new ConcurrentHashMapTest();
		concurrentHashMapTest2.start();
		ConcurrentHashMapTest concurrentHashMapTest3 = new ConcurrentHashMapTest();
		concurrentHashMapTest3.start();
		Thread.sleep(2000);
		System.out.println(semaphore.availablePermits());
//		for(int x=0;x<6;x++) {
//			SemTest temp = new SemTest();
//			temp.start();
//		}
//		Thread.sleep(2000);
//		System.out.println(semaphore.availablePermits());
	}
	
	static class ConcurrentHashMapTest extends Thread{

		@Override
		public void run() {
				if(semaphore.tryAcquire()) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
					semaphore.release();
				}else {
					System.out.println("没有许可证");
				}
			
		}
		
	}
	static class SemTest extends Thread{

		@Override
		public void run() {
			try {
				semaphore.acquire();
				System.out.println(Thread.currentThread().getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}