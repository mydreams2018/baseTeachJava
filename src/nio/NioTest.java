package nio;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Set;

public class NioTest {
    static Path path = Path.of("D:\\temp\\src","test.txt");
    static File file = new File("D:\\temp\\src\\test.txt");
    static File fileOut = new File("D:\\temp\\src\\test2.txt");

    static final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    //server
    public static void main(String[] args) throws Exception {
        //ServerSocketChannel    SelectionKey.OP_ACCEPT
        try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            Selector selector = Selector.open()){
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(),10086));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 检查是否有 相应的通道已准备好用于 I/O 操作。
            while(selector.select() >= 0){
                Thread.sleep(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                System.out.println("size" + selectionKeys.size());
                while(iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    //可支持多线程操作
                    runServer(next,selector);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void runServer(SelectionKey next,Selector selector){
        System.out.println("runserver");
        try {
            SelectableChannel channel = next.channel();
            if(next.isValid() && next.isAcceptable()){
                ServerSocketChannel serChannel = (ServerSocketChannel) channel;
                SocketChannel accept = serChannel.accept();
                if(accept != null && accept.finishConnect()){
                    System.out.println("accept");
                    accept.configureBlocking(false);
                    accept.register(selector,SelectionKey.OP_CONNECT|
                            SelectionKey.OP_READ);
                }else{
                    System.out.println("error");
                }
            }else if(next.isValid() && next.isConnectable()){
                System.out.println("connectable");
            }else if(next.isValid() && next.isReadable()){
                System.out.println("readable");
                SocketChannel clientChannel = (SocketChannel) channel;

                int read = clientChannel.read(byteBuffer);
                // read > 0 有数据  ==-1 表示流关闭  ==0 不管
                while(read > 0){
                    System.out.println(new String(byteBuffer.array(),0,read));
                    byteBuffer.clear();
                    read = clientChannel.read(byteBuffer);
                }
                if(read == -1){
                    clientChannel.close();
                }
            }else if(next.isValid() && next.isWritable()){
                System.out.println("writable");
            }else{
                System.out.println("error");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
