package base;

/*方法覆写:
1:方法名称-参数列表 必需要一致
2:子类方法返回值类型 必需是父类 返回值类型的(子类或者相同类型)
3:子类方法抛出的异常比父类 抛出的异常 要小或者相等(子类或者相同类型)
4:子类方法访问权限要比父类  更大或者相等*/

public class Dog extends Animal{
	
	public void eat() {
		System.out.println("dog-吃饭");
	}
}
