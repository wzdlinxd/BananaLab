package homework.seven.session4;

/**
 * 实现一个容器为链表的 LinkedString
 *
 * @author Seven-Steven
 * @date 2019/8/17 21:18
 */
public class LinkedString implements StringInterface {
	/**
	 * 头指针
	 */
	private LinkedNode head;

	public LinkedString(LinkedNode head) {
		this.head = head;
	}

	@Override
	public int length() {
		int length = 0;
		LinkedNode point = this.head;
		while (point != null) {
			length++;
			point = point.getNext();
		}
		return length;
	}

	@Override
	public char charAt(int position) {
		if (position < 0) {
			throw new IndexOutOfBoundsException();
		}

		int length = 0;
		LinkedNode point = this.head;
		while (point != null) {
			if (length == position) {
				return point.getValue();
			}

			point = point.getNext();
			length++;
		}

		throw new IndexOutOfBoundsException();
	}

	@Override
	public int indexOf(char[] target) {
		int index = 0;
		LinkedNode point = this.head;
		while (point != null) {
			if (target[0] != point.getValue()) {
				point = point.getNext();
				index++;
				continue;
			}

			LinkedNode temp = point.getNext();
			for (int i = 1; i < target.length; i++) {
				if (target[i] != temp.getValue()) {
					break;
				}

				if (i == target.length - 1) {
					return index;
				}
				temp = temp.getNext();
			}

			point = point.getNext();
			index++;
		}

		return -1;
	}

	@Override
	public StringInterface subString(int start, int end) {
		int length = this.length();
		if (start < 0 || end < 0 || start >= length || end >= length) {
			throw new IndexOutOfBoundsException();
		}

		if (end < start) {
			throw new IllegalArgumentException();
		}

		LinkedNode point = this.head;
		int index = 0;
		while (index < start && point != null) {
			point = point.getNext();
			index++;
		}

		assert point != null;
		LinkedNode result = new LinkedNode();
		result.setValue(point.getValue());
		index++;
		LinkedNode resultPoint = result;

		while ((point = point.getNext()) != null && index < end) {
			LinkedNode temp = new LinkedNode();
			temp.setValue(point.getValue());
			resultPoint.setNext(temp);
			resultPoint = temp;
			index++;
		}

		return new LinkedString(result);
	}

	@Override
	public StringInterface reverse() {
		int length = this.length();
		char[] chars = new char[length];

		LinkedNode point = this.head;
		int index = 0;
		while (point != null) {
			chars[index] = point.getValue();
			point = point.getNext();
			index++;
		}

		char[] reverse = new char[length];
		index = length - 1;
		for (int i = 0; index >= 0; index--, i++) {
			reverse[i] = chars[index];
		}

		return LinkedString.valueOf(reverse);
	}

	/**
	 * 将字符数组转换成字符串
	 * @param chars 待转换为字符串的数组
	 * @return 转换完成之后的字符串
	 */
	public static LinkedString valueOf(char[] chars) {
		LinkedNode result = new LinkedNode();
		LinkedNode resultPoint = result;
		int resultIndex = 0;
		resultPoint.setValue(chars[resultIndex]);
		resultIndex++;
		while (resultIndex < chars.length) {
			LinkedNode temp = new LinkedNode();
			temp.setValue(chars[resultIndex]);
			resultPoint.setNext(temp);
			resultPoint = temp;
			resultIndex++;
		}

		return new LinkedString(result);
	}

	/**
	 * 根据数字返回对应的字符串
	 * @param number 待转换为字符串的数字
	 * @return 转换完成之后的字符串
	 */
	public static StringInterface valueOf(Integer number) throws NumberFormatException{
		boolean negative = number < 0;
		// 计算 number 长度，负数预留一个符号位
		int length = negative ? 2 : 1;
		int absolute = negative ? -number : number;
		while ((absolute = absolute / 10) != 0) {
			length++;
		}

		char[] chars = new char[length];
		int index = length - 1;
		absolute = negative ? -number : number;
		for (int begin = negative ? 1 : 0; begin <= index; index--) {
			chars[index] = (char) (absolute % 10 + '0');
			absolute = absolute / 10;
		}

		if (negative) {
			chars[0] = '-';
		}

		return LinkedString.valueOf(chars);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LinkedString)) {
			return false;
		}

		LinkedString objString = (LinkedString) obj;
		int length = this.length();
		if (objString.length() != length) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (this.charAt(i) != objString.charAt(i)) {
				return false;
			}
		}

		return true;
	}
}
