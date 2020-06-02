package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest implements Runnable{
	
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		if(lock.tryLock()) {
			try {
				System.out.println(Thread.currentThread().getName());
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
		System.out.println(Thread.currentThread().getName()+":end");
	}

}
