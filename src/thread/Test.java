package thread;

public class Test {

	public static void main(String[] args) {
		//返回对当前正在执行的线程对象的引用。
//		System.out.println(Thread.currentThread().getName());
//		System.out.println(Thread.currentThread().getId());
		MyThreadOne t1 = new MyThreadOne("kungreat");
//		t1.setPriority(1);
		t1.start();
		MyThreadTwo t2 = new MyThreadTwo();
		Thread thread2 = new Thread(t2);
//		thread2.setPriority(10);
		thread2.start();
	}

}