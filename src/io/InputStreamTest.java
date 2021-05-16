package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;

public class InputStreamTest {
    static File file = new File("D:\\temp\\src\\test.txt");
    public static void main(String s[]) throws Exception {
        try(FileInputStream fileInputStream = new FileInputStream(file)){
//            System.out.println(fileInputStream.available());
//            int read = fileInputStream.read();
//            System.out.println((char)read);
//            System.out.println(fileInputStream.available());
//            fileInputStream.skip(1);
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            String s1 = new String("fsdfs我");
            System.out.println(s1);
            System.out.println(Charset.defaultCharset());
        }
    }
}
