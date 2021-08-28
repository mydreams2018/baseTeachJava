package io;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NetServerTest extends Thread implements Runnable{

    private static final List<Socket> SOCKET_LIST = new LinkedList<>();
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(2,30,300, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1024));
    private Socket accept = null;

    private NetServerTest(Socket ac){
        this.accept=ac;
    }
    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName()+":get-accept");
            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = accept.getInputStream();

            while(true){
                int read = inputStream.read();
                while(read!=-1 && read!=10 && read!=13){
                    stringBuilder.append((char)read);
                    read = inputStream.read();
                }
                System.out.println(stringBuilder);
                sendGroupMessage(stringBuilder.toString());
                stringBuilder.delete(0,stringBuilder.length());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(accept != null){
                try {
                    accept.close();
                    SOCKET_LIST.remove(accept);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(Thread.currentThread().getName()+":end-accept");
    }

    private void sendGroupMessage(String st) {
        if(st == "\r" || st == "\n"){
            return;
        }
        Iterator<Socket> iterator = SOCKET_LIST.iterator();
        while(iterator.hasNext()){
            Socket next = iterator.next();
            if(next != accept){
                try {
                    OutputStream outputStream = next.getOutputStream();
                    outputStream.write(st.getBytes());
                    outputStream.write(System.lineSeparator().getBytes());
                    outputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    iterator.remove();
                }
            }
        }

    }

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(52666)){
            while(true){
                try{
                    Socket accept = serverSocket.accept();
                    NetServerTest serverTest = new NetServerTest(accept);
                    THREAD_POOL_EXECUTOR.execute(serverTest);
                    SOCKET_LIST.add(accept);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
