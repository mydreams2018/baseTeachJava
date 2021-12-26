package base;

import java.beans.JavaBean;

//外部类
@JavaBean(description = "AAAAA")
public class  PermissionTest {

	public PermissionTest(){
		System.out.println("空构造器");
	}

	@Deprecated(since = "con2021-12-26")
	public PermissionTest(@Deprecated(since = "parm2021-12-26") String name,@Deprecated(since = "age2021-12-26")String age){
		System.out.println("构造器:"+name+age);
	}
	
//	private String nameOut="kungreat"; 
	private static String name="kungreat"; 
//	private Integer age=18; 
	
	public static void say() {
		System.out.println("PermissionTest");
	}
	
	public String getNameOut() {
		return "nameOut";
	}
//	
//	public void setNameOut(String name) {
//		nameOut=name;
//	}
	
	public static class psIn implements PermissionTestInIn{
		public void setName() {
			System.out.println("setName");
		}
		@Override
		public void run() {
			
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
