package juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Predicate;


public class JucTest {
	static final ArrayBlockingQueue<String> AQ = new ArrayBlockingQueue<>(2);
	public static void main(String[] args) throws Exception {
		AQ.add("1");
		AQ.add("2");
		
		System.out.println(AQ.poll());
		System.out.println(AQ.poll());
		System.out.println(AQ.take());
	}

}