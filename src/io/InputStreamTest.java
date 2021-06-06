package io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

public class InputStreamTest {
    static File file = new File("D:\\temp\\src\\test.txt");
//    static PipedInputStream pipedInputStream = new PipedInputStream();
//    static PipedOutputStream pipedOutputStream = new PipedOutputStream();
    public static void main(String s[]) throws Exception {
        Checksum checksum = new Adler32();
        byte[] bt = {97,115,97,115,97,115,97,115};
        checksum.update(bt);
        System.out.println(checksum.getValue());//230425357
        try(FileInputStream fileInputStream = new FileInputStream(file);
            CheckedInputStream checkedInputStream = new CheckedInputStream(fileInputStream,new Adler32())){
            byte[] bytes = checkedInputStream.readAllBytes();
            System.out.println(checkedInputStream.getChecksum().getValue());
        }
    }
}
