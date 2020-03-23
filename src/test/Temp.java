package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Temp {

	public static void main(String[] args) {
		// String to be scanned to find the pattern.
		String line = "This order was placed for QT3000! OK?";
		String pt = "(.*)(\\d{3})(.*)";

		// Create a Pattern object
		Pattern r = Pattern.compile(pt);

		// Now create matcher object.
		Matcher m = r.matcher(line);
		System.out.println(m.groupCount());
		// 尝试查找与模式匹配输入的序列
		if (m.find()) {
			for (int x = 0; x <= m.groupCount(); x++) {
				// 返回上一个匹配操作期间给定组捕获的输入子序列。
				System.out.println(m.group(x));
				// 返回在此匹配期间由给定组捕获的子序列的起始索引。
				System.out.println(m.start(x));
				// 返回由给定组捕获子序列的最后一个字符之后的偏移量
				System.out.println(m.end(x));
			} 
		}

		boolean b1 = Pattern.matches("[\u4e00-\u4e05]", "丄");
		System.out.println(b1);
		// 正向前瞻
		String route = "Windows 2000 dfsdfsWindows 95";
		route = route.replaceAll("Windows (?=95|98|NT|2000)", "xxxx");
		System.out.println(route);
		// 负向前瞻
		String route2 = "Windows dfsdfs Windows 95";
		route2 = route2.replaceAll("Windows (?!95|98|NT|2000)", "xxxx");
		System.out.println(route2);
		// 正向后瞻
		String route3 = "old route 5";
		route3 = route3.replaceAll("(?<=old )route", "xxxx");
		System.out.println(route3);
		// 负向后瞻
		String route5 = "ddd route 5";
		route5 = route5.replaceAll("(?<!old )route", "xxxx");
		System.out.println(route5);

		String route6 = "old routeold route 6";
		route6 = route6.replaceAll("old route", "xxxx");
		System.out.println(route6);
	

	}

}
