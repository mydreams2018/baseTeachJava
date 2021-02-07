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
	static MyPhaser semaphore = new MyPhaser(8);

	public static void main(String[] args) throws Exception {
		for(int x=0;x<8;x++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(!semaphore.isTerminated()){
						semaphore.arriveAndAwaitAdvance();
						try {
							if(!semaphore.isTerminated()){
								Thread.sleep((long)(Math.random()*10));
								System.out.println(Thread.currentThread().getName()+":"
										+semaphore.getPhase());
							}else {
								System.out.println("Terminated");
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
//		semaphore.forceTermination();
//		System.out.println(semaphore.getPhase());
	}
	


}