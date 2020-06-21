package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class VolatileTest implements Runnable {

	static ReadWriteLock lock = new ReentrantReadWriteLock();
	static Lock writeLock = lock.writeLock();
	static Lock readLock = lock.readLock();
	@Override
	public void run() {
		readLock.lock();
		try {
			ts();
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
//		System.out.println(Thread.currentThread().getName()+":end");
	}

	public static void ts() {
		writeLock.lock();
		try {
			System.out.println(Thread.currentThread().getName()+":ts");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
	}

}
