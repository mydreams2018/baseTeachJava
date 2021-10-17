package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {

    static File file = new File("D:\\temp\\src\\test.txt");
    static File fileOut = new File("D:\\temp\\src\\test2.txt");

    public static void main(String[] args) throws Exception {
        try(FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();

            FileOutputStream outputStream = new FileOutputStream(fileOut);
            FileChannel outChannel =  outputStream.getChannel()){

            fileChannel.position(3);
            System.out.println(fileChannel.position());
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            int count = fileChannel.read(allocate);
            allocate.flip();
            System.out.println(fileChannel.position());
            System.out.println(new String(allocate.array(), 0, count));
            System.out.println(allocate);

            outChannel.position(10);
            outChannel.write(allocate);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
