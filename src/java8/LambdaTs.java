package java8;

public class LambdaTs {


    //创建一个线程、输出一句话
    public static void main(String[] args){
        run1();
        run2();
        run3();
    }

    //lambda Java 8 新特性--Lambda表达式作为方法参数
    //简单来说,可以看成是对匿名内部类的缩写,首先前提是方法参数 是一个函数式接口
   /*
        (e) [类型] 参数名称 ->固定语法
        ()方法空参 ->固定语法
        () ->  System.out.println("lambda");
   */
    public static void run3(){
        Thread thread = new Thread(()-> System.out.println("lambda"));
        thread.start();
    }

    public static void run2(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("lambda");
            }
        });
        thread.start();
    }

    public static void run1(){
        Thread thread = new Thread(new RunnableImpl());
        thread.start();
    }

    static class RunnableImpl implements Runnable{
        @Override
        public void run() {
            System.out.println("lambda");
        }
    }

}
