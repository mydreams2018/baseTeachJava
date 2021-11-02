package nio;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class NioTest {
    static Path path = Path.of("D:\\temp\\src","test.txt");
    static File file = new File("D:\\temp\\src\\test.txt");
    static File fileOut = new File("D:\\temp\\src\\test2.txt");

    //server
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(),20066));
        System.out.println(serverSocketChannel.isBlocking());//true
        SocketChannel accept = serverSocketChannel.accept();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int read = accept.read(allocate);
        while(read != -1){
            System.out.println(new String(allocate.array(),0,read));
            allocate.clear();
            read = accept.read(allocate);
        }

        accept.close();
        serverSocketChannel.close();
    }
}
