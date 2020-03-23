package test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class  TestE{

	private String say = "kungreat";
	
	public static void main(String[] args) throws Exception{
		//System.err.println("https://www.kungreat.cn");
//		int src[] = {15,15,16,25,16};
//		int tar[] = new int[6];
//		System.arraycopy(src, 0, tar, 0, src.length);
//		System.out.println(Arrays.toString(tar));
//		System.out.println(System.currentTimeMillis());
//		System.out.println(System.nanoTime());
//		Map<String, String> getenv = System.getenv();
//		System.out.println(getenv);
		byte src[] = new byte[6];
		int read = System.in.read();
		int index = 0;
		for(int x=0;x<System.in.available();index++) {
			if(index == src.length) {
				byte tar[] = new byte[src.length+6];
				System.arraycopy(src, 0, tar, 0, src.length);
				src=tar;
			}
			src[index]=(byte) read;
			//System.out.println(read);
			read = System.in.read();
		}
		System.out.println(new String(src,0,index));
	}
	
	
}
