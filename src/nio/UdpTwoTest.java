package nio;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpTwoTest {
    static final byte[] BTS = "two server".getBytes();
    static final byte[] BTSR = new byte[64];
    public static void main(String[] args) {
        try(DatagramSocket datagramSocket = new DatagramSocket(9999, InetAddress.getLocalHost())){
            System.out.println(datagramSocket.isBound());
            System.out.println(datagramSocket.isConnected());
            DatagramPacket datagramPacket = new DatagramPacket(BTS,10,InetAddress.getLocalHost(),20066);
            datagramSocket.send(datagramPacket);
            System.out.println("send end");
            datagramPacket.setData(BTSR);
//            datagramSocket.receive(datagramPacket);
//
//            System.out.println(new String(datagramPacket.getData(),datagramPacket.getOffset(),datagramPacket.getLength()));
//            System.out.println(datagramPacket.getSocketAddress());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
