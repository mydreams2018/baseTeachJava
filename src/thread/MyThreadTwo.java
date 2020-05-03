package thread;

public class MyThreadTwo implements Runnable{

	public int num=0;

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"waitLock");
		addNum();
	}

	synchronized void addNum() {
			 System.out.println(Thread.currentThread().getName()+"getLock");
			 for(int n=0;n<100000;n++) {
					num++;
			 }
	}

}