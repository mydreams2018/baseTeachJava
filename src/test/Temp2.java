package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Temp2 {

	public static void main(String[] args) {
		// String to be scanned to find the pattern.
		String line = "hisorder111wasplaceAddfsdfsd";
		String pt = "\\w*";

		// Create a Pattern object
		Pattern r = Pattern.compile(pt);

		// Now create matcher object.
		Matcher m = r.matcher(line);
		System.out.println(m.groupCount());
		System.out.println(m.matches());
		System.out.println(m.group(0));
		// 尝试查找与模式匹配输入的序列
		if (m.find()) {
			for (int x = 1; x <= m.groupCount(); x++) {
				// 返回上一个匹配操作期间给定组捕获的输入子序列。
				System.out.println(m.group(x));
				// 返回由给定组捕获子序列的最后一个字符之后的偏移量
				System.out.println(m.end(x));
				// 返回在此匹配期间由给定组捕获的子序列的起始索引。
				System.out.println(m.start(x));
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
		// 验证邮箱
		boolean b8 = Pattern.matches("\\w{5,20}@\\w{3,8}((\\.com)|(\\.cn))", "qep454@163.cn");
		System.out.println(b8);
		// 验证手机
		boolean b9 = Pattern.matches("((135)|(158)|(186))\\d{8}", "18675788820");
		System.out.println(b9);

	}

}
