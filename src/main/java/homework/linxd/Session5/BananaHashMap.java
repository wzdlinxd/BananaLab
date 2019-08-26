package homework.linxd.Session5;

import java.util.HashMap;
import java.util.Objects;

public class BananaHashMap<K,V> extends BananaMap<K,V> {

	/**
	 * 默认容量
	 */
	static final int DEFAULT_INITIAL_CAPACITY = 16;
	/**
	 * 最大容量
	 */
	static final int MAXIMUM_CAPACITY = 1 << 30;
	/**
	 * 默认负载因子
	 */
	static final float DEFAULT_LOAD_FACTOR = 0.75f;

	private Node<K, V>[] table;

	private int size;

	private int modCount;

	private int threshold;

	private float loadFactor;

	private int capacity;

	@Override
	int size() {
		return size;
	}

	@Override
	boolean isEmpty() {
		return size == 0;
	}

	@Override
	boolean containsKey(K key) {

		return this.get(key) != null;
	}

	@Override
	V get(K key) {
		int index = this.indexOfKey(key);
		Node<K, V> p = table[index];
		int hash = key.hashCode();
		while (p != null) {
			if (p.hash == hash && (p.key == key || (p.key.equals(key) && key != null))){
				//key存在
				return p.value;
			}
			p = p.next;
		}
		return null;
	}

	@Override
	V put(K key, V value) {
		Node<K, V>[] tab = this.table;
		if (tab == null || tab.length == 0) {
			//初始化
			this.resize();
		}
		int index = -1;
		Node<K,V> p = null;
		int hash = 0;
		if ((p = tab[index = this.indexOfKey(key)]) == null) {
			//未碰撞
			tab[index] = new Node<>(hash = key.hashCode(),key,value,null);
		} else {
			while (true) {
				if (p.hash == hash && (p.key == key || (p.key.equals(key) && key != null))){
					//key存在，直接替换
					V old = p.value;
					p.value = value;
					return old;
				}
				if (p.next == null) {
					break;
				}
				p = p.next;
			}
			//key不存在
			p.next = new Node<>(hash = key.hashCode(),key,value,null);

			//查看是否需要扩容
			if (++this.size > this.threshold) {
				this.resize();
			}
		}
		return null;
	}

	@Override
	V remove(K key) {
		int index = this.indexOfKey(key);
		Node<K, V> node = table[index];
		Node<K, V> pre = null;
		int hash = key.hashCode();
		if (node != null) {
			while (node != null) {
				//找到键值对
				if (node.hash == hash && (node.key == key || (node.key.equals(key) && key != null))){
					V oldVal = node.value;
					Node<K, V> next = node.next;
					if (pre == null) {
						//是链表第一个节点
						table[index] = next;
					}
					pre.next = next;
					this.size--;
					return oldVal;
				}
				pre = node;
				node = node.next;
			}
		}
		return null;
	}

	/**
	 * 返回哈希表下标
	 * @param key
	 * @return
	 */
	private int indexOfKey(K key) {
		//key为空，返回下标0
		int hash = 0;
		return (key == null) ? 0 : (hash = key.hashCode()) ^ (hash & this.capacity-1);
	}

	private int resize() {

		return 0;
	}
	/**
	 * 节点
	 * @param <K>
	 * @param <V>
	 */
	static class Node<K, V> {
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

		public int getHash() {
			return hash;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public Node<K, V> getNext() {
			return next;
		}
		public void setNext(Node<K, V> next) {
			this.next = next;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Node<?, ?> node = (Node<?, ?>) o;
			return key.equals(node.key) &&
					value.equals(node.value);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(key) ^ Objects.hashCode(value);
		}
	}
}
