package java8;

import java.util.stream.Stream;

public class RecursionTs {

    public static void main(String[] args) {
        testRec();//1784293664
                  //1784293664
    }

    public static void testStreamIterate(){
        //1 5 5 25 25 125 125 625
        Stream.<Integer>iterate(1,(n) -> n*5).filter((n)-> n>900).limit(10).forEach(System.out::println);
    }

    public static void testRec() {
        System.out.println(factorialTailRecursion(1,1000000).invoke());
//        System.out.println(recursion(1,100000));
        //1+10+9+8+7+6+5+4+3+2 增加堆栈大小 -Xss128m
    }
//尾递归实现
    public static int recursion(final int factorial, final int number) {
        if (number == 1) return factorial;
        else return recursion(factorial + number, number - 1);
    }

    public static TailRecursion<Integer> factorialTailRecursion(final int factorial, final int number){
        if (number == 1)
            return TailRecursion.<Integer>done(factorial);
        else
//           return new TailRecursion<Integer>(){
//                @Override
//                public TailRecursion<Integer> apply() {
//                    return factorialTailRecursion(factorial + number, number - 1);
//                }
//            };
            return () -> factorialTailRecursion(factorial + number, number - 1);
    }

    @FunctionalInterface
    static interface TailRecursion<T> {
        /**
         * 用于递归栈帧之间的连接,惰性求值
         * @return 下一个递归栈帧
         */
        TailRecursion<T> apply();

        /**
         * 判断当前递归是否结束
         * @return 默认为false,因为正常的递归过程中都还未结束
         */
        default boolean isFinished(){
            return false;
        }

        /**
         * 获得递归结果,只有在递归结束才能调用,这里默认给出异常,通过工具类的重写来获得值
         * @return 递归最终结果
         */
        default T getResult()  {
            throw new RuntimeException("递归还没有结束,调用获得结果异常!");
        }

        /**
         * 及早求值,执行者一系列的递归,因为栈帧只有一个,所以使用findFirst获得最终的栈帧,接着调用getResult方法获得最终递归值
         * @return 及早求值,获得最终递归结果
         */
        default T invoke(){
            return Stream.iterate(this,TailRecursion::apply)
                    .filter(TailRecursion::isFinished)
                    .findFirst()
                    .get()
                    .getResult();
        }

        public static <T> TailRecursion<T> done(T value) {
            return new TailRecursion<T>() {
                @Override
                public TailRecursion<T> apply() {
                    throw new Error("递归已经结束,非法调用apply方法");
                }

                @Override
                public boolean isFinished() {
                    return true;
                }

                @Override
                public T getResult() {
                    return value;
                }
            };
        }
    }
}
