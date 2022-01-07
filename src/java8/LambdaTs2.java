package java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaTs2 {

    static List<Integer> list = new ArrayList<>();
   static {
        list.add(1554);
        list.add(452);
        list.add(54546);
        list.add(81);
        list.add(651);
        list.add(895);
        list.add(356);
        list.add(785);
        list.add(3485);
    }

    public static void main(String[] args){
//        System.out.println(run5(3));
        System.out.println(run6((num)-> num>2000&&num<10000));
        int sum = list.stream()
                .filter(b -> b>500)
                .mapToInt(b -> b).findFirst().stream()
                .sum();
        System.out.println(sum);
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
