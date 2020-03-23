package data;

public class MyDeque<T> {

	private Object[] element;
	private int head;
	private int tail;
	public MyDeque() {
		element = new Object[8];
	}
	
	public void pushHead(T t) {
		head = (head-1) & (element.length -1);
		element[head] = t;
		if(head == tail) {
			expansion();
		}
	}
	
	public void pushTail(T t) {
		element[tail] = t;
		tail = (tail + 1) & (element.length -1);
		if(head == tail) {
			expansion();
		}
	}
	
	public T popHead() {
		if(head == tail) {
			return null;
		}
		T t = (T) element[head];
		element[head] = null;
		head =  (head+1) & (element.length -1);
		return t;
	}
	
	public T popTail() {
		if(head == tail) {
			return null;
		}
		int temp = (tail - 1) & (element.length -1);
		T t = (T) element[temp];
		element[temp] = null;
		tail = temp;
		return t;
	}
	
	private void expansion() {
		Object temp[] = new Object[element.length << 1];
		System.arraycopy(element, head, temp, 0, element.length-head);
		System.arraycopy(element, 0, temp, element.length-head,tail);
		head = 0;
		tail = element.length;
		element = temp;
	}
	
	public int getTail() {
		return tail;
	}
	
	public int getHead() {
		return head;
	}
	
	public int length() {
		return element.length;
	}
	
}
