package thread;

public class MyThreadOne extends Thread{
	MyThreadOne(String name){
		super(name);
	}
	
	@Override
	public void run() {
		
		try {
			for(long x=0;x<1000;x++) {
				Thread.yield();
				System.out.println("MyThreadOne:end");
				Thread.sleep(1);
			}
//			System.out.println(this == Thread.currentThread());
//			System.out.println("MyThreadOne:"+Thread.currentThread().getName());
//			System.out.println("MyThreadOne:"+Thread.currentThread().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
