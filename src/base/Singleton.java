package base;

//饿汉式  多线程安全
public class Singleton {
	private Singleton() {
	}
	
	private static Singleton  SINGLE = new Singleton();
	
	public static Singleton getSingleton() {
		return SINGLE;
	}
}
