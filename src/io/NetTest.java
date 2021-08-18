package io;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class NetTest {

    public static void main(String[] args){
        try(Socket socket = new Socket("time.nist.gov",13, InetAddress.getLocalHost(),52888)){
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
