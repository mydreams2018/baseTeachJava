package io;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class OutputStreamTest {

    public static void main(String st[]) throws Exception {
        String haha = URLEncoder.encode("哈哈大", "UTF-8");
        System.out.println(URLDecoder.decode(haha, "UTF-8"));
        URL url1 = new URL("http://127.0.0.1/hello?echo="+haha);
        try(InputStream inputStream = url1.openStream()){
            byte[] bytes = inputStream.readAllBytes();
            System.out.println(new String(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println((char)21704);
        //0101 010011 001000
    }
}
