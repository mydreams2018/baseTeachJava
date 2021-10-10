package nio;

import java.io.*;
import java.nio.ByteBuffer;

public class NioTest {

    static File file = new File("D:\\temp\\src\\test.txt");


    public static void main(String[] args) throws Exception {
        ByteBuffer allocate = ByteBuffer.allocate(16);
        System.out.println(allocate.position());//0
        System.out.println(allocate.limit());//16
        System.out.println(allocate.capacity());//16

        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(16);
        System.out.println(allocateDirect.position());//0
        System.out.println(allocateDirect.limit());//16
        System.out.println(allocateDirect.capacity());//16

        byte[] bts = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        ByteBuffer wrap = ByteBuffer.wrap(bts);
        ByteBuffer wrap1 = ByteBuffer.wrap(bts, 0, 8);

    }
}
