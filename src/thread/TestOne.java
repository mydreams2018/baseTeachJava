package thread;

import java.util.function.Supplier;

public class TestOne {
  
//	public static final ThreadLocal TH2 = new ThreadLocal();
	public static void main(String[] args) throws Exception {
		/*
		 * TH.set("main"); TH.set("main1"); TH2.set("main2"); VolatileTest ts = new
		 * VolatileTest(); Thread thread = new Thread(ts); thread.start();
		 * Thread.sleep(1000); TH2.remove();
		 * System.out.println(Thread.currentThread().getName()+":"+TH.get());
		 * System.out.println(Thread.currentThread().getName()+":"+TH2.get());
		 */
		ThreadLocal TH = null ;
		for(int x=0;x <5;x++) {
			TH = new ThreadLocal();
			TH.set(x);
			Thread.sleep(200);
		}
		TH.remove();
		System.gc();
		Thread currentThread = Thread.currentThread();
		System.out.println(currentThread);
	}

}
