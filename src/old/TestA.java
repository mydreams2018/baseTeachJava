package old;

public class TestA extends TestB{
	public static TestA a = new TestA();
	
	public TestA() {
		name="fsdfs";
	}
	
	private static String name = "kungreatA";
	 
	 String getNamea() {
		return name;
	}
	
	 public void runA() {
		super.run();
	 }
	 
	 public void run() {
		 System.out.println("runa");
	 }
}
		