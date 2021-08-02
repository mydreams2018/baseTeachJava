package io;

import java.io.*;

public class OutputStreamTest {
    final static File file = new File("D:\\temp\\src\\test.txt");
    public static void main(String st[]){

        try(OutputStream outputStream = new FileOutputStream(file)){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String s = "哈哈哈";
            byteArrayOutputStream.write(s.getBytes());
            byteArrayOutputStream.writeTo(outputStream);
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
