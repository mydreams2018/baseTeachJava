package thread;

/*
*linum 2020年3月7日
*/
public class PossibleReordering {
	static int x, y = 0;
	static int a, b = 0;

	public static void main(String[] args) throws Exception {
		for (int num = 0;;) {
			num++;
			x=0;y=0;a=0;b=0;
			// x=0  y=1 (1次)thread -> thread2 |  y=0 x =1 (261)thread2 -> thread | x=1 y=1 (632957)
			// x=0 y = 0   ( 50000  158283 920578)
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					a = 1;
					x = b;
				}
			});
			Thread thread2 = new Thread(new Runnable() {
				@Override
				public void run() {
					b = 1;
					y = a;
				}
			});
			thread.start();
			thread2.start();
			thread.join();
			thread2.join();
			if(x==0 && y==0) {
				System.out.println(num);
				return;
			}
		}
	}
}
