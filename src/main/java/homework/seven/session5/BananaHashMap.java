package homework.seven.session5;

public class BananaHashMap<K,V> extends BananaMap<K,V> {
	@Override
	int size() {
		return 0;
	}

	@Override
	boolean isEmpty() {
		return false;
	}

	@Override
	boolean containsKey(K key) {
		return false;
	}

	@Override
	V get(K key) {
		return null;
	}

	@Override
	V put(K key, V value) {
		return null;
	}

	@Override
	V remove(K key) {
		return null;
	}

	static final int hash(Object key) {
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}
}
