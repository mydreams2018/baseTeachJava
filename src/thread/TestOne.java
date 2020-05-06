package thread;

public class TestOne {

	public static void main(String[] args) throws Exception {
		MyThreadTwo two = new MyThreadTwo();
		MyThreadGroup mg = new MyThreadGroup("custom");
		Thread thread1 = new Thread(mg,two);
		thread1.start();
		thread1.interrupt();//中断此线程,必须在线程启动后才启作用
		thread1.join();
	}

}
