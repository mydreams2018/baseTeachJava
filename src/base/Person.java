package base;

// ctrl+shift /
// ctrl /

/*1:使用abstract 修饰的类称为抽像类
2:abstract final 只能二选一修饰同一个类
3:不能创建实例 不能new 一个抽像类(只能通过子类继承后 new 子类)
4:可以有抽像方法(使用abstract 修饰的方法 没有方法体的称为抽像方法
		 抽像方法不能用 private final static 修饰)
5:如果抽像类存在抽像方法 那么子类在继承后一定要@Override*/
public abstract class Person extends Object{
	
	protected Person(){
		System.out.println(this);
		System.out.println(super.toString());
	}
	
	public abstract String getSex();
	
	public void  out() {
		System.out.println();
	}
	
}
