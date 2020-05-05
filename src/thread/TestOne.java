package thread;

public class TestOne {

	public static void main(String[] args) throws Exception {
		MyThreadTwo two = new MyThreadTwo();
		MyThreadGroup mg = new MyThreadGroup("custom");
		Thread thread1 = new Thread(mg,two);
		thread1.start();
		Thread.sleep(1000);
	}

}
