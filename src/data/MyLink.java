package data;

public class MyLink<T> {

	private Node<T> tail;
	private Node<T> head;
	private int size;
	
	public boolean pushTail(T item) {
		if(tail == null) {
			Node<T> temp = new Node<T>(item,null,null);
			tail=temp;
			head=temp;
		}else {
			Node<T> temp = new Node<T>(item,null,tail);
			tail.prev=temp;
			tail=temp;
		}
		size++;
		return true;
	}
	
	public boolean pushHead(T item) {
		if(head == null) {
			Node<T> temp = new Node<T>(item,null,null);
			head=temp;
			tail=temp;
		}else {
			Node<T> temp = new Node<T>(item,head,null);
			head.next=temp;
			head = temp;
		}
		size++;
		return true;
	}
	
	public T popTail() {
		if(tail != null) {
			T temp = tail.item;
			if(tail.next == null) {
				tail=null;
				head=null;
			}else {
				tail = tail.next;
				tail.prev=null;
			}
			size--;
			return temp;
		}
		return null;
	}
	
	public T popHead() {
		if(head != null) {
			T temp = head.item;
			if(head.prev == null) {
				tail=null;
				head=null;
			}else {
				head = head.prev;
				head.next=null;
			}
			size--;
			return temp;
		}
		return null;
	}
	
	public T getItem(int index) {
		T result = null;
		if(getByIndex(index) != null) {
			Node<T> byIndex = getByIndex(index);
			result = byIndex.item;
			if(byIndex.prev == null && byIndex.next == null) {
				tail = null;
				head =null;
			}else if(byIndex.prev != null && byIndex.next != null) {
				byIndex.next.prev = byIndex.prev;
				byIndex.prev.next = byIndex.next;
				byIndex.prev = null;
				byIndex.next = null;
			}else if(byIndex.prev == null && byIndex.next != null) {
				tail = byIndex.next ;
				tail.prev = null;
			}else {
				head = byIndex.prev;
				head.next = null;
			}
			size--;
		}
		return result;
	}
	
	private Node<T> getByIndex(int index){
		Node<T> rt = null;
		if(checkIndex(index)) {
			if(index < (size >> 1)) {
				rt = tail;
				for(int x= 0;x < index;x++) {
					rt = rt.next;
				}
			}else{
				rt = head;
				for(int x = size-1;x > index;x--) {
					rt = rt.prev;
				}
			}
		}
		return rt;
	}
	
	private boolean checkIndex(int index) {
		return index < size && index >=0;
	}
	
	public int getLength() {
		return size;
	}
	
	private static class Node<T>{
		private T item;
		private Node<T> prev;
		private Node<T> next;
		public Node(T item,Node<T> prev,Node<T> next) {
			this.item=item;
			this.prev=prev;
			this.next=next;
		}
		
	}
}
