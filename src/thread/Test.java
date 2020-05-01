package thread;

import java.lang.Thread.UncaughtExceptionHandler;

public class Test {

	public static void main(String[] args) throws Exception {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(t.getName()+e.getMessage()+":uncaughtException");
			}
			
		});
		//返回对当前正在执行的线程对象的引用。
//		System.out.println(Thread.currentThread().getName());
//		System.out.println(Thread.currentThread().getId());
		MyThreadOne t1 = new MyThreadOne();
		t1.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(t.getName()+e.getMessage()+":thread");
			}
			
		});
//		t1.setPriority(1);
		t1.start();
		MyThreadTwo t2 = new MyThreadTwo();
		Thread thread2 = new Thread(t2);
//		thread2.setPriority(10);
//		thread2.setDaemon(true);
		thread2.start();
		Thread.sleep(100);
//		System.out.println(Thread.activeCount());
//		Thread.dumpStack();
//		Thread tds[] = new Thread[Thread.activeCount()];
//		System.out.println(Thread.enumerate(tds));
//		for(int x=0;x<tds.length;x++) {
//			System.out.println(tds[x].getState());
//		}
//		Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
//		System.out.println(allStackTraces.size());
//		for(Map.Entry<Thread, StackTraceElement[]> ek:allStackTraces.entrySet()) {
//			System.out.println(ek.getKey().getName());
//		}
//		t1.join();
		System.out.println(Thread.getDefaultUncaughtExceptionHandler());
		System.out.println(t1.getUncaughtExceptionHandler());
		Thread.onSpinWait();
	}

}