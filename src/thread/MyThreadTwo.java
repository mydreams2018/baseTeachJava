package thread;

public class MyThreadTwo implements Runnable{

	@Override
	public void run() {
		int a = 1/0;
//		System.out.println(Thread.currentThread().getName()+":end");
	}

}