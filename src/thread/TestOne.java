package thread;

public class TestOne {

	public static void main(String[] args) throws Exception {
		MyThreadTwo two = new MyThreadTwo();
		Thread thread1 = new Thread(two);
		Thread thread2 = new Thread(two);
		Thread thread3 = new Thread(two);
		Thread thread4 = new Thread(two);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		System.out.println(two.num);
	}

}
