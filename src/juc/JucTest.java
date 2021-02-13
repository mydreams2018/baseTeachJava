package juc;

import java.util.concurrent.*;

public class JucTest {
	static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5,10L,
			TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(8));

	public static void main(String[] args) throws Exception {
		for(int x=0;x<1;x++){
			threadPoolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		System.out.println("main-end");
	}

}