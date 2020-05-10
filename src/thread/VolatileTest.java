package thread;

public class VolatileTest implements Runnable{
	private Integer food;
	private boolean around = false;
	
	@Override
	public void run() {
		try {
			for(int x=0;x<10;x++) {
				synchronized (this) {
					while(around) {
						this.wait();
					}
					food=x;
					around = true;
					System.out.println(Thread.currentThread().getName()+":生产"+food);
					this.notify();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void eat() throws Exception {
		for(int x=0;x<10;x++) {
			synchronized (this) {
				while(!around) {
					this.wait();
				}
				System.out.println(Thread.currentThread().getName()+":消费"+food);
				around = false;
				this.notify();
			}
		}
	}
}
