package thread;

public class MyThreadTwo implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(500);
//			System.out.println("MyThreadTwo:"+Thread.currentThread().getName());
//			System.out.println("MyThreadTwo:"+Thread.currentThread().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+":end");
	}

}