package juc;

import java.util.concurrent.*;

public class FutureTaskTest {

    public static void main(String s[]) throws Exception{
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask).start();
        Integer integer = futureTask.get();
        System.out.println(integer);
    }

    static class MyCallable implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return 100;
        }
    }
}
