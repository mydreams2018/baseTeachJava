package thread;

public class MyThreadTwo implements Runnable{

	public int num=0;

	@Override
	public void run() {
		addNum();
	}

	synchronized void addNum() {
		for(int n=0;n<100000;n++) {
			num++;
		}
	}
}