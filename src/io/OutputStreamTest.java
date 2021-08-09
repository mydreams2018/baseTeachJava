package io;

import java.io.*;

public class OutputStreamTest {
    final static File file = new File("D:\\temp\\src\\test.txt");

    public static void main(String st[]) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        ){
            bufferedWriter.write("erwerrw21231321");
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 11100110 10001000 10010001
        // 0110001000010001
    }
}
