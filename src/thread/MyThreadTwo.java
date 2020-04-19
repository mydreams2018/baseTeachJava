package thread;

public class MyThreadTwo implements Runnable{

	@Override
	public void run() {
		try {
			for(long x=0;x<1000;x++) {
				System.out.println("MyThreadTwo:end");
				Thread.sleep(1);
			}
//			System.out.println("MyThreadTwo:"+Thread.currentThread().getName());
//			System.out.println("MyThreadTwo:"+Thread.currentThread().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}