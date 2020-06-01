package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest implements Runnable{
	
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			try {
				lock.unlock();
			}catch (Exception e) {
				
			}
		}
		System.out.println("edddddddddddddddddddddddnd");
		System.out.println("edddddddddddddddddddddddddddddddnd");
	}

}
