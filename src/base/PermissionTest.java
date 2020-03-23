package base;

//外部类
public class  PermissionTest {
	
//	private String nameOut="kungreat"; 
	private static String name="kungreat"; 
//	private Integer age=18; 
	
	public static void say() {
		System.out.println("PermissionTest");
	}
	
//	public String getNameOut() {
//		return nameOut;
//	}
//	
//	public void setNameOut(String name) {
//		nameOut=name;
//	}
	
	static class psIn implements PermissionTestInIn{
		public void setName() {
			System.out.println("setName");
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}

     interface PermissionTestInIn{
		public default void nameOut(String name) {
			//setNameOut(name);
		}
		
		void run();
	}
	
	//非静态内部类 
	public class PermissionTestIn{
	
		//private static final Integer nameIn = 55; 
		private Integer age=18; 
		
//		public String getName() {
//			PermissionTest.this.nameOut="fsdfs";
//			this.age=28;
//			PermissionTest.this.age=32;
//			say();
//			return getNameOut();
//		}
//		public void setName(String name) {
//			setNameOut(name);
//		}
	}
	
	
}
