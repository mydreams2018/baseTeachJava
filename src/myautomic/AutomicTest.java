package myautomic;

public class AutomicTest {

	public static void main(String[] args) throws Exception {
		AutoInteger ts = new AutoInteger();
		Thread thread = new Thread(ts);
		thread.start();
		Thread thread1 = new Thread(ts);
		thread1.start();
		thread1.join();
		thread.join();
		System.out.println(ts.getNum());
	}

}
