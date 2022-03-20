package nio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class OtherTest {

    public static void main(String[] args) throws Exception {
        for(int zz=0;zz<10;zz++){
            final int cg = zz;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket();
//        socket.bind(new InetSocketAddress(InetAddress.getLocalHost(),20099));
                        socket.setKeepAlive(true);
                        //读取超时时间
                        socket.setSoTimeout(5000);
                        socket.connect(new InetSocketAddress(InetAddress.getLocalHost(),9999));
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(("hello-server"+cg).getBytes());
                        InputStream inputStream = socket.getInputStream();
                        String str ="";
                        for(int x=0;x<13;x++){
                            int read = inputStream.read();
                            Thread.sleep(200);
                            str = str+(char)read;
                        }
                        System.out.println(str);
                        socket.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }
}
