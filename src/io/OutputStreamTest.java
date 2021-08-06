package io;

import java.io.*;
import java.nio.charset.Charset;

public class OutputStreamTest {
    final static File file = new File("D:\\temp\\src\\test.txt");

    public static void main(String st[]) {
        PipedReader pipedReader = new PipedReader();
        PipedWriter pipedWriter = new PipedWriter();
        try {
            pipedWriter.connect(pipedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                int read = 0;
                try {
                    read = pipedReader.read();
                    while (read != -1) {
                        System.out.print((char) read);
                        read = pipedReader.read();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        pipedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("pipedReader - end");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int x = 0; x < 100; x++) {
                        pipedWriter.write(48 + x);
                        Thread.sleep(200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        pipedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        // 11100110 10001000 10010001
        // 0110001000010001
    }
}
