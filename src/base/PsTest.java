package base;

import base.PermissionTest.PermissionTestIn;
import base.PermissionTest.PermissionTestInIn;
import base.PermissionTest.psIn;

public class PsTest {

	public static void main(String[] args) {
//		PermissionTest ps = new PermissionTest();
//		ps.say();
//		System.out.println(ps.getNameOut());
		
//		PermissionTestIn psIn = ps.new PermissionTestIn();
//		psIn.setName("bigben");
//		System.out.println(ps.getNameOut());
		//new PermissionTest().new PermissionTestIn();
		
		//匿名内部类   【 接口内部类默认是static 修饰的】
		PermissionTestInIn ps = new PermissionTest.PermissionTestInIn() {
			public void setName() {
				System.out.println("setName");
			}
			@Override
			public void run() {
				setName();
			}
		};
		System.out.println(ps);
		ps.run();
		
		
		psIn psIn = new PermissionTest.psIn();
		psIn.setName();
		System.out.println(ps);
		
		Linum li = new Linum();

		new Animal() ;
		
		new ITestB.Inner() {
			
		};
	}

}
