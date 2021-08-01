package io;

import java.io.*;

public class OutputStreamTest {
    final static File file = new File("D:\\temp\\src\\test.txt");
    public static void main(String st[]){

        try(OutputStream outputStream = new FileOutputStream(file,true)){
            outputStream.write(23);
            outputStream.write(23);
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
