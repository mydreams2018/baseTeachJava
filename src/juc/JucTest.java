package juc;

import java.util.concurrent.SynchronousQueue;

public class JucTest {
	static final SynchronousQueue<String> AQ = new SynchronousQueue<>(true);
	public static void main(String[] args) throws Exception {
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(150);
					AQ.put("3");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}.start();
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(100);
					AQ.put("2");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}.start();
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(200);
					System.out.println(AQ.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}.start();
		AQ.put("1");
//		System.out.println(AQ.take());
//		System.out.println(AQ.take());
//		System.out.println(AQ.take());
//		System.out.println(AQ.take());
//		System.out.println(AQ.poll(3, TimeUnit.SECONDS));
	}

}