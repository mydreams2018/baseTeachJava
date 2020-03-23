package base;

public class PersonBoy extends Person {

	public PersonBoy() {
		System.out.println(this);
	}
	@Override
	public String getSex() {
		return "男的";
	}
	public void eat() {
		System.out.println("吃饭");
	}
}
