package io;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NetServerTest extends Thread implements Runnable{

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
            int read = inputStream.read();
            while(read!=-1){
                stringBuilder.append((char)read);
                read = inputStream.read();
            }
            System.out.println(stringBuilder);
            if(stringBuilder.toString().contains(":end")){
                OutputStream outputStream = accept.getOutputStream();
                String curDate = LocalDateTime.now().toString();
                outputStream.write(curDate.getBytes());
                outputStream.flush();
            }else{
                OutputStream outputStream = accept.getOutputStream();
                outputStream.write("check-error".getBytes());
                outputStream.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(accept != null){
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(Thread.currentThread().getName()+":end-accept");
    }

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(52666)){
            while(true){
                try{
                    Socket accept = serverSocket.accept();
                    NetServerTest serverTest = new NetServerTest(accept);
                    THREAD_POOL_EXECUTOR.execute(serverTest);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
