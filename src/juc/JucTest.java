package juc;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;

public class JucTest {
	static final SynchronousQueue<String> AQ = new SynchronousQueue<>();
	public static void main(String[] args) throws Exception {
		new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(200);
					AQ.add("1");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}.start();
		System.out.println(AQ.take());
//		AQ.put("4");
//		System.out.println(AQ.take());
//		System.out.println(AQ.take());
//		System.out.println(AQ.take());
//		System.out.println(AQ.take());
//		System.out.println(AQ.poll(3, TimeUnit.SECONDS));
	}

}