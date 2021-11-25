package nio;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpTest {
    static final byte[] BTS = new byte[64];
    public static void main(String[] args) {
        try(DatagramSocket datagramSocket = new DatagramSocket(20066, InetAddress.getLocalHost())){
            System.out.println(datagramSocket.isBound());
            System.out.println(datagramSocket.isConnected());
//            datagramSocket.connect(InetAddress.getLocalHost(),9999);

            DatagramPacket datagramPacket = new DatagramPacket(BTS,64,InetAddress.getLocalHost(),20088);
            datagramSocket.receive(datagramPacket);
            System.out.println(new String(datagramPacket.getData(),datagramPacket.getOffset(),datagramPacket.getLength()));
            System.out.println(datagramPacket.getSocketAddress());

            datagramPacket.setData("one server".getBytes());
            datagramSocket.send(datagramPacket);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
