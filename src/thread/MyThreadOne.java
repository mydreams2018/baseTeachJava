package thread;

public class MyThreadOne extends Thread{
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
//			System.out.println(this == Thread.currentThread());
//			System.out.println("MyThreadOne:"+Thread.currentThread().getName());
//			System.out.println("MyThreadOne:"+Thread.currentThread().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+":end");
	}
	
}
