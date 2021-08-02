package io;

import java.io.*;

public class OutputStreamTest {
    final static File file = new File("D:\\temp\\src\\test.txt");
    public static void main(String st[]){

        try(OutputStream outputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)){
            bufferedOutputStream.write(86);
            bufferedOutputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
