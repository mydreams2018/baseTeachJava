package io;

import java.io.*;
import java.nio.charset.Charset;

public class OutputStreamTest {
    final static File file = new File("D:\\temp\\src\\test.txt");
    public static void main(String st[]){

        try(InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream)
        ){
//            while(inputStreamReader.ready()){
//                System.out.print((char)inputStreamReader.read());
//            }
            char ca[] = new char[100];
            int num = inputStreamReader.read(ca);
            for(int x=0;x<num;x++){
                System.out.print(ca[x]);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        // 11100110 10001000 10010001
          // 0110001000010001
    }
}
