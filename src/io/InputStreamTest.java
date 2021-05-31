package io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Random;

public class InputStreamTest {
    static File file = new File("D:\\temp\\src\\test.txt");
//    static PipedInputStream pipedInputStream = new PipedInputStream();
//    static PipedOutputStream pipedOutputStream = new PipedOutputStream();
    public static void main(String s[]) throws Exception {
        try(FileInputStream fileInputStream = new FileInputStream(file);//0-10000
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)){
            System.out.println(bufferedInputStream.read());
            System.out.println(bufferedInputStream.read());
            bufferedInputStream.mark(20000);
            System.out.println(bufferedInputStream.read());
            System.out.println(bufferedInputStream.read());
            System.out.println(bufferedInputStream.read());
            bufferedInputStream.reset();
            System.out.println(bufferedInputStream.read());
            System.out.println(bufferedInputStream.read());
            System.out.println(bufferedInputStream.read());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
