package thread;


public class TestOne {

	public static void main(String[] args) throws Exception {
		VolatileTest ts = new VolatileTest();
		Thread thread = new Thread(ts);
		thread.start();
		
//		Thread thread1 = new Thread(ts);
//		thread1.start();
//		VolatileTest.ts();
	}

}