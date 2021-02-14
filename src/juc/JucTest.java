package juc;

import java.util.concurrent.*;

public class JucTest {
	final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5,2L,
			TimeUnit.SECONDS,new ArrayBlockingQueue<>(8),new MyRejectedExecutionHandler());

	public static void main(String[] args) throws Exception {
		for(int x=0;x<14;x++){
			threadPoolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
//		threadPoolExecutor.allowCoreThreadTimeOut(true);
		threadPoolExecutor.shutdown();
		System.out.println(threadPoolExecutor.allowsCoreThreadTimeOut());
	}

}