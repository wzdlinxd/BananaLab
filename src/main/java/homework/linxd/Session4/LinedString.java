package homework.linxd.Session4;


import java.util.Currency;

/**
 * @Author linxd
 * 这里你自己实现的时候，要去掉 abstract
 */
public class LinedString implements StringInterface {

	private LinkedNode head;
	private LinkedNode hail;
	private int length = 0;

	public void setHead(LinkedNode head) {
		this.head = head;
	}

	public void setHail(LinkedNode hail) {
		this.hail = hail;
	}

	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * 根据数字返回对应的字符串
	 * @param number
	 * @return
	 */
	public static StringInterface valueOf(Integer number) throws NumberFormatException{
		Integer num = number;

		if (num < 0) {
			num = -num;
		}
		//计算数字位数
		int length = 0;
		int[] size = new int[]{9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};
		for (int i = 0; i < size.length; i++) {
			if (num <= size[i]) {
				length = i + 1;
				break;
			}
		}

		LinkedNode head = null;
		LinkedNode hail = null;

		//逐个位拆分
		while (num != 0) {
			char val = (char) (48 + num % 10);
			if (head == null) {
				//第一次添加时
				head = new LinkedNode();
				head.setValue(val);
				hail = head;
			} else {
				LinkedNode next = head;
				head = new LinkedNode();
				head.setValue(val);
				head.setNext(next);
				next.setPrevious(head);
			}
			num = num / 10;
		}

		//负数加符号
		if (number < 0) {
			LinkedNode next = head;
			head = new LinkedNode();
			head.setValue('-');
			head.setNext(next);
			next.setPrevious(head);
			length++;
		}

		LinedString str = new LinedString();
		str.setHead(head);
		str.setHail(hail);
		str.setLength(length);
		return str;
	}

	@Override
	public int length() {
		return this.length;
	}

	@Override
	public char charAt(int position) {
		if (position < 0 || position >= this.length) {
			throw new StringIndexOutOfBoundsException(position + "不符合范围");
		}
		int mid = this.length / 2;
		if (mid > position) {
			//从前往后遍历
			LinkedNode cur = head;
			int index = 0;
			while (cur != null) {
				if (position == index) {
					return cur.getValue();
				}
				index++;
				cur = cur.getNext();
			}
		} else {
			//从后往前遍历
			LinkedNode cur = hail;
			int index = length-1;
			while (cur != null) {
				if (position == index) {
					return cur.getValue();
				}
				index--;
				cur = cur.getPrevious();
			}
		}
		return ' ';
	}

	@Override
	public int indexOf(char[] target) {
		boolean flag = false;
		LinkedNode cur = head;
		for (int i = 0; i <= this.length() - target.length; i++) {
			int k = i;
			LinkedNode temp = cur;
			for (int j = 0; j < target.length; j++) {
				if (temp.getValue() == target[j]) {
					k++;
					temp = temp.getNext();
				} else {
					break;
				}
				if (j == target.length - 1) {
					flag = true;
				}

			}
			if (flag) {
				return k - target.length;
			}
			cur = cur.getNext();
		}
		return -1;
	}

	@Override
	public StringInterface subString(int start, int end) {
		if (start < 0) {
			start = 0;
		}
		if (end > this.length) {
			end = this.length;
		}
		if (this.head == null) {
			return null;
		}
		LinkedNode newhead = null;
		LinkedNode newhail = null;
		LinkedNode cur = head;
		int index = 0;

		//移动到指定节点
		for (int i = 0; i < end; i++) {
			char val = cur.getValue();
			if (i >= start) {
				if (newhead == null) {
					//第一次添加时
					newhead = new LinkedNode();
					newhead.setValue(val);
					newhail = newhead;
				} else {
					LinkedNode newCur = new LinkedNode();
					newCur.setValue(val);
					newhail.setNext(newCur);
					newCur.setPrevious(newhail);
					newhail = newCur;
				}
			}

			cur = cur.getNext();

		}
		LinedString str = new LinedString();
		str.setHead(newhead);
		str.setHail(newhail);
		str.setLength(start - end);
		return str;
	}

	@Override
	public StringInterface reverse() {
		LinkedNode cur = head;
		while (cur != null) {
			LinkedNode next = cur.getNext();
			if (cur == head) {
				cur = next;
				head.setNext(null);
				continue;
			}

			cur.setPrevious(null);
			cur.setNext(head);
			head.setPrevious(cur);
			head = cur;

			cur = next;
		}

		return this;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		LinkedNode cur = head;
		while (cur != null) {
			s.append(cur.getValue());
			cur = cur.getNext();
		}

		return s.toString();
	}

	public static void main(String[] args) {
		StringInterface str = LinedString.valueOf(12345);
		System.out.println(str);
		System.out.println(str.charAt(4));
		System.out.println(str.subString(3,7));
		System.out.println(str.reverse());
		System.out.println(str.indexOf("21".toCharArray()));
	}

	/*
	描述为什么在 JDK 中使用数组而不是链表来实现字符串
		因为数组可以通过下标直接拿到数据，而链表需要从头遍历链表来查询，使用数组查询快，
	而字符串使用查询比使用增删多，所以使用数组会更快些

	描述为什么 String 是一个不可变的类，以及是怎么实现不可变的
		因为由于字符数组是private的，并且没有提供公共方法来修改字符数组，所以在String类的外部无法修改String。也就是说一旦初始化就不能修改。
	此外，字符数组是final的， 也就是说在String类内部，一旦这个值初始化了，字符数组引用类型变量所引用的地址不会改变，即一直引用同一个对象。
	所以可以说String对象是不可变对象
		使用final关键字修饰字符数组，且内部方法不会直接修改它，都是将其内容拷贝到新数组实现
	 */
}
