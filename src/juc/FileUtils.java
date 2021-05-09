package juc;

import java.io.File;

public class FileUtils {


    public static void printChilds(File fl){
        if(fl != null && fl.exists() && fl.isDirectory()){
            File[] fs = fl.listFiles();
            if(fs != null && fs.length > 0){
                for(int x=0;x<fs.length;x++){
                    File temp = fs[x];
                    System.out.println(temp);
                    if(temp.isDirectory()){
                        printChilds(temp);
                    }
                }
            }
        }
    }

    public static void searchNames(File fl,String name){
        if(fl != null && fl.exists() && fl.isDirectory()){
            File[] fs = fl.listFiles();
            if(fs != null && fs.length > 0){
                for(int x=0;x<fs.length;x++){
                    File temp = fs[x];
                    if(temp.getName().contains(name)){
                        System.out.println(temp);
                    }
                    if(temp.isDirectory()){
                        searchNames(temp,name);
                    }
                }
            }
        }
    }
}
