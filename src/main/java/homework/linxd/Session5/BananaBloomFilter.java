package homework.linxd.Session5;


/**
 * 去重统计的布隆过滤器
 * @param <K>
 */
public class BananaBloomFilter<K> implements CountingInterface<K> {
	private static final int DEFAULT_SIZE = 2 << 24;
	private static final int[] seeds = new int[] {7, 11, 13, 31, 37, 61};
	private BananaBitMap bitMap;
	private int size;

	public BananaBloomFilter(int capacity) {
		bitMap = new BananaBitMap(capacity);
	}

	/**
	 * 计算哈希值
	 * @param key
	 * @param seed
	 * @return
	 */
	private int hash(K key, int seed) {
		if (key == null) {
			return 0;
		}
		String keyString = key.toString();
		int h = 0;
		if (keyString.length() != 0) {
			for (int i = 0; i < keyString.length(); i++){
				h = seed * h + keyString.charAt(i);
			}
		}
		return Math.abs(h);
	}

	private boolean contains(K key) {
		boolean isContain = true;
		for (int seed : seeds) {
			isContain = isContain & bitMap.contains(hash(key, seed));
		}
		return isContain;
	}

	@Override
	public void add(K key) {
		if (contains(key)) {
			return;
		}
		for (int seed : seeds) {

			bitMap.add(hash(key, seed));
		}
		size++;

	}

	@Override
	public int size() {
		return this.size;
	}

	public static void main(String[] args) {
		BananaBloomFilter bloomFilter = new BananaBloomFilter(2 << 24);
		bloomFilter.add("a");
		bloomFilter.add("b");
		bloomFilter.add("c");
		bloomFilter.add("d");
		System.out.println(bloomFilter.size());
		bloomFilter.add("f");
		System.out.println(bloomFilter.size());

	}
}
