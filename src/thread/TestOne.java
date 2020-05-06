package thread;

public class TestOne {

	public static void main(String[] args) throws Exception {
		MyThreadTwo two = new MyThreadTwo();
		Thread thread1 = new Thread(two);
		Thread thread2 = new Thread(two);
		thread1.start();
		thread2.start();
//		thread1.interrupt();//中断此线程,必须在线程启动后才启作用
//		thread1.join();
	}

}
