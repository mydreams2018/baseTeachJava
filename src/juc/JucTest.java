package juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Predicate;


public class JucTest {
	static final LinkedBlockingDeque<String> AQ = new LinkedBlockingDeque<>(2);
	public static void main(String[] args) throws Exception {
		AQ.add("2");
		AQ.addFirst("1");
		System.out.println(AQ.pop());
		System.out.println(AQ.poll());
		System.out.println(AQ.take());
	}

}