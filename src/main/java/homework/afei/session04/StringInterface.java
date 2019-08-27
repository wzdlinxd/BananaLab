package session004;

public interface StringInterface {
	/**
	 * �ַ����ĳ���
	 * @return
	 */
	int length();


	/**
	 * ��ȡ�� position ���ַ�
	 * @param position
	 * @return
	 */
	char charAt(int position);


	/**
	 * ����target ��ȡ��һ��ƥ����ַ�����λ�ã����û�ҵ������� -1
	 * @param target
	 * @return
	 */
	int indexOf(char[] target);


	/**
	 * ���ݿ�ʼ�ڵ�ͽ����ڵ㣬�����µ��ַ�����
	 * @param start
	 * @param end
	 * @return
	 */
	StringInterface subString(int start , int end);


																																			
	/**
	 * ��β��ת�ַ�����Ҫ��ֻ��ռ�� O(1) �Ķ���ռ�
	 * @return
	 */
	StringInterface reverse();




}