package java8.lazy;

import org.junit.Test;
import java.util.stream.Stream;

public class RecursionTs {

    @Test
    public void testStreamIterate(){
        Stream.<Integer>iterate(1,(n) -> n*10).filter((n)-> n>9).limit(10).forEach(System.out::println);
    }

    @Test
    public void testRec(){
        System.out.println(factorialTailRecursion(1,10000000).invoke());
//        System.out.println(recursion(1,10000000));
    }

    public static int recursion(final int factorial, final int number) {
        if (number == 1) return factorial;
        else return recursion(factorial + number, number - 1);
    }

    public static TailRecursion<Integer> factorialTailRecursion(final int factorial, final int number){
        if (number == 1)
            return TailInvoke.done(factorial);
        else
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
            throw new Error("递归还没有结束,调用获得结果异常!");
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
    }

    public static class TailInvoke {

        /*
         * 结束当前递归，重写对应的默认方法的值,完成状态改为true,设置最终返回结果,设置非法递归调用
         *
         * @param value 最终递归值
         * @param <T>   T
         * @return 一个isFinished状态true的尾递归, 外部通过调用接口的invoke方法及早求值, 启动递归求值。
         */
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
