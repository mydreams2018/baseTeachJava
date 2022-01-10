package java8;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class LambdaTs2 {

    static List<Integer> list = new ArrayList<>();
   static {
        list.add(1554);//1
        list.add(452);
        list.add(54546);//1
        list.add(81);
        list.add(651);
        list.add(81);
        list.add(895);
        list.add(356);
        list.add(785);
        list.add(3485); // 上一次的结果 +
    }

    public static void main(String[] args){
//        System.out.println(run5(3));
//        System.out.println(run6((num)-> num>2000&&num<10000));
//        LambdaTs2 lambdaTs2 = new LambdaTs2();
//
//        Predicate<String> p = lambdaTs2::ts;
//        System.out.println(p.test(""));

//        Supplier<Apple> c1 = Apple::new;
//        Apple a1 = c1.get();
//
//        Supplier<Apple> c2 = () -> { return new Apple();};
//        Apple a2 = c2.get();

//        Function<Integer,Apple[]> c2 = Apple[]::new;
//        Apple[] a2 = c2.apply(16);
//        System.out.println(a2.length);
//
//        Function<Integer, Apple[]> c3 = (weight) -> new Apple[weight];
//        Apple[] a3 = c3.apply(110);
//        System.out.println(a3.length);

//        int sum = list.stream().filter(b -> b > 1000).mapToInt(b -> b).sum();
//        //多线程并发
//        int parallelSum = list.parallelStream().filter(b -> b > 1000).mapToInt(b -> b).sum();
//        System.out.println(sum);
//        System.out.println(parallelSum);
//        List<String> l = new ArrayList(Arrays.asList("one", "two"));
//        Stream<String> sl = l.stream();
//        l.add("three");
//        String s = sl.collect(Collectors.joining(","));
//        System.out.println(s);

//        List<Integer> results = list.stream().filter(s -> s > 1000).collect(Collectors.toList());
//        System.out.println(results);

//        Optional<Integer> reduce = list.stream().filter(s -> s > 1000).reduce((x, y) -> {
//            System.out.println(x+":"+y);
//            return x + y;});
//        System.out.println(reduce.isPresent());
//        System.out.println(reduce.get());

        //这些归约操作可以安全地并行运行，几乎不需要修改： ForkJoinPool  1000->filter -->reduce   [N]+[N]
//        Optional<Integer> reduce = list.parallelStream().filter(s -> s > 1000).reduce((x, y) -> x + y);
//        System.out.println(reduce.isPresent());
//        System.out.println(reduce.get());

//        OptionalInt heaviest = list.parallelStream().mapToInt(s -> s).min();
//        System.out.println(heaviest.getAsInt());

//        Stream<Integer> limit = list.stream().distinct();
//        limit.forEach(System.out::println);
//        long count = list.stream().distinct().count();
//        System.out.println(count);

//        Apple apple = new Apple();
        //线程不安全的方式
//        LongStream.rangeClosed(1,9999999L).parallel().forEach(apple::add);
//-5958941808141140780  6684975474523669191
//        System.out.println(apple.getNum());

//        ArrayList<String> strings = list.stream().collect(() -> new ArrayList<>(),
//                (c, e) -> c.add(e.toString()),
//                (c1, c2) -> {});
//        System.out.println(strings);

        List<String> strings = list.stream().map((e)-> e+"AAA")
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(strings);
        
    }

    public boolean ts(String s){
       return  s!=null;
    }

    public static List<Integer> run6(Predicate<Integer> predicate){
        List<Integer> rt = new ArrayList<>();
        for(int x=0;x<list.size();x++){
            if(predicate.test(list.get(x))){
                rt.add(list.get(x));
            }
        }
        return rt;
    }

// num==1  >1000
// num==2   >1000   < 30000
// num==3  >1000   < 30000 != 1554   N....
    public static List<Integer> run5(int num){
        List<Integer> rt = new ArrayList<>();
        for(int x=0;x<list.size();x++){
            if(list.get(x) > 1000 && num==1){
                rt.add(list.get(x));
            }else if(list.get(x) > 1000 && list.get(x) < 30000 && num==2) {
                rt.add(list.get(x));
            }else if(list.get(x) > 1000 && list.get(x) < 30000 && list.get(x)!=1554 && num==3) {
                rt.add(list.get(x));
            }
        }
        return rt;
    }


    //  >1000   < 30000 != 1554
    public static List<Integer> run3(){
        List<Integer> rt = new ArrayList<>();
        for(int x=0;x<list.size();x++){
            if(list.get(x) > 1000 && list.get(x) < 30000 && list.get(x)!=1554) {
                rt.add(list.get(x));
            }
        }
        return rt;
    }

    //  >1000   < 30000
    public static  List<Integer> run2(){
        List<Integer> rt = new ArrayList<>();
        for(int x=0;x<list.size();x++){
            if(list.get(x) > 1000 && list.get(x) < 30000) {
                rt.add(list.get(x));
            }
        }
        return rt;
    }

    //  >1000
    public static List<Integer> run1(){
        List<Integer> rt = new ArrayList<>();
        for(int x=0;x<list.size();x++){
            if(list.get(x) > 1000){
                rt.add(list.get(x));
            }
        }
        return rt;
    }


}
