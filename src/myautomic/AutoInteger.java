package myautomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.IntBinaryOperator;

public class AutoInteger implements Runnable{
	private String name;
	private AtomicReference<AutoInteger> at = new AtomicReference<AutoInteger>();
	public AtomicReference<AutoInteger> getAt() {
		return at;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAt(AtomicReference<AutoInteger> at) {
		this.at = at;
	}

	@Override
	public void run() {
		AutoInteger a = new AutoInteger();
		a.name="one";
		at.compareAndSet(null, a);
		AutoInteger b = new AutoInteger();
		a.name="two";
		at.compareAndSet(a, b);
		at.compareAndSet(b, a);
	}

}
