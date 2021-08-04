package io;

import java.io.*;
import java.nio.charset.Charset;

public class OutputStreamTest {
    final static File file = new File("D:\\temp\\src\\test.txt");
    public static void main(String st[]){

        try(InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//字节缓冲区
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//字符缓冲区
        ){
//            while(inputStreamReader.ready()){
//                System.out.print((char)inputStreamReader.read());
//            }
            char read = (char) bufferedReader.read();
            char ca = 13;
            char ca1 = 10;
            System.out.println(read);
        }catch (Exception e){
            e.printStackTrace();
        }
        // 11100110 10001000 10010001
          // 0110001000010001
    }
}
