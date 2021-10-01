package utils;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class CutoverBytes {

    /***指定byte数组
     * @param t1 要合并的数组
     * @param t2 要合并的数组
     * @return 合并后的新数组
     */
    public static byte[] concat(byte[] t1,byte[] t2){
        byte[] result = new byte[t1.length+t2.length];
        System.arraycopy(t1,0,result,0,t1.length);
        System.arraycopy(t2,0,result,t1.length,t2.length);
        return result;
    }
    /***泛型数组
     * @param t1 要合并的数组
     * @param t2 要合并的数组
     * @return 合并后的新数组
     */
    public static <T> T[] concat(T[] t1,T[] t2){
        T[] result = Arrays.copyOf(t1,t1.length + t2.length);
        System.arraycopy(t2,0,result,t1.length,t2.length);
        return result;
    }
    /***
     * @param a 数值
     * @return 把数值转换成byte数组
     */
    public static byte[] intToByteArray(int a) {
        return new byte[] {
                (byte) ((a >> 24)),
                (byte) ((a >> 16) & 255),
                (byte) ((a >> 8)  & 255),
                (byte) ((a >> 0)  & 255)
        };
    }
    /***
     * @param a 数值
     * @return 把数值转换成byte数组
     */
    public static byte[] longToByteArray(long a) {
        return new byte[] {
                (byte) ((a >> 56)),
                (byte) ((a >> 48)  & 255),
                (byte) ((a >> 40)  & 255),
                (byte) ((a >> 32)  & 255),
                (byte) ((a >> 24)  & 255),
                (byte) ((a >> 16)  & 255),
                (byte) ((a >> 8)   & 255),
                (byte) ((a >> 0)   & 255)
        };
    }
    /***
     * @param bytes 数组
     * @return 把数组转换成long数值
     */
    public static long readLong(byte[] bytes) {
        return (((long)bytes[0] << 56) +
                ((long)(bytes[1] & 255) << 48) +
                ((long)(bytes[2] & 255) << 40) +
                ((long)(bytes[3] & 255) << 32) +
                ((long)(bytes[4] & 255) << 24) +
                ((bytes[5] & 255) << 16) +
                ((bytes[6] & 255) <<  8) +
                ((bytes[7] & 255) <<  0));
    }

    public static void getBroadCast() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while(networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            List<InterfaceAddress> interfaceAddresses = networkInterface.getInterfaceAddresses();
            for(int x=0;x<interfaceAddresses.size();x++){
                InterfaceAddress interfaceAddress = interfaceAddresses.get(x);
                InetAddress broadcast = interfaceAddress.getBroadcast();
                if(broadcast!=null){
                    System.out.println(broadcast);
                }
            }
        }
    }
}
