package io;

import java.io.*;
import java.nio.charset.Charset;

public class OutputStreamTest {
    final static File file = new File("D:\\temp\\src\\test.txt");
    public static void main(String st[]){

        try(InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//字节缓冲区
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//字符缓冲区
            PushbackReader pushbackReader = new PushbackReader(bufferedReader,16);
        ){
            while(pushbackReader.ready()){
                char read = (char) pushbackReader.read();
                System.out.print(read);
                if('2' == read){
                    pushbackReader.unread(51);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // 11100110 10001000 10010001
          // 0110001000010001
    }
}
