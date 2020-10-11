package juc;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyDelay implements Delayed{

	private int comp;
	private long times;
	
	public MyDelay(int comp, long times) {
		super();
		this.comp = comp;
		this.times = times;
	}

	@Override
	public int compareTo(Delayed o) {
		MyDelay temp = (MyDelay)o;
		return comp-temp.comp;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return TimeUnit.MILLISECONDS.convert(times-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	@Override
	public String toString() {
		return comp+"";
	}

}
