package data;

import java.util.ArrayList;
import java.util.List;

public class MyTree<T> {
	
	private Node<T> root;
	private int size;
	public boolean putTree(T t) {
		assert t != null;
		if(root == null) {
			root = new Node<T>(t,null,null);
			size++;
		}else {
			return addData(root,t);
		}
		return true;
	}
	
	private boolean addData(Node<T> nd,T t) {
		if(!nd.item.equals(t)) {
			if(compare(nd.item,t)) {
				if(nd.left == null) {
					nd.left = new Node<T>(t,null,null);
					size++;
					return true;
				}else {
					return addData(nd.left,t);
				}
			}else {
				if(nd.right == null) {
					nd.right = new Node<T>(t,null,null);
					size++;
					return true;
				}else {
					return addData(nd.right,t);
				}
			}
		}
		return false;
	}
	
	private boolean compare(T t1,T t2) {
		return Integer.parseInt(t1.toString()) > Integer.parseInt(t2.toString());
	}
	
	public Node<T> getByEquals(T t){
		if(t != null) {
			return getByEquals(root,t);
		}
		return null;
	}
	
	private Node<T> getByEquals(Node<T> node,T t){
		if(node != null) {
			if(node.item.equals(t)) {
				return node;
			}else if(compare(node.item,t)) {
				return getByEquals(node.left,t);
			}else{
				return getByEquals(node.right,t);
			}
		}
		return null;
	}
	
	public List<T> getByLarge(T t){
		List<T> list = new ArrayList<T>();
		if(t != null) {
			getByLarge(list,root,t);
		}
		return list;
	}
	
	private void getByLarge(List<T> list,Node<T> node,T t) {
		if(node != null) {
			if(compare(node.item,t)) {
				list.add(node.item);
				getByLarge(list,node.right,t);
				getByLarge(list,node.left,t);
			}else {
				getByLarge(list,node.right,t);
			}
		}
	}
	
	private static class Node<T>{
		private T item;
		private Node<T> left;
		private Node<T> right;
		public Node(T item,Node<T> left,Node<T> right) {
			this.item=item;
			this.left=left;
			this.right=right;
		}
		
	}
}
