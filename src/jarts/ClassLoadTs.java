package jarts;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class ClassLoadTs {
    static URL url;
    static URL url2;
    static URL url3;
    static {
        try {
            url3 = new URL("file:C:\\Users\\kungreat\\IdeaProjects\\mavenTest\\target\\classes\\cn\\kungreat\\ts\\Mv.class");
            url2 = new URL("file:C:\\Users\\kungreat\\IdeaProjects\\mavenTest\\target\\classes\\cn\\kungreat\\MavenTest.class");
            url = new URL("file:C:\\Users\\kungreat\\.m2\\repository\\commons-codec\\commons-codec\\1.11\\commons-codec-1.11.jar");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
/*
* 反射框架核心、通过指定ClassLoader指定加载必要资源、再通过反射去启动加载别的模块完成整个功能、
*/
    public static void main(String[] args) throws Exception {
        MyURLClassLoader urlClassLoader = new MyURLClassLoader(new URL[]{url});
        Class<?> aClass = urlClassLoader.fileLoad(url2, "cn.kungreat.MavenTest");
        urlClassLoader.fileLoad(url3, "cn.kungreat.ts.Mv");
        Method ma = aClass.getDeclaredMethod("main", String[].class);
        ma.invoke(null,(Object) null);
    }

}
