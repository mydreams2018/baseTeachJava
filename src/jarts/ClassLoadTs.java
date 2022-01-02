package jarts;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class ClassLoadTs {
    static URL url;
    static URL url2;
    static {
        try {
            url2 = new URL("file:C:\\Users\\kungreat\\IdeaProjects\\mavenTest\\target\\classes\\");
            url = new URL("file:C:\\Users\\kungreat\\.m2\\repository\\commons-codec\\commons-codec\\1.11\\commons-codec-1.11.jar");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
/*
 [硬编码:例导包那种、直接引用那种、需要在加载运行链接时就指定资源]
* 反射框架核心、通过指定ClassLoader指定加载必要资源、再通过反射去启动加载别的模块完成整个功能、
* A-->系统ClassLoad-->平台ClassLoader
* B-->系统ClassLoad-->平台ClassLoader
* 对象概念 A、B各自管各自的资源
*/
    public static void main(String[] args) throws Exception {
        MyURLClassLoader urlClassLoader = new MyURLClassLoader(new URL[]{url,url2});
        urlClassLoader.setDefaultAssertionStatus(false);
        Class<?> aClass = urlClassLoader.loadClass("cn.kungreat.MavenTest");
        System.out.println(aClass.hashCode());//932607259

        MyURLClassLoader urlClassLoaderB = new MyURLClassLoader(new URL[]{url,url2});
        urlClassLoaderB.setDefaultAssertionStatus(false);
        Class<?> bClass = urlClassLoaderB.loadClass("cn.kungreat.MavenTest");
        System.out.println(bClass.hashCode());//905544614

        Method ma = aClass.getDeclaredMethod("main", String[].class);
        ma.invoke(null,(Object) null);
    }

}
