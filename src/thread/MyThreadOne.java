package thread;

public class MyThreadOne extends Thread{
	MyThreadOne(String name){
		super(name);
	}
	
	@Override
	public void run() {
		try {
			System.out.println(this == Thread.currentThread());
			System.out.println("MyThreadOne:"+Thread.currentThread().getName());
			System.out.println("MyThreadOne:"+Thread.currentThread().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
