package io;

import java.io.*;

public class InputStreamTest {
    static File file = new File("D:\\temp\\src\\test.txt");

    public static void main(String s[]) throws Exception {
        try(InputStream inputStream = new FileInputStream(file);
            PushbackInputStream pushInputStream = new PushbackInputStream(inputStream,10)){
            int read = pushInputStream.read();
            while(read >= 0){
                System.out.println((char)read);
                if('s' == (char)read){
                    pushInputStream.unread(',');
                }
                read = pushInputStream.read();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
