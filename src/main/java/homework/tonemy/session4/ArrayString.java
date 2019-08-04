package homework.tonemy.session4;


import java.util.Arrays;

/**
 * @author tonemy
 * 实现的时候，要去掉 abstract
 */
public  class ArrayString implements StringInterface {

	char[] chars;
	/**
	 * 初始化
	 */
	public ArrayString(String str) {
		this.chars = str.toCharArray();
	}
	/**
	 * 根据数字返回对应的字符串
	 * @param number
	 * @return
	 */
	public  String  valueOf(Integer number)  {
		if (number < Integer.MIN_VALUE || number > Integer.MAX_VALUE) {
			try {
				// 不在int范围的数字出现异常
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
		return chars.length;
	}

	/**
	 * 获取第 position 个字符
	 *
	 * @param position
	 * @return
	 */
	@Override
	public char charAt(int position) {
		if (position < 0 || position >= chars.length) {
			try {
				//超出数组长度限制异常
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return chars[position];
	}

	/**
	 * 根据target 获取第一个匹配的字符串的位置，如果没找到，返回 -1
	 * 字符串匹配算法
	 * @param target
	 * @return
	 */
	@Override
	public int indexOf(char[] target) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < target.length; i ++) {
			sb.append(target[i]);
		}
		String tarStr = sb.toString();
		String oriStr = this.subString(0, chars.length);
		int[] next = getNext(tarStr);
	 	int i = 0, j = 0;
//		System.out.println(Arrays.toString(next));
//		System.out.println(tarStr.length() + ":" + oriStr.length());
		while (j < tarStr.length() && i < oriStr.length()) {
			if( j == -1 || tarStr.charAt(j) == oriStr.charAt(i)){
				i ++;
				j ++;
			}else {
				j = next[j];
			}
		}
//		System.out.println(j +"," + i);
		if (j == tarStr.length()) {
			return i - j;
		}
		return -1;
	}
	public int[] getNext(String str) {
		char[] charts = str.trim().toCharArray();
		int[] next = new int[charts.length];
		int i = 0, j = -1;
		next[0] = -1;
//		System.out.println( charts.length);
		while (i < charts.length - 1) {
			if(j == -1 || charts[i] == charts[j]) {
				++i;
				++j;
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
	public String  subString(int start, int end) {
		if(start < 0 || start > chars.length || end < 0 || end > chars.length || start > end) {
			try {
				//索引位置异常
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (start == end) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < end; i ++) {
				sb.append(chars[i]);
		}
		return sb.toString();
	}

	/**
	 * 首尾翻转字符串，
	 *
	 * @return
	 */
	@Override
	public String  reverse() {
		for (int i = 0; i < chars.length / 2; i ++) {
			char left = chars[i];
			char right = chars[chars.length - i - 1];
			chars[i] = right;
			chars[chars.length - i - 1] = left;
		}
		return new String(chars);
	}
}
