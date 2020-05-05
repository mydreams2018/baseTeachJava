package thread;

public class MyThreadGroup extends ThreadGroup{

	public MyThreadGroup(String name) {
		super(name);
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(t.getName()+":MyThreadGroup-EXCEPTION");
	}

	
}
