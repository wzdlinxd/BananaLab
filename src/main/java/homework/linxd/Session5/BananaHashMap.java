package homework.linxd.Session5;

import java.util.HashMap;
import java.util.Objects;

public class BananaHashMap<K, V> extends BananaMap<K, V> {

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

    public BananaHashMap(int capacity, float loadFactor) {
        this.loadFactor = loadFactor;
        this.capacity = capacity;
    }

    public BananaHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public BananaHashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    @Override
    V get(K key) {
        int index = this.indexOfKey(key);
        Node<K, V> p = table[index];
        int hash = key.hashCode();
        while (p != null) {
            if (p.hash == hash && (p.key == key || (p.key.equals(key) && key != null))) {
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
            tab = this.table;
        }
        int index = -1;
        Node<K, V> p = tab[index = this.indexOfKey(key)];
        int hash = key.hashCode();
        if (p == null) {
            //未碰撞
            tab[index] = new Node<>(hash, key, value, null);
        } else {
            while (true) {
//                System.out.println(p.key+":"+(p.hash == hash && (p.key == key || (p.key.equals(key) && key != null))));
                if (p.hash == hash && (p.key == key || (p.key.equals(key) && key != null))) {
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
            p.next = new Node<>(hash = key.hashCode(), key, value, null);

        }

        //查看是否需要扩容
        if (++this.size > this.threshold) {
            this.resize();
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
                if (node.hash == hash && (node.key == key || (node.key.equals(key) && key != null))) {
                    V oldVal = node.value;
                    Node<K, V> next = node.next;
                    if (pre == null) {
                        //是链表第一个节点
                        table[index] = next;
                    } else {
                        pre.next = next;
                    }

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
     *
     * @param key
     * @return
     */
    private int indexOfKey(K key) {
        //key为空，返回下标0
        int hash = key.hashCode();
        return (key == null) ? 0 : (hash & this.capacity - 1);
    }

    /**
     * 扩容
     */
    private void resize() {
        Node<K, V>[] oldTab = this.table;
        Node<K, V>[] newTab = null;
        int newCap = this.capacity << 1;
        int oldCap = this.capacity;
        if (oldTab == null || oldTab.length == 0) {
            //如果还没设置容量
            this.threshold = (int) (this.capacity * this.loadFactor);
        } else {
            //size太大扩容
            newCap = this.capacity << 1;
            if (newCap > MAXIMUM_CAPACITY) {
                this.capacity = newCap;
                this.threshold = this.threshold << 1;
            }
        }

        newTab = (Node<K, V>[]) new Node[this.capacity];
        //转移数据
        if (oldTab != null) {
            //原哈希表存在
            for (int i = 0; i < oldCap; i++) {
                Node<K, V> e = oldTab[i];
                oldTab[i] = null;
                while (e != null) {
                    //计算元素在新表的下标
                    int index = this.indexOfKey(e.key);
                    Node<K, V> head = newTab[index];
                    Node<K, V> next = e.next;
                    e.next = null;
                    if (head == null) {
                        //没有碰撞
                        newTab[index] = e;
                    } else {
                        //出现碰撞，插在头节点
                        newTab[index] = e;
                        e.next = head;
                    }
                    e = next;
                }//while
            }//for
        }//if
        this.table = newTab;
    }

    /**
     * 节点
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
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


    public static void main(String[] args) {
        BananaMap<String, String> map = new BananaHashMap<String, String>(4);
        System.out.println(map.isEmpty());
        map.put("1","Oh My God");
        System.out.println(map.isEmpty());
        map.put("2","Oh Your God");
        map.put("3","Oh He God");
        map.put("4","Oh She God");
        map.put("1","Oh li Shift");
        map.put("5", "6666");
        System.out.println(map.get("5"));
        System.out.println(map.remove("5"));
        System.out.println(map.get("5"));
        System.out.println(map.get("1"));
        System.out.println(map.size());
        System.out.println(map.containsKey("5"));
    }
}
