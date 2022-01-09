package java8;

public class Apple {
    private long num = 0;
    public Apple(){
        System.out.println("apple");
    }

    public Apple(Integer num){
        System.out.println(num);
    }

    public void add(long num){
        this.num = this.num + num;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }
}
