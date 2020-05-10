package thread;

public class VolatileTest implements Runnable{

	
	@Override
	public void run() {
		while(TestOne.isDone) {
//			System.out.println("dd");
		}
	}
	
}
