package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {

    static File file = new File("D:\\temp\\src\\test.txt");
    static File fileOut = new File("D:\\temp\\src\\test2.txt");

    public static void main(String[] args) throws Exception {
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
            FileChannel fileChannel = randomAccessFile.getChannel()){
//            System.out.println(fileChannel.position());
//            ByteBuffer allocate = ByteBuffer.allocate(64);
//            int read = fileChannel.read(allocate);
//            System.out.println(new String(allocate.array(), 0, read,"UTF-8"));
//            System.out.println(allocate);
//            fileChannel.position(12);
//            allocate.clear();
//            String st = "你好";
//            allocate.put(st.getBytes());
//            System.out.println(allocate);
//            allocate.flip();
//            System.out.println(allocate);
//            fileChannel.write(allocate);
            MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 6);
            System.out.println(map);
            String st = "努力";
            map.put(st.getBytes());
            System.out.println(map.isLoaded());
            System.out.println(map);
            map.force();
            map.load();
            System.out.println(map.isLoaded());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
