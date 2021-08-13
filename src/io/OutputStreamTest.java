package io;

import java.net.URI;

public class OutputStreamTest {

    public static void main(String st[]) throws Exception {
        URI uri = new URI("http://www.example.com:80/languages/java/sample/a/index.html?name=kungreat#28");
        URI uri2 = new URI("http://www.example.com:80/languages1");
//        System.out.println(uri.getScheme());
//        System.out.println(uri.getAuthority());
//        System.out.println(uri.getPort());
//        System.out.println(uri.getPath());
//        System.out.println(uri.getFragment());
//        System.out.println(uri.getQuery());
//http://www.example.com:80/languages/java/sample/demo/b/index.html
//http://www.example.com:80/languages/java/sample/a/demo/b/index.html
//        URI u1 = new URI("demo/b/index.html");
//        System.out.println(u1);
//        URI resolve = uri.resolve(u1);
//        System.out.println(resolve);
        System.out.println(uri2.relativize(uri));
    }
}
