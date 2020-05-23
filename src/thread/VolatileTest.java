package thread;

public class VolatileTest implements Runnable{
	
	@Override
	public void run() {
		try {
//			System.out.println(Thread.currentThread().getName()+":"+TestOne.TH2.get());
//			System.out.println(Thread.currentThread().getName()+":"+TestOne.TH.get());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
