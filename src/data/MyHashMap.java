package data;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class MyHashMap<K,V> implements Map<K, V>{
	private Node<K,V>[] table =(Node<K,V>[]) new Node[16];
	private int size;
	Set<K>        keySet;
	Collection<V> values;
	    
    static class Node<K,V>{
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		return get(key)!=null;
	}

	@Override
	public boolean containsValue(Object value) {
		if(this.size > 0 && value != null) {
			for(int x =0;x <this.table.length;x++) {
				if(this.table[x] != null) {
					Node<K, V> node = this.table[x];
					do {
						if(node.value.equals(value)) {
							return true;
						}else {
							node = node.next;
						}
					}while(node != null);
				}
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		if(this.size > 0 && key != null) {
			for(int x =0;x <this.table.length;x++) {
				if(this.table[x] != null) {
					Node<K, V> node = this.table[x];
					do {
						if(node.key.equals(key)) {
							return node.value;
						}else {
							node = node.next;
						}
					}while(node != null);
				}
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		if(key != null && value != null) {
			int ix = key.hashCode() & this.table.length -1;
			if(this.table[ix] == null) {
				this.table[ix] = new Node<>(key.hashCode(),key,value,null);
				this.size++;
				return value;
			}else {
				Node<K, V> node = this.table[ix];
				do {
					if(node.key.equals(key)) {
						 V tempV = node.value;
						 node.value = value;
						 return tempV;
					}else {
						if(node.next == null) {
							node.next = new Node<>(key.hashCode(),key,value,null);
							this.size++;
							return value;
						}else {
							node = node.next;
						}
					}
				}while(true);
			}
		}
		return null;
	}

	@Override
	public V remove(Object key) {
		if(this.size > 0 && key != null) {
			for(int x = 0;x < this.table.length;x++) {
				if(this.table[x] != null) {
					Node<K, V> node = this.table[x];
					if(node.key.equals(key)) {
						Node<K, V> next = node.next;
						node.next = null;
						V oldValue = node.value;
						this.table[x] = next;
						this.size--;
						return oldValue;
					}else {
						while(node.next != null) {
							if(node.next.key.equals(key)){
								Node<K, V> next = node.next.next;
								node.next.next = null;
								V oldValue = node.next.value;
								node.next = next;
								this.size--;
								return oldValue;
							}else {
								node = node.next;
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		if(m != null && m.size() > 0) {
			  for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
	                this.put(e.getKey(), e.getValue());
	          }
		}
		
	}

	@Override
	public void clear() {
		Node<K,V>[] tab;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
	}

	@Override
	public Set<K> keySet() {
		if(keySet == null) {
			keySet = new MyKeySet();
		}
		return keySet;
	}
	
	private final class MyKeySet extends AbstractSet<K>{

		@Override
		public Iterator<K> iterator() {
			return new keyIterator();
		}

		@Override
		public int size() {
			return MyHashMap.this.size;
		}
		
	}
	
	class keyIterator extends MyHashIterator implements Iterator<K>{
		@Override
		public K next() {
			return nextNode().key;
		}
	}

	abstract class MyHashIterator{
		int indexIterator;
		Node<K,V> temp;
		
		private MyHashIterator(){
			if(MyHashMap.this.size > 0) {
				for(int x=0;x< MyHashMap.this.table.length;x++) {
					if(MyHashMap.this.table[x] != null) {
						indexIterator = x;
						temp = MyHashMap.this.table[x];
						break;
					}
				}
			}
		}

		public final boolean hasNext() {
			 return temp != null;
		}
		
		Node<K,V> nextNode(){
			 Node<K,V> rt = temp;
			 if (rt == null) throw new NoSuchElementException();
			 if((temp = temp.next) != null) {
			 }else {
				 for(;++indexIterator < MyHashMap.this.table.length;){
					 if((temp=MyHashMap.this.table[indexIterator]) != null) {
						 break;
					 }
//					 if(indexIterator == MyHashMap.this.table.length-1){
//						 temp = null;
//					 }
				 }
			 }
			 return rt;
		 }
	}
	
	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
}
