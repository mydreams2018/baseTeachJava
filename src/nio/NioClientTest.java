package nio;

import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;

public class NioClientTest {
    static Path path = Path.of("D:\\temp\\src","test.txt");
    static File file = new File("D:\\temp\\src\\test.txt");
    static File fileOut = new File("D:\\temp\\src\\test2.txt");

    //client
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        System.out.println(socketChannel.isBlocking());//true
//        socketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(),20077));
        socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 20066));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello word".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);

        socketChannel.close();
    }
}
