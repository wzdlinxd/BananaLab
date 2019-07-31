package homework.tonemy.session4;


/**
 * @author tonemy
 *  实现的时候，要去掉 abstract
 */
public class LinkedString implements StringInterface {

	private LinkedNode head = new LinkedNode();
	/**
	 * 初始链表,头插法
	 * @param str
	 */
	public LinkedString(String str) {
		for (int i = str.length() - 1; i >= 0; i --) {
			LinkedNode node = new LinkedNode();
			node.setValue(str.charAt(i));
			node.setPrevious(head);
			if (head.getNext() != null) {
				head.getNext().setPrevious(node);
			}
			node.setNext(head.getNext());
			head.setNext(node);
		}
	}
	public LinkedString(char[] str) {
		for (int i = str.length - 1; i >= 0; i --) {
			LinkedNode node = new LinkedNode();
			node.setValue(str[i]);
			node.setPrevious(head);
			node.setNext(head.getNext());
			head.setNext(node);
		}
	}

	/**
	 * 根据数字返回对应的字符串
	 * @param number
	 * @return
	 */
	public static String valueOf(Integer number)  {
		if (number < Integer.MIN_VALUE || number > Integer.MAX_VALUE) {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		StringBuilder sb = new StringBuilder();
		return sb.append(number).toString();
	}

	/**
	 * 字符串的长度
	 *
	 * @return
	 */
	@Override
	public int length() {
		 int len = 0;
		 LinkedNode node = head.getNext();
		 while (node != null) {
		 	len ++;
		 	node = node.getNext();

		 }
		 return len ;
	}

	/**
	 * 获取第 position 个字符
	 *
	 * @param position
	 * @return
	 */
	@Override
	public char charAt(int position) {
		if(head.getNext() == null) {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		LinkedNode node = head.getNext();
		int curPos = 0;
		while (node != null) {

			if (curPos == position){
				return node.getValue();
			}
			curPos ++;
			node = node.getNext();
		}
 		return 0;
	}

	/**
	 * 根据target 获取第一个匹配的字符串的位置，如果没找到，返回 -1
	 *
	 * @param target
	 * @return
	 */
	@Override
	public int indexOf(char[] target) {

		LinkedString tarLinkedString =new LinkedString(target);
		if (tarLinkedString.length() > this.length()) {
			return -1;
		}
		int[] next = getNext(tarLinkedString, tarLinkedString.length());
		int i = 0, j = 0;
		while ( i < this.length() && j < tarLinkedString.length()  ) {
			if( j == -1 || this.charAt(i) == tarLinkedString.charAt(j)) {
				i ++;
				j ++;
			}else {
				j = next[j];
			}
		}
		if (j == tarLinkedString.length()){
			return i - j;
		}
		return -1;
	}
	public int[] getNext(LinkedString tarLinkedString, int len) {
	//	System.out.println(len);
		int[] next = new int[len];
		int i = 0, j = -1;
		next[0] = -1;
		while (i < len - 1) {
			if(j == -1 || tarLinkedString.charAt(i) == tarLinkedString.charAt(j)) {
				++ i;
				++ j;
			//	System.out.println("i = " + i +" j = " + j);
				next[i] = j;
			}else {
				j = next[j];
			}
		}
		return next;
	}

	/**
	 * 根据开始节点和结束节点，返回新的字符串。
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	@Override
	public String subString(int start, int end) {
		if (start < 0 || start > this.length() || end < 0 || end > this.length()) {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (start == end) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		LinkedNode node = head.getNext();
		int index = 0;
		while (node != null) {
			if(index >= start && index < end) {
				sb.append(node.getValue());

			}
			node = node.getNext();
			index ++;
		}

		return sb.toString();
	}

	/**
	 * 首尾翻转字符串，要求只能占用 O(1) 的额外空间
	 *
	 * @return
	 */
	@Override
	public String reverse() {
		LinkedNode node = head;
		StringBuilder sb = new StringBuilder();
		while (node.getNext() != null) {
			node = node.getNext();
		}
		while (node != head) {
			sb.append(node.getValue());
			node = node.getPrevious();
		}
		return sb.toString();
	}
}
