package thread;

public class MyThreadTwo implements Runnable{

	private static String ALOCK = "A";
	@Override
	public void run() {
//		System.out.println(Thread.currentThread().getName()+"waitLock");
		try {
			addNumA();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	static void addNumA() throws Exception {
//		 System.out.println(Thread.interrupted());//测试当前线程是否已被中断。会清除中断标识
//		 System.out.println(Thread.currentThread().isInterrupted());//测试当前线程是否已被中断
//		 Thread.sleep(100);
		 synchronized(ALOCK) {
//			 Thread.sleep(100);
			 ALOCK = "B";
			 for(int x=0;x<6;x++) {
				 Thread.sleep(1000);
				 System.out.println(Thread.currentThread().getName()+":"+x);
			 }
		 }
//		 System.out.println(Thread.holdsLock(ALOCK));//当且仅当当前线程在指定对象上持有监视器锁时才返回true。
	}

	
}