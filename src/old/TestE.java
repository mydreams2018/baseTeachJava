package old;

public class TestE {

	public static void main(String[] args) {
		divide();
		System.out.println("end");
		
	}
	
	public static void divide(){
		
		if(false) {
			
		}else {
			try {
				int a = 1/0;
				throw new MyException("密码不对","other messgae");
				
			}finally {
				int a = 1/0;
				System.out.println("finally");
			}
			
		}
		System.out.println("divide");
	}

}
