package base;

/*1:不能直接实例化 (new XXX....)
2:接口抽像概念高于抽像类 接口只能继承接口(多继承extends关键字) 不能继承类
3:一个类(普通类,抽像类)可以实现多个接口(关键字implements)
  如果当前类存在继承关系 则实现接口要写在继承之后
  例 public class a extends b implements c,d
4:接口的字段 全是 public static final 修饰的 默认
5:接口的方法全是 public abstract 修饰的 默认*/


/*相同点:
1:都位于继承的顶端,用于被其它子类或接口(实现或继承)
2:都不能直接实例化
3:都可以定意抽像方法(普通类继承或实现必须重写这些抽像方法)
不同点:
1:接口没有构造方法(构造器)
2:一个类只能继承一个直接超类(普通类或抽像类)却可以实现多个接口
java8以后 接口的概念已经发生了改变*/

public interface ITestA extends ITestB{
	 String name = "aaagreat";
	 
	 void run();
	 
	public default void run1() {
		
		System.out.println(this);
	};
}
