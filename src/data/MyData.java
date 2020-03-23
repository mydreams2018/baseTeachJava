package data;

public class MyData {
	private Integer number;

	public MyData(int number) {
		this.number = number;
	}
	
	@Override
	public boolean equals(Object obj) {
		return toString().equals(obj.toString());
	}
	
	@Override
	public String toString() {
		return number.toString();
	}
	
	
}
