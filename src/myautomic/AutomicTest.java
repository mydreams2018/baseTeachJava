package myautomic;

public class AutomicTest {

	public static void main(String[] args) throws Exception {
		AutoInteger ts = new AutoInteger();
		Thread thread = new Thread(ts);
		thread.start();
		thread.join();
		System.out.println(ts.getAt().get().getName());
	}

}
