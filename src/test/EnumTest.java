package test;

public enum EnumTest {
	
	ONE("1"),TWO("2");
	private EnumTest(String s) {
		say=s;
	}
	
	public String say = "kungreat";
	
	public String getSay() {
		return say;
	}

	class Inner{
		
	}
}
