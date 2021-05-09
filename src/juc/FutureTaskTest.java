package juc;


import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Arrays;

public class FutureTaskTest {
//    static File file = new File("D:\\IdeaProjects\\bbs\\src\\main");
    public static void main(String s[]) throws Exception {
//        System.out.println(File.pathSeparator);
//        System.out.println(File.separator);
//        System.out.println(Arrays.toString(file.listFiles()));
//        file.setReadOnly();
//        System.out.println(file.canExecute());
//        System.out.println(file.canRead());
//        System.out.println(file.canWrite());
//        System.out.println(file.createNewFile());
//        System.out.println(File.createTempFile("MainJava", ".java",new File("D:\\temp\\src")));
//        System.out.println(file.getAbsoluteFile());
//        System.out.println(file.getCanonicalFile());
//        System.out.println(Arrays.toString(file.listFiles(new FileFilter(){
//            @Override
//            public boolean accept(File pathname) {
//                System.out.println(pathname);
//                return true;
//            }
//        })));
//        System.out.println(Arrays.toString(File.listRoots()));
//        file.renameTo(new File("D:\\temp\\src\\test2.txt"));
//        FileUtils.printChilds(file);
        File[] files = File.listRoots();
        for(int x=0;x<files.length;x++){
            final int fx = x;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FileUtils.searchNames(files[fx],".jpg");
                }
            }).start();
        }
    }
}