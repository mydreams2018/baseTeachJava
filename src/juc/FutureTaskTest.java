package juc;

import java.util.concurrent.*;

public class FutureTaskTest {
    static ScheduledThreadPoolExecutor futureTask = new ScheduledThreadPoolExecutor(2);

    public static void main(String s[]) throws Exception{
        Future<?> schedule = futureTask.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },"test");
        Object o = schedule.get();
        System.out.println(o);
        // 100 2  period>0  102 104 106 108   [6]
        //100 2  period<0   102 108+2
    }
}
