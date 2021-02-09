package juc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class JucTest {
	static MyPhaser semaphore = new MyPhaser(5);

	public static void main(String[] args) throws Exception {
		for(int x=0;x<6;x++) {
			new Thread(new Runnable() {
				@Override 
				public void run() {
					System.out.println(semaphore.arriveAndAwaitAdvance());
				}
			}).start();
		}
//327681 327680 4295294981
//		System.out.println(327681 & 0xffff);
//		System.out.println(327680 & 0xffff);
//		int ct =(int)4295294981L;
//		System.out.println(ct & 0xffff);
	}

}