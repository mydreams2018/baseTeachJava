package thread;

public class MyThreadOne extends Thread{
	
	@Override
	public void run() {
			int a = 1/0;
//			
		System.out.println(Thread.currentThread().getName()+":end");
	}
	
}
