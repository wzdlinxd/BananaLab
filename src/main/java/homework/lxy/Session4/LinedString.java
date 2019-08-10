package homework.lxy.Session4;


import homework.lxy.utils.StringUtils;

/**
 * @Author 大蕉
 * 这里你自己实现的时候，要去掉 abstract
 */
public class LinedString implements StringInterface {

	private LinkedNode head;

	private int length;

	LinedString(){
		head = new LinkedNode();
	}

	LinedString(char[] chars){
		if(chars == null || chars.length == 0){
			return;
		}
		length = chars.length;
		head = new LinkedNode();
		LinkedNode lastNode = head;
		this.setValue(chars,lastNode);
	}

	/**
	 * 根据数字返回对应的字符串
	 * @param number
	 * @return
	 */
	public static StringInterface valueOf(Integer number) throws NumberFormatException{
		if(number != null){
			int n = number;
			int length = 0;
			boolean isNegative = (n<0);
			int[] intValue = new int[8];
			do{
				intValue[length++] = n%10;
			}while ((n = n /10) > 0);

			char[] chars = new char[isNegative ? length+1 : length];
			for(int i = 0; i<length; i++){
				chars[i] = (char)('0'+intValue[i]);
			}
			if(isNegative){
				chars[length-1] = '-';
			}
			StringUtils.reverseChar(chars);
			return new LinedString(chars);
		}
		return new ArrayString();
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public char charAt(int position) {
		if(position < 0 && position > length){
			throw new StringIndexOutOfBoundsException(position);
		}
		LinkedNode targetNode = head;
		do{
			targetNode = targetNode.getNext();
			position--;
		}while (position >= 0 && targetNode != null);

		return targetNode == null ? '0' : targetNode.getValue();
	}

	@Override
	public int indexOf(char[] target) {
		if(target == null || target.length == 0 || target.length > length){
			return -1;
		}
		char[] srcChars = this.toCharArray();
		int targetLength = target.length;
		int srcLength = length;
		int index = -1;
		for(int i = 0; i < srcLength; i++){
			//没有匹配到第一个字符并且当前字符与目标的第一个字符不相等，跳过
			if(index == -1 && srcChars[i] != target[0]){
				continue;
			}
			//没有匹配到第一个字符并且剩余长度小于target的长度，匹配失败
			if(index == -1 && (srcLength - i) < targetLength){
				return -1;
			}
			char value = this.charAt(i);
			//获取target中应匹配字符
			int j = 0;
			if(index != -1){
				j = i-index;
			}
			char targetValue = target[j];
			//匹配
			if(value == targetValue){
				if(index == -1){
					index = i;
				}
				if(j == targetLength - 1){
					return index;
				}
			}else {
				i = index;//防止重复字符漏匹配，如12123中匹配123
				index = -1;
			}
		}
		return index;
	}

	@Override
	public StringInterface subString(int start, int end) {
		if(start > length
				|| end > length
				|| start > end){
			throw new IllegalArgumentException();
		}
		if(start < 0){
			start = 0;
		}
		int index = -1;
		int times = end-start;
		char[] chars = new char[times];
		LinkedNode currentNode = head;
		while (currentNode != null) {
			currentNode = currentNode.getNext();
			index++;
			if(index >= start && index < end){
				chars[index-start] = currentNode.getValue();
			}
		}
		return new LinedString(chars);
	}

	@Override
	public StringInterface reverse() {
		char[] chars = this.toCharArray();
		StringUtils.reverseChar(chars);
		return new LinedString(chars);
	}

	@Override
	public String toString(){
		return String.valueOf(this.toCharArray());
	}

	private void setValue(char[] chars,LinkedNode head){
		LinkedNode lastNode = head;
		for(int i = 0; i<length; i++){
			LinkedNode currentNode = new LinkedNode();
			currentNode.setValue(chars[i]);
			lastNode.setNext(currentNode);
			currentNode.setPrevious(lastNode);
			lastNode = currentNode;
		}
	}

	private char[] toCharArray(){
		char[] chars = new char[length];
		LinkedNode currentNode = head.getNext();
		for(int i = 0; i<length; i++){
			chars[i] = currentNode.getValue();
			currentNode = currentNode.getNext();
		}
		return chars;
	}

	public static void main(String[] args) {
		StringInterface stringInterface  = valueOf(4321321);
		stringInterface = stringInterface.reverse();
		System.out.println(stringInterface.toString());
		StringInterface newStringInterface = stringInterface.subString(-1,3);
		System.out.println(newStringInterface);

		System.out.println(stringInterface.length());

		System.out.println(stringInterface.indexOf(new char[]{'2','3','4'}));
		System.out.println(newStringInterface.indexOf(new char[]{'2','3','4'}));
	}
}
