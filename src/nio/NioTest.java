package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class NioTest {
    static Path path = Path.of("D:\\temp\\src","test.txt");
    static File file = new File("D:\\temp\\src\\test.txt");
    static File fileOut = new File("D:\\temp\\src\\test2.txt");

    public static void main(String[] args) throws Exception {

        try(FileChannel open = FileChannel.open(path, StandardOpenOption.READ)){
            ByteBuffer allocate = ByteBuffer.allocate(64);
            int read = open.read(allocate);
            System.out.println(new String(allocate.array(),0,read));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
