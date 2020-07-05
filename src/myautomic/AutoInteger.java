package myautomic;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.IntBinaryOperator;

public class AutoInteger implements Runnable{
	 static {
	        try {
	            MethodHandles.Lookup l = MethodHandles.lookup();
	            VALUE = l.findVarHandle(AutoInteger.class, "num", int.class);
	        } catch (ReflectiveOperationException e) {
	            throw new ExceptionInInitializerError(e);
	        }
	    }
	private static final VarHandle VALUE;
	private volatile int num;
	

	@Override
	public void run() {
		for(int x=0;x<100000;x++) {
			VALUE.getAndAdd(this,2);
		}
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
