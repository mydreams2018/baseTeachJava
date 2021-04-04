package juc;

import java.util.concurrent.*;

public class FutureTaskTest {
    static ForkJoinPool futureTask = new ForkJoinPool();

    public static void main(String s[]) throws Exception{
        Long invoke = futureTask.invoke(new ForkJoinTask<Long>() {
            private Long rt = 0L;
            @Override
            public Long getRawResult() {
                return 55L+rt;
            }

            @Override
            protected void setRawResult(Long value) {
            }

            @Override
            protected boolean exec() {
                System.out.println(Thread.currentThread().getName());
                ForkJoinTask<Long> task = new ForkJoinTask<>() {
                    @Override
                    public Long getRawResult() {
                        return 55L;
                    }
                    @Override
                    protected void setRawResult(Long value) {
                    }
                    @Override
                    protected boolean exec() {
                        System.out.println(Thread.currentThread().getName());
                        return true;
                    }
                };
                task.fork();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rt = task.join();
                return true;
            }
        });
//        Object o = schedule.get();
        System.out.println(invoke);
        // 100 2  period>0  102 104 106 108   [6]
        //100 2  period<0   102 108+2
    }
}
