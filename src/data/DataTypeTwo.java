package data;

public class DataTypeTwo<T> {
	
	private T t;
	
	public DataTypeTwo(T t) {
		this.t=t;
	}
	
	public static<T> T getK(T k) {
		return k;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public T getName() {
		return t;
	}

	public void setName(T t) {
		this.t = t;
	}
}
