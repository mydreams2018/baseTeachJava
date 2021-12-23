package jarts;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class MyURLClassLoader extends URLClassLoader {

    public MyURLClassLoader(URL[] urls) {
        super(urls);
    }
/*
*读取加载指定的.class文件
*/
    public Class<?> fileLoad(URL url, String name) throws IOException {
        InputStream inputStream = url.openConnection().getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        return this.defineClass(name,bytes,0,bytes.length);
    }
}
