package old;

public class MyException extends RuntimeException{
	
	private Object obj ;
	
	public MyException(String message,Object obj) {
		super(message);
		this.obj=obj;
		
	}
}
