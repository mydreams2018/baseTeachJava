package juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskTest {
    static FutureTask<String> futureTask = new FutureTask<>(new Runnable(){
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    },"test");

    public static void main(String s[]) throws Exception{
        new Thread(futureTask).start();
        String s1 = futureTask.get();
//        Thread.sleep(200);
//        futureTask.cancel(true);
//        System.out.println(futureTask.isCancelled());
        System.out.println(s1);
        String s2 = futureTask.get();
    }
}
