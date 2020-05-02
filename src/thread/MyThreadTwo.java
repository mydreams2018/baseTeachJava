package thread;

public class MyThreadTwo implements Runnable{

	private volatile int num=0;
	@Override
	public void run() {
		try {
			while(getNum() < 100) {
				Thread.sleep(1000);
				addNum();
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized void addNum() {
		if(getNum() < 100) {
			num++;
		}
	}
	int getNum() {
		return num;
	}
}