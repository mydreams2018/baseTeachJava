package thread;

public class Test {

	public static void main(String[] args) {
		//返回对当前正在执行的线程对象的引用。
		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().getId());
		MyThreadOne t1 = new MyThreadOne("kungreat");
		t1.setName("one");
		t1.start();
//		MyThreadTwo t2 = new MyThreadTwo();
//		new Thread(t2).start();
	}

}