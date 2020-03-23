package base;

//懒汉式  多线程不安全
public class Singleton2 {
	private Singleton2() {
	}
	
	private static Singleton2  SINGLE = null;
	
	public static Singleton2 getSingleton() {
		if (SINGLE == null) {
			SINGLE = new Singleton2();
		}
		return SINGLE;
	}
}
