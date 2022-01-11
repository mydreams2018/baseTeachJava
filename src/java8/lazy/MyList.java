package java8.lazy;

import java.util.function.Predicate;

public interface MyList<T> {
    T head();
    MyList<T> tail();
    MyList<T> filter(Predicate<T> p);
}
