package thread;

public class TestOne {

	public static void main(String[] args) throws Exception {
		MyThreadTwo two = new MyThreadTwo();
		Thread thread1 = new Thread(two);
		thread1.start();
		MyThreadTwo.addNumB();
	}

}
