package thread;

public class MyThreadTwo implements Runnable{

	public static int num=0;
	private static String ALOCK = "A";
	private static String BLOCK = "B";
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"waitLock");
		try {
			addNumA();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void addNumA() throws Exception {
		 synchronized(ALOCK) {
			Thread.sleep(100);
			addNumB();
		 }
	}
	static void addNumB() throws Exception {
		 synchronized(BLOCK) {
			 Thread.sleep(100);
			 addNumA();
		 }
		
	}
	
	

}