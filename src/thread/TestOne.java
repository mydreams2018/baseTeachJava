package thread;

public class TestOne {

	public static void main(String[] args) throws Exception {
		MyThreadTwo two = new MyThreadTwo();
		MyThreadTwo two1 = new MyThreadTwo();
		Thread thread1 = new Thread(two);
		Thread thread2 = new Thread(two1);
		thread1.start();
		thread2.start();
		synchronized("kdiek") {
			Thread.sleep(8000);
		}
		thread1.join();
		thread2.join();
		System.out.println(two.num);
	}

}
