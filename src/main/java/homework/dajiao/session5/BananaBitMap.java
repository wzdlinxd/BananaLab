package homework.dajiao.session5;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;


/**
 * 去重统计的 BitMap
 * @param <K>
 */
public class BananaBitMap<K> implements CountingInterface<K> {
	private byte[] filter;
	private int size;
	private DigestInterface digestInterface;


	private static final Integer DEFAULT_CAPACITY = 1000000;

	public BananaBitMap(){
		init(DEFAULT_CAPACITY);
	}

	public BananaBitMap(int capacity){
		init(capacity);
	}


	private void init(int capacity){
		this.filter = new byte[capacity];
		this.size = 0;
		this.digestInterface = new DigestMD5();
	}

	@Override
	public void add(K key) {
		String keyString = key.toString();

		int digest = digestInterface.digest(keyString);
		int position = digest % filter.length;


		if (filter[position] == 0) {
			filter[position] = 1;
			size ++;
		}

	}

	@Override
	public int size() {
		return size;
	}



	private class DigestMD5 implements DigestInterface {


		@Override
		public int digest(String key) {
			return Math.abs(encryptMD5(key));
		}

		/**
		 * 对字节流进行MD5摘要。
		 */
		private int encryptMD5(String data) {
			byte[] bytes = null;
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				bytes = md.digest(data.getBytes());
				return new String(bytes).hashCode();
			} catch (GeneralSecurityException gse) {

			}

			return 0;
		}
	}



	private interface DigestInterface {
		int digest(String key);
	}
}
