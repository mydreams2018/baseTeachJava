package io;

import utils.FileCopys;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

public class OutputStreamTest {
    public static void main(String st[]) throws Exception {
        File src = new File("E:\\Downloads\\CentOS-7-x86_64-Minimal-1810.iso");
        File tar = new File("F:\\copy",src.getName());
        if(!tar.exists()){
            System.out.println(tar.createNewFile());
        }
//        try(
//                RandomAccessFile randomAccessFile = new RandomAccessFile(tar,"rws");
//                ){
//            randomAccessFile.setLength(src.length());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//962,592,768

        // 11100110 10001000 10010001
        // 0110001000010001
    }
}
