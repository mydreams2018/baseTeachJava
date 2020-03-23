package test;

import java.lang.reflect.Method;

import base.AnonymousTest;
import base.ITestA;

public class TestA {
	//局部内部类==方法区内部类
	private Integer age = 18;
	
	public void getInner() {
		int num = 55;
		class Inner{
			int numIn = num;
			public void say() {
				System.out.println(age);
			}
		}
		Inner in = new Inner();
		in.say();
	}
	
	public static void main(String[] args) throws Exception {
		EnumTest em[] = {EnumTest.ONE};
		EnumTest.Inner in = EnumTest.ONE.new Inner();
		System.out.println(EnumTest.ONE.name());
		AnonymousTest at = new AnonymousTest() {
			
				public void run() {
					System.out.println("匿名内部类run");
				}
				@Override
				public void say() {
					System.out.println("匿名内部类say");
				}
			};
			
			at.say();
			System.out.println(at);
			
			Method[] declaredMethods = at.getClass().getDeclaredMethods();
			for(int x=0 ;x <declaredMethods.length;x++ ) {
				System.out.println(declaredMethods[x].getName());
				if("run".equals(declaredMethods[x].getName())) {
					declaredMethods[x].invoke(at);
				}
			}
			
			
//			
//			AnonymousTestEx ex = new AnonymousTestEx();
//			ex.run();
//			System.out.println(ex);
		}
	

}
