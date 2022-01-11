package java8.lazy;

public class LazyTs {

    public static void main(String[] args) {
        LazyList<Integer> numbers = from(2);
        int two = primes(numbers).head();
        int three = primes(numbers).tail().head();
        int five = primes(numbers).tail().tail().head();
        System.out.println(two + " " + three + " " + five);
    }

    public static LazyList<Integer> from(int n) {
        System.out.println(n);
        return new LazyList<Integer>(n, () -> from(n+1));
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(
                numbers.head(),
                () -> primes(
                        numbers.tail().filter(n -> n % numbers.head() != 0)
                )
        );
    }
}
