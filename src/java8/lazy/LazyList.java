package java8.lazy;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyList<T> implements MyList<T> {
    final T head;
    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail){
        this.head = head;
        this.tail = tail;
    }

    public T head() {
        return head;
    }

    public MyList<T> tail(){
        return tail.get();
    }

    public MyList<T> filter(Predicate<T> p) {
        return p.test(head()) ?
                new LazyList<T>(head(),() -> tail().filter(p))://延迟函数引用
                tail().filter(p);//迭代条件直到完成
    }

}
