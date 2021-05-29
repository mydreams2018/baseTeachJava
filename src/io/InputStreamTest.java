package io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Random;

public class InputStreamTest {
    static File file = new File("D:\\temp\\src\\test.txt");
    static PipedInputStream pipedInputStream = new PipedInputStream();
    static PipedOutputStream pipedOutputStream = new PipedOutputStream();
    public static void main(String s[]) throws Exception {
        pipedInputStream.connect(pipedOutputStream);
        new Thread(new Runnable(){
            @Override
            public void run() {
                Random random = new Random();
                try {
                    while(true){
                        int i = random.nextInt(2000);
                        Thread.sleep(i);
                        pipedOutputStream.write(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        pipedOutputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    while(true){
                        System.out.println(pipedInputStream.read());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        pipedInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
