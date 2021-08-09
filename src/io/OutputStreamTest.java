package io;

import java.io.*;

public class OutputStreamTest {
    final static File file = new File("D:\\temp\\src\\test.txt");
    final static File file1 = new File("D:\\temp\\src\\test2.txt");
    public static void main(String st[]) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
             FileOutputStream fileOutputStream1 = new FileOutputStream(file1);
             OutputStreamWriter outputStreamWriter1 = new OutputStreamWriter(fileOutputStream1);
        ){
            CharArrayWriter charArrayWriter = new CharArrayWriter();
            charArrayWriter.write("AAA");
            charArrayWriter.append("BBB");
            charArrayWriter.writeTo(outputStreamWriter);
            charArrayWriter.append("CCC");
            charArrayWriter.writeTo(outputStreamWriter1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 11100110 10001000 10010001
        // 0110001000010001
    }
}
