package data;

public class DataType {

	private Object name;

	public DataType(Object name) {
		this.name=name;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}
	
}
