package nio;

import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Set;

public class NioClientTest {
    static Path path = Path.of("D:\\temp\\src","test.txt");
    static File file = new File("D:\\temp\\src\\test.txt");
    static File fileOut = new File("D:\\temp\\src\\test2.txt");

    //client
    public static void main(String[] args) throws Exception {
        try(SocketChannel socketChannel = SocketChannel.open();
            Selector selector = Selector.open()){
            socketChannel.configureBlocking(false);
            socketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(),20077));
            socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 10086));
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            while(selector.select()>=0){
                Thread.sleep(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    SocketChannel clientChannel = (SocketChannel) next.channel();
                    if(next.isValid() && next.isConnectable()){
                        while(!clientChannel.finishConnect()){
                        }
                        clientChannel.register(selector,SelectionKey.OP_READ);
                        System.out.println("connectable");
                    }else if(next.isValid() && next.isReadable()){
                        System.out.println("Readable");
                    }else if(next.isValid() && next.isWritable()){
                        System.out.println("Writable");
                    }else{
                        System.out.println("error");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
