package io;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;

public class OutputStreamTest {

    public static void main(String st[]) throws Exception {
        System.out.println(InetAddress.getLocalHost());
        System.out.println(InetAddress.getLoopbackAddress().isLoopbackAddress());
        System.out.println(InetAddress.getByName("www.baidu.com"));
        byte bt[] = {14,(byte)215,(byte)177,38};
        System.out.println(InetAddress.getByAddress(bt).isReachable(50));
        System.out.println(Arrays.toString(InetAddress.getAllByName("testaddress")));

        NetworkInterface eth1 = NetworkInterface.getByName("eth1");
        System.out.println(eth1.getInetAddresses().nextElement());
    }
}
