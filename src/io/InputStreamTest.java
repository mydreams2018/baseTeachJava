package io;

import java.io.*;

public class InputStreamTest {
    static File file = new File("D:\\temp\\src\\test.txt");

    public static void main(String s[]) throws Exception {
        try(InputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)){
            System.out.println(objectInputStream.readInt());
            MyTs ms = (MyTs)objectInputStream.readObject();
            System.out.println(ms);
        }catch (Exception e){
            e.printStackTrace();
        }

//00 ED 00 05
//        try(OutputStream outputStream = new FileOutputStream(file);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
//            objectOutputStream.writeInt(1);
//            MyTs myTs = new MyTs();
//            myTs.setName("one");
//            myTs.setAge(28);
//            objectOutputStream.writeObject(myTs);
//            objectOutputStream.flush();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
