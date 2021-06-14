package io;

import java.io.*;

public class InputStreamTest {
    static File file = new File("D:\\temp\\src\\test.txt");

    public static void main(String s[]) throws Exception {
        try(InputStream inputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(inputStream)){
//            System.out.println(dataInputStream.readUTF());
        }catch (Exception e){
            e.printStackTrace();
        }
        //01100001 00000000 00000000 00000000
        //         01110011 00000000 00000000
        //01100001 01110011 01100001 01110011

        try(OutputStream outputStream = new FileOutputStream(file);
            DataOutputStream dataInputStream = new DataOutputStream(outputStream)){
            dataInputStream.writeInt(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
