package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest implements Runnable{
	
	static ReentrantLock lock = new ReentrantLock();
	Condition newCondition = lock.newCondition();
	Condition newCondition1 = lock.newCondition();
	@Override
	public void run() {
		lock.lock();
			try {
//				if("Thread-0".equals(Thread.currentThread().getName())
//						|| "Thread-1".equals(Thread.currentThread().getName())
//						) {
//					newCondition.await();
//				}
				Thread.sleep(1000);
				System.out.println(lock.isLocked());
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
//		System.out.println(Thread.currentThread().getName()+":end");
	}
	//可重入，计数器
	public void ts() {
		if(lock.tryLock()) {
			try {
				System.out.println("ts:"+lock.getHoldCount());//2
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
	}
}
