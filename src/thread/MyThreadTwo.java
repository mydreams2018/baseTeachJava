package thread;

public class MyThreadTwo implements Runnable{

	public static int num=0;
	private static String mk = "kdiek";
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"waitLock");
		addNum();
	}

	static void addNum() {
		 synchronized(mk) {
			 System.out.println(Thread.currentThread().getName()+"getLock");
			 for(int n=0;n<100000;n++) {
					num++;
			 }
		 }
		
	}

}