package io;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class NetTest {

    public static void main(String[] args){
        try(Socket socket = new Socket(InetAddress.getLocalHost(),52666)){
            //输入流
            new Thread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder stringBuilder = new StringBuilder();
                    try{
                        InputStream inputStream = socket.getInputStream();
                        while(true){
                            int read = inputStream.read();
                            while(read != -1 && read!=10 && read!=13){
                                stringBuilder.append((char)read);
                                read = inputStream.read();
                            }
                            System.out.println(stringBuilder);
                            stringBuilder.delete(0,stringBuilder.length());
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();

            while(true){
                StringBuilder wt = new StringBuilder();
                InputStream syin = System.in;
                int read = syin.read();
                while(read!=10 &&read!=13){
                    wt.append((char)read);
                    read = syin.read();
                }
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(wt.toString().getBytes());
                outputStream.write(System.lineSeparator().getBytes());
                outputStream.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
