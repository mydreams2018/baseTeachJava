package juc;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class JucTest {
	static final DelayQueue<MyDelay> AQ = new DelayQueue<>();
	public static void main(String[] args) throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		AQ.put(new MyDelay(10,currentTimeMillis+2000));
		AQ.put(new MyDelay(5,currentTimeMillis+5000));
		AQ.put(new MyDelay(20,currentTimeMillis+3000));
		AQ.put(new MyDelay(8,currentTimeMillis+10000));
		System.out.println(AQ.take());
		System.out.println(AQ.take());
		System.out.println(AQ.take());
		System.out.println(AQ.take());
	}

}