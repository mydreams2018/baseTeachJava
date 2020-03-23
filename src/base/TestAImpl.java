package base;

public class TestAImpl extends Animal{
	
	public TestAImpl() {
		System.out.println("construct");
	}
	
	private Integer age;
	{
		System.out.println(age);
		System.out.println("对象代码块");
	}
	

	static {
		System.out.println("one");
		//System.out.println(name); 错误
		setName("bigben");
	}
	public static String name;
	private static TestAImpl ta = new TestAImpl();
	public static String st = "dfsdf";
	
	public static String getName() {
		
		return name;
	}
	
	public static void setName(String name) {
		TestAImpl.name = name;
	}
	
	
}
