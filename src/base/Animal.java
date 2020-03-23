package base;

/*泛化: 将不同子类的共性抽像成父类的过程
特化: 在原有父类的基础上加入自已的独有特性
规则: 父类放共性 子类放个性*/
// 所有的类 都是Object 的子类  直接-间接的方式
public class Animal extends Object implements Cloneable {
	public  String age="19";
	private String anim="";
	
	static {
		System.out.println("Animal");
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}

	public void eat() {
		System.out.println("aninmal");
	}
	
	public void getThis() {
		this.eat();
	}

}
