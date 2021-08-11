package utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

public class FileCopys implements Runnable{
    private File src;
    private File tar;
    private long strindex;
    private long endindex;

    public FileCopys(File src, File tar, long strindex, long endindex) {
        if (strindex < 0 || strindex >= endindex) throw new RuntimeException("开始,结束索引异常");
        this.strindex = strindex;
        this.endindex = endindex;
        this.tar = tar;
        this.src = src;
    }

    @Override
    public void run() {
        try(RandomAccessFile srcFile = new RandomAccessFile(src,"r");
            RandomAccessFile tarFile = new RandomAccessFile(tar,"rws")){
            srcFile.seek(strindex);
            tarFile.seek(strindex);
            byte cg[] = new byte[8192];
            do{
                long i = strindex + 8192;
                if(i >= endindex){
                    int i1 = (int) (endindex - strindex);
                    srcFile.read(cg, 0, i1);
                    tarFile.write(cg,0, i1);
                    break;
                }else{
                    srcFile.read(cg);
                    tarFile.write(cg);
                    strindex+=8192;
                    srcFile.seek(strindex);
                    tarFile.seek(strindex);
                }
            }while(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":end");
    }

//        long length = src.length();
//        int num = 81920000;
//        for(int x=0; ;x++){
//            length-=num;
//            if(length<=0){
//                new Thread(new FileCopys(src,tar,x*num,src.length())).start();
//                break;
//            }else{
//                new Thread(new FileCopys(src,tar,x*num,(x+1)*num)).start();
//            }
//        }
    //检查二个文件字节是否一样
//        try(
//    FileInputStream fileInputStream = new FileInputStream(src);
//    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//    FileInputStream fileInputStream1 = new FileInputStream(tar);
//    BufferedInputStream bufferedInputStream1 = new BufferedInputStream(fileInputStream1);
//                ){
//        int read = bufferedInputStream.read();
//        int read1 = bufferedInputStream1.read();
//        int num = 0;
//        while(read != -1){
//            if(read != read1){
//                System.out.println("error");
//            }
//            read = bufferedInputStream.read();
//            read1 = bufferedInputStream1.read();
//            num++;
//        }
//        if(read != read1){
//            System.out.println("error");
//        }
//        System.out.println(num);
//    }catch (Exception e){
//        e.printStackTrace();
//    }
}
