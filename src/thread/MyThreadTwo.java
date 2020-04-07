package thread;

public class MyThreadTwo implements Runnable{

	@Override
	public void run() {
		try {
			System.out.println("MyThreadTwo:"+Thread.currentThread().getName());
			System.out.println("MyThreadTwo:"+Thread.currentThread().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}