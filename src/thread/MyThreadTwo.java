package thread;

public class MyThreadTwo implements Runnable{

	public static int num=0;
	private static String ALOCK = "A";
	private static String BLOCK = "B";
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
		 Thread thread = new Thread() {

			@Override
			public void run() {
				for(int x=0;x<10;x++) {
					 System.out.println(x);
				}
			}
			 
		 };
		 thread.start();
		 System.out.println(Thread.interrupted());//测试当前线程是否已被中断。会清除中断标识
//		 System.out.println(Thread.currentThread().isInterrupted());//测试当前线程是否已被中断
//		 Thread.sleep(100);
//		 synchronized(ALOCK) {
//			 for(int x=0;x<1000;x++) {
////				 if(Thread.currentThread().isInterrupted() && x > 5) {
////					 break;
////				 }
//				
//			 }
//			 
//		 }
		 thread.join();
//		 System.out.println(Thread.holdsLock(ALOCK));//当且仅当当前线程在指定对象上持有监视器锁时才返回true。
	}

	
}