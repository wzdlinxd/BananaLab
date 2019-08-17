package homework.seven.session4;


/**
 * 实现一个容器为数组的 ArrayString
 *
 * @author Seven-Steven
 * @date 2019/8/17 11:40
 */
public class ArrayString implements StringInterface {
	/**
	 * 字符容器
	 */
	private char[] chars;

	public ArrayString(char[] chars) {
		this.chars = chars;
	}

	@Override

	public int length() {
		return this.chars.length;
	}

	@Override
	public char charAt(int position) {
		if (position < 0 || position >= this.length()) {
			throw new IndexOutOfBoundsException();
		}

		return this.chars[position];
	}

	@Override
	public int indexOf(char[] target) {
		if (target.length <= 0 || target.length > this.length()) {
			return -1;
		}

		for (int i = 0; i < this.length(); i++) {
			if (this.charAt(i) != target[0]) {
				continue;
			}

			for (int j = 0; j < target.length; j++) {
				// 被比较字符串索引
				int sourceIndex = i + j;

				if (this.charAt(sourceIndex) == target[j]) {
					if (j == target.length - 1) {
						return i;
					}
				}
			}
		}

		return -1;
	}

	@Override
	public StringInterface subString(int start, int end) {
		if (start < 0 || end >= this.length() || end < 0 || start >= this.length()) {
			throw new IndexOutOfBoundsException();
		}

		if (start > end) {
			throw new IllegalArgumentException("start 应该小于或者等于 end");
		}

		char[] result = new char[end - start];
		for (int i = start, j = 0; i < end; i++, j++) {
			result[j] = this.charAt(i);
		}

		return new ArrayString(result);
	}

	@Override
	public StringInterface reverse() {
		char[] result = new char[this.length()];
		for (int i = this.length() - 1, j = 0; i >= 0; i--, j++) {
			result[j] = this.charAt(i);
		}

		return new ArrayString(result);
	}

	/**
	 * 根据数字返回对应的字符串
	 *
	 * @param number 待转换为字符串的数字
	 * @return 转换完成之后的字符串
	 */
	public static StringInterface valueOf(Integer number) throws NumberFormatException {
		boolean negative = number < 0;
		// 计算 number 长度，负数预留一个符号位
		int length = negative ? 2 : 1;
		int absolute = negative ? -number : number;
		while ((absolute = absolute / 10) != 0) {
			length++;
		}

		absolute = negative ? -number : number;
		char[] result = new char[length];
		int index = length - 1;
		while (index >= 0) {
			result[index] = (char) (absolute % 10 + '0');
			absolute = absolute / 10;
			index--;
		}

		if (negative) {
			result[0] = '-';
		}

		return new ArrayString(result);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ArrayString)) {
			return false;
		}

		ArrayString target = (ArrayString) obj;
		if (this.length() != target.length()) {
			return false;
		}

		for (int i = 0; i < this.length(); i++) {
			if (this.charAt(i) != target.charAt(i)) {
				return false;
			}
		}

		return true;
	}
}
