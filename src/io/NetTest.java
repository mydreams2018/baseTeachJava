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
            OutputStream outputStream = socket.getOutputStream();
            for(int x=0;x<5;x++){
                String rt = "dfsdfsdf122312312:end";
                outputStream.write(rt.getBytes());
                outputStream.flush();
            }
            socket.shutdownOutput();

            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int read = bufferedReader.read();
            while(read != -1){
                System.out.print((char)read);
                read = bufferedReader.read();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
