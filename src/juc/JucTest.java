package juc;

import java.util.concurrent.*;

public class JucTest {
	final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5,10L,
			TimeUnit.SECONDS,new ArrayBlockingQueue<>(8));

	public static void main(String[] args) throws Exception {
		System.out.println(threadPoolExecutor.allowsCoreThreadTimeOut());
		threadPoolExecutor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
		System.out.println("end");
	}

}