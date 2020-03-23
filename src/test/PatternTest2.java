package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest2 {

	public static void main(String args[]) {
		Pattern pt = Pattern.compile("\\w*?\\d\\w*");
		Matcher matcher = pt.matcher("hkejhrekTT55kkdkslo");
		// hkejhrekTT565 2 kkdkslo
		//System.out.println(matcher.groupCount());
		//System.out.println(matcher.matches());
		//System.out.println(matcher.group());
		//System.out.println(matcher.group());
		if(matcher.matches()) {
			for(int x = 1 ; x <= matcher.groupCount() ;x++ ) {
				System.out.print(matcher.group(x) + " ");
				System.out.print(matcher.start(x) + " ");
				System.out.println(matcher.end(x));
			}
		}
		String name = "bigben34343";
		String name1 = name.replaceAll("b[^i]", "111");
		//System.out.println(name1);
		
		// 正向前瞻
		String route = "Windows 2000df sdfsWindows 10";
		route = route.replaceAll("Windows (?=95|98|NT|2000)", "xxxx");
		//System.out.println(route);
		// 负向前瞻
		String route2 = "Windows dfsdfs Windows 95";
		route2 = route2.replaceAll("Windows (?!95|98|NT|2000)", "xxxx");
		//System.out.println(route2);
		
		// 正向后瞻
		String route3 = "oldroute 5old rou 5";
		route3 = route3.replaceAll("(?<=old )route", "xxxx");
		//System.out.println(route3);
		// 负向后瞻
		String route5 = " dfsdroute 5";
		route5 = route5.replaceAll("(?<!old )route", "xxxx");
		//System.out.println(route5);
		
		boolean b1 = Pattern.matches("[\u4e00-\u4e05]", "丐");
		//System.out.println(b1);
		
	
		// 验证邮箱
		boolean b8 = Pattern.matches("\\w{5,20}@\\w{3,8}(\\.com|\\.cn)",
				"qep454@163.cn");
		System.out.println(b8);
		// 验证手机
		boolean b9 = Pattern.matches("(135|158|186)\\d{8}", "18675788820");
		//System.out.println(b9);
		
		//密码  在   小写 大写  数字
		boolean b10 = Pattern.matches("(?=\\w*[a-z])(?=\\w*[A-Z])(?=\\w*[0-9])(?=\\w*[_%]).{8,}",
				                     "rwer0A%5Ydfds");
		System.out.println(b10);
		
		boolean b11 = Pattern.matches("(?!\\w*[T])\\w{8,}",
                "rwer5Ydfds");
        System.out.println(b11);
	}
	
	
}
