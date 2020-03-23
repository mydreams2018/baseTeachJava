package data;

public class MyQueue<T> {

	private Object[] t;
	private int capacity=0;
	private int popIndex=0;
	private int pushIndex=0;
	
	public MyQueue() {
		this(6);
	}
	public MyQueue(int capacity) {
		this.capacity=capacity;
		this.t=new Object[capacity];
	}
	
	public T pop() {
		if(popIndex < pushIndex) {
			T pop = (T) this.t[popIndex];
			popIndex++;
			return pop;
		}
		return null;
	}
	
	public void push(T push) {
		expansion();
		this.t[pushIndex] = push;
		pushIndex++;
	}
	
	private void expansion() {
		if(pushIndex==t.length) {
			int length = popIndex > capacity?t.length:t.length+capacity;
			Object[] temp = new Object[length];
			System.arraycopy(t, popIndex, temp, 0, pushIndex-popIndex);
			t = temp;
			pushIndex = pushIndex-popIndex;
			popIndex=0;
		}
	}
	
	public int length() {
		return t.length;
	}
}
