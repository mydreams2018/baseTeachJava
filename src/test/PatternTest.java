package test;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import base.Animal;
import base.Cat;



public class PatternTest {

	public static void main(String[] args) {
		 Pattern p = Pattern.compile("a{3,5}b");//模板
		 Matcher m = p.matcher("aab");   //内容
		 boolean b = m.matches();
	     System.out.println(b);
	     
	     boolean b1 = Pattern.matches("a{3,5}b","aab");
	     System.out.println(b1);
	  
	     boolean b2 = Pattern.matches("a{3,5}b[a-d[m-p]]","aaabe");
	     System.out.println(b2);
	     //[def] == [a-z&&[def]]
	     boolean b3 = Pattern.matches("^a{3,5}b[a-z&&[def]]$","aaabf");
	     System.out.println(b3);
	     
	     boolean b5= Pattern.matches("x|y","x");
	     System.out.println(b5);
	     //\d == [0-9]+    \w == [a-zA-Z_0-9]
	     boolean b6= Pattern.matches("\\w+","45d_45");
	     System.out.println(b6);
	}

}
