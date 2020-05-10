package thread;

public class TestOne {
	public volatile static boolean isDone = true;
	
	public static void main(String[] args) throws Exception {
		VolatileTest ts = new VolatileTest();
		Thread thread = new Thread(ts);
		thread.start();
		ts.eat();
	}

}
