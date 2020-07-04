package myautomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.IntBinaryOperator;

public class AutoInteger implements Runnable{
	private AtomicIntegerArray at = new AtomicIntegerArray(10);
	public AtomicIntegerArray getAt() {
		return at;
	}

	public void setAt(AtomicIntegerArray at) {
		this.at = at;
	}

	@Override
	public void run() {
		at.compareAndSet(9, 0, 50);
	}

}
