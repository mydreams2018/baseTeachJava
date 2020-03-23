package calculator;

public class Calculator {
	
	public static long count(String p) {
		Long one = null;
		String symbol = null;
		int symbolIndex = 0;
		Long two = null;
		String symbol2 = null;
		
		byte[] bytes = p.getBytes();
		
		for(int x =0;x < bytes.length ;x++) {
			if('+'==(char)bytes[x] || '-'==(char)bytes[x]
					|| '*' == (char)bytes[x] || '/' == (char)bytes[x])
			{
				if(one == null) {
					one = Long.parseLong(new String(bytes,0,x));
					symbol = String.valueOf((char)bytes[x]);
					symbolIndex = x ;
					//System.out.println(one);
				}else if(two == null) {
					two = Long.parseLong(new String(bytes,symbolIndex+1,x-symbolIndex-1));
					symbol2 = String.valueOf((char)bytes[x]);
					symbolIndex=x;
					//System.out.println(two);
				}else {
					if("*".equals(symbol) || "/".equals(symbol)) {
						one = math(one,symbol,two);
						symbol = symbol2;
						two = Long.parseLong(new String(bytes,symbolIndex+1,x-symbolIndex-1));
					}else if("*".equals(symbol2) || "/".equals(symbol2)) {
						long three = Long.parseLong(new String(bytes,symbolIndex+1,x-symbolIndex-1));
						two = math(two,symbol2,three);
					}else {
						one = math(one,symbol,two);
						symbol = symbol2;
						two = Long.parseLong(new String(bytes,symbolIndex+1,x-symbolIndex-1));
					}
					symbol2=String.valueOf((char)bytes[x]);
					symbolIndex = x ;
					
//					long three = Long.parseLong(new String(bytes,symbolIndex+1,x-symbolIndex-1));
//					one = math2(one,symbol,two,symbol2,three);
//					symbol = String.valueOf((char)bytes[x]);
//					symbolIndex = x ;
//					two = null;
					//symbol2 = null;
				}
			}
		}
		if(two == null) {
			return math(one,symbol,Long.parseLong(new String(bytes,symbolIndex+1,bytes.length-symbolIndex-1)));
		}
		return math2(one,symbol,two,symbol2,Long.parseLong(new String(bytes,symbolIndex+1,bytes.length-symbolIndex-1)));
		//System.out.println(one);
		
	}
	
	
	private static long math2(long a,String sy1,long b,String sy2,long c) {
		if("+".equals(sy1)) {
			return a + math(b,sy2,c);
		}else if("-".equals(sy1)) {
			if("+".equals(sy2)) {
				return a - b + c;
			}
			return a - math(b,sy2,c);
		}else {
			return math(math(a,sy1,b),sy2,c);
		}
	}
	
	private static long math(long a,String symbol,long b) {
		if("+".equals(symbol)) {
			return a+b;
		}else if("-".equals(symbol)) {
			return a-b;
		}
		else if("*".equals(symbol)) {
			return a*b;
		}else {
			return a/b;
		}
	}
	
}
